/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI.gestion_jeux.gestionImages;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author jmokh
 */
public class AfficherImageController implements Initializable {

    @FXML
    private TextField txtIdJeu;
    @FXML
    private ImageView imgviewjeu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void function (Image img, String text)
        { imgviewjeu.setImage(img);
        txtIdJeu.setText(text);
        }
}
