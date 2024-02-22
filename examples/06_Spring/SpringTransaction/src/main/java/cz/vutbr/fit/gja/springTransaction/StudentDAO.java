/*
 * Adapted from http://www.tutorialspoint.com/spring/declarative_management.htm
 */

package cz.vutbr.fit.gja.springTransaction;

import java.util.List;
import javax.sql.DataSource;

public interface StudentDAO {

  /**
   * This is the method to be used to initialize database resources ie.
   * connection.
   * 
   * @param ds Data source
   */
  public void setDataSource(DataSource ds);

  /**
   * This is the method to be used to create a record in the Student and Marks
   * tables.
   * 
   * @param name Name
   * @param age Age
   * @param marks marks
   * @param year Academic year
   */
  public void create(String name, Integer age, Integer marks, Integer year);

  /**
   * This is the method to be used to list down all the records from the Student
   * and Marks tables.
   * 
   * @return Returns all the records from the Student and Marks tables
   */
  public List<StudentMarks> listStudents();
}
