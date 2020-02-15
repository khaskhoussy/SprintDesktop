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
import javafx.scene.control.Alert;
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
public class AdPaysagisteController implements Initializable {

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
    @FXML
    private TextField nomj;
    @FXML
    private TextField prenomj;
    @FXML
    private TextField emailj;
    @FXML
    private TextField telephonej;
    @FXML
    private TextField usernamej;
    @FXML
    private TextField passwordj;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AdminService as = new AdminService();
        ArrayList<User> arrayList = (ArrayList<User>)as.afficherPaysagist();
        ObservableList obs = FXCollections.observableArrayList(arrayList);
        Table.setItems(obs);
        id.setCellValueFactory(new PropertyValueFactory<User,Integer>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<User,String>("Nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<User,String>("Prenom"));
        email.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
        telephone.setCellValueFactory(new PropertyValueFactory<User,String>("telephone"));
        username.setCellValueFactory(new PropertyValueFactory<User,String>("username"));
        password.setCellValueFactory(new PropertyValueFactory<User,String>("password"));
        System.out.println("this is wht you wait for"+as.afficherJardinier());
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
    private void ajouterPaysagiste(ActionEvent event) {
         String pnom;
        String pprenom;
        String pemail;
        String ppassword;
        String pusername;
        String ptelephone ;
                pnom = nomj.getText();
                pprenom = prenomj.getText();
                pemail = emailj.getText();
                ppassword = passwordj.getText();
                pusername = usernamej.getText();
                ptelephone= telephonej.getText();
                User c =new User(pnom, pprenom, ptelephone, pemail, ppassword, pusername);
         AdminService cs = new AdminService();
         int status = 0 ;
                 status = cs.ajouterPaysagist(c);
        cs.ajouterPaysagist(c);
          //checkLabel.setText(""+status);
          if(status>0){
                nomj.setText("");
                prenomj.setText("");
                emailj.setText("");
                passwordj.setText("");
                usernamej.setText("");
                telephonej.setText("");
              Alert alert =new Alert(Alert.AlertType.INFORMATION);
              alert.setTitle("vous avez creer compte!");
              alert.setHeaderText("Information");
              alert.setContentText("Compte bien Ajouter!");
              alert.showAndWait();
              ArrayList<User> arrayList = (ArrayList<User>)cs.afficherPaysagist();
        ObservableList obs = FXCollections.observableArrayList(arrayList);
        Table.setItems(obs);
        id.setCellValueFactory(new PropertyValueFactory<User,Integer>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<User,String>("Nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<User,String>("Prenom"));
        email.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
        telephone.setCellValueFactory(new PropertyValueFactory<User,String>("telephone"));
        username.setCellValueFactory(new PropertyValueFactory<User,String>("username"));
        password.setCellValueFactory(new PropertyValueFactory<User,String>("password"));
          }
          else 
          {
               Alert alert =new Alert(Alert.AlertType.ERROR);
              alert.setTitle("vous avez creer compte!");
              alert.setHeaderText("Information");
              alert.setContentText("username ou email dejat exicte !!");
              alert.showAndWait();
              
          }
         
        
    }

    private void refrech(ActionEvent event) {
         Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/AdPaysagiste.fxml"));
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
    private void detail(ActionEvent event) {
        Parent tableViewParent = null; 
        FXMLLoader Loader= new FXMLLoader();
        try { 
              
            Loader.setLocation(getClass().getResource("/fxml/DetaillEmAdPa.fxml"));
             tableViewParent = Loader.load();
            
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene frontPageScene= new Scene(tableViewParent); 
        //this is where the controller can call a methode
        DetaillEmAdPaController controller=Loader.getController();
        controller.setUser(Table.getSelectionModel().getSelectedItem());
        controller.show();
        //controller.initData(table.getSelectionModel().getSelectedItem());
        //to get the stage
       // User tUser = table.getSelectionModel().getSelectedItem();
        //NotreServiceController.selecteduser= new User();
        
       // System.out.println(tUser.getNom());
        //NotreServiceController.selecteduser.setNom("fefefeff");
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(frontPageScene);
        window.show();
        
    }
    
}
