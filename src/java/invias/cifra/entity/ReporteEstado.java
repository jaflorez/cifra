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
@Table(name = "reporte_estado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReporteEstado.findAll", query = "SELECT r FROM ReporteEstado r")
    , @NamedQuery(name = "ReporteEstado.findByIdentificador", query = "SELECT r FROM ReporteEstado r WHERE r.identificador = :identificador")
    , @NamedQuery(name = "ReporteEstado.findByFkReporte", query = "SELECT r FROM ReporteEstado r WHERE r.fkReporte = :fkReporte")
    , @NamedQuery(name = "ReporteEstado.findByFkUsuario", query = "SELECT r FROM ReporteEstado r WHERE r.fkUsuario = :fkUsuario")
    , @NamedQuery(name = "ReporteEstado.findByFecharegistro", query = "SELECT r FROM ReporteEstado r WHERE r.fecharegistro = :fecharegistro")
    , @NamedQuery(name = "ReporteEstado.findByFkEstado", query = "SELECT r FROM ReporteEstado r WHERE r.fkEstado = :fkEstado")
    , @NamedQuery(name = "ReporteEstado.findByObservacion", query = "SELECT r FROM ReporteEstado r WHERE r.observacion = :observacion")})
public class ReporteEstado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDENTIFICADOR")
    private Integer identificador;
    @Basic(optional = false)
    @Column(name = "FK_REPORTE")
    private int fkReporte;
    @Basic(optional = false)
    @Column(name = "FK_USUARIO")
    private int fkUsuario;
    @Basic(optional = false)
    @Column(name = "FECHAREGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecharegistro;
    @Column(name = "FK_ESTADO")
    private Integer fkEstado;
    @Column(name = "OBSERVACION")
    private String observacion;

    public ReporteEstado() {
    }

    public ReporteEstado(Integer identificador) {
        this.identificador = identificador;
    }

    public ReporteEstado(Integer identificador, int fkReporte, int fkUsuario, Date fecharegistro) {
        this.identificador = identificador;
        this.fkReporte = fkReporte;
        this.fkUsuario = fkUsuario;
        this.fecharegistro = fecharegistro;
    }

    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }

    public int getFkReporte() {
        return fkReporte;
    }

    public void setFkReporte(int fkReporte) {
        this.fkReporte = fkReporte;
    }

    public int getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(int fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    public Date getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro(Date fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

    public Integer getFkEstado() {
        return fkEstado;
    }

    public void setFkEstado(Integer fkEstado) {
        this.fkEstado = fkEstado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
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
        if (!(object instanceof ReporteEstado)) {
            return false;
        }
        ReporteEstado other = (ReporteEstado) object;
        if ((this.identificador == null && other.identificador != null) || (this.identificador != null && !this.identificador.equals(other.identificador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "invias.cifra.entity.ReporteEstado[ identificador=" + identificador + " ]";
    }
    
}
