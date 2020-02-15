/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Iservice.InterfaceReclamation;
import entits.Commande;
import entits.Reclamation;
import java.sql.Connection;
import java.sql.Date;
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
public class ServReclamation implements InterfaceReclamation {
    
     Connection c = Singleton
           .getInstancesingleton()
           .getConnection();
    Statement ste;
    
    public ServReclamation() 
 {
        try {
            ste = c.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    /*
        this.id = id;
        this.objet = objet;
        this.contenue = contenue;
        this.dateRecla = dateRecla;
        IdUser////IdCommande
    */

    @Override
    public int  AjouterReclamation(Reclamation s) 
    {int  i= 0;
        try {
            String req1="INSERT INTO `Reclamation` "
                    + "(`IdUser`,`IdCommande`,`objet`,`contenue`,`date_recla`,`etat_recla`) "
                    + "VALUES (?,?,?,?,?,?);";
            PreparedStatement ste = c.prepareStatement(req1);
            
            ste.setInt(1,s.getIdUser());
            ste.setInt(2,s.getIdCommande());
            ste.setString(3,s.getObjet());
            ste.setString(4,s.getContenue());
            ste.setDate(5, new Date(s.getDateRecla().getTime()));
            ste.setString(6,"En attente");
            
            i=ste.executeUpdate();

            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServRecette.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return i;
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
            
        String req = "UPDATE reclamation SET IdUser="+s.getIdUser()+",IdCommande="+s.getIdCommande()+",objet='"+s.getObjet()+"',contenue='"+s.getContenue()+"',`date_recla`='"+s.getDateRecla()+"',`etat_recla`='"+s.getEtat_recla()+"' WHERE id="+s.getId();
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
   Reclamation e = new Reclamation (res.getString("objet"),res.getString("contenue"),res.getDate("date_recla"),res.getInt("idUser"),res.getInt("idCommande"),res.getString("etat_recla"));
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
                     s.setDateRecla(res.getDate("date_recla"));
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
                     r.setDateRecla(res.getDate("date_recla"));
                     r.setEtat_recla(res.getString("etat_recla"));


              savs.add(r);
             // System.out.println(r);
          }
          
      } catch (SQLException ex) {

          System.out.println(ex.getMessage());
      } 
        
     return savs;
    }

    @Override
    public ArrayList<Commande> GetIdReclamation(int id) {
        
      ArrayList<Commande> savs = new ArrayList<>();
       
        Commande s =null;
      String req2="select * from commande where IdUser="+id;
      try {
         
         
          ResultSet res=  ste.executeQuery(req2);
          while (res.next()) { 
              s = new Commande(res.getInt("id"));
              s.setPrixTotal(res.getInt("prix_total"));
              savs.add(s);
          }
          
      } catch (SQLException ex) {

          System.out.println(ex.getMessage());
      } 
        
     return savs;
    }


    
}
