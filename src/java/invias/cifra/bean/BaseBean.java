/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.bean;

import invias.cifra.exception.CifraException;
import java.io.Serializable;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author jaflorez
 */
public class BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;
    protected static final Log log = LogFactory.getLog(BaseBean.class);

    /**
     * Creates a new instance of BaseBean
     */
    public BaseBean() {
    }

    protected void manageExceptionMessage(CifraException e) {
        StringBuilder messageCause = new StringBuilder(e.getTipo());
        if (e.getMessage() == null) {
            messageCause.append(". ").append(e.getCause().toString());
        } else {
            messageCause.append(". ").append(e.getCause());
        }
        addMessage("global.msg.error", messageCause.toString(), FacesMessage.SEVERITY_ERROR);
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void addMessage(String summary, String detail, Severity severity) {
        FacesMessage message = new FacesMessage(severity, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void addMessage(String summary, String detail, Severity severity, String clientId) {
        FacesMessage message = new FacesMessage(severity, summary, detail);
        FacesContext.getCurrentInstance().addMessage(clientId, message);
    }

    public void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void addMessagePage(String summary, String detail) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Flash flash = facesContext.getExternalContext().getFlash();
        flash.setKeepMessages(true);
        flash.setRedirect(true);
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail));

    }

    public Date getToday() {
        return new Date();
    }
}
