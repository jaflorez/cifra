/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.bean;

import invias.cifra.entity.Proyecto;
import invias.cifra.model.TablesSrv;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import invias.cifra.exception.CifraException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;

/**
 *
 * @author jaflorez
 */
@ManagedBean
@ViewScoped
public class ProyectoBean extends BaseBean{

    /**
     * Creates a new instance of ProyectoBean
     */
    List<Proyecto> proyectoLst;
    Proyecto curProyecto;
    public ProyectoBean() {
        this.loadProyectoLst();
    }
    

    private void loadProyectoLst() {
        TablesSrv tablesSrv = new TablesSrv();
        curProyecto = new Proyecto();
        try {
            this.proyectoLst = tablesSrv.getProyectoLst();
        } catch (CifraException ex) {
            Logger.getLogger(TablesBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void insertarProyecto() {
        try {
            TablesSrv tablesSrv = new TablesSrv();
            tablesSrv.insertarProyecto(curProyecto);
            this.proyectoLst = tablesSrv.getProyectoLst();
        } catch (CifraException ex) {
            addMessage("Error insertando proyecto", ex.getMessage(), FacesMessage.SEVERITY_ERROR);
            log.error(ex);
            Logger.getLogger(TablesBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void modificarProyecto() {
        try {
            TablesSrv tablesSrv = new TablesSrv();
            tablesSrv.modificarProyecto(curProyecto);
            if(curProyecto==null ){
                addMessage("Error en el proceso", "No se ha seleccionado un proyecto", FacesMessage.SEVERITY_ERROR);
                return;
            }
            this.proyectoLst = tablesSrv.getProyectoLst();
        } catch (CifraException ex) {
            addMessage("Error Actualizando", ex.getMessage(), FacesMessage.SEVERITY_ERROR);
            log.error(ex);
            Logger.getLogger(TablesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void newProyecto(){
        this.curProyecto = new Proyecto();
    }
    public void eliminarProyecto() {
        try {
            TablesSrv tablesSrv = new TablesSrv();
            tablesSrv.eliminarProyecto(curProyecto);
            this.proyectoLst = tablesSrv.getProyectoLst();
        } catch (CifraException ex) {
            addMessage("Error Eliminando", ex.getMessage(), FacesMessage.SEVERITY_ERROR);
            log.error(ex);
            Logger.getLogger(TablesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Proyecto getCurProyecto() {
        return curProyecto;
    }

    public void setCurProyecto(Proyecto curProyecto) {
        this.curProyecto = curProyecto;
    }

    public List<Proyecto> getProyectoLst() {
        return proyectoLst;
    }

    public void setProyectoLst(List<Proyecto> proyectoLst) {
        this.proyectoLst = proyectoLst;
    }
    
    
}
