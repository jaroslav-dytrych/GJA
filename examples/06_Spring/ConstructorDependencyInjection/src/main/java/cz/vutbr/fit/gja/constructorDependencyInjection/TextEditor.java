/*
 * Adapted from http://www.tutorialspoint.com/spring/constructor_based_dependency_injection.htm
 */

package cz.vutbr.fit.gja.constructorDependencyInjection;

public class TextEditor {

  private SpellChecker spellChecker;

  public TextEditor(SpellChecker spellChecker) {
    System.out.println("Inside TextEditor constructor.");
    this.spellChecker = spellChecker;
  }

  public void spellCheck() {
    spellChecker.checkSpelling();
  }
}
