package org.primefaces.showcase.view.datatable.data;
 
import java.io.Serializable;
import java.util.List;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.showcase.domain.Car;
import org.primefaces.showcase.service.CarService;
 
@Named("dtSelectionView")
@ViewScoped
public class SelectionView implements Serializable {
     
  private List<Car> cars1;
  private List<Car> cars2;
  private List<Car> cars3;
  private List<Car> cars4;
  private List<Car> cars5;
  private List<Car> cars6;
  private Car selectedCar;
  private List<Car> selectedCars;

  @Inject
  private CarService service;

  @PostConstruct
  public void init() {
    cars1 = service.createCars(10);
    cars2 = service.createCars(10);
    cars3 = service.createCars(10);
    cars4 = service.createCars(10);
    cars5 = service.createCars(10);
    cars6 = service.createCars(10);
  }

  public List<Car> getCars1() {
    return cars1;
  }

  public List<Car> getCars2() {
    return cars2;
  }

  public List<Car> getCars3() {
    return cars3;
  }

  public List<Car> getCars4() {
    return cars4;
  }

  public List<Car> getCars5() {
    return cars5;
  }

  public List<Car> getCars6() {
    return cars6;
  }

  public void setService(CarService service) {
    this.service = service;
  }

  public Car getSelectedCar() {
    return selectedCar;
  }

  public void setSelectedCar(Car selectedCar) {
    this.selectedCar = selectedCar;
  }

  public List<Car> getSelectedCars() {
    return selectedCars;
  }

  public void setSelectedCars(List<Car> selectedCars) {
    this.selectedCars = selectedCars;
  }

  public void onRowSelect(SelectEvent event) {
    FacesMessage msg = new FacesMessage("Car Selected" 
                                        + ((Car) event.getObject()).getId());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void onRowUnselect(UnselectEvent event) {
    FacesMessage msg = new FacesMessage("Car Unselected" 
                                        + ((Car) event.getObject()).getId());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }
}