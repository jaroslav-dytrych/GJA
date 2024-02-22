/*
 * Adapted from http://www.tutorialspoint.com/gwt/gwt_uibinder.htm
 */

package cz.vutbr.fit.gja.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class GwtUI implements EntryPoint {
  @Override
  public void onModuleLoad() {
    RootPanel.get().add(new Login());
  }
}
