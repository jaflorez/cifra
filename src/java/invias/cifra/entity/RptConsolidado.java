/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jaflorez
 */
@Entity
@Table(name = "rpt_consolidado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RptConsolidado.findAll", query = "SELECT r FROM RptConsolidado r")
    , @NamedQuery(name = "RptConsolidado.findByIdentificador", query = "SELECT r FROM RptConsolidado r WHERE r.identificador = :identificador")
    , @NamedQuery(name = "RptConsolidado.findByNumeroreporte", query = "SELECT r FROM RptConsolidado r WHERE r.numeroreporte = :numeroreporte")
    , @NamedQuery(name = "RptConsolidado.findByFkContrato", query = "SELECT r FROM RptConsolidado r WHERE r.fkContrato = :fkContrato")
    , @NamedQuery(name = "RptConsolidado.findByFkReporte", query = "SELECT r FROM RptConsolidado r WHERE r.fkReporte = :fkReporte")
    , @NamedQuery(name = "RptConsolidado.findByFechaElaboracion", query = "SELECT r FROM RptConsolidado r WHERE r.fechaElaboracion = :fechaElaboracion")
    , @NamedQuery(name = "RptConsolidado.findByProyecto", query = "SELECT r FROM RptConsolidado r WHERE r.proyecto = :proyecto")
    , @NamedQuery(name = "RptConsolidado.findByRazonsocial", query = "SELECT r FROM RptConsolidado r WHERE r.razonsocial = :razonsocial")
    , @NamedQuery(name = "RptConsolidado.findByNombre", query = "SELECT r FROM RptConsolidado r WHERE r.nombre = :nombre")
    , @NamedQuery(name = "RptConsolidado.findByNumeroContratoObra", query = "SELECT r FROM RptConsolidado r WHERE r.numeroContratoObra = :numeroContratoObra")
    , @NamedQuery(name = "RptConsolidado.findByContObraValorInicial", query = "SELECT r FROM RptConsolidado r WHERE r.contObraValorInicial = :contObraValorInicial")
    , @NamedQuery(name = "RptConsolidado.findByContObraActaInicio", query = "SELECT r FROM RptConsolidado r WHERE r.contObraActaInicio = :contObraActaInicio")
    , @NamedQuery(name = "RptConsolidado.findByContObraPlazoInicial", query = "SELECT r FROM RptConsolidado r WHERE r.contObraPlazoInicial = :contObraPlazoInicial")
    , @NamedQuery(name = "RptConsolidado.findByContObraFechaTermi", query = "SELECT r FROM RptConsolidado r WHERE r.contObraFechaTermi = :contObraFechaTermi")
    , @NamedQuery(name = "RptConsolidado.findByPresentoFactura", query = "SELECT r FROM RptConsolidado r WHERE r.presentoFactura = :presentoFactura")
    , @NamedQuery(name = "RptConsolidado.findByFechaUltimaFactura", query = "SELECT r FROM RptConsolidado r WHERE r.fechaUltimaFactura = :fechaUltimaFactura")
    , @NamedQuery(name = "RptConsolidado.findByValorUltimaFactura", query = "SELECT r FROM RptConsolidado r WHERE r.valorUltimaFactura = :valorUltimaFactura")
    , @NamedQuery(name = "RptConsolidado.findByBasicoObra", query = "SELECT r FROM RptConsolidado r WHERE r.basicoObra = :basicoObra")
    , @NamedQuery(name = "RptConsolidado.findByProgramadoAcumulado", query = "SELECT r FROM RptConsolidado r WHERE r.programadoAcumulado = :programadoAcumulado")
    , @NamedQuery(name = "RptConsolidado.findByEjecutadoAcumulado", query = "SELECT r FROM RptConsolidado r WHERE r.ejecutadoAcumulado = :ejecutadoAcumulado")
    , @NamedQuery(name = "RptConsolidado.findBySiifValor", query = "SELECT r FROM RptConsolidado r WHERE r.siifValor = :siifValor")
    , @NamedQuery(name = "RptConsolidado.findBySiifFecha", query = "SELECT r FROM RptConsolidado r WHERE r.siifFecha = :siifFecha")
    , @NamedQuery(name = "RptConsolidado.findByTotalInversionEjecutada", query = "SELECT r FROM RptConsolidado r WHERE r.totalInversionEjecutada = :totalInversionEjecutada")
    , @NamedQuery(name = "RptConsolidado.findByValorVigencia", query = "SELECT r FROM RptConsolidado r WHERE r.valorVigencia = :valorVigencia")
    , @NamedQuery(name = "RptConsolidado.findByVigenciaProgramada", query = "SELECT r FROM RptConsolidado r WHERE r.vigenciaProgramada = :vigenciaProgramada")
    , @NamedQuery(name = "RptConsolidado.findByAvanceObraFisica", query = "SELECT r FROM RptConsolidado r WHERE r.avanceObraFisica = :avanceObraFisica")
    , @NamedQuery(name = "RptConsolidado.findByEstado", query = "SELECT r FROM RptConsolidado r WHERE r.estado = :estado")
    , @NamedQuery(name = "RptConsolidado.findByAct5Prg", query = "SELECT r FROM RptConsolidado r WHERE r.act5Prg = :act5Prg")
    , @NamedQuery(name = "RptConsolidado.findByAct5Avc", query = "SELECT r FROM RptConsolidado r WHERE r.act5Avc = :act5Avc")
    , @NamedQuery(name = "RptConsolidado.findByAct1Prg", query = "SELECT r FROM RptConsolidado r WHERE r.act1Prg = :act1Prg")
    , @NamedQuery(name = "RptConsolidado.findByAct1Avc", query = "SELECT r FROM RptConsolidado r WHERE r.act1Avc = :act1Avc")
    , @NamedQuery(name = "RptConsolidado.findByAct2Prg", query = "SELECT r FROM RptConsolidado r WHERE r.act2Prg = :act2Prg")
    , @NamedQuery(name = "RptConsolidado.findByAct2Avc", query = "SELECT r FROM RptConsolidado r WHERE r.act2Avc = :act2Avc")
    , @NamedQuery(name = "RptConsolidado.findByAct3Prg", query = "SELECT r FROM RptConsolidado r WHERE r.act3Prg = :act3Prg")
    , @NamedQuery(name = "RptConsolidado.findByAct3Avc", query = "SELECT r FROM RptConsolidado r WHERE r.act3Avc = :act3Avc")
    , @NamedQuery(name = "RptConsolidado.findByAct4Prg", query = "SELECT r FROM RptConsolidado r WHERE r.act4Prg = :act4Prg")
    , @NamedQuery(name = "RptConsolidado.findByAct4Avc", query = "SELECT r FROM RptConsolidado r WHERE r.act4Avc = :act4Avc")
    , @NamedQuery(name = "RptConsolidado.findByAct6Prg", query = "SELECT r FROM RptConsolidado r WHERE r.act6Prg = :act6Prg")
    , @NamedQuery(name = "RptConsolidado.findByAct6Avc", query = "SELECT r FROM RptConsolidado r WHERE r.act6Avc = :act6Avc")
    , @NamedQuery(name = "RptConsolidado.findByAct7Prg", query = "SELECT r FROM RptConsolidado r WHERE r.act7Prg = :act7Prg")
    , @NamedQuery(name = "RptConsolidado.findByAct7Avc", query = "SELECT r FROM RptConsolidado r WHERE r.act7Avc = :act7Avc")
    , @NamedQuery(name = "RptConsolidado.findByAct8Prg", query = "SELECT r FROM RptConsolidado r WHERE r.act8Prg = :act8Prg")
    , @NamedQuery(name = "RptConsolidado.findByAct8Avc", query = "SELECT r FROM RptConsolidado r WHERE r.act8Avc = :act8Avc")
    , @NamedQuery(name = "RptConsolidado.findByAct9Prg", query = "SELECT r FROM RptConsolidado r WHERE r.act9Prg = :act9Prg")
    , @NamedQuery(name = "RptConsolidado.findByAct9Avc", query = "SELECT r FROM RptConsolidado r WHERE r.act9Avc = :act9Avc")
    , @NamedQuery(name = "RptConsolidado.findByAct10Prg", query = "SELECT r FROM RptConsolidado r WHERE r.act10Prg = :act10Prg")
    , @NamedQuery(name = "RptConsolidado.findByAct10Avc", query = "SELECT r FROM RptConsolidado r WHERE r.act10Avc = :act10Avc")
    , @NamedQuery(name = "RptConsolidado.findByAct11Prg", query = "SELECT r FROM RptConsolidado r WHERE r.act11Prg = :act11Prg")
    , @NamedQuery(name = "RptConsolidado.findByAct11Avc", query = "SELECT r FROM RptConsolidado r WHERE r.act11Avc = :act11Avc")
    , @NamedQuery(name = "RptConsolidado.findByAct12Prg", query = "SELECT r FROM RptConsolidado r WHERE r.act12Prg = :act12Prg")
    , @NamedQuery(name = "RptConsolidado.findByAct12Avc", query = "SELECT r FROM RptConsolidado r WHERE r.act12Avc = :act12Avc")
    , @NamedQuery(name = "RptConsolidado.findByAct13Prg", query = "SELECT r FROM RptConsolidado r WHERE r.act13Prg = :act13Prg")
    , @NamedQuery(name = "RptConsolidado.findByAct13Avc", query = "SELECT r FROM RptConsolidado r WHERE r.act13Avc = :act13Avc")
    , @NamedQuery(name = "RptConsolidado.findByAct14Prg", query = "SELECT r FROM RptConsolidado r WHERE r.act14Prg = :act14Prg")
    , @NamedQuery(name = "RptConsolidado.findByAct14Avc", query = "SELECT r FROM RptConsolidado r WHERE r.act14Avc = :act14Avc")
    , @NamedQuery(name = "RptConsolidado.findByAct15Prg", query = "SELECT r FROM RptConsolidado r WHERE r.act15Prg = :act15Prg")
    , @NamedQuery(name = "RptConsolidado.findByAct15Avc", query = "SELECT r FROM RptConsolidado r WHERE r.act15Avc = :act15Avc")
    , @NamedQuery(name = "RptConsolidado.findByAct16Prg", query = "SELECT r FROM RptConsolidado r WHERE r.act16Prg = :act16Prg")
    , @NamedQuery(name = "RptConsolidado.findByAct16Avc", query = "SELECT r FROM RptConsolidado r WHERE r.act16Avc = :act16Avc")
    , @NamedQuery(name = "RptConsolidado.findByAct17Prg", query = "SELECT r FROM RptConsolidado r WHERE r.act17Prg = :act17Prg")
    , @NamedQuery(name = "RptConsolidado.findByAct17Avc", query = "SELECT r FROM RptConsolidado r WHERE r.act17Avc = :act17Avc")
    , @NamedQuery(name = "RptConsolidado.findByAct18Prg", query = "SELECT r FROM RptConsolidado r WHERE r.act18Prg = :act18Prg")
    , @NamedQuery(name = "RptConsolidado.findByAct18Avc", query = "SELECT r FROM RptConsolidado r WHERE r.act18Avc = :act18Avc")
    , @NamedQuery(name = "RptConsolidado.findByAct19Prg", query = "SELECT r FROM RptConsolidado r WHERE r.act19Prg = :act19Prg")
    , @NamedQuery(name = "RptConsolidado.findByAct19Avc", query = "SELECT r FROM RptConsolidado r WHERE r.act19Avc = :act19Avc")
    , @NamedQuery(name = "RptConsolidado.findByAct20Prg", query = "SELECT r FROM RptConsolidado r WHERE r.act20Prg = :act20Prg")
    , @NamedQuery(name = "RptConsolidado.findByAct20Avc", query = "SELECT r FROM RptConsolidado r WHERE r.act20Avc = :act20Avc")
    , @NamedQuery(name = "RptConsolidado.findByAct21Prg", query = "SELECT r FROM RptConsolidado r WHERE r.act21Prg = :act21Prg")
    , @NamedQuery(name = "RptConsolidado.findByAct21Avc", query = "SELECT r FROM RptConsolidado r WHERE r.act21Avc = :act21Avc")
    , @NamedQuery(name = "RptConsolidado.findByAct22Prg", query = "SELECT r FROM RptConsolidado r WHERE r.act22Prg = :act22Prg")
    , @NamedQuery(name = "RptConsolidado.findByAct22Avc", query = "SELECT r FROM RptConsolidado r WHERE r.act22Avc = :act22Avc")
    , @NamedQuery(name = "RptConsolidado.findByAct23Prg", query = "SELECT r FROM RptConsolidado r WHERE r.act23Prg = :act23Prg")
    , @NamedQuery(name = "RptConsolidado.findByAct23Avc", query = "SELECT r FROM RptConsolidado r WHERE r.act23Avc = :act23Avc")
    , @NamedQuery(name = "RptConsolidado.findByAct24Prg", query = "SELECT r FROM RptConsolidado r WHERE r.act24Prg = :act24Prg")
    , @NamedQuery(name = "RptConsolidado.findByAct24Avc", query = "SELECT r FROM RptConsolidado r WHERE r.act24Avc = :act24Avc")
    , @NamedQuery(name = "RptConsolidado.findByAct25Prg", query = "SELECT r FROM RptConsolidado r WHERE r.act25Prg = :act25Prg")
    , @NamedQuery(name = "RptConsolidado.findByAct25Avc", query = "SELECT r FROM RptConsolidado r WHERE r.act25Avc = :act25Avc")
    , @NamedQuery(name = "RptConsolidado.findByAct26Prg", query = "SELECT r FROM RptConsolidado r WHERE r.act26Prg = :act26Prg")
    , @NamedQuery(name = "RptConsolidado.findByAct26Avc", query = "SELECT r FROM RptConsolidado r WHERE r.act26Avc = :act26Avc")
    , @NamedQuery(name = "RptConsolidado.findByAct27Prg", query = "SELECT r FROM RptConsolidado r WHERE r.act27Prg = :act27Prg")
    , @NamedQuery(name = "RptConsolidado.findByAct27Avc", query = "SELECT r FROM RptConsolidado r WHERE r.act27Avc = :act27Avc")
    , @NamedQuery(name = "RptConsolidado.findByAct28Prg", query = "SELECT r FROM RptConsolidado r WHERE r.act28Prg = :act28Prg")
    , @NamedQuery(name = "RptConsolidado.findByAct28Avc", query = "SELECT r FROM RptConsolidado r WHERE r.act28Avc = :act28Avc")
    , @NamedQuery(name = "RptConsolidado.findByAct29Prg", query = "SELECT r FROM RptConsolidado r WHERE r.act29Prg = :act29Prg")
    , @NamedQuery(name = "RptConsolidado.findByAct29Avc", query = "SELECT r FROM RptConsolidado r WHERE r.act29Avc = :act29Avc")
    , @NamedQuery(name = "RptConsolidado.findByAct30Prg", query = "SELECT r FROM RptConsolidado r WHERE r.act30Prg = :act30Prg")
    , @NamedQuery(name = "RptConsolidado.findByAct30Avc", query = "SELECT r FROM RptConsolidado r WHERE r.act30Avc = :act30Avc")
    , @NamedQuery(name = "RptConsolidado.findByAct31Prg", query = "SELECT r FROM RptConsolidado r WHERE r.act31Prg = :act31Prg")
    , @NamedQuery(name = "RptConsolidado.findByAct31Avc", query = "SELECT r FROM RptConsolidado r WHERE r.act31Avc = :act31Avc")
    , @NamedQuery(name = "RptConsolidado.findByAct32Prg", query = "SELECT r FROM RptConsolidado r WHERE r.act32Prg = :act32Prg")
    , @NamedQuery(name = "RptConsolidado.findByAct32Avc", query = "SELECT r FROM RptConsolidado r WHERE r.act32Avc = :act32Avc")
    , @NamedQuery(name = "RptConsolidado.findByAct33Prg", query = "SELECT r FROM RptConsolidado r WHERE r.act33Prg = :act33Prg")
    , @NamedQuery(name = "RptConsolidado.findByAct33Avc", query = "SELECT r FROM RptConsolidado r WHERE r.act33Avc = :act33Avc")
    , @NamedQuery(name = "RptConsolidado.findByAct34Prg", query = "SELECT r FROM RptConsolidado r WHERE r.act34Prg = :act34Prg")
    , @NamedQuery(name = "RptConsolidado.findByAct34Avc", query = "SELECT r FROM RptConsolidado r WHERE r.act34Avc = :act34Avc")
    , @NamedQuery(name = "RptConsolidado.findByAct35Prg", query = "SELECT r FROM RptConsolidado r WHERE r.act35Prg = :act35Prg")
    , @NamedQuery(name = "RptConsolidado.findByAct35Avc", query = "SELECT r FROM RptConsolidado r WHERE r.act35Avc = :act35Avc")
    , @NamedQuery(name = "RptConsolidado.findByAct36Prg", query = "SELECT r FROM RptConsolidado r WHERE r.act36Prg = :act36Prg")
    , @NamedQuery(name = "RptConsolidado.findByAct36Avc", query = "SELECT r FROM RptConsolidado r WHERE r.act36Avc = :act36Avc")
    , @NamedQuery(name = "RptConsolidado.findByAct37Prg", query = "SELECT r FROM RptConsolidado r WHERE r.act37Prg = :act37Prg")
    , @NamedQuery(name = "RptConsolidado.findByAct37Avc", query = "SELECT r FROM RptConsolidado r WHERE r.act37Avc = :act37Avc")
    , @NamedQuery(name = "RptConsolidado.findByAct38Prg", query = "SELECT r FROM RptConsolidado r WHERE r.act38Prg = :act38Prg")
    , @NamedQuery(name = "RptConsolidado.findByAct38Avc", query = "SELECT r FROM RptConsolidado r WHERE r.act38Avc = :act38Avc")
    , @NamedQuery(name = "RptConsolidado.findByAct39Prg", query = "SELECT r FROM RptConsolidado r WHERE r.act39Prg = :act39Prg")
    , @NamedQuery(name = "RptConsolidado.findByAct39Avc", query = "SELECT r FROM RptConsolidado r WHERE r.act39Avc = :act39Avc")
    , @NamedQuery(name = "RptConsolidado.findByAct40Prg", query = "SELECT r FROM RptConsolidado r WHERE r.act40Prg = :act40Prg")
    , @NamedQuery(name = "RptConsolidado.findByAct40Avc", query = "SELECT r FROM RptConsolidado r WHERE r.act40Avc = :act40Avc")
    , @NamedQuery(name = "RptConsolidado.findByTempo", query = "SELECT r FROM RptConsolidado r WHERE r.tempo = :tempo")})
