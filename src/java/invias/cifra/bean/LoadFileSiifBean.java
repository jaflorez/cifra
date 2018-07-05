/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.bean;

import invias.cifra.util.CifraUtil;
import invias.cifra.util.ReadSiifUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author jaflorez
 */
@ManagedBean
@ViewScoped
public class LoadFileSiifBean extends BaseBean {

    /**
     * Creates a new instance of LoadFileSiifBean
     */
    boolean fileUpload;
    String fileNameData;
    int numeroRegistros = 0;

    public LoadFileSiifBean() {
        this.fileUpload = false;
        this.fileNameData = "";
    }

    public void loadFileDb() {
        ReadSiifUtil siifUtil = new ReadSiifUtil();
        this.numeroRegistros = siifUtil.loadFile(this.fileNameData, "VIGENCIA");
    }

    public void proccesFile() {
        System.out.println("Proceso");

    }

    public void handleFileUpload(FileUploadEvent event) {
        Integer intTipoFile;
        String strTipo = (String) event.getComponent().getAttributes().get("tipo");
        try {
            copyFile(event.getFile().getInputstream());
        } catch (IOException e) {

        }
        this.addMessage("Proceso exitoso", "Cargo archivo " + event.getFile().getFileName());
        this.fileUpload = true;
    }

    public void copyFile( InputStream in) {
        try {
            // write the inputStream to a FileOutputStream
            this.fileNameData = CifraUtil.pathToWriteTmpFile() + "\\" + "fsiif_" + CifraUtil.getRamdomString(6)+".csv";
            System.out.println("Archivo" + this.fileNameData);
            OutputStream out = new FileOutputStream(this.fileNameData);
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            in.close();
            out.flush();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean isFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(boolean fileUpload) {
        this.fileUpload = fileUpload;
    }

    public int getNumeroRegistros() {
        return numeroRegistros;
    }

    public void setNumeroRegistros(int numeroRegistros) {
        this.numeroRegistros = numeroRegistros;
    }
    

}
