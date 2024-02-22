/*
 * Adapted from http://www.tutorialspoint.com/gwt/gwt_rpc_communication.htm
 */

package cz.vutbr.fit.gja.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface MessageServiceAsync {

  void getMessage(String input, AsyncCallback<Message> callback);

}
