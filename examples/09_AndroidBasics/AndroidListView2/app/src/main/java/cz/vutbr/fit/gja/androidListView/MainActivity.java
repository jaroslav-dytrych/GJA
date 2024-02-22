package cz.vutbr.fit.gja.androidListView;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends FragmentActivity {

	public static Context contextOfApplication;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		contextOfApplication = getApplicationContext();

		setContentView(R.layout.activity_main);

		int index=0;
		MyListFragment f = MyListFragment.newInstance(index);

		FragmentTransaction ft = getSupportFragmentManager()
				.beginTransaction();
		ft.replace(R.id.mylist, f);
		ft.commit();

	} //end oncreate

	public static Context getContextOfApplication()	{
		return contextOfApplication;
	}
 
}