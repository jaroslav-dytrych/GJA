/*
 * Adapted from http://www.roseindia.net/c-tutorials/example-of-statelfulbean.shtml
 */

package net.roseindia.ejb3.statefull.example;

import jakarta.ejb.Remote;
import jakarta.ejb.Remove;
@Remote
public interface AccountRemote {

  public float deposit(float amount);
  public float withdraw(float amount);
  @Remove 
  public void remove();
}
