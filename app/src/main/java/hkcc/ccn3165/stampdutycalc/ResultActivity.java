package hkcc.ccn3165.stampdutycalc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.android.development.R;

import java.text.DecimalFormat;

public class ResultActivity extends AppCompatActivity {

    // @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // --- Display the result ---
        this.showResult();
    }

    // -----------------------------------------------------------------------------
    // This function will be executed in the onCreate() method.
    // -----------------------------------------------------------------------------
    private void showResult() {
        double amt = 0;
        String exp = "";
        DecimalFormat df = new DecimalFormat("0,000.00");
    // Get the parameter value from the bundle.
        Bundle bundle = this.getIntent().getExtras();
        double propertyValue = Double.parseDouble(bundle.getString("PROPERTY_VALUE"));
        if ((propertyValue > 0) && (propertyValue <= 2000000)) {
            amt = propertyValue * 0.015;
            exp = df.format(propertyValue) + " x 1.5%";
        } else if ((propertyValue > 2000000) && (propertyValue <= 2176470)) {
            amt = 30000 + (propertyValue - 2000000) * 0.2;
            exp = "30,000 + ( " + df.format(propertyValue) + " - 2,0000,000) x 20%";
        } else if ((propertyValue > 2176470) && (propertyValue <= 3000000)) {
            amt = propertyValue * 0.03;
            exp = df.format(propertyValue) + " x 3.0%";
        } else if ((propertyValue > 3000000) && (propertyValue <= 3290330)) {
            amt = 90000 + (propertyValue - 3000000) * 0.2;
            exp = "90,000 + ( " + df.format(propertyValue) + " - 3,0000,000) x 20%";
        } else if ((propertyValue > 3290330) && (propertyValue <= 4000000)) {
            amt = propertyValue * 0.045;
            exp = df.format(propertyValue) + " x 4.5%";
        } else if ((propertyValue > 4000000) && (propertyValue <= 4438580)) {
            amt = 180000 + (propertyValue - 4000000) * 0.2;
            exp = "180,000 + ( " + df.format(propertyValue) + " - 4,0000,000) x 20%";
        } else if ((propertyValue > 4438580) && (propertyValue <= 6000000)) {
            amt = propertyValue * 0.06;
            exp = df.format(propertyValue) + " x 6.0%";
        } else if ((propertyValue > 6000000) && (propertyValue <= 6720000)) {
            amt = 360000 + (propertyValue - 6000000) * 0.2;
            exp = "360,000 + ( " + df.format(propertyValue) + " - 6,0000,000) x 20%";
        } else if ((propertyValue > 6720000) && (propertyValue <= 20000000)) {
            amt = propertyValue * 0.075;
            exp = df.format(propertyValue) + " x 7.5%";
        } else if ((propertyValue > 20000000) && (propertyValue <= 21739130)) {
            amt = 1500000 + (propertyValue - 20000000) * 0.2;
            exp = "1,500,000 + ( " + df.format(propertyValue) + " - 20,0000,000) x 20%";
        } else if ((propertyValue > 21739130)) {
            amt = propertyValue * 0.085;
            exp = df.format(propertyValue) + " x 8.5%";
        }
        // Output the result to the screen
        TextView result = (TextView) findViewById(R.id.tResult);
        result.setText("The stamp duty amount " + df.format(amt) + " is required.");
        TextView rule = (TextView) findViewById(R.id.tRule);
        rule.setText("Rule: " + exp);
    }

    //-----------------------------------------------------------------------------
    // This function will be executed when users press the "Recalculate" button.
    // -----------------------------------------------------------------------------
    public void recalculate(View view) {
        this.finish();
    }

    public void reference(View view) {
        // Uri uri = Uri.parse("http://www.gov.hk/en/residents/taxes/stamp/stamp_duty_rates.htm");
        // Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        Intent intent = new Intent();
        intent.setClass(this, WebviewActivity.class);
        startActivity(intent);
    }
}
