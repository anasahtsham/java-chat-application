package com.mycompany.chatappproject;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.*;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;



import java.util.List;

public class Server {
    static StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();
    static ServerSocket ss;
    static ArrayList<Socket> sockets = new ArrayList<>();
    static ArrayList<DataInputStream> dins = new ArrayList<>();
    static ArrayList<DataOutputStream> douts = new ArrayList<>();
    static ArrayList<String> ips = new ArrayList<>();
    static ArrayList<String> id = new ArrayList<>();
    static ArrayList<String> email = new ArrayList<>();
    
    
    static JTextArea area = new JTextArea();
    static JTextField m = new JTextField();
    static JButton send = new JButton("Send");
    
    static int index=0;
    
    static boolean recOnlineCheck;
    
    static PreparedStatement pstmt;
   
    static ResultSet set;
    
    Server() {
    
        JFrame main = new JFrame();
        main.setVisible(true);
        // main.setResizable(false);
        main.setSize(500, 500);
        main.setTitle("Server");
        main.setLayout(null);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.getContentPane().setBackground(Color.gray);

        area.setBounds(10, 10, 500, 300);
        m.setBounds(10, 320, 100, 50);
        send.setBounds(130, 320, 100, 25);

        main.add(area);
        main.add(m);
        main.add(send);

        
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                   
                    String msgout = "";
                    System.out.println("Server's message: " + m.getText().toString());
                    msgout = m.getText().toString();
                    area.append("\nMy: " + msgout);
                    Server.douts.get(Server.index).writeUTF(msgout);
                } 
                
                catch(Exception ex) {
                    JOptionPane.showMessageDialog(null, "Exception:" + ex.getMessage());
                    }
                }


              });
      }
                
    

  //static ServerSocket ss;
   

    public static void main(String[] args) throws IOException {
        // Listen on port 1201
        new DatabaseConnection();    // for database conectivity

        new Server();          // for showing gui 
        
        ss = new ServerSocket(1201);

        while (true) {
            // Accept connections from new clients
            Socket s = ss.accept();
            
            // Adding the socket and streams 
            sockets.add(s);
            dins.add(new DataInputStream(s.getInputStream()));
            douts.add(new DataOutputStream(s.getOutputStream()));
            
            ips.add(s.getInetAddress().getHostAddress());
            System.out.println("Connected: "+ s.getInetAddress().getHostAddress());
           
            // Creating a new thread for the client
            index=sockets.size() - 1;
            new ClientHandler(index, douts.get(douts.size()-1), dins.get(dins.size()-1)).start();
        }
        
    }

    static class ClientHandler extends Thread {
        int index;

        ClientHandler(int index, DataOutputStream dout, DataInputStream din) {
            
            this.index = index;
        }

      //=== overiding funciton of thread class
    @Override
    public void run() {       
        String msgin = "";        
        while (!msgin.equals("exit")) {
            try {
                msgin = dins.get(index).readUTF();    // getting messages from client
            }catch (IOException ex) {
               break;
            }
            // Parse the message to get the list of recipients
            String[] parts = msgin.split(":");
            String recipients = parts[0];
            String message = parts[1];           
            //== parsing again to get sender ======
            String[] parts2 = message.split("@@");
            String sender = parts2[1];
            String messagenew = parts2[0];            
            System.out.println(messagenew);            
            area.append("\n!!!Sent"+recipients+messagenew);           
            // Forwarding the message to the specified recipients
            for (int i = 0; i < sockets.size(); i++) {                
                System.out.println("rec: "+ recipients + " ip :"+ips.get(i));
                if (recipients.equals(ips.get(i))) {
                    try {
                        //Input
                        String text = messagenew;
                        CoreDocument coreDocument = new CoreDocument(text);
                        stanfordCoreNLP.annotate(coreDocument);
                        List<CoreSentence> sentences = coreDocument.sentences();
                        int countPositive = 0, countNeutral = 0, countNegative = 0;
                        for(CoreSentence sentence : sentences) {
                            String sentiment = sentence.sentiment();
                            area.append("\n" + sentiment + ": " + sentence);
                            
                            area.append("\nSent to "+recipients+messagenew+ "  from "+ sender);
                            douts.get(i).writeUTF(messagenew + "\n("+ sentiment + ")");
                            if("Negative".equals(sentiment)){
                                countNegative++;
                            }else if("Positive".equals(sentiment)){
                                countPositive++;
                            }else{
                                countNeutral++;
                            }
                        }
                            String sentiment;
                        if (countNegative >= countPositive) {
                            sentiment = "Negative";
                            if (countNegative == countPositive) {
                                sentiment = "Neutral";
                            }
                        }else{
                            sentiment = "Positive";
                        }  
                    } catch (IOException ex) {
                        break;                       
                    }
                }
            }
        }
        
        // Close the socket and streams
        try {
            sockets.get(index).close();
            dins.get(index).close();
            douts.get(index).close();
            
            // Remove the socket and streams from the lists
            sockets.remove(index);
            dins.remove(index);
            douts.remove(index);
            ips.remove(index);
            
        } catch (IOException ex) {
            
            System.out.println("");
        }


    }
}
}

