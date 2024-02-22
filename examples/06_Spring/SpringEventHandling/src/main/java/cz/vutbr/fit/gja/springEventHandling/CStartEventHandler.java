/*
 * Adapted from http://www.tutorialspoint.com//spring/event_handling_in_spring.htm
 */

package cz.vutbr.fit.gja.springEventHandling;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;

public class CStartEventHandler implements ApplicationListener<ContextStartedEvent> {

  @Override
  public void onApplicationEvent(ContextStartedEvent event) {
    System.out.println("ContextStartedEvent Received");
  }
}
