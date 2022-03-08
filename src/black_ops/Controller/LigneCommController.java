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
import black_ops.Entity.LigneComm;

/**
 *
 * @author aZiz
 */
public class LigneCommController {
    
    Connection cnx;
    PreparedStatement ste;
    
    
    public LigneCommController(){

cnx = MaConnexion.getInstance().getCnx();

}
    
    public void ajouterLigneComm(LigneComm lc){
        String Sql = "INSERT INTO lignecommande (quantite_Billet, prix_Billet, id_defi, id_commande) values (?,?,?,?)";
        try{
            ste = cnx.prepareStatement(Sql);
            ste.setInt(1, lc.getQuantite());
            ste.setInt(2, lc.getPrix());
            ste.setInt(3, lc.getId_defi());
        
            ste.setInt(4, lc.getId_commande());
            
            int row = ste.executeUpdate();
            
            if (row > 0 ){
                
                System.out.println("ligne de commande Ajoutée");
                
            }else System.out.println("Erreur lors de l'insertion");
            
        } catch (SQLException sql){
            System.out.println(sql.getMessage());
            
        }
        
    }
    
    public void updateSouscat(LigneComm lc, Integer id){
        
        String Sql = "UPDATE lignecommande SET quantite_Billet = ?, prix_Billet = ? where id_LigneCommande = ?";
        try{
            
            ste = cnx.prepareStatement(Sql);
            ste.setInt(1, lc.getQuantite());
        
            ste.setInt(2, lc.getPrix());
            ste.setInt(3, id);
            
            int row = ste.executeUpdate();
            
            if (row > 0 ){
                
                System.out.println("mise a jour avec succès");
            }else System.out.println("Erreur lors de la mise a jour (Ex : vérifier l'existence du champ)");
            
            
            
        } catch (SQLException sql){
            
            System.out.println(sql.getMessage());
            
        }
        
    }
    
    
    
    public void supprimerLigneComm(Integer id){
        String Sql = "DELETE from lignecommande where id_LigneCommande = ?";
        
        try{
            
            ste = cnx.prepareStatement(Sql);
            
            ste.setInt(1,id);
            
            int row = ste.executeUpdate();
            
            if (row > 0 ){
                System.out.println("ligne de commande Id = " + id + " à été Supprimé avec succès");                
                
            }else System.out.println("Erreur de Suppression ID = " + id + " (Ex: Verifier l'existence du champ)");
            
        } catch (SQLException sql){
            System.out.println(sql.getMessage());
            
        }
        
        
        
    }
    
    public List<LigneComm> afficherLigneComm (){
        List<LigneComm> ligneComms = new ArrayList<>();
        String Sql = "Select * from lignecommande";
        
        try{
            ste = cnx.prepareStatement(Sql);
            ResultSet rs = ste.executeQuery();
            
            while(rs.next()){
                LigneComm lc  = new LigneComm();
                
                lc.setId(rs.getInt(1));
                lc.setQuantite(rs.getInt(2));
                lc.setPrix(rs.getInt(3));
                lc.setId_defi(rs.getInt(4));
                lc.setId_commande(rs.getInt(5));
                         
                
                ligneComms.add(lc);
            }
            
            
        } catch (SQLException sql){
            System.out.println(sql.getMessage());
            
        }
        
        return ligneComms;
        
        
        
        
        
    }

    
}

