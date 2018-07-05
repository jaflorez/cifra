/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.bean;

import invias.cifra.entity.Actividad;
import invias.cifra.entity.UnidadEjecutora;
import invias.cifra.exception.CifraException;
import invias.cifra.model.TablesSrv;
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
public class TablesBean extends BaseBean {

    /**
     * Creates a new instance of TablesBean
     */
    List<Actividad> actividadLst;
    Actividad curActividad;
    List<UnidadEjecutora> unidadEjecutorasLst;
    
    
    public TablesBean() {
        this.loadActividades();
    }
    public void loadActividades() {
        TablesSrv tablesSrv = new TablesSrv();
        curActividad = new Actividad();
        try {
            this.actividadLst = tablesSrv.getActividadLst();
        } catch (CifraException ex) {
            Logger.getLogger(TablesBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void insertarActividad() {
        try {
            TablesSrv tablesSrv = new TablesSrv();
            tablesSrv.insertarActividad(curActividad);
            this.actividadLst = tablesSrv.getActividadLst();
        } catch (CifraException ex) {
            addMessage("Error insertando", ex.getMessage(), FacesMessage.SEVERITY_ERROR);
            log.error(ex);
            Logger.getLogger(TablesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void modificarActividad() {
        try {
            TablesSrv tablesSrv = new TablesSrv();
            tablesSrv.modificarActividad(curActividad);
            this.actividadLst = tablesSrv.getActividadLst();
        } catch (CifraException ex) {
            addMessage("Error Actualizando", ex.getMessage(), FacesMessage.SEVERITY_ERROR);
            log.error(ex);
            Logger.getLogger(TablesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void newActividad() {
        this.curActividad = new Actividad();
    }
    public void eliminarActividad() {
        try {
            TablesSrv tablesSrv = new TablesSrv();
            tablesSrv.eliminarActividad(curActividad);
            this.actividadLst = tablesSrv.getActividadLst();
        } catch (CifraException ex) {
            addMessage("Error Eliminando", ex.getMessage(), FacesMessage.SEVERITY_ERROR);
            log.error(ex);
            Logger.getLogger(TablesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public List<Actividad> getActividadLst() {
        return actividadLst;
    }

    public void setActividadLst(List<Actividad> actividadLst) {
        this.actividadLst = actividadLst;
    }

    public Actividad getCurActividad() {
        return curActividad;
    }

    public void setCurActividad(Actividad curActividad) {
        this.curActividad = curActividad;

    }
}
