/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import Iservice.IClientService;
import entits.Client;
import entits.User;
import static entits.User_.password;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import fxml.Singleton;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author aisce
 */
public class ClientService implements IClientService{
 Connection c =Singleton.getInstancesingleton().getConnection();
    Statement ste;
    public ClientService() {
        try {
            ste = c.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
   
    
    @Override
    public  int creerCompte(Client a) {
 int r=0;
        try {
           String req1="INSERT INTO `user`(`Nom`,`prenom`,`email`,`password`,`username`,`roles`,`username_canonical`,`email_canonical`,`enabled`,`telephone`) VALUES (?,?,?,?,?,?,?,?,?,?)" ;
           PreparedStatement pstm = c.prepareStatement(req1);
           pstm.setString(1,a.getNom());
           pstm.setString(2,a.getPrenom());
           pstm.setString(3,a.getEmail());
           pstm.setString(4,BCrypt.hashpw(a.getPassword(), BCrypt.gensalt(13)));
           pstm.setString(5,a.getUsername());
           pstm.setString(6,"a:1:{i:0;s:11:\"ROLE_CLIENT\";}");
           pstm.setString(7,a.getUsername());
           pstm.setString(8,a.getEmail());
           pstm.setInt(9,1);
           pstm.setString(10,a.getTelephone());
           r= pstm.executeUpdate();
            System.out.println(r);
           
       } catch (SQLException ex) {
           Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, ex);
       }
        return r;
    }    
}
    
    

