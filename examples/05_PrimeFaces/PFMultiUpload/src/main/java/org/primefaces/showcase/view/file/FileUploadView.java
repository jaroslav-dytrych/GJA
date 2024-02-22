/*
 * http://www.primefaces.org/showcase/ui/file/upload/multiple.xhtml
 */
package org.primefaces.showcase.view.file;
 
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
 
import org.primefaces.event.FileUploadEvent;
 
@Named
@RequestScoped
public class FileUploadView {
 
  public void handleFileUpload(FileUploadEvent event) {
    FacesMessage message = new FacesMessage("Succesful", 
                             event.getFile().getFileName() + " is uploaded.");
    FacesContext.getCurrentInstance().addMessage(null, message);
  }
}
