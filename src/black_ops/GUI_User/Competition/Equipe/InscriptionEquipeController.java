/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI_User.Competition.Equipe;

import black_ops.Controller.EquipeController;
import black_ops.Entity.Equipe;
import black_ops.config.MaConnexion;
import java.io.IOException;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * FXML Controller class
 *
 * @author medaz
 */
public class InscriptionEquipeController implements Initializable {

    @FXML
    private TextField search;
    @FXML
    private VBox add;
    @FXML
    private TextField inp_Nom;
    @FXML
    private TextField inp_url;
    @FXML
    private Button imp;
    @FXML
    private TextField inp_nbr;
    @FXML
    private Button btn_insert;
    @FXML
    private VBox aff;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btn_insert.setDisable(false);
        
    }    

    @FXML
    private void EnterRecherche(ActionEvent event) {
    }

    @FXML
    private String importImg(ActionEvent event) {
        String id = inp_Nom.getText();
        Path to = null;
        String m = null;
        String path = "src/Image/Logo_Equipe";
        JFileChooser chooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & PNG Images", "jpg", "jpeg", "PNG");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            m = chooser.getSelectedFile().getAbsolutePath();
//            System.out.println("You chose to open this file: " +m
//                    );

            if (chooser.getSelectedFile() != null) {

                try {
                    Path from = Paths.get(chooser.getSelectedFile().toURI());
                    to = Paths.get(path + "\\" + id + ".png");
                    CopyOption[] options = new CopyOption[]{
                        StandardCopyOption.REPLACE_EXISTING,
                        StandardCopyOption.COPY_ATTRIBUTES
                    };
                    Files.copy(from, to, options);
                    System.out.println("added");
//                saveSystem(selectedFile, )
                    System.out.println(to);

                } catch (IOException ex) {
                    System.out.println();
                }
            }
            inp_url.setText(to.toString());

        }
        return to.toString();
    }

    @FXML
    private void Ajouter_Equipe(ActionEvent event) {
         java.util.Date date = Calendar.getInstance().getTime();
        java.sql.Date dateJ = new java.sql.Date(date.getTime());
        String Nom = inp_Nom.getText();
        String url = inp_url.getText();
        String nbr_joueur = inp_nbr.getText();

        int nbr = Integer.parseInt(nbr_joueur);
        Equipe eq = new Equipe(3, Nom, url, dateJ, nbr);
        EquipeController Ec = new EquipeController();
        Ec.ajouterEquipe(eq);
        Show_Equipe();
    }
    public void Show_Equipe(){
         EquipeController Ec = new EquipeController();
         Equipe eq = Ec.Select();
         inp_Nom.setText(eq.getNom_Equipe());
         inp_url.setText(eq.getLogo_Equipe());
         inp_nbr.setText(""+eq.getNbr_joueur_Equipe());  
         btn_insert.setDisable(true);
} 
            
}
