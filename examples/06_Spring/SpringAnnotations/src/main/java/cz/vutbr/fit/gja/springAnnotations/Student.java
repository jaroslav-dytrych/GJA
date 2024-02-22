/*
 * Adapted from http://www.tutorialspoint.com/spring/spring_required_annotation.htm
 */

package cz.vutbr.fit.gja.springAnnotations;

import org.springframework.beans.factory.annotation.Required;

public class Student {

  private Integer age;
  private String name;

  @Required
  public void setAge(Integer age) {
    this.age = age;
  }

  public Integer getAge() {
    return age;
  }

  @Required
  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
