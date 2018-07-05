/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jaflorez
 */
@Entity
@Table(name = "contrato_maquinaria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContratoMaquinaria.findAll", query = "SELECT c FROM ContratoMaquinaria c")
    , @NamedQuery(name = "ContratoMaquinaria.findByIdentificador", query = "SELECT c FROM ContratoMaquinaria c WHERE c.identificador = :identificador")
    , @NamedQuery(name = "ContratoMaquinaria.findByCantidad", query = "SELECT c FROM ContratoMaquinaria c WHERE c.cantidad = :cantidad")})
public class ContratoMaquinaria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDENTIFICADOR")
    private Integer identificador;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CANTIDAD")
    private BigDecimal cantidad;
    @JoinColumn(name = "FK_CONTRATO", referencedColumnName = "IDENTIFICADOR")
    @ManyToOne(optional = false)
    private Contrato fkContrato;
    @JoinColumn(name = "FK_MAQUINARIA", referencedColumnName = "IDENTIFICADOR")
    @ManyToOne(optional = false)
    private Maquinaria fkMaquinaria;
    @OneToMany(mappedBy = "fkContratomaquinaria")
    private List<ReporteMaquinaria> reporteMaquinariaList;

    public ContratoMaquinaria() {
    }

    public ContratoMaquinaria(Integer identificador) {
        this.identificador = identificador;
    }

    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public Contrato getFkContrato() {
        return fkContrato;
    }

    public void setFkContrato(Contrato fkContrato) {
        this.fkContrato = fkContrato;
    }

    public Maquinaria getFkMaquinaria() {
        return fkMaquinaria;
    }

    public void setFkMaquinaria(Maquinaria fkMaquinaria) {
        this.fkMaquinaria = fkMaquinaria;
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
        if (!(object instanceof ContratoMaquinaria)) {
            return false;
        }
        ContratoMaquinaria other = (ContratoMaquinaria) object;
        if ((this.identificador == null && other.identificador != null) || (this.identificador != null && !this.identificador.equals(other.identificador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "invias.cifra.entity.ContratoMaquinaria[ identificador=" + identificador + " ]";
    }
    
}
