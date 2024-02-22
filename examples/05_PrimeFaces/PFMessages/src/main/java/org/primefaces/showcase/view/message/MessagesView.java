/*
 * http://www.primefaces.org/showcase/ui/message/messages.xhtml
 */
package org.primefaces.showcase.view.message;
 
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
 
@Named
@RequestScoped
public class MessagesView {
     
   public void info() {
    FacesContext.getCurrentInstance().addMessage(null, 
      new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "PrimeFaces Rocks."));
  }

  public void warn() {
    FacesContext.getCurrentInstance().addMessage(null, 
      new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Watch out for PrimeFaces."));
  }

  public void error() {
    FacesContext.getCurrentInstance().addMessage(null, 
      new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Contact admin."));
  }

  public void fatal() {
    FacesContext.getCurrentInstance().addMessage(null, 
      new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "System Error"));
  }
}
