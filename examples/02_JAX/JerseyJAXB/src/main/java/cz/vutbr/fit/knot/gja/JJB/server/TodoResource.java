/*
 * Project: Examples for GJA course
 * Authors: Ing. Jan Kouřil
 *          Ing. Jaroslav Dytrych idytrych@fit.vutbr.cz
 * File: TodoResource.java
 * Description: Example Jersey application with using JAXB server
 */

/**
 * @file TodoResource.java
 *
 * @brief Example Jersey application with using JAXB server
 */

/**
 * @package cz.vutbr.fit.knot.gja.JJB.client
 * 
 * @brief Example Jersey application with using JAXB server
 */
package cz.vutbr.fit.knot.gja.JJB.server;

import cz.vutbr.fit.knot.gja.JJB.shared.Todo;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/todo")
public class TodoResource {
  /**
   * This method is called if XML is requested
   * 
   * @return Returns todo
   */
  @GET
  @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
  public Todo getXML() {
    Todo todo = new Todo();
    todo.setSummary("This is my first todo");
    todo.setDescription("This is my first todo");
    return todo;
  }
  
  /**
   * This method is called if TEXT/XML is requested
   * 
   * This can be used to test the integration with the browser
   * 
   * @return Returns todo
   */
  @GET
  @Produces({ MediaType.TEXT_XML })
  public Todo getTextXML() {
    Todo todo = new Todo();
    todo.setSummary("This is my first todo");
    todo.setDescription("This is my first todo");
    return todo;
  }
  
  /**
   * This method is called if TEXT/HTML is requested
   * 
   * This can be used to test the integration with the browser
   * 
   * @return Returns todo
   */
  @GET
  @Produces({ MediaType.TEXT_HTML })
  public Todo getHTML() {
    Todo todo = new Todo();
    todo.setSummary("This is my first todo");
    todo.setDescription("This is my first todo");
    return todo;
  }

} 