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
        frame = new AppFrame2(sysAdmin, account);
      }
    });
  }

  public JPanel listPanel(boolean checkPensioner) {
    
    JPanel list = new JPanel();
    //System.out.println(this.db.getDatabase());
    JLabel header = new JLabel(
            "EmpID,"
            + "First Name, "
            + "Last Name, "
            + "Salary, "
            + "Department, "
            + "Years of Work, "
            + "Location"
    );
    list.add(header);
    
    Employee[] employees = this.db.getDatabase();
    
    
    for (int i=0; i<employees.length; i++) {
      if (employees[i] != null) {
        JPanel line = new JPanel();
        String info = "";
        Employee e = employees[i];
        String empID = Integer.toString(e.getEmpId());
        String salary = Double.toString(e.getSalary());
        String pension = Integer.toString(e.getPension());
    
        info = empID + ", " + 
                e.getFirstname() + ", " + 
                e.getLastname() +  ", " + 
                salary + ", " + 
                e.getDepartment() +  ", " + 
                pension +  ", " + 
                e.getLocation() + "\n";
        if ((checkPensioner == false) || 
                (checkPensioner == true && e.isPensioner() == true)) {
          JLabel userData = new JLabel(info);
          line.add(userData);
          list.add(line);
        }
      }
    }
    
    if (checkPensioner == true) {
//      listAllButton.addActionListener(new ActionListener() {
//        public void actionPerformed(ActionEvent e) {
//         frame.swapCenterPanel(frame, frame.subPanel, listPanel(false));
//         list.remove(listAllButton);
//        }
//      });
//      list.add(listAllButton);
    } 
    else {
//      listPensionerButton.addActionListener(new ActionListener() {
//        public void actionPerformed(ActionEvent e) {
//         frame.swapCenterPanel(frame, frame.subPanel, listPanel(true));
//         list.remove(listPensionerButton);
//        }
//      });
//      list.add(listPensionerButton);
    }
    
    
    
    return list;
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
