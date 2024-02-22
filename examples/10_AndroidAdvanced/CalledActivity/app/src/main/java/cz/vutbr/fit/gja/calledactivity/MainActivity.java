package cz.vutbr.fit.gja.calledactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Intent intent = getIntent();
    final Bundle in=intent.getExtras();

    //uri to handle
    //Uri data = intent.getData();

    //kind of MIME type activity accepts
    //String type=intent.getType();

    //this would handle list of incomming files
    //intent.getParcelableArrayListExtra(name);

    final Button hello = (Button) findViewById(R.id.hello);
    final Button hi = (Button) findViewById(R.id.hi);
    
    View.OnClickListener listener=new View.OnClickListener() {
      
      @Override
      public void onClick(View arg0) {
        String greet="world";
        if (in != null) {
          greet = in.getString("greet");
        }
        Intent result = new Intent("RESULT_ACTION");
        if (arg0 == hello){
          result.putExtra("result", "hello "+greet);
        }
        else{
          result.putExtra("result", "hi "+greet);
        }
        
        setResult(Activity.RESULT_OK, result);
        finish();
      }
    };
    
    hello.setOnClickListener(listener);
    hi.setOnClickListener(listener);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();
    if (id == R.id.action_settings) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
