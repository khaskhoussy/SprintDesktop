/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import Service.AdminService;
import entits.Evenement;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import Service.Eventservice;
import fxml.HomeController;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author FARAH
 */
public class AjouteventController implements Initializable {
 @FXML
    private Button retour;
    @FXML
    private Button valider;
    @FXML
    private TextField nbr;
    @FXML
    private DatePicker datedeb;
    @FXML
    private DatePicker datefin;
    @FXML
    private TextField desc;
    @FXML
    private TextField lieu;
    @FXML
    private TextField prix;
    private TextField image;
    @FXML
    private TextField nom;
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
        // TODO
    }    

    @FXML
    private void backAction(ActionEvent event) {
          Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/GestionEvenAdm.fxml"));
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
    private void ajoutAction(ActionEvent event) {

        
           
       Evenement e;
        e = new  Evenement (datedeb.getValue().toString(),datefin.getValue().toString(), desc.getText(),lieu.getText(), Integer.parseInt(nbr.getText()),Integer.parseInt(prix.getText()), nomImage,nom.getText());
        Eventservice d = new Eventservice(); 
         d.creerevent(e);
        
        

        
    }

    @FXML
    private void ajouterphoto(ActionEvent event) throws FileNotFoundException, IOException {
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
                String url2="C:\\wamp64\\www\\SprintWeb\\web\\uploads\\evenment\\"+fis3;
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
    }
    
}
