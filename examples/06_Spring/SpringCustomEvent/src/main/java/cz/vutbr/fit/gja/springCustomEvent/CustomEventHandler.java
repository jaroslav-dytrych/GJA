/*
 * Adapted from http://www.tutorialspoint.com//spring/custom_events_in_spring.htm
 */

package cz.vutbr.fit.gja.springCustomEvent;

import org.springframework.context.ApplicationListener;

public class CustomEventHandler implements ApplicationListener<CustomEvent> {

  @Override
  public void onApplicationEvent(CustomEvent event) {
    System.out.println(event.toString());
  }
}
