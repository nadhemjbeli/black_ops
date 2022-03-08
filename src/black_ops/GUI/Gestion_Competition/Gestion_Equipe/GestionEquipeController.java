/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI.Gestion_Competition.Gestion_Equipe;

import black_ops.Controller.EquipeController;
import black_ops.Entity.Equipe;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * FXML Controller class
 *
 * @author medaz
 */
public class GestionEquipeController implements Initializable {

    @FXML
    private Button btn_update;
    @FXML
    private Button btn_sup;
    @FXML
    private Button btn_insert;
    @FXML
    private TextField inp_Equipe;
    @FXML
    private TextField inp_Nom;
    @FXML
    private TextField inp_url;
    @FXML
    private TextField inp_date;
    @FXML
    private TextField inp_nbr;
    @FXML
    private TableView<Equipe> table;
    @FXML
    private TableColumn<Equipe, Integer> cl_id;
    @FXML
    private TableColumn<Equipe, String> cl_nom;
    @FXML
    private TableColumn<Equipe, String> cl_url;
    @FXML
    private TableColumn<Equipe, Date> cl_date;
    @FXML
    private TableColumn<Equipe, Integer> cl_nbr;
    @FXML
    private TextField recherche;
    @FXML
    private Button btn_rech;
    @FXML
    private Button btn_diselect;
    @FXML
    private Button btn_refresh;
    @FXML
    private Button imp;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Show_Equipe();
        ComboBoxListInti();
    }

    @FXML
    private void Modifier_Equipe(ActionEvent event) {
        String id_eq = inp_Equipe.getText();
        String Nom = inp_Nom.getText();
        String url = inp_url.getText();
        String nbr_joueur = inp_nbr.getText();
        String d = inp_date.getText();
        int id = Integer.parseInt(id_eq);
        Date date = Date.valueOf(d);
        int nbr = Integer.parseInt(nbr_joueur);
        Equipe eq = new Equipe(id, Nom, url, date, nbr);
        EquipeController Ec = new EquipeController();
         if (inp_Equipe.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("Vous devez selectionnez un Id ");
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
          int options = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(null, "Vous etes sure de Modifier   cette  Equipe " ,"SERIOUS QUESTION", options, 3);
        if (result == JOptionPane.YES_OPTION) {
        Ec.UpdateEquipe(eq);
        Show_Equipe();
        } else if (result == JOptionPane.NO_OPTION) {
            
          Show_Equipe();
        } 
       
    }

    @FXML
    private void Supprimer_Equipe(ActionEvent event) {
        String Id_eq = inp_Equipe.getText();
        int id = Integer.parseInt(Id_eq);
        Equipe eq = new Equipe(id);
        EquipeController Ec = new EquipeController();
        if (inp_Equipe.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("Vous devez selectionnez un pays");
            alert.showAndWait();
            return;
        } 
          int options = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(null, "Vous etes sure de Supptimer   cette  Equipe " ,"SERIOUS QUESTION", options, 3);
        if (result == JOptionPane.YES_OPTION) {
            Ec.DeleteEquipe(eq);
        Show_Equipe();
        } else if (result == JOptionPane.NO_OPTION) {
          Show_Equipe();
        } 
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

    @FXML
    private void Recherche_Defi(ActionEvent event) {

    }

    private void Show_Equipe() {
        EquipeController Ec = new EquipeController();

        ObservableList<Equipe> list = Ec.afficherEquipe();
        cl_url.setPrefWidth(80);
        cl_id.setCellValueFactory(new PropertyValueFactory<Equipe, Integer>("id_Equipe"));
        cl_nom.setCellValueFactory(new PropertyValueFactory<Equipe, String>("nom_Equipe"));
        cl_url.setCellValueFactory(new PropertyValueFactory<Equipe, String>("logo_Equipe"));
        cl_date.setCellValueFactory(new PropertyValueFactory<Equipe, Date>("date"));
        cl_nbr.setCellValueFactory(new PropertyValueFactory<Equipe, Integer>("nbr_joueur_Equipe"));

        table.setItems(list);
    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
        Equipe equipe = table.getSelectionModel().getSelectedItem();
        inp_Equipe.setText("" + equipe.getId_Equipe());
        inp_Nom.setText("" + equipe.getNom_Equipe());
        inp_url.setText("" + equipe.getLogo_Equipe());
        inp_date.setText("" + equipe.getDate());
        inp_nbr.setText("" + equipe.getNbr_joueur_Equipe());

    }

    @FXML
    private void disselect(ActionEvent event) {
        inp_Equipe.setText("");
        inp_Nom.setText("");
        inp_url.setText("");
        inp_date.setText("");
        inp_nbr.setText("");
    }

    @FXML
    private void refreshTable(ActionEvent event) {
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

}
