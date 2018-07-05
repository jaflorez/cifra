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
@Table(name = "contrato_financiero_obra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContratoFinancieroObra.findAll", query = "SELECT c FROM ContratoFinancieroObra c")
    , @NamedQuery(name = "ContratoFinancieroObra.findByIdentificador", query = "SELECT c FROM ContratoFinancieroObra c WHERE c.identificador = :identificador")
    , @NamedQuery(name = "ContratoFinancieroObra.findByValor", query = "SELECT c FROM ContratoFinancieroObra c WHERE c.valor = :valor")
    , @NamedQuery(name = "ContratoFinancieroObra.findByAno", query = "SELECT c FROM ContratoFinancieroObra c WHERE c.ano = :ano")
    , @NamedQuery(name = "ContratoFinancieroObra.findByCorte", query = "SELECT c FROM ContratoFinancieroObra c WHERE c.corte = :corte")})
public class ContratoFinancieroObra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDENTIFICADOR")
    private Integer identificador;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALOR")
    private BigDecimal valor;
    @Column(name = "ANO")
    private Integer ano;
    @Column(name = "CORTE")
    private Integer corte;
    @JoinColumn(name = "FK_CONTRATO", referencedColumnName = "IDENTIFICADOR")
    @ManyToOne(optional = false)
    private Contrato fkContrato;

    public ContratoFinancieroObra() {
    }

    public ContratoFinancieroObra(Integer identificador) {
        this.identificador = identificador;
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

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getCorte() {
        return corte;
    }

    public void setCorte(Integer corte) {
        this.corte = corte;
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
        if (!(object instanceof ContratoFinancieroObra)) {
            return false;
        }
        ContratoFinancieroObra other = (ContratoFinancieroObra) object;
        if ((this.identificador == null && other.identificador != null) || (this.identificador != null && !this.identificador.equals(other.identificador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "invias.cifra.entity.ContratoFinancieroObra[ identificador=" + identificador + " ]";
    }
    
}
