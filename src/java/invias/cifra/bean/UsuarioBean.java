/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.bean;

import invias.cifra.entity.UnidadEjecutora;
import invias.cifra.entity.Usuario;
import invias.cifra.exception.CifraException;
import invias.cifra.model.TablesSrv;
import invias.cifra.model.UsuarioSrv;
import invias.cifra.util.CifraUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author jaflorez
 */
@ManagedBean
@ViewScoped
public class UsuarioBean extends BaseBean {

    /**
     * Creates a new instance of UsuarioBean
     */
    private List<Usuario> usuarioLst;
    private Usuario curUsuario;
    private List<UnidadEjecutora> unidadEjecutoraLst;

    public UsuarioBean() {
        UsuarioSrv usuarioSrv = new UsuarioSrv();
        this.curUsuario = new Usuario();
        TablesSrv tablesSrv = new TablesSrv();
        try {
            this.unidadEjecutoraLst = tablesSrv.getUnidadEjecutoraLst();
            this.usuarioLst = usuarioSrv.getUsuarioLst();
        } catch (CifraException ex) {
            Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void insertarUsuario() {
        UsuarioSrv usuarioSrv = new UsuarioSrv();
        try {
            if (this.curUsuario.getIdentificador() != null) {
                this.curUsuario.setEstado("A");
                usuarioSrv.updateUsuario(curUsuario);
                addMessage("Grabado de datos", "Se guardaron los datos");
            }
            else{
                this.curUsuario.setEstado("A");
                usuarioSrv.insertarUsuario(this.curUsuario);
                addMessage("Grabado de datos", "Se guardaron los datos");
            }
            this.usuarioLst = usuarioSrv.getUsuarioLst();
        } catch (CifraException ex) {
            addMessage(ex.getMessage(), ex.getMessage(),FacesMessage.SEVERITY_FATAL);
            Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void nuevoUsuario() {
        this.curUsuario = new Usuario();
    }

    public String nombreTipo(int idTipo) {
        return CifraUtil.getNombreUsuario(idTipo);
    }

    public List<Usuario> getUsuarioLst() {
        return usuarioLst;
    }

    public void setUsuarioLst(List<Usuario> usuarioLst) {
        this.usuarioLst = usuarioLst;
    }

    public Usuario getCurUsuario() {
        return curUsuario;
    }

    public void setCurUsuario(Usuario curUsuario) {
        this.curUsuario = curUsuario;
    }

    public List<UnidadEjecutora> getUnidadEjecutoraLst() {
        return unidadEjecutoraLst;
    }

    public void setUnidadEjecutoraLst(List<UnidadEjecutora> unidadEjecutoraLst) {
        this.unidadEjecutoraLst = unidadEjecutoraLst;
    }

}
