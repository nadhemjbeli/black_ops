/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI.Gestion_Competition.Gestion_Details_Defis;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author medaz
 */
public class GestionDetailsDefis extends Application {
    
    @Override
    
    public void start(Stage primaryStage)throws Exception {
      Parent root = FXMLLoader.load(getClass().getResource("GestionDetailsDefis.fxml"));
        
        Scene scene = new Scene(root,1300,720);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
