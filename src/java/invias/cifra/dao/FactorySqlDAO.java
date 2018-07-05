/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.dao;

import invias.cifra.dot.FechaReporteDOT;
import invias.cifra.dot.ReporteDOT;
import invias.cifra.dot.VigenciaDOT;
import invias.cifra.entity.Contrato;
import invias.cifra.entity.ContratoActividad;
import invias.cifra.entity.ContratoFinancieroObra;
import invias.cifra.entity.ContratoMaquinaria;
import invias.cifra.entity.ContratoTramo;
import invias.cifra.entity.Usuario;
import invias.cifra.entity.UsuarioContrato;
import invias.cifra.exception.CifraException;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author jaflorez
 */
public class FactorySqlDAO extends BaseDAO {

    public FactorySqlDAO() {

    }

    public List<FechaReporteDOT> fechaReporteLst(int idUnidadEjecutora, boolean allDate) throws CifraException {
        List<FechaReporteDOT> fechaLst = new ArrayList<>();
        String strSql;
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        Connection connection = em.unwrap(java.sql.Connection.class);

        em.getTransaction().commit();
        strSql = "SELECT DISTINCT  DATE_FORMAT(rp.REPORTEDESDE, '%Y-%m-%d') AS REPORTEDESDE,";
        strSql += "DATE_FORMAT(rp.REPORTEHASTA, '%Y-%m-%d') AS REPORTEHASTA,";
        strSql += "contrato.FK_UNIDAD_EJECUTORA ";
        strSql += "FROM (reporte rp  JOIN contrato ON ((contrato.IDENTIFICADOR = rp.FK_CONTRATO))) ";
        strSql += " where  contrato.FK_UNIDAD_EJECUTORA = ? ";

        if (!allDate) {
            strSql += " and (rp.OBR_REPORTE_SIIF IS NULL OR RP.INT_REPORTE_SIIF IS NULL) ";
        }
        strSql += "ORDER BY rp.reportedesde";
        CallableStatement cStm = null;
        try {
            cStm = connection.prepareCall(strSql);
            cStm.setInt(1, idUnidadEjecutora);
            ResultSet rs = cStm.executeQuery();
            int contador = 1;
            while (rs.next()) {
                fechaLst.add(new FechaReporteDOT(rs.getString("REPORTEDESDE"), rs.getString("REPORTEHASTA"), contador));
                contador++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FactorySqlDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (cStm != null) {
                try {
                    cStm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(FactorySqlDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        em.close();
        return fechaLst;
    }

    public List<VigenciaDOT> loadFacturadoVigenciaContratoLst(int id_contrato, int numeroReporte) throws CifraException {
        List<VigenciaDOT> vigenciaLst = new ArrayList<>();
        Integer numRptSalida = 0;
        String strSql;
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Connection connection = em.unwrap(java.sql.Connection.class);
        em.getTransaction().commit();
        CallableStatement cStm = null;
        strSql = "{call sp_create_vigencias_rp(?,?,?)}";
        try {
            cStm = connection.prepareCall(strSql);
            cStm.setInt(1, id_contrato);
            cStm.setInt(2, numeroReporte);
            cStm.registerOutParameter(3, java.sql.Types.INTEGER);
            cStm.executeUpdate();
            numRptSalida = cStm.getInt(3);
        } catch (SQLException ex) {
            Logger.getLogger(FactorySqlDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        strSql = "select ano,programado,avance_final from rpt_vigencia_reporte where numero = " + numRptSalida + " order by ano";
        cStm = null;
        try {
            cStm = connection.prepareCall(strSql);
            ResultSet rs = cStm.executeQuery();
            while (rs.next()) {
                VigenciaDOT vdot = new VigenciaDOT();
                vdot.setAño(rs.getInt("ano"));
                vdot.setVigencia(rs.getBigDecimal("programado"));
                vdot.setEjecutado(rs.getBigDecimal("avance_final"));
                vigenciaLst.add(vdot);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FactorySqlDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (cStm != null) {
                try {
                    cStm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(FactorySqlDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        em.close();
        return vigenciaLst;
    }

    public void loadDataReporteDOT(ReporteDOT rdot) throws CifraException {
        rdot.setInterSumaAdiciones(BigDecimal.ZERO);
        rdot.setInterSumaProrrogas(BigDecimal.ZERO);
        rdot.setObraFacturadoTotalAcumulado(BigDecimal.ZERO);
        rdot.setObraSumaProrrogas(BigDecimal.ZERO);
        rdot.setObraSumaAdiciones(BigDecimal.ZERO);
        rdot.setObraSumaVigencias(BigDecimal.ZERO);

        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Connection connection = em.unwrap(java.sql.Connection.class);
        em.getTransaction().commit();
        CallableStatement cStm = null;
        try {
            StringBuilder strSql = new StringBuilder("");
            strSql.append("select * from rpt_reporte where IDENTIFICADOR = " );
            strSql.append(rdot.getReporte().getIdentificador());
            cStm = connection.prepareCall(strSql.toString());
            ResultSet rs = cStm.executeQuery();
            if (rs.next()) {
                rdot.setObraTotalPuentes(rs.getBigDecimal("cont_numero_puente"));
                rdot.setObraTotalKmConstruir(rs.getBigDecimal("cont_km_construir"));
                rdot.setObraTotalKmMejorar(rs.getBigDecimal("cont_km_mejorar"));
                rdot.setObraTotalKmRehabilitar(rs.getBigDecimal("cont_km_rehabilitar"));
                rdot.setObraSumaProrrogas(rs.getBigDecimal("obr_prorroga"));
                rdot.setObraSumaAdiciones(rs.getBigDecimal("obr_adiciones"));
                rdot.setObraProgramaInversionTotal(rs.getBigDecimal("obr_prg_inv_total"));
                rdot.setObraProgramaInversionAcumulado(rs.getBigDecimal("obr_prg_inv_acumulado_programa_inversion"));
                rdot.setObraFacturadoTotalAcumulado(rs.getBigDecimal("obr_facturado_total"));
                rdot.setObraFacturadoInversionAcumulado(rs.getBigDecimal("obr_facturado_programa_inversion"));
                rdot.setInterSumaAdiciones(rs.getBigDecimal("int_adiciones"));
                rdot.setInterSumaProrrogas(rs.getBigDecimal("int_prorroga"));
                rdot.setInterInversionAcumulado(BigDecimal.ZERO);
                rdot.setInterFacturadoTotalAcumulado(rs.getBigDecimal("int_acumulado_facturado"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FactorySqlDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (cStm != null) {
                try {
                    cStm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(FactorySqlDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        em.close();

    }
    public void loadDataReporteDOTOld(ReporteDOT rdot) throws CifraException {
        rdot.setInterSumaAdiciones(BigDecimal.ZERO);
        rdot.setInterSumaProrrogas(BigDecimal.ZERO);
        rdot.setObraFacturadoTotalAcumulado(BigDecimal.ZERO);
        rdot.setObraSumaProrrogas(BigDecimal.ZERO);
        rdot.setObraSumaAdiciones(BigDecimal.ZERO);
        rdot.setObraSumaVigencias(BigDecimal.ZERO);

        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Connection connection = em.unwrap(java.sql.Connection.class);
        em.getTransaction().commit();
        CallableStatement cStm = null;
        try {
            StringBuilder strSql = new StringBuilder("");
            strSql.append("select rp_identificador,ct_identificador,obra_suma_prorrogas,");
            strSql.append("obra_suma_adiciones,obra_suma_programa_inversion,obra_suma_programa_inversion_acm,");
            strSql.append("obra_suma_inversion_acm,obra_suma_facturado_acm,inter_suma_prorrogas,");
            strSql.append("inter_suma_adiciones,inter_valor_inversion_acm,inter_suma_facturado_acm,");
            strSql.append("puente,km_rehabilitar,km_mejorar,0 as km_construir ");
            strSql.append("from view_reporte_resumen where rp_identificador = ?");
            cStm = connection.prepareCall(strSql.toString());
            cStm.setInt(1, rdot.getReporte().getIdentificador());
            ResultSet rs = cStm.executeQuery();
            if (rs.next()) {
                rdot.setObraTotalPuentes(rs.getBigDecimal("puente"));
                rdot.setObraTotalKmConstruir(rs.getBigDecimal("km_construir"));
                rdot.setObraTotalKmMejorar(rs.getBigDecimal("km_mejorar"));
                rdot.setObraTotalKmRehabilitar(rs.getBigDecimal("km_rehabilitar"));
                rdot.setInterSumaAdiciones(rs.getBigDecimal("inter_suma_adiciones"));
                rdot.setInterSumaProrrogas(rs.getBigDecimal("inter_suma_prorrogas"));
                rdot.setObraSumaProrrogas(rs.getBigDecimal("obra_suma_prorrogas"));
                rdot.setObraSumaAdiciones(rs.getBigDecimal("obra_suma_adiciones"));
                rdot.setObraProgramaInversionTotal(rs.getBigDecimal("obra_suma_programa_inversion"));
                rdot.setObraProgramaInversionAcumulado(rs.getBigDecimal("obra_suma_programa_inversion_acm"));
                rdot.setObraFacturadoTotalAcumulado(rs.getBigDecimal("obra_suma_facturado_acm"));
                rdot.setObraFacturadoInversionAcumulado(rs.getBigDecimal("obra_suma_inversion_acm"));
                rdot.setInterSumaProrrogas(rs.getBigDecimal("inter_suma_prorrogas"));
                rdot.setInterSumaAdiciones(rs.getBigDecimal("inter_suma_adiciones"));
                rdot.setInterInversionAcumulado(rs.getBigDecimal("inter_valor_inversion_acm"));
                rdot.setInterFacturadoTotalAcumulado(rs.getBigDecimal("inter_suma_facturado_acm"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FactorySqlDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (cStm != null) {
                try {
                    cStm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(FactorySqlDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    public String listaReporteConFinanciero(ContratoFinancieroObra cfo) throws CifraException {
        String strSql;
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Connection connection = em.unwrap(java.sql.Connection.class);
        em.getTransaction().commit();
        strSql = "select rp.NUMEROREPORTE from reporte rp where rp.fk_cto_financiero_obra = ? order by NUMEROREPORTE";
        CallableStatement cStm = null;
        String respuesta = "";
        try {
            cStm = connection.prepareCall(strSql);
            cStm.setInt(1, cfo.getIdentificador());
            ResultSet rs = cStm.executeQuery();
            while (rs.next()) {
                respuesta += rs.getString("NUMEROREPORTE") + ",";
            }
        } catch (SQLException ex) {
            Logger.getLogger(FactorySqlDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (cStm != null) {
                try {
                    cStm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(FactorySqlDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return respuesta;
    }

    public String listaReporteConActividad(ContratoActividad ca) throws CifraException {
        String strSql;
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Connection connection = em.unwrap(java.sql.Connection.class);
        em.getTransaction().commit();
        strSql = "select distinct rp.NUMEROREPORTE from reporte_actividad ra join reporte rp on  ra.fk_reporte = rp.identificador where ra.FK_CONTRATOACTIVIDAD = ?";

        CallableStatement cStm = null;
        String respuesta = "";
        try {
            cStm = connection.prepareCall(strSql);
            cStm.setInt(1, ca.getIdentificador());
            ResultSet rs = cStm.executeQuery();
            while (rs.next()) {
                respuesta += rs.getString("NUMEROREPORTE") + ",";
            }
        } catch (SQLException ex) {
            Logger.getLogger(FactorySqlDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (cStm != null) {
                try {
                    cStm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(FactorySqlDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return respuesta;
    }

    public String listaReporteConMaquinaria(ContratoMaquinaria cm) throws CifraException {
        String strSql;
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Connection connection = em.unwrap(java.sql.Connection.class);
        em.getTransaction().commit();
        strSql = "select distinct rp.NUMEROREPORTE from reporte_maquinaria rm join reporte rp on  rm.fk_reporte = rp.identificador  where rm.FK_CONTRATOMAQUINARIA = ?";

        CallableStatement cStm = null;
        String respuesta = "";
        try {
            cStm = connection.prepareCall(strSql);
            cStm.setInt(1, cm.getIdentificador());
            ResultSet rs = cStm.executeQuery();
            while (rs.next()) {
                respuesta += rs.getString("NUMEROREPORTE") + ",";
            }
        } catch (SQLException ex) {
            Logger.getLogger(FactorySqlDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (cStm != null) {
                try {
                    cStm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(FactorySqlDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return respuesta;
    }

    public String listaReporteConTramo(ContratoTramo ct) throws CifraException {
        String strSql;
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Connection connection = em.unwrap(java.sql.Connection.class);
        em.getTransaction().commit();
        strSql = "select distinct rp.NUMEROREPORTE from reporte ";
        strSql += "rp left join reporte_maquinaria rm on rm.fk_reporte = rp.identificador ";
        strSql += "left join reporte_actividad ra on ra.fk_reporte = rp.identificador ";
        strSql += "where ra.fk_contratotramo = ? or  rm.fk_contratotramo = ? ";
        CallableStatement cStm = null;
        String respuesta = "";
        try {
            cStm = connection.prepareCall(strSql);
            cStm.setInt(1, ct.getIdentificador());
            cStm.setInt(2, ct.getIdentificador());
            ResultSet rs = cStm.executeQuery();
            while (rs.next()) {
                respuesta += rs.getString("NUMEROREPORTE") + ",";
            }
        } catch (SQLException ex) {
            Logger.getLogger(FactorySqlDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (cStm != null) {
                try {
                    cStm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(FactorySqlDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return respuesta;
    }
    public List<Contrato> buscarContratoDireccionUsuario(Integer direccion, Integer usuario) throws CifraException {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        List<Contrato> contratoLst = new ArrayList<>();
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Connection connection = em.unwrap(java.sql.Connection.class);
        em.getTransaction().commit();
        String strSql = "select contrato.IDENTIFICADOR,contrato.CONT_OBRA_CODIGO,contrato.CONT_OBRA_VIGENCIA, contrato.OBJETO " ;
        strSql += " from contrato join unidad_ejecutora on unidad_ejecutora.IDENTIFICADOR = contrato.FK_UNIDAD_EJECUTORA ";
        strSql += " where unidad_ejecutora.DIRECCION = ? " ;
        strSql += " and contrato.IDENTIFICADOR not in(select FK_CONTRATO from usuario_contrato where usuario_contrato.FK_USUARIO = ?) ";
        strSql += " order by contrato.CONT_OBRA_VIGENCIA,contrato.CONT_OBRA_CODIGO ";
                CallableStatement cStm = null;
        String respuesta = "";
        try {
            cStm = connection.prepareCall(strSql);
            cStm.setInt(1, direccion);
            cStm.setInt(2, usuario);
            ResultSet rs = cStm.executeQuery();
            Contrato contrato;
            while (rs.next()) {
                contrato = new Contrato();
                contrato.setIdentificador(rs.getInt("IDENTIFICADOR"));
                contrato.setContObraCodigo(rs.getString("CONT_OBRA_CODIGO"));
                contrato.setContObraVigencia(rs.getInt("CONT_OBRA_VIGENCIA"));
                contrato.setObjeto(rs.getString("OBJETO"));
                contratoLst.add(contrato);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FactorySqlDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (cStm != null) {
                try {
                    cStm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(FactorySqlDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return contratoLst;
    }

}
