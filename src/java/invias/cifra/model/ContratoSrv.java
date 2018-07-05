/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.model;

import invias.cifra.dao.ContratoDAO;
import invias.cifra.dao.FactorySqlDAO;
import invias.cifra.entity.Contrato;
import invias.cifra.entity.ContratoActividad;
import invias.cifra.entity.ContratoAjusteObra;
import invias.cifra.entity.ContratoFinancieroInter;
import invias.cifra.entity.ContratoFinancieroObra;
import invias.cifra.entity.ContratoMaquinaria;
import invias.cifra.entity.ContratoProrrogaObra;
import invias.cifra.entity.ContratoTramo;
import invias.cifra.entity.EstadoContrato;
import invias.cifra.exception.CifraException;
import invias.cifra.util.SuspencionUtil;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.time.DateUtils;


/**
 *
 * @author jaflorez
 */
public class ContratoSrv implements Serializable {

    private static final long serialVersionUID = 1L;
    public List<Contrato> listaContratoAdministradorUsuario(Integer direccion,Integer usuario) throws CifraException {
        FactorySqlDAO fsdao = new FactorySqlDAO();
        return fsdao.buscarContratoDireccionUsuario(direccion, usuario);
    
    }

    public List<Contrato> listaContratoAdministrador(Integer direccion) throws CifraException {
        ContratoDAO contratoDAO = new ContratoDAO();
        return contratoDAO.buscarContratoDireccion(direccion);

    }

    public List<Contrato> listaContratoInterventor(Integer interventorId) throws CifraException {
        ContratoDAO contratoDAO = new ContratoDAO();
        return contratoDAO.buscarContratoInterventor(interventorId);
    }

    public List<Contrato> listaContratoSupervisor(Integer supervisorId) throws CifraException {
        ContratoDAO contratoDAO = new ContratoDAO();
        return contratoDAO.buscarContratoSupervisor(supervisorId);
    }

    public List<Contrato> listaContratoGestor(Integer interventorId) throws CifraException {
        //TODO: Crear lista de gestor de contratos
        ContratoDAO contratoDAO = new ContratoDAO();
        return contratoDAO.buscarContratoGestor(interventorId);
    }

    public List<Contrato> listaContratoFinanciero(Integer interventorId) throws CifraException {
        //TODO: Crear lista de financiero
        ContratoDAO contratoDAO = new ContratoDAO();
        return contratoDAO.buscarContratoInterventor(interventorId);
    }

    public List<Contrato> listaContratoConsulta(Integer usuarioId) throws CifraException {
        ContratoDAO contratoDAO = new ContratoDAO();
        return contratoDAO.buscarContratoConsulta(usuarioId);
    }

    /*Validaciones para eliminar registros*/
    public String listaReporteConFinanciero(ContratoFinancieroObra cfo) throws CifraException {
        FactorySqlDAO fsdao = new FactorySqlDAO();
        return fsdao.listaReporteConFinanciero(cfo);
    }

    public String listaReporteConTramo(ContratoTramo ct) throws CifraException {
        FactorySqlDAO fsdao = new FactorySqlDAO();
        return fsdao.listaReporteConTramo(ct);
    }

    public String listaReporteConMaquinaria(ContratoMaquinaria cm) throws CifraException {
        FactorySqlDAO fsdao = new FactorySqlDAO();
        return fsdao.listaReporteConMaquinaria(cm);
    }

    public String listaReporteConActividad(ContratoActividad ca) throws CifraException {
        FactorySqlDAO fsdao = new FactorySqlDAO();
        return fsdao.listaReporteConActividad(ca);
    }

    /*Fin de validaciones para borrar*/
    public void insertarContrato(Contrato contrato) throws CifraException {
        ContratoDAO contratoDAO = new ContratoDAO();
        contratoDAO.insertar(contrato);
    }

    public void modificarContrato(Contrato contrato) throws CifraException {
        ContratoDAO cdao = new ContratoDAO();
        cdao.modificar(contrato);
    }

    public Contrato loadContratoById(Integer contratoId) throws CifraException {
        ContratoDAO contratoDAO = new ContratoDAO();
        return contratoDAO.loadContratoById(contratoId);
    }

    public List<ContratoTramo> buscarContratoTramo(Integer id_contrato) throws CifraException {
        ContratoDAO contratoDAO = new ContratoDAO();
        return contratoDAO.buscarContratoTramo(id_contrato);
    }

    public List<ContratoActividad> buscarContratoActividad(Integer id_contrato) throws CifraException {
        ContratoDAO contratoDAO = new ContratoDAO();
        return contratoDAO.buscarContratoActividad(id_contrato);

    }

    public List<ContratoMaquinaria> buscarContratoMaquinaria(Integer id_contrato) throws CifraException {
        ContratoDAO contratoDAO = new ContratoDAO();
        return contratoDAO.buscarContratoMaquinaria(id_contrato);
    }

    public List<ContratoFinancieroInter> buscarContratoFinancieroInter(Integer id_contrato) throws CifraException {
        ContratoDAO contratoDAO = new ContratoDAO();
        return contratoDAO.buscarContratoFinancieroInter(id_contrato);
    }

