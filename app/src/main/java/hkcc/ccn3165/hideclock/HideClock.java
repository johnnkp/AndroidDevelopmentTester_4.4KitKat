package hkcc.ccn3165.hideclock;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AnalogClock;
import android.widget.Button;

import com.android.development.R;

public class HideClock extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hide_clock);
        final AnalogClock clock = (AnalogClock) findViewById(R.id.AnalogClock);
        final Button show = (Button) findViewById(R.id.show);
        final Button hide = (Button) findViewById(R.id.hide);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clock.setVisibility(View.VISIBLE);
                show.setVisibility(View.INVISIBLE);
                hide.setVisibility(View.VISIBLE);
            }
        });
        hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clock.setVisibility(View.INVISIBLE);
                hide.setVisibility(View.INVISIBLE);
                show.setVisibility(View.VISIBLE);
            }
        });
    }
}
