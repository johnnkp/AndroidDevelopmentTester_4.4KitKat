// http://tw.gitbook.net/android/android_camera.html
package hkcc.ccn3165.camcap;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import static hkcc.ccn3165.camcap.MainActivity.info;

public class ShowCamera extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder holdMe;
    private Camera theCamera;

    public ShowCamera(Context context, Camera camera) {
        super(context);
        theCamera = camera;
        holdMe = getHolder();
        holdMe.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            // theCamera = MainActivity.isCameraAvailiable();
            theCamera.setPreviewDisplay(holder);
            theCamera.startPreview();
        } catch (Exception e) {
            info.setText(e.getMessage());
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
