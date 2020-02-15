
package Service;

import Iservice.InterfaceRecette;
import entits.Geste;
import entits.Recette;
import Iservice.InterfaceGeste;
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
public class ServGeste  implements InterfaceGeste
{
     Connection c = Singleton
           .getInstancesingleton()
           .getConnection();
    Statement ste;

 public ServGeste() 
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
    public int AjouterGeste(Geste g) {
        int status=0;
         try {
            String req1="INSERT INTO `geste_du_mois`(`id`, `nom_geste`, `desc_geste`, `mois_geste`, `image_geste`) VALUES (NULL,?,?,?,?)";
            PreparedStatement ste = c.prepareStatement(req1);

            ste.setString(1,g.getNom_geste());        
            ste.setString(2,g.getDesc_geste());
            ste.setString(3,g.getMois_geste());
            ste.setString(4, g.getImage_geste());
            ste.executeUpdate();

            
            
         
            
        } catch (SQLException ex) {
            Logger.getLogger(ServRecette.class.getName()).log(Level.SEVERE, null, ex);
        }
         return status;
    }

    @Override
    public int SupprimerGeste(int a) {
        int status=0;
        try {
            String req1="delete  from geste_du_mois where"
                    + " id="+a;
       
      PreparedStatement ps = c.prepareStatement(req1);
      //      ps.setInt(1, r.getRecid());
            ps.executeUpdate();
         
           
        } catch (SQLException ex) {
            Logger.getLogger(ServRecette.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

    @Override
    public void ModifierGeste(Geste g) 
    {
     
        if (g!=null)
        {
            
        String req = "UPDATE geste_du_mois SET id='"+g.getId()+"',nom_geste='"+g.getNom_geste()+"',desc_geste='"+g.getDesc_geste()+"',mois_geste='"+g.getMois_geste()+"',image_geste='"+g.getImage_geste()+"' WHERE id="+g.getId();
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
    public ArrayList<Geste> AfficherGeste() {
          String req = "select * from geste_du_mois";
        ArrayList<Geste> gestes= new ArrayList<>();
        try {
            ResultSet res=  ste.executeQuery(req);
            
           
            while (res.next()) {
               Geste e = new Geste (res.getString("nom_geste"),res.getString("mois_geste"),res.getString("desc_geste"),res.getString("image_geste"));
               e.setId(res.getInt("id"));
      gestes.add(e);
              
            }   
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return gestes ;
      }
    
    
        @Override
    public ArrayList<Geste> AfficherGeste(String geste) {
          String req = "select * from geste_du_mois where mois_geste = \""+geste+"\"";
        ArrayList<Geste> gestes= new ArrayList<>();
        try {
            ResultSet res=  ste.executeQuery(req);
            
           
            while (res.next()) {
  Geste e = new Geste (res.getString("nom_geste"),res.getString("mois_geste"),res.getString("desc_geste"),res.getString("image_geste"));
      gestes.add(e);
              
            }   
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return gestes ;
      } 
        
    // return recettes;
    

    @Override
public List<Geste> RechercherGeste(String mois_geste) 
{
    Geste g = new Geste();

         List<Geste> gestes = new ArrayList<>();
       g = null ;
       
      String req2="select * from geste_du_mois where mois_geste ='"+mois_geste+"'";
      try {
         
         
          ResultSet res=  ste.executeQuery(req2);
          while (res.next()) { 
              g= new Geste();
                       g.setNom_geste(res.getString("nom_geste"));
                      g.setMois_geste(res.getString("mois_geste") );
                      g.setDesc_geste(res.getString("desc_geste") );
                      g.setImage_geste(res.getString("image_geste"));
              gestes.add(g);
             // System.out.println(r);
          }
          
      } catch (SQLException ex) {

          System.out.println(ex.getMessage());
      } 
        
     return gestes;
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


    
