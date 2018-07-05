/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.dot;

import java.io.Serializable;

/**
 *
 * @author jaflorez
 */
public class FechaReporteDOT implements Serializable {
    private static final long serialVersionUID = 1L;
    String fechaInicio;
    String fechaFinal;
    int identificador;

    public FechaReporteDOT(String fechaInicio, String fechaFinal, int identificador) {
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.identificador = identificador;
    }


    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }
    
    
}
