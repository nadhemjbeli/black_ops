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
import black_ops.Entity.Commande;

/**
 *
 * @author aZiz
 */
public class CommandeController {
    Connection cnx;
       PreparedStatement ste;
       
       
       
       
       public CommandeController(){
           cnx=MaConnexion.getInstance().getCnx();
           
       }
       
         public void ajouterCommande(Commande c){
        String sql ="INSERT INTO commande(Etat_commande,id_cl) VALUES (?,?)";
        try {
            ste=cnx.prepareStatement(sql);
            ste.setString(1, c.getEtat());
           ste.setInt(2, c.getId_cl());

            ste.executeUpdate();
            System.out.println("Commande Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
         
         // public void updateInfoStream(Info_Stream is){
                   public void updateCommande(Commande c, Integer id){

        String sql ="UPDATE commande SET Etat_commande where id_Commande=?";
        try {
            ste=cnx.prepareStatement(sql);
            ste.setString(1, c.getEtat());
    
                        ste.setInt(2, id);


            ste.executeUpdate();
            System.out.println("Commande is Update....");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
          
          //public void supprimerInfoStream(Info_Stream is){
                    public void supprimerCommande(Integer id){

        String sql ="DELETE from commande where id_Commande=?";
        try {
            ste=cnx.prepareStatement(sql);
            ste.setInt(1, id);
        


           int row = ste.executeUpdate();
           if (row == 1 ){
            System.out.println("commande est supprimé....");
           }else {
                           System.out.println("commande n'existe pas....");

           }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
          
          
    public List<Commande> afficherCommande(){
        List<Commande> commandes = new ArrayList<>();
        String sql="select * from commande";
        try {
            ste=cnx.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Commande c = new Commande();
                c.setId(rs.getInt(1));
                c.setDate(rs.getDate(2));
               c.setEtat(rs.getString(3));
                c.setId_cl(rs.getInt(4));
                
                commandes.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return commandes;
    }
       
}
