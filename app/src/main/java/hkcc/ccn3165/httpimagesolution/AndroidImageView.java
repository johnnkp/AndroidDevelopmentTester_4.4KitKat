package hkcc.ccn3165.httpimagesolution;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.development.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import hkcc.ccn3165.httpimagesolution.HTTPImageSolution;

public class AndroidImageView extends AppCompatActivity {
    private ImageView imageView;
    private final int PICK_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageview);

        Button next = (Button) findViewById(R.id.Button01);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), HTTPImageSolution.class);
                startActivityForResult(myIntent, 0);
            }
        });

        imageView = (ImageView) this.findViewById(R.id.imageView);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button buttonSelect = findViewById(R.id.ButtonSelect);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                showImage1();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                showImage2();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                showImage3();
            }
        });
        buttonSelect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(
                        intent, "Select Picture"), PICK_IMAGE);
            }
        });
    }

    public void getImage(View view) {
        downloadImage("http://facweb1.redlands.edu/fac/patriciacornez/cs222/ass6mockupbefore.png");
    }

    private void downloadImage(final String url) {
        //PERFORM THE NETWORK DOWNLOAD IN A BACKGROUND THREAD
        new Thread() {
            public void run() {
                //TASK 1: DECLARE AN INPUT STREAM OF BYTES
                InputStream in = null;
                //TASK 2: DEFINE A MESSAGE CONTAINING A KEY-VALUE PAIR. THE VALUE IS A DATA OBJECT (BITMAP) THAT CAN BE SENT TO THE HANDLER.
                Message msg = Message.obtain();
                try {
                    //TASK 3: OPEN THE HTTP CONNECTION TO THE URL
                    in = openHttpConnection(url);
                    //TASK 4: CREATE A BITMAP OBJECT FROM THE INPUT STREAM.
                    Bitmap bitmap = BitmapFactory.decodeStream(in);
                    //TASK 5: BUNDLE A VALUE FOR A GIVEN KEY
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("bitmap", bitmap);
                    //TASK 6: PLACE THE BUNDLED KEY-VALUE PAIR IN THE MESSAGE
                    msg.setData(bundle);
                    //TASK 7: CLOSE THE INPUT STREAM
                    in.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                //TASK 8: CALL THE HANDLER TO PLACE THE MESSAGE IN THE MESSAGEQUEUE
                mHandler.sendMessage(msg);
            }
        }.start();
    }

    private InputStream openHttpConnection(String urlStr) {
        //TASK 1: SPECIFY THE INPUT STREAM OBJECT TO BE RETURNED
        InputStream in = null;
        try {
            //TASK 2: CONVERT THE URL STRING TO AN URL OBJECT AND OPEN A CONNECTION
            URL url = new URL(urlStr);
            URLConnection urlConn = url.openConnection();
            //TASK 3: IF THE URL CONNECTION IS NOT AN HTTP CONNECTION, THROW AN EXCEPTION
            if (!(urlConn instanceof HttpURLConnection)) {
                throw new IOException("URL is not an Http URL");
            }
            //TASK 4: CREATE AN HTTP CONNECTION USING A GET OPTION
            HttpURLConnection httpConnection = (HttpURLConnection) urlConn;
            httpConnection.setRequestMethod("GET");
            httpConnection.connect();
            in = httpConnection.getInputStream();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //TASK 5: RETURN THE INPUT STREAM THAT READS FROM AN OPEN HTTP CONNECTION
        return in;
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ImageView img = (ImageView) findViewById(R.id.imageView);
            img.setImageBitmap((Bitmap) (msg.getData().getParcelable("bitmap")));
        }
    };

    private void showImage1() {
        imageView.setImageResource(R.drawable.triceratops_1);
    }

    private void showImage2() {
        imageView.setImageResource(R.drawable.triceratops_2);
    }

    private void showImage3() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            URL url = new URL("https://www.gstatic.com/webp/gallery/4.sm.jpg");
            imageView.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
        } catch (IOException e) {
            //Log.e(TAG, e.getMessage());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK &&
                data != null && data.getData() != null) {
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(
                        getContentResolver(), uri);
                ImageView imageView = findViewById(R.id.imageView);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
