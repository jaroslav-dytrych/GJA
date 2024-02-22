/*
 * http://www.primefaces.org/showcase/ui/input/autoComplete.xhtml
 */
package org.primefaces.showcase.convert;
 
import jakarta.enterprise.inject.spi.CDI;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Named;
 
import org.primefaces.showcase.domain.Theme;
import org.primefaces.showcase.service.ThemeService;

@Named
@FacesConverter("themeConverter")
public class ThemeConverter implements Converter {
 
  @Override
  public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
    if (value != null && value.trim().length() > 0) {
      try {
        ThemeService service = CDI.current().select(ThemeService.class).get();
        if (service == null) {
          throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                                     "Conversion Error", "No service."));
        }
        return service.getThemes().get(Integer.parseInt(value));
      } catch (NumberFormatException e) {
        throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                                     "Conversion Error", "Not a valid theme."));
      }
    } else {
      return null;
    }
  }

  @Override
  public String getAsString(FacesContext fc, UIComponent uic, Object object) {
    if (object != null) {
      return String.valueOf(((Theme) object).getId());
    } else {
      return null;
    }
  }
}
