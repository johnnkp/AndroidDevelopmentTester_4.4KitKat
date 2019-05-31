package hkcc.ccn3165.sendemail;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.development.R;

public class SendEmail extends Activity {

    // @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sendemail);
        Button startBtn = (Button) findViewById(R.id.sendEmail);
        startBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendEmail();
            }
        });
    }

    /*
     * ----------------------------------
     * Method: Sending Emails
     * ----------------------------------
     */
    protected void sendEmail() {
        Log.i("Send email", "");
        // remember to replace this sample data by a real email address
        String[] TO = {"your first email address"};
        // remember to replace this sample data by a real email address
        String[] CC = {"your second email address"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Email Subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Please input your message here.");
        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail ..."));
            finish();
            Log.i("Finished sending email.", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(SendEmail.this,
                    "Sorry! No email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}