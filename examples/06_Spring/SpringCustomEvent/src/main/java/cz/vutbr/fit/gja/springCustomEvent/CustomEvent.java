/*
 * Adapted from http://www.tutorialspoint.com//spring/custom_events_in_spring.htm
 */

package cz.vutbr.fit.gja.springCustomEvent;

import org.springframework.context.ApplicationEvent;

public class CustomEvent extends ApplicationEvent {

  public CustomEvent(Object source) {
    super(source);
  }

  @Override
  public String toString() {
    return "My Custom Event";
  }
}
