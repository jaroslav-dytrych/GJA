/*
 * Project: Examples for GJA course
 * Author: Ing. Jaroslav Dytrych idytrych@fit.vutbr.cz
 * Refactored: Bc. Vít Barták xbarta47@stud.fit.vutbr.cz
 * File: NumberGuessBeaan.java
 * Description: Example backbean for number guess
 */

/**
 * @file NumberGuessBeaan.java
 *
 * Example backbean for number guess
 */

/**
 * @package cz.vutbr.fit.knot.gja.number
 * 
 * Example backbean for number guess
 */
package cz.vutbr.fit.knot.gja.number;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Example backbean for number guess
 */
public class NumberGuessBean {

  /** Users answer */
  private int answer;
  /** Success indicator */
  private boolean success;
  /** Hint */
  private String hint;
  /** Number of guesses */
  private int numGuesses;

  /**
   * Constructor
   */
  public NumberGuessBean() {
    reset();
  }

  /**
   * Set current guess and determines new hint
   * 
   * @param guess Guess
   */
  public void setGuess(String guess) {
    numGuesses++;

    // convert to number
    int g;
    try {
      g = Integer.parseInt(guess);
    } catch (NumberFormatException e) {
      g = -1;
    }

    // check guess
    if (g == answer) {
      success = true;
    } else if (g == -1) {
      hint = "a number next time";
    } else if (g < answer) {
      hint = "higher";
    } else if (g > answer) {
      hint = "lower";
    }
  }

  /**
   * Get success indicator
   * 
   * @return Value of success indicator
   */
  public boolean getSuccess() {
    return success;
  }

  /**
   * Get hint
   * 
   * @return Hint 
   */
  public String getHint() {
    return "" + hint;
  }

  /**
   * Get number of guesses
   * 
   * @return Number of guesses
   */
  public int getNumGuesses() {
    return numGuesses;
  }

  /**
   * Reset and generate new number
   */
  public void reset() {
    try {
      answer = Math.abs(SecureRandom.getInstance("SHA1PRNG").nextInt() % 100) + 1;
    } catch (NoSuchAlgorithmException ex) {
      answer = 0;
    }
    success = false;
    numGuesses = 0;
  }
}  // public class NumberGuessBean
