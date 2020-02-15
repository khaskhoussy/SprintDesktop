/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import entits.Categorie;
import entits.OffrePromotion;
import entits.Produit;
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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import Service.CategorieService;
import Service.OffreService;
import Service.ProduitService;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class GestionOffreController implements Initializable {

   @FXML
    private TableView<OffrePromotion> table;
    @FXML
    private TableColumn<OffrePromotion, Integer> id;
    @FXML
    private TableColumn<OffrePromotion, Double> pourcentage;
    @FXML
    private TableColumn<OffrePromotion, String> datedebut;
    @FXML
    private TableColumn<OffrePromotion, String> datefin;
    @FXML
    private TableColumn<OffrePromotion, String> type;
    @FXML
    private TableColumn<OffrePromotion, String> titre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          OffreService o = new OffreService();

        ArrayList<OffrePromotion> arrayList = (ArrayList<OffrePromotion>) o.afficheroffre();
        ObservableList obs = FXCollections.observableArrayList(arrayList);
        table.setItems(obs);
        id.setCellValueFactory(new PropertyValueFactory<OffrePromotion,Integer>("id"));
        
        datedebut.setCellValueFactory(new PropertyValueFactory<OffrePromotion,String>("dateDebut"));
        datedebut.setCellFactory(TextFieldTableCell.forTableColumn());
        datedebut.setOnEditCommit(event->{
            String newValue = event.getNewValue().equals("") ? event.getOldValue():
                event.getNewValue();
            ((OffrePromotion) event.getTableView().getItems()
                .get(event.getTablePosition().getRow())).setDateDebut(newValue);
            table.refresh();
        });
          
        datefin.setCellValueFactory(new PropertyValueFactory<OffrePromotion,String>("dateFin"));
          
        
        datefin.setCellFactory(TextFieldTableCell.forTableColumn());
        datefin.setOnEditCommit(event->{
            String newValue = event.getNewValue().equals("") ? event.getOldValue():
                event.getNewValue();
            ((OffrePromotion) event.getTableView().getItems()
                .get(event.getTablePosition().getRow())).setDateFin(newValue);
            table.refresh();
        });
         type.setCellValueFactory(new PropertyValueFactory<OffrePromotion,String>("type"));
          
        
       type.setCellFactory(TextFieldTableCell.forTableColumn());
        type.setOnEditCommit(event->{
            String newValue = event.getNewValue().equals("") ? event.getOldValue():
                event.getNewValue();
            ((OffrePromotion) event.getTableView().getItems()
                .get(event.getTablePosition().getRow())).setType(newValue);
            table.refresh();
        });
        titre.setCellValueFactory(new PropertyValueFactory<OffrePromotion,String>("titre"));
          
        
       titre.setCellFactory(TextFieldTableCell.forTableColumn());
       titre.setOnEditCommit(event->{
            String newValue = event.getNewValue().equals("") ? event.getOldValue():
                event.getNewValue();
            ((OffrePromotion) event.getTableView().getItems()
                .get(event.getTablePosition().getRow())).setTitre(newValue);
            table.refresh();
        });
        pourcentage.setCellValueFactory(new PropertyValueFactory<OffrePromotion,Double>("pourcentage"));
      pourcentage.setCellFactory(TextFieldTableCell.<OffrePromotion, Double>forTableColumn(new DoubleStringConverter()));
        pourcentage.setOnEditCommit(event->{
        Double newValue = event.getNewValue() == null ? event.getOldValue():
                event.getNewValue();
           ((OffrePromotion) event.getTableView().getItems()
                   .get(event.getTablePosition().getRow())).setPourcentage(newValue);
           
            
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
    private void ajouteroffre(ActionEvent event) {
         Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/AjoutOffre.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene frontPageScene= new Scene(frontPage); 
        //to get the stage
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(frontPageScene);
        window.show();
            
     
    

        // TODO
    }    

 
    @FXML
    private void modifieroffre(ActionEvent event) {
          OffreService o =new OffreService();
       table.getItems().stream().forEach((ps) -> {
           o.modifierOffre(ps);
        }); 
        
    }

    @FXML
    private void supprimeroffre(ActionEvent event) {
                ObservableList<OffrePromotion> selectedRows, allOffre;
        allOffre = table.getItems();
        selectedRows=table.getSelectionModel().getSelectedItems();
        OffreService o=new  OffreService();
        for(OffrePromotion offre :selectedRows){
            allOffre.remove(offre);
            o.supprimerOffre(offre);
            
        }
    }

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
    
    
}
