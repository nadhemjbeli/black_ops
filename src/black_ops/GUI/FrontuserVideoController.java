/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI;

import black_ops.Controller.ModeController;
import black_ops.Controller.Nav_barController;
import black_ops.Controller.ReplayStreamController;
import black_ops.Entity.Mode;
import black_ops.Entity.Nav_Bar;
import black_ops.Entity.Replay_Stream;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author aZiz
 */
public class FrontuserVideoController implements Initializable {
  private Stage stage;
    @FXML
    private VBox vbox;
    @FXML
    private VBox vbox2;
    private Media media;
    private int random = 0 ;
      private int index = 0;
   private MediaPlayer mediaPlayer;
    @FXML
    private ComboBox ComboStream;
    @FXML
    private ComboBox ComboJeux;
    @FXML
    private ComboBox ComboCompétition;
    @FXML
    private FontAwesomeIconView ComboUser;
    @FXML
    private AnchorPane Pane;
    @FXML
    private Text titrepage;
    @FXML
    private ScrollPane ScrollPane;
    @FXML
    private ComboBox ComboCommunaute;
    @FXML
    private Button btnmoon;
    @FXML
    private FontAwesomeIconView moonIcon;
    @FXML
    private Button btnlight;
    @FXML
    private FontAwesomeIconView lightIcon;
    @FXML
    private ImageView ImageVue;
    @FXML
    private HBox hboxnavbar;


   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Navbar();
        modeOn();
        data();
    }    
    
    
    public void data(){
        
     ReplayStreamController replayStream = new ReplayStreamController();
        
       ObservableList<Replay_Stream> list = (ObservableList<Replay_Stream>) replayStream.afficherReplay();
       
        for (Replay_Stream l:list){
      
        if (index%2 == 0){

            
            vbox.setAlignment(Pos.CENTER);
            Label labtxt = new Label(l.getNom());
               labtxt.setMinHeight(50);
           labtxt.setStyle("-fx-font-weight: bold");
        
            vbox.getChildren().add(labtxt);
        
         
                                 
           // media = new Media(file.toURI().toString());
          
     
            //mediaPlayer = new MediaPlayer(media);
       
           // System.out.println(file);
          
          
     File file = new File ("src/black_ops/MediaStream/"+l.getUrl());
            System.out.println(file);
                               media = new Media(file.toURI().toString());
                                 mediaPlayer = new MediaPlayer(media);
                                    
          
              
    
            MediaView mediaview = new MediaView();
          mediaview.setMediaPlayer(mediaPlayer);
          mediaPlayer.setAutoPlay(true);
      mediaPlayer.setMute(true);
            mediaview.setId("myId"+random);
          
      
   
            //System.out.println(mediaview.getId());
           
            
               FontAwesomeIconView playIcon = new FontAwesomeIconView(FontAwesomeIcon.PLAY);
                FontAwesomeIconView eyeIcon = new FontAwesomeIconView(FontAwesomeIcon.EYE);
                       // FontAwesomeIconView pauseIcon = new FontAwesomeIconView(FontAwesomeIcon.PAUSE);
                        playIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#09a195;"
                        );
                         eyeIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#09a195;"
                        );
//                        pauseIcon.setStyle(
//                                " -fx-cursor: hand ;"
//                                + "-glyph-size:28px;"
//                                + "-fx-fill:#09a195;"
//                        );
//                        
                         playIcon.setOnMouseClicked((MouseEvent event) -> {
                             
                             replayStream.ajouterVueReplay(l.getId());
                              mediaPlayer.stop();
                           int id  = l.getId();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/black_ops/GUI/FrontUserWatChReplay.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                ex.getMessage();
                            }
                            
                            FrontUserWatChReplayController watchReplay = loader.getController();
                       
                          watchReplay.VideoId(id);
                            Parent parent = loader.getRoot();
//                         Stage stage = new Stage();
//                            stage.setScene(new Scene(parent));
//                            //stage.initStyle(StageStyle.UTILITY);
//                            stage.show();
                            vbox.getScene().setRoot(parent);

                             
                         });
