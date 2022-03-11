/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI.gestion_utilisateur;

import black_ops.Controller.Admin_Controller;
import black_ops.Entity.Super;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
    @FXML
    private AnchorPane nav;
    @FXML
    private JFXComboBox gc;
    @FXML
    private JFXComboBox gcp;
    @FXML
    private Button gu;
    @FXML
    private JFXComboBox gCom;
    @FXML
    private JFXComboBox gcat;
    @FXML
    private JFXComboBox gS;
    @FXML
    private JFXComboBox gJ;
    @FXML
    private AnchorPane main;
    @FXML
    private Button hide;
    @FXML
    private Button show;
    private Stage stage;
 private Scene scene;
 private Parent root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showAdmin();
        main.setVisible(true);
        ComboBoxListInti();
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

    @FXML
    private void show_nav_bar(ActionEvent event) {
     nav.setVisible(true); 
     hide.setVisible(true);
     show.setVisible(false);
     main.setVisible(false);
     
    }

  

    @FXML
    private void hide_nav_bar(ActionEvent event) {
        nav.setVisible(false); 
     hide.setVisible(false);
     show.setVisible(true);
     main.setVisible(true);
    }

    @FXML
    private void showGC(ActionEvent event) {
        String s = gc.getSelectionModel().getSelectedItem().toString();
        
    }

    @FXML
    private void showComp(ActionEvent event) throws Exception {
        
        String s = gcp.getSelectionModel().getSelectedItem().toString();
//       
        switch(s){
            case "Gestion Defi" :
                 SwitchScene(stage,"Gestion_Competition/Gestion_Defis/GestionDefi",event);
                 break ;
            case "Gestion Matches" :
                    SwitchScene(stage,"Gestion_Competition/Gestion_Details_Defis/GestionDetailsDefis",event);
           break ; 
            case "Gestion Joueurs" :
                  SwitchScene(stage,"Gestion_Competition/Gestion_Joueur/GestionJoueur",event);
           break ;
            case "Gestion Equipe":
                SwitchScene(stage,"Gestion_Competition/Gestion_Equipe/GestionEquipe",event);
        }
   
    }

    @FXML
    private void showU(ActionEvent event) throws IOException, Exception {
        root = FXMLLoader.load(getClass().getResource("Admin.fxml"));
          
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
        }  
        
    

    @FXML
    private void showCommande(ActionEvent event) throws Exception {
        String s = gCom.getSelectionModel().getSelectedItem().toString();
        switch(s){
            case "Gestion Defi" :
                 SwitchScene(stage,"Gestion_Competition/Gestion_Defis/GestionDefi",event);
                 break ;
            case "Gestion Matches" :
                    SwitchScene(stage,"Gestion_Competition/Gestion_Details_Defis/GestionDetailsDefis",event);
           break ; 
            case "Gestion Joueurs" :
                  SwitchScene(stage,"Gestion_Competition/Gestion_Joueur/GestionJoueur",event);
           break ;
            case "Gestion Equipe":
                SwitchScene(stage,"Gestion_Competition/Gestion_Equipe/GestionEquipe",event);
        }
        
    }

    @FXML
    private void showCat(ActionEvent event) throws Exception {
        String s = gcat.getSelectionModel().getSelectedItem().toString();
        switch(s){
            case "Gestion Defi" :
                 SwitchScene(stage,"Gestion_Competition/Gestion_Defis/GestionDefi",event);
                 break ;
            case "Gestion Matches" :
                    SwitchScene(stage,"Gestion_Competition/Gestion_Details_Defis/GestionDetailsDefis",event);
           break ; 
            case "Gestion Joueurs" :
                  SwitchScene(stage,"Gestion_Competition/Gestion_Joueur/GestionJoueur",event);
           break ;
            case "Gestion Equipe":
                SwitchScene(stage,"Gestion_Competition/Gestion_Equipe/GestionEquipe",event);
        }
        
    }

    @FXML
    private void showS(ActionEvent event) throws Exception {
        String s = gS.getSelectionModel().getSelectedItem().toString();
        switch(s){
            case "Gestion Defi" :
                 SwitchScene(stage,"Gestion_Competition/Gestion_Defis/GestionDefi",event);
                 break ;
            case "Gestion Matches" :
                    SwitchScene(stage,"Gestion_Competition/Gestion_Details_Defis/GestionDetailsDefis",event);
           break ; 
            case "Gestion Joueurs" :
                  SwitchScene(stage,"Gestion_Competition/Gestion_Joueur/GestionJoueur",event);
           break ;
            case "Gestion Equipe":
                SwitchScene(stage,"Gestion_Competition/Gestion_Equipe/GestionEquipe",event);
        }
    }

    @FXML
    private void showJ(ActionEvent event) throws Exception {
        String s = gJ.getSelectionModel().getSelectedItem().toString();
        switch(s){
            case "Gestion Defi" :
                 SwitchScene(stage,"Gestion_Competition/Gestion_Defis/GestionDefi",event);
                 break ;
            case "Gestion Matches" :
                    SwitchScene(stage,"Gestion_Competition/Gestion_Details_Defis/GestionDetailsDefis",event);
           break ; 
            case "Gestion Joueurs" :
                  SwitchScene(stage,"Gestion_Competition/Gestion_Joueur/GestionJoueur",event);
           break ;
            case "Gestion Equipe":
                SwitchScene(stage,"Gestion_Competition/Gestion_Equipe/GestionEquipe",event);
                break ;
        }
    }
  
    public void SwitchScene(Stage stage ,String nom ,ActionEvent event ) throws Exception {
        
        String path = "/black_ops/GUI/" ;
        
    FXMLLoader loader = new FXMLLoader(getClass().getResource(path+nom+".fxml"));
            Parent root = loader.load();
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
    public void ComboBoxListInti(){
       nav.setVisible(false);  
       ObservableList<String> GestionCommunaute = FXCollections.observableArrayList(" Gestion Message" ,"Gestion Video");
       gc.setItems(GestionCommunaute);
       ObservableList<String> GestionCompetition = FXCollections.observableArrayList("Gestion Defi" ,"Gestion Matches" ,"Gestion Joueurs","Gestion Equipe");
       gcp.setItems(GestionCompetition);
       ObservableList<String> GestionCommande = FXCollections.observableArrayList("Gestion Commande" ,"Gestion  Ligne Commande" );
       gCom.setItems(GestionCommande);
       ObservableList<String> GestionCategorie = FXCollections.observableArrayList("Gestion Categorie" ,"Gestion  SousCategorie" );
       gcat.setItems(GestionCategorie);
        ObservableList<String> GestionStream = FXCollections.observableArrayList("Stream Replay " ,"Stream Info " );
       gS.setItems(GestionStream);
        ObservableList<String> GestionJeu = FXCollections.observableArrayList("Gestion Jeu" ,"Gestion  Image", "Gestion  Skin" ,"Gestion  Champion"  );
       gJ.setItems(GestionJeu);
    }

    
}
