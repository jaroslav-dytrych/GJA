/*
 * http://www.primefaces.org/showcase/ui/misc/requestContext.xhtml
 */
package org.primefaces.showcase.view.misc;
 
import com.google.gson.Gson;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;
import org.primefaces.PrimeFaces.Ajax;
import org.primefaces.context.PrimeFacesContext;
import org.primefaces.showcase.domain.User;
 
@Named
@RequestScoped
public class RequestContextView {
  
  private User user;

  @PostConstruct
  public void init() {
    user = new User();

    if (!PrimeFacesContext.getCurrentInstance().isPostback()) {
      PrimeFaces.current().executeScript("alert('This onload script is added from backing bean.')");
    }
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public void save() {
    Ajax ajax = PrimeFaces.current().ajax();
    ajax.addCallbackParam("saved", true);  // basic parameter
    Gson gson = new Gson();
    ajax.addCallbackParam("user", gson.toJson(user));

    // execute javascript oncomplete
    PrimeFaces.current().executeScript("PrimeFaces.info('Hello from the Backing Bean');");

    // update panel
    ajax.update("form:panel");

    // scroll to panel
    PrimeFaces.current().scrollTo("form:panel");

    // add facesmessage
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Success", "Success"));
  }
}
