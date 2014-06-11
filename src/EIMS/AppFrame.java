package EIMS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author mjelks
 */
public class AppFrame extends JFrame {
  private JLabel label1 = new JLabel("Foothill College");
  private JLabel label2 = new JLabel("Spring 2014");
  private JLabel label3 = new JLabel("Programming in Java");
  private String title = "EIMSApp";
  public static JPanel centerPanel = new JPanel();
  
//  public String AppFrame() {
//	return "Please pass a Panel to the constructor";
//  }
  // Frame size might be defined here
  // private final int WIDTH = 200 ;
  // private final int HEIGHT = 200 ;
  public AppFrame() {
	// setting frame attributes ("look and feel")
	setTitle(this.title);

	Toolkit kit = Toolkit.getDefaultToolkit();
	Dimension dim = kit.getScreenSize();
	int screenWidth = dim.width;
	int screenHeight = dim.height;

	setSize(screenWidth / 2, screenHeight / 2);

	// positioning the frame in the center of the screen
	setLocationRelativeTo(null);
	// creating panels

	JPanel northPanel = new JPanel();
    JPanel defaultCenter = new JPanel();
    //defaultCenter.setLayout(new GridLayout(1,2)); 
    defaultCenter.setBorder(BorderFactory.createTitledBorder("Main Panel"));
    
    centerPanel.setBorder(BorderFactory.createTitledBorder("swap Panel"));
    defaultCenter.add(centerPanel);
	JPanel southPanel = new JPanel();

        // add panels to the frame based on Frame's layout manager
	add(northPanel, BorderLayout.NORTH);
	add(defaultCenter, BorderLayout.CENTER);
	add(southPanel, BorderLayout.SOUTH);

    //  adding UI components to the panels
	northPanel.add(this.label1);
//	centerPanel.add(defaultCenter);
    //this.setCenterPanel(panelContent);
	southPanel.add(this.label3);
    
    Container pane = getContentPane();  
    setContentPane(pane);
    pane.add(defaultCenter);
	setResizable(false);

	// adding GUI components: TBD
	// setting frame behavior
	//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	addWindowListener(new WindowAdapter() {
	  public void windowClosing(WindowEvent e) {
		System.exit(0);
	  }
	}
	);
  }
  
  public void swapCenterPanel(JFrame frame, JPanel oldPanel, JPanel newPanel) {
    frame.getContentPane().remove(oldPanel);
    frame.getContentPane().add(newPanel, BorderLayout.CENTER); 
    frame.validate();
  }
  
  public void setCenterPanel(JPanel panelContent) {
    //centerPanel.add(panelContent);
    //centerPanel.remove(panelContent);
    //centerPanel.add(panelContent);
    //this.repaint();
    System.out.println("KSJFK");
  }
} // end of class AppFrame
