/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Iservice.IOffreService;
import entits.OffrePromotion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import fxml.Singleton;

/**
 *
 * @author DELL
 */
public class OffreService implements IOffreService {
      Connection cnx;
      Statement ste;
    public OffreService() {
        cnx = Singleton.getInstancesingleton().getConnection();
       try {
           ste=cnx.createStatement();
       } catch (SQLException ex) {
            System.out.println(ex);
       }
      
        
    
    }

    @Override
    public void creerOffre(OffrePromotion o) {
         try {
           String req1="INSERT INTO `Offre__Promotion`(`pourcentage`,`date_debut`,`date_fin`,`type`,`titre`) VALUES (?,?,?,?,?)" ;
           PreparedStatement pstm = cnx.prepareStatement(req1);
          
        
           pstm.setDouble(1, o.getPourcentage());
           pstm.setString(2, o.getDateDebut());
            pstm.setString(3, o.getDateFin());
             pstm.setString(4, o.getType());
               pstm.setString(5, o.getTitre());
          
           pstm.executeUpdate();
       } catch (SQLException ex) {
           Logger.getLogger(OffreService.class.getName()).log(Level.SEVERE, null, ex);
       }
         
        
        
        
    }

    @Override
    public void modifierOffre(OffrePromotion a) {
        String req="UPDATE offre__promotion SET `pourcentage`=?,`date_debut`=?,`date_fin`=?,`type`=? WHERE id="+a.getId() ; 
        try { 
            PreparedStatement ste = cnx.prepareStatement(req) ;
             
            ste.setDouble(1,a.getPourcentage()) ; 
            ste.setString(2, a.getDateDebut());
            ste.setString(3,a.getDateFin());
            ste.setString(4,a.getType());
            
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            Logger.getLogger(OffreService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @Override
    public void supprimerOffre(OffrePromotion o) {
        try {
             String req1="delete from offre__promotion where"
                    + " id=?";
       
             PreparedStatement pstm = cnx.prepareStatement(req1);
             pstm.setInt(1, o.getId());
             pstm.executeUpdate();
       } catch (SQLException ex) {
           Logger.getLogger(OffreService.class.getName()).log(Level.SEVERE, null, ex);
       }
       
    }

    @Override
    public List<OffrePromotion> afficherOffre(String type) {
        List<OffrePromotion> list = new ArrayList<>();
        OffrePromotion offrePromotion ;
          try {
              Statement s = cnx.createStatement(); 
              ResultSet resultSet =s.executeQuery("select * from offre__promotion where type = '"+type+"'");
              while(resultSet.next()) {
                  offrePromotion = new OffrePromotion(resultSet.getInt("id"));
                  offrePromotion.setPourcentage(resultSet.getDouble("pourcentage"));
                  offrePromotion.setDateDebut(resultSet.getString("date_debut"));
                  offrePromotion.setDateFin(resultSet.getString("date_fin"));
                  offrePromotion.setTitre(resultSet.getString("titre"));
                  list.add(offrePromotion);
              }
            } catch (SQLException ex) {
              Logger.getLogger(OffreService.class.getName()).log(Level.SEVERE, null, ex);
          }
        return list;
    }
    @Override
    public List<OffrePromotion> afficheroffre() {
        List<OffrePromotion> list = new ArrayList<>();
        OffrePromotion offrePromotion ;
          try {
              Statement s = cnx.createStatement(); 
              ResultSet resultSet =s.executeQuery("select * from offre__promotion");
              while(resultSet.next()) {
                  offrePromotion = new OffrePromotion(resultSet.getInt("id"));
                  offrePromotion.setPourcentage(resultSet.getDouble("pourcentage"));
                  offrePromotion.setDateDebut(resultSet.getString("date_debut"));
                  offrePromotion.setDateFin(resultSet.getString("date_fin"));
                  offrePromotion.setType(resultSet.getString("type"));
                  offrePromotion.setTitre(resultSet.getString("titre"));
                  list.add(offrePromotion);
              }
            } catch (SQLException ex) {
              Logger.getLogger(OffreService.class.getName()).log(Level.SEVERE, null, ex);
          }
        return list;
    }
}
