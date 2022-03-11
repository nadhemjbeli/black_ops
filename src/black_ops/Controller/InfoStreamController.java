/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.Controller;

import black_ops.config.MaConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import black_ops.Entity.Info_Stream;
import java.io.File;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author aZiz
 */
public class InfoStreamController {
    Connection cnx;
       PreparedStatement ste;
       
       
       
       
       public InfoStreamController(){
           cnx=MaConnexion.getInstance().getCnx();
           
       }
       
         public void ajouterInfoStream(Info_Stream is){
        String sql ="INSERT INTO stream_info(nom_Stream, image_Stream, description_Stream, id_souscat) VALUES (?,?,?,?)";
        try {
            ste=cnx.prepareStatement(sql);
            ste.setString(1, is.getNom());
            ste.setString(2, is.getImage());
            ste.setString(3, is.getDescription());
            ste.setInt(4, is.getId_souscat());

            ste.executeUpdate();
            System.out.println("Information Stream Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
         
         // public void updateInfoStream(Info_Stream is){
                   public void updateInfoStream(Info_Stream is, Integer id){

        String sql ="UPDATE stream_info SET nom_Stream=?,image_Stream=?,description_Stream=?,id_souscat=? where id_Stream=?";
        try {
            ste=cnx.prepareStatement(sql);
            ste.setString(1, is.getNom());
            ste.setString(2, is.getImage());
            ste.setString(3, is.getDescription());
            ste.setInt(4, is.getId_souscat());
                        ste.setInt(5, id);


            ste.executeUpdate();
            System.out.println("Information Stream is Update....");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
          
          //public void supprimerInfoStream(Info_Stream is){
                    public void supprimerInfoStream(Integer id){

        String sql ="DELETE from stream_info where id_Stream=?";
        try {
            ste=cnx.prepareStatement(sql);
            ste.setInt(1, id);
        


           int row = ste.executeUpdate();
           if (row == 1 ){
            System.out.println("Information Stream est supprimé....");
           }else {
                           System.out.println("Information Stream n'existe pas....");

           }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
          
          
    public List<Info_Stream> afficherInfoStream(){
        ObservableList<Info_Stream> infoS = FXCollections.observableArrayList();
        String sql="select * from stream_info";
    
               
                       
        try {
            ste=cnx.prepareStatement(sql);
          
            ResultSet rs=ste.executeQuery();
            
            while(rs.next()){
                Info_Stream is = new Info_Stream();
                is.setId(rs.getInt(1));
                is.setNom(rs.getString(2));
      is.setImage(rs.getString(3));
          File path = new File("src/black_ops/MediaStream/"+rs.getString(3));
          //is.setImage(path);
               //System.out.println(path);
                Image image = new Image(path.toURI().toString(),150,170,true,true);
               // System.out.println(image);
                is.setImageV2(image);
                ImageView Image = new ImageView(image);
                is.setImageV(Image);
                 is.setDescription(rs.getString(4));
           // ImageView nothing = new ImageView(is.getImage());
                //System.out.println(nothing);
               // System.out.println(Image);
         
              int x = (rs.getInt(5));
                String sql2 ="Select nom_SousCat from sous_categorie where id_SousCat ="+x;
               // System.out.println(sql2);
                 ste=cnx.prepareStatement(sql2);
             ResultSet rs2 =ste.executeQuery();
             while (rs2.next()){
             is.setNomsouscat(rs2.getString(1));
               
                infoS.add(is);
             }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return infoS;
    }
    
    public List<Info_Stream> afficherInfoStreamByid(int id){
        ObservableList<Info_Stream> infoS = FXCollections.observableArrayList();
        String sql="select * from stream_info where id_Stream="+id;
    
               
                       
        try {
            ste=cnx.prepareStatement(sql);
          
            ResultSet rs=ste.executeQuery();
            
            while(rs.next()){
                Info_Stream is = new Info_Stream();
                is.setId(rs.getInt(1));
                is.setNom(rs.getString(2));
      is.setImage(rs.getString(3));
          File path = new File("src/black_ops/MediaStream/"+rs.getString(3));
          //is.setImage(path);
               //System.out.println(path);
                Image image = new Image(path.toURI().toString(),560,680,true,true);
               // System.out.println(image);
                is.setImageV2(image);
                ImageView Image = new ImageView(image);
                is.setImageV(Image);
                 is.setDescription(rs.getString(4));
           // ImageView nothing = new ImageView(is.getImage());
                //System.out.println(nothing);
               // System.out.println(Image);
         
              int x = (rs.getInt(5));
                String sql2 ="Select nom_SousCat from sous_categorie where id_SousCat ="+x;
               // System.out.println(sql2);
                 ste=cnx.prepareStatement(sql2);
             ResultSet rs2 =ste.executeQuery();
             while (rs2.next()){
             is.setNomsouscat(rs2.getString(1));
               
                infoS.add(is);
             }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return infoS;
    }
    
        public List<Info_Stream> afficherInfoStream2(String nom){
        ObservableList<Info_Stream> infoS = FXCollections.observableArrayList();
        String sql="select * from stream_info where nom_Stream LIKE ?";
        
          
            //system.out.println(sql);
                
                       
        try {
            ste=cnx.prepareStatement(sql);
            String nom2 = "%"+nom+"%";
                         ste.setString(1, nom2);
            ResultSet rs=ste.executeQuery();
            
            while(rs.next()){
                Info_Stream is = new Info_Stream();
                is.setId(rs.getInt(1));
                is.setNom(rs.getString(2));
                is.setImage(rs.getString(3));
                    File path = new File("src/black_ops/MediaStream/"+rs.getString(3));
          //is.setImage(path);
               // System.out.println(path);
                Image image = new Image(path.toURI().toString(),100,100,true,true);
                is.setImageV2(image);
                ImageView Image = new ImageView(image);
                is.setImageV(Image);
                is.setDescription(rs.getString(4));
                
                
              int x = (rs.getInt(5));
                String sql2 ="Select nom_SousCat from sous_categorie where id_SousCat ="+x;
               // System.out.println(sql2);
                 ste=cnx.prepareStatement(sql2);
             ResultSet rs2 =ste.executeQuery();
             while (rs2.next()){
             is.setNomsouscat(rs2.getString(1));
               
                infoS.add(is);
             }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return infoS;
    }
        
        
        
        
        public List<Info_Stream> afficherimage(){
        ObservableList<Info_Stream> infoS = FXCollections.observableArrayList();
        String sql="select image_Stream from stream_info";
        
          
            //system.out.println(sql);
                
                       
        try {
            ste=cnx.prepareStatement(sql);
  
            ResultSet rs=ste.executeQuery();
            
            while(rs.next()){
                Info_Stream is = new Info_Stream();
                File path = new File("src/black_ops/MediaStream/"+rs.getString(1));
               // System.out.println(path);
                Image image = new Image(path.toURI().toString());
                ImageView Image = new ImageView(image);
                is.setImageV(Image);
                    
                //System.out.println(image);
               
                infoS.add(is);
            
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return infoS;
    }

  
}
