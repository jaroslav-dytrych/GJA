package cz.vutbr.fit.gja.alarmActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
  @Override
  public void onReceive(Context context, Intent intent) {
    Toast.makeText(context, "Time is up!!!!.",
        Toast.LENGTH_LONG).show();
    // Vibrate the mobile phone
    //Requires Permission
    //Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
    //vibrator.vibrate(2000);
  }

} 