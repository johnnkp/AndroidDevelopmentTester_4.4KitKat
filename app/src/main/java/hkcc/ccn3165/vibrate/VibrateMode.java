package hkcc.ccn3165.vibrate;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.development.R;

public class VibrateMode extends Activity {
    private AudioManager mAudioManager;
    private boolean mPhoneIsVibrate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vibrate_mode);
        mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        checkIfPhoneIsVibrate();
        setButtonClickListener();
        Log.d("VibrateModeApp", "This is a test");

        Log.i("VibrateModeApp", "audioManager.getRingerMode() " + mAudioManager.getRingerMode());
        System.out.println("audioManager.getRingerMode() " + mAudioManager.getRingerMode());
    }

    private void setButtonClickListener() {
        Button toggleButton = (Button) findViewById(R.id.toggleButton);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (mPhoneIsVibrate) {
                    // Change back to normal mode
                    mAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                    mPhoneIsVibrate = false;
                } else {
                    // Change to vibrate mode
                    mAudioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                    mPhoneIsVibrate = true;
                }
                // Now toggle the UI again
                toggleUi();
            }
        });
    }

    /**
     * Checks to see if the phone is currently in vibrate mode.
     */
    private void checkIfPhoneIsVibrate() {
        int ringerMode = mAudioManager.getRingerMode();
        mPhoneIsVibrate = ringerMode == AudioManager.RINGER_MODE_VIBRATE;
    }

    /**
     * Toggles the UI images from vibrate to normal and vice versa.
     */
    private void toggleUi() {
        ImageView imageView = (ImageView) findViewById(R.id.phone_icon);
        Drawable newPhoneImage;
        if (mPhoneIsVibrate) {
            newPhoneImage = getResources().getDrawable(R.drawable.vibrate);
        } else {
            newPhoneImage = getResources().getDrawable(R.drawable.normal);
        }
        imageView.setImageDrawable(newPhoneImage);
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkIfPhoneIsVibrate();
        toggleUi();
    }
}