package hkcc.ccn3165.camcap;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.development.R;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    // http://tw.gitbook.net/android/android_camera.html
    // https://www.vogella.com/tutorials/AndroidCamera/article.html#tutorial-using-the-camera-api
    public static Camera cameraObject;
    public static TextView info;
    private ShowCamera showCamera;
    private FrameLayout preview;
    private boolean pickImage = false;
    private final int PICK_IMAGE = 1;
    private int cameraId = 0;

    public static Camera isCameraAvailiable() {
        Camera object = null;
        try {
            object = Camera.open();
            System.out.println("open");
        } catch (Exception e) {
        }
        return object;
    }

    private Camera.PictureCallback capturedIt = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            if (bitmap == null) {
                Toast.makeText(getApplicationContext(), "not taken", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "taken", Toast.LENGTH_SHORT).show();
            }
            cameraObject.release();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camcap);

        preview = (FrameLayout) findViewById(R.id.camera_preview);
        info = findViewById(R.id.info);
        showCam();
    }

    public void openCameraApp(View view) {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(i);
    }

    public void imageFile(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(
                intent, "Select Picture"), PICK_IMAGE);
    }

    public void snapIt(View view) {
        if (pickImage) {
            pickImage = false;
            showCam();
            info.setText(String.valueOf(pickImage));
        }
        // cameraObject.takePicture(null, null, capturedIt);
        cameraObject.takePicture(null, null,
                new PhotoHandler(getApplicationContext()));
    }

    public void detect(View view) {
        Intent i = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("http://developer.android.com/reference/android/hardware/Camera.Face"));
        startActivity(i);
    }

    public void onClick(View view) {
        cameraObject.startPreview();
        cameraObject.takePicture(null, null,
                new PhotoHandler(getApplicationContext()));
    }

    private int findFrontFacingCamera() {
        int cameraId = -1;
        // Search for the front facing camera
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                Log.d("DEBUG_TAG", "Camera found");
                cameraId = i;
                break;
            }
        }
        return cameraId;
    }

    @Override
    protected void onPause() {
        if (cameraObject != null) {
            cameraObject.release();
            cameraObject = null;
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        if (!pickImage) {
            showCam();
        }
        super.onResume();
    }

    private void showCam() {
        try {
            // http://tw.gitbook.net/android/android_camera.html
            cameraObject = isCameraAvailiable();
            showCamera = new ShowCamera(this, cameraObject);
            preview.addView(showCamera);
        } catch (Exception e) {
            info.setText(e.getMessage());
        }
    }

    public void onDestroy(View view) {
        if (cameraObject != null) {
            cameraObject.release();
            cameraObject = null;
        } // Release Camera
        finish(); // Quit app
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
                ImageView imageView = new ImageView(this);
                imageView.setImageBitmap(bitmap);
                preview.removeAllViewsInLayout();
                preview.addView(imageView);
                info.setText(bitmap.toString());
                pickImage = true;
            } catch (IOException e) {
                info.setText(e.getMessage());
            }
        }
    }
}
