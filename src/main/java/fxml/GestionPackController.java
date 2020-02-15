/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import entits.PackDecoration;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import Service.ServPack;
import fxml.HomeController;

/**
 * FXML Controller class
 *
 * @author Dreamer
 */
public class GestionPackController implements Initializable {

    @FXML
    private TableView<PackDecoration> table;
    @FXML
    private TableColumn<PackDecoration, Integer> id;
    @FXML
    private TableColumn<PackDecoration , Double> prix;
    @FXML
    private Button ajouter;
    @FXML
    private Button modifierrecette;
    @FXML
    private Button supprimerrecette;
    @FXML
    private Button retour;
    @FXML
    private ComboBox<String> choixtypePack;
    @FXML
    private TableColumn<PackDecoration ,String> nom;
    @FXML
    private TableColumn<PackDecoration, String> type;
    @FXML
    private TableColumn<PackDecoration ,String> description;
    @FXML
    private TableColumn<PackDecoration, String> image;

    
    private List<PackDecoration> list ;
    private ServPack servPack = new ServPack();
     private ObservableList<PackDecoration> obs ;
    private ServPack e ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        e = new ServPack();
        
        table.setEditable(true);
        nom.setEditable(true);
        description.setEditable(true);
        prix.setEditable(true);
        type.setEditable(true);
        image.setEditable(true);
        List <PackDecoration> arrayList =  e.AfficherPack();
       obs = FXCollections.observableArrayList(arrayList);

        table.setItems(obs);
         
        id.setCellValueFactory(new PropertyValueFactory<PackDecoration,Integer>("id"));
        
        nom.setCellValueFactory(new PropertyValueFactory<PackDecoration,String>("nom"));
        nom.setCellFactory(TextFieldTableCell.forTableColumn());
        nom.setOnEditCommit(event->{
            String newValue = event.getNewValue().equals("") ? event.getOldValue():
                event.getNewValue();
            ((PackDecoration) event.getTableView().getItems()
                .get(event.getTablePosition().getRow())).setNom(newValue);
            table.refresh();
         });
        
         prix.setCellValueFactory(new PropertyValueFactory<PackDecoration,Double>("prixP"));
        prix.setCellFactory(TextFieldTableCell.<PackDecoration, Double>forTableColumn(new DoubleStringConverter()));
        prix.setOnEditCommit(event->{
            Double newValue = event.getNewValue().equals("") ? event.getOldValue():
                event.getNewValue();
            ((PackDecoration) event.getTableView().getItems()
                .get(event.getTablePosition().getRow())).setPrixP(newValue);
            table.refresh();
         });
        description.setCellValueFactory(new PropertyValueFactory<PackDecoration,String>("descriptionPack"));
        description.setCellFactory(TextFieldTableCell.forTableColumn());
        description.setOnEditCommit(event->{
            String newValue = event.getNewValue().equals("") ? event.getOldValue():
                event.getNewValue();
            ((PackDecoration) event.getTableView().getItems()
                .get(event.getTablePosition().getRow())).setDescriptionPack(newValue);
            table.refresh();
         });
        
       type.setCellValueFactory(new PropertyValueFactory<PackDecoration,String>("type"));
       type.setCellFactory(TextFieldTableCell.forTableColumn());
        type.setOnEditCommit(event->{
            String newValue = event.getNewValue().equals("") ? event.getOldValue():
                event.getNewValue();
            ((PackDecoration) event.getTableView().getItems()
                .get(event.getTablePosition().getRow())).setType(newValue);
            table.refresh();
         });
       
       image.setCellValueFactory(new PropertyValueFactory<PackDecoration,String>("image")); 
       image.setCellFactory(TextFieldTableCell.forTableColumn());
        image.setOnEditCommit(event->{
            String newValue = event.getNewValue().equals("") ? event.getOldValue():
                event.getNewValue();
            ((PackDecoration) event.getTableView().getItems()
                .get(event.getTablePosition().getRow())).setImage(newValue);
            table.refresh();
         });
        
        choixtypePack.setItems(FXCollections.observableArrayList("ALL","Packs pour petit bassin","Packs pour plus grand bassin","Packs pour ombre ou mi-ombre",
                "Packs de bordure","Packs de phyto-épuration"));
        choixtypePack.getSelectionModel().select("ALL");
        list = servPack.AfficherPack();
        table.setItems(FXCollections.observableArrayList(list));
    }    

    @FXML
    private void AjouterPackAction(ActionEvent event) {
         Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/AjouterPack.fxml"));
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
    private void ModifierPackAction(ActionEvent event) {
        table.getItems().stream().forEach((ps) -> {
           servPack.ModifierPack(ps);
        });
    }

    @FXML
    private void supprimerPackAction(ActionEvent event) {
        
        int status=0;
         ObservableList<PackDecoration> selectedRows, allCategorie ;
        allCategorie =table.getItems();
        selectedRows = table.getSelectionModel().getSelectedItems();
        ServPack c=new ServPack();
        for (PackDecoration packDecoration : selectedRows){
            allCategorie.remove(packDecoration);
            status=c.SupprimerPack(packDecoration.getId());
            if(status>0){
              
              Alert alert =new Alert(Alert.AlertType.INFORMATION);
              alert.setTitle("Pack_Deco!");
              alert.setHeaderText("Information");
              alert.setContentText("Pack_Deco bien Suprimée!");
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
    private void retourPaysagisteAction(ActionEvent event) {
         Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/Paysagiste.fxml"));
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
    private void ChoixTypePackAction(ActionEvent event) {
        String type = choixtypePack.getValue();
        if(type.equals("ALL")) list = servPack.AfficherPack();
        else list = servPack.AfficherPackByType(type);
        table.setItems(FXCollections.observableArrayList(list));
        table.refresh();
    }
    
}
