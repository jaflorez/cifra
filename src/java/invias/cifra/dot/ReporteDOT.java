/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.dot;

import invias.cifra.entity.Reporte;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author jaflorez
 */
public class ReporteDOT implements Serializable{

    private static final long serialVersionUID = 1L;
    private ReporteTableDOT tableDOT;
    Reporte reporte;
    BigDecimal obraTotalKmMejorar;  //Variable para las prorrogas que se registraron al contrato de obra 
    BigDecimal obraTotalKmRehabilitar;  //Variable para las prorrogas que se registraron al contrato de obra 
    BigDecimal obraTotalKmConstruir;  //Variable para las prorrogas que se registraron al contrato de obra 
    BigDecimal obraTotalPuentes;  //Variable para las prorrogas que se registraron al contrato de obra 
    BigDecimal obraSumaProrrogas;  //Variable para las prorrogas que se registraron al contrato de obra 
    BigDecimal obraSumaVigencias; //Variable para cargar todas las vigencias del contrato de obra -Contrato-Vigencia
    BigDecimal obraSumaAdiciones; //Carga todas las adiciones del contrato de obra
    BigDecimal obraProgramaInversionTotal; //Suma todos los valores del programa de inversion de obra -contrato-financiero-obra
    BigDecimal obraProgramaInversionAcumulado; //Suma todos los valores del programa de inversion a la fecha -contrato-financiero-obra
    BigDecimal obraFacturadoTotalAcumulado; // Suma todas las facturas acumuladas a la fecha -reporte-
    BigDecimal obraFacturadoInversionAcumulado; // Suma todas los valores de inversion facturas acumuladas a la fecha -reporte-
    BigDecimal interSumaProrrogas;    // Suma las prorrogas de la interventoria (tiempo) -contrato-prorroga-inter
    BigDecimal interSumaAdiciones;   // Suma las adiciones del contrato de interventoria ($) -contrato-addicion-inter 
    BigDecimal interInversionAcumulado; //Suma el programa de inversion a la fecha
    BigDecimal interFacturadoTotalAcumulado; //Suma todas las facturas de interventoria realizadas - reporte - 
    
    String  obraMesFacturado;
    
    private List<VigenciaDOT> vigenciaList;
    public ReporteDOT(Reporte reporte) {
        this.reporte = reporte;
        this.tableDOT = new ReporteTableDOT(reporte);
        this.tableDOT.makeRegistroTables(true);
    }
    public Reporte getReporte() {
        return reporte;
    }
    public void setReporte(Reporte reporte) {
        this.reporte = reporte;
    }

    public BigDecimal getObraTotalKmMejorar() {
        return obraTotalKmMejorar;
    }

    public void setObraTotalKmMejorar(BigDecimal obraTotalKmMejorar) {
        this.obraTotalKmMejorar = obraTotalKmMejorar;
    }

    public BigDecimal getObraTotalKmRehabilitar() {
        return obraTotalKmRehabilitar;
    }

    public void setObraTotalKmRehabilitar(BigDecimal obraTotalKmRehabilitar) {
        this.obraTotalKmRehabilitar = obraTotalKmRehabilitar;
    }

    public BigDecimal getObraTotalKmConstruir() {
        return obraTotalKmConstruir;
    }

    public void setObraTotalKmConstruir(BigDecimal obraTotalKmConstruir) {
        this.obraTotalKmConstruir = obraTotalKmConstruir;
    }

    public BigDecimal getObraTotalPuentes() {
        return obraTotalPuentes;
    }

    public void setObraTotalPuentes(BigDecimal obraTotalPuentes) {
        this.obraTotalPuentes = obraTotalPuentes;
    }
    
    
    

    public BigDecimal getObraSumaProrrogas() {
        return obraSumaProrrogas;
    }

    public void setObraSumaProrrogas(BigDecimal obraSumaProrrogas) {
        this.obraSumaProrrogas = obraSumaProrrogas;
    }

    public BigDecimal getObraSumaVigencias() {
        return obraSumaVigencias;
    }

    public void setObraSumaVigencias(BigDecimal obraSumaVigencias) {
        this.obraSumaVigencias = obraSumaVigencias;
    }

    public BigDecimal getObraSumaAdiciones() {
        return obraSumaAdiciones;
    }

    public void setObraSumaAdiciones(BigDecimal obraSumaAdiciones) {
        this.obraSumaAdiciones = obraSumaAdiciones;
    }

    public BigDecimal getInterSumaProrrogas() {
        return interSumaProrrogas;
    }

    public void setInterSumaProrrogas(BigDecimal interSumaProrrogas) {
        this.interSumaProrrogas = interSumaProrrogas;
    }

    public BigDecimal getInterSumaAdiciones() {
        return interSumaAdiciones;
    }

    public void setInterSumaAdiciones(BigDecimal interSumaAdiciones) {
        this.interSumaAdiciones = interSumaAdiciones;
    }

    public BigDecimal getObraProgramaInversionTotal() {
        return obraProgramaInversionTotal;
    }

    public void setObraProgramaInversionTotal(BigDecimal obraProgramaInversionTotal) {
        this.obraProgramaInversionTotal = obraProgramaInversionTotal;
    }

    public BigDecimal getObraProgramaInversionAcumulado() {
        return obraProgramaInversionAcumulado;
    }

    public void setObraProgramaInversionAcumulado(BigDecimal obraProgramaInversionAcumulado) {
        this.obraProgramaInversionAcumulado = obraProgramaInversionAcumulado;
    }

    public BigDecimal getInterInversionAcumulado() {
        return interInversionAcumulado;
    }

    public void setInterInversionAcumulado(BigDecimal interInversionAcumulado) {
        this.interInversionAcumulado = interInversionAcumulado;
    }

    public BigDecimal getObraFacturadoTotalAcumulado() {
        return obraFacturadoTotalAcumulado;
    }

    public void setObraFacturadoTotalAcumulado(BigDecimal obraFacturadoTotalAcumulado) {
        this.obraFacturadoTotalAcumulado = obraFacturadoTotalAcumulado;
    }

    public BigDecimal getObraFacturadoInversionAcumulado() {
        return obraFacturadoInversionAcumulado;
    }

    public void setObraFacturadoInversionAcumulado(BigDecimal obraFacturadoInversionAcumulado) {
        this.obraFacturadoInversionAcumulado = obraFacturadoInversionAcumulado;
    }

    public BigDecimal getInterFacturadoTotalAcumulado() {
        return interFacturadoTotalAcumulado;
    }

    public void setInterFacturadoTotalAcumulado(BigDecimal interFacturadoTotalAcumulado) {
        this.interFacturadoTotalAcumulado = interFacturadoTotalAcumulado;
    }

    public String getObraMesFacturado() {
        return obraMesFacturado;
    }

    public void setObraMesFacturado(String obraMesFacturado) {
        this.obraMesFacturado = obraMesFacturado;
    }

    public List<VigenciaDOT> getVigenciaList() {
        return vigenciaList;
    }

    public void setVigenciaList(List<VigenciaDOT> vigenciaList) {
        this.vigenciaList = vigenciaList;
    }

    public ReporteTableDOT getTableDOT() {
        return tableDOT;
    }

    public void setTableDOT(ReporteTableDOT tableDOT) {
        this.tableDOT = tableDOT;
    }
    
    

}
