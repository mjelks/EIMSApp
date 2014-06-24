package EIMS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class EIMSApp extends JFrame {

  private SysAdmin sysAdmin;
  private EmployeeDatabase db;
  private Account account;
  public AppFrame2 frame;

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
      @Override
      public void run() {
        frame = new AppFrame2(sysAdmin, account, db);
      }
    });
  }

  
  

  
//  confirmButton.addActionListener(new ActionListener() {
//      public void actionPerformed(ActionEvent e) {
//        char[] password = passTextField.getPassword();
//        account.setUsername(userTextField.getText());
//        account.setPassword(new String(password));
//        try {
//          boolean loginCheck = 
//                  sysAdmin.login(account.getUsername(), account.getPassword());
//          System.out.println(loginCheck);
//          if (loginCheck == true) {
//            frame.swapCenterPanel(frame, frame.subPanel, listPanel(false));
//          } else {
//            // clear the text fields and flash warning
//            userTextField.setText("");
//            passTextField.setText("");
//          }
//        } catch (IOException ef) {
//          ef.printStackTrace();
//        }
//        dispose();
//        panel.remove(confirmButton);
//      }
//    });
  
}
