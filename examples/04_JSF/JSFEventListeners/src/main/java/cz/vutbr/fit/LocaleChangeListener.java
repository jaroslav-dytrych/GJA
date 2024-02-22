/*
 * Adapted from http://www.tutorialspoint.com/jsf/jsf_valuechangelistener_tag.htm
 */
package cz.vutbr.fit;

import jakarta.el.ELContext;
import jakarta.faces.application.Application;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.AbortProcessingException;
import jakarta.faces.event.ValueChangeEvent;
import jakarta.faces.event.ValueChangeListener;

/**
 *
 * Used in h:selectOneMenu element in home.xhtml. On value change, this listener
 * is called.
 */
public class LocaleChangeListener implements ValueChangeListener {

    /**
     * On value change (Second country combobox), Get the new value and set it
     * to UserData bean.
     *
     * @param event value change event holding information about the change in
     * selectOneMenu
     * @throws AbortProcessingException
     */
    @Override
    public void processValueChange(ValueChangeEvent event) throws AbortProcessingException {

        // access country bean directly
        FacesContext context = FacesContext.getCurrentInstance();
        Application application = context.getApplication();
        ELContext elContext = context.getELContext();
        UserData userData = (UserData) application.getExpressionFactory()
                .createValueExpression(elContext, "#{userData}", UserData.class).getValue(elContext);

        // get the new value
        String newValue = event.getNewValue().toString();
        // set it to the userData bean 
        userData.setSecondCountry(newValue);
    }
}
