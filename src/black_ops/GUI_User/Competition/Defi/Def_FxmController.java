/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI_User.Competition.Defi;

import black_ops.Controller.DefiController;
import black_ops.Entity.Champion;
import black_ops.Entity.Defi;


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


import black_ops.config.MaConnexion;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Def_FxmController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
@FXML
    private VBox chosenChampCard;
    private Label ChampNom;

    private ImageView ChampImg;

    @FXML
    private GridPane grid;
    private Label RoleLabel;
   private ObservableList<Defi> def = FXCollections.observableArrayList();
    private MyListener mylistener;
     private Image image;
    @FXML
    private Label LabelNom1;
    @FXML
    private Label nomD;
    @FXML
    private ImageView imgD;
    @FXML
    private Label Date;
    @FXML
    private Button showm;
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
    
public ObservableList<Defi> getData()
{ 
           DefiController Df = new DefiController();
       
            ObservableList<Defi> df =Df.afficherDefi();
            
      return df;
     
}
 private void setChosenChamp(Defi Df) {
        nomD.setText(Df.getNom_Defi());
        Date.setText(""+Df.getDate_defi());
        String path1= Df.getNom_Defi();
      
       String path2=Df.getImg_Defi();
      
//        System.out.println(path2);
       
        String Path_name = new File(path2).getAbsolutePath();
//        System.out.println(Path_name);
        ImageView i = new ImageView();
        File f =  new File (Path_name);
        Image im =new Image(f.toURI().toString());
        imgD.setImage(im);
       
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ComboBoxListInti();
         def.addAll(getData());
          if (def.size() > 0) {
            setChosenChamp(def.get(0));
           mylistener = new MyListener() {
                @Override
                public void onClickListener(Defi champion) {
                    setChosenChamp(champion);
                }
            };}
         int column = 0;
           int row = 1;
           
         try{
          for (int i = 0; i < def.size(); i++) {
              FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../Defi/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
               
                ItemController it = fxmlLoader.getController();
                it.setData(def.get(i), mylistener);
          
                if (column == 3) {
                    column = 0;
                    row++;
                }
                grid.setId("grid");
                grid.add(anchorPane, column++, row); //(child,column,row)
               
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(900);
                grid.setMaxWidth(600);
                
                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(500);
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
            ic.SetNom(nomD.getText());
            Scene scene = new Scene(root);
            Stage stage=new Stage();
            
            stage.setScene(scene);
            stage.setWidth(1300);
            stage.setHeight(700);
            stage.show();
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
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
