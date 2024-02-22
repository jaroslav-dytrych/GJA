/*
 * Project: Examples for GJA course
 * Authors: Ing. Jan Kouřil
 *          Ing. Jaroslav Dytrych idytrych@fit.vutbr.cz
 * File: TodoDao.java
 * Description: Example Jersey application for CRUD - shared data object
 */

/**
 * @file TodoDao.java
 *
 * @brief Example Jersey application for CRUD - shared data object
 */

package cz.vutbr.fit.knot.gja.CRUD.shared;

import java.util.HashMap;
import java.util.Map;

/**
 * Shared data object
 */
public enum TodoDao {
  instance;
  
  /** Data storage */
  private Map<String, Todo> contentProvider = new HashMap<>();
  
  /**
   * Constructor
   */
  private TodoDao() {
    Todo todo = new Todo("1", "Learn REST");
    todo.setDescription("Read http://en.wikipedia.org/wiki/Representational_State_Transfer");
    contentProvider.put("1", todo);
    todo = new Todo("2", "Learn Jersey");
    todo.setDescription("Read complete https://jersey.java.net/documentation/latest/index.html");
    contentProvider.put("2", todo);    
  }
  /**
   * Gets data model
   * 
   * @return Returns data model
   */
  public Map<String, Todo> getModel(){
    return contentProvider;
  }
  
}
