/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EIMS;

/**
 *
 * @author mjelks
 */
//Imports are listed in full to show what's being used
//could just import javax.swing.* and java.awt.* etc..
import java.awt.*;
import javax.swing.*;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.swing.border.Border;

public class AppFrame2 {

  public JFrame guiFrame;
  public CardLayout cards;
  public static JPanel cardPanel;

  private Account account;
  private SysAdmin sysAdmin;
  private EmployeeDatabase db;
  private JTextField userTextField;
  private JPasswordField passTextField;

  public AppFrame2(SysAdmin sys, Account acct, EmployeeDatabase db) {
    guiFrame = new JFrame();

    //make sure the program exits when the frame closes
    guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    guiFrame.setTitle("EIMSApp");
    //guiFrame.setSize(400,300);
    Toolkit kit = Toolkit.getDefaultToolkit();
    Dimension dim = kit.getScreenSize();
    int screenWidth = dim.width;
    int screenHeight = dim.height;
    //guiFrame.setSize(screenWidth /2 , screenHeight / 2);
    guiFrame.setSize(525, 740);

    //This will center the JFrame in the middle of the screen
    guiFrame.setLocationRelativeTo(null);
    guiFrame.setLayout(new BorderLayout());

    JPanel tabsPanel = firstTabButtons();
    guiFrame.add(tabsPanel, BorderLayout.SOUTH);

    cards = new CardLayout();
    cardPanel = new JPanel();
    cardPanel.setLayout(cards);
    cards.show(cardPanel, "Login");

    sysAdmin = sys;
    account = acct;
    this.db = db;

    JPanel firstCard = new JPanel();
    firstCard.add(userPanel());
    firstCard.add(passPanel());

    JPanel secondCard = new JPanel();

    cardPanel.add(firstCard, "Login");
    cardPanel.add(secondCard, "Menu");

    guiFrame.add(tabsPanel, BorderLayout.SOUTH);
    guiFrame.add(cardPanel, BorderLayout.CENTER);
    guiFrame.setVisible(true);
  }

