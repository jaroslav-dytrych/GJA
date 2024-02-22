/*
 * http://www.primefaces.org/showcase/ui/ajax/poll.xhtml
 */
package org.primefaces.showcase.view.df;
 
import java.util.HashMap;
import java.util.Map;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.showcase.domain.Car;
 
@Named("dfView")
@RequestScoped
public class DFView {
         
  public void chooseCar() {
    Map<String, Object> options = new HashMap<>();
    options.put("resizable", false);
    options.put("draggable", false);
    options.put("modal", true);
    PrimeFaces.current().dialog().openDynamic("selectCar", options, null);
  }

  public void onCarChosen(SelectEvent event) {
    Car car = (Car) event.getObject();
    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, 
                                            "Car Selected", "Id:" + car.getId());

    FacesContext.getCurrentInstance().addMessage(null, message);
  }
}
