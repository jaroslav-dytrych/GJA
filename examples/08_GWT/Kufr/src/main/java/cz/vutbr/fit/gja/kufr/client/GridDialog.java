package cz.vutbr.fit.gja.kufr.client;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.VerticalPanel;

public class GridDialog extends DialogBox {
  ArrayList<ResizeListener> listeners;

  public GridDialog() {
    listeners = new ArrayList<ResizeListener>();
    setText("Choose grid size");
    VerticalPanel panel = new VerticalPanel();
    RadioButton four = new RadioButton("group", "four");
    four.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        fireResized(4);
        hide();
      }
    });
    RadioButton six = new RadioButton("group", "six");
    six.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        fireResized(6);
        hide();

      }
    });
    RadioButton eight = new RadioButton("group", "eight");
    eight.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        fireResized(8);
        hide();
      }
    });
    panel.add(four);
    panel.add(six);
    panel.add(eight);
    panel.setWidth("100%");
    setWidget(panel);
  }

  public void addResizeListener(ResizeListener r) {
    listeners.add(r);
  }

  public void removeResizeListener(ResizeListener r) {
    listeners.remove(r);
  }

  public void fireResized(int r) {
    for (int i = 0; i < listeners.size(); i++) {
      listeners.get(i).resized(r);
    }
  }
}
