package jchart;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing how to create a pie chart using 
 * data from a {@link DefaultPieDataset}.
 */
public class piechart extends ApplicationFrame {

    /**
     * Default constructor.
     *
     * @param title  the frame title.
     */
	
    public piechart(String title) {
        super(title);
        
    }

    /**
     * Creates a sample dataset.
     * 
     * @return A sample dataset.
     */
    private static PieDataset createDataset(double total, double avild) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        double favild = (int)(avild/total*100);
        double fused = (int)(100-favild);
        dataset.setValue("Used", new Double(fused));
        dataset.setValue("Avail", new Double(favild));
        return dataset;        
    }
    private static PieDataset createDatasetforcpu(double used) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        double favild = (int)(100-used);
        double fused = (int)(used);
        dataset.setValue("Used", new Double(fused));
        dataset.setValue("Avail", new Double(favild));
        return dataset;        
    }
    /**
     * Creates a chart.
     * 
     * @param dataset  the dataset.
     * 
     * @return A chart.
     */
    private static JFreeChart createChart(PieDataset dataset, String title) {
        
        JFreeChart chart = ChartFactory.createPieChart(
            title,  // chart title
            dataset,             // data
            true,               // include legend
            true,
            false
        );

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.setNoDataMessage("No data available");
        plot.setCircular(false);
        plot.setLabelGap(0.02);
        plot.setBaseSectionOutlinePaint(Color.black);
        plot.setLabelOutlinePaint(Color.black);
        plot.setSectionPaint(1, Color.DARK_GRAY);
        plot.setSectionPaint(0, Color.blue);
        return chart;
        
    }
    
    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     * 
     * @return A panel.
     */
    public static JPanel createDemoPanel(String total, String avild, String title) {
        double dtotal;
        double davild;
        dtotal = Double.parseDouble(total);
        davild = Double.parseDouble(avild);
        JFreeChart chart = createChart(createDataset(dtotal, davild), title);
        return new ChartPanel(chart);
    }
    public static JPanel createDemoPanelforcpu(String used, String title) {
    	double dcpu;
    	dcpu = Double.parseDouble(used);
        JFreeChart chart = createChart(createDatasetforcpu(dcpu),title);
        return new ChartPanel(chart);
    }
    
    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */


}
