/*
 * Project: Examples for GJA course
 * Authors: Ing. Jan Kouřil
 *          Ing. Jaroslav Dytrych idytrych@fit.vutbr.cz
 * File: FooBarImpl.java
 * Description: Example JAX-WS Service for Document Binding - Service Implementation
 */

/**
 * @file FooBarImpl.java
 *
 * @brief Example Example JAX-WS Service for Document Binding - Service Implementation
 */

package cz.vutbr.fit.knot.gja.ws;

import jakarta.jws.WebService;

/**
 * Service implementation
 */
@WebService(endpointInterface = "cz.vutbr.fit.knot.gja.ws.FooBar")
public class FooBarImpl implements FooBar {

  /**
   * Prints string with given name
   * 
   * @param name Name
   * @return String with given name
   */
  @Override
  public String callFooBar(String name) {
    return "FooBar called by " + name;
  }

  /**
   * Gets server detail
   * 
   * @param client Client
   * @return Returns server detail information
   */
  @Override
  public Server getServerDetail(String client) {
    Server server = new Server();
    server.setName("localhost");
    server.setIp("127.0.1.1");
    server.setMac("08-11-96-2F-D3-DC");
    server.setOs("Ubuntu");
    return server;
  }
}
