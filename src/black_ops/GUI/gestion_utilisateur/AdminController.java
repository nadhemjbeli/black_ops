/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI.gestion_utilisateur;

import black_ops.Controller.Admin_Controller;
import black_ops.Controller.Message_Controller;
import black_ops.Entity.Messagee;
import black_ops.Entity.Super;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Khalil
 */
public class AdminController implements Initializable {

    @FXML
    private TableView<Super> table_messages;
    @FXML
    private TableColumn<Super, Integer> col_id;
    @FXML
    private TableColumn<Super, String> col_mail;
    @FXML
    private TableColumn<Super, Integer> col_grade;
    @FXML
    private TextField txt_id;
    @FXML
    private TextField txt_email;
    @FXML
    private TextField txt_grade;
    @FXML
    private TextField txt_password;
    @FXML
    private Button btn_add;
    @FXML
    private Button btn_edit;
    @FXML
    private Button btn_delete;
    @FXML
    private Button btn_diselect;
    @FXML
    private Button btn_refresh;
    @FXML
    private TextField txt_search;
    @FXML
    private Button btn_search;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showAdmin();
    }    

    @FXML
    private void add_message(ActionEvent event) {
        
        try{
           String id_admin  = txt_id.getText();
            String mail_admin  = txt_email.getText();
            String grade = txt_grade.getText();
            String password_admin  = txt_password.getText();
            
            
           int i_a = Integer.parseInt(id_admin);
           int g = Integer.parseInt(grade);
//           Timestamp ts = Timestamp.valueOf(text);
           Super admin = new Super(1, mail_admin,password_admin,g);
           Admin_Controller adminc = new Admin_Controller();
           adminc.ajouterAdmin(admin);
           showAdmin(); 
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void edit_message(ActionEvent event) {
         try{
            String id_admin=txt_id.getText();
            int id_msg = Integer.parseInt(id_admin);
            String mail_admin = txt_email.getText();
            String grade = txt_grade.getText();
            String password_admin = txt_password.getText();
            
           int i_a = Integer.parseInt(id_admin);
           int g = Integer.parseInt(grade);
//           Timestamp ts = Timestamp.valueOf(text);
           Super admin = new Super(id_msg, mail_admin,password_admin,g);
           Admin_Controller adminc = new Admin_Controller();
           adminc.UpdateAdmin(admin);
           showAdmin();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    @FXML
    private void handleMouseAction(MouseEvent event) {
        Super msg = table_messages.getSelectionModel().getSelectedItem();
        txt_id.setText(""+msg.getId_admin());
        txt_email.setText(""+msg.getMail_admin());
        txt_grade.setText(""+msg.getGrade());
        txt_password.setText(""+msg.getPassword_admin());

    }
    @FXML
    private void delete_message(ActionEvent event) {
        try{
            
            String idadmin=txt_id.getText();
            int id_admn = Integer.parseInt(idadmin);
           Admin_Controller adminc = new Admin_Controller();
           Super a= new Super(id_admn,"","",0);
           adminc.DeletePersonne(a);
           showAdmin();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void disselect(ActionEvent event) {
        txt_id.setText("");
        txt_email.setText("");
        txt_grade.setText("");
        txt_password.setText("");
    }

    @FXML
    private void refreshTable(ActionEvent event) {
         Admin_Controller m_c = new Admin_Controller();
        ObservableList<Super> list = m_c.afficherPersonne();
        list.clear();
        showAdmin();
//        disselect(event);
    }

    @FXML
    private void seach_messages_par_contenu(ActionEvent event) {
    }

    private void showAdmin() {
        Admin_Controller acl = new Admin_Controller();
       
        ObservableList<Super> list = acl.afficherPersonne();
     
        
        col_id.setCellValueFactory(new PropertyValueFactory<Super, Integer>("id_admin"));
        col_mail.setCellValueFactory(new PropertyValueFactory<>("mail_admin"));
        col_grade.setCellValueFactory(new PropertyValueFactory<>("grade"));
        
        table_messages.setItems(list);
    }
    
}
