/*
 * Project: Examples for GJA course
 * Authors: Ing. Jan Kouřil
 *          Ing. Jaroslav Dytrych idytrych@fit.vutbr.cz
 * File: Todo.java
 * Description: Example Jersey application with using JAXB - XML binded object
 */

/**
 * @file Todo.java
 *
 * @brief Example Jersey application with using JAXB server - XML binded object
 */

/**
 * @package cz.vutbr.fit.knot.gja.JJB.shared
 * 
 * @brief Example Jersey application with using JAXB server - shared object
 */

package cz.vutbr.fit.knot.gja.JJB.shared;

import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Example XML binded object
 */
@XmlRootElement
public class Todo {
  private String summary;
  private String description;
  public String getSummary() {
    return summary;
  }
  public void setSummary(String summary) {
    this.summary = summary;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
} 
