/*
 * Adapted from http://www.tutorialspoint.com/spring/aspectj_based_aop_appoach.htm
 */

package cz.vutbr.fit.gja.springAspect;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

  public static void main(String[] args) {
    ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
    Student student = (Student) context.getBean("student");
    student.getName();
    student.getAge();
    student.printThrowException();
  }
}
