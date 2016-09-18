package in.dreamnation.salesgun.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.res.TypedArray;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

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

import in.dreamnation.salesgun.R;
import in.dreamnation.salesgun.adapters.MyBrandInfoAdapter;
import in.dreamnation.salesgun.dbmodels.BrandModel;
import in.dreamnation.salesgun.helpers.AppConfig;
import in.dreamnation.salesgun.helpers.AppController;
import in.dreamnation.salesgun.helpers.CustomRequest;
import in.dreamnation.salesgun.helpers.DatabaseManager;
import in.dreamnation.salesgun.models.AllBrandInfo;
import in.dreamnation.salesgun.models.MyBrandInfo;


public class MyBrandsFragment extends Fragment {

    public static final String ARG_SECTION_NUMBER = "section_number";
    private static TypedArray navMenuIcons;
    private static TypedArray navStatusIcons;

    DatabaseManager db;
    public boolean networkAvailable = false;
    private ProgressDialog pDialog;
    public List<MyBrandInfo> MyBrandInfoList;
    public List<BrandModel> MyBrandsList;
    private static final String LOG_TAG = "MyBrandsFragment";
    MyBrandInfoAdapter ca;



    public MyBrandsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_menu_icons);
        navStatusIcons =  getResources().obtainTypedArray(R.array.nav_drawer_status_icons);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_brands_my, container, false);

        RecyclerView recList = (RecyclerView) rootView.findViewById(R.id.myCardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(rootView.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        MyBrandInfoList = new ArrayList<MyBrandInfo>();
        MyBrandsList = new ArrayList<BrandModel>();

        ca = new MyBrandInfoAdapter(rootView.getContext(), MyBrandInfoList);
        recList.setAdapter(ca);

        db = new DatabaseManager(getActivity().getApplicationContext());

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
        if(networkAvailable)
        {
            Log.e("test call", "networkavailable");
            pDialog = new ProgressDialog(getActivity());
            // Showing progress dialog before making http request
            pDialog.setMessage("Loading...");
            pDialog.show();

            //db.upgradeServicesTable(db.getDatabase());

            db.onUpgrade(db.getDatabase(), 1,1);

            //Log.e("skp", "db brands count= " + );

            Map<String, String> params = new HashMap<String, String>();
            params.put("action", "getUserBrands");
            params.put("validRequest", "$m@rt");
            params.put("userid", "1");

            CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, AppConfig.URL_SG_DB, params, new Response.Listener<JSONArray>() {

                @Override
                public void onResponse(JSONArray response) {
                    hidePDialog();
                    Log.d("Responseeee: ", response.toString());
                    for (int i = 0; i < response.length(); i++) {

                        try {
                            JSONObject jObj = response.getJSONObject(i);
                            Log.e("test call", "Call successful");
                            Log.e("test call", "jsonobject: " + jObj);

                            BrandModel brand = new BrandModel();
                            brand.setBrandId(Integer.parseInt(jObj.getString("BrandId")));

                            brand.setBrandName(jObj.getString("BrandName"));
                            brand.setBrandImage(jObj.getString("Logo"));
                            brand.setBrandDescription(jObj.getString("Description"));
                            brand.setBrandTagLine(jObj.getString("TagLine"));


                            MyBrandInfo model = new MyBrandInfo();
                            model.brandImage = brand.getBrandImage();
                            model.name = brand.getBrandName();
                            model.credits = "My Brand Credits = "+ jObj.getString("Credits");
                            model.numberOfTasksInProgress = "Tasks in Progress = " + jObj.getString("TasksInProgress");;
                            model.description = brand.getBrandDescription();
                            model.brandTag = brand.getBrandTagLine();
                            if(brand.getBrandStatus() == 1)
                            {
                                model.status = navStatusIcons.getResourceId(1, -1);
                            }
                            else
                            {
                                model.status = navStatusIcons.getResourceId(0, -1);
                            }



                            MyBrandInfoList.add(model);

                            db.createBrand(brand);


                        } catch (JSONException e) {
                            // JSON error
                            Log.e("test call", "Call error");
                            e.printStackTrace();
                        }
                    }
                    ca.notifyDataSetChanged();

                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError response) {
                    hidePDialog();
                    Toast.makeText(getActivity().getApplicationContext(),
                            "error occurred", Toast.LENGTH_LONG).show();

                    Log.e("test call", "error1");
                    MyBrandInfoList.clear();
                    MyBrandsList = db.getAllBrands();

                    List<MyBrandInfo> tempList = new ArrayList<MyBrandInfo>();
                    for(int i=1; i<= MyBrandsList.size(); i++)
                    {
                        BrandModel b = MyBrandsList.get(i);
                        MyBrandInfo a = new MyBrandInfo();
                        a.brandImage = b.getBrandImage();
                        a.name = b.getBrandName();
                        a.description = b.getBrandDescription();
                        a.brandTag = b.getBrandTagLine();
                        tempList.add(a);
                    }


                    MyBrandInfoList.addAll(tempList);
                    ca.notifyDataSetChanged();
                    Log.d("Response: ", response.toString());
                }
            });
            AppController.getInstance().addToRequestQueue(jsObjRequest);
        }
        else
        {
            Log.e("test call", "network unavailable");
            MyBrandInfoList.clear();
            MyBrandsList = db.getAllUserBrands();


            List<MyBrandInfo> tempList = new ArrayList<MyBrandInfo>();
            for(int i=1; i<= MyBrandsList.size(); i++)
            {
                BrandModel b = MyBrandsList.get(i);
                MyBrandInfo a = new MyBrandInfo();
                a.brandImage = b.getBrandImage();
                a.name = b.getBrandName();
                a.description = b.getBrandDescription();
                a.brandTag = b.getBrandTagLine();
                tempList.add(a);
            }


            MyBrandInfoList.addAll(tempList);
            ca.notifyDataSetChanged();

        }


        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }



    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    private class checkNetworkConnection extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected Void doInBackground(Void... params) {
            //  Auto-generated method stub
            Log.e("skp", "do in bg");
            ConnectivityManager connectivityManager  = (ConnectivityManager) getActivity().getSystemService(android.content.Context.CONNECTIVITY_SERVICE);
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

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }
    }

}