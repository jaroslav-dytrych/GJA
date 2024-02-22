/*
 * Project: Examples for GJA course
 * Authors: Ing. Jan Kouřil
 *          Ing. Jaroslav Dytrych idytrych@fit.vutbr.cz
 * File: BookService.java
 * Description: Example Jersey application with using of Matrix parameters
 */

/**
 * @file BookService.java
 *
 * @brief Example Jersey application with using of Matrix parameters
 */

/**
 * @package cz.vutbr.fit.knot.gja.JM.server
 * 
 * @brief Example Jersey application with using of Matrix parameters
 */
package cz.vutbr.fit.knot.gja.JM.server;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.MatrixParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
 
/**
 * Example Jersey application with using of Matrix parameters - server
 */
@Path("/books")
public class BookService {

  @GET
  @Path("{year}")
  public Response getBooks(@PathParam("year") String year,
                           @MatrixParam("author") String author,
                           @MatrixParam("country") String country) {

    return Response.status(200).entity("getBooks is called, year : " + year
            + ", author : " + author + ", country : " + country).build();

  }
}