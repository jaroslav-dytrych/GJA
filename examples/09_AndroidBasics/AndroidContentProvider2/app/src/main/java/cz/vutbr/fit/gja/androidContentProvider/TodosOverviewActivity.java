package cz.vutbr.fit.gja.androidContentProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

public class TodosOverviewActivity extends FragmentActivity {
  public static Context contextOfApplication;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    contextOfApplication = getApplicationContext();

    setContentView(R.layout.fragment_activity);

    int index=0;
    TodosOverviewFragment f = TodosOverviewFragment.newInstance(index);

    FragmentTransaction ft = getSupportFragmentManager()
        .beginTransaction();
    ft.replace(R.id.mylist, f);
    ft.commit();

  }//end oncreate

  public static Context getContextOfApplication()
  {
    return contextOfApplication;
  }
}
