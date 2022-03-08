/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI.gestion_communaute;

import com.jfoenix.controls.JFXSlider;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class Video_playController implements Initializable {
    
    Stage stage;
    Scene scene;

    @FXML
    private MediaView mv;
    private MediaPlayer mp;
    private Media me;
    @FXML
    private JFXSlider volume_slider;
    @FXML
    private Button btn_play;
    private FontAwesomeIconView PLAY;
    public static int id = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        String path = new File("src/media/"+id+".mp4").getAbsolutePath();
//        String path = new File("src/media/1.mp4").getAbsolutePath();
        me = new Media(new File(path).toURI().toString());
        mp = new MediaPlayer(me);
        mv.setMediaPlayer(mp);
        mp.setAutoPlay(true);
        DoubleProperty width = mv.fitWidthProperty();
        DoubleProperty height = mv.fitHeightProperty();
        width.bind(Bindings.selectDouble(mv.sceneProperty(), "width"));
        height.bind(Bindings.selectDouble(mv.sceneProperty(), "height"));
        volume_slider.setValue(mp.getVolume() * 100);
        volume_slider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                mp.setVolume(volume_slider.getValue() / 100);
            }
        });
    }   
    @FXML
    private void play_and_pause(ActionEvent event){
        try{
            mp.play();
            if(mp.getStatus().equals(Status.PAUSED)) {
                mp.play();
            }
            else if(mp.getStatus().equals(Status.PLAYING))
            {
                mp.pause();
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        
    }
    @FXML
    private void pause(ActionEvent event){
        mp.pause();
    }
    @FXML
    private void normal(ActionEvent event){
        mp.setRate(1);
    }
    @FXML
    private void fast(ActionEvent event){
        mp.setRate(1.25f);
    }
    @FXML
    private void slow(ActionEvent event){
        mp.setRate(.75f);
    }
    @FXML
    private void reload(ActionEvent event){
        mp.seek(mp.getStartTime());
        mp.play();
    }
    private void start(ActionEvent event){
        mp.seek(mp.getStartTime());
        mp.stop();
    }
    @FXML
    private void last(ActionEvent event){
        mp.seek(mp.getTotalDuration());
        mp.stop();
        
    }

    @FXML
    private void show_video_uploade_scene(ActionEvent event) {
        try {
            last(event);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("video_uploade.fxml"));
            Parent root = loader.load();
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


    

    @FXML
    private void click_key(ActionEvent event) {
//        btn_play.setOnKeyPressed(new EventHandler<KeyEvent>(){
//            @Override
//            public void handle(KeyEvent keyEvent) {
//                if(keyEvent.getCode()==KeyCode.SPACE){
//                mp.pause();
//
//        }
//            }
//            
//        });
//        
//        btn_play.setOnMouseClicked(new EventHandler<MouseEvent>(){
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                if(mouseEvent)
//            }
//            
//        });
        
        
    }

    @FXML
    private void click_to_pause(MouseEvent event) {
        System.out.println(mp.getStatus());
//        mp.pause();
        if(mp.getStatus().equals(Status.PAUSED)) {
            mp.play();
        }
        else if(mp.getStatus().equals(Status.PLAYING))
            mp.pause();
    }

    public void view(int id) {
        System.out.println("id : "+id);
        
        
    }
    
}
