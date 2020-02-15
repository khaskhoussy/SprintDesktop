/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import Service.AdminService;
import Service.ServiceTreavaille;
import entits.ListeTravail;
import entits.User;
import fxml.HomeController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Service.ServiceTreavaille;
import java.util.ArrayList;
import java.util.List;
import Service.AdminService;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author aisce
 */
public class ListTravailleJarController implements Initializable {
private FileChooser filechooser;
    private File filePath;
    String path;
    private ListView ListTra;
    @FXML
    private DatePicker dateAj;
    @FXML
    private TextArea descriptionAj;
    int x=0;
    AdminService ads=new AdminService();
    public int idj = ads.modifId(HomeController.username);
    int z;

    /**
     * Initializes the controller class.
     */
    Connection c =Singleton.getInstancesingleton().getConnection();
    Statement ste;
    @FXML
    private ImageView imageAj;
    @FXML
    private TableColumn<ListeTravail, Integer> idja;
    @FXML
    private TableColumn<ListeTravail, String> dateja;
    @FXML
    private TableColumn<ListeTravail, String> descriptionja;
    @FXML
    private TableColumn<ListeTravail, String> imgja;
    @FXML
    private TableView<ListeTravail> Table;
    @FXML
    private Label descriptionaf;
    @FXML
    private ImageView imageaff;
    private File filepath;
    public ListTravailleJarController() {
        try {
            ste = c.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AdminService ads=new AdminService();
      ServiceTreavaille as = new ServiceTreavaille();
        ArrayList<ListeTravail> arrayList = (ArrayList<ListeTravail>)as.afficherTravail(ads.modifId(HomeController.username));
        ObservableList obs = FXCollections.observableArrayList(arrayList);
        Table.setItems(obs);
        idja.setCellValueFactory(new PropertyValueFactory<ListeTravail,Integer>("id"));
        dateja.setCellValueFactory(new PropertyValueFactory<ListeTravail,String>("dateTravail"));
        descriptionja.setCellValueFactory(new PropertyValueFactory<ListeTravail,String>("descriptionTravail"));
        imgja.setCellValueFactory(new PropertyValueFactory<ListeTravail,String>("image"));
        
        System.out.println("this is what you wait for"+ads.modifId(HomeController.username));
    }    

    private void affichage(ActionEvent event) {
        String txt=null;
        ServiceTreavaille st=new ServiceTreavaille();
        txt=st.afficherTravaille(idj);
        ListTra.getItems().addAll(txt);
        List <User> u = new ArrayList<>();
          u = null;
          AdminService cs = new AdminService();
          Connection c =Singleton.getInstancesingleton().getConnection();
         // u.addAll(cs.afficherJardinier());
          System.out.println("what are you waiting for is here"+u);
    }

    @FXML
    private void Deconnection(ActionEvent event) {
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
    private void retour(ActionEvent event) {
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
    private void ajouter(ActionEvent event) {
        AdminService as=new AdminService();
       
        User u = new User(0);
        String date;
        String descrp;
        
        int id;
                date=dateAj.getValue().toString();
                descrp=descriptionAj.getText();
                id=as.modifId(HomeController.username);
                u.setId(id);
                //ListeTravail lt = new ListeTravail(descrp, date, u);
                System.out.println(u);
                ServiceTreavaille se= new ServiceTreavaille();
                
                String txt=null;
                String txt1;
                Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        filechooser=new FileChooser();
        filechooser.setTitle("open Image");
        //this.filePath=filechooser.showOpenDialog(stage);
        //setUp default directory
        String userdirectoryS =System.getProperty("user.home");
        File  userDirectory = new File(userdirectoryS);
        if(!userDirectory.canRead())
        
            userDirectory=new File("c:/");
            filechooser.setInitialDirectory(userDirectory);
            this.filePath=filechooser.showOpenDialog(stage);
        //to change the photo
       try{
           BufferedImage bufferedImage=ImageIO.read(filePath);
           Image image=new Image(filePath.toURI().toString());
           path=filePath.toPath().toString();
           System.out.println(filePath.toURI().toString());
           /*Image image =SwingFXUtils.toFXImage(bufferedImage, null);*/
          // u.setImg(path);
          // AdminService ase=new AdminService();
         //  ase.ajouterimg(u, HomeController.username);          
          // imageAj.setImage(image);
       }
           catch(IOException e)
        
       {
           System.err.println(e.getMessage());
       }
                 
                ListeTravail lt = new ListeTravail(descrp, date,u,path);
                se.ajoutTravaille(lt);
        ServiceTreavaille st=new ServiceTreavaille();
        txt=st.afficherTravaille(idj);
        ListTra.getItems().add(txt.substring(z, txt.length()));
        
     
    }

    @FXML
    private void selected(MouseEvent event) {
        
        ListeTravail listeTravail = Table.getSelectionModel().getSelectedItem();
        descriptionaf.setText(listeTravail.getDescriptionTravail());
        String path;
        path=listeTravail.getImage();
        filePath = new File(path);
         Image image=new Image(filePath.toURI().toString());
         imageaff.setImage(image);
    }
    

    
}
 