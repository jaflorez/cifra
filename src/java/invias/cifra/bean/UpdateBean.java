/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.bean;

import invias.cifra.exception.CifraException;
import invias.cifra.model.UpdateSrv;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author jaflorez
 */
@ManagedBean
@RequestScoped
public class UpdateBean {

    /**
     * Creates a new instance of UpdateBean
     */
    public UpdateBean() {

    }

    public void update() throws CifraException {
        UpdateSrv srv = new UpdateSrv();

        try {
            Date fecha = new Date();
            srv.updateEstadoContrato(fecha);
            srv.generarReporte(fecha);
            srv.updateEstadoReporte(fecha);
        } catch (CifraException ex) {
            Logger.getLogger(UpdateBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
