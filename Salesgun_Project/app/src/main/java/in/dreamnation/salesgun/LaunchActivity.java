package in.dreamnation.salesgun;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class LaunchActivity extends Activity {
    public static final String SMS = "sms";
    public static final String FLASHCALL = "flashcall";
    public static final String INTENT_PHONENUMBER = "phonenumber";
    public static final String INTENT_METHOD = "method";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_launch);

        TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        TextView phoneNumber = (TextView) findViewById(R.id.input_mobile_number);
        phoneNumber.setText(manager.getLine1Number());
    }

    private void openActivity(String phoneNumber, String method) {
        Intent verification = new Intent(this, VerificationActivity.class);
        verification.putExtra(INTENT_PHONENUMBER, phoneNumber);
        verification.putExtra(INTENT_METHOD, method);
        startActivity(verification);
    }

    private boolean checkInput() {
        TextView phoneNumber = (TextView) findViewById(R.id.input_mobile_number);
        if (phoneNumber.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please input a phone number.", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public void onButtonClicked(View view) {
        if (checkInput()) {
            TextView phoneNumber = (TextView) findViewById(R.id.input_mobile_number);
            if (view == findViewById(R.id.btn_sms_verification)) {
                openActivity(phoneNumber.getText().toString(), SMS);
            } else if (view == findViewById(R.id.btn_call_verification)) {
                openActivity(phoneNumber.getText().toString(), FLASHCALL);
            }
        }
    }
}
