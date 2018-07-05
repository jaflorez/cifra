/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.bean;

import invias.cifra.dot.FechaReporteDOT;
import invias.cifra.entity.Reporte;
import invias.cifra.exception.CifraException;
import invias.cifra.model.ContratoSiifSrv;
import invias.cifra.model.ReporteSrv;
import invias.cifra.util.CifraUtil;
import java.math.BigDecimal;
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
public class ContratoSiifBean {

    /**
     * Creates a new instance of ContratoSiifBean
     */
    private List<FechaReporteDOT> fechaReporteLst;
    private List<Reporte> resporteLst;
    private int fechaSelId;
    private final int unidaEjecutora;
    private Reporte curReporte;
    private BigDecimal valorObra;
    private BigDecimal valorInterventoria;
    private Date fechaSiif;
    public ContratoSiifBean() {
        CifraSessionBean sgbSes = CifraUtil.getSessionBean();
        unidaEjecutora = sgbSes.getCurUsuario().getFkUnidadEjecutora();
        try {
            this.cargarFechas();
        } catch (CifraException ex) {
            Logger.getLogger(ContratoSiifBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.fechaSelId = 0;
    }
    public void cargarFechas() throws CifraException {
        ContratoSiifSrv contratoSiifSrv = new ContratoSiifSrv();
        this.fechaReporteLst = contratoSiifSrv.listaFecha(true, unidaEjecutora);
    }

    public void loadReportes() {
        try {
            ReporteSrv reporteSrv = new ReporteSrv();
            Date fechaDesde = new Date();
            Date fechaHasta = new Date();
            for (FechaReporteDOT frdot : fechaReporteLst) {
                if (frdot.getIdentificador() == fechaSelId) {
                    fechaDesde = CifraUtil.parseDate(frdot.getFechaInicio(), "yyyy-MM-dd");
                    fechaHasta = CifraUtil.parseDate(frdot.getFechaFinal(), "yyyy-MM-dd");
                }
            }
            
            this.resporteLst = reporteSrv.consultarReporteSiifLst(fechaDesde, fechaHasta, unidaEjecutora);
        } catch (CifraException ex) {
            Logger.getLogger(ContratoSiifBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void actualizarSiif() {
        ReporteSrv reporteSrv = new ReporteSrv();
        try {
            if (this.curReporte == null) {
                
                return;
            }
            this.curReporte.setObrReporteSiif(this.valorObra);
            this.curReporte.setIntReporteSiif(this.valorInterventoria);
            this.curReporte.setSiifFecha(this.fechaSiif);
            reporteSrv.actualizarInformacionSiif(curReporte);
            this.loadReportes();
        } catch (CifraException ex) {
            Logger.getLogger(ContratoSiifBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    ///// Getter and setter
    public List<FechaReporteDOT> getFechaReporteLst() {
        return fechaReporteLst;
    }

    public void setFechaReporteLst(List<FechaReporteDOT> fechaReporteLst) {
        this.fechaReporteLst = fechaReporteLst;
    }

    public int getFechaSelId() {
        return fechaSelId;
    }

    public void setFechaSelId(int fechaSelId) {
        this.fechaSelId = fechaSelId;
    }


    public List<Reporte> getResporteLst() {
        return resporteLst;
    }

    public void setResporteLst(List<Reporte> resporteLst) {
        this.resporteLst = resporteLst;
    }

    public Reporte getCurReporte() {
        return curReporte;
    }

    public void setCurReporte(Reporte curReporte) {
        this.valorInterventoria = curReporte.getIntReporteSiif();
        this.valorObra = curReporte.getObrReporteSiif();
        this.fechaSiif = curReporte.getSiifFecha();
        this.curReporte = curReporte;
    }

    public BigDecimal getValorObra() {
        return valorObra;
    }

    public void setValorObra(BigDecimal valorObra) {
        this.valorObra = valorObra;
    }

    public BigDecimal getValorInterventoria() {
        return valorInterventoria;
    }

    public void setValorInterventoria(BigDecimal valorInterventoria) {
        this.valorInterventoria = valorInterventoria;
    }

    public Date getFechaSiif() {
        return fechaSiif;
    }

    public void setFechaSiif(Date fechaSiif) {
        this.fechaSiif = fechaSiif;
    }

}
