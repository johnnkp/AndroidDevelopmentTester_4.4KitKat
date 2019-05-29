package hkcc.ccn3165.temperature;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.development.R;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Temperature extends AppCompatActivity {
    // Declare global variables
    private EditText edtFah;
    private TextView txtCel;
    private Button btnConv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temperature);

        // Get the id of interface elements from R
        edtFah = (EditText) findViewById(R.id.edtFah);
        txtCel = (TextView) findViewById(R.id.txtCel);
        btnConv = (Button) findViewById(R.id.btnConv);

        // Set button element Click Event Listener to btnConvListener
        btnConv.setOnClickListener(btnConvListener);
    }

    // Define onClick() method
    private Button.OnClickListener btnConvListener = new Button.OnClickListener() {
        public void onClick(View v) {
            double Fah = Double.parseDouble(edtFah.getText().toString());
            double Cel = (Fah - 32) * 5 / 9;

            BigDecimal decimal = new BigDecimal(Cel);
            decimal = decimal.setScale(2, RoundingMode.HALF_UP);
            String result = decimal.toString();

            txtCel.setText(getResources().getString(R.string.str_result1) + result + getResources().getString(R.string.str_result2));
        }
    };
}