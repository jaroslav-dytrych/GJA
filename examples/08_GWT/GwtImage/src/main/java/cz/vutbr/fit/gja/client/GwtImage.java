/*
 * Adapted from http://www.tutorialspoint.com/gwt/gwt_image_widget.htm
 */

package cz.vutbr.fit.gja.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class GwtImage implements EntryPoint {
  @Override
  public void onModuleLoad() {
    // Create a Image widget
    Image image = new Image();
    // set image source
    image.setUrl("http://upload.wikimedia.org/wikipedia/en/6/6f/Yoda_Attack_of_the_Clones.png");
    // Add image to the root panel.
    VerticalPanel panel = new VerticalPanel();
    panel.add(image);
    RootPanel.get("gwtContainer").add(panel);
  }
}
