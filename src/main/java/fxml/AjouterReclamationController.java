/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import entits.Commande;
import entits.Recette;
import entits.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import Service.ServReclamation;
import fxml.HomeController;

/**
 * FXML Controller class
 *
 * @author Dreamer
 */
public class AjouterReclamationController implements Initializable {

    @FXML
    private Button retour;
    @FXML
    private TextField objet;
    @FXML
    private TextField contenue;
    @FXML
    private DatePicker date_recla;
    @FXML
    private TextField IdUser;
    @FXML
    private TextField etat_recla;
    @FXML
    private Button valider;
    @FXML
    private ComboBox<Commande> choiidcombobox;
    private ServReclamation e;
     private ObservableList<Commande> obs =FXCollections.observableArrayList();
    private ServReclamation servReclamation = new ServReclamation();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        choiidcombobox.setItems(FXCollections.observableArrayList(servReclamation.GetIdReclamation(HomeController.idU)));
    }    

    @FXML
    private void RetourReclamationAction(ActionEvent event) {
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

    @FXML
    private void validAjoutReclamation(ActionEvent event) {
         Reclamation g;
         int status=0;
        LocalDate localDate = date_recla.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        g = new  Reclamation(objet.getText(), contenue.getText(), date,HomeController.idU,choiidcombobox.getValue().getId(),etat_recla.getText());
        ServReclamation d = new ServReclamation(); 
        status=d.AjouterReclamation(g);
        if(status>0){
              
              Alert alert =new Alert(AlertType.INFORMATION);
              alert.setTitle("vous avez creer compte!");
              alert.setHeaderText("Information");
              alert.setContentText("reclamation bien Ajoute!");
              alert.showAndWait();
          }
          else 
          {
               Alert alert =new Alert(AlertType.ERROR);
              alert.setTitle("texte(s) manquants!");
              alert.setHeaderText("Information");
              alert.setContentText("texte(s) manquants!!!");
              alert.showAndWait();
              
          }
        
    }



    @FXML
    private void ChoixIdComboBoxAction(MouseEvent event) {
      

    }
    
}
