/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.util;

import invias.cifra.entity.AuxSiifCarga;
import invias.cifra.exception.CifraException;
import invias.cifra.model.SiifSrv;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jaflorez
 */
public class ReadSiifUtil {

    public int loadFile(String nombreArchivo, String tipoArchivo) {
        String line = "";
        String cvsSplitBy = ";";
        AuxSiifCarga carga = new AuxSiifCarga();
        Date fechaCarga = new Date();
        SiifSrv siifSrv = new SiifSrv();
        int registrosProcesados = 0;
        try {
            siifSrv.eliminarRegistrosAuxSiifCarga();
        } catch (CifraException ex) {
            Logger.getLogger(ReadSiifUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(cvsSplitBy);
                if (registrosProcesados >= 1) {
                    carga.setNumeroFila(registrosProcesados);
                    carga.setNumeroDocumento(Integer.parseInt(fields[0]));
                    carga.setFechaCarga(fechaCarga);
                    carga.setTipo(tipoArchivo);
                    carga.setDependencia(fields[5]);
                    carga.setRubro(fields[6]);
                    carga.setRecurso(fields[9]);                    
                    carga.setIdentificacion(fields[16]);
                    //carga.setNombreEmpresa(fields[17]);
                    carga.setSaldoPorUtilizar(this.bigDecimalFromString(fields[14]));
                    carga.setValorActual(this.bigDecimalFromString(fields[13]));
                    carga.setValorInicial(this.bigDecimalFromString(fields[11]));
                    carga.setTipoDocumentoSoporte(fields[32]);
                    carga.setNumeroDocumentoSoporte(fields[33]);
                    //carga.setObservaciones(fields[34]);
                    siifSrv.insertarAuxSiifCarga(carga);
                }
                registrosProcesados++;
            }
        } catch (IOException e) {
            Logger.getLogger(ReadSiifUtil.class.getName()).log(Level.SEVERE, null, e);
        } catch (CifraException ex) {
            Logger.getLogger(ReadSiifUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return registrosProcesados;
    }

    private BigDecimal bigDecimalFromString(String strNumero) {
        String str = strNumero.replaceAll(",", "");
        BigDecimal bigDecimal = new BigDecimal(str);
        return bigDecimal;
    }

}
