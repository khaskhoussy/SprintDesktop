/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import entits.Commandable;
import entits.Commande;
import entits.LigneCommande;
import entits.Produit;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
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
import javafx.stage.Stage;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import Service.CommandeService;
import Service.LigneCommandeService;

import entits.ObjetPanierProduit;
import entits.Produit;
import static fxml.MonPanierController.panier;
import java.util.List;
import Service.LigneServiceService;
import Service.PackService;
import Service.ProduitService;
import entits.LigneService;
import entits.ObjetPanierService;
import java.util.Date;

public class CommandeDetailController implements Initializable {

   @FXML
    private TableView<Commandable> tableView2;
        /*CommandeService c = new CommandeService();
        LigneCommandeService lc = new LigneCommande();
        ProduitService ps = new ProduitService();
        
        ArrayList<Commande> arrayList = (ArrayList<Commande>) c.afficherCommande();
        ArrayList<LigneCommande> arrayList = (ArrayList<LigneCommande>) c.afficherCommande();
        ArrayList<Commande> arrayList = (ArrayList<Commande>) c.afficherCommande();
        ObservableList obs = FXCollections.observableArrayList(arrayList);
        tableView.setItems(obs);
        id.setCellValueFactory(new PropertyValueFactory<Commande,Integer>("id"));
        date_validation.setCellValueFactory(new PropertyValueFactory<Commande,String>("dateValidation"));
        prix_totale.setCellValueFactory(new PropertyValueFactory<Commande,Integer>("prixTotal"));
        quantite.setCellValueFactory(new PropertyValueFactory<LigneCommande,Integer>("Qte")); 
        prix_unitaire.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("prix_prod")); 
        
        

    } */   
    @FXML
    private TableColumn<Commandable, Integer> id;
    //private TableColumn<Commande, Integer> date_validation;
    @FXML
    private TableColumn<Commandable, Integer> quantite;
    @FXML
    private Button retour;
    @FXML
    private TableColumn<Commandable, String> nom;
    @FXML
    private TableColumn<Commandable, Double> prix;
    
    private LigneCommandeService lc = new LigneCommandeService();
    private LigneServiceService ls = new LigneServiceService();
    private CommandeService commandeService = new CommandeService();
    private List<Commandable> list;
    @FXML
    private TableColumn<Commandable, String> description;

    
@Override
    public void initialize(URL url, ResourceBundle rb) {
        
        list = new ArrayList();
        List<LigneCommande> ligne_commandes = commandeService.getLigneCommande(MesCommandesController.commande);
        List<LigneService> ligneServices = ls.getLigneService(MesCommandesController.commande);
        for(LigneCommande ligneCommande:ligne_commandes){
            Commandable commandable = new ObjetPanierProduit(ligneCommande.getIdProduit(), ligneCommande.getQte());
            list.add(commandable);
        }
        for(LigneService ligneService:ligneServices) {
            Commandable commandable = new ObjetPanierService();
            ((ObjetPanierService)commandable).setDateDebut(new Date());
            ((ObjetPanierService)commandable).setDateFin(new Date());
            ((ObjetPanierService)commandable).setUser(ligneService.getIdUser());
            list.add(commandable);
        }
        
        tableView2.setItems(FXCollections.observableArrayList(list));
        
        id.setCellValueFactory(new PropertyValueFactory<Commandable,Integer>("idObjet"));
        nom.setCellValueFactory(new PropertyValueFactory<Commandable,String>("nom"));
        quantite.setCellValueFactory(new PropertyValueFactory<Commandable,Integer>("quantite"));
        prix.setCellValueFactory(new PropertyValueFactory<Commandable,Double>("prixTotale"));
        description.setCellValueFactory(new PropertyValueFactory<Commandable,String>("description"));
 
        
       

    } 

    @FXML
    private void RetourAction(ActionEvent event) {
                  
                {
                    Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/MesCommandes.fxml"));
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
}
