/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.model;

import invias.cifra.dao.TerceroDAO;
import invias.cifra.entity.Tercero;
import invias.cifra.exception.CifraException;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author jaflorez
 */
public class TerceroSrv implements Serializable{
    private static final long serialVersionUID = 1L;
    public List<Tercero>  getTereceroLst()throws CifraException{
        TerceroDAO terceroDAO = new TerceroDAO();
        return terceroDAO.buscar();
    }
    public void insertarTercero(Tercero tercero)throws CifraException{
        TerceroDAO terceroDAO = new TerceroDAO();
        terceroDAO.insertar(tercero);
    }
    public void eliminarTercero(Tercero tercero)throws CifraException{
         TerceroDAO terceroDAO = new TerceroDAO();
        terceroDAO.eliminar(tercero);
    }
    public void modificarTercero(Tercero tercero)throws CifraException{
         TerceroDAO terceroDAO = new TerceroDAO();
        terceroDAO.modificar(tercero);
    }
    
    
}
