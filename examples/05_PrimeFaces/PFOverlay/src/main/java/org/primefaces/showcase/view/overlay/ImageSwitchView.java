/*
 * http://www.primefaces.org/showcase/ui/overlay/overlayPanel.xhtml
 */
package org.primefaces.showcase.view.overlay;
 
import java.util.ArrayList;
import java.util.List;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
 
@Named
@RequestScoped
public class ImageSwitchView {
     
   private List<String> images;

  @PostConstruct
  public void init() {
    images = new ArrayList<>();

    images.add("nature1.jpg");
    images.add("nature2.jpg");
    images.add("nature3.jpg");
    images.add("nature4.jpg");
  }

  public List<String> getImages() {
    return images;
  }
}
