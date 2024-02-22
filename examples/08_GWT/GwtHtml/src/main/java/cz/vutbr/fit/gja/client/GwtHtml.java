/*
 * Adapted from http://www.tutorialspoint.com/gwt/gwt_html_widget.htm
 */

package cz.vutbr.fit.gja.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class GwtHtml implements EntryPoint {
  @Override
  public void onModuleLoad() {
    // create two HTML widgets
    HTML html1 = new HTML("This is first GWT HTML widget using <b>tag of html</b>.");
    HTML html2 = new HTML("This is second GWT HTML widget using <i>tag of html</i>.");
    // use UIObject methods to set HTML widget properties.
    html1.addStyleName("gwt-Green-Border");
    html2.addStyleName("gwt-Blue-Border");
    // add widgets to the root panel.
    VerticalPanel panel = new VerticalPanel();
    panel.setSpacing(10);
    panel.add(html1);
    panel.add(html2);
    RootPanel.get("gwtContainer").add(panel);
  }
}
