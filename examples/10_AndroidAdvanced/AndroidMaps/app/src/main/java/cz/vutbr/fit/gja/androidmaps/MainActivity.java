package cz.vutbr.fit.gja.androidmaps;

import android.content.Context;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends FragmentActivity {

  public static Context contextOfApplication;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    final int S_REQUEST_CODE = 1;
    if (!(checkSelfPermission(
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
      ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, S_REQUEST_CODE);
    }
    final int N_REQUEST_CODE = 2;
    if (!(checkSelfPermission(
        Manifest.permission.ACCESS_NETWORK_STATE) == PackageManager.PERMISSION_GRANTED)) {
      ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_NETWORK_STATE}, N_REQUEST_CODE);
    }
    final int I_REQUEST_CODE = 3;
    if (!(checkSelfPermission(
        Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED)) {
      ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, I_REQUEST_CODE);
    }
    final int C_REQUEST_CODE = 4;
    if (!(checkSelfPermission(
        Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
      ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, C_REQUEST_CODE);
    }
    final int F_REQUEST_CODE = 5;
    if (!(checkSelfPermission(
        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
      ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, F_REQUEST_CODE);
    }

    contextOfApplication = getApplicationContext();

    setContentView(R.layout.activity_main);

    int index = 0;
    MyListFragment f = MyListFragment.newInstance(index);

    FragmentTransaction ft = getSupportFragmentManager()
        .beginTransaction();
    ft.replace(R.id.mylist, f);
    ft.commit();
  }

}