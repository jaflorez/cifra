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
@Table(name = "unidad_ejecutora")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UnidadEjecutora.findAll", query = "SELECT u FROM UnidadEjecutora u")
    , @NamedQuery(name = "UnidadEjecutora.findByIdentificador", query = "SELECT u FROM UnidadEjecutora u WHERE u.identificador = :identificador")
    , @NamedQuery(name = "UnidadEjecutora.findByUnidadEjecutora", query = "SELECT u FROM UnidadEjecutora u WHERE u.unidadEjecutora = :unidadEjecutora")
    , @NamedQuery(name = "UnidadEjecutora.findByDireccion", query = "SELECT u FROM UnidadEjecutora u WHERE u.direccion = :direccion")})
public class UnidadEjecutora implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDENTIFICADOR")
    private Integer identificador;
    @Column(name = "UNIDAD_EJECUTORA")
    private String unidadEjecutora;
    @Column(name = "DIRECCION")
    private Integer direccion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkUnidadEjecutora")
    private List<Contrato> contratoList;

    public UnidadEjecutora() {
    }

    public UnidadEjecutora(Integer identificador) {
        this.identificador = identificador;
    }

    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }

    public String getUnidadEjecutora() {
        return unidadEjecutora;
    }

    public void setUnidadEjecutora(String unidadEjecutora) {
        this.unidadEjecutora = unidadEjecutora;
    }

    public Integer getDireccion() {
        return direccion;
    }

    public void setDireccion(Integer direccion) {
        this.direccion = direccion;
    }

    @XmlTransient
    public List<Contrato> getContratoList() {
        return contratoList;
    }

    public void setContratoList(List<Contrato> contratoList) {
        this.contratoList = contratoList;
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
        if (!(object instanceof UnidadEjecutora)) {
            return false;
        }
        UnidadEjecutora other = (UnidadEjecutora) object;
        if ((this.identificador == null && other.identificador != null) || (this.identificador != null && !this.identificador.equals(other.identificador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "invias.cifra.entity.UnidadEjecutora[ identificador=" + identificador + " ]";
    }
    
}
