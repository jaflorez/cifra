/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.model;

import invias.cifra.dao.FactorySqlDAO;
import invias.cifra.dao.ReporteDAO;
import invias.cifra.dot.ReporteDOT;
import invias.cifra.entity.Contrato;
import invias.cifra.entity.ContratoFinancieroObra;
import invias.cifra.entity.Estado;
import invias.cifra.entity.Reporte;
import invias.cifra.entity.ReporteArchivo;
import invias.cifra.exception.CifraException;
import invias.cifra.util.CifraUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jaflorez
 */
public class ReporteSrv implements Serializable {

    private static final long serialVersionUID = 1L;

    //public List<ReporteEstado> re
    public void eliminarReporte(Reporte reporte) throws CifraException {
        ReporteDAO reporteDAO = new ReporteDAO();
        reporteDAO.eliminar(reporte);

    }

    public void insertarNuevoReporte(Contrato contrato) throws CifraException {
        ReporteDAO reporteDAO = new ReporteDAO();
        Reporte reporteLast = reporteDAO.getLastReporte(contrato.getIdentificador());
        Reporte reporteNew = new Reporte();
        reporteNew.setReportedesde(CifraUtil.fechaMasDias(reporteLast.getReportehasta(), 1));
        reporteNew.setReportehasta(CifraUtil.fechaMasDias(reporteLast.getReportehasta(), 7));
        reporteNew.setFechaLimitePresentacion(CifraUtil.fechaMasDias(reporteLast.getReportehasta(), 7));
        reporteNew.setNumeroreporte(reporteLast.getNumeroreporte() + 1);
        reporteNew.setNumerosemana(reporteLast.getNumerosemana() + 1);
        reporteNew.setFkEstado(new Estado(1));
        reporteNew.setFkEstadoContrato(1);
        reporteNew.setFkContrato(contrato);
        reporteDAO.insertar(reporteNew);

    }

    public Reporte actualizarReporte(Reporte reporte) throws CifraException {
        ReporteDAO reporteDAO = new ReporteDAO();
        return reporteDAO.modificar(reporte);
    }

    public void actualizarEstadoReporte(Integer contratoId, Integer estadoId) throws CifraException {
        ReporteDAO reporteDAO = new ReporteDAO();
        reporteDAO.modificarEstado(contratoId, estadoId);
    }

    public void actualizarInformacionSiif(Reporte reporte) throws CifraException {
        ReporteDAO reporteDAO = new ReporteDAO();
        reporteDAO.modificarSIIF(reporte);
    }

    public void actualizarRevision(Integer contratoId, String observacion, Integer usuarioId, Integer tipoUsr) throws CifraException {
        ReporteDAO reporteDAO = new ReporteDAO();
        reporteDAO.modificarRevision(contratoId, observacion, usuarioId, tipoUsr);

    }

    public void actualizarArchivoFirma(Reporte reporte, ReporteArchivo reporteArchivo) throws CifraException {
        ReporteDAO reporteDAO = new ReporteDAO();
        if (reporte.getReporteArchivoList() != null) {
            for (ReporteArchivo rpt : reporte.getReporteArchivoList()) {
                if (rpt.getTipoArchivo().equals(2)) {
                    reporte.getReporteArchivoList().remove(rpt);
                    break;
                }
            }

        } else {
            reporte.setReporteActividadList(new ArrayList<>());
        }
        reporte.getReporteArchivoList().add(reporteArchivo);
        reporteDAO.modificar(reporte);

    }

    public void insertarReporte(Reporte reporte) throws CifraException {
        ReporteDAO reporteDAO = new ReporteDAO();
        reporteDAO.insertar(reporte);
    }

    /**
     * Se inserta en reporte en la tabla de fecha de reporte
     *
     * @param contratoId
     * @return
     */
    public List<Reporte> consultarReporteContratoLst(Integer contratoId) throws CifraException {
        ReporteDAO reporteDAO = new ReporteDAO();
        return reporteDAO.getReporteLst(contratoId);
    }

    public List<Reporte> consultarReporteSiifLst(Date fechaDesde, Date fechaHasta, int unidaEjecutora) throws CifraException {
        ReporteDAO reporteDAO = new ReporteDAO();
        return reporteDAO.getReporteSiifLst(fechaDesde, fechaHasta, unidaEjecutora);
    }

