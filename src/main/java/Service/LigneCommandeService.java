/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;


import entits.LigneCommande;
import Iservice.ILigneCommande;
import entits.Commande;
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
 * @author abdelli
 */
public class LigneCommandeService implements ILigneCommande {
   Connection c = Singleton
           .getInstancesingleton()
           .getConnection();
    Statement ste;    


public LigneCommandeService() {
               try {
            ste = c.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
}

    @Override
    public void creeLigneCommande(LigneCommande lc) {
            try {
            String req1="INSERT INTO `ligne_commande`(`qte`,`IdCommande`,`IdProduit`) VALUES (?,?,?)" ;
            PreparedStatement pstm = c.prepareStatement(req1);
                    pstm.setInt(1,lc.getQte());
                    pstm.setInt(2,lc.getIdCommande().getId());
                    pstm.setInt(3,lc.getIdProduit().getId());
                    pstm.executeUpdate();                     
         
        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }

    @Override
    public void modifierLigneCommande(LigneCommande lc) {
               String req2="UPDATE ligne_commande SET id=?, qte=? WHERE id =?" ; 
        try {
            PreparedStatement pstm = c.prepareStatement(req2);
                    pstm.setInt(1,lc.getId());
                    pstm.setInt(2,lc.getQte());
                    pstm.setInt(3,lc.getId());
            pstm.executeUpdate();            
            
        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimerLigneCommande(LigneCommande lc) {
                  try {
            String req3="delete from ligne_commande where"
                    + " id=?";
       
      PreparedStatement ps = c.prepareStatement(req3);
            ps.setInt(1, lc.getId());
            //ps.setPanid(1, p.getPanid());
            ps.executeUpdate();
         
           
        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }

    @Override
    public List<LigneCommande> afficherLigneCommande() {
          List<LigneCommande> ligne_commandes = new ArrayList<>();
      LigneCommande lc = null ;
      String req4="select * from ligne_commande";
      try {
         
         
          ResultSet res=  ste.executeQuery(req4);
          while (res.next()) { 
              lc = new LigneCommande();
                      lc.setId( res.getInt("id"));
                      lc.setQte(res.getInt("qte"));                     
              ligne_commandes.add(lc);
          }
          
      } catch (SQLException ex) {
          System.out.println(ex.getMessage());
      } 
        
     return ligne_commandes;    
    }
          /*public void valider(LigneCommande lc) throws SQLException {
          String req = "INSERT INTO commande(id,dateValidation,prixTotal) VALUES(?,?,?)";
          //String req0 = "SELECT quantite FROM produit WHERE id=?";
          //String req1 = "UPDATE produit SET quantite=? WHERE id=?";
          
          
          for
          PreparedStatement pste = c.prepareStatement(req);
          pste.setInt(1,lc.getId());
          pste.setString(2,lc.getDateValidation());
          pste.setInt(3,lc.getPrixTotal());
          
          PreparedStatement pste0 = c.prepareStatement(req0);
          pste0.setInt(1, lc.getId());
          ResultSet r0 = pste0.executeQuery();
          r0.next();
          int a = r0.getInt("quantite");
          int b = a+1 ; 
            System.out.println(b); 
          
          PreparedStatement pste1 = c.prepareStatement(req1); 
          pste1.setInt(1, b);
          pste.setString(2,lc.getDateValidation());
          pste.setInt(3,lc.getPrixTotal());
          pste1.executeUpdate();
           }*/
}        
