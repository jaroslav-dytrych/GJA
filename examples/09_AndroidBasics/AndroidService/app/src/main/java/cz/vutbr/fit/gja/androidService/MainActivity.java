package cz.vutbr.fit.gja.androidService;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

  private static final int REQUEST_EXTERNAL_STORAGE = 1;
  private static String[] PERMISSIONS_STORAGE = {
    Manifest.permission.READ_EXTERNAL_STORAGE,
    Manifest.permission.WRITE_EXTERNAL_STORAGE
  };

  private TextView textView;
  private BroadcastReceiver receiver = new BroadcastReceiver() {

    @Override
    public void onReceive(Context context, Intent intent) {
      Bundle bundle = intent.getExtras();
      if (bundle != null) {
        String string = bundle.getString(DownloadService.FILEPATH);
        int resultCode = bundle.getInt(DownloadService.RESULT);
        if (resultCode == RESULT_OK) {
          Toast.makeText(MainActivity.this,
            "Download complete. Download URI: " + string,
            Toast.LENGTH_LONG).show();
          textView.setText("Download done");
        } else {
          Toast.makeText(MainActivity.this, "Download failed",
            Toast.LENGTH_LONG).show();
          textView.setText("Download failed");
        }
      }
    }
  };

  @Override
  public void onCreate(Bundle savedInstanceState) {

    int permission = ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
    if (permission != PackageManager.PERMISSION_GRANTED) {
      // We don't have permission so prompt the user
      ActivityCompat.requestPermissions(
        this,
        PERMISSIONS_STORAGE,
        REQUEST_EXTERNAL_STORAGE
      );
    }

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    textView = (TextView) findViewById(R.id.status);
  }

  @Override
  protected void onResume() {
    super.onResume();
    registerReceiver(receiver, new IntentFilter(DownloadService.NOTIFICATION), RECEIVER_NOT_EXPORTED);
  }

  @Override
  protected void onPause() {
    super.onPause();
    unregisterReceiver(receiver);
  }

  public void onClick(View view) {
    Intent intent = new Intent(this, DownloadService.class);
    // add infos for the service which file to download and where to store
    intent.putExtra(DownloadService.FILENAME, "index.html");
    intent.putExtra(DownloadService.URL,"https://www.fit.vutbr.cz/study/courses/GJA/public/index.html");
    startService(intent);
    textView.setText("Service started");
  }
} 