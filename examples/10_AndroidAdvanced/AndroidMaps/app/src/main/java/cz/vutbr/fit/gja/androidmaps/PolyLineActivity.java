package cz.vutbr.fit.gja.androidmaps;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

/**
 * This shows how to draw polylines on a map.
 */
public class PolyLineActivity extends FragmentActivity implements OnSeekBarChangeListener, OnMapReadyCallback {
  private static final LatLng MELBOURNE = new LatLng(-37.81319, 144.96298);
  private static final LatLng SYDNEY = new LatLng(-33.87365, 151.20689);
  private static final LatLng ADELAIDE = new LatLng(-34.92873, 138.59995);
  private static final LatLng PERTH = new LatLng(-31.95285, 115.85734);

  private static final LatLng LHR = new LatLng(51.471547, -0.460052);
  private static final LatLng LAX = new LatLng(33.936524, -118.377686);
  private static final LatLng JFK = new LatLng(40.641051, -73.777485);
  private static final LatLng AKL = new LatLng(-37.006254, 174.783018);

  private static final int WIDTH_MAX = 50;
  private static final int HUE_MAX = 360;
  private static final int ALPHA_MAX = 255;

  private GoogleMap mMap;
  private Polyline mMutablePolyline;
  private SeekBar mColorBar;
  private SeekBar mAlphaBar;
  private SeekBar mWidthBar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_polyline);

    mColorBar = (SeekBar) findViewById(R.id.hueSeekBar);
    mColorBar.setMax(HUE_MAX);
    mColorBar.setProgress(0);

    mAlphaBar = (SeekBar) findViewById(R.id.alphaSeekBar);
    mAlphaBar.setMax(ALPHA_MAX);
    mAlphaBar.setProgress(255);

    mWidthBar = (SeekBar) findViewById(R.id.widthSeekBar);
    mWidthBar.setMax(WIDTH_MAX);
    mWidthBar.setProgress(10);

    setUpMapIfNeeded();
  }

  @Override
  protected void onResume() {
    super.onResume();
    setUpMapIfNeeded();
  }

  private void setUpMapIfNeeded() {
    // Do a null check to confirm that we have not already instantiated the map.
    if (mMap == null) {
      MapsInitializer.initialize(this);
      // Try to obtain the map from the SupportMapFragment.
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

    // A simple polyline with the default options from Melbourne-Adelaide-Perth.
    mMap.addPolyline((new PolylineOptions())
        .add(MELBOURNE, ADELAIDE, PERTH));

    // A geodesic polyline that goes around the world.
    mMap.addPolyline((new PolylineOptions())
        .add(LHR, AKL, LAX, JFK, LHR)
        .width(5)
        .color(Color.BLUE)
        .geodesic(true));

    // Rectangle centered at Sydney.  This polyline will be mutable.
    int radius = 5;
    PolylineOptions options = new PolylineOptions()
        .add(new LatLng(SYDNEY.latitude + radius, SYDNEY.longitude + radius))
        .add(new LatLng(SYDNEY.latitude + radius, SYDNEY.longitude - radius))
        .add(new LatLng(SYDNEY.latitude - radius, SYDNEY.longitude - radius))
        .add(new LatLng(SYDNEY.latitude - radius, SYDNEY.longitude + radius))
        .add(new LatLng(SYDNEY.latitude + radius, SYDNEY.longitude + radius));
    int color = Color.HSVToColor(
        mAlphaBar.getProgress(), new float[]{mColorBar.getProgress(), 1, 1});
    mMutablePolyline = mMap.addPolyline(options
        .color(color)
        .width(mWidthBar.getProgress()));

    mColorBar.setOnSeekBarChangeListener(this);
    mAlphaBar.setOnSeekBarChangeListener(this);
    mWidthBar.setOnSeekBarChangeListener(this);

    // Move the map so that it is centered on the mutable polyline.
    mMap.moveCamera(CameraUpdateFactory.newLatLng(SYDNEY));
  }

  @Override
  public void onStopTrackingTouch(SeekBar seekBar) {
    // Don't do anything here.
  }

  @Override
  public void onStartTrackingTouch(SeekBar seekBar) {
    // Don't do anything here.
  }

  @Override
  public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
    if (mMutablePolyline == null) {
      return;
    }

    if (seekBar == mColorBar) {
      mMutablePolyline.setColor(Color.HSVToColor(
          Color.alpha(mMutablePolyline.getColor()), new float[]{progress, 1, 1}));
    } else if (seekBar == mAlphaBar) {
      float[] prevHSV = new float[3];
      Color.colorToHSV(mMutablePolyline.getColor(), prevHSV);
      mMutablePolyline.setColor(Color.HSVToColor(progress, prevHSV));
    } else if (seekBar == mWidthBar) {
      mMutablePolyline.setWidth(progress);
    }
  }
}

