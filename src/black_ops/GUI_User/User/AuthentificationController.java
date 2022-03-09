/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI_User.User;

import black_ops.Controller.Client_Controller;
import black_ops.config.MaConnexion;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
    @FXML
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
     Stage Stage1;
    private AnchorPane anchorpanelglobal;
    @FXML
    private AnchorPane anchorpanelglobal1;
    
    
    
    
    
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
        String sql="Select * from client where mail_Cl = ? and pass_Cl =?   " ;
        if (txt_email.getText().isEmpty() 
                        || !txt_email.getText().contains("@") 
                        || !txt_email.getText().contains(".") 
                       //|| txt_email.getText().indexOf("@", 0) > email.getText().indexOf(".", 0) 
                        || txt_email.getText().indexOf("#", 0) >= 0
                        || txt_email.getText().indexOf("&", 0) >= 0
                        || txt_email.getText().indexOf("(", 0) >= 0
                        //| txt_email.getText().length() - email.getText().replace("@", "").length() > 1
                        //|| txt_email.getText().length() - email.getText().replace(".", "").length() > 1
                        || txt_email.getText().indexOf("Â§", 0) >= 0
                        || txt_email.getText().indexOf("!", 0) >= 0
                        || txt_email.getText().indexOf("Ã§", 0) >= 0
                        || txt_email.getText().indexOf("Ã ", 0) >= 0
                        || txt_email.getText().indexOf("Ã©", 0) >= 0
                        || txt_email.getText().indexOf(")", 0) >= 0
                        || txt_email.getText().indexOf("{", 0) >= 0
                        || txt_email.getText().indexOf("}", 0) >= 0
                        || txt_email.getText().indexOf("|", 0) >= 0
                        || txt_email.getText().indexOf("$", 0) >= 0
                        || txt_email.getText().indexOf("*", 0) >= 0
                        || txt_email.getText().indexOf("â‚¬", 0) >= 0
                        || txt_email.getText().indexOf("`", 0) >= 0
                        || txt_email.getText().indexOf("\'", 0) >= 0
                        || txt_email.getText().indexOf("\"", 0) >= 0
                        || txt_email.getText().indexOf("%", 0) >= 0
                        || txt_email.getText().indexOf("+", 0) >= 0
                        || txt_email.getText().indexOf("=", 0) >= 0
                        || txt_email.getText().indexOf("/", 0) >= 0
                        || txt_email.getText().indexOf("\\", 0) >= 0
                        || txt_email.getText().indexOf(":", 0) >= 0
                        || txt_email.getText().indexOf(",", 0) >= 0
                        || txt_email.getText().indexOf("?", 0) >= 0
                        || txt_email.getText().indexOf(";", 0) >= 0
                        || txt_email.getText().indexOf("Â°", 0) >= 0
                        || txt_email.getText().indexOf("<", 0) >= 0
                        || txt_email.getText().indexOf(">", 0) >= 0) 
                {
                    
                     Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Erreur");
                alert.setContentText("Vous devez saisir un mail valid");
                alert.showAndWait();
                return;
                }
        if (txt_password.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("Vous devez selectionnez un password");
            alert.showAndWait();
            return;
        }
       
        
        try{
            
            String h=Hash();
            String pwd="";
            pst=cn.prepareStatement(sql);
            pst.setString(1, txt_email.getText());
            pst.setString(2, h+txt_password.getText());
            System.out.println(txt_password.getText());
            rs=pst.executeQuery();
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "E-mail and Password is Correct");
            }else
                JOptionPane.showMessageDialog(null, "Invalide E-mail Or Password");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
    }

    

    @FXML
    private void btn_signup(ActionEvent event) throws Exception {
        cn=MaConnexion.getInstance().getCnx();
         if (txt_username_up.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("Vous devez selectionnez un username");
            alert.showAndWait();
            return;
        }
        if (txt_password_up.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("Vous devez selectionnez un password");
            alert.showAndWait();
            return;
        }
       
        if (txt_email_up.getText().isEmpty() 
                        || !txt_email_up.getText().contains("@") 
                        || !txt_email_up.getText().contains(".") 
                       //|| txt_email_up.getText().indexOf("@", 0) > email.getText().indexOf(".", 0) 
                        || txt_email_up.getText().indexOf("#", 0) >= 0
                        || txt_email_up.getText().indexOf("&", 0) >= 0
                        || txt_email_up.getText().indexOf("(", 0) >= 0
                        //| txt_email_up.getText().length() - email.getText().replace("@", "").length() > 1
                        //|| txt_email_up.getText().length() - email.getText().replace(".", "").length() > 1
                        || txt_email_up.getText().indexOf("Â§", 0) >= 0
                        || txt_email_up.getText().indexOf("!", 0) >= 0
                        || txt_email_up.getText().indexOf("Ã§", 0) >= 0
                        || txt_email_up.getText().indexOf("Ã ", 0) >= 0
                        || txt_email_up.getText().indexOf("Ã©", 0) >= 0
                        || txt_email_up.getText().indexOf(")", 0) >= 0
                        || txt_email_up.getText().indexOf("{", 0) >= 0
                        || txt_email_up.getText().indexOf("}", 0) >= 0
                        || txt_email_up.getText().indexOf("|", 0) >= 0
                        || txt_email_up.getText().indexOf("$", 0) >= 0
                        || txt_email_up.getText().indexOf("*", 0) >= 0
                        || txt_email_up.getText().indexOf("â‚¬", 0) >= 0
                        || txt_email_up.getText().indexOf("`", 0) >= 0
                        || txt_email_up.getText().indexOf("\'", 0) >= 0
                        || txt_email_up.getText().indexOf("\"", 0) >= 0
                        || txt_email_up.getText().indexOf("%", 0) >= 0
                        || txt_email_up.getText().indexOf("+", 0) >= 0
                        || txt_email_up.getText().indexOf("=", 0) >= 0
                        || txt_email_up.getText().indexOf("/", 0) >= 0
                        || txt_email_up.getText().indexOf("\\", 0) >= 0
                        || txt_email_up.getText().indexOf(":", 0) >= 0
                        || txt_email_up.getText().indexOf(",", 0) >= 0
                        || txt_email_up.getText().indexOf("?", 0) >= 0
                        || txt_email_up.getText().indexOf(";", 0) >= 0
                        || txt_email_up.getText().indexOf("Â°", 0) >= 0
                        || txt_email_up.getText().indexOf("<", 0) >= 0
                        || txt_email_up.getText().indexOf(">", 0) >= 0) 
                {
                    
                     Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Erreur");
                alert.setContentText("Vous devez saisir un mail valid");
                alert.showAndWait();
                return;
                }
        
         
       
        
        
        String sql="insert into client (Pseaudo_Cl,Photo_Cl,mail_Cl,pass_Cl,DateNaissance_Cl) values(?,?,?,?,?) " ;
        try{
            String h=Hash();
            Date D=Date.valueOf(txt_date_up.getValue());
            pst=cn.prepareStatement(sql);
            pst.setString(1, txt_username_up.getText());
            pst.setString(3, txt_email_up.getText());
            pst.setString(4, h+txt_password_up.getText());
            pst.setString(2, url.getText());
            pst.setDate(5, D);
            
            pst.execute();
            
                JOptionPane.showMessageDialog(null, "Saved");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        Client_Controller c = new Client_Controller();
        System.out.println(txt_email_up.getText());
        String object = "User Email Verification";
        String corps="Registred Successfully.";
        
        c.sendMail(txt_email_up.getText(),object,corps);
        
    }


    public String Hash() throws Exception {

        String mdp_user = "";

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(mdp_user.getBytes());

        byte byteData[] = md.digest();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();

    }
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoginpaneShow();
        
    }    
   

    @FXML
    private String input_imagee(ActionEvent event) {
        String id= txt_username_up.getText();
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
                    to = Paths.get(path+"\\"+id+".png");
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
             url.setText(to.toString());
             
            
        
    }
      return to.toString();
    }

    @FXML
    private void date(ActionEvent event) {
    }
    
    private void exitX(MouseEvent event) {
        Stage1 = (Stage) anchorpanelglobal.getScene().getWindow();
        System.out.println("you succesfully close the application");
        Stage1.close();
    }
    @FXML
    private void forgot_pass(ActionEvent event) throws IOException {
        
        
    
         try {
                     Parent root = FXMLLoader.load(getClass().getResource("FXMLforgot.fxml"));
                        Stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
                                      root.setOnMousePressed(pressEvent -> {
                        root.setOnMouseDragged(dragEvent -> {
        Stage1.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
        Stage1.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
    });
});
                        Scene  scene = new Scene(root);
                        Stage1.setScene(scene);
                        Stage1.show();

                } catch (IOException ex) {
                     System.out.println(ex.getMessage());
                }
         
    }


    

   
    

    
}
