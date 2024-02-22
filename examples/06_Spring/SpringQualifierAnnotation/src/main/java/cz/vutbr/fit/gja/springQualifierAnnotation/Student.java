/*
 * Adapted from http://www.tutorialspoint.com/spring/spring_qualifier_annotation.htm
 */

package cz.vutbr.fit.gja.springQualifierAnnotation;

public class Student {

  private Integer age;
  private String name;

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
}
