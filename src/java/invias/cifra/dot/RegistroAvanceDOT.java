/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.dot;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author jaflorez
 */
public class RegistroAvanceDOT implements Serializable {
    private static final long serialVersionUID = 1L;
    String titulo;
    private BigDecimal programado;
    private BigDecimal acumulado;
    private BigDecimal[] valor = new BigDecimal[30];
    private Integer idRegisto;

    public RegistroAvanceDOT() {
        
    }
    

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public BigDecimal getAcumulado() {
        return acumulado;
    }

    public void setAcumulado(BigDecimal acumulado) {
        this.acumulado = acumulado;
    }


    public BigDecimal[] getValor() {
        return valor;
    }

    public void setValor(BigDecimal[] valor) {
        this.valor = valor;
    }

    public BigDecimal getProgramado() {
        return programado;
    }

    public void setProgramado(BigDecimal programado) {
        this.programado = programado;
    }

    public Integer getIdRegisto() {
        return idRegisto;
    }
    public void setIdRegisto(Integer idRegisto) {
        this.idRegisto = idRegisto;
    }


}
