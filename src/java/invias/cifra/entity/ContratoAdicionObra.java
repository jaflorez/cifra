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
@Table(name = "contrato_adicion_obra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContratoAdicionObra.findAll", query = "SELECT c FROM ContratoAdicionObra c")
    , @NamedQuery(name = "ContratoAdicionObra.findByIdentificador", query = "SELECT c FROM ContratoAdicionObra c WHERE c.identificador = :identificador")
    , @NamedQuery(name = "ContratoAdicionObra.findByValor", query = "SELECT c FROM ContratoAdicionObra c WHERE c.valor = :valor")
    , @NamedQuery(name = "ContratoAdicionObra.findByTipo", query = "SELECT c FROM ContratoAdicionObra c WHERE c.tipo = :tipo")
    , @NamedQuery(name = "ContratoAdicionObra.findByVigencia", query = "SELECT c FROM ContratoAdicionObra c WHERE c.vigencia = :vigencia")
    , @NamedQuery(name = "ContratoAdicionObra.findByFecha", query = "SELECT c FROM ContratoAdicionObra c WHERE c.fecha = :fecha")})
public class ContratoAdicionObra implements Serializable {

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
    @Column(name = "TIPO")
    private String tipo;
    @Column(name = "VIGENCIA")
    private Integer vigencia;
    @Basic(optional = false)
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "FK_CONTRATO", referencedColumnName = "IDENTIFICADOR")
    @ManyToOne(optional = false)
    private Contrato fkContrato;

    public ContratoAdicionObra() {
    }

    public ContratoAdicionObra(Integer identificador) {
        this.identificador = identificador;
    }

    public ContratoAdicionObra(Integer identificador, BigDecimal valor, Date fecha) {
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getVigencia() {
        return vigencia;
    }

    public void setVigencia(Integer vigencia) {
        this.vigencia = vigencia;
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
        if (!(object instanceof ContratoAdicionObra)) {
            return false;
        }
        ContratoAdicionObra other = (ContratoAdicionObra) object;
        if ((this.identificador == null && other.identificador != null) || (this.identificador != null && !this.identificador.equals(other.identificador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "invias.cifra.entity.ContratoAdicionObra[ identificador=" + identificador + " ]";
    }
    
}
