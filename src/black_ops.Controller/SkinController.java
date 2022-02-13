/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Skin;
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
public class SkinController {
  Connection mc;
    PreparedStatement ste;
    
    public SkinController(){
       mc=MaConnexion.getInstance().getCnx();
    }
    public void ajouterSkin(Skin s){
        String sql ="insert into skin ( image_skin,Id_champ ) Values(?,?)";
        try {
            ste=mc.prepareStatement(sql);
            ste.setString(1,s.getImage_skin());         
            ste.setInt(2, s.getId_champ());            
            ste.executeUpdate();
            System.out.println("skin Ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
public List<Skin> afficherSkins(){
        List<Skin> skins = new ArrayList<>();
        String sql="select * from skin";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Skin c = new Skin();
                c.setId_skin(rs.getInt("Id_skin"));
                c.setImage_skin(rs.getString("image_skin"));
                c.setId_champ (rs.getInt("Id_champ"));               
                skins.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return skins;
    }
      public void updateSkin(Skin s){
        String sql ="update skin set image_skin = ? ,Id_champ= ?  where Id_skin = ? ";
        try {
            ste=mc.prepareStatement(sql);
            ste.setString(1,s.getImage_skin());
            ste.setInt(2, s.getId_champ());
            ste.setInt(3, s.getId_skin());
            ste.executeUpdate();
            System.out.println("skin modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
       public void deleteSkin(Skin s){
        String sql ="delete from skin where Id_skin  = ? ";      
        try {
            ste=mc.prepareStatement(sql);                  
           ste.setInt(1, s.getId_skin());
            ste.executeUpdate();       
            ste.close();
        } 
         catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
}
}