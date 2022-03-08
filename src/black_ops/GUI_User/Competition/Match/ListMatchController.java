/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI_User.Competition.Match;

import black_ops.Controller.DetailsDefiController;
import black_ops.Entity.Defi;
import black_ops.Entity.DetailsDefi;
import black_ops.Entity.Equipe;
import black_ops.Entity.Joueur;
import black_ops.GUI.Gestion_Competition.Gestion_Joueur.Pair;
import black_ops.config.MaConnexion;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.property.DoubleProperty;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author medaz
 */
public class ListMatchController implements Initializable {

    Connection mc;
    PreparedStatement ste;
    PreparedStatement ste1;
    PreparedStatement ste2;
    PreparedStatement ste3;
    private DoubleProperty zoom;
    private ScrollPane scrollPane;
    private ImageView imageView;
    private Stage stage;
    String nom = "";
    @FXML
    private AnchorPane Main;
    DetailsDefiController dfc = new DetailsDefiController();
    @FXML
    private VBox mat;
    @FXML
    private TextField search;
    @FXML
    private Label titre;
    @FXML
    private VBox mat1;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ShowMatch();
       ComboBoxListInti() ;
        
    }

    public ListMatchController() {
        mc = MaConnexion.getInstance().getCnx();
    }
    

    public void ShowMatch() {
        nom = search.getText();
       
        ObservableList<DetailsDefi> list = dfc.View_Details_defi();
       // ObservableList<Recherche> match = dfc.recherche(nom);

       
        try {
            String sql2 = "select nom_Equipe,logo_Equipe from equipe where id_equipe= ?";
            String sql3 = "select nom_Equipe,logo_Equipe from equipe where id_equipe=?";
            String sql4 = "select nom_Defi from defi where id_Defi=?";
//           String sql5 = "SELECT DISTINCT * FROM defi de "
//                    + "INNER JOIN details_defi dd on de.id_Defi = dd.id_defi "
//                    + "INNER join equipe ea on (dd.EquipeA = ea.id_Equipe)"
//                    + "INNER join equipe eb on (dd.EquipeB = eb.id_Equipe)"
//                    + "WHERE ea.nom_equipe LIKE '%"+nom+"%' or eb.nom_equipe LIKE '%"+nom+"%' ";
            
        
        final VBox vb = new VBox();
        final ScrollPane sp = new ScrollPane();
        
        vb.setAlignment(Pos.CENTER);
        mat.getChildren().add(sp);
        mat.setAlignment(Pos.CENTER);
        
            for (DetailsDefi l : list) {
                VBox vb3 = new VBox();
                VBox.setVgrow(sp, Priority.ALWAYS);
                HBox.setHgrow(sp, Priority.ALWAYS);
               
                ste = mc.prepareStatement(sql2);
                ste1 = mc.prepareStatement(sql3);
                ste2 = mc.prepareStatement(sql4);
                ste.setInt(1, l.getEquipeA());
                ste1.setInt(1, l.getEquipeB());
                ste2.setInt(1, l.getId_defi());
                ResultSet rs = ste.executeQuery();
                ResultSet rs1 = ste1.executeQuery();
                ResultSet rs2 = ste2.executeQuery(); 
                while (rs.next() && rs1.next() && rs2.next()  ) {

                    String k = rs.getString("nom_Equipe");
                    Label A = new Label(k);
                    String k1 = rs1.getString("nom_Equipe");
                    Label B = new Label(k1);
                    String k2 = rs2.getString("nom_Defi");
                    Label df = new Label(k2);

                    sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                    sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

                    final HBox hb = new HBox();
                    hb.setAlignment(Pos.TOP_CENTER);
                    final Button btn = new Button("Afficher Plus");
                    final ImageView im = new ImageView();
                    final ImageView im2 = new ImageView();

                    Label score = new Label(l.getScore_finale());

                    A.setId("equipeA");
                    B.setId("equipeB");
                    df.setId("defi");
                    im.setId("imA");
                    im2.setId("ImB");
                    String logoA = rs.getString("logo_Equipe");
                    String logoB = rs1.getString("logo_Equipe");

                    // String Path_name = new File("src/Image/Logo_Equipe").getAbsolutePath();
                    String image = logoA;
                    String image2 = logoB;
                    File f = new File(image);
                    File f2 = new File(image2);
                    Image img = new Image(f.toURI().toString());
                    Image img1 = new Image(f2.toURI().toString());
                    im.setImage(img);
                    im2.setImage(img1);
                    im.setFitHeight(50);
                    im.setFitWidth(50);
                    im2.setFitHeight(50);
                    im2.setFitWidth(50);
                    final VBox vb1 = new VBox();
                    hb.setPrefSize(500, 500);

                    hb.getChildren().add(im);
                    hb.getChildren().add(A);
                    vb1.getChildren().add(score);
                    vb1.getChildren().add(df);
                    vb1.getChildren().add(btn);
                    hb.getChildren().add(vb1);
                    hb.getChildren().add(B);
                    hb.getChildren().add(im2);
                    vb.setPrefSize(200, 200);
                    vb3.getChildren().add(hb);
                    vb.setPrefSize(430, 500);
                    vb.getChildren().add(vb3);
                    vb3.setId("vb3");
                    hb.setId("hb");
                    String det = l.getImgScore();
                    String dd = det;
                    File f1 = new File(dd);

                    Image ddd = new Image(f1.toURI().toString());
                    btn.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("Detail.fxml"));
                                Parent root = (Parent) loader.load();
                                DetailController ic = loader.getController();
                                ic.fun(ddd);
//            AfficherSkinController ic2 = loader.getController();
//            ic2.function2(nom);
                                Scene scene = new Scene(root);
                                Stage stage = new Stage();

                                stage.setScene(scene);
                                stage.show();

                            } catch (IOException ex) {
                                System.out.println(ex.getMessage());
                            }
                        }
                    });

                }
                
            }
        sp.setPrefSize(600, 600);
        sp.setContent(vb);
           
           
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        

    }

   
    public void ShowMatch1() {
        nom = search.getText();
       
        
        ObservableList<Recherche> match = dfc.recherche(nom);

       
        try {

        final VBox vb = new VBox();
        final ScrollPane sp = new ScrollPane();
        
        vb.setAlignment(Pos.CENTER);
        mat1.getChildren().add(sp);
        mat1.setAlignment(Pos.CENTER);
      
            for (Recherche m : match) {
                
                VBox vb3 = new VBox();
                VBox.setVgrow(sp, Priority.ALWAYS);
                HBox.setHgrow(sp, Priority.ALWAYS);
                
                
                    
                   
                    Label A = new Label(m.getEquipeA().getNom_Equipe());
                    
                    Label B = new Label(m.getEquipeB().getNom_Equipe());
                   
                    Label df = new Label(m.getDefi().getNom_Defi());
                    
                    sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                    sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

                    final HBox hb = new HBox();
                    hb.setAlignment(Pos.TOP_CENTER);
                    final Button btn = new Button("Afficher Plus");
                    final ImageView im = new ImageView();
                    final ImageView im2 = new ImageView();

                    Label score = new Label(m.getDdefi().getScore_finale());
                    A.setId("equipeA");
                    B.setId("equipeB");
                    df.setId("defi");
                    im.setId("imA");
                    im2.setId("ImB");
                   String logoA = m.getEquipeA().getLogo_Equipe();
                    String logoB = m.getEquipeB().getLogo_Equipe();

                    // String Path_name = new File("src/Image/Logo_Equipe").getAbsolutePath();
                    String image = logoA;
                    String image2 = logoB;
                    File f = new File(image);
                    File f2 = new File(image2);
                    Image img = new Image(f.toURI().toString());
                    Image img1 = new Image(f2.toURI().toString());
                    im.setImage(img);
                    im2.setImage(img1);
                    im.setFitHeight(50);
                    im.setFitWidth(50);
                    im2.setFitHeight(50);
                    im2.setFitWidth(50);
                    final VBox vb1 = new VBox();
                    hb.setPrefSize(500, 500);

                    hb.getChildren().add(im);
                    hb.getChildren().add(A);
                    vb1.getChildren().add(score);
                    vb1.getChildren().add(df);
                    vb1.getChildren().add(btn);
                    hb.getChildren().add(vb1);
                    hb.getChildren().add(B);
                    hb.getChildren().add(im2);
                    vb.setPrefSize(200, 200);
                    vb3.getChildren().add(hb);
                    vb.setPrefSize(430, 500);
                    vb.getChildren().add(vb3);
                    vb3.setId("vb3");
                    hb.setId("hb");
                    String det = m.getDdefi().getImgScore();
                    String dd = det;
                    File f1 = new File(dd);

                    Image ddd = new Image(f1.toURI().toString());
                    btn.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("Detail.fxml"));
                                Parent root = (Parent) loader.load();
                                DetailController ic = loader.getController();
                                ic.fun(ddd);
//            AfficherSkinController ic2 = loader.getController();
//            ic2.function2(nom);
                                Scene scene = new Scene(root);
                                Stage stage = new Stage();

                                stage.setScene(scene);
                                stage.show();

                            } catch (IOException ex) {
                                System.out.println(ex.getMessage());
                            }
                        }
                    });

                }
                
            
        sp.setPrefSize(600, 600);
        sp.setContent(vb);
           
           
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        

    }

    @FXML
    private void EnterRecherche(ActionEvent event) {
        String h = search.getText();
        
        
        search.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                   if(!h.isEmpty()){
                       mat1.getChildren().clear();
                       ShowMatch1();
                        mat.setVisible(false);
                   }else{
                       mat.getChildren().clear();
                       mat1.setVisible(false);
                       mat.setVisible(true);
                        ShowMatch();
                    }
                }
            }
        });

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
