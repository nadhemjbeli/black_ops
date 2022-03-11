/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI;

import black_ops.Controller.Defi_StreamController;
import black_ops.Controller.LiveStreamController;
import black_ops.Controller.ModeController;
import black_ops.Controller.Nav_barController;
import black_ops.Entity.Defi_Stream;
import black_ops.Entity.Live_Stream;
import black_ops.Entity.Mode;
import black_ops.Entity.Nav_Bar;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.ColorInput;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aZiz
 */
public class DefiLiveController implements Initializable {
  private Stage stage;
    @FXML
    private VBox box1;
    @FXML
    private VBox box2;
    @FXML
    private VBox box3;
    private int index = 1;

    @FXML
    private ComboBox ComboJeux;
    @FXML
    private ComboBox ComboStream;
    @FXML
    private ComboBox ComboCompétition;
    @FXML
    private FontAwesomeIconView ComboUser;
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
    private AnchorPane Pane;
    @FXML
    private ScrollPane ScrollPane;
    @FXML
    private Text titrepage;
    @FXML
    private HBox hboxnavbar;
    @FXML
    private ImageView ImageVue;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
       Navbar();
       
       modeOn();
        
        data();
        // TODO
        
    
    }    
    
    
    public void data(){
     

 
          LiveStreamController liveStream = new LiveStreamController();
   ObservableList<Live_Stream> list = (ObservableList<Live_Stream>) liveStream.afficherLiveStream();
   Defi_StreamController defiStream = new Defi_StreamController ();
      ObservableList<Defi_Stream> defi ; 

    for (Live_Stream l:list){
        
        if (index == 1){
   
           box1.setAlignment(Pos.CENTER);

         
         if (l.getVisiblte().equals("Afficher")){
           Label labVisi = new Label("Dans quelques instants");
           labVisi.setStyle("-fx-font-weight: bold;"+"-fx-text-fill:#09a195;");
           labVisi.setAlignment(Pos.CENTER_LEFT);
           labVisi.setMinHeight(40);
          box1.getChildren().add(labVisi);
         }if (l.getVisiblte().equals("En cours")) {
             
              Label labVisi = new Label("Live");
           labVisi.setStyle("-fx-font-weight: bold;"+"-fx-text-fill:#ff0000;");
           labVisi.setAlignment(Pos.CENTER_LEFT);
           labVisi.setMinHeight(40);
          box1.getChildren().add(labVisi);
             
             
             
         }
        Label labText = new Label(l.getNom());
        labText.setStyle("-fx-font-weight: bold");
        labText.setMinHeight(50);
    
        int id = (l.getId_defi());
        defi = (ObservableList<Defi_Stream>) defiStream.afficherDefi(id);
    Label labdescriDefi = new Label();
    ImageView image = null;
      for(Defi_Stream d:defi){
               //System.out.println("id1="+id);
               
               image = new ImageView(d.getImagev());
              
               
         
           labdescriDefi.setText(d.getDescription());
          
      } 
         if (l.getVisiblte().equals("En cours")) {
      image.setId("img");
      ColorAdjust monochrome = new ColorAdjust();
    monochrome.setSaturation(-1.0);

       Blend blush = new Blend(
        BlendMode.MULTIPLY,
        monochrome,
        new ColorInput(
            0,
            0,
            image.getImage().getWidth(),
            image.getImage().getHeight(),
              
            Color.DARKSEAGREEN
        )
    );
          image.effectProperty().bind(
        Bindings
          .when(image.hoverProperty())
            .then((Effect) blush)
            .otherwise((Effect) null)
    );
      }
     
       box1.getChildren().add((image));
     labdescriDefi.setWrapText(true);
labdescriDefi.setTextAlignment(TextAlignment.JUSTIFY);
labdescriDefi.setMinHeight(85);
      
         box1.getChildren().add(labText);
         box1.getChildren().add(labdescriDefi);
        
        
       if (l.getVisiblte().equals("En cours")){
         image.setOnMouseClicked((MouseEvent event) -> {
         
              FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/black_ops/GUI/LiveStream.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                ex.getMessage();
                            }
                LiveStreamingController livestreaming = loader.getController();
                     
                            livestreaming.setTextField(l.getId());
                            Parent parent = loader.getRoot();
                           // Stage stage = new Stage();
                           // stage.setScene(new Scene(parent));
                           // stage.initStyle(StageStyle.UTILITY);
                            //stage.show();
                            box1.getScene().setRoot(parent);
         
         
         
         
         });
       }
        }
        
        if (index  == 2){
            
              box2.setAlignment(Pos.CENTER);
    
         
         if (l.getVisiblte().equals("Afficher")){
           Label labVisi = new Label("Dans quelques instants");
           labVisi.setStyle("-fx-font-weight: bold;"+"-fx-text-fill:#09a195;");
           labVisi.setAlignment(Pos.CENTER_LEFT);
           labVisi.setMinHeight(40);
          box2.getChildren().add(labVisi);
         }if (l.getVisiblte().equals("En cours")) {
             
              Label labVisi = new Label("Live");
           labVisi.setStyle("-fx-font-weight: bold;"+"-fx-text-fill:#ff0000;");
           labVisi.setAlignment(Pos.CENTER_LEFT);
           labVisi.setMinHeight(40);
          box2.getChildren().add(labVisi);
             
             
             
         }
        Label labText = new Label(l.getNom());
        labText.setStyle("-fx-font-weight: bold");
        labText.setMinHeight(50);
      
        int id = (l.getId_defi());
          //  System.out.println("id2="+id);
        defi = (ObservableList<Defi_Stream>) defiStream.afficherDefi(id);
    Label labdescriDefi = new Label();
        ImageView image = null;
      for(Defi_Stream d:defi){
          
          image = new ImageView(d.getImagev());
           labdescriDefi.setText(d.getDescription());
          
      }  
      if (l.getVisiblte().equals("En cours")) {
      image.setId("img");
      ColorAdjust monochrome = new ColorAdjust();
    monochrome.setSaturation(-1.0);

       Blend blush = new Blend(
        BlendMode.MULTIPLY,
        monochrome,
        new ColorInput(
            0,
            0,
            image.getImage().getWidth(),
            image.getImage().getHeight(),
              
            Color.DARKSEAGREEN
        )
    );
          image.effectProperty().bind(
        Bindings
          .when(image.hoverProperty())
            .then((Effect) blush)
            .otherwise((Effect) null)
    );
      }
      box2.getChildren().add(image);
        labdescriDefi.setWrapText(true);
labdescriDefi.setTextAlignment(TextAlignment.JUSTIFY);
labdescriDefi.setMinHeight(85);
          box2.getChildren().add(labText);
             box2.getChildren().add(labdescriDefi);
            
                if (l.getVisiblte().equals("En cours")){
         image.setOnMouseClicked((MouseEvent event) -> {
         
              FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/black_ops/GUI/LiveStream.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                ex.getMessage();
                            }
                LiveStreamingController livestreaming = loader.getController();
                     
                            livestreaming.setTextField(l.getId());
                            Parent parent = loader.getRoot();
                           // Stage stage = new Stage();
                           // stage.setScene(new Scene(parent));
                           // stage.initStyle(StageStyle.UTILITY);
                            //stage.show();
                            box1.getScene().setRoot(parent);
         
         
         
         
         });
       }
            
        }
        
        
        if (index == 3){
            
              box3.setAlignment(Pos.CENTER);
         if (l.getVisiblte().equals("Afficher")){
           Label labVisi = new Label("Dans quelques instants");
           labVisi.setStyle("-fx-font-weight: bold;"+"-fx-text-fill:#09a195;");
           labVisi.setAlignment(Pos.CENTER_LEFT);
           labVisi.setMinHeight(40);
          box3.getChildren().add(labVisi);
         }if (l.getVisiblte().equals("En cours")) {
             
              Label labVisi = new Label("Live");
           labVisi.setStyle("-fx-font-weight: bold;"+"-fx-text-fill:#ff0000;");
           labVisi.setAlignment(Pos.CENTER_LEFT);
           labVisi.setMinHeight(40);
          box3.getChildren().add(labVisi);
             
             
             
         }
        Label labText = new Label(l.getNom());
        labText.setStyle("-fx-font-weight: bold");
        labText.setMinHeight(50);
      
        int id = (l.getId_defi());
        defi = (ObservableList<Defi_Stream>) defiStream.afficherDefi(id);
    Label labdescriDefi = new Label();
        ImageView image = null;
      for(Defi_Stream d:defi){
          
          image = new ImageView(d.getImagev());
     
          
           labdescriDefi.setText(d.getDescription());
      }  
      
         if (l.getVisiblte().equals("En cours")) {
      image.setId("img");
      ColorAdjust monochrome = new ColorAdjust();
    monochrome.setSaturation(-1.0);

       Blend blush = new Blend(
        BlendMode.MULTIPLY,
        monochrome,
        new ColorInput(
            0,
            0,
            image.getImage().getWidth(),
            image.getImage().getHeight(),
              
            Color.DARKSEAGREEN
        )
    );
          image.effectProperty().bind(
        Bindings
          .when(image.hoverProperty())
            .then((Effect) blush)
            .otherwise((Effect) null)
    );
      }
      box3.getChildren().add(image);
        labdescriDefi.setWrapText(true);
labdescriDefi.setTextAlignment(TextAlignment.JUSTIFY);
labdescriDefi.setMinHeight(85);
          box3.getChildren().add(labText);
            
             box3.getChildren().add(labdescriDefi);
             
                if (l.getVisiblte().equals("En cours")){
         image.setOnMouseClicked((MouseEvent event) -> {
         
              FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/black_ops/GUI/LiveStream.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                ex.getMessage();
                            }
                LiveStreamingController livestreaming = loader.getController();
                     
                            livestreaming.setTextField(l.getId());
                            Parent parent = loader.getRoot();
                           // Stage stage = new Stage();
                           // stage.setScene(new Scene(parent));
                           // stage.initStyle(StageStyle.UTILITY);
                            //stage.show();
                            box1.getScene().setRoot(parent);
         
         
         
         
         });
       }
        }
        
        index ++;
      if (index == 4 ){
          
          index = 1;
      }
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
                  
        //retour.setFill(Paint.valueOf("Black"));
        titrepage.setFill(Paint.valueOf("Black"));
             hboxnavbar.setStyle("-fx-background-color : #e0e4e7");   
                   ComboStream.setStyle("-fx-background-color:transparent;"+"-fx-border-color: #09a195");
        ComboJeux.setStyle("-fx-background-color:transparent;"+"-fx-border-color: #09a195");
        ComboCompétition.setStyle("-fx-background-color:transparent;"+"-fx-border-color: #09a195");
        ComboCommunaute.setStyle("-fx-background-color:transparent;"+"-fx-border-color: #09a195");
          ComboUser.setFill(Paint.valueOf("black"));
       
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
