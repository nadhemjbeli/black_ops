/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI2.gestion_jeux.gestionchamps;


import black_ops.Controller.ChampionController;
import black_ops.Entity.Champion;
import black_ops.Entity.Fruit;
import black_ops.GUI.gestion_jeux.gestionChampions.ImageChampionController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import black_ops.GUI2.gestion_jeux.gestionchamps.Main;
import black_ops.config.MaConnexion;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author jmokh
 */
public class ChampsController implements Initializable {
@FXML
    private VBox chosenChampCard;
    @FXML
    private Label ChampNom;

    @FXML
    private ImageView ChampImg;

    @FXML
    private GridPane grid;
    @FXML
    private Label RoleLabel;
   private ObservableList<Champion> champions = FXCollections.observableArrayList();
    private MyListener mylistener;
     private Image image;
    @FXML
    private Label LabelNom1;
    
public ObservableList<Champion> getData()
{ 
            ChampionController c1 = new ChampionController();
       
            ObservableList<Champion> champions = c1.afficherChampions();
            
      return champions;
     
}
 private void setChosenChamp(Champion champion) {
        ChampNom.setText(champion.getNom_Champ());
        RoleLabel.setText(champion.getRole_Champ());
        String path1= champion.getImage_Champ();
      
       String path2="src/Images/ImagesChampions/"+path1;
      
//        System.out.println(path2);
       
        String Path_name = new File(path2).getAbsolutePath();
//        System.out.println(Path_name);
        ImageView i = new ImageView();
        File f =  new File (Path_name);
        Image im =new Image(f.toURI().toString());
        ChampImg.setImage(im);
       
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         champions.addAll(getData());
          if (champions.size() > 0) {
            setChosenChamp(champions.get(0));
           mylistener = new MyListener() {
                @Override
                public void onClickListener(Champion champion) {
                    setChosenChamp(champion);
                }
            };}
         int column = 0;
           int row = 1;
           
         try{
          for (int i = 0; i < champions.size(); i++) {
              FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../gestionchamps/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
            ItemController it = fxmlLoader.getController();
                 it.setData(champions.get(i), mylistener);
          
                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
               
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void Information(ActionEvent event) {
         try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("AfficherInfo.fxml"));
            Parent root=(Parent)loader.load();
            AfficherInfoController ic = loader.getController();
            ic.SetNom(ChampNom.getText());
            Scene scene = new Scene(root);
            Stage stage=new Stage();
            
        stage.setScene(scene);
        stage.show();
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void Checkskins(ActionEvent event) throws IOException {
        try {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("AfficherSkins.fxml"));
            Parent root=(Parent)loader.load();
            AfficherSkinsController ic = loader.getController();
            ic.SetNom(ChampNom.getText());
            Scene scene = new Scene(root);
            Stage stage=new Stage();
            
        stage.setScene(scene);
        stage.show();
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
}}}


