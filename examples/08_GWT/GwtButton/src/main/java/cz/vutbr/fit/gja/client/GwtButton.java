/*
 * Adapted from http://www.tutorialspoint.com/gwt/gwt_button_widget.htm
 */

package cz.vutbr.fit.gja.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class GwtButton implements EntryPoint {
  @Override
  public void onModuleLoad() {
    // create buttons
    Button redButton = new Button("Red");
    Button greenButton = new Button("Green");
    Button blueButton = new Button("Blue");
    // use UIObject methods to set button properties.
    redButton.setWidth("100px");
    greenButton.setWidth("100px");
    blueButton.setWidth("100px");
    greenButton.addStyleName("gwt-Green-Button");
    blueButton.addStyleName("gwt-Blue-Button");
    // add a clickListener to the button
    redButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        Window.alert("Red Button clicked!");
      }
    });
    // add a clickListener to the button
    greenButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        Window.alert("Green Button clicked!");
      }
    });
    // add a clickListener to the button
    blueButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        Window.alert("Blue Button clicked!");
      }
    });
    // Add button to the root panel.
    VerticalPanel panel = new VerticalPanel();
    panel.setSpacing(10);
    panel.add(redButton);
    panel.add(greenButton);
    panel.add(blueButton);
    RootPanel.get("gwtContainer").add(panel);
  }
}
