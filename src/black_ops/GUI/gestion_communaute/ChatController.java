/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI.gestion_communaute;

import black_ops.Controller.Message_Controller;
import black_ops.Entity.Messagee;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ChatController implements Initializable {

    @FXML
    private JFXButton send_btn;
    private VBox scrolled;
    private ScrollPane sp;
    @FXML
    private AnchorPane anchorid;
    @FXML
    private VBox box;
    
    public static String client_email = "";
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        final ScrollPane sp = new ScrollPane();
        System.out.println("mail: "+client_email);
        show_msgs();
//        VBox.setVgrow(sp, Priority.ALWAYS);
    }    

    @FXML
    private void send_msg(ActionEvent event) {
        show_msgs();
    }
    private void clearTable(ActionEvent event) {
            Message_Controller mcl = new Message_Controller();
    //        ScrollPane scroll = new ScrollPane();
    //        scroll.setContent(checkboxContainer); 
            ObservableList<Messagee> list = mcl.afficherMessages();
            list.clear();
        }
    
    private void show_msgs() {
        final ScrollPane sp = new ScrollPane();
        Message_Controller mcl = new Message_Controller();
//        ScrollPane scroll = new ScrollPane();
//        scroll.setContent(checkboxContainer); 
        ObservableList<Messagee> list = mcl.afficherMessages();
        
        
        
        final VBox vb = new VBox();
        anchorid.getChildren().addAll(sp);
        VBox.setVgrow(sp, Priority.ALWAYS);
        HBox.setHgrow(sp, Priority.ALWAYS);
        for(Messagee l:list){
            HBox hb = new HBox();
            sp.setHbarPolicy(ScrollBarPolicy.NEVER);
            sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
            Label labelId = new Label(""+l.getId_cl());
            Label labelName = new Label(""+l.getContenu_message());
            Label labelDate = new Label(""+l.getDate_message());
            labelId.setId("labelId");
            labelName.setId("labelName");
            labelDate.setId("labelDate");
            labelDate.setAlignment(Pos.TOP_RIGHT);
//            sp.setFitToWidth(true);
//            sp.setFitToHeight(true);
//            sp.setVmax(600);
//            hb.getChildren().add(labelName);
//            hb.getChildren().add(labelId);
            vb.getChildren().add(labelId);
            
            vb.getChildren().add(labelName);
            
            vb.getChildren().add(labelDate);
//            chatBox.getChildren().add();
        }
//        sp.setVmax(440);
        sp.setPrefSize(500, 500);
        sp.setContent(vb);
//        chatBox.getChildren().add(new Label("Button "));
    }
    
}
