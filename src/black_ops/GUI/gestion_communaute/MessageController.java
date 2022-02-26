/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI.gestion_communaute;

import black_ops.Controller.JeuController;
import black_ops.Controller.Message_Controller;
import black_ops.Entity.Messagee;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class MessageController implements Initializable {

    @FXML
    private TextField id_message;
    @FXML
    private TextArea txt_contenu;
    @FXML
    private TextField txt_id_cl;
    @FXML
    private TextField txt_id_sous_cat;
    @FXML
    private TextField date_message;
    @FXML
    private Button btn_add;
    @FXML
    private Button btn_delete;
    @FXML
    private Button btn_edit;
    @FXML
    private Button btn_refresh;
    @FXML
    private Button btn_search;
    
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TableColumn<Messagee, Integer> col_id;
    @FXML
    private TableColumn<Messagee, Timestamp> col_date;
    @FXML
    private TableColumn<Messagee, Integer> col_id_client;
    @FXML
    private TableColumn<Messagee, Integer> col_id_sous;
    @FXML
    private TableColumn<Messagee, String> col_contenu_message;
    @FXML
    private TableView<Messagee> table_messages;
    @FXML
    private TextField txt_search;
    @FXML
    private Button btn_diselect;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showMessages();
    }    

    @FXML
    private void add_message(ActionEvent event) {
        try{
           String contenu = txt_contenu.getText();
            String id_cl = txt_id_cl.getText();
            String sous_cat = txt_id_sous_cat.getText();
            
           int s_c = Integer.parseInt(sous_cat);
           int c_l = Integer.parseInt(id_cl);
//           Timestamp ts = Timestamp.valueOf(text);
           Messagee msg = new Messagee(1, contenu, s_c, c_l);
           Message_Controller msgc = new Message_Controller();
           msgc.ajouterMessage(msg);
           showMessages(); 
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void delete_message(ActionEvent event) {
        try{
            String idmsg=id_message.getText();
            int id_msg = Integer.parseInt(idmsg);
           Message_Controller msgc = new Message_Controller();
           msgc.DeleteMessage(id_msg);
           showMessages();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void edit_message(ActionEvent event) {
        try{
            String idmsg=id_message.getText();
            int id_msg = Integer.parseInt(idmsg);
            String contenu = txt_contenu.getText();
            String id_cl = txt_id_cl.getText();
            String sous_cat = txt_id_sous_cat.getText();
            
           int s_c = Integer.parseInt(sous_cat);
           int c_l = Integer.parseInt(id_cl);
//           Timestamp ts = Timestamp.valueOf(text);
           Messagee msg = new Messagee(id_msg, contenu, s_c, c_l);
           Message_Controller msgc = new Message_Controller();
           msgc.UpdateMessage(msg);
           showMessages();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void refreshTable(ActionEvent event) {
    }
    
    public void showMessages(){
        Message_Controller mcl = new Message_Controller();
       
        ObservableList<Messagee> list = mcl.afficherMessages();
     
        col_id.setCellValueFactory(new PropertyValueFactory<Messagee, Integer>("id_message"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date_message"));
        col_contenu_message.setCellValueFactory(new PropertyValueFactory<>("contenu_message"));
        col_id_client.setCellValueFactory(new PropertyValueFactory<>("id_cl"));
        col_id_sous.setCellValueFactory(new PropertyValueFactory<>("id_sous_cat"));
        table_messages.setItems(list);
        
        
    }
    
    

    @FXML
    private void handleMouseAction(MouseEvent event) {
        Messagee msg = table_messages.getSelectionModel().getSelectedItem();
        id_message.setText(""+msg.getId_message());
        txt_contenu.setText(""+msg.getContenu_message());
        txt_id_cl.setText(""+msg.getId_cl());
        date_message.setText(""+msg.getDate_message());
        txt_id_sous_cat.setText(""+msg.getId_sous_cat());
    }

    @FXML
    private void diselect(ActionEvent event) {
        id_message.setText("");
        txt_contenu.setText("");
        txt_id_cl.setText("");
        date_message.setText("");
        txt_id_sous_cat.setText("");
    }

//    @FXML
//    private void search_messages_par_contenu(ActionEvent event) {
//        Message_Controller mcl = new Message_Controller();
//       
//        ObservableList<Messagee> list = mcl.afficherMessages();
//     
//        col_id.setCellValueFactory(new PropertyValueFactory<Messagee, Integer>("id_message"));
//        col_date.setCellValueFactory(new PropertyValueFactory<>("date_message"));
//        col_contenu_message.setCellValueFactory(new PropertyValueFactory<>("contenu_message"));
//        col_id_client.setCellValueFactory(new PropertyValueFactory<>("id_cl"));
//        col_id_sous.setCellValueFactory(new PropertyValueFactory<>("id_sous_cat"));
//        table_messages.setItems(list);
//    }
    
    
    
}
