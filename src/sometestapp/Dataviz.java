package sometestapp;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javafx.scene.chart.LineChart;
import javax.swing.JDesktopPane;
import javax.swing.JLayeredPane;
import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYLineAnnotation;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.AbstractCategoryItemRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.DefaultXYItemRenderer;
import org.jfree.chart.renderer.xy.XYBubbleRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYStepRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

public class Dataviz{    
    public static void drawData(String chartTitle, String xLabel, String yLabel, double[][] data, HashMap<String, int[]> labels){
        XYSeries series1 = new XYSeries("Original");
        HashMap<Integer, String> labelindices = new HashMap<>();
        HashMap<String, XYSeries> labelseries = new HashMap<>();
        XYSeries seriesn = new XYSeries("");
        seriesn.add(0,0);
        
        Iterator it = labels.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, int[]> pair = (Map.Entry)it.next();
            String key = pair.getKey();
            
            labelseries.put(key, new XYSeries(key));
            for(int val : pair.getValue()){
                labelindices.put(val, key);
            }
            it.remove();
        }
        
        for(int i = 0; i < data.length; i++){
            series1.add(data[i][0], data[i][1]);        
            
            if(labelindices.containsKey(i)){
                labelseries.get(labelindices.get(i)).add(data[i][0], data[i][1]);
            }
            if(i+1 == data.length){
                seriesn.add(data[i][0],0);
            }
        }
        
        XYSeriesCollection dataset1 = new XYSeriesCollection();
        XYSeriesCollection dataset2 = new XYSeriesCollection();
        
        it = labelseries.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, XYSeries> pair = (Map.Entry)it.next();
            dataset1.addSeries(pair.getValue());
            it.remove();
        }
        dataset1.addSeries(series1);
        dataset2.addSeries(seriesn);

        JFreeChart chart1 = ChartFactory.createXYLineChart("", "", "", 
                dataset1, PlotOrientation.VERTICAL, false, false, false);
        JFreeChart chart2 = ChartFactory.createXYLineChart("", "", "", 
                dataset2, PlotOrientation.VERTICAL, false, false, false);
        
//        chart1.
                
//        chart2.setBackgroundPaint(null);
        chart2.getPlot().setBackgroundAlpha(0.0f);
//        chart2.setBackgroundPaint(null);
        
        XYPlot plot = (XYPlot) chart1.getPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setDrawSeriesLineAsPath(false);
        renderer.setLinesVisible(false);
//        renderer.setBaseLinesVisible(false);
        plot.setRenderer(renderer);
        
        ChartPanel panel1 = new ChartPanel(chart1);
        ChartPanel panel2 = new ChartPanel(chart2);

        panel1.setBounds(0,0,860, 367);
        panel2.setBounds(0,0,860, 367);
        
        panel2.setOpaque(false);
        
        JLayeredPane layer = new JLayeredPane();
        layer.add(panel1, new Integer(0));
//        layer.add(panel2, new Integer(1));
        layer.setPreferredSize(new java.awt.Dimension(860, 367));
        
        ChartScreen screen = new ChartScreen(chartTitle);
        screen.setContentPane(layer);
        screen.pack();
        RefineryUtilities.centerFrameOnScreen(screen);
        screen.setVisible(true);
    }
    
    private static class ChartScreen extends ApplicationFrame{
        
        public ChartScreen(String title) {
            super(title);
        }
        
    }
}
