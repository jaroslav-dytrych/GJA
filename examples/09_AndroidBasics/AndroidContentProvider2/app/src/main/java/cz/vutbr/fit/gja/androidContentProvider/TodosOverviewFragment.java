package cz.vutbr.fit.gja.androidContentProvider;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.ListFragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;


/*
 * TodosOverviewActivity displays the existing todo items
 * in a list
 * 
 * You can create new ones via the ActionBar entry "Insert"
 * You can delete existing ones via a long press on the item
 */

public class TodosOverviewFragment extends ListFragment implements
    AdapterView.OnItemClickListener, LoaderManager.LoaderCallbacks<Cursor> {
  private static final int ACTIVITY_CREATE = 0;
  private static final int ACTIVITY_EDIT = 1;
  private static final int DELETE_ID = Menu.FIRST + 1;
  // private Cursor cursor;
  private SimpleCursorAdapter adapter;

  public static TodosOverviewFragment newInstance(int index) {
    TodosOverviewFragment f = new TodosOverviewFragment();
    f.setHasOptionsMenu(true);
    return f;
  }

/** Called when the activity is first created. */


  // Create the menu based on the XML defintion
  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    inflater.inflate(R.menu.listmenu, menu);
    getListView().setOnItemClickListener(this);
    registerForContextMenu(getListView());
  }

  @Override
  public View onCreateView(LayoutInflater inflater,
                           ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.todo_list, container, false);
    this.fillData();
    return view;
  }

  // Reaction to the menu selection
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
    case R.id.insert:
      createTodo();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public boolean onContextItemSelected(MenuItem item) {
    switch (item.getItemId()) {
    case DELETE_ID:
      AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
          .getMenuInfo();
      Uri uri = Uri.parse(MyTodoContentProvider.CONTENT_URI + "/"
          + info.id);
      TodosOverviewActivity.getContextOfApplication().getContentResolver().delete(uri, null, null);
      fillData();
      return true;
    }
    return super.onContextItemSelected(item);
  }

  private void createTodo() {
    Intent i = new Intent(getActivity(), TodoDetailActivity.class);
    startActivity(i);
  }

  // Opens the second activity if an entry is clicked
  @Override
  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    Intent i = new Intent(getActivity(), TodoDetailActivity.class);
    Uri todoUri = Uri.parse(MyTodoContentProvider.CONTENT_URI + "/" + id);
    i.putExtra(MyTodoContentProvider.CONTENT_ITEM_TYPE, todoUri);

    startActivity(i);
  }

  private void fillData() {

    // Fields from the database (projection)
    // Must include the _id column for the adapter to work
    String[] from = new String[] { TodoTable.COLUMN_SUMMARY };
    // Fields on the UI to which we map
    int[] to = new int[] { R.id.label };

    LoaderManager.getInstance(this).initLoader(0, null, this);
    adapter = new SimpleCursorAdapter(getActivity(), R.layout.todo_row, null, from,
        to, 0);

    setListAdapter(adapter);
  }

  @Override
  public void onCreateContextMenu(ContextMenu menu, View v,
      ContextMenuInfo menuInfo) {
    super.onCreateContextMenu(menu, v, menuInfo);
    menu.add(0, DELETE_ID, 0, R.string.menu_delete);
  }

  // Creates a new loader after the initLoader () call
  @Override
  public Loader<Cursor> onCreateLoader(int id, Bundle args) {
    String[] projection = { TodoTable.COLUMN_ID, TodoTable.COLUMN_SUMMARY };
    CursorLoader cursorLoader = new CursorLoader(getActivity(),
        MyTodoContentProvider.CONTENT_URI, projection, null, null, null);
    return cursorLoader;
  }

  @Override
  public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
    adapter.swapCursor(data);
  }

  @Override
  public void onLoaderReset(Loader<Cursor> loader) {
    // data is not available anymore, delete reference
    adapter.swapCursor(null);
  }

} 
