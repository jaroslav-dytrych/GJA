package cz.vutbr.fit;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
 
@ManagedBean(name="normal")
@SessionScoped
public class NormalBean{

  public String data;

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