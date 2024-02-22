package cz.vutbr.fit.gja.startdraw;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import androidx.core.app.ActivityCompat;
import android.view.Menu;
import android.view.MenuItem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class StartDraw extends Activity {
  DrawView drawView;
  final int REQUEST_CODE = 1;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    drawView = new DrawView(this);
    drawView.setBackgroundColor(Color.WHITE);
    setContentView(drawView);
  }
  
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }
  
  public boolean onOptionsItemSelected(MenuItem item) {
    int id=item.getItemId();
    if (id == R.id.load) {
      loadImage();
      return true;
    } 
    else if (id == R.id.start)
      drawView.start();
    else if (id == R.id.next)
      drawView.next();
    else if (id == R.id.prev)
      drawView.prev();
    else if (id == R.id.show)
      drawView.clear();
    else if (id==R.id.grid)
      gridDialog();

    return (super.onOptionsItemSelected(item));
  }   
  
  public void gridDialog(){
    final CharSequence[] items = {"4","6","8"};
    
    // Creating and Building the Dialog 
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle("Select grid size");
    builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
    public void onClick(DialogInterface dialog, int item) {
      drawView.changeGrid(4+2*item);
      Dialog.class.cast(dialog).dismiss();   
      }
    });
    builder.create().show();
  }
  
  public void loadImage(){
    Intent intent = new Intent();
    intent.setType("image/*");
    intent.setAction(Intent.ACTION_GET_CONTENT);
    intent.addCategory(Intent.CATEGORY_OPENABLE);
    startActivityForResult(intent, REQUEST_CODE);
  }
  
  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    InputStream stream = null;
    Bitmap bitmap = null;
    if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK)
      try {
        stream = getContentResolver().openInputStream(data.getData());
        bitmap = BitmapFactory.decodeStream(stream);
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } finally {
        if (stream != null)
          try {
            stream.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
      }
      drawView.addImage(bitmap);
   }
}