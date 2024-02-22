/*
 * Adapted from http://www.tutorialspoint.com/spring/spring_bean_definition_inheritance.htm
 */

package cz.vutbr.fit.gja.beanInheritance;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

  public static void main(String[] args) {
    ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
    
    HelloWorld objA = (HelloWorld) context.getBean("helloWorld");
    objA.getMessage1();
    objA.getMessage2();
    
    HelloCR objB = (HelloCR) context.getBean("helloCR");
    objB.getMessage1();
    objB.getMessage2();
    objB.getMessage3();
  }
}
