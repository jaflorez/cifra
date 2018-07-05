/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.dao;

import invias.cifra.exception.CifraException;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;

/**
 *
 * @author jaflorez
 */
public class UpdateDAO extends BaseDAO {

    public void updateEstadoContrato(java.util.Date fecha) throws CifraException  {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("sp_update_contrato");
        storedProcedure.registerStoredProcedureParameter("FECHA_CORTE", java.sql.Date.class, ParameterMode.IN);
        java.sql.Date sqlDate = new java.sql.Date(fecha.getTime());
        storedProcedure.setParameter("FECHA_CORTE", sqlDate);
        storedProcedure.execute();
        em.close();
    }

    /**
     * 
     * @param fecha 
     */
    public void generarReporte(java.util.Date fecha) throws CifraException  {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("sp_create_all_rpt_semana");
        storedProcedure.registerStoredProcedureParameter("P_FECHA_CORTE", java.sql.Date.class, ParameterMode.IN);
        java.sql.Date sqlDate = new java.sql.Date(fecha.getTime());
        storedProcedure.setParameter("P_FECHA_CORTE", sqlDate);
        storedProcedure.execute();
        em.close();
    }

    /**
     * Llamar al procedimiento almacenado que actualiza el estado de los
     * contratos
     * @param fecha
     */
    public void updateReportes(java.util.Date fecha) throws CifraException  {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("sp_update_all_rpt_semana");
        java.sql.Date sqlDate = new java.sql.Date(fecha.getTime());
        storedProcedure.registerStoredProcedureParameter("FECHA_REPORTE", Integer.class, ParameterMode.IN);
        storedProcedure.execute();
        em.close();
    }

}
