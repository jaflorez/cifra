/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.bean;
import invias.cifra.entity.Contrato;
import invias.cifra.entity.Reporte;
import invias.cifra.exception.CifraException;
import invias.cifra.model.ContratoSrv;
import invias.cifra.model.ReporteSrv;
import invias.cifra.util.CifraUtil;
import java.util.Date;
import java.util.List;
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
public class ReporteLstBean  extends BaseBean{

    /**
     * Creates a new instance of ReporteLstBean
     */
    private Contrato contrato;
    private List<Reporte>  reporteLst;
    private boolean disableNuevoReporte; 
    private Reporte curReporte;
    
    public ReporteLstBean() {
        CifraSessionBean sgbSes = CifraUtil.getSessionBean();
        if(sgbSes == null){
            CifraUtil.redirectRootOut();
        }
        try {
            Date fechaHoy = new Date();
            ReporteSrv reporteSrv = new ReporteSrv();
            ContratoSrv contratoSrv = new ContratoSrv();
            this.contrato = contratoSrv.loadContratoById(sgbSes.getContratoId());
            this.reporteLst = reporteSrv.consultarReporteContratoLst(sgbSes.getContratoId());
            this.disableNuevoReporte = false;
            if(this.reporteLst.size()>0){
                this.disableNuevoReporte = true;
            }
                
        }
        catch(CifraException ex){
        
        }
    }
    
    public void newReporte(){
        ReporteSrv srv = new ReporteSrv();
        try {
            CifraSessionBean sgbSes = CifraUtil.getSessionBean();
            srv.insertarNuevoReporte(contrato);
            this.reporteLst = srv.consultarReporteContratoLst(sgbSes.getContratoId());
        } catch (CifraException ex) {
            Logger.getLogger(ReporteLstBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public boolean isDisableNuevoReporte() {
        return disableNuevoReporte;
    }

    public void setDisableNuevoReporte(boolean disableNuevoReporte) {
        this.disableNuevoReporte = disableNuevoReporte;
    }

    public List<Reporte> getReporteLst() {
        return reporteLst;
    }

    public void setReporteLst(List<Reporte> reporteLst) {
        this.reporteLst = reporteLst;
    }

    public Reporte getCurReporte() {
        return curReporte;
    }

    public void setCurReporte(Reporte curReporte) {
        System.err.println("Asigno");
        this.curReporte = curReporte;
    }

    
    
}
