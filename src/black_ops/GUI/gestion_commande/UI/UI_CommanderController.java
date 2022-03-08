/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI.gestion_commande.UI;

import black_ops.Controller.CommandeController;
import black_ops.GUI.gestion_commande.LigneCommande.Ligne_Commande_Main;
import black_ops.Controller.LigneCommController;
import black_ops.Entity.LigneComm;
import black_ops.Entity.Commande;
import black_ops.config.MaConnexion;
import java.io.IOException;

import java.net.URL;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import static javafx.scene.input.KeyCode.T;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.*;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class UI_CommanderController implements Initializable {

    @FXML
    private TextField id_defi;
    @FXML
    private TextField quantite_billet;
    @FXML
    private TextField id_client;
    @FXML
    private Button btn_passer;
    @FXML
    private Button btn_annuller;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void Passer_commande(ActionEvent event) throws SQLException {
        String Id_Client = id_client.getText();
        int idclient = Integer.parseInt(Id_Client);
        Commande c = new Commande(idclient, "commande passee");
        CommandeController cc = new CommandeController();
        cc.ajouterCommande(c);
        Connection cnx = MaConnexion.getInstance().getCnx();
         String sql="select * from commande";
          PreparedStatement ste=cnx.prepareStatement(sql);
     
            ResultSet rs=ste.executeQuery();
            int idc = 0;
            while(rs.next()){
                
                Commande c1 = new Commande();
                idc = rs.getInt(("id_Commande"));
                
            }
            
           String quantite = quantite_billet.getText();
            int Quantite = Integer.parseInt(quantite);
            String IdDefi = id_defi.getText();
            int ID_defi = Integer.parseInt(IdDefi);
            
            LigneComm lc = new LigneComm(ID_defi,idc,Quantite);
            LigneCommController lcc = new LigneCommController();
            lcc.ajouterLigneComm(lc);
            System.out.println(idc);
    
}

    @FXML
    private void Annuler_commande(ActionEvent event) {
        try {
           Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../UI/Annuller_Commande.fxml"));
        
        Scene scene = new Scene(root);
        primaryStage.setTitle("Annuller ma commande");
        primaryStage.setScene(scene);
        primaryStage.show();
            
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
       }
    }

}