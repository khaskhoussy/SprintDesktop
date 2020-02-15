package fxml;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Service.AdminService;
import Service.ClientService;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import entits.Client;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;


/**
 * FXML Controller class
 *
 * @author DELL
 */
    public class HomeController implements Initializable {

        public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
            return matcher.find();
    }
    public static int idU;
    public static String who;
 public static String username;
 public   static String pageClient  = "/fxml/front.fxml" ;
 public   static String pageAdmin ="/fxml/Admin.fxml";
 public   static String pageExpert ="/fxml/Expert.fxml";
 public   static String pageJardinier ="/fxml/Jardinier.fxml" ;
 public   static String pagePaysagist ="/fxml/Paysagiste.fxml" ;
        public static final String ACCOUNT_SID ="AC0fa16824d8545f1267a7928180de1679";
     public static final String AUTH_TOKEN ="0182dd1bc482d5dca3c2e5afd8da4596";
        
         
    @FXML
    private Button con;
    
    @FXML
    private TextField Username;
    @FXML
    private TextField ajNom;
    @FXML
    private TextField ajPrenom;
    @FXML
    private TextField ajEmail;
    @FXML
    private TextField ajPassword;
    @FXML
    private TextField ajUsername;
    @FXML
    private Label checkLabel;
    @FXML
    private TextField ajTelephone;
    @FXML
    private PasswordField password;
    @FXML
    private TextField oldEmail;
    @FXML
    private Label EmailLabel;
    @FXML
    private Button envoieyButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Connection myConn0 = Singleton.getInstancesingleton()
            .getConnection();
        EmailLabel.setVisible(false);
        oldEmail.setVisible(false);
        envoieyButton.setVisible(false);
    }    
