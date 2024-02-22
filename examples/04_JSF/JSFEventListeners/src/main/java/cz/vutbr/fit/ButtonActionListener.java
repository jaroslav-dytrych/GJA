package cz.vutbr.fit;

import jakarta.el.ELContext;
import jakarta.faces.application.Application;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.AbortProcessingException;
import jakarta.faces.event.ActionEvent;
import jakarta.faces.event.ActionListener;

/**
 *
 * Actionlistener for reset button
 */
public class ButtonActionListener implements ActionListener {

    /**
     * On button pressed, resets UserData bean countries to default
     *
     * @param event action event holding information about the button click
     * @throws AbortProcessingException
     */
    @Override
    public void processAction(ActionEvent event) throws AbortProcessingException {

        FacesContext context = FacesContext.getCurrentInstance();
        Application application = context.getApplication();
        ELContext elContext = context.getELContext();
        UserData userData = (UserData) application.getExpressionFactory()
                .createValueExpression(elContext, "#{userData}", UserData.class).getValue(elContext);

        userData.setSelectedCountry(UserData.DEFAULT_LOCALE);
        userData.setSecondCountry(UserData.DEFAULT_LOCALE);
    }
}
