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
import black_ops.Entity.Categorie;
import black_ops.GUI.gestion_categorie.SousCategorie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author amin
 * 
 */
public class CategorieController {
    Connection cnx;
       PreparedStatement ste;
       
       
       
       
       public CategorieController(){
           cnx=MaConnexion.getInstance().getCnx();
           
       }
       
         public void ajouterCategorie(Categorie c){
        String sql ="INSERT INTO categorie(nom_cat) VALUES (?)";
        try {
            ste=cnx.prepareStatement(sql);
            ste.setString(1, c.getNom());
           

            ste.executeUpdate();
            System.out.println("Categorie Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
         
         // public void updateInfoStream(Info_Stream is){
                   public void updateCategorie(Categorie c){

        String sql ="UPDATE categorie SET nom_cat=? where id_cat=?";
        try {
            ste=cnx.prepareStatement(sql);
            ste.setString(1, c.getNom());
    
                        ste.setInt(2, c.getId());


            ste.executeUpdate();
            System.out.println("Categorie is Update....");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
          
          //public void supprimerInfoStream(Info_Stream is){
                    public void supprimerCategorie(Integer id){

        String sql ="DELETE from categorie where id_cat=?";
        try {
            ste=cnx.prepareStatement(sql);
            ste.setInt(1, id);
        


           int row = ste.executeUpdate();
           if (row == 1 ){
            System.out.println("Categorie est supprimé....");
           }else {
                           System.out.println("Categorie n'existe pas....");

           }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
          
          
    public ObservableList<Categorie> afficherCategorie(){
        ObservableList<Categorie> categories;
        categories = FXCollections.observableArrayList();
        String sql="select * from categorie";
        try {
            ste=cnx.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Categorie c = new Categorie();
                c.setId(rs.getInt(1));
                c.setNom(rs.getString(2));
              
                
                categories.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return categories;
    }


       
}
