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
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import Service.CategorieService;
import Service.OffreService;
import Service.ProduitService;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class GestionproduitController implements Initializable {

   @FXML
    private TableView<Produit> table;
    @FXML
    private TableColumn<Produit, Integer> id;
    @FXML
    private TableColumn<Produit, String> Nom_Prod;
    @FXML
    private TableColumn<Produit, Double> prix_prod;
    @FXML
    private TableColumn<Produit, Double> prix_offre;
    @FXML
    private TableColumn<Produit, String> description;
    @FXML
    private TableColumn<Produit, Integer> quantite;
    @FXML
    private TableColumn<Produit, String> image;
    //@FXML
   // private TableColumn<Produit, Integer> id_categorie;
    @FXML
    private ComboBox<Categorie> choiceBox;

    
    private CategorieService categorieService = new CategorieService();
    private ProduitService produitService = new ProduitService();
    private OffreService offreService = new OffreService();
    private ObservableList<Produit> obs ;
    @FXML
    private TextField searchField;
    @FXML
    private ComboBox<OffrePromotion> offreList;
    /**
     * Initializes the controller class.
     */
    
    private void remplir(List<Produit> produit) {
    table.setEditable(true);
      
         ProduitService p = new ProduitService();
        obs = FXCollections.observableArrayList(produit);
        table.setItems(obs);
        id.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("id"));
        
        Nom_Prod.setCellValueFactory(new PropertyValueFactory<Produit,String>("nomProd"));
         
        Nom_Prod.setCellFactory(TextFieldTableCell.forTableColumn());
        Nom_Prod.setOnEditCommit(event->{
            String newValue = event.getNewValue().equals("") ? event.getOldValue():
                event.getNewValue();
            ((Produit) event.getTableView().getItems()
                .get(event.getTablePosition().getRow())).setNomProd(newValue);
            table.refresh();
        });
        prix_prod.setCellValueFactory(new PropertyValueFactory<Produit,Double>("prixProd"));
        prix_prod.setCellFactory(TextFieldTableCell.<Produit, Double>forTableColumn(new DoubleStringConverter()));
        prix_prod.setOnEditCommit(event->{
        Double newValue = event.getNewValue() == null ? event.getOldValue():
                event.getNewValue();
            Produit p1 = ((Produit) event.getTableView().getItems()
                .get(event.getTablePosition().getRow()));
            p1.setPrixProd(newValue);
            if(p1.getOffre() != null) {
                p1.setPrixOffre(p1.getPrixProd()-(p1.getPrixProd()*p1.getOffre().getPourcentage()));
            }
            table.refresh();
        });
        
        
        prix_offre.setCellValueFactory(new PropertyValueFactory<Produit,Double>("prixOffre"));
        description.setCellValueFactory(new PropertyValueFactory<Produit,String>("prodDescription"));
        description.setCellFactory(TextFieldTableCell.forTableColumn());
        description.setOnEditCommit(event->{
            String newValue = event.getNewValue().equals("") ? event.getOldValue():
                event.getNewValue();
            ((Produit) event.getTableView().getItems()
                .get(event.getTablePosition().getRow())).setProdDescription(newValue);
            table.refresh();
        });
        
        quantite.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("quantite"));
        quantite.setCellFactory(TextFieldTableCell.<Produit, Integer>forTableColumn(new IntegerStringConverter()));
        quantite.setOnEditCommit(event->{
        Integer newValue = event.getNewValue() == null ? event.getOldValue():
                event.getNewValue();
            ((Produit) event.getTableView().getItems()
                .get(event.getTablePosition().getRow())).setQuantite(newValue);
            table.refresh();
        });
        image.setCellValueFactory(new PropertyValueFactory<Produit,String>("image"));
         image.setCellFactory(TextFieldTableCell.forTableColumn());
        image.setOnEditCommit(event->{
            String newValue = event.getNewValue().equals("") ? event.getOldValue():
                event.getNewValue();
            ((Produit) event.getTableView().getItems()
                .get(event.getTablePosition().getRow())).setImage(newValue);
            table.refresh();
        });
        //id_categorie.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("idCategorie"));
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         offreList.setItems(FXCollections.observableArrayList(offreService.afficherOffre("produit")));
         final ProduitService p = new ProduitService();
         ObservableList<Categorie> list  = FXCollections.observableArrayList(categorieService.affichercategories());
         Categorie dc = new Categorie("All", "");
         list.add(0,dc);
         choiceBox.setItems(list);
         choiceBox.getSelectionModel().select(dc);
        ArrayList<Produit> arrayList = (ArrayList<Produit>) p.afficherproduits();
        remplir(arrayList);
        choiceBox.valueProperty().addListener(new ChangeListener<Categorie>() {
            @Override public void changed(ObservableValue ov, Categorie t, Categorie t1) {
                if(t1.getNomCat().equals("All")) 
                {
                    ArrayList<Produit> arrayList = (ArrayList<Produit>) p.afficherproduits();
                    remplir(arrayList);
                    return;
                }
                remplir(produitService.filtrer(t1.getNomCat()));
            }    
        });
    }    

    @FXML
    private void AjoutProduit(ActionEvent event) {
        Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/AjoutProduit.fxml"));
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
    private void ModifierProduit(ActionEvent event) {
  
       ProduitService p =new ProduitService();
       table.getItems().stream().forEach((ps) -> {
           p.modifierProduit(ps);
        }); //p.modifierProduit(table.getSelectionModel().getSelectedItem());
    }

    
    @FXML
    private void SupprimerProduit(ActionEvent event) {
        ObservableList<Produit> selectedRows, allProduit;
        allProduit = table.getItems();
        selectedRows=table.getSelectionModel().getSelectedItems();
        ProduitService p=new ProduitService();
        for(Produit produit :selectedRows){
            allProduit.remove(produit);
            p.supprimerProduit(produit);
            
        }
        
        
     
    }

    @FXML
    private void retour(ActionEvent event) {
         Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/Categorie.fxml"));
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
    private void search(KeyEvent event) {
        if(searchField.getText().equals("")) {
            table.setItems(obs);
        }
        List<Produit> list =  (List<Produit>) obs.stream().filter(new Predicate() {
            @Override
            public boolean test(Object t) {
                Produit p = (Produit) t ;
                return p.getNomProd().contains(searchField.getCharacters());
            }
        }).collect(Collectors.toList());
        table.setItems(FXCollections.observableArrayList(list));
    }

    @FXML
    private void affecterOffre(ActionEvent event) {
        OffrePromotion offre = offreList.getSelectionModel().getSelectedItem();
        Produit produit = table.getSelectionModel().getSelectedItem();
        produitService.affecterPromotion(produit, offre);
        table.refresh();
    }

}
