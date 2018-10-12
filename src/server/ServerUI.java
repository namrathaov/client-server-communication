package server;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;
import java.awt.Component;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;

/**
 * ServerUI Class to generate user interface as per the user requirements
 *
 * @author team 7
 * @version 1.0
 * @since 2018-02-23
 * 
 */

public class ServerUI {

	private JFrame JFrame;
	private JTextField HighestTextField;
	private JTextField LowestValueText;
	private JTextField frequencyValueText;
	private JButton startStopBtn;
	private boolean serverActiveFlag;
	ServerConnection serverConnection;
	ServerStatus plotPanel = new ServerStatus();


	/**
	 * Create the application.
	 */
	public ServerUI() {
	}

	/**
	 * Initialize the contents of the frame.
	   Creates the server interface by adding components to the server frame.
	 * 
	 */
	public void initialize() {
		JFrame = new JFrame();
		JFrame.setBounds(new Rectangle(20, 10, 400, 400));
		JFrame.getContentPane().setBackground(new Color(135, 206, 250));
		JFrame.setTitle("Server");
		JFrame.setAutoRequestFocus(false);
		JFrame.setBounds(200, 100, 886, 700);
		JFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		serverActiveFlag = false;
		serverConnection = new ServerConnection();


		startStopBtn = new JButton("Start");
		startStopBtn.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		startStopBtn.setBounds(694, 16, 115, 29);
		startStopBtn.setForeground(Color.BLACK);
		startStopBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				startStopBtnClick(arg0);
			}
		});
		JFrame.getContentPane().setLayout(null);
		startStopBtn.setBackground(Color.PINK);
		JFrame.getContentPane().add(startStopBtn);
		
		JPanel highestValPanel = new JPanel();
		highestValPanel.setBorder(new LineBorder(Color.BLUE));
		highestValPanel.setBounds(528, 80, 131, 55);
		highestValPanel.setBackground(Color.PINK);
		JFrame.getContentPane().add(highestValPanel);
		highestValPanel.setLayout(null);
		
		JLabel HighestValueLabel = new JLabel("Highest Value");
		HighestValueLabel.setHorizontalAlignment(SwingConstants.CENTER);
		HighestValueLabel.setBounds(new Rectangle(14, 5, 97, 20));
		HighestValueLabel.setVerticalAlignment(SwingConstants.TOP);
		HighestValueLabel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		highestValPanel.add(HighestValueLabel);
		
		JPanel controlPanel = new JPanel();
		controlPanel.setBorder(new LineBorder(Color.BLUE));
		controlPanel.setBackground(Color.LIGHT_GRAY);
		controlPanel.setBounds(18, 61, 831, 371);
		JFrame.getContentPane().add(controlPanel);
		controlPanel.setLayout(null);
		
		HighestTextField = new JTextField();
		HighestTextField.setBorder(new LineBorder(Color.BLUE));
		HighestTextField.setHorizontalAlignment(SwingConstants.CENTER);
		HighestTextField.setText("");
		HighestTextField.setBackground(new Color(135, 206, 250));
		HighestTextField.setBounds(672, 16, 132, 53);
		controlPanel.add(HighestTextField);
		HighestTextField.setColumns(10);
		
		JPanel LowestValuePanel = new JPanel();
		LowestValuePanel.setBorder(new LineBorder(Color.BLUE));
		LowestValuePanel.setBackground(new Color(135, 206, 235));
		LowestValuePanel.setBounds(514, 105, 132, 63);
		controlPanel.add(LowestValuePanel);
		
		JLabel lowestValueLabel = new JLabel("Lowest Value");
		lowestValueLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lowestValueLabel.setHorizontalAlignment(SwingConstants.CENTER);
		LowestValuePanel.add(lowestValueLabel);
		
		LowestValueText = new JTextField();
		LowestValueText.setBorder(new LineBorder(Color.BLUE));
		LowestValueText.setBackground(Color.PINK);
		LowestValueText.setText("");
		LowestValueText.setHorizontalAlignment(SwingConstants.CENTER);
		LowestValueText.setBounds(672, 105, 132, 63);
		controlPanel.add(LowestValueText);
		LowestValueText.setColumns(10);
		
		JPanel FrequencyPanel = new JPanel();
		FrequencyPanel.setBorder(new LineBorder(Color.BLUE));
		FrequencyPanel.setBackground(Color.PINK);
		FrequencyPanel.setBounds(514, 201, 132, 63);
		controlPanel.add(FrequencyPanel);
		
		JLabel FreqLabel = new JLabel("Frequency");
		FrequencyPanel.add(FreqLabel);
		
		frequencyValueText = new JTextField();
		frequencyValueText.setBorder(new LineBorder(Color.BLUE));
		frequencyValueText.setBackground(new Color(135, 206, 250));
		frequencyValueText.setHorizontalAlignment(SwingConstants.CENTER);
		frequencyValueText.setActionCommand("");
		frequencyValueText.setText("5");
		frequencyValueText.setBounds(672, 201, 132, 63);
		controlPanel.add(frequencyValueText);
		frequencyValueText.setColumns(10);
		
		plotPanel.setBorder(new LineBorder(Color.BLUE));
		plotPanel.setBackground(Color.PINK);
		plotPanel.setBounds(26, 16, 457, 324);
		controlPanel.add(plotPanel);
				
		JTextPane consoletextPane = (JTextPane) ServerConsole.getConsolePane();
		JScrollPane scrolltxt=new JScrollPane(consoletextPane);
		scrolltxt.setBounds(28, 459, 834, 157);
		JFrame.getContentPane().add(scrolltxt);
				
		JLabel lblConsole = new JLabel("Console:");
		lblConsole.setBounds(28, 443, 61, 14);
		JFrame.getContentPane().add(lblConsole);
		
		JFrame.setVisible(true);
        	JFrame.setResizable(false);
        
	}
	
	
	/**
	* The function start and stops the server connection on click on startStopNtn. The serverActive flag get set to true if  
	* server is active and get set to false, otherwise.
	*/

	private void startStopBtnClick(java.awt.event.ActionEvent evt){
		int maxValue = 1024;
		int minValue =0;
		if(!serverActiveFlag){
			try{
				if(HighestTextField.getText().equals(""))
				{
					ServerConsole.setErrorMessage("Please set max value..");
				}else {
					maxValue = Integer.parseInt(HighestTextField.getText());
					if (LowestValueText.getText().equals("")) {
						ServerConsole.setErrorMessage("Please set min value..");
					} else {
						minValue = Integer.parseInt(LowestValueText.getText());
						serverConnection.start();
						serverConnection.setFrequency(5);
						serverActiveFlag = true;
						startStopBtn.setText("Stop");
						plotPanel.startBlinking();
						ServerConsole.setMessage("Server started.");
					}
				}
				serverConnection.setMax(maxValue);
				serverConnection.setMin(minValue);

			}catch (Exception e){
				ServerConsole.setErrorMessage(e.getMessage());
			}
		}
		else{
			plotPanel.stopBlinking();
			serverActiveFlag = false;
			serverConnection.stop();
			ServerConsole.setMessage("Server stopped.");
			startStopBtn.setText("Start");
		}
	}
}
