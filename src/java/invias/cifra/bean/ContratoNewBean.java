/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.bean;

import invias.cifra.entity.Actividad;
import invias.cifra.entity.Ajuste;
import invias.cifra.entity.Contrato;
import invias.cifra.entity.ContratoAdicionInter;
import invias.cifra.entity.ContratoAdicionObra;
import invias.cifra.entity.ContratoProrrogaInter;
import invias.cifra.entity.ContratoProrrogaObra;
import invias.cifra.entity.ContratoActividad;
import invias.cifra.entity.ContratoAjusteObra;
import invias.cifra.entity.ContratoAjusteInter;
import invias.cifra.entity.ContratoFinancieroInter;
import invias.cifra.entity.ContratoFinancieroObra;
import invias.cifra.entity.ContratoMaquinaria;
import invias.cifra.entity.ContratoTerceroInter;
import invias.cifra.entity.ContratoTerceroObra;
import invias.cifra.entity.ContratoTramo;
import invias.cifra.entity.ContratoVigenciaObra;
import invias.cifra.entity.DireccionTerritorial;
import invias.cifra.entity.EstadoContrato;
import invias.cifra.entity.Maquinaria;
import invias.cifra.entity.Programa;
import invias.cifra.entity.Proyecto;
import invias.cifra.entity.Tercero;
import invias.cifra.entity.UnidadEjecutora;
import invias.cifra.entity.Usuario;
import invias.cifra.exception.CifraException;
import invias.cifra.model.ContratoSrv;
import invias.cifra.model.TablesSrv;
import invias.cifra.model.TerceroSrv;
import invias.cifra.model.UsuarioSrv;
import invias.cifra.util.CifraUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.faces.model.SelectItem;

/**
 *
 * @author jaflorez
 */
@ManagedBean
@ViewScoped
public class ContratoNewBean extends BaseBean {

    /**
     * Creates a new instance of ContratoNewBean
     */
    private Contrato contrato;
    private List<Actividad> actividadLst;
    private List<Maquinaria> maquinariaLst;
    private List<Proyecto> proyectoLst;
    private List<UnidadEjecutora> unidadEjecutoraLst;
    private List<DireccionTerritorial> direccionTerritorialLst;
    private List<Ajuste> ajusteLst;
    private List<Programa> programaLst;
    private List<Usuario> usuarioGestorLst;
    private List<Usuario> usuarioSupervisorLst;
    private List<Tercero> terceroLst;

    /*Datos para el tramo*/
    ContratoTramo curContratoTramo;
    /*Datos de la actividas*/
    ContratoActividad curContratoActividad;
    /*Datos de la maquinaria*/
    ContratoMaquinaria curContratoMaquinaria;
    /*Datos del contrato*/

 /*Datos para el financiero*/
    private ContratoFinancieroObra curContratoFinancieroObra;
    private ArrayList<SelectItem> listaAnos;
    /*Datos para el contratista*/
    private ContratoTerceroObra curContratoTerceroObra;
    private ContratoTerceroInter curContratoTerceroInter;

    /*Datos para las prorrogas*/
    private ContratoProrrogaObra curProrrogaObra;
    private ContratoProrrogaInter curProrrogaInter;
    /*Datos para las vigencia de obra*/
    private ContratoVigenciaObra curVigenciaObra;
    /*Datos para las adiciones*/
    private ContratoAdicionObra curAdicionObra;
    private ContratoAdicionInter curAdicionInter;
    /*Datos para el ajuse de contrato*/
    private ContratoAjusteObra curAjusteObra;

    private ContratoAjusteInter curAjusteInter;

    private String sumaParticipacionObraOk;
    private Integer sumaParticipacionObra;
    private BigDecimal sumaVigenciaObra;
    private BigDecimal sumaFacturadoObra;
    private BigDecimal sumaFinancieroObra;
    private BigDecimal sumaAdicionObra = new BigDecimal(0);
    private BigDecimal sumaProrrogaObra = new BigDecimal(0);

    private String sumaParticipacionInterOk;
    private Integer sumaParticipacionInter;
    private BigDecimal sumaVigenciaInter;
    private BigDecimal sumaAdicionInter = new BigDecimal(0);
    private BigDecimal sumaProrrogaInter = new BigDecimal(0);
    private boolean newContrato;
    /*Banderas para edicion*/
    private boolean editContratoFinancieroObra;
    private boolean editContratoTramo;
    private boolean editContratoActividad;
    private boolean editContratoMaquinaria;

