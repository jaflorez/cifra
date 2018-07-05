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
public class VigenciaDOT implements Serializable{
    private static final long serialVersionUID = 1L;
    Integer ano;
    BigDecimal vigencia;
    BigDecimal ejecutado;

    public Integer getAno() {
        return ano;
    }

    public void setAño(Integer ano) {
        this.ano = ano;
    }

    public BigDecimal getVigencia() {
        return vigencia;
    }

    public void setVigencia(BigDecimal vigencia) {
        this.vigencia = vigencia;
    }

    public BigDecimal getEjecutado() {
        return ejecutado;
    }

    public void setEjecutado(BigDecimal ejecutado) {
        this.ejecutado = ejecutado;
    }
    
    
}
