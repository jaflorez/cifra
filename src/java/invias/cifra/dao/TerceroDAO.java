/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.dao;

import invias.cifra.entity.Tercero;
import invias.cifra.exception.CifraException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author jaflorez
 */
public class TerceroDAO extends BaseDAO {

    public List<Tercero> buscar() throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        TypedQuery<Tercero> query = null;
        query = em.createNamedQuery("Tercero.findAll", Tercero.class);
        List<Tercero> lstTercero = query.getResultList();
        return lstTercero;
    }

    public void insertar(Tercero tercero) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(tercero);
        em.getTransaction().commit();
    }

    public void eliminar(Tercero tercero) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Tercero terc;
        terc = em.find(Tercero.class, tercero.getIdentificador());
        em.getTransaction().begin();
        em.remove(terc);
        em.getTransaction().commit();
    }
    public void modificar(Tercero tercero) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        if (em.find(Tercero.class, tercero.getIdentificador()) == null) {
            throw new CifraException("Llave no encontrada: ");
        }
        em.getTransaction().begin();
        Tercero act = em.merge(tercero);
        em.getTransaction().commit();
    }

}
