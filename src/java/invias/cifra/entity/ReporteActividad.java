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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jaflorez
 */
@Entity
@Table(name = "reporte_actividad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReporteActividad.findAll", query = "SELECT r FROM ReporteActividad r")
    , @NamedQuery(name = "ReporteActividad.findByIdentificador", query = "SELECT r FROM ReporteActividad r WHERE r.identificador = :identificador")
    , @NamedQuery(name = "ReporteActividad.findByCantidad", query = "SELECT r FROM ReporteActividad r WHERE r.cantidad = :cantidad")})
public class ReporteActividad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDENTIFICADOR")
    private Integer identificador;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CANTIDAD")
    private BigDecimal cantidad;
    @JoinColumn(name = "FK_CONTRATOACTIVIDAD", referencedColumnName = "IDENTIFICADOR")
    @ManyToOne
    private ContratoActividad fkContratoactividad;
    @JoinColumn(name = "FK_CONTRATOTRAMO", referencedColumnName = "IDENTIFICADOR")
    @ManyToOne
    private ContratoTramo fkContratotramo;
    @JoinColumn(name = "FK_REPORTE", referencedColumnName = "IDENTIFICADOR")
    @ManyToOne
    private Reporte fkReporte;

    public ReporteActividad() {
    }

    public ReporteActividad(Integer identificador) {
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

    public ContratoActividad getFkContratoactividad() {
        return fkContratoactividad;
    }

    public void setFkContratoactividad(ContratoActividad fkContratoactividad) {
        this.fkContratoactividad = fkContratoactividad;
    }

    public ContratoTramo getFkContratotramo() {
        return fkContratotramo;
    }

    public void setFkContratotramo(ContratoTramo fkContratotramo) {
        this.fkContratotramo = fkContratotramo;
    }

    public Reporte getFkReporte() {
        return fkReporte;
    }

    public void setFkReporte(Reporte fkReporte) {
        this.fkReporte = fkReporte;
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
        if (!(object instanceof ReporteActividad)) {
            return false;
        }
        ReporteActividad other = (ReporteActividad) object;
        if ((this.identificador == null && other.identificador != null) || (this.identificador != null && !this.identificador.equals(other.identificador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "invias.cifra.entity.ReporteActividad[ identificador=" + identificador + " ]";
    }
    
}
