package hkcc.ccn3165.servicedemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.development.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service);
    }

    // Method to start the service
    public void onStartService(View view) {
        startService(new Intent(getBaseContext(), ServiceDemo.class));
    }
    // Method to stop the service
    public void onStopService(View view) {
        stopService(new Intent(getBaseContext(), ServiceDemo.class));
    }
}
