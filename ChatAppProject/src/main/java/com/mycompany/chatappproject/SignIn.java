package com.mycompany.chatappproject;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class SignIn {
  
    static PreparedStatement pstmt;
   
    static ResultSet set;
  
    static String userid;
    static String mail;
    
    static Color colblue = new Color(93, 95, 239);
    
    SignIn()
    {
         JFrame signin = new JFrame();
         signin.setResizable(false);
         JButton b = new JButton("SignIn");
         b.setBackground(colblue);
         b.setForeground(Color.WHITE);
         
         
         JLabel l = new JLabel("Don't have any account? SignUp");
         JTextField email = new JTextField();
         JTextField password = new JTextField();
         JLabel title = new JLabel("SignIn");
         
         
        JLabel em = new JLabel("Enter Email");
        JLabel pass = new JLabel("Enter Password");
        
         
         signin.setVisible(true);
         signin.setSize(500,500);
         signin.setTitle("SignIn");
         signin.getContentPane().setBackground(Color.white);
         
         signin.setLayout(null);
         signin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
         signin.add(title);
         signin.add(email);
         signin.add(b);
         signin.add(password);
         signin.add(l);
         signin.add(em);
         signin.add(pass);
        
         
         //======== Setting location ===================
         
         title.setBounds(200, 20, 200, 50);
         
         em.setBounds(80, 100, 100, 25);
         email.setBounds(180, 100, 150, 25);
         
         pass.setBounds(80, 140, 100, 25);
         password.setBounds(180, 140, 150, 25);
         
         b.setBounds(180, 190, 100, 25);
         
         
         l.setBounds(150, 220, 200, 20);
         
         
         b.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
             
                String em = email.getText().toString();
                String P = password.getText().toString();
        
                try{
         
                    String q2 = "select * from users where Email ='"+em+"' and Password ='"+P+"'";

                    pstmt = DatabaseConnection.con.prepareStatement(q2);

                    set = pstmt.executeQuery(q2);

                    int i=0;
                    while(set.next())
                    {
                       
                        userid = set.getString(1);
                           mail = em;
                           
                           Server.id.add(userid);
                           Server.email.add(mail);
                           
                           System.out.println("ID AND EM"+ userid+mail);
                           
                           JOptionPane.showMessageDialog(null, "Sucess");
                          
                           
                           signin.setVisible(false);
                          
                           System.out.println("NOW GOING TOWARDS SOCKET");

                           new HomePage();
                           
                    }


                }

                catch(Exception ee)
                {
                   System.out.println("Exception"+ee.getMessage());
                }

            }
        });
         
         
         
         l.addMouseListener(new MouseListener(){
             @Override
             public void mouseClicked(MouseEvent e) {
                 Signup sp = new Signup();
                 signin.setVisible(false);
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
