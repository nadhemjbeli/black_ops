/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI_User.Jeux.gestionjeuxVidéos;

import black_ops.Controller.JeuController;
import black_ops.Controller.Nav_barController;

import black_ops.Entity.Jeu;
import black_ops.Entity.Nav_Bar;
import black_ops.GUI_User.Jeux.gestionchamps.ChampsController;
import black_ops.GUI_User.Jeux.gestionjeuxVidéos.MyListener2;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
  private ObservableList<Jeu> jeux2 = FXCollections.observableArrayList();
    @FXML
    private ScrollPane sp;
    @FXML
    private HBox hboxmain;   
    @FXML
    private ComboBox ComboStream;
    @FXML
    private ComboBox ComboCompétition;
    @FXML
    private ComboBox ComboCommunaute;
    @FXML
    private FontAwesomeIconView ComboUser;
          private Stage stage;
 private Scene scene;
 private Parent root;
    @FXML
    private ComboBox ComboJeux;
    /**
     * Initializes the controller class.
     */
    public ObservableList<Jeu> getData()
{ 
    JeuController c1 = new JeuController();
   ObservableList<Jeu> jeux = c1.AfficherJeux();
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
    ComboBoxListInti();
        show();
        Navbar();
   
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
    private void showComp(ActionEvent event) throws Exception {

        String s = ComboCompétition.getSelectionModel().getSelectedItem().toString();
        
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

    @FXML
    private void Showj(ActionEvent event) throws IOException {
        try {
            String s = ComboJeux.getSelectionModel().getSelectedItem().toString();
            switch (s){
                case"jeux":
                    SwitchScene(stage, "Jeux/gestionjeuxVidéos/jeux", event);
                    break;
            }
//            root = FXMLLoader.load(getClass().getResource("black_ops/GUI_User/Jeux/gestionjeuxVidéos/jeux.fxml"));
//            
//            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//            scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @FXML
    private void showComu(ActionEvent event) throws Exception {
        String s = ComboCommunaute.getSelectionModel().getSelectedItem().toString();
        switch (s) {
            case "chat":
                SwitchScene_comu(stage, "gestion_communaute/chat", event);
                break;
            case "video":
                SwitchScene_comu(stage, "gestion_communaute/user/video_list_user/video_list_user", event);
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
        
        ObservableList<String> GestionCommunaute = FXCollections.observableArrayList("chat", "video");
        ComboCommunaute.setItems(GestionCommunaute);
        ObservableList<String> GestionCompetition = FXCollections.observableArrayList("Defi", "Match", "Joueur", "Equipe");
        ComboCompétition.setItems(GestionCompetition);  
        ObservableList<String> GestionJeux = FXCollections.observableArrayList("jeux");
        ComboJeux.setItems(GestionJeux);
       
    }
    
    public void SwitchScene_comu(Stage stage, String nom, ActionEvent event) throws Exception {

        String path = "/black_ops/GUI/";

        FXMLLoader loader = new FXMLLoader(getClass().getResource(path + nom + ".fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

  public void Navbar(){
     
     
       
     Nav_barController navbar = new Nav_barController();
  
    ObservableList<Nav_Bar> list = navbar.afficherNavBar();
    
      ObservableList data = FXCollections.observableArrayList();
    for (Nav_Bar l:list){
        
       data.add(l.getNom());
        
    }
    ComboStream.setItems(data);
        
        
  
     
     
     
 }   

    @FXML
    private void NvabarTarget(ActionEvent event) {
        
      
           if (ComboStream.getValue().equals("Live"))
           {
                   try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/black_ops/GUI/DefiLive.fxml"));
            Parent root = loader.load();
       
            
            ComboStream.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
               
               
           }
        if (ComboStream.getValue().equals("Replay"))
           {
               
                           try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/black_ops/GUI/FrontUserVideo.fxml"));
            Parent root = loader.load();
       
            
            ComboStream.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
               
           }
        if (ComboStream.getValue().equals( "Information"))
           {
               
                try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/black_ops/GUI/FrontUser.fxml"));
            Parent root = loader.load();
       
            
            ComboStream.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
               
           }
        
    }

    @FXML
    private void Accueil(MouseEvent event) {
            try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/black_ops/GUI/AccueilPage.fxml"));
            Parent root = loader.load();
       
            
            ComboStream.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}