package cz.vutbr.fit.gja.androidboundservice;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class BoundService extends Service {

  private final IBinder myBinder = new MyLocalBinder();
  
  @Override
  public IBinder onBind(Intent arg0) {
    return myBinder;
  }

  public String getCurrentTime() {
    SimpleDateFormat dateformat = 
      new SimpleDateFormat("HH:mm:ss MM/dd/yyyy", Locale.US);
    return (dateformat.format(new Date()));
  }

  public class MyLocalBinder extends Binder {
    BoundService getService() {
      return BoundService.this;
    }
  }  
}