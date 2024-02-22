/*
 * Adapted from http://www.tutorialspoint.com/hibernate/hibernate_native_sql.htm
 */

package cz.vutbr.fit.gja;

import java.util.*;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;

public class ManageEmployee {

  private static SessionFactory factory;

  public static void main(String[] args) {
    try {
      factory = new Configuration().configure().buildSessionFactory();
    } catch (Throwable ex) {
      System.err.println("Failed to create sessionFactory object." + ex);
      throw new ExceptionInInitializerError(ex);
    }
    ManageEmployee ME = new ManageEmployee();

    /* Add few employee records in database */
    ME.addEmployee("Zara", "Ali", 2000);
    ME.addEmployee("Daisy", "Das", 5000);
    ME.addEmployee("John", "Paul", 5000);
    ME.addEmployee("Mohd", "Yasee", 3000);

    /* List down employees and their salary using Scalar Query */
    ME.listEmployeesScalar();

    /* List down complete employees information using Entity Query */
    ME.listEmployeesEntity();
  }

  /** 
   * Method to CREATE an employee in the database 
   * 
   * @param fname First name
   * @param lname Last name
   * @param salary Salary
   */
  public void addEmployee(String fname, String lname, int salary) {
    Session session = factory.openSession();
    Transaction tx = null;
    Integer employeeID = null;
    try {
      tx = session.beginTransaction();
      Employee employee = new Employee(fname, lname, salary);
      session.persist(employee);
      tx.commit();
    } catch (HibernateException e) {
      if (tx != null) {
        tx.rollback();
      }
      e.printStackTrace();
    } finally {
      session.close();
    }
  }

  /** 
   * Method to  READ all the employees using Scalar Query 
   */
  public void listEmployeesScalar() {
    Session session = factory.openSession();
    Transaction tx = null;
    try {
      tx = session.beginTransaction();
      String sql = "SELECT id, first_name, last_name, salary FROM EMPLOYEE";
      NativeQuery<Employee> query = session.createNativeQuery(sql,Employee.class);
      List<Employee> data = query.list();

      for (Employee em : data) {
        System.out.print("First Name: " + em.getFirstName());
        System.out.println(", Salary: " + em.getSalary());
      }
      tx.commit();
    } catch (HibernateException e) {
      if (tx != null) {
        tx.rollback();
      }
      e.printStackTrace();
    } finally {
      session.close();
    }
  }

  /** 
   * Method to  READ all the employees using Entity Query 
   */
  public void listEmployeesEntity() {
    Session session = factory.openSession();
    Transaction tx = null;
    try {
      tx = session.beginTransaction();
      String sql = "SELECT * FROM EMPLOYEE";
      NativeQuery<Employee> query = session.createNativeQuery(sql,Employee.class);
      List<Employee> employees = query.list();

      for (Employee employee : employees) {
        System.out.print("First Name: " + employee.getFirstName());
        System.out.print("  Last Name: " + employee.getLastName());
        System.out.println("  Salary: " + employee.getSalary());
      }
      tx.commit();
    } catch (HibernateException e) {
      if (tx != null) {
        tx.rollback();
      }
      e.printStackTrace();
    } finally {
      session.close();
    }
  }
}
