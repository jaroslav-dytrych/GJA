package cz.vutbr.fit.gja.androidRemoteService;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;

import java.util.List;

public class MainActivity extends Activity {

  Messenger myService = null;
  boolean isBound;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Intent intent = new Intent("cz.vutbr.fit.gja.androidRemoteService.RemoteService");
    Intent intentToSent = createExplicitFromImplicitIntent(this,intent);
    bindService(intentToSent, myConnection, Context.BIND_AUTO_CREATE);

  }
  
  public static Intent createExplicitFromImplicitIntent(Context context, Intent implicitIntent) {
    // Retrieve all services that can match the given intent
    PackageManager pm = context.getPackageManager();
    List<ResolveInfo> resolveInfo = pm.queryIntentServices(implicitIntent, PackageManager.ResolveInfoFlags.of(0));
 
    // Make sure only one match was found
    if (resolveInfo == null || resolveInfo.size() != 1) {
      return null;
    }
 
    // Get component info and create ComponentName
    ResolveInfo serviceInfo = resolveInfo.get(0);
    String packageName = serviceInfo.serviceInfo.packageName;
    String className = serviceInfo.serviceInfo.name;
    ComponentName component = new ComponentName(packageName, className);
 
    // Create a new intent. Use the old one for extras and such reuse
    Intent explicitIntent = new Intent(implicitIntent);
 
    // Set the component to be explicit
    explicitIntent.setComponent(component);
 
    return explicitIntent;
  }

  private ServiceConnection myConnection = new ServiceConnection() {
    public void onServiceConnected(ComponentName className, IBinder service) {
      myService = new Messenger(service);
      isBound = true;
    }

    public void onServiceDisconnected(ComponentName className) {
      myService = null;
        isBound = false;
    }
  };
    
  public void sendMessage(View view)
  {
    if (!isBound) return;
            
    Message msg = Message.obtain();
            
    Bundle bundle = new Bundle();
    bundle.putString("MyString", "Message Received");
            
    msg.setData(bundle);
            
    try {
      myService.send(msg);
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }
}