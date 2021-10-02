/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packet.analyzer.engine;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import static packet.analyzer.ui.UDPReceiver.JTextAreaLoggerPanelUpdater;
import static packet.analyzer.ui.UDPReceiver.showMessageDialog;
import static packet.analyzer.ui.UDPReceiver.JButtonStartSwitch;
import static packet.analyzer.ui.UDPReceiver.JButtonStopSwitch;
import static packet.analyzer.ui.UDPReceiver.jLabelListenerUpdate;
import static packet.analyzer.ui.UDPReceiver.jLabelAnalyzerStatusUpdate;
import static packet.analyzer.ui.UDPReceiver.getPortAddress;
import static packet.analyzer.ui.UDPReceiver.getInstanceConnectedIPAddress;

/**
 *
 * @author NanoX
 */
public class UDPReceiver implements Runnable {
    
    private DatagramSocket socket;
    
    private volatile boolean exit = false;

        @Override
        public void run() 
        {
            while (!exit) 
            {
                DatagramPacket packet = null;
                try 
                {
                    socket = new DatagramSocket(getPortAddress());
                    byte[] data = new byte[1024];
                    packet = new DatagramPacket(data, data.length);
                    while (true) 
                    {
                        socket.receive(packet);
                        byte[] bytes = packet.getData();
                        String message = new String(bytes, 0, bytes.length);
                        String str =  getInstanceConnectedIPAddress() + ":" + getPortAddress() + ": " + message + '\n';
                        JTextAreaLoggerPanelUpdater(str);
                    }
                } 
                catch (IOException e) 
                {
                    e.printStackTrace();
                } 
                finally 
                {
                    socket.close();
                    showMessageDialog("Terminated successfully!");
                    JButtonStartSwitch(true);
                    JButtonStopSwitch(true);
                    
                    jLabelListenerUpdate("Listening");
                    jLabelAnalyzerStatusUpdate("Ready");
                    
                    exit = false;
                }
            }
        }

        public void terminate() 
        {
            exit = true;
        }
}
