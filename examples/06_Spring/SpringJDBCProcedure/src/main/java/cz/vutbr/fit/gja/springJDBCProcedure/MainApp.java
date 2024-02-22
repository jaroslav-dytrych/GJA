/*
 * Adapted from http://www.tutorialspoint.com/spring/calling_stored_procedure.htm
 */

package cz.vutbr.fit.gja.springJDBCProcedure;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

  public static void main(String[] args) {
    ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
    StudentJDBCTemplate studentJDBCTemplate
      = (StudentJDBCTemplate) context.getBean("studentJDBCTemplate");
    
    System.out.println("------Records Creation--------");
    studentJDBCTemplate.create("Martin", 11);
    studentJDBCTemplate.create("Stephen", 2);
    studentJDBCTemplate.create("Paula", 15);
    
    System.out.println("------Listing Multiple Records--------");
    List<Student> students = studentJDBCTemplate.listStudents();
    for (Student record : students) {
      System.out.print("ID : " + record.getId());
      System.out.print(", Name : " + record.getName());
      System.out.println(", Age : " + record.getAge());
    }
    
    System.out.println("----Listing Record with ID = 2 -----");
    Student student = studentJDBCTemplate.getStudent(2);
    System.out.print("ID : " + student.getId());
    System.out.print(", Name : " + student.getName());
    System.out.println(", Age : " + student.getAge());
  }
}
