/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.Controller;

import black_ops.Entity.Jeu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import black_ops.config.MaConnexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author jmokh
 */
public class JeuController {
      Connection mc;
    PreparedStatement ste;
    
    public JeuController(){
       mc=MaConnexion.getInstance().getCnx();
    }
    
    public void ajouterJeu(Jeu j){
        String sql ="insert into jeu(nom,description,Url,id_souscat) Values(?,?,?,?)";
        try {
            ste=mc.prepareStatement(sql);
            ste.setString(1,j.getNom());
            ste.setString(2, j.getDescription());
            ste.setString(3, j.getUrl());
            ste.setInt(4, j.getId_souscat());
            ste.executeUpdate();
            System.out.println("Jeu Ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
       public ObservableList<Jeu> AfficherJeux(){
        ObservableList<Jeu> jeux = FXCollections.observableArrayList();
       
        String sql="select * from jeu";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Jeu j = new Jeu();
                j.setId_Jeu(rs.getInt("Id_Jeu"));
                j.setNom(rs.getString("Nom"));
                j.setDescription(rs.getString("description"));
                j.setUrl(rs.getString("Url"));
                j.setId_souscat(rs.getInt("id_souscat"));
                jeux.add(j);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return jeux;
    }
       
    public void updateJeu(Jeu j){
        String sql ="update jeu set Nom = ? , description = ? , Url= ?,id_souscat=? where Id_Jeu = ? ";
        try {
            ste=mc.prepareStatement(sql);
            ste.setString(1,j.getNom());
            ste.setString(2, j.getDescription());
            ste.setString(3, j.getUrl());
            ste.setInt(4, j.getId_souscat());
            ste.setInt(5, j.getId_Jeu());
            ste.executeUpdate();
            System.out.println("Jeu modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
     public void deletejeu(Jeu j){
        String sql ="delete from champion where Id_jeu = ( SELECT Id_jeu from jeu where Id_Jeu = ? )";
        String sql3 ="delete from image where Id_jeu = ( SELECT Id_jeu from jeu where Id_Jeu = ? )";
        String sql2 = "delete from jeu where Id_Jeu= ?";
        try {
            ste=mc.prepareStatement(sql);
            //ste=mc.prepareStatement(sql2);
          
            ste.setInt(1, j.getId_Jeu());
            
            ste.executeUpdate();
       
            ste.close();

        } 
         catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }
        try {
            ste=mc.prepareStatement(sql3);
            //ste=mc.prepareStatement(sql2);
          
            ste.setInt(1, j.getId_Jeu());
            
            ste.executeUpdate();
       
            ste.close();

        } 
         catch (SQLException ex) {
            System.out.println(ex.getMessage());}
        try {
            ste=mc.prepareStatement(sql2);
            //ste=mc.prepareStatement(sql2);
          
            ste.setInt(1, j.getId_Jeu());
            
            ste.executeUpdate();
            System.out.println("jeu supprimé");
            ste.close();

        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        

    }
}
    

