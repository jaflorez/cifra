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
@Table(name = "tercero")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tercero.findAll", query = "SELECT t FROM Tercero t")
    , @NamedQuery(name = "Tercero.findByIdentificador", query = "SELECT t FROM Tercero t WHERE t.identificador = :identificador")
    , @NamedQuery(name = "Tercero.findByNit", query = "SELECT t FROM Tercero t WHERE t.nit = :nit")
    , @NamedQuery(name = "Tercero.findByRazonsocial", query = "SELECT t FROM Tercero t WHERE t.razonsocial = :razonsocial")
    , @NamedQuery(name = "Tercero.findByTelefonocontacto", query = "SELECT t FROM Tercero t WHERE t.telefonocontacto = :telefonocontacto")})
public class Tercero implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDENTIFICADOR")
    private Integer identificador;
    @Column(name = "NIT")
    private String nit;
    @Column(name = "RAZONSOCIAL")
    private String razonsocial;
    @Column(name = "TELEFONOCONTACTO")
    private String telefonocontacto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkTerceroInter")
    private List<Contrato> contratoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkTerceroObra")
    private List<Contrato> contratoList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkTercero")
    private List<ContratoTerceroInter> contratoTerceroInterList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkTercero")
    private List<ContratoTerceroObra> contratoTerceroObraList;

    public Tercero() {
    }

    public Tercero(Integer identificador) {
        this.identificador = identificador;
    }

    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getRazonsocial() {
        return razonsocial;
    }

    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }

    public String getTelefonocontacto() {
        return telefonocontacto;
    }

    public void setTelefonocontacto(String telefonocontacto) {
        this.telefonocontacto = telefonocontacto;
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
    public List<ContratoTerceroInter> getContratoTerceroInterList() {
        return contratoTerceroInterList;
    }

    public void setContratoTerceroInterList(List<ContratoTerceroInter> contratoTerceroInterList) {
        this.contratoTerceroInterList = contratoTerceroInterList;
    }

    @XmlTransient
    public List<ContratoTerceroObra> getContratoTerceroObraList() {
        return contratoTerceroObraList;
    }

    public void setContratoTerceroObraList(List<ContratoTerceroObra> contratoTerceroObraList) {
        this.contratoTerceroObraList = contratoTerceroObraList;
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
        if (!(object instanceof Tercero)) {
            return false;
        }
        Tercero other = (Tercero) object;
        if ((this.identificador == null && other.identificador != null) || (this.identificador != null && !this.identificador.equals(other.identificador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "invias.cifra.entity.Tercero[ identificador=" + identificador + " ]";
    }
    
}
