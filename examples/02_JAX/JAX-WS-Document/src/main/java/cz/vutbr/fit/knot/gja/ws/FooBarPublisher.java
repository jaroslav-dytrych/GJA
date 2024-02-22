/*
 * Project: Examples for GJA course
 * Authors: Ing. Jan Kouřil
 *          Ing. Jaroslav Dytrych idytrych@fit.vutbr.cz
 * File: FooBarPublisher.java
 * Description: Example JAX-WS Service for Document Binding - Service Endpoint publisher
 */

/**
 * @file FooBarPublisher.java
 *
 * @brief Example Example JAX-WS Service for Document Binding - Service Endpoint publisher
 */

package cz.vutbr.fit.knot.gja.ws;

import jakarta.xml.ws.Endpoint;

/**
 * Endpoint publisher
 */
public class FooBarPublisher {

  /**
   * Main method
   * 
   * @param args Command line arguments
   */
  public static void main(String[] args) {
    Endpoint.publish("http://localhost:7777/JAX-WS-Document/foobar", new FooBarImpl());
    System.out.println("Server is published at port 7777!");
  }
}
