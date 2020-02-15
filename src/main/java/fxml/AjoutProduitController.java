/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import Service.AdminService;
import entits.Categorie;
import entits.Produit;
import java.io.File;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Service.CategorieService;
import Service.ProduitService;
import entits.User;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class AjoutProduitController implements Initializable {

   
    @FXML
    private TextField nomprod;
    @FXML
    private TextField prixprod;
    @FXML
    private TextField description;
    @FXML
    private TextField quantite;
    private TextField image;
    
    @FXML
    private ComboBox<Categorie> combobox;
    @FXML
    private Button ajoutImage;
    private FileChooser filechooser;
    private File filePath;
    AdminService as=new AdminService();
   private String path;
   private String nomImage;
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CategorieService c=new CategorieService();
        ObservableList<Categorie> list  = FXCollections.observableArrayList(c.affichercategories());
        combobox.setItems(list);
        combobox.getSelectionModel().select(0);
    }    

    @FXML
    private void AjouterProduit(ActionEvent event) {
       /* Categorie categorie = combobox.getValue();
        System.out.println(categorie);
       Produit p = new Produit(nomprod.getText(),Float.parseFloat(prixprod.getText()),description.getText(),Integer.parseInt(quantite.getText()),nomImage,categorie.getId());
       
        ProduitService ps=new ProduitService();
        ps.creerProduit(p);*/
        
        
        
    }

    @FXML
    private void retour(ActionEvent event) {
         Parent frontPage = null; 
        try {
            frontPage = FXMLLoader.load(getClass().getResource("/fxml/Gestionproduit.fxml"));
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
    private void ajoutImage(ActionEvent event) throws FileNotFoundException, IOException {
        User u =new User();
        AdminService as= new AdminService();
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
             String fis3 = new String(filePath.getName());
             nomImage = fis3;
            System.out.println(fis3);
            try{
                            FileInputStream fis33 = new FileInputStream(this.filePath);
            } 
            catch (FileNotFoundException ex) {
                    Logger.getLogger(MonCompteController.class.getName()).log(Level.SEVERE, null, ex);
                }
            try
            {
                FileInputStream fis33 = new FileInputStream(this.filePath);
                InputStream is = fis33;
                String url2="C:\\wamp64\\www\\SprintWeb\\web\\images\\"+fis3;
                System.out.println(url2);
                OutputStream os = new FileOutputStream( new File(url2));
                byte[] content = new byte[2048];
                      int size = 0;
                     while((size = is.read(content)) != -1){
                          os.write(content, 0, size);
            }
            }
           catch (MalformedURLException ex) {
                          
                            Logger.getLogger(CompteJarController.class.getName()).log(Level.SEVERE, null, ex);
                        }
       Categorie categorie = combobox.getValue();
         Produit p = new Produit(nomprod.getText(),Float.parseFloat(prixprod.getText()),description.getText(),Integer.parseInt(quantite.getText()),nomImage,categorie.getId());
       
        ProduitService ps=new ProduitService();
        ps.creerProduit(p);
        
      
    }
    
}
