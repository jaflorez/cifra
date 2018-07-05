/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jaflorez
 */
@Entity
@Table(name = "reporte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reporte.findAll", query = "SELECT r FROM Reporte r")
    , @NamedQuery(name = "Reporte.findByIdentificador", query = "SELECT r FROM Reporte r WHERE r.identificador = :identificador")
    , @NamedQuery(name = "Reporte.findByNumeroreporte", query = "SELECT r FROM Reporte r WHERE r.numeroreporte = :numeroreporte")
    , @NamedQuery(name = "Reporte.findByNumerosemana", query = "SELECT r FROM Reporte r WHERE r.numerosemana = :numerosemana")
    , @NamedQuery(name = "Reporte.findByReportedesde", query = "SELECT r FROM Reporte r WHERE r.reportedesde = :reportedesde")
    , @NamedQuery(name = "Reporte.findByReportehasta", query = "SELECT r FROM Reporte r WHERE r.reportehasta = :reportehasta")
    , @NamedQuery(name = "Reporte.findByObrPresentoFactura", query = "SELECT r FROM Reporte r WHERE r.obrPresentoFactura = :obrPresentoFactura")
    , @NamedQuery(name = "Reporte.findByObrValorInversion", query = "SELECT r FROM Reporte r WHERE r.obrValorInversion = :obrValorInversion")
    , @NamedQuery(name = "Reporte.findByObrValorFacturado", query = "SELECT r FROM Reporte r WHERE r.obrValorFacturado = :obrValorFacturado")
    , @NamedQuery(name = "Reporte.findByObrFechaFactura", query = "SELECT r FROM Reporte r WHERE r.obrFechaFactura = :obrFechaFactura")
    , @NamedQuery(name = "Reporte.findByObrAvanceFisico", query = "SELECT r FROM Reporte r WHERE r.obrAvanceFisico = :obrAvanceFisico")
    , @NamedQuery(name = "Reporte.findByObrReporteSiif", query = "SELECT r FROM Reporte r WHERE r.obrReporteSiif = :obrReporteSiif")
    , @NamedQuery(name = "Reporte.findByIntPresentoFactura", query = "SELECT r FROM Reporte r WHERE r.intPresentoFactura = :intPresentoFactura")
    , @NamedQuery(name = "Reporte.findByIntValorFactura", query = "SELECT r FROM Reporte r WHERE r.intValorFactura = :intValorFactura")
    , @NamedQuery(name = "Reporte.findByIntFechaFactura", query = "SELECT r FROM Reporte r WHERE r.intFechaFactura = :intFechaFactura")
    , @NamedQuery(name = "Reporte.findByIntInvProgramada", query = "SELECT r FROM Reporte r WHERE r.intInvProgramada = :intInvProgramada")
    , @NamedQuery(name = "Reporte.findByIntMesFacturado", query = "SELECT r FROM Reporte r WHERE r.intMesFacturado = :intMesFacturado")
    , @NamedQuery(name = "Reporte.findByIntReporteSiif", query = "SELECT r FROM Reporte r WHERE r.intReporteSiif = :intReporteSiif")
    , @NamedQuery(name = "Reporte.findBySiifFecha", query = "SELECT r FROM Reporte r WHERE r.siifFecha = :siifFecha")
    , @NamedQuery(name = "Reporte.findByActividadesRealizadas", query = "SELECT r FROM Reporte r WHERE r.actividadesRealizadas = :actividadesRealizadas")
    , @NamedQuery(name = "Reporte.findByActividadesProxima", query = "SELECT r FROM Reporte r WHERE r.actividadesProxima = :actividadesProxima")
    , @NamedQuery(name = "Reporte.findByResumen", query = "SELECT r FROM Reporte r WHERE r.resumen = :resumen")
    , @NamedQuery(name = "Reporte.findByActividadARealizar", query = "SELECT r FROM Reporte r WHERE r.actividadARealizar = :actividadARealizar")
    , @NamedQuery(name = "Reporte.findByActividadRealizada", query = "SELECT r FROM Reporte r WHERE r.actividadRealizada = :actividadRealizada")
    , @NamedQuery(name = "Reporte.findByGesoSede", query = "SELECT r FROM Reporte r WHERE r.gesoSede = :gesoSede")
    , @NamedQuery(name = "Reporte.findByGesoNocapacitaciones", query = "SELECT r FROM Reporte r WHERE r.gesoNocapacitaciones = :gesoNocapacitaciones")
    , @NamedQuery(name = "Reporte.findByGesoNosocializaciones", query = "SELECT r FROM Reporte r WHERE r.gesoNosocializaciones = :gesoNosocializaciones")
    , @NamedQuery(name = "Reporte.findByGesoEmpindirgen", query = "SELECT r FROM Reporte r WHERE r.gesoEmpindirgen = :gesoEmpindirgen")
    , @NamedQuery(name = "Reporte.findByGesoEmpdirgen", query = "SELECT r FROM Reporte r WHERE r.gesoEmpdirgen = :gesoEmpdirgen")
    , @NamedQuery(name = "Reporte.findByGesoEmpgen", query = "SELECT r FROM Reporte r WHERE r.gesoEmpgen = :gesoEmpgen")
    , @NamedQuery(name = "Reporte.findByGesoObservaciones", query = "SELECT r FROM Reporte r WHERE r.gesoObservaciones = :gesoObservaciones")
    , @NamedQuery(name = "Reporte.findByGeprObservaciones", query = "SELECT r FROM Reporte r WHERE r.geprObservaciones = :geprObservaciones")
    , @NamedQuery(name = "Reporte.findByGeprPredadquiridos", query = "SELECT r FROM Reporte r WHERE r.geprPredadquiridos = :geprPredadquiridos")
    , @NamedQuery(name = "Reporte.findByGeprAvaluos", query = "SELECT r FROM Reporte r WHERE r.geprAvaluos = :geprAvaluos")
    , @NamedQuery(name = "Reporte.findByGeprFichasaprob", query = "SELECT r FROM Reporte r WHERE r.geprFichasaprob = :geprFichasaprob")
    , @NamedQuery(name = "Reporte.findByGeprPredadquirir", query = "SELECT r FROM Reporte r WHERE r.geprPredadquirir = :geprPredadquirir")
    , @NamedQuery(name = "Reporte.findByPagaObservaciones", query = "SELECT r FROM Reporte r WHERE r.pagaObservaciones = :pagaObservaciones")
    , @NamedQuery(name = "Reporte.findByPagaEstado", query = "SELECT r FROM Reporte r WHERE r.pagaEstado = :pagaEstado")
    , @NamedQuery(name = "Reporte.findByAmbObservaciones", query = "SELECT r FROM Reporte r WHERE r.ambObservaciones = :ambObservaciones")
    , @NamedQuery(name = "Reporte.findByAmbEmiciones", query = "SELECT r FROM Reporte r WHERE r.ambEmiciones = :ambEmiciones")
    , @NamedQuery(name = "Reporte.findByAmbVertimentos", query = "SELECT r FROM Reporte r WHERE r.ambVertimentos = :ambVertimentos")
    , @NamedQuery(name = "Reporte.findByAmbOcupacioncauces", query = "SELECT r FROM Reporte r WHERE r.ambOcupacioncauces = :ambOcupacioncauces")
    , @NamedQuery(name = "Reporte.findByAmbConcecionaguas", query = "SELECT r FROM Reporte r WHERE r.ambConcecionaguas = :ambConcecionaguas")
    , @NamedQuery(name = "Reporte.findByAmbAprovforestal", query = "SELECT r FROM Reporte r WHERE r.ambAprovforestal = :ambAprovforestal")
    , @NamedQuery(name = "Reporte.findByAmbLicenciambiental", query = "SELECT r FROM Reporte r WHERE r.ambLicenciambiental = :ambLicenciambiental")
    , @NamedQuery(name = "Reporte.findByAmbBotaderos", query = "SELECT r FROM Reporte r WHERE r.ambBotaderos = :ambBotaderos")
    , @NamedQuery(name = "Reporte.findByFtmatObservaciones", query = "SELECT r FROM Reporte r WHERE r.ftmatObservaciones = :ftmatObservaciones")
    , @NamedQuery(name = "Reporte.findByFechaLimitePresentacion", query = "SELECT r FROM Reporte r WHERE r.fechaLimitePresentacion = :fechaLimitePresentacion")
    , @NamedQuery(name = "Reporte.findByFechaPresentacionReporte", query = "SELECT r FROM Reporte r WHERE r.fechaPresentacionReporte = :fechaPresentacionReporte")
    , @NamedQuery(name = "Reporte.findByFechaRegistroInicial", query = "SELECT r FROM Reporte r WHERE r.fechaRegistroInicial = :fechaRegistroInicial")
    , @NamedQuery(name = "Reporte.findBySupervisorFechaRevision", query = "SELECT r FROM Reporte r WHERE r.supervisorFechaRevision = :supervisorFechaRevision")
    , @NamedQuery(name = "Reporte.findBySupervisorObservaciones", query = "SELECT r FROM Reporte r WHERE r.supervisorObservaciones = :supervisorObservaciones")
    , @NamedQuery(name = "Reporte.findByGestorObservaciones", query = "SELECT r FROM Reporte r WHERE r.gestorObservaciones = :gestorObservaciones")
    , @NamedQuery(name = "Reporte.findByGestorFechaRevision", query = "SELECT r FROM Reporte r WHERE r.gestorFechaRevision = :gestorFechaRevision")
    , @NamedQuery(name = "Reporte.findByFkUsuarioreporto", query = "SELECT r FROM Reporte r WHERE r.fkUsuarioreporto = :fkUsuarioreporto")
    , @NamedQuery(name = "Reporte.findByFkCtoFinancieroObra", query = "SELECT r FROM Reporte r WHERE r.fkCtoFinancieroObra = :fkCtoFinancieroObra")
    , @NamedQuery(name = "Reporte.findByFkCtoFinancieroInter", query = "SELECT r FROM Reporte r WHERE r.fkCtoFinancieroInter = :fkCtoFinancieroInter")
    , @NamedQuery(name = "Reporte.findByFkUsuarioGestor", query = "SELECT r FROM Reporte r WHERE r.fkUsuarioGestor = :fkUsuarioGestor")
    , @NamedQuery(name = "Reporte.findByFkUsuarioSupervisor", query = "SELECT r FROM Reporte r WHERE r.fkUsuarioSupervisor = :fkUsuarioSupervisor")
    , @NamedQuery(name = "Reporte.findByNombreFirma", query = "SELECT r FROM Reporte r WHERE r.nombreFirma = :nombreFirma")
    , @NamedQuery(name = "Reporte.findByMatriculaProfesional", query = "SELECT r FROM Reporte r WHERE r.matriculaProfesional = :matriculaProfesional")})
