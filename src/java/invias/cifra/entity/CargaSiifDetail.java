/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "carga_siif_detail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CargaSiifDetail.findAll", query = "SELECT c FROM CargaSiifDetail c")
    , @NamedQuery(name = "CargaSiifDetail.findByIdentificador", query = "SELECT c FROM CargaSiifDetail c WHERE c.identificador = :identificador")
    , @NamedQuery(name = "CargaSiifDetail.findByValorActual", query = "SELECT c FROM CargaSiifDetail c WHERE c.valorActual = :valorActual")
    , @NamedQuery(name = "CargaSiifDetail.findBySaldoPorUtilizar", query = "SELECT c FROM CargaSiifDetail c WHERE c.saldoPorUtilizar = :saldoPorUtilizar")
    , @NamedQuery(name = "CargaSiifDetail.findByObligado", query = "SELECT c FROM CargaSiifDetail c WHERE c.obligado = :obligado")
    , @NamedQuery(name = "CargaSiifDetail.findByNumeroDocumentoSoporte", query = "SELECT c FROM CargaSiifDetail c WHERE c.numeroDocumentoSoporte = :numeroDocumentoSoporte")
    , @NamedQuery(name = "CargaSiifDetail.findByContratoObraId", query = "SELECT c FROM CargaSiifDetail c WHERE c.contratoObraId = :contratoObraId")
    , @NamedQuery(name = "CargaSiifDetail.findByContratoInterId", query = "SELECT c FROM CargaSiifDetail c WHERE c.contratoInterId = :contratoInterId")})
public class CargaSiifDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDENTIFICADOR")
    private Integer identificador;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALOR_ACTUAL")
    private BigDecimal valorActual;
    @Column(name = "SALDO_POR_UTILIZAR")
    private BigDecimal saldoPorUtilizar;
    @Column(name = "OBLIGADO")
    private BigDecimal obligado;
    @Column(name = "NUMERO_DOCUMENTO_SOPORTE")
    private String numeroDocumentoSoporte;
    @Column(name = "CONTRATO_OBRA_ID")
    private Integer contratoObraId;
    @Column(name = "CONTRATO_INTER_ID")
    private Integer contratoInterId;
    @JoinColumn(name = "FK_SIIF_HEADER", referencedColumnName = "IDENTIFICADOR")
    @ManyToOne
    private CargaSiifHeader fkSiifHeader;

    public CargaSiifDetail() {
    }

    public CargaSiifDetail(Integer identificador) {
        this.identificador = identificador;
    }

    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }

    public BigDecimal getValorActual() {
        return valorActual;
    }

    public void setValorActual(BigDecimal valorActual) {
        this.valorActual = valorActual;
    }

    public BigDecimal getSaldoPorUtilizar() {
        return saldoPorUtilizar;
    }

    public void setSaldoPorUtilizar(BigDecimal saldoPorUtilizar) {
        this.saldoPorUtilizar = saldoPorUtilizar;
    }

    public BigDecimal getObligado() {
        return obligado;
    }

    public void setObligado(BigDecimal obligado) {
        this.obligado = obligado;
    }

    public String getNumeroDocumentoSoporte() {
        return numeroDocumentoSoporte;
    }

    public void setNumeroDocumentoSoporte(String numeroDocumentoSoporte) {
        this.numeroDocumentoSoporte = numeroDocumentoSoporte;
    }

    public Integer getContratoObraId() {
        return contratoObraId;
    }

    public void setContratoObraId(Integer contratoObraId) {
        this.contratoObraId = contratoObraId;
    }

    public Integer getContratoInterId() {
        return contratoInterId;
    }

    public void setContratoInterId(Integer contratoInterId) {
        this.contratoInterId = contratoInterId;
    }

    public CargaSiifHeader getFkSiifHeader() {
        return fkSiifHeader;
    }

    public void setFkSiifHeader(CargaSiifHeader fkSiifHeader) {
        this.fkSiifHeader = fkSiifHeader;
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
        if (!(object instanceof CargaSiifDetail)) {
            return false;
        }
        CargaSiifDetail other = (CargaSiifDetail) object;
        if ((this.identificador == null && other.identificador != null) || (this.identificador != null && !this.identificador.equals(other.identificador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "invias.cifra.entity.CargaSiifDetail[ identificador=" + identificador + " ]";
    }
    
}
