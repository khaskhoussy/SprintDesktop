/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import entits.Recette;
import fxml.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import Service.ServRecette;
import fxml.HomeController;

/**
 * FXML Controller class
 *
 * @author Dreamer
 */
public class GestionrecetteController implements Initializable {

    @FXML
    private Button retour;
    @FXML
    private Button ajouter;
    @FXML
    private TableColumn<Recette, String> Nom;
    @FXML
    private TableColumn<Recette, String> Desc;
    @FXML
    private TableColumn<Recette, String> Type;
    @FXML
    private TableColumn<Recette, Integer> id;
    @FXML
    private TableView<Recette> table;
    @FXML
    private TableColumn<Recette, String> imagerecette;
    @FXML
    private Button supprimerrecette;
    @FXML
    private Button modifierrecette;
    @FXML
    private ComboBox<String> CHOIXTYPERECETTE;

    private ObservableList<Recette> obs ;
    private ServRecette e ;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          e = new ServRecette();
        
        table.setEditable(true);
        Nom.setEditable(true);
        Desc.setEditable(true);
        imagerecette.setEditable(true);
        ArrayList <Recette> arrayList =  e.AfficherRecette();
       obs = FXCollections.observableArrayList(arrayList);

        table.setItems(obs);
         
        id.setCellValueFactory(new PropertyValueFactory<Recette,Integer>("id"));
        
        Nom.setCellValueFactory(new PropertyValueFactory<Recette,String>("nomRec"));
        Nom.setCellFactory(TextFieldTableCell.forTableColumn());
        Nom.setOnEditCommit(event->{
            String newValue = event.getNewValue().equals("") ? event.getOldValue():
                event.getNewValue();
            ((Recette) event.getTableView().getItems()
                .get(event.getTablePosition().getRow())).setNomRec(newValue);
            table.refresh();
         });
        
        Desc.setCellValueFactory(new PropertyValueFactory<Recette,String>("recDescription"));
        Desc.setCellFactory(TextFieldTableCell.forTableColumn());
        Desc.setOnEditCommit(event->{
            String newValue = event.getNewValue().equals("") ? event.getOldValue():
                event.getNewValue();
            ((Recette) event.getTableView().getItems()
                .get(event.getTablePosition().getRow())).setRecDescription(newValue);
            table.refresh();
         });
        
       Type.setCellValueFactory(new PropertyValueFactory<Recette,String>("typeRec"));
       Type.setCellFactory(TextFieldTableCell.forTableColumn());
        Type.setOnEditCommit(event->{
            String newValue = event.getNewValue().equals("") ? event.getOldValue():
                event.getNewValue();
            ((Recette) event.getTableView().getItems()
                .get(event.getTablePosition().getRow())).setTypeRec(newValue);
            table.refresh();
         });
       
       imagerecette.setCellValueFactory(new PropertyValueFactory<Recette,String>("imagerecette")); 
       imagerecette.setCellFactory(TextFieldTableCell.forTableColumn());
        imagerecette.setOnEditCommit(event->{
            String newValue = event.getNewValue().equals("") ? event.getOldValue():
                event.getNewValue();
            ((Recette) event.getTableView().getItems()
                .get(event.getTablePosition().getRow())).setImagerecette(newValue);
            table.refresh();
         });
        // TODO
        
        //*****************************Choix de Recette selon le type*****************************
        CHOIXTYPERECETTE.getItems().addAll("ALL","Médicale","Esthétique","Bio");
        CHOIXTYPERECETTE.getSelectionModel().selectFirst();
        
        
    }    

    @FXML
    private void AjouterRecetteAction(ActionEvent event) {
         Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/AjouterRecette.fxml"));
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
    private void retourExpertAction(ActionEvent event) {
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

    @FXML
    private void supprimerRecetteAction(ActionEvent event) {
        int status=0;
         ObservableList<Recette> selectedRows, allCategorie ;
        allCategorie =table.getItems();
        selectedRows = table.getSelectionModel().getSelectedItems();
        ServRecette c=new ServRecette();
        for (Recette recette : selectedRows){
            allCategorie.remove(recette);
            status=c.SupprimerRec(recette.getId());
            if(status>0){
              
              Alert alert =new Alert(Alert.AlertType.INFORMATION);
              alert.setTitle("Recette!");
              alert.setHeaderText("Information");
              alert.setContentText("recette bien Suprimée!");
              alert.showAndWait();
          }
          else 
          {
               Alert alert =new Alert(Alert.AlertType.ERROR);
              alert.setTitle("texte(s) manquants!");
              alert.setHeaderText("Information");
              alert.setContentText("texte(s) manquants!!!");
              alert.showAndWait();
              
          }
        }

    


    
    }

    @FXML
    private void ModifierRecetteAction(ActionEvent event) 
    {
         ServRecette p =new ServRecette();
       table.getItems().stream().forEach((ps) -> {
           p.ModifierRec(ps);
        });
    }


    @FXML
    private void ChoixTypeRecetteAction(ActionEvent event) {
        System.out.println(CHOIXTYPERECETTE.getValue());
        if(CHOIXTYPERECETTE.getValue().equals("ALL")) {
            obs = FXCollections.observableArrayList(e.AfficherRecette());
            table.setItems(obs);
            return;
        }
        obs = FXCollections.observableArrayList(e.AfficherRecette(CHOIXTYPERECETTE.getValue()));
        table.setItems(obs);
        
        
    }

}
