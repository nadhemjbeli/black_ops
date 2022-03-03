/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI.Gestion_Competition.Gestion_Joueur;

import black_ops.Controller.Joueur_Controller;
import black_ops.Entity.Equipe;
import black_ops.Entity.Joueur;
import com.jfoenix.controls.JFXComboBox;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
    private TableColumn<Joueur, Integer> cl_id;
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
    private Button btn_refresh;
    public static final String ACCOUNT_SID = "AC05bff866418daf2f594de475cdd43ee8";

    public static final String AUTH_TOKEN = "87e10d3329e3e895db98826290377bf5";
    
    Joueur_Controller Jc = new Joueur_Controller();
    @FXML
    private TableView<Pair> table1;
    @FXML
    private TableColumn<Pair, String> cl_nom_j;
    @FXML
    private TableColumn<Pair, String> cl_nom_eq;
    @FXML
    private Button show;
    @FXML
    private AnchorPane nav;
    @FXML
    private JFXComboBox gc;
    @FXML
    private JFXComboBox gcp;
    @FXML
    private Button gu;
    @FXML
    private JFXComboBox gCom;
    @FXML
    private JFXComboBox gcat;
    @FXML
    private JFXComboBox gS;
    @FXML
    private JFXComboBox gJ;
    @FXML
    private Button hide;
 private Stage stage;
 private Scene scene;
 private Parent root;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Show_Joueur();
       
        ComboBoxListInti();

    }

    @FXML
    private void Modifier_Joueur(ActionEvent event) {
        String Id = inp_id.getText();
        String Nom = inp_Nom.getText();
        String rang = inp_Rang.getText();
        String eq = inp_Eq.getText();
        String ps = inp_P.getText();
        int id_eq = Integer.parseInt(eq);
        int id = Integer.parseInt(Id);
        Joueur J1 = new Joueur(id, Nom, rang, ps, id_eq);
        Jc.UpdateJoueur(J1);
        Show_Joueur();
        table1.setVisible(false);

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
        String eq = inp_Eq.getText();
        String ps = inp_P.getText();
        int id_eq = Integer.parseInt(eq);
        Joueur J1 = new Joueur(4, Nom, rang, ps, id_eq);
        Jc.ajouterJoueur(J1);
        Show_Joueur();
        SendSms();
    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
        Joueur Jr = table.getSelectionModel().getSelectedItem();
        inp_id.setText("" + Jr.getId_Joueur());
        inp_Nom.setText("" + Jr.getNom_Joueur());
        inp_Rang.setText("" + Jr.getRang_Joueur());
        inp_P.setText("" + Jr.getPseaudo_Joueur());
        inp_Eq.setText("" + Jr.getId_equipe());
    }

    @FXML
    private void Recherche_Defi(ActionEvent event) {
        String srsh = recherche.getText();
        ObservableList<Pair> list = Jc.RechercheAV(srsh);

        cl_nom_j.setCellValueFactory(new PropertyValueFactory<Pair, String>("jj"));
        cl_nom_eq.setCellValueFactory(new PropertyValueFactory<Pair, String>("ee"));
        System.out.println(list);
        table1.setItems(list);

    }

    private void Show_Joueur() {
        ObservableList<Joueur> list = Jc.afficherJoueur();
        cl_id.setCellValueFactory(new PropertyValueFactory<Joueur, Integer>("id_Joueur"));
        cl_nom.setCellValueFactory(new PropertyValueFactory<Joueur, String>("nom_Joueur"));
        cl_rang.setCellValueFactory(new PropertyValueFactory<Joueur, String>("rang_Joueur"));
        cl_P.setCellValueFactory(new PropertyValueFactory<Joueur, String>("Pseaudo_Joueur"));
        cl_eq.setCellValueFactory(new PropertyValueFactory<Joueur, Integer>("id_equipe"));
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
        table1.setVisible(false);
    }

    public void SendSms() {
        String nom = inp_Nom.getText();
        String eq = inp_Eq.getText();
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(new com.twilio.type.PhoneNumber("+21655226248"),
                new com.twilio.type.PhoneNumber("+18623977436"),
                " félicitation " + nom + " vous etes inscirt dans l'equipe " + eq).create();

        System.out.println(message.getSid());
    }

    @FXML
    private void EnterRecherche(ActionEvent event) {
         String h = "";
        recherche.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    Recherche_Defi(event);
                    if(recherche.getText().equals(h)){
            table1.setVisible(false);
        }else{
        table1.setVisible(true);
        }
                }
            }
        });
    }

   @FXML
    private void show_nav_bar(ActionEvent event) {
     nav.setVisible(true); 
     hide.setVisible(true);
     show.setVisible(false);
     
    }

    @FXML
    private void hide_nav_bar(ActionEvent event) {
        nav.setVisible(false); 
     hide.setVisible(false);
     show.setVisible(true);
    }

    @FXML
    private void showGC(ActionEvent event) {
        String s = gc.getSelectionModel().getSelectedItem().toString();
        
    }

    @FXML
    private void showComp(ActionEvent event) throws Exception {
        
        String s = gcp.getSelectionModel().getSelectedItem().toString();
//       
        switch(s){
            case "Gestion Defi" :
                 SwitchScene(stage,"Gestion_Competition/Gestion_Defis/GestionDefi",event);
                 break ;
            case "Gestion Matches" :
                    SwitchScene(stage,"Gestion_Competition/Gestion_Details_Defis/GestionDetailsDefis",event);
           break ; 
            case "Gestion Joueurs" :
                  SwitchScene(stage,"Gestion_Competition/Gestion_Joueur/GestionJoueur",event);
           break ;
            case "Gestion Equipe":
                SwitchScene(stage,"Gestion_Competition/Gestion_Equipe/GestionEquipe",event);
                break ;
        }
   
    }

    @FXML
    private void showU(ActionEvent event) throws IOException, Exception {
        SwitchScene(stage,"Gestion_Competition/Gestion_Defis/GestionDefi",event);
        }  
        
    

    @FXML
    private void showCommande(ActionEvent event) throws Exception {
        String s = gCom.getSelectionModel().getSelectedItem().toString();
        switch(s){
            case "Gestion Defi" :
                 SwitchScene(stage,"Gestion_Competition/Gestion_Defis/GestionDefi",event);
                 break ;
            case "Gestion Matches" :
                    SwitchScene(stage,"Gestion_Competition/Gestion_Details_Defis/GestionDetailsDefis",event);
           break ; 
            case "Gestion Joueurs" :
                  SwitchScene(stage,"Gestion_Competition/Gestion_Joueur/GestionJoueur",event);
           break ;
            case "Gestion Equipe":
                SwitchScene(stage,"Gestion_Competition/Gestion_Equipe/GestionEquipe",event);
                break ;
        }
        
    }

    @FXML
    private void showCat(ActionEvent event) throws Exception {
        String s = gcat.getSelectionModel().getSelectedItem().toString();
        switch(s){
            case "Gestion Defi" :
                 SwitchScene(stage,"Gestion_Competition/Gestion_Defis/GestionDefi",event);
                 break ;
            case "Gestion Matches" :
                    SwitchScene(stage,"Gestion_Competition/Gestion_Details_Defis/GestionDetailsDefis",event);
           break ; 
            case "Gestion Joueurs" :
                  SwitchScene(stage,"Gestion_Competition/Gestion_Joueur/GestionJoueur",event);
           break ;
            case "Gestion Equipe":
                SwitchScene(stage,"Gestion_Competition/Gestion_Equipe/GestionEquipe",event);
                break ;
        }
        
    }

    @FXML
    private void showS(ActionEvent event) throws Exception {
        String s = gS.getSelectionModel().getSelectedItem().toString();
        switch(s){
            case "Gestion Defi" :
                 SwitchScene(stage,"Gestion_Competition/Gestion_Defis/GestionDefi",event);
                 break ;
            case "Gestion Matches" :
                    SwitchScene(stage,"Gestion_Competition/Gestion_Details_Defis/GestionDetailsDefis",event);
           break ; 
            case "Gestion Joueurs" :
                  SwitchScene(stage,"Gestion_Competition/Gestion_Joueur/GestionJoueur",event);
           break ;
            case "Gestion Equipe":
                SwitchScene(stage,"Gestion_Competition/Gestion_Equipe/GestionEquipe",event);
                break ;
        }
    }

    @FXML
    private void showJ(ActionEvent event) throws Exception {
        String s = gJ.getSelectionModel().getSelectedItem().toString();
        switch(s){
            case "Gestion Defi" :
                 SwitchScene(stage,"Gestion_Competition/Gestion_Defis/GestionDefi",event);
                 break ;
            case "Gestion Matches" :
                    SwitchScene(stage,"Gestion_Competition/Gestion_Details_Defis/GestionDetailsDefis",event);
           break ; 
            case "Gestion Joueurs" :
                  SwitchScene(stage,"Gestion_Competition/Gestion_Joueur/GestionJoueur",event);
           break ;
            case "Gestion Equipe":
                SwitchScene(stage,"Gestion_Competition/Gestion_Equipe/GestionEquipe",event);
                break ;
        }
    }
  
    public void SwitchScene(Stage stage ,String nom ,ActionEvent event ) throws Exception {
        
        String path = "/black_ops/GUI/" ;
        
    FXMLLoader loader = new FXMLLoader(getClass().getResource(path+nom+".fxml"));
            Parent root = loader.load();
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
     public void ComboBoxListInti(){
       nav.setVisible(false);  
       ObservableList<String> GestionCommunaute = FXCollections.observableArrayList(" Gestion Message" ,"Gestion Video");
       gc.setItems(GestionCommunaute);
       ObservableList<String> GestionCompetition = FXCollections.observableArrayList("Gestion Defi" ,"Gestion Matches" ,"Gestion Joueurs","Gestion Equipe");
       gcp.setItems(GestionCompetition);
       ObservableList<String> GestionCommande = FXCollections.observableArrayList("Gestion Commande" ,"Gestion  Ligne Commande" );
       gCom.setItems(GestionCommande);
       ObservableList<String> GestionCategorie = FXCollections.observableArrayList("Gestion Categorie" ,"Gestion  SousCategorie" );
       gcat.setItems(GestionCategorie);
        ObservableList<String> GestionStream = FXCollections.observableArrayList("Stream Replay " ,"Stream Info " );
       gS.setItems(GestionStream);
        ObservableList<String> GestionJeu = FXCollections.observableArrayList("Gestion Jeu" ,"Gestion  Image", "Gestion  Skin" ,"Gestion  Champion"  );
       gJ.setItems(GestionJeu);
    }

}
