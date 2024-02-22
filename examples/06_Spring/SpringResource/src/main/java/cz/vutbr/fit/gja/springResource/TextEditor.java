/*
 * Adapted from http://www.tutorialspoint.com/spring/spring_jsr250_annotations.htm
 */

package cz.vutbr.fit.gja.springResource;

import javax.annotation.Resource;

public class TextEditor {

  private SpellChecker spellChecker;

  @Resource(name = "spellChecker")
  public void setSpellChecker(SpellChecker spellChecker) {
    this.spellChecker = spellChecker;
  }

  public SpellChecker getSpellChecker() {
    return spellChecker;
  }

  public void spellCheck() {
    spellChecker.checkSpelling();
  }
}
