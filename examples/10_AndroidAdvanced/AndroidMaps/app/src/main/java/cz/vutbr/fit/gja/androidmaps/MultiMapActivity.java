package cz.vutbr.fit.gja.androidmaps;

import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import androidx.fragment.app.FragmentActivity;

/**
 * This shows how to create a simple activity with multiple maps on screen.
 */
public class MultiMapActivity extends FragmentActivity implements OnMapReadyCallback {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_multimap);

    MapsInitializer.initialize(this);
    // Getting GoogleMap object from the fragment
    SupportMapFragment smf = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map1);
    smf.getMapAsync(this);

    smf = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map2);
    smf.getMapAsync(this);

    smf = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map3);
    smf.getMapAsync(this);

    smf = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map4);
    smf.getMapAsync(this);
  }

  @Override
  public void onMapReady(GoogleMap googleMap) {
    googleMap = googleMap;
  }
}
