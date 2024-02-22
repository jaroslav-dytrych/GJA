/*
 * http://www.primefaces.org/showcase/ui/ajax/event.xhtml
 */
package org.primaefaces.showcase.view.ajax;
 
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
 
@Named
@RequestScoped
public class UserView {
 
  private String firstname;
  private String lastname;

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }
}
