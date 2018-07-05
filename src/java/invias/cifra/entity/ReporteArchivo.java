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
import javax.persistence.Lob;
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
@Table(name = "reporte_archivo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReporteArchivo.findAll", query = "SELECT r FROM ReporteArchivo r")
    , @NamedQuery(name = "ReporteArchivo.findByIdentificador", query = "SELECT r FROM ReporteArchivo r WHERE r.identificador = :identificador")
    , @NamedQuery(name = "ReporteArchivo.findByTipoArchivo", query = "SELECT r FROM ReporteArchivo r WHERE r.tipoArchivo = :tipoArchivo")
    , @NamedQuery(name = "ReporteArchivo.findByNombre", query = "SELECT r FROM ReporteArchivo r WHERE r.nombre = :nombre")
    , @NamedQuery(name = "ReporteArchivo.findByExtension", query = "SELECT r FROM ReporteArchivo r WHERE r.extension = :extension")
    , @NamedQuery(name = "ReporteArchivo.findBySize", query = "SELECT r FROM ReporteArchivo r WHERE r.size = :size")})
public class ReporteArchivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDENTIFICADOR")
    private Integer identificador;
    @Column(name = "TIPO_ARCHIVO")
    private Integer tipoArchivo;
    @Lob
    @Column(name = "ARCHIVO")
    private byte[] archivo;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "EXTENSION")
    private String extension;
    @Column(name = "SIZE")
    private Integer size;
    @JoinColumn(name = "FK_REPORTE", referencedColumnName = "IDENTIFICADOR")
    @ManyToOne
    private Reporte fkReporte;

    public ReporteArchivo() {
    }

    public ReporteArchivo(Integer identificador) {
        this.identificador = identificador;
    }

    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }

    public Integer getTipoArchivo() {
        return tipoArchivo;
    }

    public void setTipoArchivo(Integer tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }

    public byte[] getArchivo() {
        return archivo;
    }

    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
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
        if (!(object instanceof ReporteArchivo)) {
            return false;
        }
        ReporteArchivo other = (ReporteArchivo) object;
        if ((this.identificador == null && other.identificador != null) || (this.identificador != null && !this.identificador.equals(other.identificador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "invias.cifra.entity.ReporteArchivo[ identificador=" + identificador + " ]";
    }
    
}
