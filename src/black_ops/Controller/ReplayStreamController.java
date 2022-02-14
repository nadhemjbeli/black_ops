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
import black_ops.Entity.Replay_Stream;

/**
 *
 * @author aZiz
 */
public class ReplayStreamController {
    
    Connection cnx;
    PreparedStatement ste;
    
    
    public ReplayStreamController(){

cnx = MaConnexion.getInstance().getCnx();

}
    
    public void ajouterReplay(Replay_Stream replay){
        String Sql = "INSERT INTO replay_stream (nom_Replay,URL_Video,Description_Replay,id_souscat) values (?,?,?,?)";
        try{
            ste = cnx.prepareStatement(Sql);
            ste.setString(1, replay.getNom());
            ste.setString(2, replay.getUrl());
            ste.setString(3, replay.getDescription());
            ste.setInt(4, replay.getId_souscat());
            
            int row = ste.executeUpdate();
            
            if (row > 0 ){
                
                System.out.println("Replay Streaming Ajoutée");
                
            }else System.out.println("Erreur lors de l'insertion");
            
        } catch (SQLException sql){
            System.out.println(sql.getMessage());
            
        }
        
    }
    
    public void updateReplay(Replay_Stream replay, Integer id){
        
        String Sql = "UPDATE replay_stream SET nom_Replay = ?, URL_Video = ?, Description_Replay = ?, id_souscat = ? where id_Replay = ?";
        try{
            
            ste = cnx.prepareStatement(Sql);
            ste.setString(1, replay.getNom());
            ste.setString(2, replay.getUrl());
            ste.setString(3, replay.getDescription());
            ste.setInt(4, replay.getId_souscat());
            ste.setInt(5, id);
            
            int row = ste.executeUpdate();
            
            if (row > 0 ){
                
                System.out.println("mise a jour avec succès");
            }else System.out.println("Erreur lors de la mise a jour (Ex : vérifier l'existence du champ)");
            
            
            
        } catch (SQLException sql){
            
            System.out.println(sql.getMessage());
            
        }
        
    }
    
    
    
    public void supprimerReplay(Integer id){
        String Sql = "DELETE from replay_stream where id_Replay = ?";
        
        try{
            
            ste = cnx.prepareStatement(Sql);
            
            ste.setInt(1,id);
            
            int row = ste.executeUpdate();
            
            if (row > 0 ){
                System.out.println("Replay Stream Id = " + id + " à été Supprimé avec succès");                
                
            }else System.out.println("Erreur de Suppression ID = " + id + " (Ex: Verifier l'existence du champ)");
            
        } catch (SQLException sql){
            System.out.println(sql.getMessage());
            
        }
        
        
        
    }
    
    public List<Replay_Stream> afficherReplay (){
        List<Replay_Stream> replays = new ArrayList<>();
        String Sql = "Select * from replay_stream";
        
        try{
            ste = cnx.prepareStatement(Sql);
            ResultSet rs = ste.executeQuery();
            
            while(rs.next()){
                Replay_Stream replay  = new Replay_Stream();
                
                replay.setId(rs.getInt(1));
                replay.setNom(rs.getString(2));
                replay.setUrl(rs.getString(3));
                replay.setDate(rs.getDate(4));
                replay.setDescription(rs.getString(5));
                replay.setId_souscat(rs.getInt(6));
                
                replays.add(replay);
            }
            
            
        } catch (SQLException sql){
            System.out.println(sql.getMessage());
            
        }
        
        return replays;
        
        
        
        
        
    }

    
}

