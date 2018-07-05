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
@Table(name = "actividad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Actividad.findAll", query = "SELECT a FROM Actividad a")
    , @NamedQuery(name = "Actividad.findByIdentificador", query = "SELECT a FROM Actividad a WHERE a.identificador = :identificador")
    , @NamedQuery(name = "Actividad.findByActividad", query = "SELECT a FROM Actividad a WHERE a.actividad = :actividad")
    , @NamedQuery(name = "Actividad.findByUnidades", query = "SELECT a FROM Actividad a WHERE a.unidades = :unidades")})
public class Actividad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDENTIFICADOR")
    private Integer identificador;
    @Column(name = "ACTIVIDAD")
    private String actividad;
    @Column(name = "UNIDADES")
    private String unidades;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkActividad")
    private List<ContratoActividad> contratoActividadList;

    public Actividad() {
    }

    public Actividad(Integer identificador) {
        this.identificador = identificador;
    }

    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getUnidades() {
        return unidades;
    }

    public void setUnidades(String unidades) {
        this.unidades = unidades;
    }

    @XmlTransient
    public List<ContratoActividad> getContratoActividadList() {
        return contratoActividadList;
    }

    public void setContratoActividadList(List<ContratoActividad> contratoActividadList) {
        this.contratoActividadList = contratoActividadList;
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
        if (!(object instanceof Actividad)) {
            return false;
        }
        Actividad other = (Actividad) object;
        if ((this.identificador == null && other.identificador != null) || (this.identificador != null && !this.identificador.equals(other.identificador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "invias.cifra.entity.Actividad[ identificador=" + identificador + " ]";
    }
    
}
