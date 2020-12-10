/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensorgraph;


import com.fazecast.jSerialComm.SerialPort;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author pavel
 */
public class SensorGraph {
    public static void main(String[] args) {
        
        // create and configure the window
        JFrame window = new JFrame();
        window.setTitle("Sensor Graph GUI");
        window.setSize(600, 400);
        window.setLayout(new BorderLayout());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // create a drop-down box and connect button, then place them at the top of the window
        JComboBox<String> portList = new JComboBox<String>();
        JButton connectButton = new JButton("Connect");
        JPanel topPanel = new JPanel();
        
        // place our combobox and button to the panel
        topPanel.add(portList);
        topPanel.add(connectButton);
        
        // put that panel in the top region of our window
        window.add(topPanel, BorderLayout.NORTH);
        
        // populate the drop-down box
        SerialPort[] portNames = SerialPort.getCommPorts();
        for( int i = 0; i < portNames.length; i++ )
        {
            portList.addItem(portNames[i].getSystemPortName());
        }
        
        // Create the line graph
        XYSeries series = new XYSeries("Light Sensor Readings");
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        
        JFreeChart chart = ChartFactory.createXYLineChart("Light Sensor Readings", "Time (seconds)", "", dataset);
        
        series.add(1, 2);
        window.add(new ChartPanel(chart), BorderLayout.CENTER);
        
        // configure the connect button and use another thread to listen for data
        
        
        // show the window
        window.setVisible(true);
    }
}
