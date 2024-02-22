/*
 * Adapted from http://www.tutorialspoint.com/spring/spring_autowired_annotation.htm
 */

package cz.vutbr.fit.gja.springAnnotationsAutowire;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

  public static void main(String[] args) {
    ApplicationContext context
            = new ClassPathXmlApplicationContext("Beans.xml");
    TextEditor te = (TextEditor) context.getBean("textEditor");
    te.spellCheck();
  }
}
