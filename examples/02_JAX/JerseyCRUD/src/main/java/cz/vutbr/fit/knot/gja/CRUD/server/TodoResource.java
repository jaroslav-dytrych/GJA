/*
 * Project: Examples for GJA course
 * Authors: Ing. Jan Kouřil
 *          Ing. Jaroslav Dytrych idytrych@fit.vutbr.cz
 * File: TodoResource.java
 * Description: Example Jersey application for CRUD - resource for one object
 */

/**
 * @file TodoResource.java
 *
 * @brief Example Jersey application for CRUD - resource for one object
 */

package cz.vutbr.fit.knot.gja.CRUD.server;

import cz.vutbr.fit.knot.gja.CRUD.shared.TodoDao;
import cz.vutbr.fit.knot.gja.CRUD.shared.Todo;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Request;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.xml.bind.JAXBElement;

/**
 * Example Jersey application for CRUD - resource for one object
 */
public class TodoResource {
  
  // Contextual objects
  @Context
  UriInfo uriInfo;
  @Context
  Request request;
  
  /** TODO ID */
  String id;
  
  /**
   * Constructor
   */
  public TodoResource(UriInfo uriInfo, Request request, String id) {
    this.uriInfo = uriInfo;
    this.request = request;
    this.id = id;
  }
  
  /**
   * Gets the TODO for applications
   * 
   * @return Returns the TODO for applications
   */   
  @GET
  @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
  public Todo getTodo() {
    Todo todo = TodoDao.instance.getModel().get(id);
    if(todo==null)
      throw new RuntimeException("Get: Todo with " + id + " not found");
    return todo;
  }
  
  /** 
   * Gets the todo to the user in the browser
   * 
   * @return Returns the todo to the user in the browser
   */
  @GET
  @Produces(MediaType.TEXT_HTML)
  public Todo getTodoHTML() {
    Todo todo = TodoDao.instance.getModel().get(id);
    if(todo==null)
      throw new RuntimeException("Get: Todo with " + id + " not found");
    return todo;
  }
  
  /** 
   * Gets the todo to the user in the browser
   * 
   * @return Returns the todo to the user in the browser
   */
  @GET
  @Produces(MediaType.TEXT_XML)
  public Todo getTodoXML() {
    Todo todo = TodoDao.instance.getModel().get(id);
    if(todo==null)
      throw new RuntimeException("Get: Todo with " + id + " not found");
    return todo;
  }
  
  /**
   * Receives TODO from client and stores it
   * 
   * @param todo Received TODO
   * @return Returns response for client
   */
  @PUT
  @Consumes(MediaType.APPLICATION_XML)
  public Response putTodo(JAXBElement<Todo> todo) {
    Todo c = todo.getValue();
    return putAndGetResponse(c);
  }
  
  /**
   * Processes request for deletion of TODO
   */
  @DELETE
  public void deleteTodo() {
    Todo c = TodoDao.instance.getModel().remove(id);
    if(c==null)
      throw new RuntimeException("Delete: Todo with " + id +  " not found");
  }
  
  /**
   * Processes received object (from PUT message) and gets response for client
   * 
   * @param todo REceived TODO
   * @return Returns response for client
   */
  private Response putAndGetResponse(Todo todo) {
    Response res;
    if(TodoDao.instance.getModel().containsKey(todo.getId())) {
      res = Response.noContent().build();
    } else {
      res = Response.created(uriInfo.getAbsolutePath()).build();
    }
    TodoDao.instance.getModel().put(todo.getId(), todo);
    return res;
  }
} // public class TodoResource