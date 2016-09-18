package in.dreamnation.salesgun;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.NetworkImageView;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import butterknife.ButterKnife;
import butterknife.InjectView;
//import in.dreamnation.salesgun.adapters.SettingsListAdapter;
import in.dreamnation.salesgun.dbmodels.BrandModel;
import in.dreamnation.salesgun.fragments.FragmentDrawer;
import in.dreamnation.salesgun.helpers.AppConfig;
import in.dreamnation.salesgun.helpers.AppController;
import in.dreamnation.salesgun.helpers.CustomRequest;
import in.dreamnation.salesgun.helpers.DatabaseManager;
import in.dreamnation.salesgun.helpers.SessionManager;
import in.dreamnation.salesgun.models.AllBrandInfo;
import in.dreamnation.salesgun.models.Setting;


public class SettingsActivity extends AppCompatActivity  {
    public static final String LOG_TAG = "SettingsActivity";
    public boolean networkAvailable = false;

    SessionManager session;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.inject(this);

        // Session Manager
        session = new SessionManager(getApplicationContext());



        class AllBrandInfoViewHolder extends RecyclerView.ViewHolder {

            TextView UserName;

            Button Update;
            TextView UserEmail;
            TextView Back;

            public AllBrandInfoViewHolder(View v) {
                super(v);
                UserName = (TextView) v.findViewById(R.id.input_name);
                UserEmail = (TextView) v.findViewById(R.id.input_email);


                Update.setOnClickListener(_UpdateButton);

            }

            DatabaseManager db = new DatabaseManager(getApplicationContext());

            View.OnClickListener _UpdateButton = new View.OnClickListener() {

                @Override
                public void onClick(View v) {


                    try {
                        new checkNetworkConnection().execute().get(100000, TimeUnit.MILLISECONDS);
                    } catch (InterruptedException e1) {
                        //  Auto-generated catch block

                        e1.printStackTrace();
                    } catch (ExecutionException e1) {
                        //  Auto-generated catch block
                        e1.printStackTrace();
                    } catch (TimeoutException e1) {
                        //  Auto-generated catch block
                        e1.printStackTrace();
                    }

                    Log.e("skp", "after done networkavailable= " + networkAvailable);
                    if (networkAvailable) {
                        String name = UserName.getText().toString();
                        String email = UserEmail.getText().toString();
                        //int userId = db.getAllUsers().get(0).getUserId();
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("action", "storeUser");
                        params.put("validRequest", "$m@rt");
                        params.put("userid", "1");
                        //params.put("userid", Integer.toString(userId));


                        params.put("name", name);
                        params.put("email", email);

                        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, AppConfig.URL_SG_DB, params, new Response.Listener<JSONArray>() {

                            @Override
                            public void onResponse(JSONArray response) {
                                Log.e("Responseeee: ", response.toString());
                                for (int i = 0; i < response.length(); i++) {
                                    Log.e("testlen", Integer.toString(response.length()));
                                    try {
                                        JSONObject jObj = response.getJSONObject(i);
                                        Log.e("test call", "Call successful");
                                        Log.e("test call", "jsonobject: " + jObj);



                                    } catch (JSONException e) {
                                        // JSON error
                                        Log.e("test call", "Call error");
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError response) {
                                Toast.makeText(getApplicationContext(),
                                        "error occurred", Toast.LENGTH_LONG).show();

                                Log.e("test call", "error1");

                            }
                        });
                        AppController.getInstance().addToRequestQueue(jsObjRequest);
                    } else {
                        Log.e("test call", "network unavailable");

                    }
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);

                }
            };





        }

    }




    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }



    private class checkNetworkConnection extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected Void doInBackground(Void... params) {
            //  Auto-generated method stub
            Log.e("skp", "do in bg");
            ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(android.content.Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            boolean isNetworkAvailable = activeNetworkInfo != null;


            if (isNetworkAvailable) {
                try {
                    HttpURLConnection urlc = (HttpURLConnection) (new URL("http://www.google.com").openConnection());
                    urlc.setRequestProperty("User-Agent", "Test");
                    urlc.setRequestProperty("Connection", "close");
                    urlc.setConnectTimeout(1500);
                    urlc.connect();
                    networkAvailable = (urlc.getResponseCode() == 200);
                    Log.e("skp", "done1 networkavailable= " + networkAvailable);
                    return null;
                } catch (IOException e) {
                    Log.e(LOG_TAG, "Error checking internet connection", e);
                }
            } else {
                Log.d(LOG_TAG, "No network available!");
            }
            networkAvailable = false;
            Log.e("skp", "done2 networkavailable= " + networkAvailable);
            return null;
        }

    }

}


