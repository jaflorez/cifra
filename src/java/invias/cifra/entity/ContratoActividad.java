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
@Table(name = "contrato_actividad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContratoActividad.findAll", query = "SELECT c FROM ContratoActividad c")
    , @NamedQuery(name = "ContratoActividad.findByIdentificador", query = "SELECT c FROM ContratoActividad c WHERE c.identificador = :identificador")
    , @NamedQuery(name = "ContratoActividad.findByCantidad", query = "SELECT c FROM ContratoActividad c WHERE c.cantidad = :cantidad")})
public class ContratoActividad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDENTIFICADOR")
    private Integer identificador;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CANTIDAD")
    private BigDecimal cantidad;
    @JoinColumn(name = "FK_ACTIVIDAD", referencedColumnName = "IDENTIFICADOR")
    @ManyToOne(optional = false)
    private Actividad fkActividad;
    @JoinColumn(name = "FK_CONTRATO", referencedColumnName = "IDENTIFICADOR")
    @ManyToOne(optional = false)
    private Contrato fkContrato;
    @OneToMany(mappedBy = "fkContratoactividad")
    private List<ReporteActividad> reporteActividadList;

    public ContratoActividad() {
    }

    public ContratoActividad(Integer identificador) {
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

    public Actividad getFkActividad() {
        return fkActividad;
    }

    public void setFkActividad(Actividad fkActividad) {
        this.fkActividad = fkActividad;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (identificador != null ? identificador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContratoActividad)) {
            return false;
        }
        ContratoActividad other = (ContratoActividad) object;
        if ((this.identificador == null && other.identificador != null) || (this.identificador != null && !this.identificador.equals(other.identificador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "invias.cifra.entity.ContratoActividad[ identificador=" + identificador + " ]";
    }
    
}
