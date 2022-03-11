/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI.gestion_communaute.user.video_user_play;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class Video_user_playController implements Initializable {

    Stage stage;
    Scene scene;

    @FXML
    private MediaView mv;
    public static MediaPlayer mp;
    private Media me;
    @FXML
    private JFXSlider volume_slider;
    @FXML
    private Button btn_play;
    private FontAwesomeIconView PLAY;
    public static int id = 0;
    @FXML
    private TextField hidden_text;
    @FXML
    private JFXButton btn_exit;
    @FXML
    private JFXSlider time_slider;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        
        // Listen to the slider. When it changes, seek with the player.
        // MediaPlayer.seek does nothing when the player is stopped, so the play/pause button's handler
        // always sets the start time to the slider's current value, and then resets it to 0 after starting the player.
        InvalidationListener sliderChangeListener = (o-> {
            Duration seekTo = Duration.seconds(time_slider.getValue());
            mp.seek(seekTo);
        });
        time_slider.valueProperty().addListener(sliderChangeListener);

        // Link the player's time to the slider
        mp.currentTimeProperty().addListener(l-> {
            // Temporarily remove the listener on the slider, so it doesn't respond to the change in playback time
            // I thought timeSlider.isValueChanging() would be useful for this, but it seems to get stuck at true
            // if the user slides the slider instead of just clicking a position on it.
            time_slider.valueProperty().removeListener(sliderChangeListener);

            // Keep timeText's text up to date with the slider position.
            Duration currentTime = mp.getCurrentTime();
            int value = (int) currentTime.toSeconds();
            time_slider.setValue(value);

            // Re-add the slider listener
            time_slider.valueProperty().addListener(sliderChangeListener);
        });
    }    

    @FXML
    private void play_and_pause(ActionEvent event){
        try{
            mp.play();
            if(mp.getStatus().equals(MediaPlayer.Status.PAUSED)) {
                mp.play();
            }
            else if(mp.getStatus().equals(MediaPlayer.Status.PLAYING))
            {
                mp.pause();
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        
    }
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
//        try {
            last(event);
            stage = (Stage) btn_exit.getScene().getWindow();
            // do what you have to do
            stage.close();
//            Platform.exit();
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/black_ops/GUI/gestion_communaute/user/video_list_user/video_list_user.fxml"));
//            Parent root = loader.load();
//            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//            scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
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
        if(mp.getStatus().equals(MediaPlayer.Status.PAUSED)) {
            mp.play();
        }
        else if(mp.getStatus().equals(MediaPlayer.Status.PLAYING))
            mp.pause();
    }

    public void view(int id) {
        System.out.println("id : "+id);
        
        
    }
    
}
