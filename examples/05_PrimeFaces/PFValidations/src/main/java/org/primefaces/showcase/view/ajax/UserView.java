/*
 * http://www.primefaces.org/showcase/ui/ajax/validation.xhtml
 */
package org.primefaces.showcase.view.ajax;
 
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
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

  public void save() {
    FacesContext.getCurrentInstance().addMessage(null,
      new FacesMessage("Welcome " + firstname + " " + lastname));
  }
}
