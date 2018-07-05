/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.dao;

import invias.cifra.entity.Contrato;
import invias.cifra.entity.ContratoActividad;
import invias.cifra.entity.ContratoFinancieroInter;
import invias.cifra.entity.ContratoFinancieroObra;
import invias.cifra.entity.ContratoMaquinaria;
import invias.cifra.entity.ContratoTramo;
import invias.cifra.exception.CifraException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
/**
 *
 * @author jaflorez
 */
public class ContratoDAO extends BaseDAO {
    public void insertar(Contrato contrato) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(contrato);
        em.getTransaction().commit();
        em.close();
    }

    public void modificar(Contrato contrato) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        if (em.find(Contrato.class, contrato.getIdentificador()) == null) {
            throw new CifraException("Llave no encontrada");
        }
        em.getTransaction().begin();
        Contrato cto = em.merge(contrato);
        em.getTransaction().commit();
        em.close();

    }

    public List<Contrato> buscar() throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        TypedQuery<Contrato> query = null;
        query = em.createNamedQuery("Contrato.findAll", Contrato.class);
        List<Contrato> lstContrato = query.getResultList();
        em.close();
        return lstContrato;
    }
    
    public List<Contrato> buscarContratoDireccion(Integer direccion) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        TypedQuery<Contrato> query = null;
        query = em.createQuery("SELECT c FROM Contrato c WHERE c.fkUnidadEjecutora.direccion = :direccion order by c.contObraVigencia,c.contObraCodigo", Contrato.class);
        query.setParameter("direccion", direccion);
        List<Contrato> lstContrato = query.getResultList();
        em.close();
        return lstContrato;
    }

    public List<Contrato> buscarContratoInterventor(Integer interventorId) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        TypedQuery<Contrato> query = null;
        query = em.createQuery("SELECT c FROM Contrato c WHERE c.fkUsuarioInterventor.identificador = :interventorId order by c.contObraVigencia,c.contObraCodigo", Contrato.class);
        query.setParameter("interventorId", interventorId);
        List<Contrato> lstContrato = query.getResultList();
        em.close();
        return lstContrato;
    }

    public List<Contrato> buscarContratoGestor(Integer gestorId) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        TypedQuery<Contrato> query = null;
        query = em.createQuery("SELECT c FROM Contrato c WHERE c.fkUsuarioGestor.identificador = :gestorId order by c.contObraVigencia,c.contObraCodigo", Contrato.class);
        query.setParameter("gestorId", gestorId);
        List<Contrato> lstContrato = query.getResultList();
        em.close();
        return lstContrato;
    }

    public List<Contrato> buscarContratoSupervisor(Integer supervisorId) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        TypedQuery<Contrato> query = null;
        query = em.createQuery("SELECT c FROM Contrato c WHERE c.fkUsuarioSupervisor.identificador = :supervisorId", Contrato.class);
        query.setParameter("supervisorId", supervisorId);
        List<Contrato> lstContrato = query.getResultList();
        em.close();
        return lstContrato;
    }
    public List<Contrato> buscarContratoConsulta(Integer usuarioId) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        TypedQuery<Contrato> query = null;
        query = em.createQuery("SELECT c FROM Contrato c, UsuarioContrato uc  WHERE uc.fkContrato.identificador = c.identificador AND  uc.fkUsuario.identificador = :usuarioId ", Contrato.class);
        query.setParameter("usuarioId", usuarioId);
        List<Contrato> lstContrato = query.getResultList();
        em.close();
        return lstContrato;
    }

    public Contrato loadContratoById(Integer contratoId) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Contrato p = (Contrato) em.find(Contrato.class, contratoId);
        em.close();
        return p;
    }

    public List<ContratoTramo> buscarContratoTramo(Integer contratoId) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        TypedQuery<ContratoTramo> query = em.createQuery("SELECT c FROM ContratoTramo c WHERE c.fkContrato.identificador = :contratoId ORDER BY c.numero", ContratoTramo.class);
        query.setParameter("contratoId", contratoId);
        List<ContratoTramo> results = query.getResultList();
        em.close();
        return results;
    }

    public List<ContratoActividad> buscarContratoActividad(Integer contratoId) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        TypedQuery<ContratoActividad> query = em.createQuery("SELECT c FROM ContratoActividad c WHERE c.fkContrato.identificador = :contratoId", ContratoActividad.class).setParameter("contratoId", contratoId);
        List<ContratoActividad> results = query.getResultList();
        em.close();
        return results;
    }

    public List<ContratoMaquinaria> buscarContratoMaquinaria(Integer contratoId) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        TypedQuery<ContratoMaquinaria> query = em.createQuery("SELECT c FROM ContratoMaquinaria c WHERE c.fkContrato.identificador = :contratoId", ContratoMaquinaria.class).setParameter("contratoId", contratoId);
        List<ContratoMaquinaria> results = query.getResultList();
        em.close();
        return results;
    }

    public List<ContratoFinancieroInter> buscarContratoFinancieroInter(Integer contratoId) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        TypedQuery<ContratoFinancieroInter> query = em.createQuery("SELECT c FROM ContratoFinancieroInter c WHERE c.fkContrato.identificador = :contratoId ORDER BY c.ano,c.corte DESC", ContratoFinancieroInter.class).setParameter("contratoId", contratoId);
        List<ContratoFinancieroInter> results = query.getResultList();
        em.close();
        return results;
    }

    public List<ContratoFinancieroObra> buscarContratoFinancieroObra(Integer contratoId) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        TypedQuery<ContratoFinancieroObra> query = em.createQuery("SELECT c FROM ContratoFinancieroObra c WHERE c.fkContrato.identificador = :contratoId ORDER BY c.ano,c.corte DESC", ContratoFinancieroObra.class).setParameter("contratoId", contratoId);
        List<ContratoFinancieroObra> results = query.getResultList();
        em.close();
        return results;
    }
    public ContratoTramo loadContratoTramoById(Integer contratoTramoId) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        ContratoTramo p = (ContratoTramo) em.find(ContratoTramo.class, contratoTramoId);
        em.close();
        return p;
    }
    public void modificarContratoTramo(ContratoTramo contratoTramo) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        if (em.find(ContratoTramo.class, contratoTramo.getIdentificador()) == null) {
            throw new CifraException("Llave no encontrada");
        }
        em.getTransaction().begin();
        ContratoTramo ct = em.merge(contratoTramo);
        em.getTransaction().commit();
        em.close();

    }


}
