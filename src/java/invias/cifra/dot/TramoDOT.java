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
public class TramoDOT implements Serializable {

    private static final long serialVersionUID = 1L;
    Integer numero;
    Integer identificador;
    Integer indice;

    public TramoDOT(Integer indice, Integer identificador, Integer numero) {
        this.numero = numero;
        this.identificador = identificador;
        this.indice = indice;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getIndice() {
        return indice;
    }

    public void setIndice(Integer indice) {
        this.indice = indice;
    }

    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }

}
