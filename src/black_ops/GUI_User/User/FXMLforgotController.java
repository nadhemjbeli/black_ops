/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI_User.User;


import black_ops.Controller.Client_Controller;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Khalil
 */
public class FXMLforgotController implements Initializable {
String emailPW = null;
    private TextField txt_email;
    private Button Reset;
    @FXML
    private Button resetPW;
    @FXML
    private Label emailLab;
    @FXML
    private TextField email;
    @FXML
    private Button codeConfirmation;
    @FXML
    private TextField code;
    @FXML
    private Label codeLab;
    @FXML
    private Button verifCode;
    @FXML
    private Hyperlink back;
    @FXML
    private PasswordField nvPw;
    @FXML
    private PasswordField cnvPW;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label cpwLab;
    @FXML
    private Label pwLab;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        visibility(true,false,false);
        

    }

    public void visibility(boolean phase1, boolean phase2, boolean phase3) {
        emailLab.setVisible(phase1);
        email.setVisible(phase1);
        codeConfirmation.setVisible(phase1); // fin phase 1
        codeLab.setVisible(phase2);
        code.setVisible(phase2);
        verifCode.setVisible(phase2); // fin phase 2
        pwLab.setVisible(phase3);
        nvPw.setVisible(phase3);
        cpwLab.setVisible(phase3);
        cnvPW.setVisible(phase3);
        resetPW.setVisible(phase3); // fin phase 3
    }

    Random rand = new Random();
    int codeVerification = rand.nextInt((1000) + (9999));
    String codeVerif = String.valueOf(codeVerification);

    
    
    private void BackToLogin() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("authentification.fxml"));
        Parent root = loader.load();
        Stage window = (Stage) resetPW.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    private void codeConfirmation(ActionEvent event) {

        String mail = email.getText();
        Client_Controller sU = new Client_Controller();
        
        
        if (email.getText().isEmpty() 
                        || !email.getText().contains("@") 
                        || !email.getText().contains(".") 
                       //|| email.getText().indexOf("@", 0) > email.getText().indexOf(".", 0) 
                        || email.getText().indexOf("#", 0) >= 0
                        || email.getText().indexOf("&", 0) >= 0
                        || email.getText().indexOf("(", 0) >= 0
                        //| email.getText().length() - email.getText().replace("@", "").length() > 1
                        //|| email.getText().length() - email.getText().replace(".", "").length() > 1
                        || email.getText().indexOf("Â§", 0) >= 0
                        || email.getText().indexOf("!", 0) >= 0
                        || email.getText().indexOf("Ã§", 0) >= 0
                        || email.getText().indexOf("Ã ", 0) >= 0
                        || email.getText().indexOf("Ã©", 0) >= 0
                        || email.getText().indexOf(")", 0) >= 0
                        || email.getText().indexOf("{", 0) >= 0
                        || email.getText().indexOf("}", 0) >= 0
                        || email.getText().indexOf("|", 0) >= 0
                        || email.getText().indexOf("$", 0) >= 0
                        || email.getText().indexOf("*", 0) >= 0
                        || email.getText().indexOf("â‚¬", 0) >= 0
                        || email.getText().indexOf("`", 0) >= 0
                        || email.getText().indexOf("\'", 0) >= 0
                        || email.getText().indexOf("\"", 0) >= 0
                        || email.getText().indexOf("%", 0) >= 0
                        || email.getText().indexOf("+", 0) >= 0
                        || email.getText().indexOf("=", 0) >= 0
                        || email.getText().indexOf("/", 0) >= 0
                        || email.getText().indexOf("\\", 0) >= 0
                        || email.getText().indexOf(":", 0) >= 0
                        || email.getText().indexOf(",", 0) >= 0
                        || email.getText().indexOf("?", 0) >= 0
                        || email.getText().indexOf(";", 0) >= 0
                        || email.getText().indexOf("Â°", 0) >= 0
                        || email.getText().indexOf("<", 0) >= 0
                        || email.getText().indexOf(">", 0) >= 0) 
                {
                    
                     Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Erreur");
                alert.setContentText("Vous devez saisir un mail valid");
                alert.showAndWait();
                return;
                }
        
        if ( !sU.verifierEmailBd(mail)) {
            sU.alert_Box("Verifier adresse", "Veillez saisir une adresse mail valide");
        } else {
            emailPW = mail;
            sU.sendMail(mail, "Compte créer avec succès", "voici votre code de " + codeVerification + "  ");
            sU.information_Box("Succes", "verifier votre boite mail");
            visibility(false,true,false);
        }
    }

    @FXML
    private void verifCode(ActionEvent event) {
        Client_Controller sU = new Client_Controller();
        String codeV = code.getText();
        if (!codeV.equals(this.codeVerif)) {
            sU.alert_Box("Verifier ", "Veillez verifier le code ");
        } else {
            sU.information_Box("VCode verifier", "Vous pouvez desoermer changer votre mot de passe");
        visibility(false,false,true);
        }
        
    }

    @FXML
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
    @FXML
    private void resetPW(ActionEvent event) throws IOException, Exception {
    String h=Hash ();
      
        Client_Controller sU = new Client_Controller();
        String password = nvPw.getText();
        String cPassword = cnvPW.getText();

        if   (!password.equals(cPassword)) {
            sU.alert_Box("Verifier mot de passe", "Veillez verifier votre mot de passe ");
        } else {
            sU.modifierPassword(emailPW, (h+password));
            sU.information_Box("mot de passe", "mot de passe changer avec succes");
            BackToLogin();

        }

    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        BackToLogin();
    }
}
