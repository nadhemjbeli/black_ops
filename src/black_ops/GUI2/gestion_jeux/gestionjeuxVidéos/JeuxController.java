/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI2.gestion_jeux.gestionjeuxVidéos;

import black_ops.Controller.ChampionController;
import black_ops.Controller.ImageController;
import black_ops.Controller.JeuController;

import black_ops.Entity.Jeu;
import black_ops.GUI2.gestion_jeux.gestionchamps.AfficherInfoController;
import black_ops.GUI2.gestion_jeux.gestionchamps.ChampsController;
import black_ops.GUI2.gestion_jeux.gestionchamps.ItemController;

import black_ops.GUI2.gestion_jeux.gestionjeuxVidéos.MyListener2;
import black_ops.config.MaConnexion;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JFrame;

/**
 * FXML Controller class
 *
 * @author jmokh
 */
public class JeuxController implements Initializable {

    @FXML
    private Label LabelNom1;
    @FXML
    private GridPane grid;
    @FXML
    private Label jeuNom;
    @FXML
    private Label GenreLabel;
    @FXML
    private ImageView JeuImg;
    @FXML
    private VBox chosenjeuCard;
    Connection mc;
    PreparedStatement ste;
    private MyListener2 mylistener2;
 private ObservableList<Jeu> jeux = FXCollections.observableArrayList();
    @FXML
    private JFXTextField txtsearch;
    @FXML
    private ScrollPane sp;
    /**
     * Initializes the controller class.
     */
    public ObservableList<Jeu> getData()
{ 
            JeuController c1 = new JeuController();
//       ImageController i1 = new ImageController();
            ObservableList<Jeu> jeux = c1.AfficherJeux();
//            
//             ObservableList<Image> images = i1.afficherImages();
        return jeux;
            
      
}
     private void setChosenjeu(Jeu j) {
         int id =0;
         int idj=0;
         String Urlimg="";
         String nomsscat="";
         int idimg=0;
        jeuNom.setText(j.getNom());
        Integer i1=j.getId_souscat();
        GenreLabel.setText(i1.toString());
        
        String nomjeu=jeuNom.getText();
        try {
                  
            String sql="select * from jeu where Nom=?";
             mc=MaConnexion.getInstance().getCnx();
            
            ste=mc.prepareStatement(sql);
            ste.setString(1,nomjeu);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
               id=rs.getInt("id_souscat");
               idj=rs.getInt("Id_Jeu");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        try {
                  
            String sql="select nom_SousCat from sous_categorie where id_SousCat=?";
             mc=MaConnexion.getInstance().getCnx();
            ste=mc.prepareStatement(sql);
            ste.setInt(1,id);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
               nomsscat=rs.getString("nom_SousCat");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        GenreLabel.setText(nomsscat);
        try {
                  
            String sql="select Url_Image from image where Id_jeu=?";
             mc=MaConnexion.getInstance().getCnx();
            ste=mc.prepareStatement(sql);
            ste.setInt(1,idj);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
               Urlimg=rs.getString("Url_Image");
//                idimg=rs.getInt("Id_Image");
                System.out.println(Urlimg);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
////         Integer h = idimg;
//         black_ops.Entity.Image i4 = null ;
//        String path1=i4.getUrl_Image();
//
        String path2 = "src/Images/ImagesJeux/"+Urlimg;
         System.out.println(path2);

        String Path_name = new File(path2).getAbsolutePath();
//        System.out.println(Path_name);
        ImageView i = new ImageView();
        File f = new File(Path_name);
        Image im = new Image(f.toURI().toString());
        JeuImg.setImage(im);
     }
        
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    show();
    }
        public void show(){
         jeux.addAll(getData());
         if (jeux.size() > 0) {
            setChosenjeu(jeux.get(0));
           mylistener2=new MyListener2() {
                @Override
                public void onClickListener(Jeu jeu) {
                   setChosenjeu(jeu);
                }
                
            };}
           int column = 0;
           int row = 1;
         try{
          for (int i = 0; i < jeux.size(); i++) {
              FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../gestionjeuxVidéos/itemjeu.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                
            ItemjeuController it = fxmlLoader.getController();
                 it.setData(jeux.get(i),mylistener2);
          
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
            FXMLLoader loader= new FXMLLoader(getClass().getResource("AfficherInfoJeu.fxml"));
            Parent root=(Parent)loader.load();
            AfficherInfoJeuController ic = loader.getController();
            ic.SetNom(jeuNom.getText());
            Scene scene = new Scene(root);
            Stage stage=new Stage();
            
        stage.setScene(scene);
        stage.show();
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void Checkchampions(ActionEvent event) {
          ChampsController.nomjeu=jeuNom.getText();
         try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gestionchamps/Champs.fxml"));
        Parent root = (Parent) loader.load();
        ChampsController ih = loader.getController();
      
             System.out.println(jeuNom.getText());
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        
      stage.show();
      ih.SetNom(jeuNom.getText());
     
    }
    catch (IOException ex)

         {
            System.out.println(ex.getMessage());
    }

    }

    @FXML
    private void Avancer(ActionEvent event) {
        Information(event);
    }
    

    @FXML
    private void Rechercher(ActionEvent event) {
        grid.toBack();
        HBox h = new HBox();
        h.getChildren().clear();
                
        String nom =txtsearch.getText();
         JeuController c1 = new JeuController();
//       ImageController i1 = new ImageController();
            ObservableList<Jeu> j = c1.RechercherJeux(nom);
            
            
            jeux.addAll(j);
         if (jeux.size() > 0) {
            setChosenjeu(jeux.get(0));
           mylistener2=new MyListener2() {
                @Override
                public void onClickListener(Jeu jeu) {
                   setChosenjeu(jeu);
                }
                
            };}
           int column = 0;
           int row = 1;
         try{
          for (int i = 0; i < jeux.size(); i++) {
              FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../gestionjeuxVidéos/itemjeu.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
            ItemjeuController it = fxmlLoader.getController();
                 it.setData(jeux.get(i),mylistener2);
          
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

    
    
}

    
    
