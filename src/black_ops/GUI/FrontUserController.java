/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI;

import black_ops.Controller.InfoStreamController;
import black_ops.Controller.ModeController;
import black_ops.Controller.Nav_barController;
import black_ops.Entity.Info_Stream;
import black_ops.Entity.Mode;
import black_ops.Entity.Nav_Bar;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aZiz
 */
public class FrontUserController implements Initializable {
  private Stage stage;
    @FXML
    private VBox boxxx;
    private int index = 0;
    @FXML
    private VBox boxxx1;
    @FXML
    private AnchorPane Pane;
    @FXML
    private ComboBox ComboJeux;
    @FXML
    private ComboBox ComboStream;
    @FXML
    private ComboBox ComboCompétition;
    @FXML
    private FontAwesomeIconView ComboUser;
    @FXML
    private Button btnmoon;
    @FXML
    private FontAwesomeIconView moonIcon;
    @FXML
    private Button btnlight;
    @FXML
    private FontAwesomeIconView lightIcon;
    
 
    @FXML
    private ScrollPane ScrollPane;
    @FXML
    private Text titrepage;
    @FXML
    private ComboBox ComboCommunaute;
    @FXML
    private HBox hboxnavbar;
    @FXML
    private ImageView ImageVue;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                         //  ScrollPane scp = new ScrollPane(Pane);

      //scp.setFitToWidth(true);
modeOn() ;
        database();
      Navbar();
        // TODO
    }    
    
    
    
    public void database(){
               // ScrollPane scp = new ScrollPane();
                //scp.setFitToWidth(true);

        
   InfoStreamController infoStream = new InfoStreamController();
   ObservableList<Info_Stream> list = (ObservableList<Info_Stream>) infoStream.afficherInfoStream();
       // vbox.setItems((ObservableList<Info_Stream>) infoStream.afficherInfoStream()); 
       
 
       for (Info_Stream l:list){
           
           if (index%2 == 0){
//                ScrollBar sc = new ScrollBar();
// Group root = new Group();
// 
// root.getChildren().addAll(boxxx,sc);
// boxxx.setLayoutX(20);
// boxxx.setSpacing(20);
// sc.setOrientation(Orientation.VERTICAL);
// sc.setPrefHeight(500);
            //tableview.setItems((ObservableList<Info_Stream>) l.getImageV());
             FontAwesomeIconView goIcon = new FontAwesomeIconView(FontAwesomeIcon.ARROW_CIRCLE_RIGHT);
                       // FontAwesomeIconView pauseIcon = new FontAwesomeIconView(FontAwesomeIcon.PAUSE);
                        goIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#09a195;"
                        );
            boxxx.setAlignment(Pos.CENTER);
             boxxx.setMinWidth(200);
           
 //boxxx.setPadding(new Insets(15));
 
 Label labText = new Label(l.getNom(),goIcon);
      
        //   scp.setContent(labText);
          labText.setMinHeight(50);
           labText.setStyle("-fx-font-weight: bold");
    boxxx.getChildren().add(new ImageView(l.getImageV2()));
        boxxx.getChildren().add(labText);
  // imageview.setImage(l.getImageV2());
Label lab = new Label(l.getDescription());
lab.setWrapText(true);
lab.setTextAlignment(TextAlignment.JUSTIFY);
lab.setMinHeight(85);
//lab.setTranslateX(25);
     // lab.setTranslateY(25);
//lab.setMinWidth(170);
//lab.setFont(new Font("Gordita Light"));
                      //boxxx.getChildren().add(lab);
                       goIcon.setOnMouseClicked((MouseEvent event) -> {
                                
                                 int id = l.getId();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/black_ops/GUI/FrontUserDescription.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                ex.getMessage();
                            }
                            
                           FrontUserDescriptionController userdescription = loader.getController();
                       
                           userdescription.setTextField(id);
                            Parent parents = loader.getRoot();
//                            Stage stage = new Stage();
//                            stage.setScene(new Scene(parents));
//                            stage.initStyle(StageStyle.UTILITY);
//                            stage.show();
                            Pane.getScene().setRoot(parents);
                            });
                       
                      
           }
            if (index%2 == 1){
                
                      FontAwesomeIconView goIcon = new FontAwesomeIconView(FontAwesomeIcon.ARROW_CIRCLE_RIGHT);
                       // FontAwesomeIconView pauseIcon = new FontAwesomeIconView(FontAwesomeIcon.PAUSE);
                        goIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#09a195;"
                        );
            //tableview.setItems((ObservableList<Info_Stream>) l.getImageV());
             boxxx1.setAlignment(Pos.CENTER);
                   boxxx1.setMinWidth(200);
 Label labText = new Label(l.getNom(),goIcon);
           boxxx1.getChildren().add(labText);
           labText.setMinHeight(50);
           labText.setStyle("-fx-font-weight: bold");
    boxxx1.getChildren().add(new ImageView(l.getImageV2()));
  // imageview.setImage(l.getImageV2());
Label lab = new Label(l.getDescription());
lab.setWrapText(true);
lab.setTextAlignment(TextAlignment.JUSTIFY);
lab.setMinHeight(85);
  // imageview.setImage(l.getImageV2());

                    //  boxxx1.getChildren().add(lab);
                        //scp.setContent(boxxx1);
                        
                           goIcon.setOnMouseClicked((MouseEvent event) -> {
                                
                                 int id = l.getId();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/black_ops/GUI/FrontUserDescription.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                ex.getMessage();
                            }
                            
                           FrontUserDescriptionController userdescription = loader.getController();
                       
                           userdescription.setTextField(id);
                            Parent parents = loader.getRoot();
//                            Stage stage = new Stage();
//                            stage.setScene(new Scene(parents));
//                            stage.initStyle(StageStyle.UTILITY);
//                            stage.show();
                            Pane.getScene().setRoot(parents);
                            });
                        
                        
                        
           }

           index ++ ;
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
         
//            IsLight = !IsLight;
//        
//        if (IsLight){
//            setIsLight(true);
//            lightMode();
//            System.out.println(IsLight);
//                        System.out.println(isIsLight());
//
//
//            
//        }else {
//            
//             setIsLight(false);
//            moonMode();
//             System.out.println(isIsLight());
//        }
        
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


