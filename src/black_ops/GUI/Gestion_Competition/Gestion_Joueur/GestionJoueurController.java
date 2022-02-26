/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI.Gestion_Competition.Gestion_Joueur;

import black_ops.Controller.Joueur_Controller;
import black_ops.Entity.Joueur;
import java.net.URL;
import java.util.ResourceBundle;
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

/**
 * FXML Controller class
 *
 * @author medaz
 */
public class GestionJoueurController implements Initializable {

    @FXML
    private Button btn_update;
    @FXML
    private Button btn_sup;
    @FXML
    private Button btn_insert;
    @FXML
    private TextField inp_id;
    @FXML
    private TextField inp_Nom;
    @FXML
    private TextField inp_Rang;
    @FXML
    private TextField inp_P;
    @FXML
    private TextField inp_Eq;
    @FXML
    private TableView<Joueur> table;
    @FXML
    private TableColumn<Joueur,Integer> cl_id;
    @FXML
    private TableColumn<Joueur, String> cl_nom;
    @FXML
    private TableColumn<Joueur, String> cl_rang;
    @FXML
    private TableColumn<Joueur, String> cl_P;
    @FXML
    private TableColumn<Joueur, Integer> cl_eq;
    @FXML
    private TextField recherche;
    @FXML
    private Button btn_rech;

    @FXML
    private Button btn_diselect;
    @FXML
    private Button btn_refresh ;
   
    Joueur_Controller Jc = new Joueur_Controller(); 

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Show_Joueur();  
    } 
    @FXML
    private void Modifier_Joueur(ActionEvent event) {
            String Id  = inp_id.getText();
            String Nom = inp_Nom.getText();
            String rang = inp_Rang.getText();
            String eq = inp_Eq.getText() ;
            String ps = inp_P.getText();
            int id_eq = Integer.parseInt(eq);
            int id = Integer.parseInt(Id);
            Joueur J1 = new Joueur(id,Nom,rang,ps,id_eq) ;
            Jc.UpdateJoueur(J1);
            Show_Joueur();
        
    }

    @FXML
    private void Supprimer_Joueur(ActionEvent event) {
        String Id = inp_id.getText();
         int id = Integer.parseInt(Id);
         Joueur j = new Joueur(id);
         Jc.DeleteJoueur(j);
         Show_Joueur();
    }

    @FXML
    private void Ajouter_Joueur(ActionEvent event) {
             String Nom = inp_Nom.getText();
             String rang = inp_Rang.getText();
             String eq = inp_Eq.getText() ;
             String ps = inp_P.getText();
              int id_eq = Integer.parseInt(eq);
              Joueur J1 = new Joueur(4,Nom,rang,ps,id_eq) ;
               Jc.ajouterJoueur(J1);
               Show_Joueur();
        
    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
        Joueur Jr = table.getSelectionModel().getSelectedItem();
        inp_id.setText(""+Jr.getId_Joueur());
        inp_Nom.setText(""+Jr.getNom_Joueur());
        inp_Rang.setText(""+Jr.getRang_Joueur());
        inp_P.setText(""+Jr.getPseaudo_Joueur());
        inp_Eq.setText(""+Jr.getId_equipe());
    }

    @FXML
    private void Recherche_Defi(ActionEvent event) {
    }
    
    private void Show_Joueur(){
    ObservableList<Joueur> list = Jc.afficherJoueur() ; 
     cl_id.setCellValueFactory(new PropertyValueFactory<Joueur,Integer>("id_Joueur"));
     cl_nom.setCellValueFactory(new PropertyValueFactory<Joueur,String>("nom_Joueur") );
     cl_rang.setCellValueFactory(new PropertyValueFactory<Joueur,String>("rang_Joueur") );
     cl_P.setCellValueFactory(new PropertyValueFactory<Joueur,String>("Pseaudo_Joueur") );
     cl_eq.setCellValueFactory(new PropertyValueFactory<Joueur,Integer>("id_equipe") );
     table.setItems(list);
    }

    @FXML
    private void disselect(ActionEvent event) {
        inp_id.setText("");
        inp_Nom.setText("");
        inp_Rang.setText("");
        inp_P.setText("");
        inp_Eq.setText("");
    }

    @FXML
    private void refreshTable(ActionEvent event) {
    }
    
}
