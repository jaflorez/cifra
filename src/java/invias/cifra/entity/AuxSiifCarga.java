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
@Table(name = "aux_siif_carga")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuxSiifCarga.findAll", query = "SELECT a FROM AuxSiifCarga a")
    , @NamedQuery(name = "AuxSiifCarga.findByNumeroFila", query = "SELECT a FROM AuxSiifCarga a WHERE a.numeroFila = :numeroFila")
    , @NamedQuery(name = "AuxSiifCarga.findByTipo", query = "SELECT a FROM AuxSiifCarga a WHERE a.tipo = :tipo")
    , @NamedQuery(name = "AuxSiifCarga.findByFechaCarga", query = "SELECT a FROM AuxSiifCarga a WHERE a.fechaCarga = :fechaCarga")
    , @NamedQuery(name = "AuxSiifCarga.findByNumeroDocumento", query = "SELECT a FROM AuxSiifCarga a WHERE a.numeroDocumento = :numeroDocumento")
    , @NamedQuery(name = "AuxSiifCarga.findByRubro", query = "SELECT a FROM AuxSiifCarga a WHERE a.rubro = :rubro")
    , @NamedQuery(name = "AuxSiifCarga.findByIdentificacion", query = "SELECT a FROM AuxSiifCarga a WHERE a.identificacion = :identificacion")
    , @NamedQuery(name = "AuxSiifCarga.findByNombreEmpresa", query = "SELECT a FROM AuxSiifCarga a WHERE a.nombreEmpresa = :nombreEmpresa")
    , @NamedQuery(name = "AuxSiifCarga.findByDependencia", query = "SELECT a FROM AuxSiifCarga a WHERE a.dependencia = :dependencia")
    , @NamedQuery(name = "AuxSiifCarga.findByRecurso", query = "SELECT a FROM AuxSiifCarga a WHERE a.recurso = :recurso")
    , @NamedQuery(name = "AuxSiifCarga.findByValorActual", query = "SELECT a FROM AuxSiifCarga a WHERE a.valorActual = :valorActual")
    , @NamedQuery(name = "AuxSiifCarga.findByValorInicial", query = "SELECT a FROM AuxSiifCarga a WHERE a.valorInicial = :valorInicial")
    , @NamedQuery(name = "AuxSiifCarga.findBySaldoPorUtilizar", query = "SELECT a FROM AuxSiifCarga a WHERE a.saldoPorUtilizar = :saldoPorUtilizar")
    , @NamedQuery(name = "AuxSiifCarga.findByTipoDocumentoSoporte", query = "SELECT a FROM AuxSiifCarga a WHERE a.tipoDocumentoSoporte = :tipoDocumentoSoporte")
    , @NamedQuery(name = "AuxSiifCarga.findByNumeroDocumentoSoporte", query = "SELECT a FROM AuxSiifCarga a WHERE a.numeroDocumentoSoporte = :numeroDocumentoSoporte")
    , @NamedQuery(name = "AuxSiifCarga.findByObservaciones", query = "SELECT a FROM AuxSiifCarga a WHERE a.observaciones = :observaciones")})
public class AuxSiifCarga implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NUMERO_FILA")
    private Integer numeroFila;
    @Basic(optional = false)
    @Column(name = "TIPO")
    private String tipo;
    @Basic(optional = false)
    @Column(name = "FECHA_CARGA")
    @Temporal(TemporalType.DATE)
    private Date fechaCarga;
    @Basic(optional = false)
    @Column(name = "NUMERO_DOCUMENTO")
    private int numeroDocumento;
    @Column(name = "RUBRO")
    private String rubro;
    @Column(name = "IDENTIFICACION")
    private String identificacion;
    @Column(name = "NOMBRE_EMPRESA")
    private String nombreEmpresa;
    @Column(name = "DEPENDENCIA")
    private String dependencia;
    @Column(name = "RECURSO")
    private String recurso;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALOR_ACTUAL")
    private BigDecimal valorActual;
    @Column(name = "VALOR_INICIAL")
    private BigDecimal valorInicial;
    @Column(name = "SALDO_POR_UTILIZAR")
    private BigDecimal saldoPorUtilizar;
    @Column(name = "TIPO_DOCUMENTO_SOPORTE")
    private String tipoDocumentoSoporte;
    @Column(name = "NUMERO_DOCUMENTO_SOPORTE")
    private String numeroDocumentoSoporte;
    @Column(name = "OBSERVACIONES")
    private String observaciones;

    public AuxSiifCarga() {
    }

    public AuxSiifCarga(Integer numeroFila) {
        this.numeroFila = numeroFila;
    }

    public AuxSiifCarga(Integer numeroFila, String tipo, Date fechaCarga, int numeroDocumento) {
        this.numeroFila = numeroFila;
        this.tipo = tipo;
        this.fechaCarga = fechaCarga;
        this.numeroDocumento = numeroDocumento;
    }

    public Integer getNumeroFila() {
        return numeroFila;
    }

    public void setNumeroFila(Integer numeroFila) {
        this.numeroFila = numeroFila;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFechaCarga() {
        return fechaCarga;
    }

    public void setFechaCarga(Date fechaCarga) {
        this.fechaCarga = fechaCarga;
    }

    public int getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(int numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getRubro() {
        return rubro;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }

    public String getRecurso() {
        return recurso;
    }

    public void setRecurso(String recurso) {
        this.recurso = recurso;
    }

    public BigDecimal getValorActual() {
        return valorActual;
    }

    public void setValorActual(BigDecimal valorActual) {
        this.valorActual = valorActual;
    }

    public BigDecimal getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(BigDecimal valorInicial) {
        this.valorInicial = valorInicial;
    }

    public BigDecimal getSaldoPorUtilizar() {
        return saldoPorUtilizar;
    }

    public void setSaldoPorUtilizar(BigDecimal saldoPorUtilizar) {
        this.saldoPorUtilizar = saldoPorUtilizar;
    }

    public String getTipoDocumentoSoporte() {
        return tipoDocumentoSoporte;
    }

    public void setTipoDocumentoSoporte(String tipoDocumentoSoporte) {
        this.tipoDocumentoSoporte = tipoDocumentoSoporte;
    }

    public String getNumeroDocumentoSoporte() {
        return numeroDocumentoSoporte;
    }

    public void setNumeroDocumentoSoporte(String numeroDocumentoSoporte) {
        this.numeroDocumentoSoporte = numeroDocumentoSoporte;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numeroFila != null ? numeroFila.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuxSiifCarga)) {
            return false;
        }
        AuxSiifCarga other = (AuxSiifCarga) object;
        if ((this.numeroFila == null && other.numeroFila != null) || (this.numeroFila != null && !this.numeroFila.equals(other.numeroFila))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "invias.cifra.entity.AuxSiifCarga[ numeroFila=" + numeroFila + " ]";
    }
    
}
