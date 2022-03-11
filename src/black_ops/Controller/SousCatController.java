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
import black_ops.Entity.SousCat;
import black_ops.GUI.gestion_categorie.SousCategorie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author aZiz
 */
public class SousCatController {
    
    Connection cnx;
    PreparedStatement ste;
    
    
    public SousCatController(){

cnx = MaConnexion.getInstance().getCnx();

}
    
    public void ajouterSouscat(SousCat sc){
        String Sql = "INSERT INTO sous_categorie (nom_SousCat,id_cat) values (?,?)";
        try{
            ste = cnx.prepareStatement(Sql);
            ste.setString(1, sc.getNom());
        
            ste.setInt(2, sc.getId_cat());
            
            int row = ste.executeUpdate();
            
            if (row > 0 ){
                
                System.out.println("sous_categorie Ajoutée");
                
            }else System.out.println("Erreur lors de l'insertion");
            
        } catch (SQLException sql){
            System.out.println(sql.getMessage());
            
        }
        
    }
    
    public void updateSouscat(SousCat sc){
        
        String Sql = "UPDATE sous_categorie SET nom_SousCat = ?, id_cat = ? where id_SousCat = ?";
        try{
            
            ste = cnx.prepareStatement(Sql);
            ste.setString(1, sc.getNom());
        
            ste.setInt(2, sc.getId_cat());
            
            ste.setInt(3,sc.getId());
            
            
            int row = ste.executeUpdate();
            
            if (row > 0 ){
                
                System.out.println("mise a jour avec succès");
            }else System.out.println("Erreur lors de la mise a jour (Ex : vérifier l'existence du champ)");
            
            
            
        } catch (SQLException sql){
            
            System.out.println(sql.getMessage());
            
        }
        
    }
    
    
    
    public void supprimerSouscat(Integer id){
        String Sql = "DELETE from sous_categorie where id_SousCat = ?";
        
        try{
            
            ste = cnx.prepareStatement(Sql);
            
            ste.setInt(1,id);
            
            int row = ste.executeUpdate();
            
            if (row > 0 ){
                System.out.println("sous_categorie Id = " + id + " à été Supprimé avec succès");                
                
            }else System.out.println("Erreur de Suppression ID = " + id + " (Ex: Verifier l'existence du champ)");
            
        } catch (SQLException sql){
            System.out.println(sql.getMessage());
            
        }
        
        
        
    }
    
    public ObservableList<SousCat> afficherSouscat (){
        ObservableList<SousCat> SousCats = FXCollections.observableArrayList();
        String Sql = "Select * from sous_categorie";
        
        try{
            ste = cnx.prepareStatement(Sql);
            ResultSet rs = ste.executeQuery();
            
            while(rs.next()){
                SousCat sc  = new SousCat();
                
                sc.setId(rs.getInt(1));
                sc.setNom(rs.getString(2));
            
                sc.setId_cat(rs.getInt(3));
                
                SousCats.add(sc);
            }
            
            
        } catch (SQLException sql){
            System.out.println(sql.getMessage());
            
        }
        
        return SousCats;
        
        
        
        
        
    }

    
}

