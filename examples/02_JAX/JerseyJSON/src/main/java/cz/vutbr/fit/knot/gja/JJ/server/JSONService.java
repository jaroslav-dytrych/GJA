/*
 * Project: Examples for GJA course
 * Authors: Ing. Jan Kouřil
 *          Ing. Jaroslav Dytrych idytrych@fit.vutbr.cz
 * File: JSONService.java
 * Description: Example Jersey application with using JSON - server
 */

/**
 * @file JSONService.java
 *
 * @brief Example Jersey application with using JSON - server
 */

/**
 * @package cz.vutbr.fit.knot.gja.JJ.server
 * 
 * @brief Example Jersey application with using JSON - server
 */
package cz.vutbr.fit.knot.gja.JJ.server;

import cz.vutbr.fit.knot.gja.JJ.shared.Track;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * Example Jersey application with using JSON - server
 */
@Path("/json/metallica")
public class JSONService {

  /**
   * This method will be called for GET request
   * 
   * @return Returns requested object
   */
  @GET
  @Path("/get")
  @Produces(MediaType.APPLICATION_JSON)
  public Track getTrackInJSON() {

    Track track = new Track();
    track.setTitle("Enter Sandman");
    track.setSinger("Metallica");

    return track;

  }

  /**
   * This method will be called for POST request
   * Receives send object
   * 
   * @param track Send object
   * @return Returns response for client
   */
  @POST
  @Path("/post")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createTrackInJSON(Track track) {

    String result = "Track saved : " + track;
    return Response.status(201).entity(result).build();

  }
}
