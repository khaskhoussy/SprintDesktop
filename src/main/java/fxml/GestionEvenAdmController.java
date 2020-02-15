/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;



import entits.Evenement;
import entits.OffrePromotion;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import Service.Eventservice;
import Service.OffreService;
import fxml.HomeController;

/**
 * FXML Controller class
 *
 * @author FARAH
 */
public class GestionEvenAdmController implements Initializable {
 @FXML
    private TableView< Evenement> table;
    @FXML
    private TableColumn<Evenement,Integer> id;
    @FXML
    private TableColumn<Evenement,String> datedeb;
    @FXML
    private TableColumn<Evenement,String> datefin;
    @FXML
    private TableColumn<Evenement,String> desc;
    @FXML
    private TableColumn<Evenement,String> lieu;
    @FXML
    private TableColumn<Evenement,Integer> nbr;
    @FXML
    private TableColumn<Evenement,Integer> prix;
    @FXML
    private TableColumn<Evenement,Double> offre;
    @FXML
    private TableColumn<Evenement,String> image;
     @FXML
    private TableColumn<Evenement,Integer> idoff;
    @FXML
    private Button add;
    @FXML
    private Button supprimer;
    @FXML
    private Button Modifier;
    @FXML
    private TableColumn<Evenement, String> nom;
    @FXML
    private ComboBox<OffrePromotion> offreList;
    private OffreService offreService = new OffreService();
    private Eventservice eventservice= new Eventservice ();
    private ObservableList<Evenement>obs;

    /**
     * Initializes the controller class.
     */
     @FXML
    private void adminAc(ActionEvent event) {
         Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/Admin.fxml"));
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
        
        
        offreList.setItems(FXCollections.observableArrayList(offreService.afficherOffre("event")));
         final Eventservice p = new Eventservice();
        Eventservice e = new Eventservice();

        ArrayList<Evenement> arrayList = (ArrayList<Evenement>) e.afficherevent();
        ObservableList obs = FXCollections.observableArrayList(arrayList);
        table.setItems(obs);
        
        
        id.setCellValueFactory(new PropertyValueFactory<Evenement,Integer>("id"));
         datedeb.setCellValueFactory(new PropertyValueFactory<Evenement,String>("datedebut"));
         
            datedeb.setCellFactory(TextFieldTableCell.forTableColumn());
        datedeb.setOnEditCommit(event->{
            String newValue = event.getNewValue().equals("") ? event.getOldValue():
                event.getNewValue();
            ((Evenement) event.getTableView().getItems()
                .get(event.getTablePosition().getRow())).setDatedebut(newValue);
            table.refresh();
        });
        datefin.setCellValueFactory(new PropertyValueFactory<Evenement,String>("datefin"));
           datefin.setCellFactory(TextFieldTableCell.forTableColumn());
        datefin.setOnEditCommit(event->{
            String newValue = event.getNewValue().equals("") ? event.getOldValue():
                event.getNewValue();
            ((Evenement) event.getTableView().getItems()
                .get(event.getTablePosition().getRow())).setDatefin(newValue);
            table.refresh();
        });
       desc.setCellValueFactory(new PropertyValueFactory<Evenement,String>("description"));
          
            desc.setCellFactory(TextFieldTableCell.forTableColumn());
        desc.setOnEditCommit(event->{
            String newValue = event.getNewValue().equals("") ? event.getOldValue():
                event.getNewValue();
            ((Evenement) event.getTableView().getItems()
                .get(event.getTablePosition().getRow())).setDescription(newValue);
            table.refresh();
        });
         
        
        lieu.setCellValueFactory(new PropertyValueFactory<Evenement,String>("lieu"));
        
        
        
            lieu.setCellFactory(TextFieldTableCell.forTableColumn());
        lieu.setOnEditCommit(event->{
            String newValue = event.getNewValue().equals("") ? event.getOldValue():
                event.getNewValue();
            ((Evenement) event.getTableView().getItems()
                .get(event.getTablePosition().getRow())).setLieu(newValue);
            table.refresh();
        });
         nbr.setCellValueFactory(new PropertyValueFactory<Evenement,Integer>("nbrParticipants"));
        prix.setCellValueFactory(new PropertyValueFactory<Evenement,Integer>("eveprix"));
        
        prix.setCellFactory(TextFieldTableCell.<Evenement, Integer>forTableColumn(new IntegerStringConverter()));
       prix.setOnEditCommit(event->{
        Integer newValue = event.getNewValue() == null ? event.getOldValue():
                event.getNewValue();
            ((Evenement) event.getTableView().getItems()
                .get(event.getTablePosition().getRow())).setEveprix(newValue);
            
            Evenement p1 = ((Evenement) event.getTableView().getItems()
                .get(event.getTablePosition().getRow()));
            p1.setEveprix(newValue);
            if(p1.getOffre() != null) {
                p1.setPrix_offre(p1.getEveprix()-(p1.getEveprix()*p1.getOffre().getPourcentage()));
            }
            table.refresh();
        });
        
        
        
        
       offre.setCellValueFactory(new PropertyValueFactory<Evenement,Double>("prix_offre"));
       
       
       
       
       
        image.setCellValueFactory(new PropertyValueFactory<Evenement,String>("image"));
        
        
            image.setCellFactory(TextFieldTableCell.forTableColumn());
        image.setOnEditCommit(event->{
            String newValue = event.getNewValue().equals("") ? event.getOldValue():
                event.getNewValue();
            ((Evenement) event.getTableView().getItems()
                .get(event.getTablePosition().getRow())).setImage(newValue);
            table.refresh();
        });
        nom.setCellValueFactory(new PropertyValueFactory<Evenement,String>("nom"));
        nom.setCellFactory(TextFieldTableCell.forTableColumn());
        nom.setOnEditCommit(event->{
            String newValue = event.getNewValue().equals("") ? event.getOldValue():
                event.getNewValue();
            ((Evenement) event.getTableView().getItems()
                .get(event.getTablePosition().getRow())).setNom(newValue);
            table.refresh();
        });
        
        
        
        
         
        idoff.setCellValueFactory(new PropertyValueFactory<Evenement,Integer>("idOffre"));
        
        
        
        // TODO
    }    


    @FXML
    private void addAction(ActionEvent event) {
         Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/ajoutevent.fxml"));
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
    private void supprimerAction(ActionEvent event) {
        
        ObservableList<Evenement> selectedRows, allCategorie ;
        allCategorie =table.getItems();
        selectedRows = table.getSelectionModel().getSelectedItems();
        Eventservice c=new Eventservice();
        for (Evenement evenement : selectedRows){
            allCategorie.remove(evenement);
            c.deleteevent(evenement);
            
        
        
    }}

    @FXML
    private void modifierAction(ActionEvent event) {
        Eventservice e = new Eventservice();
      
       table.getItems().stream().forEach((ps) -> {
           e.updateevent(ps);
        });
        
    }

    @FXML
    private void affecterOffre(ActionEvent event) {
        
        
        
        OffrePromotion offre = offreList.getSelectionModel().getSelectedItem();
        Evenement evenement = table.getSelectionModel().getSelectedItem();
        eventservice.affecterPromotion(evenement, offre);
        
    }
    
}
