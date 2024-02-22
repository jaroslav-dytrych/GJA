package cz.vutbr.fit;

import java.io.Serializable;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.event.ActionEvent;
 
@Named("normal")
@SessionScoped
public class NormalBean implements Serializable {

  private String data;

  public void doIt(ActionEvent event) {

    // Get submit button id
    // data = event.getComponent().getClientId();
    data = event.getComponent().getId();
  }

  public String outcome() {
    return "result";
  }

  public void setData(String s) {
    data = s;
  }

  public String getData() {
    return data;
  }	
}