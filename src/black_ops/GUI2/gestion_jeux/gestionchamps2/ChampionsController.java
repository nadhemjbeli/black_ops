/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI2.gestion_jeux.gestionchamps2;

import black_ops.Controller.ChampionController;
import black_ops.Entity.Champion;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
 

/**
 * FXML Controller class
 *
 * @author jmokh
 */
public class ChampionsController implements Initializable {

    @FXML
    private VBox mainvb;
      private Stage stage;
 private Scene scene;
 private Parent root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showchamps();
        // TODO
    }    
//    public void showchamps(){
//    { VBox vb=new VBox();
//    
//    final ScrollPane sp = new ScrollPane();
//        mainvb.getChildren().add(sp);
//         ChampionController c1 = new ChampionController();
//         ObservableList<Champion> list = c1.afficherChampions();
//           for (Champion l : list) {
//           final ImageView im = new ImageView();
//           HBox hb = new HBox();
//           HBox hb2 = new HBox();
//           VBox vb1 = new VBox();
//           Label nom = new Label("Nom");
//           Label role = new Label("Rôle");
//           Button info = new Button();
//           Button skins = new Button();
//           String Path_name = new File("src/ImagesChampions").getAbsolutePath();
//            Champion c = new Champion();
//            String image = Path_name +"\\"+"Daruis.png";
//            File f = new File(image);
//            Image img = new Image(f.toURI().toString());
//            im.setImage(img);
//              im.setFitHeight(70);
//            im.setFitWidth(70);
//           
//            //
//            hb.getChildren().add(nom);
//            hb.getChildren().add(role);
//              hb2.getChildren().add(info);
//           hb2.getChildren().add(skins);
//            vb.getChildren().add(hb);
//            vb.getChildren().add(im);
//          
//           vb.getChildren().add(hb2);
//           
//         
//
//           }
//    
//    }
//}
            public void showchamps() {
          ChampionController c1 = new ChampionController();
       
            ObservableList<Champion> list = c1.afficherChampions();
        final VBox vb = new VBox();
        final ScrollPane sp = new ScrollPane();
        mainvb.getChildren().add(sp);

        VBox.setVgrow(sp, Priority.ALWAYS);
        HBox.setHgrow(sp, Priority.ALWAYS);
        vb.setAlignment(Pos.BOTTOM_RIGHT);
        for (Champion l : list) {
           
             sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
            final HBox hb = new HBox();
             hb.setAlignment(Pos.CENTER);
            final ImageView im = new ImageView();
           
            Label nom = new Label(""+l.getNom_Champ());
            Label role = new Label(l.getRole_Champ());
            Label A = new Label("Nom :");
            Label B = new Label("Role:");
            String Path_name = new File("src\\").getAbsolutePath();
            Champion c = new Champion();
            String image = Path_name +l.getImage_Champ();
            
            File f = new File(image);
            Image img = new Image(f.toURI().toString());
            im.setImage(img);
            VBox vb3 = new VBox();
            VBox vb2 = new VBox();
            HBox hb2 = new HBox();
        Button btn1 = new Button("Info");
        Button btn2 =new Button ("Skins");
           
            vb2.getChildren().add(A);
            vb2.getChildren().add(B);
            vb2.getChildren().add(btn1);
            
            hb.getChildren().add(vb2);
          
            vb3.getChildren().add(nom);
            vb3.getChildren().add(role);
            vb3.getChildren().add(btn2);
            hb.getChildren().add(vb3);
                hb.getChildren().add(im);
            vb.getChildren().add(hb);
            
            im.setFitHeight(278);
            im.setFitWidth(450);
          btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent event) {
                try {
                    root = FXMLLoader.load(getClass().getResource("InfoChamps.fxml"));
                    
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(ChampionsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        }
        
        sp.setContent(vb);

    }}