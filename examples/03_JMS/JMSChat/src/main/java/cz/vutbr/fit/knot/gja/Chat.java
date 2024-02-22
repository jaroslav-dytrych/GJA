/**
 * Adapted from http://www.onjava.com/pub/a/onjava/excerpt/jms_ch2/?page=2
 */

package cz.vutbr.fit.knot.gja;


import javax.jms.*;
import javax.naming.*;
import java.io.*;
import java.io.InputStreamReader;
import java.util.Properties;

public class Chat implements javax.jms.MessageListener{
  private TopicSession pubSession;
  private TopicSession subSession;
  private TopicPublisher publisher;
  private TopicConnection connection;
  private String username;

  /**
   * Constructor. Establishes JMS publisher and subscriber
   * 
   * @param topicName Topic name
   * @param username Username
   * @param password Password
   * @throws java.lang.Exception
   */
  public Chat(String topicName, String username, String password) throws Exception {
    // Obtain a JNDI connection
    Properties env = new Properties();
    // ... specify the JNDI properties specific to the vendor
    env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.SerialInitContextFactory");
    env.put(Context.PROVIDER_URL, "localhost:3700");

    InitialContext jndi = new InitialContext(env);

    // Look up a JMS connection factory
    TopicConnectionFactory conFactory
      = (TopicConnectionFactory) jndi.lookup("TopicConnectionFactory");

    // Create a JMS connection
    TopicConnection tConnection = conFactory.createTopicConnection(username, password);

    // Create two JMS session objects
    pubSession = tConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
    subSession = tConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

    // Look up a JMS topic
    Topic chatTopic = (Topic) jndi.lookup(topicName);

    // Create a JMS publisher and subscriber
    TopicPublisher tPublisher = pubSession.createPublisher(chatTopic);
    TopicSubscriber subscriber = subSession.createSubscriber(chatTopic);

    // Set a JMS message listener
    subscriber.setMessageListener(this);

    // Intialize the Chat application
    set(tConnection, pubSession, subSession, tPublisher, username);

    // Start the JMS connection; allows messages to be delivered
    tConnection.start();

  }

  /**
   * Initialize the instance variables 
   * 
   * @param con Connection
   * @param pubSess Publisher's session
   * @param subSess Subscriber's session
   * @param pub Publisher
   * @param username Username
   */
  public void set(TopicConnection con, TopicSession pubSess, TopicSession subSess, 
                  TopicPublisher pub, String username) {
    this.connection = con;
    this.pubSession = pubSess;
    this.subSession = subSess;
    this.publisher = pub;
    this.username = username;
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
   * Creates and sends message using topic publisher 
   * 
   * @param text Text to be send
   * @throws javax.jms.JMSException
   */
  protected void writeMessage(String text) throws JMSException {
    TextMessage message = pubSession.createTextMessage();
    message.setText(username + " : " + text);
    publisher.publish(message);
  }

  /**
   * Closes the JMS connection
   *
   * @throws javax.jms.JMSException
   */
  public void close() throws JMSException {
    connection.close();
  }

  /**
   * Main method - Run the Chat client 
   * @param args Command line arguments - topic, username and password
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
      
      System.out.println("Chat ready");
      
      // Loop until the word "exit" is typed
      while (true) {
        String s = commandLine.readLine();
        if (s.equalsIgnoreCase("exit")) {
          chat.close(); // close down connection
          System.exit(0);// exit program
        } else {
          chat.writeMessage(s);
        }
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
