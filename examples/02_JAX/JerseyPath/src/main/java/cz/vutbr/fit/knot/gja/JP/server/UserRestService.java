/*
 * Project: Examples for GJA course
 * Authors: Ing. Jan Kouřil
 *          Ing. Jaroslav Dytrych idytrych@fit.vutbr.cz
 * File: UserRestService.java
 * Description: Example Jersey application with using of Path parameters
 */

/**
 * @file UserRestService.java
 *
 * @brief Example Jersey application with using of Path parameters
 */

/**
 * @package cz.vutbr.fit.knot.gja.JP.server
 * 
 * @brief Example Jersey application with using of Path parameters
 */
package cz.vutbr.fit.knot.gja.JP.server;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

/**
 * Example Jersey application with using of Path parameters - server
 */
@Path("/users")
public class UserRestService {

  /**
   * Gets user by id
   * 
   * @param id User id
   * @return Returns response for client
   */
  @GET
  @Path("/id/{id : \\d+}") // supports digit only
  public Response getUserById(@PathParam("id") String id) {

    return Response.status(200).entity("getUserById is called, id : " + id).build();
  }

  /**
   * Gets user by username
   * 
   * @param username Username
   * @return Returns response for client
   */
  @GET
  @Path("/username/{username : [a-zA-Z][a-zA-Z_0-9]*}")
  public Response getUserByUserName(@PathParam("username") String username) {

    return Response.status(200).entity("getUserByUserName is called, username : " 
                                       + username).build();
  }

  /**
   * Gets user's book by ISBN
   * 
   * @param isbn ISBN
   * @return Returns response for client
   */
  @GET
  @Path("/books/{isbn : \\d+}")
  public Response getUserBookByISBN(@PathParam("isbn") String isbn) {

    return Response.status(200).entity("getUserBookByISBN is called, isbn : " 
                                       + isbn).build();
  }
}
