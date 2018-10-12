package client;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Component;

import javax.swing.*;

import util.Constants;

/**
 * ClientDataPanel - returns panel containing client data values
 *
 * @author team7
 */
public class ClientDataPanel extends JPanel {
    public static JPanel clientDataPanel;
    private static JLabel highestValLbl;
    private static JTextField highestTxtField;
    private static JLabel lowestValLbl;
    private static JTextField lowestTxtField;
    private static JLabel averageValLbl;
    private static JTextField averageTxtfield;
    private static JLabel chanelValLbl;
    private static JSpinner spinner;
    private static JLabel frequencyValLbl;
    private static JTextField frequencyTxtField;
    private static SpinnerModel value = new SpinnerNumberModel(0, 0, 10, 1);
    private static Dimension txtFieldPreferredSize = new Dimension(120, 50);
    private static Dimension lblPreferredSize = new Dimension(120, 42);

    /*
    Creates new JLabel object for client data labels
    @param: String lbl
    */
    private static JLabel createDataLabel(String lbl) {
        JLabel dataLbl = new JLabel(lbl);
        dataLbl.setHorizontalAlignment(JLabel.CENTER);
        dataLbl.setVerticalAlignment(JLabel.CENTER);
        return dataLbl;
    }

    /*
    Creates new Jpanel object to contain client data labels
    @param: JLabel dataLbl, Color clr
    */
    private static JPanel createDataLabelPanel(JLabel dataLbl, Color clr) {
        JPanel dataLblPanel = new JPanel(new BorderLayout());
        dataLblPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        dataLblPanel.setPreferredSize(lblPreferredSize);
        dataLblPanel.setBackground(clr);
        dataLblPanel.add(dataLbl, BorderLayout.CENTER);
        return dataLblPanel;
    }

    /*
    Creates new JLabel object for client data labels
    @param: Color clr
    */
    private static JTextField createTxtField(Color clr) {
        JTextField txtField = new JTextField();
        txtField.setPreferredSize(txtFieldPreferredSize);
        txtField.setEditable(false);
        txtField.setBackground(clr);
        return txtField;
    }

    /*
    *Creates new JPanel to contain text fields for client data output
    * @param: JTextField txtField
    */
    private static JPanel createTxtFieldPanel(JTextField txtField) {
        JPanel txtFieldPanel = new JPanel(new BorderLayout());
        txtFieldPanel.add(txtField, BorderLayout.CENTER);
        return txtFieldPanel;
    }

    /**
     * constructor to initialize the default values
     */
    public ClientDataPanel() {
        GridBagLayout grid = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        clientDataPanel = new JPanel(grid);
        clientDataPanel.setSize(new Dimension(250, 250));
        spinner = new JSpinner(value);
        spinner.setPreferredSize(txtFieldPreferredSize);
        Component c = spinner.getEditor().getComponent(0);
        c.setBackground(Constants.COLOR_BLUE);

        highestValLbl = createDataLabel("Highest Value: ");
        JPanel highestValLblPanel = createDataLabelPanel(highestValLbl, Constants.COLOR_BLUE);

        highestTxtField = createTxtField(Constants.COLOR_PINK);
        JPanel highestTxtFieldPanel = createTxtFieldPanel(highestTxtField);

        lowestValLbl = createDataLabel("Lowest Value: ");
        JPanel lowestValLblPanel = createDataLabelPanel(lowestValLbl, Constants.COLOR_PINK);

        lowestTxtField = createTxtField(Constants.COLOR_BLUE);
        JPanel lowestTxtFieldPanel = createTxtFieldPanel(lowestTxtField);

        averageValLbl = createDataLabel("Average: ");
        JPanel averageValLblPanel = createDataLabelPanel(averageValLbl, Constants.COLOR_BLUE);

        averageTxtfield = createTxtField(Constants.COLOR_PINK);
        JPanel averageTxtfieldPanel = createTxtFieldPanel(averageTxtfield);

        chanelValLbl = createDataLabel("Channel: ");
        JPanel chanelValLblPanel = createDataLabelPanel(chanelValLbl, Constants.COLOR_PINK);

        frequencyValLbl = createDataLabel("Frequency(Hz): ");
        JPanel frequencyValLblPanel = createDataLabelPanel(frequencyValLbl, Constants.COLOR_BLUE);

        frequencyTxtField = createTxtField(Constants.COLOR_PINK);
        JPanel frequencyTxtFieldPanel = createTxtFieldPanel(frequencyTxtField);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        clientDataPanel.add(highestValLblPanel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 0;
        clientDataPanel.add(highestTxtFieldPanel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        clientDataPanel.add(lowestValLblPanel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 1;
        clientDataPanel.add(lowestTxtFieldPanel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        clientDataPanel.add(averageValLblPanel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 2;
        clientDataPanel.add(averageTxtfieldPanel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 3;
        clientDataPanel.add(chanelValLblPanel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 3;
        clientDataPanel.add(spinner, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 4;
        clientDataPanel.add(frequencyValLblPanel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 4;
        clientDataPanel.add(frequencyTxtFieldPanel, gbc);
    }

    /**
     * returns the clientDataPanel
     * @return
     */
    public JPanel getClientDataPanel() {
        return clientDataPanel;
    }

    public void setAverage(int average) {
        averageTxtfield.setText(String.valueOf(average));
    }

    public void setMax(int max) {
        highestTxtField.setText(String.valueOf(max));
    }

    public void setMin(int min) {
        lowestTxtField.setText(String.valueOf(min));
    }

    public void setFrequency(int frequency) {
        frequencyTxtField.setText(String.valueOf(frequency));
    }
}
