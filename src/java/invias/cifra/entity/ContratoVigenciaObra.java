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
@Table(name = "contrato_vigencia_obra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContratoVigenciaObra.findAll", query = "SELECT c FROM ContratoVigenciaObra c")
    , @NamedQuery(name = "ContratoVigenciaObra.findByIdentificador", query = "SELECT c FROM ContratoVigenciaObra c WHERE c.identificador = :identificador")
    , @NamedQuery(name = "ContratoVigenciaObra.findByValor", query = "SELECT c FROM ContratoVigenciaObra c WHERE c.valor = :valor")
    , @NamedQuery(name = "ContratoVigenciaObra.findByAno", query = "SELECT c FROM ContratoVigenciaObra c WHERE c.ano = :ano")
    , @NamedQuery(name = "ContratoVigenciaObra.findByAvance", query = "SELECT c FROM ContratoVigenciaObra c WHERE c.avance = :avance")
    , @NamedQuery(name = "ContratoVigenciaObra.findByTipo", query = "SELECT c FROM ContratoVigenciaObra c WHERE c.tipo = :tipo")})
public class ContratoVigenciaObra implements Serializable {

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
    @Column(name = "ANO")
    private int ano;
    @Column(name = "AVANCE")
    private BigDecimal avance;
    @Column(name = "TIPO")
    private String tipo;
    @JoinColumn(name = "FK_CONTRATO", referencedColumnName = "IDENTIFICADOR")
    @ManyToOne(optional = false)
    private Contrato fkContrato;

    public ContratoVigenciaObra() {
    }

    public ContratoVigenciaObra(Integer identificador) {
        this.identificador = identificador;
    }

    public ContratoVigenciaObra(Integer identificador, BigDecimal valor, int ano) {
        this.identificador = identificador;
        this.valor = valor;
        this.ano = ano;
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

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public BigDecimal getAvance() {
        return avance;
    }

    public void setAvance(BigDecimal avance) {
        this.avance = avance;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
        if (!(object instanceof ContratoVigenciaObra)) {
            return false;
        }
        ContratoVigenciaObra other = (ContratoVigenciaObra) object;
        if ((this.identificador == null && other.identificador != null) || (this.identificador != null && !this.identificador.equals(other.identificador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "invias.cifra.entity.ContratoVigenciaObra[ identificador=" + identificador + " ]";
    }
    
}
