package cz.vutbr.fit.gja.filerequest;

import java.io.FileNotFoundException;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.print.PrintHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
  
  Uri imageUri=null;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    
    Button get=(Button) findViewById(R.id.get);
    
    get.setOnClickListener(new View.OnClickListener() {
      
      @Override
      public void onClick(View v) {
        Intent mRequestFileIntent = new Intent(Intent.ACTION_PICK);
            mRequestFileIntent.setType("image/png");
            mRequestFileIntent.setComponent(new ComponentName("cz.vutbr.fit.gja.fileprovider", "cz.vutbr.fit.gja.fileprovider.MainActivity"));
        
        startActivityForResult(mRequestFileIntent,1);
        
      }
    });
    
    Button print=(Button) findViewById(R.id.print);
    
    print.setOnClickListener(new View.OnClickListener() {
      
      @Override
      public void onClick(View v) {
        PrintHelper photoPrinter = new PrintHelper(MainActivity.this);
            photoPrinter.setScaleMode(PrintHelper.SCALE_MODE_FIT);
            try {
          photoPrinter.printBitmap("Returned image - test print", imageUri);
        } catch (FileNotFoundException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        
      }
    });
    print.setEnabled(false);
    
    
  }
  
  @Override
    public void onActivityResult(int requestCode, int resultCode, Intent returnIntent) {
        // If the selection didn't work
        if (resultCode != RESULT_OK) {
            // Exit without doing anything else
            return;
        } else {
            // Get the file's content URI from the incoming Intent
            Uri returnUri = returnIntent.getData();

            ImageView img=(ImageView) findViewById(R.id.image);
            img.setImageURI(returnUri);
            imageUri=returnUri;
            Button print=(Button) findViewById(R.id.print);
            print.setEnabled(true);
            
        }
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
