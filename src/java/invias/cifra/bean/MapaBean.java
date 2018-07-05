/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.bean;

import invias.cifra.entity.ContratoTramo;
import invias.cifra.exception.CifraException;
import invias.cifra.model.ContratoSrv;
import invias.cifra.util.CifraUtil;
import invias.cifra.util.ViasUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author jaflorez
 */
@ManagedBean
@ViewScoped
public class MapaBean extends BaseBean {

    /**
     * Creates a new instance of MapaBean
     */
    private List<SelectItem> vias;
    private ContratoTramo contratoTramo;
    Integer idContrato;
    Integer idTramo;

    @PostConstruct
    public void init() {
    }
    public MapaBean() {
        CifraSessionBean sgbSes = CifraUtil.getSessionBean();
        ViasUtil viasUtil = new ViasUtil();
        this.vias = viasUtil.getVias();
        ContratoSrv contratoSrv = new ContratoSrv();
        try {
            this.contratoTramo = contratoSrv.loadContratoTramoById(sgbSes.getTramoId());
            this.idTramo = sgbSes.getTramoId();
            this.idContrato = this.contratoTramo.getFkContrato().getIdentificador();
        } catch (CifraException ex) {
            Logger.getLogger(MapaBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void cancelarEdicion() {
        CifraUtil.redirectPage("contratos/contrato.xhtml");
    }

    public void actualizarTramo() {
        ContratoSrv contratoSrv = new ContratoSrv();
        try {
            contratoSrv.modificarContratoTramo(this.contratoTramo);
            this.addMessage("Informacion actualizada", "Se ha actualizado la información");
            CifraUtil.redirectPage("contratos/contrato.xhtml", "Tramo", "Se actualizo el tramo");
        } catch (CifraException ex) {
            Logger.getLogger(MapaBean.class.getName()).log(Level.SEVERE, null, ex);
            this.addMessage("Error en el proceso", "Error:" + ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void actualizarGeometriaTramo() {
        ContratoSrv contratoSrv = new ContratoSrv();
        try {
            contratoSrv.modificarContratoTramo(this.contratoTramo);
        } catch (CifraException ex) {
            Logger.getLogger(MapaBean.class.getName()).log(Level.SEVERE, null, ex);
            this.addMessage("Error en el proceso", "Error:" + ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public List<SelectItem> getVias() {
        return vias;
    }

    public void setVias(List<SelectItem> vias) {
        this.vias = vias;
    }

    public ContratoTramo getContratoTramo() {
        return contratoTramo;
    }

    public void setContratoTramo(ContratoTramo contratoTramo) {
        this.contratoTramo = contratoTramo;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(Integer idContrato) {
        this.idContrato = idContrato;
    }

    public Integer getIdTramo() {
        return idTramo;
    }

    public void setIdTramo(Integer idTramo) {
        this.idTramo = idTramo;
    }

}
