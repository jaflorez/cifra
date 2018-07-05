/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.bean;

import invias.cifra.entity.Contrato;
import invias.cifra.exception.CifraException;
import invias.cifra.model.ContratoSrv;
import invias.cifra.util.CifraUtil;
import java.math.BigDecimal;
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
public class ContratoViewBean extends BaseBean {

    /**
     * Creates a new instance of ContratoViewBean
     */
    
    Contrato contrato;

    private Integer sumaParticipacionObra;
    private BigDecimal sumaVigenciaObra;
    private BigDecimal sumaAdicionObra = new BigDecimal(0);
    private BigDecimal sumaProrrogaObra = new BigDecimal(0);
    private Integer sumaParticipacionInter;
    private BigDecimal sumaVigenciaInter;
    private BigDecimal sumaAdicionInter = new BigDecimal(0);
    private BigDecimal sumaProrrogaInter = new BigDecimal(0);

    public ContratoViewBean() {
        ContratoSrv contratoSrv = new ContratoSrv();
        CifraSessionBean sgbSes = CifraUtil.getSessionBean();
        if (sgbSes == null) {
            CifraUtil.redirectRootOut();
        }
        if(sgbSes.getCurUsuario().getIdentificador()<1){
            CifraUtil.redirectRootOut();
        }
        if (sgbSes.getContratoId() != null && sgbSes.getContratoId() > 0) {
            try {
                this.contrato = contratoSrv.loadContratoById(sgbSes.getContratoId());
            } catch (CifraException ex) {
                Logger.getLogger(ContratoViewBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

        }
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public Integer getSumaParticipacionObra() {
        return sumaParticipacionObra;
    }

    public void setSumaParticipacionObra(Integer sumaParticipacionObra) {
        this.sumaParticipacionObra = sumaParticipacionObra;
    }

    public BigDecimal getSumaVigenciaObra() {
        return sumaVigenciaObra;
    }

    public void setSumaVigenciaObra(BigDecimal sumaVigenciaObra) {
        this.sumaVigenciaObra = sumaVigenciaObra;
    }

    public BigDecimal getSumaAdicionObra() {
        return sumaAdicionObra;
    }

    public void setSumaAdicionObra(BigDecimal sumaAdicionObra) {
        this.sumaAdicionObra = sumaAdicionObra;
    }

    public BigDecimal getSumaProrrogaObra() {
        return sumaProrrogaObra;
    }

    public void setSumaProrrogaObra(BigDecimal sumaProrrogaObra) {
        this.sumaProrrogaObra = sumaProrrogaObra;
    }

    public Integer getSumaParticipacionInter() {
        return sumaParticipacionInter;
    }

    public void setSumaParticipacionInter(Integer sumaParticipacionInter) {
        this.sumaParticipacionInter = sumaParticipacionInter;
    }

    public BigDecimal getSumaVigenciaInter() {
        return sumaVigenciaInter;
    }

    public void setSumaVigenciaInter(BigDecimal sumaVigenciaInter) {
        this.sumaVigenciaInter = sumaVigenciaInter;
    }

    public BigDecimal getSumaAdicionInter() {
        return sumaAdicionInter;
    }

    public void setSumaAdicionInter(BigDecimal sumaAdicionInter) {
        this.sumaAdicionInter = sumaAdicionInter;
    }

    public BigDecimal getSumaProrrogaInter() {
        return sumaProrrogaInter;
    }

    public void setSumaProrrogaInter(BigDecimal sumaProrrogaInter) {
        this.sumaProrrogaInter = sumaProrrogaInter;
    }

}
