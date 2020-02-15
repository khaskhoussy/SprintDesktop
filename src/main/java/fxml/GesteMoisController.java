/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import entits.Geste;
import entits.Recette;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import Service.ServGeste;
import fxml.HomeController;


/**
 * FXML Controller class
 *
 * @author Dreamer
 */
public class GesteMoisController implements Initializable {

    @FXML
    private TableView<Geste> table;
    @FXML
    private TableColumn<Geste, String> nom;
    @FXML
    private TableColumn<Geste, String> mois;
    @FXML
    private TableColumn<Geste, String> desc;
    @FXML
    private TableColumn<Geste, String> image;
    private ObservableList<Geste> obs ;
    private ServGeste g = new ServGeste() ;
    @FXML
    private ComboBox<String> choixgesteparmois;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServGeste g = new ServGeste();
        
        table.setEditable(true);
        nom.setEditable(true);
        desc.setEditable(true);
        image.setEditable(true);
        mois.setEditable(true);
        ArrayList <Geste> arrayList =  g.AfficherGeste();
       obs = FXCollections.observableArrayList(arrayList);

        table.setItems(obs);
        
         nom.setCellValueFactory(new PropertyValueFactory<Geste,String>("nom_geste"));
         nom.setCellFactory(TextFieldTableCell.forTableColumn());
        nom.setOnEditCommit(event->{
            String newValue = event.getNewValue().equals("") ? event.getOldValue():
                event.getNewValue();
            ((Geste) event.getTableView().getItems()
                .get(event.getTablePosition().getRow())).setNom_geste(newValue);
            table.refresh();
         });
        desc.setCellValueFactory(new PropertyValueFactory<Geste,String>("desc_geste"));
        desc.setCellFactory(TextFieldTableCell.forTableColumn());
        desc.setOnEditCommit(event->{
            String newValue = event.getNewValue().equals("") ? event.getOldValue():
                event.getNewValue();
            ((Geste) event.getTableView().getItems()
                .get(event.getTablePosition().getRow())).setDesc_geste(newValue);
            table.refresh();
         });
        
       mois.setCellValueFactory(new PropertyValueFactory<Geste,String>("mois_geste"));
       mois.setCellFactory(TextFieldTableCell.forTableColumn());
        mois.setOnEditCommit(event->{
            String newValue = event.getNewValue().equals("") ? event.getOldValue():
                event.getNewValue();
            ((Geste) event.getTableView().getItems()
                .get(event.getTablePosition().getRow())).setMois_geste(newValue);
            table.refresh();
         });
       
       image.setCellValueFactory(new PropertyValueFactory<Geste,String>("image_geste")); 
        image.setCellFactory(TextFieldTableCell.forTableColumn());
        image.setOnEditCommit(event->{
            String newValue = event.getNewValue().equals("") ? event.getOldValue():
                event.getNewValue();
            ((Geste) event.getTableView().getItems()
                .get(event.getTablePosition().getRow())).setImage_geste(newValue);
            table.refresh();
         });
       
        // TODO
        choixgesteparmois.getItems().addAll("ALL","Janvier","Février","Mars","Avril","Mai","Juin","Juillet","Aout","Septembre","Octobre","Novembre","Décembre");
        choixgesteparmois.getSelectionModel().selectFirst();
    }    

    @FXML
    private void AjouterGesteAction(ActionEvent event) {
         Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/AjouterGeste.fxml"));
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
    private void ModifierGesteAction(ActionEvent event) {
        ServGeste p =new ServGeste();
       table.getItems().stream().forEach((ps) -> {
           p.ModifierGeste(ps);
        });
    }

    @FXML
    private void SupprimerGesteAction(ActionEvent event) {
         int status=0;

        ObservableList<Geste> selectedRows, allCategorie ;
        allCategorie =table.getItems();
        selectedRows = table.getSelectionModel().getSelectedItems();
        ServGeste c=new ServGeste();
        for (Geste geste : selectedRows){
            allCategorie.remove(geste);
            status=c.SupprimerGeste(geste.getId());
            
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
    private void retourgestedumoisJardinierAction(ActionEvent event) {
        Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/Jardinier.fxml"));
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
    private void choixgesteComboBoxAction(ActionEvent event) {
        System.out.println(choixgesteparmois.getValue());
        if(choixgesteparmois.getValue().equals("ALL")) {
            obs = FXCollections.observableArrayList(g.AfficherGeste());
            table.setItems(obs);
            return;
        }
        obs = FXCollections.observableArrayList(g.AfficherGeste(choixgesteparmois.getValue()));
        table.setItems(obs);
    }
    
}
