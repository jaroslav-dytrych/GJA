package cz.vutbr.fit.gja.androidmaps;


import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraMoveStartedListener;
import com.google.android.gms.maps.GoogleMap.OnCameraMoveListener;
import com.google.android.gms.maps.GoogleMap.OnCameraIdleListener;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import android.widget.TextView;

/**
 * This shows how to listen to some {@link GoogleMap} events.
 */
public class EventsActivity extends FragmentActivity
    implements OnMapClickListener, OnMapLongClickListener, OnCameraMoveStartedListener, OnCameraMoveListener, OnCameraIdleListener,  OnMapReadyCallback {

  private GoogleMap mMap;
  private TextView mTapTextView;
  private TextView mCameraTextView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_events);

    mTapTextView = (TextView) findViewById(R.id.tap_text);
    mCameraTextView = (TextView) findViewById(R.id.camera_text);

    setUpMapIfNeeded();
  }

  @Override
  protected void onResume() {
    super.onResume();
    setUpMapIfNeeded();
  }

  private void setUpMapIfNeeded() {
    if (mMap == null) {
      MapsInitializer.initialize(this);
      SupportMapFragment smf = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
      smf.getMapAsync(this);
    }
  }

  @Override
  public void onMapReady(GoogleMap googleMap) {
    mMap = googleMap;
    // Check if we were successful in obtaining the map.
    if (mMap != null) {
      setUpMap();
    }
  }


  private void setUpMap() {
    mMap.setOnMapClickListener(this);
    mMap.setOnMapLongClickListener(this);
    mMap.setOnCameraIdleListener(this);
    mMap.setOnCameraMoveStartedListener(this);
  }

  @Override
  public void onMapClick(LatLng point) {
    mTapTextView.setText("tapped, point=" + point);
  }

  @Override
  public void onMapLongClick(LatLng point) {
    mTapTextView.setText("long pressed, point=" + point);
  }

  @Override
  public void onCameraMoveStarted(int reason) {
    if (reason == OnCameraMoveStartedListener.REASON_GESTURE) {
      mCameraTextView.setText("The user gestured on the map.");
    } else if (reason == OnCameraMoveStartedListener
        .REASON_API_ANIMATION) {
      mCameraTextView.setText("The user tapped something on the map.");
    } else if (reason == OnCameraMoveStartedListener
        .REASON_DEVELOPER_ANIMATION) {
      mCameraTextView.setText("The app moved the camera.");
    }
  }

  @Override
  public void onCameraIdle() {
    mCameraTextView.setText("The camera has stopped moving.");
  }

  @Override
  public void onCameraMove() {
      mCameraTextView.setText("Camera is moving");
  }

  @Override
  public void onPointerCaptureChanged(boolean hasCapture) {
    super.onPointerCaptureChanged(hasCapture);
  }
}

