import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class LineChart_AWT extends ApplicationFrame {
	private double[] fsum = new double[40];

   public LineChart_AWT( String applicationTitle , String chartTitle, double[] fsum) {
	   
      super(applicationTitle);
      this.fsum=fsum;
      JFreeChart lineChart = ChartFactory.createLineChart(
         chartTitle,
         "Cycles","Sum of Distance",
         createDataset(),
         PlotOrientation.VERTICAL,
         true,true,false);
         
      ChartPanel chartPanel = new ChartPanel( lineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
      setContentPane( chartPanel );
   }

   private DefaultCategoryDataset createDataset( ) {
      DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
      for(int i=0;i<40;i++)
      {
    	  String x =Integer.toString(i);
    	  dataset.addValue( fsum[i] , "cycles" ,x );
      }
      return dataset;
   }
}