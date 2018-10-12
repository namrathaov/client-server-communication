package server;

import java.awt.Color;
import java.awt.Font;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 * ServerConsole class to display error and success message
 *
 * @author Team 7
 * @version 1.0
 */
public class ServerConsole {

	private final static Logger LOGGER = Logger.getLogger(ServerConsole.class.getName());
	private static JTextPane consoletextPane = null;
	private final static String CONTENT_TYPE = "text/html";

	public ServerConsole() {}

	/**
	 *  Sets the layout of error messages
	 * @param errorMessage 
	 */
	public static void setErrorMessage(String errorMessage) {

		try
		{
			consoletextPane.setContentType(CONTENT_TYPE);
			StyledDocument doc = (StyledDocument) consoletextPane.getDocument();
			SimpleAttributeSet keyWord = new SimpleAttributeSet();
			StyleConstants.setForeground(keyWord, Color.RED);
			StyleConstants.setFontFamily(keyWord, "Times New Roman");
			StyleConstants.setFontSize(keyWord, 9);
			StyleConstants.setBackground(keyWord, Color.LIGHT_GRAY);

			doc.insertString(0,new Date()+" - Error - "+errorMessage +"\n",keyWord );
		}
		catch(Exception ex) 
		{ 
			LOGGER.log(Level.SEVERE,"Exception while adding Error Message.", ex); 
		}

	}

	/**
	 * sets the layout for normal messages
	 * @param message
	 */
	public static void setMessage(String message) {
		try {
			consoletextPane.setContentType(CONTENT_TYPE);
			StyledDocument doc = (StyledDocument) consoletextPane.getDocument();
			SimpleAttributeSet keyWord = new SimpleAttributeSet();
			StyleConstants.setForeground(keyWord, Color.BLACK);
			StyleConstants.setFontFamily(keyWord, "Times New Roman");
			StyleConstants.setFontSize(keyWord, 9);
			StyleConstants.setBackground(keyWord, Color.LIGHT_GRAY);
			doc.insertString(0,new Date()+"- Message - "+message+"\n",keyWord );
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE,"Exception while adding Message", ex);
		}
	}

	/**
	 * Creating Text Pane for console.
	 */
	private static void constructConsolePanel()
	{
		consoletextPane = new JTextPane();
		consoletextPane.setBorder(new LineBorder(Color.BLUE));
		consoletextPane.setFont(new Font("Tahoma", Font.PLAIN, 18));
		consoletextPane.setEditable(false);
		consoletextPane.setForeground(Color.BLACK);
		consoletextPane.setBackground(Color.LIGHT_GRAY);
		consoletextPane.setBounds(28, 459, 834, 157);

	}

	/**
	 * 
	 * @return Text Pane with or without updated Message.
	 */
	public static JTextPane getConsolePane()
	{
		if(consoletextPane == null)
		{	
			constructConsolePanel();
		}
		return consoletextPane;
	}
}


