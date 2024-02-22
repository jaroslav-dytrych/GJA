/*
 * Adapted from http://www.tutorialspoint.com/gwt/gwt_rpc_communication.htm
 */

package cz.vutbr.fit.gja.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("message")
public interface MessageService extends RemoteService {
  Message getMessage(String input);
}
