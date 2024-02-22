package cz.vutbr.fit.knot.gja;


import javax.jms.*;
import javax.naming.*;

public class Sender {

  public static void main(String[] args) {
    String messages[] = {"Message 1",
      "Message 2",
      "Message 3",
      "Message 4"};

    QueueConnectionFactory queueConnectionFactory = null;
    Queue queue = null;

    try {
      
      Context jndiContext = new InitialContext();
      
      queueConnectionFactory = (QueueConnectionFactory) jndiContext.lookup("QueueConnectionFactory");
      queue = (Queue) jndiContext.lookup("myQueue");
      
    } catch (NamingException nameEx) {
      System.out.println("Naming Exception: "
              + nameEx.toString());
    }

    QueueConnection queueConnection = null;

    try {
      queueConnection = queueConnectionFactory.createQueueConnection();
      
      QueueSession queueSession = queueConnection.createQueueSession(false, 
                                    Session.AUTO_ACKNOWLEDGE);
      
      QueueSender queueSender = queueSession.createSender(queue);
      
      TextMessage textMessage = queueSession.createTextMessage();

      for (int msgCount = 0; msgCount < messages.length; msgCount++) {
        textMessage.setText(messages[msgCount]);
        queueSender.send(textMessage);
        System.out.println(" sending line " + msgCount + " : " + messages[msgCount]);
      }

      textMessage.setText("end of message");
      queueSender.send(textMessage);
      System.out.println(" sending last line " + " : " + textMessage.getText());

      //data will wait in queue, even if connection is closed
      queueConnection.close();

      System.out.println("Sender closed");

    } catch (javax.jms.JMSException jmsEx) {
      System.out.println("JMS Exception: "
              + jmsEx.toString());
    } finally {
      if (queueConnection != null) {
        try {
          queueConnection.close();
        } catch (javax.jms.JMSException jmse) {
        }
      }
    }

  }
}
