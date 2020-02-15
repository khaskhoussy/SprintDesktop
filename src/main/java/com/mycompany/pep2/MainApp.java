package com.mycompany.pep2;

import Service.ClientService;
import entits.Client;
import java.sql.Connection;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import fxml.Singleton;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/home.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("JavaFX and Maven");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      Connection myConn0 = Singleton.getInstancesingleton()
            .getConnection();
        launch(args);
        /*Client c = new Client("ammar", "khaskhoussy", "ammar@khaskhosy", "password", "ammarkh");
        ClientService sc=new ClientService();
        sc.creerCompte(c);*/
        
    }

}
