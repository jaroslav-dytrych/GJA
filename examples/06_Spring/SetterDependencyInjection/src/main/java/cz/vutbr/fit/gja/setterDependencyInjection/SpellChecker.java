/*
 * Adapted from http://www.tutorialspoint.com/spring/setter_based_dependency_injection.htm
 */

package cz.vutbr.fit.gja.setterDependencyInjection;

public class SpellChecker {

  public SpellChecker() {
    System.out.println("Inside SpellChecker constructor.");
  }

  public void checkSpelling() {
    System.out.println("Inside checkSpelling.");
  }
}
