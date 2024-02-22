/*
 * Project: Examples for GJA course
 * Authors: Ing. Jan Kouřil
 *          Ing. Jaroslav Dytrych idytrych@fit.vutbr.cz
 * File: App.java
 * Description: Example application for JUnit test
 */

/**
 * @file App.java
 *
 * @brief Example application for JUnit test
 */

/**
 * @package cz.vutbr.fit.knot.gja.numberGenerator
 * 
 * @brief Example of JUnit test
 */
package cz.vutbr.fit.knot.gja.numberGenerator;

import java.util.UUID;

/**
 * Generate a unique number
 */
public class App {

  /**
   * Main method
   *
   * @param args Command line arguments
   */
  public static void main(String[] args) {
    App obj = new App();
    System.out.println("Unique ID : " + obj.generateUniqueKey());
  }

  /**
   * Generates unique number
   *
   * @return Returns unique number
   */
  public String generateUniqueKey() {

    String id = UUID.randomUUID().toString();
    return id;

  }
}
