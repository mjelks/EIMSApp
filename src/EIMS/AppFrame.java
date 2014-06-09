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
  
  public String AppFrame() {
	return "Please pass a Panel to the constructor";
  }
  // Frame size might be defined here
  // private final int WIDTH = 200 ;
  // private final int HEIGHT = 200 ;
  public AppFrame(JPanel panelContent) {
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
	JPanel centerPanel = new JPanel();
	JPanel southPanel = new JPanel();

        // add panels to the frame based on Frame's layout manager
	add(northPanel, BorderLayout.NORTH);
	add(centerPanel, BorderLayout.CENTER);
	add(southPanel, BorderLayout.SOUTH);

    //  adding UI components to the panels
	northPanel.add(this.label1);
	centerPanel.add(panelContent);
	southPanel.add(this.label3);

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
} // end of class AppFrame
