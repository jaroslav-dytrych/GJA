/*
 * Project: Examples for GJA course
 * Authors: Ing. Jan Kouřil
 *          Ing. Jaroslav Dytrych idytrych@fit.vutbr.cz
 * File: AppTest.java
 * Description: Example of JUnit test
 */

/**
 * @file AppTest.java
 *
 * @brief Example of JUnit test
 */

package cz.vutbr.fit.knot.gja.numberGenerator;

import org.junit.Assert;
import org.junit.Test;

/**
 * Example of JUnit test
 * 
 * @brief Example of JUnit test
 */
public class AppTest {

  /**
   * Key length
   */
  private static final int KEY_LENGTH = 36;

  /**
   * Test of key length
   */
  @Test
  public void testLengthOfTheUniqueKey() {
    App obj = new App();
    Assert.assertEquals(KEY_LENGTH, obj.generateUniqueKey().length());
  }
}
