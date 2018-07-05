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
@Table(name = "direccion_territorial")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DireccionTerritorial.findAll", query = "SELECT d FROM DireccionTerritorial d")
    , @NamedQuery(name = "DireccionTerritorial.findByIdentificador", query = "SELECT d FROM DireccionTerritorial d WHERE d.identificador = :identificador")
    , @NamedQuery(name = "DireccionTerritorial.findByDireccionTerrritorial", query = "SELECT d FROM DireccionTerritorial d WHERE d.direccionTerrritorial = :direccionTerrritorial")})
public class DireccionTerritorial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDENTIFICADOR")
    private Integer identificador;
    @Column(name = "DIRECCION_TERRRITORIAL")
    private String direccionTerrritorial;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkDireccionTerritorial")
    private List<Contrato> contratoList;

    public DireccionTerritorial() {
    }

    public DireccionTerritorial(Integer identificador) {
        this.identificador = identificador;
    }

    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }

    public String getDireccionTerrritorial() {
        return direccionTerrritorial;
    }

    public void setDireccionTerrritorial(String direccionTerrritorial) {
        this.direccionTerrritorial = direccionTerrritorial;
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
        if (!(object instanceof DireccionTerritorial)) {
            return false;
        }
        DireccionTerritorial other = (DireccionTerritorial) object;
        if ((this.identificador == null && other.identificador != null) || (this.identificador != null && !this.identificador.equals(other.identificador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "invias.cifra.entity.DireccionTerritorial[ identificador=" + identificador + " ]";
    }
    
}
