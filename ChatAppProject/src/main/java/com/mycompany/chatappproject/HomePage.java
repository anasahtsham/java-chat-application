package com.mycompany.chatappproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class HomePage {
    
    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;
    
    static PreparedStatement pstmt;
    static ResultSet set;
    
    static String senderIp="";
    static String recieverIp="";
    static String  selectedIp ="";
   
    
    //========== gui compoenets ==================
    static Color colblue = new Color(93, 95, 239);
    
    static JTextField m = new JTextField();
    static JButton send = new JButton("Send");
    
    static JLabel title = new JLabel();
    
    static JPanel ips = new JPanel();
    static JPanel chats = new JPanel();
    static JPanel panelleft = new JPanel();
    JPanel panelright = new JPanel();
        
    JScrollPane sp =new JScrollPane(ips);     // scrollpane for IP'S
    JScrollPane sp2 =new JScrollPane(chats);    // scrollpane for Chats
    
    JFrame main = new JFrame();
    
    static Box box = Box.createVerticalBox();  
    
    //=========================== MAIN ================================
     public static void main(String[] args) throws IOException {
        
      senderIp = "192.168.1.121";
      recieverIp = selectedIp;
        
      //=== calling database connection class and homepage           
        new DatabaseConnection();
        new HomePage();
       
    } 
   
  
   //========================= HomePage Class ===============================
    HomePage() throws IOException
    {
        // Create a new thread to establish the socket connection
         Thread socketThread = new Thread(new Runnable() {
         
         @Override
         public void run() {
           try {
               //==== connecting with server =================
               s = new Socket("192.168.74.229", 1201);
               din = new DataInputStream(s.getInputStream());
               dout = new DataOutputStream(s.getOutputStream());
                                       
               senderIp = "192.168.74.30";
       
               recieverIp = selectedIp;

               
                //================= Reading Messages From Server ======================
                String msgin ="";
                while(!msgin.equals("exit"))
                 {
                     msgin = din.readUTF();
                     
                     //=== Callling messages panel function to get current message with panel ======
                     JPanel p2 = MessagePanel(msgin, Color.lightGray);
                     
                     //===== Setting layout to border in chats panel ==========
                     chats.setLayout(new BorderLayout());
                     
                     //==== creating new panel to add message panel to it ======
                     JPanel left = new JPanel(new BorderLayout());
                     left.add(p2, BorderLayout.LINE_START);
                     
                     //==== Adding left panel to box =====================
                     box.add(left);
                     box.add(Box.createVerticalStrut(15));
                     
                     //==== Adding box to chats ======================
                     chats.add(box, BorderLayout.PAGE_START);
                          
                     //=== for autoscroll to bottom =========================
                     sp2.getVerticalScrollBar().setValue(sp2.getVerticalScrollBar().getMaximum());

                          
                     //=== insert message to dataabse ==================
                     String sql = "INSERT INTO `messages`(`SenderIP`, `ReciverIp`, `Message`) VALUES ('"+selectedIp+"','"+senderIp+"','"+msgin+"')";
                     try {
                        pstmt=DatabaseConnection.con.prepareStatement(sql);
                        pstmt.executeUpdate();
                           
                      } 
                 
                     catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "ExceptionSQL " + ex.getMessage());
                      }

                  }
             } 
           
                catch (IOException e) {
                   e.printStackTrace();
               }
              
              }
            });
         
                // Start the socket connection thread
                 socketThread.start();
       
                           
                           
        //=============================GUI CODE ================================
         main.setVisible(true);
         main.setResizable(false);
         main.setSize(1160, 570);
         main.setTitle("Main");
         main.setLayout(null);
         main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         main.getContentPane().setBackground(Color.white);
          
         
        
         //==== Adding to frame ===============
         main.add(panelleft);
         main.add(panelright);

         //=== adding to panel ================         
         panelright.add(sp2);
         panelright.add(m);
         panelright.add(send);
         panelright.add(title);
         
         //== add to PANEL LEFT ========
         panelleft.add(sp);

         
         // setting panels bounds ===========
         panelleft.setBounds(10, 10, 200, 500);
         panelright.setBounds(220, 10, 920, 500);
         
         panelleft.setLayout(null);
         panelright.setLayout(null);
      
         
         //== setting color and other designing properties ============
         panelright.setBackground(colblue);
         send.setBackground(Color.white);
         send.setForeground(colblue);
         title.setForeground(Color.white);
        
         
        //=== calling getips to get IP'S==========
         getIps();
         
        //=== Setting layout of IP'S PANEL ==============
        ips.setLayout(new BoxLayout(ips, BoxLayout.Y_AXIS));
        sp.setBounds(0, 0, 200, 500);
        
        
         //===== Setting panel for chats =============================
         title.setBounds(10, 5, 300, 30);
         sp2.setBounds(10, 40, 900, 400);
         m.setBounds(10, 450, 500, 30);
         send.setBounds(520, 450, 100, 25);
 
        
         oldchats();    //== calling for oldchats to retrieve chats from database
        
           
        
          //=== Saving to database ==============================
         send.addActionListener(new ActionListener(){
             @Override
             public void actionPerformed(ActionEvent e) {
                 try{
                    
                     String msgout="";
                     System.out.println("Client's message: "+ m.getText().toString());
                     msgout = m.getText().trim();
                    
                     //=== calling messagePanel function to get panel with given message 
                     JPanel p2 = MessagePanel(msgout, colblue);                  
                     
                     //==== setting chats panel layout to border 
                     chats.setLayout(new BorderLayout());
                     
                     //===== creating a panel to add P2 into it ==============
                     JPanel right = new JPanel(new BorderLayout());
                     right.add(p2, BorderLayout.LINE_END);
           
                     //====== Now adding right panel with contains P2 in Box to show messages vertically 
                     box.add(right);
                     box.add(Box.createVerticalStrut(15));

                     // adding box to chats panel =================
                     chats.add(box, BorderLayout.PAGE_START);
                     
                     //=== for autoscroll to bottom =========================
                    sp2.getVerticalScrollBar().setValue(sp2.getVerticalScrollBar().getMaximum());

                     
                    chats.revalidate();
                    chats.repaint();
                       
                     m.setText("");
                        
                    
                     recieverIp = "192.168.74.209";   
                     dout.writeUTF(recieverIp+":"+msgout+"@@"+senderIp);  // concating IP's of sender and reciever t
                     System.out.println(recieverIp+":"+msgout+"@@"+senderIp);
                     
                     //=== insert message to dataabse ==================
                     String sql = "INSERT INTO `messages`(`SenderIP`, `ReciverIp`, `Message`) VALUES ('"+senderIp+"','"+recieverIp+"','"+msgout+"')";

                    try {
                        pstmt=DatabaseConnection.con.prepareStatement(sql);
                        pstmt.executeUpdate();
                        
                    } 
                    
                    catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Exception sql " + ex.getMessage());
                    }

                 }
                 
                 catch(Exception ex)
                 {
                     JOptionPane.showMessageDialog(null, "Exception:"+ex.getMessage());
                 }
             }
           
         });
         
    }
    
    
    
    
    
    //================ Getting IP's =======================
    static public void getIps() throws IOException
    {
        Process p = Runtime.getRuntime().exec("arp -a");
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        
        while ((line = reader.readLine()) != null) {
           
          String[] parts = line.split("\\s+");
                 
          if (parts.length > 1 && parts[1].startsWith("192")  && !parts[1].startsWith("192.168.25.")  && !parts[1].startsWith("192.168.40.")) {
              
              System.out.println(parts[1]);
              

//=============LOGIC FOR GETTING CONNECTED IP'S AND EMAIL ==========================
              
              ips.removeAll();
              ips.revalidate();
              ips.repaint();
            
              JPanel ll = new JPanel();
                    ll.setBackground(colblue);

                    
                    JLabel li = new JLabel("192.168.74.209");   // here we are hard coding

                    System.out.println(parts[1]);

                    li.setForeground(Color.white);

                    //=== adding mouse listener on IP's label =================
                    li.addMouseListener(new MouseListener(){
                        @Override
                        public void mouseClicked(MouseEvent e) {

                            recieverIp = li.getText().toString();

                            selectedIp =li.getText().toString();
                            System.out.println("sel: "+selectedIp);

                            title.setText("Abdullah Shahid");

                            //=== Refreshing chats panel and box ===============
                            chats.removeAll();
                            chats.revalidate();
                            chats.repaint();
                       
                            box.removeAll();
                            box.revalidate();
                            box.repaint();

                             m.setText("");

                            oldchats();  // again calling old chats to get old chats of new selected reciever
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


                    //add to panel
                    ll.add(li);
                  
                    ips.add(ll);

                  }
               }
        }
    
    
    //====================== retriving old chats from db ============================
   static void oldchats()
     {
         if(!selectedIp.isEmpty()){
          try{
         
              senderIp = "192.168.74.30";
              recieverIp = "192.168.74.209";
              
             String q2 = "select * from messages where (`SenderIP` ='"+senderIp+ "' AND `ReciverIp`="+ "'"+recieverIp+ "') OR (`SenderIP` ='"+recieverIp+ "' AND `ReciverIp`="+ "'"+senderIp+ "')";

             pstmt = DatabaseConnection.con.prepareStatement(q2);

             set = pstmt.executeQuery(q2);

             int i=0;
             
             while(set.next())
             {
                //=========== if i am sender =======================
                if(senderIp.equals(set.getString(2)))
                {
                    //=== Calling messages panel function to get current message with panel ======
                     JPanel p2 = MessagePanel(set.getString(4), colblue);
                     
                     //=== Setting layout of chats panel to Border =========
                     chats.setLayout(new BorderLayout());
                     
                     //=== Setting layout of right panel to Border =========
                     JPanel right = new JPanel(new BorderLayout());
                     right.add(p2, BorderLayout.LINE_END);
           
                     //===== Adding right panel to Box ============
                     box.add(right);
                     box.add(Box.createVerticalStrut(15));


                     chats.add(box, BorderLayout.PAGE_START);
                    
                }
                
                
                //=== If i am reciever ==============================================
                else if(senderIp.equals(set.getString(3)))
                {
                  //=== Calling messages panel function to get current message with panel ======
                    
                     JPanel p2 = MessagePanel(set.getString(4), Color.lightGray);
                     
                     chats.setLayout(new BorderLayout());
                     
                     //=== Setting layout of right panel to Border =========
                     JPanel left = new JPanel(new BorderLayout());
                     left.add(p2, BorderLayout.LINE_START);
                    
                     //===== Adding left panel to Box ============
                     box.add(left);
                     box.add(Box.createVerticalStrut(15));
                     
                     //==== adding box to chat panel ============
                     chats.add(box, BorderLayout.PAGE_START);
                    
                }

              i++;
             }

        }

         catch(Exception ee)
         {
           System.out.println("Exception"+ee.getMessage());
         }
   
         }
     }
       
     
     //============= MessagePanel function to take message and color as argument and return panel containing message
     public static JPanel MessagePanel(String out, Color c) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel output = new JLabel("<html><p style=\"width: 150px;\">" + out + "</p></html>");
        output.setFont(new Font("Tahoma", Font.PLAIN, 16));
        output.setBackground(c);
        output.setForeground(Color.white);
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15, 15, 15, 50));
        
        panel.add(output);
       
        return panel;
    }
    
}
