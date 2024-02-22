/*
 * Adapted from http://www.roseindia.net/c-tutorials/example-of-statelfulbean.shtml
 */

package net.roseindia.ejb3.statefull.example;

import jakarta.ejb.Stateful;
import jakarta.ejb.Remote;  
import jakarta.ejb.Remove; 

@Stateful(name = "AccountBean")
@Remote(AccountRemote.class)
public class AccountBean implements AccountRemote {

  float balance = 0;

  @Override
  public float deposit(float amount) {
    balance += amount;
    return balance;
  }

  @Override
  public float withdraw(float amount) {
    balance -= amount;
    return balance;
  }

  @Override
  @Remove
  public void remove() {
    balance = 0;
  }
}