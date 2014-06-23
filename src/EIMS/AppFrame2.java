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
    private JTextField userTextField;
    private JPasswordField passTextField;
    
  

//    public static void main(String[] args) {
//     
//         //Use the event dispatch thread for Swing components
//         EventQueue.invokeLater(new Runnable()
//         {
//             
//            @Override
//             public void run()
//             {
//                 
//                 new AppFrame2();         
//             }
//         });
//              
//    }
    
    public AppFrame2(SysAdmin sys, Account acct)
    { 
        guiFrame = new JFrame();
        
        //make sure the program exits when the frame closes
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("EIMSApp");
        //guiFrame.setSize(400,300);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension dim = kit.getScreenSize();
        int screenWidth = dim.width;
        int screenHeight = dim.height;
        guiFrame.setSize(screenWidth /2 , screenHeight / 2);
      
        //This will center the JFrame in the middle of the screen
        guiFrame.setLocationRelativeTo(null);
        guiFrame.setLayout(new BorderLayout());
        
        JPanel tabsPanel = firstTabButtons();
        guiFrame.add(tabsPanel,BorderLayout.SOUTH);
        
        
        cards = new CardLayout();
        cardPanel = new JPanel();
        cardPanel.setLayout(cards);
        cards.show(cardPanel, "Login");
        
        sysAdmin = sys;
        account = acct;
                
        
        JPanel firstCard = new JPanel();
//        firstCard.setBackground(Color.GREEN);
        firstCard.add(userPanel());
        firstCard.add(passPanel());
        
        
        JPanel secondCard = new JPanel();
//        secondCard.setBackground(Color.BLUE);
        addButton(secondCard, "LEEKS");
        addButton(secondCard, "TOMATOES");
        addButton(secondCard, "PEAS");
        
        cardPanel.add(firstCard, "Login");
        cardPanel.add(secondCard, "Menu");
        
        guiFrame.add(tabsPanel,BorderLayout.SOUTH);
        guiFrame.add(cardPanel,BorderLayout.CENTER);
        guiFrame.setVisible(true);
    }
    
    private JPanel firstTabButtons() {
        //creating a border to highlight the JPanel areas
        //Border outline = BorderFactory.createLineBorder(Color.black);
        
        JPanel tabsPanel = new JPanel();
        //tabsPanel.setBorder(outline);
        JButton switchCards = new JButton("Login");
        switchCards.setActionCommand("Login");
        switchCards.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
              char[] password = passTextField.getPassword();
              account.setUsername(userTextField.getText());
              account.setPassword(new String(password));
              try {
                boolean loginCheck = 
                        sysAdmin.login(account.getUsername(), account.getPassword());
                System.out.println(loginCheck);
                if (loginCheck == true) {
                  //guiFrame.swapCenterPanel(frame, frame.subPanel, listPanel(false));
                  cards.show(cardPanel, "Menu");
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
        JButton listAllButton = new JButton("List All Entries");
        JButton listPensionerButton = new JButton("List Pensioners");
    
        
        listAllButton.setActionCommand("list_all");
        listPensionerButton.setActionCommand("list_pensioner");
        listAllButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                cards.show(cardPanel, "list_all");
                //swapPanel(guiFrame, tabsPanel, firstTabButtons());
            }
        });
        tabsPanel.add(listAllButton);
        tabsPanel.add(listPensionerButton);
        
        return tabsPanel;
    }
    
    //All the buttons are following the same pattern
    //so create them all in one place.
    private void addButton(Container parent, String name)
    {
        JButton but = new JButton(name);
        but.setActionCommand(name);
        parent.add(but);
    }

    public void swapCenterPanel(JFrame frame, JPanel oldPanel, JPanel newPanel) 
    {
      frame.getContentPane().remove(oldPanel);
      frame.getContentPane().add(newPanel, BorderLayout.CENTER); 
      frame.validate();
    }
    
    public void swapPanel(JFrame frame, JPanel oldPanel, JPanel newPanel) 
    {
      frame.getContentPane().remove(oldPanel);
      frame.getContentPane().add(newPanel, BorderLayout.SOUTH); 
      frame.validate();
    }
}
