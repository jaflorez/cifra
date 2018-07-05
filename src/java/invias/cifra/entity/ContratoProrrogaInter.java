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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "contrato_prorroga_inter")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContratoProrrogaInter.findAll", query = "SELECT c FROM ContratoProrrogaInter c")
    , @NamedQuery(name = "ContratoProrrogaInter.findByIdentificador", query = "SELECT c FROM ContratoProrrogaInter c WHERE c.identificador = :identificador")
    , @NamedQuery(name = "ContratoProrrogaInter.findByCantidad", query = "SELECT c FROM ContratoProrrogaInter c WHERE c.cantidad = :cantidad")
    , @NamedQuery(name = "ContratoProrrogaInter.findByFecha", query = "SELECT c FROM ContratoProrrogaInter c WHERE c.fecha = :fecha")})
public class ContratoProrrogaInter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDENTIFICADOR")
    private Integer identificador;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "CANTIDAD")
    private BigDecimal cantidad;
    @Basic(optional = false)
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "FK_CONTRATO", referencedColumnName = "IDENTIFICADOR")
    @ManyToOne(optional = false)
    private Contrato fkContrato;

    public ContratoProrrogaInter() {
    }

    public ContratoProrrogaInter(Integer identificador) {
        this.identificador = identificador;
    }

    public ContratoProrrogaInter(Integer identificador, BigDecimal cantidad, Date fecha) {
        this.identificador = identificador;
        this.cantidad = cantidad;
        this.fecha = fecha;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Contrato getFkContrato() {
        return fkContrato;
    }

    public void setFkContrato(Contrato fkContrato) {
        this.fkContrato = fkContrato;
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
        if (!(object instanceof ContratoProrrogaInter)) {
            return false;
        }
        ContratoProrrogaInter other = (ContratoProrrogaInter) object;
        if ((this.identificador == null && other.identificador != null) || (this.identificador != null && !this.identificador.equals(other.identificador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "invias.cifra.entity.ContratoProrrogaInter[ identificador=" + identificador + " ]";
    }
    
}
