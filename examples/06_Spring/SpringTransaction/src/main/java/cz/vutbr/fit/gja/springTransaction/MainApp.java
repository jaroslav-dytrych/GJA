/*
 * Adapted from http://www.tutorialspoint.com/spring/declarative_management.htm
 */

package cz.vutbr.fit.gja.springTransaction;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

  public static void main(String[] args) {
    ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
    StudentJDBCTemplate studentJDBCTemplate
      = (StudentJDBCTemplate) context.getBean("studentJDBCTemplate");
    
    System.out.println("------Records creation--------");
    studentJDBCTemplate.create("John", 21, 36, 2010);
    studentJDBCTemplate.create("Peter", 22, 37, 2010);
    studentJDBCTemplate.create("Paul", 24, 41, 2011);
    
    System.out.println("------Listing all the records--------");
    List<StudentMarks> studentMarks = studentJDBCTemplate.listStudents();
    for (StudentMarks record : studentMarks) {
      System.out.print("ID : " + record.getId());
      System.out.print(", Name : " + record.getName());
      System.out.print(", Marks : " + record.getMarks());
      System.out.print(", Year : " + record.getYear());
      System.out.println(", Age : " + record.getAge());
    }
  }
}
