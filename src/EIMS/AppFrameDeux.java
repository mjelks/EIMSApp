/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package EIMS;
//Java Core Package  
import javax.swing.*;  
//Java Extension Package  
import java.awt.*; 

/**
 *
 * @author mjelks
 */
public class AppFrameDeux extends JFrame {
 //Initializing JPanels  
    private JPanel mainPanel;
    public static JPanel subPanel;
  
    //Setting up GUI  
    public AppFrameDeux(){  
 
        //Set Size of the Window (WIDTH, HEIGHT)  
        setSize(600,1000);  
  
        //Exit Property of the Window  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
          
        //Constructing Main JPanel with GridLayout of 1 row and 2 column  
        mainPanel = new JPanel();  
        mainPanel.setBorder(BorderFactory.createTitledBorder("Main Panel"));  
        mainPanel.setLayout(new GridLayout(1,2));  
          
        //Constructing JPanel 1 and 2 with GridLayout of 1 row and 1 column  
        subPanel = new JPanel();  
        subPanel.setBorder(BorderFactory.createTitledBorder("Sub Panel 1"));  
        subPanel.setLayout(new GridLayout(1,1));  
          
        //Adding JPanel 3 to JPanel 1 which means JPanel 3 is inside JPanel 1  
        //subPanel1.add(subPanel3);  
          
        //Adding JPanel 1 and 2 to main JPanel  
        mainPanel.add(subPanel);  
        
        //Setting up the container ready for the components to be added.  
        Container pane = getContentPane();  
        setContentPane(pane);  
          
        //Adding the main JPanel to the container  
        pane.add(mainPanel);  
  
        /**Set all the Components Visible. 
         * If it is set to "false", the components in the container will not be visible. 
         */  
        setVisible(true);  
        setResizable(false);
    }
  
  public void swapCenterPanel(JFrame frame, JPanel oldPanel, JPanel newPanel) {
    newPanel.setBorder(BorderFactory.createTitledBorder("Sub Panel 1"));  
//    newPanel.setLayout(new GridLayout(1,1));
    frame.getContentPane().remove(oldPanel);
    frame.getContentPane().add(newPanel); 
    frame.validate();
  }
}
