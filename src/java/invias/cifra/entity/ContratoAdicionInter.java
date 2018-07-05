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
@Table(name = "contrato_adicion_inter")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContratoAdicionInter.findAll", query = "SELECT c FROM ContratoAdicionInter c")
    , @NamedQuery(name = "ContratoAdicionInter.findByIdentificador", query = "SELECT c FROM ContratoAdicionInter c WHERE c.identificador = :identificador")
    , @NamedQuery(name = "ContratoAdicionInter.findByValor", query = "SELECT c FROM ContratoAdicionInter c WHERE c.valor = :valor")
    , @NamedQuery(name = "ContratoAdicionInter.findByFecha", query = "SELECT c FROM ContratoAdicionInter c WHERE c.fecha = :fecha")})
public class ContratoAdicionInter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDENTIFICADOR")
    private Integer identificador;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "VALOR")
    private BigDecimal valor;
    @Basic(optional = false)
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "FK_CONTRATO", referencedColumnName = "IDENTIFICADOR")
    @ManyToOne(optional = false)
    private Contrato fkContrato;

    public ContratoAdicionInter() {
    }

    public ContratoAdicionInter(Integer identificador) {
        this.identificador = identificador;
    }

    public ContratoAdicionInter(Integer identificador, BigDecimal valor, Date fecha) {
        this.identificador = identificador;
        this.valor = valor;
        this.fecha = fecha;
    }

    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
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
        if (!(object instanceof ContratoAdicionInter)) {
            return false;
        }
        ContratoAdicionInter other = (ContratoAdicionInter) object;
        if ((this.identificador == null && other.identificador != null) || (this.identificador != null && !this.identificador.equals(other.identificador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "invias.cifra.entity.ContratoAdicionInter[ identificador=" + identificador + " ]";
    }
    
}