    public ContratoNewBean() {
        TablesSrv tablesSrv = new TablesSrv();
        UsuarioSrv usarioSrv = new UsuarioSrv();
        TerceroSrv terceroSrv = new TerceroSrv();
        this.curContratoTramo = new ContratoTramo();
        this.curContratoActividad = new ContratoActividad();
        this.curContratoActividad.setFkActividad(new Actividad());
        this.curContratoMaquinaria = new ContratoMaquinaria();
        this.curContratoMaquinaria.setFkMaquinaria(new Maquinaria());
        this.curContratoFinancieroObra = new ContratoFinancieroObra();
        this.curAjusteObra = new ContratoAjusteObra();
        this.curAjusteObra.setFkAjuste(new Ajuste());
        this.curAjusteInter = new ContratoAjusteInter();
        this.curAjusteInter.setFkAjuste(new Ajuste());
        this.curContratoFinancieroObra.setAno(Calendar.getInstance().get(Calendar.YEAR));
        this.curContratoTerceroObra = new ContratoTerceroObra();
        this.curContratoTerceroObra.setFkTercero(new Tercero());
        this.curVigenciaObra = new ContratoVigenciaObra();
        this.curContratoTerceroInter = new ContratoTerceroInter();
        this.curContratoTerceroInter.setFkTercero(new Tercero());
        this.curProrrogaObra = new ContratoProrrogaObra();
        this.curProrrogaInter = new ContratoProrrogaInter();
        this.curAdicionInter = new ContratoAdicionInter();
        this.curAdicionObra = new ContratoAdicionObra();
        this.listaAnos = CifraUtil.listaAnos();
        this.editContratoFinancieroObra = false;
        this.editContratoTramo = false;
        this.editContratoMaquinaria = false;
        this.editContratoActividad = false;
        String estadoNuevo;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        estadoNuevo = (String) facesContext.getExternalContext().getRequestParameterMap().get("newContrato");
        this.newContrato = false;
        if (estadoNuevo != null) {
            if (estadoNuevo.compareTo("S") == 0) {
                this.newContrato = true;
            }
        }
        try {
            this.actividadLst = tablesSrv.getActividadLst();
            this.maquinariaLst = tablesSrv.getMaquinariaLst();
            this.proyectoLst = tablesSrv.getProyectoLst();
            this.programaLst = tablesSrv.getProgramaLst();
            this.ajusteLst = tablesSrv.getAjusteLst();
            this.unidadEjecutoraLst = tablesSrv.getUnidadEjecutoraLst();
            this.direccionTerritorialLst = tablesSrv.getDireccionTerritorialLst();
            this.usuarioGestorLst = usarioSrv.getUsuarioTipoLst(2);
            this.usuarioSupervisorLst = usarioSrv.getUsuarioTipoLst(4);
            this.terceroLst = terceroSrv.getTereceroLst();
            if (!this.newContrato) {
                ContratoSrv contratoSrv = new ContratoSrv();
                CifraSessionBean ssb = CifraUtil.getSessionBean();
                this.contrato = contratoSrv.loadContratoById(ssb.getContratoId());
                this.updateInfoSumaAdicionInter();
                this.updateInfoSumaAdicionObra();
                this.updateInfoSumaIntegranteInter();
                this.updateInfoSumaIntegranteObra();
                this.updateInfoSumaProrrogaInter();
                this.updateInfoSumaProrrogaObra();
                this.updateSumaFinancieroObra();
                this.updateSumaFinancieroObra();
                this.updateSumaVigenciaObra();
            } else {
                this.contrato = new Contrato();
                this.contrato.setFkPrograma(new Programa());
                this.contrato.setFkDireccionTerritorial(new DireccionTerritorial());
                this.contrato.setFkProyecto(new Proyecto());
                this.contrato.setFkTerceroInter(new Tercero());
                this.contrato.setFkTerceroObra(new Tercero());
                this.contrato.setFkUnidadEjecutora(new UnidadEjecutora());
                this.contrato.setFkUsuarioInterventor(new Usuario());
                this.contrato.setFkUsuarioGestor(new Usuario());
                this.contrato.setFkUsuarioSupervisor(new Usuario());
                this.contrato.setFkEstadoContrato(new EstadoContrato());
                this.contrato.getFkEstadoContrato().setIdentificador(1);
                
            }

        } catch (CifraException ex) {
            Logger.getLogger(ContratoNewBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.sumaParticipacionObraOk = "si";
    }

    public void addicionarTerceroInter() {
        for (Tercero ter : this.terceroLst) {
            if (curContratoTerceroInter.getFkTercero().getIdentificador().equals(ter.getIdentificador())) {
                curContratoTerceroInter.getFkTercero().setNit(ter.getNit());
                curContratoTerceroInter.getFkTercero().setRazonsocial(ter.getRazonsocial());
            }
        }
        if (this.contrato.getContratoTerceroInterList() == null) {
            this.contrato.setContratoTerceroInterList(new ArrayList<ContratoTerceroInter>());
        }
        this.curContratoTerceroInter.setFkContrato(this.contrato);
        this.contrato.getContratoTerceroInterList().add(this.curContratoTerceroInter);
        this.curContratoTerceroInter = new ContratoTerceroInter();
        this.curContratoTerceroInter.setFkTercero(new Tercero());
        this.updateInfoSumaIntegranteInter();

    }
    public void editGeometria(Integer id_tramo) {
        CifraSessionBean sgbSes = CifraUtil.getSessionBean();
        sgbSes.setTramoId(id_tramo);
        CifraUtil.redirectPage("contratos/mapa.xhtml");
    }
    public void addicionarTerceroObra() {
        for (Tercero ter : this.terceroLst) {
            if (curContratoTerceroObra.getFkTercero().getIdentificador().equals(ter.getIdentificador())) {
                curContratoTerceroObra.getFkTercero().setNit(ter.getNit());
                curContratoTerceroObra.getFkTercero().setRazonsocial(ter.getRazonsocial());
                break;
            }
        }
        /*Si la lista esta en nulo, se instancia el objeto*/
        if (this.contrato.getContratoTerceroObraList() == null) {
            this.contrato.setContratoTerceroObraList(new ArrayList<ContratoTerceroObra>());
        }

        this.curContratoTerceroObra.setFkContrato(this.contrato);
        this.contrato.getContratoTerceroObraList().add(this.curContratoTerceroObra);
        this.curContratoTerceroObra = new ContratoTerceroObra();
        this.curContratoTerceroObra.setFkTercero(new Tercero());
        this.updateInfoSumaIntegranteObra();
        this.addMessage("Actuacion exitosa", "Se ha insertado con exito la informacion", FacesMessage.SEVERITY_INFO);
    }

    public void removeTerceroObra(Object obj) {
        if (!(obj instanceof ContratoTerceroObra)) {
            addMessage("Error en el proceso", "No se puede eliminar este registro", FacesMessage.SEVERITY_ERROR);
            return;
        }
        ContratoTerceroObra ter = (ContratoTerceroObra) obj;
        for (ContratoTerceroObra cto : this.contrato.getContratoTerceroObraList()) {
            if (cto.getFkTercero().getNit().equals(ter.getFkTercero().getNit())) {
                this.contrato.getContratoTerceroObraList().remove(cto);
                break;
            }
        }
        this.updateInfoSumaIntegranteObra();
    }

    public void removeTerceroInter(Object obj) {
        if (!(obj instanceof ContratoTerceroInter)) {
            addMessage("Error en el proceso", "No se puede eliminar este registro", FacesMessage.SEVERITY_ERROR);
            return;
        }
        ContratoTerceroInter tercero = (ContratoTerceroInter) obj;
        for (ContratoTerceroInter cti : this.contrato.getContratoTerceroInterList()) {
            if (cti.getFkTercero().getNit().equals(tercero.getFkTercero().getNit())) {
                this.contrato.getContratoTerceroInterList().remove(cti);
                break;
            }
        }
        this.updateInfoSumaIntegranteInter();
    }

    public void eliminarFuncion() {

        
    }

    private void updateInfoSumaIntegranteObra() {
        this.sumaParticipacionObra = 0;
        for (ContratoTerceroObra cto : this.contrato.getContratoTerceroObraList()) {
            this.sumaParticipacionObra += cto.getCantidad();
        }
        if (this.sumaParticipacionObra == 0 || this.sumaParticipacionObra == 100) {
            this.sumaParticipacionObraOk = "si";
        } else {
            this.sumaParticipacionObraOk = null;
        }
    }

    private void updateInfoSumaIntegranteInter() {
        this.sumaParticipacionInter = 0;
        for (ContratoTerceroInter cti : this.contrato.getContratoTerceroInterList()) {
            this.sumaParticipacionInter += cti.getCantidad();
        }
        if (this.sumaParticipacionInter == 0 || this.sumaParticipacionInter == 100) {
            this.sumaParticipacionInterOk = "si";
        } else {
            this.sumaParticipacionInterOk = null;
        }
    }

    private void updateInfoSumaProrrogaObra() {
        //this.sumaAdicionObra = new BigDecimal(0);
        double valor = 0;
        for (ContratoProrrogaObra cpo : this.contrato.getContratoProrrogaObraList()) {
            if (cpo.getCantidad() != null) {
                valor += cpo.getCantidad().doubleValue();
            }
        }
        this.sumaProrrogaObra = BigDecimal.valueOf(valor);

    }

    private void updateInfoSumaProrrogaInter() {
        double valor = 0;
        for (ContratoProrrogaInter cpi : this.contrato.getContratoProrrogaInterList()) {
            if (cpi.getCantidad() != null) {
                valor += cpi.getCantidad().doubleValue();
            }
        }
        this.sumaProrrogaInter = BigDecimal.valueOf(valor);

    }

    private void updateInfoSumaAdicionObra() {
        double valor = 0;
        for (ContratoAdicionObra cao : this.contrato.getContratoAdicionObraList()) {
            if (cao.getValor() != null) {
                valor += cao.getValor().doubleValue();
            }
        }
        this.sumaAdicionObra = BigDecimal.valueOf(valor);
    }

    private void updateInfoSumaAdicionInter() {
        double valor = 0;
        for (ContratoAdicionInter cai : this.contrato.getContratoAdicionInterList()) {
            if (cai.getValor() != null) {
                valor += cai.getValor().doubleValue();
            }
        }
        this.sumaAdicionInter = BigDecimal.valueOf(valor);
    }

    private void updateSumaVigenciaObra() {
        double sumatoria = 0;
        double sumaFacturado = 0;
        for (ContratoVigenciaObra cvo : this.contrato.getContratoVigenciaObraList()) {
            sumatoria += cvo.getValor().doubleValue();
            sumaFacturado += cvo.getAvance().doubleValue();
        }
        this.sumaVigenciaObra = BigDecimal.valueOf(sumatoria);
        this.sumaFacturadoObra = BigDecimal.valueOf(sumaFacturado);
    }

    private void updateSumaFinancieroObra() {
        double sumatoria = 0;
        for (ContratoFinancieroObra cfo : this.contrato.getContratoFinancieroObraList()) {
            try {
                sumatoria += cfo.getValor().doubleValue();
            } catch (NullPointerException ex) {
                Logger.getLogger(ContratoNewBean.class.getName()).log(Level.SEVERE, null, ex);

            }
        }
        this.sumaFinancieroObra = BigDecimal.valueOf(sumatoria);
    }

    private void updateInfoSumaVigenciaInter() {

        double sumatoria = 0;
        for (ContratoFinancieroInter cfi : this.contrato.getContratoFinancieroInterList()) {
            sumatoria += cfi.getValor().doubleValue();
        }
        this.sumaVigenciaInter = BigDecimal.valueOf(sumatoria);
    }

    public void removeFinancieroObra() {
        try {
            ContratoSrv contratoSrv = new ContratoSrv();
            String lista = contratoSrv.listaReporteConFinanciero(curContratoFinancieroObra);
            if (!"".equals(lista)) {
                this.addMessage("Error en el proceso Este registro tiene un reportes asociados " + lista, "Este registro tiene un reportes asociados " + lista, FacesMessage.SEVERITY_ERROR);
                return;
            }
        } catch (CifraException ex) {
            Logger.getLogger(ContratoNewBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        ContratoFinancieroObra cfo;

        for (int i = 0; i <= this.contrato.getContratoFinancieroObraList().size(); i++) {
            cfo = this.contrato.getContratoFinancieroObraList().get(i);
            if (cfo.getAno().equals(curContratoFinancieroObra.getAno()) && cfo.getCorte().equals(curContratoFinancieroObra.getCorte())) {
                this.contrato.getContratoFinancieroObraList().remove(i);
                break;
            }
        }
        this.updateSumaFinancieroObra();
    }

    public void removeVigenciaObra(Object obj) {
        if (!(obj instanceof ContratoVigenciaObra)) {
            addMessage("Error en el proceso", "No se puede eliminar este registro", FacesMessage.SEVERITY_ERROR);
        }
        ContratoVigenciaObra vigenciaObra = (ContratoVigenciaObra) obj;

        ContratoVigenciaObra cvo;
        for (int i = 0; i <= this.contrato.getContratoVigenciaObraList().size(); i++) {
            cvo = this.contrato.getContratoVigenciaObraList().get(i);
            if (cvo.getIdentificador().equals(vigenciaObra.getIdentificador())) {
                this.contrato.getContratoVigenciaObraList().remove(i);
                break;
            }
        }
        this.updateSumaVigenciaObra();
    }

    public void removeAjusteObra(Object obj) {
        if (!(obj instanceof ContratoAjusteObra)) {
            addMessage("Error en el proceso", "No se puede eliminar este registro", FacesMessage.SEVERITY_ERROR);
        }
        ContratoAjusteObra ajusteObra = (ContratoAjusteObra) obj;
        for (ContratoAjusteObra cao : this.contrato.getContratoAjusteObraList()) {
            if (cao.getFecha().equals(ajusteObra.getFecha()) && cao.getFkAjuste().getIdentificador() == ajusteObra.getFkAjuste().getIdentificador()) {
                this.contrato.getContratoAjusteObraList().remove(cao);
                break;
            }
        }
    }

    public void removeAjusteInter(Object obj) {
        if (!(obj instanceof ContratoAjusteInter)) {
            addMessage("Error en el proceso", "No se puede eliminar este registro", FacesMessage.SEVERITY_ERROR);
        }
        ContratoAjusteInter ajusteInter = (ContratoAjusteInter) obj;
        for (ContratoAjusteInter cai : this.contrato.getContratoAjusteInterList()) {
            if (cai.getFecha().equals(ajusteInter.getFecha()) && cai.getFkAjuste().getIdentificador() == ajusteInter.getFkAjuste().getIdentificador()) {
                this.contrato.getContratoAjusteInterList().remove(cai);
                break;
            }
        }
    }

    public void removeProrrogaObra(Object obj) {
        if (!(obj instanceof ContratoProrrogaObra)) {
            addMessage("Error en el proceso", "No se puede eliminar este registro", FacesMessage.SEVERITY_ERROR);
        }
        ContratoProrrogaObra prorrogaObra = (ContratoProrrogaObra) obj;
        for (ContratoProrrogaObra cpo : this.contrato.getContratoProrrogaObraList()) {
            if ((cpo.getFecha().equals(prorrogaObra.getFecha()))) {
                this.contrato.getContratoProrrogaObraList().remove(cpo);
                break;
            }
        }
        this.updateInfoSumaProrrogaObra();
    }

    public void removeProrrogaInter(Object obj) {
        if (!(obj instanceof ContratoProrrogaInter)) {
            addMessage("Error en el proceso", "No se puede eliminar este registro", FacesMessage.SEVERITY_ERROR);
        }
        ContratoProrrogaInter prorrogaInter = (ContratoProrrogaInter) obj;
        for (ContratoProrrogaInter cpi : this.contrato.getContratoProrrogaInterList()) {
            if ((cpi.getFecha().equals(prorrogaInter.getFecha()))) {
                this.contrato.getContratoProrrogaInterList().remove(cpi);
                break;
            }
        }
        this.updateInfoSumaProrrogaInter();
    }

    public void removeAdicionObra(Object obj) {
        if (!(obj instanceof ContratoAdicionObra)) {
            addMessage("Error en el proceso", "No se puede eliminar este registro", FacesMessage.SEVERITY_ERROR);
        }
        ContratoAdicionObra adicionObra = (ContratoAdicionObra) obj;
        for (ContratoAdicionObra cao : this.contrato.getContratoAdicionObraList()) {
            if ((cao.getFecha().equals(adicionObra.getFecha()))) {
                this.contrato.getContratoAdicionObraList().remove(cao);
                break;
            }
        }
        this.updateInfoSumaAdicionObra();

    }

    public void removeTramo() {
        try {
            ContratoSrv contratoSrv = new ContratoSrv();
            String lista = contratoSrv.listaReporteConTramo(curContratoTramo);
            if (!"".equals(lista)) {
                this.addMessage("Error en el proceso: Este tramo se encuentra en estos reportes: " + lista, "Este tramo se encuentra en estos reportes:" + lista, FacesMessage.SEVERITY_ERROR);
                return;
            }
        } catch (CifraException ex) {
            Logger.getLogger(ContratoNewBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        ContratoTramo ct;
        for (int i = 0; i <= this.contrato.getContratoTramoList().size(); i++) {
            ct = this.contrato.getContratoTramoList().get(i);
            if (ct.getIdentificador().equals(curContratoTramo.getIdentificador())) {
                this.contrato.getContratoTramoList().remove(i);
                break;
            }
        }
    }

    public void removeActividad() {
        try {
            ContratoSrv contratoSrv = new ContratoSrv();
            String lista = contratoSrv.listaReporteConActividad(curContratoActividad);
            if (!"".equals(lista)) {
                this.addMessage("Error en el proceso: Este registro tiene un reportes asociados " + lista, "Esta actividad se encuentra en estos reportes:" + lista, FacesMessage.SEVERITY_ERROR);
                return;
            }
        } catch (CifraException ex) {
            Logger.getLogger(ContratoNewBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        ContratoActividad ca;
        for (int i = 0; i <= this.contrato.getContratoActividadList().size(); i++) {
            ca = this.contrato.getContratoActividadList().get(i);
            if (ca.getIdentificador().equals(curContratoActividad.getIdentificador())) {
                this.contrato.getContratoActividadList().remove(i);
                break;
            }
        }
    }

    public void removeMaquinaria() {
        try {
            ContratoSrv contratoSrv = new ContratoSrv();
            String lista = contratoSrv.listaReporteConMaquinaria(curContratoMaquinaria);
            if (!"".equals(lista)) {
                this.addMessage("Error en el proceso: Este registro tiene reportes asociados: " + lista, "Este maquinaria se encuentra en estos reportes" + lista, FacesMessage.SEVERITY_ERROR);
                return;
            }
        } catch (CifraException ex) {
            Logger.getLogger(ContratoNewBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        ContratoMaquinaria cm;
        for (int i = 0; i <= this.contrato.getContratoMaquinariaList().size(); i++) {
            cm = this.contrato.getContratoMaquinariaList().get(i);
            if (cm.getIdentificador().equals(curContratoMaquinaria.getIdentificador())) {
                this.contrato.getContratoMaquinariaList().remove(i);
                break;
            }
        }
    }

    public void removeAdicionInter(Object obj) {
        if (!(obj instanceof ContratoAdicionInter)) {
            addMessage("Error en el proceso", "No se puede eliminar este registro", FacesMessage.SEVERITY_ERROR);
        }
        ContratoAdicionInter adicionInter = (ContratoAdicionInter) obj;
        for (ContratoAdicionInter cai : this.contrato.getContratoAdicionInterList()) {
            if ((cai.getFecha().equals(adicionInter.getFecha()))) {
                this.contrato.getContratoAdicionInterList().remove(cai);
                break;
            }
        }
        this.updateInfoSumaAdicionInter();

    }

    /**
     * Funcion para adicionar la maquinaria al listado, esta funcion se llama
     * desde el formulario
     */
    public void addicionarMaquinaria() {
        Maquinaria maquinaria = new Maquinaria();
        for (Maquinaria maq : this.maquinariaLst) {
            if (maq.getIdentificador().equals(this.curContratoMaquinaria.getFkMaquinaria().getIdentificador())) {
                maquinaria.setMaquinaria(maq.getMaquinaria());
                maquinaria.setIdentificador(maq.getIdentificador());
            }
        }
        this.curContratoMaquinaria.setFkMaquinaria(maquinaria);
        this.curContratoMaquinaria.setFkContrato(this.contrato);
        if (this.contrato.getContratoMaquinariaList() == null) {
            this.contrato.setContratoMaquinariaList(new ArrayList<>());
        }
        this.contrato.getContratoMaquinariaList().add(this.curContratoMaquinaria);
        this.curContratoMaquinaria = new ContratoMaquinaria();
        this.curContratoMaquinaria.setFkMaquinaria(new Maquinaria());

    }

    public void modificarFinancieroObra() {
        for (ContratoFinancieroObra cfo : this.contrato.getContratoFinancieroObraList()) {
            if (cfo.getIdentificador().equals(this.curContratoFinancieroObra.getIdentificador())) {
                cfo.setAno(this.curContratoFinancieroObra.getAno());
                cfo.setCorte(this.curContratoFinancieroObra.getCorte());
                cfo.setValor(this.curContratoFinancieroObra.getValor());
            }
        }
        this.updateSumaFinancieroObra();
    }

    public void modificarActividad() {
        for (ContratoActividad ca : this.contrato.getContratoActividadList()) {
            if (ca.getIdentificador().equals(this.curContratoActividad.getIdentificador())) {
                ca.setCantidad(this.curContratoActividad.getCantidad());
            }
        }
    }

    public void modificarMaquinaria() {
        for (ContratoMaquinaria cm : this.contrato.getContratoMaquinariaList()) {
            if (cm.getIdentificador().equals(this.curContratoMaquinaria.getIdentificador())) {
                cm.setCantidad(this.curContratoMaquinaria.getCantidad());
            }
        }
    }

    public void modificarTramo() {
        for (ContratoTramo ct : this.contrato.getContratoTramoList()) {
            if (ct.getIdentificador().equals(this.curContratoTramo.getIdentificador())) {
                ct.setCodigovia(this.curContratoTramo.getCodigovia());
                ct.setDisFinal(this.curContratoTramo.getDisFinal());
                ct.setDisIncial(this.curContratoTramo.getDisIncial());
                ct.setNumero(this.curContratoTramo.getNumero());
                ct.setTramo(this.curContratoTramo.getTramo());
            }
        }
    }

    public void addicionarFinancieroObra() {
        if (this.existeFinanciero(curContratoFinancieroObra)) {
            this.addMessage("Error en el proceso,No se puede ingresar dos programaciones en el mismo mes", "", FacesMessage.SEVERITY_ERROR);
            return;
        }

        this.curContratoFinancieroObra.setFkContrato(this.contrato);
        if (this.contrato.getContratoFinancieroObraList() == null) {
            this.contrato.setContratoFinancieroObraList(new ArrayList<ContratoFinancieroObra>());
        }
        this.curContratoFinancieroObra.setFkContrato(this.contrato);
        this.contrato.getContratoFinancieroObraList().add(this.curContratoFinancieroObra);
        Integer ano;
        ano = this.curContratoFinancieroObra.getAno();
        this.curContratoFinancieroObra = new ContratoFinancieroObra();
        this.curContratoFinancieroObra.setAno(ano);
        this.updateSumaFinancieroObra();

    }

    /**
     * Valida que no exista el registro en la lista
     *
     * @param financieroObra
     * @return
     */
    private boolean existeFinanciero(ContratoFinancieroObra financieroObra) {
        if (this.contrato.getContratoFinancieroObraList() == null) {
            return false;
        }
        for (ContratoFinancieroObra cfo : this.contrato.getContratoFinancieroObraList()) {
            if (cfo.getAno().equals(financieroObra.getAno()) && cfo.getCorte().equals(financieroObra.getCorte())) {
                return true;
            }
        }
        return false;
    }

    public void addicionarVigenciaObra() {
        if (curVigenciaObra.getValor() == null || curVigenciaObra.getAno() < 2000 || curVigenciaObra.getAvance() == null) {
            addMessage("Datos incompletos", "Datos incompletos, no se puede agregar el segistro", FacesMessage.SEVERITY_ERROR);
            return;
        }
        this.curVigenciaObra.setIdentificador(this.getNextIdVigencia(curVigenciaObra));
        this.curVigenciaObra.setFkContrato(this.contrato);
        if (this.contrato.getContratoVigenciaObraList() == null) {
            this.contrato.setContratoVigenciaObraList(new ArrayList<ContratoVigenciaObra>());
        }
        this.curVigenciaObra.setFkContrato(this.contrato);
        this.contrato.getContratoVigenciaObraList().add(this.curVigenciaObra);
        this.curVigenciaObra = new ContratoVigenciaObra();
        this.updateSumaVigenciaObra();
    }

    private Integer getNextIdVigencia(ContratoVigenciaObra vigenciaObra) {
        Integer idVigencia = 0;
        if (this.contrato.getContratoVigenciaObraList() == null) {
            return 1;
        }
        for (ContratoVigenciaObra cvo : this.contrato.getContratoVigenciaObraList()) {
            if (idVigencia < cvo.getIdentificador()) {
                idVigencia = cvo.getIdentificador();
            }
        }
        return idVigencia + 1;
    }

    /**/
    public void newContratoFinancieroObra() {
        this.curContratoFinancieroObra = new ContratoFinancieroObra();
        this.editContratoFinancieroObra = false;
    }

    public void newContratoTramo() {
        this.curContratoTramo = new ContratoTramo();
        this.editContratoTramo = false;

    }

    public void newContratoActividad() {
        this.curContratoActividad = new ContratoActividad();
        this.curContratoActividad.setFkActividad(new Actividad());
        this.editContratoActividad = false;

    }

    public void newContratoMaquinaria() {
        this.curContratoMaquinaria = new ContratoMaquinaria();
        this.curContratoMaquinaria.setFkMaquinaria(new Maquinaria());
        this.editContratoMaquinaria = false;

    }

    /**
     * Funcion para agregar una actividad, se llama desde el formulario
     */
    public void addicionarActividad() {
        Actividad actividad = new Actividad();
        for (Actividad act : this.actividadLst) {
            if (act.getIdentificador().equals(this.curContratoActividad.getFkActividad().getIdentificador())) {
                actividad.setActividad(act.getActividad());
                actividad.setIdentificador(act.getIdentificador());
            }
        }
        this.curContratoActividad.setFkActividad(actividad);
        this.curContratoActividad.setFkContrato(this.contrato);
        if (this.contrato.getContratoActividadList() == null) {
            this.contrato.setContratoActividadList(new ArrayList<>());
        }
        this.contrato.getContratoActividadList().add(this.curContratoActividad);
        this.curContratoActividad = new ContratoActividad();
        this.curContratoActividad.setFkActividad(new Actividad());

    }

    /**
     * Funcion para adicionar un tramo, esta funcion se llama desde el
     * formulario
     */
    public void addicionarTramo() {
        this.curContratoTramo.setFkContrato(this.contrato);
        if (this.contrato.getContratoTramoList() == null) {
            this.contrato.setContratoTramoList(new ArrayList<ContratoTramo>());
        }
        this.contrato.getContratoTramoList().add(this.curContratoTramo);
        /*Se limpian la variables*/
        this.curContratoTramo = new ContratoTramo();
    }

    public void guardarContrato() {

        if (this.newContrato) {
            /*Registro nuevo, se crea un nuevo contrato*/
            try {
                this.insertarContrato();
                CifraUtil.redirectPage("contratos/contratolst.xhtml?faces-redirect=true", "Registro exitoso", "El contrato se ha gardado");
            } catch (CifraException ex) {
                Logger.getLogger(ContratoNewBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                this.modificarContrato();
                CifraUtil.redirectPage("contratos/contratolst.xhtml?faces-redirect=true", "Actualizacion exitosa", "El contrato se ha gardado");
            } catch (CifraException ex) {
                Logger.getLogger(ContratoNewBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            /*Se acutalizar el contrato actual*/
        }

    }

    private void modificarContrato() throws CifraException {
        /*Se calcula la fecha final del contrato*/
 /*Para hacer los calculo se toma la fecha actual*/
        Date fechaActual = new Date();
        ContratoSrv contratoSrv = new ContratoSrv();
        this.contrato.setContObraFechaTermiCalculada(contratoSrv.getFechaFinalContrato(contrato, fechaActual));
        this.contrato.setFkEstadoContrato(contratoSrv.getEstadoContrato(contrato, fechaActual));
        contratoSrv.modificarContrato(this.contrato);
    }

    private void insertarContrato() throws CifraException {
        ContratoSrv contratoSrv = new ContratoSrv();
        //Se asigna el supervisor
        CifraSessionBean sgbSes = CifraUtil.getSessionBean();
        Integer curIdInter = sgbSes.getCurUsuario().getIdentificador();
        this.contrato.setFkUsuarioInterventor(new Usuario(curIdInter));
        contratoSrv.insertarContrato(this.contrato);
    }

    public void adicionarProrrogaObra() {
        if (this.contrato.getContratoProrrogaObraList() == null) {
            this.contrato.setContratoProrrogaObraList(new ArrayList<ContratoProrrogaObra>());
        }
        this.curProrrogaObra.setFkContrato(this.contrato);
        this.contrato.getContratoProrrogaObraList().add(this.curProrrogaObra);
        this.curProrrogaObra = new ContratoProrrogaObra();
        this.updateInfoSumaProrrogaObra();
    }

    public void adicionarAdicionObra() {
        if (this.contrato.getContratoAdicionObraList() == null) {
            this.contrato.setContratoAdicionObraList(new ArrayList<ContratoAdicionObra>());
        }
        this.curAdicionObra.setFkContrato(this.contrato);
        this.contrato.getContratoAdicionObraList().add(this.curAdicionObra);
        this.curAdicionObra = new ContratoAdicionObra();
        this.updateInfoSumaAdicionObra();

    }

    public void adicionarProrrogaInter() {
        if (this.contrato.getContratoProrrogaInterList() == null) {
            this.contrato.setContratoProrrogaInterList(new ArrayList<ContratoProrrogaInter>());
        }
        this.curProrrogaInter.setFkContrato(this.contrato);
        this.contrato.getContratoProrrogaInterList().add(this.curProrrogaInter);
        this.curProrrogaInter = new ContratoProrrogaInter();
        this.updateInfoSumaProrrogaInter();
    }

    public void adicionarAdicionInter() {
        if (this.contrato.getContratoAdicionInterList() == null) {
            this.contrato.setContratoAdicionInterList(new ArrayList<ContratoAdicionInter>());
        }
        this.curAdicionInter.setFkContrato(this.contrato);
        this.contrato.getContratoAdicionInterList().add(this.curAdicionInter);
        this.curAdicionInter = new ContratoAdicionInter();
        this.updateInfoSumaAdicionInter();

    }

    public void adicionarAjusteObra() {
        if (this.contrato.getContratoAjusteObraList() == null) {
            this.contrato.setContratoAjusteObraList(new ArrayList<ContratoAjusteObra>());
        }
        this.curAjusteObra.setFkContrato(this.contrato);
        for (Ajuste ajt : this.ajusteLst) {
            if (ajt.getIdentificador().compareTo(this.curAjusteObra.getFkAjuste().getIdentificador()) == 0) {
                this.curAjusteObra.getFkAjuste().setAjuste(ajt.getAjuste());
                break;
            }
        }
        this.contrato.getContratoAjusteObraList().add(this.curAjusteObra);
        this.curAjusteObra = new ContratoAjusteObra();
        this.curAjusteObra.setFkAjuste(new Ajuste());
    }

    public void adicionarAjusteInter() {
        if (this.contrato.getContratoAjusteInterList() == null) {
            this.contrato.setContratoAjusteInterList(new ArrayList<ContratoAjusteInter>());
        }
        this.curAjusteInter.setFkContrato(this.contrato);
        for (Ajuste ajt : this.ajusteLst) {
            if (ajt.getIdentificador().compareTo(this.curAjusteInter.getFkAjuste().getIdentificador()) == 0) {
                this.curAjusteInter.getFkAjuste().setAjuste(ajt.getAjuste());
                break;
            }
        }
        this.contrato.getContratoAjusteInterList().add(this.curAjusteInter);
        this.curAjusteInter = new ContratoAjusteInter();
        this.curAjusteInter.setFkAjuste(new Ajuste());
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public List<Actividad> getActividadLst() {
        return actividadLst;
    }

    public void setActividadLst(List<Actividad> actividadLst) {
        this.actividadLst = actividadLst;
    }

    public List<Maquinaria> getMaquinariaLst() {
        return maquinariaLst;
    }

    public void setMaquinariaLst(List<Maquinaria> maquinariaLst) {
        this.maquinariaLst = maquinariaLst;
    }

    public List<Proyecto> getProyectoLst() {
        return proyectoLst;
    }

    public void setProyectoLst(List<Proyecto> proyectoLst) {
        this.proyectoLst = proyectoLst;
    }

    public List<Programa> getProgramaLst() {
        return programaLst;
    }

    public void setProgramaLst(List<Programa> programaLst) {
        this.programaLst = programaLst;
    }

    public ContratoTramo getCurContratoTramo() {
        return curContratoTramo;
    }

    public void setCurContratoTramo(ContratoTramo curContratoTramo) {
        this.editContratoTramo = true;
        this.curContratoTramo = new ContratoTramo();
        this.curContratoTramo.setNumero(curContratoTramo.getNumero());
        this.curContratoTramo.setIdentificador(curContratoTramo.getIdentificador());
        this.curContratoTramo.setTramo(curContratoTramo.getTramo());
        this.curContratoTramo.setCodigovia(curContratoTramo.getCodigovia());
        this.curContratoTramo.setPrInicial(curContratoTramo.getPrInicial());
        this.curContratoTramo.setPrFinal(curContratoTramo.getPrFinal());
        this.curContratoTramo.setDisIncial(curContratoTramo.getDisIncial());
        this.curContratoTramo.setDisFinal(curContratoTramo.getDisFinal());
    }

    public ContratoActividad getCurContratoActividad() {
        return curContratoActividad;
    }

    public void setCurContratoActividad(ContratoActividad curContratoActividad) {
        this.editContratoActividad = true;
        this.curContratoActividad = new ContratoActividad();
        this.curContratoActividad.setCantidad(curContratoActividad.getCantidad());
        this.curContratoActividad.setFkActividad(new Actividad(curContratoActividad.getFkActividad().getIdentificador()));
        this.curContratoActividad.setIdentificador(curContratoActividad.getIdentificador());
    }

    public ContratoMaquinaria getCurContratoMaquinaria() {
        return curContratoMaquinaria;
    }

    public void setCurContratoMaquinaria(ContratoMaquinaria curContratoMaquinaria) {
        this.editContratoMaquinaria = true;
        this.curContratoMaquinaria = new ContratoMaquinaria();
        this.curContratoMaquinaria.setIdentificador(curContratoMaquinaria.getIdentificador());
        this.curContratoMaquinaria.setFkMaquinaria(curContratoMaquinaria.getFkMaquinaria());
        this.curContratoMaquinaria.setCantidad(curContratoMaquinaria.getCantidad());
    }

    public ArrayList<SelectItem> getListaAnos() {
        return listaAnos;
    }

    public void setListaAnos(ArrayList<SelectItem> listaAnos) {
        this.listaAnos = listaAnos;
    }

    public List<Tercero> getTerceroLst() {
        return terceroLst;
    }

    public void setTerceroLst(List<Tercero> terceroLst) {
        this.terceroLst = terceroLst;
    }

    public ContratoTerceroObra getCurContratoTerceroObra() {
        return curContratoTerceroObra;
    }

    public void setCurContratoTerceroObra(ContratoTerceroObra curContratoTerceroObra) {
        this.curContratoTerceroObra = curContratoTerceroObra;
    }

    public ContratoTerceroInter getCurContratoTerceroInter() {
        return curContratoTerceroInter;
    }

    public void setCurContratoTerceroInter(ContratoTerceroInter curContratoTerceroInter) {
        this.curContratoTerceroInter = curContratoTerceroInter;
    }

    public List<UnidadEjecutora> getUnidadEjecutoraLst() {
        return unidadEjecutoraLst;
    }

    public void setUnidadEjecutoraLst(List<UnidadEjecutora> unidadEjecutoraLst) {
        this.unidadEjecutoraLst = unidadEjecutoraLst;
    }

    public List<DireccionTerritorial> getDireccionTerritorialLst() {
        return direccionTerritorialLst;
    }

    public void setDireccionTerritorialLst(List<DireccionTerritorial> direccionTerritorialLst) {
        this.direccionTerritorialLst = direccionTerritorialLst;
    }

    public ContratoProrrogaObra getCurProrrogaObra() {
        return curProrrogaObra;
    }

    public void setCurProrrogaObra(ContratoProrrogaObra curProrrogaObra) {
        this.curProrrogaObra = curProrrogaObra;
    }

    public ContratoProrrogaInter getCurProrrogaInter() {
        return curProrrogaInter;
    }

    public void setCurProrrogaInter(ContratoProrrogaInter curProrrogaInter) {
        this.curProrrogaInter = curProrrogaInter;
    }

    public ContratoAdicionObra getCurAdicionObra() {
        return curAdicionObra;
    }

    public void setCurAdicionObra(ContratoAdicionObra curAdicionObra) {
        this.curAdicionObra = curAdicionObra;
    }

    public ContratoAdicionInter getCurAdicionInter() {
        return curAdicionInter;
    }

    public void setCurAdicionInter(ContratoAdicionInter curAdicionInter) {
        this.curAdicionInter = curAdicionInter;
    }

    public List<Ajuste> getAjusteLst() {
        return ajusteLst;
    }

    public void setAjusteLst(List<Ajuste> ajusteLst) {
        this.ajusteLst = ajusteLst;
    }

    public ContratoFinancieroObra getCurContratoFinancieroObra() {

        return curContratoFinancieroObra;

    }

    public void setCurContratoFinancieroObra(ContratoFinancieroObra curContratoFinancieroObra) {
        this.editContratoFinancieroObra = true;
        this.curContratoFinancieroObra = new ContratoFinancieroObra();
        this.curContratoFinancieroObra.setAno(curContratoFinancieroObra.getAno());
        this.curContratoFinancieroObra.setCorte(curContratoFinancieroObra.getCorte());
        this.curContratoFinancieroObra.setValor(curContratoFinancieroObra.getValor());
        this.curContratoFinancieroObra.setIdentificador(curContratoFinancieroObra.getIdentificador());

    }

    public Integer getSumaParticipacionObra() {
        return sumaParticipacionObra;
    }

    public void setSumaParticipacionObra(Integer sumaParticipacionObra) {
        this.sumaParticipacionObra = sumaParticipacionObra;
    }

    public String getSumaParticipacionObraOk() {
        return sumaParticipacionObraOk;
    }

    public void setSumaParticipacionObraOk(String sumaParticipacionObraOk) {
        this.sumaParticipacionObraOk = sumaParticipacionObraOk;
    }

    public ContratoAjusteObra getCurAjusteObra() {
        return curAjusteObra;
    }

    public void setCurAjusteObra(ContratoAjusteObra curAjusteObra) {
        this.curAjusteObra = curAjusteObra;
    }

    public ContratoAjusteInter getCurAjusteInter() {
        return curAjusteInter;
    }

    public void setCurAjusteInter(ContratoAjusteInter curAjusteInter) {
        this.curAjusteInter = curAjusteInter;
    }

    public BigDecimal getSumaVigenciaObra() {
        return sumaVigenciaObra;
    }

    public void setSumaVigenciaObra(BigDecimal sumaVigenciaObra) {
        this.sumaVigenciaObra = sumaVigenciaObra;
    }

    public BigDecimal getSumaAdicionObra() {
        return sumaAdicionObra;
    }

    public void setSumaAdicionObra(BigDecimal sumaAdicionObra) {
        this.sumaAdicionObra = sumaAdicionObra;
    }

    public BigDecimal getSumaProrrogaObra() {
        return sumaProrrogaObra;
    }

    public void setSumaProrrogaObra(BigDecimal sumaProrrogaObra) {
        this.sumaProrrogaObra = sumaProrrogaObra;
    }

    public String getSumaParticipacionInterOk() {
        return sumaParticipacionInterOk;
    }

    public void setSumaParticipacionInterOk(String sumaParticipacionInterOk) {
        this.sumaParticipacionInterOk = sumaParticipacionInterOk;
    }

    public Integer getSumaParticipacionInter() {
        return sumaParticipacionInter;
    }

    public void setSumaParticipacionInter(Integer sumaParticipacionInter) {
        this.sumaParticipacionInter = sumaParticipacionInter;
    }

    public BigDecimal getSumaVigenciaInter() {
        return sumaVigenciaInter;
    }

    public void setSumaVigenciaInter(BigDecimal sumaVigenciaInter) {
        this.sumaVigenciaInter = sumaVigenciaInter;
    }

    public BigDecimal getSumaAdicionInter() {
        return sumaAdicionInter;
    }

    public void setSumaAdicionInter(BigDecimal sumaAdicionInter) {
        this.sumaAdicionInter = sumaAdicionInter;
    }

    public BigDecimal getSumaProrrogaInter() {
        return sumaProrrogaInter;
    }

    public void setSumaProrrogaInter(BigDecimal sumaProrrogaInter) {
        this.sumaProrrogaInter = sumaProrrogaInter;
    }

    public ContratoVigenciaObra getCurVigenciaObra() {
        return curVigenciaObra;
    }

    public void setCurVigenciaObra(ContratoVigenciaObra curVigenciaObra) {
        this.curVigenciaObra = curVigenciaObra;
    }

    public BigDecimal getSumaFinancieroObra() {
        return sumaFinancieroObra;
    }

    public void setSumaFinancieroObra(BigDecimal sumaFinancieroObra) {
        this.sumaFinancieroObra = sumaFinancieroObra;
    }

    public List<Usuario> getUsuarioGestorLst() {
        return usuarioGestorLst;
    }

    public void setUsuarioGestorLst(List<Usuario> usuarioGestorLst) {
        this.usuarioGestorLst = usuarioGestorLst;
    }

    public List<Usuario> getUsuarioSupervisorLst() {
        return usuarioSupervisorLst;
    }

    public void setUsuarioSupervisorLst(List<Usuario> usuarioSupervisorLst) {
        this.usuarioSupervisorLst = usuarioSupervisorLst;
    }

    public BigDecimal getSumaFacturadoObra() {
        return sumaFacturadoObra;
    }

    public void setSumaFacturadoObra(BigDecimal sumaFacturadoObra) {
        this.sumaFacturadoObra = sumaFacturadoObra;
    }

    public BigDecimal totalValorContrato() {
        BigDecimal totalValorContrato = new BigDecimal(BigInteger.ZERO);
        totalValorContrato.add(this.contrato.getContObraValorInicial());
        totalValorContrato.add(this.sumaAdicionObra);
        return totalValorContrato;
    }

    public BigDecimal totalValorVigencia() {
        BigDecimal totalValorVigencia = new BigDecimal(BigInteger.ZERO);
        totalValorVigencia.add(sumaVigenciaObra);
        totalValorVigencia.add(this.sumaAdicionObra);
        return totalValorVigencia;
    }

    public boolean isEditContratoFinancieroObra() {
        return editContratoFinancieroObra;
    }

    public void setEditContratoFinancieroObra(boolean editContrtoFinancieroObra) {
        this.editContratoFinancieroObra = editContrtoFinancieroObra;
    }

    public boolean isEditContratoActividad() {
        return editContratoActividad;
    }

    public void setEditContratoActividad(boolean editContratoActividad) {
        this.editContratoActividad = editContratoActividad;
    }

    public boolean isEditContratoTramo() {
        return editContratoTramo;
    }

    public void setEditContratoTramo(boolean editContratoTramo) {
        this.editContratoTramo = editContratoTramo;
    }

    public boolean isEditContratoMaquinaria() {
        return editContratoMaquinaria;
    }

    public void setEditContratoMaquinaria(boolean editContratoMaquinaria) {
        this.editContratoMaquinaria = editContratoMaquinaria;
    }

    public boolean isNewContrato() {
        return newContrato;
    }

    public void setNewContrato(boolean newContrato) {
        this.newContrato = newContrato;
    }

}
