package cz.vutbr.fit.gja.androidinteraction;


import java.io.File;
import java.io.InputStream;
import java.util.Calendar;
import java.util.List;

import org.apache.http.protocol.HTTP;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
  
  static final int PICK_CONTACT_REQUEST = 1;  // The request code
  static final int COMMUNICATE_REQUEST = 2;
  
  private void pickContact() {
    Intent pickContactIntent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
    pickContactIntent.setType(Phone.CONTENT_TYPE); // Show user only contacts w/ phone numbers
    startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    
    //works in genymotion with google play services extension
    Button map=(Button) findViewById(R.id.map);
    map.setOnClickListener(new View.OnClickListener() {
      
      @Override
      public void onClick(View v) {
        Uri location = Uri.parse("geo:0,0?q=1600+Amphitheatre+Parkway,+Mountain+View,+California");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
        startActivity(mapIntent);
        
      }
    });
    //map.setEnabled(false);
    
    //only on mobile phone
    Button dial=(Button) findViewById(R.id.dial);
    dial.setOnClickListener(new View.OnClickListener() {
      
      @Override
      public void onClick(View v) {
        Uri number = Uri.parse("tel:123456789");
        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
        startActivity(callIntent);
        
      }
    });
    dial.setEnabled(false);
    
    Button web=(Button) findViewById(R.id.web);
    web.setOnClickListener(new View.OnClickListener() {
      
      @Override
      public void onClick(View v) {
        Uri webpage = Uri.parse("http://www.android.com");
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(webIntent);
        
      }
    });
    
    Button email=(Button) findViewById(R.id.email);
    email.setOnClickListener(new View.OnClickListener() {
      
      @Override
      public void onClick(View v) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        // The intent does not have a URI, so declare the "text/plain" MIME type
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"dytrych@vutbr.cz"}); // recipients
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Email subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message text");
        
        //file must be present on given location
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.test);
        emailIntent.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(emailIntent);
        
      }
    });
    
    Button calendar=(Button) findViewById(R.id.calendar);
    calendar.setOnClickListener(new View.OnClickListener() {
      
      @Override
      public void onClick(View v) {
        Intent calendarIntent = new Intent(Intent.ACTION_INSERT, Events.CONTENT_URI);

        Calendar.getInstance().set(2015, 5, 5, 8, 0);
        calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, Calendar.getInstance().getTimeInMillis());
        Calendar.getInstance().set(2015, 5, 5, 9, 0);
        calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, Calendar.getInstance().getTimeInMillis());
        calendarIntent.putExtra(Events.TITLE, "Piano class");
        calendarIntent.putExtra(Events.EVENT_LOCATION, "School");
        startActivity(calendarIntent);
        
      }
    });
    
    Button dreaming=(Button) findViewById(R.id.dreaming);
    dreaming.setOnClickListener(new View.OnClickListener() {
      
      @Override
      public void onClick(View v) {
        PackageManager packageManager = getPackageManager();
        
        
        Intent intent = new Intent(Intent.ACTION_DREAMING_STARTED);

        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
        boolean isIntentSafe = activities.size() > 0;
        
        if (isIntentSafe){
          startActivity(intent);
        }
        else{
          Toast.makeText(MainActivity.this, "Not supported", Toast.LENGTH_SHORT).show();
        }
        
      }
    });
    
    
    Button pick=(Button) findViewById(R.id.pick);
    pick.setOnClickListener(new View.OnClickListener() {
      
      @Override
      public void onClick(View v) {
        pickContact();
        
      }
    });
    
    Button communicate=(Button) findViewById(R.id.communicate);
    communicate.setOnClickListener(new View.OnClickListener() {
      
      @Override
      public void onClick(View v) {
        Intent comIntent=new Intent(Intent.ACTION_SEND);
        comIntent.putExtra("greet", "Jarek");
        comIntent.setType("text/plain");
        
        //sending files is similar
        //commIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
        
        //explicitly call my activity (no choosing)
        comIntent.setComponent(new ComponentName("cz.vutbr.fit.gja.calledactivity", "cz.vutbr.fit.gja.calledactivity.MainActivity"));
        
        startActivityForResult(comIntent,COMMUNICATE_REQUEST);
      }
    });
    
  }
  
  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    // Check which request we're responding to
    if (requestCode == PICK_CONTACT_REQUEST) {
      // Make sure the request was successful
      if (resultCode == RESULT_OK) {
         Uri contactUri = data.getData();
         // We only need the NUMBER column, because there will be only one row in the result
         String[] projection = {Phone.NUMBER};
           
         // Perform the query on the contact to get the NUMBER column
         // We don't need a selection or sort order (there's only one result for the given URI)
         // CAUTION: The query() method should be called from a separate thread to avoid blocking
         // your app's UI thread. (For simplicity of the sample, this code doesn't do that.)
         // Consider using CursorLoader to perform the query.
         Cursor cursor = getContentResolver()
           .query(contactUri, projection, null, null, null);
         cursor.moveToFirst();
           
         // Retrieve the phone number from the NUMBER column
         int column = cursor.getColumnIndex(Phone.NUMBER);
         String number = cursor.getString(column);
         Toast.makeText(MainActivity.this, number, Toast.LENGTH_SHORT).show();
      }
    }
    else if (requestCode == COMMUNICATE_REQUEST){
      if (resultCode == RESULT_OK){
        Bundle result=data.getExtras();
        Toast.makeText(MainActivity.this, result.getString("result"), Toast.LENGTH_LONG).show();
      }
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
