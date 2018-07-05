/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.dao;

import invias.cifra.entity.RptConsolidado;
import invias.cifra.entity.RptReporte;
import invias.cifra.exception.CifraException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author jaflorez
 */
public class RptDAO extends BaseDAO {

    public Integer buildReporteConsolidado(Date fecha, Integer idDireccion) throws CifraException {
        Integer numRptSalida = 0;
        String strSql;
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Connection connection = em.unwrap(java.sql.Connection.class);
        em.getTransaction().commit();
        CallableStatement cStm = null;
        strSql = "{call sp_create_reporte_consolidado(?,?,?)}";
        try {
            cStm = connection.prepareCall(strSql);
            cStm.setDate(1, new java.sql.Date(fecha.getTime()));
            cStm.setInt(2, idDireccion);
            cStm.registerOutParameter(3, java.sql.Types.INTEGER);
            cStm.executeUpdate();
            numRptSalida = cStm.getInt(3);
        } catch (SQLException ex) {
            Logger.getLogger(FactorySqlDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        em.close();
        return numRptSalida;
    }

    public List<RptConsolidado> getReporteLst(Integer reporteId) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        TypedQuery<RptConsolidado> query = em.createNamedQuery("RptConsolidado.findByNumeroreporte", RptConsolidado.class);
        query.setParameter("numeroreporte", reporteId);
        List<RptConsolidado> lstRptConsolidado = query.getResultList();
        em.close();
        return lstRptConsolidado;
    }

    public RptReporte loadRptReporteById(Integer reporteId) throws CifraException  {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        RptReporte p = (RptReporte) em.find(RptReporte.class, reporteId);
        em.close();
        return p;
    }
    public List<RptReporte> rptReporteContrato(Integer contratoId){
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        TypedQuery<RptReporte> query = em.createQuery("SELECT r FROM RptReporte r WHERE r.fkContrato = :fkContrato AND r.estado = :estado  order by r.reportedesde ", RptReporte.class);
        query.setParameter("fkContrato", contratoId);
        query.setParameter("estado", "TERMINADO");
        List<RptReporte> lstRptConsolidado = query.getResultList();
        em.close();
        return lstRptConsolidado;
    }

}
