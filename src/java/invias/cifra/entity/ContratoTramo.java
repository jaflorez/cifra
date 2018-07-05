/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jaflorez
 */
@Entity
@Table(name = "contrato_tramo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContratoTramo.findAll", query = "SELECT c FROM ContratoTramo c")
    , @NamedQuery(name = "ContratoTramo.findByIdentificador", query = "SELECT c FROM ContratoTramo c WHERE c.identificador = :identificador")
    , @NamedQuery(name = "ContratoTramo.findByTramo", query = "SELECT c FROM ContratoTramo c WHERE c.tramo = :tramo")
    , @NamedQuery(name = "ContratoTramo.findByNumero", query = "SELECT c FROM ContratoTramo c WHERE c.numero = :numero")
    , @NamedQuery(name = "ContratoTramo.findByCodigovia", query = "SELECT c FROM ContratoTramo c WHERE c.codigovia = :codigovia")
    , @NamedQuery(name = "ContratoTramo.findByPrInicial", query = "SELECT c FROM ContratoTramo c WHERE c.prInicial = :prInicial")
    , @NamedQuery(name = "ContratoTramo.findByDisIncial", query = "SELECT c FROM ContratoTramo c WHERE c.disIncial = :disIncial")
    , @NamedQuery(name = "ContratoTramo.findByPrFinal", query = "SELECT c FROM ContratoTramo c WHERE c.prFinal = :prFinal")
    , @NamedQuery(name = "ContratoTramo.findByDisFinal", query = "SELECT c FROM ContratoTramo c WHERE c.disFinal = :disFinal")})
public class ContratoTramo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDENTIFICADOR")
    private Integer identificador;
    @Column(name = "TRAMO")
    private String tramo;
    @Column(name = "NUMERO")
    private Integer numero;
    @Column(name = "CODIGOVIA")
    private String codigovia;
    @Column(name = "PR_INICIAL")
    private Integer prInicial;
    @Column(name = "DIS_INCIAL")
    private Integer disIncial;
    @Column(name = "PR_FINAL")
    private Integer prFinal;
    @Column(name = "DIS_FINAL")
    private Integer disFinal;
    @Lob
    @Column(name = "GEOMETRIA")
    private String geometria;
    @JoinColumn(name = "FK_CONTRATO", referencedColumnName = "IDENTIFICADOR")
    @ManyToOne(optional = false)
    private Contrato fkContrato;
    @OneToMany(mappedBy = "fkContratotramo")
    private List<ReporteActividad> reporteActividadList;
    @OneToMany(mappedBy = "fkContratotramo")
    private List<ReporteMaquinaria> reporteMaquinariaList;

    public ContratoTramo() {
    }

    public ContratoTramo(Integer identificador) {
        this.identificador = identificador;
    }

    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }

    public String getTramo() {
        return tramo;
    }

    public void setTramo(String tramo) {
        this.tramo = tramo;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getCodigovia() {
        return codigovia;
    }

    public void setCodigovia(String codigovia) {
        this.codigovia = codigovia;
    }

    public Integer getPrInicial() {
        return prInicial;
    }

    public void setPrInicial(Integer prInicial) {
        this.prInicial = prInicial;
    }

    public Integer getDisIncial() {
        return disIncial;
    }

    public void setDisIncial(Integer disIncial) {
        this.disIncial = disIncial;
    }

    public Integer getPrFinal() {
        return prFinal;
    }

    public void setPrFinal(Integer prFinal) {
        this.prFinal = prFinal;
    }

    public Integer getDisFinal() {
        return disFinal;
    }

    public void setDisFinal(Integer disFinal) {
        this.disFinal = disFinal;
    }

    public String getGeometria() {
        return geometria;
    }

    public void setGeometria(String geometria) {
        this.geometria = geometria;
    }

    public Contrato getFkContrato() {
        return fkContrato;
    }

    public void setFkContrato(Contrato fkContrato) {
        this.fkContrato = fkContrato;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (identificador != null ? identificador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContratoTramo)) {
            return false;
        }
        ContratoTramo other = (ContratoTramo) object;
        if ((this.identificador == null && other.identificador != null) || (this.identificador != null && !this.identificador.equals(other.identificador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "invias.cifra.entity.ContratoTramo[ identificador=" + identificador + " ]";
    }
    
}
