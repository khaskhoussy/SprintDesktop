/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import Service.AdminService;
import entits.User;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author aisce
 */
public class ListeEmployerController implements Initializable {

    @FXML
    private TableView<User> Table;
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
    private TableColumn<User, String> role;
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         AdminService as = new AdminService();
        ArrayList<User> arrayList = (ArrayList<User>)as.afficherStaff();
        ObservableList obs = FXCollections.observableArrayList(arrayList);
        Table.setItems(obs);
        id.setCellValueFactory(new PropertyValueFactory<User,Integer>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<User,String>("Nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<User,String>("Prenom"));
        email.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
        telephone.setCellValueFactory(new PropertyValueFactory<User,String>("telephone"));
        role.setCellValueFactory(new PropertyValueFactory<User,String>("telephone"));

        System.out.println("this is wht you wait for"+as.afficherJardinier());
    }    

    @FXML
    private void Type(ActionEvent event) {
        
    }

    @FXML
    private void retour(ActionEvent event) {
    }

    @FXML
    private void chercher(ActionEvent event) {
    }

    @FXML
    private void deconnexion(ActionEvent event) {
    }
    
}
