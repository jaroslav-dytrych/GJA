/*
 * Adapted from http://www.tutorialspoint.com/spring/calling_stored_procedure.htm
 */

package cz.vutbr.fit.gja.springJDBCProcedure;

public class Student {

  private Integer age;
  private String name;
  private Integer id;

  public void setAge(Integer age) {
    this.age = age;
  }

  public Integer getAge() {
    return age;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }
}
