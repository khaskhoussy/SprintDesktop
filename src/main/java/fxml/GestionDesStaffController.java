/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import Service.AdminService;
import entits.User;
import static fxml.HomeController.validate;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aisce
 */
public class GestionDesStaffController implements Initializable {

    @FXML
    private TableView<User> table;
    @FXML
    private TableColumn<User, Integer> id;
    @FXML
    private TableColumn<User, String> nom;
    @FXML
    private TableColumn<User, String> prenom;
    @FXML
    private TableColumn<User, String> telephone;
    @FXML
    private TableColumn<User, String> email;
    @FXML
    private TableColumn<User, String> Role;
    @FXML
    private TableColumn<User, String> usernameaff;
    @FXML
    private TableColumn<User, String> password;
    @FXML
    private TextField nom_ajouter;
    @FXML
    private TextField prenom_ajouter;
    @FXML
    private TextField telephone_ajouter;
    @FXML
    private TextField email_ajouter;
    @FXML
    private TextField username_ajouter;
    @FXML
    private TextField password_ajouter;
    @FXML
    private ChoiceBox role_ajouter;
    @FXML
    private ComboBox<String> type;
    @FXML
    private TextField search;
 private AdminService as ;
 private List<User> list;
    @FXML
    private Label checkLabel;
    /**
     * Initializes the controller class.
     */
     @FXML
    private void deconnexion(ActionEvent event) {
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
     public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
            return matcher.find();
    }
     @FXML
    private void retour(ActionEvent event) {
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
    @FXML
    private void espaceJardinier(ActionEvent event) {
        Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/AdJardinier.fxml"));
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
        as = new AdminService();
        list = (ArrayList<User>)as.afficherGlobal();
        table.setItems(FXCollections.observableArrayList(list));
       /* ArrayList<User> arrayList = (ArrayList<User>)as.afficherGlobal();
        ObservableList obs = FXCollections.observableArrayList(arrayList);
        table.setItems(obs);*/
        id.setCellValueFactory(new PropertyValueFactory<User,Integer>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<User,String>("Nom"));
        nom.setCellFactory(TextFieldTableCell.forTableColumn());
        nom.setOnEditCommit(event->{
            String newValue = event.getNewValue().equals("") ? event.getOldValue():
                event.getNewValue();
            ((User) event.getTableView().getItems()
                .get(event.getTablePosition().getRow())).setNom(newValue);
          table.refresh();
        });
        prenom.setCellValueFactory(new PropertyValueFactory<User,String>("Prenom"));
        prenom.setCellFactory(TextFieldTableCell.forTableColumn());
        prenom.setOnEditCommit(event->{
            String newValue = event.getNewValue().equals("") ? event.getOldValue():
                event.getNewValue();
            ((User) event.getTableView().getItems()
                .get(event.getTablePosition().getRow())).setPrenom(newValue);
           table.refresh(); 
        });
        email.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
        email.setCellFactory(TextFieldTableCell.forTableColumn());
        email.setOnEditCommit(event->{
            String newValue = event.getNewValue().equals("") ? event.getOldValue():
                event.getNewValue();
            ((User) event.getTableView().getItems()
                .get(event.getTablePosition().getRow())).setEmail(newValue);
           table.refresh(); 
        });
        telephone.setCellValueFactory(new PropertyValueFactory<User,String>("telephone"));
        telephone.setCellFactory(TextFieldTableCell.forTableColumn());
        telephone.setOnEditCommit(event->{
            String newValue = event.getNewValue().equals("") ? event.getOldValue():
                event.getNewValue();
            ((User) event.getTableView().getItems()
                .get(event.getTablePosition().getRow())).setTelephone(newValue);
           table.refresh(); 
        });
        usernameaff.setCellValueFactory(new PropertyValueFactory<User,String>("username"));
        usernameaff.setCellFactory(TextFieldTableCell.forTableColumn());
        usernameaff.setOnEditCommit(event->{
            String newValue = event.getNewValue().equals("") ? event.getOldValue():
                event.getNewValue();
            ((User) event.getTableView().getItems()
                .get(event.getTablePosition().getRow())).setUsername(newValue);
           table.refresh(); 
        });
        password.setCellValueFactory(new PropertyValueFactory<User,String>("password"));
        password.setCellFactory(TextFieldTableCell.forTableColumn());
        password.setOnEditCommit(event->{
            String newValue = event.getNewValue().equals("") ? event.getOldValue():
                event.getNewValue();
            ((User) event.getTableView().getItems()
                .get(event.getTablePosition().getRow())).setPassword(newValue);
           table.refresh();
        });
        
        Role.setCellValueFactory(new PropertyValueFactory<User,String>("roles"));
        Role.setCellFactory(TextFieldTableCell.forTableColumn());
        Role.setOnEditCommit(event->{
            String newValue = event.getNewValue().equals("") ? event.getOldValue():
                event.getNewValue();
            ((User) event.getTableView().getItems()
                .get(event.getTablePosition().getRow())).setRoles(newValue);
            table.refresh();
        });
        System.out.println("this is wht you wait for"+as.afficherJardinier());
        role_ajouter.getItems().addAll("Administrateur","Expert","Jardinier","Paysagist");
        type.getItems().addAll("ALL","Jardinier","Paysagist","Admin","Expert","Client");
        type.getSelectionModel().select("ALL");
        // TODO
    }    

    @FXML
    private void espacePaysagiste(ActionEvent event) {
         Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/AdPaysagiste.fxml"));
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
    private void espaceExpert(ActionEvent event) {
        Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/AdExpert.fxml"));
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
    private void espaceClient(ActionEvent event) {
        Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/AdClient.fxml"));
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
    private void modifier(ActionEvent event) {
        User a=new User();
        a=table.getSelectionModel().getSelectedItem();
        as.modfierComptee(a);
        
        
    }

    @FXML
    private void ajouter(ActionEvent event) {
        int status = 0;
         String jnom;
        String jprenom;
        String jemail;
        String jpassword;
        String jusername;
        String jtelephone ;
        jnom=nom_ajouter.getText();
        jprenom=prenom_ajouter.getText();
        jtelephone=telephone_ajouter.getText();
        jemail=email_ajouter.getText();
        jusername=username_ajouter.getText();
        jpassword=password_ajouter.getText();
        User c =new User(jnom, jprenom, jtelephone, jemail, jpassword, jusername);
         AdminService cs = new AdminService();
        if (role_ajouter.getValue().toString().equals("Administrateur"))
        {   if( !validate(email_ajouter.getText()))
         {
             checkLabel.setText("verfier votre email !!");
             return;
         }
            status=cs.ajouterAdmin(c);
         if(status>0){
                nom_ajouter.setText("");
                prenom_ajouter.setText("");
                telephone_ajouter.setText("");
                email_ajouter.setText("");
                username_ajouter.setText("");
                password_ajouter.setText("");
              Alert alert =new Alert(Alert.AlertType.INFORMATION);
              alert.setTitle("vous avez creer compte!");
              alert.setHeaderText("Information");
              alert.setContentText("Compte bien Ajouter!");
              alert.showAndWait();
              ArrayList<User> arrayList = (ArrayList<User>)cs.afficherGlobal();
        ObservableList obs = FXCollections.observableArrayList(arrayList);
        table.setItems(obs);
        id.setCellValueFactory(new PropertyValueFactory<User,Integer>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<User,String>("Nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<User,String>("Prenom"));
        email.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
        telephone.setCellValueFactory(new PropertyValueFactory<User,String>("telephone"));
        usernameaff.setCellValueFactory(new PropertyValueFactory<User,String>("username"));
        password.setCellValueFactory(new PropertyValueFactory<User,String>("password"));
          }
          else 
          {
               Alert alert =new Alert(Alert.AlertType.ERROR);
              alert.setTitle("vous avez creer compte!");
              alert.setHeaderText("Information");
              alert.setContentText("username ou email dejat exicte !!");
              alert.showAndWait();
              
          }
         
        }
        
        if (role_ajouter.getValue().toString().equals("Expert"))
        {  if( !validate(email_ajouter.getText()))
         {
             checkLabel.setText("verfier votre email !!");
             return;
         }
            status=cs.ajouterExpert(c);
        if(status>0){
                nom_ajouter.setText("");
                prenom_ajouter.setText("");
                telephone_ajouter.setText("");
                email_ajouter.setText("");
                username_ajouter.setText("");
                password_ajouter.setText("");
              Alert alert =new Alert(Alert.AlertType.INFORMATION);
              alert.setTitle("vous avez creer compte!");
              alert.setHeaderText("Information");
              alert.setContentText("Compte bien Ajouter!");
              alert.showAndWait();
              ArrayList<User> arrayList = (ArrayList<User>)cs.afficherGlobal();
        ObservableList obs = FXCollections.observableArrayList(arrayList);
        table.setItems(obs);
        id.setCellValueFactory(new PropertyValueFactory<User,Integer>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<User,String>("Nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<User,String>("Prenom"));
        email.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
        telephone.setCellValueFactory(new PropertyValueFactory<User,String>("telephone"));
        usernameaff.setCellValueFactory(new PropertyValueFactory<User,String>("username"));
        password.setCellValueFactory(new PropertyValueFactory<User,String>("password"));
          }
          else 
          {
               Alert alert =new Alert(Alert.AlertType.ERROR);
              alert.setTitle("vous avez creer compte!");
              alert.setHeaderText("Information");
              alert.setContentText("username ou email dejat exicte !!");
              alert.showAndWait();
              
          }
          
        }
        
        if (role_ajouter.getValue().toString().equals("Jardinier"))
        { status=cs.ajouterJardinier(c);
        if(status>0){
                nom_ajouter.setText("");
                prenom_ajouter.setText("");
                telephone_ajouter.setText("");
                email_ajouter.setText("");
                username_ajouter.setText("");
                password_ajouter.setText("");
              Alert alert =new Alert(Alert.AlertType.INFORMATION);
              alert.setTitle("vous avez creer compte!");
              alert.setHeaderText("Information");
              alert.setContentText("Compte bien Ajouter!");
              alert.showAndWait();
              ArrayList<User> arrayList = (ArrayList<User>)cs.afficherGlobal();
        ObservableList obs = FXCollections.observableArrayList(arrayList);
        table.setItems(obs);
        id.setCellValueFactory(new PropertyValueFactory<User,Integer>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<User,String>("Nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<User,String>("Prenom"));
        email.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
        telephone.setCellValueFactory(new PropertyValueFactory<User,String>("telephone"));
        usernameaff.setCellValueFactory(new PropertyValueFactory<User,String>("username"));
        password.setCellValueFactory(new PropertyValueFactory<User,String>("password"));
          }
          else 
          {
               Alert alert =new Alert(Alert.AlertType.ERROR);
              alert.setTitle("vous avez creer compte!");
              alert.setHeaderText("Information");
              alert.setContentText("username ou email dejat exicte !!");
              alert.showAndWait();
              
          }
         
        }
        if (role_ajouter.getValue().toString().equals("Paysagist"))
             if( !validate(email_ajouter.getText()))
         {
             checkLabel.setText("verfier votre email !!");
             return;
         }
         
        { status=cs.ajouterPaysagist(c);
        if(status>0){
                nom_ajouter.setText("");
                prenom_ajouter.setText("");
                telephone_ajouter.setText("");
                email_ajouter.setText("");
                username_ajouter.setText("");
                password_ajouter.setText("");
              Alert alert =new Alert(Alert.AlertType.INFORMATION);
              alert.setTitle("vous avez creer compte!");
              alert.setHeaderText("Information");
              alert.setContentText("Compte bien Ajouter!");
              alert.showAndWait();
              ArrayList<User> arrayList = (ArrayList<User>)cs.afficherGlobal();
        ObservableList obs = FXCollections.observableArrayList(arrayList);
        table.setItems(obs);
        id.setCellValueFactory(new PropertyValueFactory<User,Integer>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<User,String>("Nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<User,String>("Prenom"));
        email.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
        telephone.setCellValueFactory(new PropertyValueFactory<User,String>("telephone"));
        usernameaff.setCellValueFactory(new PropertyValueFactory<User,String>("username"));
        password.setCellValueFactory(new PropertyValueFactory<User,String>("password"));
          }
          else 
          {
               Alert alert =new Alert(Alert.AlertType.ERROR);
              alert.setTitle("vous avez creer compte!");
              alert.setHeaderText("Information");
              alert.setContentText("username ou email dejat exicte !!");
              alert.showAndWait();
              
          }
        
        }
        
    }

    @FXML
    private void supprimer(ActionEvent event) {
          ObservableList<User> selectedRows, allCategorie ;
        allCategorie =table.getItems();
        selectedRows = table.getSelectionModel().getSelectedItems();
         AdminService as = new AdminService();
        for (User user : selectedRows){
            allCategorie.remove(user);
            as.supprimerJardinier(user);
        
    }
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
        list = (ArrayList<User>)as.afficherGlobal();
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
                        u.getEmail().contains(search.getText()) ||
                        u.getUsername().contains(search.getText())||
                        u.getPassword().contains(search.getText());
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
    private void telTyped(KeyEvent event) {
        String text = event.getCharacter() ;
        if(text.equals("")) return ;
        if(text.charAt(text.length()-1) < '0' || text.charAt(text.length()-1) > '9' ) {
            event.consume();
        }
    }
}
