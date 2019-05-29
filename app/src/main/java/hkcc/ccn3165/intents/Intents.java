package hkcc.ccn3165.intents;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Contacts;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.development.R;

public class Intents extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intents);

        Button buttonPage2 = (Button) findViewById(R.id.ButtonPage2);
        buttonPage2.setOnClickListener(buttonPage2Listner);
        Button buttonMarket = (Button) findViewById(R.id.ButtonMarket);
        Button buttonUrl = (Button) findViewById(R.id.ButtonUrl);
        Button buttonEmail = (Button) findViewById(R.id.ButtonEmail);
        Button buttonGMap = (Button) findViewById(R.id.ButtonGMap);
        Button buttonTel = (Button) findViewById(R.id.ButtonTel);
        Button buttonCamera = (Button) findViewById(R.id.ButtonCamera);
        Button buttonImage = (Button) findViewById(R.id.ButtonImage);
        Button buttonPeople = (Button) findViewById(R.id.ButtonPeople);

        final boolean isPlayInstalled = appInstalledOrNot("com.android.vending");
        final boolean isGMapInstalled = appInstalledOrNot("com.google.android.apps.maps");
        final boolean isGoMapInstalled = appInstalledOrNot("com.google.android.apps.mapslite");

        buttonMarket.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                try {
                    Uri uri = Uri.parse("market://search?q=AR");
                    Intent it = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(it);
                } catch (Exception e) {
                    Intent i = new Intent(android.content.Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store"));
                    startActivity(i);
                }
            }
        });

        /* Tutorial 4: Intents
        Button button = (Button) findViewById(R.id.ButtonUrl);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://www.hkcc-polyu.edu.hk/"));
                startActivity(i);
            }
        }); */

        buttonUrl.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                Uri uri = Uri.parse("http://moodle.cpce-polyu.edu.hk");
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(i);
            }
        });
        buttonEmail.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                Uri uri = Uri.parse("mailto:press@google.com");
                Intent i = new Intent(Intent.ACTION_SENDTO, uri);
                startActivity(i);
            }
        });
        buttonGMap.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                try {
                    Uri uri = Uri.parse("geo:22.303342,114.184828");
                    Intent i = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(i);
                } catch (Exception e) {
                    Intent i = new Intent(android.content.Intent.ACTION_VIEW,
                            Uri.parse("http://www.google.com.hk/maps/place/HKCC-HHB/@22.3033116,114.1847644,21z/data=!4m5!3m4!1s0x340400e6d07a9527:0xe93a262e9b73cd30!8m2!3d22.3032283!4d114.1848477?hl=zh-TW"));
                    startActivity(i);
                }
            }
        });
        buttonTel.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                Uri uri = Uri.parse("tel:25 123 123");
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(i);
            }
        });
        buttonCamera.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(i);
            }
        });
        buttonImage.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("image/*");
                startActivityForResult(i, 0);
            }
        });
        buttonPeople.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW, Contacts.People.CONTENT_URI);
                startActivity(i);
            }
        });
    }

    private Button.OnClickListener buttonPage2Listner = new Button.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(Intents.this, Second.class);
            startActivity(intent);
        }
    };

    // https://stackoverflow.com/questions/11392183/how-to-check-programmatically-if-an-application-is-installed-or-not-in-android
    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
        }

        return false;
    }
}
