/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.bean;

import static invias.cifra.bean.BaseBean.log;
import invias.cifra.model.TablesSrv;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import invias.cifra.entity.Maquinaria;
import invias.cifra.exception.CifraException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;

/**
 *
 * @author jaflorez
 */
@ManagedBean
@ViewScoped
public class MaquinariaBean extends BaseBean{

    /**
     * Creates a new instance of MaquinariaBean
     */
    Maquinaria curMaquinaria;
    List<Maquinaria> maquinariaLst;
    public MaquinariaBean()  {
        this.loadMaquinariaLst();
    }

    private void loadMaquinariaLst() {
        TablesSrv tablesSrv = new TablesSrv();
        curMaquinaria = new Maquinaria();
        try {
            this.maquinariaLst = tablesSrv.getMaquinariaLst();
        } catch (CifraException ex) {
            Logger.getLogger(TablesBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void insertarMaquinaria() {
        try {
            TablesSrv tablesSrv = new TablesSrv();
            tablesSrv.insertarMaquinaria(curMaquinaria);
            this.maquinariaLst = tablesSrv.getMaquinariaLst();
        } catch (CifraException ex) {
            addMessage("Error insertando maquinaria", ex.getMessage(), FacesMessage.SEVERITY_ERROR);
            log.error(ex);
            Logger.getLogger(TablesBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void modificarMaquinaria() {
        try {
            TablesSrv tablesSrv = new TablesSrv();
            tablesSrv.modificarMaquinaria(curMaquinaria);
            this.maquinariaLst = tablesSrv.getMaquinariaLst();
        } catch (CifraException ex) {
            addMessage("Error Actualizando", ex.getMessage(), FacesMessage.SEVERITY_ERROR);
            log.error(ex);
            Logger.getLogger(TablesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void newMaquinaria(){
        this.curMaquinaria = new Maquinaria();
    }
    public void eliminarMaquinaria() {
        try {
            TablesSrv tablesSrv = new TablesSrv();
            tablesSrv.eliminarMaquinaria(curMaquinaria);
            this.maquinariaLst = tablesSrv.getMaquinariaLst();
        } catch (CifraException ex) {
            addMessage("Error Eliminando", ex.getMessage(), FacesMessage.SEVERITY_ERROR);
            log.error(ex);
            Logger.getLogger(TablesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Maquinaria getCurMaquinaria() {
        return curMaquinaria;
    }

    public void setCurMaquinaria(Maquinaria curMaquinaria) {
        this.curMaquinaria = curMaquinaria;
    }

    public List<Maquinaria> getMaquinariaLst() {
        return maquinariaLst;
    }

    public void setMaquinariaLst(List<Maquinaria> maquinariaLst) {
        this.maquinariaLst = maquinariaLst;
    }
    
    

}
