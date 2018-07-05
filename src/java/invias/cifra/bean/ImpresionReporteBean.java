/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.bean;

import invias.cifra.entity.RptReporte;
import invias.cifra.exception.CifraException;
import invias.cifra.model.RptSrv;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author jaflorez
 */
@ManagedBean
@ViewScoped
public class ImpresionReporteBean {

    /**
     * Creates a new instance of ImpresionReporteBean
     */
    private String nomFileReporte;
    public ImpresionReporteBean() {
        try {
            /*1357*/
            RptSrv  rptSrv = new RptSrv();
            RptReporte reporte =  rptSrv.getReporteById(1357);
            
        } catch (CifraException ex) {
            Logger.getLogger(ImpresionReporteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    public String getNomFileReporte() {
        return nomFileReporte;
    }

    public void setNomFileReporte(String nomFileReporte) {
        this.nomFileReporte = nomFileReporte;
    }
    
    
    
    
    
    
    
}
