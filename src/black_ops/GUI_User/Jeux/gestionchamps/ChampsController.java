/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI_User.Jeux.gestionchamps;


import black_ops.Controller.ChampionController;
import black_ops.Entity.Champion;
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
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    public  ObservableList<Champion> champions =FXCollections.observableArrayList();;
    private MyListener mylistener;
     private Image image;
         private Stage stage;
 private Scene scene;
 private Parent root;
    
     Connection mc;
    PreparedStatement ste;
    @FXML
    private Label LabelJeu;
    public static String nomjeu;
     public void SetNom(String txt)
    { LabelJeu.setText(txt);
    }

    public ObservableList<Champion> getData()
   {   
//       System.out.println(LabelJeu.getText());
           ObservableList<Champion> champions = FXCollections.observableArrayList();
//        System.out.println(nomjeu);       
//       nomjeu = LabelJeu.getText();
//        System.out.println(nomjeu);
        int idjeu = 0 ;
//        String txt=LabelJeu.getText();      
        try {
            mc=MaConnexion.getInstance().getCnx();
               String sql2="select Id_Jeu from jeu where Nom=?";                          
             ste=mc.prepareStatement(sql2);
             
              ste.setString(1,nomjeu);
              
              System.out.println("hello"+LabelJeu.getText());
              ResultSet rs=ste.executeQuery();
               
               while(rs.next()){
            idjeu=rs.getInt("Id_Jeu");
       System.out.println(idjeu);
            
            
        } }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         
       
        try {
             String sql3="select * from champion where Id_jeu=?";
            mc=MaConnexion.getInstance().getCnx();
            ste=mc.prepareStatement(sql3);
            ste.setInt(1,idjeu);
             System.out.println(idjeu);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Champion c = new Champion();
                
                c.setId_Champ(rs.getInt("Id_Champ"));
                c.setNom_Champ(rs.getString("Nom_champ"));
                c.setDescription_Champ(rs.getString("description_Champ"));
                c.setRole_Champ(rs.getString("Role_Champ"));
                c.setDifficulte_Champ(rs.getString("Difficulte_Champ"));
                c.setImage_Champ(rs.getString("Image_Champ"));
                c.setId_jeu(rs.getInt("Id_jeu"));
                
                champions.add(c);
            }
            System.out.println(champions);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
//        System.out.println(champions);
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
    
       show();
       
    }
    public void show(){
         champions.addAll(getData());
         System.out.println(LabelJeu.getText());
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
}
    
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
              root = FXMLLoader.load(getClass().getResource("../gestionjeuxVidéos/jeux.fxml"));
          
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
    }

    @FXML
    private void move(ActionEvent event) {
        Information(event);
        
    }

    private void get(ActionEvent event) {
        getData();
        
    }




}


