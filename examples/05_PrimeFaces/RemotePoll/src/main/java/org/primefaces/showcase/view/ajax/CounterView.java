/*
 * http://www.primefaces.org/showcase/ui/ajax/poll.xhtml
 */
package org.primefaces.showcase.view.ajax;
 
import java.io.Serializable;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
 
@Named
@ViewScoped
public class CounterView implements Serializable {
  
  private int number;

  public int getNumber() {
    return number;
  }

  public void increment() {
    number++;
  }
}
 
