/*
 * Adapted from http://www.roseindia.net/ejb/examples-of-StatelessBean.shtml
 */
package net.roseindia.ejb3.stateless.example;

import jakarta.ejb.Remote;

@Remote
public interface CalculatorRemote {

  public float add(float x, float y);

  public float subtract(float x, float y);

  public float multiply(float x, float y);

  public float division(float x, float y);
}