/*
 * Adapted from http://www.tutorialspoint.com/spring/spring_autowiring_byconstructor.htm
 */

package cz.vutbr.fit.gja.springAutowireConstructor;

public class SpellChecker {

  public SpellChecker() {
    System.out.println("Inside SpellChecker constructor.");
  }

  public void checkSpelling() {
    System.out.println("Inside checkSpelling.");
  }
}
