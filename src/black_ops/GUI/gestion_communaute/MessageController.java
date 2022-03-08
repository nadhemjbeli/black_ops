/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI.gestion_communaute;

import black_ops.Controller.JeuController;
import black_ops.Controller.Message_Controller;
import black_ops.Entity.Messagee;
import black_ops.config.MaConnexion;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
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
    private TextField txt_id_cl;
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
    public static String admin_email = "";
    
    
    Connection mc;
    PreparedStatement ste;
    @FXML
    private JFXComboBox liste_cl;
    @FXML
    private JFXComboBox liste_sc;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fill_client_combo();
        fill_s_c_combo();
        showMessages();
    }    

    @FXML
    private void add_message(ActionEvent event) {
        try{
            String id_cl = liste_cl.getSelectionModel().getSelectedItem().toString();
            String id_s_c = liste_sc.getSelectionModel().getSelectedItem().toString();


            String sql2 = "select id_cl from client where Pseaudo_Cl =?";
            int idc = select_id_cl(sql2, id_cl);


            String sql3 = "select id_SousCat from sous_categorie where nom_SousCat =?";
            int id_sc = select_id_sc(sql3, id_s_c);
            String contenu = txt_contenu.getText();
            
            
           Messagee msg = new Messagee(1, contenu, idc, id_sc);
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
            
            String id_cl = liste_cl.getSelectionModel().getSelectedItem().toString();
            String id_s_c = liste_sc.getSelectionModel().getSelectedItem().toString();

            
            String sql2 = "select id_cl from client where Pseaudo_Cl =?";
            int idc = select_id_cl(sql2, id_cl);
            
            
            String sql3 = "select id_SousCat from sous_categorie where nom_SousCat =?";
            int id_sc = select_id_sc(sql3, id_s_c);
            
            
            String idmsg=id_message.getText();
            int id_msg = Integer.parseInt(idmsg);
            String contenu = txt_contenu.getText();
            
            
           Messagee msg = new Messagee(id_msg, contenu, idc, id_sc);
           Message_Controller msgc = new Message_Controller();
           msgc.UpdateMessage(msg);
           showMessages();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    private int select_id_cl(String sql, String id_cl) throws SQLException{
        int idc = 0;
        mc = MaConnexion.getInstance().getCnx();
            ste = mc.prepareStatement(sql);

            ste.setString(1, id_cl);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                idc = rs.getInt("id_cl");
            }
            return idc;
    }
    
    private int select_id_sc(String sql, String id_s_c) throws SQLException{
        int idsc = 0;
        mc = MaConnexion.getInstance().getCnx();
        ste = mc.prepareStatement(sql);

        ste.setString(1, id_s_c);
        ResultSet rs1 = ste.executeQuery();
        while (rs1.next()) {
            idsc = rs1.getInt("id_SousCat");
        }
        return idsc;
    }

    @FXML
    private void refreshTable(ActionEvent event) {
        Message_Controller m_c = new Message_Controller();
        ObservableList<Messagee> list = m_c.afficherMessages();
        list.clear();
        showMessages();
        disselect(event);
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
        String user = "";
        String sous_c = "";
        id_message.setText(""+msg.getId_message());
        txt_contenu.setText(""+msg.getContenu_message());
        mc = MaConnexion.getInstance().getCnx();
        try {

            ObservableList options2 = FXCollections.observableArrayList();
            
            String sql22 = "select Pseaudo_Cl from client where id_cl = ?";
            ste=mc.prepareStatement(sql22);
            ste.setInt(1,msg.getId_cl());
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                user = rs.getString("Pseaudo_Cl");
            }
            liste_cl.setValue(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        
        try {

            ObservableList options2 = FXCollections.observableArrayList();

            String sql3 = "select nom_SousCat from sous_categorie where id_SousCat =?";
            ste=mc.prepareStatement(sql3);
             ste.setInt(1,msg.getId_sous_cat());
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                sous_c = rs.getString("nom_SousCat");
            }
            liste_sc.setValue(sous_c);
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        date_message.setText(""+msg.getDate_message());
    }

    @FXML
    private void disselect(ActionEvent event) {
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

    @FXML
    private void search_messages_par_contenu(ActionEvent event) {
    }

    @FXML
    private void fill_client_combo() {
        mc = MaConnexion.getInstance().getCnx();
        try {

            ObservableList options2 = FXCollections.observableArrayList();

            String sql2 = "select Pseaudo_Cl from client order by id_cl";
            ResultSet rs = mc.createStatement().executeQuery(sql2);
            while (rs.next()) {
                options2.add(rs.getString(1));
            }
            liste_cl.setItems(options2);
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    @FXML
    private void fill_s_c_combo() {
        mc = MaConnexion.getInstance().getCnx();
        try {

            ObservableList options3 = FXCollections.observableArrayList();

            String sql2 = "select nom_sousCat from sous_categorie order by id_sousCat";
            ResultSet rs = mc.createStatement().executeQuery(sql2);
            while (rs.next()) {
                options3.add(rs.getString(1));
            }
            liste_sc.setItems(options3);
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }
    
    
    
}
