/*
 * Adapted from: http://www.primefaces.org/showcase/ui/menu/contextmenu/basic.xhtml
 */
package org.primefaces.showcase.view.menu;
 
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
 
@Named
@RequestScoped
public class MenuView {
    
  public void save() {
    addMessage("Success", "Data saved");
  }

  public void update() {
    addMessage("Success", "Data updated");
  }

  public void delete() {
    addMessage("Success", "Data deleted");
  }

  public void addMessage(String summary, String detail) {
    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, 
                                            summary, detail);
    FacesContext.getCurrentInstance().addMessage(null, message);
  }
}
