/*
 * Project: Examples for GJA course
 * Authors: Ing. Jan Kouřil
 *          Ing. Jaroslav Dytrych idytrych@fit.vutbr.cz
 * File: WsPublisher.java
 * Description: Example of  Web Service handlers - Endpoint publisher
 */

/**
 * @file WsPublisher.java
 *
 * @brief Example of  Web Service handlers - Endpoint publisher
 */

/**
 * @package fit.ws.server
 * 
 * @brief Example of  Web Service handlers
 */
package cz.vutbr.fit.knot.gja.ws.server;

import jakarta.xml.ws.Endpoint;

/**
 * Endpoint publisher
 */
public class WsPublisher {

  /**
   * Main method
   *
   * @param args Command line arguments
   */
  public static void main(String[] args) {
    Endpoint.publish("http://localhost:9999/ws/server", new Server());

    System.out.println("Service is published at port 9999!");
  }
}
