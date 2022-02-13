/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import config.MaConnexion;

/**
 *
 * @author jmokh
 */
public class ImageService {
    Connection mc;
    PreparedStatement ste;
    
    public ImageService(){
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
            System.out.println(ex.getMessage());
        }
        
    }
       public List<Image> afficherImages(){
        List<Image> Images = new ArrayList<>();
        String sql="select * from image";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Image i = new Image();
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
            System.out.println(ex.getMessage());
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

}
