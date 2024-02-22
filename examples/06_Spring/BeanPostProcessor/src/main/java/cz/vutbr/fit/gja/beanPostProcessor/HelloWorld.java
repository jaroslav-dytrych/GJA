/*
 * Adapted from http://www.tutorialspoint.com//spring/spring_bean_post_processors.htm
 */

package cz.vutbr.fit.gja.beanPostProcessor;

public class HelloWorld {

  private String message;

  public void setMessage(String message) {
    this.message = message;
  }

  public void getMessage() {
    System.out.println("Your Message : " + message);
  }

  public void init() {
    System.out.println("Bean is going through init.");
  }

  public void destroy() {
    System.out.println("Bean will destroy now.");
  }
}
