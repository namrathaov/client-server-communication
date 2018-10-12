package client;

import java.awt.Dimension;
import java.awt.Font;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.html.HTMLDocument;

import util.Constants;

/**
 * ClientConsole class to handle displaying message and error message in the 
 * client console
 *
 * @author team 7
 * @version 1
 */
public class ClientConsole {

    private static JTextPane consoleDisplay = new JTextPane();
    private static JLabel consoleLabel = new JLabel();
    private static JScrollPane consoleScroller = new JScrollPane(
    						  consoleDisplay);
    private static JPanel consolePanel = null;
    private final static Logger LOGGER = Logger.getLogger(
    					 ClientConsole.class.getName());
    private final static String CONTENT_TYPE = "text/html";

    /**
     * Used to display error messages in the client console
     *
     * @param errorMessage - error message that has to be displayed in console
     */
    public static void setErrorMessage(String errorMessage) {
        try {
        		consoleDisplay.setContentType(CONTENT_TYPE);
        		HTMLDocument doc=(HTMLDocument) consoleDisplay.getDocument();
        		doc.insertAfterEnd(doc.getCharacterElement(doc.getLength()),
        				"<span style=\"color:red\">[" + new Date() + "] "
        						+ errorMessage + "</span> <br>");
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,
            		"Exception while adding Error Message to client console",
            		ex);
        }
    }

    /**
     * Used to display messages in the client console
     *
     * @param message - message that has to be displayed in console
     */
    public static void setMessage(String message) {
        try {
        		consoleDisplay.setContentType(CONTENT_TYPE);
        		HTMLDocument doc=(HTMLDocument) consoleDisplay.getDocument();
        		doc.insertAfterEnd(doc.getCharacterElement(doc.getLength()), 
        				"<span style=\"color:black\">[" + new Date() + "] "
        						+ message + "</span> <br>");
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,
            		"Exception while adding Message to client console",
            		ex);
        }
    }
    
    /**
     * A static method to get the console panel for Client UI 
     *  
     * @return the console panel
     */
    public static JPanel getConsolePanel() {
    		if (consolePanel == null) {
    			constructConsolePanel();
    		}
    		
    		return consolePanel;
    }
    
    /**
     *  A private static method that constructs the console panel
     */
    private static void constructConsolePanel() {
    	
	consolePanel = new JPanel();
	consolePanel.setBackground(Constants.COLOR_GRAY);
        consolePanel.setBorder(javax.swing.BorderFactory
        		.createLineBorder(Constants.COLOR_GRAY));
        consolePanel.setPreferredSize(new Dimension(40, 80));
        
        consoleLabel.setFont(new Font("Courier New", 0, 16)); 
        consoleLabel.setText("Console:");

        SimpleAttributeSet background = new SimpleAttributeSet();
        StyleConstants.setBackground(background, Constants.COLOR_GRAY);
        
        consoleDisplay.setEditable(false);
        consoleDisplay.setContentType("text/html");
        consoleDisplay.getStyledDocument().setParagraphAttributes(0,
        	consoleDisplay.getDocument().getLength(), background, false);
        consoleDisplay.setBorder(BorderFactory.createEmptyBorder());

        consoleScroller.setBorder(BorderFactory.createEmptyBorder());
        consoleScroller.setPreferredSize(new Dimension(40, 120));

        GroupLayout consolePanelLayout = new GroupLayout(consolePanel);
        consolePanel.setLayout(consolePanelLayout);
        consolePanelLayout.setHorizontalGroup(
                consolePanelLayout.createParallelGroup(
                		GroupLayout.Alignment.LEADING)
                        .addGroup(consolePanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(consoleLabel)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE,
                                		Short.MAX_VALUE))
                        .addGroup(consolePanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(consoleScroller))
        );
        
        consolePanelLayout.setVerticalGroup(
                consolePanelLayout.createParallelGroup(
                		GroupLayout.Alignment.LEADING)
                        .addGroup(consolePanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(consoleLabel)
                                .addComponent(consoleScroller)
                                .addContainerGap(88, Short.MAX_VALUE))
        );
    }
}
