/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.bean;

import invias.cifra.entity.CargaSiifDetail;
import invias.cifra.entity.CargaSiifHeader;
import invias.cifra.exception.CifraException;
import invias.cifra.model.SiifSrv;
import invias.cifra.util.CifraUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.OutputStream;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author jaflorez
 */
@ManagedBean
@ViewScoped
public class FileUploadViewSp {
    

    /**
     * Creates a new instance of FileUploadViewSp
     */
    private String fileName;
    private String pathFileName;
    CargaSiifHeader siifHeader;
    public FileUploadViewSp() {
        fileName = null;
        siifHeader =  new CargaSiifHeader();
        CifraSessionBean sgbSes = CifraUtil.getSessionBean();
        siifHeader.setFkUsuario(sgbSes.getCurUsuario().getIdentificador());
    }

    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage message = new FacesMessage("El archivo ", event.getFile().getFileName() + " ha sido cargado.");
        
        try {
            copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
            this.fileName = event.getFile().getFileName();
        } catch (IOException ex) {
            Logger.getLogger(FileUploadViewSp.class.getName()).log(Level.SEVERE, null, ex);
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    private void copyFile(String fileName, InputStream in) {
        try {
            // write the inputStream to a FileOutputStream
            String cadenaDir = CifraUtil.pathToResources();
            this.pathFileName = cadenaDir +"/files/" +fileName;
            OutputStream out = new FileOutputStream(new File(this.pathFileName));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            in.close();
            out.flush();
            out.close();
            
        } catch (IOException e) {
            Logger.getLogger(FileUploadViewSp.class.getName()).log(Level.SEVERE, null, e);
            
        }
    }
    
    

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public void processFile(){
        
        SiifSrv siifSrv = new SiifSrv();
        BufferedReader br = null;
        try {
            siifHeader.setFechaProceso(new Date());
            siifSrv.insertarHeader(siifHeader);
            String line = "";
            String cvsSplitBy = ";";
            br = new BufferedReader(new FileReader(this.pathFileName));
            
            line = br.readLine();
            
            while ((line = br.readLine()) != null) {
                // use comma as separator
                
                String[] data = line.split(cvsSplitBy);
                if(data.length == 4){
                    CargaSiifDetail csd = new CargaSiifDetail();
                    csd.setFkSiifHeader(siifHeader);
                    csd.setValorActual(CifraUtil.getNumberFromText(data[0]));
                    csd.setSaldoPorUtilizar(CifraUtil.getNumberFromText(data[1]));
                    csd.setObligado(CifraUtil.getNumberFromText(data[2]));
                    csd.setNumeroDocumentoSoporte(data[3].trim());
                    siifSrv.insertarDetail(csd);
                    
                }
            }
        } catch (CifraException ex) {
            Logger.getLogger(FileUploadViewSp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUploadViewSp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileUploadViewSp.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
             if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    Logger.getLogger(FileUploadViewSp.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    
    }

    public CargaSiifHeader getSiifHeader() {
        return siifHeader;
    }

    public void setSiifHeader(CargaSiifHeader siifHeader) {
        this.siifHeader = siifHeader;
    }
    
    

}
