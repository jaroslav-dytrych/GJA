/*
 * Adapted from http://www.tutorialspoint.com/gwt/gwt_style_with_css.htm
 */

package cz.vutbr.fit.gja.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

public class GwtCssExample implements EntryPoint {
  @Override
  public void onModuleLoad() {
    // add button to change font to big when clicked.
    Button Btn1 = new Button("Big Text");
    Btn1.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        RootPanel.get("mytext").setStyleName("gwt-Big-Text");
      }
    });
    // add button to change font to small when clicked.
    Button Btn2 = new Button("Small Text");
    Btn2.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        RootPanel.get("mytext").setStyleName("gwt-Small-Text");
      }
    });

    RootPanel.get("gwtGreenButton").add(Btn1);
    RootPanel.get("gwtRedButton").add(Btn2);
  }
}
