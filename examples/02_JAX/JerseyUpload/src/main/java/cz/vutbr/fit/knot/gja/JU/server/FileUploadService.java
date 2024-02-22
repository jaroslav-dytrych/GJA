/*
 * Project: Examples for GJA course
 * Authors: Ing. Jan Kouřil
 *          Ing. Jaroslav Dytrych dytrych@fit.vutbr.cz
 * File: FileUploadService.java
 * Description: Example Jersey application for file upload and download
 *              - upload service
 */

/**
 * @file FileUploadService.java
 *
 * @brief Example Jersey application for file upload and download - upload service
 */
package cz.vutbr.fit.knot.gja.JU.server;

import jakarta.servlet.ServletContext;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 * Example Jersey application for file upload and download - upload service
 */
@Path("/file")
public class FileUploadService {

  /** Servlet context */
  @Context
  ServletContext servletContext;

  /**
   * Receives file from the client and stores it
   * 
   * @param uploadedInputStream Stream with uploaded file
   * @return Returns response for the client
   */
  @POST
  @Path("/upload")
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  @Produces(MediaType.TEXT_PLAIN)
  public Response uploadFile(@FormDataParam("file") InputStream uploadedInputStream) {

    String contextRoot = servletContext.getRealPath("image_on_server.png");
    String uploadedFileLocation = contextRoot;
    if (uploadedFileLocation == null) {  // if getRealPath() returns null, use home
      uploadedFileLocation = System.getProperty("user.home") + "/downloads/image_on_server.png";
    }

    // save file
    writeToFile(uploadedInputStream, uploadedFileLocation);

    String output = "File uploaded to : " + uploadedFileLocation;

    return Response.status(200).entity(output).build();
  }

  /**
   * Saves uploaded file to new location
   * 
   * @param uploadedInputStream Stream with uploaded file
   * @param uploadedFileLocation Location of uploaded file
   */
  private void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) {

    try {
      OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
      int read;
      byte[] bytes = new byte[1024];

      while ((read = uploadedInputStream.read(bytes)) != -1) {
        out.write(bytes, 0, read);
      }
      out.flush();
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}  // public class FileUploadService
