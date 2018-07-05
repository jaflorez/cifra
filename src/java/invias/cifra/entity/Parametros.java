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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jaflorez
 */
@Entity
@Table(name = "parametros")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parametros.findAll", query = "SELECT p FROM Parametros p")
    , @NamedQuery(name = "Parametros.findByDiaInicioSemana", query = "SELECT p FROM Parametros p WHERE p.diaInicioSemana = :diaInicioSemana")
    , @NamedQuery(name = "Parametros.findByDiaReporteSemana", query = "SELECT p FROM Parametros p WHERE p.diaReporteSemana = :diaReporteSemana")
    , @NamedQuery(name = "Parametros.findByEmailSend", query = "SELECT p FROM Parametros p WHERE p.emailSend = :emailSend")
    , @NamedQuery(name = "Parametros.findByIdentificador", query = "SELECT p FROM Parametros p WHERE p.identificador = :identificador")})
public class Parametros implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "dia_inicio_semana")
    private int diaInicioSemana;
    @Column(name = "dia_reporte_semana")
    private Integer diaReporteSemana;
    @Column(name = "email_send")
    private String emailSend;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "identificador")
    private Integer identificador;

    public Parametros() {
    }

    public Parametros(Integer identificador) {
        this.identificador = identificador;
    }

    public Parametros(Integer identificador, int diaInicioSemana) {
        this.identificador = identificador;
        this.diaInicioSemana = diaInicioSemana;
    }

    public int getDiaInicioSemana() {
        return diaInicioSemana;
    }

    public void setDiaInicioSemana(int diaInicioSemana) {
        this.diaInicioSemana = diaInicioSemana;
    }

    public Integer getDiaReporteSemana() {
        return diaReporteSemana;
    }

    public void setDiaReporteSemana(Integer diaReporteSemana) {
        this.diaReporteSemana = diaReporteSemana;
    }

    public String getEmailSend() {
        return emailSend;
    }

    public void setEmailSend(String emailSend) {
        this.emailSend = emailSend;
    }

    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
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
        if (!(object instanceof Parametros)) {
            return false;
        }
        Parametros other = (Parametros) object;
        if ((this.identificador == null && other.identificador != null) || (this.identificador != null && !this.identificador.equals(other.identificador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "invias.cifra.entity.Parametros[ identificador=" + identificador + " ]";
    }
    
}
