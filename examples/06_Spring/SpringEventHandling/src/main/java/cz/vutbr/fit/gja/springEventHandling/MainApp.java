/*
 * Adapted from http://www.tutorialspoint.com//spring/event_handling_in_spring.htm
 */

package cz.vutbr.fit.gja.springEventHandling;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

  public static void main(String[] args) {
    ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
    
    // Let us raise a start event.
    context.start();
    HelloWorld obj = (HelloWorld) context.getBean("helloWorld");
    obj.getMessage();
    
    // Let us raise a stop event.
    context.stop();
  }
}
