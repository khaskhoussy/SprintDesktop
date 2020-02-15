/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import Service.SujetService;
import entits.Sujet;
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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aisce
 */
public class ListeSujetController implements Initializable {

    @FXML
    private TableColumn<Sujet, String> Sujets;
    @FXML
    private TableColumn<Sujet, Integer>NombreDeVue ;
    @FXML
    private TableColumn<Sujet, String>Description;
    @FXML
    private TableColumn<Sujet, Integer> NombreDeCommentaire;
    @FXML
    private TableColumn<Sujet, String> DatePublication;
    @FXML
    private TableView<Sujet> Table;
     @FXML private ChoiceBox ChoiceBox;
    @FXML
    private TextField rechercheBar;
    @FXML
    private Button filter;
     @FXML
    private Button go;
    @FXML
    private Pane pnlCustomer11;
    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Table.refresh();
        SujetService a = new SujetService();
        ArrayList<Sujet> arrayList = (ArrayList<Sujet>)a.afficherSujet();
        ObservableList obs = FXCollections.observableArrayList(arrayList);
        Table.setItems(obs);
        Sujets.setCellValueFactory(new PropertyValueFactory<>("nomsujet"));
        Description.setCellValueFactory(new PropertyValueFactory<>("description"));
        DatePublication.setCellValueFactory(new PropertyValueFactory<>("datepub"));
        ChoiceBox.getItems().addAll("Plante","Accessoire","Divers","All");
        Table.setRowFactory( tv -> {
            TableRow row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                int i=0;
            if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                String mSujetNom = Table.getSelectionModel().getSelectedItem().getNomsujet();
                int mSujetId = a.getSujetIdByName(mSujetNom);
                
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ListeCommentairesAdmin.fxml"));

                Parent root;
                try {
                    root = loader.load();
                     Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                     window.setScene(new Scene(root));
                     window.show();

                } catch (IOException ex) {
                    Logger.getLogger(ForomController.class.getName()).log(Level.SEVERE, null, ex);
                }
                 ListeCommentairesController commentsController = loader.getController();
                //Pass whatever data you want. You can have multiple method calls here
                commentsController.transferMessage(mSujetId);
                //Get controller of scene2
                
 
                //Show scene 2 in new window     
               
               /** Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();**/

               
                /*Parent frontPage = null; 
                try {
                frontPage = FXMLLoader.load(getClass().getResource("/fxml/ListeCommentaires.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Scene frontPageScene= new Scene(frontPage); 
                //to get the stage
                Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(frontPageScene);
                window.show();*/
                i++;
            }
        });
        return row ;
        });
    }
    @FXML
    private void filter (ActionEvent event) {
        clickItem();
    }
    @FXML
    private void go (ActionEvent event) {
        Recherche();
    }
    void Recherche(){
        String recherche = rechercheBar.getText().toString();
        SujetService sujet =new SujetService();
        ArrayList<Sujet> arrayList = (ArrayList<Sujet>)sujet.chercherSujet(recherche);
        ObservableList obs = FXCollections.observableArrayList(arrayList);
        Table.setItems(obs);
        Table.refresh(); 
        
    }

    void clickItem(){
        String myChoice = ChoiceBox.getSelectionModel().getSelectedItem().toString();
        
        
        try{
            SujetService sujet =new SujetService();
        if (myChoice.equals("Plante"))
        {
        ArrayList<Sujet> arrayList = (ArrayList<Sujet>)sujet.afficherSujetPlante();
        ObservableList obs = FXCollections.observableArrayList(arrayList);
        Table.setItems(obs);
        Table.refresh();
        }
        else if (myChoice.equals("Accessoire"))
        {
            ArrayList<Sujet> arrayList = (ArrayList<Sujet>)sujet.afficherSujetAccessoire();
        ObservableList obs = FXCollections.observableArrayList(arrayList);
        Table.setItems(obs);
        Table.refresh();
        }
        else if (myChoice.equals("Divers"))
        {
            ArrayList<Sujet> arrayList = (ArrayList<Sujet>)sujet.afficherSujetDivers();
            ObservableList obs = FXCollections.observableArrayList(arrayList);
            Table.setItems(obs);
            Table.refresh();
        }
        else{
        
        ArrayList<Sujet> arrayList = (ArrayList<Sujet>)sujet.afficherSujet();
        ObservableList obs = FXCollections.observableArrayList(arrayList);
        Table.setItems(obs);
        Sujets.setCellValueFactory(new PropertyValueFactory<>("nomsujet"));
        DatePublication.setCellValueFactory(new PropertyValueFactory<>("datepub"));
            Table.refresh();
        }
        }catch(Exception e){
            System.out.println(e);
        }
       Table.refresh();
    }
    
    @FXML
    private void AjouterSujetForum(ActionEvent event) throws IOException {
        Parent frontPage = null; 

        frontPage = FXMLLoader.load(getClass().getResource("/fxml/AjouterSujetForum.fxml"));

        Scene frontPageScene= new Scene(frontPage); 
        //to get the stage
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(frontPageScene);
        window.show();
    }
    


    
       
        
        
        
    }
