package hkcc.ccn3165.httpimagesolution;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

public class ImageDownloader extends AsyncTask<Object, Integer, Bitmap> {
    private ImageView iv;

    @Override
    protected void onPreExecute() {
        //Setup is done here
    }

    @Override
    protected Bitmap doInBackground(Object... params) {
        Bitmap bitmap = null;
        try {
            Log.i("ImageDownloader", "1");
            URL url = new URL((String) params[0]);
            Log.i("ImageDownloader", (String) params[0]);
            iv = (ImageView) params[1];
            Log.i("ImageDownloader", "2");
            InputStream input = url.openStream();
            Log.i("ImageDownloader", "3");
            bitmap = BitmapFactory.decodeStream(input);
            Log.i("ImageDownloader", "4");
        } catch (Exception e) {
            Log.i("ImageDownloader", "5");
            Log.e("ImageDownloader", e.getMessage());
        }
        return bitmap;
    }

    @Override
    protected void onProgressUpdate(Integer... params) {
        //Update a progress bar here, or ignore it
    }

    @Override
    protected void onPostExecute(Bitmap img) {
        Log.i("ImageDownloader", "6");
        iv.setImageBitmap(img);
    }

    @Override
    protected void onCancelled() {
        //Events to be done when canceling this task
    }

}
