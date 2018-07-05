/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jaflorez
 */
@Entity
@Table(name = "rpt_reporte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RptReporte.findAll", query = "SELECT r FROM RptReporte r")
    , @NamedQuery(name = "RptReporte.findByIdentificador", query = "SELECT r FROM RptReporte r WHERE r.identificador = :identificador")
    , @NamedQuery(name = "RptReporte.findByFkContrato", query = "SELECT r FROM RptReporte r WHERE r.fkContrato = :fkContrato order by r.reportedesde")
    , @NamedQuery(name = "RptReporte.findByNumeroreporte", query = "SELECT r FROM RptReporte r WHERE r.numeroreporte = :numeroreporte")
    , @NamedQuery(name = "RptReporte.findByNumerosemana", query = "SELECT r FROM RptReporte r WHERE r.numerosemana = :numerosemana")
    , @NamedQuery(name = "RptReporte.findByReportedesde", query = "SELECT r FROM RptReporte r WHERE r.reportedesde = :reportedesde")
    , @NamedQuery(name = "RptReporte.findByReportehasta", query = "SELECT r FROM RptReporte r WHERE r.reportehasta = :reportehasta")
    , @NamedQuery(name = "RptReporte.findByContProyecto", query = "SELECT r FROM RptReporte r WHERE r.contProyecto = :contProyecto")
    , @NamedQuery(name = "RptReporte.findByContUnidadEjecutora", query = "SELECT r FROM RptReporte r WHERE r.contUnidadEjecutora = :contUnidadEjecutora")
    , @NamedQuery(name = "RptReporte.findByContDireccionTerritorial", query = "SELECT r FROM RptReporte r WHERE r.contDireccionTerritorial = :contDireccionTerritorial")
    , @NamedQuery(name = "RptReporte.findByContSupervisor", query = "SELECT r FROM RptReporte r WHERE r.contSupervisor = :contSupervisor")
    , @NamedQuery(name = "RptReporte.findByContGestor", query = "SELECT r FROM RptReporte r WHERE r.contGestor = :contGestor")
    , @NamedQuery(name = "RptReporte.findByContInterventor", query = "SELECT r FROM RptReporte r WHERE r.contInterventor = :contInterventor")
    , @NamedQuery(name = "RptReporte.findByContObjeto", query = "SELECT r FROM RptReporte r WHERE r.contObjeto = :contObjeto")
    , @NamedQuery(name = "RptReporte.findByContKmMejorar", query = "SELECT r FROM RptReporte r WHERE r.contKmMejorar = :contKmMejorar")
    , @NamedQuery(name = "RptReporte.findByContKmRehabilitar", query = "SELECT r FROM RptReporte r WHERE r.contKmRehabilitar = :contKmRehabilitar")
    , @NamedQuery(name = "RptReporte.findByContKmConstruir", query = "SELECT r FROM RptReporte r WHERE r.contKmConstruir = :contKmConstruir")
    , @NamedQuery(name = "RptReporte.findByContNumeroPuente", query = "SELECT r FROM RptReporte r WHERE r.contNumeroPuente = :contNumeroPuente")
    , @NamedQuery(name = "RptReporte.findByObrContratistaNombre", query = "SELECT r FROM RptReporte r WHERE r.obrContratistaNombre = :obrContratistaNombre")
    , @NamedQuery(name = "RptReporte.findByObrContratistaNit", query = "SELECT r FROM RptReporte r WHERE r.obrContratistaNit = :obrContratistaNit")
    , @NamedQuery(name = "RptReporte.findByObrPrgInvValor", query = "SELECT r FROM RptReporte r WHERE r.obrPrgInvValor = :obrPrgInvValor")
    , @NamedQuery(name = "RptReporte.findByObrPrgInvMes", query = "SELECT r FROM RptReporte r WHERE r.obrPrgInvMes = :obrPrgInvMes")
    , @NamedQuery(name = "RptReporte.findByObrPrgInvAno", query = "SELECT r FROM RptReporte r WHERE r.obrPrgInvAno = :obrPrgInvAno")
    , @NamedQuery(name = "RptReporte.findByObrPrgInvTotal", query = "SELECT r FROM RptReporte r WHERE r.obrPrgInvTotal = :obrPrgInvTotal")
    , @NamedQuery(name = "RptReporte.findByObrPrgInvAcumuladoProgramaInversion", query = "SELECT r FROM RptReporte r WHERE r.obrPrgInvAcumuladoProgramaInversion = :obrPrgInvAcumuladoProgramaInversion")
    , @NamedQuery(name = "RptReporte.findByObrPrgInvAcumuladoFechaReporte", query = "SELECT r FROM RptReporte r WHERE r.obrPrgInvAcumuladoFechaReporte = :obrPrgInvAcumuladoFechaReporte")
    , @NamedQuery(name = "RptReporte.findByObrNumeroContrato", query = "SELECT r FROM RptReporte r WHERE r.obrNumeroContrato = :obrNumeroContrato")
    , @NamedQuery(name = "RptReporte.findByObrValorInicial", query = "SELECT r FROM RptReporte r WHERE r.obrValorInicial = :obrValorInicial")
    , @NamedQuery(name = "RptReporte.findByObrAdiciones", query = "SELECT r FROM RptReporte r WHERE r.obrAdiciones = :obrAdiciones")
    , @NamedQuery(name = "RptReporte.findByObrPlazoInicial", query = "SELECT r FROM RptReporte r WHERE r.obrPlazoInicial = :obrPlazoInicial")
    , @NamedQuery(name = "RptReporte.findByObrProrroga", query = "SELECT r FROM RptReporte r WHERE r.obrProrroga = :obrProrroga")
    , @NamedQuery(name = "RptReporte.findByObrPresentoFactura", query = "SELECT r FROM RptReporte r WHERE r.obrPresentoFactura = :obrPresentoFactura")
    , @NamedQuery(name = "RptReporte.findByObrValorInversion", query = "SELECT r FROM RptReporte r WHERE r.obrValorInversion = :obrValorInversion")
    , @NamedQuery(name = "RptReporte.findByObrValorFacturado", query = "SELECT r FROM RptReporte r WHERE r.obrValorFacturado = :obrValorFacturado")
    , @NamedQuery(name = "RptReporte.findByObrFechaFactura", query = "SELECT r FROM RptReporte r WHERE r.obrFechaFactura = :obrFechaFactura")
    , @NamedQuery(name = "RptReporte.findByObrAvanceFisico", query = "SELECT r FROM RptReporte r WHERE r.obrAvanceFisico = :obrAvanceFisico")
    , @NamedQuery(name = "RptReporte.findByObrFacturadoProgramaInversion", query = "SELECT r FROM RptReporte r WHERE r.obrFacturadoProgramaInversion = :obrFacturadoProgramaInversion")
    , @NamedQuery(name = "RptReporte.findByObrFacturadoTotal", query = "SELECT r FROM RptReporte r WHERE r.obrFacturadoTotal = :obrFacturadoTotal")
    , @NamedQuery(name = "RptReporte.findByObrValUltFactura", query = "SELECT r FROM RptReporte r WHERE r.obrValUltFactura = :obrValUltFactura")
    , @NamedQuery(name = "RptReporte.findByObrFechaUltFactura", query = "SELECT r FROM RptReporte r WHERE r.obrFechaUltFactura = :obrFechaUltFactura")
    , @NamedQuery(name = "RptReporte.findByObrSiifValor", query = "SELECT r FROM RptReporte r WHERE r.obrSiifValor = :obrSiifValor")
    , @NamedQuery(name = "RptReporte.findByObrSiifFecha", query = "SELECT r FROM RptReporte r WHERE r.obrSiifFecha = :obrSiifFecha")
    , @NamedQuery(name = "RptReporte.findByIntContratistaNombre", query = "SELECT r FROM RptReporte r WHERE r.intContratistaNombre = :intContratistaNombre")
    , @NamedQuery(name = "RptReporte.findByIntContratistaNit", query = "SELECT r FROM RptReporte r WHERE r.intContratistaNit = :intContratistaNit")
    , @NamedQuery(name = "RptReporte.findByIntNumeroContrato", query = "SELECT r FROM RptReporte r WHERE r.intNumeroContrato = :intNumeroContrato")
    , @NamedQuery(name = "RptReporte.findByIntPresentoFactura", query = "SELECT r FROM RptReporte r WHERE r.intPresentoFactura = :intPresentoFactura")
    , @NamedQuery(name = "RptReporte.findByIntValorInicial", query = "SELECT r FROM RptReporte r WHERE r.intValorInicial = :intValorInicial")
    , @NamedQuery(name = "RptReporte.findByIntAdiciones", query = "SELECT r FROM RptReporte r WHERE r.intAdiciones = :intAdiciones")
    , @NamedQuery(name = "RptReporte.findByIntPlazoInicial", query = "SELECT r FROM RptReporte r WHERE r.intPlazoInicial = :intPlazoInicial")
    , @NamedQuery(name = "RptReporte.findByIntProrroga", query = "SELECT r FROM RptReporte r WHERE r.intProrroga = :intProrroga")
    , @NamedQuery(name = "RptReporte.findByIntValorFactura", query = "SELECT r FROM RptReporte r WHERE r.intValorFactura = :intValorFactura")
    , @NamedQuery(name = "RptReporte.findByIntFechaFactura", query = "SELECT r FROM RptReporte r WHERE r.intFechaFactura = :intFechaFactura")
    , @NamedQuery(name = "RptReporte.findByIntFacturadoProgramadoNumMes", query = "SELECT r FROM RptReporte r WHERE r.intFacturadoProgramadoNumMes = :intFacturadoProgramadoNumMes")
    , @NamedQuery(name = "RptReporte.findByIntFacturadoProgramadoFechaReporte", query = "SELECT r FROM RptReporte r WHERE r.intFacturadoProgramadoFechaReporte = :intFacturadoProgramadoFechaReporte")
    , @NamedQuery(name = "RptReporte.findByIntFacturado", query = "SELECT r FROM RptReporte r WHERE r.intFacturado = :intFacturado")
    , @NamedQuery(name = "RptReporte.findByIntMesFacturado", query = "SELECT r FROM RptReporte r WHERE r.intMesFacturado = :intMesFacturado")
    , @NamedQuery(name = "RptReporte.findByIntAcumuladoFacturado", query = "SELECT r FROM RptReporte r WHERE r.intAcumuladoFacturado = :intAcumuladoFacturado")
    , @NamedQuery(name = "RptReporte.findByIntSiifValor", query = "SELECT r FROM RptReporte r WHERE r.intSiifValor = :intSiifValor")
    , @NamedQuery(name = "RptReporte.findByIntSiifFecha", query = "SELECT r FROM RptReporte r WHERE r.intSiifFecha = :intSiifFecha")
    , @NamedQuery(name = "RptReporte.findByActividadesRealizadas", query = "SELECT r FROM RptReporte r WHERE r.actividadesRealizadas = :actividadesRealizadas")
    , @NamedQuery(name = "RptReporte.findByActividadesProxima", query = "SELECT r FROM RptReporte r WHERE r.actividadesProxima = :actividadesProxima")
    , @NamedQuery(name = "RptReporte.findByResumen", query = "SELECT r FROM RptReporte r WHERE r.resumen = :resumen")
    , @NamedQuery(name = "RptReporte.findByActividadARealizar", query = "SELECT r FROM RptReporte r WHERE r.actividadARealizar = :actividadARealizar")
    , @NamedQuery(name = "RptReporte.findByActividadRealizada", query = "SELECT r FROM RptReporte r WHERE r.actividadRealizada = :actividadRealizada")
    , @NamedQuery(name = "RptReporte.findByGesoSede", query = "SELECT r FROM RptReporte r WHERE r.gesoSede = :gesoSede")
    , @NamedQuery(name = "RptReporte.findByGesoNocapacitaciones", query = "SELECT r FROM RptReporte r WHERE r.gesoNocapacitaciones = :gesoNocapacitaciones")
    , @NamedQuery(name = "RptReporte.findByGesoNosocializaciones", query = "SELECT r FROM RptReporte r WHERE r.gesoNosocializaciones = :gesoNosocializaciones")
    , @NamedQuery(name = "RptReporte.findByGesoEmpindirgen", query = "SELECT r FROM RptReporte r WHERE r.gesoEmpindirgen = :gesoEmpindirgen")
    , @NamedQuery(name = "RptReporte.findByGesoEmpdirgen", query = "SELECT r FROM RptReporte r WHERE r.gesoEmpdirgen = :gesoEmpdirgen")
    , @NamedQuery(name = "RptReporte.findByGesoEmpgen", query = "SELECT r FROM RptReporte r WHERE r.gesoEmpgen = :gesoEmpgen")
    , @NamedQuery(name = "RptReporte.findByGesoObservaciones", query = "SELECT r FROM RptReporte r WHERE r.gesoObservaciones = :gesoObservaciones")
    , @NamedQuery(name = "RptReporte.findByGeprObservaciones", query = "SELECT r FROM RptReporte r WHERE r.geprObservaciones = :geprObservaciones")
    , @NamedQuery(name = "RptReporte.findByGeprPredadquiridos", query = "SELECT r FROM RptReporte r WHERE r.geprPredadquiridos = :geprPredadquiridos")
    , @NamedQuery(name = "RptReporte.findByGeprAvaluos", query = "SELECT r FROM RptReporte r WHERE r.geprAvaluos = :geprAvaluos")
    , @NamedQuery(name = "RptReporte.findByGeprFichasaprob", query = "SELECT r FROM RptReporte r WHERE r.geprFichasaprob = :geprFichasaprob")
    , @NamedQuery(name = "RptReporte.findByGeprPredadquirir", query = "SELECT r FROM RptReporte r WHERE r.geprPredadquirir = :geprPredadquirir")
    , @NamedQuery(name = "RptReporte.findByPagaObservaciones", query = "SELECT r FROM RptReporte r WHERE r.pagaObservaciones = :pagaObservaciones")
    , @NamedQuery(name = "RptReporte.findByPagaEstado", query = "SELECT r FROM RptReporte r WHERE r.pagaEstado = :pagaEstado")
    , @NamedQuery(name = "RptReporte.findByAmbObservaciones", query = "SELECT r FROM RptReporte r WHERE r.ambObservaciones = :ambObservaciones")
    , @NamedQuery(name = "RptReporte.findByAmbEmiciones", query = "SELECT r FROM RptReporte r WHERE r.ambEmiciones = :ambEmiciones")
    , @NamedQuery(name = "RptReporte.findByAmbVertimentos", query = "SELECT r FROM RptReporte r WHERE r.ambVertimentos = :ambVertimentos")
    , @NamedQuery(name = "RptReporte.findByAmbOcupacioncauces", query = "SELECT r FROM RptReporte r WHERE r.ambOcupacioncauces = :ambOcupacioncauces")
    , @NamedQuery(name = "RptReporte.findByAmbConcecionaguas", query = "SELECT r FROM RptReporte r WHERE r.ambConcecionaguas = :ambConcecionaguas")
    , @NamedQuery(name = "RptReporte.findByAmbAprovforestal", query = "SELECT r FROM RptReporte r WHERE r.ambAprovforestal = :ambAprovforestal")
    , @NamedQuery(name = "RptReporte.findByAmbLicenciambiental", query = "SELECT r FROM RptReporte r WHERE r.ambLicenciambiental = :ambLicenciambiental")
    , @NamedQuery(name = "RptReporte.findByAmbBotaderos", query = "SELECT r FROM RptReporte r WHERE r.ambBotaderos = :ambBotaderos")
    , @NamedQuery(name = "RptReporte.findByFtmatObservaciones", query = "SELECT r FROM RptReporte r WHERE r.ftmatObservaciones = :ftmatObservaciones")
    , @NamedQuery(name = "RptReporte.findByFechaLimitePresentacion", query = "SELECT r FROM RptReporte r WHERE r.fechaLimitePresentacion = :fechaLimitePresentacion")
    , @NamedQuery(name = "RptReporte.findByFechaPresentacionReporte", query = "SELECT r FROM RptReporte r WHERE r.fechaPresentacionReporte = :fechaPresentacionReporte")
    , @NamedQuery(name = "RptReporte.findByFechaRegistroInicial", query = "SELECT r FROM RptReporte r WHERE r.fechaRegistroInicial = :fechaRegistroInicial")
    , @NamedQuery(name = "RptReporte.findBySupervisorFechaRevision", query = "SELECT r FROM RptReporte r WHERE r.supervisorFechaRevision = :supervisorFechaRevision")
    , @NamedQuery(name = "RptReporte.findBySupervisorObservaciones", query = "SELECT r FROM RptReporte r WHERE r.supervisorObservaciones = :supervisorObservaciones")
    , @NamedQuery(name = "RptReporte.findByGestorObservaciones", query = "SELECT r FROM RptReporte r WHERE r.gestorObservaciones = :gestorObservaciones")
    , @NamedQuery(name = "RptReporte.findByGestorFechaRevision", query = "SELECT r FROM RptReporte r WHERE r.gestorFechaRevision = :gestorFechaRevision")
    , @NamedQuery(name = "RptReporte.findByFkUsuarioreporto", query = "SELECT r FROM RptReporte r WHERE r.fkUsuarioreporto = :fkUsuarioreporto")
    , @NamedQuery(name = "RptReporte.findByFkEstado", query = "SELECT r FROM RptReporte r WHERE r.fkEstado = :fkEstado")
    , @NamedQuery(name = "RptReporte.findByFkCtoFinancieroObra", query = "SELECT r FROM RptReporte r WHERE r.fkCtoFinancieroObra = :fkCtoFinancieroObra")
    , @NamedQuery(name = "RptReporte.findByFkCtoFinancieroInter", query = "SELECT r FROM RptReporte r WHERE r.fkCtoFinancieroInter = :fkCtoFinancieroInter")
    , @NamedQuery(name = "RptReporte.findByFkUsuarioGestor", query = "SELECT r FROM RptReporte r WHERE r.fkUsuarioGestor = :fkUsuarioGestor")
    , @NamedQuery(name = "RptReporte.findByFkUsuarioSupervisor", query = "SELECT r FROM RptReporte r WHERE r.fkUsuarioSupervisor = :fkUsuarioSupervisor")
    , @NamedQuery(name = "RptReporte.findByNombreFirma", query = "SELECT r FROM RptReporte r WHERE r.nombreFirma = :nombreFirma")
    , @NamedQuery(name = "RptReporte.findByMatriculaProfesional", query = "SELECT r FROM RptReporte r WHERE r.matriculaProfesional = :matriculaProfesional")
    , @NamedQuery(name = "RptReporte.findByFechaElaboracion", query = "SELECT r FROM RptReporte r WHERE r.fechaElaboracion = :fechaElaboracion")
    , @NamedQuery(name = "RptReporte.findByAvanceObraFisica", query = "SELECT r FROM RptReporte r WHERE r.avanceObraFisica = :avanceObraFisica")
    , @NamedQuery(name = "RptReporte.findByEstado", query = "SELECT r FROM RptReporte r WHERE r.estado = :estado")
    , @NamedQuery(name = "RptReporte.findByAct5Prg", query = "SELECT r FROM RptReporte r WHERE r.act5Prg = :act5Prg")
    , @NamedQuery(name = "RptReporte.findByAct5Avc", query = "SELECT r FROM RptReporte r WHERE r.act5Avc = :act5Avc")
    , @NamedQuery(name = "RptReporte.findByAct1Prg", query = "SELECT r FROM RptReporte r WHERE r.act1Prg = :act1Prg")
    , @NamedQuery(name = "RptReporte.findByAct1Avc", query = "SELECT r FROM RptReporte r WHERE r.act1Avc = :act1Avc")
    , @NamedQuery(name = "RptReporte.findByAct2Prg", query = "SELECT r FROM RptReporte r WHERE r.act2Prg = :act2Prg")
    , @NamedQuery(name = "RptReporte.findByAct2Avc", query = "SELECT r FROM RptReporte r WHERE r.act2Avc = :act2Avc")
    , @NamedQuery(name = "RptReporte.findByAct3Prg", query = "SELECT r FROM RptReporte r WHERE r.act3Prg = :act3Prg")
    , @NamedQuery(name = "RptReporte.findByAct3Avc", query = "SELECT r FROM RptReporte r WHERE r.act3Avc = :act3Avc")
    , @NamedQuery(name = "RptReporte.findByAct4Prg", query = "SELECT r FROM RptReporte r WHERE r.act4Prg = :act4Prg")
    , @NamedQuery(name = "RptReporte.findByAct4Avc", query = "SELECT r FROM RptReporte r WHERE r.act4Avc = :act4Avc")
    , @NamedQuery(name = "RptReporte.findByAct6Prg", query = "SELECT r FROM RptReporte r WHERE r.act6Prg = :act6Prg")
    , @NamedQuery(name = "RptReporte.findByAct6Avc", query = "SELECT r FROM RptReporte r WHERE r.act6Avc = :act6Avc")
    , @NamedQuery(name = "RptReporte.findByAct7Prg", query = "SELECT r FROM RptReporte r WHERE r.act7Prg = :act7Prg")
    , @NamedQuery(name = "RptReporte.findByAct7Avc", query = "SELECT r FROM RptReporte r WHERE r.act7Avc = :act7Avc")
    , @NamedQuery(name = "RptReporte.findByAct8Prg", query = "SELECT r FROM RptReporte r WHERE r.act8Prg = :act8Prg")
    , @NamedQuery(name = "RptReporte.findByAct8Avc", query = "SELECT r FROM RptReporte r WHERE r.act8Avc = :act8Avc")
    , @NamedQuery(name = "RptReporte.findByAct9Prg", query = "SELECT r FROM RptReporte r WHERE r.act9Prg = :act9Prg")
    , @NamedQuery(name = "RptReporte.findByAct9Avc", query = "SELECT r FROM RptReporte r WHERE r.act9Avc = :act9Avc")
    , @NamedQuery(name = "RptReporte.findByAct10Prg", query = "SELECT r FROM RptReporte r WHERE r.act10Prg = :act10Prg")
    , @NamedQuery(name = "RptReporte.findByAct10Avc", query = "SELECT r FROM RptReporte r WHERE r.act10Avc = :act10Avc")
    , @NamedQuery(name = "RptReporte.findByAct11Prg", query = "SELECT r FROM RptReporte r WHERE r.act11Prg = :act11Prg")
    , @NamedQuery(name = "RptReporte.findByAct11Avc", query = "SELECT r FROM RptReporte r WHERE r.act11Avc = :act11Avc")
    , @NamedQuery(name = "RptReporte.findByAct12Prg", query = "SELECT r FROM RptReporte r WHERE r.act12Prg = :act12Prg")
    , @NamedQuery(name = "RptReporte.findByAct12Avc", query = "SELECT r FROM RptReporte r WHERE r.act12Avc = :act12Avc")
    , @NamedQuery(name = "RptReporte.findByAct13Prg", query = "SELECT r FROM RptReporte r WHERE r.act13Prg = :act13Prg")
    , @NamedQuery(name = "RptReporte.findByAct13Avc", query = "SELECT r FROM RptReporte r WHERE r.act13Avc = :act13Avc")
    , @NamedQuery(name = "RptReporte.findByAct14Prg", query = "SELECT r FROM RptReporte r WHERE r.act14Prg = :act14Prg")
    , @NamedQuery(name = "RptReporte.findByAct14Avc", query = "SELECT r FROM RptReporte r WHERE r.act14Avc = :act14Avc")
    , @NamedQuery(name = "RptReporte.findByAct15Prg", query = "SELECT r FROM RptReporte r WHERE r.act15Prg = :act15Prg")
    , @NamedQuery(name = "RptReporte.findByAct15Avc", query = "SELECT r FROM RptReporte r WHERE r.act15Avc = :act15Avc")
    , @NamedQuery(name = "RptReporte.findByAct16Prg", query = "SELECT r FROM RptReporte r WHERE r.act16Prg = :act16Prg")
    , @NamedQuery(name = "RptReporte.findByAct16Avc", query = "SELECT r FROM RptReporte r WHERE r.act16Avc = :act16Avc")
    , @NamedQuery(name = "RptReporte.findByAct17Prg", query = "SELECT r FROM RptReporte r WHERE r.act17Prg = :act17Prg")
    , @NamedQuery(name = "RptReporte.findByAct17Avc", query = "SELECT r FROM RptReporte r WHERE r.act17Avc = :act17Avc")
    , @NamedQuery(name = "RptReporte.findByAct18Prg", query = "SELECT r FROM RptReporte r WHERE r.act18Prg = :act18Prg")
    , @NamedQuery(name = "RptReporte.findByAct18Avc", query = "SELECT r FROM RptReporte r WHERE r.act18Avc = :act18Avc")
    , @NamedQuery(name = "RptReporte.findByAct19Prg", query = "SELECT r FROM RptReporte r WHERE r.act19Prg = :act19Prg")
    , @NamedQuery(name = "RptReporte.findByAct19Avc", query = "SELECT r FROM RptReporte r WHERE r.act19Avc = :act19Avc")
    , @NamedQuery(name = "RptReporte.findByAct20Prg", query = "SELECT r FROM RptReporte r WHERE r.act20Prg = :act20Prg")
    , @NamedQuery(name = "RptReporte.findByAct20Avc", query = "SELECT r FROM RptReporte r WHERE r.act20Avc = :act20Avc")
    , @NamedQuery(name = "RptReporte.findByAct21Prg", query = "SELECT r FROM RptReporte r WHERE r.act21Prg = :act21Prg")
    , @NamedQuery(name = "RptReporte.findByAct21Avc", query = "SELECT r FROM RptReporte r WHERE r.act21Avc = :act21Avc")
    , @NamedQuery(name = "RptReporte.findByAct22Prg", query = "SELECT r FROM RptReporte r WHERE r.act22Prg = :act22Prg")
    , @NamedQuery(name = "RptReporte.findByAct22Avc", query = "SELECT r FROM RptReporte r WHERE r.act22Avc = :act22Avc")
    , @NamedQuery(name = "RptReporte.findByAct23Prg", query = "SELECT r FROM RptReporte r WHERE r.act23Prg = :act23Prg")
    , @NamedQuery(name = "RptReporte.findByAct23Avc", query = "SELECT r FROM RptReporte r WHERE r.act23Avc = :act23Avc")
    , @NamedQuery(name = "RptReporte.findByAct24Prg", query = "SELECT r FROM RptReporte r WHERE r.act24Prg = :act24Prg")
    , @NamedQuery(name = "RptReporte.findByAct24Avc", query = "SELECT r FROM RptReporte r WHERE r.act24Avc = :act24Avc")
    , @NamedQuery(name = "RptReporte.findByAct25Prg", query = "SELECT r FROM RptReporte r WHERE r.act25Prg = :act25Prg")
    , @NamedQuery(name = "RptReporte.findByAct25Avc", query = "SELECT r FROM RptReporte r WHERE r.act25Avc = :act25Avc")
    , @NamedQuery(name = "RptReporte.findByAct26Prg", query = "SELECT r FROM RptReporte r WHERE r.act26Prg = :act26Prg")
    , @NamedQuery(name = "RptReporte.findByAct26Avc", query = "SELECT r FROM RptReporte r WHERE r.act26Avc = :act26Avc")
    , @NamedQuery(name = "RptReporte.findByAct27Prg", query = "SELECT r FROM RptReporte r WHERE r.act27Prg = :act27Prg")
    , @NamedQuery(name = "RptReporte.findByAct27Avc", query = "SELECT r FROM RptReporte r WHERE r.act27Avc = :act27Avc")
    , @NamedQuery(name = "RptReporte.findByAct28Prg", query = "SELECT r FROM RptReporte r WHERE r.act28Prg = :act28Prg")
    , @NamedQuery(name = "RptReporte.findByAct28Avc", query = "SELECT r FROM RptReporte r WHERE r.act28Avc = :act28Avc")
    , @NamedQuery(name = "RptReporte.findByAct29Prg", query = "SELECT r FROM RptReporte r WHERE r.act29Prg = :act29Prg")
    , @NamedQuery(name = "RptReporte.findByAct29Avc", query = "SELECT r FROM RptReporte r WHERE r.act29Avc = :act29Avc")
    , @NamedQuery(name = "RptReporte.findByAct30Prg", query = "SELECT r FROM RptReporte r WHERE r.act30Prg = :act30Prg")
    , @NamedQuery(name = "RptReporte.findByAct30Avc", query = "SELECT r FROM RptReporte r WHERE r.act30Avc = :act30Avc")
    , @NamedQuery(name = "RptReporte.findByAct31Prg", query = "SELECT r FROM RptReporte r WHERE r.act31Prg = :act31Prg")
    , @NamedQuery(name = "RptReporte.findByAct31Avc", query = "SELECT r FROM RptReporte r WHERE r.act31Avc = :act31Avc")
    , @NamedQuery(name = "RptReporte.findByAct32Prg", query = "SELECT r FROM RptReporte r WHERE r.act32Prg = :act32Prg")
    , @NamedQuery(name = "RptReporte.findByAct32Avc", query = "SELECT r FROM RptReporte r WHERE r.act32Avc = :act32Avc")
    , @NamedQuery(name = "RptReporte.findByAct33Prg", query = "SELECT r FROM RptReporte r WHERE r.act33Prg = :act33Prg")
    , @NamedQuery(name = "RptReporte.findByAct33Avc", query = "SELECT r FROM RptReporte r WHERE r.act33Avc = :act33Avc")
    , @NamedQuery(name = "RptReporte.findByAct34Prg", query = "SELECT r FROM RptReporte r WHERE r.act34Prg = :act34Prg")
    , @NamedQuery(name = "RptReporte.findByAct34Avc", query = "SELECT r FROM RptReporte r WHERE r.act34Avc = :act34Avc")
    , @NamedQuery(name = "RptReporte.findByAct35Prg", query = "SELECT r FROM RptReporte r WHERE r.act35Prg = :act35Prg")
    , @NamedQuery(name = "RptReporte.findByAct35Avc", query = "SELECT r FROM RptReporte r WHERE r.act35Avc = :act35Avc")
    , @NamedQuery(name = "RptReporte.findByAct36Prg", query = "SELECT r FROM RptReporte r WHERE r.act36Prg = :act36Prg")
    , @NamedQuery(name = "RptReporte.findByAct36Avc", query = "SELECT r FROM RptReporte r WHERE r.act36Avc = :act36Avc")
    , @NamedQuery(name = "RptReporte.findByAct37Prg", query = "SELECT r FROM RptReporte r WHERE r.act37Prg = :act37Prg")
    , @NamedQuery(name = "RptReporte.findByAct37Avc", query = "SELECT r FROM RptReporte r WHERE r.act37Avc = :act37Avc")
    , @NamedQuery(name = "RptReporte.findByAct38Prg", query = "SELECT r FROM RptReporte r WHERE r.act38Prg = :act38Prg")
    , @NamedQuery(name = "RptReporte.findByAct38Avc", query = "SELECT r FROM RptReporte r WHERE r.act38Avc = :act38Avc")
    , @NamedQuery(name = "RptReporte.findByAct39Prg", query = "SELECT r FROM RptReporte r WHERE r.act39Prg = :act39Prg")
    , @NamedQuery(name = "RptReporte.findByAct39Avc", query = "SELECT r FROM RptReporte r WHERE r.act39Avc = :act39Avc")
    , @NamedQuery(name = "RptReporte.findByAct40Prg", query = "SELECT r FROM RptReporte r WHERE r.act40Prg = :act40Prg")
    , @NamedQuery(name = "RptReporte.findByAct40Avc", query = "SELECT r FROM RptReporte r WHERE r.act40Avc = :act40Avc")
    , @NamedQuery(name = "RptReporte.findByAct41Prg", query = "SELECT r FROM RptReporte r WHERE r.act41Prg = :act41Prg")
    , @NamedQuery(name = "RptReporte.findByAct41Avc", query = "SELECT r FROM RptReporte r WHERE r.act41Avc = :act41Avc")
    , @NamedQuery(name = "RptReporte.findByAct42Prg", query = "SELECT r FROM RptReporte r WHERE r.act42Prg = :act42Prg")
    , @NamedQuery(name = "RptReporte.findByAct42Avc", query = "SELECT r FROM RptReporte r WHERE r.act42Avc = :act42Avc")
    , @NamedQuery(name = "RptReporte.findByAct43Prg", query = "SELECT r FROM RptReporte r WHERE r.act43Prg = :act43Prg")
    , @NamedQuery(name = "RptReporte.findByAct43Avc", query = "SELECT r FROM RptReporte r WHERE r.act43Avc = :act43Avc")
    , @NamedQuery(name = "RptReporte.findByTempo", query = "SELECT r FROM RptReporte r WHERE r.tempo = :tempo")
    , @NamedQuery(name = "RptReporte.findByFechaInformacionReporte", query = "SELECT r FROM RptReporte r WHERE r.fechaInformacionReporte = :fechaInformacionReporte")
    , @NamedQuery(name = "RptReporte.findByFechaInformacionContrato", query = "SELECT r FROM RptReporte r WHERE r.fechaInformacionContrato = :fechaInformacionContrato")})
