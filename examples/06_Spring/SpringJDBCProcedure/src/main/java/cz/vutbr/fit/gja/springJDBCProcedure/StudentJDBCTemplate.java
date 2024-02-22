/*
 * Adapted from http://www.tutorialspoint.com/spring/calling_stored_procedure.htm
 */

package cz.vutbr.fit.gja.springJDBCProcedure;

import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

public class StudentJDBCTemplate implements StudentDAO {

  private DataSource dataSource;
  private SimpleJdbcCall jdbcCall;
  private JdbcTemplate jdbcTemplateObject;

  @Override
  public void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
    this.jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("getRecord");
    this.jdbcTemplateObject = new JdbcTemplate(dataSource);
  }

  @Override
  public void create(String name, Integer age) {
    String SQL = "insert into Student (name, age) values (?, ?)";
    jdbcTemplateObject.update(SQL, name, age);
    System.out.println("Created Record Name = " + name + " Age = " + age);
  }

  @Override
  public Student getStudent(Integer id) {
    SqlParameterSource in = new MapSqlParameterSource().addValue("in_id", id);
    Map<String, Object> out = jdbcCall.execute(in);
    Student student = new Student();
    student.setId(id);
    student.setName((String) out.get("out_name"));
    student.setAge((Integer) out.get("out_age"));

    return student;
  }

  @Override
  public List<Student> listStudents() {
    String SQL = "select * from Student";
    List<Student> students = jdbcTemplateObject.query(SQL, new StudentMapper());
    return students;
  }
}
