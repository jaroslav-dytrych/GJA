/*
 * Project: Examples for GJA course
 * Authors: Ing. Jan Kouřil
 *          Ing. Jaroslav Dytrych idytrych@fit.vutbr.cz
 * File: Hello.java
 * Description: Example Jersey application server
 */

/**
 * @file Hello.java
 *
 * @brief Example Jersey application server
 */

/**
 * @package cz.vutbr.fit.knot.gja.jHW.server
 * 
 * @brief Example Jersey application server
 */
package cz.vutbr.fit.knot.gja.jHW.server;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/** 
 * Example Jersey application server
 * 
 * Plain old Java Object it does not extend as class or implements 
 * an interface
 *
 * The class registers its methods for the HTTP GET request using the 
 * @GET annotation. Using the @Produces annotation, it defines that it 
 * can deliver several MIME types, text, XML and HTML. 
 * The browser requests per default the HTML MIME type.
 * 
 * @brief Example Jersey application server
 */
@Path("/hello")  // Sets the path to base URL + /hello
public class Hello {

  /**
   * This method is called if TEXT_PLAIN is requested
   *
   * @return Returns string with requested information
   */
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String sayPlainTextHello() {
    return "Hello Jersey";
  }

  /**
   * This method is called if XML is requested
   *
   * @return Returns string with requested information in XML
   */
  @GET
  @Produces(MediaType.TEXT_XML)
  public String sayXMLHello() {
    return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey" + "</hello>";
  }

  /**
   * This method is called if HTML is requested
   *
   * @return Returns string with requested information in HTML
   */
  @GET
  @Produces(MediaType.TEXT_HTML)
  public String sayHtmlHello() {
    return "<html> " + "<title>" + "Hello Jersey" + "</title>"
           + "<body><h1>" + "Hello Jersey" + "</body></h1>" + "</html> ";
  }
}
