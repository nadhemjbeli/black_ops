/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI_User.gestion_jeux.gestionchamps;

import black_ops.Entity.Champion;
import black_ops.Entity.Skin;
import black_ops.config.MaConnexion;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author jmokh
 */
public class AfficherSkinsController implements Initializable {

    @FXML
    private AnchorPane MainAnchor;
    @FXML
    private Label Nomchamp;
    Connection mc;
    PreparedStatement ste;
    @FXML
    private VBox vboxmain;
    @FXML
    private Button btnreload;
    
public void SetNom(String txt)
    { Nomchamp.setText(txt);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    @FXML
    public void afficherskins() {
        ObservableList<Skin> Images = FXCollections.observableArrayList();
        int id = 0;
        try {
            String nom = Nomchamp.getText();

            String sql2 = "select Id_champ from champion where Nom_Champ=?";

            mc = MaConnexion.getInstance().getCnx();
            ste = mc.prepareStatement(sql2);

            ste.setString(1, nom);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                id = rs.getInt("Id_champ");

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        try {

            String sql = "select image_skin from skin where Id_champ=?";
            mc = MaConnexion.getInstance().getCnx();

            ste = mc.prepareStatement(sql);
            ste.setInt(1, id);
            ResultSet rs = ste.executeQuery();
            final ScrollPane sp = new ScrollPane();
             VBox vb = new VBox();
                vboxmain.getChildren().add(sp);
            while (rs.next()) {
                Skin s = new Skin();
                String img4 = rs.getString("image_skin");
                s.setImage_skin(img4);
                Images.add(s);
                System.out.println(s);

                ////////////////
               
                VBox.setVgrow(sp, Priority.ALWAYS);
                HBox.setHgrow(sp, Priority.ALWAYS);
                  String path1 = img4;
//              System.out.println(path1);
                    String path2 = "src/Images/SkinImages/" + path1;
                    System.out.println(path2);
                    String Path_name = new File(path2).getAbsolutePath();
//        System.out.println(Path_name);
                    ImageView i = new ImageView();
                    File f = new File(Path_name);
                    Image im4 = new Image(f.toURI().toString());
                    i.setImage(im4);
                for (Skin l : Images) {
                  
                   
                    
                    vb.setAlignment(Pos.BOTTOM_RIGHT);
                    
                    sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                    sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
                    
                       i.setFitHeight(575);
                       i.setFitWidth(675);
                }
                vb.getChildren().add(i);
                sp.setContent(vb);
                sp.setPrefSize(600,600);
            }
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        btnreload.setDisable(true);
        btnreload.setVisible(false);
    }
    
}
