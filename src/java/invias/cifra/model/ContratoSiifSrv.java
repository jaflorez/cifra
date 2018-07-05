/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.model;

import invias.cifra.dao.FactorySqlDAO;
import invias.cifra.dot.FechaReporteDOT;
import invias.cifra.exception.CifraException;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author jaflorez
 */
public class ContratoSiifSrv implements Serializable {
    private static final long serialVersionUID = 1L;
    public List<FechaReporteDOT> listaFecha(boolean allDates,int unidadEjecutora) throws CifraException{
        FactorySqlDAO sqlDAO = new FactorySqlDAO();
        return sqlDAO.fechaReporteLst(unidadEjecutora, allDates);
    }
    
    
}
