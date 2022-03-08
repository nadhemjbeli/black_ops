/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI.Gestion_Competition.Gestion_Joueur;

import black_ops.Controller.Joueur_Controller;
import black_ops.Entity.Equipe;
import black_ops.Entity.Joueur;
import black_ops.config.MaConnexion;
import com.jfoenix.controls.JFXComboBox;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

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
    public static final String ACCOUNT_SID = "";

    public static final String AUTH_TOKEN = "";

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
    Connection mc;
    PreparedStatement ste;
    @FXML
    private ComboBox ListeR;
    @FXML
    private TextField inp_P;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Show_Joueur();
        fillcomboBox();
        ComboBoxListInti();

    }

    @FXML
    private void Modifier_Joueur(ActionEvent event) {
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
         
        if (inp_id.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("Vous devez selectionnez un id valid ");
            alert.showAndWait();
            return;
        }   
        if (inp_Nom.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("Vous devez selectionnez un Nom");
            alert.showAndWait();
            return;
        }
       // JOptionPane.showConfirmDialog(parentComponent, stage)
        Joueur J1 = new Joueur(4, Nom, rang, ps, k);
        int options = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(null, "Vous etes sure de Modifier   ce  Joueur " ,"SERIOUS QUESTION", options, 3);
        if (result == JOptionPane.YES_OPTION) {
          Jc.UpdateJoueur(J1);
        Show_Joueur();
        } else if (result == JOptionPane.NO_OPTION) {
            
           Show_Joueur();
        } 
      

    }

    @FXML
    private void Supprimer_Joueur(ActionEvent event) {
        String Id = inp_id.getText();
        int id = Integer.parseInt(Id);
        Joueur j = new Joueur(id);
        
        if (inp_id.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("Vous devez selectionnez un id valid");
            alert.showAndWait();
            return;
        }  
        
        int options = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(null, "Vous etes sure de Supprimer   ce  Joueur " ,"SERIOUS QUESTION", options, 3);
        if (result == JOptionPane.YES_OPTION) {
          Jc.DeleteJoueur(j);
        Show_Joueur();
        } else if (result == JOptionPane.NO_OPTION) {
            
           Show_Joueur();
        } 
       
         
    }

    @FXML
    private void Ajouter_Joueur(ActionEvent event) {
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
       
     
        Joueur J1 = new Joueur(4, Nom, rang, ps, k);
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
        try {
            String sql2="select nom_Equipe from equipe where id_equipe=?";
               
             mc=MaConnexion.getInstance().getCnx();
             ste=mc.prepareStatement(sql2);
             ste.setInt(1,Jr.getId_equipe());
              ResultSet rs=ste.executeQuery();
               while(rs.next()){
        String     k=rs.getString("nom_Equipe");
        ListeR.setValue(k);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        

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
        ListeR.setValue("");
    }

    @FXML
    private void refreshTable(ActionEvent event) {
        table1.setVisible(false);
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

    @FXML
    private void EnterRecherche(ActionEvent event) {
        String h = "";
        recherche.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    Recherche_Defi(event);
                    if (recherche.getText().equals(h)) {
                        table1.setVisible(false);
                    } else {
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
        switch (s) {
            case "Gestion Defi":
                SwitchScene(stage, "Gestion_Competition/Gestion_Defis/GestionDefi", event);
                break;
            case "Gestion Matches":
                SwitchScene(stage, "Gestion_Competition/Gestion_Details_Defis/GestionDetailsDefis", event);
                break;
            case "Gestion Joueurs":
                SwitchScene(stage, "Gestion_Competition/Gestion_Joueur/GestionJoueur", event);
                break;
            case "Gestion Equipe":
                SwitchScene(stage, "Gestion_Competition/Gestion_Equipe/GestionEquipe", event);
                break;
        }

    }

    @FXML
    private void showU(ActionEvent event) throws IOException, Exception {
        SwitchScene(stage, "Gestion_Competition/Gestion_Defis/GestionDefi", event);
    }

    @FXML
    private void showCommande(ActionEvent event) throws Exception {
        String s = gCom.getSelectionModel().getSelectedItem().toString();
        switch (s) {
            case "Gestion Defi":
                SwitchScene(stage, "Gestion_Competition/Gestion_Defis/GestionDefi", event);
                break;
            case "Gestion Matches":
                SwitchScene(stage, "Gestion_Competition/Gestion_Details_Defis/GestionDetailsDefis", event);
                break;
            case "Gestion Joueurs":
                SwitchScene(stage, "Gestion_Competition/Gestion_Joueur/GestionJoueur", event);
                break;
            case "Gestion Equipe":
                SwitchScene(stage, "Gestion_Competition/Gestion_Equipe/GestionEquipe", event);
                break;
        }

    }

    @FXML
    private void showCat(ActionEvent event) throws Exception {
        String s = gcat.getSelectionModel().getSelectedItem().toString();
        switch (s) {
            case "Gestion Defi":
                SwitchScene(stage, "Gestion_Competition/Gestion_Defis/GestionDefi", event);
                break;
            case "Gestion Matches":
                SwitchScene(stage, "Gestion_Competition/Gestion_Details_Defis/GestionDetailsDefis", event);
                break;
            case "Gestion Joueurs":
                SwitchScene(stage, "Gestion_Competition/Gestion_Joueur/GestionJoueur", event);
                break;
            case "Gestion Equipe":
                SwitchScene(stage, "Gestion_Competition/Gestion_Equipe/GestionEquipe", event);
                break;
        }

    }

    @FXML
    private void showS(ActionEvent event) throws Exception {
        String s = gS.getSelectionModel().getSelectedItem().toString();
        switch (s) {
            case "Gestion Defi":
                SwitchScene(stage, "Gestion_Competition/Gestion_Defis/GestionDefi", event);
                break;
            case "Gestion Matches":
                SwitchScene(stage, "Gestion_Competition/Gestion_Details_Defis/GestionDetailsDefis", event);
                break;
            case "Gestion Joueurs":
                SwitchScene(stage, "Gestion_Competition/Gestion_Joueur/GestionJoueur", event);
                break;
            case "Gestion Equipe":
                SwitchScene(stage, "Gestion_Competition/Gestion_Equipe/GestionEquipe", event);
                break;
        }
    }

    @FXML
    private void showJ(ActionEvent event) throws Exception {
        String s = gJ.getSelectionModel().getSelectedItem().toString();
        switch (s) {
            case "Gestion Defi":
                SwitchScene(stage, "Gestion_Competition/Gestion_Defis/GestionDefi", event);
                break;
            case "Gestion Matches":
                SwitchScene(stage, "Gestion_Competition/Gestion_Details_Defis/GestionDetailsDefis", event);
                break;
            case "Gestion Joueurs":
                SwitchScene(stage, "Gestion_Competition/Gestion_Joueur/GestionJoueur", event);
                break;
            case "Gestion Equipe":
                SwitchScene(stage, "Gestion_Competition/Gestion_Equipe/GestionEquipe", event);
                break;
        }
    }

    public void SwitchScene(Stage stage, String nom, ActionEvent event) throws Exception {

        String path = "/black_ops/GUI/";

        FXMLLoader loader = new FXMLLoader(getClass().getResource(path + nom + ".fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void ComboBoxListInti() {
        nav.setVisible(false);
        ObservableList<String> GestionCommunaute = FXCollections.observableArrayList(" Gestion Message", "Gestion Video");
        gc.setItems(GestionCommunaute);
        ObservableList<String> GestionCompetition = FXCollections.observableArrayList("Gestion Defi", "Gestion Matches", "Gestion Joueurs", "Gestion Equipe");
        gcp.setItems(GestionCompetition);
        ObservableList<String> GestionCommande = FXCollections.observableArrayList("Gestion Commande", "Gestion  Ligne Commande");
        gCom.setItems(GestionCommande);
        ObservableList<String> GestionCategorie = FXCollections.observableArrayList("Gestion Categorie", "Gestion  SousCategorie");
        gcat.setItems(GestionCategorie);
        ObservableList<String> GestionStream = FXCollections.observableArrayList("Stream Replay ", "Stream Info ");
        gS.setItems(GestionStream);
        ObservableList<String> GestionJeu = FXCollections.observableArrayList("Gestion Jeu", "Gestion  Image", "Gestion  Skin", "Gestion  Champion");
        gJ.setItems(GestionJeu);
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

}