  private JPanel firstTabButtons() {
    //creating a border to highlight the JPanel areas
    //Border outline = BorderFactory.createLineBorder(Color.black);

    JPanel tabsPanel = new JPanel();
    //tabsPanel.setBorder(outline);
    JButton switchCards = new JButton("Login");
    switchCards.setActionCommand("Login");
    switchCards.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        char[] password = passTextField.getPassword();
        account.setUsername(userTextField.getText());
        account.setPassword(new String(password));
        try {
          boolean loginCheck
                  = sysAdmin.login(account.getUsername(), account.getPassword());
          System.out.println(loginCheck);
          if (loginCheck == true) {
            cardPanel.add(listPanel(false), "list_all");
            cards.show(cardPanel, "list_all");
            //cards.show(cardPanel, "Menu");
            // swap bottom buttons
            swapPanel(guiFrame, tabsPanel, secondTabButtons());
          } else {
            // clear the text fields and flash warning
            userTextField.setText("");
            passTextField.setText("");
            guiFrame.validate();
          }
        } catch (IOException ef) {
          //ef.printStackTrace();
          userTextField.setText("");
          passTextField.setText("");
          guiFrame.validate();
        }

      }
    });
    tabsPanel.add(switchCards);

    return tabsPanel;
  }

  private JPanel userPanel() {
    int column_width = 15;
    JPanel userPanel = new JPanel();
    JLabel userLabel = new JLabel("Username: ");
    userLabel.setDisplayedMnemonic(KeyEvent.VK_U);
    userTextField = new JTextField(null, null, column_width);
    userLabel.setLabelFor(userTextField);
    userPanel.add(userLabel);
    userPanel.add(userTextField);

    return userPanel;
  }

  private JPanel passPanel() {
    int column_width = 15;
    JPanel passPanel = new JPanel();
    JLabel passLabel = new JLabel("Password: ");
    passLabel.setDisplayedMnemonic(KeyEvent.VK_P);
    passTextField = new JPasswordField(null, null, column_width);
    passLabel.setLabelFor(passTextField);
    passPanel.add(passLabel);
    passPanel.add(passTextField);

    return passPanel;

  }

  private JPanel secondTabButtons() {

    JPanel tabsPanel = new JPanel();
    JButton listAllButton = new JButton("List All");
    JButton listPensionerButton = new JButton("List Pensioners");
    JButton addUserButton = new JButton("Add");
    JButton deleteUserButton = new JButton("Delete");
    JButton searchUserButton = new JButton("Search");

    listAllButton.setActionCommand("list_all");
    listAllButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        //JPanel listAllCard = new JPanel();
        cardPanel.add(listPanel(false), "list_all");
        cards.show(cardPanel, "list_all");

        //swapPanel(guiFrame, tabsPanel, firstTabButtons());
      }
    });

    listPensionerButton.setActionCommand("list_pensioner");
    listPensionerButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        //JPanel listAllCard = new JPanel();
        cardPanel.add(listPanel(true), "list_p");
        cards.show(cardPanel, "list_p");

        //swapPanel(guiFrame, tabsPanel, firstTabButtons());
      }
    });

    addUserButton.setActionCommand("add_user");
    addUserButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        //JPanel listAllCard = new JPanel();
        cardPanel.add(addUserPanel(), "add_u");
        cards.show(cardPanel, "add_u");
      }
    });

    searchUserButton.setActionCommand("search_user");
    searchUserButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        //JPanel listAllCard = new JPanel();
        cardPanel.add(searchPanel(), "search_u");
        cards.show(cardPanel, "search_u");

        //swapPanel(guiFrame, tabsPanel, firstTabButtons());
      }
    });

    deleteUserButton.setActionCommand("delete_user");
    deleteUserButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        if (db.getEmployeeCount() > 0) {
          //JPanel listAllCard = new JPanel();
          cardPanel.add(deletePanel(), "delete_u");
          cards.show(cardPanel, "delete_u");

          //swapPanel(guiFrame, tabsPanel, firstTabButtons()); 
        } else {
          JOptionPane.showMessageDialog(
                  null,
                  "You have already deleted all items from the list",
                  "Employee Search",
                  JOptionPane.PLAIN_MESSAGE
          );
        }
      }
    });

    tabsPanel.add(listAllButton);
    tabsPanel.add(listPensionerButton);
    tabsPanel.add(addUserButton);
    tabsPanel.add(searchUserButton);
    tabsPanel.add(deleteUserButton);

    return tabsPanel;
  }

  private JPanel searchPanel() {
    JPanel panel = new JPanel();
    int column_width = 15;

    JPanel userPanel = new JPanel();
    JLabel userLabel = new JLabel("Employee ID to Search: ");
    JTextField searchTextField = new JTextField(null, null, column_width);
    userLabel.setLabelFor(searchTextField);
    userPanel.add(userLabel);
    userPanel.add(searchTextField);

    JPanel userSearch = new JPanel();
    JButton searchUser = new JButton("Search");
    searchUser.setActionCommand("search_user");
    searchUser.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        if (searchTextField.getText().equals("")) {
          JOptionPane.showMessageDialog(
                  null,
                  "Field can't be blank",
                  "Employee Search",
                  JOptionPane.PLAIN_MESSAGE
          );
        } else {
          String message;
          String id = searchTextField.getText();
          Employee emp = db.query(Integer.parseInt(id));
          if (emp != null) {
            message = "I FOUND employee with id : " + id + " " + emp.getFirstname() + " " + emp.getLastname();
          } else {
            message = "I was not able an employee with id: " + id;
          }
          JOptionPane.showMessageDialog(
                  null,
                  message,
                  "Employee Search",
                  JOptionPane.PLAIN_MESSAGE
          );
        }
      }
    });
    userSearch.add(searchUser);

    panel.add(userPanel);
    panel.add(searchUser);

    return panel;
  }

  private JPanel deletePanel() {
    JPanel panel = new JPanel();
    int column_width = 15;

    JPanel userPanel = new JPanel();
    JLabel userLabel = new JLabel("Employee ID to Delete: ");
    JTextField deleteTextField = new JTextField(null, null, column_width);
    userLabel.setLabelFor(deleteTextField);
    userPanel.add(userLabel);
    userPanel.add(deleteTextField);

    JPanel userDelete = new JPanel();
    JButton deleteUser = new JButton("Delete User");
    deleteUser.setActionCommand("delete_user");
    deleteUser.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        System.out.println(deleteTextField.getText());
        if (deleteTextField.getText().equals("")) {
          JOptionPane.showMessageDialog(
                  null,
                  "Field can't be blank",
                  "Employee Delete",
                  JOptionPane.PLAIN_MESSAGE
          );
        } else {
          boolean result = db.delete(Integer.parseInt(deleteTextField.getText()));
          if (result == true) {
            cardPanel.add(listPanel(false), "list_all");
            cards.show(cardPanel, "list_all");
          } else {
            JOptionPane.showMessageDialog(
                    null,
                    "There is no Employee with that ID",
                    "Employee Search",
                    JOptionPane.PLAIN_MESSAGE
            );
          }
        }
      }

    });
    userDelete.add(deleteUser);

    panel.add(userPanel);
    panel.add(deleteUser);

    return panel;
  }

  private JPanel addUserPanel() {
    JPanel panel = new JPanel();
    int column_width = 15;

    JPanel userPanel = new JPanel();
    JLabel userLabel = new JLabel("First Name: ");
    JTextField userTextField = new JTextField(null, null, column_width);
    userLabel.setLabelFor(userTextField);
    userPanel.add(userLabel);
    userPanel.add(userTextField);

    JPanel lastPanel = new JPanel();
    JLabel lastLabel = new JLabel("Last Name: ");
    JTextField lastTextField = new JTextField(null, null, column_width);
    lastLabel.setLabelFor(lastTextField);
    lastPanel.add(lastLabel);
    lastPanel.add(lastTextField);

    JPanel empPanel = new JPanel();
    JLabel empLabel = new JLabel("Employee ID: ");
    JTextField empTextField = new JTextField(null, null, column_width);
    empLabel.setLabelFor(empTextField);
    empPanel.add(empLabel);
    empPanel.add(empTextField);

    JPanel salaryPanel = new JPanel();
    JLabel salaryLabel = new JLabel("Salary: ");
    JTextField salaryTextField = new JTextField(null, null, column_width);
    salaryLabel.setLabelFor(salaryTextField);
    salaryPanel.add(salaryLabel);
    salaryPanel.add(salaryTextField);

    JPanel deptPanel = new JPanel();
    JLabel deptLabel = new JLabel("Department: ");
    JTextField deptTextField = new JTextField(null, null, column_width);
    deptLabel.setLabelFor(deptTextField);
    deptPanel.add(deptLabel);
    deptPanel.add(deptTextField);

    JPanel yearPanel = new JPanel();
    JLabel yearLabel = new JLabel("Years of Work: ");
    JTextField yearTextField = new JTextField(null, null, column_width);
    yearLabel.setLabelFor(yearTextField);
    yearPanel.add(yearLabel);
    yearPanel.add(yearTextField);

    JPanel workPanel = new JPanel();
    JLabel workLabel = new JLabel("Work Location: ");
    JTextField workTextField = new JTextField(null, null, column_width);
    workLabel.setLabelFor(workTextField);
    workPanel.add(workLabel);
    workPanel.add(workTextField);

    JPanel userAdd = new JPanel();
    JButton addUser = new JButton("Add User");
    addUser.setActionCommand("add_user");
    addUser.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        boolean result = db.addEmployee(
                userTextField.getText(),
                lastTextField.getText(),
                Integer.parseInt(empTextField.getText()),
                Double.parseDouble(salaryTextField.getText()),
                deptTextField.getText(),
                Integer.parseInt(yearTextField.getText()),
                workTextField.getText()
        );
        if (result == true) {
          cardPanel.add(listPanel(false), "list_all");
          cards.show(cardPanel, "list_all");
        } else {
          JOptionPane.showMessageDialog(
                  null,
                  "There is already an Employee with that ID",
                  "Employee Search",
                  JOptionPane.PLAIN_MESSAGE
          );
        }
      }
    });
    userAdd.add(addUser);

    panel.add(userPanel);
    panel.add(lastPanel);
    panel.add(empPanel);
    panel.add(salaryPanel);
    panel.add(deptPanel);
    panel.add(yearPanel);
    panel.add(workPanel);
    panel.add(userAdd);

    return panel;
  }

  //All the buttons are following the same pattern
  //so create them all in one place.
  private void addButton(Container parent, String name) {
    JButton but = new JButton(name);
    but.setActionCommand(name);
    parent.add(but);
  }

  private void addPanel(Container parent) {
    JPanel panel = new JPanel();
    parent.add(panel);
  }

  public void swapCenterPanel(JFrame frame, JPanel oldPanel, JPanel newPanel) {
    frame.getContentPane().remove(oldPanel);
    frame.getContentPane().add(newPanel, BorderLayout.CENTER);
    frame.validate();
  }

  public void swapPanel(JFrame frame, JPanel oldPanel, JPanel newPanel) {
    frame.getContentPane().remove(oldPanel);
    frame.getContentPane().add(newPanel, BorderLayout.SOUTH);
    frame.validate();
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

    Employee[] employees = db.getDatabase();

    for (int i = 0; i < employees.length; i++) {
      if (employees[i] != null) {
        JPanel line = new JPanel();
        String info = "";
        Employee e = employees[i];
        String empID = Integer.toString(e.getEmpId());
        String salary = Double.toString(e.getSalary());
        String pension = Integer.toString(e.getPension());

        info = empID + ", "
                + e.getFirstname() + ", "
                + e.getLastname() + ", "
                + salary + ", "
                + e.getDepartment() + ", "
                + pension + ", "
                + e.getLocation() + "\n";
        if ((checkPensioner == false)
                || (checkPensioner == true && e.isPensioner() == true)) {
          JLabel userData = new JLabel(info);
          line.add(userData);
          list.add(line);
        }
      }
    }

    return list;
  }

}
