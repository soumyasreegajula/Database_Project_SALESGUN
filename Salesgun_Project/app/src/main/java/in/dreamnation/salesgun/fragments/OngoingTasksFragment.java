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
import in.dreamnation.salesgun.adapters.OngoingTaskInfoAdapter;
import in.dreamnation.salesgun.dbmodels.BrandModel;
import in.dreamnation.salesgun.dbmodels.TaskModel;
import in.dreamnation.salesgun.helpers.AppConfig;
import in.dreamnation.salesgun.helpers.AppController;
import in.dreamnation.salesgun.helpers.CustomRequest;
import in.dreamnation.salesgun.helpers.DatabaseManager;
import in.dreamnation.salesgun.models.AllTaskInfo;
import in.dreamnation.salesgun.models.MyBrandInfo;
import in.dreamnation.salesgun.models.OngoingTaskInfo;


public class OngoingTasksFragment extends Fragment {

    public static final String ARG_SECTION_NUMBER = "section_number";
    private static TypedArray navMenuIcons;
    private static TypedArray navStatusIcons;
    DatabaseManager db;
    public boolean networkAvailable = false;
    private ProgressDialog pDialog;
    public List<OngoingTaskInfo> OngoingTaskInfoList;
    public List<TaskModel> OngoingTaskList;
    private static final String LOG_TAG = "OngoingTasksFragment";
    OngoingTaskInfoAdapter ca;

    public OngoingTasksFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_tasks_ongoing, container, false);

        RecyclerView recList = (RecyclerView) rootView.findViewById(R.id.ongoingTasksCardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(rootView.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        OngoingTaskInfoList = new ArrayList<OngoingTaskInfo>();
        OngoingTaskList = new ArrayList<TaskModel>();

        ca = new OngoingTaskInfoAdapter(rootView.getContext(), OngoingTaskInfoList);
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
            params.put("action", "getUserTasks");
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

                            TaskModel task = new TaskModel();
                            task.setTaskId(Integer.parseInt(jObj.getString("TaskId")));

                            task.setTaskName(jObj.getString("TaskName"));
                            //task.setBrandImage(jObj.getString("Logo"));
                            task.setTaskDescription(jObj.getString("Description"));
                            task.setTaskCredits(jObj.getInt("Credits"));

                            OngoingTaskInfo model = new OngoingTaskInfo();
                           // model.brandImage = brand.getBrandImage();
                            model.name = task.getTaskName();
                            model.credits = jObj.getString("Credits");
                           // model.numberOfTasksInProgress = jObj.getString("TasksInProgress");;
                            model.description = task.getTaskDescription();
                            model.credits = Integer.toString(task.getTaskCredits());
                           // model.brandTag = brand.getBrandTagLine();
                            if(task.getTaskStatus() == 1)
                            {
                                model.status = navStatusIcons.getResourceId(1, -1);
                            }
                            else
                            {
                                model.status = navStatusIcons.getResourceId(0, -1);
                            }



                            OngoingTaskInfoList.add(model);

                            db.createTask(task);


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
                    OngoingTaskInfoList.clear();
                    OngoingTaskList  = db.getAllTasks();

                    List<OngoingTaskInfo> tempList = new ArrayList<OngoingTaskInfo>();
                    for(int i=1; i<= OngoingTaskList.size(); i++)
                    {
                        TaskModel b = OngoingTaskList.get(i);
                        OngoingTaskInfo a = new OngoingTaskInfo();
                       // a.brandImage = b.getBrandImage();
                        a.id=b.getTaskId();
                        a.name = b.getTaskName();
                        a.description = b.getTaskDescription();
                        a.credits = Integer.toString(b.getTaskCredits());
                        tempList.add(a);
                    }


                    OngoingTaskInfoList.addAll(tempList);
                    ca.notifyDataSetChanged();
                    Log.d("Response: ", response.toString());
                }
            });
            AppController.getInstance().addToRequestQueue(jsObjRequest);
        }
        else
        {
            Log.e("test call", "network unavailable");
            OngoingTaskInfoList.clear();
            OngoingTaskList = db.getAllUserTasks();


            List<OngoingTaskInfo> tempList = new ArrayList<OngoingTaskInfo>();
            for(int i=1; i<= OngoingTaskList.size(); i++)
            {
                TaskModel b = OngoingTaskList.get(i);
                OngoingTaskInfo a = new OngoingTaskInfo();
                //a.brandImage = b.getBrandImage();
                a.name = b.getTaskName();
                a.description = b.getTaskDescription();
                a.credits = Integer.toString(b.getTaskCredits());
                tempList.add(a);
            }


            OngoingTaskInfoList.addAll(tempList);
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
    public void onDetach() { super.onDetach(); }



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