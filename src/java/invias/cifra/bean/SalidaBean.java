/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.bean;

import com.itextpdf.text.DocumentException;
import invias.cifra.dot.ReporteDOT;
import invias.cifra.exception.CifraException;
import invias.cifra.model.ReporteSrv;
import invias.cifra.util.CifraUtil;
import invias.cifra.util.PdfUtil;
import java.io.FileNotFoundException;
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
public class SalidaBean extends BaseBean{

    /**
     * Creates a new instance of SalidaBean
     */
    private String nomFileReporte;
    public SalidaBean() {
        CifraSessionBean sgbSes = CifraUtil.getSessionBean();
        ReporteSrv reporteSrv = new ReporteSrv();
        if (sgbSes.getReporteId() != null && sgbSes.getReporteId() > 0) {
            try {
                ReporteDOT reporteDot = reporteSrv.consultarReporteDOT(sgbSes.getReporteId());
                PdfUtil pdfUtil = new PdfUtil();
                String nomFile = sgbSes.getReporteId() + "_" +   CifraUtil.getRamdomString(6) +".pdf";
                pdfUtil.createFilePdf(reporteDot, CifraUtil.getRealPathResource() + "\\reports\\" + nomFile);
                this.nomFileReporte  = CifraUtil.getRequestURL() +"resources/reports/"+ nomFile;
                
            } catch (CifraException | DocumentException | FileNotFoundException ex) {
                Logger.getLogger(SalidaBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    public String getNomFileReporte() {
        return nomFileReporte;
    }

    public void setNomFileReporte(String nomFileReporte) {
        this.nomFileReporte = nomFileReporte;
    }
    
    

}
