package cz.vutbr.fit.gja.androidPassword;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
 
public class MainActivity extends Activity {
 
  private EditText password;
  private Button btnSubmit;
 
  @Override
  public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
 
	addListenerOnButton();
 
  }
 
  public void addListenerOnButton() {
 
	password = (EditText) findViewById(R.id.txtPassword);	
	btnSubmit = (Button) findViewById(R.id.btnSubmit);
 
	btnSubmit.setOnClickListener(new OnClickListener() {
 
		@Override
		public void onClick(View v) {
 
		  Toast.makeText(MainActivity.this, password.getText(),
			Toast.LENGTH_SHORT).show();
 
		}
 
	});
 
  }
}