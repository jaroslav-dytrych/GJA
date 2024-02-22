/*
 * Adapted from http://www.tutorialspoint.com/hibernate/hibernate_batch_processing.htm
 */

package cz.vutbr.fit.gja;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
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

    /* Print Total employee's count */
    ME.countEmployee();
    
    /* Add employee records in batches */
    ME.addEmployees();
    
    /* Print Total employee's count */
    ME.countEmployee();
  }

  /**
   * Method to create employee records in batches 
   */
  public void addEmployees() {
    Session session = factory.openSession();
    Transaction tx = null;
    Integer employeeID = null;
    try {
      tx = session.beginTransaction();
      for (int i = 0; i < 100000; i++) {
        String fname = "First Name " + i;
        String lname = "Last Name " + i;
        Integer salary = i;
        Employee employee = new Employee(fname, lname, salary);
        session.persist(employee);
        if (i % 50 == 0) {
          session.flush();
          session.clear();
        }
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
   * Method to print total number of records 
   */
  public void countEmployee() {
    Session session = factory.openSession();
    Transaction tx = null;
    try {
      tx = session.beginTransaction();
      
      CriteriaBuilder cb = session.getCriteriaBuilder();
      // To get total row count.
      CriteriaQuery<Long> cq = cb.createQuery(Long.class);
      Root<Employee> root = cq.from(Employee.class);
      cq.select(cb.count(root));
      Long count = session.createQuery(cq).getSingleResult();

      System.out.println("Total Count: " + count);
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
