/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.bean;

import invias.cifra.entity.Contrato;
import invias.cifra.entity.ContratoActividad;
import invias.cifra.entity.ContratoFinancieroInter;
import invias.cifra.entity.ContratoFinancieroObra;
import invias.cifra.entity.ContratoMaquinaria;
import invias.cifra.entity.ContratoTramo;
import invias.cifra.entity.Reporte;
import invias.cifra.entity.ReporteArchivo;
import invias.cifra.entity.ReporteMateriales;
import invias.cifra.exception.CifraException;
import invias.cifra.model.ContratoSrv;
import invias.cifra.dot.RegistroAvanceDOT;
import invias.cifra.dot.ReporteTableDOT;
import invias.cifra.email.DatosCorreo;
import invias.cifra.email.Mail365;
import invias.cifra.entity.Estado;
import invias.cifra.model.ReporteSrv;
import invias.cifra.util.CifraUtil;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 * Clase bean para manejo del reporte
 *
 * @author jaflorez
 * @version 1.0
 *
 */
@ManagedBean
@ViewScoped
public class ReporteBean extends BaseBean {

    //Declaración de atributos privados
    private Contrato contrato;
    private Reporte reporte;
    private List<RegistroAvanceDOT> registroActividadLst;
    private List<RegistroAvanceDOT> registroMaquinariaLst;
    private List<ContratoTramo> contratoTramoLst;
    private List<ContratoActividad> contratoActividadLst;
    private List<ContratoMaquinaria> contratoMaquinariaLst;
    private List<ContratoFinancieroInter> contratoFinancieroInterLst;
    private List<ContratoFinancieroObra> contratoFinancieroObraLst;

    private Date curDate;
    private ReporteMateriales curReporteMateriales;
    private boolean enEdicion;
    private boolean[] panelOk = new boolean[11];
    private boolean[] panelFocus = new boolean[11];

    private ContratoFinancieroObra curFinancieroObra;
    private ContratoFinancieroInter curFinancieroInter;

    private List<ReporteArchivo> reporteFotoLst;
    private String nomFileImageAvance;
    private ReporteArchivo imageAvance;

    private Boolean disabledGuardarReporteBase;
    private ReporteTableDOT rtdot;
    private boolean isAvanceFisicoTables;
    private String nomFileImageFirma;
    private boolean aceptarReporte;
    private String validarValorFactura;

