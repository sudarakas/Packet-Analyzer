/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packet.analyzer.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author NanoX
 */
public class UDPReceiver extends JPanel implements ActionListener {
    
    JPanel UDPAnalyzerWindow;
    static JPanel UDPDataLogger;
    JPanel UDPOptionPanel;
    
    JScrollPane DataLoggerScrollPane;

    
    JLabel jLabelinstanceConnectedIPAddress;
    JLabel jLabelPortAddress;
    
    static JLabel jLabelListener;
    static JLabel jLabelAnalyzerStatus;

    JTextField JTextFieldinstanceConnectedIPAddress;
    JTextField JTextFieldPortAddress;
    JTextField JTextFieldAnalyzerStatus;

    static JTextArea JTextAreaLoggerPanel;

    static JButton JButtonStart;
    static JButton JButtonStop;
    static JButton JButtonClear;
    
    private static String instanceConnectedIPAddress;
    private static int portAddress;

    public static String getInstanceConnectedIPAddress() {
        return instanceConnectedIPAddress;
    }

    public static int getPortAddress() {
        return portAddress;
    }

    public UDPReceiver(){
        initUI();
    }

    private void initUI() {
        UDPAnalyzerWindow = new JPanel();
        UDPDataLogger = new JPanel();
        UDPOptionPanel = new JPanel();

        JTextAreaLoggerPanel = new JTextArea(40, 68);
        JTextAreaLoggerPanel.setEditable(false);

        DataLoggerScrollPane = new JScrollPane(JTextAreaLoggerPanel);
        DataLoggerScrollPane.setBorder(BorderFactory.createTitledBorder("Capturing UDP Packets"));

        jLabelListener = new JLabel("Listening To: ", JLabel.RIGHT);
        jLabelAnalyzerStatus = new JLabel("Status: ", JLabel.RIGHT);
        jLabelinstanceConnectedIPAddress = new JLabel("Connected IP: ", JLabel.RIGHT);
        jLabelPortAddress = new JLabel("Connected Port:", JLabel.RIGHT);
        
        JTextFieldAnalyzerStatus = new JTextField(8);
        JTextFieldinstanceConnectedIPAddress = new JTextField(5);
        JTextFieldPortAddress = new JTextField(5);
        
        JTextFieldAnalyzerStatus.setText("Ready");
        
        JTextFieldAnalyzerStatus.setEditable(false);

        JButtonStart = new JButton("Start");
        JButtonStart.addActionListener(this);
        JButtonStop = new JButton("Stop");
        JButtonStop.addActionListener(this);
        JButtonClear = new JButton("Clear");
        JButtonClear.addActionListener(this);

        setLayout(new BorderLayout());
        add(UDPAnalyzerWindow, BorderLayout.NORTH);
        add(UDPDataLogger, BorderLayout.CENTER);
        add(UDPOptionPanel, BorderLayout.SOUTH);

        UDPAnalyzerWindow.setLayout(new FlowLayout());
        UDPAnalyzerWindow.add(jLabelListener);

        UDPDataLogger.setLayout(new GridLayout(1, 1));
        UDPDataLogger.add(DataLoggerScrollPane);

        UDPOptionPanel.setLayout(new FlowLayout());
        UDPOptionPanel.add(jLabelAnalyzerStatus);
        UDPOptionPanel.add(JTextFieldAnalyzerStatus);
        UDPOptionPanel.add(jLabelinstanceConnectedIPAddress);
        UDPOptionPanel.add(JTextFieldinstanceConnectedIPAddress);
        UDPOptionPanel.add(jLabelPortAddress);
        UDPOptionPanel.add(JTextFieldPortAddress);
        UDPOptionPanel.add(JButtonStart);
        UDPOptionPanel.add(JButtonStop);
        UDPOptionPanel.add(JButtonClear);
    }
    
    public static void JTextAreaLoggerPanelUpdater(String packetData){
        JTextAreaLoggerPanel.append(packetData);
    }
    
    public static void showMessageDialog(String message){
        JOptionPane.showMessageDialog(UDPDataLogger, message);
    }
    
    public static void JButtonStartSwitch(boolean status){
        JButtonStart.setEnabled(status);
    }
    
    public static void JButtonStopSwitch(boolean status){
       JButtonStop.setEnabled(status);
    }
    
    public static void jLabelListenerUpdate(String message){
        jLabelListener.setText(message);
    }
    
    public static void jLabelAnalyzerStatusUpdate(String message){
        jLabelAnalyzerStatus.setText(message);
    }

    @Override
    public void actionPerformed(ActionEvent e){
               Object source = e.getSource();
        
        packet.analyzer.engine.UDPReceiver UDPThread = new packet.analyzer.engine.UDPReceiver();
        Thread thread = new Thread(UDPThread);
        
        if(!thread.isAlive())
        {
            thread = new Thread(UDPThread);
        }
        
        if (JButtonStart.equals(source))
        {
            if (JTextFieldinstanceConnectedIPAddress.getText().equals("") || JTextFieldPortAddress.getText().equals(""))
            {
                JOptionPane.showMessageDialog(this, "Incorrect IP and Port!");
            } 
            else
            {
                jLabelListener.setText("Listening: " + JTextFieldinstanceConnectedIPAddress.getText() + " : " + JTextFieldPortAddress.getText());
                instanceConnectedIPAddress = JTextFieldinstanceConnectedIPAddress.getText();
                portAddress = Integer.parseInt(JTextFieldPortAddress.getText());
                
                JTextFieldAnalyzerStatus.setText("Processing");
                JButtonStart.setEnabled(false);
                JTextAreaLoggerPanel.setText("");
                
                thread.start();
                JTextFieldAnalyzerStatus.setText("Capturing: ");
            }

        } 
        else if (JButtonStop.equals(source)) 
        {
            JTextFieldAnalyzerStatus.setText("Terminating");
            JButtonStop.setEnabled(false);
            try {
                UDPThread.terminate();
                thread.join(500);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        else if (JButtonClear.equals(source)) 
        {
            JTextFieldAnalyzerStatus.setText("Cleaning");
            JTextAreaLoggerPanel.setText("");
        }
    }
}
