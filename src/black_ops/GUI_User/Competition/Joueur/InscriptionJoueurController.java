/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI_User.Competition.Joueur;

import black_ops.Controller.EquipeController;
import black_ops.Controller.Joueur_Controller;
import black_ops.Entity.Equipe;
import black_ops.Entity.Joueur;
import static black_ops.GUI.Gestion_Competition.Gestion_Joueur.GestionJoueurController.ACCOUNT_SID;
import static black_ops.GUI.Gestion_Competition.Gestion_Joueur.GestionJoueurController.AUTH_TOKEN;
import black_ops.GUI.Gestion_Competition.Gestion_Joueur.Pair;
import black_ops.GUI_User.Competition.Match.DetailController;
import black_ops.GUI_User.Competition.Match.Recherche;
import black_ops.config.MaConnexion;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author medaz
 */
public class InscriptionJoueurController implements Initializable {

    @FXML
    private TextField search;
    @FXML
    private VBox add;
    @FXML
    private TextField inp_Nom;
    @FXML
    private ComboBox  ListeR;
    @FXML
    private Button btn_insert;
    @FXML
    private VBox list;
    @FXML
    private TextField inp_P;
    @FXML
    private TextField inp_Rang;
    
    Connection mc;
    PreparedStatement ste;
     public static final String ACCOUNT_SID = "";

    public static final String AUTH_TOKEN = "";

    Joueur_Controller Jc = new Joueur_Controller();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillcomboBox();
        
    }    

    @FXML
    private void EnterRecherche(ActionEvent event) {
    }

    @FXML
    private void fillcomboBox() {
         mc = MaConnexion.getInstance().getCnx();
        try {

            ObservableList options2 = FXCollections.observableArrayList();

            String sql2 = "select nom_Equipe from Equipe";
            ResultSet rs = mc.createStatement().executeQuery(sql2);
            while (rs.next()) {
                options2.add(rs.getString(1));
            }
            ListeR.setItems(options2);
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    @FXML
    private void Ajouter_Equipe(ActionEvent event ) {
        int k = 0;
        String id_eq = ListeR.getSelectionModel().getSelectedItem().toString();
        String Nom = inp_Nom.getText();
        String rang = inp_Rang.getText();

        String ps = inp_P.getText();

        try {

            String sql2 = "select id_equipe from equipe where nom_Equipe=?";

            mc = MaConnexion.getInstance().getCnx();
            ste = mc.prepareStatement(sql2);

            ste.setString(1, id_eq);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                k = rs.getInt("id_equipe");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        Joueur J1 = new Joueur(4, Nom, rang, ps, k);
        if (ListeR.getSelectionModel().getSelectedItem().toString().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("Vous devez selectionnez une Equipe");
            alert.showAndWait();
            return;
        }
       
        
        
         if (inp_Rang.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("Vous devez selectionnez un Rang");
            alert.showAndWait();
            return;
        }
        if (inp_P.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("Vous devez selectionnez un pseaudo valid ");
            alert.showAndWait();
            return;
        }
        Jc.ajouterJoueur(J1);
        
        Show_Joueur();
        SendSms();

        
        
    }
     public void SendSms() {
        String nom = inp_Nom.getText();
        String id_eq = ListeR.getSelectionModel().getSelectedItem().toString();
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(new com.twilio.type.PhoneNumber("+21655226248"),
                new com.twilio.type.PhoneNumber("+18623977436"),
                " félicitation " + nom + " vous etes inscirt dans l'equipe " + id_eq).create();

        System.out.println(message.getSid());
    }
      
      public void Show_Joueur(){
         Joueur_Controller Ec = new Joueur_Controller();
         Joueur eq = Ec.Select();
         inp_Nom.setText(eq.getNom_Joueur());
         inp_Rang.setText(eq.getRang_Joueur());
         inp_P.setText(""+eq.getPseaudo_Joueur());  
         btn_insert.setDisable(true);
         try {
            String sql2="select nom_Equipe from equipe where id_equipe=?";
               
             mc=MaConnexion.getInstance().getCnx();
             ste=mc.prepareStatement(sql2);
             ste.setInt(1,eq.getId_equipe());
              ResultSet rs=ste.executeQuery();
               while(rs.next()){
                String k = rs.getString("nom_Equipe");
                ListeR.setValue(k);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
         String nom   = ListeR.getSelectionModel().getSelectedItem().toString();
         Show_Joueurs(nom);
} 
      public void Show_Joueurs(String nom){
          nom   = ListeR.getSelectionModel().getSelectedItem().toString();
           ObservableList<Pair> match = Jc.RechercheAV(nom);
           System.out.println(match);
           try {
        final VBox vb = new VBox();
        final ScrollPane sp = new ScrollPane();
        vb.setAlignment(Pos.CENTER);
        list.getChildren().add(sp);
        list.setAlignment(Pos.CENTER);
      
            for (Pair p : match) {
                
               
                VBox vb1 = new VBox();
                VBox vb2 = new VBox();
                VBox.setVgrow(sp, Priority.ALWAYS);
                HBox.setHgrow(sp, Priority.ALWAYS);
                HBox hb = new HBox();
                    Label titre1 = new Label("Joueur"); 
                    Label titre2 = new Label("Equipe");            
                    Label J = new Label("hey");
                    Label E = new Label("hey");
                    vb1.getChildren().add(titre1);
                    vb1.getChildren().add(J);
                    vb2.getChildren().add(titre2);
                    vb2.getChildren().add(E);
                    hb.getChildren().add(vb1);
                    hb.getChildren().add(vb2);
                    vb.getChildren().add(hb);
                
            }
        sp.setPrefSize(600, 600);
        sp.setContent(vb);
           
           
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
      }
}
