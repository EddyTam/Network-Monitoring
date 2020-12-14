package jchart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;

import javax.swing.ImageIcon;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing how to create a line chart using data from a
 * {@link CategoryDataset}.
 */
public class linechart extends ApplicationFrame {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public linechart(String title,String [] resultlist, String [] dateArray) {
        super(title);
        final CategoryDataset dataset = createDataset(title,resultlist,dateArray);
        final JFreeChart chart = createChart(dataset,title);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Creates a sample dataset.
     * 
     * @return The dataset.
     */
    private CategoryDataset createDataset(String title,String [] resultlist,String[]dateArray) {
    	
        Double [] Dresultlist = new Double[resultlist.length];
        for(int n=0;n<resultlist.length;n++){
        	Dresultlist[n] = Double.parseDouble(resultlist[n]);
        }
    	
        // row keys...
        final String series1 = title;

        // column keys...
        final String type1 = dateArray[0];
        final String type2 = dateArray[1];
        final String type3 = dateArray[2];
        final String type4 = dateArray[3];
        final String type5 = dateArray[4];
        final String type6 = dateArray[5];
        final String type7 = dateArray[6];

        // create the dataset...
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        //for(int i=0;i<Dresultlist.length;i++){
        dataset.addValue(Dresultlist[0], series1, type1);
        dataset.addValue(Dresultlist[1], series1, type2);
        dataset.addValue(Dresultlist[2], series1, type3);
        dataset.addValue(Dresultlist[3], series1, type4);
        dataset.addValue(Dresultlist[4], series1, type5);
        dataset.addValue(Dresultlist[5], series1, type6);
        dataset.addValue(Dresultlist[6], series1, type7);
        
        //}
        return dataset;
                
    }
    
    /**
     * Creates a sample chart.
     * 
     * @param dataset  a dataset.
     * 
     * @return The chart.
     */
    private JFreeChart createChart(final CategoryDataset dataset,String title) {
    	ImageIcon bgimage = new ImageIcon("icons/simple-ubuntu-blue.png");
        
        // create the chart...
        final JFreeChart chart = ChartFactory.createLineChart(
            title,       // chart title
            "Type",                    // domain axis label
            "Value",                   // range axis label
            dataset,                   // data
            PlotOrientation.VERTICAL,  // orientation
            true,                      // include legend
            true,                      // tooltips
            false                      // urls
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
//        final StandardLegend legend = (StandardLegend) chart.getLegend();
  //      legend.setDisplaySeriesShapes(true);
    //    legend.setShapeScaleX(1.5);
      //  legend.setShapeScaleY(1.5);
        //legend.setDisplaySeriesLines(true);

        chart.setBackgroundPaint(Color.white);

        final CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.black);
        plot.setRangeGridlinePaint(Color.white);

        // customise the range axis...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setAutoRangeIncludesZero(true);
        
        // customise the renderer...
        final LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setBaseShapesVisible(true);
        renderer.setBaseLinesVisible(true);
        renderer.setDrawOutlines(true);
       renderer.setUseFillPaint(true);
//        renderer.setBaseFillPaint(Color.white);
        renderer.setSeriesStroke(0, new BasicStroke(3.0f));
        renderer.setSeriesOutlineStroke(0, new BasicStroke(2.0f));
        renderer.setSeriesShape(0, new Ellipse2D.Double(0,0,0,0));
        renderer.setPaint(Color.blue);

        /*renderer.setSeriesStroke(
            0, new BasicStroke(
                2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
                1.0f, new float[] {10.0f, 6.0f}, 0.0f
            )
        );
        renderer.setSeriesStroke(
            1, new BasicStroke(
                2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
                1.0f, new float[] {6.0f, 6.0f}, 0.0f
            )
        );
        renderer.setSeriesStroke(
            2, new BasicStroke(
                2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
                1.0f, new float[] {2.0f, 6.0f}, 0.0f
            )
        );*/
        // OPTIONAL CUSTOMISATION COMPLETED.
        
        return chart;
    }
    
    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public void windowClosing(final WindowEvent evt){
    	if(evt.getWindow() == this){
    	dispose();

    	}
    }  	
}


