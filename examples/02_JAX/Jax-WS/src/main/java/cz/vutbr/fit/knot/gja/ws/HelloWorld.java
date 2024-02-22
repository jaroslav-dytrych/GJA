/*
 * Project: Examples for GJA course
 * Authors: Ing. Jan Kouřil
 *          Ing. Jaroslav Dytrych idytrych@fit.vutbr.cz
 * File: HelloWorld.java
 * Description: Example JAX-WS Service Endpoint Interface
 */

/**
 * @file HelloWorld.java
 *
 * @brief Example JAX-WS Service Endpoint Interface
 */

/**
 * @package cz.vutbr.fit.knot.gja.ws
 * 
 * @brief Example JAX-WS Service Endpoint Interface
 */
package cz.vutbr.fit.knot.gja.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.Style;

/**
 * Service Endpoint Interface
 */
@WebService
@SOAPBinding(style = Style.RPC)
public interface HelloWorld {

  /**
   * Gets hello world string with given name
   * 
   * @param name Name
   * @return Returns hello world string with given name
   */
  @WebMethod
  String getHelloWorldAsString(String name);
}
