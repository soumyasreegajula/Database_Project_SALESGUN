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
import in.dreamnation.salesgun.models.OngoingTaskInfo;


public class OngoingTaskInfoAdapter  extends RecyclerView.Adapter<OngoingTaskInfoAdapter.OngoingTaskInfoViewHolder> {

    List<OngoingTaskInfo> TaskList = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;
    //keep track of the previous position for animations where scrolling down requires a different animation compared to scrolling up
    private int mPreviousPosition = 0;

    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    DatabaseManager db;
    public boolean networkAvailable = false;
    private static final String LOG_TAG = "OngoingTaskInfoAdapter";

    public OngoingTaskInfoAdapter(Context context, List<OngoingTaskInfo> taskList) {
        this.context = context;
        Log.e("skp", "context is " + context);
        inflater = LayoutInflater.from(context);
        this.TaskList = taskList;
    }


    @Override
    public int getItemCount() {
        return TaskList.size();
    }

    @Override
    public void onBindViewHolder(OngoingTaskInfoViewHolder taskViewHolder, int i) {
        OngoingTaskInfo ci = TaskList.get(i);
        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        taskViewHolder.TaskName.setText(ci.name);

        taskViewHolder.TaskDescription.setText(ci.description);
        taskViewHolder.TaskCredits.setText(ci.credits);


        if (i > mPreviousPosition) {
            AnimationUtils.animateSunblind(taskViewHolder, true);

        } else {
            AnimationUtils.animateSunblind(taskViewHolder, false);

        }
        mPreviousPosition = i;
    }

    @Override
    public OngoingTaskInfoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.ongoing_tasks_card_layout, viewGroup, false);
        OngoingTaskInfoViewHolder holder = new OngoingTaskInfoViewHolder(view);
        return holder;
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
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(android.content.Context.CONNECTIVITY_SERVICE);
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


    class OngoingTaskInfoViewHolder extends RecyclerView.ViewHolder {
        TextView TaskId;
        TextView TaskBrandName;
        TextView TaskName;
        TextView TaskDescription;
        ImageView TaskBrandImage;
        TextView TaskCredits;
        TextView TaskStatus;
        TextView TaskAmount;
        TextView TaskQuantity;


        public OngoingTaskInfoViewHolder(View v) {
            super(v);
            TaskId = (TextView) v.findViewById(R.id.TaskId);
            TaskName = (TextView) v.findViewById(R.id.OngoingTaskBrandName);
            TaskAmount = (TextView) v.findViewById(R.id.AllTaskAmount);
            TaskQuantity = (TextView) v.findViewById(R.id.AllTaskQuantity);
            //TaskStatus = (TextView) v.findViewById(R.id.TaskStatus);

           // TaskBrandImage = (ImageView) v.findViewById(R.id.OngoingTaskBrandImage);
            TaskDescription = (TextView) v.findViewById(R.id.OngoingTaskDescription);
            TaskCredits = (TextView) v.findViewById(R.id.OngoingTaskCredits);

        }
    }

}

