// https://www.vogella.com/tutorials/AndroidCamera/article.html#tutorial-using-the-camera-api
package hkcc.ccn3165.camcap;

import android.content.Context;
import android.hardware.Camera;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import static hkcc.ccn3165.camcap.MainActivity.info;

public class PhotoHandler implements Camera.PictureCallback {
    private final Context context;

    public PhotoHandler(Context context) {
        this.context = context;
    }

    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
        File pictureFileDir = getDir();

        if (!pictureFileDir.exists() && !pictureFileDir.mkdirs()) {
            Log.d("MainActivity.DEBUG_TAG", "Can't create directory to save image.");
            Toast.makeText(context, "Can't create directory to save image.",
                    Toast.LENGTH_LONG).show();
            info.setText("Can't create directory to save image.");
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
        String date = dateFormat.format(new Date());
        String photoFile = "Picture_" + date + ".jpg";

        String filename = pictureFileDir.getPath() + File.separator + photoFile;

        File pictureFile = new File(filename);

        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            fos.write(data);
            fos.close();
            Toast.makeText(context, "New Image saved: " + photoFile,
                    Toast.LENGTH_LONG).show();
            info.setText("New Image saved: " + photoFile);
        } catch (Exception error) {
            Log.d("MainActivity.DEBUG_TAG", "File " + filename + "not saved: "
                    + error.getMessage());
            Toast.makeText(context, "Image could not be saved.",
                    Toast.LENGTH_LONG).show();
            info.setText("File " + filename + "not saved: " + error.getMessage());
        }
        // https://stackoverflow.com/questions/6139409/android-camera-freezes-after-taking-one-photo/31260958#31260958
        MainActivity.cameraObject.stopPreview();
        MainActivity.cameraObject.startPreview();
    }

    private File getDir() {
        File sdDir = Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        return new File(sdDir, "CameraAPIDemo");
    }
}