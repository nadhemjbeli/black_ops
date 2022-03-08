/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI.gestion_commande.UI;

import java.io.IOException;
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
 * @author asus
 */
public class UI_Main extends Application {
    
    @Override
    public void start(Stage primaryStage)  {
        try{
        Parent root = FXMLLoader.load(getClass().getResource("UI_Commander.fxml"));
        
        Scene scene = new Scene(root);
        primaryStage.setTitle("user interface");
        primaryStage.setScene(scene);
        primaryStage.show();
    }catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
        
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
