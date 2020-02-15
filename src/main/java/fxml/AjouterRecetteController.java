/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import entits.Recette;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import Service.ServRecette;
import fxml.HomeController;

/**
 * FXML Controller class
 *
 * @author Dreamer
 */
public class AjouterRecetteController implements Initializable {

    @FXML
    private Button retour;
    @FXML
    private TextField desc;
    private TextField image;
    @FXML
    private Button valider;
    @FXML
    private TextField nomrecette;
    @FXML
    private TextField type;
    @FXML
    private ImageView Dphoto;
    FileChooser filechooser;
    private File filePath; 
   private String path;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    



    @FXML
    private void RetourRecetteAction(ActionEvent event) {
         Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/Gestionrecette.fxml"));
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
    private void choisir(ActionEvent event) {
        String fileSeparator = System.getProperty("file.separator");
        Recette u =new Recette();
         ServRecette d = new ServRecette(); 
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        filechooser=new FileChooser();
        filechooser.setTitle("open Image");
        //this.filePath=filechooser.showOpenDialog(stage);
        //setUp default directory
        filechooser.setInitialDirectory(new File("c:/"));
        this.filePath=filechooser.showOpenDialog(stage);
        //to change the photo
        try {
            String cPath = "C:/images/";
           System.out.println(filePath.toPath());
           cPath = cPath+filePath.getName();
           System.out.println(cPath);
           File cFile = new File(cPath);
            cFile.createNewFile();
            FileInputStream fis = new FileInputStream(filePath);
            FileOutputStream fos = new FileOutputStream(cFile);
            
            byte[] buffer = new byte[1024];
            int length;

            while ((length = fis.read(buffer)) > 0) {

                fos.write(buffer, 0, length);
            }
            
            BufferedImage bufferedImage=ImageIO.read(cFile);
            Image image=new Image(cFile.toURI().toString());
           /*Image image =SwingFXUtils.toFXImage(bufferedImage, null);*/
           path = cFile.getName();
            Dphoto.setImage(image);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AjouterRecetteController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AjouterRecetteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void validAjoutRecette(ActionEvent event) {
           
       Recette r;
       int status=0;
        r = new  Recette(nomrecette.getText(),desc.getText(), type.getText(),path);
        ServRecette d = new ServRecette(); 
        status=d.AjouterRec(r);
         if(status>0){
              
              Alert alert =new Alert(Alert.AlertType.INFORMATION);
              alert.setTitle("Recette!");
              alert.setHeaderText("Information");
              alert.setContentText("recette bien Ajoutée!");
              alert.showAndWait();
          }
          else 
          {
               Alert alert =new Alert(Alert.AlertType.ERROR);
              alert.setTitle("texte(s) manquants!");
              alert.setHeaderText("Information");
              alert.setContentText("texte(s) manquants!!!");
              alert.showAndWait();
              
          }
        
    }

  
    
}
