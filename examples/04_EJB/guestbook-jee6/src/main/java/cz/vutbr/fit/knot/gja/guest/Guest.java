/*
 * Project: Examples for GJA course
 * Adapted from http://www.objectdb.com/tutorial/jpa/netbeans/web/entity
 * Editors: Ing. Jan Kouřil
 *          Ing. Jaroslav Dytrych idytrych@fit.vutbr.cz
 * File: Guest.java
 * Description: JPA Guestbook Web Application Tutorial 
 *              - Guest entity representation
 */

/**
 * @file Guest.java
 *
 * @brief Guest entity representation
 */

/**
 * @package cz.vutbr.fit.knot.gja.guest
 *
 * @brief JPA Guestbook Web Application Tutorial
 */
package cz.vutbr.fit.knot.gja.guest;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Guest entity representation
 */
@Entity
public class Guest implements Serializable {

  private static final long serialVersionUID = 1L;

  // Persistent Fields:
  /**
   * Guest id
   */
  @Id
  @GeneratedValue
  Long id;
  /**
   * Guest name
   */
  private String name;
  /**
   * Signing date
   */
  private Date signingDate;

  /**
   * Constructor
   */
  public Guest() {
  }

  /**
   * Constructor
   *
   * @param name Guest name
   */
  public Guest(String name) {
    this.name = name;
    this.signingDate = new Date(System.currentTimeMillis());
  }

   /**
   * Gets String Representation:
   *
   * @return Returns String Representation
   */
  @Override
  public String toString() {
    return name + " (signed on " + signingDate + ")";
  }
}