//creerCompte
    @FXML
    public void creerCompte()
    {
         if(ajNom.getText().equals(""))
         {
             checkLabel.setText("verfier votre nom !!");
         }
         if(ajPrenom.getText().equals(""))
         {
             checkLabel.setText("verfier votre prenom !!");
         }
         if( !validate(ajEmail.getText()))
         {
             checkLabel.setText("verfier votre email !!");
             return;
         }
         if(ajPassword.getText().equals(""))
         {
             checkLabel.setText("verfier votre password !!");
         }
         if(ajUsername.getText().equals(""))
         {
             checkLabel.setText("verfier votre username !!");
         }
          if(ajTelephone.getText().equals(""))
         {
             checkLabel.setText("verfier votre numero Telephone !!");
         }
        
        String usernameaj;
        String nom;
        String prenom;
        String email;
        String password;
        String telephone ;
        
                nom = ajNom.getText();
                prenom = ajPrenom.getText();
                email = ajEmail.getText();
                password = ajPassword.getText();
                usernameaj = ajUsername.getText();
                telephone=ajTelephone.getText();
                Client c =new Client(nom, prenom, telephone, email, password, usernameaj);
         ClientService cs = new ClientService();
        int status = cs.creerCompte(c);
          checkLabel.setText(""+status);
          if(status>0){
              ajNom.setText("");
                 ajPrenom.setText("");
                ajEmail.setText("");
                ajPassword.setText("");
                ajUsername.setText("");
                ajTelephone.setText("");
              Alert alert =new Alert(AlertType.INFORMATION);
              alert.setTitle("vous avez creer compte!");
              alert.setHeaderText("Information");
              alert.setContentText("Compte bien Ajouter!");
              alert.showAndWait();
          }
          else 
          {
               Alert alert =new Alert(AlertType.ERROR);
              alert.setTitle("vous avez creer compte!");
              alert.setHeaderText("Information");
              alert.setContentText("username ou email dejat exicte !!");
              alert.showAndWait();
              
          }
          
         
        
    }
    @FXML
    private void connecter(ActionEvent event) {
        //condition Jardinier
AdminService as = new AdminService();
        String teest;
        String passwordE;
            passwordE=this.password.getText();
           username= Username.getText();
        //System.out.println("you are waiting for"+as.chercherJardinier(username));
        System.out.println(as.chercherJardinier1(username));
   //as.chercherJardinier1(username).equals("Paysagist")
     teest=as.chercherJardinier1(username);
  
        
     String cPwd = as.chercherPassword(username) ;
     
     if (as.chercherJardinier1(username).contains("ADMIN")&& BCrypt.checkpw(passwordE,cPwd.substring(0,2)+"a"+cPwd.substring(3)))
         
                {   who="admin";
                    Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource(pageAdmin));
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene frontPageScene= new Scene(frontPage); 
        //to get the stage
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(frontPageScene);
        window.show();
                }

               if (as.chercherJardinier1(username).contains("PAYSAGISTE")&& BCrypt.checkpw(passwordE,cPwd.substring(0,2)+"a"+cPwd.substring(3)))
                {
                    idU=as.modifId(username);
                    Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource(pagePaysagist));
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene frontPageScene= new Scene(frontPage); 
        //to get the stage
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(frontPageScene);
        window.show();
                }
      if (as.chercherJardinier1(username).contains("EXPERT") && BCrypt.checkpw(passwordE,cPwd.substring(0,2)+"a"+cPwd.substring(3)))
                {
                    idU=as.modifId(username);
                    Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource(pageExpert));
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene frontPageScene= new Scene(frontPage); 
        //to get the stage
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(frontPageScene);
        window.show();
                }
        if (as.chercherJardinier1(username).contains("JARDINIER")&& BCrypt.checkpw(passwordE,cPwd.substring(0,2)+"a"+cPwd.substring(3)))
        {   idU=as.modifId(username);
        Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource(pageJardinier));
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene frontPageScene= new Scene(frontPage); 
        //to get the stage
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(frontPageScene);
        window.show();
        }
         if (as.chercherJardinier1(username).contains("CLIENT")&& BCrypt.checkpw(passwordE,cPwd.substring(0,2)+"a"+cPwd.substring(3))){
          idU=as.modifId(username);
          who="user";
              System.out.println();
            Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource(pageClient));
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene frontPageScene= new Scene(frontPage); 
        //to get the stage
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(frontPageScene);
        window.show();
        }

      
     
       /*
              Alert alert =new Alert(AlertType.ERROR);
              alert.setTitle("Connexion!");
              alert.setHeaderText("Information");
              alert.setContentText("Verfier password ou username!!");
              alert.showAndWait();
               checkLabel.setText("verfier votre numero Telephone !!");
        */
    }


    @FXML
    private void telTyped(KeyEvent event) {
        String text = event.getCharacter() ;
        if(text.equals("")) return ;
        if(text.charAt(text.length()-1) < '0' || text.charAt(text.length()-1) > '9' ) {
            event.consume();
        }
    }

    @FXML
    private void mdpoublier(MouseEvent event) {
        EmailLabel.setVisible(true);
        oldEmail.setVisible(true);
        envoieyButton.setVisible(true);
    }

    @FXML
    private void envoyermdp(ActionEvent event) {
        AdminService as=new AdminService();
        if(as.recupTel(oldEmail.getText())==null)
        {
               Alert alert =new Alert(AlertType.ERROR);
              alert.setTitle("vous avez creer compte!");
              alert.setHeaderText("Information");
              alert.setContentText("@email n'existe pas !!");
              alert.showAndWait();         
              return;
        }
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
       com.twilio.rest.api.v2010.account.Message messages = com.twilio.rest.api.v2010.account.Message.creator(new PhoneNumber("+216"+as.recupTel(oldEmail.getText())), new PhoneNumber("+15414257375"),as.recupMdp(oldEmail.getText())).create();
         Alert alert =new Alert(AlertType.INFORMATION);
              alert.setTitle("envoie Mail!");
              alert.setHeaderText("Information");
              alert.setContentText("message envoyer!!");
              alert.showAndWait();         
              return;
        
            
    }


    
}
 /*  switch(teest) {
         case "Paysagist" :
            { 
                    Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource(pagePaysagist));
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene frontPageScene= new Scene(frontPage); 
        //to get the stage
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(frontPageScene);
        window.show();
            }
            break;
         default :
                 {
                     Alert alert =new Alert(AlertType.ERROR);
              alert.setTitle("Connexion!");
              alert.setHeaderText("Information");
              alert.setContentText("Verfier password ou username!!");
              alert.showAndWait();
               checkLabel.setText("verfier votre numero Telephone !!");
                 }
     }*/