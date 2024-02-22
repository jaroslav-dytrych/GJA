/*
 * Adapted from http://www.tutorialspoint.com/gwt/gwt_fileupload_widget.htm
 */

package cz.vutbr.fit.gja.example.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.NamedFrame;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Encoding;
import com.smartgwt.client.types.FormMethod;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.UploadItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class smartgwtupload implements EntryPoint {

  private final String uploadId = "Upload";
  HLayout layout;
  VLayout mainLayout;

  @Override
  public void onModuleLoad() {

    final String uploadItemName = uploadId;
    String iframeName = uploadId + "_iframe";

    addCompletionCallback(this);

    final UploadItem fileItem = new UploadItem(uploadItemName);

    final DynamicForm mainForm = new DynamicForm();
    mainForm.setEncoding(Encoding.MULTIPART);
    mainForm.setMethod(FormMethod.POST);
    mainForm.setTarget(iframeName);
    mainForm.setAction(GWT.getModuleBaseURL() + "upload");
    IButton save = new IButton();
    save.setTitle("Save");
    mainForm.setItems(fileItem);

    layout = new HLayout();
    layout.setAlign(Alignment.CENTER);
    layout.addMember(mainForm);
    layout.addMember(save);

    mainLayout = new VLayout();

    NamedFrame magicFrame = new NamedFrame(iframeName);
    magicFrame.setWidth("1px");
    magicFrame.setHeight("1px");
    magicFrame.setVisible(false);
    RootPanel.get().add(magicFrame);

    mainLayout.addMember(layout);

    RootPanel.get().add(mainLayout);
    save.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {

      @Override
      public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
        if (fileItem.getValue() == null || fileItem.getValueAsString().isEmpty()) {
          SC.say("Select a file before uploading.");
          return;
        }
        mainForm.submitForm();

      }
    });

    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
      @Override
      public void execute() {
        enableMultiFileUploadNative(uploadItemName);
      }
    });

  }

  public void uploadCompleted(String msg) {
    String images[] = msg.split(",");
    for (String image : images) {
      final String file = image.replace("images/", "");
      Img img = new Img(file);
      HLayout newLayout = new HLayout();
      newLayout.addMember(img);
      IButton button = new IButton("Download");
      button.setWidth(100);
      newLayout.addMember(button);
      button.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {

        @Override
        public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
          Window.Location.assign(GWT.getHostPageBaseURL() + "SmartGWTUpload/download?file=" + file);

        }

      });
      img.setWidth(400);
      newLayout.setHeight(250);
      newLayout.setWidth(500);
      newLayout.setAlign(Alignment.CENTER);
      mainLayout.addMember(newLayout);
    }
  }

  private native void addCompletionCallback(smartgwtupload upload) /*-{
		$wnd.uploadComplete = function(msg) {
			upload.@cz.vutbr.fit.gja.example.client.smartgwtupload::uploadCompleted(Ljava/lang/String;)(msg);
		};
  }-*/;

  private native void enableMultiFileUploadNative(String uploadItemName) /*-{
		var newAttr = document.createAttribute('multiple');
		newAttr.nodeValue = 'multiple';
		var inputElement = $wnd.document.getElementsByName(uploadItemName)[0];
		inputElement.setAttributeNode(newAttr);
  }-*/;
}
