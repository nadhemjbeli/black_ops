/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI_User.Competition.Match;

import black_ops.Controller.DetailsDefiController;
import black_ops.Entity.DetailsDefi;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
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
 * @author medaz
 */
public class ListMatchController implements Initializable {

    @FXML
    private AnchorPane Main;
    DetailsDefiController dfc = new DetailsDefiController();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ShowMatch();
    }

    public void ShowMatch() {
        final VBox vb = new VBox();
        final ScrollPane sp = new ScrollPane();
        Main.getChildren().add(sp);

        VBox.setVgrow(sp, Priority.ALWAYS);
        HBox.setHgrow(sp, Priority.ALWAYS);
        ObservableList<DetailsDefi> list = dfc.View_Details_defi();
        for (DetailsDefi l : list) {
            final HBox hb = new HBox();
            final Button btn = new Button();
            final ImageView im = new ImageView();
            final ImageView im2 = new ImageView();
            Label df = new Label(""+l.getId_defi());
            Label score = new Label(l.getScore_finale());
            Label A = new Label("EquipeA");
            Label B = new Label("EquipeB");
            
            String Path_name = new File("src/Image/Logo_Equipe").getAbsolutePath();
            String image = Path_name + "\\Es_Gaming2.png";
            File f = new File(image);
            
            Image img = new Image(f.toURI().toString());
            im.setImage(img);
            im2.setImage(img);
            
            im.setFitHeight(50);
            im.setFitWidth(50);
            im2.setFitHeight(50);
            im2.setFitWidth(50);
           
            
            hb.setPrefSize(500, 500);
            hb.getChildren().add(im);
            hb.getChildren().add(A);
            hb.getChildren().add(score);
            hb.getChildren().add(df);
            hb.getChildren().add(B);
            hb.getChildren().add(im2);
            hb.getChildren().add(btn);
            vb.setPrefSize(500, 500);
            vb.getChildren().add(hb);
            

        }
        sp.setPrefSize(500, 500);
        sp.setContent(vb);

    }
}
