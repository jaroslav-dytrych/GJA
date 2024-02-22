/*
 * Adapted from http://www.tutorialspoint.com/spring/spring_autowiring_byname.htm
 */

package cz.vutbr.fit.gja.springAutowireByName;

public class TextEditor {

  private SpellChecker spellChecker;
  private String name;

  public void setSpellChecker(SpellChecker spellChecker) {
    this.spellChecker = spellChecker;
  }

  public SpellChecker getSpellChecker() {
    return spellChecker;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void spellCheck() {
    spellChecker.checkSpelling();
  }
}
