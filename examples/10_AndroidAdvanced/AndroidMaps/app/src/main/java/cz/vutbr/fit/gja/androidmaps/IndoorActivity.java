package cz.vutbr.fit.gja.androidmaps;

import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.IndoorBuilding;
import com.google.android.gms.maps.model.IndoorLevel;
import com.google.android.gms.maps.model.LatLng;


import java.util.List;

/**
 * A demo activity showing how to use indoor.
 */
public class IndoorActivity extends FragmentActivity implements OnMapReadyCallback {

  private GoogleMap mMap;

  private boolean showLevelPicker = true;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_indoor);
    MapsInitializer.initialize(this);
    SupportMapFragment smf = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
    smf.getMapAsync(this);
  }

  @Override
  public void onMapReady(GoogleMap googleMap) {
    mMap = googleMap;
    // SFO airport
    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.614631, -122.385153), 18));
  }

  /**
   * Called when the toggle level picker button is clicked.
   */
  public void onToggleLevelPicker(View view) {
    showLevelPicker = !showLevelPicker;
    mMap.getUiSettings().setIndoorLevelPickerEnabled(showLevelPicker);
  }

  /**
   * Called when the focused building info is clicked.
   */
  public void onFocusedBuildingInfo(View view) {
    IndoorBuilding building = mMap.getFocusedBuilding();
    if (building != null) {
      String s = "";
      for (IndoorLevel level : (List<IndoorLevel>) building.getLevels()) {
        s = s + level.getName() + " ";
      }
      if (building.isUnderground()) {
        s += "is underground";
      }
      setText(s);
    } else {
      setText("No visible building");
    }
  }

  /**
   * Called when the focused level info is clicked.
   */
  public void onVisibleLevelInfo(View view) {
    IndoorBuilding building = mMap.getFocusedBuilding();
    if (building != null) {
      IndoorLevel level = (IndoorLevel) building.getLevels().get(building.getActiveLevelIndex());
      if (level != null) {
        setText(level.getName());
      } else {
        setText("No visible level");
      }
    } else {
      setText("No visible building");
    }
  }

  /**
   * Called when the activate higher level is clicked.
   */
  public void onHigherLevel(View view) {
    IndoorBuilding building = mMap.getFocusedBuilding();
    if (building != null) {
      List<IndoorLevel> levels = building.getLevels();
      if (!levels.isEmpty()) {
        int currentLevel = building.getActiveLevelIndex();
        // The levels are in 'display order' from top to bottom,
        // i.e. higher levels in the building are lower numbered in the array.
        int newLevel = currentLevel - 1;
        if (newLevel == -1) {
          newLevel = levels.size() - 1;
        }
        IndoorLevel level = levels.get(newLevel);
        setText("Activiating level " + level.getName());
        level.activate();
      } else {
        setText("No levels in building");
      }
    } else {
      setText("No visible building");
    }
  }

  private void setText(String message) {
    TextView text = (TextView) findViewById(R.id.top_text);
    text.setText(message);
  }
}
