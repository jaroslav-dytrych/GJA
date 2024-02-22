/*
 * Project: Examples for GJA course
 * Authors: Ing. Jan Kouřil
 *          Ing. Jaroslav Dytrych idytrych@fit.vutbr.cz
 * File: HelloWorld.java
 * Description: JAX-WS Web application example
 */

/**
 * @file HelloWorld.java
 *
 * @brief JAX-WS Web application example
 */

/**
 * @package cz.vutbr.fit.knot.gja.example
 * 
 * @brief JAX-WS Web application example
 */
package cz.vutbr.fit.knot.gja.example;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

/**
 * JAX-WS Web application example - web service
 */
@WebService
public class HelloWorld {

  /**
   * Gets hello world string with given name
   * 
   * @param name Name
   * @return Returns hello world string with given name
   */
  @WebMethod(operationName = "getHelloWorld")
  public String getHelloWorld(String name) {
    return "Hello World JAX-WS " + name;
  }
}
