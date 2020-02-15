/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import Service.AdminService;
import Service.ServiceTreavaille;
import entits.ListeTravail;
import entits.User;
import java.io.File;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aisce
 */
public class DetaillEmAdPaController implements Initializable {

    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label email;
    @FXML
    private Label telephone;
    @FXML
    private Label role;
    @FXML
    private TableView<ListeTravail> table;
    @FXML
    private TableColumn<ListeTravail, Integer> idem;
    @FXML
    private TableColumn<ListeTravail, String> dateem;
    @FXML
    private TableColumn<ListeTravail, String> descriptionem;
    @FXML
    private TableColumn<ListeTravail, String> imageem;
    @FXML
    private Label idLabele;
    private User user ;
    @FXML
    private Label descriptionaf;
    @FXML
    private ImageView imageaff;
   private File filePath;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void show(){
        nom.setText(user.getNom());
        prenom.setText(user.getPrenom());
        email.setText(user.getEmail());
        role.setText(user.getRoles());
        telephone.setText(user.getTelephone());
        System.out.println(user.getPrenom());
        AdminService ads=new AdminService();
      ServiceTreavaille as = new ServiceTreavaille();
        ArrayList<ListeTravail> arrayList = (ArrayList<ListeTravail>)as.afficherTravail(user.getId());
        ObservableList obs = FXCollections.observableArrayList(arrayList);
        table.setItems(obs);
        idem.setCellValueFactory(new PropertyValueFactory<ListeTravail,Integer>("id"));
        dateem.setCellValueFactory(new PropertyValueFactory<ListeTravail,String>("dateTravail"));
        descriptionem.setCellValueFactory(new PropertyValueFactory<ListeTravail,String>("descriptionTravail"));
        imageem.setCellValueFactory(new PropertyValueFactory<ListeTravail,String>("image"));
        System.out.println("you are waiting for "+as.afficherTravaille(user.getId()));
    }

    @FXML
    private void retour(ActionEvent event) {
        if (HomeController.who.equals("user")) 
        {Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/NotreService.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene frontPageScene= new Scene(frontPage); 
        //to get the stage
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(frontPageScene);
        window.show();
        }
        if (HomeController.who.equals("admin")) 
        {Parent frontPage = null; 
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
    }
   /* public void initData(User u){
       User a=new User();
        selectedperson=u;
        System.out.println("this is what u waiting for"+u.getPrenom());
       
}*/

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

     @FXML
    private void selected(MouseEvent event) {
        
        ListeTravail listeTravail = table.getSelectionModel().getSelectedItem();
        descriptionaf.setText(listeTravail.getDescriptionTravail());
        String path;
        path=listeTravail.getImage();
        filePath = new File(path);
         Image image=new Image(filePath.toURI().toString());
         imageaff.setImage(image);
         
    }
    
}
