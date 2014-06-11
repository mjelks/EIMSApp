package EIMS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class EIMSApp extends JFrame {

  private SysAdmin sysAdmin;
  private EmployeeDatabase db;
  private Account account;
  public AppFrame frame;

  public static void main(String[] args) {
    // show initial prompt
    new EIMSApp().startApp();
  }

  public EIMSApp() {
    this.sysAdmin = new SysAdmin();
    this.db = new EmployeeDatabase();
    this.db.buildDB();
    this.account = new Account();
  }

  private void startApp() {
    System.out.println("INVOKED!");
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        frame = new AppFrame();
        frame.setVisible(true); // AppFrame now comes to life
        welcome();
      }
    });
  }

  public JPanel welcome() {
    JPanel welcomePanel = new JPanel(new BorderLayout());

    JButton confirmButton = new JButton("Login");
    welcomePanel.add(confirmButton, BorderLayout.SOUTH);
    JPanel oldPanel = new AppFrame().centerPanel;
    frame.swapCenterPanel(frame, oldPanel, welcomePanel);
    
    confirmButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("IFJSKLFJKLS");
        frame.swapCenterPanel(frame, welcomePanel, userPass());
      }
    });
    
    return welcomePanel;
  }
  
  public JPanel userPass() {
    int column_width = 15;
    JPanel userPanel = new JPanel(new BorderLayout());
    JLabel userLabel = new JLabel("Username: ");
    userLabel.setDisplayedMnemonic(KeyEvent.VK_U);
    JTextField userTextField = new JTextField(null, null, column_width);
    userLabel.setLabelFor(userTextField);
    userPanel.add(userLabel, BorderLayout.WEST);
    userPanel.add(userTextField, BorderLayout.CENTER);

    JPanel passPanel = new JPanel(new BorderLayout());
    JLabel passLabel = new JLabel("Password: ");
    passLabel.setDisplayedMnemonic(KeyEvent.VK_P);
    JPasswordField passTextField = new JPasswordField(null, null, column_width);
    passLabel.setLabelFor(passTextField);
    passPanel.add(passLabel, BorderLayout.WEST);
    passPanel.add(passTextField, BorderLayout.CENTER);

    JPanel panel = new JPanel(new BorderLayout());
    panel.add(userPanel, BorderLayout.NORTH);
    panel.add(passPanel, BorderLayout.CENTER);
    
    JButton confirmButton = new JButton("OK");
    panel.add(confirmButton, BorderLayout.SOUTH);
    confirmButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        char[] password = passTextField.getPassword();
        account.setUsername(userTextField.getText());
        account.setPassword(new String(password));
        try {
          boolean test = sysAdmin.login(account.getUsername(), account.getPassword());
          System.out.println(test);
          if (test == true) {
            JPanel oldPanel = new AppFrame().centerPanel;
            JPanel newPanel = listPanel();
            frame.swapCenterPanel(frame, oldPanel, newPanel);
//            System.out.println(db.getDatabase());
            
          }
        } catch (IOException ef) {
          ef.printStackTrace();
        }
        dispose();
      }
    });

    return panel;
  }
  
  public JPanel listPanel() {
    JPanel list = new JPanel(new BorderLayout());
    System.out.println(this.db.getDatabase());
    return list;
  }

}
