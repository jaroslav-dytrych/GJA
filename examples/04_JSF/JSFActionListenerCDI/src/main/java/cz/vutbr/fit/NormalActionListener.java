package cz.vutbr.fit;

import jakarta.faces.context.FacesContext;
import jakarta.faces.event.AbortProcessingException;
import jakarta.faces.event.ActionEvent;
import jakarta.faces.event.ActionListener;
 
public class NormalActionListener implements ActionListener {

  @Override
  public void processAction(ActionEvent event) throws AbortProcessingException {

    FacesContext context = FacesContext.getCurrentInstance();
    NormalBean bean = (NormalBean) context.getApplication()
      .evaluateExpressionGet(context, "#{normal}", NormalBean.class);
    bean.setData(event.getComponent().getId());
  }
}
