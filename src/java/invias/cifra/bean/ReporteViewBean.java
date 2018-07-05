/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.bean;

import invias.cifra.entity.Contrato;
import invias.cifra.exception.CifraException;
import invias.cifra.model.ContratoSrv;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


/**
 *
 * @author jaflorez
 */
@ManagedBean
@ViewScoped
public class ReporteViewBean extends BaseBean{

    /**
     * Creates a new instance of ReporteViewBean
     */
    Contrato contrato;
    public ReporteViewBean() {
        String contrato_id;
        contrato_id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("contratoId");
        ContratoSrv contratoSrv = new ContratoSrv();
        try {
            this.contrato = contratoSrv.loadContratoById(Integer.parseInt(contrato_id));
        }
        catch(CifraException ex){
            
        }
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }
    
    
    
}
