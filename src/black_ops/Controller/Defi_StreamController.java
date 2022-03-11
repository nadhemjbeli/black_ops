/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.Controller;

import black_ops.Entity.Defi_Stream;
import black_ops.config.MaConnexion;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author aZiz
 */
public class Defi_StreamController {
    
       Connection mc;
    PreparedStatement ste;

    public Defi_StreamController() {
        mc = MaConnexion.getInstance().getCnx();
    }
    
     public ObservableList<Defi_Stream> afficherDefi(){
        ObservableList<Defi_Stream> Defi = FXCollections.observableArrayList();;
        String sql="select * from Defi";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Defi_Stream d = new Defi_Stream();
                d.setId_Defi(rs.getInt(1));
                d.setNom_Defi(rs.getString(2));
                d.setDescription(rs.getString(3));
                d.setImg_Defi(rs.getString(4));
                  File path = new File(rs.getString(4));
     
                Image image = new Image(path.toURI().toString(),100,100,true,true);
             
                ImageView Image = new ImageView(image);
                d.setImage(Image);
                
                d.setPrix(rs.getInt(5));
                d.setDate_defi(rs.getDate(6));
                d.setJeu_Defis((rs.getString(7)));
                d.setNbr_equipe_Defi(rs.getInt(8));
                d.setRégle_Defi(rs.getString(9));
                d.setRecompense_Defi(rs.getString(10));
                Defi.add(d);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return Defi;
    } 
    
     
      public ObservableList<Defi_Stream> afficherDefi(int id){
        ObservableList<Defi_Stream> Defi = FXCollections.observableArrayList();;
        String sql="select * from Defi where id_Defi="+id;
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Defi_Stream d = new Defi_Stream();
                d.setId_Defi(rs.getInt(1));
                d.setNom_Defi(rs.getString(2));
                d.setDescription(rs.getString(3));
                d.setImg_Defi(rs.getString(4));
                  File path = new File(rs.getString(4));
     
                Image image = new Image(path.toURI().toString(),400,300,true,true);
             d.setImagev(image);
                ImageView Image = new ImageView(image);
                d.setImage(Image);
                
                d.setPrix(rs.getInt(5));
                d.setDate_defi(rs.getDate(6));
                d.setJeu_Defis((rs.getString(7)));
                d.setNbr_equipe_Defi(rs.getInt(8));
                d.setRégle_Defi(rs.getString(9));
                d.setRecompense_Defi(rs.getString(10));
                Defi.add(d);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return Defi;
    }
      
      
    
 
}
