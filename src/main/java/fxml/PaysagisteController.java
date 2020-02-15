/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import Service.AdminService;
import entits.LigneService;
import entits.User;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class PaysagisteController implements Initializable {

    @FXML
    private Button valider;
     @FXML
    private TableView<LigneService> table;
    @FXML
    private TableColumn<LigneService, String> nomClient;
    @FXML
    private TableColumn<LigneService, String> prenomClient;
    @FXML
    private TableColumn<LigneService, String> telClient;
    @FXML
    private TableColumn<LigneService, String> dateDebutS;
    @FXML
    private TableColumn<LigneService, String> dateFinS;

    private AdminService adminService;
    private List<LigneService> list ;
    private DateFormat dateFormat ;

    /**
     * Initializes the controller class.
     */
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        adminService = new AdminService();
        list = adminService.getLigneServicesByEmployer(new User(HomeController.idU));
        table.setItems(FXCollections.observableList(list));
        
        nomClient.setCellValueFactory((TableColumn.CellDataFeatures<LigneService, String> param) -> {
            return new SimpleStringProperty(param.getValue().getIdCommande().getIdUser().getNom());
        });
        prenomClient.setCellValueFactory((TableColumn.CellDataFeatures<LigneService, String> param) -> {
            return new SimpleStringProperty(param.getValue().getIdCommande().getIdUser().getPrenom());
        });
        telClient.setCellValueFactory((TableColumn.CellDataFeatures<LigneService, String> param) -> {
            return new SimpleStringProperty(param.getValue().getIdCommande().getIdUser().getTelephone());
        });
        dateDebutS.setCellValueFactory((TableColumn.CellDataFeatures<LigneService, String> param) -> {
            return new SimpleStringProperty(dateFormat.format(param.getValue().getDateDebut()));
        });
        dateFinS.setCellValueFactory((TableColumn.CellDataFeatures<LigneService, String> param) -> {
            return new SimpleStringProperty(dateFormat.format(param.getValue().getDateFin()));
        });
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
    private void GestionCompte(ActionEvent event) {
        Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/ComptePaysagiste.fxml"));
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
    private void ajouterTravaille(ActionEvent event) {
        Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/ListTravaillePay.fxml"));
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
    private void AjouterPackAction(ActionEvent event) {
         Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/GestionPack.fxml"));
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
