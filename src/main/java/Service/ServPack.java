/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import entits.LignePack;
import entits.PackDecoration;
import Iservice.InterfacePack;
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
 * @author Dreamer
 */
public class ServPack implements InterfacePack {
     Connection c = Singleton
           .getInstancesingleton()
           .getConnection();
    Statement ste;
    
    public ServPack() 
 {
        try {
            ste = c.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public int ajouterPack(PackDecoration s) {
         try {
             PreparedStatement preparedStatement = 
                     c.prepareStatement("INSERT INTO `pack_decoration`(`prixP`, `nom_pack`, `description_pack`, `typeP`, `image_pack`) VALUES (?,?,?,?,?)"
                             ,PreparedStatement.RETURN_GENERATED_KEYS);
             preparedStatement.setDouble(1, s.getPrixP());
             preparedStatement.setString(2, s.getNom());
             preparedStatement.setString(3, s.getDescriptionPack());
             preparedStatement.setString(4, s.getType());
             preparedStatement.setString(5, s.getImage());
             preparedStatement.executeUpdate();
             ResultSet rs = preparedStatement.getGeneratedKeys();
             if(!rs.next()) return 0 ;
             return (int) rs.getLong(1);
         } catch (SQLException ex) {
             Logger.getLogger(ServPack.class.getName()).log(Level.SEVERE, null, ex);
         }
         return 0 ;
    }

    @Override
    public int SupprimerPack(int a) {
        int status =0;
        try {
            String req1="delete  from pack_decoration where"
                    + " id="+a;
       
      PreparedStatement ps = c.prepareStatement(req1);
      //      ps.setInt(1, r.getRecid());
            status=ps.executeUpdate();
         
           
        } catch (SQLException ex) {
            Logger.getLogger(ServRecette.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

    @Override
    public void ModifierPack(PackDecoration s) {
         try {
             PreparedStatement preparedStatement = c.prepareStatement("update `pack_decoration` set nom_pack=? , typeP = ? , description_pack=? , prixP=? , "
                     + "image_pack=? where id=?");
             preparedStatement.setString(1, s.getNom());
             preparedStatement.setString(2, s.getType());
             preparedStatement.setString(3, s.getDescriptionPack());
             preparedStatement.setDouble(4, s.getPrixP());
             preparedStatement.setString(5, s.getImage());
             preparedStatement.setInt(6, s.getId());
             preparedStatement.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(ServPack.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @Override
    public List<PackDecoration> AfficherPack() {
        List<PackDecoration> list = new ArrayList<>();
        PackDecoration packDecoration ;
         try {
             PreparedStatement preparedStatement = c.prepareStatement("select * from pack_decoration");
             ResultSet resultSet = preparedStatement.executeQuery();
             while(resultSet.next()) {
                 packDecoration = new PackDecoration(resultSet.getInt("id"),resultSet.getString("description_pack"), resultSet.getDouble("prixP"));
                 packDecoration.setNom(resultSet.getString("nom_pack"));
                 packDecoration.setImage(resultSet.getString("image_pack"));
                 packDecoration.setType(resultSet.getString("typeP"));
                 list.add(packDecoration);
             }
         } catch (SQLException ex) {
             Logger.getLogger(ServPack.class.getName()).log(Level.SEVERE, null, ex);
         }
         return list ;
    }

    @Override
    public List<PackDecoration> AfficherPackByType(String type) {
        List<PackDecoration> list = new ArrayList<>();
        PackDecoration packDecoration ;
         try {
             PreparedStatement preparedStatement = c.prepareStatement("select * from pack_decoration where typeP = ?");
             preparedStatement.setString(1, type);
             ResultSet resultSet = preparedStatement.executeQuery();
             while(resultSet.next()) {
                 packDecoration = new PackDecoration(resultSet.getInt("id"),resultSet.getString("description_pack"), resultSet.getDouble("prixP"));
                 packDecoration.setNom(resultSet.getString("nom_pack"));
                 packDecoration.setImage(resultSet.getString("image_pack"));
                 packDecoration.setType(resultSet.getString("typeP"));
                 list.add(packDecoration);
             }
         } catch (SQLException ex) {
             Logger.getLogger(ServPack.class.getName()).log(Level.SEVERE, null, ex);
         }
         return list ;
    }

    @Override
    public List<PackDecoration> RecherchePack(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouterLignePack(LignePack lignePack) {
                 try {
             PreparedStatement preparedStatement = 
                     c.prepareStatement("INSERT INTO `ligne_pack`(`quantite_pack`, `description_pack`, `IdProduit`, `IdPack`) VALUES (?,?,?,?)");
             preparedStatement.setInt(1, lignePack.getQuantite());
             preparedStatement.setString(2,lignePack.getDescription());
             preparedStatement.setInt(3, lignePack.getIdProduit().getId());
             preparedStatement.setInt(4, lignePack.getIdPack().getId());
             preparedStatement.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(ServPack.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
}
    /*
        this.id = id;
        this.objet = objet;
        this.contenue = contenue;
        this.dateRecla = dateRecla;
        IdUser////IdCommande
    */

    /*@Override
    public void AjouterPack(Pack s) 
    {
        try {
            String req1="INSERT INTO `Reclamation` "
                    + "(`IdUser`,`IdCommande`,`objet`,`contenue`,`date_recla`,`etat_recla`) "
                    + "VALUES (?,?,?,?,?,?);";
            PreparedStatement ste = c.prepareStatement(req1);
            
            ste.setInt(1,s.getIdUser());
            ste.setInt(2,s.getIdCommande());
            ste.setString(3,s.getObjet());
            ste.setString(4,s.getContenue());
            ste.setString(5,s.getDateRecla());
            ste.setString(6,s.getEtat_recla());
            
            ste.executeUpdate();

            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServRecette.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }

    @Override
    public void SupprimerReclamation(int a) 
    {
        try {
            String req1="delete  from Reclamation where"
                    + " id="+a;
       
      PreparedStatement ps = c.prepareStatement(req1);
      //      ps.setInt(1, r.getRecid());
            ps.executeUpdate();
         
           
        } catch (SQLException ex) {
            Logger.getLogger(ServRecette.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void ModifierReclamation(Reclamation s) 
    {
         if (s!=null)
        {
            
        String req = "UPDATE recette SET id='"+s.getId()+"',IdUser='"+s.getIdUser()+"',IdCommande='"+s.getIdCommande()+"',objet='"+s.getObjet()+"',contenu='"+s.getContenue()+"',DateRecla='"+s.getDateRecla()+"',etat_recla='"+s.getEtat_recla()+"' WHERE id="+s.getId();
        try {
            ste.executeUpdate(req);
            System.out.println("eyh");
        } catch (SQLException ex) {
            Logger.getLogger(ServRecette.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }
                System.out.println("lala");
    }
    
    
     @Override
    public ArrayList<Reclamation> AfficherReclamation() {
          String req = "select * from Reclamation";
        ArrayList<Reclamation> reclamations= new ArrayList<>();
        try {
            ResultSet res=  ste.executeQuery(req);
            
           
            while (res.next()) {
   Reclamation e = new Reclamation (res.getString("objet"),res.getString("contenue"),res.getString("date_recla"),res.getInt("idUser"),res.getInt("idCommande"),res.getString("etat_recla"));
   e.setId(res.getInt("id"));
      reclamations.add(e);
              
            }   
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return reclamations ;
      }

    @Override
    public List<Reclamation> AfficherReclamation2() 
    {
        List<Reclamation> savs = new ArrayList<>();
       
       Reclamation s = new Reclamation();
      String req2="select * from Reclamation";
      try {
         
         
          ResultSet res=  ste.executeQuery(req2);
          while (res.next()) { 
              s = new Reclamation();
                     s.setId(res.getInt("id"));
                     s.setIdUser(res.getInt("IdUser"));
                     s.setIdCommande(res.getInt("IdCommande"));
                     s.setObjet(res.getString("objet") );
                     s.setContenue(res.getString("contenue"));
                     s.setDateRecla(res.getString("date_recla"));
                     s.setEtat_recla(res.getString("etat_recla"));


              savs.add(s);
              System.out.println(s);
          }
          
      } catch (SQLException ex) {

          System.out.println(ex.getMessage());
      } 
        
     return savs;
    }

    @Override
    public List<Reclamation> RechercheReclamation(int id) 
    {
        Reclamation r = new Reclamation();

         List<Reclamation> savs = new ArrayList<>();
       r = null ;
       
   //   String req2="select * from service_apres_vente where savid ='"+savid"'";
            String req2="select * from Reclamation where id ='"+id+"'";
      try {
         
         
          ResultSet res=  ste.executeQuery(req2);
          while (res.next()) { 
              r = new Reclamation();
                     r.setId(res.getInt("id"));
                     r.setIdUser(res.getInt("IdUser"));
                     r.setIdCommande(res.getInt("IdCommande"));
                     r.setObjet(res.getString("objet") );
                     r.setContenue(res.getString("contenue"));
                     r.setDateRecla(res.getString("date_recla"));
                     r.setEtat_recla(res.getString("etat_recla"));


              savs.add(r);
             // System.out.println(r);
          }
          
      } catch (SQLException ex) {

          System.out.println(ex.getMessage());
      } 
        
     return savs;
    }


    
}

    
}
*/