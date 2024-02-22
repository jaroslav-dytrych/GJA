package cz.vutbr.fit.gja.androidExplicitIntent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
  private static final int REQUEST_CODE = 10;

  
/** Called when the activity is first created. */

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public void onClick(View view) {
    Intent i = new Intent(this, ActivityTwo.class);
    i.putExtra("Value1", "1. value for ActivityTwo");
    i.putExtra("Value2", "2. value for ActivityTwo");
    // Set the request code to any code you like, you can identify the
    // callback via this code
    startActivityForResult(i, REQUEST_CODE);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
      if (data.hasExtra("returnKey1")) {
        Toast.makeText(this, data.getExtras().getString("returnKey1"),
            Toast.LENGTH_SHORT).show();
      }
    }
  }
} 