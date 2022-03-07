/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI_User.gestion_jeux.gestionjeuxVidéos;

import black_ops.config.MaConnexion;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author jmokh
 */
public class AfficherInfoJeuController implements Initializable {

    @FXML
    private Label NomJeu;
    @FXML
    private ImageView imgjeu;
    @FXML
    private Label Idsscat;
    @FXML
    private Label DesJeu;
      Connection mc;
    PreparedStatement ste;
    @FXML
    private JFXTextField Download;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
 public void SetNom(String txt)
    { NomJeu.setText(txt);
    }
    @FXML
    private void afficherInforma(ActionEvent event) {
        String nomj=NomJeu.getText();
   int idimg=0;
    

         int id =0;
         int idj=0;
         String Urlimg="";
         String nomsscat="";
       
        try {
                  
            String sql="select * from jeu where Nom=?";
             mc=MaConnexion.getInstance().getCnx();
            
            ste=mc.prepareStatement(sql);
            ste.setString(1,nomj);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
               id=rs.getInt("id_souscat");
               idj=rs.getInt("Id_Jeu");
               DesJeu.setText(rs.getString("description"));
               Download.setText(rs.getString("Url"));
               Download.setStyle("-fx-text-inner-color: white;");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        try {
                  
            String sql="select nom_SousCat from sous_categorie where id_SousCat=?";
             mc=MaConnexion.getInstance().getCnx();
            ste=mc.prepareStatement(sql);
            ste.setInt(1,id);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
               nomsscat=rs.getString("nom_SousCat");
               
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        Idsscat.setText(nomsscat);
        try {
                  
            String sql="select * from image where Id_jeu=?";
             mc=MaConnexion.getInstance().getCnx();
            ste=mc.prepareStatement(sql);
            ste.setInt(1,idj);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
               Urlimg=rs.getString("Url_Image");
               idimg=rs.getInt("Id_Image");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        Integer h = idimg;
        String path1=h.toString()+".png";

        String path2 = "src/Images/ImagesJeux/" + path1;

//        System.out.println(path2);
        String Path_name = new File(path2).getAbsolutePath();
//        System.out.println(Path_name);
        ImageView i = new ImageView();
        File f = new File(Path_name);
        Image im = new Image(f.toURI().toString());
        imgjeu.setImage(im);
 
    }
    
}
