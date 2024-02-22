/*
 * Adapted from http://www.tutorialspoint.com//spring/custom_events_in_spring.htm
 */

package cz.vutbr.fit.gja.springCustomEvent;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

  public static void main(String[] args) {
    ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
    CustomEventPublisher cvp = (CustomEventPublisher) context.getBean("customEventPublisher");

    cvp.publish();
    cvp.publish();
  }
}
