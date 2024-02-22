/*
 * http://www.primefaces.org/showcase/ui/message/messages.xhtml
 */
package org.primefaces.showcase.view.data.datatable;
 
import java.io.Serializable;
import java.util.List;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.showcase.domain.Car;
import org.primefaces.showcase.service.CarService;
 
@Named("dtEditView")
@ViewScoped
public class EditView implements Serializable {
     
  private List<Car> cars1;
  private List<Car> cars2;

  @Inject
  private CarService service;

  @PostConstruct
  public void init() {
    cars1 = service.createCars(10);
    cars2 = service.createCars(10);
  }

  public List<Car> getCars1() {
    return cars1;
  }

  public List<Car> getCars2() {
    return cars2;
  }

  public List<String> getBrands() {
    return service.getBrands();
  }

  public List<String> getColors() {
    return service.getColors();
  }

  public void setService(CarService service) {
    this.service = service;
  }

  public void onRowEdit(RowEditEvent event) {
    FacesMessage msg = new FacesMessage("Car Edited " 
                                        + ((Car) event.getObject()).getId());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void onRowCancel(RowEditEvent event) {
    FacesMessage msg = new FacesMessage("Edit Cancelled " 
                                        + ((Car) event.getObject()).getId());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void onCellEdit(CellEditEvent event) {
    Object oldValue = event.getOldValue();
    Object newValue = event.getNewValue();

    if (newValue != null && !newValue.equals(oldValue)) {
      FacesMessage msg = new FacesMessage(
        FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue 
        + ", New:" + newValue);
      FacesContext.getCurrentInstance().addMessage(null, msg);
    }
  }
}
