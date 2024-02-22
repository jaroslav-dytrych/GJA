/*
 * Adapted from http://www.tutorialspoint.com/hibernate/hibernate_interceptors.htm 
 */

package cz.vutbr.fit.gja;

import java.io.Serializable;
import java.util.Iterator;

import org.hibernate.Interceptor;
import org.hibernate.type.Type;

public class MyInterceptor implements Interceptor {

  private int updates;
  private int creates;
  private int loads;

  /**
   * This method is called before Employee object gets deleted.
   */
  @Override
  public void onDelete(Object entity,
                       Serializable id,
                       Object[] state,
                       String[] propertyNames,
                       Type[] types) {
    // do nothing
  }

  /**
   * This method is called when Employee object gets updated 
   * (when an object is detected to be dirty, during a flush).
   * 
   * @return true if the user modified the state in any way.
   */
  @Override
  public boolean onFlushDirty(Object entity,
                              Serializable id,
                              Object[] currentState,
                              Object[] previousState,
                              String[] propertyNames,
                              Type[] types) {
    if (entity instanceof Employee) {
      System.out.println("Update Operation");
      return true;
    }
    return false;
  }
  
  /**
   * This method is called when Employee object gets loaded 
   * (just before an object is initialized). Entity will be an empty 
   * uninitialized instance of the class.
   * 
   * @return true if the user modified the state in any way.
   */
  @Override
  public boolean onLoad(Object entity,
                        Serializable id,
                        Object[] state,
                        String[] propertyNames,
                        Type[] types) {
    // do nothing
    return true;
  }
  
  /**
   * This method is called before Employee object gets saved.
   * 
   * @return true if the user modified the state in any way.
   */
  @Override
  public boolean onSave(Object entity,
                        Serializable id,
                        Object[] state,
                        String[] propertyNames,
                        Type[] types) {
    if (entity instanceof Employee) {
      System.out.println("Create Operation");
      return true;
    }
    return false;
  }
  
  /**
   * This method is called before a flush into database
   */
  @Override
  public void preFlush(Iterator entities) {
    System.out.println("preFlush");
  }
  
  /**
   * This method is called after a flush that actually ends in execution 
   * of the SQL statements required to synchronize in-memory state with 
   * the database. 
   */
  @Override
  public void postFlush(Iterator entities) {
    System.out.println("postFlush");
  }
}
