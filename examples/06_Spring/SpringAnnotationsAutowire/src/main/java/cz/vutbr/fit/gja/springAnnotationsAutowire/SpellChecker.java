/*
 * Adapted from http://www.tutorialspoint.com/spring/spring_autowired_annotation.htm
 */

package cz.vutbr.fit.gja.springAnnotationsAutowire;

public class SpellChecker {

  public SpellChecker() {
    System.out.println("Inside SpellChecker constructor.");
  }

  public void checkSpelling() {
    System.out.println("Inside checkSpelling.");
  }
}
