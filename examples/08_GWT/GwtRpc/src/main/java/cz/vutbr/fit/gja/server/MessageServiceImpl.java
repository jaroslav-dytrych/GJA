/*
 * Adapted from http://www.tutorialspoint.com/gwt/gwt_rpc_communication.htm
 */

package cz.vutbr.fit.gja.server;

import cz.vutbr.fit.gja.client.MessageService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import cz.vutbr.fit.gja.client.Message;

public class MessageServiceImpl extends RemoteServiceServlet implements MessageService {
  private static final long serialVersionUID = 1L;

  @Override
  public Message getMessage(String input) {
    String messageString = "Hello " + input + "!";
    Message message = new Message();
    message.setMessage(messageString);
    return message;
  }
}
