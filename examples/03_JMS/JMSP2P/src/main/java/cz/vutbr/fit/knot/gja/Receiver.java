package cz.vutbr.fit.knot.gja;


import javax.jms.*;
import javax.naming.*;

public class Receiver {

  public static void main(String[] args) {

    QueueConnectionFactory queueConnectionFactory = null;
    Queue queue = null;

    try {

      Context jndiContext = new InitialContext();
      
      queueConnectionFactory = (QueueConnectionFactory) jndiContext.lookup("QueueConnectionFactory");
      queue = (Queue) jndiContext.lookup("myQueue");

    } catch (NamingException nameEx) {
      System.out.println("Naming Exception: " + nameEx.toString());
    }

    QueueConnection queueConnection = null;

    try {

      queueConnection = queueConnectionFactory.createQueueConnection();
      
      QueueSession queueSession = queueConnection.createQueueSession(false, 
                                    Session.AUTO_ACKNOWLEDGE);
      
      QueueReceiver queueReceiver = queueSession.createReceiver(queue);
      
      queueConnection.start();

      TextMessage textMessage = null;

      while (true) {
        textMessage = (TextMessage) queueReceiver.receive();
        System.out.println(" receiving line " + " : " + textMessage.getText());
        if (textMessage.getText().equals("end of message")) {
          break;
        }
      }

      queueConnection.close();

      System.out.println("Receiver closed");

    } catch (javax.jms.JMSException jmsEx) {
      jmsEx.printStackTrace();
      System.out.println("JMS Exception: " + jmsEx.toString());
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
