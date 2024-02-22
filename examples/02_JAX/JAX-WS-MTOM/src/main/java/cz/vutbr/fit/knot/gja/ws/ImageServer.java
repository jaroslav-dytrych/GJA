/*
 * Project: Examples for GJA course
 * Authors: Ing. Jan Kouřil
 *          Ing. Jaroslav Dytrych idytrych@fit.vutbr.cz
 * File: ImageServer.java
 * Description: Example JAX-WS Service with using MTOM - Service Endpoint Interface
 * 
 * Note: You need "test.jpg" file and "downloads" directory in your home
 * 
 */

/**
 * @file ImageServer.java
 *
 * @brief Example JAX-WS Service with using MTOM - Service Endpoint Interface
 */

/**
 * @package cz.vutbr.fit.knot.gja.ws
 * 
 * @brief Example JAX-WS Service with using MTOM 
 */
package cz.vutbr.fit.knot.gja.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.Style;
import java.awt.Image;
 
/**
 * Service Endpoint Interface
 */
@WebService
@SOAPBinding(style = Style.RPC)
public interface ImageServer {

  /**
   * Downloads image
   *
   * @param name File name of image
   * @return Returns downloaded image
   */
  @WebMethod
  Image downloadImage(String name);

  /**
   * Uploads image
   *
   * @param data Image data
   * @param filename File name of image
   * @return Returns message about success
   */
  @WebMethod
  String uploadImage(Image data, String filename);
}
