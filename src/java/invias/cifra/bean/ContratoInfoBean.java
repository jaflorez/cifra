/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.bean;

import invias.cifra.entity.Contrato;
import invias.cifra.entity.ContratoActividad;
import invias.cifra.entity.ContratoFinancieroObra;
import invias.cifra.entity.RptReporte;
import invias.cifra.exception.CifraException;
import invias.cifra.model.ContratoSrv;
import invias.cifra.model.RptSrv;
import invias.cifra.util.CifraUtil;
import invias.cifra.util.FacturaItemUtil;
import invias.cifra.util.SeleccionItem;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author jaflorez
 */
@ManagedBean
@ViewScoped
public class ContratoInfoBean extends BaseBean {

    private LineChartModel dateModel;
    private Contrato contrato;
    private List<RptReporte> rptReportes;
    private Integer curIdReporte;
    private TreeNode root;
    private RptReporte curReporte;
    private int idVentana;
    private TreeNode selectedNode;
    private List<FacturaItemUtil> facturaItemUtils;
    private Integer idTramo;
    private String geometria;
    private Date fechaFinReporte;

    @PostConstruct
    public void init() {
        ContratoSrv contratoSrv = new ContratoSrv();
        RptSrv rptSrv = new RptSrv();
        CifraSessionBean sgbSes = CifraUtil.getSessionBean();
        if (sgbSes.getContratoId() != null && sgbSes.getContratoId() > 0) {
            try {
                this.contrato = contratoSrv.loadContratoById(sgbSes.getContratoId());
                this.rptReportes = rptSrv.rptReporteContratoLst(sgbSes.getContratoId());
                this.fillRootNode();
                if(curReporte != null){
                    if(this.contrato.getContObraFechaTermiCalculada() != null ){
                        if(this.contrato.getContObraFechaTermiCalculada().compareTo(this.curReporte.getReportehasta())>0){
                            this.fechaFinReporte = this.contrato.getContObraFechaTermiCalculada();
                        }
                        else{
                            this.fechaFinReporte = this.curReporte.getReportehasta();
                        }
                    }
                    else{
                        if(this.contrato.getContObraFechaTermi().compareTo(this.curReporte.getReportehasta())>0){
                            this.fechaFinReporte = this.contrato.getContObraFechaTermi();
                        }
                        else{
                            this.fechaFinReporte = this.curReporte.getReportehasta();
                        }
                    
                    }
                }
                    
                
                
            } catch (CifraException ex) {
                Logger.getLogger(ContratoInfoBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private double getValorActividadFromReporte(RptReporte reporte, Integer idActividad) {
        try {
            String nameMethod = "getAct" + idActividad + "Avc";
            Class aClass = reporte.getClass();
            Method metodo = aClass.getDeclaredMethod(nameMethod, null);
            BigDecimal valor = (BigDecimal) metodo.invoke(reporte, null);
            if (valor != null) {
                return valor.doubleValue();
            } else {
                return 0;
            }
        } catch (SecurityException | IllegalArgumentException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ex) {
            Logger.getLogger(ContratoInfoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0.0;

    }

    public double valorTotalActividad(Integer idActividad) {
        try {
            if (curReporte != null) {
                String nameMethod = "getAct" + idActividad + "Avc";
                Class aClass = curReporte.getClass();
                Method metodo = aClass.getDeclaredMethod(nameMethod, null);
                BigDecimal valor = (BigDecimal) metodo.invoke(curReporte, null);
                if (valor != null) {
                    return valor.doubleValue();
                } else {
                    return 0;
                }
            }
            return 0;
        } catch (SecurityException ex) {
            Logger.getLogger(ContratoInfoBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(ContratoInfoBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ContratoInfoBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(ContratoInfoBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(ContratoInfoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0.0;
    }

    private void fillRootNode() {
        Calendar cal = Calendar.getInstance();
        int curYear = 0;
        int curMonth = -1;
        int year;
        int month;
        root = new DefaultTreeNode("Root", null);
        TreeNode nodeYear = null;
        TreeNode nodeMonth = null;
        SeleccionItem item;
        for (RptReporte reporte : this.rptReportes) {
            cal.setTime(reporte.getReportehasta());
            year = cal.get(Calendar.YEAR);
            month = cal.get(Calendar.MONTH);
            if (year != curYear) {
                item = new SeleccionItem(0, Integer.toString(year));
                nodeYear = new DefaultTreeNode(item, root);
                curYear = year;
                curMonth = -1;
            }
            if (month != curMonth) {
                String nameMes = CifraUtil.getNombreMes(month + 1);
                item = new SeleccionItem(0, nameMes);
                nodeMonth = new DefaultTreeNode(item, nodeYear);
                curMonth = month;
            }
            DateFormat df = new SimpleDateFormat("yy-MM-dd");
            String s = df.format(reporte.getReportehasta()) + "-Sm:" + reporte.getNumerosemana() + " Rp:" + reporte.getNumeroreporte();
            item = new SeleccionItem(reporte.getIdentificador(), s);
            TreeNode no = new DefaultTreeNode(item, nodeMonth);
            this.curReporte = reporte;
        }

    }

    public void onNodeSelect() {
        if (this.selectedNode == null) {
            return;
        }
        SeleccionItem item = (SeleccionItem) this.selectedNode.getData();
        if (item.getId() > 0) {
            for (RptReporte reporte : this.rptReportes) {
                if (reporte.getIdentificador().equals(item.getId())) {
                    this.curReporte = reporte;
                    this.addMessage("Actualizando", "Actualizando la informacion del contrato " + item.getDescripcion(), FacesMessage.SEVERITY_INFO);
                    return;
                }
            }
        } else {
            this.addMessage("Alerta", "Seleccion una fecha", FacesMessage.SEVERITY_WARN);
        }
    }

    public void asignarCurGeomTramo(Integer idTramo) {
        this.idTramo = idTramo;
    }

    public void interTotalFacturado() {
        this.idVentana = 416;
        this.dateModel = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Facurado de la interventoria");
        this.facturaItemUtils = new ArrayList<>();
        FacturaItemUtil fiu;
        double acumulado = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        for (RptReporte reporte : this.rptReportes) {
            if (reporte.getIntPresentoFactura() != null) {
                if (reporte.getIntPresentoFactura()) {
                    fiu = new FacturaItemUtil(reporte.getNumerosemana(), reporte.getNumeroreporte(), reporte.getIntFechaFactura(), reporte.getIntValorFactura());
                    this.facturaItemUtils.add(fiu);
                    if (reporte.getIntValorFactura() != null) {
                        acumulado += (reporte.getIntValorFactura().doubleValue() / 1000000);
                    }
                    series1.set(format.format(reporte.getReportehasta()), acumulado);
                }
            }
        }
        dateModel.addSeries(series1);
        dateModel.getAxis(AxisType.Y).setLabel("Facturado (millones)");
        dateModel.setZoom(true);
        DateAxis axis = new DateAxis("Fecha");
        if (this.contrato.getContObraFechaTermiCalculada() != null) {
            axis.setMax(format.format(CifraUtil.fechaMasDias(this.contrato.getContObraFechaTermiCalculada(), 60)));

        } else {
            axis.setMax(format.format(CifraUtil.fechaMasDias(this.contrato.getContObraFechaTermi(), 60)));

        }
        axis.setMin(format.format(this.contrato.getContObraActaInicio()));
        axis.setTickAngle(-50);
        //TODO Implementar fecha terminacion interventoria calculada
        axis.setMax(format.format(this.contrato.getContInterFechaTermi()));
        axis.setTickFormat("%b %#d, %y");
        dateModel.setLegendPosition("e");
        dateModel.getAxes().put(AxisType.X, axis);

    }

    /**
     * Creates a new instance of ContratoInfoBean
     */
    public ContratoInfoBean() {

    }

    public void obraAvance() {
        this.idVentana = 511;

        this.dateModel = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Porcentaje de avance(%)");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        double acumulado = 0;
        FacturaItemUtil fiu;
        this.facturaItemUtils = new ArrayList<>();
        for (RptReporte reporte : this.rptReportes) {

            if (reporte.getObrAvanceFisico() != null) {
                if (reporte.getObrFacturadoProgramaInversion() != null) {
                    series1.set(format.format(reporte.getReportehasta()), reporte.getObrAvanceFisico().doubleValue());
                    fiu = new FacturaItemUtil(reporte.getNumerosemana(), reporte.getNumeroreporte(), reporte.getIntFechaFactura(), reporte.getObrAvanceFisico());
                    this.facturaItemUtils.add(fiu);
                }
            }
        }
        dateModel.addSeries(series1);
        DateAxis axis = new DateAxis("Fecha");
        axis.setTickAngle(-50);
        if (this.contrato.getContObraFechaTermiCalculada() != null) {
            axis.setMax(format.format(this.contrato.getContObraFechaTermiCalculada()));
        } else {
            axis.setMax(format.format(this.contrato.getContObraFechaTermi()));
        }
        axis.setMin(format.format(this.contrato.getContObraActaInicio()));
        axis.setTickFormat("%b %#d, %y");
        dateModel.setLegendPosition("e");
        dateModel.getAxis(AxisType.Y).setLabel("Valores(%)");
        dateModel.setZoom(true);
        dateModel.getAxes().put(AxisType.X, axis);
    }

    public void obraProgInvTotalFacturado() {

        this.idVentana = 445;
        /*Se define el programa de inversion*/
        this.dateModel = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();
        /*Programa de inversion*/
        series1.setLabel("Programa de inversion");
        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Facturado por inversion");
        LineChartSeries series3 = new LineChartSeries();
        series3.setLabel("Total facturado");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        double acumulado = 0;
        double valor = 0;
        FacturaItemUtil fiu;
        this.facturaItemUtils = new ArrayList<>();
        for (ContratoFinancieroObra cfo : this.contrato.getContratoFinancieroObraList()) {
            acumulado += (cfo.getValor().doubleValue() / 1000000);
            series1.set(cfo.getAno().toString() + "-" + cfo.getCorte().toString() + "-01", acumulado);
        }
        dateModel.addSeries(series1);
        for (RptReporte reporte : this.rptReportes) {
            if (reporte.getObrPresentoFactura() != null) {
                if (reporte.getObrPresentoFactura()) {
                    if (reporte.getObrFacturadoProgramaInversion() != null) {
                        valor = (reporte.getObrFacturadoProgramaInversion().doubleValue() / 1000000);
                        series2.set(format.format(reporte.getReportehasta()), valor);
                    }
                    if (reporte.getObrFacturadoTotal() != null) {
                        valor = (reporte.getObrFacturadoTotal().doubleValue() / 1000000);

                        series3.set(format.format(reporte.getReportehasta()), valor);
                        fiu = new FacturaItemUtil(reporte.getNumerosemana(), reporte.getNumeroreporte(), reporte.getIntFechaFactura(), reporte.getObrValorFacturado());
                        this.facturaItemUtils.add(fiu);
                    }
                }
            }
        }
        dateModel.addSeries(series2);
        dateModel.addSeries(series3);
        DateAxis axis = new DateAxis("Fecha");
        axis.setTickAngle(-50);
        if (this.contrato.getContObraFechaTermiCalculada() != null) {
            axis.setMax(format.format(CifraUtil.fechaMasDias(this.contrato.getContObraFechaTermiCalculada(), 60)));
        } else {
            axis.setMax(format.format(CifraUtil.fechaMasDias(this.contrato.getContObraFechaTermi(), 60)));
        }
        axis.setTickFormat("%b %#d, %y");
        dateModel.setLegendPosition("e");
        dateModel.getAxis(AxisType.Y).setLabel("Valores (millones)");
        dateModel.setZoom(true);
        dateModel.getAxes().put(AxisType.X, axis);

    }

    public void obraProgInvFacturadoInversion() {
        this.idVentana = 445;
        /*Se define el programa de inversion*/
        this.dateModel = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();
        /*Programa de inversion*/
        series1.setLabel("Programa de inversion");
        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Facturado por inversion");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        double acumulado = 0;
        double valor = 0;
        FacturaItemUtil fiu;
        this.facturaItemUtils = new ArrayList<>();
        for (ContratoFinancieroObra cfo : this.contrato.getContratoFinancieroObraList()) {
            acumulado += (cfo.getValor().doubleValue() / 1000000);
            series1.set(cfo.getAno().toString() + "-" + cfo.getCorte().toString() + "-01", acumulado);
        }
        dateModel.addSeries(series1);
        for (RptReporte reporte : this.rptReportes) {
            if (reporte.getObrPresentoFactura() != null) {
                if (reporte.getObrPresentoFactura()) {
                    if (reporte.getObrFacturadoProgramaInversion() != null) {
                        valor = (reporte.getObrFacturadoProgramaInversion().doubleValue() / 1000000);
                        series2.set(format.format(reporte.getReportehasta()), valor);
                        fiu = new FacturaItemUtil(reporte.getNumerosemana(), reporte.getNumeroreporte(), reporte.getIntFechaFactura(), reporte.getObrValorFacturado());
                        this.facturaItemUtils.add(fiu);
                    }
                }
            }
        }
        dateModel.addSeries(series2);
        DateAxis axis = new DateAxis("Fecha");
        axis.setTickAngle(-50);
        if (this.contrato.getContObraFechaTermiCalculada() != null) {
            axis.setMax(format.format(CifraUtil.fechaMasDias(this.contrato.getContObraFechaTermiCalculada(), 60)));
        } else {
            axis.setMax(format.format(CifraUtil.fechaMasDias(this.contrato.getContObraFechaTermi(), 60)));
        }
        axis.setTickFormat("%b %#d, %y");
        dateModel.setLegendPosition("e");
        dateModel.getAxis(AxisType.Y).setLabel("Valores (millones)");
        dateModel.setZoom(true);
        dateModel.getAxes().put(AxisType.X, axis);
    }

    public void obraProgramaInversion() {
        this.idVentana = 444;
        /*Se define el programa de inversion*/
        this.dateModel = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();
        /*Programa de inversion*/
        series1.setLabel("Programa de inversion");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        double acumulado = 0;
        double valor = 0;
        for (ContratoFinancieroObra cfo : this.contrato.getContratoFinancieroObraList()) {
            acumulado += (cfo.getValor().doubleValue() / 1000000);
            series1.set(cfo.getAno().toString() + "-" + cfo.getCorte().toString() + "-01", acumulado);
        }
        dateModel.addSeries(series1);
        DateAxis axis = new DateAxis("Fecha");
        axis.setTickAngle(-50);
        if (this.contrato.getContObraFechaTermiCalculada() != null) {
            axis.setMax(format.format(CifraUtil.fechaMasDias(this.contrato.getContObraFechaTermiCalculada(), 60)));
        } else {
            axis.setMax(format.format(CifraUtil.fechaMasDias(this.contrato.getContObraFechaTermi(), 60)));
        }
        axis.setTickFormat("%b %#d, %y");
        dateModel.setLegendPosition("e");
        dateModel.getAxis(AxisType.Y).setLabel("Valores (millones)");
        dateModel.setZoom(true);
        dateModel.getAxes().put(AxisType.X, axis);
    }

    public void calcularPredios() {
        this.idVentana = 531;
        this.dateModel = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();
        LineChartSeries series2 = new LineChartSeries();
        LineChartSeries series3 = new LineChartSeries();
        LineChartSeries series4 = new LineChartSeries();
        series1.setLabel("Predios a adquirir");
        series2.setLabel("Fichas aprobadas");
        series3.setLabel("Avaluos Aprobados");
        series4.setLabel("Predios Adquiridos");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        this.facturaItemUtils = new ArrayList<>();
        FacturaItemUtil fiu;
        double valor = 0;
        for (RptReporte reporte : this.rptReportes) {
            fiu = new FacturaItemUtil(reporte.getNumerosemana(), reporte.getNumeroreporte(), reporte.getReportehasta(), BigDecimal.valueOf(valor));
            this.facturaItemUtils.add(fiu);
            series1.set(format.format(reporte.getReportehasta()), reporte.getGeprPredadquirir());
            series2.set(format.format(reporte.getReportehasta()), reporte.getGeprFichasaprob());
            series3.set(format.format(reporte.getReportehasta()), reporte.getGeprAvaluos());
            series4.set(format.format(reporte.getReportehasta()), reporte.getGeprPredadquiridos());
        }
        dateModel.addSeries(series1);
        dateModel.addSeries(series2);
        dateModel.addSeries(series3);
        dateModel.addSeries(series4);
        DateAxis axis = new DateAxis("Fecha");
        axis.setTickAngle(-50);
        if (this.contrato.getContObraFechaTermiCalculada() != null) {
            axis.setMax(format.format(CifraUtil.fechaMasDias(this.contrato.getContObraFechaTermiCalculada(), 60)));
        } else {
            axis.setMax(format.format(CifraUtil.fechaMasDias(this.contrato.getContObraFechaTermi(), 60)));
        }

        axis.setTickFormat("%b %#d, %y");
        dateModel.setLegendPosition("e");
        dateModel.getAxis(AxisType.Y).setLabel("Cantidad");
        dateModel.setZoom(true);
        dateModel.getAxes().put(AxisType.X, axis);
    }

    public void calcularEmpleos() {
        this.idVentana = 531;
        this.dateModel = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();
        LineChartSeries series2 = new LineChartSeries();
        LineChartSeries series3 = new LineChartSeries();
        LineChartSeries series4 = new LineChartSeries();
        LineChartSeries series5 = new LineChartSeries();
        series1.setLabel("Empleos Generados");
        series2.setLabel("Empleos Directos");
        series3.setLabel("Empleos Indirectos");
        series4.setLabel("Socializaciones");
        series5.setLabel("Capacitaciones");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        this.facturaItemUtils = new ArrayList<>();
        FacturaItemUtil fiu;
        double valor = 0;
        for (RptReporte reporte : this.rptReportes) {
            fiu = new FacturaItemUtil(reporte.getNumerosemana(), reporte.getNumeroreporte(), reporte.getReportehasta(), BigDecimal.valueOf(valor));
            this.facturaItemUtils.add(fiu);
            series1.set(format.format(reporte.getReportehasta()), reporte.getGesoEmpgen());
            series2.set(format.format(reporte.getReportehasta()), reporte.getGesoEmpdirgen());
            series3.set(format.format(reporte.getReportehasta()), reporte.getGesoEmpindirgen());
            series4.set(format.format(reporte.getReportehasta()), reporte.getGesoNosocializaciones());
            series5.set(format.format(reporte.getReportehasta()), reporte.getGesoNocapacitaciones());
        }
        dateModel.addSeries(series1);
        dateModel.addSeries(series2);
        dateModel.addSeries(series3);
        dateModel.addSeries(series4);
        dateModel.addSeries(series5);
        DateAxis axis = new DateAxis("Fecha");
        axis.setTickAngle(-50);
        if (this.contrato.getContObraFechaTermiCalculada() != null) {
            axis.setMax(format.format(CifraUtil.fechaMasDias(this.contrato.getContObraFechaTermiCalculada(), 60)));
        } else {
            axis.setMax(format.format(CifraUtil.fechaMasDias(this.contrato.getContObraFechaTermi(), 60)));
        }

        axis.setTickFormat("%b %#d, %y");
        dateModel.setLegendPosition("e");
        dateModel.getAxis(AxisType.Y).setLabel("Cantidad");
        dateModel.setZoom(true);
        dateModel.getAxes().put(AxisType.X, axis);
    }

    public void obraAvanceActividad(int actividad) {
        this.idVentana = 521;
        /*Se define el programa de inversion*/
        this.dateModel = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Avance de " + this.getNombreActividad(actividad));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        this.facturaItemUtils = new ArrayList<>();
        FacturaItemUtil fiu;
        double valor = 0;
        for (RptReporte reporte : this.rptReportes) {
            valor = this.getValorActividadFromReporte(reporte, actividad);
            fiu = new FacturaItemUtil(reporte.getNumerosemana(), reporte.getNumeroreporte(), reporte.getReportehasta(), BigDecimal.valueOf(valor));
            this.facturaItemUtils.add(fiu);
            series1.set(format.format(reporte.getReportehasta()), valor);
        }
        dateModel.addSeries(series1);
        DateAxis axis = new DateAxis("Fecha");
        axis.setTickAngle(-50);
        if (this.contrato.getContObraFechaTermiCalculada() != null) {
            axis.setMax(format.format(CifraUtil.fechaMasDias(this.contrato.getContObraFechaTermiCalculada(), 60)));
        } else {
            axis.setMax(format.format(CifraUtil.fechaMasDias(this.contrato.getContObraFechaTermi(), 60)));
        }

        axis.setTickFormat("%b %#d, %y");
        dateModel.setLegendPosition("e");
        dateModel.getAxis(AxisType.Y).setLabel("Avance");
        dateModel.setZoom(true);
        dateModel.getAxes().put(AxisType.X, axis);

    }

    private String getNombreActividad(int idActividad) {
        String nombre = "";
        for (ContratoActividad ca : this.contrato.getContratoActividadList()) {
            if (ca.getFkActividad().getIdentificador() == idActividad) {
                return ca.getFkActividad().getActividad();
            }
        }
        return nombre;
    }

    public void setCurVentana(int idVentana) {
        this.idVentana = idVentana;
    }

    public void showActividadDetalle(int idActividad) {
        this.idVentana = 2;

    }

    public LineChartModel getDateModel() {
        return dateModel;
    }

    public void setDateModel(LineChartModel dateModel) {
        this.dateModel = dateModel;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public List<RptReporte> getRptReportes() {
        return rptReportes;
    }

    public void setRptReportes(List<RptReporte> rptReportes) {
        this.rptReportes = rptReportes;
    }

    public Integer getCurIdReporte() {
        return curIdReporte;
    }

    public void setCurIdReporte(Integer curIdReporte) {
        this.curIdReporte = curIdReporte;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public RptReporte getCurReporte() {
        return curReporte;
    }

    public void setCurReporte(RptReporte curReporte) {
        this.curReporte = curReporte;
    }

    public int getIdVentana() {
        return idVentana;
    }

    public void setIdVentana(int idVentana) {
        this.idVentana = idVentana;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public List<FacturaItemUtil> getFacturaItemUtils() {
        return facturaItemUtils;
    }

    public void setFacturaItemUtils(List<FacturaItemUtil> facturaItemUtils) {
        this.facturaItemUtils = facturaItemUtils;
    }

    public Integer getIdTramo() {
        return idTramo;
    }

    public void setIdTramo(Integer idTramo) {
        this.idTramo = idTramo;
    }

    public String getGeometria() {
        return geometria;
    }

    public void setGeometria(String geometria) {
        this.geometria = geometria;
    }

}
