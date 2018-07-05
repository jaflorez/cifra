/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.dao;

import invias.cifra.entity.ContratoFinancieroObra;
import invias.cifra.entity.Estado;
import invias.cifra.entity.Reporte;
import invias.cifra.entity.ReporteArchivo;
import invias.cifra.exception.CifraException;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;

/**
 *
 * @author jaflorez
 */
public class ReporteDAO extends BaseDAO {

    public void insertar(Reporte reporte) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(reporte);
        em.getTransaction().commit();
        em.close();
        this.updateRptReporte(reporte.getIdentificador());
    }
    private void updateRptReporte(Integer idReporte) throws CifraException{
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("sp_rpt_reporte");
        storedProcedure.registerStoredProcedureParameter("ID_REPORTE", Integer.class, ParameterMode.IN);
        storedProcedure.setParameter("ID_REPORTE", idReporte);
        storedProcedure.execute();
        em.close();
    }
    public void eliminar(Reporte reporte) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Reporte current=null;
        if (!em.contains(reporte)) {
           current = em.merge(reporte);
        }
        em.remove(current);
        em.getTransaction().commit();
        em.close();
        this.updateRptReporte(reporte.getIdentificador());
    }

    public Reporte modificar(Reporte reporte) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        if (em.find(Reporte.class, reporte.getIdentificador()) == null) {
            throw new CifraException("Llave no encontrada: ");
        }
        em.getTransaction().begin();
        Reporte rp = em.merge(reporte);
        em.getTransaction().commit();
        em.close();
        this.updateRptReporte(reporte.getIdentificador());
        return rp;
    }

    public void terminarReporte(Integer reporteId, Integer estado) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Reporte rp = em.find(Reporte.class, reporteId);
        if (rp == null) {
            throw new CifraException("Llave no encontrada: ");
        }
        em.getTransaction().begin();
        rp.setFkEstado(new Estado(estado));
        rp.setFechaPresentacionReporte(new Date());
        em.getTransaction().commit();
        em.close();
        this.updateRptReporte(reporteId);
    }

    public void modificarEstado(Integer reporteId, Integer estado) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Reporte rp = em.find(Reporte.class, reporteId);
        if (rp == null) {
            throw new CifraException("Llave no encontrada: ");
        }
        em.getTransaction().begin();
        rp.setFkEstado(new Estado(estado));
        rp.setFechaPresentacionReporte(new Date());
        em.getTransaction().commit();
        em.close();
        this.updateRptReporte(reporteId);
    }

    public void modificarSIIF(Reporte reporte) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Reporte rp = em.find(Reporte.class, reporte.getIdentificador());
        if (rp == null) {
            throw new CifraException("Llave no encontrada: ");
        }
        em.getTransaction().begin();
        rp.setObrReporteSiif(reporte.getObrReporteSiif());
        rp.setIntReporteSiif(reporte.getIntReporteSiif());
        rp.setSiifFecha(reporte.getSiifFecha());
        em.getTransaction().commit();
        this.updateRptReporte(reporte.getIdentificador());
    }

    public void modificarRevision(Integer reporteId, String observacion, Integer usuarioId, Integer tipoUsr) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Reporte rp = em.find(Reporte.class, reporteId);
        if (rp == null) {
            throw new CifraException("Llave no encontrada: ");
        }
        em.getTransaction().begin();
        if (tipoUsr == 2) { // Usuario gestor
            rp.setGestorFechaRevision(new Date());
            rp.setGestorObservaciones(observacion);
            rp.setFkUsuarioGestor(usuarioId);
        }
        if (tipoUsr == 4) { // Usuario gestor
            rp.setSupervisorFechaRevision(new Date());
            rp.setSupervisorObservaciones(observacion);
            rp.setFkUsuarioSupervisor(usuarioId);
        }
        em.getTransaction().commit();
        em.close();
        this.updateRptReporte(reporteId);

    }

    public Reporte getLastReporte(Integer contratoId) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        TypedQuery<Reporte> query = null;
        String strSql;
        strSql = "select  c from Reporte  c  where c.fkContrato.identificador = :contrato_id ORDER BY c.reportedesde DESC";
        query = em.createQuery(strSql, Reporte.class);
        query.setParameter("contrato_id", contratoId);
        List<Reporte> lstFecRep = query.getResultList();
        if (lstFecRep.isEmpty()) {
            return null;
        }
        em.close();
        return lstFecRep.get(0);
    }

    public List<Reporte> getReporteLst(Integer contratoId)  throws CifraException {

        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        TypedQuery<Reporte> query = em.createQuery("SELECT c FROM Reporte c WHERE c.fkContrato.identificador = :contratoId ORDER BY c.numerosemana DESC", Reporte.class);
        query.setParameter("contratoId", contratoId);
        List<Reporte> results = query.getResultList();
        em.close();
        return results;

    }

    public List<Reporte> getReporteSiifLst(Date fechaDesde, Date fechaHasta, int unidaEjecutora) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        String sql;
        sql = "SELECT r FROM Reporte r,Contrato c  WHERE r.fkContrato.identificador = c.identificador ";
        sql += "and r.reportedesde >= :fechaDesde and r.reportehasta <= :fechaHasta ";
        sql += "and c.fkUnidadEjecutora.identificador = :idUnidadEjecutora ORDER BY r.reportedesde DESC ";
        TypedQuery<Reporte> query = em.createQuery(sql, Reporte.class);
        query.setParameter("fechaDesde", fechaDesde);
        query.setParameter("fechaHasta", fechaHasta);
        query.setParameter("idUnidadEjecutora", unidaEjecutora);
        List<Reporte> results = query.getResultList();
        em.close();
        return results;
    }

    public Reporte loadReporte(Integer reporteId) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Reporte p = (Reporte) em.find(Reporte.class, reporteId);
        return p;
    }

    public void cleanForUpdate(Integer reporteId) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("DELETE FROM ReporteActividad c WHERE c.fkReporte.identificador = :reporteId");
        query.setParameter("reporteId", reporteId);
        query.executeUpdate();

        query = em.createQuery("DELETE FROM ReporteMaquinaria c WHERE c.fkReporte.identificador = :reporteId");
        query.setParameter("reporteId", reporteId);
        query.executeUpdate();

        query = em.createQuery("DELETE FROM ReporteMateriales c WHERE c.fkReporte.identificador = :reporteId");
        query.setParameter("reporteId", reporteId);
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
        this.updateRptReporte(reporteId);
    }

    public ContratoFinancieroObra cargarContratoFinancieroById(Integer financieroId) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        ContratoFinancieroObra p = (ContratoFinancieroObra) em.find(ContratoFinancieroObra.class, financieroId);
        em.close();
        return p;
    }

    public void removeFirmaFromRepore(Integer reporte_id) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        TypedQuery<ReporteArchivo> query = em.createQuery("SELECT c FROM ReporteArchivo c where c.fkReporte.identificador = :reporte_id and c.tipoArchivo = 3", ReporteArchivo.class);
        query.setParameter("reporte_id", reporte_id);
        List<ReporteArchivo> results = query.getResultList();
        if (results.size() > 0) {
            for (ReporteArchivo ra : results) {
                em.getTransaction().begin();
                em.remove(ra);
                em.getTransaction().commit();
            }
        }
        em.close();

    }

    public ReporteArchivo loadFirmaFromRepore(Integer reporte_id) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();

        return null;
    }

    /**
     * Regresa el ultimo identificador de reporte
     *
     * @param id_contrato codigo del contrato
     * @return identificador
     */
    public Integer getLastIdReporteFromContrato(Integer id_contrato) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Query query = em.createQuery("Select MAX(c.identificador) FROM Reporte c where c.fkContrato.identificador = :id_contrato");
        query.setParameter("id_contrato", id_contrato);
        Integer result = (Integer) query.getSingleResult();
        em.close();
        return result;
        
    }

}
