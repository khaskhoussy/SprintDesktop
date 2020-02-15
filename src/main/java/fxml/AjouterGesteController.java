/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import entits.Geste;
import fxml.HomeController;
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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import Service.ServGeste;
import fxml.HomeController;

/**
 * FXML Controller class
 *
 * @author Dreamer
 */
public class AjouterGesteController implements Initializable {

    @FXML
    private Button retour;
    @FXML
    private TextField nomgeste;
    @FXML
    private TextField moisgeste;
    @FXML
    private TextField descriptiongeste;
    @FXML
    private ImageView imagegeste;
    @FXML
    private Button valider;
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
    private void RetourGesteAction(ActionEvent event) {
         Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/GesteMois.fxml"));
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
    private void validAjoutGeste(ActionEvent event) {
        Geste g;
        g = new  Geste(nomgeste.getText(), moisgeste.getText(),descriptiongeste.getText(),path);
        ServGeste d = new ServGeste(); 
        d.AjouterGeste(g);
    }

    @FXML
    private void ParcourirImageGeste(ActionEvent event) {
          String fileSeparator = System.getProperty("file.separator");
        Geste u =new Geste();
         ServGeste d = new ServGeste(); 
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        filechooser=new FileChooser();
        filechooser.setTitle("open Image");
        //this.filePath=filechooser.showOpenDialog(stage);
        //setUp default directory
        filechooser.setInitialDirectory(new File("c:/"));
        this.filePath=filechooser.showOpenDialog(stage);
        //to change the photo
        try {
            String cPath = getClass().getResource("/fxml/").toURI().getPath();
           System.out.println(cPath);
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
            imagegeste.setImage(image);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AjouterRecetteController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | URISyntaxException ex) {
            Logger.getLogger(AjouterRecetteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}
}
