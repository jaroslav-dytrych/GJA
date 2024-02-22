/*
 * http://tomee.apache.org/examples-trunk/simple-mdb/
 */

package example;
import javax.annotation.Resource;
import javax.ejb.MessageDriven;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

@MessageDriven
public class ChatBean implements MessageListener {

  @Resource
  private ConnectionFactory connectionFactory;
  
  @Resource(name = "AnswerQueue")
  private Queue answerQueue;

  @Override
  public void onMessage(Message message) {
    try {

      final TextMessage textMessage = (TextMessage) message;
      final String question = textMessage.getText();

      if ("Hello World!".equals(question)) {
        System.out.println("Responding to Hello World!");
        respond("Hello, Test Case!");

      } else if ("How are you?".equals(question)) {
        System.out.println("Responding to How are you?");
        respond("I'm doing well.");

      } else if ("Still spinning?".equals(question)) {
        System.out.println("Responding to Still spinning?");
        respond("Once every day, as usual.");

      }
    } catch (JMSException e) {
      throw new IllegalStateException(e);
    }
  }

  private void respond(String text) throws JMSException {

    Connection connection = null;
    Session session = null;

    try {
      connection = connectionFactory.createConnection();
      connection.start();

      // Create a Session
      session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

      // Create a MessageProducer from the Session to the Topic or Queue
      MessageProducer producer = session.createProducer(answerQueue);
      producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

      // Create a message
      TextMessage message = session.createTextMessage(text);

      // Tell the producer to send the message
      producer.send(message);
    } finally {
      // Clean up
      if (session != null) {
        session.close();
      }
      if (connection != null) {
        connection.close();
      }
    }
  }
}
