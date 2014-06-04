package EIMS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EIMSApp extends JFrame
{
  
  private SysAdmin sysAdmin;
  private EmployeeDatabase db;
  
  public static void main(String[] args) {
    // show initial prompt
	new EIMSApp().startApp();
  }
  
  public EIMSApp() {
	this.sysAdmin = new SysAdmin();
	this.db = new EmployeeDatabase();
	this.db.buildDB(); 
  }
  
  private void startApp() {
	System.out.println("INVOKED!");
	EventQueue.invokeLater( new Runnable ()
	{
	  public void run () {
		JFrame frame = new AppFrame(new EIMSApp().userPass());
		frame.setVisible (true); // AppFrame now comes to life
	  }
	});
  }
  
  public JPanel userPass() {
	JPanel userPanel = new JPanel(new BorderLayout());
    JLabel userLabel = new JLabel("Username: ");
    userLabel.setDisplayedMnemonic(KeyEvent.VK_U);
    JTextField userTextField = new JTextField();
    userLabel.setLabelFor(userTextField);
    userPanel.add(userLabel, BorderLayout.WEST);
    userPanel.add(userTextField, BorderLayout.CENTER);

    JPanel passPanel = new JPanel(new BorderLayout());
    JLabel passLabel = new JLabel("Password: ");
    passLabel.setDisplayedMnemonic(KeyEvent.VK_P);
    JPasswordField passTextField = new JPasswordField();
    passLabel.setLabelFor(passTextField);
    passPanel.add(passLabel, BorderLayout.WEST);
    passPanel.add(passTextField, BorderLayout.CENTER);

    JPanel panel = new JPanel(new BorderLayout());
    panel.add(userPanel, BorderLayout.NORTH);
    panel.add(passPanel, BorderLayout.CENTER);
	
	JButton OKButton = new JButton("OK");
	//OKButton.addActionListener(new submitAction());
//	OKButton.addActionListener(new ActionListener() {
//	  public void actionPerformed(ActionEvent e) {
//                if (Login.authenticate(getUsername(), getPassword())) {
//                    JOptionPane.showMessageDialog(LoginDialog.this,
//                            "Hi " + getUsername() + "! You have successfully logged in.",
//                            "Login",
//                            JOptionPane.INFORMATION_MESSAGE);
//                    succeeded = true;
//                    dispose();
//                } else {
//                    JOptionPane.showMessageDialog(LoginDialog.this,
//                            "Invalid username or password",
//                            "Login",
//                            JOptionPane.ERROR_MESSAGE);
//                    // reset username and password
//                    tfUsername.setText("");
//                    pfPassword.setText("");
//                    succeeded = false;
// 
//                }
//            }
//        });
//        JButton btnCancel = new JButton("Cancel");
//        btnCancel.addActionListener(new ActionListener() {
// 
//            public void actionPerformed(ActionEvent e) {
//                dispose();
//            }
//	});
	panel.add(OKButton,BorderLayout.SOUTH);
    //content.add(panel, BorderLayout.NORTH);
	
	return panel;
  }
 
//  public static ActionEvent userValidate(ActionEvent e) {
//	System.out.println("IVOIKKJDSFK");
//	
//	return e;
//  }
  
  
  
  
  
//	static class submitAction implements ActionListener {
//	  /** Handle the button click. */
//	  public void actionPerformed(ActionEvent e) {
//		System.out.println("Action Performed: " + e);
//		//System.out.println(e.cmd());
//	  }
//	}
  
}