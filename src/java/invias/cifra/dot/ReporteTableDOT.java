/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.dot;

import invias.cifra.entity.ContratoActividad;
import invias.cifra.entity.ContratoMaquinaria;
import invias.cifra.entity.ContratoTramo;
import invias.cifra.entity.Reporte;
import invias.cifra.entity.ReporteActividad;
import invias.cifra.entity.ReporteMaquinaria;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jaflorez
 */
public class ReporteTableDOT implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<TramoDOT> tramoLst;
    private List<RegistroAvanceDOT> registroActividadLst;
    private List<RegistroAvanceDOT> registroMaquinariaLst;
    Reporte reporte;

    public ReporteTableDOT(Reporte reporte) {
        this.reporte = reporte;
    }

    public void makeRegistroTables(boolean calcularTotales) {
        this.makeTramoLst();
        this.makeRegistroTableActividad(calcularTotales);
        this.makeRegistroTableMaquinaria(calcularTotales);

    }

    public String getTituloTramo(int indice,String prefijo) {
        for (TramoDOT tdot : this.tramoLst) {
            if (tdot.indice.equals(indice - 1)) {
                return prefijo + tdot.numero;
            }
        }
        return "??:" + indice;
    }

    public boolean dibujarColumna(int indice) {
        return indice <= this.tramoLst.size();
    }

    public int getIndiceTramo(Integer identificador) {
        for (TramoDOT tdot : this.tramoLst) {
            if (tdot.identificador.equals(identificador)) {
                return tdot.indice;
            }
        }
        return -1;
    }

    public int getIndentificadorTramo(Integer indice) {
        for (TramoDOT tdot : this.tramoLst) {
            if (tdot.indice.equals(indice)) {
                return tdot.identificador;
            }
        }
        return -1;
    }

    private void makeTramoLst() {
        int contador = 0;
        this.tramoLst = new ArrayList<>();
        for (ContratoTramo ct : reporte.getFkContrato().getContratoTramoList()) {
            TramoDOT tdot = new TramoDOT(contador, ct.getIdentificador(), ct.getNumero());
            this.tramoLst.add(tdot);
            contador++;
        }
    }

    private void inserReporteActividadIntoRegistroActividad(ReporteActividad ra) {
        for (RegistroAvanceDOT regAvance : this.registroActividadLst) {
            if (regAvance.getIdRegisto().equals(ra.getFkContratoactividad().getIdentificador())) {
                int indiceTramo = this.getIndiceTramo(ra.getFkContratotramo().getIdentificador());
                if (indiceTramo >= 0) {
                    regAvance.getValor()[indiceTramo] = ra.getCantidad();
                    return;
                }
            }
        }
    }

    private void makeRegistroTableActividad(boolean calcularTotales) {
        this.registroActividadLst = new ArrayList<>(); // Se crea el arreglo para capturar las actividades
        for (ContratoActividad ctra : reporte.getFkContrato().getContratoActividadList()) { // Se recorren todas las actividades
            RegistroAvanceDOT ra = new RegistroAvanceDOT(); // Se crea un registro para cada una de las actividades
            ra.setIdRegisto(ctra.getIdentificador()); // se asigna el registro de la actividad 
            ra.setTitulo(ctra.getFkActividad().getActividad());
            ra.setProgramado(ctra.getCantidad()); // Se asigna el valor programado para la actividad

            this.registroActividadLst.add(ra);
        }
        /*Se asignan los valores del contrato*/
        for (ReporteActividad rpa : this.reporte.getReporteActividadList()) {// Se cargan las actividades registradas en el reporte
            this.inserReporteActividadIntoRegistroActividad(rpa);
        }
        if (calcularTotales) {
            /*Se calculan los totales */
            double acumulado;
            double valor;
            for (RegistroAvanceDOT regAvance : this.registroActividadLst) {
                acumulado = 0;
                for (int i = 0; i <= this.tramoLst.size(); i++) {
                    if (regAvance.getValor()[i] != null) {
                        valor = regAvance.getValor()[i].doubleValue();
                        acumulado += valor;
                    }
                }
                regAvance.setAcumulado(BigDecimal.valueOf(acumulado));
            }
        }
    }

    private void inserReporteMaquinariaIntoRegistroMaquinaria(ReporteMaquinaria rm) {
        for (RegistroAvanceDOT regAvance : this.registroMaquinariaLst) {
            if (regAvance.getIdRegisto().equals(rm.getFkContratomaquinaria().getIdentificador())) {
                int indiceTramo = this.getIndiceTramo(rm.getFkContratotramo().getIdentificador());
                if (indiceTramo >= 0) {
                    regAvance.getValor()[indiceTramo] = rm.getCantidad();
                    return;
                }
            }
        }
    }
    private void makeRegistroTableMaquinaria(boolean calcularTotales) {
        this.registroMaquinariaLst = new ArrayList<>(); // Se crea el arreglo para capturar las actividades
        /*Se crea la matriz de datos*/
        for (ContratoMaquinaria cm : reporte.getFkContrato().getContratoMaquinariaList()) { // Se recorren todas las actividades
            RegistroAvanceDOT ra = new RegistroAvanceDOT(); // Se crea un registro para cada una de las actividades
            ra.setIdRegisto(cm.getIdentificador());
            ra.setTitulo(cm.getFkMaquinaria().getMaquinaria());
            ra.setProgramado(cm.getCantidad()); // Se asigna el valor programado para la actividad
            this.registroMaquinariaLst.add(ra);
        }
        /*Se asignan los valores del contrato*/
        this.reporte.getReporteMaquinariaList().stream().forEach((rpa) -> {
            // Se cargan las actividades registradas en el reporte
            this.inserReporteMaquinariaIntoRegistroMaquinaria(rpa);
        });
        if(calcularTotales){
            /*Se calculan los totales */
            double acumulado;
            double valor;
            for (RegistroAvanceDOT regAvance : this.registroMaquinariaLst) {
                acumulado = 0;
                for (int i = 0; i <= this.tramoLst.size(); i++) {
                    if (regAvance.getValor()[i] != null) {
                        valor = regAvance.getValor()[i].doubleValue();
                        acumulado += valor;
                    }
                }
                regAvance.setAcumulado(BigDecimal.valueOf(acumulado));
            }
        }

    }

    public List<ReporteActividad> loadReporteActividadLst() {
        ArrayList<ReporteActividad> reporteActividadLst = new ArrayList<>();
        ReporteActividad ra;
        if (this.registroActividadLst == null) {
            return reporteActividadLst;
        }
        for (RegistroAvanceDOT regAv : this.registroActividadLst) {
            for (int i = 0; i <= this.tramoLst.size(); i++) {
                if (regAv.getValor()[i] != null && regAv.getValor()[i].compareTo(BigDecimal.ZERO) > 0) {
                    ra = new ReporteActividad();
                    ra.setCantidad(regAv.getValor()[i]);
                    ra.setFkReporte(this.reporte);
                    ContratoTramo ct = new ContratoTramo();
                    ct.setFkContrato(this.reporte.getFkContrato());
                    ct.setIdentificador(this.getIndentificadorTramo(i));
                    ra.setFkContratotramo(ct);
                    ra.setFkContratoactividad(new ContratoActividad(regAv.getIdRegisto()));
                    reporteActividadLst.add(ra);
                }
            }
        }
        return reporteActividadLst;
    }

    public List<ReporteMaquinaria> loadReporteMaquinariaLst() {
        ArrayList<ReporteMaquinaria> reporteMaquinariaLst = new ArrayList<>();
        ReporteMaquinaria rm;
        if (this.registroMaquinariaLst == null) {
            return reporteMaquinariaLst;
        }
        for (RegistroAvanceDOT regAv : this.registroMaquinariaLst) {
            for (int i = 0; i <= this.tramoLst.size(); i++) {
                if (regAv.getValor()[i] != null && regAv.getValor()[i].compareTo(BigDecimal.ZERO) > 0) {
                    rm = new ReporteMaquinaria();
                    rm.setCantidad(regAv.getValor()[i]);
                    rm.setFkReporte(this.reporte);
                    ContratoTramo ct = new ContratoTramo();
                    ct.setFkContrato(this.reporte.getFkContrato());
                    ct.setIdentificador(this.getIndentificadorTramo(i));
                    rm.setFkContratotramo(ct);
                    rm.setFkContratomaquinaria(new ContratoMaquinaria(regAv.getIdRegisto()));
                    reporteMaquinariaLst.add(rm);
                }
            }
        }
        return reporteMaquinariaLst;
    }

    /*Getter / setter*/
    public List<RegistroAvanceDOT> getRegistroActividadLst() {
        return registroActividadLst;
    }

    public void setRegistroActividadLst(List<RegistroAvanceDOT> registroActividadLst) {
        this.registroActividadLst = registroActividadLst;
    }

    public List<RegistroAvanceDOT> getRegistroMaquinariaLst() {
        return registroMaquinariaLst;
    }

    public void setRegistroMaquinariaLst(List<RegistroAvanceDOT> registroMaquinariaLst) {
        this.registroMaquinariaLst = registroMaquinariaLst;
    }

}
