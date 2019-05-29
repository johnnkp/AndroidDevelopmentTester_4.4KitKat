package hkcc.ccn3165.intents;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.android.development.R;

public class Second extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page2);
        Button buttonHome = (Button) findViewById(R.id.ButtonHome);
        buttonHome.setOnClickListener(btnHomeListner);
    }

    private Button.OnClickListener btnHomeListner = new Button.OnClickListener() {
        public void onClick(View v) {
            finish();
        }
    };
}
