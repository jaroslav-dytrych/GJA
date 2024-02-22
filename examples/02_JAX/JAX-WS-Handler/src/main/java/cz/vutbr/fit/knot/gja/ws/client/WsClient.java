/*
 * Project: Examples for GJA course
 * Authors: Ing. Jan Kouřil
 *          Ing. Jaroslav Dytrych idytrych@fit.vutbr.cz
 * File: WsClient.java
 * Description: Example of Web Service handlers - Client
 */

/**
 * @file WsClient.java
 *
 * @brief Example of Web Service handlers - Client
 */

/**
 * @package fit.ws.client
 * 
 * @brief Example of Web Service handlers - Client
 */
package cz.vutbr.fit.knot.gja.ws.client;

/**
 * Client
 */
public class WsClient {

  /**
   * Main method
   *
   * @param args Command line arguments
   * @throws java.lang.Exception
   */
  public static void main(String[] args) throws Exception {

    ServerInfoService sis = new ServerInfoService();
    ServerInfo si = sis.getServerPort();

    System.out.println(si.getServerName());

  }
}