/*
 * Project: Examples for GJA course
 * Authors: Ing. Jan Kouřil
 *          Ing. Jaroslav Dytrych idytrych@fit.vutbr.cz
 * File: Test.java
 * Description: Example Jersey application with using JAXB client
 */

/**
 * @file Test.java
 *
 * @brief Example Jersey application with using JAXB client
 */

/**
 * @package cz.vutbr.fit.knot.gja.JJB.client
 * 
 * @brief Example Jersey application with using JAXB client
 */
package cz.vutbr.fit.knot.gja.JJB.client;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriBuilder;
import java.net.URI;

/**
 * Example Jersey application with using JAXB client
 */
public class Test {
  
  /**
   * Main method
   * 
   * @param args Command line arguments
   */
  public static void main(String[] args) {
    
    Client client = ClientBuilder.newBuilder().build();
    WebTarget target = client.target(getBaseURI()).path("rest").path("todo");
    
    // Get XML
    System.out.println(target.request(MediaType.TEXT_XML).get(String.class));
    
    // Get JSON for application
    System.out.println(target.request(MediaType.APPLICATION_JSON).get(String.class));
    
    // Get XML for application
    System.out.println(target.request(MediaType.APPLICATION_XML).get(String.class));
  }

  /**
   * Gets server base URI
   * 
   * @return Returns server base URI
   */
  private static URI getBaseURI() {
    return UriBuilder.fromUri("http://localhost:8080/JerseyJAXB").build();
  }
} 
