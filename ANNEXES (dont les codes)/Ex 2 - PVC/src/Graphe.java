import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.style.Styler.LegendPosition;


public class Graphe {

	public static void main(String[] args) throws Exception {
	

	}

	public static void Traces(double[] xData, double[][] yData, String[] Nom_courbes , String titre_Graphique) throws Exception {

			// Create Chart
		    XYChart chart = new XYChartBuilder().width(800).height(600).title(titre_Graphique).xAxisTitle("").yAxisTitle("").build();
		   
		    // Customize Chart
		    chart.getStyler().setLegendPosition(LegendPosition.InsideNW);
		    chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Line);
		    //chart.getStyler().setYAxisLabelAlignment(Styler.TextAlignment.Right);
		    //chart.getStyler().setYAxisDecimalPattern("$ #,###.##");
		    chart.getStyler().setPlotMargin(0);
		    chart.getStyler().setPlotContentSize(.95);
	    
		    for(int i =0 ; i < yData.length ; i++ ) {
		    	chart.addSeries(Nom_courbes[i], xData, yData[i]); //.setMarkerColor(Couleur).setLineColor(Couleur); // Si personnalisation		
		    }
		    
		    // Affichage
		    new SwingWrapper<XYChart>(chart).displayChart();
			 
			  }
		
		
	public static void Trace(double[] xData, double[] yData, String Nom_courbes , String titre_Graphique) throws Exception {

			// Create Chart
		    XYChart chart = new XYChartBuilder().width(800).height(600).title(titre_Graphique).xAxisTitle("").yAxisTitle("").build();
		   
		    // Customize Chart
		    chart.getStyler().setLegendPosition(LegendPosition.InsideNW);
		    chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Line);
		    //chart.getStyler().setYAxisLabelAlignment(Styler.TextAlignment.Right);
		    //chart.getStyler().setYAxisDecimalPattern("$ #,###.##");
		    chart.getStyler().setPlotMargin(0);
		    chart.getStyler().setPlotContentSize(.95);
	    
		   
	    	chart.addSeries(Nom_courbes, xData, yData); //.setMarkerColor(Couleur).setLineColor(Couleur); // Si personnalisation		
		    
		    
		    // Affichage
		    new SwingWrapper<XYChart>(chart).displayChart();
			 
			  }
		
	}
	
	
