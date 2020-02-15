/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import entits.Evenement;
import entits.OffrePromotion;
import entits.Participation;
import Iservice.Ieventservice;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import fxml.Singleton;

/**
 *
 * @author FARAH
 */

    
    
public class Eventservice implements Ieventservice {
      Connection c =Singleton.getInstancesingleton().getConnection();
    Statement ste;
    PreparedStatement pste,pste1;
    public Eventservice () {
    try{
        ste = c.createStatement();
    }   catch (SQLException ex) {
             System.out.println(ex);

        }
    
    
  }

    @Override
    public void creerevent(Evenement e) {
       String req = "INSERT INTO `evenement`(`nom`,`datedebut`, `datefin`, `description`, `lieu`, `nbr_participants`, `eveprix`, `image`, `IdOffre`) VALUES (?,?,?,?,?,?,?,?,NULL)";
    
          try {
           PreparedStatement pste = c.prepareStatement(req);
           pste.setString(1,e.getNom());
           pste.setString(2, e.getDatedebut());
           pste.setString(3, e.getDatefin());
           pste.setString(4, e.getDescription());
           pste.setString(5,e.getLieu());
           pste.setInt(6,e.getNbrParticipants());
           pste.setInt(7,e.getEveprix());
           pste.setString(8,e.getImage());
           pste.executeUpdate();
              
              
          } catch (SQLException ex) {
              Logger.getLogger(Eventservice.class.getName()).log(Level.SEVERE, null, ex);
          }
    
    }

