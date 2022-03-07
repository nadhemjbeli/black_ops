/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.Controller;

import black_ops.Entity.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import black_ops.config.MaConnexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 *
 * @author jmokh
 */
public class ImageController {
    Connection mc;
    PreparedStatement ste;
    
    public ImageController(){
       mc=MaConnexion.getInstance().getCnx();
    }
    
    public void ajouterImage(Image i){
        String sql ="insert into image(Url_Image,Id_jeu) Values(?,?)";
        try {
            ste=mc.prepareStatement(sql);
            ste.setString(1,i.getUrl_Image());
            ste.setInt(2, i.getId_jeu());
            ste.executeUpdate();
            System.out.println("Image Ajoutée");
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
        
    }
      public ObservableList<Image> afficherImages(){
        ObservableList<Image> Images = FXCollections.observableArrayList();
        String sql="select * from image";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Image i = new Image();
                i.setId_Image(rs.getInt("Id_Image"));
                i.setUrl_Image(rs.getString("Url_Image"));
                i.setId_jeu(rs.getInt("Id_jeu"));
                Images.add(i);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return Images;
    
    
    
}
        public void updateImage(Image i){
        String sql ="update image set Url_Image = ? , Id_Jeu = ?  where Id_Image = ?";
        try {
            ste=mc.prepareStatement(sql);
            ste.setString(1, i.getUrl_Image());
            ste.setInt(2, i.getId_jeu());
            ste.setInt(3, i.getId_Image());
            
            ste.executeUpdate();
            System.out.println("Image modifiée");
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
        
    }
        public void deleteImage(Image i){
        String sql = "delete from image where Id_Image= ?";
        try {
            ste=mc.prepareStatement(sql);
            ste.setInt(1, i.getId_Image());            
            ste.executeUpdate();      
            ste.close();
        } 
         catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

}
         public ObservableList<Image> RechercherImage (String url)
        { ObservableList<Image> images = FXCollections.observableArrayList();
        String sql="select * from image where Url_Image='"+url+"'";
         try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Image i = new Image();
                i.setId_Image(rs.getInt("Id_Image"));
                i.setUrl_Image(rs.getString("Url_Image"));
                i.setId_jeu(rs.getInt("Id_jeu"));
                images.add(i);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return images;

}}
