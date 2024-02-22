package cz.vutbr.fit.gja.androidScrollView;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    TextView view =  (TextView) findViewById(R.id.TextView02);
    String s="";
    for (int i=0; i < 300; i++) {
      s += "androidScrollView.gja.fit.vutbr.cz ";
    }
    view.setText(s);
  }
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

}
