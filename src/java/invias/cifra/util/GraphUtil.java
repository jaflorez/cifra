/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invias.cifra.util;


import invias.cifra.dot.RegistroAvanceDOT;
import java.awt.Color;
import java.awt.Font;
import java.io.*;
import java.text.NumberFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.axis.AxisSpace;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.ui.TextAnchor;
/**
 *
 * @author jaflorez
 */
public final class GraphUtil {

    static String GetNomFileImageBarChar(int width, int height, List<RegistroAvanceDOT> dataActividadLst, int numCol) {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int i=1;
        float valor;
        
        
        for(RegistroAvanceDOT dOT:dataActividadLst){
            valor=0;
            if(dOT.getProgramado() != null && dOT.getAcumulado() != null){
                valor = ((float) (100f * dOT.getAcumulado().floatValue()))/dOT.getProgramado().floatValue();
            }
            dataset.addValue(valor,Integer.toString(i), "");
            i++;
        }
        JFreeChart barChart = ChartFactory.createBarChart("","", "",dataset, PlotOrientation.HORIZONTAL,false, true, false);
        final CategoryPlot plot =  (CategoryPlot) barChart.getPlot();
        ValueAxis range = plot.getRangeAxis();
        range.setVisible(false);
        AxisSpace axisSpace=new AxisSpace();
        axisSpace.setRight(1);
        axisSpace.setBottom(1);
        axisSpace.setTop(1);
        axisSpace.setLeft(1);
        plot.setFixedDomainAxisSpace(axisSpace);            
        plot.getRangeAxis().setUpperMargin(0.0);
        plot.getRangeAxis().setLowerMargin(0.0);
        
        CategoryItemRenderer renderer = plot.getRenderer(); 
        CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator("{2} %", NumberFormat.getInstance()); 
        renderer.setSeriesItemLabelsVisible(0, true);
        renderer.setSeriesPositiveItemLabelPosition(0, new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.CENTER, TextAnchor.CENTER, 0));
        
        plot.setBackgroundPaint(Color.white);
        //final BarRenderer renderer = (BarRenderer) plot.getRenderer();
        ValueAxis yAxis = plot.getRangeAxis();
        yAxis.setRange(0.0, 100.0);
//        renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator()); 
        
        
        renderer.setItemLabelsVisible(false);
        renderer.setItemLabelFont(new Font("SansSerif", Font.PLAIN, 9));
        renderer.setItemLabelPaint(null);
        renderer.setSeriesItemLabelPaint(0, renderer.getSeriesPaint(0));
        renderer.setSeriesItemLabelPaint(1, renderer.getSeriesPaint(1));        
        String nomFile;
        nomFile =  CifraUtil.pathToWriteTmpFile() + "/img"+CifraUtil.getRamdomString(12)+".jpeg";
        File BarChart = new File(nomFile);
        try {
            ChartUtilities.saveChartAsJPEG(BarChart, barChart, width, height);
        } catch (IOException ex) {
            Logger.getLogger("Error GraphUtil").log(Level.SEVERE, null, ex);
        }
        return nomFile;

    }

}
