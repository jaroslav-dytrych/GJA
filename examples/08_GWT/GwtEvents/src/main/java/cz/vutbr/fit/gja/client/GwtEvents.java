/*
 * Adapted from http://www.tutorialspoint.com/gwt/gwt_event_handling.htm
 */

package cz.vutbr.fit.gja.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class GwtEvents implements EntryPoint {
  @Override
  public void onModuleLoad() {
    /*
     * create textbox and attach key down handler
     */
    TextBox textBox = new TextBox();
    textBox.addKeyDownHandler(new MyKeyDownHandler());
    /*
     * create button and attach click handler
     */
    Button button = new Button("Click Me!");
    button.addClickHandler(new MyClickHandler());
    VerticalPanel panel = new VerticalPanel();
    panel.setSpacing(10);
    panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
    panel.setSize("300", "100");
    panel.add(textBox);
    panel.add(button);
    DecoratorPanel decoratorPanel = new DecoratorPanel();
    decoratorPanel.add(panel);
    RootPanel.get("gwtContainer").add(decoratorPanel);
  }

  /**
   * create a custom click handler which will call onClick method when button 
   * is clicked.
   */
  private class MyClickHandler implements ClickHandler {
    @Override
    public void onClick(ClickEvent event) {
      Window.alert("Hello World!");
    }
  }

  /**
   * create a custom key down handler which will call onKeyDown method when
   * a key is down in textbox.
   */
  private class MyKeyDownHandler implements KeyDownHandler {
    @Override
    public void onKeyDown(KeyDownEvent event) {
      if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
        Window.alert(((TextBox) event.getSource()).getValue());
      }
    }
  }
}
