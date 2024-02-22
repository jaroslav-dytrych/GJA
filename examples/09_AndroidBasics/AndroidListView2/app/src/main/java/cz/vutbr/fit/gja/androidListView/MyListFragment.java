package cz.vutbr.fit.gja.androidListView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

public class MyListFragment extends ListFragment implements AdapterView.OnItemClickListener {
  static final String[] FRUITS = new String[] { "Apple", "Avocado", "Banana",
      "Blueberry", "Coconut", "Durian", "Guava", "Kiwifruit",
      "Jackfruit", "Mango", "Olive", "Pear", "Sugar-apple" };

  public static MyListFragment newInstance(int index) {
    MyListFragment f = new MyListFragment();
    return f;
  }

  @Override
  public View onCreateView(LayoutInflater inflater,
                           ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.list_fragment, container, false);

    ListView l = (ListView) view.findViewById(android.R.id.list);
    l.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, FRUITS));
    return view;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    getListView().setOnItemClickListener(this);
  }

  @Override
  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    Toast.makeText(getActivity(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
  }

}
