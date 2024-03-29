package cz.vutbr.fit.gja.androidmaps;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import java.util.Arrays;
import java.util.List;

/**
 * This shows how to draw polygons on a map.
 */
public class PolygonActivity extends FragmentActivity implements OnSeekBarChangeListener, OnMapReadyCallback {
  private static final LatLng SYDNEY = new LatLng(-33.87365, 151.20689);

  private static final int WIDTH_MAX = 50;
  private static final int HUE_MAX = 360;
  private static final int ALPHA_MAX = 255;

  private GoogleMap mMap;

  private Polygon mMutablePolygon;

  private SeekBar mColorBar;
  private SeekBar mAlphaBar;
  private SeekBar mWidthBar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_polygon);

    mColorBar = (SeekBar) findViewById(R.id.hueSeekBar);
    mColorBar.setMax(HUE_MAX);
    mColorBar.setProgress(0);

    mAlphaBar = (SeekBar) findViewById(R.id.alphaSeekBar);
    mAlphaBar.setMax(ALPHA_MAX);
    mAlphaBar.setProgress(127);

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
    // Create a rectangle with two rectangular holes.
    mMap.addPolygon(new PolygonOptions()
        .addAll(createRectangle(new LatLng(-20, 130), 5, 5))
        .addHole(createRectangle(new LatLng(-22, 128), 1, 1))
        .addHole(createRectangle(new LatLng(-18, 133), 0.5, 1.5))
        .fillColor(Color.CYAN)
        .strokeColor(Color.BLUE)
        .strokeWidth(5));

    // Create a rectangle centered at Sydney.
    PolygonOptions options = new PolygonOptions().addAll(createRectangle(SYDNEY, 5, 8));

    int fillColor = Color.HSVToColor(
        mAlphaBar.getProgress(), new float[]{mColorBar.getProgress(), 1, 1});
    mMutablePolygon = mMap.addPolygon(options
        .strokeWidth(mWidthBar.getProgress())
        .strokeColor(Color.BLACK)
        .fillColor(fillColor));

    mColorBar.setOnSeekBarChangeListener(this);
    mAlphaBar.setOnSeekBarChangeListener(this);
    mWidthBar.setOnSeekBarChangeListener(this);

    // Move the map so that it is centered on the mutable polygon.
    mMap.moveCamera(CameraUpdateFactory.newLatLng(SYDNEY));
  }

  /**
   * Creates a List of LatLngs that form a rectangle with the given dimensions.
   */
  private List<LatLng> createRectangle(LatLng center, double halfWidth, double halfHeight) {
    return Arrays.asList(new LatLng(center.latitude - halfHeight, center.longitude - halfWidth),
        new LatLng(center.latitude - halfHeight, center.longitude + halfWidth),
        new LatLng(center.latitude + halfHeight, center.longitude + halfWidth),
        new LatLng(center.latitude + halfHeight, center.longitude - halfWidth),
        new LatLng(center.latitude - halfHeight, center.longitude - halfWidth));
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
    if (mMutablePolygon == null) {
      return;
    }

    if (seekBar == mColorBar) {
      mMutablePolygon.setFillColor(Color.HSVToColor(
          Color.alpha(mMutablePolygon.getFillColor()), new float[]{progress, 1, 1}));
    } else if (seekBar == mAlphaBar) {
      int prevColor = mMutablePolygon.getFillColor();
      mMutablePolygon.setFillColor(Color.argb(
          progress, Color.red(prevColor), Color.green(prevColor),
          Color.blue(prevColor)));
    } else if (seekBar == mWidthBar) {
      mMutablePolygon.setStrokeWidth(progress);
    }
  }
}

