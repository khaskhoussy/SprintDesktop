/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Iservice.ICategorieService;
import entits.Categorie;
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
public class CategorieService implements ICategorieService{
      Connection cnx;
    public CategorieService() {
        cnx = Singleton.getInstancesingleton().getConnection();
       try {
           ste=cnx.createStatement();
       } catch (SQLException ex) {
            System.out.println(ex);
       }
      
        
    
    }Statement ste;

    @Override
    public void creerCategorie(Categorie c) {
        try {
           String req1="INSERT INTO `categorie`(`nom_cat`,`description_cat`) VALUES (?,?)" ;
           
           PreparedStatement pstm = cnx.prepareStatement(req1);
          
           
           pstm.setString(1, c.getNomCat());
           pstm.setString(2, c.getDescriptionCat());
          
           pstm.executeUpdate();
       } catch (SQLException ex) {
           Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
       }
         
    }

    @Override
    public void modifierCategorie(Categorie c) {
       String req="UPDATE Categorie SET nom_cat=?,description_cat=? where id = ?" ; 
        try { 
            PreparedStatement ste = cnx.prepareStatement(req) ;
             
            ste.setString(1,c.getNomCat()) ; 
            ste.setString(2, c.getDescriptionCat());
            ste.setInt(3, c.getId());
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
        }
    }

    @Override
    public void supprimerCategorie(Categorie c) {
        try { 
            
             String req1="delete from Categorie where"
                    + " id=?";
       
             PreparedStatement pstm = cnx.prepareStatement(req1);
             pstm.setInt(1, c.getId());
             pstm.executeUpdate();
       } catch (SQLException ex) {
           Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    @Override
    public List<Categorie> affichercategories() {
            List<Categorie> categories = new ArrayList<>();
      Categorie c = null ;
      String req2="select * from Categorie";
      try {
         
         
          ResultSet res=  ste.executeQuery(req2);
          while (res.next()) { 
              c = new Categorie();
                      c.setId( res.getInt("id"));
                      c.setNomCat(res.getString("nom_cat"));
                       c.setDescriptionCat(res.getString("description_cat"));
              categories.add(c);
          }
          
      } catch (SQLException ex) {
          System.out.println(ex.getMessage());
      } 
        
     return categories;
    }

    @Override
    public boolean rechercherparNom(String nom_cat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int checkCategorie(Categorie c) {
        ResultSet rs = null;
          try {
              String req="SELECT COUNT(*) FROM produit WHERE idCategorie=?";
              PreparedStatement pstm = cnx.prepareStatement(req);
              pstm.setInt(1, c.getId());
              rs = pstm.executeQuery();
              rs.next();
             return rs.getInt("COUNT(*)");
          } catch (SQLException ex) {
              Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
          }

       return 0 ;
    }
    }

   
    

