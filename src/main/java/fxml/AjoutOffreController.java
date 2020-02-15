/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import entits.Categorie;
import entits.OffrePromotion;
import entits.Produit;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Service.OffreService;
import Service.ProduitService;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class AjoutOffreController implements Initializable {

    @FXML
    private DatePicker datedebut;
    @FXML
    private DatePicker datefin;
    @FXML
    private ComboBox<String> combobox;
    @FXML
    private TextField titre;
    @FXML
    private TextField pourcentage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       combobox.setItems(FXCollections.observableArrayList("Evenement","Produit"));
    }    

    @FXML
    private void valider(ActionEvent event) {
        String type = combobox.getValue();
       
       OffrePromotion p = new OffrePromotion(Double.parseDouble(pourcentage.getText()),datedebut.getValue().toString(),datefin.getValue().toString(),titre.getText(),type);
       
        OffreService ps=new OffreService();
        ps.creerOffre(p);
        
    }

    @FXML
    private void retour(ActionEvent event) {
        
         Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/GestionOffre.fxml"));
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
