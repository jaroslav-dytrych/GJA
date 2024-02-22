/*
 * Project: Examples for GJA course
 * Authors: Ing. Jan Kouřil
 *          Ing. Jaroslav Dytrych idytrych@fit.vutbr.cz
 * File: TodosResource.java
 * Description: Example Jersey application for CRUD - server
 */

/**
 * @file TodosResource.java
 *
 * @brief Example Jersey application for CRUD - server
 */

/**
 * @package cz.vutbr.fit.knot.gja.CRUD.server
 * 
 * @brief Example Jersey application for CRUD - server
 */
package cz.vutbr.fit.knot.gja.CRUD.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cz.vutbr.fit.knot.gja.CRUD.shared.TodoDao;
import cz.vutbr.fit.knot.gja.CRUD.shared.Todo;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Request;
import jakarta.ws.rs.core.UriInfo;

/**
 * Example Jersey application for CRUD - server
 */
@Path("/todos")  // Map the resource to the URL todos
public class TodosResource {

  // Allows to insert contextual objects into the class, 
  // e.g. ServletContext, Request, Response, UriInfo
  @Context
  UriInfo uriInfo;
  @Context
  Request request;


  /** 
   * Gets the list of todos to the user in the browser
   * 
   * @return Returns the list of todos to the user in the browser
   */
  @GET
  @Produces(MediaType.TEXT_XML)
  public List<Todo> getTodosBrowser() {
    List<Todo> todos = new ArrayList<>();
    todos.addAll(TodoDao.instance.getModel().values());
    return todos; 
  }
  
  /**
   * Gets the list of TODOs for applications
   * 
   * @return Returns the list of TODOs for applications
   */
  @GET
  @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
  public List<Todo> getTodos() {
    List<Todo> todos = new ArrayList<>();
    todos.addAll(TodoDao.instance.getModel().values());
    return todos; 
  }
  
  /**
   * Gets count of TODOs
   * 
   * @return Returns count of TODOs
   */
  @GET
  @Path("count")
  @Produces(MediaType.TEXT_PLAIN)
  public String getCount() {
    int count = TodoDao.instance.getModel().size();
    return String.valueOf(count);
  }
  
  /**
   * Handles POST request and creates todo
   * 
   * @param id ID of TODO
   * @param summary TODO summary
   * @param description TODO description
   * @param servletResponse Servlet response
   * @throws IOException 
   */
  @POST
  @Produces(MediaType.TEXT_HTML)
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  public void newTodo(@FormParam("id") String id,
                      @FormParam("summary") String summary,
                      @FormParam("description") String description,
                      @Context HttpServletResponse servletResponse) throws IOException {
    Todo todo = new Todo(id,summary);
    if (description!=null){
      todo.setDescription(description);
    }
    TodoDao.instance.getModel().put(id, todo);
    
    servletResponse.sendRedirect("../create_todo.html");
  }
  
  /**
   * Gets TODO by id
   * 
   * @param id ID of TODO
   * @return REturns TODO with given id
   */
  @Path("{todo}")
  public TodoResource getTodo(@PathParam("todo") String id) {
    return new TodoResource(uriInfo, request, id);
  }
  
} // public class TodosResource
