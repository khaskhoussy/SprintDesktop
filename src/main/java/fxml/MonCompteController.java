/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import Service.AdminService;
import entits.User;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class MonCompteController implements Initializable {
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
     private FileChooser filechooser;
    private File filePath;
    AdminService as=new AdminService();
   private String path;
   private String nomImage;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        path=as.modifImg(HomeController.username);
         File p = new File(path);     
         Image image=new Image(p.toURI().toString());
        // Dphoto.setImage(image);
          Dphoto.setImage(new Image("http://localhost/sprintWeb/web/uploads/Users/"+as.modifImg(HomeController.username)));
        
         
    }    

    @FXML
    private void deconnectee(ActionEvent event) {
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

    @FXML
    private void boutique(ActionEvent event) {
         Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/Boutique.fxml"));
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
    private void notreService(ActionEvent event) {
        Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/NotreService.fxml"));
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
    private void mesCommandes(ActionEvent event) {
         Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/MesCommandes.fxml"));
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
    private void notreOffre(ActionEvent event) {
         Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/NosPacks.fxml"));
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
    private void monPanier(ActionEvent event) {
         Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/MonPanier.fxml"));
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
    private void evenment(ActionEvent event) {
         Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/Evenement.fxml"));
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
    private void forom(ActionEvent event) {
         Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/Forom.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene frontPageScene= new Scene(frontPage); 
        //to get the stage
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(frontPageScene);
        window.show();
    }

    /*@FXML
    private void monCompte(ActionEvent event) {
        Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/MonCompte.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene frontPageScene= new Scene(frontPage); 
        //to get the stage
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(frontPageScene);
        window.show();
    }*/

    @FXML
    private void monCompte(ActionEvent event) {
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
        
        User old =new User(oldnom, oldPrenom, oldtelephone, oldemail, oldpassword, "Client", oldusername, oldusername, oldemail,id);
        User apres=new User(newnom, newPrenom, newtelephone, newemail, newpassword, "Client", newusername, newusername, newemail, id);
        as.modfierCompte(apres, old); 
        
        
        
        //System.out.println(as.modifNom(HomeController.username));
    }
  @FXML public void  ChangeButton(ActionEvent event) throws FileNotFoundException, IOException
    {  User u =new User();
        AdminService as= new AdminService();
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        filechooser=new FileChooser();
        filechooser.setTitle("open Image");
        //this.filePath=filechooser.showOpenDialog(stage);
        //setUp default directory
        String userdirectoryS =System.getProperty("user.home");
        File  userDirectory = new File(userdirectoryS);
        if(!userDirectory.canRead())
        
            userDirectory=new File("c:/");
            filechooser.setInitialDirectory(userDirectory);
            this.filePath=filechooser.showOpenDialog(stage);
             String fis3 = new String(filePath.getName());
             nomImage = fis3;
            System.out.println(fis3);
            try{
                            FileInputStream fis33 = new FileInputStream(this.filePath);
            } 
            catch (FileNotFoundException ex) {
                    Logger.getLogger(MonCompteController.class.getName()).log(Level.SEVERE, null, ex);
                }
            try
            {
                FileInputStream fis33 = new FileInputStream(this.filePath);
                InputStream is = fis33;
                String url2="C:\\wamp64\\www\\SprintWeb\\web\\uploads\\Users\\"+fis3;
                OutputStream os = new FileOutputStream( new File(url2));
                byte[] content = new byte[2048];
                      int size = 0;
                     while((size = is.read(content)) != -1){
                          os.write(content, 0, size);
            }
            }
           catch (MalformedURLException ex) {
                          
                            Logger.getLogger(CompteJarController.class.getName()).log(Level.SEVERE, null, ex);
                        }
        //to change the photo
       try{
           BufferedImage bufferedImage=ImageIO.read(filePath);
           Image image=new Image(filePath.toURI().toString());
           path=filePath.toPath().toString();
           System.out.println(filePath.toURI().toString());
           /*Image image =SwingFXUtils.toFXImage(bufferedImage, null);*/
          // u.setImg(path);
             u.setImg(nomImage);
           AdminService ase=new AdminService();
           ase.ajouterimg(u, HomeController.username);          
           Dphoto.setImage(image);
       }
       catch(IOException e)
        
       {
           System.err.println(e.getMessage());
       }
    }
  @FXML
    private void Recette(ActionEvent event) {
         Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/Recette.fxml"));
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
    private void gestedumois(ActionEvent event) {
        Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/GesteFront.fxml"));
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
    private void reclamation(ActionEvent event) {
        Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/Reclamation.fxml"));
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
