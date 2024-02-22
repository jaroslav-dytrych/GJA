/*
 * Adapted from https://www.primefaces.org/showcase/ui/multimedia/galleria.xhtml
 */

package org.primefaces.showcase.multimedia;
 
import java.util.ArrayList;
import java.util.List;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
 
@Named
@RequestScoped
public class ImagesView {
     
    private List<String> images;
     
    @PostConstruct
    public void init() {
        images = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            images.add("nature" + i + ".jpg");
        }
    }
 
    public List<String> getImages() {
        return images;
    }
}