    public void fillReportes(Integer contratoId, Date fecha) throws CifraException {
        ReporteDAO reporteDAO = new ReporteDAO();
        Reporte lastReporte = reporteDAO.getLastReporte(contratoId);
        if (lastReporte == null) {
            return;
        }
        if (lastReporte.getFechaLimitePresentacion() == null) {
            return;
        }
        if (lastReporte.getFechaLimitePresentacion().compareTo(fecha) < 0) {
            //Se debe construir los registros necesarios
            Reporte reporteNew = null;
            while (true) {
                reporteNew = new Reporte();
                reporteNew.setReportedesde(CifraUtil.fechaMasDias(lastReporte.getReportehasta(), 1));
                reporteNew.setReportehasta(CifraUtil.fechaMasDias(reporteNew.getReportedesde(), 6));
                reporteNew.setFechaLimitePresentacion(CifraUtil.fechaMasDias(reporteNew.getReportedesde(), 6));
                reporteNew.setFkContrato(new Contrato(contratoId));
                reporteNew.setNumeroreporte(lastReporte.getNumeroreporte() + 1);
                reporteNew.setNumerosemana(lastReporte.getNumerosemana() + 1);
                reporteNew.setFkEstado(new Estado(1));
                reporteNew.setFkEstadoContrato(1);
                reporteDAO.insertar(reporteNew);
                if (reporteNew.getFechaLimitePresentacion().compareTo(fecha) > 0) {
                    break;
                }
                lastReporte = this.duplicarReporte(reporteNew);
            }
        }
    }

    private Reporte duplicarReporte(Reporte src) {
        Reporte r = new Reporte();
        r.setFkContrato(new Contrato(src.getFkContrato().getIdentificador()));
        r.setFkEstado(new Estado(src.getFkEstado().getIdentificador()));
        r.setFkUsuarioreporto(src.getFkUsuarioreporto());
        r.setReportedesde(src.getReportedesde());
        r.setReportehasta(src.getReportehasta());
        r.setFechaLimitePresentacion(src.getFechaLimitePresentacion());
        r.setNumeroreporte(src.getNumeroreporte());
        r.setNumerosemana(src.getNumerosemana());
        return r;
    }

    public Reporte consultarReporte(Integer reporteId) throws CifraException {
        ReporteDAO reporteDAO = new ReporteDAO();
        return reporteDAO.loadReporte(reporteId);
    }

    /**
     *
     * @param reporteId
     * @return Retorna un objeto de Tipo ReporteDOT, (Reporte Extendido)
     * @throws CifraException
     */
    public ReporteDOT consultarReporteDOT(Integer reporteId) throws CifraException {
        ReporteDAO reporteDAO = new ReporteDAO();
        Reporte reporte = reporteDAO.loadReporte(reporteId);
        ReporteDOT reporteDOT = new ReporteDOT(reporte);
        if (reporte.getFkCtoFinancieroObra() != null) {
            ContratoFinancieroObra cfo;
            cfo = reporteDAO.cargarContratoFinancieroById(reporte.getFkCtoFinancieroObra());
            if (cfo != null) {
                reporteDOT.setObraMesFacturado(cfo.getAno().toString() + "-" + cfo.getCorte().toString());
            } else {
                reporteDOT.setObraMesFacturado("?-?");
            }

        } else {
            reporteDOT.setObraMesFacturado("?-?");
        }

        FactorySqlDAO fsdao = new FactorySqlDAO();
        fsdao.loadDataReporteDOT(reporteDOT);
        reporteDOT.setVigenciaList(fsdao.loadFacturadoVigenciaContratoLst(reporte.getFkContrato().getIdentificador(), reporte.getNumeroreporte()));
        /*Llenar los datos de actividades*/
        int numActividades;
        int numColumnas;
        numActividades = reporte.getFkContrato().getContratoActividadList().size(); // Se define el numero de filas igual a las actividades del contrato
        numColumnas = reporte.getFkContrato().getContratoTramoList().size(); // Se define el numero de columnas igual a  numero de tramos
        return reporteDOT;
    }

    private float[][] getMatrizFloat(int filas, int columnas) {
        float matActividad[][];
        matActividad = new float[filas][];
        for (int i = 0; i < filas; i++) {
            matActividad[i] = new float[columnas];
        }
        return matActividad;
    }

    public Integer getLastIdReporteFromContrato(Integer id_contrato) throws CifraException {
        ReporteDAO reporteDAO = new ReporteDAO();
        return reporteDAO.getLastIdReporteFromContrato(id_contrato);
    }

}
