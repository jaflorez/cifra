/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.model;

import invias.cifra.dao.UpdateDAO;
import invias.cifra.exception.CifraException;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author jaflorez
 */
public class UpdateSrv implements Serializable {

    private static final long serialVersionUID = 1L;

    public void updateEstadoContrato(Date fechaCorte) throws CifraException {
        UpdateDAO updateDAO = new UpdateDAO();
        updateDAO.updateEstadoContrato(fechaCorte);
    }

    public void generarReporte(Date fechaCorte) throws CifraException {
        UpdateDAO updateDAO = new UpdateDAO();
        updateDAO.generarReporte(fechaCorte);

    }
    public void updateEstadoReporte(Date fechaCorte) throws CifraException {
        UpdateDAO updateDAO = new UpdateDAO();
        updateDAO.updateReportes(fechaCorte);
    }

}
