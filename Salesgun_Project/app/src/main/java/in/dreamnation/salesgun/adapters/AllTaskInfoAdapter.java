package in.dreamnation.salesgun.adapters;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import in.dreamnation.salesgun.R;
import in.dreamnation.salesgun.anim.AnimationUtils;
import in.dreamnation.salesgun.helpers.AppConfig;
import in.dreamnation.salesgun.helpers.AppController;
import in.dreamnation.salesgun.helpers.CustomRequest;
import in.dreamnation.salesgun.helpers.DatabaseManager;
import in.dreamnation.salesgun.models.AllBrandInfo;
import in.dreamnation.salesgun.models.AllTaskInfo;


public class AllTaskInfoAdapter extends RecyclerView.Adapter<AllTaskInfoAdapter.AllTaskInfoViewHolder> {

    List<AllTaskInfo> TaskList = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;
    //keep track of the previous position for animations where scrolling down requires a different animation compared to scrolling up
    private int mPreviousPosition = 0;

    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    DatabaseManager db;
    public boolean networkAvailable = false;
    private static final String LOG_TAG = "AllTaskInfoAdapter";

    public AllTaskInfoAdapter(Context context, List<AllTaskInfo> taskList) {
        this.context = context;
        Log.e("skp","context is " + context);
        inflater = LayoutInflater.from(context);
        this.TaskList = taskList;

        db = new DatabaseManager(context);
    }





    @Override
    public int getItemCount() {
        return TaskList.size();
    }

    @Override
    public void onBindViewHolder(AllTaskInfoViewHolder taskViewHolder, int i) {
        AllTaskInfo ci = TaskList.get(i);

       taskViewHolder.TaskBrandName.setText(ci.name);
        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        taskViewHolder.TaskBrandImage.setImageResource(ci.icon);
        taskViewHolder.TaskDescription.setText(ci.description);
        taskViewHolder.TaskStatus.setBackgroundResource(ci.status);
        taskViewHolder.TaskStatus.setTag(ci.status);
        taskViewHolder.AllTaskCredits.setText(ci.credits);
        taskViewHolder.TaskId.setText(ci.taskId);



        if (i > mPreviousPosition) {
            AnimationUtils.animateSunblind(taskViewHolder, true);

        } else {
            AnimationUtils.animateSunblind(taskViewHolder, false);

        }
        mPreviousPosition = i;
    }

    @Override
    public AllTaskInfoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.all_tasks_card_layout, viewGroup, false);
        AllTaskInfoViewHolder holder = new AllTaskInfoViewHolder(view);
        return holder;
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
            ConnectivityManager connectivityManager  = (ConnectivityManager) context.getSystemService(android.content.Context.CONNECTIVITY_SERVICE);
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
    class AllTaskInfoViewHolder extends RecyclerView.ViewHolder {

        TextView TaskBrandName;
        TextView TaskDescription;
        ImageView TaskBrandImage;
        Button TaskStatus;
        TextView AllTaskCredits;
        TextView TaskId;
        TextView TaskAmount;
        TextView TaskQuantity;


        public AllTaskInfoViewHolder(View v) {
            super(v);
            TaskId =  (TextView) v.findViewById(R.id.TaskId);
            TaskBrandName =  (TextView) v.findViewById(R.id.TaskBrandName);
            TaskBrandImage = (ImageView)  v.findViewById(R.id.TaskBrandImage);
            TaskDescription = (TextView) v.findViewById(R.id.TaskDescription);
            TaskStatus = (Button)v.findViewById(R.id.TaskAddImage);
            TaskStatus.setOnClickListener(imgButtonHandler);
            AllTaskCredits = (TextView) v.findViewById(R.id.AllTaskCredits);
            TaskAmount =  (TextView) v.findViewById(R.id.AllTaskAmount);
            TaskQuantity =  (TextView) v.findViewById(R.id.AllTaskQuantity);


        }

        View.OnClickListener imgButtonHandler = new View.OnClickListener() {

            public void onClick(View v) {
                Log.e("img", "BrandStatus.getBg(): " + TaskStatus.getBackground());
                Log.e("img", "ContextCompat.getDrawable(this, R.drawable.your_drawable): " + ContextCompat.getDrawable(context, R.drawable.add1));
                Log.e("img", "BrandStatus.getTag().equals(R.drawable.add1): " + TaskStatus.getTag().equals(R.drawable.add1));
                if(TaskStatus.getTag().equals(R.drawable.add1))
                {


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
                        DatabaseManager db;
                        String s = TaskId.getText().toString();
                        String amount = TaskAmount.getText().toString();
                        String quantity= TaskQuantity.getText().toString();

                        //int userId = db.getAllUsers().get(0).getUserId();
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("action", "saveUserTask");
                        params.put("validRequest", "$m@rt");

                        //params.put("userid", Integer.toString(userId));
                        params.put("userid", "1");
                        params.put("taskid",s);
                        params.put("amount", amount);
                        params.put("quantity", quantity);

                        Log.e("reqtaskid: ", s);


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
                                        TaskStatus.setBackgroundResource(R.drawable.tick);


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
                                Toast.makeText(context,
                                        "error occurred", Toast.LENGTH_LONG).show();

                                Log.e("test call", "error1");

                            }
                        });
                        AppController.getInstance().addToRequestQueue(jsObjRequest);
                    }
                    else
                    {
                        Log.e("test call", "network unavailable");

                    }
                }
            }
        };
    }
}



