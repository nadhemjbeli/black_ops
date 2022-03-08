/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.ui.login;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Khalil
 */
public class ResetPasswordController implements Initializable {

    @FXML
    private AnchorPane rootPane;
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
    private Label cpwLab;
    @FXML
    private Label pwLab;
    @FXML
    private Button resetPW;
    @FXML
    private Button verifCode;
    @FXML
    private Hyperlink back;
    @FXML
    private PasswordField nvPw;
    @FXML
    private PasswordField cnvPW;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void codeConfirmation(ActionEvent event) {
    }

    @FXML
    private void resetPW(ActionEvent event) {
    }

    @FXML
    private void verifCode(ActionEvent event) {
    }

    @FXML
    private void back(ActionEvent event) {
    }
    
}
