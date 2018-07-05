/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.bean;

import invias.cifra.entity.RptConsolidado;
import invias.cifra.exception.CifraException;
import invias.cifra.model.RptSrv;
import invias.cifra.util.CifraUtil;
import invias.cifra.util.ExelUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author jaflorez
 */
@ManagedBean
@ViewScoped
public class RptBean extends BaseBean {

    /**
     * Creates a new instance of RptBean
     */
    private List<RptConsolidado> rptConsolidadoLst;
    private Date fechaReporte;

    private String rutaFile;
    private boolean disableDescarga;

    public RptBean() {
        this.fechaReporte = new Date();
        disableDescarga = true;
    }

    public void updateTable() {
        RptSrv rptSrv = new RptSrv();
        this.rutaFile = "";
        this.disableDescarga = true;
        try {

            CifraSessionBean sgbSes = CifraUtil.getSessionBean();
            if (sgbSes == null) {
                CifraUtil.redirectRootOut();
            }
            if (null != sgbSes.getCurUsuario()) {
            } else {
                CifraUtil.redirectRootOut();
            }

            int tipoUsr = sgbSes.getCurUsuario().getFkUnidadEjecutora();
            this.rptConsolidadoLst = rptSrv.reporteConsolidado(this.fechaReporte, tipoUsr);
            if(this.rptConsolidadoLst.size() >0){
                this.disableDescarga = false;
            }
        } catch (CifraException ex) {
            Logger.getLogger(RptBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String exportExcel(List<RptConsolidado> consolidadoLst) {
        String strRutaFile;
        try {
            ExelUtil eu = new ExelUtil();
            if (consolidadoLst != null) {
                strRutaFile = CifraUtil.pathToWriteTmpFile() + "\\" + CifraUtil.getNomFileFromDate() + ".xls";
                eu.createExcelPdf(consolidadoLst, strRutaFile);
                return strRutaFile;
            } else {
                this.addMessage("Error en el proceso", "Debe generar un reporte para exportar", FacesMessage.SEVERITY_WARN);
            }
        } catch (CifraException ex) {
            Logger.getLogger(RptBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";

    }

    public StreamedContent getFileDownload() throws Exception {
        this.rutaFile = "";
        if (this.rptConsolidadoLst != null) {
            this.rutaFile = this.exportExcel(this.rptConsolidadoLst);
            StreamedContent download = new DefaultStreamedContent();
            File file = new File(this.rutaFile);
            InputStream input = new FileInputStream(file);
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            download = new DefaultStreamedContent(input, externalContext.getMimeType(file.getName()), file.getName());
            return download;

        } else {
            
        }
        return null;

    }

    public List<RptConsolidado> getRptConsolidadoLst() {
        return rptConsolidadoLst;
    }

    public void setRptConsolidadoLst(List<RptConsolidado> rptConsolidadoLst) {
        this.rptConsolidadoLst = rptConsolidadoLst;
    }

    public Date getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(Date fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public boolean isDisableDescarga() {
        return disableDescarga;
    }

    public void setDisableDescarga(boolean disableDescarga) {
        this.disableDescarga = disableDescarga;
    }
    

}
