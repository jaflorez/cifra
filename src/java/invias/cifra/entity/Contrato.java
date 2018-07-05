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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
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
@Table(name = "contrato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contrato.findAll", query = "SELECT c FROM Contrato c")
    , @NamedQuery(name = "Contrato.findByIdentificador", query = "SELECT c FROM Contrato c WHERE c.identificador = :identificador")
    , @NamedQuery(name = "Contrato.findByObjeto", query = "SELECT c FROM Contrato c WHERE c.objeto = :objeto")
    , @NamedQuery(name = "Contrato.findByContObraCodigo", query = "SELECT c FROM Contrato c WHERE c.contObraCodigo = :contObraCodigo")
    , @NamedQuery(name = "Contrato.findByContObraVigencia", query = "SELECT c FROM Contrato c WHERE c.contObraVigencia = :contObraVigencia")
    , @NamedQuery(name = "Contrato.findByContObraFecha", query = "SELECT c FROM Contrato c WHERE c.contObraFecha = :contObraFecha")
    , @NamedQuery(name = "Contrato.findByContObraActaInicio", query = "SELECT c FROM Contrato c WHERE c.contObraActaInicio = :contObraActaInicio")
    , @NamedQuery(name = "Contrato.findByContObraValorInicial", query = "SELECT c FROM Contrato c WHERE c.contObraValorInicial = :contObraValorInicial")
    , @NamedQuery(name = "Contrato.findByContObraPlazoInicial", query = "SELECT c FROM Contrato c WHERE c.contObraPlazoInicial = :contObraPlazoInicial")
    , @NamedQuery(name = "Contrato.findByContObraFechaTermi", query = "SELECT c FROM Contrato c WHERE c.contObraFechaTermi = :contObraFechaTermi")
    , @NamedQuery(name = "Contrato.findByContInterCodigo", query = "SELECT c FROM Contrato c WHERE c.contInterCodigo = :contInterCodigo")
    , @NamedQuery(name = "Contrato.findByContInterFechaTermi", query = "SELECT c FROM Contrato c WHERE c.contInterFechaTermi = :contInterFechaTermi")
    , @NamedQuery(name = "Contrato.findByContInterPlazoInicial", query = "SELECT c FROM Contrato c WHERE c.contInterPlazoInicial = :contInterPlazoInicial")
    , @NamedQuery(name = "Contrato.findByContInterValorInicial", query = "SELECT c FROM Contrato c WHERE c.contInterValorInicial = :contInterValorInicial")
    , @NamedQuery(name = "Contrato.findByContInterActaInicio", query = "SELECT c FROM Contrato c WHERE c.contInterActaInicio = :contInterActaInicio")
    , @NamedQuery(name = "Contrato.findByContInterFecha", query = "SELECT c FROM Contrato c WHERE c.contInterFecha = :contInterFecha")
    , @NamedQuery(name = "Contrato.findByContInterVigencia", query = "SELECT c FROM Contrato c WHERE c.contInterVigencia = :contInterVigencia")
    , @NamedQuery(name = "Contrato.findByGeometria", query = "SELECT c FROM Contrato c WHERE c.geometria = :geometria")
    , @NamedQuery(name = "Contrato.findByTipocont", query = "SELECT c FROM Contrato c WHERE c.tipocont = :tipocont")
    , @NamedQuery(name = "Contrato.findByContObraPatronSiif", query = "SELECT c FROM Contrato c WHERE c.contObraPatronSiif = :contObraPatronSiif")
    , @NamedQuery(name = "Contrato.findByContInterPatronSiif", query = "SELECT c FROM Contrato c WHERE c.contInterPatronSiif = :contInterPatronSiif")
    , @NamedQuery(name = "Contrato.findByTramo", query = "SELECT c FROM Contrato c WHERE c.tramo = :tramo")})
public class Contrato implements Serializable {

