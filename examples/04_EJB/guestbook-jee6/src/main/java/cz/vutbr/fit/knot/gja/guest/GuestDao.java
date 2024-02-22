/*
 * Project: Examples for GJA course
 * Adapted from https://github.com/tginn/Guestbook/blob/master/src/main/java/guest/GuestDao.java
 * Editors: Ing. Jan Kouřil
 *          Ing. Jaroslav Dytrych idytrych@fit.vutbr.cz
 * File: GuestDao.java
 * Description: JPA Guestbook Web Application Tutorial 
 *              - Guest database object
 */

/**
 * @file GuestDao.java
 *
 * @brief Guest database object
 */

package cz.vutbr.fit.knot.gja.guest;
 
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
 
/**
 * Guest database object
 */
@Stateless
public class GuestDao {

  /**
   * Stores a new guest
   *
   * @param emf Enitity manager factory
   * @param guest Guest to be stored
   */
  public void persist(EntityManagerFactory emf, Guest guest) {
    EntityManager em = emf.createEntityManager();
    try {
      em.getTransaction().begin();
      em.persist(guest);
      em.getTransaction().commit();
    } finally {
      if (em.getTransaction().isActive())
        em.getTransaction().rollback();
      em.close();
    }
  }

  /**
   * Retrieves all the guests
   *
   * @param emf Enitity manager factory
   * @return Returns list of guests
   */
  public List<Guest> getAllGuests(EntityManagerFactory emf) {
    EntityManager em = emf.createEntityManager();
    try {
      TypedQuery<Guest> query = em.createQuery(
        "SELECT g FROM Guest g ORDER BY g.id", Guest.class);
      return query.getResultList();
    } finally {
      em.close();
    }
  }
}