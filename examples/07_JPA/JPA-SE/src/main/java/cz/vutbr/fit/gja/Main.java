package cz.vutbr.fit.gja;

import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class Main {
  
  // add objectdb-2.2.5 from directory lib as a dependence
  
  public static void main(String[] args) {
    // Open a database connection
    // (create a new database if it doesn't exist yet):
    EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("$objectdb/db/points.odb");
    EntityManager em = emf.createEntityManager();

    Random r = new Random();

    // Store 100 Point objects in the database:
    em.getTransaction().begin();
    for (int i = 0; i < 100; i++) {
      Point p = new Point(Math.abs(r.nextInt() % 100), Math.abs(r.nextInt() % 100));
      em.persist(p);
    }
    em.getTransaction().commit();

    // Retrieve all the Point objects from the database:
    TypedQuery<Point> query = em.createQuery("SELECT p FROM Point p", Point.class);
    List<Point> results = query.getResultList();
    for (Point p : results) {
      System.out.println(p);
    }

    // Find the number of Point objects in the database:
    Query q1 = em.createQuery("SELECT COUNT(p) FROM Point p");
    System.out.println("Total Points: " + q1.getSingleResult());

    // Find the average X value:
    Query q2 = em.createQuery("SELECT AVG(p.x) FROM Point p");
    System.out.println("Average X: " + q2.getSingleResult());

    //comment this section and rerun to show persistence
    //em.getTransaction().begin();
    //Query q=em.createQuery("DELETE FROM Point");
    //q.executeUpdate();
    //em.getTransaction().commit();
    ///////////////////////////////////////////////////
    // Close the database connection:
    em.close();
    emf.close();
  }
}
