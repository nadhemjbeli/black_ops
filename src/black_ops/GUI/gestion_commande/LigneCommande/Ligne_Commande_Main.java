/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI.gestion_commande.LigneCommande;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author asus
 */
public class Ligne_Commande_Main extends Application {
    
    @Override
    public void start(Stage primaryStage)  {
       
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Ligne_Commande.fxml"));
            
            Scene scene = new Scene(root);
            primaryStage.setTitle("Black Ops");
            primaryStage.setScene(scene);
            primaryStage.show();
            
         }catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
