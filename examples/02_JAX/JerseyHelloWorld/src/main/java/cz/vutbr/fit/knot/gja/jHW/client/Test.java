/*
 * Project: Examples for GJA course
 * Authors: Ing. Jan Kouřil
 *          Ing. Jaroslav Dytrych idytrych@fit.vutbr.cz
 * File: Test.java
 * Description: Example Jersey application client
 */

/**
 * @file Test.java
 *
 * @brief Example Jersey application client
 */

/**
 * @package cz.vutbr.fit.knot.gja.jHW.client
 * 
 * @brief Example Jersey application client
 */
package cz.vutbr.fit.knot.gja.jHW.client;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriBuilder;
import java.net.URI;

/**
 * Example Jersey application client
 */
public class Test {
  
  /**
   * Main method
   * 
   * @param args Command line arguments
   */
  public static void main(String[] args) {
    Client client = ClientBuilder.newBuilder().build();
    WebTarget target = client.target(getBaseURI()).path("rest").path("hello");
    
    // Fluent interfaces
    System.out.println(target.request(MediaType.TEXT_PLAIN).get().toString());
    // Get plain text
    System.out.println(target.request(MediaType.TEXT_PLAIN).get(String.class));
    // Get XML
    System.out.println(target.request(MediaType.TEXT_XML).get(String.class));
    // The HTML
    System.out.println(target.request(MediaType.TEXT_HTML).get(String.class));

  }

  /**
   * Gets server base URI
   * 
   * @return Returns server base URI
   */
  private static URI getBaseURI() {
    return UriBuilder.fromUri("http://localhost:8080/JerseyHelloWorld").build();
  }

} 