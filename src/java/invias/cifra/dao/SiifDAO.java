/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.dao;

import invias.cifra.entity.AuxSiifCarga;
import invias.cifra.entity.CargaSiifDetail;
import invias.cifra.entity.CargaSiifHeader;
import invias.cifra.exception.CifraException;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author jaflorez
 */
public class SiifDAO extends BaseDAO {

    public void insertarSiifHeader(CargaSiifHeader siifHeader) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(siifHeader);
        em.getTransaction().commit();
    }

    public void insertarSiifDetail(CargaSiifDetail siifDetail) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(siifDetail);
        em.getTransaction().commit();
    }

    public void updateCarga(CargaSiifHeader siifHeader) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Query qr = em.createNativeQuery("");
        //qr.setParameter(param, qr);
        qr.executeUpdate();
    }

    public void insertarAuxSiifCarga(AuxSiifCarga auxSiifCarga) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(auxSiifCarga);
        em.getTransaction().commit();
    }

    public void eliminarAuxSiifCarga() throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("Delete  from AuxSiifCarga c");
        int rows = query.executeUpdate();
        em.getTransaction().commit();
    }

}
