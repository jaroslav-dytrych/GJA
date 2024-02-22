package cz.vutbr.fit.gja.androidmaps;

import static com.google.android.gms.maps.GoogleMap.MAP_TYPE_HYBRID;
import static com.google.android.gms.maps.GoogleMap.MAP_TYPE_NONE;
import static com.google.android.gms.maps.GoogleMap.MAP_TYPE_NORMAL;
import static com.google.android.gms.maps.GoogleMap.MAP_TYPE_SATELLITE;
import static com.google.android.gms.maps.GoogleMap.MAP_TYPE_TERRAIN;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Demonstrates the different base layers of a map.
 */
public class LayersActivity extends FragmentActivity implements OnItemSelectedListener, OnMapReadyCallback {

  private GoogleMap mMap;

  private CheckBox mTrafficCheckbox;
  private CheckBox mMyLocationCheckbox;
  private CheckBox mBuildingsCheckbox;
  private CheckBox mIndoorCheckbox;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_layers);

    Spinner spinner = (Spinner) findViewById(R.id.layers_spinner);
    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
        this, R.array.layers_array, android.R.layout.simple_spinner_item);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner.setAdapter(adapter);
    spinner.setOnItemSelectedListener(this);

    mTrafficCheckbox = (CheckBox) findViewById(R.id.traffic);
    mMyLocationCheckbox = (CheckBox) findViewById(R.id.my_location);
    mBuildingsCheckbox = (CheckBox) findViewById(R.id.buildings);
    mIndoorCheckbox = (CheckBox) findViewById(R.id.indoor);
    setUpMapIfNeeded();
  }

  @Override
  protected void onResume() {
    super.onResume();
    setUpMapIfNeeded();
    if (mMap != null) {
      updateTraffic();
      updateMyLocation();
      updateBuildings();
      updateIndoor();
    }
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
  }

  private boolean checkReady() {
    if (mMap == null) {
      Toast.makeText(this, R.string.map_not_ready, Toast.LENGTH_SHORT).show();
      return false;
    }
    return true;
  }

  /**
   * Called when the Traffic checkbox is clicked.
   */
  public void onTrafficToggled(View view) {
    updateTraffic();
  }

  private void updateTraffic() {
    if (!checkReady()) {
      return;
    }
    mMap.setTrafficEnabled(mTrafficCheckbox.isChecked());
  }

  /**
   * Called when the MyLocation checkbox is clicked.
   */
  public void onMyLocationToggled(View view) {
    updateMyLocation();
  }

  private void updateMyLocation() {
    if (!checkReady()) {
      return;
    }
    if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
      return;
    }
    mMap.setMyLocationEnabled(mMyLocationCheckbox.isChecked());
  }

  /**
   * Called when the Buildings checkbox is clicked.
   */
  public void onBuildingsToggled(View view) {
    updateBuildings();
  }

  private void updateBuildings() {
    if (!checkReady()) {
      return;
    }
    mMap.setBuildingsEnabled(mBuildingsCheckbox.isChecked());
  }

  /**
   * Called when the Indoor checkbox is clicked.
   */
  public void onIndoorToggled(View view) {
    updateIndoor();
  }

  private void updateIndoor() {
    if (!checkReady()) {
      return;
    }
    mMap.setIndoorEnabled(mIndoorCheckbox.isChecked());
  }

  @Override
  public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    setLayer((String) parent.getItemAtPosition(position));
  }

  private void setLayer(String layerName) {
    if (!checkReady()) {
      return;
    }
    if (layerName.equals(getString(R.string.normal))) {
      mMap.setMapType(MAP_TYPE_NORMAL);
    } else if (layerName.equals(getString(R.string.hybrid))) {
      mMap.setMapType(MAP_TYPE_HYBRID);
    } else if (layerName.equals(getString(R.string.satellite))) {
      mMap.setMapType(MAP_TYPE_SATELLITE);
    } else if (layerName.equals(getString(R.string.terrain))) {
      mMap.setMapType(MAP_TYPE_TERRAIN);
    } else if (layerName.equals(getString(R.string.none_map))) {
      mMap.setMapType(MAP_TYPE_NONE);
    } else {
      Log.i("LDA", "Error setting layer with name " + layerName);
    }
  }

  @Override
  public void onNothingSelected(AdapterView<?> parent) {
    // Do nothing.
  }
}
