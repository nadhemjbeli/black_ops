/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI.gestion_communaute;

import black_ops.Controller.Message_Controller;
import black_ops.Entity.Messagee;
import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author ASUS
 */
public class Testchat extends Application {
    final ScrollPane sp = new ScrollPane();
    final VBox vb = new VBox();
    @Override
    public void start(Stage stage) {
        VBox box = new VBox();
        Scene scene = new Scene(box, 250, 250);
        stage.setScene(scene);
        stage.setTitle("Scroll Pane");
        box.getChildren().addAll(sp);
        VBox.setVgrow(sp, Priority.ALWAYS);
 
        
        Message_Controller mcl = new Message_Controller();
        ObservableList<Messagee> list = mcl.afficherMessages();
        for(Messagee l:list){
            sp.setHbarPolicy(ScrollBarPolicy.NEVER);
            sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
            Label labelId = new Label(""+l.getId_message());
            Label labelName = new Label(""+l.getContenu_message());
            labelId.setId("labelId");
            labelName.setId("labelName");
//            sp.setFitToWidth(true);
//            sp.setFitToHeight(true);
//            sp.setVmax(600);
            vb.getChildren().add(labelId);
            vb.getChildren().add(labelName);
//            chatBox.getChildren().add();
        }
 
        sp.setVmax(440);
        sp.setPrefSize(115, 150);
        sp.setContent(vb);
//        sp.vvalueProperty().addListener(new ChangeListener<Number>() {
//            public void changed(ObservableValue<? extends Number> ov,
//                Number old_val, Number new_val) {
//                    
//            }
//        });
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
