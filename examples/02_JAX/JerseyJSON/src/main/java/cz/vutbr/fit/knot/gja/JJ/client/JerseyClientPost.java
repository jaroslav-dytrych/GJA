/*
 * Project: Examples for GJA course
 * Authors: Ing. Jan Kouřil
 *          Ing. Jaroslav Dytrych idytrych@fit.vutbr.cz
 * File: JerseyClientPost.java
 * Description: Example Jersey application with using JSON - client using POST
 */

/**
 * @file JerseyClientPost.java
 *
 * @brief Example Jersey application with using JSON - client using POST
 */
package cz.vutbr.fit.knot.gja.JJ.client;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import java.net.URI;

/**
 * Example Jersey application with using JSON - client using POST
 */
public class JerseyClientPost {

  /**
   * Main method
   * 
   * @param args Command line arguments
   */
  public static void main(String[] args) {

    Client client = ClientBuilder.newBuilder().build();
    WebTarget target = client.target(getBaseURI()).path("rest").path("json")
                       .path("metallica").path("post");

    String input = "{\"singer\":\"Metallica\",\"title\":\"Fade To Black\"}";

    Response response = target.request(MediaType.APPLICATION_JSON).post(Entity.json(input));

    if (response.getStatus() != 201) {
      throw new RuntimeException("Failed : HTTP error code : "
                                 + response.getStatus());
    }

    System.out.println("Output from Server .... \n");
    String output = response.readEntity(String.class);
    System.out.println(output);

  }
  
  /**
   * Gets server base URI
   * 
   * @return Returns server base URI
   */
  private static URI getBaseURI() {
    return UriBuilder.fromUri("http://localhost:8080/JerseyJSON").build();
  }
}