//                         pauseIcon.setOnMouseClicked((MouseEvent event) -> {
//                       
//                             
//                         });
          
            mediaview.setFitHeight(300);
            mediaview.setFitWidth(700);
            vbox.getChildren().add(mediaview);
       Label vues = new Label();
      String vue = Integer.toString(l.getVues()) ;
      vues.setText(" "+vue+" Vues");
                          HBox managebtn = new HBox(playIcon,vues,eyeIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(playIcon, new Insets(2, 2, 0, 3));
                      HBox.setMargin(vues, new Insets(2, 7, 0, 20));
                        
                        vbox.getChildren().add(managebtn);
                        
            Label labdescription = new Label(l.getDescription());
            
              labdescription.setMinHeight(50);
           labdescription.setStyle("-fx-font-family: Orkney");
           labdescription.setAlignment(Pos.CENTER_LEFT);
           vbox.getChildren().add(labdescription);
            
            
            
            
        }
         if (index%2 == 1){
//          
vbox2.setAlignment(Pos.CENTER);
            Label labtxt = new Label(l.getNom());
               labtxt.setMinHeight(50);
           labtxt.setStyle("-fx-font-weight: bold");
        
            vbox2.getChildren().add(labtxt);
            File file = new File ("src/black_ops/MediaStream/"+l.getUrl());
            media = new Media(file.toURI().toString());
          
            mediaPlayer = new MediaPlayer(media);
            MediaView mediaview = new MediaView(mediaPlayer);
                mediaPlayer.setAutoPlay(true);
          mediaPlayer.setMute(true);
            mediaview.setFitHeight(300);
            mediaview.setFitWidth(700);
            vbox2.getChildren().add(mediaview);
          FontAwesomeIconView playIcon = new FontAwesomeIconView(FontAwesomeIcon.PLAY);
                         FontAwesomeIconView eyeIcon = new FontAwesomeIconView(FontAwesomeIcon.EYE);
                        playIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#09a195;"
                        );
                         eyeIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#09a195;"
                        );
                   
                         playIcon.setOnMouseClicked((MouseEvent event) -> {
                             
                             replayStream.ajouterVueReplay(l.getId());
                             mediaPlayer.stop();
                           int id  = l.getId();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/black_ops/GUI/FrontUserWatChReplay.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                ex.getMessage();
                            }
                            
                            FrontUserWatChReplayController watchReplay = loader.getController();
                       
                          watchReplay.VideoId(id);
                            Parent parent = loader.getRoot();
//                         Stage stage = new Stage();
//                            stage.setScene(new Scene(parent));
//                            //stage.initStyle(StageStyle.UTILITY);
//                            stage.show();
                            vbox.getScene().setRoot(parent);
                             
                         });
                         Label vues = new Label();
      String vue = Integer.toString(l.getVues()) ;
      vues.setText(" "+vue+" Vues");
                          HBox managebtn = new HBox(playIcon,vues,eyeIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(playIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(vues, new Insets(2, 7, 0, 20));
                        
                        vbox2.getChildren().add(managebtn);
                        
            Label labdescription = new Label(l.getDescription());
            
              labdescription.setMinHeight(50);
           labdescription.setStyle("-fx-font-family: Orkney");
           labdescription.setAlignment(Pos.CENTER_LEFT);
           vbox2.getChildren().add(labdescription);
             
         }
        index ++;
 
        
        }
        
        
        
       
        
    }
    
         public void Navbar(){
             ObservableList<String> GestionCommunaute = FXCollections.observableArrayList("chat", "video");
        ComboCommunaute.setItems(GestionCommunaute);
        ObservableList<String> GestionCompetition = FXCollections.observableArrayList("Defi", "Match", "Joueur", "Equipe");
        ComboCompétition.setItems(GestionCompetition);  
        ObservableList<String> GestionJeux = FXCollections.observableArrayList("jeux");
        ComboJeux.setItems(GestionJeux);
       
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

    @FXML
    private void changemode(ActionEvent event) {
       
           ModeController mode = new ModeController();
        
         ObservableList<Mode> list = (ObservableList<Mode>) mode.affichermode();
         
         for (Mode l:list){
             
             if (l.getLight_mode() == 1){
                 
                 moonMode();
                 mode.ModifiermodeDark();
                 
                 
             }else {
                 
                 lightMode();
                 mode.ModifiermodeLight();
                 
             }
         
         }
    }
        private void lightMode() {
           btnmoon.setVisible(true);
           btnlight.setVisible(false);
            
             
            Pane.getStylesheets().remove(getClass().getResource("style_dark.css").toString());
            Pane.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
             titrepage.setFill(Paint.valueOf("Black"));
                hboxnavbar.setStyle("-fx-background-color : #e0e4e7");   
                   ComboStream.setStyle("-fx-background-color:transparent;"+"-fx-border-color: #09a195");
        ComboJeux.setStyle("-fx-background-color:transparent;"+"-fx-border-color: #09a195");
        ComboCompétition.setStyle("-fx-background-color:transparent;"+"-fx-border-color: #09a195");
        ComboCommunaute.setStyle("-fx-background-color:transparent;"+"-fx-border-color: #09a195");
          ComboUser.setFill(Paint.valueOf("black"));
        //retour.setFill(Paint.valueOf("Black"));
       
        Image image = new Image("/black_ops/MediaStream/Logo.png");
    ImageVue.setImage(image);
        
    }

    private void moonMode() {
         btnmoon.setVisible(false);
            btnlight.setVisible(true);
            
           //Pane.getStylesheets().remove("style.css");
            Pane.getStylesheets().add(getClass().getResource("style_dark.css").toString());
            
        //retour.setFill(Paint.valueOf("white"));
            titrepage.setFill(Paint.valueOf("white"));
        hboxnavbar.setStyle("-fx-background-color : #0f1316");
        ComboUser.setFill(Paint.valueOf("white"));
    
        ComboStream.setStyle("-fx-prompt-text-fill:white;"+"-fx-border-color: #09a195");
        ComboJeux.setStyle("-fx-prompt-text-fill:white;"+"-fx-border-color: #09a195");
        ComboCompétition.setStyle("-fx-prompt-text-fill:white;"+"-fx-border-color: #09a195");
        ComboCommunaute.setStyle("-fx-prompt-text-fill:white;"+"-fx-border-color: #09a195");
              
            Image image = new Image("/black_ops/MediaStream/Logo_Blanc.png");
    ImageVue.setImage(image);
    }
    
         private void modeOn() {
   ModeController mode = new ModeController();
        
         ObservableList<Mode> list = (ObservableList<Mode>) mode.affichermode();
         
         for (Mode l:list){
             
             if (l.getLight_mode() == 1){
                 
             lightMode();
            
                 
                 
             }else {
                 
                 moonMode();
            
                 
             }
     
         }
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
      public void SwitchScene_comu(Stage stage, String nom, ActionEvent event) throws Exception {

        String path = "/black_ops/GUI/";

        FXMLLoader loader = new FXMLLoader(getClass().getResource(path + nom + ".fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
 
}

