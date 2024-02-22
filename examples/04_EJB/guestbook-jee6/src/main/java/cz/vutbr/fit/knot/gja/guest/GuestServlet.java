/*
 * Project: Examples for GJA course
 * Adapted from http://www.objectdb.com/tutorial/jpa/netbeans/web/servlet
 * Editors: Ing. Jan Kouřil
 *          Ing. Jaroslav Dytrych idytrych@fit.vutbr.cz
 * File: GuestServlet.java
 * Description: JPA Guestbook Web Application Tutorial - Servlet
 */

/**
 * @file GuestServlet.java
 *
 * @brief JPA Guestbook Web Application Tutorial - Servlet
 */

package cz.vutbr.fit.knot.gja.guest;
 
import java.io.IOException;
import javax.ejb.EJB;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
/**
 * JPA Guestbook Web Application Tutorial - Servlet
 */
@WebServlet(name = "GuestServlet", urlPatterns = {"/guest"})
public class GuestServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;
  /** Injected DAO EJB */
  @EJB
  GuestDao guestDao;

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
  protected void doGet(
          HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

    // Display the list of guests:
    EntityManagerFactory emf =
           (EntityManagerFactory) getServletContext().getAttribute("emf");
    request.setAttribute("guests", guestDao.getAllGuests(emf));
    request.getRequestDispatcher("/guest.jsp").forward(request, response);
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
  protected void doPost(
          HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

    EntityManagerFactory emf =
           (EntityManagerFactory) getServletContext().getAttribute("emf");
    
    // Handle a new guest:
    String name = request.getParameter("name");
    if (name != null) {
      guestDao.persist(emf, new Guest(name));
    }

    // Display the list of guests:
    doGet(request, response);
  }
}