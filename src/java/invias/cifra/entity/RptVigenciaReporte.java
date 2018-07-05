/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jaflorez
 */
@Entity
@Table(name = "rpt_vigencia_reporte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RptVigenciaReporte.findAll", query = "SELECT r FROM RptVigenciaReporte r")
    , @NamedQuery(name = "RptVigenciaReporte.findByIdentificador", query = "SELECT r FROM RptVigenciaReporte r WHERE r.identificador = :identificador")
    , @NamedQuery(name = "RptVigenciaReporte.findByNumero", query = "SELECT r FROM RptVigenciaReporte r WHERE r.numero = :numero")
    , @NamedQuery(name = "RptVigenciaReporte.findByAno", query = "SELECT r FROM RptVigenciaReporte r WHERE r.ano = :ano")
    , @NamedQuery(name = "RptVigenciaReporte.findByProgramado", query = "SELECT r FROM RptVigenciaReporte r WHERE r.programado = :programado")
    , @NamedQuery(name = "RptVigenciaReporte.findByAvance", query = "SELECT r FROM RptVigenciaReporte r WHERE r.avance = :avance")
    , @NamedQuery(name = "RptVigenciaReporte.findByAcmFactuVigencia", query = "SELECT r FROM RptVigenciaReporte r WHERE r.acmFactuVigencia = :acmFactuVigencia")
    , @NamedQuery(name = "RptVigenciaReporte.findByFacturadoReporte", query = "SELECT r FROM RptVigenciaReporte r WHERE r.facturadoReporte = :facturadoReporte")
    , @NamedQuery(name = "RptVigenciaReporte.findByAvanceFinal", query = "SELECT r FROM RptVigenciaReporte r WHERE r.avanceFinal = :avanceFinal")})
public class RptVigenciaReporte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "identificador")
    private Integer identificador;
    @Basic(optional = false)
    @Column(name = "numero")
    private int numero;
    @Basic(optional = false)
    @Column(name = "ano")
    private int ano;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "programado")
    private BigDecimal programado;
    @Column(name = "avance")
    private BigDecimal avance;
    @Column(name = "acm_factu_vigencia")
    private BigDecimal acmFactuVigencia;
    @Column(name = "facturado_reporte")
    private BigDecimal facturadoReporte;
    @Column(name = "avance_final")
    private BigDecimal avanceFinal;

    public RptVigenciaReporte() {
    }

    public RptVigenciaReporte(Integer identificador) {
        this.identificador = identificador;
    }

    public RptVigenciaReporte(Integer identificador, int numero, int ano, BigDecimal programado) {
        this.identificador = identificador;
        this.numero = numero;
        this.ano = ano;
        this.programado = programado;
    }

    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public BigDecimal getProgramado() {
        return programado;
    }

    public void setProgramado(BigDecimal programado) {
        this.programado = programado;
    }

    public BigDecimal getAvance() {
        return avance;
    }

    public void setAvance(BigDecimal avance) {
        this.avance = avance;
    }

    public BigDecimal getAcmFactuVigencia() {
        return acmFactuVigencia;
    }

    public void setAcmFactuVigencia(BigDecimal acmFactuVigencia) {
        this.acmFactuVigencia = acmFactuVigencia;
    }

    public BigDecimal getFacturadoReporte() {
        return facturadoReporte;
    }

    public void setFacturadoReporte(BigDecimal facturadoReporte) {
        this.facturadoReporte = facturadoReporte;
    }

    public BigDecimal getAvanceFinal() {
        return avanceFinal;
    }

    public void setAvanceFinal(BigDecimal avanceFinal) {
        this.avanceFinal = avanceFinal;
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
        if (!(object instanceof RptVigenciaReporte)) {
            return false;
        }
        RptVigenciaReporte other = (RptVigenciaReporte) object;
        if ((this.identificador == null && other.identificador != null) || (this.identificador != null && !this.identificador.equals(other.identificador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "invias.cifra.entity.RptVigenciaReporte[ identificador=" + identificador + " ]";
    }
    
}