    /**
     * Constructor de la clase
     */
    public ReporteBean() {
        isAvanceFisicoTables = false;
        this.disabledGuardarReporteBase = true;
        this.aceptarReporte = false;
        this.curDate = new Date();
        CifraSessionBean sgbSes = CifraUtil.getSessionBean();
        //Determinar si esta en edicion
        this.enEdicion = sgbSes.isEnEdicion();
        this.panelFocus[0] = true;
        for (int i = 1; i <= 10; i++) {
            this.panelOk[i] = false;
        }
        panelOk[0] = true;
        ContratoSrv contratoSrv = new ContratoSrv();
        reporte = new Reporte();
        reporte.setFkContrato(this.contrato);
        reporte.setFechaRegistroInicial(curDate);
        reporte.setFechaPresentacionReporte(curDate);
        reporte.setObrPresentoFactura(false);
        reporte.setIntPresentoFactura(false);

        this.curReporteMateriales = new ReporteMateriales();
        this.curFinancieroObra = new ContratoFinancieroObra();
        try {
            this.contrato = contratoSrv.loadContratoById(sgbSes.getContratoId());
            this.contratoTramoLst = contratoSrv.buscarContratoTramo(sgbSes.getContratoId());
            this.contratoActividadLst = contratoSrv.buscarContratoActividad(sgbSes.getContratoId());
            this.contratoMaquinariaLst = contratoSrv.buscarContratoMaquinaria(sgbSes.getContratoId());
            this.contratoFinancieroInterLst = contratoSrv.buscarContratoFinancieroInter(sgbSes.getContratoId());
            this.contratoFinancieroObraLst = contratoSrv.buscarContratoFinancieroObra(sgbSes.getContratoId());

            ReporteSrv reporteSrv = new ReporteSrv();
            if (sgbSes.getReporteId() != null && sgbSes.getReporteId() > 0) {
                this.reporte = reporteSrv.consultarReporte(sgbSes.getReporteId());
                if (this.reporte.getObrPresentoFactura() == null) {
                    this.reporte.setObrPresentoFactura(false);
                    this.reporte.setIntPresentoFactura(false);
                }
            }
            this.updateStatePanel();
        } catch (CifraException ex) {
            Logger.getLogger(ReporteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertar() {
        try {
            ReporteSrv reporteSrv = new ReporteSrv();
            this.reporte.setFkContrato(this.contrato);
            this.reporte.setFkEstado(new Estado(1));
            this.reporte.setFkEstadoContrato(1);
            reporteSrv.insertarReporte(this.reporte);
            reporteSrv.fillReportes(this.contrato.getIdentificador(), new Date());
            addMessage("Accion exitosa", "Se ha registrado el reporte", FacesMessage.SEVERITY_INFO);
            CifraUtil.redirectPage("contratos/reportelst.xhtml");
        } catch (CifraException ex) {
            Logger.getLogger(ReporteBean.class.getName()).log(Level.SEVERE, null, ex);
            addMessage("Error en el proceo", ex.getMessage(), FacesMessage.SEVERITY_FATAL);
        }

    }

    /**
     * Carga el archivo de firma
     *
     * @param event Evento enviado desde el componente de cargar archivos
     */
    public void handleFileUploadFirma(FileUploadEvent event) {
        UploadedFile uFile = event.getFile();
        ReporteArchivo reporteArchivo = new ReporteArchivo();
        reporteArchivo.setFkReporte(this.reporte);
        String extFile = CifraUtil.getExtension(CifraUtil.getExtension(uFile.getFileName()));
        reporteArchivo.setExtension(extFile);
        reporteArchivo.setArchivo(event.getFile().getContents());
        reporteArchivo.setSize((int) event.getFile().getSize());
        reporteArchivo.setTipoArchivo(2);
        reporteArchivo.setNombre("esquema");

        reporteArchivo.setFkReporte(reporte);
        reporteArchivo.setIdentificador(CifraUtil.getRandomNumberInRange(100, 1000000));
        ReporteSrv reporteSrv = new ReporteSrv();
        try {
            reporteSrv.actualizarArchivoFirma(reporte, reporteArchivo);
        } catch (CifraException ex) {
            Logger.getLogger(ReporteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        File uploadedFile;

        String nomFile = "firma_" + CifraUtil.getRamdomString(10) + "." + CifraUtil.getExtension(uFile.getFileName());
        String tempDir = CifraUtil.getRealPathResource() + "\\tmp\\";
        uploadedFile = new File(tempDir, nomFile);
        if (!uploadedFile.exists()) {
            try {
                uploadedFile.createNewFile();
                this.nomFileImageFirma = nomFile;
            } catch (IOException ex) {
                Logger.getLogger(ReporteBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            Files.copy(uFile.getInputstream(), uploadedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            Logger.getLogger(ReporteBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Funcion para crear las mateices para captura los avances en actividad y
     * maquinaria
     */
    public void updateImages() {
        if (this.reporte.getReporteArchivoList() == null) {
            this.reporte.setReporteArchivoList(new ArrayList<>());
        }
        Iterator<ReporteArchivo> iterator;
        iterator = this.reporte.getReporteArchivoList().iterator();

        //Se eliminan los archivos de imagenes y avance
        while (iterator.hasNext()) {
            ReporteArchivo ra = iterator.next();
            if (ra.getTipoArchivo().equals(1) || ra.getTipoArchivo().equals(0)) {
                iterator.remove();
            }
        }
        //Se insertar los nuevos archivos
        iterator = this.reporteFotoLst.iterator();
        while (iterator.hasNext()) {
            ReporteArchivo ra = iterator.next();
            ReporteArchivo ra1 = new ReporteArchivo();
            ra1.setExtension(ra.getExtension());
            ra1.setFkReporte(reporte);
            ra1.setNombre(ra.getNombre());
            ra1.setArchivo(ra.getArchivo());
            ra1.setTipoArchivo(ra.getTipoArchivo());
            ra1.setSize(ra.getSize());
            this.reporte.getReporteArchivoList().add(ra1);
        }

        if (this.imageAvance != null) {
            this.reporte.getReporteArchivoList().add(this.imageAvance);
        }

        ReporteSrv reporteSrv = new ReporteSrv();
        try {
            reporteSrv.actualizarReporte(reporte);
            Integer temp = reporte.getIdentificador();
            this.reporte = new Reporte();
            this.reporte = reporteSrv.consultarReporte(temp);
            this.onFocusPanel(10);
            this.updateStatePanel();
        } catch (CifraException ex) {
            Logger.getLogger(ReporteBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void cancelUpdateImages() {
        this.isAvanceFisicoTables = false;
        try {
            ReporteSrv reporteSrv = new ReporteSrv();
            this.reporte = reporteSrv.consultarReporte(reporte.getIdentificador());
            this.onFocusPanel(10);
            this.updateStatePanel();
        } catch (CifraException ex) {
            Logger.getLogger(ReporteBean.class.getName()).log(Level.SEVERE, null, ex);
            addMessage("Error en el proceso", ex.getMessage(), FacesMessage.SEVERITY_FATAL);
        }
    }

    public void onFocusPanel(int curPanel) {
        for (int i = 0; i < 10; i++) {
            this.panelFocus[i] = false;
        }
        if (curPanel > 0 && curPanel < 10) {
            this.panelFocus[curPanel] = true;
            this.panelFocus[9] = true;
        } else {
            this.panelFocus[0] = true;
            this.panelFocus[10] = true;
        }
        //Si se carga el formularion para imagenes
        if (curPanel == 6) {
            this.cargarImagenesFromReporteToArray(true);

        }

        //Si se carga el formulario para firma del interventor
        if (curPanel == 7) {
            this.copiarFirmaFromReporteToFile(reporte);
        }

        if (curPanel == 2) {
            this.loadTramoActividadMaquinaria();
            isAvanceFisicoTables = true;
        }
    }

    public void actualizarReporteEstado() {
        ReporteSrv reporteSrv = new ReporteSrv();
        try {
            reporteSrv.actualizarEstadoReporte(this.reporte.getIdentificador(), this.reporte.getFkEstado().getIdentificador());
            CifraUtil.redirectPage("contratos/reportelst.xhtml");
        } catch (CifraException ex) {
            Logger.getLogger(ReporteBean.class.getName()).log(Level.SEVERE, null, ex);
            this.addMessage("Error en el proceso", ex.getMessage(), FacesMessage.SEVERITY_FATAL);
        }
    }

    public void actualizarReporteRevisadoGestor() {
        ReporteSrv reporteSrv = new ReporteSrv();
        try {
            reporteSrv.actualizarRevision(this.reporte.getIdentificador(), reporte.getGestorObservaciones(), CifraUtil.getSessionBean().getCurUsuario().getIdentificador(), CifraUtil.getSessionBean().getCurUsuario().getTipo());
            CifraUtil.redirectPage("contratos/reportelst.xhtml");
        } catch (CifraException ex) {
            Logger.getLogger(ReporteBean.class.getName()).log(Level.SEVERE, null, ex);
            this.addMessage("Error en el proceso", ex.getMessage(), FacesMessage.SEVERITY_FATAL);
        }

    }

    public void actualizarReporteRevisadoSupervisor() {
        ReporteSrv reporteSrv = new ReporteSrv();
        try {
            reporteSrv.actualizarRevision(this.reporte.getIdentificador(), reporte.getSupervisorObservaciones(), CifraUtil.getSessionBean().getCurUsuario().getIdentificador(), CifraUtil.getSessionBean().getCurUsuario().getTipo());
            CifraUtil.redirectPage("contratos/reportelst.xhtml");
        } catch (CifraException ex) {
            Logger.getLogger(ReporteBean.class.getName()).log(Level.SEVERE, null, ex);
            this.addMessage("Error en el proceso", ex.getMessage(), FacesMessage.SEVERITY_FATAL);
        }

    }

    public void actualizarReporte() {
        try {
            ReporteSrv reporteSrv = new ReporteSrv();
            if (isAvanceFisicoTables) {
                this.reporte.setReporteActividadList(rtdot.loadReporteActividadLst());
                this.reporte.setReporteMaquinariaList(rtdot.loadReporteMaquinariaLst());
                isAvanceFisicoTables = false;
            }

            this.reporte = reporteSrv.actualizarReporte(reporte);
            this.onFocusPanel(10);
            this.updateStatePanel();
        } catch (CifraException ex) {
            Logger.getLogger(ReporteBean.class.getName()).log(Level.SEVERE, null, ex);
            addMessage("Error en el proceso", ex.getMessage(), FacesMessage.SEVERITY_FATAL);
        }
    }

    private void updateStatePanel() {
        this.panelOk[1] = this.reporte.getObrFechaFactura() != null;
        this.panelOk[2] = this.reporte.getObrAvanceFisico() != null;
        this.panelOk[3] = this.reporte.getAmbBotaderos() != null;
        this.panelOk[4] = this.reporte.getGeprAvaluos() != null;
        this.panelOk[5] = this.reporte.getActividadRealizada() != null;
        this.panelOk[6] = this.validarSoporteGrafico();
        this.panelOk[7] = this.reporte.getNombreFirma() != null;

    }

    private boolean validarSoporteGrafico() {
        if (this.reporte.getReporteArchivoList() == null) {
            return false;
        }

        int contadorFotos = 0;
        int contadorAvance = 0;
        try {
            for (ReporteArchivo ra : this.reporte.getReporteArchivoList()) {
                if (ra.getTipoArchivo() == 0) {
                    contadorFotos++;
                }
                if (ra.getTipoArchivo() == 1) {
                    contadorAvance++;
                }
            }
        } catch (NullPointerException ex) {
            Logger.getLogger(ReporteBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        if (contadorFotos == 3 && contadorAvance == 1) {
            return true;
        }
        return false;

    }

    public void actualizarTerminarReporte() {
        if (this.panelOk[1] && this.panelOk[3] && this.panelOk[4] && this.panelOk[5] && this.panelOk[6]) {
            ReporteSrv reporteSrv = new ReporteSrv();
            try {
                reporteSrv.actualizarEstadoReporte(this.reporte.getIdentificador(), 2);
                this.enviarCorreoFinReporte(this.reporte);
                /*Enviar correo fin reporte*/

                CifraUtil.redirectPage("contratos/reportelst.xhtml");
            } catch (CifraException ex) {
                Logger.getLogger(ReporteBean.class.getName()).log(Level.SEVERE, null, ex);
                this.addMessage("Error en el proceso", ex.getMessage(), FacesMessage.SEVERITY_FATAL);
            }
        } else {
            this.addMessage("Accion no permitida", "No se ha registrado toda la información de reporte", FacesMessage.SEVERITY_WARN);
        }
    }

    /**
     * Enviar un correo al supervisor y gestor informando que el reporte esta
     * listo
     */
    private void enviarCorreoFinReporte(Reporte reporte) {
        DatosCorreo dat = new DatosCorreo();
        ArrayList arr = new ArrayList();
        String mensaje;
        arr.add(reporte.getFkContrato().getFkUsuarioGestor().getEmail());
        arr.add(reporte.getFkContrato().getFkUsuarioSupervisor().getEmail());
        dat.setAsunto("CIFRA - INVIAS: Reporte  disponible del contrato " + reporte.getFkContrato().getContObraCodigo());
        mensaje = "Se ha finalizado el reporte " + reporte.getNumeroreporte() + " del contrato " + reporte.getFkContrato().getContObraCodigo() + " el cual queda disponible para su revisión.  <br><br>";
        mensaje += "<br><br>";
        dat.setMensaje(mensaje);
        dat.setDestinatario(arr);
        try {
            Mail365 mail365 = new Mail365();
            mail365.sendMail(dat);
        } catch (Exception ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            throw new SecurityException("Error en el proceso");
        }

    }

    public boolean dibujarColumna(int columna) {
        return this.rtdot.dibujarColumna(columna);
    }

    public String tituloColumna(int columna) {
        return this.rtdot.getTituloTramo(columna, "Tr:");
    }

    public void adicionarMateriales() {
        this.curReporteMateriales.setFkReporte(this.reporte);
        if (this.reporte.getReporteMaterialesList() == null) {
            this.reporte.setReporteMaterialesList(new ArrayList<ReporteMateriales>());
        }
        this.reporte.getReporteMaterialesList().add(curReporteMateriales);
        this.curReporteMateriales = new ReporteMateriales();
    }

    public void removeMateriales(ReporteMateriales rm) {
        if (this.reporte.getReporteMaterialesList() == null) {
            addMessage("Error en el proceso", "Lista vacia", FacesMessage.SEVERITY_ERROR);
            return;
        }
        for (ReporteMateriales rpM : this.reporte.getReporteMaterialesList()) {
            if (rpM.getUbicacion().equals(rm.getUbicacion())) {
                this.reporte.getReporteMaterialesList().remove(rpM);
                addMessage("Proceso exitoso", "Se elimino el registro", FacesMessage.SEVERITY_INFO);
                return;
            }
        }
    }

    public void calcularFechas() {
        Calendar c = Calendar.getInstance();
        c.setTime(this.reporte.getReportedesde());
        this.disabledGuardarReporteBase = true;
        if (c.get(Calendar.DAY_OF_WEEK) == 6) { // Se verifica que el dia
            this.disabledGuardarReporteBase = false;
        } else {
            addMessage("Error en el proceso", "La fecha de inicio del reporte es el dia viernes", FacesMessage.SEVERITY_ERROR);
        }
        this.reporte.setReportehasta(CifraUtil.fechaMasDias(this.reporte.getReportedesde(), 6));
        this.reporte.setFechaLimitePresentacion(CifraUtil.fechaMasDias(this.reporte.getReportedesde(), 6));
    }

    /* Se elimina, la insercion de reportes se hace con un procedimiento
    public void insertar() {
        try {
            ReporteSrv reporteSrv = new ReporteSrv();
            this.reporte.setFkContrato(this.contrato);
            this.reporte.setFkEstado(new Estado(1));
            reporteSrv.insertarReporte(this.reporte);
            reporteSrv.fillReportes(this.contrato.getIdentificador(), new Date());
            addMessage("Accion exitosa", "Se ha registrado el reporte", FacesMessage.SEVERITY_INFO);
            CifraUtil.redirectPage("contratos/reportelst.xhtml");
        } catch (CifraException ex) {
            Logger.getLogger(ReporteBean.class.getName()).log(Level.SEVERE, null, ex);
            addMessage("Error en el proceo", ex.getMessage(), FacesMessage.SEVERITY_FATAL);
        }
    }
     */
    public String labelFechaFactura(int tipo) {
        String retorno = "Fecha?";
        if (tipo == 1) {
            if (this.reporte.getObrPresentoFactura() == null) {
                this.reporte.setObrPresentoFactura(false);
            }
            if (this.reporte.getObrPresentoFactura()) {
                retorno = "Fecha";
            } else {
                retorno = "Fecha estimada de presentación";
            }
        }
        if (tipo == 2) {
            if (this.reporte.getIntPresentoFactura() == null) {
                this.reporte.setIntPresentoFactura(false);
            }
            if (this.reporte.getIntPresentoFactura()) {
                retorno = "Fecha";
            } else {
                retorno = "Fecha estimada de presentación";
            }
        }
        return retorno;
    }

    public String labelValorFactura(int tipo) {
        String retorno = "Valor?";
        if (tipo == 1) {
            if (this.reporte.getObrPresentoFactura()) {
                retorno = "Valor basico de obra segun factura";
            } else {
                retorno = "Valor estimado de factura";
            }
        }
        if (tipo == 2) {
            if (this.reporte.getIntPresentoFactura()) {
                retorno = "Valor total facturado";
            } else {
                retorno = "Valor estimado de factura";
            }
        }
        return retorno;
    }

    public void handleFileUploadFoto(FileUploadEvent event) {
        String fileName;
        fileName = event.getFile().getFileName();
        ReporteArchivo reporteArchivo = new ReporteArchivo();
        reporteArchivo.setFkReporte(this.reporte);
        String extFile = CifraUtil.getExtension(fileName);
        reporteArchivo.setNombre(fileName);
        reporteArchivo.setExtension(extFile);
        reporteArchivo.setArchivo(event.getFile().getContents());
        reporteArchivo.setSize((int) event.getFile().getSize());
        reporteArchivo.setTipoArchivo(0);
        reporteArchivo.setFkReporte(reporte);
        reporteArchivo.setIdentificador(CifraUtil.getRandomNumberInRange(100, 1000000));
        ReporteSrv reporteSrv = new ReporteSrv();
        if (this.reporteFotoLst == null) {
            this.reporteFotoLst = new ArrayList<ReporteArchivo>();
        }
        this.reporteFotoLst.add(reporteArchivo);
    }

    public void removeImageFromArray(Integer id) {
        for (ReporteArchivo ra : this.reporteFotoLst) {
            if (ra.getIdentificador() == id) {
                this.reporteFotoLst.remove(ra);
                break;
            }
        }

    }

    public void handleFileUploadAvance(FileUploadEvent event) {

        String fileName;
        fileName = event.getFile().getFileName();
        this.imageAvance = new ReporteArchivo();
        this.imageAvance.setFkReporte(this.reporte);
        String extFile = CifraUtil.getExtension(fileName);
        this.imageAvance.setNombre(fileName);
        this.imageAvance.setExtension(extFile);
        this.imageAvance.setArchivo(event.getFile().getContents());
        this.imageAvance.setSize((int) event.getFile().getSize());
        this.imageAvance.setTipoArchivo(1);
        this.imageAvance.setFkReporte(reporte);

    }

    public void removeFile(Integer id) {
        for (ReporteArchivo ra : this.reporte.getReporteArchivoList()) {
            if (ra.getIdentificador().equals(id)) {
                this.reporte.getReporteArchivoList().remove(ra);
                this.actualizarReporte();
                break;
            }
        }

    }

    public StreamedContent convertImage(Integer idArchivo) {
        DefaultStreamedContent myImage = null;
        for (ReporteArchivo ra : this.reporte.getReporteArchivoList()) {
            if (ra.getIdentificador().equals(idArchivo)) {
                InputStream is = new ByteArrayInputStream(ra.getArchivo());
                myImage = new DefaultStreamedContent(is, "image/jpg");
                break;
            }
        }
        return myImage;
    }

    public void setCurrentFinancieroObra() {
        for (ContratoFinancieroObra cfo : this.contratoFinancieroObraLst) {
            if (cfo.getIdentificador().equals(this.reporte.getFkCtoFinancieroObra())) {
                this.curFinancieroObra = cfo;
                return;
            }
        }

    }

    public void setCurrentFinancieroInter() {
        for (ContratoFinancieroInter cfi : this.contratoFinancieroInterLst) {
            if (cfi.getIdentificador() == this.reporte.getFkCtoFinancieroInter()) {
                this.curFinancieroInter = cfi;
            }
        }
    }

    /**
     * Clasifica la imagenes que vienen de la base de datos las que son soporte
     * fotográfico y las que son del plan
     *
     * @param ramdomId
     */
    public void cargarImagenesFromReporteToArray(boolean ramdomId) {
        this.reporteFotoLst = new ArrayList<>();
        this.imageAvance = new ReporteArchivo();
        for (ReporteArchivo ra : this.reporte.getReporteArchivoList()) {
            ReporteArchivo ra1 = new ReporteArchivo();
            ra1.setArchivo(ra.getArchivo());
            ra1.setExtension(ra.getExtension());
            ra1.setFkReporte(reporte);
            ra1.setNombre(ra.getNombre());
            ra1.setSize(ra.getSize());
            ra1.setTipoArchivo(ra.getTipoArchivo());
            if (ramdomId) {
                ra1.setIdentificador(CifraUtil.getRandomNumberInRange(100, 1000000));
            } else {
                ra1.setIdentificador(ra.getIdentificador());
            }
            if (ra.getTipoArchivo() != null) {
                if (ra.getTipoArchivo() == 0) { // Es una imagen de soporte fotografico
                    this.reporteFotoLst.add(ra1);
                }
                if (ra.getTipoArchivo() == 1) {
                    this.imageAvance = ra1;
                }
            }
        }
    }

    /**
     * Funcion para copiar la imagen de la firma en file system y poder
     * visualizarla
     *
     * @param rp reporte del cual se extraen las imagenes
     */
    public void copiarFirmaFromReporteToFile(Reporte rp) {
        this.nomFileImageFirma = "firma.png";
        for (ReporteArchivo ra : rp.getReporteArchivoList()) {
            if (ra.getTipoArchivo().equals(2)) {
                this.nomFileImageFirma = "firma_" + CifraUtil.getRamdomString(10) + "." + ra.getExtension();
                String rutaFile = CifraUtil.pathToWriteTmpFile() + "\\" + this.nomFileImageFirma;
                //Copiar el archivo en file sistem
                try (OutputStream out = new BufferedOutputStream(new FileOutputStream(rutaFile))) {
                    try {
                        out.write(ra.getArchivo());
                    } catch (IOException ex) {
                        Logger.getLogger(ReporteBean.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ReporteBean.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ReporteBean.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }

    }

    public void loadTramoActividadMaquinaria() {
        this.rtdot = new ReporteTableDOT(reporte);
        this.rtdot.makeRegistroTables(false);

    }

    public void copiarImagenesFromReporteToFile() throws IOException {
        for (ReporteArchivo ra : this.reporte.getReporteArchivoList()) {
            //Copiar el archivo en file sistem
            String rutaFile = CifraUtil.pathToWriteTmpFile() + "/img" + ra.getIdentificador() + "." + ra.getExtension();
            try (OutputStream out = new BufferedOutputStream(new FileOutputStream(rutaFile))) {
                try {
                    out.write(ra.getArchivo());
                } catch (IOException ex) {
                    Logger.getLogger(ReporteBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ReporteBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public StreamedContent getContent() {
        return null;
    }

    /*Geter Seter*/
    public Reporte getReporte() {
        return reporte;
    }

    public void setReporte(Reporte reporte) {
        this.reporte = reporte;
    }

    public List<RegistroAvanceDOT> getRegistroActividadLst() {
        return registroActividadLst;
    }

    public void setRegistroActividadLst(List<RegistroAvanceDOT> registroActividadLst) {
        this.registroActividadLst = registroActividadLst;
    }

    public List<RegistroAvanceDOT> getRegistroMaquinariaLst() {
        return registroMaquinariaLst;
    }

    public void setRegistroMaquinariaLst(List<RegistroAvanceDOT> registroMaquinariaLst) {
        this.registroMaquinariaLst = registroMaquinariaLst;
    }

    public Date getCurDate() {
        return curDate;
    }

    public void setCurDate(Date curDate) {
        this.curDate = curDate;
    }

    public ReporteMateriales getCurReporteMateriales() {
        return curReporteMateriales;
    }

    public void setCurReporteMateriales(ReporteMateriales curReporteMateriales) {
        this.curReporteMateriales = curReporteMateriales;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public boolean isEnEdicion() {
        return enEdicion;
    }

    public void setEnEdicion(boolean enEdicion) {
        this.enEdicion = enEdicion;
    }

    public boolean[] getPanelOk() {
        return panelOk;
    }

    public void setPanelOk(boolean[] panelOk) {
        this.panelOk = panelOk;
    }

    public boolean[] getPanelFocus() {
        return panelFocus;
    }

    public void setPanelFocus(boolean[] panelFocus) {
        this.panelFocus = panelFocus;
    }

    public List<ContratoFinancieroInter> getContratoFinancieroInterLst() {
        return contratoFinancieroInterLst;
    }

    public void setContratoFinancieroInterLst(List<ContratoFinancieroInter> contratoFinancieroInterLst) {
        this.contratoFinancieroInterLst = contratoFinancieroInterLst;
    }

    public List<ContratoFinancieroObra> getContratoFinancieroObraLst() {
        return contratoFinancieroObraLst;
    }

    public void setContratoFinancieroObraLst(List<ContratoFinancieroObra> contratoFinancieroObraLst) {
        this.contratoFinancieroObraLst = contratoFinancieroObraLst;
    }

    public ContratoFinancieroObra getCurFinancieroObra() {
        return curFinancieroObra;
    }

    public void setCurFinancieroObra(ContratoFinancieroObra curFinancieroObra) {
        this.curFinancieroObra = curFinancieroObra;
    }

    public ContratoFinancieroInter getCurFinancieroInter() {
        return curFinancieroInter;
    }

    public void setCurFinancieroInter(ContratoFinancieroInter curFinancieroInter) {
        this.curFinancieroInter = curFinancieroInter;
    }

    public String getNomFileImageAvance() {
        return nomFileImageAvance;
    }

    public void setNomFileImageAvance(String nomFileImageAvance) {
        this.nomFileImageAvance = nomFileImageAvance;
    }

    public List<ReporteArchivo> getReporteFotoLst() {
        return reporteFotoLst;
    }

    public void setReporteFotoLst(List<ReporteArchivo> reporteFotoLst) {
        this.reporteFotoLst = reporteFotoLst;
    }

    public ReporteArchivo getImageAvance() {
        return imageAvance;
    }

    public void setImageAvance(ReporteArchivo imageAvance) {
        this.imageAvance = imageAvance;
    }

    public Boolean getDisabledGuardarReporteBase() {
        return disabledGuardarReporteBase;
    }

    public void setDisabledGuardarReporteBase(Boolean disabledGuardarReporteBase) {
        this.disabledGuardarReporteBase = disabledGuardarReporteBase;
    }

    public ReporteTableDOT getRtdot() {
        return rtdot;
    }

    public void setRtdot(ReporteTableDOT rtdot) {
        this.rtdot = rtdot;
    }

    public String getNomFileImageFirma() {
        return nomFileImageFirma;
    }

    public void setNomFileImageFirma(String nomFileImageFirma) {
        this.nomFileImageFirma = nomFileImageFirma;
    }

    public boolean isAceptarReporte() {
        return aceptarReporte;
    }

    public void setAceptarReporte(boolean aceptarReporte) {
        this.aceptarReporte = aceptarReporte;
    }

    public String getValidarValorFactura() {
        validarValorFactura = null;
        if (this.reporte.getObrPresentoFactura()) {
            if (this.reporte.getObrValorAjustes() != null
                    && this.reporte.getObrValorAmbiental() != null
                    && this.reporte.getObrValorEstudios() != null
                    && this.reporte.getObrValorPredial() != null
                    && this.reporte.getObrValorInversion() != null
                    && this.reporte.getObrValorIva() != null
                    && this.reporte.getObrValorOtros() != null
                    && this.reporte.getObrValorAmortizacion() != null
                    && this.reporte.getObrValorFacturado() != null) {
                double sumandos;
                sumandos = this.reporte.getObrValorAjustes().doubleValue()
                        + this.reporte.getObrValorAmbiental().doubleValue()
                        + this.reporte.getObrValorEstudios().doubleValue()
                        + this.reporte.getObrValorPredial().doubleValue()
                        + this.reporte.getObrValorInversion().doubleValue()
                        + this.reporte.getObrValorIva().doubleValue()
                        + this.reporte.getObrValorOtros().doubleValue();
                double resta = sumandos - this.reporte.getObrValorAmortizacion().doubleValue();
                if (resta == this.reporte.getObrValorFacturado().doubleValue()) {
                    validarValorFactura = "Ok";
                } else {
                    validarValorFactura = null;
                }
            } else {
                validarValorFactura = null;
            }

        } else {
            validarValorFactura = "ok";
        }
        return validarValorFactura;
    }

    public void setValidarValorFactura(String validarValorFactura) {
        this.validarValorFactura = validarValorFactura;
    }

}
