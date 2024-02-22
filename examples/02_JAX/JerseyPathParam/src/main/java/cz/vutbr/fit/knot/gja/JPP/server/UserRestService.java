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
 * @package cz.vutbr.fit.knot.gja.JPP.server
 * 
 * @brief Example Jersey application with using of Path parameters
 */
package cz.vutbr.fit.knot.gja.JPP.server;
 
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

/**
 * Example Jersey application with using of Path parameters - server
 */
@Path("/users")
public class UserRestService {

  @GET
  @Path("{year}/{month}/{day}")
  public Response getUserHistory(
          @PathParam("year") int year,
          @PathParam("month") int month,
          @PathParam("day") int day) {

    String date = year + "/" + month + "/" + day;

    return Response.status(200).entity("getUserHistory is called, year/month/day : " + date).build();
  }
}