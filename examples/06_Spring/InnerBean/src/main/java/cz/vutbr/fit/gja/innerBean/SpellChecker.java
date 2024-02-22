/*
 * Adapted from http://www.tutorialspoint.com//spring/spring_injecting_inner_beans.htm
 */

package cz.vutbr.fit.gja.innerBean;

public class SpellChecker {

  public SpellChecker() {
    System.out.println("Inside SpellChecker constructor.");
  }

  public void checkSpelling() {
    System.out.println("Inside checkSpelling.");
  }
}
