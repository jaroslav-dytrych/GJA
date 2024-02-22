/*
 * Adapted from http://www.tutorialspoint.com/spring/spring_jsr250_annotations.htm
 */

package cz.vutbr.fit.gja.springJSR;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

  public static void main(String[] args) {
    AbstractApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
    HelloWorld obj = (HelloWorld) context.getBean("helloWorld");
    obj.getMessage();
    context.registerShutdownHook();
  }
}
