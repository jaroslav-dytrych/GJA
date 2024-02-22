/*
 * Adapted from http://www.onjava.com/pub/a/onjava/excerpt/jms_ch2/?page=2
 */

package cz.vutbr.fit.gja.jms.chat;
 
import javax.jms.*;
import javax.naming.*;
import java.io.*;
import java.io.InputStreamReader;
import com.sun.messaging.ConnectionConfiguration;
import com.sun.messaging.ConnectionFactory;

public class Chat implements javax.jms.MessageListener {

  private final Connection connection;
  private final Session session;
  private final Topic chatTopic;
  private final MessageProducer producer;
  private final MessageConsumer consumer;
  private final String username;

  /**
   * Constructor. Establishes JMS publisher and subscriber 
   * @param topicName Topic name
   * @param username Username
   * @param password Password
   * @throws javax.jms.JMSException
   * @throws javax.naming.NamingException
   */
  public Chat(String topicName, String username, String password) throws JMSException, NamingException {
    this.username = username;

    ConnectionFactory connFactory = new ConnectionFactory();
    connFactory.setProperty(ConnectionConfiguration.imqAddressList, "localhost:7676");

    // Create a JMS connection
    connection = connFactory.createConnection();
    session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    chatTopic = session.createTopic(topicName);

    // Create a JMS publisher and subscriber
    producer = session.createProducer(chatTopic);
    consumer = session.createConsumer(chatTopic);

    // Set a JMS message listener
    consumer.setMessageListener(this);

    // Start the JMS connection; allows messages to be delivered
    connection.start();
  }

  /** 
   * Receive message from topic subscriber 
   * 
   * @param message Message
   */
  @Override
  public void onMessage(Message message) {
    try {
      TextMessage textMessage = (TextMessage) message;
      String text = textMessage.getText();
      System.out.println(text);
    } catch (JMSException jmse) {
      jmse.printStackTrace();
    }
  }

  /**
   * Create and send message using topic publisher 
   * 
   * @param text Text of the message
   */
  protected void writeMessage(String text) throws JMSException {
    TextMessage message = session.createTextMessage();
    message.setText(username + " : " + text);
    producer.send(message);
  }

  /** 
   * Close the JMS connection 
   */
  public void close() throws JMSException {
    connection.close();
  }

  /** 
   * Main method - Run the Chat client 
   * 
   * @param args Command line arguments (topicName username password)
   */
  public static void main(String[] args) {
    try {
      if (args.length != 3) {
        System.out.println("Topic or username missing");
      }

      // args[0]=topicName; args[1]=username; args[2]=password
      Chat chat = new Chat(args[0], args[1], args[2]);

      // Read from command line
      BufferedReader commandLine = new java.io.BufferedReader(new InputStreamReader(System.in));

      // Loop until the word "exit" is typed
      while (true) {
        String s = commandLine.readLine();
        if (s.equalsIgnoreCase("exit")) {
          chat.close();  // close down connection
          System.exit(0);  // exit program
        } else {
          chat.writeMessage(s);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
