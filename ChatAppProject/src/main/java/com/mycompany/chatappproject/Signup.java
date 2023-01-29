package com.mycompany.chatappproject;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Signup {
    static PreparedStatement pstmt;
   
    Signup()
    {
        JFrame SignUp = new JFrame();
        SignUp.setVisible(true);
        SignUp.setResizable(false);
        SignUp.getContentPane().setBackground(Color.white);
        
        JButton b2 = new JButton("SignUp");
        b2.setBackground(SignIn.colblue);
        b2.setForeground(Color.white);
        
        
        JLabel l2 = new JLabel("Already have an account? SignIn");
        JLabel name = new JLabel("Enter Name");
        JLabel email = new JLabel("Enter Email");
        JLabel password = new JLabel("Enter Password");
        JLabel title = new JLabel("SignUp");
         
        
        JTextField nam = new JTextField();
        JTextField pass = new JTextField();
        JTextField mail = new JTextField();
        
        
         
         SignUp.setSize(500,500);
         SignUp.setTitle("SignUp");
         SignUp.setLayout(null);
         SignUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
         SignUp.add(name);
         SignUp.add(nam);
         SignUp.add(email);
         SignUp.add(mail);
         SignUp.add(password);
         SignUp.add(pass);
        
         SignUp.add(b2);
          
         
         SignUp.add(l2);
         SignUp.add(title);
         

        //======== Setting location ===================
         title.setBounds(200, 20, 200, 50);
         
         name.setBounds(80, 100, 100, 25);
         nam.setBounds(180, 100, 150, 25);
         
         
         email.setBounds(80, 140, 100, 25);
         mail.setBounds(180, 140, 150, 25);
         
         password.setBounds(80, 180, 100, 25);
         pass.setBounds(180, 180, 150, 25);
         
         
         b2.setBounds(180, 220, 100, 25);
         l2.setBounds(150, 250, 200, 20);
         
         
         
         b2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String na = nam.getText().toString();
                String Pasword = pass.getText().toString();
                String em = mail.getText().toString();
                
                if(na.isEmpty() || Pasword.isEmpty() || em.isEmpty())
                {
                   JOptionPane.showMessageDialog(null, "Please fill all fields");   
                }
                
                else{
                String sql = "INSERT INTO `users`(`Name`, `Email`, `Password`) VALUES ('"+na+"','"+em+"','"+Pasword+"')";

                try {
                    pstmt=DatabaseConnection.con.prepareStatement(sql);
                    pstmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Inserted Sucessfully");
                    SignUp.setVisible(false);
                    new SignIn();
                    
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Exception " + ex.getMessage());
                }

            }
            }
          
         });
       
         l2.addMouseListener(new MouseListener(){
             @Override
             public void mouseClicked(MouseEvent e) {
                 SignIn sn = new SignIn();
                 SignUp.setVisible(false);
             }

             @Override
             public void mousePressed(MouseEvent e) {
             }

             @Override
             public void mouseReleased(MouseEvent e) {
             }

             @Override
             public void mouseEntered(MouseEvent e) {
             }

             @Override
             public void mouseExited(MouseEvent e) {
             }
         });
      
    }
}
