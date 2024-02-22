package cz.vutbr.fit.gja.fileprovider;

import java.io.File;
import java.util.ArrayList;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.core.content.FileProvider;
import androidx.core.app.ActivityCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.pm.PackageManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

  // The path to the root of this app's internal storage
  private File mPrivateRootDir;
  // The path to the "images" subdirectory
  private File mImagesDir;
  // Array of files in the images subdirectory
  File[] mImageFiles;

  // Initialize the Activity
  ListView listView ;
  
  ArrayList<String> files;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    final int REQUEST_CODE = 1;
    if (!(checkSelfPermission(
          android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
      ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
    }

    final Intent mResultIntent = new Intent("ACTION_RETURN_FILE");
    // Get the files/ subdirectory of internal storage
    mPrivateRootDir = getFilesDir();
    // Get the files/images subdirectory;
    // put some images to /data/user/0/cz.vutbr.fit.gja.fileprovider/files/images
    //   apt-get install -y adb
    // - If old version is installed, use it from SDK ./Android/Sdk/platform-tools/adb
    //   adb root
    //   adb push ~/test.png /data/user/0/cz.vutbr.fit.gja.fileprovider/files/images/test.png
    // - if there are none -- app crashes
    // Note: "adb root" can not run on system image witg Google Play Services
    Toast.makeText(getApplicationContext(), mPrivateRootDir.getAbsolutePath() + "/images",Toast.LENGTH_SHORT).show();
    mImagesDir = new File(mPrivateRootDir, "images");
    if (!mImagesDir.isDirectory()) {
      mImagesDir.mkdirs();
    }
    boolean canRead = mImagesDir.canRead();
    // Get the files in the images subdirectory
    mImageFiles = mImagesDir.listFiles();
    // Set the Activity's result to null to begin with
    setResult(Activity.RESULT_CANCELED, null);
    
    // Get ListView object from xml
    listView = (ListView) findViewById(R.id.listview);
    
    files=new ArrayList<String>();

    for (File f:mImageFiles){
      files.add(f.getAbsolutePath());
    }

    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
      android.R.layout.simple_list_item_1, android.R.id.text1, files);


    // Assign adapter to ListView
    listView.setAdapter(adapter); 
    
    // ListView Item Click Listener
    listView.setOnItemClickListener(
        new AdapterView.OnItemClickListener() {
      @Override
      /*
       * When a filename in the ListView is clicked, get its
       * content URI and send it to the requesting app
       */
      public void onItemClick(AdapterView<?> adapterView,
          View view,
          int position,
          long rowId) {
        /*
         * Get a File for the selected file name.
         * Assume that the file names are in the
         * mImageFilename array.
         */
        File requestFile = new File(files.get(position));
        /*
         * Most file-related method calls need to be in
         * try-catch blocks.
         */
        // Use the FileProvider to get a content URI
        Uri fileUri=null;
        try {
          fileUri = FileProvider.getUriForFile(
              MainActivity.this,
              "cz.vutbr.fit.gja.fileprovider",
              requestFile);
        } catch (IllegalArgumentException e) {
          Log.e("File Selector",
              "The selected file can't be shared");
        }
        
        mResultIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        if (fileUri != null) {

          // Put the URI and MIME type in the result Intent
          mResultIntent.setDataAndType(
              fileUri,
              getContentResolver().getType(fileUri));
          // Set the result
          MainActivity.this.setResult(Activity.RESULT_OK, mResultIntent);
          } else {
            mResultIntent.setDataAndType(null, "");
            MainActivity.this.setResult(RESULT_CANCELED, mResultIntent);
          }
        MainActivity.this.finish();
        }
        
      
     }); 
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
