/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.util;

import java.io.Serializable;

/**
 *
 * @author jaflorez
 */
public class SeleccionItem implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String descripcion;

    public SeleccionItem(Integer id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