public class RptReporte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDENTIFICADOR")
    private Integer identificador;
    @Basic(optional = false)
    @Column(name = "FK_CONTRATO")
    private int fkContrato;
    @Column(name = "NUMEROREPORTE")
    private Integer numeroreporte;
    @Column(name = "NUMEROSEMANA")
    private Integer numerosemana;
    @Column(name = "REPORTEDESDE")
    @Temporal(TemporalType.DATE)
    private Date reportedesde;
    @Column(name = "REPORTEHASTA")
    @Temporal(TemporalType.DATE)
    private Date reportehasta;
    @Column(name = "CONT_PROYECTO")
    private String contProyecto;
    @Column(name = "CONT_UNIDAD_EJECUTORA")
    private String contUnidadEjecutora;
    @Column(name = "CONT_DIRECCION_TERRITORIAL")
    private String contDireccionTerritorial;
    @Column(name = "CONT_SUPERVISOR")
    private String contSupervisor;
    @Column(name = "CONT_GESTOR")
    private String contGestor;
    @Column(name = "CONT_INTERVENTOR")
    private String contInterventor;
    @Column(name = "CONT_OBJETO")
    private String contObjeto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CONT_KM_MEJORAR")
    private BigDecimal contKmMejorar;
    @Column(name = "CONT_KM_REHABILITAR")
    private BigDecimal contKmRehabilitar;
    @Column(name = "CONT_KM_CONSTRUIR")
    private BigDecimal contKmConstruir;
    @Column(name = "CONT_NUMERO_PUENTE")
    private BigDecimal contNumeroPuente;
    @Column(name = "OBR_CONTRATISTA_NOMBRE")
    private String obrContratistaNombre;
    @Column(name = "OBR_CONTRATISTA_NIT")
    private String obrContratistaNit;
    @Column(name = "OBR_PRG_INV_VALOR")
    private BigDecimal obrPrgInvValor;
    @Column(name = "OBR_PRG_INV_MES")
    private Integer obrPrgInvMes;
    @Column(name = "OBR_PRG_INV_ANO")
    private Integer obrPrgInvAno;
    @Column(name = "OBR_PRG_INV_TOTAL")
    private BigDecimal obrPrgInvTotal;
    @Column(name = "OBR_PRG_INV_ACUMULADO_PROGRAMA_INVERSION")
    private BigDecimal obrPrgInvAcumuladoProgramaInversion;
    @Column(name = "OBR_PRG_INV_ACUMULADO_FECHA_REPORTE")
    private BigDecimal obrPrgInvAcumuladoFechaReporte;
    @Column(name = "OBR_NUMERO_CONTRATO")
    private String obrNumeroContrato;
    @Column(name = "OBR_VALOR_INICIAL")
    private BigDecimal obrValorInicial;
    @Column(name = "OBR_ADICIONES")
    private BigDecimal obrAdiciones;
    @Column(name = "OBR_PLAZO_INICIAL")
    private BigDecimal obrPlazoInicial;
    @Column(name = "OBR_PRORROGA")
    private BigDecimal obrProrroga;
    @Column(name = "OBR_PRESENTO_FACTURA")
    private Boolean obrPresentoFactura;
    @Column(name = "OBR_VALOR_INVERSION")
    private BigDecimal obrValorInversion;
    @Column(name = "OBR_VALOR_FACTURADO")
    private BigDecimal obrValorFacturado;
    @Column(name = "OBR_FECHA_FACTURA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date obrFechaFactura;
    @Column(name = "OBR_AVANCE_FISICO")
    private BigDecimal obrAvanceFisico;
    @Column(name = "OBR_FACTURADO_PROGRAMA_INVERSION")
    private BigDecimal obrFacturadoProgramaInversion;
    @Column(name = "OBR_FACTURADO_TOTAL")
    private BigDecimal obrFacturadoTotal;
    @Column(name = "OBR_VAL_ULT_FACTURA")
    private BigDecimal obrValUltFactura;
    @Column(name = "OBR_FECHA_ULT_FACTURA")
    @Temporal(TemporalType.DATE)
    private Date obrFechaUltFactura;
    @Column(name = "OBR_SIIF_VALOR")
    private BigDecimal obrSiifValor;
    @Column(name = "OBR_SIIF_FECHA")
    @Temporal(TemporalType.DATE)
    private Date obrSiifFecha;
    @Column(name = "INT_CONTRATISTA_NOMBRE")
    private String intContratistaNombre;
    @Column(name = "INT_CONTRATISTA_NIT")
    private String intContratistaNit;
    @Column(name = "INT_NUMERO_CONTRATO")
    private String intNumeroContrato;
    @Column(name = "INT_PRESENTO_FACTURA")
    private Boolean intPresentoFactura;
    @Column(name = "INT_VALOR_INICIAL")
    private BigDecimal intValorInicial;
    @Column(name = "INT_ADICIONES")
    private BigDecimal intAdiciones;
    @Column(name = "INT_PLAZO_INICIAL")
    private BigDecimal intPlazoInicial;
    @Column(name = "INT_PRORROGA")
    private BigDecimal intProrroga;
    @Column(name = "INT_VALOR_FACTURA")
    private BigDecimal intValorFactura;
    @Column(name = "INT_FECHA_FACTURA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date intFechaFactura;
    @Column(name = "INT_FACTURADO_PROGRAMADO_NUM_MES")
    private BigDecimal intFacturadoProgramadoNumMes;
    @Column(name = "INT_FACTURADO_PROGRAMADO_FECHA_REPORTE")
    private BigDecimal intFacturadoProgramadoFechaReporte;
    @Column(name = "INT_FACTURADO")
    private BigDecimal intFacturado;
    @Column(name = "INT_MES_FACTURADO")
    private Integer intMesFacturado;
    @Column(name = "INT_ACUMULADO_FACTURADO")
    private BigDecimal intAcumuladoFacturado;
    @Column(name = "INT_SIIF_VALOR")
    private BigDecimal intSiifValor;
    @Column(name = "INT_SIIF_FECHA")
    @Temporal(TemporalType.DATE)
    private Date intSiifFecha;
    @Column(name = "ACTIVIDADES_REALIZADAS")
    private String actividadesRealizadas;
    @Column(name = "ACTIVIDADES_PROXIMA")
    private String actividadesProxima;
    @Column(name = "RESUMEN")
    private String resumen;
    @Column(name = "ACTIVIDAD_A_REALIZAR")
    private String actividadARealizar;
    @Column(name = "ACTIVIDAD_REALIZADA")
    private String actividadRealizada;
    @Column(name = "GESO_SEDE")
    private String gesoSede;
    @Column(name = "GESO_NOCAPACITACIONES")
    private Integer gesoNocapacitaciones;
    @Column(name = "GESO_NOSOCIALIZACIONES")
    private Integer gesoNosocializaciones;
    @Column(name = "GESO_EMPINDIRGEN")
    private Integer gesoEmpindirgen;
    @Column(name = "GESO_EMPDIRGEN")
    private Integer gesoEmpdirgen;
    @Column(name = "GESO_EMPGEN")
    private Integer gesoEmpgen;
    @Column(name = "GESO_OBSERVACIONES")
    private String gesoObservaciones;
    @Column(name = "GEPR_OBSERVACIONES")
    private String geprObservaciones;
    @Column(name = "GEPR_PREDADQUIRIDOS")
    private Integer geprPredadquiridos;
    @Column(name = "GEPR_AVALUOS")
    private Integer geprAvaluos;
    @Column(name = "GEPR_FICHASAPROB")
    private Integer geprFichasaprob;
    @Column(name = "GEPR_PREDADQUIRIR")
    private Integer geprPredadquirir;
    @Column(name = "PAGA_OBSERVACIONES")
    private String pagaObservaciones;
    @Column(name = "PAGA_ESTADO")
    private String pagaEstado;
    @Column(name = "AMB_OBSERVACIONES")
    private String ambObservaciones;
    @Column(name = "AMB_EMICIONES")
    private Integer ambEmiciones;
    @Column(name = "AMB_VERTIMENTOS")
    private Integer ambVertimentos;
    @Column(name = "AMB_OCUPACIONCAUCES")
    private Integer ambOcupacioncauces;
    @Column(name = "AMB_CONCECIONAGUAS")
    private Integer ambConcecionaguas;
    @Column(name = "AMB_APROVFORESTAL")
    private Integer ambAprovforestal;
    @Column(name = "AMB_LICENCIAMBIENTAL")
    private Integer ambLicenciambiental;
    @Column(name = "AMB_BOTADEROS")
    private Integer ambBotaderos;
    @Column(name = "FTMAT_OBSERVACIONES")
    private String ftmatObservaciones;
    @Column(name = "FECHA_LIMITE_PRESENTACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaLimitePresentacion;
    @Column(name = "FECHA_PRESENTACION_REPORTE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPresentacionReporte;
    @Column(name = "FECHA_REGISTRO_INICIAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistroInicial;
    @Column(name = "SUPERVISOR_FECHA_REVISION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date supervisorFechaRevision;
    @Column(name = "SUPERVISOR_OBSERVACIONES")
    private String supervisorObservaciones;
    @Column(name = "GESTOR_OBSERVACIONES")
    private String gestorObservaciones;
    @Column(name = "GESTOR_FECHA_REVISION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date gestorFechaRevision;
    @Column(name = "FK_USUARIOREPORTO")
    private Integer fkUsuarioreporto;
    @Column(name = "FK_ESTADO")
    private Integer fkEstado;
    @Column(name = "FK_CTO_FINANCIERO_OBRA")
    private Integer fkCtoFinancieroObra;
    @Column(name = "FK_CTO_FINANCIERO_INTER")
    private Integer fkCtoFinancieroInter;
    @Column(name = "FK_USUARIO_GESTOR")
    private Integer fkUsuarioGestor;
    @Column(name = "FK_USUARIO_SUPERVISOR")
    private Integer fkUsuarioSupervisor;
    @Column(name = "NOMBRE_FIRMA")
    private String nombreFirma;
    @Column(name = "MATRICULA_PROFESIONAL")
    private String matriculaProfesional;
    @Column(name = "FECHA_ELABORACION")
    @Temporal(TemporalType.DATE)
    private Date fechaElaboracion;
    @Column(name = "AVANCE_OBRA_FISICA")
    private BigDecimal avanceObraFisica;
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "ACT_5_PRG")
    private BigDecimal act5Prg;
    @Column(name = "ACT_5_AVC")
    private BigDecimal act5Avc;
    @Column(name = "ACT_1_PRG")
    private BigDecimal act1Prg;
    @Column(name = "ACT_1_AVC")
    private BigDecimal act1Avc;
    @Column(name = "ACT_2_PRG")
    private BigDecimal act2Prg;
    @Column(name = "ACT_2_AVC")
    private BigDecimal act2Avc;
    @Column(name = "ACT_3_PRG")
    private BigDecimal act3Prg;
    @Column(name = "ACT_3_AVC")
    private BigDecimal act3Avc;
    @Column(name = "ACT_4_PRG")
    private BigDecimal act4Prg;
    @Column(name = "ACT_4_AVC")
    private BigDecimal act4Avc;
    @Column(name = "ACT_6_PRG")
    private BigDecimal act6Prg;
    @Column(name = "ACT_6_AVC")
    private BigDecimal act6Avc;
    @Column(name = "ACT_7_PRG")
    private BigDecimal act7Prg;
    @Column(name = "ACT_7_AVC")
    private BigDecimal act7Avc;
    @Column(name = "ACT_8_PRG")
    private BigDecimal act8Prg;
    @Column(name = "ACT_8_AVC")
    private BigDecimal act8Avc;
    @Column(name = "ACT_9_PRG")
    private BigDecimal act9Prg;
    @Column(name = "ACT_9_AVC")
    private BigDecimal act9Avc;
    @Column(name = "ACT_10_PRG")
    private BigDecimal act10Prg;
    @Column(name = "ACT_10_AVC")
    private BigDecimal act10Avc;
    @Column(name = "ACT_11_PRG")
    private BigDecimal act11Prg;
    @Column(name = "ACT_11_AVC")
    private BigDecimal act11Avc;
    @Column(name = "ACT_12_PRG")
    private BigDecimal act12Prg;
    @Column(name = "ACT_12_AVC")
    private BigDecimal act12Avc;
    @Column(name = "ACT_13_PRG")
    private BigDecimal act13Prg;
    @Column(name = "ACT_13_AVC")
    private BigDecimal act13Avc;
    @Column(name = "ACT_14_PRG")
    private BigDecimal act14Prg;
    @Column(name = "ACT_14_AVC")
    private BigDecimal act14Avc;
    @Column(name = "ACT_15_PRG")
    private BigDecimal act15Prg;
    @Column(name = "ACT_15_AVC")
    private BigDecimal act15Avc;
    @Column(name = "ACT_16_PRG")
    private BigDecimal act16Prg;
    @Column(name = "ACT_16_AVC")
    private BigDecimal act16Avc;
    @Column(name = "ACT_17_PRG")
    private BigDecimal act17Prg;
    @Column(name = "ACT_17_AVC")
    private BigDecimal act17Avc;
    @Column(name = "ACT_18_PRG")
    private BigDecimal act18Prg;
    @Column(name = "ACT_18_AVC")
    private BigDecimal act18Avc;
    @Column(name = "ACT_19_PRG")
    private BigDecimal act19Prg;
    @Column(name = "ACT_19_AVC")
    private BigDecimal act19Avc;
    @Column(name = "ACT_20_PRG")
    private BigDecimal act20Prg;
    @Column(name = "ACT_20_AVC")
    private BigDecimal act20Avc;
    @Column(name = "ACT_21_PRG")
    private BigDecimal act21Prg;
    @Column(name = "ACT_21_AVC")
    private BigDecimal act21Avc;
    @Column(name = "ACT_22_PRG")
    private BigDecimal act22Prg;
    @Column(name = "ACT_22_AVC")
    private BigDecimal act22Avc;
    @Column(name = "ACT_23_PRG")
    private BigDecimal act23Prg;
    @Column(name = "ACT_23_AVC")
    private BigDecimal act23Avc;
    @Column(name = "ACT_24_PRG")
    private BigDecimal act24Prg;
    @Column(name = "ACT_24_AVC")
    private BigDecimal act24Avc;
    @Column(name = "ACT_25_PRG")
    private BigDecimal act25Prg;
    @Column(name = "ACT_25_AVC")
    private BigDecimal act25Avc;
    @Column(name = "ACT_26_PRG")
    private BigDecimal act26Prg;
    @Column(name = "ACT_26_AVC")
    private BigDecimal act26Avc;
    @Column(name = "ACT_27_PRG")
    private BigDecimal act27Prg;
    @Column(name = "ACT_27_AVC")
    private BigDecimal act27Avc;
    @Column(name = "ACT_28_PRG")
    private BigDecimal act28Prg;
    @Column(name = "ACT_28_AVC")
    private BigDecimal act28Avc;
    @Column(name = "ACT_29_PRG")
    private BigDecimal act29Prg;
    @Column(name = "ACT_29_AVC")
    private BigDecimal act29Avc;
    @Column(name = "ACT_30_PRG")
    private BigDecimal act30Prg;
    @Column(name = "ACT_30_AVC")
    private BigDecimal act30Avc;
    @Column(name = "ACT_31_PRG")
    private BigDecimal act31Prg;
    @Column(name = "ACT_31_AVC")
    private BigDecimal act31Avc;
    @Column(name = "ACT_32_PRG")
    private BigDecimal act32Prg;
    @Column(name = "ACT_32_AVC")
    private BigDecimal act32Avc;
    @Column(name = "ACT_33_PRG")
    private BigDecimal act33Prg;
    @Column(name = "ACT_33_AVC")
    private BigDecimal act33Avc;
    @Column(name = "ACT_34_PRG")
    private BigDecimal act34Prg;
    @Column(name = "ACT_34_AVC")
    private BigDecimal act34Avc;
    @Column(name = "ACT_35_PRG")
    private BigDecimal act35Prg;
    @Column(name = "ACT_35_AVC")
    private BigDecimal act35Avc;
    @Column(name = "ACT_36_PRG")
    private BigDecimal act36Prg;
    @Column(name = "ACT_36_AVC")
    private BigDecimal act36Avc;
    @Column(name = "ACT_37_PRG")
    private BigDecimal act37Prg;
    @Column(name = "ACT_37_AVC")
    private BigDecimal act37Avc;
    @Column(name = "ACT_38_PRG")
    private BigDecimal act38Prg;
    @Column(name = "ACT_38_AVC")
    private BigDecimal act38Avc;
    @Column(name = "ACT_39_PRG")
    private BigDecimal act39Prg;
    @Column(name = "ACT_39_AVC")
    private BigDecimal act39Avc;
    @Column(name = "ACT_40_PRG")
    private BigDecimal act40Prg;
    @Column(name = "ACT_40_AVC")
    private BigDecimal act40Avc;
    @Column(name = "ACT_41_PRG")
    private BigDecimal act41Prg;
    @Column(name = "ACT_41_AVC")
    private BigDecimal act41Avc;
    @Column(name = "ACT_42_PRG")
    private BigDecimal act42Prg;
    @Column(name = "ACT_42_AVC")
    private BigDecimal act42Avc;
    @Column(name = "ACT_43_PRG")
    private BigDecimal act43Prg;
    @Column(name = "ACT_43_AVC")
    private BigDecimal act43Avc;
    @Column(name = "tempo")
    private String tempo;
    @Column(name = "FECHA_INFORMACION_REPORTE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInformacionReporte;
    @Column(name = "FECHA_INFORMACION_CONTRATO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInformacionContrato;

    public RptReporte() {
    }

    public RptReporte(Integer identificador) {
        this.identificador = identificador;
    }

    public RptReporte(Integer identificador, int fkContrato) {
        this.identificador = identificador;
        this.fkContrato = fkContrato;
    }

    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }

    public int getFkContrato() {
        return fkContrato;
    }

    public void setFkContrato(int fkContrato) {
        this.fkContrato = fkContrato;
    }

    public Integer getNumeroreporte() {
        return numeroreporte;
    }

    public void setNumeroreporte(Integer numeroreporte) {
        this.numeroreporte = numeroreporte;
    }

    public Integer getNumerosemana() {
        return numerosemana;
    }

    public void setNumerosemana(Integer numerosemana) {
        this.numerosemana = numerosemana;
    }

    public Date getReportedesde() {
        return reportedesde;
    }

    public void setReportedesde(Date reportedesde) {
        this.reportedesde = reportedesde;
    }

    public Date getReportehasta() {
        return reportehasta;
    }

    public void setReportehasta(Date reportehasta) {
        this.reportehasta = reportehasta;
    }

    public String getContProyecto() {
        return contProyecto;
    }

    public void setContProyecto(String contProyecto) {
        this.contProyecto = contProyecto;
    }

    public String getContUnidadEjecutora() {
        return contUnidadEjecutora;
    }

    public void setContUnidadEjecutora(String contUnidadEjecutora) {
        this.contUnidadEjecutora = contUnidadEjecutora;
    }

    public String getContDireccionTerritorial() {
        return contDireccionTerritorial;
    }

    public void setContDireccionTerritorial(String contDireccionTerritorial) {
        this.contDireccionTerritorial = contDireccionTerritorial;
    }

    public String getContSupervisor() {
        return contSupervisor;
    }

    public void setContSupervisor(String contSupervisor) {
        this.contSupervisor = contSupervisor;
    }

    public String getContGestor() {
        return contGestor;
    }

    public void setContGestor(String contGestor) {
        this.contGestor = contGestor;
    }

    public String getContInterventor() {
        return contInterventor;
    }

    public void setContInterventor(String contInterventor) {
        this.contInterventor = contInterventor;
    }

    public String getContObjeto() {
        return contObjeto;
    }

    public void setContObjeto(String contObjeto) {
        this.contObjeto = contObjeto;
    }

    public BigDecimal getContKmMejorar() {
        return contKmMejorar;
    }

    public void setContKmMejorar(BigDecimal contKmMejorar) {
        this.contKmMejorar = contKmMejorar;
    }

    public BigDecimal getContKmRehabilitar() {
        return contKmRehabilitar;
    }

    public void setContKmRehabilitar(BigDecimal contKmRehabilitar) {
        this.contKmRehabilitar = contKmRehabilitar;
    }

    public BigDecimal getContKmConstruir() {
        return contKmConstruir;
    }

    public void setContKmConstruir(BigDecimal contKmConstruir) {
        this.contKmConstruir = contKmConstruir;
    }

    public BigDecimal getContNumeroPuente() {
        return contNumeroPuente;
    }

    public void setContNumeroPuente(BigDecimal contNumeroPuente) {
        this.contNumeroPuente = contNumeroPuente;
    }

    public String getObrContratistaNombre() {
        return obrContratistaNombre;
    }

    public void setObrContratistaNombre(String obrContratistaNombre) {
        this.obrContratistaNombre = obrContratistaNombre;
    }

    public String getObrContratistaNit() {
        return obrContratistaNit;
    }

    public void setObrContratistaNit(String obrContratistaNit) {
        this.obrContratistaNit = obrContratistaNit;
    }

    public BigDecimal getObrPrgInvValor() {
        return obrPrgInvValor;
    }

    public void setObrPrgInvValor(BigDecimal obrPrgInvValor) {
        this.obrPrgInvValor = obrPrgInvValor;
    }

    public Integer getObrPrgInvMes() {
        return obrPrgInvMes;
    }

    public void setObrPrgInvMes(Integer obrPrgInvMes) {
        this.obrPrgInvMes = obrPrgInvMes;
    }

    public Integer getObrPrgInvAno() {
        return obrPrgInvAno;
    }

    public void setObrPrgInvAno(Integer obrPrgInvAno) {
        this.obrPrgInvAno = obrPrgInvAno;
    }

    public BigDecimal getObrPrgInvTotal() {
        return obrPrgInvTotal;
    }

    public void setObrPrgInvTotal(BigDecimal obrPrgInvTotal) {
        this.obrPrgInvTotal = obrPrgInvTotal;
    }

    public BigDecimal getObrPrgInvAcumuladoProgramaInversion() {
        return obrPrgInvAcumuladoProgramaInversion;
    }

    public void setObrPrgInvAcumuladoProgramaInversion(BigDecimal obrPrgInvAcumuladoProgramaInversion) {
        this.obrPrgInvAcumuladoProgramaInversion = obrPrgInvAcumuladoProgramaInversion;
    }

    public BigDecimal getObrPrgInvAcumuladoFechaReporte() {
        return obrPrgInvAcumuladoFechaReporte;
    }

    public void setObrPrgInvAcumuladoFechaReporte(BigDecimal obrPrgInvAcumuladoFechaReporte) {
        this.obrPrgInvAcumuladoFechaReporte = obrPrgInvAcumuladoFechaReporte;
    }

    public String getObrNumeroContrato() {
        return obrNumeroContrato;
    }

    public void setObrNumeroContrato(String obrNumeroContrato) {
        this.obrNumeroContrato = obrNumeroContrato;
    }

    public BigDecimal getObrValorInicial() {
        return obrValorInicial;
    }

    public void setObrValorInicial(BigDecimal obrValorInicial) {
        this.obrValorInicial = obrValorInicial;
    }

    public BigDecimal getObrAdiciones() {
        return obrAdiciones;
    }

    public void setObrAdiciones(BigDecimal obrAdiciones) {
        this.obrAdiciones = obrAdiciones;
    }

    public BigDecimal getObrPlazoInicial() {
        return obrPlazoInicial;
    }

    public void setObrPlazoInicial(BigDecimal obrPlazoInicial) {
        this.obrPlazoInicial = obrPlazoInicial;
    }

    public BigDecimal getObrProrroga() {
        return obrProrroga;
    }

    public void setObrProrroga(BigDecimal obrProrroga) {
        this.obrProrroga = obrProrroga;
    }

    public Boolean getObrPresentoFactura() {
        return obrPresentoFactura;
    }

    public void setObrPresentoFactura(Boolean obrPresentoFactura) {
        this.obrPresentoFactura = obrPresentoFactura;
    }

    public BigDecimal getObrValorInversion() {
        return obrValorInversion;
    }

    public void setObrValorInversion(BigDecimal obrValorInversion) {
        this.obrValorInversion = obrValorInversion;
    }

    public BigDecimal getObrValorFacturado() {
        return obrValorFacturado;
    }

    public void setObrValorFacturado(BigDecimal obrValorFacturado) {
        this.obrValorFacturado = obrValorFacturado;
    }

    public Date getObrFechaFactura() {
        return obrFechaFactura;
    }

    public void setObrFechaFactura(Date obrFechaFactura) {
        this.obrFechaFactura = obrFechaFactura;
    }

    public BigDecimal getObrAvanceFisico() {
        return obrAvanceFisico;
    }

    public void setObrAvanceFisico(BigDecimal obrAvanceFisico) {
        this.obrAvanceFisico = obrAvanceFisico;
    }

    public BigDecimal getObrFacturadoProgramaInversion() {
        return obrFacturadoProgramaInversion;
    }

    public void setObrFacturadoProgramaInversion(BigDecimal obrFacturadoProgramaInversion) {
        this.obrFacturadoProgramaInversion = obrFacturadoProgramaInversion;
    }

    public BigDecimal getObrFacturadoTotal() {
        return obrFacturadoTotal;
    }

    public void setObrFacturadoTotal(BigDecimal obrFacturadoTotal) {
        this.obrFacturadoTotal = obrFacturadoTotal;
    }

    public BigDecimal getObrValUltFactura() {
        return obrValUltFactura;
    }

    public void setObrValUltFactura(BigDecimal obrValUltFactura) {
        this.obrValUltFactura = obrValUltFactura;
    }

    public Date getObrFechaUltFactura() {
        return obrFechaUltFactura;
    }

    public void setObrFechaUltFactura(Date obrFechaUltFactura) {
        this.obrFechaUltFactura = obrFechaUltFactura;
    }

    public BigDecimal getObrSiifValor() {
        return obrSiifValor;
    }

    public void setObrSiifValor(BigDecimal obrSiifValor) {
        this.obrSiifValor = obrSiifValor;
    }

    public Date getObrSiifFecha() {
        return obrSiifFecha;
    }

    public void setObrSiifFecha(Date obrSiifFecha) {
        this.obrSiifFecha = obrSiifFecha;
    }

    public String getIntContratistaNombre() {
        return intContratistaNombre;
    }

    public void setIntContratistaNombre(String intContratistaNombre) {
        this.intContratistaNombre = intContratistaNombre;
    }

    public String getIntContratistaNit() {
        return intContratistaNit;
    }

    public void setIntContratistaNit(String intContratistaNit) {
        this.intContratistaNit = intContratistaNit;
    }

    public String getIntNumeroContrato() {
        return intNumeroContrato;
    }

    public void setIntNumeroContrato(String intNumeroContrato) {
        this.intNumeroContrato = intNumeroContrato;
    }

    public Boolean getIntPresentoFactura() {
        return intPresentoFactura;
    }

    public void setIntPresentoFactura(Boolean intPresentoFactura) {
        this.intPresentoFactura = intPresentoFactura;
    }

    public BigDecimal getIntValorInicial() {
        return intValorInicial;
    }

    public void setIntValorInicial(BigDecimal intValorInicial) {
        this.intValorInicial = intValorInicial;
    }

    public BigDecimal getIntAdiciones() {
        return intAdiciones;
    }

    public void setIntAdiciones(BigDecimal intAdiciones) {
        this.intAdiciones = intAdiciones;
    }

    public BigDecimal getIntPlazoInicial() {
        return intPlazoInicial;
    }

    public void setIntPlazoInicial(BigDecimal intPlazoInicial) {
        this.intPlazoInicial = intPlazoInicial;
    }

    public BigDecimal getIntProrroga() {
        return intProrroga;
    }

    public void setIntProrroga(BigDecimal intProrroga) {
        this.intProrroga = intProrroga;
    }

    public BigDecimal getIntValorFactura() {
        return intValorFactura;
    }

    public void setIntValorFactura(BigDecimal intValorFactura) {
        this.intValorFactura = intValorFactura;
    }

    public Date getIntFechaFactura() {
        return intFechaFactura;
    }

    public void setIntFechaFactura(Date intFechaFactura) {
        this.intFechaFactura = intFechaFactura;
    }

    public BigDecimal getIntFacturadoProgramadoNumMes() {
        return intFacturadoProgramadoNumMes;
    }

    public void setIntFacturadoProgramadoNumMes(BigDecimal intFacturadoProgramadoNumMes) {
        this.intFacturadoProgramadoNumMes = intFacturadoProgramadoNumMes;
    }

    public BigDecimal getIntFacturadoProgramadoFechaReporte() {
        return intFacturadoProgramadoFechaReporte;
    }

    public void setIntFacturadoProgramadoFechaReporte(BigDecimal intFacturadoProgramadoFechaReporte) {
        this.intFacturadoProgramadoFechaReporte = intFacturadoProgramadoFechaReporte;
    }

    public BigDecimal getIntFacturado() {
        return intFacturado;
    }

    public void setIntFacturado(BigDecimal intFacturado) {
        this.intFacturado = intFacturado;
    }

    public Integer getIntMesFacturado() {
        return intMesFacturado;
    }

    public void setIntMesFacturado(Integer intMesFacturado) {
        this.intMesFacturado = intMesFacturado;
    }

    public BigDecimal getIntAcumuladoFacturado() {
        return intAcumuladoFacturado;
    }

    public void setIntAcumuladoFacturado(BigDecimal intAcumuladoFacturado) {
        this.intAcumuladoFacturado = intAcumuladoFacturado;
    }

    public BigDecimal getIntSiifValor() {
        return intSiifValor;
    }

    public void setIntSiifValor(BigDecimal intSiifValor) {
        this.intSiifValor = intSiifValor;
    }

    public Date getIntSiifFecha() {
        return intSiifFecha;
    }

    public void setIntSiifFecha(Date intSiifFecha) {
        this.intSiifFecha = intSiifFecha;
    }

    public String getActividadesRealizadas() {
        return actividadesRealizadas;
    }

    public void setActividadesRealizadas(String actividadesRealizadas) {
        this.actividadesRealizadas = actividadesRealizadas;
    }

    public String getActividadesProxima() {
        return actividadesProxima;
    }

    public void setActividadesProxima(String actividadesProxima) {
        this.actividadesProxima = actividadesProxima;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getActividadARealizar() {
        return actividadARealizar;
    }

    public void setActividadARealizar(String actividadARealizar) {
        this.actividadARealizar = actividadARealizar;
    }

    public String getActividadRealizada() {
        return actividadRealizada;
    }

    public void setActividadRealizada(String actividadRealizada) {
        this.actividadRealizada = actividadRealizada;
    }

    public String getGesoSede() {
        return gesoSede;
    }

    public void setGesoSede(String gesoSede) {
        this.gesoSede = gesoSede;
    }

    public Integer getGesoNocapacitaciones() {
        return gesoNocapacitaciones;
    }

    public void setGesoNocapacitaciones(Integer gesoNocapacitaciones) {
        this.gesoNocapacitaciones = gesoNocapacitaciones;
    }

    public Integer getGesoNosocializaciones() {
        return gesoNosocializaciones;
    }

    public void setGesoNosocializaciones(Integer gesoNosocializaciones) {
        this.gesoNosocializaciones = gesoNosocializaciones;
    }

    public Integer getGesoEmpindirgen() {
        return gesoEmpindirgen;
    }

    public void setGesoEmpindirgen(Integer gesoEmpindirgen) {
        this.gesoEmpindirgen = gesoEmpindirgen;
    }

    public Integer getGesoEmpdirgen() {
        return gesoEmpdirgen;
    }

    public void setGesoEmpdirgen(Integer gesoEmpdirgen) {
        this.gesoEmpdirgen = gesoEmpdirgen;
    }

    public Integer getGesoEmpgen() {
        return gesoEmpgen;
    }

    public void setGesoEmpgen(Integer gesoEmpgen) {
        this.gesoEmpgen = gesoEmpgen;
    }

    public String getGesoObservaciones() {
        return gesoObservaciones;
    }

    public void setGesoObservaciones(String gesoObservaciones) {
        this.gesoObservaciones = gesoObservaciones;
    }

    public String getGeprObservaciones() {
        return geprObservaciones;
    }

    public void setGeprObservaciones(String geprObservaciones) {
        this.geprObservaciones = geprObservaciones;
    }

    public Integer getGeprPredadquiridos() {
        return geprPredadquiridos;
    }

    public void setGeprPredadquiridos(Integer geprPredadquiridos) {
        this.geprPredadquiridos = geprPredadquiridos;
    }

    public Integer getGeprAvaluos() {
        return geprAvaluos;
    }

    public void setGeprAvaluos(Integer geprAvaluos) {
        this.geprAvaluos = geprAvaluos;
    }

    public Integer getGeprFichasaprob() {
        return geprFichasaprob;
    }

    public void setGeprFichasaprob(Integer geprFichasaprob) {
        this.geprFichasaprob = geprFichasaprob;
    }

    public Integer getGeprPredadquirir() {
        return geprPredadquirir;
    }

    public void setGeprPredadquirir(Integer geprPredadquirir) {
        this.geprPredadquirir = geprPredadquirir;
    }

    public String getPagaObservaciones() {
        return pagaObservaciones;
    }

    public void setPagaObservaciones(String pagaObservaciones) {
        this.pagaObservaciones = pagaObservaciones;
    }

    public String getPagaEstado() {
        return pagaEstado;
    }

    public void setPagaEstado(String pagaEstado) {
        this.pagaEstado = pagaEstado;
    }

    public String getAmbObservaciones() {
        return ambObservaciones;
    }

    public void setAmbObservaciones(String ambObservaciones) {
        this.ambObservaciones = ambObservaciones;
    }

    public Integer getAmbEmiciones() {
        return ambEmiciones;
    }

    public void setAmbEmiciones(Integer ambEmiciones) {
        this.ambEmiciones = ambEmiciones;
    }

    public Integer getAmbVertimentos() {
        return ambVertimentos;
    }

    public void setAmbVertimentos(Integer ambVertimentos) {
        this.ambVertimentos = ambVertimentos;
    }

    public Integer getAmbOcupacioncauces() {
        return ambOcupacioncauces;
    }

    public void setAmbOcupacioncauces(Integer ambOcupacioncauces) {
        this.ambOcupacioncauces = ambOcupacioncauces;
    }

    public Integer getAmbConcecionaguas() {
        return ambConcecionaguas;
    }

    public void setAmbConcecionaguas(Integer ambConcecionaguas) {
        this.ambConcecionaguas = ambConcecionaguas;
    }

    public Integer getAmbAprovforestal() {
        return ambAprovforestal;
    }

    public void setAmbAprovforestal(Integer ambAprovforestal) {
        this.ambAprovforestal = ambAprovforestal;
    }

    public Integer getAmbLicenciambiental() {
        return ambLicenciambiental;
    }

    public void setAmbLicenciambiental(Integer ambLicenciambiental) {
        this.ambLicenciambiental = ambLicenciambiental;
    }

    public Integer getAmbBotaderos() {
        return ambBotaderos;
    }

    public void setAmbBotaderos(Integer ambBotaderos) {
        this.ambBotaderos = ambBotaderos;
    }

    public String getFtmatObservaciones() {
        return ftmatObservaciones;
    }

    public void setFtmatObservaciones(String ftmatObservaciones) {
        this.ftmatObservaciones = ftmatObservaciones;
    }

    public Date getFechaLimitePresentacion() {
        return fechaLimitePresentacion;
    }

    public void setFechaLimitePresentacion(Date fechaLimitePresentacion) {
        this.fechaLimitePresentacion = fechaLimitePresentacion;
    }

    public Date getFechaPresentacionReporte() {
        return fechaPresentacionReporte;
    }

    public void setFechaPresentacionReporte(Date fechaPresentacionReporte) {
        this.fechaPresentacionReporte = fechaPresentacionReporte;
    }

    public Date getFechaRegistroInicial() {
        return fechaRegistroInicial;
    }

    public void setFechaRegistroInicial(Date fechaRegistroInicial) {
        this.fechaRegistroInicial = fechaRegistroInicial;
    }

    public Date getSupervisorFechaRevision() {
        return supervisorFechaRevision;
    }

    public void setSupervisorFechaRevision(Date supervisorFechaRevision) {
        this.supervisorFechaRevision = supervisorFechaRevision;
    }

    public String getSupervisorObservaciones() {
        return supervisorObservaciones;
    }

    public void setSupervisorObservaciones(String supervisorObservaciones) {
        this.supervisorObservaciones = supervisorObservaciones;
    }

    public String getGestorObservaciones() {
        return gestorObservaciones;
    }

    public void setGestorObservaciones(String gestorObservaciones) {
        this.gestorObservaciones = gestorObservaciones;
    }

    public Date getGestorFechaRevision() {
        return gestorFechaRevision;
    }

    public void setGestorFechaRevision(Date gestorFechaRevision) {
        this.gestorFechaRevision = gestorFechaRevision;
    }

    public Integer getFkUsuarioreporto() {
        return fkUsuarioreporto;
    }

    public void setFkUsuarioreporto(Integer fkUsuarioreporto) {
        this.fkUsuarioreporto = fkUsuarioreporto;
    }

    public Integer getFkEstado() {
        return fkEstado;
    }

    public void setFkEstado(Integer fkEstado) {
        this.fkEstado = fkEstado;
    }

    public Integer getFkCtoFinancieroObra() {
        return fkCtoFinancieroObra;
    }

    public void setFkCtoFinancieroObra(Integer fkCtoFinancieroObra) {
        this.fkCtoFinancieroObra = fkCtoFinancieroObra;
    }

    public Integer getFkCtoFinancieroInter() {
        return fkCtoFinancieroInter;
    }

    public void setFkCtoFinancieroInter(Integer fkCtoFinancieroInter) {
        this.fkCtoFinancieroInter = fkCtoFinancieroInter;
    }

    public Integer getFkUsuarioGestor() {
        return fkUsuarioGestor;
    }

    public void setFkUsuarioGestor(Integer fkUsuarioGestor) {
        this.fkUsuarioGestor = fkUsuarioGestor;
    }

    public Integer getFkUsuarioSupervisor() {
        return fkUsuarioSupervisor;
    }

    public void setFkUsuarioSupervisor(Integer fkUsuarioSupervisor) {
        this.fkUsuarioSupervisor = fkUsuarioSupervisor;
    }

    public String getNombreFirma() {
        return nombreFirma;
    }

    public void setNombreFirma(String nombreFirma) {
        this.nombreFirma = nombreFirma;
    }

    public String getMatriculaProfesional() {
        return matriculaProfesional;
    }

    public void setMatriculaProfesional(String matriculaProfesional) {
        this.matriculaProfesional = matriculaProfesional;
    }

    public Date getFechaElaboracion() {
        return fechaElaboracion;
    }

    public void setFechaElaboracion(Date fechaElaboracion) {
        this.fechaElaboracion = fechaElaboracion;
    }

    public BigDecimal getAvanceObraFisica() {
        return avanceObraFisica;
    }

    public void setAvanceObraFisica(BigDecimal avanceObraFisica) {
        this.avanceObraFisica = avanceObraFisica;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigDecimal getAct5Prg() {
        return act5Prg;
    }

    public void setAct5Prg(BigDecimal act5Prg) {
        this.act5Prg = act5Prg;
    }

    public BigDecimal getAct5Avc() {
        return act5Avc;
    }

    public void setAct5Avc(BigDecimal act5Avc) {
        this.act5Avc = act5Avc;
    }

    public BigDecimal getAct1Prg() {
        return act1Prg;
    }

    public void setAct1Prg(BigDecimal act1Prg) {
        this.act1Prg = act1Prg;
    }

    public BigDecimal getAct1Avc() {
        return act1Avc;
    }

    public void setAct1Avc(BigDecimal act1Avc) {
        this.act1Avc = act1Avc;
    }

    public BigDecimal getAct2Prg() {
        return act2Prg;
    }

    public void setAct2Prg(BigDecimal act2Prg) {
        this.act2Prg = act2Prg;
    }

    public BigDecimal getAct2Avc() {
        return act2Avc;
    }

    public void setAct2Avc(BigDecimal act2Avc) {
        this.act2Avc = act2Avc;
    }

    public BigDecimal getAct3Prg() {
        return act3Prg;
    }

    public void setAct3Prg(BigDecimal act3Prg) {
        this.act3Prg = act3Prg;
    }

    public BigDecimal getAct3Avc() {
        return act3Avc;
    }

    public void setAct3Avc(BigDecimal act3Avc) {
        this.act3Avc = act3Avc;
    }

    public BigDecimal getAct4Prg() {
        return act4Prg;
    }

    public void setAct4Prg(BigDecimal act4Prg) {
        this.act4Prg = act4Prg;
    }

    public BigDecimal getAct4Avc() {
        return act4Avc;
    }

    public void setAct4Avc(BigDecimal act4Avc) {
        this.act4Avc = act4Avc;
    }

    public BigDecimal getAct6Prg() {
        return act6Prg;
    }

    public void setAct6Prg(BigDecimal act6Prg) {
        this.act6Prg = act6Prg;
    }

    public BigDecimal getAct6Avc() {
        return act6Avc;
    }

    public void setAct6Avc(BigDecimal act6Avc) {
        this.act6Avc = act6Avc;
    }

    public BigDecimal getAct7Prg() {
        return act7Prg;
    }

    public void setAct7Prg(BigDecimal act7Prg) {
        this.act7Prg = act7Prg;
    }

    public BigDecimal getAct7Avc() {
        return act7Avc;
    }

    public void setAct7Avc(BigDecimal act7Avc) {
        this.act7Avc = act7Avc;
    }

    public BigDecimal getAct8Prg() {
        return act8Prg;
    }

    public void setAct8Prg(BigDecimal act8Prg) {
        this.act8Prg = act8Prg;
    }

    public BigDecimal getAct8Avc() {
        return act8Avc;
    }

    public void setAct8Avc(BigDecimal act8Avc) {
        this.act8Avc = act8Avc;
    }

    public BigDecimal getAct9Prg() {
        return act9Prg;
    }

    public void setAct9Prg(BigDecimal act9Prg) {
        this.act9Prg = act9Prg;
    }

    public BigDecimal getAct9Avc() {
        return act9Avc;
    }

    public void setAct9Avc(BigDecimal act9Avc) {
        this.act9Avc = act9Avc;
    }

    public BigDecimal getAct10Prg() {
        return act10Prg;
    }

    public void setAct10Prg(BigDecimal act10Prg) {
        this.act10Prg = act10Prg;
    }

    public BigDecimal getAct10Avc() {
        return act10Avc;
    }

    public void setAct10Avc(BigDecimal act10Avc) {
        this.act10Avc = act10Avc;
    }

    public BigDecimal getAct11Prg() {
        return act11Prg;
    }

    public void setAct11Prg(BigDecimal act11Prg) {
        this.act11Prg = act11Prg;
    }

    public BigDecimal getAct11Avc() {
        return act11Avc;
    }

    public void setAct11Avc(BigDecimal act11Avc) {
        this.act11Avc = act11Avc;
    }

    public BigDecimal getAct12Prg() {
        return act12Prg;
    }

    public void setAct12Prg(BigDecimal act12Prg) {
        this.act12Prg = act12Prg;
    }

    public BigDecimal getAct12Avc() {
        return act12Avc;
    }

    public void setAct12Avc(BigDecimal act12Avc) {
        this.act12Avc = act12Avc;
    }

    public BigDecimal getAct13Prg() {
        return act13Prg;
    }

    public void setAct13Prg(BigDecimal act13Prg) {
        this.act13Prg = act13Prg;
    }

    public BigDecimal getAct13Avc() {
        return act13Avc;
    }

    public void setAct13Avc(BigDecimal act13Avc) {
        this.act13Avc = act13Avc;
    }

    public BigDecimal getAct14Prg() {
        return act14Prg;
    }

    public void setAct14Prg(BigDecimal act14Prg) {
        this.act14Prg = act14Prg;
    }

    public BigDecimal getAct14Avc() {
        return act14Avc;
    }

    public void setAct14Avc(BigDecimal act14Avc) {
        this.act14Avc = act14Avc;
    }

    public BigDecimal getAct15Prg() {
        return act15Prg;
    }

    public void setAct15Prg(BigDecimal act15Prg) {
        this.act15Prg = act15Prg;
    }

    public BigDecimal getAct15Avc() {
        return act15Avc;
    }

    public void setAct15Avc(BigDecimal act15Avc) {
        this.act15Avc = act15Avc;
    }

    public BigDecimal getAct16Prg() {
        return act16Prg;
    }

    public void setAct16Prg(BigDecimal act16Prg) {
        this.act16Prg = act16Prg;
    }

    public BigDecimal getAct16Avc() {
        return act16Avc;
    }

    public void setAct16Avc(BigDecimal act16Avc) {
        this.act16Avc = act16Avc;
    }

    public BigDecimal getAct17Prg() {
        return act17Prg;
    }

    public void setAct17Prg(BigDecimal act17Prg) {
        this.act17Prg = act17Prg;
    }

    public BigDecimal getAct17Avc() {
        return act17Avc;
    }

    public void setAct17Avc(BigDecimal act17Avc) {
        this.act17Avc = act17Avc;
    }

    public BigDecimal getAct18Prg() {
        return act18Prg;
    }

    public void setAct18Prg(BigDecimal act18Prg) {
        this.act18Prg = act18Prg;
    }

    public BigDecimal getAct18Avc() {
        return act18Avc;
    }

    public void setAct18Avc(BigDecimal act18Avc) {
        this.act18Avc = act18Avc;
    }

    public BigDecimal getAct19Prg() {
        return act19Prg;
    }

    public void setAct19Prg(BigDecimal act19Prg) {
        this.act19Prg = act19Prg;
    }

    public BigDecimal getAct19Avc() {
        return act19Avc;
    }

    public void setAct19Avc(BigDecimal act19Avc) {
        this.act19Avc = act19Avc;
    }

    public BigDecimal getAct20Prg() {
        return act20Prg;
    }

    public void setAct20Prg(BigDecimal act20Prg) {
        this.act20Prg = act20Prg;
    }

    public BigDecimal getAct20Avc() {
        return act20Avc;
    }

    public void setAct20Avc(BigDecimal act20Avc) {
        this.act20Avc = act20Avc;
    }

    public BigDecimal getAct21Prg() {
        return act21Prg;
    }

    public void setAct21Prg(BigDecimal act21Prg) {
        this.act21Prg = act21Prg;
    }

    public BigDecimal getAct21Avc() {
        return act21Avc;
    }

    public void setAct21Avc(BigDecimal act21Avc) {
        this.act21Avc = act21Avc;
    }

    public BigDecimal getAct22Prg() {
        return act22Prg;
    }

    public void setAct22Prg(BigDecimal act22Prg) {
        this.act22Prg = act22Prg;
    }

    public BigDecimal getAct22Avc() {
        return act22Avc;
    }

    public void setAct22Avc(BigDecimal act22Avc) {
        this.act22Avc = act22Avc;
    }

    public BigDecimal getAct23Prg() {
        return act23Prg;
    }

    public void setAct23Prg(BigDecimal act23Prg) {
        this.act23Prg = act23Prg;
    }

    public BigDecimal getAct23Avc() {
        return act23Avc;
    }

    public void setAct23Avc(BigDecimal act23Avc) {
        this.act23Avc = act23Avc;
    }

    public BigDecimal getAct24Prg() {
        return act24Prg;
    }

    public void setAct24Prg(BigDecimal act24Prg) {
        this.act24Prg = act24Prg;
    }

    public BigDecimal getAct24Avc() {
        return act24Avc;
    }

    public void setAct24Avc(BigDecimal act24Avc) {
        this.act24Avc = act24Avc;
    }

    public BigDecimal getAct25Prg() {
        return act25Prg;
    }

    public void setAct25Prg(BigDecimal act25Prg) {
        this.act25Prg = act25Prg;
    }

    public BigDecimal getAct25Avc() {
        return act25Avc;
    }

    public void setAct25Avc(BigDecimal act25Avc) {
        this.act25Avc = act25Avc;
    }

    public BigDecimal getAct26Prg() {
        return act26Prg;
    }

    public void setAct26Prg(BigDecimal act26Prg) {
        this.act26Prg = act26Prg;
    }

    public BigDecimal getAct26Avc() {
        return act26Avc;
    }

    public void setAct26Avc(BigDecimal act26Avc) {
        this.act26Avc = act26Avc;
    }

    public BigDecimal getAct27Prg() {
        return act27Prg;
    }

    public void setAct27Prg(BigDecimal act27Prg) {
        this.act27Prg = act27Prg;
    }

    public BigDecimal getAct27Avc() {
        return act27Avc;
    }

    public void setAct27Avc(BigDecimal act27Avc) {
        this.act27Avc = act27Avc;
    }

    public BigDecimal getAct28Prg() {
        return act28Prg;
    }

    public void setAct28Prg(BigDecimal act28Prg) {
        this.act28Prg = act28Prg;
    }

    public BigDecimal getAct28Avc() {
        return act28Avc;
    }

    public void setAct28Avc(BigDecimal act28Avc) {
        this.act28Avc = act28Avc;
    }

    public BigDecimal getAct29Prg() {
        return act29Prg;
    }

    public void setAct29Prg(BigDecimal act29Prg) {
        this.act29Prg = act29Prg;
    }

    public BigDecimal getAct29Avc() {
        return act29Avc;
    }

    public void setAct29Avc(BigDecimal act29Avc) {
        this.act29Avc = act29Avc;
    }

    public BigDecimal getAct30Prg() {
        return act30Prg;
    }

    public void setAct30Prg(BigDecimal act30Prg) {
        this.act30Prg = act30Prg;
    }

    public BigDecimal getAct30Avc() {
        return act30Avc;
    }

    public void setAct30Avc(BigDecimal act30Avc) {
        this.act30Avc = act30Avc;
    }

    public BigDecimal getAct31Prg() {
        return act31Prg;
    }

    public void setAct31Prg(BigDecimal act31Prg) {
        this.act31Prg = act31Prg;
    }

    public BigDecimal getAct31Avc() {
        return act31Avc;
    }

    public void setAct31Avc(BigDecimal act31Avc) {
        this.act31Avc = act31Avc;
    }

    public BigDecimal getAct32Prg() {
        return act32Prg;
    }

    public void setAct32Prg(BigDecimal act32Prg) {
        this.act32Prg = act32Prg;
    }

    public BigDecimal getAct32Avc() {
        return act32Avc;
    }

    public void setAct32Avc(BigDecimal act32Avc) {
        this.act32Avc = act32Avc;
    }

    public BigDecimal getAct33Prg() {
        return act33Prg;
    }

    public void setAct33Prg(BigDecimal act33Prg) {
        this.act33Prg = act33Prg;
    }

    public BigDecimal getAct33Avc() {
        return act33Avc;
    }

    public void setAct33Avc(BigDecimal act33Avc) {
        this.act33Avc = act33Avc;
    }

    public BigDecimal getAct34Prg() {
        return act34Prg;
    }

    public void setAct34Prg(BigDecimal act34Prg) {
        this.act34Prg = act34Prg;
    }

    public BigDecimal getAct34Avc() {
        return act34Avc;
    }

    public void setAct34Avc(BigDecimal act34Avc) {
        this.act34Avc = act34Avc;
    }

    public BigDecimal getAct35Prg() {
        return act35Prg;
    }

    public void setAct35Prg(BigDecimal act35Prg) {
        this.act35Prg = act35Prg;
    }

    public BigDecimal getAct35Avc() {
        return act35Avc;
    }

    public void setAct35Avc(BigDecimal act35Avc) {
        this.act35Avc = act35Avc;
    }

    public BigDecimal getAct36Prg() {
        return act36Prg;
    }

    public void setAct36Prg(BigDecimal act36Prg) {
        this.act36Prg = act36Prg;
    }

    public BigDecimal getAct36Avc() {
        return act36Avc;
    }

    public void setAct36Avc(BigDecimal act36Avc) {
        this.act36Avc = act36Avc;
    }

    public BigDecimal getAct37Prg() {
        return act37Prg;
    }

    public void setAct37Prg(BigDecimal act37Prg) {
        this.act37Prg = act37Prg;
    }

    public BigDecimal getAct37Avc() {
        return act37Avc;
    }

    public void setAct37Avc(BigDecimal act37Avc) {
        this.act37Avc = act37Avc;
    }

    public BigDecimal getAct38Prg() {
        return act38Prg;
    }

    public void setAct38Prg(BigDecimal act38Prg) {
        this.act38Prg = act38Prg;
    }

    public BigDecimal getAct38Avc() {
        return act38Avc;
    }

    public void setAct38Avc(BigDecimal act38Avc) {
        this.act38Avc = act38Avc;
    }

    public BigDecimal getAct39Prg() {
        return act39Prg;
    }

    public void setAct39Prg(BigDecimal act39Prg) {
        this.act39Prg = act39Prg;
    }

    public BigDecimal getAct39Avc() {
        return act39Avc;
    }

    public void setAct39Avc(BigDecimal act39Avc) {
        this.act39Avc = act39Avc;
    }

    public BigDecimal getAct40Prg() {
        return act40Prg;
    }

    public void setAct40Prg(BigDecimal act40Prg) {
        this.act40Prg = act40Prg;
    }

    public BigDecimal getAct40Avc() {
        return act40Avc;
    }

    public void setAct40Avc(BigDecimal act40Avc) {
        this.act40Avc = act40Avc;
    }

    public BigDecimal getAct41Prg() {
        return act41Prg;
    }

    public void setAct41Prg(BigDecimal act41Prg) {
        this.act41Prg = act41Prg;
    }

    public BigDecimal getAct41Avc() {
        return act41Avc;
    }

    public void setAct41Avc(BigDecimal act41Avc) {
        this.act41Avc = act41Avc;
    }

    public BigDecimal getAct42Prg() {
        return act42Prg;
    }

    public void setAct42Prg(BigDecimal act42Prg) {
        this.act42Prg = act42Prg;
    }

    public BigDecimal getAct42Avc() {
        return act42Avc;
    }

    public void setAct42Avc(BigDecimal act42Avc) {
        this.act42Avc = act42Avc;
    }

    public BigDecimal getAct43Prg() {
        return act43Prg;
    }

    public void setAct43Prg(BigDecimal act43Prg) {
        this.act43Prg = act43Prg;
    }

    public BigDecimal getAct43Avc() {
        return act43Avc;
    }

    public void setAct43Avc(BigDecimal act43Avc) {
        this.act43Avc = act43Avc;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public Date getFechaInformacionReporte() {
        return fechaInformacionReporte;
    }

    public void setFechaInformacionReporte(Date fechaInformacionReporte) {
        this.fechaInformacionReporte = fechaInformacionReporte;
    }

    public Date getFechaInformacionContrato() {
        return fechaInformacionContrato;
    }

    public void setFechaInformacionContrato(Date fechaInformacionContrato) {
        this.fechaInformacionContrato = fechaInformacionContrato;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (identificador != null ? identificador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RptReporte)) {
            return false;
        }
        RptReporte other = (RptReporte) object;
        if ((this.identificador == null && other.identificador != null) || (this.identificador != null && !this.identificador.equals(other.identificador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.RptReporte[ identificador=" + identificador + " ]";
    }
    
}
