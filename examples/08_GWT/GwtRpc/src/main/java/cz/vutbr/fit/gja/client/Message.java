/*
 * Adapted from http://www.tutorialspoint.com/gwt/gwt_rpc_communication.htm
 */

package cz.vutbr.fit.gja.client;

import java.io.Serializable;

public class Message implements Serializable {
  private static final long serialVersionUID = 1L;
  private String message;

  public Message() {
  };

  public void setMessage(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
