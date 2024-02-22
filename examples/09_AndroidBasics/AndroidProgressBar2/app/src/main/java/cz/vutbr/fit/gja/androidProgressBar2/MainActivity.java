package cz.vutbr.fit.gja.androidProgressBar2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;

public class MainActivity extends Activity {
 
  Button btnStartProgress;
  ProgressBar progressBar;
  private int progressBarStatus = 0;
  private Handler progressBarHandler = new Handler(Looper.getMainLooper());

  AlertDialog progDialog;
  AlertDialog.Builder dialogBuilder;

  private long fileSize = 0;
 
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
 
    addListenerOnButton();
  }

  public void showProgressDialog() {
    dialogBuilder = new AlertDialog.Builder(this);
    LayoutInflater inflater = (LayoutInflater) getSystemService( Context.LAYOUT_INFLATER_SERVICE );
    View dialogView = inflater.inflate(R.layout.progress_dialog_layout, null);
    dialogBuilder.setView(dialogView);
    dialogBuilder.setCancelable(true);
    progDialog = dialogBuilder.create();
    progDialog.show();
    progressBar = progDialog.findViewById(R.id.progressBar2);
    progressBar.setMin(0);
    progressBar.setMax(100);
    progressBar.setProgress(0);
  }

  public void hideProgressDialog(){
    progDialog.dismiss();
  }

  public void addListenerOnButton() {
 
    btnStartProgress = (Button) findViewById(R.id.btnStartProgress);
    btnStartProgress.setOnClickListener(new OnClickListener() {
 
      @Override
      public void onClick(View v) {
 
        // prepare for a progress bar dialog
        showProgressDialog();

        //reset progress bar status
        progressBarStatus = 0;
 
        //reset filesize
        fileSize = 0;

        new Thread(new Runnable() {
          public void run() {
            while (progressBarStatus < 100) {
 
              // process some tasks
              progressBarStatus = doSomeTasks();
 
              // your computer is too fast, sleep 1 second
              try {
                Thread.sleep(100);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
 
              // Update the progress bar
              progressBarHandler.post(new Runnable() {
                public void run() {
                  progressBar.setProgress(progressBarStatus);
                }
              });
            }
 
            // ok, file is downloaded,
            if (progressBarStatus >= 100) {
 
              // sleep 2 seconds, so that you can see the 100%
              try {
                Thread.sleep(2000);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }

              // hide the progress bar dialog
              hideProgressDialog();
            }
          }
        }).start();
       
      }
 
    });
 
  }
 
  // file download simulator... a really simple
  public int doSomeTasks() {
    
    return (int) ++fileSize;
  }
 
}