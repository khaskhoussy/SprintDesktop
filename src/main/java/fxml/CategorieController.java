/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import entits.Categorie;
import entits.Produit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;
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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import Service.CategorieService;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class CategorieController implements Initializable {

   @FXML
    private TableView<Categorie> table;
    @FXML
    private TableColumn<Categorie, Integer> id;
    @FXML
    private TableColumn<Categorie, String> nom;
    @FXML
    private TableColumn<Categorie, String> description;
  
    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtdesc;
    @FXML
    private Button Ajout;
    @FXML
    private Button edit;
    @FXML
    private Button delete;
   // public static Observable<Categorie> obs;

    /**
     * Initializes the controller class.
     */
    @FXML
    private void retour(ActionEvent event) {
        Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/Expert.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene frontPageScene= new Scene(frontPage); 
        //to get the stage
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(frontPageScene);
        window.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        table.setEditable(true);
        description.setEditable(true);
         CategorieService c = new CategorieService();

        ArrayList<Categorie> arrayList = (ArrayList<Categorie>) c.affichercategories();
        ObservableList obs = FXCollections.observableArrayList(arrayList);
        table.setItems(obs);
        id.setCellValueFactory(new PropertyValueFactory<Categorie,Integer>("id"));
        
        nom.setCellValueFactory(new PropertyValueFactory<Categorie,String>("nomCat"));
          nom.setCellFactory(TextFieldTableCell.forTableColumn());
        nom.setOnEditCommit(event->{
            String newValue = event.getNewValue().equals("") ? event.getOldValue():
                event.getNewValue();
            ((Categorie) event.getTableView().getItems()
                .get(event.getTablePosition().getRow())).setNomCat(newValue);
            table.refresh();
        });
          id.setCellValueFactory(new PropertyValueFactory<Categorie,Integer>("id"));
        description.setCellValueFactory(new PropertyValueFactory<Categorie,String>("descriptionCat"));
          
        
        description.setCellFactory(TextFieldTableCell.forTableColumn());
        description.setOnEditCommit(event->{
            String newValue = event.getNewValue().equals("") ? event.getOldValue():
                event.getNewValue();
            ((Categorie) event.getTableView().getItems()
                .get(event.getTablePosition().getRow())).setDescriptionCat(newValue);
            table.refresh();
        });
        
      table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        // TODO
    }    

   /* @FXML
    private void AffAction(ActionEvent event) throws SQLException {
         CategorieService c = new CategorieService();

        ArrayList<Categorie> arrayList = (ArrayList<Categorie>) c.affichercategories();
        ObservableList obs = FXCollections.observableArrayList(arrayList);
        table.setItems(obs);
        id.setCellValueFactory(new PropertyValueFactory<Categorie,Integer>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<Categorie,String>("nomCat"));
        description.setCellValueFactory(new PropertyValueFactory<Categorie,String>("descriptionCat"));
        
       
       

       

    }*/

    @FXML
    private void AjouterAction(ActionEvent event) {
            
         Categorie c = new Categorie(txtnom.getText(),txtdesc.getText());
         CategorieService cs = new CategorieService();
         cs.creerCategorie(c);
       

        ArrayList<Categorie> arrayList = (ArrayList<Categorie>) cs.affichercategories();
        ObservableList obs = FXCollections.observableArrayList(arrayList);
        table.setItems(obs);
        id.setCellValueFactory(new PropertyValueFactory<Categorie,Integer>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<Categorie,String>("nomCat"));
        description.setCellValueFactory(new PropertyValueFactory<Categorie,String>("descriptionCat"));
      table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
         
        
    }

    @FXML
    private void ModifierAction(ActionEvent event) throws SQLException {
        CategorieService c1=new CategorieService();
      Categorie c = table.getSelectionModel().getSelectedItem();
       c1.modifierCategorie(c);
       
       

        
    
        
    }

    @FXML
    private void SupprimerAction(ActionEvent event) {

        Categorie c = table.getSelectionModel().getSelectedItem();
        CategorieService cs =new CategorieService();
        int i = cs.checkCategorie(c);
        if(i != 0) {
            Alert alert=new Alert(AlertType.INFORMATION);
            alert.setTitle("Catégorie n'est pas vide");
            alert.setHeaderText("Information");
            alert.setContentText("Categorie n'est pas vide");
            alert.showAndWait();
            //System.out.println("Catégorie n'est pas vide");
            return;
        }
        cs.supprimerCategorie(c);
         Alert alert=new Alert(AlertType.INFORMATION);
            alert.setTitle("Catégorie était supprimé");
            alert.setHeaderText("Information");
            alert.setContentText("Catégorie était supprimé");
            alert.showAndWait();
            

        ArrayList<Categorie> arrayList = (ArrayList<Categorie>) cs.affichercategories();
        ObservableList obs = FXCollections.observableArrayList(arrayList);
        table.setItems(obs);
        id.setCellValueFactory(new PropertyValueFactory<Categorie,Integer>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<Categorie,String>("nomCat"));
        description.setCellValueFactory(new PropertyValueFactory<Categorie,String>("descriptionCat"));
      table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        //System.out.println("Catégorie était supprimé");
    }

    @FXML
    private void validerAction(ActionEvent event) {
        
        if (JOptionPane.showConfirmDialog(null, "confirmer la modification","modification",
                JOptionPane.YES_NO_OPTION)==JOptionPane.OK_OPTION){
            CategorieService c=new CategorieService();
            Categorie c1= table.getSelectionModel().getSelectedItem();
            if(c1 !=null){
                c.modifierCategorie(c1);
            }
            
        }
    }

    @FXML
    private void next(ActionEvent event) {
        Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/Gestionproduit.fxml"));
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
