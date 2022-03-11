/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI;

import black_ops.Controller.ModeController;
import black_ops.Controller.ReplayStreamController;
import black_ops.Entity.Mode;
import black_ops.Entity.Replay_Stream;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author aZiz
 */
public class FrontUserWatChReplayController implements Initializable {

    @FXML
    private VBox vbox;
  private Media media;

   private MediaPlayer mediaPlayer;
    @FXML
    private Text retour;
    @FXML
    private Slider sliderTime;
    @FXML
    private Label totaltime;
    @FXML
    private Label currenttime;
    @FXML
    private AnchorPane Pane;
    @FXML
    private Rectangle rectainsta;
    @FXML
    private Rectangle rectafb;
    @FXML
    private Rectangle rectayou;
    @FXML
    private Rectangle rectatw;
    @FXML
    private Button btnmoon;
    @FXML
    private FontAwesomeIconView moonIcon;
    @FXML
    private Button btnlight;
    @FXML
    private FontAwesomeIconView lightIcon;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        modeOn();
        // TODO
    }    

    public void VideoId(int id) {
     ReplayStreamController replayStream = new ReplayStreamController();
        
       ObservableList<Replay_Stream> list = (ObservableList<Replay_Stream>) replayStream.afficherReplay(id);
       
       for (Replay_Stream l:list){
         vbox.setAlignment(Pos.CENTER);
            Label labtxt = new Label(l.getNom());
               labtxt.setMinHeight(50);
           labtxt.setStyle("-fx-font-weight: bold");
        
            vbox.getChildren().add(labtxt);
            
              File file = new File ("src/black_ops/MediaStream/"+l.getUrl());
                               media = new Media(file.toURI().toString());
                                 mediaPlayer = new MediaPlayer(media);
                                    
          
              
    
            MediaView mediaview = new MediaView();
          mediaview.setMediaPlayer(mediaPlayer);
          mediaPlayer.setAutoPlay(true);
           FontAwesomeIconView playIcon = new FontAwesomeIconView(FontAwesomeIcon.PLAY);
                        FontAwesomeIconView pauseIcon = new FontAwesomeIconView(FontAwesomeIcon.PAUSE);
                            FontAwesomeIconView volumeIcon = new FontAwesomeIconView(FontAwesomeIcon.VOLUME_OFF);
                              FontAwesomeIconView repeatIcon = new FontAwesomeIconView(FontAwesomeIcon.REPEAT);
                        Slider sliderVolume = new Slider();
                        sliderVolume.setMax(1);
                        repeatIcon.setVisible(false);
                        mediaPlayer.volumeProperty().bindBidirectional(sliderVolume.valueProperty());
                        
                        playIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#09a195;"
                        );
                        pauseIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#09a195;"
                        );
                        volumeIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#09a195;"
                        );
                          repeatIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#09a195;"
                        );
                        
                        
                         playIcon.setOnMouseClicked((MouseEvent event) -> {
                                 mediaPlayer.play();
                         });
                        pauseIcon.setOnMouseClicked((MouseEvent event) -> {
                       mediaPlayer.pause();
                             
                         });
                                                  
                                                  
                      repeatIcon.setOnMouseClicked((MouseEvent event) -> {
                          repeatIcon.setVisible(false);
                           playIcon.setVisible(true);
                           pauseIcon.setVisible(true);
                     if ( mediaPlayer.getStatus() != MediaPlayer.Status.READY ){
                         
                         mediaPlayer.seek(Duration.seconds(0.0));
                         bindCurrentTimelabel();
                     }
                             
                         });                             
//                                                      sliderVolume.setOnMouseClicked((MouseEvent event) -> {
//                        if ((sliderVolume.getValue() != 0.0)){
//                
//                volumeIcon.setIcon(FontAwesomeIcon.VOLUME_DOWN);
//                
//                
//            }
//            if (sliderVolume.getValue() > 0.7){
//                
//                    volumeIcon.setIcon(FontAwesomeIcon.VOLUME_UP);
//                
//                
//            }
//              if (sliderVolume.getValue() == 0.0){
//                
//                    volumeIcon.setIcon(FontAwesomeIcon.VOLUME_OFF);
//                
//                
//            }
//                             
//                         });


