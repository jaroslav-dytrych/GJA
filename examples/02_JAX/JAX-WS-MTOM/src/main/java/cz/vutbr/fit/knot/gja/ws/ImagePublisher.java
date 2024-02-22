/*
 * Project: Examples for GJA course
 * Authors: Ing. Jan Kouřil
 *          Ing. Jaroslav Dytrych idytrych@fit.vutbr.cz
 * File: ImagePublisher.java
 * Description: Example JAX-WS Service with using MTOM - Endpoint publisher
 */

/**
 * @file ImagePublisher.java
 *
 * @brief Example JAX-WS Service with using MTOM - Endpoint publisher
 */

package cz.vutbr.fit.knot.gja.ws;
 
import jakarta.xml.ws.Endpoint;

/**
 * Endpoint publisher
 */
public class ImagePublisher {

  /**
   * Main method
   *
   * @param args Command line arguments
   */
  public static void main(String[] args) {

    Endpoint.publish("http://localhost:8888/ws/image", new ImageServerImpl());
    System.out.println("Server is published at port 8888!");

  }
}
