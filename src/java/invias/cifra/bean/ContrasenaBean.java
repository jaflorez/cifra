/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.bean;

import invias.cifra.email.DatosCorreo;
import invias.cifra.email.Mail365;
import invias.cifra.entity.Usuario;
import invias.cifra.exception.CifraException;
import invias.cifra.model.UsuarioSrv;
import invias.cifra.util.CifraUtil;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

/**
 *
 * @author jaflorez
 */
@ManagedBean
@ViewScoped
public class ContrasenaBean extends BaseBean {

    /**
     * Creates a new instance of ContrasenaBean
     */
    String documento;
    String clave;
    String claveOk;
    Usuario usuario;

    public ContrasenaBean() {
        String code = null;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        code = (String) facesContext.getExternalContext().getRequestParameterMap().get("code");
        if (code != null) {
            UsuarioSrv usuarioSrv = new UsuarioSrv();
            try {
                this.usuario = usuarioSrv.loadUsuarioByCadenaVerificacion(code);
                if (this.usuario == null) {
                    addMessage("Error en el proceo", "Se presento un error", FacesMessage.SEVERITY_FATAL);
                }

            } catch (CifraException ex) {
                Logger.getLogger(ContrasenaBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public String solicitarCambioContrasena() {
        UsuarioSrv usuarioSrv = new UsuarioSrv();
        Usuario usuarioDb;
        try {
            usuarioDb = usuarioSrv.loadUsuarioByDocumento(this.documento);
        } catch (CifraException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            addMessage("Error en el proceso", ex.getMessage(), FacesMessage.SEVERITY_FATAL);
            return "solicambiocontrasena";
        }
        if (usuarioDb == null) {
            addMessage("Error en el proceso", "El usuario no existe, verifique el  número de documento", FacesMessage.SEVERITY_WARN);
            return "solicambiocontrasena";
        }
        usuarioDb.setCadenaverificacion(CifraUtil.getRamdomString(25));

        try {
            usuarioSrv.updateUsuario(usuarioDb);
            this.sendMailCambioContrasena(usuarioDb);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            Flash flash = facesContext.getExternalContext().getFlash();
            flash.setKeepMessages(true);
            flash.setRedirect(true);
            String msg = "Señor usuario se ha enviado un vinculo a su cuenta de correo, por favor consultela para continuar con el proceso ";
            this.addMessage("Proceso realizado con exito", msg, FacesMessage.SEVERITY_INFO);
            return "solicambiocontrasena";
        } catch (CifraException ex) {
            Logger.getLogger(ContrasenaBean.class.getName()).log(Level.SEVERE, null, ex);
            addMessage("Error en el proceso", ex.getMessage(), FacesMessage.SEVERITY_FATAL);
            return "solicambiocontrasena";
        }
    }

    private void sendMailCambioContrasena(Usuario usuario) throws CifraException {
        DatosCorreo dat = new DatosCorreo();
        ArrayList arr = new ArrayList();
        String mensaje;
        arr.add(usuario.getEmail());
        dat.setAsunto("CIFRA - INVIAS: Cambio de contrasena");
        mensaje = "Ha solicitado que se restablezca su contraseña. Haga clic en el siguiente enlace. <br> Le llevará a una página web en la que podrá cambiar la contraseña. <br><br>";
        mensaje += "<a href = " + CifraUtil.getRequestURL() + "faces/out/cambiarcontrasena.xhtml?code=" + usuario.getCadenaverificacion() + ">Restaurar contrasena</a>";
        mensaje += "<br><br>";
        mensaje += "Este enlace se puede usar solo una vez y tiene viegencia de un dia <br><br>";
        dat.setMensaje(mensaje);
        dat.setDestinatario(arr);
        try {
            Mail365 mail365 = new Mail365();
            mail365.sendMail(dat);
        } catch (Exception ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            throw new CifraException("Error en el proceso:" + ex.getMessage());
        }

    }

    public String cambiarContrasena() {
        this.usuario.setContrasena(CifraUtil.GetMD5(this.clave));
        UsuarioSrv usuarioSrv = new UsuarioSrv();
        try {
            usuarioSrv.updateUsuario(usuario);
        } catch (CifraException ex) {
            Logger.getLogger(ContrasenaBean.class.getName()).log(Level.SEVERE, null, ex);
            addMessage("Error en el proceso", ex.getMessage(), FacesMessage.SEVERITY_FATAL);
            return "cambiarcontrasena";
        }

        FacesContext facesContext = FacesContext.getCurrentInstance();
        Flash flash = facesContext.getExternalContext().getFlash();
        flash.setKeepMessages(true);
        flash.setRedirect(true);
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Proceso exitoso", "Se ha realizado el cambio de contrasena"));
        return "inicio";

    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getClaveOk() {
        return claveOk;
    }

    public void setClaveOk(String claveOk) {
        this.claveOk = claveOk;
    }

}
