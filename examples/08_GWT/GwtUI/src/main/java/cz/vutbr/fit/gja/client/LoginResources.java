/*
 * Adapted from http://www.tutorialspoint.com/gwt/gwt_uibinder.htm
 */

package cz.vutbr.fit.gja.client;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface LoginResources extends ClientBundle {
  /**
   * Sample CssResource.
   */
  public interface MyCss extends CssResource {
    String blackText();

    String redText();

    String loginButton();

    String box();

    String background();

    String greyText();
  }

  @Source("Login.css")
  MyCss style();
}
