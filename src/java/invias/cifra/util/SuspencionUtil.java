/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.util;

import invias.cifra.entity.ContratoAjusteObra;
import invias.cifra.entity.EstadoContrato;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.lang3.time.DateUtils;

/**
 *
 * @author jaflorez
 */
public class SuspencionUtil implements Serializable  {
     private static final long serialVersionUID = 1L;

    private ArrayList<ContratoAjusteObra> suspensiones;

    public SuspencionUtil() {
        this.suspensiones = new ArrayList<ContratoAjusteObra>();
    }

    public void addAjuste(ContratoAjusteObra ajusteObra) {
        this.suspensiones.add(ajusteObra);
    }

    public EstadoContrato getEstadoContrato(Date fecha) {
        Date fechaDesde=null;
        Date fechaHasta=null;
        ContratoAjusteObra curCao = null;

        EstadoContrato estadoContrato = new EstadoContrato();
        estadoContrato.setIdentificador(1);
        estadoContrato.setEstado("En Ejecucion");
        /*Se hacer un for para determinar la existencia de la suspension*/
        for (ContratoAjusteObra cao : this.suspensiones) {
            if (cao.getFkAjuste().getIdentificador() == 3 || cao.getFkAjuste().getIdentificador() == 5) {
                /*Se evaluan las suspensiones y las amapliaciones de supension*/
                fechaDesde = DateUtils.truncate(cao.getFechaInicio(), Calendar.DATE);
                fechaHasta = DateUtils.truncate(cao.getFechaFin(), Calendar.DATE);
                /*Suspencion y Ampliacion de suspencion*/
                if (fecha.compareTo(fechaDesde) >= 0 && fecha.compareTo(fechaHasta) <= 0) {
                    estadoContrato.setIdentificador(2);
                    estadoContrato.setEstado("Suspendido");
                    curCao = cao;
                    /*Se guarda la actual suspension*/
                }
            }
        }
        /*Se evaluan las reanudaciones*/
        if (curCao != null) {
            Date fechaReanudacion;
            for (ContratoAjusteObra cao : this.suspensiones) {
                if (cao.getFkAjuste().getIdentificador() == 4) {
                    fechaReanudacion = DateUtils.truncate(cao.getFechaInicio(), Calendar.DATE);
                    /*Reanudacion*/
                    if (fechaReanudacion.compareTo(fechaDesde) >= 0 && fechaReanudacion.compareTo(fechaHasta) <= 0) {
                        estadoContrato.setIdentificador(1);
                        estadoContrato.setEstado("En Ejecucion");
                        curCao = cao;
                    }
                }
            }
        }
        return estadoContrato;
    }

}
