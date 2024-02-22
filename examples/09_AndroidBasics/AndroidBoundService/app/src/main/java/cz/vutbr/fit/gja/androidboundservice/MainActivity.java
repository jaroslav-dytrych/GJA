package cz.vutbr.fit.gja.androidboundservice;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.TextView;

import cz.vutbr.fit.gja.androidboundservice.BoundService.MyLocalBinder;


public class MainActivity extends Activity {

  BoundService myService;
  boolean isBound = false;

  
  public void showTime(View view)
  {
    String currentTime = myService.getCurrentTime();
    TextView myTextView = (TextView)findViewById(R.id.myTextView);
    myTextView.setText(currentTime);
  }

  private ServiceConnection myConnection = new ServiceConnection() {

    public void onServiceConnected(ComponentName className, IBinder service) {
      MyLocalBinder binder = (MyLocalBinder) service;
      myService = binder.getService();
      isBound = true;
    }
      
    public void onServiceDisconnected(ComponentName arg0) {
      isBound = false;
    }
      
  };
     
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Intent intent = new Intent(this, BoundService.class);
    bindService(intent, myConnection, Context.BIND_AUTO_CREATE);
  }

}