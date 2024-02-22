/*
 * Adapted from http://www.tutorialspoint.com/spring/spring_autowired_annotation.htm
 */

package cz.vutbr.fit.gja.springAnnotationsAutowire;

import org.springframework.beans.factory.annotation.Autowired;

public class TextEditor {
  //@Autowired can be also here - setSpellChecker will be called

  private SpellChecker spellChecker;

  @Autowired
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
