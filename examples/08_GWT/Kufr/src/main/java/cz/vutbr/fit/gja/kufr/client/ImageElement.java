package cz.vutbr.fit.gja.kufr.client;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public class ImageElement extends HorizontalPanel {

  private String name;
  private Label label;
  ArrayList<DeleteListener> listeners;

  public ImageElement(String n) {
    listeners = new ArrayList<DeleteListener>();
    name = n;

    label = new Label(name);
    add(label);
    Button x = new Button("X", new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        fireDeleted();
      }
    });
    add(x);
    setCellHorizontalAlignment(x, HasAlignment.ALIGN_CENTER);

  }

  public void addListener(DeleteListener l) {
    listeners.add(l);
  }

  void fireDeleted() {
    for (int i = 0; i < listeners.size(); i++) {
      listeners.get(i).elementDeleted(name);
    }
  }

}
