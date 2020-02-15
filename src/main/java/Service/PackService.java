/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Iservice.IPackService;
import entits.LignePack;
import entits.PackDecoration;
import entits.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import fxml.Singleton;

/**
 *
 * @author abdelli
 */
public class PackService implements IPackService{

    Connection c = Singleton
           .getInstancesingleton()
           .getConnection();
    
    @Override
    public List<LignePack> getLignePackByPack(PackDecoration pack) {
        List<LignePack> getLignePackByPack = new ArrayList<>();
      LignePack lp = null ;
      String req4="select * from `ligne_pack` l join `produit` p on l.IdProduit = p.id where l.IdPack = ? ";
      //String req4 = "select  from  "
      try {
          PreparedStatement ste = c.prepareStatement(req4);
          ste.setInt(1, pack.getId());
          ResultSet res=  ste.executeQuery();
          
          while (res.next()) { 
              lp = new LignePack();
                      lp.setId( res.getInt("id"));
                      lp.setQuantite( res.getInt("quantite") );
                      lp.setDescription(res.getString("description"));
                      Produit produit = new Produit();
                      produit.setId(res.getInt("p.id"));
                      produit.setNomProd(res.getString("p.prod_description"));
                      produit.setPrixProd(res.getDouble("prix_prod"));
                      produit.setPrixOffre(res.getDouble("prix_offre"));
                      produit.setNomProd(res.getString("nom_prod"));
                      lp.setIdProduit(produit);
                      lp.setIdPack(new PackDecoration(res.getInt("IdPack")));
              getLignePackByPack.add(lp);
          }
          
      } catch (SQLException ex) {
          System.out.println(ex.getMessage());
      } 
        
     return getLignePackByPack;
    }
          
  

    }
    

