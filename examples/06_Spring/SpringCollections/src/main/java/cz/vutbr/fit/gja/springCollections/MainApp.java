/*
 * Adapted from http://www.tutorialspoint.com/spring/spring_injecting_collection.htm
 */

package cz.vutbr.fit.gja.springCollections;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

  public static void main(String[] args) {
    ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
    JavaCollection jc = (JavaCollection) context.getBean("javaCollection");
    jc.getAddressList();
    jc.getAddressSet();
    jc.getAddressMap();
    jc.getAddressProp();
  }
}
