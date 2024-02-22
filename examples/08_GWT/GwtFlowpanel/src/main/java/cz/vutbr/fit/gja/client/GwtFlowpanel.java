/*
 * Adapted from http://www.tutorialspoint.com/gwt/gwt_flowpanel_widget.htm
 */

package cz.vutbr.fit.gja.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootPanel;

public class GwtFlowpanel implements EntryPoint {
  @Override
  public void onModuleLoad() {
    // Create a flow panel
    FlowPanel flowPanel = new FlowPanel();
    // Add CheckBoxes to flow Panel
    for (int i = 1; i <= 10; i++) {
      CheckBox checkBox = new CheckBox("Item" + i);
      flowPanel.add(checkBox);
    }
    DecoratorPanel decoratorPanel = new DecoratorPanel();
    decoratorPanel.setWidth("500");
    decoratorPanel.add(flowPanel);
    // Add the widgets to the root panel.
    RootPanel.get().add(decoratorPanel);
  }
}