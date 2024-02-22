/*
 * Adapted from http://www.tutorialspoint.com/hibernate/hibernate_annotations.htm
 */

package cz.vutbr.fit.gja;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "EMPLOYEE")
public class Employee implements Serializable {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO, generator="native")
  @GenericGenerator(name = "native")
  @Column(name = "id")
  private int id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "salary")
  private int salary;

  public Employee() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String first_name) {
    this.firstName = first_name;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String last_name) {
    this.lastName = last_name;
  }

  public int getSalary() {
    return salary;
  }

  public void setSalary(int salary) {
    this.salary = salary;
  }
}
