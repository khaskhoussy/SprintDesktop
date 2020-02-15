/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Iservice.IadminService;
import entits.Commande;
import entits.LigneService;
import entits.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import fxml.Singleton;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author aisce
 */
public class AdminService implements IadminService{
 Connection c =Singleton.getInstancesingleton().getConnection();
    Statement ste;
    public AdminService() {
        try {
            ste = c.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    @Override
    public int ajouterAdmin(User a) {
   int r=0;
        try {
           String req1="INSERT INTO `user`(`Nom`,`prenom`,`email`,`password`,`username`,`roles`,`username_canonical`,`email_canonical`,`enabled`,`telephone`) VALUES (?,?,?,?,?,?,?,?,?,?)" ;
           PreparedStatement pstm = c.prepareStatement(req1);
           pstm.setString(1,a.getNom());
           pstm.setString(2,a.getPrenom());
           pstm.setString(3,a.getEmail());
           pstm.setString(4,BCrypt.hashpw(a.getPassword(), BCrypt.gensalt(13)));
           pstm.setString(5,a.getUsername());
           pstm.setString(6,"a:1:{i:0;s:10:\"ROLE_ADMIN\";}");
           pstm.setString(7,a.getUsername());
           pstm.setString(8,a.getEmail());
           pstm.setInt(9,1);
           pstm.setString(10,a.getTelephone());
          r= pstm.executeUpdate();
           
       } catch (SQLException ex) {
           Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, ex);
       }
        return r;
    }
    @Override
    public int ajouterJardinier(User a) {
   int r=0;
        try {
           String req1="INSERT INTO `user`(`Nom`,`prenom`,`email`,`password`,`username`,`roles`,`username_canonical`,`email_canonical`,`enabled`,`telephone`) VALUES (?,?,?,?,?,?,?,?,?,?)" ;
           PreparedStatement pstm = c.prepareStatement(req1);
           pstm.setString(1,a.getNom());
           pstm.setString(2,a.getPrenom());
           pstm.setString(3,a.getEmail());
           pstm.setString(4,BCrypt.hashpw(a.getPassword(), BCrypt.gensalt(13)));
           pstm.setString(5,a.getUsername());
           pstm.setString(6,"a:1:{i:0;s:14:\"ROLE_JARDINIER\";}");
           pstm.setString(7,a.getUsername());
           pstm.setString(8,a.getEmail());
           pstm.setInt(9,1);
           pstm.setString(10,a.getTelephone());
          r= pstm.executeUpdate();
           
       } catch (SQLException ex) {
           Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, ex);
       }
        return r;
    }

