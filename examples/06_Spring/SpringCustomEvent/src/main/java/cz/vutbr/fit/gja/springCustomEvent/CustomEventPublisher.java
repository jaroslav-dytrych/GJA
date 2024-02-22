/*
 * Adapted from http://www.tutorialspoint.com//spring/custom_events_in_spring.htm
 */

package cz.vutbr.fit.gja.springCustomEvent;

import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationEventPublisher;

public class CustomEventPublisher implements ApplicationEventPublisherAware {

  private ApplicationEventPublisher publisher;

  @Override
  public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
    this.publisher = publisher;
  }

  public void publish() {
    CustomEvent ce = new CustomEvent(this);
    publisher.publishEvent(ce);
  }
}
