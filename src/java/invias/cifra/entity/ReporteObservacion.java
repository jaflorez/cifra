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
@Table(name = "reporte_observacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReporteObservacion.findAll", query = "SELECT r FROM ReporteObservacion r")
    , @NamedQuery(name = "ReporteObservacion.findByIdentificador", query = "SELECT r FROM ReporteObservacion r WHERE r.identificador = :identificador")
    , @NamedQuery(name = "ReporteObservacion.findByFecharegistro", query = "SELECT r FROM ReporteObservacion r WHERE r.fecharegistro = :fecharegistro")
    , @NamedQuery(name = "ReporteObservacion.findByObservacion", query = "SELECT r FROM ReporteObservacion r WHERE r.observacion = :observacion")})
public class ReporteObservacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDENTIFICADOR")
    private Integer identificador;
    @Basic(optional = false)
    @Column(name = "FECHAREGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecharegistro;
    @Column(name = "OBSERVACION")
    private String observacion;
    @JoinColumn(name = "FK_REPORTE", referencedColumnName = "IDENTIFICADOR")
    @ManyToOne(optional = false)
    private Reporte fkReporte;
    @JoinColumn(name = "FK_USUARIO", referencedColumnName = "IDENTIFICADOR")
    @ManyToOne(optional = false)
    private Usuario fkUsuario;

    public ReporteObservacion() {
    }

    public ReporteObservacion(Integer identificador) {
        this.identificador = identificador;
    }

    public ReporteObservacion(Integer identificador, Date fecharegistro) {
        this.identificador = identificador;
        this.fecharegistro = fecharegistro;
    }

    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }

    public Date getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro(Date fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Reporte getFkReporte() {
        return fkReporte;
    }

    public void setFkReporte(Reporte fkReporte) {
        this.fkReporte = fkReporte;
    }

    public Usuario getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Usuario fkUsuario) {
        this.fkUsuario = fkUsuario;
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
        if (!(object instanceof ReporteObservacion)) {
            return false;
        }
        ReporteObservacion other = (ReporteObservacion) object;
        if ((this.identificador == null && other.identificador != null) || (this.identificador != null && !this.identificador.equals(other.identificador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "invias.cifra.entity.ReporteObservacion[ identificador=" + identificador + " ]";
    }
    
}
