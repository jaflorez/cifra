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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jaflorez
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findByIdentificador", query = "SELECT u FROM Usuario u WHERE u.identificador = :identificador")
    , @NamedQuery(name = "Usuario.findByDocumento", query = "SELECT u FROM Usuario u WHERE u.documento = :documento")
    , @NamedQuery(name = "Usuario.findByContrasena", query = "SELECT u FROM Usuario u WHERE u.contrasena = :contrasena")
    , @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre")
    , @NamedQuery(name = "Usuario.findByApellido", query = "SELECT u FROM Usuario u WHERE u.apellido = :apellido")
    , @NamedQuery(name = "Usuario.findByEstado", query = "SELECT u FROM Usuario u WHERE u.estado = :estado")
    , @NamedQuery(name = "Usuario.findByCadenaverificacion", query = "SELECT u FROM Usuario u WHERE u.cadenaverificacion = :cadenaverificacion")
    , @NamedQuery(name = "Usuario.findByFechaCadena", query = "SELECT u FROM Usuario u WHERE u.fechaCadena = :fechaCadena")
    , @NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.email = :email")
    , @NamedQuery(name = "Usuario.findByTipo", query = "SELECT u FROM Usuario u WHERE u.tipo = :tipo")
    , @NamedQuery(name = "Usuario.findByFkUnidadEjecutora", query = "SELECT u FROM Usuario u WHERE u.fkUnidadEjecutora = :fkUnidadEjecutora")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDENTIFICADOR")
    private Integer identificador;
    @Basic(optional = false)
    @Column(name = "DOCUMENTO")
    private String documento;
    @Column(name = "CONTRASENA")
    private String contrasena;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "APELLIDO")
    private String apellido;
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "CADENAVERIFICACION")
    private String cadenaverificacion;
    @Column(name = "FECHA_CADENA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCadena;
    @Basic(optional = false)
    @Column(name = "EMAIL")
    private String email;
    @Basic(optional = false)
    @Column(name = "TIPO")
    private int tipo;
    @Column(name = "FK_UNIDAD_EJECUTORA")
    private Integer fkUnidadEjecutora;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkUsuario",orphanRemoval = true)
    private List<UsuarioContrato> usuarioContratoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkUsuario")
    private List<ReporteObservacion> reporteObservacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkUsuarioGestor")
    private List<Contrato> contratoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkUsuarioInterventor")
    private List<Contrato> contratoList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkUsuarioSupervisor")
    private List<Contrato> contratoList2;

    public Usuario() {
    }

    public Usuario(Integer identificador) {
        this.identificador = identificador;
    }

    public Usuario(Integer identificador, String documento, String nombre, String apellido, String email, int tipo) {
        this.identificador = identificador;
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.tipo = tipo;
    }

    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCadenaverificacion() {
        return cadenaverificacion;
    }

    public void setCadenaverificacion(String cadenaverificacion) {
        this.cadenaverificacion = cadenaverificacion;
    }

    public Date getFechaCadena() {
        return fechaCadena;
    }

    public void setFechaCadena(Date fechaCadena) {
        this.fechaCadena = fechaCadena;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Integer getFkUnidadEjecutora() {
        return fkUnidadEjecutora;
    }

    public void setFkUnidadEjecutora(Integer fkUnidadEjecutora) {
        this.fkUnidadEjecutora = fkUnidadEjecutora;
    }

    @XmlTransient
    public List<UsuarioContrato> getUsuarioContratoList() {
        return usuarioContratoList;
    }

    public void setUsuarioContratoList(List<UsuarioContrato> usuarioContratoList) {
        this.usuarioContratoList = usuarioContratoList;
    }

    @XmlTransient
    public List<ReporteObservacion> getReporteObservacionList() {
        return reporteObservacionList;
    }

    public void setReporteObservacionList(List<ReporteObservacion> reporteObservacionList) {
        this.reporteObservacionList = reporteObservacionList;
    }

    @XmlTransient
    public List<Contrato> getContratoList() {
        return contratoList;
    }

    public void setContratoList(List<Contrato> contratoList) {
        this.contratoList = contratoList;
    }

    @XmlTransient
    public List<Contrato> getContratoList1() {
        return contratoList1;
    }

    public void setContratoList1(List<Contrato> contratoList1) {
        this.contratoList1 = contratoList1;
    }

    @XmlTransient
    public List<Contrato> getContratoList2() {
        return contratoList2;
    }

    public void setContratoList2(List<Contrato> contratoList2) {
        this.contratoList2 = contratoList2;
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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.identificador == null && other.identificador != null) || (this.identificador != null && !this.identificador.equals(other.identificador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "invias.cifra.entity.Usuario[ identificador=" + identificador + " ]";
    }
    
}
