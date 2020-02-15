/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import entits.Produit;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class DetailproduitController implements Initializable {

   @FXML
    private ImageView imageview;
    @FXML
    private Label nomprod;
    @FXML
    private Label Prixoffre;
    @FXML
    private Label Prix;
    @FXML
    private Label description;
    @FXML
    private Button ajouteraupanier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nomprod.setText(BoutiqueController.produitSelectionne.getNomProd());
        description.setText(BoutiqueController.produitSelectionne.getProdDescription());
        Prix.setText(String.valueOf(BoutiqueController.produitSelectionne.getPrixProd()));
        Prixoffre.setText(BoutiqueController.produitSelectionne.getPrixOffre().toString());
         
       
   }

    @FXML
    private void retour(ActionEvent event) {
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
    
}
