/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

//import com.jfoenix.controls.JFXDatePicker;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Service.SujetService;
import entits.Sujet;
import entits.User;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;



/**
 * FXML Controller class
 *
 * @author hp
 */
public class AjouterSujetForumController implements Initializable {

    @FXML
    private Label subject1 =new Label();
    @FXML
    private TextField title = new TextField();

    @FXML
    private TextArea description=new TextArea();
    @FXML 
    private ChoiceBox categorie=new ChoiceBox();
    @FXML
    private VBox pnItems;
    @FXML
    private Label Label;
    @FXML
    private Button Send;
    @FXML
    private Label subject;

     
  /**  private linkForumController linkForumController;
    public void setLinkForumController(linkForumController linkForumController) {
        this.linkForumController = linkForumController;
    }

**/
 

    @FXML
    private void Retour(ActionEvent event) throws IOException {
      Parent frontPage = null; 
      frontPage = FXMLLoader.load(getClass().getResource("/fxml/Forom.fxml"));
       Scene frontPageScene= new Scene(frontPage); 
        //to get the stage
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(frontPageScene);
        window.show();
    }
 
    void Ajouter(ActionEvent event) throws SQLException {
  /**CommentaireService postsService = new CommentaireService();
        if (!subject.getText().isEmpty()&&!description.getText().isEmpty())
        {**/
              
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList cat = categorie.getItems();
            cat.addAll("Plante","Accessoire","Divers");        
           /*categorie.setCellValueFactory(new PropertyValueFactory<Sujet, String>("Categorie"));
            DatePub.setCellValueFactory(new PropertyValueFactory<Sujet, Date>("postedOn"));
            description.setCellValueFactory(new PropertyValueFactory<Sujet, String>("description"));*/
    }

    @FXML
    private void Send(ActionEvent event) throws SQLException {
      String t, desc, req;
        t = title.getText();
        desc = description.getText();
        Date date = new Date();
        date = Calendar.getInstance().getTime();
        String mDate = date.toString();
        String mCat = categorie.getSelectionModel().getSelectedItem().toString();
            User user = new User();
            Sujet s = new Sujet();
            user.setId(HomeController.idU);
            s.setIdUser(user.getId());
            SujetService ss = new SujetService();
            Sujet p1 = new Sujet(t,s.getIdUser(),mCat,mDate,desc);
            ss.ajouterSujet(p1);  

        title.clear();
        description.clear();

    }
}
