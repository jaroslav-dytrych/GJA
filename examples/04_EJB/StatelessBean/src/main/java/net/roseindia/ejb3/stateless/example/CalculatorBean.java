/*
 * Adapted from http://www.roseindia.net/ejb/examples-of-StatelessBean.shtml
 */

package net.roseindia.ejb3.stateless.example;

import jakarta.ejb.Remote;
import jakarta.ejb.Stateless;

@Stateless(name = "CalculatorBean")
@Remote(CalculatorRemote.class)
public class CalculatorBean implements CalculatorRemote {

  @Override
  public float add(float x, float y) {
    return x + y;
  }

  @Override
  public float subtract(float x, float y) {
    return x - y;
  }

  @Override
  public float multiply(float x, float y) {
    return x * y;
  }

  @Override
  public float division(float x, float y) {
    return x / y;
  }
}