    @Override
    public int ajouterExpert(User a) {
 int r=0;
        try {
           String req1="INSERT INTO `user`(`Nom`,`prenom`,`email`,`password`,`username`,`roles`,`username_canonical`,`email_canonical`,`enabled`,`telephone`) VALUES (?,?,?,?,?,?,?,?,?,?)" ;
           PreparedStatement pstm = c.prepareStatement(req1);
           pstm.setString(1,a.getNom());
           pstm.setString(2,a.getPrenom());
           pstm.setString(3,a.getEmail());
           pstm.setString(4,BCrypt.hashpw(a.getPassword(), BCrypt.gensalt(13)));
           pstm.setString(5,a.getUsername());
           pstm.setString(6,"a:1:{i:0;s:11:\"ROLE_EXPERT\";}");
           pstm.setString(7,a.getUsername());
           pstm.setString(8,a.getEmail());
           pstm.setInt(9,1);
           pstm.setString(10,a.getTelephone());
          r= pstm.executeUpdate();
           
       } catch (SQLException ex) {
           Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, ex);
       }
        return r;
    }

    @Override
    public int ajouterPaysagist(User a) {
 int r=0;
        try {
           String req1="INSERT INTO `user`(`Nom`,`prenom`,`email`,`password`,`username`,`roles`,`username_canonical`,`email_canonical`,`enabled`,`telephone`) VALUES (?,?,?,?,?,?,?,?,?,?)" ;
           PreparedStatement pstm = c.prepareStatement(req1);
           pstm.setString(1,a.getNom());
           pstm.setString(2,a.getPrenom());
           pstm.setString(3,a.getEmail());
           pstm.setString(4,BCrypt.hashpw(a.getPassword(), BCrypt.gensalt(13)));
           pstm.setString(5,a.getUsername());
           pstm.setString(6,"a:1:{i:0;s:15:\"ROLE_PAYSAGISTE\";}");
           pstm.setString(7,a.getUsername());
           pstm.setString(8,a.getEmail());
           pstm.setInt(9,1);
           pstm.setString(10,a.getTelephone());
          r= pstm.executeUpdate();
           
       } catch (SQLException ex) {
           Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, ex);
       }
        return r;   
    }
    @Override
    public List<User> afficherGlobal() {
      
      List<User> jardinier = new ArrayList<>();
      User p = null ;
      String req2=("SELECT * FROM user");
      try {
         
         
          ResultSet res=  ste.executeQuery(req2);
          while (res.next()) { 
              p = new User();
                      p.setId( res.getInt("id"));
                      p.setNom( res.getString("Nom") );
                      p.setPrenom(res.getString("Prenom") );
                      p.setEmail(res.getString("email"));
                      p.setTelephone(res.getString("telephone"));
                      p.setUsername(res.getString("username"));
                      p.setPassword(res.getString("password"));
                      p.setRoles(res.getString("roles"));
                      if(p.getRoles().contains("JARDINIER"))
                          p.setRoles("Jardinier");
                      if(p.getRoles().contains("PAYSAGISTE"))
                          p.setRoles("Paysagist");
                      if(p.getRoles().contains("CLIENT"))
                          p.setRoles("Client");
                      if(p.getRoles().contains("EXPERT"))
                          p.setRoles("Expert");
                      if(p.getRoles().contains("ADMIN"))
                          p.setRoles("Admin");

              jardinier.add(p);
          }
          
      } catch (SQLException ex) {
          System.out.println(ex.getMessage());
      } 
        
     return jardinier;
    }
    public List<User> afficherJardinier() {
      
      List<User> jardinier = new ArrayList<>();
      User p = null ;
      String x;
      x="Jardinier";
      String req2=("SELECT * FROM user WHERE roles='a:1:{i:0;s:14:\"ROLE_JARDINIER\";}'");
      try {
         
         
          ResultSet res=  ste.executeQuery(req2);
          while (res.next()) { 
              p = new User();
                      p.setId( res.getInt("id"));
                      p.setNom( res.getString("Nom") );
                      p.setPrenom(res.getString("Prenom") );
                      p.setEmail(res.getString("email"));
                      p.setTelephone(res.getString("telephone"));
                      p.setUsername(res.getString("username"));
                      p.setPassword(res.getString("password"));

              jardinier.add(p);
          }
          
      } catch (SQLException ex) {
          System.out.println(ex.getMessage());
      } 
        
     return jardinier;
    }
     @Override
    public void supprimerJardinier(User a) {
        try {
            String req1="delete from `user` WHERE  username=?";
       
      PreparedStatement ps = c.prepareStatement(req1);
            ps.setString(1,a.getUsername());
            ps.executeUpdate();
         
           
        } catch (SQLException ex) {
            Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      //@Override
   public List<User>chercherJardinier() {
      List<User> jardinier = new ArrayList<>();
        User p = null;
       
        //String req=("SELECT * FROM `user` WHERE username='"+use+"'");
        String req=("SELECT * FROM user WHERE username='ammar'");
       try {
         
         Connection c =Singleton.getInstancesingleton().getConnection();
          ResultSet res=  ste.executeQuery(req);
          while (res.next()) { 
              p = new User();
                      p = new User();
                      p.setId( res.getInt("id"));
                      p.setNom( res.getString("Nom") );
                      p.setPrenom(res.getString("Prenom") );
                      p.setEmail(res.getString("email"));
                      p.setTelephone(res.getString("telephone"));
                      p.setUsername(res.getString("username"));
                      p.setPassword(res.getString("password"));
                      p.setRoles(res.getString("roles"));

              jardinier.add(p);
          }
          
      } catch (SQLException ex) {
          System.out.println(ex.getMessage());
      } 
        
     return jardinier;
    }

    @Override
     public String chercherJardinier1(String use) {
        String req=("SELECT * FROM `user` WHERE username='"+use+"'" );
      //  String req=("SELECT * FROM user WHERE username='ammar'");
         User  p = new User();
         String salem=null;
          try {
                ResultSet res=ste.executeQuery(req);   
                 
                     while (res.next()) { 
              p = new User();
                      p.setId( res.getInt("id"));
                      p.setNom( res.getString("Nom") );
                      p.setPrenom(res.getString("Prenom") );
                      p.setEmail(res.getString("email"));
                      p.setTelephone(res.getString("telephone"));
                      p.setUsername(res.getString("username"));
                      p.setPassword(res.getString("password"));
                      p.setRoles(res.getString("roles"));

              
          }

                 
        }
        catch (SQLException ex) {
            Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
        }
          
        return p.getRoles();
        
    }
     @Override
     public String chercherPassword(String use) {
        String req=("SELECT * FROM `user` WHERE username='"+use+"'" );
      //  String req=("SELECT * FROM user WHERE username='ammar'");
         User  p = new User();
         String salem=null;
          try {
                ResultSet res=ste.executeQuery(req);   
                 
                     while (res.next()) { 
              p = new User();
                      p.setId( res.getInt("id"));
                      p.setNom( res.getString("Nom") );
                      p.setPrenom(res.getString("Prenom") );
                      p.setEmail(res.getString("email"));
                      p.setTelephone(res.getString("telephone"));
                      p.setUsername(res.getString("username"));
                      p.setPassword(res.getString("password"));
                       p.setRoles(res.getString("roles"));

              
          }

                 
        }
        catch (SQLException ex) {
            Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
        }
          
        return p.getPassword();
        
    }
    @Override
    public List<User> afficherPaysagist() {
      
      List<User> jardinier = new ArrayList<>();
      User p = null ;
      String x;
     
      String req2=("SELECT * FROM user WHERE roles='a:1:{i:0;s:15:\"ROLE_PAYSAGISTE\";}'");
      try {
         
         
          ResultSet res=  ste.executeQuery(req2);
          while (res.next()) { 
              p = new User();
                      p.setId( res.getInt("id"));
                      p.setNom( res.getString("Nom") );
                      p.setPrenom(res.getString("Prenom") );
                      p.setEmail(res.getString("email"));
                      p.setTelephone(res.getString("telephone"));
                      p.setUsername(res.getString("username"));
                      p.setPassword(res.getString("password"));

              jardinier.add(p);
          }
          
      } catch (SQLException ex) {
          System.out.println(ex.getMessage());
      } 
        
     return jardinier;
    }
     @Override
    public List<User> afficherExpert() {
      
      List<User> jardinier = new ArrayList<>();
      User p = null ;
      String x;
     
      String req2=("SELECT * FROM user WHERE roles='a:1:{i:0;s:11:\"ROLE_EXPERT\";}'");
      try {
         
         
          ResultSet res=  ste.executeQuery(req2);
          while (res.next()) { 
              p = new User();
                      p.setId( res.getInt("id"));
                      p.setNom( res.getString("Nom") );
                      p.setPrenom(res.getString("Prenom") );
                      p.setEmail(res.getString("email"));
                      p.setTelephone(res.getString("telephone"));
                      p.setUsername(res.getString("username"));
                      p.setPassword(res.getString("password"));

              jardinier.add(p);
          }
          
      } catch (SQLException ex) {
          System.out.println(ex.getMessage());
      } 
        
     return jardinier;
    }
     @Override
    public List<User> afficherClient() {
      Connection c =Singleton.getInstancesingleton().getConnection();
   
      List<User> jardinier = new ArrayList<>();
      User p = null ;
      String x;
     
      String req2=("SELECT * FROM user WHERE roles='a:1:{i:0;s:11:\"ROLE_CLIENT\";}'");
      
      try {
          
         
          ResultSet res=  ste.executeQuery(req2);
          while (res.next()) { 
              p = new User();
                      p.setId( res.getInt("id"));
                      p.setNom( res.getString("Nom") );
                      p.setPrenom(res.getString("Prenom") );
                      p.setEmail(res.getString("email"));
                      p.setTelephone(res.getString("telephone"));
                      p.setUsername(res.getString("username"));
                      p.setPassword(res.getString("password"));

              jardinier.add(p);
          }
          
      } catch (SQLException ex) {
          System.out.println(ex.getMessage());
      } 
        
     return jardinier;
    }
     @Override
    public List<User> chercherNom(String nom) {
      
      List<User> jardinier = new ArrayList<>();
      User p = null ;
      String x;
     
      String req2=("SELECT * FROM `user` WHERE Nom ='"+nom+"'And roles ='a:1:{i:0;s:11:\"ROLE_CLIENT\";}'");
      try {
         
         
          ResultSet res=  ste.executeQuery(req2);
          while (res.next()) { 
              p = new User();
                      p.setId( res.getInt("id"));
                      p.setNom( res.getString("Nom") );
                      p.setPrenom(res.getString("Prenom") );
                      p.setEmail(res.getString("email"));
                      p.setTelephone(res.getString("telephone"));
                      p.setUsername(res.getString("username"));
                      p.setPassword(res.getString("password"));

              jardinier.add(p);
          }
          
      } catch (SQLException ex) {
          System.out.println(ex.getMessage());
      } 
        
     return jardinier;
    }
     @Override
    public List<User> chercherPrenom(String prenom) {
      
      List<User> jardinier = new ArrayList<>();
      User p = null ;
      String x;
     
      String req2=("SELECT * FROM `user` WHERE prenom ='"+prenom+"'And roles ='a:1:{i:0;s:11:\"ROLE_CLIENT\";}'");
      try {
         
         
          ResultSet res=  ste.executeQuery(req2);
          while (res.next()) { 
              p = new User();
                      p.setId( res.getInt("id"));
                      p.setNom( res.getString("Nom") );
                      p.setPrenom(res.getString("Prenom") );
                      p.setEmail(res.getString("email"));
                      p.setTelephone(res.getString("telephone"));
                      p.setUsername(res.getString("username"));
                      p.setPassword(res.getString("password"));

              jardinier.add(p);
          }
          
      } catch (SQLException ex) {
          System.out.println(ex.getMessage());
      } 
        
     return jardinier;
    }
     @Override
    public List<User> chercheremail(String email) {
      
      List<User> jardinier = new ArrayList<>();
      User p = null ;
      String x;
     
      String req2=("SELECT * FROM `user` WHERE email='"+email+"'And roles ='a:1:{i:0;s:11:\"ROLE_CLIENT\";}'");
      try {
         
         
          ResultSet res=  ste.executeQuery(req2);
          while (res.next()) { 
              p = new User();
                      p.setId( res.getInt("id"));
                      p.setNom( res.getString("Nom") );
                      p.setPrenom(res.getString("Prenom") );
                      p.setEmail(res.getString("email"));
                      p.setTelephone(res.getString("telephone"));
                      p.setUsername(res.getString("username"));
                      p.setPassword(res.getString("password"));

              jardinier.add(p);
          }
          
      } catch (SQLException ex) {
          System.out.println(ex.getMessage());
      } 
        
     return jardinier;
    }
     @Override
    public List<User> cherchertelephone(String telephone) {
      
      List<User> jardinier = new ArrayList<>();
      User p = null ;
      String x;
     
      String req2=("SELECT * FROM `user` WHERE telephone ='"+telephone+"'And roles ='a:1:{i:0;s:11:\"ROLE_CLIENT\";}'");
      try {
         
         
          ResultSet res=  ste.executeQuery(req2);
          while (res.next()) { 
              p = new User();
                      p.setId( res.getInt("id"));
                      p.setNom( res.getString("Nom") );
                      p.setPrenom(res.getString("Prenom") );
                      p.setEmail(res.getString("email"));
                      p.setTelephone(res.getString("telephone"));
                      p.setUsername(res.getString("username"));
                      p.setPassword(res.getString("password"));

              jardinier.add(p);
          }
          
      } catch (SQLException ex) {
          System.out.println(ex.getMessage());
      } 
        
     return jardinier;
    }

    @Override
    public String modifNom(int idu) {
 String req=("SELECT * FROM `user` WHERE id='"+idu+"'" );
      //  String req=("SELECT * FROM user WHERE username='ammar'");
         User  p = new User();
         String salem=null;
          try {
                ResultSet res=ste.executeQuery(req);   
                 
                     while (res.next()) { 
              p = new User();
                      p.setId( res.getInt("id"));
                      p.setNom( res.getString("Nom") );
                      p.setPrenom(res.getString("Prenom") );
                      p.setEmail(res.getString("email"));
                      p.setTelephone(res.getString("telephone"));
                      p.setUsername(res.getString("username"));
                      p.setPassword(res.getString("password"));
                       p.setRoles(res.getString("roles"));

              
          }

                 
        }
        catch (SQLException ex) {
            Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
        }
          
        return p.getNom();   
    }

    @Override
    public String modifPrenom(int idu) {
String req=("SELECT * FROM `user` WHERE id='"+idu+"'");
      //  String req=("SELECT * FROM user WHERE username='ammar'");
         User  p = new User();
         String salem=null;
          try {
                ResultSet res=ste.executeQuery(req);   
                 
                     while (res.next()) { 
              p = new User();
                      p.setId( res.getInt("id"));
                      p.setNom( res.getString("Nom") );
                      p.setPrenom(res.getString("Prenom") );
                      p.setEmail(res.getString("email"));
                      p.setTelephone(res.getString("telephone"));
                      p.setUsername(res.getString("username"));
                      p.setPassword(res.getString("password"));
                       p.setRoles(res.getString("roles"));

              
          }

                 
        }
        catch (SQLException ex) {
            Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
        }
          
        return p.getPrenom();
    }

    @Override
    public String modifEmail(int idu) {
String req=("SELECT * FROM `user` WHERE id='"+idu+"'" );
      //  String req=("SELECT * FROM user WHERE username='ammar'");
         User  p = new User();
         String salem=null;
          try {
                ResultSet res=ste.executeQuery(req);   
                 
                     while (res.next()) { 
              p = new User();
                      p.setId( res.getInt("id"));
                      p.setNom( res.getString("Nom") );
                      p.setPrenom(res.getString("Prenom") );
                      p.setEmail(res.getString("email"));
                      p.setTelephone(res.getString("telephone"));
                      p.setUsername(res.getString("username"));
                      p.setPassword(res.getString("password"));
                       p.setRoles(res.getString("roles"));

              
          }

                 
        }
        catch (SQLException ex) {
            Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
        }
          
        return p.getEmail();    
    }

    @Override
    public String modifTelephone(int idu) {
String req=("SELECT * FROM `user` WHERE id='"+idu+"'");
      //  String req=("SELECT * FROM user WHERE username='ammar'");
         User  p = new User();
         String salem=null;
          try {
                ResultSet res=ste.executeQuery(req);   
                 
                     while (res.next()) { 
              p = new User();
                      p.setId( res.getInt("id"));
                      p.setNom( res.getString("Nom") );
                      p.setPrenom(res.getString("Prenom") );
                      p.setEmail(res.getString("email"));
                      p.setTelephone(res.getString("telephone"));
                      p.setUsername(res.getString("username"));
                      p.setPassword(res.getString("password"));
                       p.setRoles(res.getString("roles"));

              
          }

                 
        }
        catch (SQLException ex) {
            Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
        }
          
        return p.getTelephone();    
    }

    @Override
    public String modifPassword(int idu) {
String req=("SELECT * FROM `user` WHERE id='"+idu+"'");
      //  String req=("SELECT * FROM user WHERE username='ammar'");
         User  p = new User();
         String salem=null;
          try {
                ResultSet res=ste.executeQuery(req);   
                 
                     while (res.next()) { 
              p = new User();
                      p.setId( res.getInt("id"));
                      p.setNom( res.getString("Nom") );
                      p.setPrenom(res.getString("Prenom") );
                      p.setEmail(res.getString("email"));
                      p.setTelephone(res.getString("telephone"));
                      p.setUsername(res.getString("username"));
                      p.setPassword(res.getString("password"));
                       p.setRoles(res.getString("roles"));

              
          }

                 
        }
        catch (SQLException ex) {
            Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
        }
          
        return p.getPassword();    
    }
    @Override
    public int  modifId(String use) {
String req=("SELECT * FROM `user` WHERE username='"+use+"'");
      //  String req=("SELECT * FROM user WHERE username='ammar'");
         User  p = new User();
         String salem=null;
          try {
                ResultSet res=ste.executeQuery(req);   
                 
                     while (res.next()) { 
              p = new User();
                      p.setId( res.getInt("id"));
                      p.setNom( res.getString("Nom") );
                      p.setPrenom(res.getString("Prenom") );
                      p.setEmail(res.getString("email"));
                      p.setTelephone(res.getString("telephone"));
                      p.setUsername(res.getString("username"));
                      p.setPassword(res.getString("password"));
                       p.setRoles(res.getString("roles"));

              
          }

                 
        }
        catch (SQLException ex) {
            Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
        }
          
        return p.getId();
    }
     @Override
        public int  modfierCompte(User a ,User b){
         int x=0;
        String req = "UPDATE `user` SET  Nom=? ,Prenom= ?,email=?,telephone = ?,username= ?,username_canonical = ?,email_canonical = ?,`password`= ? WHERE id=?";
        try {
            PreparedStatement pstm = c.prepareStatement(req);
           pstm.setString(1,a.getNom());
           pstm.setString(2,a.getPrenom());
           pstm.setString(3,a.getEmail());
           pstm.setString(4,a.getTelephone());
           pstm.setString(5,a.getUsername());
           pstm.setString(6,a.getUsernameCanonical());
           pstm.setString(7,a.getEmailCanonical());
           pstm.setString(8,a.getPassword());
           pstm.setInt(9,b.getId());
           x=pstm.executeUpdate();
           
        } catch (SQLException ex) {
            Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null,ex);
        }
        return x;
        }
         @Override
    public List<User> afficherStaff() {
      
      List<User> jardinier = new ArrayList<>();
      User p = null ;
      String x;
     
      String req2=("SELECT * FROM user WHERE roles='a:1:{i:0;s:15:\"ROLE_PAYSAGISTE\";}' OR roles='a:1:{i:0;s:14:\"ROLE_JARDINIER\";}'");
      try {
         
         
          ResultSet res=  ste.executeQuery(req2);
          while (res.next()) { 
              p = new User();
                      p.setId( res.getInt("id"));
                      p.setNom( res.getString("Nom") );
                      p.setPrenom(res.getString("Prenom") );
                      p.setEmail(res.getString("email"));
                      p.setTelephone(res.getString("telephone"));  
                      p.setRoles(res.getString("roles"));
                      if(p.getRoles().contains("JARDINIER"))
                          p.setRoles("Jardinier");
                      else
                          p.setRoles("Paysagist");
              jardinier.add(p);
          }
          
      } catch (SQLException ex) {
          System.out.println(ex.getMessage());
      } 
        
     return jardinier;
    }
    @Override
    public String modifImg(String use) {
 String req=("SELECT * FROM `user` WHERE username='"+use+"'" );
      //  String req=("SELECT * FROM user WHERE username='ammar'");
         User  p = new User();
         String salem=null;
         BufferedImage image =null;
          try {
                ResultSet res=ste.executeQuery(req);   
                 
                     while (res.next()) { 
              p = new User();
                      p.setId( res.getInt("id"));
                      p.setNom( res.getString("Nom") );
                      p.setPrenom(res.getString("Prenom") );
                      p.setEmail(res.getString("email"));
                      p.setTelephone(res.getString("telephone"));
                      p.setUsername(res.getString("username"));
                      p.setPassword(res.getString("password"));
                       p.setRoles(res.getString("roles"));
                       p.setImg(res.getString("image"));
                    try {
                        image = ImageIO.read(res.getBinaryStream("image" ));
                    } catch (IOException ex) {
                        Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
                    }

              
          }

                 
        }
        catch (SQLException ex) {
            Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
        }
          System.out.println(p.getImg());
        return p.getImg();   
    }
   @Override
    public int ajouterimg(User a,String u) {
   int r=0;
        
        try {
            String req = "UPDATE `user` SET `image`= ? WHERE username= '"+u+"'"; ;
           PreparedStatement pstm = c.prepareStatement(req);
           pstm.setString(1,a.getImg());    
               
          r= pstm.executeUpdate();
           
       } catch (SQLException ex) {
           Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, ex);
       }
        return r;
    }
    @Override
    public String recupTel(String use) {
 String req=("SELECT * FROM `user` WHERE email='"+use+"'" );
      //  String req=("SELECT * FROM user WHERE username='ammar'");
         User  p = new User();
         String salem=null;
         BufferedImage image =null;
          try {
                ResultSet res=ste.executeQuery(req);   
                 
                     while (res.next()) { 
              p = new User();
                      p.setId( res.getInt("id"));
                      p.setNom( res.getString("Nom") );
                      p.setPrenom(res.getString("Prenom") );
                      p.setEmail(res.getString("email"));
                      p.setTelephone(res.getString("telephone"));
                      p.setUsername(res.getString("username"));
                      p.setPassword(res.getString("password"));
                       p.setRoles(res.getString("roles"));
                       p.setImg(res.getString("image"));
                    try {
                        image = ImageIO.read(res.getBinaryStream("image" ));
                    } catch (IOException ex) {
                        Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
                    }

              
          }

                 
        }
        catch (SQLException ex) {
            Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
        }
          System.out.println(p.getImg());
        return p.getTelephone();   
    }
    @Override
    public String recupMdp(String use) {
 String req=("SELECT * FROM `user` WHERE email='"+use+"'" );
      //  String req=("SELECT * FROM user WHERE username='ammar'");
         User  p = new User();
         String salem=null;
         BufferedImage image =null;
          try {
                ResultSet res=ste.executeQuery(req);   
                 
                     while (res.next()) { 
              p = new User();
                      p.setId( res.getInt("id"));
                      p.setNom( res.getString("Nom") );
                      p.setPrenom(res.getString("Prenom") );
                      p.setEmail(res.getString("email"));
                      p.setTelephone(res.getString("telephone"));
                      p.setUsername(res.getString("username"));
                      p.setPassword(res.getString("password"));
                       p.setRoles(res.getString("roles"));
                       p.setImg(res.getString("image"));
                    try {
                        image = ImageIO.read(res.getBinaryStream("image" ));
                    } catch (IOException ex) {
                        Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
                    }

              
          }

                 
        }
        catch (SQLException ex) {
            Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
        }
          System.out.println(p.getImg());
        return p.getPassword();   
    }
    @Override
        public int  modfierComptee(User a){
         int x=0;
        String req = "UPDATE `user` SET  Nom=? ,Prenom= ?,email=?,telephone = ?,username= ?,username_canonical=?,email_canonical = ?,`password`= ? WHERE id=?";
        try {
            PreparedStatement pstm = c.prepareStatement(req);
           pstm.setString(1,a.getNom());
           pstm.setString(2,a.getPrenom());
           pstm.setString(3,a.getEmail());
           pstm.setString(4,a.getTelephone());
           pstm.setString(5,a.getUsername());
           pstm.setString(6,a.getUsername());
           pstm.setString(7,a.getEmail());
           pstm.setString(8,a.getPassword());
           pstm.setInt(9,a.getId());
           x=pstm.executeUpdate();
           
        } catch (SQLException ex) {
            Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null,ex);
        }
        return x;
        }

    @Override
    public List<LigneService> getLigneServicesByEmployer(User user) {
        List<LigneService> list = new ArrayList<>();
        LigneService ls ;
        User userLs ;
        Commande commande;
     try {
         Statement statement = c.createStatement();
         ResultSet rs = statement.executeQuery("SELECT * FROM ligne_service ls JOIN commande c on ls.IdCommande = c.id "
                 + "JOIN user u on u.id = c.IdUser where ls.IdUser = "+user.getId());
         while(rs.next()) {
             ls = new LigneService(rs.getInt("ls.id"));
             ls.setDateDebut(rs.getDate("ls.DateDebut"));
             ls.setDateFin(rs.getDate("ls.DateFin"));
             userLs = new User(rs.getInt("u.id"));
             userLs.setNom(rs.getString("u.Nom"));
             userLs.setPrenom(rs.getString("u.prenom"));
             userLs.setTelephone(rs.getString("u.telephone"));
             commande = new Commande(rs.getInt("c.id"));
             commande.setIdUser(userLs);
             ls.setIdCommande(commande);
             list.add(ls);
         }
     } catch (SQLException ex) {
         Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
     }
     return list;
    }
}
    
    

