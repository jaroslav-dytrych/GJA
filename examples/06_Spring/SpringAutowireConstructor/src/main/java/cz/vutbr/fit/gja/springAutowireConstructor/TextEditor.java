/*
 * Adapted from http://www.tutorialspoint.com/spring/spring_autowiring_byconstructor.htm
 */

package cz.vutbr.fit.gja.springAutowireConstructor;

public class TextEditor {

  private SpellChecker spellChecker;
  private String name;

  public TextEditor(SpellChecker spellChecker, String name) {
    this.spellChecker = spellChecker;
  }

  public SpellChecker getSpellChecker() {
    return spellChecker;
  }

  public String getName() {
    return name;
  }

  public void spellCheck() {
    spellChecker.checkSpelling();
  }
}
