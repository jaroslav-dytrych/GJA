/*
 * Adapted from http://www.tutorialspoint.com/spring/spring_qualifier_annotation.htm
 */

package cz.vutbr.fit.gja.springQualifierAnnotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

  public static void main(String[] args) {
    ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
    Profile profile = (Profile) context.getBean("profile");
    profile.printAge();
    profile.printName();
  }
}
