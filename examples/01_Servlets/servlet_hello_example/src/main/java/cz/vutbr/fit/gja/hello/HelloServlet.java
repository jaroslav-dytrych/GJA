/*
 * Project: Examples for GJA course
 * Author: Ing. Jaroslav Dytrych, Ph.D. dytrych@fit.vut.cz
 * File: HelloServlet.java
 * Description: Example of simple servlet
 */

/**
 * @file HelloServlet.java
 *
 * @brief Example of simple servlet
 */

/**
 * @package cz.vutbr.fit.gja.hello
 * 
 * @brief Example of simple servlet
 */
package cz.vutbr.fit.gja.hello;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Example of simple servlet
 *
 * @brief Example of simple servlet
 */
//import jakarta.servlet.annotation.WebServlet;
//@WebServlet(name = "HelloWorld", urlPatterns = {"/HelloWorld"})
public class HelloServlet extends HttpServlet {

  /**
   * The serialization runtime associates with each serializable class 
   * a version number, called a serialVersionUID, which is used during 
   * deserialization to verify that the sender and receiver of a serialized 
   * object have loaded classes for that object that are compatible with 
   * respect to serialization.
   * https://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html
   */
  private static final long serialVersionUID = 9122397357236826833L;

  /**
   * Processes requests for both HTTP
   * <code>GET</code> and
   * <code>POST</code> methods.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
                                throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    String name = request.getParameter("hello");
    out.println("<!DOCTYPE html>" +
                "<html>\n" +
                "<head><title>Hello</title></head>\n" +
                "<body style=\"bgcolor:#FDF5E6;\">\n" +
                "<h1>Hello " + name + "</h1>\n" +
                "</body></html>");
    out.close();
  }

  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  /**
   * Handles the HTTP
   * <code>GET</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   * Handles the HTTP
   * <code>POST</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   * Returns a short description of the servlet.
   *
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo() {
    return "Hello servlet";
  }
}  // public class HelloServlet
