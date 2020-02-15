/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

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
import Iservice.ICommandeService;

import entits.LigneCommande;
import entits.Produit;

/**
 *
 * @author abdelli
 */
public class CommandeService implements ICommandeService {
   Connection c = Singleton
           .getInstancesingleton()
           .getConnection();
    Statement ste;
    //PreparedStatement pstm;
public CommandeService() {
               try {
            ste = c.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
}

    @Override
    public int creerCommande(Commande p) {
        try {
            String req1="INSERT INTO `commande`(`date_validation`, `prix_total`,`IdUser`) VALUES (?,?,?)" ;
            PreparedStatement pstm = c.prepareStatement(req1,Statement.RETURN_GENERATED_KEYS);
                    pstm.setString(1,p.getDateValidation());
                    pstm.setInt(2,p.getPrixTotal());
                    pstm.setInt(3, p.getIdUser().getId());
                    pstm.executeUpdate();                     
                    ResultSet generatedKeys = pstm.getGeneratedKeys();
                    generatedKeys.next();
                    return (int) generatedKeys.getLong(1);
        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0 ;
}

    @Override
    public void modifierCommande(Commande p) {
        String req2="UPDATE commande SET date_validation=?,prix_total=? WHERE id =?" ; 
        try {
            PreparedStatement pstm = c.prepareStatement(req2);
                    pstm.setString(1,p.getDateValidation());
                    pstm.setInt(2,p.getPrixTotal());
                    pstm.setInt(3,p.getId());
            pstm.executeUpdate();            
            
        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimerCommande(Commande p) {
              try {
            String req3="delete from commande where"
                    + " id=?";
       
      PreparedStatement ps = c.prepareStatement(req3);
            ps.setInt(1, p.getId());
            //ps.setPanid(1, p.getPanid());
            ps.executeUpdate();
         
           
        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List<Commande> afficherCommande() {
          
      List<Commande> commandes = new ArrayList<>();
      Commande c = null ;
      //LigneCommande ls = null ;
      //Produit p = null ;
      String req4="select * from commande";
      //String req4 = "select commande.id commande.date_validation commande.prix_totale ligne_commande.qte produit.prix_prod  from commande,ligne_commande,produit  ";
      try {
         
         
          ResultSet res=  ste.executeQuery(req4);
          while (res.next()) { 
              c = new Commande();
              //ls = new LigneCommande();
              //p = new Produit() ;
              
              
                      c.setId( res.getInt("id"));
                      c.setDateValidation( res.getString("date_validation") );
                      c.setPrixTotal(res.getInt("prix_total"));
                      //ls.setQte(res.getInt("Qte"));
                      //p.setPrixProd(res.getInt("prix_prod"));
              commandes.add(c);
          }
          
      } catch (SQLException ex) {
          System.out.println(ex.getMessage());
      } 
        
     return commandes;  
    }
    @Override
    public List<LigneCommande> getLigneCommande(Commande commande) {
               List<LigneCommande> getLigneCommande = new ArrayList<>();
      LigneCommande lc = null ;
      String req4="select * from ligne_commande l join commande c on l.IdCommande = c.id join produit p on l.IdProduit = p.id where c.id=?";
      //String req4 = "select  from  "
      try {
          PreparedStatement ste = c.prepareStatement(req4);
          ste.setInt(1, commande.getId());
          ResultSet res=  ste.executeQuery();
          
          while (res.next()) { 
              lc = new LigneCommande();
              Commande cs = new Commande();
              cs.setId(res.getInt("c.id"));
              cs.setPrixTotal(res.getInt("c.prix_total"));
              Produit produit = new Produit();
              
                      lc.setId( res.getInt("id"));
                      lc.setQte(res.getInt("Qte"));
                      cs.setDateValidation(res.getString("c.date_validation") );                    
                      produit.setId(res.getInt("p.id"));
                      produit.setQuantite(res.getInt("p.quantite"));
                      produit.setPrixProd(res.getDouble("p.prix_prod"));
                      produit.setNomProd(res.getString("p.nom_prod"));
                      produit.setPrixOffre(res.getDouble("p.prix_offre"));
                      produit.setProdDescription(res.getString("p.prod_description"));
                      lc.setIdProduit(produit);
                      lc.setIdCommande(cs);
              getLigneCommande.add(lc);
          }
          
      } catch (SQLException ex) {
          System.out.println(ex.getMessage());
      } 
        
     return getLigneCommande;
    }

}
