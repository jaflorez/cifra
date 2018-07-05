/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.util;
//import org.pdfclown.documents.Document;
//import org.pdfclown.documents.contents.colors.DeviceRGBColor;

import com.itextpdf.text.BadElementException;
import invias.cifra.entity.Reporte;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;

import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import invias.cifra.dot.RegistroAvanceDOT;
import invias.cifra.dot.ReporteDOT;

import invias.cifra.dot.VigenciaDOT;
import invias.cifra.entity.Contrato;

import invias.cifra.entity.ContratoTerceroInter;
import invias.cifra.entity.ContratoTerceroObra;
import invias.cifra.entity.ReporteMateriales;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

//import org.pdfclown.documents.contents.composition.*;
/**
 *
 * @author jaflorez
 */
public class PdfUtil {

    Document document;
    protected final static Font FONTBASE = new Font(Font.FontFamily.HELVETICA, 4.6f, Font.NORMAL);
    protected final static Font FONTBASETN = new Font(Font.FontFamily.HELVETICA, 3.8f, Font.NORMAL);
    protected final static Font FONTBASEBOLD = new Font(Font.FontFamily.HELVETICA, 4.6f, Font.BOLD);
    protected final static Font FONTTITLE = new Font(Font.FontFamily.HELVETICA, 5f, Font.BOLD);
    private PdfPCell pCellSiXNo_;
    private PdfPCell pCellSi_NoX;
    private PdfPCell pCellSiXNo_Na_;
    private PdfPCell pCellSi_NoXNa_;
    private PdfPCell pCellSi_No_NaX;
    private PdfPCell pCellNull;

