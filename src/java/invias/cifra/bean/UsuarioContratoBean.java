/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.bean;

import invias.cifra.entity.Contrato;
import invias.cifra.entity.Usuario;
import invias.cifra.entity.UsuarioContrato;
import invias.cifra.exception.CifraException;
import invias.cifra.model.ContratoSrv;
import invias.cifra.model.UsuarioSrv;
import invias.cifra.util.CifraUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
public class UsuarioContratoBean extends BaseBean {

    /**
     * Creates a new instance of UsuarioContratoBean
     */
    private List<Contrato> contratoLst;
    private Usuario usuarioConsulta;

    public UsuarioContratoBean() {
        this.updateListas();
    }

    /**
     * Funcion para actualizar la informacion del usuario
     */
    private void updateListas() {
        try {
            CifraSessionBean sgbSes = CifraUtil.getSessionBean();
            UsuarioSrv usuarioSrv = new UsuarioSrv();
            this.usuarioConsulta = usuarioSrv.loadUsuario(sgbSes.getUsuarioId());
            ContratoSrv contratoSrv = new ContratoSrv();
            this.contratoLst = contratoSrv.listaContratoAdministradorUsuario(sgbSes.getCurUsuario().getFkUnidadEjecutora(), this.usuarioConsulta.getIdentificador());

        } catch (CifraException ex) {
            Logger.getLogger(UsuarioContratoBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void eliminarUsuarioContrato(Integer idContrato) {
        try {
            Iterator<UsuarioContrato> i = this.usuarioConsulta.getUsuarioContratoList().iterator();
            while (i.hasNext()) {
                if (i.next().getFkContrato().getIdentificador().equals(idContrato)) {
                    i.remove();
                }
            }
            UsuarioSrv usuarioSrv = new UsuarioSrv();
            usuarioSrv.updateUsuario(usuarioConsulta);
            
        } catch (CifraException ex) {
            Logger.getLogger(UsuarioContratoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.updateListas();

    }
    public void asignarUsuarioContrato(Integer idContrato) {
        try {
            UsuarioSrv usuarioSrv = new UsuarioSrv();
            UsuarioContrato usuarioContrato = new UsuarioContrato();
            usuarioContrato.setFkContrato(new Contrato(idContrato));
            usuarioContrato.setFkUsuario(this.usuarioConsulta);
            if(this.usuarioConsulta.getUsuarioContratoList() == null){
                this.usuarioConsulta.setUsuarioContratoList(new ArrayList<>());
            }
            this.usuarioConsulta.getUsuarioContratoList().add(usuarioContrato);
            usuarioSrv.updateUsuario(usuarioConsulta);
            this.updateListas();
        } catch (CifraException ex) {
            Logger.getLogger(UsuarioContratoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<Contrato> getContratoLst() {
        return contratoLst;
    }

    public void setContratoLst(List<Contrato> contratoLst) {
        this.contratoLst = contratoLst;
    }

    public Usuario getUsuarioConsulta() {
        return usuarioConsulta;
    }

    public void setUsuarioConsulta(Usuario usuarioConsulta) {
        this.usuarioConsulta = usuarioConsulta;
    }

}
