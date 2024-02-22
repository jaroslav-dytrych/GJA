/*
 * http://www.primefaces.org/showcase/ui/menu/menu.xhtml
 */
package org.primefaces.showcase.view.menu;
 
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
 
@Named
@RequestScoped
public class MenuView {
     
  private MenuModel model;

  @PostConstruct
  public void init() {
    model = new DefaultMenuModel();

    //First submenu
    DefaultSubMenu firstSubmenu = DefaultSubMenu.builder().label("Dynamic Submenu").build();

    DefaultMenuItem item = DefaultMenuItem.builder().value("External").build();
    item.setUrl("http://www.primefaces.org");
    item.setIcon("pi pi-external-link");
    firstSubmenu.getElements().add(item);

    model.getElements().add(firstSubmenu);

    //Second submenu
    DefaultSubMenu secondSubmenu = DefaultSubMenu.builder().label("Dynamic Actions").build();

    item = DefaultMenuItem.builder().value("Save").build();
    item.setIcon("pi pi-save");
    item.setCommand("#{menuView.save}");
    item.setUpdate("messages");
    secondSubmenu.getElements().add(item);

    item = DefaultMenuItem.builder().value("Delete").build();
    item.setIcon("pi pi-trash");
    item.setCommand("#{menuView.delete}");
    item.setAjax(false);
    secondSubmenu.getElements().add(item);

    item = DefaultMenuItem.builder().value("Redirect").build();
    item.setIcon("pi pi-external-link");
    item.setCommand("#{menuView.redirect}");
    secondSubmenu.getElements().add(item);

    model.getElements().add(secondSubmenu);
  }

  public MenuModel getModel() {
    return model;
  }

  public void save() {
    addMessage("Success", "Data saved");
  }

  public void update() {
    addMessage("Success", "Data updated");
  }

  public void delete() {
    addMessage("Success", "Data deleted");
  }

  public void addMessage(String summary, String detail) {
    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
    FacesContext.getCurrentInstance().addMessage(null, message);
  }
}
