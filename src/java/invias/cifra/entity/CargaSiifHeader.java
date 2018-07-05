/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jaflorez
 */
@Entity
@Table(name = "carga_siif_header")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CargaSiifHeader.findAll", query = "SELECT c FROM CargaSiifHeader c")
    , @NamedQuery(name = "CargaSiifHeader.findByIdentificador", query = "SELECT c FROM CargaSiifHeader c WHERE c.identificador = :identificador")
    , @NamedQuery(name = "CargaSiifHeader.findBySiifDesde", query = "SELECT c FROM CargaSiifHeader c WHERE c.siifDesde = :siifDesde")
    , @NamedQuery(name = "CargaSiifHeader.findBySiifHasta", query = "SELECT c FROM CargaSiifHeader c WHERE c.siifHasta = :siifHasta")
    , @NamedQuery(name = "CargaSiifHeader.findByFechaProceso", query = "SELECT c FROM CargaSiifHeader c WHERE c.fechaProceso = :fechaProceso")
    , @NamedQuery(name = "CargaSiifHeader.findByFkUsuario", query = "SELECT c FROM CargaSiifHeader c WHERE c.fkUsuario = :fkUsuario")})
public class CargaSiifHeader implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDENTIFICADOR")
    private Integer identificador;
    @Column(name = "SIIF_DESDE")
    @Temporal(TemporalType.DATE)
    private Date siifDesde;
    @Column(name = "SIIF_HASTA")
    @Temporal(TemporalType.DATE)
    private Date siifHasta;
    @Column(name = "FECHA_PROCESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaProceso;
    @Column(name = "FK_USUARIO")
    private Integer fkUsuario;
    @OneToMany(mappedBy = "fkSiifHeader")
    private List<CargaSiifDetail> cargaSiifDetailList;

    public CargaSiifHeader() {
    }

    public CargaSiifHeader(Integer identificador) {
        this.identificador = identificador;
    }

    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }

    public Date getSiifDesde() {
        return siifDesde;
    }

    public void setSiifDesde(Date siifDesde) {
        this.siifDesde = siifDesde;
    }

    public Date getSiifHasta() {
        return siifHasta;
    }

    public void setSiifHasta(Date siifHasta) {
        this.siifHasta = siifHasta;
    }

    public Date getFechaProceso() {
        return fechaProceso;
    }

    public void setFechaProceso(Date fechaProceso) {
        this.fechaProceso = fechaProceso;
    }

    public Integer getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Integer fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    @XmlTransient
    public List<CargaSiifDetail> getCargaSiifDetailList() {
        return cargaSiifDetailList;
    }

    public void setCargaSiifDetailList(List<CargaSiifDetail> cargaSiifDetailList) {
        this.cargaSiifDetailList = cargaSiifDetailList;
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
        if (!(object instanceof CargaSiifHeader)) {
            return false;
        }
        CargaSiifHeader other = (CargaSiifHeader) object;
        if ((this.identificador == null && other.identificador != null) || (this.identificador != null && !this.identificador.equals(other.identificador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "invias.cifra.entity.CargaSiifHeader[ identificador=" + identificador + " ]";
    }
    
}
