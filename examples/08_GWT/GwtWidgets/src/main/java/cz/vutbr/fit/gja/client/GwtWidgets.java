/*
 * Adapted from http://www.tutorialspoint.com/gwt/gwt_form_widgets.htm
 */

package cz.vutbr.fit.gja.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.VerticalPanel;

public class GwtWidgets implements EntryPoint {
  @Override
  public void onModuleLoad() {
    // create a push button
    PushButton pushButton = new PushButton("Click Me!");
    // create a push button
    PushButton pushButton1 = new PushButton("Click Me!");
    // disable a push button
    pushButton1.setEnabled(false);
    // add a clickListener to the push button
    pushButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        Window.alert("Hello World!");
      }
    });
    // Add push buttons to the root panel.
    VerticalPanel panel = new VerticalPanel();
    panel.setSpacing(10);
    panel.add(pushButton);
    panel.add(pushButton1);
    RootPanel.get("gwtContainer").add(panel);

    ToggleButton toggleButton = new ToggleButton("Click Me!");
    ToggleButton toggleButton1 = new ToggleButton("Click Me!");
    // disable a toggle button
    toggleButton1.setEnabled(false);
    // add a clickListener to the toggle button
    toggleButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        Window.alert("Hello World!");
      }
    });
    // Add toggle button to the root panel.
    VerticalPanel panel2 = new VerticalPanel();
    panel2.setSpacing(10);
    panel2.add(toggleButton);
    panel2.add(toggleButton1);
    RootPanel.get("gwtContainer").add(panel2);

    // Make a new check box, and select it by default.
    CheckBox checkBox1 = new CheckBox("Check Me!");
    CheckBox checkBox2 = new CheckBox("Check Me!");
    // set check box as selected
    checkBox1.setValue(true);
    // disable a checkbox
    checkBox2.setEnabled(false);
    checkBox1.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        CheckBox checkBox = (CheckBox) event.getSource();
        Window.alert("CheckBox is " + (checkBox.getValue() ? "" : "not") + " checked");
      }
    });
    // Add checkboxes to the root panel.
    VerticalPanel panel3 = new VerticalPanel();
    panel3.setSpacing(10);
    panel3.add(checkBox1);
    panel3.add(checkBox2);

    RootPanel.get("gwtContainer").add(panel3);

    // Create some radio buttons,
    RadioButton radioButton1 = new RadioButton("radioGroup", "First");
    RadioButton radioButton2 = new RadioButton("radioGroup", "Second");
    RadioButton radioButton3 = new RadioButton("radioGroup", "Third");
    // Check 'First' by default.
    radioButton1.setValue(true);
    // disable 'Second' radio button
    radioButton2.setEnabled(false);
    // Add toggle button to the root panel.
    VerticalPanel panel4 = new VerticalPanel();
    panel4.setSpacing(10);
    panel4.add(radioButton1);
    panel4.add(radioButton2);
    panel4.add(radioButton3);

    RootPanel.get("gwtContainer").add(panel4);

    ListBox listBox1 = new ListBox();
    listBox1.addItem("First");
    listBox1.addItem("Second");
    listBox1.addItem("Third");
    listBox1.addItem("Fourth");
    listBox1.addItem("Fifth");
    // Make a new list box, adding a few items to it.
    ListBox listBox2 = new ListBox();
    listBox2.addItem("First");
    listBox2.addItem("Second");
    listBox2.addItem("Third");
    listBox2.addItem("Fourth");
    listBox2.addItem("Fifth");
    // Make enough room for all five items
    listBox1.setVisibleItemCount(5);
    // setting itemcount value to 1 turns listbox into a drop-down list.
    listBox2.setVisibleItemCount(1);
    // Add listboxes to the root panel.
    VerticalPanel panel5 = new VerticalPanel();
    panel5.setSpacing(10);
    panel5.add(listBox1);
    panel5.add(listBox2);

    RootPanel.get("gwtContainer").add(panel5);

    MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
    oracle.add("A");
    oracle.add("AB");
    oracle.add("ABC");
    oracle.add("ABCD");
    oracle.add("B");
    oracle.add("BC");
    oracle.add("BCD");
    oracle.add("BCDE");
    oracle.add("C");
    oracle.add("CD");
    oracle.add("CDE");
    oracle.add("CDEF");
    oracle.add("D");
    oracle.add("DE");
    oracle.add("DEF");
    oracle.add("DEFG");
    // create the suggestion box and pass it the data created above
    SuggestBox suggestionBox = new SuggestBox(oracle);
    // set width to 200px.
    suggestionBox.setWidth("200");
    // Add suggestionbox to the root panel.
    VerticalPanel panel6 = new VerticalPanel();
    panel6.add(suggestionBox);
    RootPanel.get("gwtContainer").add(panel6);

    TextBox textBox1 = new TextBox();
    TextBox textBox2 = new TextBox();
    // add text to text box
    textBox2.setText("Hello World!");
    // set textbox as readonly
    textBox2.setReadOnly(true);
    // Add text boxes to the root panel.
    VerticalPanel panel7 = new VerticalPanel();
    panel7.setSpacing(10);
    panel7.add(textBox1);
    panel7.add(textBox2);
    RootPanel.get("gwtContainer").add(panel7);

    PasswordTextBox passwordTextBox1 = new PasswordTextBox();
    PasswordTextBox passwordTextBox2 = new PasswordTextBox();
    // add text to text box
    passwordTextBox2.setText("hell@W@rld");
    // set textbox as readonly
    passwordTextBox2.setReadOnly(true);
    // Add text boxes to the root panel.
    VerticalPanel panel8 = new VerticalPanel();
    panel8.setSpacing(10);
    panel8.add(passwordTextBox1);
    panel8.add(passwordTextBox2);

    RootPanel.get("gwtContainer").add(panel8);

    TextArea textArea1 = new TextArea();
    TextArea textArea2 = new TextArea();
    // set width as 10 characters
    textArea1.setCharacterWidth(20);
    textArea2.setCharacterWidth(20);
    // set height as 5 lines
    textArea1.setVisibleLines(5);
    textArea2.setVisibleLines(5);
    textArea2.setText(" Hello World! \n Be Happy! \n Stay Cool!");
    // set textbox as readonly
    textArea2.setReadOnly(true);
    // Add text boxes to the root panel.
    VerticalPanel panel9 = new VerticalPanel();
    panel9.setSpacing(10);
    panel9.add(textArea1);
    panel9.add(textArea2);

    RootPanel.get("gwtContainer").add(panel9);

    RichTextArea richTextArea = new RichTextArea();
    richTextArea.setHeight("200");
    richTextArea.setWidth("200");
    // add text to text area
    richTextArea.setHTML("<b>Hello World!</b><br/><br/>" + "<i>Be Happy!</i></br><br/><u>Stay Cool!</u>");
    // Add text boxes to the root panel.
    VerticalPanel panel10 = new VerticalPanel();
    panel10.add(richTextArea);
    RootPanel.get("gwtContainer").add(panel10);

    // create a FormPanel
    final FormPanel form = new FormPanel();
    // create a file upload widget
    final FileUpload fileUpload = new FileUpload();
    fileUpload.setName("upload");
    // create labels
    Label selectLabel = new Label("Select a file:");
    // create upload button
    Button uploadButton = new Button("Upload File");
    // pass action to the form to point to service handling file
    // receiving operation.
    form.setAction("upload.jsp");
    // set form to use the POST method, and multipart MIME encoding.
    form.setEncoding(FormPanel.ENCODING_MULTIPART);
    form.setMethod(FormPanel.METHOD_POST);
    // add a label
    panel.add(selectLabel);
    // add fileUpload widget
    panel.add(fileUpload);
    // add a button to upload the file
    panel.add(uploadButton);
    uploadButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        // get the filename to be uploaded
        String filename = fileUpload.getFilename();
        if (filename.length() == 0) {
          Window.alert("No File Specified!");
        } else {
          // submit the form
          form.submit();
        }
      }
    });
    form.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
      @Override
      public void onSubmitComplete(SubmitCompleteEvent event) {
        // When the form submission is successfully completed, this
        // event is fired. Assuming the service returned a response
        // of type text/html, we can get the result text here
        Window.alert(event.getResults());
      }
    });
    panel.setSpacing(10);
    // Add form to the root panel.
    form.add(panel);

    RootPanel.get("gwtContainer").add(form);

    final TextBox textBox = new TextBox();
    textBox.setWidth("275");
    Button button1 = new Button("Set Value of Hidden Input");
    Button button2 = new Button("Get Value of Hidden Input");
    final Hidden hidden = new Hidden();
    button1.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        hidden.setValue(textBox.getValue());
        Window.alert("Value of Hidden Widget Updated!");
      }
    });
    button2.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        Window.alert("Value of Hidden Widget: " + hidden.getValue());
      }
    });
    // Add widgets to the root panel.
    VerticalPanel panel12 = new VerticalPanel();
    panel12.setSpacing(10);
    panel12.add(textBox);
    panel12.add(button1);
    panel12.add(hidden);
    panel12.add(button2);

    RootPanel.get("gwtContainer").add(panel12);

  }
}
