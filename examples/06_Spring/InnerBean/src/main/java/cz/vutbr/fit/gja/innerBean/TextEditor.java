/*
 * Adapted from http://www.tutorialspoint.com//spring/spring_injecting_inner_beans.htm
 */

package cz.vutbr.fit.gja.innerBean;

public class TextEditor {

  private SpellChecker spellChecker;
  
  /**
   * A setter method to inject the dependency.
   * 
   * @param spellChecker New spellChecker
   */
  public void setSpellChecker(SpellChecker spellChecker) {
    System.out.println("Inside setSpellChecker.");
    this.spellChecker = spellChecker;
  }

  /**
   * A getter method to return spellChecker
   * 
   * @return Returns spellChecker
   */
  public SpellChecker getSpellChecker() {
    return spellChecker;
  }

  public void spellCheck() {
    spellChecker.checkSpelling();
  }
}
