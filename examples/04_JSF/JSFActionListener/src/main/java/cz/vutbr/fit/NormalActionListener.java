package cz.vutbr.fit;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
 
public class NormalActionListener implements ActionListener {

  @Override
  public void processAction(ActionEvent event) throws AbortProcessingException {

    NormalBean bean = (NormalBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("normal");
    bean.setData(event.getComponent().getId());
  }
}
