package cz.vutbr.fit.gja.androidlocation;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import android.Manifest;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements
    GoogleApiClient.ConnectionCallbacks,
    GoogleApiClient.OnConnectionFailedListener {

  private TextView mAddress;
  private ProgressBar mActivityIndicator;
  private GoogleApiClient mGoogleApiClient;

  protected synchronized void buildGoogleApiClient() {
    mGoogleApiClient = new GoogleApiClient.Builder(this)
        .addConnectionCallbacks(this)
        .addOnConnectionFailedListener(this)
        .addApi(LocationServices.API)
        .build();
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    final int REQUEST_CODE = 1;
    if (!(checkSelfPermission(
            Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
      ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
    }
    /*
     * Create a new location client, using the enclosing class to
     * handle callbacks.
     */
    mAddress = (TextView) findViewById(R.id.address);
    mActivityIndicator =
            (ProgressBar) findViewById(R.id.address_progress);

    buildGoogleApiClient();

    location = getLocation();
    Button location = (Button) findViewById(R.id.location);
    location.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        getAddress(v);
      }
    });
  }

  private class GetAddressTask {
    ExecutorService executor = Executors.newSingleThreadExecutor();
    Handler handler = new Handler(Looper.getMainLooper());
    Address address;
    String addressText;

    protected void doInBackground(Location... params) {
      executor.execute(() -> {
            Context mContext = getApplicationContext();
            Geocoder geocoder =
                new Geocoder(mContext, Locale.getDefault());
            // Get the current location from the input parameter list
            Location loc = params[0];
            // Create a list to contain the result address
            List<Address> addresses = null;
            try {
              /*
               * Return 1 address.
               */
              addresses = geocoder.getFromLocation(loc.getLatitude(),
                  loc.getLongitude(), 1);
            } catch (IOException e1) {
              Log.e("LocationSampleActivity",
                  "IO Exception in getFromLocation()");
              e1.printStackTrace();
            } catch (IllegalArgumentException e2) {
              // Error message to post in the log
              String errorString = "Illegal arguments " +
                  Double.toString(loc.getLatitude()) +
                  " , " +
                  Double.toString(loc.getLongitude()) +
                  " passed to address service";
              Log.e("LocationSampleActivity", errorString);
              e2.printStackTrace();
            }
            // If the reverse geocode returned an address
            if (addresses != null && addresses.size() > 0) {
              // Get the first address
              address = addresses.get(0);

              /*
               * Format the first line of address (if available),
               * city, and country name.
               */
              addressText = String.format(
                  "%s, %s, %s",
                  // If there's a street address, add it
                  address.getMaxAddressLineIndex() > 0 ?
                      address.getAddressLine(0) : "",
                  // Locality is usually a city
                  address.getLocality(),
                  // The country of the address
                  address.getCountryName());
            } else {
              // Error message to post in the log
              String errorString = "No address found";
              Log.e("LocationSampleActivity", errorString);
            }
        //Background work here
        handler.post(() -> {
          // Set activity indicator visibility to "gone"
          mActivityIndicator.setVisibility(View.GONE);
          // Display the results of the lookup.
          mAddress.setText(addressText);
        });
      });
    }

  }

  private static final String PROVIDER = "flp";
  private static final double LAT = 37.377166;
  private static final double LNG = -122.086966;
  private static final float ACCURACY = 3.0f;

  Location location;

  public Location getLocation() {
    Location newLocation = new Location(PROVIDER);
    newLocation.setLatitude(LAT);
    newLocation.setLongitude(LNG);
    newLocation.setAccuracy(ACCURACY);
    return newLocation;
  }

  /*
   * Called by Location Services when the request to connect the
   * client finishes successfully. At this point, you can
   * request the current location or start periodic updates
   */
  @Override
  public void onConnected(Bundle dataBundle) {
    // Display the connection status
    Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();

    //LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient); --works on device with GPS (or with genymotion emulator)
  }

  public void getAddress(View v) {
    // Ensure that a Geocoder services is available
    if (Build.VERSION.SDK_INT >=
        Build.VERSION_CODES.GINGERBREAD
        &&
        Geocoder.isPresent()) {
      // Show the activity indicator
      mActivityIndicator.setVisibility(View.VISIBLE);
      /*
       * Reverse geocoding is long-running and synchronous.
       * Run it on a background thread.
       * Pass the current location to the background task.
       * When the task finishes,
       * onPostExecute() displays the address.
       */
      (new GetAddressTask()).doInBackground(location);
    }

  }

  @Override
  public void onConnectionFailed(ConnectionResult connectionResult) {
    /*
     * Google Play services can resolve some errors it detects.
     * If the error has a resolution, try sending an Intent to
     * start a Google Play services activity that can resolve
     * error.
     */
    if (connectionResult.hasResolution()) {
      try {
        // Start an Activity that tries to resolve the error
        connectionResult.startResolutionForResult(
            this,
            1);
      /*
       * Thrown if Google Play services canceled the original
       * PendingIntent
       */
      } catch (IntentSender.SendIntentException e) {
        // Log the error
        e.printStackTrace();
      }
    } else {
      /*
       * If no resolution is available, display a dialog to the
       * user with the error.
       */
      Toast.makeText(this, String.valueOf(connectionResult.getErrorCode()), Toast.LENGTH_SHORT).show();
    }

  }

  @Override
  public void onConnectionSuspended(int cause) {
    Toast.makeText(this, "Connection suspended. Please re-connect.",
        Toast.LENGTH_SHORT).show();

  }

}