    @Column(name = "FECHA_ACTUALIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDENTIFICADOR")
    private Integer identificador;
    @Basic(optional = false)
    @Column(name = "OBJETO")
    private String objeto;
    @Basic(optional = false)
    @Column(name = "CONT_OBRA_CODIGO")
    private String contObraCodigo;
    @Basic(optional = false)
    @Column(name = "CONT_OBRA_VIGENCIA")
    private int contObraVigencia;
    @Basic(optional = false)
    @Column(name = "CONT_OBRA_FECHA")
    @Temporal(TemporalType.DATE)
    private Date contObraFecha;
    @Column(name = "CONT_OBRA_ACTA_INICIO")
    @Temporal(TemporalType.DATE)
    private Date contObraActaInicio;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "CONT_OBRA_VALOR_INICIAL")
    private BigDecimal contObraValorInicial;
    @Column(name = "CONT_OBRA_VALOR_ANTICIPO")
    private BigDecimal contObraValorAnticipo;
    @Basic(optional = false)
    @Column(name = "CONT_OBRA_PLAZO_INICIAL")
    private BigDecimal contObraPlazoInicial;
    @Column(name = "CONT_OBRA_FECHA_TERMI")
    @Temporal(TemporalType.TIMESTAMP)
    private Date contObraFechaTermi;
    @Column(name = "CONT_OBRA_FECHA_TERMI_CALCULADA")
    @Temporal(TemporalType.DATE)
    private Date contObraFechaTermiCalculada;    
    @Column(name = "CONT_INTER_CODIGO")
    private String contInterCodigo;
    @Column(name = "CONT_INTER_FECHA_TERMI")
    @Temporal(TemporalType.DATE)
    private Date contInterFechaTermi;
    @Column(name = "CONT_INTER_PLAZO_INICIAL")
    private BigDecimal contInterPlazoInicial;
    @Column(name = "CONT_INTER_VALOR_INICIAL")
    private BigDecimal contInterValorInicial;
    @Column(name = "CONT_INTER_ACTA_INICIO")
    @Temporal(TemporalType.DATE)
    private Date contInterActaInicio;
    @Column(name = "CONT_INTER_FECHA")
    @Temporal(TemporalType.DATE)
    private Date contInterFecha;
    @Column(name = "CONT_INTER_VIGENCIA")
    private Integer contInterVigencia;
    @Column(name = "GEOMETRIA")
    private String geometria;
    @Column(name = "TIPOCONT")
    private Integer tipocont;
    @Column(name = "CONT_OBRA_PATRON_SIIF")
    private String contObraPatronSiif;
    @Column(name = "CONT_INTER_PATRON_SIIF")
    private String contInterPatronSiif;
    @Column(name = "TRAMO")
    private String tramo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkContrato",orphanRemoval = true)
    private List<ContratoAdicionObra> contratoAdicionObraList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkContrato",orphanRemoval = true)
    private List<ContratoProrrogaInter> contratoProrrogaInterList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkContrato",orphanRemoval = true)
    private List<ContratoAjusteObra> contratoAjusteObraList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkContrato",orphanRemoval = true)
    private List<Reporte> reporteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkContrato",orphanRemoval = true)
    private List<ContratoAjusteInter> contratoAjusteInterList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkContrato",orphanRemoval = true)
    private List<UsuarioContrato> usuarioContratoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkContrato",orphanRemoval = true)
    @OrderBy("cantidad asc")
    private List<ContratoMaquinaria> contratoMaquinariaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkContrato",orphanRemoval = true)
    @OrderBy("numero ASC")
    private List<ContratoTramo> contratoTramoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkContrato",orphanRemoval = true)
    @OrderBy("ano asc")
    private List<ContratoVigenciaObra> contratoVigenciaObraList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkContrato",orphanRemoval = true)
    private List<ContratoProrrogaObra> contratoProrrogaObraList;
    @JoinColumn(name = "FK_DIRECCION_TERRITORIAL", referencedColumnName = "IDENTIFICADOR")
    @ManyToOne(optional = false)
    private DireccionTerritorial fkDireccionTerritorial;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkContrato")
    private List<ContratoSiifDocumento> contratoSiifDocumentoList;
    @JoinColumn(name = "FK_ESTADO_CONTRATO", referencedColumnName = "IDENTIFICADOR")
    @ManyToOne(optional = false)
    private EstadoContrato fkEstadoContrato;
    @JoinColumn(name = "FK_PROGRAMA", referencedColumnName = "IDENTIFICADOR")
    @ManyToOne(optional = false)
    private Programa fkPrograma;
    @JoinColumn(name = "FK_PROYECTO", referencedColumnName = "IDENTIFICADOR")
    @ManyToOne(optional = false)
    private Proyecto fkProyecto;
    @JoinColumn(name = "FK_TERCERO_INTER", referencedColumnName = "IDENTIFICADOR")
    @ManyToOne(optional = false)
    private Tercero fkTerceroInter;
    @JoinColumn(name = "FK_TERCERO_OBRA", referencedColumnName = "IDENTIFICADOR")
    @ManyToOne(optional = false)
    private Tercero fkTerceroObra;
    @JoinColumn(name = "FK_UNIDAD_EJECUTORA", referencedColumnName = "IDENTIFICADOR")
    @ManyToOne(optional = false)
    private UnidadEjecutora fkUnidadEjecutora;
    @JoinColumn(name = "FK_USUARIO_GESTOR", referencedColumnName = "IDENTIFICADOR")
    @ManyToOne(optional = false)
    private Usuario fkUsuarioGestor;
    @JoinColumn(name = "FK_USUARIO_INTERVENTOR", referencedColumnName = "IDENTIFICADOR")
    @ManyToOne(optional = false)
    private Usuario fkUsuarioInterventor;
    @JoinColumn(name = "FK_USUARIO_SUPERVISOR", referencedColumnName = "IDENTIFICADOR")
    @ManyToOne(optional = false)
    private Usuario fkUsuarioSupervisor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkContrato",orphanRemoval = true)
    @OrderBy("cantidad desc")
    private List<ContratoActividad> contratoActividadList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkContrato",orphanRemoval = true)
    @OrderBy("ano asc, corte asc")
    private List<ContratoFinancieroObra> contratoFinancieroObraList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkContrato",orphanRemoval = true)
    private List<ContratoTerceroInter> contratoTerceroInterList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkContrato",orphanRemoval = true)
    @OrderBy("ano asc, corte asc")
    private List<ContratoFinancieroInter> contratoFinancieroInterList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkContrato", orphanRemoval = true)
    private List<ContratoAdicionInter> contratoAdicionInterList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkContrato", orphanRemoval = true)
    private List<ContratoTerceroObra> contratoTerceroObraList;

    public Contrato() {
    }

    public Contrato(Integer identificador) {
        this.identificador = identificador;
    }

    public Contrato(Integer identificador, String objeto, String contObraCodigo, int contObraVigencia, Date contObraFecha, BigDecimal contObraValorInicial, BigDecimal contObraPlazoInicial) {
        this.identificador = identificador;
        this.objeto = objeto;
        this.contObraCodigo = contObraCodigo;
        this.contObraVigencia = contObraVigencia;
        this.contObraFecha = contObraFecha;
        this.contObraValorInicial = contObraValorInicial;
        this.contObraPlazoInicial = contObraPlazoInicial;
    }

    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public String getContObraCodigo() {
        return contObraCodigo;
    }

    public void setContObraCodigo(String contObraCodigo) {
        this.contObraCodigo = contObraCodigo;
    }

    public int getContObraVigencia() {
        return contObraVigencia;
    }

    public void setContObraVigencia(int contObraVigencia) {
        this.contObraVigencia = contObraVigencia;
    }

    public Date getContObraFecha() {
        return contObraFecha;
    }

    public void setContObraFecha(Date contObraFecha) {
        this.contObraFecha = contObraFecha;
    }

    public Date getContObraActaInicio() {
        return contObraActaInicio;
    }

    public void setContObraActaInicio(Date contObraActaInicio) {
        this.contObraActaInicio = contObraActaInicio;
    }

    public BigDecimal getContObraValorInicial() {
        return contObraValorInicial;
    }

    public void setContObraValorInicial(BigDecimal contObraValorInicial) {
        this.contObraValorInicial = contObraValorInicial;
    }

    public BigDecimal getContObraValorAnticipo() {
        return contObraValorAnticipo;
    }

    public void setContObraValorAnticipo(BigDecimal contObraValorAnticipo) {
        this.contObraValorAnticipo = contObraValorAnticipo;
    }

    public BigDecimal getContObraPlazoInicial() {
        return contObraPlazoInicial;
    }

    public void setContObraPlazoInicial(BigDecimal contObraPlazoInicial) {
        this.contObraPlazoInicial = contObraPlazoInicial;
    }

    public Date getContObraFechaTermi() {
        return contObraFechaTermi;
    }

    public void setContObraFechaTermi(Date contObraFechaTermi) {
        this.contObraFechaTermi = contObraFechaTermi;
    }

    public Date getContObraFechaTermiCalculada() {
        return contObraFechaTermiCalculada;
    }

    public void setContObraFechaTermiCalculada(Date contObraFechaTermiCalculada) {
        this.contObraFechaTermiCalculada = contObraFechaTermiCalculada;
    }

    public String getContInterCodigo() {
        return contInterCodigo;
    }

    public void setContInterCodigo(String contInterCodigo) {
        this.contInterCodigo = contInterCodigo;
    }

    public Date getContInterFechaTermi() {
        return contInterFechaTermi;
    }

    public void setContInterFechaTermi(Date contInterFechaTermi) {
        this.contInterFechaTermi = contInterFechaTermi;
    }

    public BigDecimal getContInterPlazoInicial() {
        return contInterPlazoInicial;
    }

    public void setContInterPlazoInicial(BigDecimal contInterPlazoInicial) {
        this.contInterPlazoInicial = contInterPlazoInicial;
    }

    public BigDecimal getContInterValorInicial() {
        return contInterValorInicial;
    }

    public void setContInterValorInicial(BigDecimal contInterValorInicial) {
        this.contInterValorInicial = contInterValorInicial;
    }

    public Date getContInterActaInicio() {
        return contInterActaInicio;
    }

    public void setContInterActaInicio(Date contInterActaInicio) {
        this.contInterActaInicio = contInterActaInicio;
    }

    public Date getContInterFecha() {
        return contInterFecha;
    }

    public void setContInterFecha(Date contInterFecha) {
        this.contInterFecha = contInterFecha;
    }

    public Integer getContInterVigencia() {
        return contInterVigencia;
    }

    public void setContInterVigencia(Integer contInterVigencia) {
        this.contInterVigencia = contInterVigencia;
    }

    public String getGeometria() {
        return geometria;
    }

    public void setGeometria(String geometria) {
        this.geometria = geometria;
    }

    public Integer getTipocont() {
        return tipocont;
    }

    public void setTipocont(Integer tipocont) {
        this.tipocont = tipocont;
    }

    public String getContObraPatronSiif() {
        return contObraPatronSiif;
    }

    public void setContObraPatronSiif(String contObraPatronSiif) {
        this.contObraPatronSiif = contObraPatronSiif;
    }

    public String getContInterPatronSiif() {
        return contInterPatronSiif;
    }

    public void setContInterPatronSiif(String contInterPatronSiif) {
        this.contInterPatronSiif = contInterPatronSiif;
    }

    public String getTramo() {
        return tramo;
    }

    public void setTramo(String tramo) {
        this.tramo = tramo;
    }

    @XmlTransient
    public List<ContratoAdicionObra> getContratoAdicionObraList() {
        return contratoAdicionObraList;
    }

    public void setContratoAdicionObraList(List<ContratoAdicionObra> contratoAdicionObraList) {
        this.contratoAdicionObraList = contratoAdicionObraList;
    }

    @XmlTransient
    public List<ContratoProrrogaInter> getContratoProrrogaInterList() {
        return contratoProrrogaInterList;
    }

    public void setContratoProrrogaInterList(List<ContratoProrrogaInter> contratoProrrogaInterList) {
        this.contratoProrrogaInterList = contratoProrrogaInterList;
    }

    @XmlTransient
    public List<ContratoAjusteObra> getContratoAjusteObraList() {
        return contratoAjusteObraList;
    }

    public void setContratoAjusteObraList(List<ContratoAjusteObra> contratoAjusteObraList) {
        this.contratoAjusteObraList = contratoAjusteObraList;
    }

    @XmlTransient
    public List<Reporte> getReporteList() {
        return reporteList;
    }

    public void setReporteList(List<Reporte> reporteList) {
        this.reporteList = reporteList;
    }

    @XmlTransient
    public List<ContratoAjusteInter> getContratoAjusteInterList() {
        return contratoAjusteInterList;
    }

    public void setContratoAjusteInterList(List<ContratoAjusteInter> contratoAjusteInterList) {
        this.contratoAjusteInterList = contratoAjusteInterList;
    }

    @XmlTransient
    public List<UsuarioContrato> getUsuarioContratoList() {
        return usuarioContratoList;
    }

    public void setUsuarioContratoList(List<UsuarioContrato> usuarioContratoList) {
        this.usuarioContratoList = usuarioContratoList;
    }

    @XmlTransient
    public List<ContratoMaquinaria> getContratoMaquinariaList() {
        return contratoMaquinariaList;
    }

    public void setContratoMaquinariaList(List<ContratoMaquinaria> contratoMaquinariaList) {
        this.contratoMaquinariaList = contratoMaquinariaList;
    }

    @XmlTransient
    public List<ContratoTramo> getContratoTramoList() {
        return contratoTramoList;
    }

    public void setContratoTramoList(List<ContratoTramo> contratoTramoList) {
        this.contratoTramoList = contratoTramoList;
    }

    @XmlTransient
    public List<ContratoVigenciaObra> getContratoVigenciaObraList() {
        return contratoVigenciaObraList;
    }

    public void setContratoVigenciaObraList(List<ContratoVigenciaObra> contratoVigenciaObraList) {
        this.contratoVigenciaObraList = contratoVigenciaObraList;
    }

    @XmlTransient
    public List<ContratoProrrogaObra> getContratoProrrogaObraList() {
        return contratoProrrogaObraList;
    }

    public void setContratoProrrogaObraList(List<ContratoProrrogaObra> contratoProrrogaObraList) {
        this.contratoProrrogaObraList = contratoProrrogaObraList;
    }

    public DireccionTerritorial getFkDireccionTerritorial() {
        return fkDireccionTerritorial;
    }

    public void setFkDireccionTerritorial(DireccionTerritorial fkDireccionTerritorial) {
        this.fkDireccionTerritorial = fkDireccionTerritorial;
    }

    public EstadoContrato getFkEstadoContrato() {
        return fkEstadoContrato;
    }

    public void setFkEstadoContrato(EstadoContrato fkEstadoContrato) {
        this.fkEstadoContrato = fkEstadoContrato;
    }

    public Programa getFkPrograma() {
        return fkPrograma;
    }

    public void setFkPrograma(Programa fkPrograma) {
        this.fkPrograma = fkPrograma;
    }

    public Proyecto getFkProyecto() {
        return fkProyecto;
    }

    public void setFkProyecto(Proyecto fkProyecto) {
        this.fkProyecto = fkProyecto;
    }

    public Tercero getFkTerceroInter() {
        return fkTerceroInter;
    }

    public void setFkTerceroInter(Tercero fkTerceroInter) {
        this.fkTerceroInter = fkTerceroInter;
    }

    public Tercero getFkTerceroObra() {
        return fkTerceroObra;
    }

    public void setFkTerceroObra(Tercero fkTerceroObra) {
        this.fkTerceroObra = fkTerceroObra;
    }

    public UnidadEjecutora getFkUnidadEjecutora() {
        return fkUnidadEjecutora;
    }

    public void setFkUnidadEjecutora(UnidadEjecutora fkUnidadEjecutora) {
        this.fkUnidadEjecutora = fkUnidadEjecutora;
    }

    public Usuario getFkUsuarioGestor() {
        return fkUsuarioGestor;
    }

    public void setFkUsuarioGestor(Usuario fkUsuarioGestor) {
        this.fkUsuarioGestor = fkUsuarioGestor;
    }

    public Usuario getFkUsuarioInterventor() {
        return fkUsuarioInterventor;
    }

    public void setFkUsuarioInterventor(Usuario fkUsuarioInterventor) {
        this.fkUsuarioInterventor = fkUsuarioInterventor;
    }

    public Usuario getFkUsuarioSupervisor() {
        return fkUsuarioSupervisor;
    }

    public void setFkUsuarioSupervisor(Usuario fkUsuarioSupervisor) {
        this.fkUsuarioSupervisor = fkUsuarioSupervisor;
    }

    @XmlTransient

    public List<ContratoSiifDocumento> getContratoSiifDocumentoList() {
        return contratoSiifDocumentoList;
    }

    public void setContratoSiifDocumentoList(List<ContratoSiifDocumento> contratoSiifDocumentoList) {
        this.contratoSiifDocumentoList = contratoSiifDocumentoList;
    }

    public List<ContratoActividad> getContratoActividadList() {
        return contratoActividadList;
    }

    public void setContratoActividadList(List<ContratoActividad> contratoActividadList) {
        this.contratoActividadList = contratoActividadList;
    }

    @XmlTransient
    public List<ContratoFinancieroObra> getContratoFinancieroObraList() {
        return contratoFinancieroObraList;
    }

    public void setContratoFinancieroObraList(List<ContratoFinancieroObra> contratoFinancieroObraList) {
        this.contratoFinancieroObraList = contratoFinancieroObraList;
    }

    @XmlTransient
    public List<ContratoTerceroInter> getContratoTerceroInterList() {
        return contratoTerceroInterList;
    }

    public void setContratoTerceroInterList(List<ContratoTerceroInter> contratoTerceroInterList) {
        this.contratoTerceroInterList = contratoTerceroInterList;
    }

    @XmlTransient
    public List<ContratoFinancieroInter> getContratoFinancieroInterList() {
        return contratoFinancieroInterList;
    }

    public void setContratoFinancieroInterList(List<ContratoFinancieroInter> contratoFinancieroInterList) {
        this.contratoFinancieroInterList = contratoFinancieroInterList;
    }

    @XmlTransient
    public List<ContratoAdicionInter> getContratoAdicionInterList() {
        return contratoAdicionInterList;
    }

    public void setContratoAdicionInterList(List<ContratoAdicionInter> contratoAdicionInterList) {
        this.contratoAdicionInterList = contratoAdicionInterList;
    }

    @XmlTransient
    public List<ContratoTerceroObra> getContratoTerceroObraList() {
        return contratoTerceroObraList;
    }

    public void setContratoTerceroObraList(List<ContratoTerceroObra> contratoTerceroObraList) {
        this.contratoTerceroObraList = contratoTerceroObraList;
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
        if (!(object instanceof Contrato)) {
            return false;
        }
        Contrato other = (Contrato) object;
        if ((this.identificador == null && other.identificador != null) || (this.identificador != null && !this.identificador.equals(other.identificador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "invias.cifra.entity.Contrato[ identificador=" + identificador + " ]";
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
    
}
