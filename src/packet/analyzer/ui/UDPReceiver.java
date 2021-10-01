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
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    JPanel UDPDataLogger;
    JPanel UDPOptionPanel;
    
    JScrollPane DataLoggerScrollPane;

    JLabel jLabelListener;
    JLabel jLabelinstanceConnectedIPAddress;
    JLabel jLabelPotAddress;
    JLabel jLabelAnalyzerStatus;

    JTextField JTextFieldinstanceConnectedIPAddress;
    JTextField JTextFieldPotAddress;
    JTextField JTextFieldAnalyzerStatus;

    JTextArea JTextAreaLoggerPanel;

    JButton JButtonStart;
    JButton JButtonStop;
    JButton JButtonClear;

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
        jLabelPotAddress = new JLabel("Connected Port:", JLabel.RIGHT);
        
        JTextFieldAnalyzerStatus = new JTextField(8);
        JTextFieldinstanceConnectedIPAddress = new JTextField(5);
        JTextFieldPotAddress = new JTextField(5);
        
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
        UDPOptionPanel.add(jLabelPotAddress);
        UDPOptionPanel.add(JTextFieldPotAddress);
        UDPOptionPanel.add(JButtonStart);
        UDPOptionPanel.add(JButtonStop);
        UDPOptionPanel.add(JButtonClear);
    }

    @Override
    public void actionPerformed(ActionEvent e){
       // TO DO : Added UDP Logic Here 
    }
}
