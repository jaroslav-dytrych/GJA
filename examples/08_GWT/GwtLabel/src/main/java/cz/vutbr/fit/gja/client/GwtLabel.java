/*
 * Adapted from http://www.tutorialspoint.com/gwt/gwt_label_widget.htm
 */

package cz.vutbr.fit.gja.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class GwtLabel implements EntryPoint {
  @Override
  public void onModuleLoad() {
    // create two Labels
    Label label1 = new Label("This is first GWT Label");
    Label label2 = new Label("This is second GWT Label");
    // use UIObject methods to set label properties.
    label1.setTitle("Title for first Label");
    label1.addStyleName("gwt-Green-Border");
    label2.setTitle("Title for second Label");
    label2.addStyleName("gwt-Blue-Border");
    // add labels to the root panel.
    VerticalPanel panel = new VerticalPanel();
    panel.add(label1);
    panel.add(label2);
    RootPanel.get("gwtContainer").add(panel);
  }
}
