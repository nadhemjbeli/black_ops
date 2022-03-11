/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI;

import black_ops.Controller.CommentaireFiltre;
import black_ops.Controller.LiveStreamController;
import black_ops.Controller.ModeController;
import black_ops.Entity.Commentaire_Stream;
import black_ops.Entity.Live_Stream;
import black_ops.Entity.Mode;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import javafx.scene.text.Text;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author aZiz
 */
public class LiveStreamingController implements Initializable {
 private int idstream; 
    @FXML
    private TextField txtcomm;
    @FXML
    private Button btnsend;
    @FXML
    private Text retour;
    @FXML
    private VBox vboxmedia;
    @FXML
    private Label nomlive;
    @FXML
    private Label nomlive1;
    @FXML
    private VBox vboxcommentaire;
    private int index;
    @FXML
    private AnchorPane Pane;
    @FXML
    private Text titrepage;
    @FXML
    private Button btnmoon;
    @FXML
    private FontAwesomeIconView moonIcon;
    @FXML
    private Button btnlight;
    @FXML
    private FontAwesomeIconView lightIcon;
    @FXML
    private Rectangle rectinsta;
    @FXML
    private Rectangle rectfb;
    @FXML
    private Rectangle rectyou;
    @FXML
    private Rectangle recttw;
    
    public static String idclcom = "";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       setTextField(idstream);
       modeOn();
       if (idstream != 0){
        affichercommentaire();
       }
    }    
 
    
    
    public void setTextField(int id){
        
       idstream = id;
        
  
        System.out.println(idstream);
      if (idstream != 0 ){
                data();
      }
        
        
        
       
    }
    
    public void data(){
        
          LiveStreamController liveStream = new LiveStreamController();
        
       ObservableList<Live_Stream> list = (ObservableList<Live_Stream>) liveStream.afficherVideoStream(idstream);
WebView webview = new WebView();


for (Live_Stream ls:list){
    
    nomlive.setStyle("-fx-font-weight: bold");
    nomlive.setText(ls.getNom());
       webview.getEngine().loadContent("<iframe width=\"900\" height=\"600\" src=\"https://www.youtube.com/embed/"+ls.getPath()+"?autoplay=1&showinfo=0&controls=0&disablekb=1&modestbranding=1&rel=0\" frameborder=\"0\" allowfullscreen ></iframe>" +"","text/html") ;
       
       vboxmedia.getChildren().add(webview);

            
            
}
     
        
        
        
    }

    @FXML
    private void Afficherretour(MouseEvent event) {
        
        
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Annuler Ajout Information");
                                alert.setHeaderText("Êtes-vous sûr de vouloir retourner au Live Stream Videos");
                                     Optional<ButtonType> option = alert.showAndWait();
                                       if (option.get() == ButtonType.OK) {
                                          
                                          
          try {
          
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/black_ops/GUI/DefiLive.fxml"));
            Parent root = loader.load();
       
            
            nomlive.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

}
    }

    @FXML
    private void ajoutercomm(ActionEvent event) {
        
     
   CommentaireFiltre.loadConfigs();
        String Commentaire = txtcomm.getText();
      ArrayList<String> newCommentaire = CommentaireFiltre.badWordsFound(Commentaire);
     if (newCommentaire.isEmpty() ){
      
        if (! Commentaire.isEmpty() && Commentaire.length()<250){
               LiveStreamController liveStream = new LiveStreamController();
               liveStream.ajouterCommentaireLiveStream(Commentaire, idstream);
               
               txtcomm.setText("");
         
                affichercommentaire();
             
               
      
               
               
            
        }else {
            
              Alert alert = new Alert(Alert.AlertType.ERROR);
            
            alert.setHeaderText(null);
           alert.setContentText("Le commentaire ne doit pas être vide et il dépasse pas 250 caractère  ");
           txtcomm.setText("");
            alert.showAndWait();
            
            
        }
      }else {
         
           Alert alert = new Alert(Alert.AlertType.ERROR);
            
            alert.setHeaderText(null);
           alert.setContentText("Il faut pas dire de gros mot! "+CommentaireFiltre.badWordsFound(Commentaire).toString()+" est qualifié de gros mot");
           txtcomm.setText("");
            alert.showAndWait();
         
         CommentaireFiltre.filterText(Commentaire, "Gamer");
         
     }
        
    }
    
    
    public void affichercommentaire(){
        
          LiveStreamController liveStream = new LiveStreamController();
         ObservableList<Commentaire_Stream> ls =  (ObservableList<Commentaire_Stream>) liveStream.afficherCommentaireLiveStream(idstream);
      
       for (Commentaire_Stream cs:ls){
           
           if (index%2==0){
           
             Label labcontenu = new Label();
                labcontenu.setWrapText(true);
             labcontenu.setMinHeight(50);
           labcontenu.setMinWidth(50);
             labcontenu.setAlignment(Pos.TOP_RIGHT);
           labcontenu.setText("Name Gamer  "+idclcom+" : "+cs.getContenu());
           vboxcommentaire.getChildren().add(labcontenu);
                   
           }
           
           if (index % 2 == 1){
               
                 Label labcontenu = new Label();
                   labcontenu.setAlignment(Pos.TOP_RIGHT);
                labcontenu.setWrapText(true);
             labcontenu.setMinHeight(50);
             labcontenu.setMinWidth(50);
           
           labcontenu.setText("Gamer : "+cs.getContenu());
           vboxcommentaire.getChildren().add(labcontenu);
               
               
           }
           
           index++;
           
           
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
                  vboxcommentaire.setStyle("-fx-background-color:#ececec");
                      rectfb.setFill(Paint.valueOf("white"));
             rectinsta.setFill(Paint.valueOf("white"));
              recttw.setFill(Paint.valueOf("white"));
               rectyou.setFill(Paint.valueOf("white"));
        retour.setFill(Paint.valueOf("Black"));
        titrepage.setFill(Paint.valueOf("Black"));
        
    }

    private void moonMode() {
         btnmoon.setVisible(false);
            btnlight.setVisible(true);
            
           //Pane.getStylesheets().remove("style.css");
            Pane.getStylesheets().add(getClass().getResource("style_dark.css").toString());
            rectfb.setFill(Paint.valueOf("#0f1316"));
             rectinsta.setFill(Paint.valueOf("#0f1316"));
              recttw.setFill(Paint.valueOf("#0f1316"));
               rectyou.setFill(Paint.valueOf("#0f1316"));
                    vboxcommentaire.setStyle("-fx-background-color:#0f1316");
        retour.setFill(Paint.valueOf("white"));
        titrepage.setFill(Paint.valueOf("white"));
        
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
}
