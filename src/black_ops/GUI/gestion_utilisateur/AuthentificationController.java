/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI.gestion_utilisateur;


import black_ops.GUI.gestion_communaute.ChatController;
import black_ops.GUI.gestion_communaute.MessageController;
import black_ops.GUI.gestion_communaute.user.Video_uploade_userController;
import black_ops.GUI.gestion_communaute.user.video_list_user.Video_list_userController;
import black_ops.config.MaConnexion;
import java.io.File;

import java.io.IOException;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * FXML Controller class
 *
 * @author Khalil
 */
public class AuthentificationController implements Initializable {

    @FXML
    private AnchorPane pane_login;
    private TextField txt_email;
    @FXML
    private Button id_btn_login;
    @FXML
    private PasswordField txt_password;
    @FXML
    private AnchorPane pane_signup;
    @FXML
    private TextField txt_username_up;
    @FXML
    private TextField txt_email_up;
    @FXML
    private DatePicker txt_date_up;
    @FXML
    private PasswordField txt_password_up;
    @FXML
    private Button btn_image_up;
    @FXML
    private ImageView image_up;
    

    Connection cn = null; 
    ResultSet rs= null;
    PreparedStatement pst = null;
    @FXML
    private Button id_pane_signup;
    @FXML
    private TextField url;
    @FXML
    private TextField txt_name;
    
    
    
    
    
    @FXML
    public void LoginpaneShow(){
        pane_login.setVisible(true);
        pane_signup.setVisible(false);
    }
    @FXML
    public void SignuppaneShow(){
        pane_login.setVisible(false);
        pane_signup.setVisible(true);
    }
    
    
    
    @FXML
    private void btn_login(ActionEvent event) throws Exception {
        cn=MaConnexion.getInstance().getCnx();
        String sql="Select * from client where Pseaudo_Cl = ? and pass_Cl =?   " ;
        
        try{
            pst=cn.prepareStatement(sql);
            pst.setString(1, txt_name.getText());
            pst.setString(2, txt_password.getText());
            rs=pst.executeQuery();
            if(rs.next()){
                if(txt_name.getText().equals("admin")){
                    JOptionPane.showMessageDialog(null, "welcome admin");
                }
                else{
                    ChatController.client_name = txt_name.getText();
                    Video_uploade_userController.client_name = txt_name.getText();
                    Video_list_userController.client_name = txt_name.getText();
                    JOptionPane.showMessageDialog(null, "bienvenu "+txt_name.getText()+" !");
                    switchChat(event);
                }
                
            }else
                JOptionPane.showMessageDialog(null, "Invalide E-mail Or Password");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
    }

    

    @FXML
    private void btn_signup(ActionEvent event) throws Exception {
        cn=MaConnexion.getInstance().getCnx();
        String sql="insert into client (Pseaudo_Cl,Photo_Cl,mail_Cl,pass_Cl,DateNaissance_Cl) values(?,?,?,?,?) " ;
        try{
            Date D=Date.valueOf(txt_date_up.getValue());
            pst=cn.prepareStatement(sql);
            pst.setString(1, txt_username_up.getText());
            pst.setString(3, txt_email_up.getText());
            pst.setString(4, txt_password_up.getText());
            pst.setString(2, url.getText());
            pst.setDate(5, D);
            
            pst.execute();
            
                JOptionPane.showMessageDialog(null, "Saved");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }


    
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }    
   

    @FXML
    private String input_imagee(ActionEvent event) {
        String name= txt_username_up.getText();
        Path to = null;
        String  m = null;
        String path = "src/Images/Imageclient";
        JFileChooser chooser = new JFileChooser();
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & PNG Images", "jpg","jpeg","PNG");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
           m = chooser.getSelectedFile().getAbsolutePath();
          System.out.println("You chose to open this file: " +m );
            
            if(chooser.getSelectedFile() != null){
                
               try {
                   Path from = Paths.get(chooser.getSelectedFile().toURI());
                    to = Paths.get(path+"\\"+name+".png");
                       System.out.println("aa");
                   CopyOption[] options = new CopyOption[]{
                       StandardCopyOption.REPLACE_EXISTING,
                       StandardCopyOption.COPY_ATTRIBUTES
                   };
                   Files.copy(from, to, options);
                   System.out.println("added");
                    System.out.println(to);
                    ImageView i = new ImageView();
             File f = new File(to.toString());
             Image im  = new Image(f.toURI().toString());
             image_up.setImage(im);
                   
//                saveSystem(selectedFile, )
               } catch (IOException ex) {
                   System.out.println();
               }
            }
             url.setText(name+".png");
             
            
        
    }
      return name+".png";
    }

    @FXML
    private void date(ActionEvent event) {
    }
    
    private void switchChat(ActionEvent event) throws IOException{
        Stage stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/black_ops/GUI/gestion_communaute/chat.fxml"));
            Parent root = loader.load();
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

    

    
}
