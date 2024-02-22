/*
 * Project: Examples for GJA course
 * Authors: Ing. Jan Kouřil
 *          Ing. Jaroslav Dytrych dytrych@fit.vutbr.cz
 * File: FileDownloadService.java
 * Description: Example Jersey application for file upload and download
 *              - download service
 */

/**
 * @file FileDownloadService.java
 *
 * @brief Example Jersey application for file upload and download - download service
 */

/**
 * @package cz.vutbr.fit.knot.gja.JU.server
 * 
 * @brief Example Jersey application for file upload and download
 */
package cz.vutbr.fit.knot.gja.JU.server;

import jakarta.servlet.ServletContext;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import java.io.File;

/**
 * Example Jersey application for file upload and download - download service
 */
@Path("/image")
public class FileDownloadService {

  /** Servlet context */
  @Context
  ServletContext servletContext;

  /**
   * Gets file
   * 
   * @return Response for client with given file
   */
  @GET
  @Path("/get")
  @Produces("image/png")
  public Response getFile() {

    String contextRoot = servletContext.getRealPath("image_on_server.png");
    String uploadedFileLocation = contextRoot;
    if (uploadedFileLocation == null) {  // if getRealPath() returns null, use home
      uploadedFileLocation = System.getProperty("user.home") + "/downloads/image_on_server.png";
    }
    File file = new File(uploadedFileLocation);

    ResponseBuilder response = Response.ok((Object) file);
    response.header("Content-Disposition", "attachment; filename=image_on_server.png");
    return response.build();
  }

}
