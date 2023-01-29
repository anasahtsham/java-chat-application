*************************************************************
***************  Java Based Chat Application  ***************
*************************************************************

------------------------ Description ------------------------
 
 This is a Java program that runs on multiple computers and  
 allows them to connect to each other via Java sockets and  
 send and receive messages. The sentiment of the message is 
 assigned to the received messages. One computer acts as the
 server and the rest act as the clients.                     
_____________________________________________________________

----------------------- Pre-requisites ----------------------

 > Three computers or laptops (minimum)
 ->One to run the server, and other two to run the Chatapp
 > A single private network connection, wifi or ethernet
 > Any Java compiler (recommeded: Netbeans)
 > XAMPP Server (for the MySQL Database)
 > Java Driver for MySQL
_____________________________________________________________

------------------------ Directions -------------------------

 > Import java project folder into the java compiler
 > Add the .jar file for MySQL driver in project Libraries
 > Run XAMPP server
 > Open Admin for MySQL module
 > Create a new database & name it "chat_app"
 > Import the "chat_app.sql" file into the database
 > First run the "server.java" file on computer 1. Wait for 
   all the of the maven dependancies to install
 > Once the server GUI shows up, it will take about a minute
   or so to initiate the stanford NLP pipeline, responsible
   for sentiment analysis
 > Run the project on computer 2 and 3
 > Now you are all set, message away!
_____________________________________________________________

-------------------- Java Components Used -------------------

 > Java Sockets
 > Java Threads
 > Java Standford NLP
 > Java GUI
 > Java JDBC
_____________________________________________________________

----------------------- Libraries Used ----------------------

 > java.net
 > java.io
 > edu.stanford.nlp
 > java.awt
 > java.swing
 > java.sql
 > java.io
_____________________________________________________________
 
--------------------- Code Contributions --------------------
 > Muhammad Ahmad @https://github.com/MA23664
 -> ChatAppProject.java
 -> DatabaseConnection.java
 -> HomePage.java
 Code for the GUI of the ChatApp, the database connection,
 sockets code, and the threading

 > Anas Ahtsham @https://github.com/anasahtsham
 -> Server.java
 Code for the sentiment analysis and README documentation

 > Abdullah Shahid @https://github.com/abdi677
 -> SignIn.java
 -> Signup.java
 Code for the sign up and sign in pages
=============================================================