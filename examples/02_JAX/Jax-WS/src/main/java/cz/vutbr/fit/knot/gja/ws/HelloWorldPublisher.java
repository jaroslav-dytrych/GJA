/*
 * Project: Examples for GJA course
 * Authors: Ing. Jan Kouřil
 *          Ing. Jaroslav Dytrych idytrych@fit.vutbr.cz
 * File: HelloWorldPublisher.java
 * Description: Example JAX-WS Service Endpoint publisher
 */

/**
 * @file HelloWorldPublisher.java
 *
 * @brief Example JAX-WS Service Endpoint publisher
 */

package cz.vutbr.fit.knot.gja.ws;

import jakarta.xml.ws.Endpoint;
 
/**
 * Endpoint publisher
 */
public class HelloWorldPublisher {

  /**
   * Main method
   * 
   * @param args Command line arguments
   */
  public static void main(String[] args) {
    Endpoint.publish("http://localhost:6666/ws/hello", new HelloWorldImpl());
    System.out.println("Server is published at port 6666!");
  }
}
