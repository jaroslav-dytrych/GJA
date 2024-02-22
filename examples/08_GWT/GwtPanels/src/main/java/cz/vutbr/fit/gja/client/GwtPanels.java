/*
 * Adapted from http://www.tutorialspoint.com/gwt/gwt_layout_panels.htm
 */

package cz.vutbr.fit.gja.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class GwtPanels implements EntryPoint {
  @Override
  public void onModuleLoad() {
    // Create a horizontal panel
    HorizontalPanel horizontalPanel = new HorizontalPanel();
    // Add CheckBoxes to horizontal Panel
    for (int i = 1; i <= 10; i++) {
      CheckBox checkBox = new CheckBox("Item" + i);
      horizontalPanel.add(checkBox);
    }
    DecoratorPanel decoratorPanel = new DecoratorPanel();
    decoratorPanel.setWidth("500");
    decoratorPanel.add(horizontalPanel);
    // Add the widgets to the root panel.
    RootPanel.get("gwtContainer").add(decoratorPanel);

    VerticalPanel verticalPanel = new VerticalPanel();
    // Add CheckBoxes to vertical Panel
    for (int i = 1; i <= 10; i++) {
      CheckBox checkBox = new CheckBox("Item" + i);
      verticalPanel.add(checkBox);
    }
    DecoratorPanel decoratorPanel2 = new DecoratorPanel();
    decoratorPanel2.add(verticalPanel);
    // Add the widgets to the root panel.
    RootPanel.get("gwtContainer").add(decoratorPanel2);

    SplitLayoutPanel horizontalSplitPanel = new SplitLayoutPanel();
    horizontalSplitPanel.setSize("300px", "200px");
    // Add some content
    String randomText = "This is a sample text.";
    for (int i = 0; i < 2; i++) {
      randomText += randomText;
    }
    ScrollPanel leftScrollPanel = new ScrollPanel();
    ScrollPanel rightScrollPanel = new ScrollPanel();
    horizontalSplitPanel.addWest(leftScrollPanel, 100);
    horizontalSplitPanel.add(rightScrollPanel);
    leftScrollPanel.add(new HTML(randomText));
    rightScrollPanel.add(new HTML(randomText));
    DecoratorPanel decoratorPanel3 = new DecoratorPanel();
    decoratorPanel3.add(horizontalSplitPanel);
    // Add the widgets to the root panel.
    RootPanel.get("gwtContainer").add(decoratorPanel3);

    SplitLayoutPanel verticalSplitPanel = new SplitLayoutPanel();
    verticalSplitPanel.setSize("300px", "200px");

    // Add some content
    String randomText2 = "This is a sample text.";
    for (int i = 0; i < 2; i++) {
      randomText += randomText;
    }

    ScrollPanel topScrollPanel = new ScrollPanel();
    ScrollPanel bottomScrollPanel = new ScrollPanel();
    verticalSplitPanel.addNorth(topScrollPanel, 70);
    verticalSplitPanel.add(bottomScrollPanel);

    topScrollPanel.add(new HTML(randomText2));
    bottomScrollPanel.add(new HTML(randomText2));
    DecoratorPanel decoratorPanel4 = new DecoratorPanel();
    decoratorPanel4.add(verticalSplitPanel);
    // Add the widgets to the root panel.
    RootPanel.get("gwtContainer").add(decoratorPanel4);

    final FlexTable flexTable = new FlexTable();
    FlexCellFormatter cellFormatter = flexTable.getFlexCellFormatter();
    flexTable.addStyleName("flexTable");
    flexTable.setWidth("32em");
    flexTable.setCellSpacing(5);
    flexTable.setCellPadding(3);
    // Add some text
    cellFormatter.setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_LEFT);
    flexTable.setHTML(0, 0, "This is a FlexTable that supports" + " <b>colspans</b> and <b>rowspans</b>."
        + " You can use it to format your page" + " or as a special purpose table.");
    cellFormatter.setColSpan(0, 0, 2);
    // Add a button that will add more rows to the table
    Button addRowButton = new Button("Add a Row");
    addRowButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        addRow(flexTable);
      }
    });
    addRowButton.addStyleName("fixedWidthButton");
    // Add a button that will add more rows to the table
    Button removeRowButton = new Button("Remove a Row");
    removeRowButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        removeRow(flexTable);
      }
    });
    removeRowButton.addStyleName("fixedWidthButton");
    VerticalPanel buttonPanel = new VerticalPanel();
    buttonPanel.setStyleName("flexTable-buttonPanel");
    buttonPanel.add(addRowButton);
    buttonPanel.add(removeRowButton);
    flexTable.setWidget(0, 1, buttonPanel);
    cellFormatter.setVerticalAlignment(0, 1, HasVerticalAlignment.ALIGN_TOP);
    // Add two rows to start
    addRow(flexTable);
    addRow(flexTable);
    // Add the widgets to the root panel.
    RootPanel.get("gwtContainer").add(flexTable);

    Grid grid = new Grid(2, 2);
    // Add images to the grid
    int numRows = grid.getRowCount();
    int numColumns = grid.getColumnCount();
    for (int row = 0; row < numRows; row++) {
      for (int col = 0; col < numColumns; col++) {
        grid.setWidget(row, col, new Image("http://www.tutorialspoint.com/images/gwt-mini.png"));
      }
    }
    DecoratorPanel decoratorPanel5 = new DecoratorPanel();
    decoratorPanel5.add(grid);
    // Add the widgets to the root panel.
    RootPanel.get("gwtContainer").add(decoratorPanel5);

    final DeckPanel deckPanel = new DeckPanel();
    deckPanel.setSize("300px", "120px");
    deckPanel.setStyleName("deckpanel");
    // Create lables to add to deckpanel
    Label label1 = new Label("This is first Page");
    Label label2 = new Label("This is second Page");
    Label label3 = new Label("This is third Page");
    // Add labels to deckpanel
    deckPanel.add(label1);
    deckPanel.add(label2);
    deckPanel.add(label3);
    // show first label
    deckPanel.showWidget(0);
    // create button bar
    HorizontalPanel buttonBar = new HorizontalPanel();
    buttonBar.setSpacing(5);
    // create button and add click handlers
    // show different labels on click of different buttons
    Button button1 = new Button("Page 1");
    button1.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        deckPanel.showWidget(0);
      }
    });
    Button button2 = new Button("Page 2");
    button2.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        deckPanel.showWidget(1);
      }
    });
    Button button3 = new Button("Page 3");
    button3.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        deckPanel.showWidget(2);
      }
    });
    buttonBar.add(button1);
    buttonBar.add(button2);
    buttonBar.add(button3);
    VerticalPanel vPanel = new VerticalPanel();
    vPanel.add(deckPanel);
    vPanel.add(buttonBar);
    // Add the widgets to the root panel.
    RootPanel.get("gwtContainer").add(vPanel);

    DockPanel dockPanel = new DockPanel();
    dockPanel.setStyleName("dockpanel");
    dockPanel.setSpacing(4);
    dockPanel.setHorizontalAlignment(DockPanel.ALIGN_CENTER);
    // Add text all around
    dockPanel.add(new HTML("This is the first north component."), DockPanel.NORTH);
    dockPanel.add(new HTML("This is the first south component."), DockPanel.SOUTH);
    dockPanel.add(new HTML("This is the east component."), DockPanel.EAST);
    dockPanel.add(new HTML("This is the west component."), DockPanel.WEST);
    dockPanel.add(new HTML("This is the second north component."), DockPanel.NORTH);
    dockPanel.add(new HTML("This is the second south component"), DockPanel.SOUTH);
    // Add scrollable text in the center
    HTML contents = new HTML("This is a ScrollPanel contained" + " at the center of a DockPanel. "
        + " By putting some fairly large contents in the middle"
        + " and setting its size explicitly, it becomes a scrollable area"
        + " within the page, but without requiring the use of an IFRAME."
        + " Here's quite a bit more meaningless text that will serveprimarily"
        + " to make this thing scroll off the bottom of its visible area."
        + " Otherwise, you might have to make it really, really" + " small in order to see the nifty scroll bars!");
    ScrollPanel scroller = new ScrollPanel(contents);
    scroller.setSize("400px", "100px");
    dockPanel.add(scroller, DockPanel.CENTER);
    VerticalPanel vPanel2 = new VerticalPanel();
    vPanel2.add(dockPanel);
    // Add the widgets to the root panel.
    RootPanel.get("gwtContainer").add(vPanel2);

    String htmlString = "This is a <b>HTMLPanel</b> containing" + " html contents. "
        + " <i>By putting some fairly large contents in the middle"
        + " and setting its size explicitly, it becomes a scrollable area"
        + " within the page, but without requiring the use of an	IFRAME.</i>"
        + " <u>Here's quite a bit more meaningless text that will serve"
        + " to make this thing scroll off the bottom of its visible area."
        + " Otherwise, you might have to make it really, really" + " small in order to see the nifty scroll bars!</u>";
    HTMLPanel htmlPanel = new HTMLPanel(htmlString);
    DecoratorPanel panel = new DecoratorPanel();
    panel.add(htmlPanel);
    // Add the widgets to the root panel.
    RootPanel.get("gwtContainer").add(panel);

    // Create an empty tab panel
    TabPanel tabPanel = new TabPanel();
    // create contents for tabs of tabpanel
    Label label11 = new Label("This is contents of TAB 1");
    label1.setHeight("200");
    Label label22 = new Label("This is contents of TAB 2");
    label2.setHeight("200");
    Label label33 = new Label("This is contents of TAB 3");
    label3.setHeight("200");
    // create titles for tabs
    String tab1Title = "TAB 1";
    String tab2Title = "TAB 2";
    String tab3Title = "TAB 3";
    // create tabs
    tabPanel.add(label11, tab1Title);
    tabPanel.add(label22, tab2Title);
    tabPanel.add(label33, tab3Title);

    // select first tab
    tabPanel.selectTab(0);
    // set width if tabpanel
    tabPanel.setWidth("400");
    // Add the widgets to the root panel.
    RootPanel.get("gwtContainer").add(tabPanel);

    OptionalTextBox otb = new OptionalTextBox("Check this to enable me");
    RootPanel.get("gwtContainer").add(otb);

    HTML contents1 = new HTML("This is a FocusPanel." + " Click on the panel and it will attain focus.");
    // create focus panel with content
    FocusPanel focusPanel = new FocusPanel(contents1);
    focusPanel.setSize("400px", "100px");
    DecoratorPanel decoratorPanel6 = new DecoratorPanel();
    decoratorPanel6.add(focusPanel);
    // Add the widgets to the root panel.
    RootPanel.get("gwtContainer").add(decoratorPanel6);

    final FormPanel form = new FormPanel();
    form.setAction("formHandler.jsp");
    // Because we're going to add a FileUpload widget,
    // we'll need to set the form to use the POST method,
    // and multipart MIME encoding.
    form.setEncoding(FormPanel.ENCODING_MULTIPART);
    form.setMethod(FormPanel.METHOD_POST);
    // Create a panel to hold all of the form widgets.
    VerticalPanel panel1 = new VerticalPanel();
    panel1.setSpacing(10);
    form.setWidget(panel1);
    // Create a TextBox, giving it a name so that it will be submitted.
    final TextBox tb = new TextBox();
    tb.setWidth("220");
    tb.setName("textBoxFormElement");
    panel1.add(tb);
    // Create a ListBox, giving it a name and
    // some values to be associated with its options.
    ListBox lb = new ListBox();
    lb.setName("listBoxFormElement");
    lb.addItem("item1", "item1");
    lb.addItem("item2", "item2");
    lb.addItem("item3", "item3");
    lb.setWidth("220");
    panel1.add(lb);
    // Create a FileUpload widget.
    FileUpload upload = new FileUpload();
    upload.setName("uploadFormElement");
    panel1.add(upload);

    // Add a 'submit' button.
    panel1.add(new Button("Submit", new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        form.submit();
      }
    }));

    form.addSubmitHandler(new FormPanel.SubmitHandler() {
      @Override
      public void onSubmit(SubmitEvent event) {
        // This event is fired just before the form is submitted.
        // We can take this opportunity to perform validation.
        if (tb.getText().length() == 0) {
          Window.alert("The text box must not be empty");
          event.cancel();
        }
      }
    });

    form.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {

      @Override
      public void onSubmitComplete(SubmitCompleteEvent event) {
        // When the form submission is successfully completed,
        // this event is fired. Assuming the service returned
        // a response of type text/html, we can get the result
        // here.
        Window.alert(event.getResults());
      }
    });
    DecoratorPanel decoratorPanel1 = new DecoratorPanel();
    decoratorPanel1.add(form);
    // Add the widgets to the root panel.
    RootPanel.get("gwtContainer").add(decoratorPanel1);

    // Create a panel and add it to the screen
    VerticalPanel panel3 = new VerticalPanel();
    RootPanel.get("gwtContainer").add(panel3);
    panel.setStyleName("table-center");
    //
    // Create a PopUpPanel with a button to close it
    final PopupPanel popup = new PopupPanel(false);
    popup.setStyleName("demo-PopUpPanel");
    VerticalPanel PopupContents = new VerticalPanel();
    PopupContents.add(new Label("PopUpPanel"));
    popup.add(new Label("PopUpPanel"));
    HTML message = new HTML("Click 'Close' to close");
    message.setStyleName("demo-PopUpPanel-message");

    Button button = new Button("Close", new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        popup.hide();
      }
    });
    SimplePanel holder = new SimplePanel();
    holder.add(button);
    holder.setStyleName("demo-PopUpPanel-footer");
    PopupContents.add(message);
    PopupContents.add(holder);
    popup.setWidget(PopupContents);
    //
    // Add a button to the demo to show the above PopUpPanel
    button = new Button("Show PopUpPanel", new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        popup.center();
      }
    });
    panel3.add(button);

    VerticalPanel panel4 = new VerticalPanel();
    RootPanel.get("gwtContainer").add(panel4);
    panel4.setStyleName("table-center");
    //
    // Create a DialogBox with a button to close it
    final DialogBox dialogbox = new DialogBox(false);
    dialogbox.setStyleName("demo-DialogBox");
    VerticalPanel DialogBoxContents = new VerticalPanel();
    dialogbox.setText("DialogBox");
    HTML message2 = new HTML("Click 'Close' to close");
    message2.setStyleName("demo-DialogBox-message");

    Button button4 = new Button("Close", new ClickHandler() {
      public void onClick(ClickEvent event) {
        dialogbox.hide();
      }
    });
    SimplePanel holder2 = new SimplePanel();
    holder2.add(button4);
    holder2.setStyleName("demo-DialogBox-footer");
    DialogBoxContents.add(message2);
    DialogBoxContents.add(holder2);
    dialogbox.setWidget(DialogBoxContents);
    //
    // Add a button to the demo to show the above DialogBox
    Button button5 = new Button("Show DialogBox", new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        dialogbox.center();
      }
    });
    panel4.add(button5);

  }

  private void addRow(FlexTable flexTable) {
    int numRows = flexTable.getRowCount();
    flexTable.setWidget(numRows, 0, new Image("http://www.tutorialspoint.com/images/gwt-mini.png"));
    flexTable.setWidget(numRows, 1, new Image("http://www.tutorialspoint.com/images/gwt-mini.png"));
    flexTable.getFlexCellFormatter().setRowSpan(0, 1, numRows + 1);
  }

  private void removeRow(FlexTable flexTable) {
    int numRows = flexTable.getRowCount();
    if (numRows > 1) {
      flexTable.removeRow(numRows - 1);
      flexTable.getFlexCellFormatter().setRowSpan(0, 1, numRows - 1);
    }
  }

  private static class OptionalTextBox extends Composite implements ClickHandler {
    private TextBox textBox = new TextBox();
    private CheckBox checkBox = new CheckBox();

    /**
     * Constructs an OptionalTextBox with the given caption on the check.
     * 
     * @param caption The caption to be displayed with the check box
     */
    public OptionalTextBox(String caption) {
      // Place the check above the text box using a vertical panel.
      VerticalPanel panel = new VerticalPanel();
      // panel.setBorderWidth(1);
      panel.setSpacing(10);
      panel.add(checkBox);
      panel.add(textBox);
      textBox.setWidth("200");
      // Set the check box's caption, and check it by default.
      checkBox.setText(caption);
      checkBox.setValue(true);
      checkBox.addClickHandler(this);

      DecoratorPanel decoratorPanel = new DecoratorPanel();
      decoratorPanel.add(panel);
      // All composites must call initWidget() in their constructors.
      initWidget(decoratorPanel);

    }

    @Override
    public void onClick(ClickEvent event) {
      if (event.getSource() == checkBox) {
        // When the check box is clicked,
        // update the text box's enabled state.

        textBox.setEnabled(checkBox.getValue());
      }
    }
  }

}
