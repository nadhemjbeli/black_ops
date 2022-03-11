/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI_User.Competition.Joueur;

import black_ops.Controller.EquipeController;
import black_ops.Controller.Joueur_Controller;
import black_ops.Controller.Nav_barController;
import black_ops.Entity.Equipe;
import black_ops.Entity.Joueur;
import black_ops.Entity.Nav_Bar;
import static black_ops.GUI.Gestion_Competition.Gestion_Joueur.GestionJoueurController.ACCOUNT_SID;
import static black_ops.GUI.Gestion_Competition.Gestion_Joueur.GestionJoueurController.AUTH_TOKEN;
import black_ops.GUI.Gestion_Competition.Gestion_Joueur.Pair;
import black_ops.GUI_User.Competition.Match.DetailController;
import black_ops.GUI_User.Competition.Match.Recherche;
import black_ops.config.MaConnexion;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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
import javafx.scene.Node;
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
import javafx.scene.input.MouseEvent;
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
     public static final String ACCOUNT_SID = "AC05bff866418daf2f594de475cdd43ee8";

    public static final String AUTH_TOKEN = "87e10d3329e3e895db98826290377bf5";

    Joueur_Controller Jc = new Joueur_Controller();
    @FXML
    private ComboBox ComboStream;
    @FXML
    private ComboBox ComboCompétition;
    @FXML
    private ComboBox ComboCommunaute;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private ComboBox ComboJeux;
    @FXML
    private FontAwesomeIconView ComboUser;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillcomboBox();
         ComboBoxListInti();
         Navbar();
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
      
    @FXML
    private void showComp(ActionEvent event) throws Exception {

        String s = ComboCompétition.getSelectionModel().getSelectedItem().toString();
        
        switch (s) {
            case "Defi":
                SwitchScene(stage, "Competition/Defi/Defi", event);
                break;
            case "Match":
                SwitchScene(stage, "Competition/Match/ListMatch", event);
                break;
            case "Joueur":
                SwitchScene(stage, "Competition/Joueur/InscriptionJoueur", event);
                break;
            case "Equipe":
                SwitchScene(stage, "Competition/Equipe/InscriptionEquipe", event);
                break;
        }

    }

    @FXML
    private void Showj(ActionEvent event) throws IOException {
        try {
            String s = ComboJeux.getSelectionModel().getSelectedItem().toString();
            switch (s){
                case"jeux":
                    SwitchScene(stage, "Jeux/gestionjeuxVidéos/jeux", event);
                    break;
            }
//            root = FXMLLoader.load(getClass().getResource("black_ops/GUI_User/Jeux/gestionjeuxVidéos/jeux.fxml"));
//            
//            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//            scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @FXML
    private void showComu(ActionEvent event) throws Exception {
        String s = ComboCommunaute.getSelectionModel().getSelectedItem().toString();
        switch (s) {
            case "chat":
                SwitchScene_comu(stage, "gestion_communaute/chat", event);
                break;
            case "video":
                SwitchScene_comu(stage, "gestion_communaute/user/video_list_user/video_list_user", event);
                break;
        }
    }

    public void SwitchScene(Stage stage, String nom, ActionEvent event) throws Exception {

        String path = "/black_ops/GUI_User/";

        FXMLLoader loader = new FXMLLoader(getClass().getResource(path + nom + ".fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void ComboBoxListInti() {
        
        ObservableList<String> GestionCommunaute = FXCollections.observableArrayList("chat", "video");
        ComboCommunaute.setItems(GestionCommunaute);
        ObservableList<String> GestionCompetition = FXCollections.observableArrayList("Defi", "Match", "Joueur", "Equipe");
        ComboCompétition.setItems(GestionCompetition);  
        ObservableList<String> GestionJeux = FXCollections.observableArrayList("jeux");
        ComboJeux.setItems(GestionJeux);
       
    }
    
    public void SwitchScene_comu(Stage stage, String nom, ActionEvent event) throws Exception {

        String path = "/black_ops/GUI/";

        FXMLLoader loader = new FXMLLoader(getClass().getResource(path + nom + ".fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

       
 public void Navbar(){
     
      ObservableList<String> GestionCommunaute = FXCollections.observableArrayList("chat", "video");
        ComboCommunaute.setItems(GestionCommunaute);
        ObservableList<String> GestionCompetition = FXCollections.observableArrayList("Defi", "Match", "Joueur", "Equipe");
        ComboCompétition.setItems(GestionCompetition);  
        ObservableList<String> GestionJeux = FXCollections.observableArrayList("jeux");
        ComboJeux.setItems(GestionJeux);
       
     Nav_barController navbar = new Nav_barController();
  
    ObservableList<Nav_Bar> list = navbar.afficherNavBar();
    
      ObservableList data = FXCollections.observableArrayList();
    for (Nav_Bar l:list){
        
       data.add(l.getNom());
        
    }
    ComboStream.setItems(data);
        
        
  
     
     
     
 }   

    @FXML
    private void NvabarTarget(ActionEvent event) {
        
      
           if (ComboStream.getValue().equals("Live"))
           {
                   try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/black_ops/GUI/DefiLive.fxml"));
            Parent root = loader.load();
       
            
            ComboStream.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
               
               
           }
        if (ComboStream.getValue().equals("Replay"))
           {
               
                           try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/black_ops/GUI/FrontUserVideo.fxml"));
            Parent root = loader.load();
       
            
            ComboStream.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
               
           }
        if (ComboStream.getValue().equals( "Information"))
           {
               
                try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/black_ops/GUI/FrontUser.fxml"));
            Parent root = loader.load();
       
            
            ComboStream.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
               
           }
        
    }

    @FXML
    private void Accueil(MouseEvent event) {
            try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/black_ops/GUI/AccueilPage.fxml"));
            Parent root = loader.load();
       
            
            ComboStream.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
