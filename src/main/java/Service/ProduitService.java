/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Iservice.IProduitService;
import entits.OffrePromotion;
import entits.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import fxml.Singleton;

/**
 *
 * @author DELL
 */
public class ProduitService implements IProduitService{
      Connection c =Singleton.getInstancesingleton().getConnection();
    Statement ste,ste1;
    PreparedStatement pste,pste1;
    public ProduitService(){
         try{
        ste = c.createStatement();
    }   catch (SQLException ex) {
             System.out.println(ex);

        }
    }
      
        
    
    

    @Override
    public void creerProduit(Produit p) {
         try {
           String req1="INSERT INTO `produit`(`nom_prod`,`prix_prod`,`prod_description`,`quantite`,`image`,`idCategorie`) VALUES (?,?,?,?,?,?)" ;
           PreparedStatement pstm = c.prepareStatement(req1);
          
        
           pstm.setString(1, p.getNomProd());
           pstm.setDouble(2, p.getPrixProd());
           pstm.setString(3, p.getProdDescription());
           pstm.setInt(4, p.getQuantite());
           pstm.setString(5,p.getImage());
          
           pstm.setInt(6, p.getIdCategorie());
           pstm.executeUpdate();
           
       } catch (SQLException ex) {
           Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
       }
        
         
    }

    @Override
    public void modifierProduit(Produit p) {
       String sql="UPDATE produit SET `nom_prod`=?,`prix_prod`=?,`prod_Description`=?,`quantite`=? WHERE id="+p.getId();
   PreparedStatement ps;
        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, p.getNomProd());
            ps.setDouble(2, p.getPrixProd());
            
            ps.setString(3, p.getProdDescription());
            ps.setInt(4, p.getQuantite());
                
            ps.executeUpdate();
            int rowsUpdated=ps.executeUpdate();
            if (rowsUpdated>0){
            System.out.println("La modification de produit"+p.getId()+" a été éffectué avec succée ");
            }}
        catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimerProduit(Produit p) {
         try {
             String req1="delete from produit where"
                    + " id=?";
       
             PreparedStatement pstm = c.prepareStatement(req1);
             pstm.setInt(1, p.getId());
             pstm.executeUpdate();
       } catch (SQLException ex) {
           Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
       }    }

    @Override
    public List<Produit> afficherproduits() {
        List<Produit> produits = new ArrayList<>();
      Produit p = null ;
      OffrePromotion offre = null;
      String req2="select * from produit";
      try {
         
         
          ResultSet res=  ste.executeQuery("SELECT * FROM produit p LEFT JOIN offre__promotion o on p.IdOffre = o.id");
          while (res.next()) { 
             
              p = new Produit();
                      p.setId( res.getInt("p.id"));
                      p.setNomProd( res.getString("nom_prod") );
                      p.setPrixProd(res.getDouble("prix_prod"));
                     
                      p.setProdDescription(res.getString("prod_description"));
                      p.setQuantite(res.getInt("quantite"));
                      p.setImage(res.getString("image"));
                      p.setIdCategorie(res.getInt("idCategorie"));
                      p.setPrixOffre(res.getDouble("prix_offre"));
                      
                      if(res.getInt("idOffre") != 0) {
                        offre = new OffrePromotion();
                        offre.setId(res.getInt("idOffre"));
                        offre.setTitre(res.getString("titre"));
                        offre.setPourcentage(res.getDouble("pourcentage"));
                 
                        p.setOffre(offre);
                        p.setIdOffre(offre.getId());
                      }
                    produits.add(p);
                      
          }
          
      } catch (SQLException ex) {
          System.out.println(ex.getMessage());
      } 
        
     return produits;
    }

    @Override
    public void affecterPromotion(Produit p,OffrePromotion o) {
            
          p.setPrixOffre(p.getPrixProd() - (p.getPrixProd()*o.getPourcentage()));
          String req2="UPDATE produit SET prix_offre=? , idOffre=? WHERE id=?";
          
           PreparedStatement ste;
             
             try{
            ste = c.prepareStatement(req2);
            ste.setDouble(1, p.getPrixOffre());
            ste.setInt(2, o.getId());
            ste.setInt(3, p.getId());
            ste.executeUpdate();
    }catch (SQLException ex) {
             Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
         }
       
    }

    @Override
    public List<Produit> filtrer(String catnom) {
     List<Produit> produits = new ArrayList<>();
      Produit p = null ;
      OffrePromotion offre = null;
      String req="SELECT * FROM produit p LEFT JOIN offre__promotion o on p.IdOffre = o.id where p.IdCategorie = (SELECT id as IdCategorie from categorie where nom_cat=?)";
       try {
         
         
           
            PreparedStatement pstm = c.prepareStatement(req);
            pstm.setString(1, catnom);
            ResultSet res = pstm.executeQuery();
            while (res.next()) { 

                p = new Produit();
                p.setId( res.getInt("p.id"));
                p.setNomProd( res.getString("nom_prod") );
                p.setPrixProd(res.getDouble("prix_prod"));
                p.setPrixOffre(res.getDouble("prix_offre"));
                p.setProdDescription(res.getString("prod_description"));
                p.setQuantite(res.getInt("quantite"));
                p.setImage(res.getString("image"));
               // p.setIdCategorie(res.getInt("idCategorie"));
                p.setIdOffre(res.getInt("idOffre"));

                if(res.getInt("idOffre") != 0) {
                  offre = new OffrePromotion();
                  offre.setId(res.getInt("idOffre"));
                  offre.setTitre(res.getString("titre"));
                  offre.setPourcentage(res.getInt("pourcentage"));
                  p.setOffre(offre);
                  p.setIdOffre(offre.getId());
                }
              produits.add(p);

}
                      
          
          
      } catch (SQLException ex) {
          System.out.println(ex.getMessage());
      } 
        
     return produits;
    }
    @Override
    public void updateQuantite(Produit p, int quantite) {
         try {
           String req8="UPDATE produit SET `quantite`=quantite - ? WHERE id="+p.getId();
           PreparedStatement pstm = c.prepareStatement(req8);
          
        
           pstm.setInt(1, quantite);
           pstm.executeUpdate();
           
       } catch (SQLException ex) {
           Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
       }
         }

         @Override
        public void updateQuantitePlus(Produit p, int quantite) {
         try {
           String req8="UPDATE produit SET `quantite`=quantite + ? WHERE id="+p.getId();
           PreparedStatement pstm = c.prepareStatement(req8);
          
        
           pstm.setInt(1, quantite);
           pstm.executeUpdate();
           
       } catch (SQLException ex) {
           Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
       }
        
         
    }
          @Override
    public List<Produit> afficherproduitss() {
        List<Produit> produits = new ArrayList<>();
      Produit p = null ;
      OffrePromotion offre = null;
      String req2="select * from produit";
      try {
         
         
          ResultSet res=  ste.executeQuery("SELECT * FROM produit");
          while (res.next()) { 
             
              p = new Produit();
                      p.setId( res.getInt("id"));
                      p.setNomProd( res.getString("nom_prod") );
                      p.setPrixProd(res.getDouble("prix_prod"));
                     
                      p.setProdDescription(res.getString("prod_description"));
                      p.setQuantite(res.getInt("quantite"));
                      p.setImage(res.getString("image"));
                      p.setPrixOffre(res.getDouble("prix_offre"));
                   
                    produits.add(p);
                      
          }
          
      } catch (SQLException ex) {
          System.out.println(ex.getMessage());
      } 
        
     return produits;
    }

}