/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import entits.LignePack;
import entits.PackDecoration;
import entits.Produit;
import entits.Reclamation;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import Service.ProduitService;
import Service.ServPack;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dreamer
 */
public class AjouterPackController implements Initializable {

    @FXML
    private Button retour;
    @FXML
    private TextField nompack;
    @FXML
    private TextField desc;
    @FXML
    private TextField type;
    @FXML
    private TextField prix;
    @FXML
    private Button valider;
    @FXML
    private TableView<Produit> listeProduits;
    @FXML
    private TableColumn<Produit, String> nomProduit;
    @FXML
    private TableColumn<Produit, String> descriptionProduit;
    @FXML
    private TableColumn<Produit, Double> prixProduit;

    @FXML
    private TableView<LignePack> listeProduitSelectionne;
    @FXML
    private TableColumn<LignePack, String> nomProduitS;
    @FXML
    private TableColumn<LignePack, Integer> qteProduitS;
    @FXML
    private Button ajouterBtn;
    @FXML
    private TextField qteAjout;
    
    private ProduitService produitService ;
    private List<Produit> list ;
    
    private ServPack servPack;
    private ObservableList<LignePack> lignePacks;
    @FXML
    private TextArea descriptionAjout;
    @FXML
    private TableColumn<LignePack, String> descriptionProduitS;
    @FXML
    private Button choisirImage;
    @FXML
    private Button supprimerBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        produitService = new ProduitService();
        servPack = new ServPack();
        lignePacks = FXCollections.observableArrayList();
        listeProduitSelectionne.setItems(lignePacks);
        
        nomProduit.setCellValueFactory(new PropertyValueFactory<>("nomProd"));
        descriptionProduit.setCellValueFactory(new PropertyValueFactory<>("prodDescription"));
        prixProduit.setCellValueFactory(new PropertyValueFactory<>("prixProd"));
        
        nomProduitS.setCellValueFactory(new PropertyValueFactory<>("nomProd"));
        qteProduitS.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        descriptionProduitS.setCellValueFactory(new PropertyValueFactory<>("description"));
        
        listeProduits.setItems(FXCollections.observableArrayList(produitService.afficherproduitss()));
    }    


    @FXML
    private void choisir(ActionEvent event) {
    }

    @FXML
    private void ajouterAction(ActionEvent event) {
        Produit produit = listeProduits.getSelectionModel().getSelectedItem();
        LignePack lignePack = new LignePack();
        lignePack.setIdProduit(produit);
        lignePack.setQuantite(Integer.parseInt(qteAjout.getText()));
        System.out.println(Integer.parseInt(qteAjout.getText()));
        lignePack.setDescription(descriptionAjout.getText());
        lignePacks.add(lignePack);
    }

    @FXML
    private void RetourAction(ActionEvent event) {
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
    private void validAjoutPack(ActionEvent event) {
        PackDecoration packDecoration = new PackDecoration();
        packDecoration.setNom(nompack.getText());
        packDecoration.setDescriptionPack(desc.getText());
        packDecoration.setPrixP(Double.parseDouble(prix.getText()));
        packDecoration.setType(type.getText());
        packDecoration.setImage("image.png");
        int id = servPack.ajouterPack(packDecoration);
        packDecoration.setId(id);
        lignePacks.stream().map((l) -> {
            l.setIdPack(packDecoration);
            return l;
        }).forEachOrdered((l) -> {
            servPack.ajouterLignePack(l);
        });
    }

    @FXML
    private void supprimerProduit(ActionEvent event) {
        LignePack lignePack = listeProduitSelectionne.getSelectionModel().getSelectedItem();
        lignePacks.remove(lignePack);       
    }

    @FXML
    private void validAjoutPack(MouseEvent event) {
    }

    @FXML
    private void ajouterAction(MouseEvent event) {
    }

    @FXML
    private void supprimerProduit(MouseEvent event) {
    }
    
}
