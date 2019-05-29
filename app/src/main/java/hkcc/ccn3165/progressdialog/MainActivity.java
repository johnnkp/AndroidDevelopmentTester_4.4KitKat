package hkcc.ccn3165.progressdialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.development.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_dialog);

        Button portProgressDialog = findViewById(R.id.portprogressdialog);
        portProgressDialog.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ProgressDialog(MainActivity.this).show();
            }
        });

        Button createProgressDialog = findViewById(R.id.createprogressdialog);
        createProgressDialog.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // http://nickcode4fun.net/2018/01/18/MaterialDesign-Android-Dialogs/
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Progress Dialog")
                        .setMessage(".setView(R.layout.progress_dialog)")
                        .create()
                        .show();
            }
        });
    }
}
