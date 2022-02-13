/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.Controller;
import black_ops.Entity.Champion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import black_ops.config.MaConnexion;

/**
 *
 * @author jmokh
 */
public class ChampionController {
          Connection mc;
    PreparedStatement ste;
    
    public ChampionController(){
       mc=MaConnexion.getInstance().getCnx();
    }
    
    public void ajouterChampion(Champion c){
        String sql ="insert into champion ( Nom_Champ,description_Champ,Role_Champ,Difficulte_Champ,Image_Champ,Id_jeu ) Values(?,?,?,?,?,?)";
        try {
            ste=mc.prepareStatement(sql);
            ste.setString(1,c.getNom_Champ());
            ste.setString(2, c.getDescription_Champ());
            ste.setString(3, c.getRole_Champ());
            ste.setString(4, c.getDifficulte_Champ());
            ste.setString(5, c.getImage_Champ());
            ste.setInt(6, c.getId_jeu());            
            ste.executeUpdate();
            System.out.println("Champion Ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
      public List<Champion> afficherChampions(){
        List<Champion> Champions = new ArrayList<>();
        String sql="select * from champion";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Champion c = new Champion();
                c.setId_Champ(rs.getInt("Id_Champ"));
                c.setNom_Champ(rs.getString("Nom_champ"));
                c.setDescription_Champ(rs.getString("description_Champ"));
                c.setRole_Champ(rs.getString("Role_Champ"));
                c.setDifficulte_Champ(rs.getString("Difficulte_Champ"));
                c.setImage_Champ(rs.getString("Image_Champ"));
                c.setId_jeu(rs.getInt("Id_jeu"));
                
                Champions.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return Champions;
    }
      public void updateChampion(Champion c){
        String sql ="update champion set Nom_Champ = ? , description_Champ = ? , Role_Champ= ? ,Difficulte_Champ=? ,Image_Champ= ?,Id_jeu= ?  where Id_Champ = ? ";
        try {
            ste=mc.prepareStatement(sql);
            ste.setString(1,c.getNom_Champ());
            ste.setString(2, c.getDescription_Champ());
            ste.setString(3, c.getRole_Champ());
            ste.setString(4, c.getDifficulte_Champ());
            ste.setString(5, c.getImage_Champ());
            ste.setInt(6, c.getId_jeu());
            ste.setInt(7, c.getId_Champ());
            ste.executeUpdate();
            System.out.println("champion modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
       public void deleteChampion(Champion c){
        String sql ="delete from champion where Nom_Champ = ? ";      
        try {
            ste=mc.prepareStatement(sql);                  
           ste.setString(1, c.getNom_Champ());           
            ste.executeUpdate();       
            ste.close();
        } 
         catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
}}
