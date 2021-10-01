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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author NanoX
 */
public class TCPReceiver extends JPanel implements ActionListener {
    JPanel TCPAnalyzerWindow;
    JPanel TCPDataLogger;
    JPanel TCPOptionPanel;
    
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

    public TCPReceiver(){
        initUI();
    }

    private void initUI() {
        TCPAnalyzerWindow = new JPanel();
        TCPDataLogger = new JPanel();
        TCPOptionPanel = new JPanel();

        JTextAreaLoggerPanel = new JTextArea(40, 68);
        JTextAreaLoggerPanel.setEditable(false);

        DataLoggerScrollPane = new JScrollPane(JTextAreaLoggerPanel);
        DataLoggerScrollPane.setBorder(BorderFactory.createTitledBorder("Capturing TCP Packets"));

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
        add(TCPAnalyzerWindow, BorderLayout.NORTH);
        add(TCPDataLogger, BorderLayout.CENTER);
        add(TCPOptionPanel, BorderLayout.SOUTH);

        TCPAnalyzerWindow.setLayout(new FlowLayout());
        TCPAnalyzerWindow.add(jLabelListener);

        TCPDataLogger.setLayout(new GridLayout(1, 1));
        TCPDataLogger.add(DataLoggerScrollPane);

        TCPOptionPanel.setLayout(new FlowLayout());
        TCPOptionPanel.add(jLabelAnalyzerStatus);
        TCPOptionPanel.add(JTextFieldAnalyzerStatus);
        TCPOptionPanel.add(jLabelinstanceConnectedIPAddress);
        TCPOptionPanel.add(JTextFieldinstanceConnectedIPAddress);
        TCPOptionPanel.add(jLabelPotAddress);
        TCPOptionPanel.add(JTextFieldPotAddress);
        TCPOptionPanel.add(JButtonStart);
        TCPOptionPanel.add(JButtonStop);
        TCPOptionPanel.add(JButtonClear);
    }

    @Override
    public void actionPerformed(ActionEvent e){
       // TO DO : Added TCP Logic Here 
    }
}
