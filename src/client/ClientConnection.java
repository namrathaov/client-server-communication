package client;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import network.Register;
import network.Request;
import network.Response;
import util.Constants;

import javax.swing.*;
import java.io.IOException;
import java.util.Arrays;

/**
 * ClientConnection
 *
 * Creates socket client connection and sends requests to the server
 * and handles responses.
 *
 * @author Team 7
 * @version 1.0
 */
public class ClientConnection {

	private Client client;
	private ClientData clientData;
	private ClientPlotGraph  graphPlot;
	private ClientDataPanel cdpanel;

	/**
	 * constructor to initialize default value
	 */
	public ClientConnection() {
		clientData = new ClientData();
		client = new Client();
		cdpanel = new ClientDataPanel();
		graphPlot = new ClientPlotGraph();
	}

	/**
	 * Sets a listener that waits for responses from the server
	 *
	 * @param channels 		the number of channels
	 */
	private void setListener(int channels) {
		client.addListener(new Listener() {
			public void received (Connection connection, Object object) {
				if (object instanceof Response) {
					Response response = (Response) object;
					System.out.println("A response from the server: " + Arrays.toString(response.getChannelNumbers()));
					clientData.addChannelData(response.getChannelNumbers());
					System.out.println(
							"Max is: " + clientData.getMax()
							+ " Min is: " + clientData.getMin()
							+ " Average is: " + clientData.getAverage()
							+ " Frequency is: " + response.getFrequency());

					cdpanel.setAverage((int) clientData.getAverage());
					cdpanel.setFrequency(response.getFrequency());
					cdpanel.setMax(clientData.getMax());
					cdpanel.setMin(clientData.getMin());
				}
			}
		});
	}


	/**
	 * Alerts the server the number of channels has changed
	 *
	 * @param channels 		the number of channels
	 */
	public boolean setNumChannels(int channels) {
	    boolean isServerActive = false;
		if(!client.isConnected()) {
			isServerActive = start(channels);
		}
		Request request = new Request(channels);
		client.sendTCP(request);
		return isServerActive;
	}

	/**
	 * get clientDataPanel
	 *
	 * @return
	 */
	public JPanel getClientDataPanel() {
		return cdpanel.getClientDataPanel();
	}

	/**
	 * Starts the client connection
	 *
	 * @param channels 		the number of channels
	 */
	public boolean start(int channels) {
		if(!client.isConnected()) {
			client.start();

			try {
				client.connect(Constants.TIMEOUT, Constants.HOST, Constants.TCP_PORT);
			} catch (IOException e) {
				System.out.println("Cannot connect, Server is not up.");
				return false;
			}

			Register.register(client);
			setListener(channels);
		}
		return true;
	}

	/**
	 * Ends the connection to the server
	 */
	public void stop() {
		client.close();
		client.stop();
	}
}
