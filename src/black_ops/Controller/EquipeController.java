
package black_ops.Controller;

import black_ops.Entity.Equipe;
import black_ops.config.MaConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class EquipeController {
    Connection mc;
    PreparedStatement ste;

    public EquipeController() {
        mc = MaConnexion.getInstance().getCnx();
    }
    public void ajouterEquipe(Equipe eq){
    String sql = "INSERT INTO equipe(nom_Equipe,"
            + " logo_Equipe, date, nbr_joueur_Equipe) VALUES (?,?,?,?)";
    try {
            ste=mc.prepareStatement(sql);
            ste.setString(1, eq.getNom_Equipe());
            ste.setString(2, eq.getLogo_Equipe());
            ste.setDate(3, eq.getDate());
            ste.setInt(4, eq.getNbr_joueur_Equipe());
            ste.executeUpdate();
            System.out.println("Equipe  Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }
    
    public ObservableList<Equipe> afficherEquipe(){
ObservableList<Equipe> Equipe = FXCollections.observableArrayList();
String sql="select * from Equipe";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Equipe eq = new Equipe();
                eq.setId_Equipe(rs.getInt("id_Equipe"));
                eq.setNom_Equipe(rs.getString("nom_Equipe"));
                eq.setLogo_Equipe(rs.getString("logo_Equipe"));
                eq.setDate(rs.getDate("date"));
                eq.setNbr_joueur_Equipe(rs.getInt("nbr_joueur_Equipe"));
                Equipe.add(eq);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return Equipe;
    }
    public void DeleteEquipe(Equipe eq ){
        String sql =" DELETE FROM joueur WHERE id_equipe = ( SELECT id_equipe from equipe where id_equipe = ? )" ;
       
        try {
            ste=mc.prepareStatement(sql);
            ste.setInt(1, eq.getId_Equipe());  
            ste.executeUpdate();
            System.out.println("Joueur Suprrime");
            ste.close();

        } 
         catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         String sql3 ="delete from details_defi where EquipeA = ( SELECT id_equipe from equipe where id_equipe = ? )" ;
        try {
            ste=mc.prepareStatement(sql3);
            ste.setInt(1,eq.getId_Equipe());
            ste.executeUpdate();
            System.out.println("Detail_defis supprimé");
            ste.close();

        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        String sql4 ="delete from details_defi where EquipeB = ( SELECT id_equipe from equipe where id_equipe = ? )" ;
        try {
            ste=mc.prepareStatement(sql3);
            ste.setInt(1,eq.getId_Equipe());
            ste.executeUpdate();
            System.out.println("Detail_defis supprimé");
            ste.close();

        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         String sql2 ="delete from equipe where id_equipe = ?" ;
        try {
            ste=mc.prepareStatement(sql2);
            ste.setInt(1,eq.getId_Equipe());
            ste.executeUpdate();
            System.out.println("Equipe supprimé");
            ste.close();

        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
        }
    
    public void UpdateEquipe(Equipe eq){
        String sql ="UPDATE equipe SET nom_Equipe= ?, logo_Equipe=? , date= ? , nbr_joueur_Equipe= ?"
                + " WHERE id_Equipe = ?";
        try {
            ste=mc.prepareStatement(sql);
            ste.setInt(5, eq.getId_Equipe());
            ste.setString(1, eq.getNom_Equipe());
            ste.setString(2, eq.getLogo_Equipe());
            ste.setDate(3,  eq.getDate());
            ste.setInt(4, eq.getNbr_joueur_Equipe());
            ste.executeUpdate();
            System.out.println("Equipe modifier");
            ste.close();
      
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
        public ObservableList<Equipe> Select_Detail(String nom){
        ObservableList<Equipe> Equipe = FXCollections.observableArrayList();

        String sql="select * from Equipe where nom_Equipe  COLLATE UTF8_GENERAL_CI like '%"+nom+"%'" ;
               
        
        try{
        ste=mc.prepareStatement(sql);
        
        ResultSet rs=ste.executeQuery();
           while(rs.next()){
               Equipe Eq_s = new Equipe();
              Eq_s.setId_Equipe(rs.getInt("id_Equipe"));
               Eq_s.setNom_Equipe(rs.getString("nom_Equipe"));
                Eq_s.setLogo_Equipe(rs.getString("logo_Equipe"));
                Eq_s.setDate(rs.getDate("date"));
                Eq_s.setNbr_joueur_Equipe(rs.getInt("nbr_joueur_Equipe"));
                Equipe.add(Eq_s);
           }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Equipe ;
} 
    }
   
