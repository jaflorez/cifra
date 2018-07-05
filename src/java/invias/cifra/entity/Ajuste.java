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
@Table(name = "ajuste")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ajuste.findAll", query = "SELECT a FROM Ajuste a")
    , @NamedQuery(name = "Ajuste.findByIdentificador", query = "SELECT a FROM Ajuste a WHERE a.identificador = :identificador")
    , @NamedQuery(name = "Ajuste.findByAjuste", query = "SELECT a FROM Ajuste a WHERE a.ajuste = :ajuste")})
public class Ajuste implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDENTIFICADOR")
    private Integer identificador;
    @Column(name = "AJUSTE")
    private String ajuste;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkAjuste")
    private List<ContratoAjusteObra> contratoAjusteObraList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkAjuste")
    private List<ContratoAjusteInter> contratoAjusteInterList;

    public Ajuste() {
    }

    public Ajuste(Integer identificador) {
        this.identificador = identificador;
    }

    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }

    public String getAjuste() {
        return ajuste;
    }

    public void setAjuste(String ajuste) {
        this.ajuste = ajuste;
    }

    @XmlTransient
    public List<ContratoAjusteObra> getContratoAjusteObraList() {
        return contratoAjusteObraList;
    }

    public void setContratoAjusteObraList(List<ContratoAjusteObra> contratoAjusteObraList) {
        this.contratoAjusteObraList = contratoAjusteObraList;
    }

    @XmlTransient
    public List<ContratoAjusteInter> getContratoAjusteInterList() {
        return contratoAjusteInterList;
    }

    public void setContratoAjusteInterList(List<ContratoAjusteInter> contratoAjusteInterList) {
        this.contratoAjusteInterList = contratoAjusteInterList;
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
        if (!(object instanceof Ajuste)) {
            return false;
        }
        Ajuste other = (Ajuste) object;
        if ((this.identificador == null && other.identificador != null) || (this.identificador != null && !this.identificador.equals(other.identificador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "invias.cifra.entity.Ajuste[ identificador=" + identificador + " ]";
    }
    
}
