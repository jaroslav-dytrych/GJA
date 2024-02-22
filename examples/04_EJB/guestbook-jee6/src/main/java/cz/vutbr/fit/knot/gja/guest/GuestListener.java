/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.vutbr.fit.knot.gja.guest;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Web application lifecycle listener.
 *
 * @author dytrych
 */
@WebListener()
public class GuestListener implements ServletContextListener {

  @Override
  public void contextInitialized(ServletContextEvent e) {
    com.objectdb.Enhancer.enhance("cz.vutbr.fit.knot.gja.guest.Guest");
    EntityManagerFactory emf =
      Persistence.createEntityManagerFactory("$objectdb/db/guests.odb");
    e.getServletContext().setAttribute("emf", emf);
  }

  @Override
  public void contextDestroyed(ServletContextEvent e) {
    EntityManagerFactory emf =
      (EntityManagerFactory)e.getServletContext().getAttribute("emf");
    emf.close();
  }
}
