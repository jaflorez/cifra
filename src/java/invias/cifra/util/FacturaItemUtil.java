/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 *
 * @author jaflorez
 */
public class FacturaItemUtil implements Serializable{
    private static final long serialVersionUID = 1L;
    private BigDecimal valor;
    private int numeroSemana;
    private int numeroReporte;
    private Date fecha;

    public FacturaItemUtil(int numeroSemana, int numeroReporte, Date fecha, BigDecimal valor) {
        this.numeroSemana = numeroSemana;
        this.numeroReporte = numeroReporte;
        this.fecha = fecha;
        this.valor = valor;
    }
    
    
    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getNumeroSemana() {
        return numeroSemana;
    }

    public void setNumeroSemana(int numeroSemana) {
        this.numeroSemana = numeroSemana;
    }

    public int getNumeroReporte() {
        return numeroReporte;
    }

    public void setNumeroReporte(int numeroReporte) {
        this.numeroReporte = numeroReporte;
    }
    
    
}
