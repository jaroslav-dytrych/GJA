/*
 * Adapted from http://www.tutorialspoint.com//spring/event_handling_in_spring.htm
 */

package cz.vutbr.fit.gja.springEventHandling;

public class HelloWorld {

  private String message;

  public void setMessage(String message) {
    this.message = message;
  }

  public void getMessage() {
    System.out.println("Your Message : " + message);
  }
}
