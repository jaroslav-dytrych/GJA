/*
 * Project: Examples for GJA course
 * Authors: Ing. Jan Kouřil
 *          Ing. Jaroslav Dytrych idytrych@fit.vutbr.cz
 * File: Server.java
 * Description: Example of Web Service handlers
 */

/**
 * @file Server.java
 *
 * @brief Example of Web Service handlers
 */

package cz.vutbr.fit.knot.gja.ws.server;

import jakarta.jws.HandlerChain;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

/**
 * Example nandler
 */
@WebService
@HandlerChain(file = "handler-server.xml")
public class Server {

  /**
   * Gets this server name
   * 
   * @return Returns this server name
   */
  @WebMethod
  public String getServerName() {
    return "localhost server";
  }
}