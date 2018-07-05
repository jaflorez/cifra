/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.entity;

import java.io.Serializable;
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
@Table(name = "reporte_materiales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReporteMateriales.findAll", query = "SELECT r FROM ReporteMateriales r")
    , @NamedQuery(name = "ReporteMateriales.findByIdentificador", query = "SELECT r FROM ReporteMateriales r WHERE r.identificador = :identificador")
    , @NamedQuery(name = "ReporteMateriales.findByUbicacion", query = "SELECT r FROM ReporteMateriales r WHERE r.ubicacion = :ubicacion")
    , @NamedQuery(name = "ReporteMateriales.findByPermisoMinero", query = "SELECT r FROM ReporteMateriales r WHERE r.permisoMinero = :permisoMinero")
    , @NamedQuery(name = "ReporteMateriales.findByLicenciaAmbiental", query = "SELECT r FROM ReporteMateriales r WHERE r.licenciaAmbiental = :licenciaAmbiental")})
public class ReporteMateriales implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDENTIFICADOR")
    private Integer identificador;
    @Column(name = "UBICACION")
    private String ubicacion;
    @Column(name = "PERMISO_MINERO")
    private Boolean permisoMinero;
    @Column(name = "LICENCIA_AMBIENTAL")
    private Boolean licenciaAmbiental;
    @JoinColumn(name = "FK_REPORTE", referencedColumnName = "IDENTIFICADOR")
    @ManyToOne
    private Reporte fkReporte;

    public ReporteMateriales() {
    }

    public ReporteMateriales(Integer identificador) {
        this.identificador = identificador;
    }

    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Boolean getPermisoMinero() {
        return permisoMinero;
    }

    public void setPermisoMinero(Boolean permisoMinero) {
        this.permisoMinero = permisoMinero;
    }

    public Boolean getLicenciaAmbiental() {
        return licenciaAmbiental;
    }

    public void setLicenciaAmbiental(Boolean licenciaAmbiental) {
        this.licenciaAmbiental = licenciaAmbiental;
    }

    public Reporte getFkReporte() {
        return fkReporte;
    }

    public void setFkReporte(Reporte fkReporte) {
        this.fkReporte = fkReporte;
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
        if (!(object instanceof ReporteMateriales)) {
            return false;
        }
        ReporteMateriales other = (ReporteMateriales) object;
        if ((this.identificador == null && other.identificador != null) || (this.identificador != null && !this.identificador.equals(other.identificador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "invias.cifra.entity.ReporteMateriales[ identificador=" + identificador + " ]";
    }
    
}
