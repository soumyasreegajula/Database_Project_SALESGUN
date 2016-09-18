package in.dreamnation.salesgun;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.sinch.verification.Config;
import com.sinch.verification.SinchVerification;
import com.sinch.verification.Verification;
import com.sinch.verification.VerificationListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import in.dreamnation.salesgun.dbmodels.BrandModel;
import in.dreamnation.salesgun.dbmodels.UserModel;
import in.dreamnation.salesgun.helpers.AppConfig;
import in.dreamnation.salesgun.helpers.AppController;
import in.dreamnation.salesgun.helpers.CustomRequest;
import in.dreamnation.salesgun.helpers.DatabaseManager;
import in.dreamnation.salesgun.helpers.SessionManager;
import in.dreamnation.salesgun.models.AllBrandInfo;


public class VerificationActivity extends Activity {

    private static final String TAG = Verification.class.getSimpleName();
    private final String APPLICATION_KEY = "c96c4f15-7114-43d2-bc0e-1d8f80250b3b";

    // Session Manager Class
    SessionManager session;
    DatabaseManager db;

    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressIndicator);
        progressBar.setVisibility(View.VISIBLE);

        // Session Manager
        session = new SessionManager(getApplicationContext());

        db = new DatabaseManager(getApplicationContext());


        Intent intent = getIntent();
        if (intent != null) {
            String phoneNumber = intent.getStringExtra(LaunchActivity.INTENT_PHONENUMBER);
            String method = intent.getStringExtra(LaunchActivity.INTENT_METHOD);
            TextView phoneText = (TextView) findViewById(R.id.numberText);
            phoneText.setText(phoneNumber);
            phone = phoneNumber;
            Log.e("phone", phoneNumber + " " + method);
            createVerification(phoneNumber, method);
        }

    }

    void createVerification(String phoneNumber, String method) {
        Config config = SinchVerification.config().applicationKey(APPLICATION_KEY).context(getApplicationContext())
                .build();
        VerificationListener listener = new MyVerificationListener();
        Verification verification;
        if (method.equalsIgnoreCase(LaunchActivity.SMS)) {
            verification = SinchVerification.createSmsVerification(config, phoneNumber, listener);
        } else {
            TextView messageText = (TextView) findViewById(R.id.textView);
            messageText.setText(R.string.flashcalling);
            verification = SinchVerification.createFlashCallVerification(config, phoneNumber, listener);
        }
        verification.initiate();
    }

    class MyVerificationListener implements VerificationListener {

        @Override
        public void onInitiated() {
            Log.d(TAG, "Initialized!");
        }

        @Override
        public void onInitiationFailed(Exception exception) {
            Log.e(TAG, "Verification initialization failed: " + exception.getMessage());
            hideProgress(R.string.failed, false);
            //to be removed
            onVerified();
        }

        @Override
        public void onVerified() {
            Log.d(TAG, "Verified!");
            hideProgress(R.string.verified, true);
            session.createLoginSession(phone);

            db.onUpgrade(db.getDatabase(), 1, 1);

            DateFormat Created_date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();


            Map<String, String> params = new HashMap<String, String>();
            params.put("action", "saveUser");
            params.put("validRequest", "$m@rt");

            params.put("phone",phone);
            params.put("createdat",Created_date.format(cal.getTime()));


            CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, AppConfig.URL_SG_DB, params, new Response.Listener<JSONArray>() {

                @Override
                public void onResponse(JSONArray response) {
                    Log.d("Responseeee: ", response.toString());
                    for (int i = 0; i < response.length(); i++) {

                        try {
                            JSONObject jObj = response.getJSONObject(i);
                            Log.e("test call", "Call successful");
                            Log.e("test call", "jsonobject: " + jObj);

                            UserModel user = new UserModel();
                            user.setUserId(Integer.parseInt(jObj.getString("userid")));
                            user.setUserName(jObj.getString("name"));
                            user.setEmail(jObj.getString("email"));
                            user.setPhone(jObj.getString("phone"));

                            db.createUser(user);


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
                    Log.d("Response: ", response.toString());
                }
            });
            AppController.getInstance().addToRequestQueue(jsObjRequest);


            Intent intent = new Intent(VerificationActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        @Override
        public void onVerificationFailed(Exception exception) {
            Log.e(TAG, "Verification failed: " + exception.getMessage());
            hideProgress(R.string.failed, false);
        }
    }

    void hideProgress(int message, boolean success) {
        if (success) {
            ImageView checkMark = (ImageView) findViewById(R.id.checkmarkImage);
            checkMark.setVisibility(View.VISIBLE);
        }
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressIndicator);
        progressBar.setVisibility(View.INVISIBLE);
        TextView progressText = (TextView) findViewById(R.id.progressText);
        progressText.setVisibility(View.INVISIBLE);
        TextView messageText = (TextView) findViewById(R.id.textView);
        messageText.setText(message);
    }

}
