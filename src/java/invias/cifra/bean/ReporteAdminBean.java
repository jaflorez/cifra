/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.bean;

import invias.cifra.entity.Reporte;
import invias.cifra.exception.CifraException;
import invias.cifra.model.ReporteSrv;
import invias.cifra.util.CifraUtil;
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
public class ReporteAdminBean extends BaseBean{

    /**
     * Creates a new instance of ReporteAdminBean
     */
    Reporte reporte;
    boolean esElUltimo;
    boolean habilitarGestor;
    boolean habilitarSupervisor;
    Integer curEstadoContrato;
    Integer curEstadoReporte;
    
    public ReporteAdminBean() {
        try {
            ReporteSrv reporteSrv = new ReporteSrv();
            CifraSessionBean sgbSes = CifraUtil.getSessionBean();
            this.reporte = reporteSrv.consultarReporte(sgbSes.getReporteId());
            this.curEstadoContrato = this.reporte.getFkEstadoContrato();
            this.curEstadoReporte = this.reporte.getFkEstado().getIdentificador();
            Integer ultimoReporte = reporteSrv.getLastIdReporteFromContrato(this.reporte.getFkContrato().getIdentificador());
            esElUltimo = this.reporte.getIdentificador().equals(ultimoReporte) ? true: false;
        } catch (CifraException ex) {
            Logger.getLogger(ReporteAdminBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public String actualizar(){
        
        try {
            ReporteSrv reporteSrv = new ReporteSrv();
            if(this.habilitarGestor){ /*Se habilitan las observaciones del gestor*/
                this.reporte.setGestorFechaRevision(null);
            }
            if(this.habilitarSupervisor){ /*Se habilitan las observaciones del supervisor*/
                this.reporte.setSupervisorFechaRevision(null);
            }
            Integer lastEstadoContrato = this.reporte.getFkEstadoContrato();
            this.reporte.getFkEstado().setIdentificador(curEstadoReporte);
            this.reporte.setFkEstadoContrato(curEstadoContrato);
            reporteSrv.actualizarReporte(reporte);
            if(!this.curEstadoContrato.equals(lastEstadoContrato)){ 
                /*Se actualiza el estado del reporte*/
                if(lastEstadoContrato == 1 && curEstadoContrato == 2  ){
                    /*Se debe bloquear el numero del reporte actual y queda igual al reporte anterior*/
                    this.reporte.setNumeroreporte(this.reporte.getNumeroreporte()-1);
                    reporteSrv.actualizarReporte(reporte);
                    List<Reporte>  reporteLst= reporteSrv.consultarReporteContratoLst(this.reporte.getFkContrato().getIdentificador());
                    /*Se actualizan los reportes susesivos*/
                    for(Reporte r:reporteLst){
                        if(r.getNumerosemana()> reporte.getNumerosemana() ){
                            r.setNumeroreporte(r.getNumeroreporte()-1);
                            reporteSrv.actualizarReporte(r);
                        }
                    }
                }
                if((lastEstadoContrato == 2 && curEstadoContrato == 1)   ){
                    this.reporte.setNumeroreporte(this.reporte.getNumeroreporte()+1);
                    reporteSrv.actualizarReporte(reporte);
                    List<Reporte>  reporteLst= reporteSrv.consultarReporteContratoLst(this.reporte.getFkContrato().getIdentificador());
                    /*Se actualizan los reportes susesivos*/
                    for(Reporte r:reporteLst){
                        if(r.getNumerosemana()> reporte.getNumerosemana() ){
                            r.setNumeroreporte(r.getNumeroreporte()+1);
                            reporteSrv.actualizarReporte(r);
                        }
                    }
                    
                    
                }
                
                
            }
            
            this.addMessagePage("CIFRA - Actualización", "Se actualizó el Registro");
            return "reportelst?faces-redirect=true";
        } catch (CifraException ex) {
            Logger.getLogger(ReporteAdminBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    public String eliminar(){
        try {
            ReporteSrv reporteSrv = new ReporteSrv();
            reporteSrv.eliminarReporte(reporte);
            this.addMessagePage("CIFRA", "Se eliminó el Registro");
            return "reportelst?faces-redirect=true";
        } catch (CifraException ex) {
            Logger.getLogger(ReporteAdminBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    public Reporte getReporte() {
        return reporte;
    }
    public void setReporte(Reporte reporte) {
        this.reporte = reporte;
    }

    public boolean isEsElUltimo() {
        return esElUltimo;
    }

    public void setEsElUltimo(boolean esElUltimo) {
        this.esElUltimo = esElUltimo;
    }

    public boolean isHabilitarGestor() {
        return habilitarGestor;
    }

    public void setHabilitarGestor(boolean habilitarGestor) {
        this.habilitarGestor = habilitarGestor;
    }

    public boolean isHabilitarSupervisor() {
        return habilitarSupervisor;
    }

    public void setHabilitarSupervisor(boolean habilitarSupervisor) {
        this.habilitarSupervisor = habilitarSupervisor;
    }

    public Integer getCurEstadoContrato() {
        return curEstadoContrato;
    }

    public void setCurEstadoContrato(Integer curEstadoContrato) {
        this.curEstadoContrato = curEstadoContrato;
    }

    public Integer getCurEstadoReporte() {
        return curEstadoReporte;
    }

    public void setCurEstadoReporte(Integer curEstadoReporte) {
        this.curEstadoReporte = curEstadoReporte;
    }
    
    
    
}
