package sometestapp;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class DataVisualizationHelper{    
    public static void drawData(String chartTitle, String xLabel, String yLabel, int[][] data){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for(int i = 0; i < data.length; i++){
            System.out.println("dataset adds: " + data[i][1] + " " + data[i][0]);
            dataset.addValue(data[i][1], "", data[i][0] + "");
        }
        
        JFreeChart chart = ChartFactory.createLineChart(chartTitle, xLabel, yLabel, 
                dataset, PlotOrientation.VERTICAL, true, true, false);
        ChartPanel panel = new ChartPanel(chart);
        panel.setPreferredSize(new java.awt.Dimension(860, 367));
        ChartScreen screen = new ChartScreen(chartTitle);
        screen.setContentPane(panel);
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