    public List<ContratoFinancieroObra> buscarContratoFinancieroObra(Integer id_contrato) throws CifraException {
        ContratoDAO contratoDAO = new ContratoDAO();
        return contratoDAO.buscarContratoFinancieroObra(id_contrato);
    }

    public ContratoTramo loadContratoTramoById(Integer contratoTramoId) throws CifraException {
        ContratoDAO contratoDAO = new ContratoDAO();
        return contratoDAO.loadContratoTramoById(contratoTramoId);
    }

    public void modificarContratoTramo(ContratoTramo contratoTramo) throws CifraException {
        ContratoDAO cdao = new ContratoDAO();
        cdao.modificarContratoTramo(contratoTramo);
    }

    /**
     *
     * @param contrato Contrato para calcularle el estado. El estado del
     * contrato se calcula teniendo en cuenta los siguientes criterios. Primero
     * la fecha final del contrato. Segunto si existen prorrogas, se debe
     * considerar la fecha de la ultma minuta. Esta es la prorroga que aplica
     * Tercero si existen modificaciones que sean de tipo suspencion,
     * reanuadacion o apliaciondelasuspencion se debe tomar la ultima fecha de
     * la minuta.
     *
     * @param fecha
     * @throws CifraException
     */
    public Date getFechaFinalContrato(Contrato contrato, Date fecha) throws CifraException {
        ContratoProrrogaObra prorrogaObra = null;
        if (contrato.getContratoProrrogaObraList() != null) {
            if (contrato.getContratoProrrogaObraList().size() > 0) {
                for (ContratoProrrogaObra cpo : contrato.getContratoProrrogaObraList()) {
                    if (prorrogaObra == null) {
                        prorrogaObra = cpo;
                    } else {
                        if (prorrogaObra.getFecha().compareTo(cpo.getFecha()) < 1) {
                            prorrogaObra = cpo;
                        }
                    }
                }
            }
        }
        ContratoAjusteObra ajusteObra = null;
        if (contrato.getContratoAjusteObraList() != null) {
            if (contrato.getContratoAjusteObraList().size() > 0) {
                for (ContratoAjusteObra cao : contrato.getContratoAjusteObraList()) {
                    if (cao.getFkAjuste().getIdentificador() == 3 /*Es un modificacion de suspencion*/
                            || cao.getFkAjuste().getIdentificador() == 4 /*Es una modificacion de reanudacion*/
                            || cao.getFkAjuste().getIdentificador() == 5 /*Es una modificacion de ampliacion de suspencion*/) {
                        if (ajusteObra == null) {
                            /*Es el primero*/
                            ajusteObra = cao;
                            /*Se asigan*/
                        } else {
                            if (ajusteObra.getFecha().compareTo(cao.getFecha()) < 1) {
                                /*Se escoje el mayor*/
                                ajusteObra = cao;
                            }
                        }
                    }
                }
            }
        }
        if (ajusteObra != null || prorrogaObra != null) {
            /*Existe una prorroga o una modificacion*/
            if (ajusteObra != null && prorrogaObra != null) {
                /*Existe prorroga y modificacion */
                if (ajusteObra.getFecha().compareTo(prorrogaObra.getFecha()) > 0) {
                    return ajusteObra.getFechaFinalContrato();
                } else {
                    return prorrogaObra.getFechaFinalContrato();
                }
            }
            if (ajusteObra != null) {
                return ajusteObra.getFechaFinalContrato();
            }
            if(prorrogaObra.getFechaFinalContrato()!=null){
                return prorrogaObra.getFechaFinalContrato();
            }
            else{
                return contrato.getContObraFechaTermi();
            }
            
        } else {/*No existe prorroga ni suspencion*/
            return contrato.getContObraFechaTermi();
        }
    }

    public EstadoContrato getEstadoContrato(Contrato contrato, Date fecha) throws CifraException {
        /* 1	En Ejecucion,2	Suspendido,3	Terminado*/
        
        Date fechaIn = DateUtils.truncate(fecha, Calendar.DATE);
        EstadoContrato estadoContrato = new EstadoContrato();
        estadoContrato.setEstado("En Ejecucion");
        estadoContrato.setIdentificador(1);
        Date fechaUtil = DateUtils.truncate(contrato.getContObraFechaTermiCalculada(), Calendar.DATE);
        if (fechaIn.compareTo(fechaUtil) > 0) {
            /*Contrato terminado*/
            estadoContrato.setEstado("Terminado");
            estadoContrato.setIdentificador(3);
            return estadoContrato;
        }
        /*Se revisan las Suspenciones*/
        SuspencionUtil supencionUtil = new SuspencionUtil();
        if (contrato.getContratoAjusteObraList() != null) {
            for (ContratoAjusteObra cao : contrato.getContratoAjusteObraList()) {
                if (cao.getFkAjuste().getIdentificador() == 3 || cao.getFkAjuste().getIdentificador() == 4 || cao.getFkAjuste().getIdentificador() == 5) {
                    /*Es una Suspencion o  una reanudacion o ampliacion de suspencion*/
                    supencionUtil.addAjuste(cao);
                }
            }
        }
        return supencionUtil.getEstadoContrato(fechaIn);
        

    }

}
