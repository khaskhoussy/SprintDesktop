/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import fxml.Singleton;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import Iservice.IserviceTravaille;
import entits.ListeTravail;
import entits.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aisce
 */
public class ServiceTreavaille implements IserviceTravaille{
    
     Connection c =Singleton.getInstancesingleton().getConnection();
    Statement ste;
    public ServiceTreavaille() {
        try {
            ste = c.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    @Override
     public int ajoutTravaille(ListeTravail a) {
   int r=0;
        try {
           String req1="INSERT INTO `liste_travail`(`description_travail`,`date_travail`,`IdUser`,`image`) VALUES (?,?,?,?)" ;
           PreparedStatement pstm = c.prepareStatement(req1);
           pstm.setString(1,a.getDescriptionTravail());
           pstm.setString(2,a.getDateTravail());
          pstm.setInt(3,a.getIdUser().getId());
           pstm.setString(4,a.getImage());
          
          r= pstm.executeUpdate();
           
       } catch (SQLException ex) {
           Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, ex);
       }
        return r;
    }
      @Override
     public String afficherTravaille(int use) {
        String req=("SELECT * FROM `liste_travail` WHERE IdUser='"+use+"'" );
      //  String req=("SELECT * FROM user WHERE username='ammar'");
         ListeTravail  p = new ListeTravail();
         String txt="";
          try {
                ResultSet res=ste.executeQuery(req);   
                 
                     while (res.next()) { 
              p = new ListeTravail();
  
              
              
              
              p.setDateTravail(res.getString("date_travail"));
                      p.setDescriptionTravail(res.getString("description_travail") );
                     txt+=("date : "+p.getDateTravail()+"\nDescription : "+p.getDescriptionTravail()+"\n");
                     

              
          }

                 
        }
        catch (SQLException ex) {
            Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
        }
          
        return txt;
        
    }
     @Override
      public List<ListeTravail> afficherTravail(int idu) {
      
      List<ListeTravail> lt = new ArrayList<>();
      ListeTravail p = null ;
      String x;
      x="Jardinier";
      String req2=("SELECT * FROM liste_travail WHERE IdUser='"+idu+"'");
      try {
         
         
          ResultSet res=  ste.executeQuery(req2);
          while (res.next()) { 
              p = new ListeTravail();
                      p.setId( res.getInt("id"));
                      p.setDescriptionTravail(res.getString("description_travail"));
                      p.setDateTravail(res.getString("date_travail"));
                      p.setImage(res.getString("image"));
              lt.add(p);
          }
          
      } catch (SQLException ex) {
          System.out.println(ex.getMessage());
      } 
        
     return lt;
    }
     
    
}
