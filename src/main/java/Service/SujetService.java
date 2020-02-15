/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Iservice.ISujetService;
//import com.jfoenix.controls.JFXDatePicker;
import entits.Commentaire;
import entits.Sujet;
import entits.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import fxml.Singleton;


/**
 *
 * @author hp
 */
public class SujetService implements ISujetService{
 Connection c =Singleton.getInstancesingleton().getConnection();
    Statement ste;
    public SujetService() {
        try {
            ste = c.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public SujetService(String Recherche) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public void ajouterSujet(Sujet s) {
    try {
         /*User user = new User();
            user.setId(4);
            s.setIdUser(user.getId());*/
        String req = 
                "INSERT INTO sujet"
                + "(Nom_sujet, IdUser, Categorie, DatePub, description)"
                +"VALUES ('"+s.getNomsujet()+"','"+s.getIdUser()+"','"+s.getCategorie()+"','"+s.getDatepub()+"','"+s.getDescription()+"')";
        
           
        ste.executeUpdate(req);
        
        
        } catch (SQLException ex) {
             
            Logger.getLogger(SujetService.class.getName()).log(Level.SEVERE, null, ex);
        }    }

    @Override
    public void modifierSujet(Sujet s) {
try {
    String req ="UPDATE `sujet` SET `id`=?,`Nom_sujet`=? WHERE id=?";
         PreparedStatement udt = c.prepareStatement(req);
            udt.setInt(1, s.getId());
            udt.setString(2, s.getNomsujet());
            
            udt.setInt(3, s.getIdUser());

            udt.executeUpdate();
                
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }  
}
    
    @Override
    public void supprimerSujet(Sujet s) {
        try {
    String req ="DELETE commentaire FROM sujet inner join commentaire on commentaire.IdSujet=sujet.id WHERE sujet.id=?";
         PreparedStatement udt = c.prepareStatement(req);
            udt.setInt(1, s.getId());

            udt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    
    public int getSujetIdByName(String NomSujet){
        int myId = 0;
        try {
    String req ="Select id FROM `Sujet` WHERE `Nom_Sujet`='"+NomSujet+"' ";          
         ResultSet res = ste.executeQuery(req);
          while (res.next()) { 
          
              Sujet s = new Sujet();
              String temp = res.getString("id");
              myId = Integer.valueOf(temp);

          }
         //myId = Integer.valueOf(res.toString());
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
        return myId;
    }
    @Override
    public ArrayList<Sujet> afficherSujet() {
      ArrayList<Sujet> Sujet = new ArrayList<>();
      Sujet s = null ;
      String req2="select * from Sujet ";

      
      try {
         
         
          ResultSet res=  ste.executeQuery(req2);
          while (res.next()) { 
          
              s = new Sujet();
              s.setNomsujet(res.getString("Nom_sujet"));
              s.setCategorie(res.getString("categorie"));
              s.setDescription(res.getString("description"));
              s.setDatepub(res.getString("DatePub"));
               Sujet.add(s);
          }
          
      } catch (SQLException ex) {
          System.out.println(ex.getMessage());
      } 
        
     return Sujet;
    }

    @Override
    public List<Sujet> afficherSujetPlante() {
ArrayList<Sujet> Sujet = new ArrayList<>();
      Sujet s = null ;
      String req2="select * from Sujet inner join User where sujet.IdUser=user.id && categorie='Plante' ";

      
      try {
         
         
          ResultSet res=  ste.executeQuery(req2);
          while (res.next()) { 
          
              s = new Sujet();
              s.setNomsujet(res.getString("Nom_sujet"));

               Sujet.add(s);
          }
          
      } catch (SQLException ex) {
          System.out.println(ex.getMessage());
      } 
        
     return Sujet;    }

    @Override
    public List<Sujet> afficherSujetAccessoire() {
ArrayList<Sujet> Sujet = new ArrayList<>();
      Sujet s = null ;
      String req2="select * from Sujet inner join User where sujet.IdUser=user.id && categorie='Accessoire' ";

      
      try {
         
         
          ResultSet res=  ste.executeQuery(req2);
          while (res.next()) { 
          
              s = new Sujet();
              s.setNomsujet(res.getString("Nom_sujet"));

               Sujet.add(s);
          }
          
      } catch (SQLException ex) {
          System.out.println(ex.getMessage());
      } 
        
     return Sujet;    }

    @Override
    public List<Sujet> afficherSujetDivers() {
ArrayList<Sujet> Sujet = new ArrayList<>();
      Sujet s = null ;
      String req2="select * from Sujet inner join User where sujet.IdUser=user.id && categorie='Divers' ";

      
      try {
         
         
          ResultSet res=  ste.executeQuery(req2);
          while (res.next()) { 
          
              s = new Sujet();
              s.setNomsujet(res.getString("Nom_sujet"));

               Sujet.add(s);
          }
          
      } catch (SQLException ex) {
          System.out.println(ex.getMessage());
      } 
        
     return Sujet;    }

    @Override
    public List<Sujet> chercherSujet(String NomSujet) {
        ArrayList<Sujet> Sujet = new ArrayList<>();
      Sujet s = null ;
      String req2="select * from Sujet WHERE Nom_sujet ='"+NomSujet+"'";

      
      try {
         
         
          ResultSet res=  ste.executeQuery(req2);
          while (res.next()) { 
          
              s = new Sujet();
              s.setNomsujet(res.getString("Nom_sujet"));

               Sujet.add(s);
          }
          
      } catch (SQLException ex) {
          System.out.println(ex.getMessage());
      } 
        
     return Sujet;    
    }
    
}
