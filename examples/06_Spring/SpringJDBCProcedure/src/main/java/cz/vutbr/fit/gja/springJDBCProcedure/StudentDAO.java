/*
 * Adapted from http://www.tutorialspoint.com/spring/calling_stored_procedure.htm
 */

package cz.vutbr.fit.gja.springJDBCProcedure;

import javax.sql.DataSource;
import java.util.List;

public interface StudentDAO {

  /**
   * This is the method to be used to initialize database resources ie.
   * connection.
   * 
   * @param ds Data source
   */
  public void setDataSource(DataSource ds);

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
   * @param id Id of student
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
}
