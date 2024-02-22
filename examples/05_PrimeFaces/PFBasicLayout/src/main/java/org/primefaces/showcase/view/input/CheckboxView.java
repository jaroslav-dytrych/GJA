/*
 * http://www.primefaces.org/showcase/ui/input/manyCheckbox.xhtml
 */
package org.primefaces.showcase.view.input;
 
import java.util.ArrayList;
import java.util.List;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

 
@Named
@RequestScoped
public class CheckboxView {
 
  private String[] selectedConsoles;
  private String[] selectedConsoles2;
  private String[] selectedCities;
  private String[] selectedCities2;
  private List<String> cities;

  @PostConstruct
  public void init() {
    cities = new ArrayList<>();
    cities.add("Miami");
    cities.add("London");
    cities.add("Paris");
    cities.add("Istanbul");
    cities.add("Berlin");
    cities.add("Barcelona");
    cities.add("Rome");
    cities.add("Brasilia");
    cities.add("Amsterdam");
  }

  public String[] getSelectedConsoles() {
    return selectedConsoles;
  }

  public void setSelectedConsoles(String[] selectedConsoles) {
    this.selectedConsoles = selectedConsoles;
  }

  public String[] getSelectedCities() {
    return selectedCities;
  }

  public void setSelectedCities(String[] selectedCities) {
    this.selectedCities = selectedCities;
  }

  public String[] getSelectedCities2() {
    return selectedCities2;
  }

  public void setSelectedCities2(String[] selectedCities2) {
    this.selectedCities2 = selectedCities2;
  }

  public String[] getSelectedConsoles2() {
    return selectedConsoles2;
  }

  public void setSelectedConsoles2(String[] selectedConsoles2) {
    this.selectedConsoles2 = selectedConsoles2;
  }

  public List<String> getCities() {
    return cities;
  }
}
