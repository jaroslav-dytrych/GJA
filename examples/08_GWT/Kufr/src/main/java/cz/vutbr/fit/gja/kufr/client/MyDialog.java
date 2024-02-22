package cz.vutbr.fit.gja.kufr.client;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

class MyDialog extends DialogBox implements DeleteListener {
  private static final String UPLOAD_ACTION_URL = GWT.getModuleBaseURL() + "upload";
  ArrayList<FileListener> listeners;

  public MyDialog() {
    final FormPanel form = new FormPanel();
    form.setAction(UPLOAD_ACTION_URL);
    listeners = new ArrayList<FileListener>();
    // Because we're going to add a FileUpload widget, we'll need to set the
    // form to use the POST method, and multipart MIME encoding.
    form.setEncoding(FormPanel.ENCODING_MULTIPART);
    form.setMethod(FormPanel.METHOD_POST);

    // Create a panel to hold all of the form widgets.
    VerticalPanel panel = new VerticalPanel();
    form.setWidget(panel);

    // Create a FileUpload widget.
    final FileUpload upload = new FileUpload();
    upload.setName("uploadFormElement");
    panel.add(upload);

    HorizontalPanel buttons = new HorizontalPanel();

    Button submit = new Button("Submit image", new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        form.submit();
      }
    });
    buttons.add(submit);
    buttons.setCellHorizontalAlignment(submit, HasAlignment.ALIGN_LEFT);
    Button hide = new Button("Hide", new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        hide();
      }
    });
    buttons.add(hide);

    buttons.setCellHorizontalAlignment(hide, HasAlignment.ALIGN_RIGHT);

    buttons.setWidth("100%");
    panel.add(buttons);

    final ImageHolder images = new ImageHolder();
    images.addListener(this);

    images.setWidth("100%");
    panel.add(images);

    // Add an event handler to the form.
    form.addSubmitHandler(new FormPanel.SubmitHandler() {
      @Override
      public void onSubmit(SubmitEvent event) {
        // This event is fired just before the form is submitted. We can
        // take this opportunity to perform validation.
        String name = upload.getFilename();
        name = name.substring(name.lastIndexOf('\\') + 1);

        if (!name.endsWith("jpg") && !name.endsWith("jpeg") && !name.endsWith("png") && !name.endsWith("giff")) {
          Window.alert("Only images [\"jpg\",\"png\",\"giff\"] can be used.");
          event.cancel();
          return;
        }

        if (!images.addImage(name))
          event.cancel();
      }
    });

    form.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
      @Override
      public void onSubmitComplete(SubmitCompleteEvent event) {
        // When the form submission is successfully completed, this
        // event is fired. Assuming the service returned a response of type
        // text/html, we can get the result text here (see the FormPanel
        // documentation for further explanation).
        // Window.alert(event.getResults());
        String result = event.getResults();
        String path = result.replaceAll("<.*?>", "");
        // RootPanel.get().add(new Image(path));
        fireFileAdded(path);

      }
    });
    this.setWidget(form);
    this.setWidth("100%");
  }

  public void addFileListener(FileListener f) {
    listeners.add(f);
  }

  public void removeFileListener(FileListener f) {
    listeners.remove(f);
  }

  public void fireFileAdded(String str) {
    for (int i = 0; i < listeners.size(); i++) {
      listeners.get(i).fileAdded(str);
    }
  }

  public void fireFileRemoved(String str) {
    for (int i = 0; i < listeners.size(); i++) {
      listeners.get(i).fileRemoved(str);
    }
  }

  @Override
  public void elementDeleted(String d) {
    fireFileRemoved("img/" + d);
  }
}