sliderVolume.valueProperty().addListener(new InvalidationListener() {
             @Override
             public void invalidated(Observable observable) {
mediaPlayer.setVolume(sliderVolume.getValue());
    if ((sliderVolume.getValue() != 0.0)){
                
                volumeIcon.setIcon(FontAwesomeIcon.VOLUME_DOWN);
                
                
            }
            if (sliderVolume.getValue() > 0.7){
                
                    volumeIcon.setIcon(FontAwesomeIcon.VOLUME_UP);
                
                
            }
              if (sliderVolume.getValue() == 0.0){
                
                    volumeIcon.setIcon(FontAwesomeIcon.VOLUME_OFF);
                
                
            }

             }
         });

                                                  
             mediaview.setFitHeight(510);
            mediaview.setFitWidth(1000);
            vbox.getChildren().add(mediaview);
          
    
                          HBox managebtn = new HBox(repeatIcon,playIcon,pauseIcon,volumeIcon,sliderVolume);
                        managebtn.setStyle("-fx-alignment:center");
                        
                        HBox.setMargin(playIcon, new Insets(10, 10, 10, 3));
                      HBox.setMargin(pauseIcon, new Insets(10, 10, 10, 3));
                      HBox.setMargin(volumeIcon, new Insets(10, 10, 10, 3));
                       HBox.setMargin(repeatIcon, new Insets(10, 10, 10, 3));
                    
                        vbox.getChildren().add(managebtn);
                        
            Label labdescription = new Label(l.getDescription());
            
              labdescription.setMinHeight(50);
           labdescription.setStyle("-fx-font-family: Orkney");
           labdescription.setAlignment(Pos.CENTER_LEFT);
           vbox.getChildren().add(labdescription);
           
           
           bindCurrentTimelabel();
           
           mediaPlayer.totalDurationProperty().addListener(new ChangeListener<Duration>() {
             @Override
             public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                sliderTime.setMax(newValue.toSeconds());
                totaltime.setText(getTime(newValue));
             }
         
               
           });
           
           
           
           sliderTime.valueChangingProperty().addListener(new ChangeListener<Boolean>(){
             @Override
             public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                 if (!newValue){
                     
                     mediaPlayer.seek(Duration.seconds(sliderTime.getValue()));
                 }
             }
           });
           
           
           
           sliderTime.valueProperty().addListener(new ChangeListener<Number>(){
             @Override
             public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double currenttime = mediaPlayer.getCurrentTime().toSeconds();
                
                if (Math.abs(currenttime - newValue.doubleValue()) > 0.5){
                    
                    mediaPlayer.seek(Duration.seconds(newValue.doubleValue()));
                    
                }
             }
              
           });
           
           
           mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>(){
             @Override
             public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                 if (!sliderTime.isValueChanging()){
                     
                     sliderTime.setValue(newValue.toSeconds());
                     
                 }
             }
               
           });
           
           mediaPlayer.setOnEndOfMedia(new Runnable() {
             @Override
             public void run() {
               
               repeatIcon.setVisible(true);
               playIcon.setVisible(false);
                pauseIcon.setVisible(false);
               
               if (!currenttime.textProperty().equals(totaltime.textProperty())){
                   currenttime.textProperty().unbind();
                   currenttime.setText(getTime(mediaPlayer.getTotalDuration())+ " / ");
                   
                   
               }
             }
         });
          
       }
       
    }

    @FXML
    private void Afficherretour(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Annuler Ajout Information");
                                alert.setHeaderText("Êtes-vous sûr de vouloir retourner au Replays Stream");
                                     Optional<ButtonType> option = alert.showAndWait();
                                       if (option.get() == ButtonType.OK) {
                                          mediaPlayer.stop();
          try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/black_ops/GUI/FrontuserVideo.fxml"));
            Parent root = loader.load();
       
            
            vbox.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

}
    }

    private void bindCurrentTimelabel() {
        currenttime.textProperty().bind(Bindings.createStringBinding(new Callable<String>(){
            @Override
            public String call() throws Exception {
                return getTime(mediaPlayer.getCurrentTime()) + " / ";
            }
            
            
            
            
            
            
            
            
            
        }, mediaPlayer.currentTimeProperty()));
    }

    public String getTime(Duration time){
        
       int hours = (int) time.toHours();
         int minutes = (int) time.toMinutes();
          int seconds = (int) time.toSeconds();
    
    
    if (seconds > 59 ) seconds = seconds % 60 ;
    if (minutes > 59 ) minutes = minutes % 60 ;
    if (hours > 59 ) hours = hours % 60 ;
    
    
    if (hours > 0 ) return String.format("%d:%02d:%02d", 
            hours,
            minutes,
            seconds);
    
    else return String.format("%02d:%02d",
            minutes,
            seconds);
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
                  
                      rectafb.setFill(Paint.valueOf("white"));
             rectainsta.setFill(Paint.valueOf("white"));
              rectatw.setFill(Paint.valueOf("white"));
               rectayou.setFill(Paint.valueOf("white"));
        retour.setFill(Paint.valueOf("Black"));
       // titrepage.setFill(Paint.valueOf("Black"));
        
    }

    private void moonMode() {
         btnmoon.setVisible(false);
            btnlight.setVisible(true);
            
           //Pane.getStylesheets().remove("style.css");
            Pane.getStylesheets().add(getClass().getResource("style_dark.css").toString());
            rectafb.setFill(Paint.valueOf("#0f1316"));
             rectainsta.setFill(Paint.valueOf("#0f1316"));
              rectatw.setFill(Paint.valueOf("#0f1316"));
               rectayou.setFill(Paint.valueOf("#0f1316"));
                  
        retour.setFill(Paint.valueOf("white"));
        //titrepage.setFill(Paint.valueOf("white"));
        
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
