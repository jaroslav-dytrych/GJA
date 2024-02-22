/*
 * Adapted from http://www.tutorialspoint.com/hibernate/hibernate_interceptors.htm 
 */

package cz.vutbr.fit.gja;

import java.util.List;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
    Integer empID1 = ME.addEmployee("Zara", "Ali", 1000);
    Integer empID2 = ME.addEmployee("Daisy", "Das", 5000);
    Integer empID3 = ME.addEmployee("John", "Paul", 10000);

    /* List down all the employees */
    ME.listEmployees();

    /* Update employee's records */
    ME.updateEmployee(empID1, 5000);

    /* Delete an employee from the database */
    ME.deleteEmployee(empID2);

    /* List down new list of the employees */
    ME.listEmployees();
  }

  /**
   * Method to CREATE an employee in the database 
   * 
   * @param fname First name
   * @param lname Last name
   * @param salary Salary
   * @return Returns ID of created employee
   */
  public Integer addEmployee(String fname, String lname, int salary) {
    Session session = factory.withOptions().interceptor(new MyInterceptor()).openSession();
    Transaction tx = null;
    Employee employee = new Employee(fname, lname, salary);
    try {
      tx = session.beginTransaction();
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
    return employee.getId();
  }

  /** 
   * Method to  READ all the employees 
   */
  public void listEmployees() {
    Session session = factory.withOptions().interceptor(new MyInterceptor()).openSession();
    Transaction tx = null;
    try {
      tx = session.beginTransaction();
      List<Employee> employees = session.createQuery("FROM Employee",Employee.class).list();
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

  /**
   * Method to UPDATE salary for an employee 
   * 
   * @param EmployeeID ID of an employee
   * @param salary New salary
   */
  public void updateEmployee(Integer EmployeeID, int salary) {
    Session session = factory.withOptions().interceptor(new MyInterceptor()).openSession();
    Transaction tx = null;
    try {
      tx = session.beginTransaction();
      Employee employee = (Employee) session.get(Employee.class, EmployeeID);
      employee.setSalary(salary);
      session.merge(employee);
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
   * Method to DELETE an employee from the records 
   * 
   * @param EmployeeID ID of an employee
   */
  public void deleteEmployee(Integer EmployeeID) {
    Session session = factory.withOptions().interceptor(new MyInterceptor()).openSession();
    Transaction tx = null;
    try {
      tx = session.beginTransaction();
      Employee employee = (Employee) session.get(Employee.class, EmployeeID);
      session.remove(employee);
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
