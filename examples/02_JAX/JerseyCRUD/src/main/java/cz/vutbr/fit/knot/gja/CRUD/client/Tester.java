/*
 * Project: Examples for GJA course
 * Authors: Ing. Jan Kouřil
 *          Ing. Jaroslav Dytrych idytrych@fit.vutbr.cz
 * File: Tester.java
 * Description: Example Jersey application for CRUD - testing client
 */

/**
 * @file Tester.java
 *
 * @brief Example Jersey application for CRUD - testing client
 */

/**
 * @package cz.vutbr.fit.knot.gja.CRUD.client
 * 
 * @brief Example Jersey application for CRUD - testing client
 */
package cz.vutbr.fit.knot.gja.CRUD.client;

import java.net.URI;
import cz.vutbr.fit.knot.gja.CRUD.shared.Todo;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Form;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;


/**
 * Example Jersey application for CRUD - testing client
 */
public class Tester {
  
  /**
   * Main method
   * 
   * @param args Command line arguments 
   */
  public static void main(String[] args) {
    Client client = ClientBuilder.newBuilder().build();
    
    // Create one todo
    Todo todo = new Todo("3", "Blablabla");
    
    WebTarget target = client.target(getBaseURI()).path("rest").path("todos");
    
    Response response = target.path(todo.getId()).request(MediaType.APPLICATION_XML).put(Entity.xml(todo));
    // Return code should be 204 No Content => created resource
    System.out.println(response.getStatus());
    
    // Get the Todos
    System.out.println(target.path(todo.getId()).request(MediaType.TEXT_XML).get(String.class));
    
    // Get JSON for application
    System.out.println(target.request(MediaType.APPLICATION_JSON).get(String.class));
    
    // Get XML for application
    System.out.println(target.path(todo.getId()).request(MediaType.APPLICATION_XML).get(String.class));

    // Get the Todo with id 1
    System.out.println(target.path("1").request(MediaType.APPLICATION_XML).get(String.class));
    // Delete Todo with id 1
    target.path("1").request().delete();
    // Get the all todos, id 1 should be deleted
    System.out.println(target.request(MediaType.APPLICATION_XML).get(String.class));

    // create a Todo
    Form form = new Form();
    form.param("id", "4");
    form.param("summary", "Demonstration of the client lib for forms");
    response = target.request().post(Entity.form(form));
    System.out.println("Form response " + response.getEntity());
    // Get the all todos, id 4 should be created
    System.out.println(target.request(MediaType.APPLICATION_XML).get(String.class));

  }  // main()

  /**
   * Gets server base URI
   * 
   * @return Returns server base URI
   */
  private static URI getBaseURI() {
    return UriBuilder.fromUri("http://localhost:8080/JerseyCRUD").build();
  }
} // public class Tester
