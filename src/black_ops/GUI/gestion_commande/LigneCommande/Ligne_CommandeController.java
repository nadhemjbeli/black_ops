/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI.gestion_commande.LigneCommande;

import black_ops.Controller.LigneCommController;
import black_ops.Entity.LigneComm;

import java.util.Date;
import java.text.ParseException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.PropertySheet.Item;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class Ligne_CommandeController implements Initializable {

    @FXML
    private Button btn_update;
    @FXML
    private Button btn_sup;
    @FXML
    private Button btn_insert;
    @FXML
    private TextField inp_ID_LC;
    @FXML
    private TextField inp_quantite;
    @FXML
    private TextField inp_prix;
    @FXML
    private TextField inp_Id_defi;
    @FXML
    private TextField inp_Id_commande;
    @FXML
    private TableView<LigneComm> table;
    @FXML
    private TableColumn<LigneComm, Integer> glc_ID_Lcommande;
    @FXML
    private TableColumn<LigneComm,Integer> glc_quantite;
    @FXML
    private TableColumn<LigneComm,Integer> glc_prix;
    @FXML
    private TableColumn<LigneComm,Integer> glc_id_defi;
    @FXML
    private TableColumn<LigneComm,Integer> glc_idc;
    private TextField search;
    @FXML
    private TextField Search;
    @FXML
    private Button btn_refresh;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Show_ligne_commande();
        rechercheEq();
    }    

    @FXML
    private void Modifier_Ligne_Commande(ActionEvent event) {
      try {
            String Id_LC = inp_ID_LC.getText();
            int idLC = Integer.parseInt(Id_LC);
            
            String quantite = inp_quantite.getText();
            int Quantite = Integer.parseInt(quantite);
            
            
            String prix = inp_prix.getText();
            int Prix = Integer.parseInt(prix);
            
            String IdDefi = inp_Id_defi.getText();
            int ID_defi = Integer.parseInt(IdDefi);
            
            String idc = inp_Id_commande.getText();
            int idC = Integer.parseInt(idc);
            
            LigneComm lc = new LigneComm(idLC,ID_defi,idC,Prix,Quantite);
            LigneCommController lcc = new LigneCommController();
            lcc.updateLigneComm(lc,idLC);
            System.out.println("hello");
            Show_ligne_commande();
            }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void Supprimer_Ligne_Commande(ActionEvent event) {
        try {
    String Id_Lcommande = inp_ID_LC.getText();
         int idlc = Integer.parseInt(Id_Lcommande);
            LigneComm lc = new LigneComm(idlc);
            LigneCommController lcc = new LigneCommController();
            lcc.supprimerLigneComm(idlc);
            Show_ligne_commande();
               
                        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        
        
    }

    @FXML
    private void Ajouter_Ligne_Commande(ActionEvent event) {
        try {
             String Id_LC = inp_ID_LC.getText();
            int idLC = Integer.parseInt(Id_LC);
            
            String quantite = inp_quantite.getText();
            int Quantite = Integer.parseInt(quantite);
            
            
            String prix = inp_prix.getText();
            int Prix = Integer.parseInt(prix);
            
            String IdDefi = inp_Id_defi.getText();
            int ID_defi = Integer.parseInt(IdDefi);
            
            String idc = inp_Id_commande.getText();
            int idC = Integer.parseInt(idc);
            
            LigneComm lc = new LigneComm(idLC,ID_defi,idC,Prix,Quantite);
            LigneCommController lcc = new LigneCommController();
            lcc.ajouterLigneComm(lc);
            Show_ligne_commande();
            System.out.println("hello");
                    }catch(Exception e){
            System.out.println(e.getMessage());
        }
       
    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
        LigneComm lc = table.getSelectionModel().getSelectedItem();
       // private TextField inp_ID_LC;
    //private TextField inp_quantite;
    //private TextField inp_prix;
    //private TextField inp_Id_defi;
    //private TextField inp_Id_commande;
        inp_ID_LC.setText(""+lc.getId());
        inp_quantite.setText(""+lc.getQuantite());
        inp_prix.setText(""+lc.getPrix());
        inp_Id_defi.setText(""+lc.getId_defi());
        inp_Id_commande.setText(""+lc.getId_commande());
        
    }
    
    public void Show_ligne_commande(){
     LigneCommController lcc = new LigneCommController();
     ObservableList<LigneComm> list =  lcc.afficherLigneComm();
    
     
     glc_ID_Lcommande.setCellValueFactory(new PropertyValueFactory<LigneComm,Integer>("id"));
     glc_quantite.setCellValueFactory(new PropertyValueFactory<LigneComm,Integer>("quantite") );
     glc_prix.setCellValueFactory(new PropertyValueFactory<LigneComm,Integer>("prix") );
     glc_id_defi.setCellValueFactory(new PropertyValueFactory<LigneComm,Integer>("id_defi"));
     glc_idc.setCellValueFactory(new PropertyValueFactory<LigneComm,Integer>("id_commande"));
     
     table.setItems(list);
    }
           private void rechercheEq() {
            
       Search.textProperty().addListener(new InvalidationListener() {
           

        @Override
        public void invalidated(Observable observable) {
            LigneCommController lcc = new LigneCommController();
            ObservableList <LigneComm> list =  lcc.afficherLigneComm();
    
            glc_ID_Lcommande.setCellValueFactory(new PropertyValueFactory<LigneComm,Integer>("id"));
            glc_quantite.setCellValueFactory(new PropertyValueFactory<LigneComm,Integer>("quantite") );
            glc_prix.setCellValueFactory(new PropertyValueFactory<LigneComm,Integer>("prix") );
            glc_id_defi.setCellValueFactory(new PropertyValueFactory<LigneComm,Integer>("id_defi"));
            glc_idc.setCellValueFactory(new PropertyValueFactory<LigneComm,Integer>("id_commande"));
            table.setItems(list);
            if(Search.textProperty().get().isEmpty()) {
                table.setItems(list);
                return;
            }
            ObservableList<LigneComm> tableItems = FXCollections.observableArrayList();
            ObservableList<TableColumn<LigneComm,?>> cols = table.getColumns();
            for(int i=0; i<list.size(); i++) {

                for(int j=0; j<cols.size(); j++) {
                    TableColumn col = cols.get(j);
                    String cellValue = col.getCellData(list.get(i)).toString();
                    cellValue = cellValue.toLowerCase();
                    if(cellValue.contains(Search.textProperty().get().toLowerCase())) {
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
      final TableColumn< LigneComm, ? > firstColumn = table.getColumns().get( 0 );
  firstColumn.setVisible( false );
  new Timer().schedule( new TimerTask() { @Override public void run() {
     Platform.runLater( new Runnable() { @Override public void run() {
        firstColumn.setVisible( true  ); }});
     }}, 100 );
    }


    
    
}
