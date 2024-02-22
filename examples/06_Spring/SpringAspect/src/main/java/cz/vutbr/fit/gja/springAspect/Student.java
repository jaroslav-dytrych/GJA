/*
 * Adapted from http://www.tutorialspoint.com/spring/aspectj_based_aop_appoach.htm
 */

package cz.vutbr.fit.gja.springAspect;

public class Student {

  private Integer age;
  private String name;

  public void setAge(Integer age) {
    this.age = age;
  }

  public Integer getAge() {
    System.out.println("Age : " + age);
    return age;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    System.out.println("Name : " + name);
    return name;
  }

  public void printThrowException() {
    System.out.println("Exception raised");
    throw new IllegalArgumentException();
  }
}
