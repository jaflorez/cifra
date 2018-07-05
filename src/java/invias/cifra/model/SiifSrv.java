/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.model;

import invias.cifra.dao.SiifDAO;
import invias.cifra.entity.AuxSiifCarga;
import invias.cifra.entity.CargaSiifDetail;
import invias.cifra.entity.CargaSiifHeader;
import invias.cifra.exception.CifraException;
import java.io.Serializable;

/**
 *
 * @author jaflorez
 */
public class SiifSrv  implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public void insertarHeader(CargaSiifHeader cargaSiifHeader) throws CifraException{
        SiifDAO siifDAO  = new SiifDAO();
        siifDAO.insertarSiifHeader(cargaSiifHeader);
    }
    public void insertarDetail(CargaSiifDetail siifDetail) throws CifraException{
        SiifDAO siifDAO  = new SiifDAO();
        siifDAO.insertarSiifDetail(siifDetail);
    }
    public Integer ultimoRegistroSiif(){
        return 20;
    }
    public void insertarAuxSiifCarga(AuxSiifCarga auxSiifCarga) throws CifraException{
        SiifDAO dAO = new SiifDAO();
        dAO.insertarAuxSiifCarga(auxSiifCarga);
    }
    public void eliminarRegistrosAuxSiifCarga() throws CifraException{
        SiifDAO dAO = new SiifDAO();
        dAO.eliminarAuxSiifCarga();
    }
    
    
    
    
}
