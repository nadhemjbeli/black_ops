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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    private DoubleProperty zoom;
    private ScrollPane scrollPane;
    private ImageView imageView;
    private Stage stage;

    @FXML
    private AnchorPane Main;
    DetailsDefiController dfc = new DetailsDefiController();
    @FXML
    private VBox mat;
    @FXML
    private TextField search;
    @FXML
    private Label titre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ShowMatch();
        System.out.println(recherche("Fnat"));
    }

    public ListMatchController() {
        mc = MaConnexion.getInstance().getCnx();
    }
    

    public void ShowMatch() {
        final VBox vb = new VBox();
        final ScrollPane sp = new ScrollPane();
        mat.getChildren().add(sp);

        vb.setAlignment(Pos.CENTER);
        mat.setAlignment(Pos.CENTER);
        ObservableList<DetailsDefi> list = dfc.View_Details_defi();
        try {
            String sql2 = "select nom_Equipe,logo_Equipe from equipe where id_equipe= ?";
            String sql3 = "select nom_Equipe,logo_Equipe from equipe where id_equipe=?";
            String sql4 = "select nom_Defi from defi where id_Defi=?";
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
                while (rs.next() && rs1.next() && rs2.next()) {
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
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        sp.setPrefSize(600, 600);
        sp.setContent(vb);

    }

    @FXML
    private void EnterRecherche(ActionEvent event) {
        String h = search.getText();
        System.out.println(recherche(h));
//        search.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent keyEvent) {
//                if (keyEvent.getCode() == KeyCode.ENTER) {
//                    recherche();
//                    if (search.getText().equals(h)) {
//                        System.out.println(recherche());
//                    } else {
//                        System.out.println("default");
//                    }
//                }
//            }
//        });

    }

    public ObservableList<Recherche> recherche(String nom) {

        ObservableList<Recherche> dd = FXCollections.observableArrayList();

        try {
            String sql3 = "SELECT DISTINCT * FROM defi de "
                    + "INNER JOIN details_defi dd on de.id_Defi = dd.id_defi "
                    + "INNER join equipe ea on (dd.EquipeA = ea.id_Equipe)"
                    + "INNER join equipe eb on (dd.EquipeB = eb.id_Equipe)"
                    + "WHERE ea.nom_Equipe LIKE '%"+nom+"%'";
            ste = mc.prepareStatement(sql3);
            
            ResultSet rs = ste.executeQuery();
           
            while (rs.next()) {
                System.out.println("samir");
                Equipe A = new Equipe();
                Equipe B = new Equipe();
                Defi D = new Defi();
                DetailsDefi detaildefi = new DetailsDefi();
                A.setNom_Equipe(rs.getString("nom_Equipe"));
                A.setLogo_Equipe(rs.getString("logo_Equipe"));
                B.setLogo_Equipe(rs.getString("logo_Equipe"));
                B.setNom_Equipe(rs.getString("nom_Equipe"));
                D.setNom_Defi(rs.getString("nom_Defi"));
                detaildefi.setImgScore(rs.getString("imgScore"));
                detaildefi.setScore_finale(rs.getString("Score_finale"));

                Recherche r = new Recherche();
                r.setEquipeA(A);
                r.setEquipeB(B);
                r.setDefi(D);
                r.setDdefi(detaildefi);

                dd.add(r);
                System.out.println(dd);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return dd;
    }

}
