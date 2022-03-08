/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI_User.User;

import black_ops.Controller.Client_Controller;
import black_ops.config.MaConnexion;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Khalil
 */
public class ContactController implements Initializable {

    @FXML
    private TextField txt_np;
    @FXML
    private TextField txt_mail;
    @FXML
    private TextField txt_msg;
    Connection cn = null; 
    ResultSet rs= null;
    PreparedStatement pst = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btn_ajout(ActionEvent event) {
        cn=MaConnexion.getInstance().getCnx();
        
         if (txt_np.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("Vous devez selectionnez un nom et prénom");
            alert.showAndWait();
            return;
        }
        if (txt_msg.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("Vous devez selectionnez un message");
            alert.showAndWait();
            return;
        }
       
        if (txt_mail.getText().isEmpty() 
                        || !txt_mail.getText().contains("@") 
                        || !txt_mail.getText().contains(".") 
                       //|| txt_mail.getText().indexOf("@", 0) > email.getText().indexOf(".", 0) 
                        || txt_mail.getText().indexOf("#", 0) >= 0
                        || txt_mail.getText().indexOf("&", 0) >= 0
                        || txt_mail.getText().indexOf("(", 0) >= 0
                        //| txt_mail.getText().length() - email.getText().replace("@", "").length() > 1
                        //|| txt_mail.getText().length() - email.getText().replace(".", "").length() > 1
                        || txt_mail.getText().indexOf("Â§", 0) >= 0
                        || txt_mail.getText().indexOf("!", 0) >= 0
                        || txt_mail.getText().indexOf("Ã§", 0) >= 0
                        || txt_mail.getText().indexOf("Ã ", 0) >= 0
                        || txt_mail.getText().indexOf("Ã©", 0) >= 0
                        || txt_mail.getText().indexOf(")", 0) >= 0
                        || txt_mail.getText().indexOf("{", 0) >= 0
                        || txt_mail.getText().indexOf("}", 0) >= 0
                        || txt_mail.getText().indexOf("|", 0) >= 0
                        || txt_mail.getText().indexOf("$", 0) >= 0
                        || txt_mail.getText().indexOf("*", 0) >= 0
                        || txt_mail.getText().indexOf("â‚¬", 0) >= 0
                        || txt_mail.getText().indexOf("`", 0) >= 0
                        || txt_mail.getText().indexOf("\'", 0) >= 0
                        || txt_mail.getText().indexOf("\"", 0) >= 0
                        || txt_mail.getText().indexOf("%", 0) >= 0
                        || txt_mail.getText().indexOf("+", 0) >= 0
                        || txt_mail.getText().indexOf("=", 0) >= 0
                        || txt_mail.getText().indexOf("/", 0) >= 0
                        || txt_mail.getText().indexOf("\\", 0) >= 0
                        || txt_mail.getText().indexOf(":", 0) >= 0
                        || txt_mail.getText().indexOf(",", 0) >= 0
                        || txt_mail.getText().indexOf("?", 0) >= 0
                        || txt_mail.getText().indexOf(";", 0) >= 0
                        || txt_mail.getText().indexOf("Â°", 0) >= 0
                        || txt_mail.getText().indexOf("<", 0) >= 0
                        || txt_mail.getText().indexOf(">", 0) >= 0) 
                {
                    
                     Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Erreur");
                alert.setContentText("Vous devez saisir un mail valid");
                alert.showAndWait();
                return;
                }
        
        String sql="insert into contact (np_contact,mail_contact,message) values(?,?,?) " ;
        try{
            
            
            pst=cn.prepareStatement(sql);
            pst.setString(1, txt_np.getText());
            pst.setString(2, txt_mail.getText());
            pst.setString(3, txt_msg.getText());
            
           
            
            pst.execute();
            
                JOptionPane.showMessageDialog(null, "Saved");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        Client_Controller c = new Client_Controller();
        System.out.println(txt_mail.getText());
        String object = "User Email Verification";
        String corps="Votre Message est reçu.";
        
        c.sendMail(txt_mail.getText(),object,corps);
        
    }
    }
    

