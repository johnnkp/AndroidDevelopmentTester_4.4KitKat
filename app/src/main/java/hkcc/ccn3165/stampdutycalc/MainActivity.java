package hkcc.ccn3165.stampdutycalc;

import android.content.Intent;
import android.support.v7.app.*;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

import com.android.development.R;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    // Define an EditText object
    private EditText tPropertyValue;

    // @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stamp_duty);

        // Get the field value - Property Value
        tPropertyValue = (EditText) findViewById(R.id.tPropertyValue);
    }

    // --------------------------------------------------------
    // Calculate the stamp duty value
    // --------------------------------------------------------
    public void calculate(View view) {
        double propertyValue = 0;
        double amt = 0;
        String exp = "";

        // If the text field is empty, show a message
        if (tPropertyValue.getText().length() == 0) {
            // Toast = focused floating view that will be shown over the main activity
            Toast.makeText(this, "Please enter a valid value.", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent();
            intent.setClass(this, ResultActivity.class);
            // Put values to the bundle
            Bundle bundle = new Bundle();
            bundle.putString("PROPERTY_VALUE", tPropertyValue.getText().toString());
            intent.putExtras(bundle);
            // Open another page
            startActivity(intent);
        }
    }

    public void openDialog(View view) {
        new AlertDialog.Builder(this)
                .setTitle(R.string.dialog_title)
                .setMessage(R.string.dialog_msg)
                .show();
    }

    public void openDialog() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.dialog_title)
                .setMessage(R.string.dialog_msg)
                .show();
    }

    protected static final int MENU_CREDIT = Menu.FIRST;
    protected static final int MENU_EXIT = Menu.FIRST + 1;

    // @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, MENU_CREDIT, 0, "Credit");
        menu.add(0, MENU_EXIT, 0, "Exit");
        return true;
    }

    // @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_CREDIT:
                this.openDialog();
                break;
            case MENU_EXIT:
                // this.finish();
                System.exit(0);
                // break;
        }
        return super.onOptionsItemSelected(item);
    }
}
