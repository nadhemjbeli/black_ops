/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI_User.Competition.Equipe;

import black_ops.Controller.EquipeController;
import black_ops.Entity.Equipe;
import black_ops.config.MaConnexion;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * FXML Controller class
 *
 * @author medaz
 */
public class InscriptionEquipeController implements Initializable {

    @FXML
    private VBox add;
    @FXML
    private TextField inp_Nom;
    @FXML
    private TextField inp_url;
    @FXML
    private Button imp;
    @FXML
    private TextField inp_nbr;
    @FXML
    private Button btn_insert;
    @FXML
    private VBox aff;
    @FXML
    private ComboBox ComboJeux;
    @FXML
    private ComboBox ComboStream;
    @FXML
    private ComboBox ComboCompétition;
    @FXML
    private ComboBox ComboCommunaute;
    @FXML
    private FontAwesomeIconView ComboUser;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btn_insert.setDisable(false);
        ComboBoxListInti();
    }    


    @FXML
    private String importImg(ActionEvent event) {
        String id = inp_Nom.getText();
        Path to = null;
        String m = null;
        String path = "src/Image/Logo_Equipe";
        JFileChooser chooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & PNG Images", "jpg", "jpeg", "PNG");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            m = chooser.getSelectedFile().getAbsolutePath();
//            System.out.println("You chose to open this file: " +m
//                    );

            if (chooser.getSelectedFile() != null) {

                try {
                    Path from = Paths.get(chooser.getSelectedFile().toURI());
                    to = Paths.get(path + "\\" + id + ".png");
                    CopyOption[] options = new CopyOption[]{
                        StandardCopyOption.REPLACE_EXISTING,
                        StandardCopyOption.COPY_ATTRIBUTES
                    };
                    Files.copy(from, to, options);
                    System.out.println("added");
//                saveSystem(selectedFile, )
                    System.out.println(to);

                } catch (IOException ex) {
                    System.out.println();
                }
            }
            inp_url.setText(to.toString());

        }
        return to.toString();
    }

    @FXML
    private void Ajouter_Equipe(ActionEvent event) {
         java.util.Date date = Calendar.getInstance().getTime();
        java.sql.Date dateJ = new java.sql.Date(date.getTime());
        String Nom = inp_Nom.getText();
        String url = inp_url.getText();
        String nbr_joueur = inp_nbr.getText();

        int nbr = Integer.parseInt(nbr_joueur);
        Equipe eq = new Equipe(3, Nom, url, dateJ, nbr);
        EquipeController Ec = new EquipeController();
        if (inp_Nom.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("Vous devez selectionnez un Nom");
            alert.showAndWait();
            return;
        }
       
        if (inp_url.getText().isEmpty())       
                {
                    
                     Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Erreur");
                alert.setContentText("Vous devez saisir un url valid");
                alert.showAndWait();
                return;
                }
        
         if (inp_nbr.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("Vous devez selectionnez un nombre valid ");
            alert.showAndWait();
            return;
        }
        Ec.ajouterEquipe(eq);
        Show_Equipe();
    }
    public void Show_Equipe(){
         EquipeController Ec = new EquipeController();
         Equipe eq = Ec.Select();
         inp_Nom.setText(eq.getNom_Equipe());
         inp_url.setText(eq.getLogo_Equipe());
         inp_nbr.setText(""+eq.getNbr_joueur_Equipe());  
         btn_insert.setDisable(true);
} 

    
    @FXML
    private void showComp(ActionEvent event) throws Exception {

        String s = ComboCompétition.getSelectionModel().getSelectedItem().toString();
//       C:\Users\medaz\Documents\NetBeansProjects\crud_competition\src\black_ops\GUI_User\Competition\Defi\Defi.fxml
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

    private void showU(ActionEvent event) throws IOException, Exception {
        SwitchScene(stage, "Gestion_Competition/Gestion_Defis/GestionDefi", event);
    }

    

   

    private void showS(ActionEvent event) throws Exception {
        String s = ComboStream.getSelectionModel().getSelectedItem().toString();
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

    private void showJ(ActionEvent event) throws Exception {
        String s = ComboJeux.getSelectionModel().getSelectedItem().toString();
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
    private void showComu(ActionEvent event) throws Exception {
        String s = ComboCommunaute.getSelectionModel().getSelectedItem().toString();
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

        String path = "/black_ops/GUI_User/";

        FXMLLoader loader = new FXMLLoader(getClass().getResource(path + nom + ".fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void ComboBoxListInti() {
        
        ObservableList<String> GestionCommunaute = FXCollections.observableArrayList("Message", "Video");
        ComboCommunaute.setItems(GestionCommunaute);
        ObservableList<String> GestionCompetition = FXCollections.observableArrayList("Defi", "Match", "Joueur", "Equipe");
        ComboCompétition.setItems(GestionCompetition);  
        ObservableList<String> GestionStream = FXCollections.observableArrayList("Stream Replay ", "Stream Info ");
        ComboStream.setItems(GestionStream);
        ObservableList<String> GestionJeu = FXCollections.observableArrayList("Jeu", "Image", "Skin", "Champion");
        ComboJeux.setItems(GestionJeu);
    }
            
}
