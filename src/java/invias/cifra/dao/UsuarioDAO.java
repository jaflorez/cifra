/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.dao;

import invias.cifra.entity.Usuario;
import invias.cifra.entity.UsuarioContrato;
import invias.cifra.exception.CifraException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author jaflorez
 */
public class UsuarioDAO extends BaseDAO {
    public  List<UsuarioContrato>   usuarioContratoDireccionLst(Usuario usuario,Integer direccion) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        TypedQuery<UsuarioContrato> query = null;
        query = em.createQuery("SELECT u FROM UsuarioContrato u where u.fkUsuario.identificador = :usuarioId and u.fkContrato.fkUnidadEjecutora.direccion = :direccionId", UsuarioContrato.class);
        query.setParameter("usuarioId", usuario.getIdentificador());
        query.setParameter("direccionId", direccion);
        List<UsuarioContrato> lstUsuarioContrato = query.getResultList();
        return lstUsuarioContrato;
    }
    public List<Usuario> buscar() throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        TypedQuery<Usuario> query = null;
        query = em.createNamedQuery("Usuario.findAll", Usuario.class);
        List<Usuario> lstUsuario = query.getResultList();
        return lstUsuario;
    }

    public List<Usuario> buscarTipo(Integer tipo) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        TypedQuery<Usuario> query = null;
        query = em.createNamedQuery("Usuario.findByTipo", Usuario.class);
        query.setParameter("tipo", tipo);
        List<Usuario> lstUsuario = query.getResultList();
        return lstUsuario;
    }

    public void insertar(Usuario usuario) throws CifraException {
        try {
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            EntityManager em = factory.createEntityManager();
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        } catch (javax.persistence.PersistenceException ex) {
            throw new CifraException(ex.getMessage());

        }
    }

    public Usuario loadUsuario(Integer identificador) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Usuario u = (Usuario) em.find(Usuario.class, identificador);
        return u;
    }

    public Usuario loadUsuarioByDocumento(String numeroDocumento) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        TypedQuery<Usuario> query = null;
        query = em.createNamedQuery("Usuario.findByDocumento", Usuario.class);
        query.setParameter("documento", numeroDocumento);
        List<Usuario> lstUsuario = query.getResultList();
        if (lstUsuario == null || lstUsuario.size() == 0) {
            return null;
        } else {
            return lstUsuario.get(0);
        }

    }

    public Usuario loadUsuarioByCadenaVerificacion(String cadenaVerificacion) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        TypedQuery<Usuario> query = null;
        query = em.createNamedQuery("Usuario.findByCadenaverificacion", Usuario.class);
        query.setParameter("cadenaverificacion", cadenaVerificacion);
        List<Usuario> lstUsuario = query.getResultList();
        if (lstUsuario == null || lstUsuario.size() == 0) {
            return null;
        } else {
            return lstUsuario.get(0);
        }

    }


    public void updateUsuario(Usuario usuario) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        if (em.find(Usuario.class, usuario.getIdentificador()) == null) {
            throw new CifraException("Llave no encontrada: ");
        }
        em.getTransaction().begin();
        Usuario u = em.merge(usuario);
        em.getTransaction().commit();
    }


}
