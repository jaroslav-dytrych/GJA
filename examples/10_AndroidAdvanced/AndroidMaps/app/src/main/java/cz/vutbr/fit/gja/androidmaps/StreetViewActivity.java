package cz.vutbr.fit.gja.androidmaps;


import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;

import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;

public class StreetViewActivity extends FragmentActivity implements OnStreetViewPanoramaReadyCallback {

  private StreetViewPanorama svp;

  // George St, Sydney
  private static final LatLng SYDNEY = new LatLng(-33.87365, 151.20689);

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_streetview);
    setUpStreetViewPanoramaIfNeeded(savedInstanceState);
  }

  private void setUpStreetViewPanoramaIfNeeded(Bundle savedInstanceState) {
    if (svp == null) {
      ((SupportStreetViewPanoramaFragment)
          getSupportFragmentManager().findFragmentById(R.id.streetviewpanorama))
          .getStreetViewPanoramaAsync(this);

    }
  }

  @Override
  public void onStreetViewPanoramaReady(StreetViewPanorama svpa) {
    svp = svpa;
    if (svp != null) {
      svp.setPosition(SYDNEY);
    }
  }

}