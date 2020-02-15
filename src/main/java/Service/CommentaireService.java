/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Iservice.ICommentaireService;
import entits.Commentaire;
import entits.Sujet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import fxml.Singleton;

/**
 *
 * @author hp
 */
public class CommentaireService  implements ICommentaireService {
    Connection c =Singleton.getInstancesingleton().getConnection();
    Statement ste;
    PreparedStatement pste,pste1;
     public CommentaireService() {
    try{
        ste = c.createStatement();
    }   catch (SQLException ex) {
             System.out.println(ex);
   }
     }

    @Override
    public void ajouterCommentaire(Commentaire c) {
try {
            
             String req ="INSERT INTO Commentaire (description_commentaire,IdClient,DatePub,IdSujet)"+ "VALUES" +"('"+c.getDescriptionCommentaire()+"','"+c.getIdClient()+"','"+c.getDatePub()+"','"+c.getIdSujet()+"')";  
             

         ste.executeUpdate(req);
        
        } catch (SQLException ex) {
            Logger.getLogger(SujetService.class.getName()).log(Level.SEVERE, null, ex);
        }   

    }
    

    @Override
    public void ViderTableCommentaire(Commentaire s){
        try {
    String req ="DELETE commentaire FROM commentaire inner join sujet on commentaire.IdSujet=sujet.id WHERE commentaire.IdSujet=?";
         PreparedStatement udt = c.prepareStatement(req);
            udt.setInt(1, s.getId());

            udt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }  
    
    }
    @Override
    public void supprimerCommentaire(Commentaire l) {
        try {
            
             String req ="DELETE FROM Commentaire where IdClient IN (Select * FROM (Select commentaire.IdClient from commentaire inner join User WHERE commentaire.id=? && Commentaire.IdClient=User.id)tblTmp)";  
            
            PreparedStatement dlt = c.prepareStatement(req);
            dlt.setInt(1, l.getId());


            dlt.executeUpdate();
         
         dlt.executeUpdate(req);
        
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }

    /**
     *
     * @return
     */
    @Override
    public ArrayList<Commentaire> afficherCommentaire(int IdSujet) {

    ArrayList<Commentaire> Commentaire = new ArrayList<>();
    Commentaire c = null ;
    Sujet s = null;
    String req1="select * from commentaire inner join sujet where IdSujet='"+IdSujet+"'&& commentaire.IdSujet=sujet.id";

      try {
         ResultSet res1=  ste.executeQuery(req1);

          while (res1.next()) { 
          
              c = new Commentaire();
              s=new Sujet();
              c.setDescriptionCommentaire(res1.getString("description_commentaire"));
              c.setDatePub(res1.getString("commentaire.DatePub"));
              c.setDatePublication(res1.getString("sujet.DatePub"));
              

               Commentaire.add(c);
          }
           

      } catch (SQLException ex) {
          System.out.println(ex.getMessage());
      } 
        
     return Commentaire;

    }

}
