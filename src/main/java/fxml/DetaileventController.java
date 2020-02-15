/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import Service.AdminService;
import entits.Evenement;
import entits.Participation;
import fxml.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import Service.Eventservice;
import fxml.EvenementController;
import fxml.HomeController;

/**
 * FXML Controller class
 *
 * @author FARAH
 */
public class DetaileventController implements Initializable {

    @FXML
    private Button retour;
    @FXML
    private Label nom;
    @FXML
    private Label price;
    @FXML
    private Label nbr;
    @FXML
    private Label desc;
    @FXML
    private Button ngo;
    @FXML
    private Button go;
    @FXML
    private Label priceoff;

    /**
     * Initializes the controller class.
     */
    
    private AdminService adminService = new AdminService();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
             desc.setText(EvenementController.eventSelectionne.getDescription());
      nom.setText(EvenementController.eventSelectionne.getNom());
      desc.setText(EvenementController.eventSelectionne.getDescription());
      price.setText(String.valueOf(EvenementController.eventSelectionne.getEveprix()));
        priceoff.setText(String.valueOf(EvenementController.eventSelectionne.getPrix_offre()));
     // price.setText(m.getEveprix());
          String a = Integer.toString(EvenementController.eventSelectionne.getNbrParticipants());
      nbr.setText(a+" Participants ");
      
      go.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            Participation p = new Participation(EvenementController.eventSelectionne.getId(), 
                       adminService.modifId(HomeController.username));
               Eventservice ms = new Eventservice() ; 
                try {
                    ms.participer(p);
                } catch (SQLException ex) {
                    Logger.getLogger(DetaileventController.class.getName()).log(Level.SEVERE, null, ex);
                }
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("Information Dialog");
                   alert.setContentText("Participation confirmée");
                   alert.showAndWait();
            }
        });

      ngo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               Participation p = new Participation(EvenementController.eventSelectionne.getId(), 
                       adminService.modifId(HomeController.username));
               Eventservice ms = new Eventservice() ; 
                try {
                    ms.annulerparticiper(p);
                   
                } catch (SQLException ex) {
                    Logger.getLogger(DetaileventController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("Information Dialog");
                   alert.setContentText("Annulation confirmée");
                    alert.showAndWait();
            }
        });
    }    
    

    @FXML
    private void retourAction(ActionEvent event) {
        
        
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
    private void goAction(ActionEvent event) {
    }
}
