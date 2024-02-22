/*
 * http://www.primefaces.org/showcase/ui/df/data.xhtml
 */
package org.primefaces.showcase.view.df;
 
import java.io.Serializable;
import java.util.List;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;
import org.primefaces.showcase.domain.Car;
import org.primefaces.showcase.service.CarService;
 
@Named("dfCarsView")
@ViewScoped
public class DFCarsView implements Serializable {
     
  private List<Car> cars;

  @Inject
  private CarService service;

  @PostConstruct
  public void init() {
    if (service == null) {
      service = new CarService();
    }
    cars = service.populateCars(9);
  }

  public List<Car> getCars() {
    return cars;
  }

  public void setService(CarService service) {
    this.service = service;
  }

  public void selectCarFromDialog(Car car) {
    PrimeFaces.current().dialog().closeDynamic(car);
  }
}