    public void createFilePdf(ReporteDOT reporteDot, String fileName) throws DocumentException, FileNotFoundException {
        this.pCellSiXNo_ = new PdfPCell(new Phrase("Si[X] No[ ]", FONTBASEBOLD));
        this.pCellSi_NoX = new PdfPCell(new Phrase("Si[ ] No[X]", FONTBASEBOLD));
        this.pCellSiXNo_Na_ = new PdfPCell(new Phrase("Si[X] No[_] N/A[_]", FONTBASEBOLD));
        this.pCellSi_NoXNa_ = new PdfPCell(new Phrase("Si[ ] No[X] N/A[_]", FONTBASEBOLD));
        this.pCellSi_No_NaX = new PdfPCell(new Phrase("Si[ ] No[_] N/A[X]", FONTBASEBOLD));
        this.pCellNull = new PdfPCell(new Phrase("", FONTTITLE));

        document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(fileName));
        Rectangle sizePag = new Rectangle(612, 936);
        document.setPageSize(sizePag);
        document.open();
        document.setMargins(15, 15, 15, 15);
        this.createReport(reporteDot);
        document.close();

    }

    private void createReport(ReporteDOT reporteDot) throws DocumentException {

        String strTemp = "";
        PdfPTable table;
        PdfPCell cell;
        table = new PdfPTable(12);
        table.setWidthPercentage(100);
        table.setSpacingBefore(0f);
        table.setSpacingAfter(0f);

        Contrato contrato = reporteDot.getReporte().getFkContrato();
        Reporte reporte = reporteDot.getReporte();
        try {
            cell = createImageCell(CifraUtil.pathToResources() + "\\images\\invias_cld.png");
            cell.setRowspan(4);
            table.addCell(cell);
        } catch (IOException ex) {
            Logger.getLogger(PdfUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            if (reporte.getFkEstado().getIdentificador() == 1) {
                cell = createImageCell(CifraUtil.pathToResources() + "\\images\\header_rp_.png");
            } else {
                cell = createImageCell(CifraUtil.pathToResources() + "\\images\\header_rp.png");
            }

            cell.setColspan(8);
            cell.setRowspan(4);
            table.addCell(cell);
        } catch (IOException ex) {
            Logger.getLogger(PdfUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        cell = new PdfPCell(new Phrase("Codigo", FONTBASE));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("MINFRA-MN-IN-15-FR-2", FONTBASE));

        cell.setColspan(2);
        table.addCell(cell);
        //Nueva linea
        cell = new PdfPCell(new Phrase("Version", FONTBASE));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("1", FONTBASE));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("", FONTBASE));
        table.addCell(cell);
        //Nueva linea
        cell = new PdfPCell(new Phrase("Pagina", FONTBASE));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("1 de 1", FONTBASE));
        cell.setColspan(2);
        table.addCell(cell);
        //Nueva linea
        cell = new PdfPCell(new Phrase("Fecha", FONTBASE));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(CifraUtil.formatDateMM(reporteDot.getReporte().getFechaPresentacionReporte()), FONTBASE));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("", FONTBASE));
        table.addCell(cell);
        //Fin header
        cell = new PdfPCell(new Phrase("Informacion General", FONTTITLE));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setColspan(12);
        table.addCell(cell);
        //
        cell = new PdfPCell(new Phrase("Proyecto Carretera", FONTBASE));
        cell.setColspan(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(contrato.getFkProyecto().getNombre(), FONTBASE));
        cell.setColspan(6);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Tramo y o sector", FONTBASE));
        cell.setColspan(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(CifraUtil.formatString(contrato.getTramo()), FONTBASE));
        cell.setColspan(2);
        table.addCell(cell);
        /// Nueva linea

        cell = new PdfPCell(new Phrase("Unidad Ejecutora", FONTBASE));
        cell.setColspan(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(contrato.getFkUnidadEjecutora().getUnidadEjecutora(), FONTBASE));
        cell.setColspan(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Direccion territorial", FONTBASE));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(contrato.getFkDireccionTerritorial().getDireccionTerrritorial(), FONTBASE));
        cell.setColspan(3);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("SemanaNo: " + reporteDot.getReporte().getNumerosemana(), FONTBASE));
        cell.setColspan(4);
        table.addCell(cell);
        //nueva linea        
        cell = new PdfPCell(new Phrase("Supervisor Contrato interventoria:", FONTBASE));
        cell.setColspan(2);
        table.addCell(cell);
        strTemp = contrato.getFkUsuarioSupervisor().getNombre() + " " + contrato.getFkUsuarioSupervisor().getApellido();
        cell = new PdfPCell(new Phrase(strTemp, FONTBASE));
        cell.setColspan(4);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Gestor tècnico del proyecto:", FONTBASE));
        cell.setColspan(2);
        table.addCell(cell);
        strTemp = contrato.getFkUsuarioGestor().getNombre() + " " + contrato.getFkUsuarioGestor().getApellido();
        cell = new PdfPCell(new Phrase(strTemp, FONTBASE));
        cell.setColspan(4);
        table.addCell(cell);
        // Nueva linea
        cell = new PdfPCell(new Phrase("Objeto del conrato de obra: ", FONTBASE));
        cell.setColspan(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(contrato.getObjeto(), FONTBASE));
        cell.setColspan(8);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Fecha de inicio", FONTBASE));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(CifraUtil.formatDate(contrato.getContObraFecha()), FONTBASE));
        table.addCell(cell);
        ///        
        cell = new PdfPCell(new Phrase("Km a mejorar: ", FONTBASE));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(CifraUtil.formatNumero(reporteDot.getObraTotalKmMejorar()), FONTBASE));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Km a rehabilitar", FONTBASE));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(CifraUtil.formatNumero(reporteDot.getObraTotalKmRehabilitar()), FONTBASE));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Km a Construir", FONTBASE));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(CifraUtil.formatNumero(reporteDot.getObraTotalKmConstruir()), FONTBASE));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("No. Puente", FONTBASE));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(CifraUtil.formatNumero(reporteDot.getObraTotalPuentes()), FONTBASE));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Otros", FONTBASE));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("", FONTBASE));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("", FONTBASE));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("", FONTBASE));
        table.addCell(cell);
        //Fin nueva linea
        cell = new PdfPCell(new Phrase("Contrato de Obra: ", FONTBASE));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setColspan(6);

        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Contrato de Interventoria", FONTBASE));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setColspan(6);
        table.addCell(cell);
        ///
        cell = new PdfPCell(new Phrase("Contratista", FONTBASE));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(contrato.getFkTerceroObra().getRazonsocial(), FONTBASE));
        cell.setColspan(5);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Interventor", FONTBASE));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(contrato.getFkTerceroInter().getRazonsocial(), FONTBASE));
        cell.setColspan(5);
        table.addCell(cell);
        //Fin linea
        cell = new PdfPCell(new Phrase("Integrantes", FONTBASE));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("", FONTBASE));
        cell.addElement(this.getTableIntegrante(contrato, 1));
        cell.setColspan(5);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Integrantes", FONTBASE));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("", FONTBASE));
        cell.addElement(this.getTableIntegrante(contrato, 2));
        cell.setColspan(5);
        table.addCell(cell);
        //Fin linea
        cell = new PdfPCell(new Phrase("Contrato No", FONTBASE));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(contrato.getContObraCodigo() + " - " + contrato.getContObraVigencia(), FONTBASE));
        cell.setColspan(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Valor Inicial", FONTBASE));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(CifraUtil.formatPesos(contrato.getContObraValorInicial()), FONTBASE));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell.setColspan(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Contrato No", FONTBASE));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(contrato.getContInterCodigo() + " - " + contrato.getContInterVigencia(), FONTBASE));
        cell.setColspan(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Valor Inicial", FONTBASE));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(CifraUtil.formatPesos(contrato.getContInterValorInicial()), FONTBASE));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell.setColspan(2);
        table.addCell(cell);
        //Fin linea        
        cell = new PdfPCell(new Phrase("Plazo Inicial", FONTBASE));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(CifraUtil.formatNumero(contrato.getContObraPlazoInicial()), FONTBASE));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell.setColspan(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Adiciones", FONTBASE));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(CifraUtil.formatPesos(reporteDot.getObraSumaAdiciones()), FONTBASE));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell.setColspan(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Plazo Inicial", FONTBASE));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(contrato.getContInterPlazoInicial().toString(), FONTBASE));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell.setColspan(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Adiciones", FONTBASE));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(CifraUtil.formatPesos(reporteDot.getInterSumaAdiciones()), FONTBASE));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell.setColspan(2);
        table.addCell(cell);
        //Fin linea
        cell = new PdfPCell(new Phrase("Plazo acumulado", FONTBASE));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(CifraUtil.formatNumero(contrato.getContObraPlazoInicial().add(reporteDot.getObraSumaProrrogas())), FONTBASE));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell.setColspan(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Valor acumulado", FONTBASE));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(CifraUtil.formatPesos(contrato.getContObraValorInicial().add(reporteDot.getObraSumaAdiciones())), FONTBASE));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell.setColspan(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Plazo acumulado", FONTBASE));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(CifraUtil.formatNumero(contrato.getContObraPlazoInicial().add(reporteDot.getInterSumaProrrogas())), FONTBASE));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell.setColspan(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Valor Acumulado", FONTBASE));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(CifraUtil.formatPesos(contrato.getContInterValorInicial().add(reporteDot.getInterSumaAdiciones())), FONTBASE));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell.setColspan(2);
        table.addCell(cell);
        ///
        cell = new PdfPCell(new Phrase("II Informacion Presupuestal y avance financiero", FONTTITLE));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setColspan(12);
        table.addCell(cell);
        ///
        cell = new PdfPCell(new Phrase("Distribuciòn de recursos presupuestales contrato de obra por vigencia", FONTBASE));
        cell.setColspan(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(":", FONTBASE));
        cell.addElement(this.getTableVigencia(reporteDot));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setColspan(10);
        table.addCell(cell);
        //Fin linea
        cell = new PdfPCell(new Phrase("Contrato de obra: ", FONTTITLE));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setColspan(6);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Contrato de interventoria", FONTTITLE));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setColspan(6);
        table.addCell(cell);
        ///Nueva linea
        cell = new PdfPCell(new Phrase("Valor básico contrato de obra", FONTBASE));
        cell.setColspan(3);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(CifraUtil.formatPesos(reporteDot.getObraProgramaInversionTotal()), FONTBASE));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell.setColspan(3);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("", FONTBASE));
        cell.setColspan(6);
        cell.setRowspan(6);
        table.addCell(cell);
        //
        ///
        cell = new PdfPCell(new Phrase("Anticipos asignados", FONTBASE));
        cell.setColspan(3);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(CifraUtil.formatPesos(reporteDot.getObraProgramaInversionAcumulado()), FONTBASE));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell.setColspan(3);
        table.addCell(cell);
        
        ///
        cell = new PdfPCell(new Phrase("Inversion obra, programada acumulada", FONTBASE));
        cell.setColspan(3);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(CifraUtil.formatPesos(reporteDot.getObraProgramaInversionAcumulado()), FONTBASE));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell.setColspan(3);
        table.addCell(cell);
        //
        cell = new PdfPCell(new Phrase("Inversion obra, ejecutada acumulada", FONTBASE));
        cell.setColspan(3);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(CifraUtil.formatPesos(reporteDot.getObraFacturadoInversionAcumulado()), FONTBASE));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell.setColspan(3);
        table.addCell(cell);

        //
        cell = new PdfPCell(new Phrase("Avance obra en % B/A", FONTBASE));
        cell.setColspan(3);
        table.addCell(cell);
        BigDecimal valorBA;
        Double dbBA;
        if (reporteDot.getObraFacturadoInversionAcumulado().doubleValue() == 0 || reporteDot.getObraProgramaInversionTotal().doubleValue() == 0) {
            dbBA = 0d;
        } else {
            dbBA = reporteDot.getObraFacturadoInversionAcumulado().doubleValue() / reporteDot.getObraProgramaInversionTotal().doubleValue();
            dbBA *= 100.0d;
        }

        valorBA = BigDecimal.valueOf(dbBA);
        cell = new PdfPCell(new Phrase(CifraUtil.formatNumero(valorBA), FONTBASE));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell.setColspan(3);
        table.addCell(cell);
        //        
        cell = new PdfPCell(new Phrase("Avance obra fisica %", FONTBASE));
        cell.setColspan(3);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(CifraUtil.formatNumero(reporte.getObrAvanceFisico()), FONTBASE));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell.setColspan(3);
        table.addCell(cell);

        //
        cell = new PdfPCell(new Phrase("Otras inversiones ejecutadas (Estudios y diseños gestion predial, social ambiental y otras)", FONTBASE));
        cell.setColspan(3);
        table.addCell(cell);
        BigDecimal temp = BigDecimal.ZERO;
        //Se calcula el valor de otras inversiones
        temp = reporteDot.getObraFacturadoTotalAcumulado().subtract(reporteDot.getObraFacturadoInversionAcumulado());
        cell = new PdfPCell(new Phrase(CifraUtil.formatPesos(temp), FONTBASE));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell.setColspan(3);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("", FONTBASE));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Ejecutado acumulado", FONTBASE));
        cell.setColspan(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(CifraUtil.formatPesos(reporteDot.getInterFacturadoTotalAcumulado()), FONTBASE));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell.setColspan(4);
        table.addCell(cell);
        // fin de linea
        cell = new PdfPCell(new Phrase("Total inversion ejecutada", FONTBASE));
        cell.setColspan(3);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(CifraUtil.formatPesos(reporteDot.getObraFacturadoTotalAcumulado()), FONTBASE));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell.setColspan(3);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("", FONTBASE));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("", FONTBASE));
        cell.setColspan(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("", FONTBASE));
        cell.setColspan(4);
        table.addCell(cell);
        // fin de linea
        cell = new PdfPCell(new Phrase("Presento factura en la presente semana", FONTBASE));
        cell.setColspan(3);
        table.addCell(cell);

        if (reporte.getObrPresentoFactura() != null) {
            if (reporte.getObrPresentoFactura()) {
                cell = this.pCellSiXNo_;
            } else {
                cell = this.pCellSi_NoX;
            }
        } else {
            cell = this.pCellNull;
        }
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("", FONTBASE));
        cell.setColspan(2);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("", FONTBASE));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Presento factura en la presente semana", FONTBASE));
        cell.setColspan(2);
        table.addCell(cell);

        if (reporte.getIntPresentoFactura() != null) {
            if (reporte.getIntPresentoFactura()) {
                cell = this.pCellSiXNo_;
            } else {
                cell = this.pCellSi_NoX;
            }
        } else {
            cell = this.pCellNull;
        }
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("", FONTBASE));
        cell.setColspan(2);
        table.addCell(cell);
        // fin de linea

        cell = new PdfPCell(new Phrase("Mes facturado", FONTBASE));
        cell.setColspan(3);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(reporteDot.getObraMesFacturado(), FONTBASE));
        cell.setColspan(1);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(" ", FONTBASE));
        cell.setColspan(2);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("", FONTBASE));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Mes facturado", FONTBASE));
        cell.setColspan(2);
        table.addCell(cell);
        if (reporteDot.getReporte().getIntMesFacturado() != null) {
            cell = new PdfPCell(new Phrase(reporteDot.getReporte().getIntMesFacturado().toString(), FONTBASE));
        } else {
            cell = new PdfPCell(new Phrase("", FONTBASE));
        }

        cell.setColspan(4);
        table.addCell(cell);
        // fin de linea
        cell = new PdfPCell(new Phrase("Valor factura", FONTBASE));
        cell.setColspan(2);
        table.addCell(cell);
        /*Si no presento factura se presenta el valor proyectado*/
        if (reporte.getIntPresentoFactura() != null) {
            if (reporte.getIntPresentoFactura()) {
                cell = new PdfPCell(new Phrase(reporte.getObrValorFacturado() == null ? "" : CifraUtil.formatPesos(reporte.getObrValorFacturado()), FONTBASE));
            } else {
                cell = new PdfPCell(new Phrase(reporte.getObrValorInversion() == null ? "" : CifraUtil.formatPesos(reporte.getObrValorInversion()), FONTBASE));
            }
        } else {
            cell = this.pCellNull;
        }
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell.setColspan(2);
        table.addCell(cell);
        
        
        
        
        cell = new PdfPCell(new Phrase("Fecha factura", FONTBASE));
        cell.setColspan(1);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(CifraUtil.formatDate(reporte.getObrFechaFactura()), FONTBASE));
        cell.setColspan(1);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("", FONTBASE));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Valor factura", FONTBASE));
        cell.setColspan(1);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(CifraUtil.formatPesos(reporte.getIntValorFactura()), FONTBASE));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell.setColspan(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Fecha factura", FONTBASE));
        cell.setColspan(1);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(CifraUtil.formatDate(reporte.getIntFechaFactura()), FONTBASE));
        cell.setColspan(2);
        table.addCell(cell);
        // fin de linea
        cell = new PdfPCell(new Phrase("", FONTBASE));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Pagos acumulados reporte SIIF NACION", FONTBASE));
        cell.setColspan(3);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(CifraUtil.formatPesos(reporte.getObrReporteSiif()), FONTBASE));
        cell.setColspan(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("", FONTBASE));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Pagos acumulados reporte SIIF NACION", FONTBASE));
        cell.setColspan(3);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(CifraUtil.formatPesos(reporte.getIntReporteSiif()), FONTBASE));
        cell.setColspan(3);
        table.addCell(cell);

        // fin de linea
        cell = new PdfPCell(new Phrase("III Avance Físico", FONTTITLE));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setColspan(12);
        table.addCell(cell);
        if (reporteDot.getReporte().getFkContrato().getContratoTramoList().size() > 8) {
            cell = new PdfPCell(new Phrase("", FONTTITLE));
            cell.addElement(this.getTableAvanceObra(reporteDot));
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            cell.setColspan(12);
            table.addCell(cell);
            // fin de linea
            cell = new PdfPCell(new Phrase("", FONTTITLE));
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            cell.addElement(this.getTableAvanceMaquinaria(reporteDot));
            cell.setColspan(12);
            table.addCell(cell);

        } else {
            cell = new PdfPCell(new Phrase("", FONTTITLE));
            cell.addElement(this.getTableAvanceObra(reporteDot));
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            cell.setColspan(7);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("", FONTTITLE));
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            cell.addElement(this.getTableAvanceMaquinaria(reporteDot));
            cell.setColspan(5);
            table.addCell(cell);

        }

        ///
        cell = new PdfPCell(new Phrase("IV Gestion Ambiental", FONTTITLE));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setColspan(12);
        table.addCell(cell);
        // fin de linea
        cell = new PdfPCell(new Phrase("Fuentes de materiales", FONTTITLE));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setColspan(6);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Permisos ambientales y/o licenciamiento ambiental", FONTTITLE));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setColspan(6);
        table.addCell(cell);
        // fin de linea
        cell = new PdfPCell(new Phrase(" ", FONTBASE));
        cell.addElement(this.getTableFuentesMateriales(reporte));
        cell.setColspan(6);
        cell.setRowspan(4);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Zonas de deposito", FONTBASE));
        cell.setColspan(2);
        table.addCell(cell);
        cell = this.getTextSiNoNa(reporte.getAmbBotaderos());
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Ocupación de causes", FONTBASE));
        cell.setColspan(2);
        table.addCell(cell);
        cell = this.getTextSiNoNa(reporte.getAmbOcupacioncauces());
        table.addCell(cell);

        // fin de linea
        cell = new PdfPCell(new Phrase("Licencia ambiental", FONTBASE));
        cell.setColspan(2);
        table.addCell(cell);
        cell = this.getTextSiNoNa(reporte.getAmbLicenciambiental());
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Vertimientos", FONTBASE));
        cell.setColspan(2);
        table.addCell(cell);
        if (reporte.getAmbVertimentos() != null) {
            cell = this.getTextSiNoNa(reporte.getAmbVertimentos());
        } else {
            cell = this.getTextSiNoNa(5);
        }
        table.addCell(cell);

        // fin de linea
        cell = new PdfPCell(new Phrase("Aprovechamiento forestal", FONTBASE));
        cell.setColspan(2);
        table.addCell(cell);
        cell = this.getTextSiNoNa(reporte.getAmbAprovforestal());
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Emisiones", FONTBASE));
        cell.setColspan(2);
        table.addCell(cell);
        if (reporte.getAmbEmiciones() != null) {
            cell = this.getTextSiNoNa(reporte.getAmbEmiciones());
        } else {
            cell = this.getTextSiNoNa(5);
        }
        table.addCell(cell);
        // fin de linea
        cell = new PdfPCell(new Phrase("Concesion de aguas", FONTBASE));
        cell.setColspan(2);
        table.addCell(cell);
        cell = this.getTextSiNoNa(reporte.getAmbConcecionaguas());
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Otros", FONTBASE));
        cell.setColspan(2);
        table.addCell(cell);
        cell = this.getTextSiNoNa(3);
        table.addCell(cell);

        // fin de linea
        cell = new PdfPCell(new Phrase("Observaciones:" + reporte.getFtmatObservaciones(), FONTBASE));
        cell.setColspan(6);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Observaciones:" + reporte.getAmbObservaciones(), FONTBASE));
        cell.setColspan(6);
        table.addCell(cell);
        //fin linea      
        cell = new PdfPCell(new Phrase("P.A.G.A. (Programa de Adaptación a  la Guia Ambiental  )", FONTBASE));
        cell.setColspan(6);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Observaciones: " + reporte.getPagaObservaciones(), FONTBASE));
        cell.setColspan(6);
        cell.setRowspan(2);
        table.addCell(cell);
        //fin linea      
        cell = new PdfPCell(new Phrase("Estado", FONTBASE));
        cell.setColspan(1);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(reporte.getPagaEstado(), FONTBASE));
        cell.setColspan(5);
        table.addCell(cell);

        //fin linea      
        cell = new PdfPCell(new Phrase("V. Gestion predial", FONTTITLE));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setColspan(12);
        table.addCell(cell);
        //fin linea      
        cell = new PdfPCell(new Phrase("Total predios a adquirir", FONTBASE));
        cell.setColspan(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(CifraUtil.formatInteger(reporte.getGeprPredadquirir()), FONTBASE));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("", FONTBASE));
        cell.setColspan(3);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Observaciones: " + reporte.getGeprObservaciones(), FONTBASE));
        cell.setColspan(6);
        cell.setRowspan(4);
        table.addCell(cell);
        //fin linea      
        cell = new PdfPCell(new Phrase("Fichas prediales aprobadas", FONTBASE));
        cell.setColspan(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(CifraUtil.formatInteger(reporte.getGeprFichasaprob()), FONTBASE));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("", FONTBASE));
        cell.setColspan(3);
        table.addCell(cell);

        //fin linea      
        cell = new PdfPCell(new Phrase("Avaluos aprobados", FONTBASE));
        cell.setColspan(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(CifraUtil.formatInteger(reporte.getGeprAvaluos()), FONTBASE));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("", FONTBASE));
        cell.setColspan(3);
        table.addCell(cell);

        //fin linea      
        cell = new PdfPCell(new Phrase("Predios adquiridos", FONTBASE));
        cell.setColspan(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(CifraUtil.formatInteger(reporte.getGeprPredadquiridos()), FONTBASE));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("", FONTBASE));
        cell.setColspan(3);
        table.addCell(cell);

        //fin linea      
        cell = new PdfPCell(new Phrase("VI. Gestion Social", FONTTITLE));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setColspan(12);
        table.addCell(cell);
        //fin linea      
        cell = new PdfPCell(new Phrase("Total empleos generados", FONTBASE));
        cell.setColspan(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(CifraUtil.formatInteger(reporte.getGesoEmpgen()), FONTBASE));
        cell.setColspan(1);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("No. Socializaciones realizadas", FONTBASE));
        cell.setColspan(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(CifraUtil.formatInteger(reporte.getGesoNosocializaciones()), FONTBASE));
        cell.setColspan(1);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Observaciones:" + CifraUtil.formatString(reporte.getGesoObservaciones()), FONTBASE));
        cell.setColspan(6);
        cell.setRowspan(4);
        table.addCell(cell);
        //
        cell = new PdfPCell(new Phrase("Empleos directos generados", FONTBASE));
        cell.setColspan(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(CifraUtil.formatInteger(reporte.getGesoEmpdirgen()), FONTBASE));
        cell.setColspan(1);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("No. Capacitaciones realizadas", FONTBASE));
        cell.setColspan(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(CifraUtil.formatInteger(reporte.getGesoNocapacitaciones()), FONTBASE));
        cell.setColspan(1);
        table.addCell(cell);
        //Fin linea
        cell = new PdfPCell(new Phrase("Empleos indirectos generados", FONTBASE));
        cell.setColspan(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(CifraUtil.formatInteger(reporte.getGesoEmpindirgen()), FONTBASE));
        cell.setColspan(1);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Direccion sede atencion al usuario: " + reporte.getGesoSede(), FONTBASE));
        cell.setColspan(3);
        cell.setRowspan(2);
        table.addCell(cell);
        //Fin linea
        cell = new PdfPCell(new Phrase("Otros", FONTBASE));
        cell.setColspan(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("", FONTBASE));
        cell.setColspan(1);
        table.addCell(cell);
        //Fin linea
        cell = new PdfPCell(new Phrase("VII. Actividades realizadas en la semana", FONTTITLE));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setColspan(12);
        table.addCell(cell);
        //fin linea      
        cell = new PdfPCell(new Phrase(CifraUtil.formatString(reporte.getActividadRealizada()), FONTBASE));
        cell.setColspan(12);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("VIII. Actividades a realizar en la siguiente semana", FONTTITLE));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setColspan(12);
        table.addCell(cell);
        //fin linea      
        cell = new PdfPCell(new Phrase(CifraUtil.formatString(reporte.getActividadARealizar()), FONTBASE));
        cell.setColspan(12);
        table.addCell(cell);
        //fin linea      
        cell = new PdfPCell(new Phrase("IX. Esquema grafico del proyecto", FONTTITLE));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setColspan(12);
        table.addCell(cell);
        //Esquema grafico
        cell = getImageEsquema(reporteDot);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setColspan(12);
        table.addCell(cell);
        //fin linea      
        cell = new PdfPCell(new Phrase("X. Registro fotografico", FONTTITLE));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setColspan(12);
        table.addCell(cell);
        //fin linea      
        //Fotos 
        cell = getImageSoporteGrafico(reporteDot, 1);
        cell.setColspan(4);
        table.addCell(cell);
        cell = getImageSoporteGrafico(reporteDot, 2);
        cell.setColspan(4);
        table.addCell(cell);
        cell = getImageSoporteGrafico(reporteDot, 3);
        cell.setColspan(4);
        table.addCell(cell);
        //fin linea      
        cell = new PdfPCell(new Phrase("Nota: Las cifras cantidades y valores consignadas en el presente reporte son responsabilidad esclusiva del interventor", FONTBASE));
        cell.setColspan(12);
        table.addCell(cell);
        //fin linea      
        cell = new PdfPCell(new Phrase("XI. Describir las acciones a seguir por parte de la interventoria que contribuyan al cumplimiento de las obligaciones contractuales por parte del contratista de obra", FONTBASE));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setColspan(8);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Director de interventoria", FONTBASE));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setColspan(4);
        table.addCell(cell);
        //fin linea      
        cell = new PdfPCell(new Phrase(CifraUtil.formatString(reporte.getResumen()), FONTBASE));
        cell.setColspan(8);
        cell.setRowspan(4);
        table.addCell(cell);
        cell = getImageFirma(reporteDot);
        cell.setColspan(4);
        cell.setRowspan(2);
        table.addCell(cell);
        //fin linea      
        cell = new PdfPCell(new Phrase("Nombre " + reporte.getNombreFirma(), FONTBASE));
        cell.setColspan(4);
        table.addCell(cell);
        //fin linea      
        cell = new PdfPCell(new Phrase("Mat Prof" + reporte.getMatriculaProfesional(), FONTBASE));
        cell.setColspan(4);
        table.addCell(cell);
        //fin linea      
        document.add(table);

    }

    private PdfPCell getImageSoporteGrafico(ReporteDOT rdot, int numImage) {
        PdfPCell cell;

        String rutaFileImage = "";
        try {
            int contador = 0;
            for (int i = 0; i < rdot.getReporte().getReporteArchivoList().size(); i++) {
                if (rdot.getReporte().getReporteArchivoList().get(i).getTipoArchivo() == 0) {
                    contador++;
                }
                if (contador == numImage) {
                    rutaFileImage = CifraUtil.imageToFile(rdot.getReporte().getReporteArchivoList().get(i));
                    break;
                }
            }
            if (!"".equals(rutaFileImage)) {
                Rectangle position = new Rectangle(150f, 75f);
                cell = createImageCellPosition(rutaFileImage, position);
            } else {
                cell = new PdfPCell(new Phrase("??", FONTBASE));
            }
            return cell;
        } catch (IOException ex) {
            Logger.getLogger(PdfUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }
    private PdfPCell getImageFirma(ReporteDOT rdot){

        PdfPCell cell;
        String rutaFileImage = "";
        try {
            for (int i = 0; i < rdot.getReporte().getReporteArchivoList().size(); i++) {
                if (rdot.getReporte().getReporteArchivoList().get(i).getTipoArchivo() == 2) {
                    rutaFileImage = CifraUtil.imageToFile(rdot.getReporte().getReporteArchivoList().get(i));
                    break;
                }
            }
            if (!"".equals(rutaFileImage)) {
                Rectangle position = new Rectangle(150f, 70f);
                cell = createImageCellPosition(rutaFileImage, position);
            } else {
                cell = new PdfPCell(new Phrase("??", FONTBASE));
            }
            return cell;
        } catch (IOException ex) {
            Logger.getLogger(PdfUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
        
        
    }

    private PdfPCell getImageEsquema(ReporteDOT rdot) {
        PdfPCell cell;
        String rutaFileImage = "";
        try {
            for (int i = 0; i < rdot.getReporte().getReporteArchivoList().size(); i++) {
                if (rdot.getReporte().getReporteArchivoList().get(i).getTipoArchivo() == 1) {
                    rutaFileImage = CifraUtil.imageToFile(rdot.getReporte().getReporteArchivoList().get(i));
                    break;
                }
            }
            if (!"".equals(rutaFileImage)) {
                Rectangle position = new Rectangle(500f, 50f);
                cell = createImageCellPosition(rutaFileImage, position);
            } else {
                cell = new PdfPCell(new Phrase("??", FONTBASE));
            }
            return cell;
        } catch (IOException ex) {
            Logger.getLogger(PdfUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

    private PdfPTable getTableAvanceMaquinaria(ReporteDOT rdot) {
        float anchoCelda = 14;
        float ancho;
        int numeroTramos;
        numeroTramos = rdot.getReporte().getFkContrato().getContratoTramoList().size();
        ancho = (float) ((numeroTramos + 6) * anchoCelda);
        PdfPTable table = new PdfPTable(numeroTramos + 7);
        table.setTotalWidth(ancho);
        table.setLockedWidth(true);
        PdfPCell cell;
        /*Conformacion del header de la tabla*/
        cell = new PdfPCell(new Phrase("Maquinaria", FONTBASETN));
        cell.setColspan(5);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("MMR", FONTBASETN)); // Datos de programado
        table.addCell(cell);
        for (int i = 1; i <= numeroTramos; i++) {
            String nomTramo = rdot.getTableDOT().getTituloTramo(i, "");
            cell = new PdfPCell(new Phrase(nomTramo, FONTBASETN));
            table.addCell(cell);
        }
        cell = new PdfPCell(new Phrase("Total", FONTBASETN));
        table.addCell(cell);
        for (RegistroAvanceDOT avanceDOT : rdot.getTableDOT().getRegistroMaquinariaLst()) {
            cell = new PdfPCell(new Phrase(avanceDOT.getTitulo(), FONTBASE));
            cell.setColspan(5);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(CifraUtil.formatNumeroTn(avanceDOT.getProgramado()), FONTBASETN));
            table.addCell(cell);
            for (int j = 0; j < numeroTramos; j++) {
                if (avanceDOT.getValor() != null) {
                    if (avanceDOT.getValor()[j] != null) {
                        cell = new PdfPCell(new Phrase(CifraUtil.formatNumeroTn(avanceDOT.getValor()[j]), FONTBASETN));
                    } else {
                        cell = new PdfPCell(new Phrase("", FONTBASETN));
                    }

                } else {
                    cell = new PdfPCell(new Phrase("", FONTBASETN));
                }

                table.addCell(cell);
            }
            cell = new PdfPCell(new Phrase(CifraUtil.formatNumeroTn(avanceDOT.getAcumulado()), FONTBASETN));
            table.addCell(cell);
        }
        return table;
    }

    private PdfPTable getTableAvanceObra(ReporteDOT rdot) {
        float anchoCelda = 18;
        float ancho;
        int numeroTramos;
        numeroTramos = rdot.getReporte().getFkContrato().getContratoTramoList().size();
        ancho = (float) ((numeroTramos + 6) * anchoCelda);
        PdfPTable table = new PdfPTable(numeroTramos + 10);
        table.setTotalWidth(ancho);
        table.setLockedWidth(true);
        PdfPCell cell;
        /*Conformacion del header de la tabla*/
        cell = new PdfPCell(new Phrase("Actividad", FONTBASETN));
        cell.setColspan(5);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("PRG", FONTBASETN)); // Datos de programado
        table.addCell(cell);
        for (int i = 1; i <= numeroTramos; i++) {
            String nomTramo = rdot.getTableDOT().getTituloTramo(i, "");
            cell = new PdfPCell(new Phrase(nomTramo, FONTBASETN));
            table.addCell(cell);
        }
        cell = new PdfPCell(new Phrase("Total", FONTBASETN));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("", FONTBASETN));
        cell.setColspan(5);
        table.addCell(cell);
        boolean isFirst = true;
        for (RegistroAvanceDOT avanceDOT : rdot.getTableDOT().getRegistroActividadLst()) {
            cell = new PdfPCell(new Phrase(avanceDOT.getTitulo(), FONTBASETN));
            cell.setColspan(5);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(CifraUtil.formatNumeroTn(avanceDOT.getProgramado()), FONTBASETN));
            table.addCell(cell);
            for (int j = 0; j < numeroTramos; j++) {
                if (avanceDOT.getValor() != null) {
                    if (avanceDOT.getValor()[j] != null) {
                        cell = new PdfPCell(new Phrase(CifraUtil.formatNumeroTn(avanceDOT.getValor()[j]), FONTBASETN));
                    } else {
                        cell = new PdfPCell(new Phrase("", FONTBASETN));
                    }

                } else {
                    cell = new PdfPCell(new Phrase("", FONTBASETN));
                }

                table.addCell(cell);
            }
            cell = new PdfPCell(new Phrase(CifraUtil.formatNumeroTn(avanceDOT.getAcumulado()), FONTBASETN));
            table.addCell(cell);
            /**
             * Rutina para incorporar la imagen, se hace cuando se esta
             * dibujando la primera linea de la tabla
             */
            if (isFirst) {
                String nomFile = GraphUtil.GetNomFileImageBarChar((int) (anchoCelda * 5), rdot.getReporte().getFkContrato().getContratoActividadList().size() * 20, rdot.getTableDOT().getRegistroActividadLst(), rdot.getReporte().getFkContrato().getContratoTramoList().size() + 1);
                try {
                    cell = createImageCell(nomFile);
                    cell.setColspan(5);
                    cell.setRowspan(rdot.getTableDOT().getRegistroActividadLst().size());
                    table.addCell(cell);
                } catch (IOException ex) {
                    Logger.getLogger(PdfUtil.class.getName()).log(Level.SEVERE, null, ex);
                }

                isFirst = false;
            }
        }
        return table;
    }

    private PdfPTable getTableVigencia(ReporteDOT rdot) {
        PdfPTable nestedTable = new PdfPTable(2 + rdot.getVigenciaList().size());
        float ancho;
        ancho = 60 + (float) (rdot.getVigenciaList().size() * 50);
        nestedTable.setTotalWidth(ancho);
        nestedTable.setLockedWidth(true);
        PdfPCell cell;
        cell = new PdfPCell(new Phrase("Vigencia", FONTBASE));
        cell.setColspan(2);
        nestedTable.addCell(cell);
        for (VigenciaDOT vdot : rdot.getVigenciaList()) {
            cell = new PdfPCell(new Phrase(vdot.getAno().toString(), FONTBASE));
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            nestedTable.addCell(cell);
        }
        cell = new PdfPCell(new Phrase("Recursos x vigencia", FONTBASE));
        cell.setColspan(2);
        nestedTable.addCell(cell);
        for (VigenciaDOT vdot : rdot.getVigenciaList()) {
            cell = new PdfPCell(new Phrase(CifraUtil.formatPesos(vdot.getVigencia()), FONTBASE));
            cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            nestedTable.addCell(cell);
        }
        cell = new PdfPCell(new Phrase("Ejecutado", FONTBASE));
        cell.setColspan(2);
        nestedTable.addCell(cell);
        for (VigenciaDOT vdot : rdot.getVigenciaList()) {
            cell = new PdfPCell(new Phrase(CifraUtil.formatPesos(vdot.getEjecutado()), FONTBASE));
            cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            nestedTable.addCell(cell);
        }
        return nestedTable;
    }

    private PdfPTable getTableIntegrante(Contrato contrato, int tipo) {
        PdfPTable nestedTable = new PdfPTable(5);
        nestedTable.setWidthPercentage(100);
        nestedTable.setSpacingBefore(0f);
        nestedTable.setSpacingAfter(0f);

        PdfPCell cell;
        cell = new PdfPCell(new Phrase("Nit", FONTBASE));
        nestedTable.addCell(cell);
        cell = new PdfPCell(new Phrase("Razon social", FONTBASE));
        cell.setColspan(3);
        nestedTable.addCell(cell);
        cell = new PdfPCell(new Phrase("%", FONTBASE));
        nestedTable.addCell(cell);

        if (tipo == 1) { // Integrantes de contrato de obra
            for (ContratoTerceroObra cto : contrato.getContratoTerceroObraList()) {
                cell = new PdfPCell(new Phrase(cto.getFkTercero().getNit(), FONTBASE));
                nestedTable.addCell(cell);
                cell = new PdfPCell(new Phrase(cto.getFkTercero().getRazonsocial(), FONTBASE));
                cell.setColspan(3);
                nestedTable.addCell(cell);
                cell = new PdfPCell(new Phrase(cto.getCantidad().toString() + "%", FONTBASE));
                nestedTable.addCell(cell);

            }
        } else { //  integranes del contrato de interventoria
            for (ContratoTerceroInter cti : contrato.getContratoTerceroInterList()) {
                cell = new PdfPCell(new Phrase(cti.getFkTercero().getNit(), FONTBASE));
                nestedTable.addCell(cell);
                cell = new PdfPCell(new Phrase(cti.getFkTercero().getRazonsocial(), FONTBASE));
                cell.setColspan(3);
                nestedTable.addCell(cell);
                cell = new PdfPCell(new Phrase(cti.getCantidad().toString() + "%", FONTBASE));
                nestedTable.addCell(cell);

            }

        }
        return nestedTable;

    }

    private PdfPTable getTableFuentesMateriales(Reporte reporte) {
        PdfPTable nestedTable = new PdfPTable(5);

        nestedTable.setWidthPercentage(100);
        nestedTable.setSpacingBefore(0f);
        nestedTable.setSpacingAfter(0f);

        PdfPCell cell;
        cell = new PdfPCell(new Phrase("Ubicación", FONTBASE));
        cell.setColspan(3);
        nestedTable.addCell(cell);
        cell = new PdfPCell(new Phrase("Permiso minero", FONTBASE));
        nestedTable.addCell(cell);
        cell = new PdfPCell(new Phrase("Licencia ambiental", FONTBASE));
        nestedTable.addCell(cell);

        for (ReporteMateriales rm : reporte.getReporteMaterialesList()) {
            cell = new PdfPCell(new Phrase(rm.getUbicacion(), FONTBASE));
            cell.setColspan(3);
            nestedTable.addCell(cell);
            if (rm.getPermisoMinero()) {
                cell = this.pCellSiXNo_;
            } else {
                cell = this.pCellSi_NoX;
            }
            nestedTable.addCell(cell);
            if (rm.getLicenciaAmbiental()) {
                cell = this.pCellSiXNo_;
            } else {
                cell = this.pCellSi_NoX;
            }
            nestedTable.addCell(cell);
        }
        return nestedTable;
    }

    private PdfPCell getTextSiNoNa(Integer tipo) {
        if (tipo == null) {
            return this.pCellNull;
        }
        if (tipo == 1) {
            return this.pCellSiXNo_Na_;
        }
        if (tipo == -1) {
            return this.pCellSi_NoXNa_;
        }
        if (tipo == 0) {
            return this.pCellSi_No_NaX;

        }
        PdfPCell cell = new PdfPCell(new Phrase("", FONTTITLE));
        return cell;
    }

    public static PdfPCell createImageCellPosition(String path, Rectangle position) throws IOException {
        try {
            Image img = Image.getInstance(path);
            img.setAbsolutePosition(2f, 2f);
            img.setBorder(1);
            img.scaleAbsolute(position.getWidth(), position.getHeight());

            PdfPCell cell = new PdfPCell(img, false);
            return cell;
        } catch (BadElementException | IOException ex) {
            Logger.getLogger(PdfUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static PdfPCell createImageCell(String path) throws IOException {
        try {
            Image img = Image.getInstance(path);
            PdfPCell cell = new PdfPCell(img, true);
            return cell;
        } catch (BadElementException ex) {
            Logger.getLogger(PdfUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PdfUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
