/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.dao;

import invias.cifra.entity.Actividad;
import invias.cifra.entity.Ajuste;
import invias.cifra.entity.DireccionTerritorial;
import invias.cifra.entity.Maquinaria;
import invias.cifra.entity.Programa;
import invias.cifra.entity.Proyecto;
import invias.cifra.entity.UnidadEjecutora;
import invias.cifra.exception.CifraException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author jaflorez
 */
public class TablesDAO extends BaseDAO {

    public List<Actividad> getActividadLst() throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        TypedQuery<Actividad> query = null;
        query = em.createNamedQuery("Actividad.findAll", Actividad.class);
        List<Actividad> lstActividad = query.getResultList();
        return lstActividad;
    }

    public void insertarActividad(Actividad actividad) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(actividad);
        em.getTransaction().commit();
    }

    public void modificarActividad(Actividad actividad) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        if (em.find(Actividad.class, actividad.getIdentificador()) == null) {
            throw new CifraException("Llave no encontrada: ");
        }
        em.getTransaction().begin();
        Actividad act = em.merge(actividad);
        em.getTransaction().commit();
    }

    public void eliminarActividad(Actividad actividad) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Actividad act;
        act = em.find(Actividad.class, actividad.getIdentificador());
        em.getTransaction().begin();
        em.remove(act);
        em.getTransaction().commit();
    }

    public List<Maquinaria> getMaquinariaLst() throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        TypedQuery<Maquinaria> query = null;
        query = em.createNamedQuery("Maquinaria.findAll", Maquinaria.class);
        List<Maquinaria> lstMaquinaria = query.getResultList();
        return lstMaquinaria;
    }

    public void insertarMaquinaria(Maquinaria maquinaria) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(maquinaria);
        em.getTransaction().commit();
    }

    public void modificarMaquinaria(Maquinaria maquinaria) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        if (em.find(Maquinaria.class, maquinaria.getIdentificador()) == null) {
            throw new CifraException("Llave no encontrada: ");
        }
        em.getTransaction().begin();
        Maquinaria maq = em.merge(maquinaria);
        em.getTransaction().commit();
    }

    public void eliminarMaquinaria(Maquinaria maquinaria) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Maquinaria act;
        act = em.find(Maquinaria.class, maquinaria.getIdentificador());
        em.getTransaction().begin();
        em.remove(act);
        em.getTransaction().commit();
    }

    public List<Programa> getProgramaLst() throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        TypedQuery<Programa> query = null;
        query = em.createNamedQuery("Programa.findAll", Programa.class);
        List<Programa> lstPrograma = query.getResultList();
        return lstPrograma;
    }

    public void insertarPrograma(Programa programa) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(programa);
        em.getTransaction().commit();
    }

    public List<Proyecto> getProyectoLst() throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        TypedQuery<Proyecto> query = null;
        query = em.createNamedQuery("Proyecto.findAll", Proyecto.class);
        List<Proyecto> lstProyecto = query.getResultList();
        return lstProyecto;
    }

    public void insertarProyecto(Proyecto proyecto) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(proyecto);
        em.getTransaction().commit();
    }

    public void modificarProyecto(Proyecto proyecto) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        if (em.find(Maquinaria.class, proyecto.getIdentificador()) == null) {
            throw new CifraException("Llave no encontrada:" + proyecto.getIdentificador());
        }
        em.getTransaction().begin();
        Proyecto pry = em.merge(proyecto);
        em.getTransaction().commit();
    }

    public void eliminarProyecto(Proyecto proyecto) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Proyecto pry;
        pry = em.find(Proyecto.class, proyecto.getIdentificador());
        if (pry == null) {
            throw new CifraException("Llave no encontrada no se puede eliminar el proyecto");
        }
        em.getTransaction().begin();
        em.remove(pry);
        em.getTransaction().commit();
    }

    /*Direccion Territorial*/
    public List<DireccionTerritorial> getDireccionTerritorialLst() throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        TypedQuery<DireccionTerritorial> query = null;
        query = em.createNamedQuery("DireccionTerritorial.findAll", DireccionTerritorial.class);
        List<DireccionTerritorial> lstDireccionTerritorial = query.getResultList();
        return lstDireccionTerritorial;

    }

    public void insertarDireccionTerritorial(DireccionTerritorial direccionTerritorial) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(direccionTerritorial);
        em.getTransaction().commit();
    }

    public void eliminarDireccionTerritorial(DireccionTerritorial direccionTerritorial) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        DireccionTerritorial dt;
        dt = em.find(DireccionTerritorial.class, direccionTerritorial.getIdentificador());
        em.getTransaction().begin();
        em.remove(dt);
        em.getTransaction().commit();
    }

    public void modificarDireccionTerritorial(DireccionTerritorial direccionTerritorial) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        if (em.find(DireccionTerritorial.class, direccionTerritorial.getIdentificador()) == null) {
            throw new CifraException("Llave no encontrada: ");
        }
        em.getTransaction().begin();
        DireccionTerritorial dt = em.merge(direccionTerritorial);
        em.getTransaction().commit();
    }

    /*Unidad Ejecutora*/
    public List<UnidadEjecutora> getUnidadEjecutoraLst() throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        TypedQuery<UnidadEjecutora> query = null;
        query = em.createNamedQuery("UnidadEjecutora.findAll", UnidadEjecutora.class);
        List<UnidadEjecutora> lstUnidadEjecutora = query.getResultList();
        return lstUnidadEjecutora;

    }

    public void insertarUnidadEjecutora(UnidadEjecutora unidadEjecutora) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(unidadEjecutora);
        em.getTransaction().commit();
    }

    public void eliminarUnidadEjecutora(UnidadEjecutora unidadEjecutora) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        DireccionTerritorial dt;
        dt = em.find(DireccionTerritorial.class, unidadEjecutora.getIdentificador());
        em.getTransaction().begin();
        em.remove(dt);
        em.getTransaction().commit();
    }

    public void modificarUnidadEjecutora(UnidadEjecutora unidadEjecutora) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        if (em.find(UnidadEjecutora.class, unidadEjecutora.getIdentificador()) == null) {
            throw new CifraException("Llave no encontrada: ");
        }
        em.getTransaction().begin();
        UnidadEjecutora ue = em.merge(unidadEjecutora);
        em.getTransaction().commit();
    }
    public List<Ajuste> getAjusteLst() throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        TypedQuery<Ajuste> query = null;
        query = em.createNamedQuery("Ajuste.findAll", Ajuste.class);
        List<Ajuste> lstAjuste = query.getResultList();
        return lstAjuste;
    }

}
