package cz.vutbr.fit.gja.androidmaps;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.ListFragment;

public class MyListFragment extends ListFragment implements AdapterView.OnItemClickListener {
  static final String[] ACTIVITIES = new String[]{"Marker", "StreetView", "MultiMap",
      "Layers", "Indoor", "Polygon", "Camera", "Events", "Circle", "Tile", "Polyline"};


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
    l.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, ACTIVITIES));
    l.setTextFilterEnabled(true);
    return view;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    getListView().setOnItemClickListener(this);
  }

  @Override
  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    FragmentActivity activity = getActivity();
    switch (position) {
      case 0:
        startActivity(new Intent(activity, MarkerActivity.class));
        break;
      case 1:
        startActivity(new Intent(activity, StreetViewActivity.class));
        break;
      case 2:
        startActivity(new Intent(activity, MultiMapActivity.class));
        break;
      case 3:
        startActivity(new Intent(activity, LayersActivity.class));
        break;
      case 4:
        startActivity(new Intent(activity, IndoorActivity.class));
        break;
      case 5:
        startActivity(new Intent(activity, PolygonActivity.class));
        break;
      case 6:
        startActivity(new Intent(activity, CameraActivity.class));
        break;
      case 7:
        startActivity(new Intent(activity, EventsActivity.class));
        break;
      case 8:
        startActivity(new Intent(activity, CircleActivity.class));
        break;
      case 9:
        startActivity(new Intent(activity, TileActivity.class));
        break;
      case 10:
        startActivity(new Intent(activity, PolyLineActivity.class));
        break;
    }
  }

}
