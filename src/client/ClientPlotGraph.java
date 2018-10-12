package client;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

public class ClientPlotGraph extends JFrame implements ActionListener {
	/**
	 * GraphPlot creates and plots data as line graphs based on the number of channels
	 * and frequency set by the server.
	 */
	private static final long serialVersionUID = 1L;
	TimeSeries[] graph;
	int channelCount=0;
	double factor=0;
	private static JFreeChart chart;
	private static ChartPanel chartPanel;
	public Map<Integer, List<Integer>> dataMap;
	private Timer refresher;
	ClientData clientData;

	/**
	 * Initializes the graph and sets the layout
	 * @param channels
	 * @param dataMap1
	 * @return
	 */
	public ChartPanel drawGraph(int channels, Map<Integer, List<Integer>> dataMap1) {
		refresher = new Timer(250, this);
		graph=new TimeSeries[channels];
		final TimeSeriesCollection dataset=new TimeSeriesCollection();
		channelCount=channels;
		for(int channel=0;channel<channelCount;channel++) {
			graph[channel] = new TimeSeries("Channel "+channel);
			dataset.addSeries(graph[channel]);
		}
		chart = createChart(dataset);

		chartPanel = new ChartPanel(chart);
		refresher.start();

		return chartPanel;
	}

	/**
	 * define the initial layout of the graph and returns chart
	 */
	private JFreeChart createChart(final XYDataset dataset) {
		final JFreeChart result = ChartFactory.createTimeSeriesChart(
				"Graph Plot", "", "", dataset, true, true, true);

		final XYPlot plot = result.getXYPlot();
		plot.setDomainGridlinesVisible(false);
		plot.setRangeGridlinesVisible(false);
		ValueAxis xaxis = plot.getDomainAxis();
		xaxis.setAutoRange(true);
		xaxis.setFixedAutoRange(1000);
		xaxis.setVerticalTickLabels(false);
		xaxis.setTickLabelsVisible(false);
		ValueAxis yaxis = plot.getDomainAxis();
		yaxis.setVerticalTickLabels(false);
		yaxis.setTickLabelsVisible(false);
		return result;
	}

	/**
	 * action listener for refresher
	 * @param e
	 */
	public void actionPerformed(ActionEvent e) {
		for (int channel = 0; channel < channelCount; channel++) {
			factor = 100*Math.random();
			System.out.println("Plotting number.. " + factor);
			this.graph[channel].add(new Millisecond(), channel*factor);
		}

	}
}