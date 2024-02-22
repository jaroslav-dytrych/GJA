package cz.vutbr.fit.gja.androidmaps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

public class MarkerActivity extends FragmentActivity implements OnMapReadyCallback {

  private static final double LAT = 49.19205056;
  private static final double LNG = 16.613191;


  GoogleMap googleMap;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_marker);
    MapsInitializer.initialize(this);
    SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentByTag("maps");

    // Getting GoogleMap object from the fragment
    SupportMapFragment smf = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
    smf.getMapAsync(this);
  }

  @Override
  public void onMapReady(GoogleMap googleMap) {
    googleMap = googleMap;
    googleMap.addMarker(new MarkerOptions().position(new LatLng(LAT, LNG)));
  }

}