public class Reporte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDENTIFICADOR")
    private Integer identificador;
    @Basic(optional = false)
    @Column(name = "NUMEROREPORTE")
    private int numeroreporte;
    @Basic(optional = false)
    @Column(name = "NUMEROSEMANA")
    private int numerosemana;
    @Basic(optional = false)
    @Column(name = "REPORTEDESDE")
    @Temporal(TemporalType.DATE)
    private Date reportedesde;
    @Basic(optional = false)
    @Column(name = "REPORTEHASTA")
    @Temporal(TemporalType.DATE)
    private Date reportehasta;
    @Column(name = "OBR_PRESENTO_FACTURA")
    private Boolean obrPresentoFactura;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "OBR_VALOR_INVERSION")
    private BigDecimal obrValorInversion;
    @Column(name = "OBR_VALOR_ESTUDIOS")
    private BigDecimal obrValorEstudios;
    @Column(name = "OBR_VALOR_PREDIAL")
    private BigDecimal obrValorPredial;
    @Column(name = "OBR_VALOR_AMBIENTAL")
    private BigDecimal obrValorAmbiental;
    @Column(name = "OBR_VALOR_AJUSTES")
    private BigDecimal obrValorAjustes;
    @Column(name = "OBR_VALOR_IVA")
    private BigDecimal obrValorIva;
    @Column(name = "OBR_VALOR_OTROS")
    private BigDecimal obrValorOtros;    
    @Column(name = "OBR_VALOR_AMORTIZACION")
    private BigDecimal obrValorAmortizacion;
    @Column(name = "OBR_VALOR_FACTURADO")
    private BigDecimal obrValorFacturado;
    @Column(name = "OBR_FECHA_FACTURA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date obrFechaFactura;
    @Column(name = "OBR_AVANCE_FISICO")
    private BigDecimal obrAvanceFisico;
    @Column(name = "OBR_REPORTE_SIIF")
    private BigDecimal obrReporteSiif;
    @Column(name = "INT_PRESENTO_FACTURA")
    private Boolean intPresentoFactura;
    @Column(name = "INT_VALOR_FACTURA")
    private BigDecimal intValorFactura;
    @Column(name = "INT_FECHA_FACTURA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date intFechaFactura;
    @Column(name = "INT_INV_PROGRAMADA")
    private BigDecimal intInvProgramada;
    @Column(name = "INT_MES_FACTURADO")
    private Integer intMesFacturado;
    @Column(name = "INT_REPORTE_SIIF")
    private BigDecimal intReporteSiif;
    @Column(name = "SIIF_FECHA")
    @Temporal(TemporalType.DATE)
    private Date siifFecha;
    @Column(name = "ACTIVIDADES_REALIZADAS")
    private String actividadesRealizadas;
    @Column(name = "ACTIVIDADES_PROXIMA")
    private String actividadesProxima;
    @Column(name = "RESUMEN")
    private String resumen;
    @Column(name = "ACTIVIDAD_A_REALIZAR")
    private String actividadARealizar;
    @Column(name = "ACTIVIDAD_REALIZADA")
    private String actividadRealizada;
    @Column(name = "GESO_SEDE")
    private String gesoSede;
    @Column(name = "GESO_NOCAPACITACIONES")
    private Integer gesoNocapacitaciones;
    @Column(name = "GESO_NOSOCIALIZACIONES")
    private Integer gesoNosocializaciones;
    @Column(name = "GESO_EMPINDIRGEN")
    private Integer gesoEmpindirgen;
    @Column(name = "GESO_EMPDIRGEN")
    private Integer gesoEmpdirgen;
    @Column(name = "GESO_EMPGEN")
    private Integer gesoEmpgen;
    @Column(name = "GESO_OBSERVACIONES")
    private String gesoObservaciones;
    @Column(name = "GEPR_OBSERVACIONES")
    private String geprObservaciones;
    @Column(name = "GEPR_PREDADQUIRIDOS")
    private Integer geprPredadquiridos;
    @Column(name = "GEPR_AVALUOS")
    private Integer geprAvaluos;
    @Column(name = "GEPR_FICHASAPROB")
    private Integer geprFichasaprob;
    @Column(name = "GEPR_PREDADQUIRIR")
    private Integer geprPredadquirir;
    @Column(name = "PAGA_OBSERVACIONES")
    private String pagaObservaciones;
    @Column(name = "PAGA_ESTADO")
    private String pagaEstado;
    @Column(name = "AMB_OBSERVACIONES")
    private String ambObservaciones;
    @Column(name = "AMB_EMICIONES")
    private Integer ambEmiciones;
    @Column(name = "AMB_VERTIMENTOS")
    private Integer ambVertimentos;
    @Column(name = "AMB_OCUPACIONCAUCES")
    private Integer ambOcupacioncauces;
    @Column(name = "AMB_CONCECIONAGUAS")
    private Integer ambConcecionaguas;
    @Column(name = "AMB_APROVFORESTAL")
    private Integer ambAprovforestal;
    @Column(name = "AMB_LICENCIAMBIENTAL")
    private Integer ambLicenciambiental;
    @Column(name = "AMB_BOTADEROS")
    private Integer ambBotaderos;
    @Column(name = "FTMAT_OBSERVACIONES")
    private String ftmatObservaciones;
    @Column(name = "FECHA_LIMITE_PRESENTACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaLimitePresentacion;
    @Column(name = "FECHA_PRESENTACION_REPORTE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPresentacionReporte;
    @Column(name = "FECHA_REGISTRO_INICIAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistroInicial;
    @Column(name = "SUPERVISOR_FECHA_REVISION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date supervisorFechaRevision;
    @Column(name = "SUPERVISOR_OBSERVACIONES")
    private String supervisorObservaciones;
    @Column(name = "GESTOR_OBSERVACIONES")
    private String gestorObservaciones;
    @Column(name = "GESTOR_FECHA_REVISION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date gestorFechaRevision;
    @Basic(optional = false)
    @Column(name = "FK_USUARIOREPORTO")
    private int fkUsuarioreporto;
    @Column(name = "FK_CTO_FINANCIERO_OBRA")
    private Integer fkCtoFinancieroObra;
    @Column(name = "FK_CTO_FINANCIERO_INTER")
    private Integer fkCtoFinancieroInter;
    @Column(name = "FK_ESTADO_CONTRATO")
    private Integer fkEstadoContrato;
    @Column(name = "FK_USUARIO_GESTOR")
    private Integer fkUsuarioGestor;
    @Column(name = "FK_USUARIO_SUPERVISOR")
    private Integer fkUsuarioSupervisor;
    @Column(name = "NOMBRE_FIRMA")
    private String nombreFirma;
    @Column(name = "MATRICULA_PROFESIONAL")
    private String matriculaProfesional;
    @JoinColumn(name = "FK_CONTRATO", referencedColumnName = "IDENTIFICADOR")
    @ManyToOne(optional = false)
    private Contrato fkContrato;
    @JoinColumn(name = "FK_ESTADO", referencedColumnName = "IDENTIFICADOR")
    @ManyToOne(optional = false)
    private Estado fkEstado;
    @OneToMany(mappedBy = "fkReporte", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ReporteArchivo> reporteArchivoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkReporte", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ReporteObservacion> reporteObservacionList;
    @OneToMany(mappedBy = "fkReporte", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ReporteActividad> reporteActividadList;
    @OneToMany(mappedBy = "fkReporte", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ReporteMaquinaria> reporteMaquinariaList;
    @OneToMany(mappedBy = "fkReporte", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ReporteMateriales> reporteMaterialesList;

    public Reporte() {
    }

    public Reporte(Integer identificador) {
        this.identificador = identificador;
    }

    public Reporte(Integer identificador, int numeroreporte, int numerosemana, Date reportedesde, Date reportehasta, int fkUsuarioreporto) {
        this.identificador = identificador;
        this.numeroreporte = numeroreporte;
        this.numerosemana = numerosemana;
        this.reportedesde = reportedesde;
        this.reportehasta = reportehasta;
        this.fkUsuarioreporto = fkUsuarioreporto;
    }

    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }

    public int getNumeroreporte() {
        return numeroreporte;
    }

    public void setNumeroreporte(int numeroreporte) {
        this.numeroreporte = numeroreporte;
    }

    public int getNumerosemana() {
        return numerosemana;
    }

    public void setNumerosemana(int numerosemana) {
        this.numerosemana = numerosemana;
    }

    public Date getReportedesde() {
        return reportedesde;
    }

    public void setReportedesde(Date reportedesde) {
        this.reportedesde = reportedesde;
    }

    public Date getReportehasta() {
        return reportehasta;
    }

    public void setReportehasta(Date reportehasta) {
        this.reportehasta = reportehasta;
    }

    public Boolean getObrPresentoFactura() {
        return obrPresentoFactura;
    }

    public void setObrPresentoFactura(Boolean obrPresentoFactura) {
        this.obrPresentoFactura = obrPresentoFactura;
    }

    public BigDecimal getObrValorInversion() {
        return obrValorInversion;
    }

    public void setObrValorInversion(BigDecimal obrValorInversion) {
        this.obrValorInversion = obrValorInversion;
    }

    public BigDecimal getObrValorEstudios() {
        return obrValorEstudios;
    }

    public void setObrValorEstudios(BigDecimal obrValorEstudios) {
        this.obrValorEstudios = obrValorEstudios;
    }

    public BigDecimal getObrValorPredial() {
        return obrValorPredial;
    }

    public void setObrValorPredial(BigDecimal obrValorPredial) {
        this.obrValorPredial = obrValorPredial;
    }

    public BigDecimal getObrValorAmbiental() {
        return obrValorAmbiental;
    }

    public void setObrValorAmbiental(BigDecimal obrValorAmbiental) {
        this.obrValorAmbiental = obrValorAmbiental;
    }

    public BigDecimal getObrValorAjustes() {
        return obrValorAjustes;
    }

    public void setObrValorAjustes(BigDecimal obrValorAjustes) {
        this.obrValorAjustes = obrValorAjustes;
    }

    public BigDecimal getObrValorIva() {
        return obrValorIva;
    }

    public void setObrValorIva(BigDecimal obrValorIva) {
        this.obrValorIva = obrValorIva;
    }

    public BigDecimal getObrValorOtros() {
        return obrValorOtros;
    }

    public void setObrValorOtros(BigDecimal obrValorOtros) {
        this.obrValorOtros = obrValorOtros;
    }

    public BigDecimal getObrValorAmortizacion() {
        return obrValorAmortizacion;
    }

    public void setObrValorAmortizacion(BigDecimal obrValorAmortizacion) {
        this.obrValorAmortizacion = obrValorAmortizacion;
    }

    
    
    public BigDecimal getObrValorFacturado() {
        return obrValorFacturado;
    }

    public void setObrValorFacturado(BigDecimal obrValorFacturado) {
        this.obrValorFacturado = obrValorFacturado;
    }

    public Date getObrFechaFactura() {
        return obrFechaFactura;
    }

    public void setObrFechaFactura(Date obrFechaFactura) {
        this.obrFechaFactura = obrFechaFactura;
    }

    public BigDecimal getObrAvanceFisico() {
        return obrAvanceFisico;
    }

    public void setObrAvanceFisico(BigDecimal obrAvanceFisico) {
        this.obrAvanceFisico = obrAvanceFisico;
    }

    public BigDecimal getObrReporteSiif() {
        return obrReporteSiif;
    }

    public void setObrReporteSiif(BigDecimal obrReporteSiif) {
        this.obrReporteSiif = obrReporteSiif;
    }

    public Boolean getIntPresentoFactura() {
        return intPresentoFactura;
    }

    public void setIntPresentoFactura(Boolean intPresentoFactura) {
        this.intPresentoFactura = intPresentoFactura;
    }

    public BigDecimal getIntValorFactura() {
        return intValorFactura;
    }

    public void setIntValorFactura(BigDecimal intValorFactura) {
        this.intValorFactura = intValorFactura;
    }

    public Date getIntFechaFactura() {
        return intFechaFactura;
    }

    public void setIntFechaFactura(Date intFechaFactura) {
        this.intFechaFactura = intFechaFactura;
    }

    public BigDecimal getIntInvProgramada() {
        return intInvProgramada;
    }

    public void setIntInvProgramada(BigDecimal intInvProgramada) {
        this.intInvProgramada = intInvProgramada;
    }

    public Integer getIntMesFacturado() {
        return intMesFacturado;
    }

    public void setIntMesFacturado(Integer intMesFacturado) {
        this.intMesFacturado = intMesFacturado;
    }

    public BigDecimal getIntReporteSiif() {
        return intReporteSiif;
    }

    public void setIntReporteSiif(BigDecimal intReporteSiif) {
        this.intReporteSiif = intReporteSiif;
    }

    public Date getSiifFecha() {
        return siifFecha;
    }

    public void setSiifFecha(Date siifFecha) {
        this.siifFecha = siifFecha;
    }

    public String getActividadesRealizadas() {
        return actividadesRealizadas;
    }

    public void setActividadesRealizadas(String actividadesRealizadas) {
        this.actividadesRealizadas = actividadesRealizadas;
    }

    public String getActividadesProxima() {
        return actividadesProxima;
    }

    public void setActividadesProxima(String actividadesProxima) {
        this.actividadesProxima = actividadesProxima;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getActividadARealizar() {
        return actividadARealizar;
    }

    public void setActividadARealizar(String actividadARealizar) {
        this.actividadARealizar = actividadARealizar;
    }

    public String getActividadRealizada() {
        return actividadRealizada;
    }

    public void setActividadRealizada(String actividadRealizada) {
        this.actividadRealizada = actividadRealizada;
    }

    public String getGesoSede() {
        return gesoSede;
    }

    public void setGesoSede(String gesoSede) {
        this.gesoSede = gesoSede;
    }

    public Integer getGesoNocapacitaciones() {
        return gesoNocapacitaciones;
    }

    public void setGesoNocapacitaciones(Integer gesoNocapacitaciones) {
        this.gesoNocapacitaciones = gesoNocapacitaciones;
    }

    public Integer getGesoNosocializaciones() {
        return gesoNosocializaciones;
    }

    public void setGesoNosocializaciones(Integer gesoNosocializaciones) {
        this.gesoNosocializaciones = gesoNosocializaciones;
    }

    public Integer getGesoEmpindirgen() {
        return gesoEmpindirgen;
    }

    public void setGesoEmpindirgen(Integer gesoEmpindirgen) {
        this.gesoEmpindirgen = gesoEmpindirgen;
    }

    public Integer getGesoEmpdirgen() {
        return gesoEmpdirgen;
    }

    public void setGesoEmpdirgen(Integer gesoEmpdirgen) {
        this.gesoEmpdirgen = gesoEmpdirgen;
    }

    public Integer getGesoEmpgen() {
        return gesoEmpgen;
    }

    public void setGesoEmpgen(Integer gesoEmpgen) {
        this.gesoEmpgen = gesoEmpgen;
    }

    public String getGesoObservaciones() {
        return gesoObservaciones;
    }

    public void setGesoObservaciones(String gesoObservaciones) {
        this.gesoObservaciones = gesoObservaciones;
    }

    public String getGeprObservaciones() {
        return geprObservaciones;
    }

    public void setGeprObservaciones(String geprObservaciones) {
        this.geprObservaciones = geprObservaciones;
    }

    public Integer getGeprPredadquiridos() {
        return geprPredadquiridos;
    }

    public void setGeprPredadquiridos(Integer geprPredadquiridos) {
        this.geprPredadquiridos = geprPredadquiridos;
    }

    public Integer getGeprAvaluos() {
        return geprAvaluos;
    }

    public void setGeprAvaluos(Integer geprAvaluos) {
        this.geprAvaluos = geprAvaluos;
    }

    public Integer getGeprFichasaprob() {
        return geprFichasaprob;
    }

    public void setGeprFichasaprob(Integer geprFichasaprob) {
        this.geprFichasaprob = geprFichasaprob;
    }

    public Integer getGeprPredadquirir() {
        return geprPredadquirir;
    }

    public void setGeprPredadquirir(Integer geprPredadquirir) {
        this.geprPredadquirir = geprPredadquirir;
    }

    public String getPagaObservaciones() {
        return pagaObservaciones;
    }

    public void setPagaObservaciones(String pagaObservaciones) {
        this.pagaObservaciones = pagaObservaciones;
    }

    public String getPagaEstado() {
        return pagaEstado;
    }

    public void setPagaEstado(String pagaEstado) {
        this.pagaEstado = pagaEstado;
    }

    public String getAmbObservaciones() {
        return ambObservaciones;
    }

    public void setAmbObservaciones(String ambObservaciones) {
        this.ambObservaciones = ambObservaciones;
    }

    public Integer getAmbEmiciones() {
        return ambEmiciones;
    }

    public void setAmbEmiciones(Integer ambEmiciones) {
        this.ambEmiciones = ambEmiciones;
    }

    public Integer getAmbVertimentos() {
        return ambVertimentos;
    }

    public void setAmbVertimentos(Integer ambVertimentos) {
        this.ambVertimentos = ambVertimentos;
    }

    public Integer getAmbOcupacioncauces() {
        return ambOcupacioncauces;
    }

    public void setAmbOcupacioncauces(Integer ambOcupacioncauces) {
        this.ambOcupacioncauces = ambOcupacioncauces;
    }

    public Integer getAmbConcecionaguas() {
        return ambConcecionaguas;
    }

    public void setAmbConcecionaguas(Integer ambConcecionaguas) {
        this.ambConcecionaguas = ambConcecionaguas;
    }

    public Integer getAmbAprovforestal() {
        return ambAprovforestal;
    }

    public void setAmbAprovforestal(Integer ambAprovforestal) {
        this.ambAprovforestal = ambAprovforestal;
    }

    public Integer getAmbLicenciambiental() {
        return ambLicenciambiental;
    }

    public void setAmbLicenciambiental(Integer ambLicenciambiental) {
        this.ambLicenciambiental = ambLicenciambiental;
    }

    public Integer getAmbBotaderos() {
        return ambBotaderos;
    }

    public void setAmbBotaderos(Integer ambBotaderos) {
        this.ambBotaderos = ambBotaderos;
    }

    public String getFtmatObservaciones() {
        return ftmatObservaciones;
    }

    public void setFtmatObservaciones(String ftmatObservaciones) {
        this.ftmatObservaciones = ftmatObservaciones;
    }

    public Date getFechaLimitePresentacion() {
        return fechaLimitePresentacion;
    }

    public void setFechaLimitePresentacion(Date fechaLimitePresentacion) {
        this.fechaLimitePresentacion = fechaLimitePresentacion;
    }

    public Date getFechaPresentacionReporte() {
        return fechaPresentacionReporte;
    }

    public void setFechaPresentacionReporte(Date fechaPresentacionReporte) {
        this.fechaPresentacionReporte = fechaPresentacionReporte;
    }

    public Date getFechaRegistroInicial() {
        return fechaRegistroInicial;
    }

    public void setFechaRegistroInicial(Date fechaRegistroInicial) {
        this.fechaRegistroInicial = fechaRegistroInicial;
    }

    public Date getSupervisorFechaRevision() {
        return supervisorFechaRevision;
    }

    public void setSupervisorFechaRevision(Date supervisorFechaRevision) {
        this.supervisorFechaRevision = supervisorFechaRevision;
    }

    public String getSupervisorObservaciones() {
        return supervisorObservaciones;
    }

    public void setSupervisorObservaciones(String supervisorObservaciones) {
        this.supervisorObservaciones = supervisorObservaciones;
    }

    public String getGestorObservaciones() {
        return gestorObservaciones;
    }

    public void setGestorObservaciones(String gestorObservaciones) {
        this.gestorObservaciones = gestorObservaciones;
    }

    public Date getGestorFechaRevision() {
        return gestorFechaRevision;
    }

    public void setGestorFechaRevision(Date gestorFechaRevision) {
        this.gestorFechaRevision = gestorFechaRevision;
    }

    public int getFkUsuarioreporto() {
        return fkUsuarioreporto;
    }

    public void setFkUsuarioreporto(int fkUsuarioreporto) {
        this.fkUsuarioreporto = fkUsuarioreporto;
    }

    public Integer getFkCtoFinancieroObra() {
        return fkCtoFinancieroObra;
    }

    public void setFkCtoFinancieroObra(Integer fkCtoFinancieroObra) {
        this.fkCtoFinancieroObra = fkCtoFinancieroObra;
    }

    public Integer getFkCtoFinancieroInter() {
        return fkCtoFinancieroInter;
    }

    public void setFkCtoFinancieroInter(Integer fkCtoFinancieroInter) {
        this.fkCtoFinancieroInter = fkCtoFinancieroInter;
    }

    public Integer getFkUsuarioGestor() {
        return fkUsuarioGestor;
    }

    public void setFkUsuarioGestor(Integer fkUsuarioGestor) {
        this.fkUsuarioGestor = fkUsuarioGestor;
    }

    public Integer getFkUsuarioSupervisor() {
        return fkUsuarioSupervisor;
    }

    public void setFkUsuarioSupervisor(Integer fkUsuarioSupervisor) {
        this.fkUsuarioSupervisor = fkUsuarioSupervisor;
    }

    public String getNombreFirma() {
        return nombreFirma;
    }

    public void setNombreFirma(String nombreFirma) {
        this.nombreFirma = nombreFirma;
    }

    public String getMatriculaProfesional() {
        return matriculaProfesional;
    }

    public void setMatriculaProfesional(String matriculaProfesional) {
        this.matriculaProfesional = matriculaProfesional;
    }

    public Contrato getFkContrato() {
        return fkContrato;
    }

    public void setFkContrato(Contrato fkContrato) {
        this.fkContrato = fkContrato;
    }

    public Estado getFkEstado() {
        return fkEstado;
    }

    public void setFkEstado(Estado fkEstado) {
        this.fkEstado = fkEstado;
    }

    public Integer getFkEstadoContrato() {
        return fkEstadoContrato;
    }

    public void setFkEstadoContrato(Integer fkEstadoContrato) {
        this.fkEstadoContrato = fkEstadoContrato;
    }

    @XmlTransient
    public List<ReporteArchivo> getReporteArchivoList() {
        return reporteArchivoList;
    }

    public void setReporteArchivoList(List<ReporteArchivo> reporteArchivoList) {
        this.reporteArchivoList = reporteArchivoList;
    }

    @XmlTransient
    public List<ReporteObservacion> getReporteObservacionList() {
        return reporteObservacionList;
    }

    public void setReporteObservacionList(List<ReporteObservacion> reporteObservacionList) {
        this.reporteObservacionList = reporteObservacionList;
    }

    @XmlTransient
    public List<ReporteActividad> getReporteActividadList() {
        return reporteActividadList;
    }

    public void setReporteActividadList(List<ReporteActividad> reporteActividadList) {
        this.reporteActividadList = reporteActividadList;
    }

    @XmlTransient
    public List<ReporteMaquinaria> getReporteMaquinariaList() {
        return reporteMaquinariaList;
    }

    public void setReporteMaquinariaList(List<ReporteMaquinaria> reporteMaquinariaList) {
        this.reporteMaquinariaList = reporteMaquinariaList;
    }

    @XmlTransient
    public List<ReporteMateriales> getReporteMaterialesList() {
        return reporteMaterialesList;
    }

    public void setReporteMaterialesList(List<ReporteMateriales> reporteMaterialesList) {
        this.reporteMaterialesList = reporteMaterialesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (identificador != null ? identificador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reporte)) {
            return false;
        }
        Reporte other = (Reporte) object;
        if ((this.identificador == null && other.identificador != null) || (this.identificador != null && !this.identificador.equals(other.identificador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "invias.cifra.entity.Reporte[ identificador=" + identificador + " ]";
    }

}
