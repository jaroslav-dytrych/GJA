/*
 * Project: Examples for GJA course
 * Authors: Ing. Jan Kouřil
 *          Ing. Jaroslav Dytrych idytrych@fit.vutbr.cz
 * File: FooBar.java
 * Description: Example JAX-WS Service for Document Binding - Service Endpoint 
 *              Interface
 */

/**
 * @file FooBar.java
 *
 * @brief Example Example JAX-WS Service for Document Binding - Service Endpoint Interface
 */

/**
 * @package cz.vutbr.fit.knot.gja.ws
 * 
 * @brief Example JAX-WS Service for Document Binding
 */
package cz.vutbr.fit.knot.gja.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.jws.soap.SOAPBinding.Use;

/**
 * Service Endpoint Interface
 */
@WebService
@SOAPBinding(style = Style.DOCUMENT, use = Use.LITERAL)
public interface FooBar {

  /**
   * Prints string with given name
   * 
   * @param name Name
   * @return String with given name
   */
  @WebMethod
  String callFooBar(String name);

  /**
   * Gets server detail
   * 
   * @param client Client
   * @return Returns server detail information
   */
  @WebMethod
  Server getServerDetail(String client);
}
