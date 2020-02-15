/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entits;

import Service.AdminService;
import fxml.HomeController;
import fxml.Singleton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aisce
 */
public class CompteExpertController implements Initializable {

    @FXML
    private TextField nomModif;
    @FXML
    private TextField PrenomModif;
    @FXML
    private TextField emailModif;
    @FXML
    private TextField telephoneModif;
    @FXML
    private TextField usernameModif;
    @FXML
    private TextField passwordModif;
     @FXML
    private ImageView Dphoto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         Connection myConn0 = Singleton.getInstancesingleton()
            .getConnection();
         AdminService as = new AdminService();
         User u =new User();
         nomModif.setText(as.modifNom(HomeController.idU));
         PrenomModif.setText(as.modifPrenom(HomeController.idU));
         emailModif.setText(as.modifEmail(HomeController.idU));
         usernameModif.setText(HomeController.username);
         telephoneModif.setText(as.modifTelephone(HomeController.idU));
         passwordModif.setText(as.modifPassword(HomeController.idU));
        // Dphoto.setImage(u.getPhoto());
        //Dphoto.setImage(new Image(getClass().getResource("/fxml/Default.jpg").toString()));
         //Image image = new Image("/Images/Default.jpg");
         Dphoto.setImage(u.getPhoto());
    }    

   @FXML
    private void Modifier(ActionEvent event) {
       AdminService as= new AdminService();
       int id =as.modifId(HomeController.username);
//old
        String oldnom;
        String oldPrenom;
        String oldemail;
        String oldusername;
        String oldpassword;
        String oldtelephone;
        
        
//new
        String newnom;
        String newPrenom;
        String newemail;
        String newusername;
        String newpassword;
        String newtelephone;
        
//affectation O
        oldnom=as.modifNom(HomeController.idU);
        oldPrenom=as.modifPrenom(HomeController.idU);
        oldemail=as.modifEmail(HomeController.idU);
        oldusername=HomeController.username;
        oldpassword=as.modifPassword(HomeController.idU);
        oldtelephone=as.modifTelephone(HomeController.idU);
        
        
        
        
//affectation de N
        newnom=nomModif.getText();
        newPrenom=PrenomModif.getText();
        newemail=emailModif.getText();
        newtelephone=telephoneModif.getText();
        newusername=usernameModif.getText();
        newpassword=passwordModif.getText();
        
         //public User(String nom, String prenom, String telephone, String email, String password, String roles, String username, String usernameCanonical, String emailCanonical,id)
        
        User old =new User(oldnom, oldPrenom, oldtelephone, oldemail, oldpassword, "Expert", oldusername, oldusername, oldemail,id);
        User apres=new User(newnom, newPrenom, newtelephone, newemail, newpassword, "Expert", newusername, newusername, newemail, id);
        as.modfierCompte(apres, old); 
        
        
        
        //System.out.println(as.modifNom(HomeController.username));
    }

    @FXML
    private void retour(ActionEvent event) {
         Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/Jardinier.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene frontPageScene= new Scene(frontPage); 
        //to get the stage
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(frontPageScene);
        window.show();
    }

    @FXML
    private void Deconnexion(ActionEvent event) {
         Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/home.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene frontPageScene= new Scene(frontPage); 
        //to get the stage
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(frontPageScene);
        window.show();
    }
    
    
}
