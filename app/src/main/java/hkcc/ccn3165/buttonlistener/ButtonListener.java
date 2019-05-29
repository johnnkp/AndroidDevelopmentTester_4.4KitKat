package hkcc.ccn3165.buttonlistener;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.development.R;

public class ButtonListener extends AppCompatActivity {
    private Button btnN1, btnN2, btnN3, btnN4, btnN5, btnN6;
    private String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button_listener);

        btnN1 = (Button) findViewById(R.id.btnN1);
        btnN2 = (Button) findViewById(R.id.btnN2);
        btnN3 = (Button) findViewById(R.id.btnN3);
        btnN4 = (Button) findViewById(R.id.btnN4);
        btnN5 = (Button) findViewById(R.id.btnN5);
        btnN6 = (Button) findViewById(R.id.btnN6);
        btnN1.setOnClickListener(listener);
        btnN2.setOnClickListener(listener);
        btnN3.setOnClickListener(listener);
        btnN4.setOnClickListener(listener);
        btnN5.setOnClickListener(listener);
        btnN6.setOnClickListener(listener);
    }

    private Button.OnClickListener listener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnN1:
                    str = btnN1.getText().toString();
                    display(str);
                    break;
                case R.id.btnN2:
                    str = btnN2.getText().toString();
                    display(str);
                    break;
                case R.id.btnN3:
                    str = btnN3.getText().toString();
                    display(str);
                    break;
                case R.id.btnN4:
                    str = btnN4.getText().toString();
                    display(str);
                    break;
                case R.id.btnN5:
                    str = btnN5.getText().toString();
                    display(str);
                    break;
                case R.id.btnN6:
                    str = btnN6.getText().toString();
                    display(str);
                    break;
            }
        }
    };

    private void display(String s) {
        Drawable ic_launcher = getResources().getDrawable(R.drawable.ic_launcher);
        Bitmap ic = ((BitmapDrawable) ic_launcher).getBitmap();
        Drawable icon = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(ic, getActionBarSize(), getActionBarSize(), true));

        new AlertDialog.Builder(ButtonListener.this)
                .setTitle("Dialog Box")
                .setIcon(icon)
                .setMessage("You have pressed " + str + "!\nPlease press Okay to close this dialog")
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                    }
                })
                .show();

        Toast toast = Toast.makeText(getApplicationContext(),
                "You have pressed " + str + "!", Toast.LENGTH_LONG);
        Configuration config = getResources().getConfiguration();

        // Checks the orientation of the screen
        if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            toast.setGravity(Gravity.CENTER, 0, 180);
        } else if (config.orientation == Configuration.ORIENTATION_PORTRAIT) {
            toast.setGravity(Gravity.CENTER, 0, 0);
        }

        toast.show();
    }

    // http://blog.moagrius.com/android/android-get-system-defined-actionbar-size-height
    public int getActionBarSize() {
        TypedArray styledAttributes = getTheme().obtainStyledAttributes(new int[]{android.R.attr.actionBarSize});
        int actionBarSize = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();
        return actionBarSize;
    }
}
