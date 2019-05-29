package hkcc.ccn3165.maphhb;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;

import com.android.development.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if (!mIsGserviceInstalled()) {
            Intent i = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("http://www.google.com.hk/maps/place/HKCC-HHB/@22.3033116,114.1847644,21z/data=!4m5!3m4!1s0x340400e6d07a9527:0xe93a262e9b73cd30!8m2!3d22.3032283!4d114.1848477?hl=zh-TW"));
            startActivity(i);
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
            return;
        }

        /* // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        LatLng HKCC_HHB = new LatLng(22.303538, 114.184638);
        mMap.addMarker(new MarkerOptions().position(HKCC_HHB).title("HKCC HHB"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(HKCC_HHB));

        // Changing the Map Type
        if (mMap != null) {
            mMap.setMyLocationEnabled(true);
            // mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
            // mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            // mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            mMap.getUiSettings().setCompassEnabled(true);
        } */

        // Create a LatLng object for the location of HKCC HHB Campus
        LatLng HKCC = new LatLng(22.303538, 114.184638);
        // Show the current location in Google Map
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(HKCC) // Sets the center of the map to LatLng (refer to snippet)
                .zoom(10) // Sets the zoom 1 (Earth) - 21 (Street view)
                .bearing(90) // Sets the orientation of the camera to east
                .tilt(0) // Sets the tilt of the camera to 0 - 90 degrees
                .build(); // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        // mMap.moveCamera(CameraUpdateFactory.newLatLng(HKCC));
        mMap.addMarker(new MarkerOptions().position(HKCC).title("HKCC").snippet("HHB Campus"));
    }

    // https://discuss.erpnext.com/t/how-to-developing-apps-for-android/21016
    private boolean mIsGserviceInstalled() {
        PackageManager pm = getPackageManager();
        try {
            pm.getPackageInfo("com.google.android.gms", PackageManager.GET_ACTIVITIES);
            return pm.getPackageInfo("com.google.android.gms", 0).versionCode >= 13400000;
        } catch (PackageManager.NameNotFoundException e) {
        }

        return false;
    }
}
