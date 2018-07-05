/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.bean;

import invias.cifra.entity.Usuario;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author jaflorez
 */
@ManagedBean
@SessionScoped
public class CifraSessionBean extends BaseBean{

    /**
     * Creates a new instance of CifraSessionBean
     */
    private Integer contratoId;
    private Integer reporteId;
    private Integer tramoId;
    private Integer usuarioId;
    private Usuario curUsuario;
    private boolean enEdicion;
    public CifraSessionBean() {
    }

    public Integer getContratoId() {
        return contratoId;
    }

    public void setContratoId(Integer contratoId) {
        this.contratoId = contratoId;
    }

    public Integer getReporteId() {
        return reporteId;
    }

    public void setReporteId(Integer reporteId) {
        this.reporteId = reporteId;
    }

    public Usuario getCurUsuario() {
        return curUsuario;
    }

    public void setCurUsuario(Usuario curUsuario) {
        this.curUsuario = curUsuario;
    }

    public boolean isEnEdicion() {
        return enEdicion;
    }

    public void setEnEdicion(boolean enEdicion) {
        this.enEdicion = enEdicion;
    }

    public Integer getTramoId() {
        return tramoId;
    }

    public void setTramoId(Integer tramoId) {
        this.tramoId = tramoId;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }
    
    
    
    
    
}