public class RptConsolidado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDENTIFICADOR")
    private Integer identificador;
    @Basic(optional = false)
    @Column(name = "NUMEROREPORTE")
    private int numeroreporte;
    @Column(name = "FK_CONTRATO")
    private Integer fkContrato;
    @Column(name = "FK_REPORTE")
    private Integer fkReporte;
    @Column(name = "FECHA_ELABORACION")
    @Temporal(TemporalType.DATE)
    private Date fechaElaboracion;
    @Basic(optional = false)
    @Column(name = "PROYECTO")
    private String proyecto;
    @Column(name = "RAZONSOCIAL")
    private String razonsocial;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "NUMERO_CONTRATO_OBRA")
    private String numeroContratoObra;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "CONT_OBRA_VALOR_INICIAL")
    private BigDecimal contObraValorInicial;
    @Column(name = "CONT_OBRA_ACTA_INICIO")
    @Temporal(TemporalType.DATE)
    private Date contObraActaInicio;
    @Basic(optional = false)
    @Column(name = "CONT_OBRA_PLAZO_INICIAL")
    private BigDecimal contObraPlazoInicial;
    @Column(name = "CONT_OBRA_FECHA_TERMI")
    @Temporal(TemporalType.TIMESTAMP)
    private Date contObraFechaTermi;
    @Basic(optional = false)
    @Column(name = "PRESENTO_FACTURA")
    private String presentoFactura;
    @Column(name = "FECHA_ULTIMA_FACTURA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUltimaFactura;
    @Column(name = "VALOR_ULTIMA_FACTURA")
    private BigDecimal valorUltimaFactura;
    @Column(name = "BASICO_OBRA")
    private BigDecimal basicoObra;
    @Column(name = "PROGRAMADO_ACUMULADO")
    private BigDecimal programadoAcumulado;
    @Column(name = "EJECUTADO_ACUMULADO")
    private BigDecimal ejecutadoAcumulado;
    @Column(name = "SIIF_VALOR")
    private BigDecimal siifValor;
    @Column(name = "SIIF_FECHA")
    @Temporal(TemporalType.DATE)
    private Date siifFecha;
    @Column(name = "TOTAL_INVERSION_EJECUTADA")
    private BigDecimal totalInversionEjecutada;
    @Column(name = "VALOR_VIGENCIA")
    private BigDecimal valorVigencia;
    @Column(name = "VIGENCIA_PROGRAMADA")
    private BigDecimal vigenciaProgramada;
    @Column(name = "AVANCE_OBRA_FISICA")
    private BigDecimal avanceObraFisica;
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "ACT_5_PRG")
    private BigDecimal act5Prg;
    @Column(name = "ACT_5_AVC")
    private BigDecimal act5Avc;
    @Column(name = "ACT_1_PRG")
    private BigDecimal act1Prg;
    @Column(name = "ACT_1_AVC")
    private BigDecimal act1Avc;
    @Column(name = "ACT_2_PRG")
    private BigDecimal act2Prg;
    @Column(name = "ACT_2_AVC")
    private BigDecimal act2Avc;
    @Column(name = "ACT_3_PRG")
    private BigDecimal act3Prg;
    @Column(name = "ACT_3_AVC")
    private BigDecimal act3Avc;
    @Column(name = "ACT_4_PRG")
    private BigDecimal act4Prg;
    @Column(name = "ACT_4_AVC")
    private BigDecimal act4Avc;
    @Column(name = "ACT_6_PRG")
    private BigDecimal act6Prg;
    @Column(name = "ACT_6_AVC")
    private BigDecimal act6Avc;
    @Column(name = "ACT_7_PRG")
    private BigDecimal act7Prg;
    @Column(name = "ACT_7_AVC")
    private BigDecimal act7Avc;
    @Column(name = "ACT_8_PRG")
    private BigDecimal act8Prg;
    @Column(name = "ACT_8_AVC")
    private BigDecimal act8Avc;
    @Column(name = "ACT_9_PRG")
    private BigDecimal act9Prg;
    @Column(name = "ACT_9_AVC")
    private BigDecimal act9Avc;
    @Column(name = "ACT_10_PRG")
    private BigDecimal act10Prg;
    @Column(name = "ACT_10_AVC")
    private BigDecimal act10Avc;
    @Column(name = "ACT_11_PRG")
    private BigDecimal act11Prg;
    @Column(name = "ACT_11_AVC")
    private BigDecimal act11Avc;
    @Column(name = "ACT_12_PRG")
    private BigDecimal act12Prg;
    @Column(name = "ACT_12_AVC")
    private BigDecimal act12Avc;
    @Column(name = "ACT_13_PRG")
    private BigDecimal act13Prg;
    @Column(name = "ACT_13_AVC")
    private BigDecimal act13Avc;
    @Column(name = "ACT_14_PRG")
    private BigDecimal act14Prg;
    @Column(name = "ACT_14_AVC")
    private BigDecimal act14Avc;
    @Column(name = "ACT_15_PRG")
    private BigDecimal act15Prg;
    @Column(name = "ACT_15_AVC")
    private BigDecimal act15Avc;
    @Column(name = "ACT_16_PRG")
    private BigDecimal act16Prg;
    @Column(name = "ACT_16_AVC")
    private BigDecimal act16Avc;
    @Column(name = "ACT_17_PRG")
    private BigDecimal act17Prg;
    @Column(name = "ACT_17_AVC")
    private BigDecimal act17Avc;
    @Column(name = "ACT_18_PRG")
    private BigDecimal act18Prg;
    @Column(name = "ACT_18_AVC")
    private BigDecimal act18Avc;
    @Column(name = "ACT_19_PRG")
    private BigDecimal act19Prg;
    @Column(name = "ACT_19_AVC")
    private BigDecimal act19Avc;
    @Column(name = "ACT_20_PRG")
    private BigDecimal act20Prg;
    @Column(name = "ACT_20_AVC")
    private BigDecimal act20Avc;
    @Column(name = "ACT_21_PRG")
    private BigDecimal act21Prg;
    @Column(name = "ACT_21_AVC")
    private BigDecimal act21Avc;
    @Column(name = "ACT_22_PRG")
    private BigDecimal act22Prg;
    @Column(name = "ACT_22_AVC")
    private BigDecimal act22Avc;
    @Column(name = "ACT_23_PRG")
    private BigDecimal act23Prg;
    @Column(name = "ACT_23_AVC")
    private BigDecimal act23Avc;
    @Column(name = "ACT_24_PRG")
    private BigDecimal act24Prg;
    @Column(name = "ACT_24_AVC")
    private BigDecimal act24Avc;
    @Column(name = "ACT_25_PRG")
    private BigDecimal act25Prg;
    @Column(name = "ACT_25_AVC")
    private BigDecimal act25Avc;
    @Column(name = "ACT_26_PRG")
    private BigDecimal act26Prg;
    @Column(name = "ACT_26_AVC")
    private BigDecimal act26Avc;
    @Column(name = "ACT_27_PRG")
    private BigDecimal act27Prg;
    @Column(name = "ACT_27_AVC")
    private BigDecimal act27Avc;
    @Column(name = "ACT_28_PRG")
    private BigDecimal act28Prg;
    @Column(name = "ACT_28_AVC")
    private BigDecimal act28Avc;
    @Column(name = "ACT_29_PRG")
    private BigDecimal act29Prg;
    @Column(name = "ACT_29_AVC")
    private BigDecimal act29Avc;
    @Column(name = "ACT_30_PRG")
    private BigDecimal act30Prg;
    @Column(name = "ACT_30_AVC")
    private BigDecimal act30Avc;
    @Column(name = "ACT_31_PRG")
    private BigDecimal act31Prg;
    @Column(name = "ACT_31_AVC")
    private BigDecimal act31Avc;
    @Column(name = "ACT_32_PRG")
    private BigDecimal act32Prg;
    @Column(name = "ACT_32_AVC")
    private BigDecimal act32Avc;
    @Column(name = "ACT_33_PRG")
    private BigDecimal act33Prg;
    @Column(name = "ACT_33_AVC")
    private BigDecimal act33Avc;
    @Column(name = "ACT_34_PRG")
    private BigDecimal act34Prg;
    @Column(name = "ACT_34_AVC")
    private BigDecimal act34Avc;
    @Column(name = "ACT_35_PRG")
    private BigDecimal act35Prg;
    @Column(name = "ACT_35_AVC")
    private BigDecimal act35Avc;
    @Column(name = "ACT_36_PRG")
    private BigDecimal act36Prg;
    @Column(name = "ACT_36_AVC")
    private BigDecimal act36Avc;
    @Column(name = "ACT_37_PRG")
    private BigDecimal act37Prg;
    @Column(name = "ACT_37_AVC")
    private BigDecimal act37Avc;
    @Column(name = "ACT_38_PRG")
    private BigDecimal act38Prg;
    @Column(name = "ACT_38_AVC")
    private BigDecimal act38Avc;
    @Column(name = "ACT_39_PRG")
    private BigDecimal act39Prg;
    @Column(name = "ACT_39_AVC")
    private BigDecimal act39Avc;
    @Column(name = "ACT_40_PRG")
    private BigDecimal act40Prg;
    @Column(name = "ACT_40_AVC")
    private BigDecimal act40Avc;
    @Column(name = "ACT_41_PRG")
    private BigDecimal act41Prg;
    @Column(name = "ACT_41_AVC")
    private BigDecimal act41Avc;
    @Column(name = "ACT_42_PRG")
    private BigDecimal act42Prg;
    @Column(name = "ACT_42_AVC")
    private BigDecimal act42Avc;
    @Column(name = "ACT_43_PRG")
    private BigDecimal act43Prg;
    @Column(name = "ACT_43_AVC")
    private BigDecimal act43Avc;
    @Column(name = "tempo")
    private String tempo;

    public RptConsolidado() {
    }

    public RptConsolidado(Integer identificador) {
        this.identificador = identificador;
    }

    public RptConsolidado(Integer identificador, int numeroreporte, String proyecto, BigDecimal contObraValorInicial, BigDecimal contObraPlazoInicial, String presentoFactura) {
        this.identificador = identificador;
        this.numeroreporte = numeroreporte;
        this.proyecto = proyecto;
        this.contObraValorInicial = contObraValorInicial;
        this.contObraPlazoInicial = contObraPlazoInicial;
        this.presentoFactura = presentoFactura;
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

    public Integer getFkContrato() {
        return fkContrato;
    }

    public void setFkContrato(Integer fkContrato) {
        this.fkContrato = fkContrato;
    }

    public Integer getFkReporte() {
        return fkReporte;
    }

    public void setFkReporte(Integer fkReporte) {
        this.fkReporte = fkReporte;
    }

    public Date getFechaElaboracion() {
        return fechaElaboracion;
    }

    public void setFechaElaboracion(Date fechaElaboracion) {
        this.fechaElaboracion = fechaElaboracion;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    public String getRazonsocial() {
        return razonsocial;
    }

    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumeroContratoObra() {
        return numeroContratoObra;
    }

    public void setNumeroContratoObra(String numeroContratoObra) {
        this.numeroContratoObra = numeroContratoObra;
    }

    public BigDecimal getContObraValorInicial() {
        return contObraValorInicial;
    }

    public void setContObraValorInicial(BigDecimal contObraValorInicial) {
        this.contObraValorInicial = contObraValorInicial;
    }

    public Date getContObraActaInicio() {
        return contObraActaInicio;
    }

    public void setContObraActaInicio(Date contObraActaInicio) {
        this.contObraActaInicio = contObraActaInicio;
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

    public String getPresentoFactura() {
        return presentoFactura;
    }

    public void setPresentoFactura(String presentoFactura) {
        this.presentoFactura = presentoFactura;
    }

    public Date getFechaUltimaFactura() {
        return fechaUltimaFactura;
    }

    public void setFechaUltimaFactura(Date fechaUltimaFactura) {
        this.fechaUltimaFactura = fechaUltimaFactura;
    }

    public BigDecimal getValorUltimaFactura() {
        return valorUltimaFactura;
    }

    public void setValorUltimaFactura(BigDecimal valorUltimaFactura) {
        this.valorUltimaFactura = valorUltimaFactura;
    }

    public BigDecimal getBasicoObra() {
        return basicoObra;
    }

    public void setBasicoObra(BigDecimal basicoObra) {
        this.basicoObra = basicoObra;
    }

    public BigDecimal getProgramadoAcumulado() {
        return programadoAcumulado;
    }

    public void setProgramadoAcumulado(BigDecimal programadoAcumulado) {
        this.programadoAcumulado = programadoAcumulado;
    }

    public BigDecimal getEjecutadoAcumulado() {
        return ejecutadoAcumulado;
    }

    public void setEjecutadoAcumulado(BigDecimal ejecutadoAcumulado) {
        this.ejecutadoAcumulado = ejecutadoAcumulado;
    }

    public BigDecimal getSiifValor() {
        return siifValor;
    }

    public void setSiifValor(BigDecimal siifValor) {
        this.siifValor = siifValor;
    }

    public Date getSiifFecha() {
        return siifFecha;
    }

    public void setSiifFecha(Date siifFecha) {
        this.siifFecha = siifFecha;
    }

    public BigDecimal getTotalInversionEjecutada() {
        return totalInversionEjecutada;
    }

    public void setTotalInversionEjecutada(BigDecimal totalInversionEjecutada) {
        this.totalInversionEjecutada = totalInversionEjecutada;
    }

    public BigDecimal getValorVigencia() {
        return valorVigencia;
    }

    public void setValorVigencia(BigDecimal valorVigencia) {
        this.valorVigencia = valorVigencia;
    }

    public BigDecimal getVigenciaProgramada() {
        return vigenciaProgramada;
    }

    public void setVigenciaProgramada(BigDecimal vigenciaProgramada) {
        this.vigenciaProgramada = vigenciaProgramada;
    }

    public BigDecimal getAvanceObraFisica() {
        return avanceObraFisica;
    }

    public void setAvanceObraFisica(BigDecimal avanceObraFisica) {
        this.avanceObraFisica = avanceObraFisica;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigDecimal getAct5Prg() {
        return act5Prg;
    }

    public void setAct5Prg(BigDecimal act5Prg) {
        this.act5Prg = act5Prg;
    }

    public BigDecimal getAct5Avc() {
        return act5Avc;
    }

    public void setAct5Avc(BigDecimal act5Avc) {
        this.act5Avc = act5Avc;
    }

    public BigDecimal getAct1Prg() {
        return act1Prg;
    }

    public void setAct1Prg(BigDecimal act1Prg) {
        this.act1Prg = act1Prg;
    }

    public BigDecimal getAct1Avc() {
        return act1Avc;
    }

    public void setAct1Avc(BigDecimal act1Avc) {
        this.act1Avc = act1Avc;
    }

    public BigDecimal getAct2Prg() {
        return act2Prg;
    }

    public void setAct2Prg(BigDecimal act2Prg) {
        this.act2Prg = act2Prg;
    }

    public BigDecimal getAct2Avc() {
        return act2Avc;
    }

    public void setAct2Avc(BigDecimal act2Avc) {
        this.act2Avc = act2Avc;
    }

    public BigDecimal getAct3Prg() {
        return act3Prg;
    }

    public void setAct3Prg(BigDecimal act3Prg) {
        this.act3Prg = act3Prg;
    }

    public BigDecimal getAct3Avc() {
        return act3Avc;
    }

    public void setAct3Avc(BigDecimal act3Avc) {
        this.act3Avc = act3Avc;
    }

    public BigDecimal getAct4Prg() {
        return act4Prg;
    }

    public void setAct4Prg(BigDecimal act4Prg) {
        this.act4Prg = act4Prg;
    }

    public BigDecimal getAct4Avc() {
        return act4Avc;
    }

    public void setAct4Avc(BigDecimal act4Avc) {
        this.act4Avc = act4Avc;
    }

    public BigDecimal getAct6Prg() {
        return act6Prg;
    }

    public void setAct6Prg(BigDecimal act6Prg) {
        this.act6Prg = act6Prg;
    }

    public BigDecimal getAct6Avc() {
        return act6Avc;
    }

    public void setAct6Avc(BigDecimal act6Avc) {
        this.act6Avc = act6Avc;
    }

    public BigDecimal getAct7Prg() {
        return act7Prg;
    }

    public void setAct7Prg(BigDecimal act7Prg) {
        this.act7Prg = act7Prg;
    }

    public BigDecimal getAct7Avc() {
        return act7Avc;
    }

    public void setAct7Avc(BigDecimal act7Avc) {
        this.act7Avc = act7Avc;
    }

    public BigDecimal getAct8Prg() {
        return act8Prg;
    }

    public void setAct8Prg(BigDecimal act8Prg) {
        this.act8Prg = act8Prg;
    }

    public BigDecimal getAct8Avc() {
        return act8Avc;
    }

    public void setAct8Avc(BigDecimal act8Avc) {
        this.act8Avc = act8Avc;
    }

    public BigDecimal getAct9Prg() {
        return act9Prg;
    }

    public void setAct9Prg(BigDecimal act9Prg) {
        this.act9Prg = act9Prg;
    }

    public BigDecimal getAct9Avc() {
        return act9Avc;
    }

    public void setAct9Avc(BigDecimal act9Avc) {
        this.act9Avc = act9Avc;
    }

    public BigDecimal getAct10Prg() {
        return act10Prg;
    }

    public void setAct10Prg(BigDecimal act10Prg) {
        this.act10Prg = act10Prg;
    }

    public BigDecimal getAct10Avc() {
        return act10Avc;
    }

    public void setAct10Avc(BigDecimal act10Avc) {
        this.act10Avc = act10Avc;
    }

    public BigDecimal getAct11Prg() {
        return act11Prg;
    }

    public void setAct11Prg(BigDecimal act11Prg) {
        this.act11Prg = act11Prg;
    }

    public BigDecimal getAct11Avc() {
        return act11Avc;
    }

    public void setAct11Avc(BigDecimal act11Avc) {
        this.act11Avc = act11Avc;
    }

    public BigDecimal getAct12Prg() {
        return act12Prg;
    }

    public void setAct12Prg(BigDecimal act12Prg) {
        this.act12Prg = act12Prg;
    }

    public BigDecimal getAct12Avc() {
        return act12Avc;
    }

    public void setAct12Avc(BigDecimal act12Avc) {
        this.act12Avc = act12Avc;
    }

    public BigDecimal getAct13Prg() {
        return act13Prg;
    }

    public void setAct13Prg(BigDecimal act13Prg) {
        this.act13Prg = act13Prg;
    }

    public BigDecimal getAct13Avc() {
        return act13Avc;
    }

    public void setAct13Avc(BigDecimal act13Avc) {
        this.act13Avc = act13Avc;
    }

    public BigDecimal getAct14Prg() {
        return act14Prg;
    }

    public void setAct14Prg(BigDecimal act14Prg) {
        this.act14Prg = act14Prg;
    }

    public BigDecimal getAct14Avc() {
        return act14Avc;
    }

    public void setAct14Avc(BigDecimal act14Avc) {
        this.act14Avc = act14Avc;
    }

    public BigDecimal getAct15Prg() {
        return act15Prg;
    }

    public void setAct15Prg(BigDecimal act15Prg) {
        this.act15Prg = act15Prg;
    }

    public BigDecimal getAct15Avc() {
        return act15Avc;
    }

    public void setAct15Avc(BigDecimal act15Avc) {
        this.act15Avc = act15Avc;
    }

    public BigDecimal getAct16Prg() {
        return act16Prg;
    }

    public void setAct16Prg(BigDecimal act16Prg) {
        this.act16Prg = act16Prg;
    }

    public BigDecimal getAct16Avc() {
        return act16Avc;
    }

    public void setAct16Avc(BigDecimal act16Avc) {
        this.act16Avc = act16Avc;
    }

    public BigDecimal getAct17Prg() {
        return act17Prg;
    }

    public void setAct17Prg(BigDecimal act17Prg) {
        this.act17Prg = act17Prg;
    }

    public BigDecimal getAct17Avc() {
        return act17Avc;
    }

    public void setAct17Avc(BigDecimal act17Avc) {
        this.act17Avc = act17Avc;
    }

    public BigDecimal getAct18Prg() {
        return act18Prg;
    }

    public void setAct18Prg(BigDecimal act18Prg) {
        this.act18Prg = act18Prg;
    }

    public BigDecimal getAct18Avc() {
        return act18Avc;
    }

    public void setAct18Avc(BigDecimal act18Avc) {
        this.act18Avc = act18Avc;
    }

    public BigDecimal getAct19Prg() {
        return act19Prg;
    }

    public void setAct19Prg(BigDecimal act19Prg) {
        this.act19Prg = act19Prg;
    }

    public BigDecimal getAct19Avc() {
        return act19Avc;
    }

    public void setAct19Avc(BigDecimal act19Avc) {
        this.act19Avc = act19Avc;
    }

    public BigDecimal getAct20Prg() {
        return act20Prg;
    }

    public void setAct20Prg(BigDecimal act20Prg) {
        this.act20Prg = act20Prg;
    }

    public BigDecimal getAct20Avc() {
        return act20Avc;
    }

    public void setAct20Avc(BigDecimal act20Avc) {
        this.act20Avc = act20Avc;
    }

    public BigDecimal getAct21Prg() {
        return act21Prg;
    }

    public void setAct21Prg(BigDecimal act21Prg) {
        this.act21Prg = act21Prg;
    }

    public BigDecimal getAct21Avc() {
        return act21Avc;
    }

    public void setAct21Avc(BigDecimal act21Avc) {
        this.act21Avc = act21Avc;
    }

    public BigDecimal getAct22Prg() {
        return act22Prg;
    }

    public void setAct22Prg(BigDecimal act22Prg) {
        this.act22Prg = act22Prg;
    }

    public BigDecimal getAct22Avc() {
        return act22Avc;
    }

    public void setAct22Avc(BigDecimal act22Avc) {
        this.act22Avc = act22Avc;
    }

    public BigDecimal getAct23Prg() {
        return act23Prg;
    }

    public void setAct23Prg(BigDecimal act23Prg) {
        this.act23Prg = act23Prg;
    }

    public BigDecimal getAct23Avc() {
        return act23Avc;
    }

    public void setAct23Avc(BigDecimal act23Avc) {
        this.act23Avc = act23Avc;
    }

    public BigDecimal getAct24Prg() {
        return act24Prg;
    }

    public void setAct24Prg(BigDecimal act24Prg) {
        this.act24Prg = act24Prg;
    }

    public BigDecimal getAct24Avc() {
        return act24Avc;
    }

    public void setAct24Avc(BigDecimal act24Avc) {
        this.act24Avc = act24Avc;
    }

    public BigDecimal getAct25Prg() {
        return act25Prg;
    }

    public void setAct25Prg(BigDecimal act25Prg) {
        this.act25Prg = act25Prg;
    }

    public BigDecimal getAct25Avc() {
        return act25Avc;
    }

    public void setAct25Avc(BigDecimal act25Avc) {
        this.act25Avc = act25Avc;
    }

    public BigDecimal getAct26Prg() {
        return act26Prg;
    }

    public void setAct26Prg(BigDecimal act26Prg) {
        this.act26Prg = act26Prg;
    }

    public BigDecimal getAct26Avc() {
        return act26Avc;
    }

    public void setAct26Avc(BigDecimal act26Avc) {
        this.act26Avc = act26Avc;
    }

    public BigDecimal getAct27Prg() {
        return act27Prg;
    }

    public void setAct27Prg(BigDecimal act27Prg) {
        this.act27Prg = act27Prg;
    }

    public BigDecimal getAct27Avc() {
        return act27Avc;
    }

    public void setAct27Avc(BigDecimal act27Avc) {
        this.act27Avc = act27Avc;
    }

    public BigDecimal getAct28Prg() {
        return act28Prg;
    }

    public void setAct28Prg(BigDecimal act28Prg) {
        this.act28Prg = act28Prg;
    }

    public BigDecimal getAct28Avc() {
        return act28Avc;
    }

    public void setAct28Avc(BigDecimal act28Avc) {
        this.act28Avc = act28Avc;
    }

    public BigDecimal getAct29Prg() {
        return act29Prg;
    }

    public void setAct29Prg(BigDecimal act29Prg) {
        this.act29Prg = act29Prg;
    }

    public BigDecimal getAct29Avc() {
        return act29Avc;
    }

    public void setAct29Avc(BigDecimal act29Avc) {
        this.act29Avc = act29Avc;
    }

    public BigDecimal getAct30Prg() {
        return act30Prg;
    }

    public void setAct30Prg(BigDecimal act30Prg) {
        this.act30Prg = act30Prg;
    }

    public BigDecimal getAct30Avc() {
        return act30Avc;
    }

    public void setAct30Avc(BigDecimal act30Avc) {
        this.act30Avc = act30Avc;
    }

    public BigDecimal getAct31Prg() {
        return act31Prg;
    }

    public void setAct31Prg(BigDecimal act31Prg) {
        this.act31Prg = act31Prg;
    }

    public BigDecimal getAct31Avc() {
        return act31Avc;
    }

    public void setAct31Avc(BigDecimal act31Avc) {
        this.act31Avc = act31Avc;
    }

    public BigDecimal getAct32Prg() {
        return act32Prg;
    }

    public void setAct32Prg(BigDecimal act32Prg) {
        this.act32Prg = act32Prg;
    }

    public BigDecimal getAct32Avc() {
        return act32Avc;
    }

    public void setAct32Avc(BigDecimal act32Avc) {
        this.act32Avc = act32Avc;
    }

    public BigDecimal getAct33Prg() {
        return act33Prg;
    }

    public void setAct33Prg(BigDecimal act33Prg) {
        this.act33Prg = act33Prg;
    }

    public BigDecimal getAct33Avc() {
        return act33Avc;
    }

    public void setAct33Avc(BigDecimal act33Avc) {
        this.act33Avc = act33Avc;
    }

    public BigDecimal getAct34Prg() {
        return act34Prg;
    }

    public void setAct34Prg(BigDecimal act34Prg) {
        this.act34Prg = act34Prg;
    }

    public BigDecimal getAct34Avc() {
        return act34Avc;
    }

    public void setAct34Avc(BigDecimal act34Avc) {
        this.act34Avc = act34Avc;
    }

    public BigDecimal getAct35Prg() {
        return act35Prg;
    }

    public void setAct35Prg(BigDecimal act35Prg) {
        this.act35Prg = act35Prg;
    }

    public BigDecimal getAct35Avc() {
        return act35Avc;
    }

    public void setAct35Avc(BigDecimal act35Avc) {
        this.act35Avc = act35Avc;
    }

    public BigDecimal getAct36Prg() {
        return act36Prg;
    }

    public void setAct36Prg(BigDecimal act36Prg) {
        this.act36Prg = act36Prg;
    }

    public BigDecimal getAct36Avc() {
        return act36Avc;
    }

    public void setAct36Avc(BigDecimal act36Avc) {
        this.act36Avc = act36Avc;
    }

    public BigDecimal getAct37Prg() {
        return act37Prg;
    }

    public void setAct37Prg(BigDecimal act37Prg) {
        this.act37Prg = act37Prg;
    }

    public BigDecimal getAct37Avc() {
        return act37Avc;
    }

    public void setAct37Avc(BigDecimal act37Avc) {
        this.act37Avc = act37Avc;
    }

    public BigDecimal getAct38Prg() {
        return act38Prg;
    }

    public void setAct38Prg(BigDecimal act38Prg) {
        this.act38Prg = act38Prg;
    }

    public BigDecimal getAct38Avc() {
        return act38Avc;
    }

    public void setAct38Avc(BigDecimal act38Avc) {
        this.act38Avc = act38Avc;
    }

    public BigDecimal getAct39Prg() {
        return act39Prg;
    }

    public void setAct39Prg(BigDecimal act39Prg) {
        this.act39Prg = act39Prg;
    }

    public BigDecimal getAct39Avc() {
        return act39Avc;
    }

    public void setAct39Avc(BigDecimal act39Avc) {
        this.act39Avc = act39Avc;
    }

    public BigDecimal getAct40Prg() {
        return act40Prg;
    }

    public void setAct40Prg(BigDecimal act40Prg) {
        this.act40Prg = act40Prg;
    }

    public BigDecimal getAct40Avc() {
        return act40Avc;
    }

    public void setAct40Avc(BigDecimal act40Avc) {
        this.act40Avc = act40Avc;
    }

    public BigDecimal getAct41Prg() {
        return act41Prg;
    }

    public void setAct41Prg(BigDecimal act41Prg) {
        this.act41Prg = act41Prg;
    }

    public BigDecimal getAct41Avc() {
        return act41Avc;
    }

    public void setAct41Avc(BigDecimal act41Avc) {
        this.act41Avc = act41Avc;
    }

    public BigDecimal getAct42Prg() {
        return act42Prg;
    }

    public void setAct42Prg(BigDecimal act42Prg) {
        this.act42Prg = act42Prg;
    }

    public BigDecimal getAct42Avc() {
        return act42Avc;
    }

    public void setAct42Avc(BigDecimal act42Avc) {
        this.act42Avc = act42Avc;
    }

    public BigDecimal getAct43Prg() {
        return act43Prg;
    }

    public void setAct43Prg(BigDecimal act43Prg) {
        this.act43Prg = act43Prg;
    }

    public BigDecimal getAct43Avc() {
        return act43Avc;
    }

    public void setAct43Avc(BigDecimal act43Avc) {
        this.act43Avc = act43Avc;
    }
    

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
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
        if (!(object instanceof RptConsolidado)) {
            return false;
        }
        RptConsolidado other = (RptConsolidado) object;
        if ((this.identificador == null && other.identificador != null) || (this.identificador != null && !this.identificador.equals(other.identificador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "invias.cifra.entity.RptConsolidado[ identificador=" + identificador + " ]";
    }
    
}
