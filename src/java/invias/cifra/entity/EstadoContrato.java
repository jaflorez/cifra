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
@Table(name = "estado_contrato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoContrato.findAll", query = "SELECT e FROM EstadoContrato e")
    , @NamedQuery(name = "EstadoContrato.findByIdentificador", query = "SELECT e FROM EstadoContrato e WHERE e.identificador = :identificador")
    , @NamedQuery(name = "EstadoContrato.findByEstado", query = "SELECT e FROM EstadoContrato e WHERE e.estado = :estado")
    , @NamedQuery(name = "EstadoContrato.findByGeneraReporte", query = "SELECT e FROM EstadoContrato e WHERE e.generaReporte = :generaReporte")})
public class EstadoContrato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDENTIFICADOR")
    private Integer identificador;
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private String estado;
    @Basic(optional = false)
    @Column(name = "GENERA_REPORTE")
    private int generaReporte;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkEstadoContrato")
    private List<Contrato> contratoList;

    public EstadoContrato() {
    }

    public EstadoContrato(Integer identificador) {
        this.identificador = identificador;
    }

    public EstadoContrato(Integer identificador, String estado, int generaReporte) {
        this.identificador = identificador;
        this.estado = estado;
        this.generaReporte = generaReporte;
    }

    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getGeneraReporte() {
        return generaReporte;
    }

    public void setGeneraReporte(int generaReporte) {
        this.generaReporte = generaReporte;
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
        if (!(object instanceof EstadoContrato)) {
            return false;
        }
        EstadoContrato other = (EstadoContrato) object;
        if ((this.identificador == null && other.identificador != null) || (this.identificador != null && !this.identificador.equals(other.identificador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.EstadoContrato[ identificador=" + identificador + " ]";
    }
    
}
