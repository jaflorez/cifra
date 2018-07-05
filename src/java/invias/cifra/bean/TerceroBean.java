/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.bean;

import invias.cifra.entity.Tercero;
import invias.cifra.exception.CifraException;
import invias.cifra.model.TerceroSrv;
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
public class TerceroBean extends BaseBean{

    /**
     * Creates a new instance of TerceroBean
     */
    private List<Tercero> terceroLst;
    private Tercero curTercero;
    
    public TerceroBean() {
        try {
            this.curTercero = new Tercero();
            TerceroSrv terceroSrv = new TerceroSrv();
            this.terceroLst = terceroSrv.getTereceroLst();
        } catch (CifraException ex) {
            Logger.getLogger(TerceroBean.class.getName()).log(Level.SEVERE, null, ex);
            addMessage("Error en el proceso", ex.getMessage(), FacesMessage.SEVERITY_FATAL);
        }
        
    }
    
    public void eliminarTercero(){
        try {
            TerceroSrv terceroSrv = new TerceroSrv();
            terceroSrv.eliminarTercero(curTercero);
            this.terceroLst = terceroSrv.getTereceroLst();
            this.curTercero = new Tercero();
            
        } catch (CifraException ex) {
            addMessage("Error Eliminando", ex.getMessage(), FacesMessage.SEVERITY_ERROR);
            log.error(ex);
            Logger.getLogger(TablesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    }
    public void insertarTercero(){
        try {
            TerceroSrv terceroSrv =  new TerceroSrv();
            terceroSrv.insertarTercero(this.curTercero);
            this.terceroLst = terceroSrv.getTereceroLst();
            this.curTercero = new Tercero();
        } catch (CifraException ex) {
            Logger.getLogger(TerceroBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void modificarTercero(){
        try {
            TerceroSrv terceroSrv =  new TerceroSrv();
            terceroSrv.modificarTercero(this.curTercero);
            this.terceroLst = terceroSrv.getTereceroLst();
            this.curTercero = new Tercero();
        } catch (CifraException ex) {
            Logger.getLogger(TerceroBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            
    public void newTercero(){
        this.curTercero = new Tercero();
    }
    
    public List<Tercero> getTerceroLst() {
        return terceroLst;
    }

    public void setTerceroLst(List<Tercero> terceroLst) {
        this.terceroLst = terceroLst;
    }

    public Tercero getCurTercero() {
        return curTercero;
    }

    public void setCurTercero(Tercero curTercero) {
        this.curTercero = curTercero;
    }
    
    
}
