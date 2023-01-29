package com.mycompany.chatappproject;
import java.io.IOException;

public class ChatAppProject {


    public static void main(String[] args) throws IOException {
      
        new DatabaseConnection();
        new SignIn();
        
         System.out.println("main");
    }         
    
}
