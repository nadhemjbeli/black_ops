/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI_User.Competition.Defi;

import black_ops.Entity.Defi;
import black_ops.config.MaConnexion;
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
 * @author medaz
 */
public class AfficherInfoController implements Initializable {

    @FXML
    private Label Nom;
    @FXML
    private ImageView img;
    @FXML
    private Label date;
    @FXML
    private Label prix;
    @FXML
    private Label desc;
    @FXML
    private Label rec;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }    
   public void SetNom(String txt)
    { 
        Nom.setText(txt);
    }
    @FXML
    private void afficherInforma(ActionEvent event) {
        try{
        String sql = "Select * from defi  where nom_Defi = ? " ;
        Connection mc=MaConnexion.getInstance().getCnx();
            Nom.getText();
             PreparedStatement ste=mc.prepareStatement(sql);
            ste.setString(1,Nom.getText());
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Defi c = new Defi();
                desc.setText(rs.getString("Description_Defi"));
                date.setText(""+rs.getDate("date_Defi"));
                prix.setText(""+rs.getInt("prix_Defi"));
                rec.setText(rs.getString("Recompense_Defi"));

//              System.out.println(path1);
       String path2= rs.getString("img_Defi");
      

       
        String Path_name = new File(path2).getAbsolutePath();
//        System.out.println(Path_name);
        ImageView i = new ImageView();
        File f =  new File (Path_name);
        Image im =new Image(f.toURI().toString());
       img.setImage(im);
//                c.setId_jeu(rs.getInt("Id_jeu"));
                
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            
            }
   

}

