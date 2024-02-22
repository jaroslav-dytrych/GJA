/*
 * Adapted from http://www.tutorialspoint.com/spring/calling_stored_procedure.htm
 */

package cz.vutbr.fit.gja.springJDBCProcedure;

import org.springframework.jdbc.core.RowMapper;
import java.sql.SQLException;
import java.sql.ResultSet;

public class StudentMapper implements RowMapper<Student> {

  @Override
  public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
    Student student = new Student();
    student.setId(rs.getInt("id"));
    student.setName(rs.getString("name"));
    student.setAge(rs.getInt("age"));
    return student;
  }
}
