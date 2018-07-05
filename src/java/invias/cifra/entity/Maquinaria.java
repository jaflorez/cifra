/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "maquinaria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Maquinaria.findAll", query = "SELECT m FROM Maquinaria m")
    , @NamedQuery(name = "Maquinaria.findByIdentificador", query = "SELECT m FROM Maquinaria m WHERE m.identificador = :identificador")
    , @NamedQuery(name = "Maquinaria.findByMaquinaria", query = "SELECT m FROM Maquinaria m WHERE m.maquinaria = :maquinaria")})
public class Maquinaria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDENTIFICADOR")
    private Integer identificador;
    @Column(name = "MAQUINARIA")
    private String maquinaria;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkMaquinaria")
    private List<ContratoMaquinaria> contratoMaquinariaList;

    public Maquinaria() {
    }

    public Maquinaria(Integer identificador) {
        this.identificador = identificador;
    }

    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }

    public String getMaquinaria() {
        return maquinaria;
    }

    public void setMaquinaria(String maquinaria) {
        this.maquinaria = maquinaria;
    }

    @XmlTransient
    public List<ContratoMaquinaria> getContratoMaquinariaList() {
        return contratoMaquinariaList;
    }

    public void setContratoMaquinariaList(List<ContratoMaquinaria> contratoMaquinariaList) {
        this.contratoMaquinariaList = contratoMaquinariaList;
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
        if (!(object instanceof Maquinaria)) {
            return false;
        }
        Maquinaria other = (Maquinaria) object;
        if ((this.identificador == null && other.identificador != null) || (this.identificador != null && !this.identificador.equals(other.identificador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "invias.cifra.entity.Maquinaria[ identificador=" + identificador + " ]";
    }
    
}
