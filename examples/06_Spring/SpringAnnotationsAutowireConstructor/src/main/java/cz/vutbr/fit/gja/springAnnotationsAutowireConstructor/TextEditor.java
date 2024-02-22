/*
 * Adapted from http://www.tutorialspoint.com/spring/spring_autowired_annotation.htm
 */

package cz.vutbr.fit.gja.springAnnotationsAutowireConstructor;

import org.springframework.beans.factory.annotation.Autowired;

public class TextEditor {
  //@Autowire(require=false) exists - can be on property, which can or doesn't have to be in Beans.xml

  private SpellChecker spellChecker;

  @Autowired
  public TextEditor(SpellChecker spellChecker) {
    System.out.println("Inside TextEditor constructor.");
    this.spellChecker = spellChecker;
  }

  public SpellChecker getSpellChecker() {
    return spellChecker;
  }

  public void spellCheck() {
    spellChecker.checkSpelling();
  }
}
