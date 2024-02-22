/*
 * Adapted from http://www.tutorialspoint.com/spring/aspectj_based_aop_appoach.htm
 */

package cz.vutbr.fit.gja.springJAspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.AfterReturning;

@Aspect
public class Logging {

  /**
   * Following is the definition for a pointcut to select all the methods
   * available. So advice will be called for all the methods.
   */
  @Pointcut("execution(* cz.vutbr.fit.gja.springJAspect.*.*(..))")
  private void selectAll() {
  }

  /**
   * This is the method which I would like to execute before a selected method
   * execution.
   * 
   * @param jp Join point
   */
  @Before("selectAll()")
  public void beforeAdvice(JoinPoint jp) {
    System.out.println("Going to setup student profile.");
    System.out.println("Going to " + jp.getSignature());
  }

  /**
   * This is the method which I would like to execute after a selected method
   * execution.
   */
  @After("selectAll()")
  public void afterAdvice() {
    System.out.println("Student profile has been setup.");
  }

  /**
   * This is the method which I would like to execute when any method returns.
   * 
   * @param retVal Return value of the method
   */
  @AfterReturning(pointcut = "selectAll()", returning = "retVal")
  public void afterReturningAdvice(Object retVal) {
    System.out.println("Returning:" + retVal.toString());
  }

  /**
   * This is the method which I would like to execute if there is an exception
   * raised by any method.
   * 
   * @param ex Exception
   */
  @AfterThrowing(pointcut = "selectAll()", throwing = "ex")
  public void AfterThrowingAdvice(IllegalArgumentException ex) {
    System.out.println("There has been an exception: " + ex.toString());
  }
}
