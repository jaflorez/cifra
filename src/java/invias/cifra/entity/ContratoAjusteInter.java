/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.entity;

import java.io.Serializable;
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
@Table(name = "contrato_ajuste_inter")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContratoAjusteInter.findAll", query = "SELECT c FROM ContratoAjusteInter c")
    , @NamedQuery(name = "ContratoAjusteInter.findByIdentificador", query = "SELECT c FROM ContratoAjusteInter c WHERE c.identificador = :identificador")
    , @NamedQuery(name = "ContratoAjusteInter.findByFecha", query = "SELECT c FROM ContratoAjusteInter c WHERE c.fecha = :fecha")
    , @NamedQuery(name = "ContratoAjusteInter.findByDescripcion", query = "SELECT c FROM ContratoAjusteInter c WHERE c.descripcion = :descripcion")})
public class ContratoAjusteInter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDENTIFICADOR")
    private Integer identificador;
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @JoinColumn(name = "FK_AJUSTE", referencedColumnName = "IDENTIFICADOR")
    @ManyToOne(optional = false)
    private Ajuste fkAjuste;
    @JoinColumn(name = "FK_CONTRATO", referencedColumnName = "IDENTIFICADOR")
    @ManyToOne(optional = false)
    private Contrato fkContrato;

    public ContratoAjusteInter() {
    }

    public ContratoAjusteInter(Integer identificador) {
        this.identificador = identificador;
    }

    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Ajuste getFkAjuste() {
        return fkAjuste;
    }

    public void setFkAjuste(Ajuste fkAjuste) {
        this.fkAjuste = fkAjuste;
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
        if (!(object instanceof ContratoAjusteInter)) {
            return false;
        }
        ContratoAjusteInter other = (ContratoAjusteInter) object;
        if ((this.identificador == null && other.identificador != null) || (this.identificador != null && !this.identificador.equals(other.identificador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "invias.cifra.entity.ContratoAjusteInter[ identificador=" + identificador + " ]";
    }
    
}
