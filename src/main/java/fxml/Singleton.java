/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class Singleton {
       
    String url="jdbc:mysql://localhost:3306/pep";
    String login="root";
    String password="";
    Connection c;
    static Singleton instanceBD;
    
    private Singleton (){
        
     try {
          c = DriverManager.getConnection(url, login,
                      password);
              System.out.println("Connexion etablie");
     
     }   catch (SQLException ex) {
             Logger.getLogger(Singleton.class.getName()).log(Level.SEVERE, null, ex);
         }
    
    }
    
      public static Singleton getInstancesingleton() {
        if (instanceBD == null)
            instanceBD = new Singleton();
            return instanceBD;
    }
    public  Connection getConnection() {
            return  c;
    }
    
}
