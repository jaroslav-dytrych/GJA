/*
 * http://www.primefaces.org/showcase/ui/file/upload/basic.xhtml
 */
package org.primefaces.showcase.view.file;
 
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
 
import org.primefaces.model.file.UploadedFile;
 
@Named
@RequestScoped
public class FileUploadView {
     
   private UploadedFile file;

  public UploadedFile getFile() {
    return file;
  }

  public void setFile(UploadedFile file) {
    this.file = file;
  }

  public void upload() {
    if (file != null) {
      FacesMessage message = new FacesMessage("Succesful", file.getFileName() 
                                              + " is uploaded.");
      FacesContext.getCurrentInstance().addMessage(null, message);
    }
  }
}
