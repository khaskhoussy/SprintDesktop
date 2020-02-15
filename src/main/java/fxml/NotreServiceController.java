/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import Service.AdminService;
import Service.LigneServiceService;
import entits.LigneService;
import entits.ObjetPanierService;
import entits.User;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class NotreServiceController implements Initializable {

    @FXML
    private TableColumn<User, Integer> id;
    @FXML
    private TableColumn<User, String> nom;
    @FXML
    private TableColumn<User, String> prenom;
    @FXML
    private TableColumn<User, String> email;
    @FXML
    private TableColumn<User, String> role;
    @FXML
    private TableView<User> table;
    @FXML
    private TableColumn<User, String> telephone;
    @FXML
    private Button dt;
    @FXML
    private ComboBox<String> type;
    @FXML
    private TextField search;
  
    private List<User> list;
    @FXML
    private Button searchBtn;
    private AdminService as ;
    
    
    public static User selecteduser;
    private LigneServiceService ligneServiceService;
    @FXML
    private DatePicker dateDebutService;
    @FXML
    private DatePicker dateFinService;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        as = new AdminService();
        ligneServiceService = new LigneServiceService();
        list = (ArrayList<User>)as.afficherStaff();
        table.setItems(FXCollections.observableArrayList(list));
        id.setCellValueFactory(new PropertyValueFactory<User,Integer>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<User,String>("Nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<User,String>("Prenom"));
        email.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
        telephone.setCellValueFactory(new PropertyValueFactory<User,String>("telephone"));
        role.setCellValueFactory(new PropertyValueFactory<User,String>("roles"));
        System.out.println("this is wht you wait for"+as.afficherJardinier());
        //desiable button
        this.dt.setDisable(true);
        type.getItems().addAll("ALL","Jardinier","Paysagist");
        type.getSelectionModel().select("ALL");
        
    }    
//pour activer la button
    @FXML
    public void onTableClick()
    {
        this.dt.setDisable(false);
    }
    @FXML
    private void Boutique(ActionEvent event) {
           Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/Boutique.fxml"));
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
    private void monCompte(ActionEvent event) {
        Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/MonCompte.fxml"));
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
    private void deconnectee(ActionEvent event) {
        Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/home.fxml"));
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
    private void mesCommandes(ActionEvent event) {
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

    @FXML
    private void notreOffre(ActionEvent event) {
         Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/NosPacks.fxml"));
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
    private void monPanier(ActionEvent event) {
         Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/MonPanier.fxml"));
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
    private void evenment(ActionEvent event) {
         Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/Evenement.fxml"));
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
    private void forom(ActionEvent event) {
         Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/Forom.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene frontPageScene= new Scene(frontPage); 
        //to get the stage
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(frontPageScene);
        window.show();
    }

    private void listeStaff(ActionEvent event) {
        Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/ListeEmployer.fxml"));
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
    private void detail(ActionEvent event) {
      User u=new User(); 
        u=table.getSelectionModel().getSelectedItem();
        if (u.getRoles().equals("Jardinier"))
        { Parent tableViewParent = null;
        
        FXMLLoader Loader= new FXMLLoader();
        try { 
              
            Loader.setLocation(getClass().getResource("/fxml/DetailleEmAd.fxml"));
             tableViewParent = Loader.load();
            
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene frontPageScene= new Scene(tableViewParent); 
        //this is where the controller can call a methode
        DetailleEmAdController controller=Loader.getController();
        controller.setUser(table.getSelectionModel().getSelectedItem());
        controller.show();
        //controller.initData(table.getSelectionModel().getSelectedItem());
        //to get the stage
       // User tUser = table.getSelectionModel().getSelectedItem();
        //NotreServiceController.selecteduser= new User();
        
       // System.out.println(tUser.getNom());
        //NotreServiceController.selecteduser.setNom("fefefeff");
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(frontPageScene);
        window.show();
        }
          if (u.getRoles().equals("Paysagist"))
        { Parent tableViewParent = null;
        
        FXMLLoader Loader= new FXMLLoader();
        try { 
              
            Loader.setLocation(getClass().getResource("/fxml/DetailleEmAd.fxml"));
             tableViewParent = Loader.load();
            
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene frontPageScene= new Scene(tableViewParent); 
        //this is where the controller can call a methode
        DetailleEmAdController controller=Loader.getController();
        controller.setUser(table.getSelectionModel().getSelectedItem());
        controller.show();
        //controller.initData(table.getSelectionModel().getSelectedItem());
        //to get the stage
       // User tUser = table.getSelectionModel().getSelectedItem();
        //NotreServiceController.selecteduser= new User();
        
       // System.out.println(tUser.getNom());
        //NotreServiceController.selecteduser.setNom("fefefeff");
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(frontPageScene);
        window.show();
        }        
    }

    
    private void filter() {
        list = (ArrayList<User>)as.afficherStaff();
        if(!type.getValue().equals("ALL")){
            list = list.stream().filter((u)-> {
                return u.getRoles().equals(type.getValue());
            }).collect(Collectors.toList());
        }
        String s = "ferregrdg";
        if(!search.getText().equals("")) {
            list = list.stream().filter((u)-> {
                return u.getNom().contains(search.getText()) ||
                        u.getPrenom().contains(search.getText()) ||
                        u.getTelephone().contains(search.getText()) ||
                        u.getEmail().contains(search.getText());
            }).collect(Collectors.toList());
        }
        
        table.setItems(FXCollections.observableArrayList(list));
    }
    
    @FXML
    private void typeAction(ActionEvent event) {
        filter();
    }

    @FXML
    private void searchAction(ActionEvent event) {
        filter();
    }
@FXML
    private void Recette(ActionEvent event) {
         Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/Recette.fxml"));
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
    private void gestedumois(ActionEvent event) {
        Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/GesteFront.fxml"));
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
    private void reclamation(ActionEvent event) {
        Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/Reclamation.fxml"));
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
    private void commander(ActionEvent event) {
        User user = table.getSelectionModel().getSelectedItem();
        List<LigneService> ligneSevices = ligneServiceService.getLigneServiceByUser(user);
        
        
        LocalDate localDate = dateDebutService.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date dateDebut = Date.from(instant);
        
        localDate = dateFinService.getValue();
        instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date dateFin = Date.from(instant);
        
        
        for(LigneService lc : ligneSevices) {
            if(!((dateDebut.before(lc.getDateDebut()) && dateFin.before(lc.getDateDebut()))
                    || (dateDebut.after(lc.getDateFin()) && dateFin.after(lc.getDateFin())))) {
                //alert ....................
                 Alert alert =new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Commande");
                alert.setHeaderText("Information");
                alert.setContentText("Date invalide");
                alert.showAndWait();
                return ;
            }
        }
                Alert alert =new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Commande");
                alert.setHeaderText("Information");
                alert.setContentText("Cammande bien Ajoutee!s");
                alert.showAndWait();
        ObjetPanierService ops = new ObjetPanierService();
        ops.setDateDebut(dateDebut);
        ops.setDateFin(dateFin);
        ops.setUser(user);
        MonPanierController.panier.add(ops);
    }
}
    

