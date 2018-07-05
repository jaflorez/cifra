/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.bean;

import invias.cifra.entity.Contrato;
import invias.cifra.entity.EstadoContrato;
import invias.cifra.exception.CifraException;
import invias.cifra.model.ContratoSrv;
import invias.cifra.util.CifraUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author jaflorez
 */
@ManagedBean
@ViewScoped
public class ContratoViewListBean extends BaseBean {

    /**
     * Creates a new instance of ContratoViewListBean
     */
    Collection<Contrato> contratoLst;
    Collection<Contrato> contratoFiltradoLst;
    Collection<EstadoContrato> estadoContratoLst;

    public ContratoViewListBean() {
        estadoContratoLst = new ArrayList<>();
        EstadoContrato ec = new EstadoContrato(1, "En Ejecución", 0);
        estadoContratoLst.add(ec);
        ec = new EstadoContrato(2, "Suspendido", 0);
        estadoContratoLst.add(ec);
        ec = new EstadoContrato(3, "Terminado", 0);
        estadoContratoLst.add(ec);
        
        
        
        
        CifraSessionBean sgbSes = CifraUtil.getSessionBean();
        ContratoSrv contratoSrv = new ContratoSrv();
        try {

            switch (sgbSes.getCurUsuario().getTipo()) {
                case 1: /*Administrador*/
                    this.contratoLst = contratoSrv.listaContratoAdministrador(sgbSes.getCurUsuario().getFkUnidadEjecutora());
                    break;

                case 2: /*Interventor*/
                    this.contratoLst = contratoSrv.listaContratoGestor(sgbSes.getCurUsuario().getIdentificador());
                    break;
                case 3:  /*Gestor*/
                    this.contratoLst = contratoSrv.listaContratoInterventor(sgbSes.getCurUsuario().getIdentificador());
                    break;
                case 4: /*Supervisor*/
                    this.contratoLst = contratoSrv.listaContratoSupervisor(sgbSes.getCurUsuario().getIdentificador());
                    break;
                case 5: /*Financiero*/
                    this.contratoLst = contratoSrv.listaContratoFinanciero(sgbSes.getCurUsuario().getIdentificador());
                    break;
                case 6: /*Consulta*/
                    this.contratoLst = contratoSrv.listaContratoConsulta(sgbSes.getCurUsuario().getIdentificador());
                    break;
                default:
                    this.contratoLst = new ArrayList<>();
                    break;
            }

        } catch (CifraException ex) {

            Logger.getLogger(ContratoViewBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Collection<Contrato> getContratoLst() {
        return contratoLst;
    }

    public void setContratoLst(Collection<Contrato> contratoLst) {
        this.contratoLst = contratoLst;
    }

    public Collection<Contrato> getContratoFiltradoLst() {
        return contratoFiltradoLst;
    }

    public void setContratoFiltradoLst(Collection<Contrato> contratoFiltradoLst) {
        this.contratoFiltradoLst = contratoFiltradoLst;
    }

    public Collection<EstadoContrato> getEstadoContratoLst() {
        return estadoContratoLst;
    }

    public void setEstadoContratoLst(Collection<EstadoContrato> estadoContratoLst) {
        this.estadoContratoLst = estadoContratoLst;
    }
    

}
