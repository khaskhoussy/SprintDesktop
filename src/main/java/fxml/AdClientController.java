/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import Service.AdminService;
import entits.User;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aisce
 */
public class AdClientController implements Initializable {

    @FXML
    private TableColumn<User, Integer> id;
    @FXML
    private TableColumn<User, String> nom;
    @FXML
    private TableColumn<User, String> prenom;
    @FXML
    private TableColumn<User, String> email;
    @FXML
    private TableColumn<User, String> telephone;
    @FXML
    private TableColumn<User, String> username;
    @FXML
    private TableColumn<User, String> password;
    @FXML
    private TableView<User> Table;
     @FXML private ChoiceBox ChoiceBox;
    @FXML
    private TextField rechercheBar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         AdminService as = new AdminService();
        ArrayList<User> arrayList = (ArrayList<User>)as.afficherClient();
        ObservableList obs = FXCollections.observableArrayList(arrayList);
        Table.setItems(obs);
        id.setCellValueFactory(new PropertyValueFactory<User,Integer>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<User,String>("Nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<User,String>("Prenom"));
        email.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
        telephone.setCellValueFactory(new PropertyValueFactory<User,String>("telephone"));
        username.setCellValueFactory(new PropertyValueFactory<User,String>("username"));
        password.setCellValueFactory(new PropertyValueFactory<User,String>("password"));
        
         ChoiceBox.getItems().addAll("nom","prenom","email","telephone");
    }    

    @FXML
    private void SupprimerAction(ActionEvent event) {
         ObservableList<User> selectedRows, allCategorie ;
        allCategorie =Table.getItems();
        selectedRows = Table.getSelectionModel().getSelectedItems();
         AdminService as = new AdminService();
        for (User user : selectedRows){
            allCategorie.remove(user);
            as.supprimerJardinier(user);
            
        }
    }

    @FXML
    private void retour(ActionEvent event) {
        Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/GestionDesStaff.fxml"));
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
    private void deconnexion(ActionEvent event) {
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
    private void choiceBoxButton(ActionEvent event) {
        AdminService as =new AdminService();
        //System.out.println(ChoiceBox.getValue().toString());
        if (ChoiceBox.getValue().toString().equals("nom"))
        {
            String nomr ;
            nomr =rechercheBar.getText();
              ArrayList<User> arrayList = (ArrayList<User>)as.chercherNom(nomr);
        ObservableList obs = FXCollections.observableArrayList(arrayList);
        Table.setItems(obs);
        id.setCellValueFactory(new PropertyValueFactory<User,Integer>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<User,String>("Nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<User,String>("Prenom"));
        email.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
        telephone.setCellValueFactory(new PropertyValueFactory<User,String>("telephone"));
        username.setCellValueFactory(new PropertyValueFactory<User,String>("username"));
        password.setCellValueFactory(new PropertyValueFactory<User,String>("password"));
            System.out.println("what are you seraching is "+as.chercherNom(nomr));
            
        }
        else if (ChoiceBox.getValue().toString().equals("prenom"))
        {
            String nomr ;
            nomr =rechercheBar.getText();
              ArrayList<User> arrayList = (ArrayList<User>)as.chercherPrenom(nomr);
        ObservableList obs = FXCollections.observableArrayList(arrayList);
        Table.setItems(obs);
        id.setCellValueFactory(new PropertyValueFactory<User,Integer>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<User,String>("Nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<User,String>("Prenom"));
        email.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
        telephone.setCellValueFactory(new PropertyValueFactory<User,String>("telephone"));
        username.setCellValueFactory(new PropertyValueFactory<User,String>("username"));
        password.setCellValueFactory(new PropertyValueFactory<User,String>("password"));
            System.out.println("what are you seraching is "+as.chercherPrenom(nomr));
            
        }
        else if (ChoiceBox.getValue().toString().equals("email"))
        {
            String nomr ;
            nomr =rechercheBar.getText();
              ArrayList<User> arrayList = (ArrayList<User>)as.chercheremail(nomr);
        ObservableList obs = FXCollections.observableArrayList(arrayList);
        Table.setItems(obs);
        id.setCellValueFactory(new PropertyValueFactory<User,Integer>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<User,String>("Nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<User,String>("Prenom"));
        email.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
        telephone.setCellValueFactory(new PropertyValueFactory<User,String>("telephone"));
        username.setCellValueFactory(new PropertyValueFactory<User,String>("username"));
        password.setCellValueFactory(new PropertyValueFactory<User,String>("password"));
            System.out.println("what are you seraching is "+as.chercheremail(nomr));
            
        }
        else if (ChoiceBox.getValue().toString().equals("telephone"))
        {
            String nomr ;
            nomr =rechercheBar.getText();
              ArrayList<User> arrayList = (ArrayList<User>)as.cherchertelephone(nomr);
        ObservableList obs = FXCollections.observableArrayList(arrayList);
        Table.setItems(obs);
        id.setCellValueFactory(new PropertyValueFactory<User,Integer>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<User,String>("Nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<User,String>("Prenom"));
        email.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
        telephone.setCellValueFactory(new PropertyValueFactory<User,String>("telephone"));
        username.setCellValueFactory(new PropertyValueFactory<User,String>("username"));
        password.setCellValueFactory(new PropertyValueFactory<User,String>("password"));
            System.out.println("what are you seraching is "+as.cherchertelephone(nomr));
            
        }
        
    }

    @FXML
    private void refrech(ActionEvent event) {
        Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/AdClient.fxml"));
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
