/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.model;

import invias.cifra.dao.TablesDAO;
import invias.cifra.entity.Actividad;
import invias.cifra.entity.Ajuste;
import invias.cifra.entity.DireccionTerritorial;
import invias.cifra.entity.Maquinaria;
import invias.cifra.entity.Programa;
import invias.cifra.entity.Proyecto;
import invias.cifra.entity.UnidadEjecutora;
import invias.cifra.exception.CifraException;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author jaflorez
 */
public class TablesSrv implements Serializable {

    private static final long serialVersionUID = 1L;

    /*Tabla de actividades*/
    public List<Actividad> getActividadLst() throws CifraException {
        TablesDAO tablesDAO = new TablesDAO();
        return tablesDAO.getActividadLst();
    }

    public void insertarActividad(Actividad actividad) throws CifraException {
        TablesDAO tablesDAO = new TablesDAO();
        tablesDAO.insertarActividad(actividad);
    }

    public void modificarActividad(Actividad actividad) throws CifraException {
        TablesDAO tablesDAO = new TablesDAO();
        tablesDAO.modificarActividad(actividad);
    }

    public void eliminarActividad(Actividad actividad) throws CifraException {
        TablesDAO tablesDAO = new TablesDAO();
        tablesDAO.eliminarActividad(actividad);
    }

    public List<Maquinaria> getMaquinariaLst() throws CifraException {
        TablesDAO tablesDAO = new TablesDAO();
        return tablesDAO.getMaquinariaLst();
    }

    public void insertarMaquinaria(Maquinaria maquinaria) throws CifraException {
        TablesDAO tablesDAO = new TablesDAO();
        tablesDAO.insertarMaquinaria(maquinaria);
    }

    public void modificarMaquinaria(Maquinaria maquinaria) throws CifraException {
        TablesDAO tablesDAO = new TablesDAO();
        tablesDAO.modificarMaquinaria(maquinaria);
    }

    public void eliminarMaquinaria(Maquinaria maquinaria) throws CifraException {
        TablesDAO tablesDAO = new TablesDAO();
        tablesDAO.eliminarMaquinaria(maquinaria);
    }

    public List<Programa> getProgramaLst() throws CifraException {
        TablesDAO tablesDAO = new TablesDAO();
        return tablesDAO.getProgramaLst();
    }

    public void insertarPrograma(Programa programa) throws CifraException {
        TablesDAO tablesDAO = new TablesDAO();
        tablesDAO.insertarPrograma(programa);
    }

    public List<Proyecto> getProyectoLst() throws CifraException {
        TablesDAO tablesDAO = new TablesDAO();
        return tablesDAO.getProyectoLst();
    }

    public void insertarProyecto(Proyecto proyecto) throws CifraException {
        TablesDAO tablesDAO = new TablesDAO();
        tablesDAO.insertarProyecto(proyecto);
    }

    public void modificarProyecto(Proyecto proyecto) throws CifraException {
        TablesDAO tablesDAO = new TablesDAO();
        tablesDAO.modificarProyecto(proyecto);
    }
    public void eliminarProyecto(Proyecto proyecto) throws CifraException {
        TablesDAO tablesDAO = new TablesDAO();
        tablesDAO.eliminarProyecto(proyecto);
    }
    /*Direccion territorial*/
    public List<DireccionTerritorial> getDireccionTerritorialLst() throws CifraException {
        TablesDAO tablesDAO = new TablesDAO();
        return tablesDAO.getDireccionTerritorialLst();
    }
    public void insertarDireccionTerritorial(DireccionTerritorial direccionTerritorial) throws CifraException {
        TablesDAO tablesDAO = new TablesDAO();
        tablesDAO.insertarDireccionTerritorial(direccionTerritorial);
    }
    public void eliminarDireccionTerritorial(DireccionTerritorial direccionTerritorial) throws CifraException {
        TablesDAO tablesDAO = new TablesDAO();
        tablesDAO.eliminarDireccionTerritorial(direccionTerritorial);
    }
    public void modificarDireccionTerritorial(DireccionTerritorial direccionTerritorial) throws CifraException {
        TablesDAO tablesDAO = new TablesDAO();
        tablesDAO.modificarDireccionTerritorial(direccionTerritorial);
    }
    
    /*Unidad Ejecutora*/
    
    public List<UnidadEjecutora> getUnidadEjecutoraLst() throws CifraException {
        TablesDAO tablesDAO = new TablesDAO();
        return tablesDAO.getUnidadEjecutoraLst();
    }
    public void insertarUnidadEjecutora(UnidadEjecutora unidadEjecutora) throws CifraException {
        TablesDAO tablesDAO = new TablesDAO();
        tablesDAO.insertarUnidadEjecutora(unidadEjecutora);
    }
    public void eliminarUnidadEjecutora(UnidadEjecutora unidadEjecutora) throws CifraException {
        TablesDAO tablesDAO = new TablesDAO();
        tablesDAO.eliminarUnidadEjecutora(unidadEjecutora);
    }
    public void modificarUnidadEjecutora(UnidadEjecutora unidadEjecutora) throws CifraException {
        TablesDAO tablesDAO = new TablesDAO();
        tablesDAO.modificarUnidadEjecutora(unidadEjecutora);
    }
    public List<Ajuste> getAjusteLst() throws CifraException {
        TablesDAO tablesDAO = new TablesDAO();
        return tablesDAO.getAjusteLst();
    }


}
