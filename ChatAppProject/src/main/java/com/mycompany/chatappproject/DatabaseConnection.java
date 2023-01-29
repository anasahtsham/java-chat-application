package com.mycompany.chatappproject;

import java.sql.*;

public class DatabaseConnection {
    static Connection con = null;
    
    DatabaseConnection()
    {
       try{
           Class.forName("com.mysql.cj.jdbc.Driver");
           con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat_app", "root","");
           
           System.out.println("Success");
       }
       
       catch(Exception e)
       {
            System.out.println("Exception: " + e.getMessage());
       }
    }
    
}