    @Override
    public void deleteevent(Evenement e) {
        
         String req = "delete from evenement "
             + "where id= ?";
       try{
        pste = c.prepareStatement(req);
            pste.setInt(1, e.getId());
            pste.executeUpdate();
       }catch (SQLException ex) {
            Logger.getLogger(Eventservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void updateevent(Evenement e) {
        
         if (e!=null)
        {
            
        String req= "UPDATE evenement SET `nom`=?, `datedebut`=?, `datefin`=?, `description`=?, `lieu`=?, `nbr_participants`=?, `eveprix`=?, `image`=? WHERE id="+e.getId();
        try {
         PreparedStatement pste = c.prepareStatement(req);
          pste.setString(1,e.getNom());
           pste.setString(2, e.getDatedebut());
           pste.setString(3, e.getDatefin());
           pste.setString(4, e.getDescription());
           pste.setString(5,e.getLieu());
           pste.setInt(6,e.getNbrParticipants());
           pste.setInt(7,e.getEveprix());
           pste.setString(8,e.getImage());
           pste.executeUpdate();
              
            System.out.println("eyh");
        } catch (SQLException ex) {
            Logger.getLogger(Eventservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }
                System.out.println("lala");
    }

   /* @Override
    public ArrayList<Evenement> afficherevent() {
          String req = "select * from evenement";
        ArrayList<Evenement> event= new ArrayList<>();
        try {
            ResultSet res=  ste.executeQuery(req);
            
           
            while (res.next()) {
   Evenement e = new Evenement (res.getInt("id"),res.getString("datedebut"),res.getString("datefin"),res.getString("description"),res.getString("lieu"),res.getInt("nbr_participants"),res.getInt("eveprix"),res.getString("image"));
      event.add(e);
              
            }   
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return event ;
    

    }*/

    @Override
    public boolean RechercherparLieu(String lieu) {
         boolean test=false;
        String req = "SELECT * from evenement where lieu='"+lieu+"'";
            
     
        try {
           
            ResultSet stp=ste.executeQuery(req);
            stp.last();
            if(stp.getRow()!=0)
            {
                test=true;
                System.out.println(test+"event trouver");
            }
            else
            {
                test=false;
                System.out.println(test+"event non trouver");
            }
        }
            catch (SQLException ex) {
            Logger.getLogger(Eventservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return test;
    }

    @Override
    public void participer(Participation p) throws SQLException {
        String req = "INSERT INTO participation(idEvenement,idUser) VALUES(?,?)";
          String req0 = "SELECT nbr_participants FROM evenement WHERE id=?";
          String req1 = "UPDATE evenement SET nbr_participants=? WHERE id=?";
          
          
          PreparedStatement pste = c.prepareStatement(req);
          pste.setInt(1, p.getIdEvenement());
          pste.setInt(2, p.getIdUser());
          pste.executeUpdate();
          
          PreparedStatement pste0 = c.prepareStatement(req0);
          pste0.setInt(1, p.getIdEvenement());
          ResultSet r0 = pste0.executeQuery();
          r0.next();
          int a = r0.getInt("nbr_participants");
          int b = a+1 ; 
            System.out.println(b); 
          
          PreparedStatement pste1 = c.prepareStatement(req1); 
          pste1.setInt(1, b);
          pste1.setInt(2,p.getIdEvenement());
          pste1.executeUpdate();

    }

    @Override
    public void annulerparticiper(Participation p) throws SQLException {
        
         String req = "INSERT INTO participation(idEvenement,idUser) VALUES(?,?)";
          String req0 = "SELECT nbr_participants FROM evenement WHERE id=?";
          String req1 = "UPDATE evenement SET nbr_participants=? WHERE id=?";
          
          
          
          PreparedStatement pste = c.prepareStatement(req);
          pste.setInt(1, p.getIdEvenement());
          pste.setInt(2, p.getIdUser());
          pste.executeUpdate();
          
          PreparedStatement pste0 = c.prepareStatement(req0);
          pste0.setInt(1, p.getIdEvenement());
          ResultSet r0 = pste0.executeQuery();
          r0.next();
          int a = r0.getInt("nbr_participants");
          int b = a-1 ; 
          
          PreparedStatement pste1 = c.prepareStatement(req1); 
          pste1.setInt(1, b);
          pste1.setInt(2,p.getIdEvenement());
           pste1.executeUpdate();
          
      }

    @Override
    public ArrayList<Evenement> afficherevent() {
          ArrayList<Evenement> events = new ArrayList<>(); 
      Evenement e = null ;
      OffrePromotion offre = null;
      String req2="select * from evenement";
      try {
         
         
          ResultSet res=  ste.executeQuery("SELECT * FROM evenement e LEFT JOIN offre__promotion o on e.IdOffre = o.id");
          while (res.next()) { 
             
              e = new Evenement();
              e.setId(res.getInt("e.id"));
              e.setNom(res.getString("nom"));
              e.setDatedebut(res.getString("datedebut"));
              e.setDatefin(res.getString("datefin"));
              e.setDescription(res.getString("description"));
              e.setLieu(res.getString("lieu"));
              e.setPrix_offre(res.getDouble("prix_offre"));
              e.setNbrParticipants(res.getInt("nbr_participants"));
              e.setEveprix(res.getInt("eveprix"));
              e.setImage(res.getString("image"));
              e.setIdOffre(res.getInt("idOffre"));
              if(res.getInt("idOffre") != 0) {
                        offre = new OffrePromotion();
                        offre.setId(res.getInt("idOffre"));
                        offre.setTitre(res.getString("titre"));
                        offre.setPourcentage(res.getDouble("pourcentage"));
                        e.setOffre(offre);
                        e.setIdOffre(offre.getId());
                      }
                    events.add(e);
                      
          }
          
      } catch (SQLException ex) {
          System.out.println(ex.getMessage());
      } 
        
     return events;
    
             
    }

    @Override
    public void affecterPromotion(Evenement e, OffrePromotion o) {
        double prixOffre = e.getEveprix()- (e.getEveprix()*o.getPourcentage()) ;
          String req2="UPDATE evenement SET prix_offre=? , idOffre=? WHERE id=?";
          
           PreparedStatement ste;
             
             try{
            ste = c.prepareStatement(req2);
            ste.setDouble(1, prixOffre);
            ste.setInt(2, o.getId());
            ste.setInt(3, e.getId());
            ste.executeUpdate();
    }catch (SQLException ex) {
             Logger.getLogger(Eventservice.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    
      
    

}
    

    

    
