/*
 * Adapted from http://www.tutorialspoint.com/gwt/gwt_complex_widgets.htm
 */

package cz.vutbr.fit.gja.client;

import java.util.Date;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;

public class GwtComplexWidgets implements EntryPoint {
  @Override
  public void onModuleLoad() {
    // create a label
    final Label labelMessage = new Label();
    labelMessage.setWidth("300");
    // Create a root tree item as department
    TreeItem department = new TreeItem();
    department.setText("Department");

    TreeItem salesDepartment = new TreeItem();
    salesDepartment.setText("Sales");
    TreeItem marketingDepartment = new TreeItem();
    marketingDepartment.setText("Marketing");
    TreeItem manufacturingDepartment = new TreeItem();
    manufacturingDepartment.setText("Manufacturing");

    TreeItem employee1 = new TreeItem();
    employee1.setText("Robert");
    TreeItem employee2 = new TreeItem();
    employee2.setText("Joe");
    TreeItem employee3 = new TreeItem();
    employee3.setText("Chris");
    // add employees to sales department
    salesDepartment.addItem(employee1);
    salesDepartment.addItem(employee2);
    salesDepartment.addItem(employee3);
    // create other tree items as employees
    TreeItem employee4 = new TreeItem();
    employee4.setText("Mona");
    TreeItem employee5 = new TreeItem();
    employee5.setText("Tena");
    // add employees to marketing department
    marketingDepartment.addItem(employee4);
    marketingDepartment.addItem(employee5);
    // create other tree items as employees
    TreeItem employee6 = new TreeItem();
    employee6.setText("Rener");
    TreeItem employee7 = new TreeItem();
    employee7.setText("Linda");
    // add employees to sales department
    manufacturingDepartment.addItem(employee6);
    manufacturingDepartment.addItem(employee7);
    // add departments to department item
    department.addItem(salesDepartment);
    department.addItem(marketingDepartment);
    department.addItem(manufacturingDepartment);
    // create the tree
    Tree tree = new Tree();
    // add root item to the tree
    tree.addItem(department);
    tree.addSelectionHandler(new SelectionHandler<TreeItem>() {
      @Override
      public void onSelection(SelectionEvent<TreeItem> event) {
        labelMessage.setText("Selected Value: " + ((TreeItem) event.getSelectedItem()).getText());
      }
    });
    // Add text boxes to the root panel.
    VerticalPanel panel = new VerticalPanel();
    panel.setSpacing(10);
    panel.add(tree);
    panel.add(labelMessage);
    // add the tree to the root panel
    RootPanel.get("gwtContainer").add(panel);

    MenuBar menu = new MenuBar();
    menu.setAutoOpen(true);
    menu.setWidth("100px");
    menu.setAnimationEnabled(true);
    // Create the file menu
    MenuBar fileMenu = new MenuBar(true);
    fileMenu.setAnimationEnabled(true);
    fileMenu.addItem("New", new Command() {
      @Override
      public void execute() {
        Window.alert("New");
      }
    });
    fileMenu.addItem("Open", new Command() {
      @Override
      public void execute() {
        Window.alert("Open");
      }
    });
    fileMenu.addSeparator();
    fileMenu.addItem("Exit", new Command() {
      @Override
      public void execute() {
        Window.alert("Exit");
      }
    });
    // Create the edit menu
    MenuBar editMenu = new MenuBar(true);
    editMenu.setAnimationEnabled(true);
    editMenu.addItem("Undo", new Command() {
      @Override
      public void execute() {
        Window.alert("Undo");
      }
    });
    editMenu.addItem("Redo", new Command() {
      @Override
      public void execute() {
        Window.alert("Redo");
      }
    });
    editMenu.addItem("Cut", new Command() {
      @Override
      public void execute() {
        Window.alert("Cut");
      }
    });
    editMenu.addItem("Copy", new Command() {
      @Override
      public void execute() {
        Window.alert("Copy");
      }
    });
    editMenu.addItem("Paste", new Command() {
      @Override
      public void execute() {
        Window.alert("Paste");
      }
    });
    menu.addItem(new MenuItem("File", fileMenu));
    menu.addSeparator();
    menu.addItem(new MenuItem("Edit", editMenu));

    // add the menu to the root panel
    RootPanel.get("gwtContainer").add(menu);

    DatePicker datePicker = new DatePicker();
    final Label text = new Label();
    // Set the value in the text box when the user selects a date
    datePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
      @Override
      public void onValueChange(ValueChangeEvent<Date> event) {
        Date date = event.getValue();
        String dateString = DateTimeFormat.getFormat("MM/dd/yyyy").format(date);
        text.setText(dateString);
      }
    });
    // Set the default value
    datePicker.setValue(new Date(), true);
    // Create a DateBox
    DateTimeFormat dateFormat = DateTimeFormat.getFormat("MM/dd/yyyy");
    DateBox dateBox = new DateBox();
    dateBox.setFormat(new DateBox.DefaultFormat(dateFormat));
    Label selectLabel = new Label("Permanent DatePicker:");
    Label selectLabel2 = new Label("DateBox with popup DatePicker:");
    // Add widgets to the root panel.
    VerticalPanel vPanel = new VerticalPanel();
    vPanel.setSpacing(10);
    vPanel.add(selectLabel);
    vPanel.add(text);
    vPanel.add(datePicker);
    vPanel.add(selectLabel2);
    vPanel.add(dateBox);

    RootPanel.get("gwtContainer").add(vPanel);

  }
}
