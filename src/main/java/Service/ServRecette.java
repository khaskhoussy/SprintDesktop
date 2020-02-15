/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Iservice.InterfaceRecette;
import entits.Recette;
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
public class ServRecette implements InterfaceRecette
{
     Connection c = Singleton
           .getInstancesingleton()
           .getConnection();
    Statement ste;

 public ServRecette() 
 {
        try {
            ste = c.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    /**
     *
     * @param r
     */
    @Override
    public int AjouterRec(Recette r) {
        int x=0;
         try {
            String req1="INSERT INTO `recette`(`id`, `nom_rec`, `rec_description`, `type_rec`, `imagerecette`) VALUES (NULL,?,?,?,?)";
            PreparedStatement ste = c.prepareStatement(req1);

            ste.setString(1,r.getNomRec());        
            ste.setString(2,r.getRecDescription());
            ste.setString(3,r.getTypeRec());
            ste.setString(4, r.getImagerecette());
            x=ste.executeUpdate();

            
            
         
            
        } catch (SQLException ex) {
            Logger.getLogger(ServRecette.class.getName()).log(Level.SEVERE, null, ex);
        }
         return x;
    }

    @Override
    public int  SupprimerRec(int a) {
        int status =0;
        try {
            String req1="delete  from recette where"
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
    public void ModifierRec(Recette r) 
    {
     
        if (r!=null)
        {
            
        String req = "UPDATE recette SET id='"+r.getId()+"',rec_description='"+r.getRecDescription()+"',type_rec='"+r.getTypeRec()+"',nom_rec='"+r.getNomRec()+"',imagerecette='"+r.getImagerecette()+"' WHERE id="+r.getId();
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
    public ArrayList<Recette> AfficherRecette() {
          String req = "select * from Recette";
        ArrayList<Recette> recettes= new ArrayList<>();
        try {
            ResultSet res=  ste.executeQuery(req);
            
           
            while (res.next()) {
   Recette e = new Recette (res.getInt("id"),res.getString("nom_rec"),res.getString("rec_description"),res.getString("type_rec"),res.getString("imagerecette"));
      recettes.add(e);
              
            }   
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return recettes ;
      }
        @Override
    public ArrayList<Recette> AfficherRecette(String recette) {
          String req = "select * from Recette where type_rec = \""+recette+"\"";
        ArrayList<Recette> recettes= new ArrayList<>();
        try {
            ResultSet res=  ste.executeQuery(req);
            
           
            while (res.next()) {
   Recette e = new Recette (res.getInt("id"),res.getString("nom_rec"),res.getString("rec_description"),res.getString("type_rec"),res.getString("imagerecette"));
      recettes.add(e);
              
            }   
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return recettes ;
      } 
        
    // return recettes;
    

    @Override
    public List<Recette> RechercherRec(String salem) 
   {
    Recette r = new Recette();

         List<Recette> recettes = new ArrayList<>();
       r = null ;
       
      String req2="select * from recette where type_rec ='"+salem+"'";
      try {
         
         
          ResultSet res=  ste.executeQuery(req2);
          while (res.next()) { 
              r = new Recette();
                       r.setId(res.getInt("id"));
                      r.setNomRec(res.getString("nom_rec") );
                      r.setRecDescription(res.getString("rec_description") );
                      r.setTypeRec(res.getString("type_Rec"));
              recettes.add(r);
             // System.out.println(r);
          }
          
      } catch (SQLException ex) {

          System.out.println(ex.getMessage());
      } 
        
     return recettes;
    } 
   /* @Override
    public boolean RechercherRec(String type)  {
       boolean test=false;
        String req = "SELECT * from evenement where evenom='"+type+"'";
            
     
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
            Logger.getLogger(Serviceevent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return test;
        
    }*/

}

