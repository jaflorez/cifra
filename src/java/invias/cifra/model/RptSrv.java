/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.model;

import invias.cifra.dao.RptDAO;
import invias.cifra.entity.RptConsolidado;
import invias.cifra.entity.RptReporte;
import invias.cifra.exception.CifraException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jaflorez
 */
public class RptSrv implements Serializable{
    
    private static final long serialVersionUID = 1L;
    public List<RptConsolidado> reporteConsolidado(Date fechaReporte,Integer idDivision) throws CifraException {
        
        RptDAO rdao = new RptDAO();
        Integer numeroReporte = rdao.buildReporteConsolidado(fechaReporte,idDivision);
        return rdao.getReporteLst(numeroReporte);
    }
    public RptReporte getReporteById(Integer idReporte) throws CifraException {
        RptDAO rdao = new RptDAO();
        return rdao.loadRptReporteById(idReporte);
    }
    public List<RptReporte> rptReporteContratoLst(Integer idReporte){
        RptDAO rdao = new RptDAO();
        return rdao.rptReporteContrato(idReporte);
    
    }
    
}
