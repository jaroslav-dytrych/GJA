/*
 * Project: Examples for GJA course
 * Authors: Ing. Jan Kouřil
 *          Ing. Jaroslav Dytrych idytrych@fit.vutbr.cz
 * File: Todo.java
 * Description: Example Jersey application for CRUD - shared object
 */

/**
 * @file Todo.java
 *
 * @brief Example Jersey application for CRUD - shared object
 */

/**
 * @package cz.vutbr.fit.knot.gja.CRUD.shared
 * 
 * @brief Example Jersey application for CRUD - shared objects
 */
package cz.vutbr.fit.knot.gja.CRUD.shared;

import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Example Jersey application for CRUD - shared object for TODO description
 */
@XmlRootElement
public class Todo {
  /** ID */
  private String id;
  /** Summary */
  private String summary;
  /** Description */
  private String description;
  
  /** Constructor */
  public Todo(){
  }
  
  /**
   * Constructor
   * 
   * @param id ID
   * @param summary Summary 
   */
  public Todo (String id, String summary){
    this.id = id;
    this.summary = summary;
  }
  
  /**
   * Gets ID
   * 
   * @return Returns ID
   */
  public String getId() {
    return id;
  }
  
  /**
   * Sets ID
   * 
   * @param id New ID
   */
  public void setId(String id) {
    this.id = id;
  }
  
  /**
   * Gets summary
   * 
   * @return Returns summary
   */
  public String getSummary() {
    return summary;
  }
  
  /**
   * Sets summary
   * 
   * @param summary New summary
   */
  public void setSummary(String summary) {
    this.summary = summary;
  }
  
  /**
   * Gets description
   * 
   * @return Returns description
   */
  public String getDescription() {
    return description;
  }
  
  /**
   * Sets description
   * 
   * @param description New description
   */
  public void setDescription(String description) {
    this.description = description;
  }
} // public class Todo