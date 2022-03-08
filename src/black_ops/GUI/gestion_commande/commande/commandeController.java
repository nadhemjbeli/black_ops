/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI.gestion_commande.commande;

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
import java.sql.Timestamp;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TimerTask;
import javafx.application.Platform;
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
public class commandeController implements Initializable {

    @FXML
    private Button btn_update;
    @FXML
    private Button btn_sup;
    @FXML
    private Button btn_insert;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField inp_Id_commande;
    @FXML
    private TextField inp_date_commande;
    @FXML
    private TextField inp_etat_commande;   
    @FXML
    private TextField inp_ID_client;
    @FXML
    private TableView<Commande> table;
    @FXML
    private TableColumn<Commande, Integer> gc_id_commande;
    @FXML
    private TableColumn<Commande, Date> gc_date_commande;
    @FXML
    private TableColumn<Commande, String> gc_etat_commande;
    @FXML
    private TableColumn<Commande, Integer> gc_id_client;
    @FXML
    private TextField search;
    @FXML
    private Button btn_refresh;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Show_commande();
      rechercheEq();
    }    

    @FXML
    private void Modifier_Commande(ActionEvent event) {
        
            try {
            String Id_Commande = inp_Id_commande.getText();
            int idcmd = Integer.parseInt(Id_Commande);
            String Id_Client = inp_ID_client.getText();
            int idclient = Integer.parseInt(Id_Client);
            String etat_commande = inp_etat_commande.getText();
            String d = inp_date_commande.getText();
            Date date = Date.valueOf(d);
            
            Commande c = new Commande(idcmd,idclient, etat_commande,date);
            CommandeController cc = new CommandeController();
            cc.updateCommande(c,idcmd);
            System.out.println("hello");
            Show_commande();
            }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }

    @FXML
    private void Supprimer_Commande(ActionEvent event) {
        try {
         String Id_Commande;
        Id_Commande = inp_Id_commande.getText();
         int idc = Integer.parseInt(Id_Commande);
            
            
            Connection cnx = MaConnexion.getInstance().getCnx();
            String sql="select * from lignecommande";
            PreparedStatement ste=cnx.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            int idc1 = 0,idlc1=0;
            while(rs.next()){
                idc1 = rs.getInt("id_commande");
                idlc1=rs.getInt("id_LigneCommande");
                if( idc1 == idc){
                    LigneComm lc1 = new LigneComm(idlc1);
                   LigneCommController lcc = new LigneCommController();
                   lcc.supprimerLigneComm(idlc1);   
                }
                 LigneComm lc1 = new LigneComm();   
            }
            Commande c = new Commande(idc);
            CommandeController cc = new CommandeController();
            cc.supprimerCommande(idc);
            
            
           
           Show_commande();
           }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void Ajouter_Commande(ActionEvent event) {
            try {
            String Id_Commande = inp_Id_commande.getText();
            int idcmd = Integer.parseInt(Id_Commande);
            String Id_Client = inp_ID_client.getText();
            int idclient = Integer.parseInt(Id_Client);
            String etat_commande = inp_etat_commande.getText();
            String d = inp_date_commande.getText();
            Date date = Date.valueOf(d);
            
            Commande c = new Commande(idcmd,idclient, etat_commande,date);
            CommandeController cc = new CommandeController();
            cc.ajouterCommande(c);
            System.out.println("hello");
            Show_commande();
            
             
        try {
            Stage primaryStage = new Stage();
             Parent root = FXMLLoader.load(getClass().getResource("../LigneCommande/Ligne_Commande.fxml"));
        
        Scene scene = new Scene(root);
        
        primaryStage.setScene(scene);
        primaryStage.show();
            
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
            }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
    
        Commande c = table.getSelectionModel().getSelectedItem();
        inp_Id_commande.setText(""+c.getId());
        inp_date_commande.setText(""+c.getDate());
        inp_etat_commande.setText(""+c.getEtat());
        inp_ID_client.setText(""+c.getId_cl());
        
        
        
    }
    public void Show_commande(){
     CommandeController cc = new CommandeController();
     ObservableList <Commande> list =  cc.afficherCommande();
    //private TableColumn<Commande, Integer> gc_id_commande;
    //private TableColumn<Commande, Date> gc_date_commande;
    //private TableColumn<Commande, String> gc_etat_commande;
    //private TableColumn<Commande, Integer> gc_id_client;
     gc_id_commande.setCellValueFactory(new PropertyValueFactory<Commande,Integer>("id"));
     gc_date_commande.setCellValueFactory(new PropertyValueFactory<Commande,Date>("date") );
     gc_etat_commande.setCellValueFactory(new PropertyValueFactory<Commande,String>("etat") );
     gc_id_client.setCellValueFactory(new PropertyValueFactory<Commande,Integer>("id_cl") );
     table.setItems(list);
    }
            private void rechercheEq() {
     //gc_id_commande.setCellValueFactory(new PropertyValueFactory<Commande,Integer>("id"));
     //gc_date_commande.setCellValueFactory(new PropertyValueFactory<Commande,Date>("date") );
     //gc_etat_commande.setCellValueFactory(new PropertyValueFactory<Commande,String>("etat") );
     //gc_id_client.setCellValueFactory(new PropertyValueFactory<Commande,Integer>("id_cl") );        
       search.textProperty().addListener(new InvalidationListener() {
           

        @Override
        public void invalidated(Observable observable) {
            CommandeController cc = new CommandeController();
            ObservableList <Commande> list =  cc.afficherCommande();
    
            gc_id_commande.setCellValueFactory(new PropertyValueFactory<Commande,Integer>("id"));
            gc_date_commande.setCellValueFactory(new PropertyValueFactory<Commande,Date>("date") );
            gc_etat_commande.setCellValueFactory(new PropertyValueFactory<Commande,String>("etat") );
            gc_id_client.setCellValueFactory(new PropertyValueFactory<Commande,Integer>("id_cl") );
            table.setItems(list);
            if(search.textProperty().get().isEmpty()) {
                table.setItems(list);
                return;
            }
            ObservableList<Commande> tableItems = FXCollections.observableArrayList();
            ObservableList<TableColumn<Commande, ?>> cols = table.getColumns();
            for(int i=0; i<list.size(); i++) {

                for(int j=0; j<cols.size(); j++) {
                    TableColumn col = cols.get(j);
                    String cellValue = col.getCellData(list.get(i)).toString();
                    cellValue = cellValue.toLowerCase();
                    if(cellValue.contains(search.textProperty().get().toLowerCase())) {
                        tableItems.add(list.get(i));
                        break;
                    }                        
                }
            }
            table.setItems(tableItems);

        }

           
    });
           
}

    

    @FXML
    private void Refresh_Table(ActionEvent event) {
          final TableColumn< Commande, ? > firstColumn = table.getColumns().get( 0 );
  firstColumn.setVisible( false );
  new java.util.Timer().schedule( new TimerTask() { @Override public void run() {
     Platform.runLater( new Runnable() { @Override public void run() {
        firstColumn.setVisible( true  ); }});
     }}, 100 );
    }
    }
            

    
    
    
    
    

  


    

     
     
     


