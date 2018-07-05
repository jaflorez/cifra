/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.entity;

import java.io.Serializable;
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
@Table(name = "contrato_tercero_obra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContratoTerceroObra.findAll", query = "SELECT c FROM ContratoTerceroObra c")
    , @NamedQuery(name = "ContratoTerceroObra.findByIdentificador", query = "SELECT c FROM ContratoTerceroObra c WHERE c.identificador = :identificador")
    , @NamedQuery(name = "ContratoTerceroObra.findByCantidad", query = "SELECT c FROM ContratoTerceroObra c WHERE c.cantidad = :cantidad")})
public class ContratoTerceroObra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDENTIFICADOR")
    private Integer identificador;
    @Column(name = "CANTIDAD")
    private Integer cantidad;
    @JoinColumn(name = "FK_CONTRATO", referencedColumnName = "IDENTIFICADOR")
    @ManyToOne(optional = false)
    private Contrato fkContrato;
    @JoinColumn(name = "FK_TERCERO", referencedColumnName = "IDENTIFICADOR")
    @ManyToOne(optional = false)
    private Tercero fkTercero;

    public ContratoTerceroObra() {
    }

    public ContratoTerceroObra(Integer identificador) {
        this.identificador = identificador;
    }

    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Contrato getFkContrato() {
        return fkContrato;
    }

    public void setFkContrato(Contrato fkContrato) {
        this.fkContrato = fkContrato;
    }

    public Tercero getFkTercero() {
        return fkTercero;
    }

    public void setFkTercero(Tercero fkTercero) {
        this.fkTercero = fkTercero;
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
        if (!(object instanceof ContratoTerceroObra)) {
            return false;
        }
        ContratoTerceroObra other = (ContratoTerceroObra) object;
        if ((this.identificador == null && other.identificador != null) || (this.identificador != null && !this.identificador.equals(other.identificador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "invias.cifra.entity.ContratoTerceroObra[ identificador=" + identificador + " ]";
    }
    
}
