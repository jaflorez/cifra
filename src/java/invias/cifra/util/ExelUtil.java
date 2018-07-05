/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.util;
//import org.pdfclown.documents.Document;
//import org.pdfclown.documents.contents.colors.DeviceRGBColor;

import invias.cifra.entity.RptConsolidado;
import invias.cifra.exception.CifraException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author jaflorez
 */
public class ExelUtil {

    public void createExcelPdf(List<RptConsolidado> rptLista, String fileName) throws CifraException {
        String sheetName = "Consolidado";//name of sheet
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet(sheetName);
        //iterating r number of rows
        Object rpt = new RptConsolidado();
        HSSFRow row = sheet.createRow(0);
        int contadorColumna = 1;
        for (Field field : rpt.getClass().getDeclaredFields()) {
            contadorColumna++;
            if (contadorColumna > 7) {
                field.setAccessible(true); // You might want to set modifier to public first.
                HSSFCell cell = row.createCell(contadorColumna - 8);
                cell.setCellValue(field.getName().toUpperCase());
            }
        }
        int contadorFilas = 1;
        int contadorCampo;
        if (rptLista != null) {
            for (RptConsolidado rc : rptLista) {
                row = sheet.createRow(contadorFilas);
                HSSFCell cell = row.createCell(0);
                cell.setCellValue("Si");
                contadorCampo = 0;
                contadorColumna = 0;
                for (Field field : rc.getClass().getDeclaredFields()) {
                    if (contadorCampo > 5) {

                        HSSFCell cellval = row.createCell(contadorCampo - 6);
                        field.setAccessible(true); // You might want to set modifier to public first.
                        Object value = null;
                        try {
                            if (field.getType() == String.class) {
                                String valor = (String) field.get(rc);
                                if(valor != null){
                                    cellval.setCellValue(valor);
                                }
                                
                            } else if (field.getType() == BigDecimal.class) {
                                BigDecimal bigValor = (BigDecimal) field.get(rc);
                                if (bigValor != null) {
                                    Double valor = bigValor.doubleValue();
                                    cellval.setCellValue(valor);
                                }
                            } else if (field.getType() == Date.class) {
                                Date valor = (Date) field.get(rc);
                                if(valor != null){
                                    cellval.setCellValue(valor);
                                }
                                
                            } else {
                                cellval.setCellValue("Desconocido");
                            }

                        } catch (IllegalArgumentException | IllegalAccessException ex) {
                            Logger.getLogger(ExelUtil.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        

                    }
                    contadorCampo++;
                }
                contadorFilas++;
            }
        }
        try {
            FileOutputStream fileOut;
            fileOut = new FileOutputStream(fileName);
            wb.write(fileOut);
            fileOut.flush();
            fileOut.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExelUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExelUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        //write this workbook to an Outputstream.

    }

}
