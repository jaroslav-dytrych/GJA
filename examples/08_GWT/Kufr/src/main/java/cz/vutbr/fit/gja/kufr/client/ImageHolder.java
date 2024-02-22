package cz.vutbr.fit.gja.kufr.client;

import java.util.ArrayList;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ImageHolder extends VerticalPanel implements DeleteListener {

  ArrayList<DeleteListener> listeners;
  ArrayList<String> elements;

  public ImageHolder() {
    elements = new ArrayList<String>();
    listeners = new ArrayList<DeleteListener>();
  }

  public boolean addImage(String name) {
    for (String e : elements) {
      if (e.equals(name)) {
        Window.alert("Image of that name elready exists");
        return false;
      }
    }
    ImageElement newElement = new ImageElement(name);
    newElement.addListener(this);
    elements.add(name);
    add(newElement);
    newElement.setWidth("60%");
    return true;
  }

  void addListener(DeleteListener x) {
    listeners.add(x);
  }

  void fireDeleted(String d) {
    for (DeleteListener l : listeners) {
      l.elementDeleted(d);
    }
  }

  @Override
  public void elementDeleted(String d) {
    for (int i = 0; i < elements.size(); i++) {
      if (elements.get(i) == d) {
        elements.remove(i);
        fireDeleted(d);
        remove(i);
        break;
      }
    }
  }
}
