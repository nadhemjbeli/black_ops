/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI_User.gestion_jeux.gestionchamps;

import black_ops.Entity.Champion;
import black_ops.config.MaConnexion;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class AfficherInfoController implements Initializable {

    @FXML
    private Label Nomchamp;
    @FXML
    private Label roleChamp;
    @FXML
    private Label DiffChamp;
    @FXML
    private Label DescChamp;
Connection mc;
    PreparedStatement ste;
    @FXML
    private Label Jeu;
    @FXML
    private ImageView imgchamp;
   
    /**
     * Initializes the controller class.
     */
        
    public void SetNom(String txt)
    { Nomchamp.setText(txt);
    }
    
    public void afficherInforma(){
        String nom=Nomchamp.getText();
//        System.out.println(nom);
     String jeu="";
        try {
                  
            String sql="select * from champion where Nom_Champ=?";
             mc=MaConnexion.getInstance().getCnx();
            
            ste=mc.prepareStatement(sql);
            ste.setString(1,nom);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Champion c = new Champion();
                DescChamp.setText((rs.getString("description_Champ")));
                roleChamp.setText(rs.getString("Role_Champ"));
                DiffChamp.setText(rs.getString("Difficulte_Champ"));
                 jeu=rs.getString("Id_jeu");
                String img4=rs.getString("Image_Champ");
                String path1= img4;
//              System.out.println(path1);
       String path2="src/Images/ImagesChampions/"+path1;
      

       
        String Path_name = new File(path2).getAbsolutePath();
//        System.out.println(Path_name);
        ImageView i = new ImageView();
        File f =  new File (Path_name);
        Image im =new Image(f.toURI().toString());
       imgchamp.setImage(im);
//                c.setId_jeu(rs.getInt("Id_jeu"));
                
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         try {
             
               String sql2="select Nom from Jeu where Id_Jeu =?";
               
             mc=MaConnexion.getInstance().getCnx();
             ste=mc.prepareStatement(sql2);
             
              
              ste.setString(1,jeu);
              ResultSet rs=ste.executeQuery();
               while(rs.next()){
              Jeu.setText(rs.getString("Nom"));
            
            
        } }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         
    
}
@Override
    public void initialize(URL url, ResourceBundle rb) {
       afficherInforma();
       }

}
