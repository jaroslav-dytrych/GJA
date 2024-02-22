/*
 * Adapted from http://www.tutorialspoint.com/spring/spring_jdbc_example.htm
 */

package cz.vutbr.fit.gja.springJDBC;

import java.util.List;

public interface StudentDAO {

  /*
   * This is the method to be used to initialize database resources ie.
   * connection. 
   * public void setDataSource(DataSource ds); 
   */
  
   /**
   * This is the method to be used to create a record in the Student table.
   * 
   * @param name Name
   * @param age Age
   */
  public void create(String name, Integer age);

  /**
   * This is the method to be used to list down a record from the Student table
   * corresponding to a passed student id.
   * 
   * @param id Id of the student
   * @return  Returns student with given id
   */
  public Student getStudent(Integer id);

  /**
   * This is the method to be used to list down all the records from the Student
   * table.
   * 
   * @return Returns all the records from the Student table
   */
  public List<Student> listStudents();

  /**
   * This is the method to be used to delete a record from the Student table
   * corresponding to a passed student id.
   * 
   * @param id Id of deleted student
   */
  public void delete(Integer id);

  /**
   * This is the method to be used to update a record into the Student table.
   * 
   * @param id Id of the student
   * @param age New age
   */
  public void update(Integer id, Integer age);
}
