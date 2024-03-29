/*
 * Adapted from http://www.tutorialspoint.com/gwt/gwt_celllist_widget.htm
 */

package cz.vutbr.fit.gja.client;

import java.util.Arrays;
import java.util.List;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionModel;
import com.google.gwt.view.client.SingleSelectionModel;

public class GwtCellList implements EntryPoint {
  /**
   * A simple data type that represents a contact.
   */
  private static class Contact {
    private static int nextId = 0;
    private final int id;
    private String name;

    public Contact(String name) {
      nextId++;
      this.id = nextId;
      this.name = name;
    }
  }

  /**
   * A custom {@link Cell} used to render a {@link Contact}.
   */
  private static class ContactCell extends AbstractCell<Contact> {
    @Override
    public void render(com.google.gwt.cell.client.Cell.Context context, Contact value, SafeHtmlBuilder sb) {
      // TODO Auto-generated method stub
      if (value != null) {
        sb.appendEscaped(value.name);
      }
    }
  }

  /**
   * The list of data to display.
   */
  private static final List<Contact> CONTACTS = Arrays.asList(
    new Contact("John"), 
    new Contact("Joe"),
    new Contact("Michael"), 
    new Contact("Sarah"), 
    new Contact("George")
  );

  @Override
  public void onModuleLoad() {
    /*
     * Define a key provider for a Contact. We use the unique ID as the key,
     * which allows to maintain selection even if the name changes.
     */
    ProvidesKey<Contact> keyProvider = new ProvidesKey<Contact>() {
      @Override
      public Object getKey(Contact item) {
        // Always do a null check.
        return (item == null) ? null : item.id;
      };
    };
    // Create a CellList using the keyProvider.
    CellList<Contact> cellList = new CellList<Contact>(new ContactCell(), keyProvider);
    // Push data into the CellList.
    cellList.setRowCount(CONTACTS.size(), true);
    cellList.setRowData(0, CONTACTS);
    // Add a selection model using the same keyProvider.
    SelectionModel<Contact> selectionModel = new SingleSelectionModel<Contact>(keyProvider);
    cellList.setSelectionModel(selectionModel);
    /*
     * Select a contact. The selectionModel will select based on the ID because
     * we used a keyProvider.
     */
    Contact sarah = CONTACTS.get(3);
    selectionModel.setSelected(sarah, true);
    // Modify the name of the contact.
    sarah.name = "Sara";
    /*
     * Redraw the CellList. Sarah/Sara will still be selected because we
     * identify her by ID. If we did not use a keyProvider, Sara would not be
     * selected.
     */
    cellList.redraw();
    VerticalPanel panel = new VerticalPanel();
    panel.setBorderWidth(1);
    panel.setWidth("200");
    panel.add(cellList);
    // Add the widgets to the root panel.
    RootPanel.get().add(panel);
  }
}
