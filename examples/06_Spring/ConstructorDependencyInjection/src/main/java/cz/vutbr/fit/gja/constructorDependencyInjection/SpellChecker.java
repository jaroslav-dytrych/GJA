/*
 * Adapted from http://www.tutorialspoint.com/spring/constructor_based_dependency_injection.htm
 */

package cz.vutbr.fit.gja.constructorDependencyInjection;

public class SpellChecker {

  public SpellChecker() {
    System.out.println("Inside SpellChecker constructor.");
  }

  public void checkSpelling() {
    System.out.println("Inside checkSpelling.");
  }
}
