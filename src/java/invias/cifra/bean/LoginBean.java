/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.bean;

import invias.cifra.entity.Usuario;
import invias.cifra.exception.CifraException;
import invias.cifra.model.UsuarioSrv;
import invias.cifra.util.CifraUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

/**
 *
 * @author jaflorez
 */
@ManagedBean
@RequestScoped
public class LoginBean extends BaseBean {

    /**
     * Creates a new instance of LoginBean
     */
    String documento;
    String clave;
    
    

    public LoginBean() {
        String code;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        code = (String) facesContext.getExternalContext().getRequestParameterMap().get("code");
        if(code != null){
        
        }

    }

    /**
     * Retorna la ruta para iniciar seccion
     */
    public String validarUsuario() {
        UsuarioSrv usuarioSrv = new UsuarioSrv();

        Usuario usuarioDb;
        try {
            usuarioDb = usuarioSrv.loadUsuarioByDocumento(this.documento);
        } catch (CifraException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            addMessage("Error en el proceso", ex.getMessage(), FacesMessage.SEVERITY_FATAL);
            return "login";
        }
        if (usuarioDb == null) {
            addMessage("El usuario no existe", "Verifique los datos", FacesMessage.SEVERITY_WARN);
            return "login";
        }
        if (usuarioDb.getContrasena() == null) {
            addMessage("El usuario no tiene contraseña", "Solicite cambio de contraseña", FacesMessage.SEVERITY_WARN);
            return "login";
        }

        if (usuarioDb.getContrasena().equals(CifraUtil.GetMD5(this.clave))) {
            CifraSessionBean sgbSes = CifraUtil.getSessionBean();
            sgbSes.setCurUsuario(usuarioDb);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            Flash flash = facesContext.getExternalContext().getFlash();
            flash.setKeepMessages(true);
            flash.setRedirect(true);
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "CIFRA", "Bienvenido"));
            return "/inicio/index?faces-redirect=true";
        } else {
            addMessage("Contraseña equivocada", "Error en los datos de ingreso", FacesMessage.SEVERITY_WARN);
            return "login";
        }
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
    

}
