/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packet.analyzer.ui;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 *
 * @author NanoX
 */
public class Main {
    
    // Demo Run Method
    public void starter(){
        
        JFrame frame = new JFrame("UPD Packet Monitor");
        frame.add(new UDPReceiver());//Notice the order, and the add method comes first
        frame.setVisible(true);
        frame.setSize(800,600);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
        
}
