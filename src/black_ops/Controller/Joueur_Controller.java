
package black_ops.Controller;

import black_ops.Entity.Equipe;
import black_ops.Entity.Joueur;
import black_ops.GUI.Gestion_Competition.Gestion_Joueur.Pair;
import black_ops.config.MaConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class Joueur_Controller {
    
    Connection mc;
    PreparedStatement ste;

    public Joueur_Controller() {
        mc = MaConnexion.getInstance().getCnx();
    }
    public void ajouterJoueur(Joueur j){
    String sql = "INSERT INTO joueur(nom_Joueur, rang_Joueur, Pseaudo_Joueur, id_equipe) VALUES (?,?,?,?)";
    try {
            ste=mc.prepareStatement(sql);
            ste.setString(1, j.getNom_Joueur());
            ste.setString(2, j.getRang_Joueur());
            ste.setString(3, j.getPseaudo_Joueur());
            ste.setInt(4,j.getId_equipe());
            ste.executeUpdate();
            System.out.println("Joueur  Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }
    public ObservableList<Joueur>afficherJoueur(){
       ObservableList<Joueur> Joueur = FXCollections.observableArrayList();

        String sql="select * from Joueur";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Joueur j = new Joueur();
                j.setId_Joueur(rs.getInt("id_Joueur"));
                j.setNom_Joueur(rs.getString("nom_Joueur"));
                j.setRang_Joueur(rs.getString("rang_Joueur"));
                j.setPseaudo_Joueur(rs.getString("Pseaudo_Joueur"));
                j.setId_equipe(rs.getInt("id_equipe"));

                Joueur.add(j);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return Joueur;
    }
    
    public void DeleteJoueur(Joueur j ){
        
        String sql =" DELETE FROM joueur WHERE id_Joueur = ?" ;
        try{
        ste=mc.prepareStatement(sql);
        ste.setInt(1, j.getId_Joueur());
        ste.executeUpdate();
        System.out.println("Joueur supprimer");
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void UpdateJoueur(Joueur j){
    String sql = " UPDATE joueur SET nom_Joueur= ? ,"
            + "rang_Joueur= ? ,Pseaudo_Joueur= ? ,"
            + "id_equipe= ? WHERE id_Joueur = ? " ;
    try {
            ste=mc.prepareStatement(sql);
            ste.setString(1, j.getNom_Joueur());
            ste.setString(2, j.getRang_Joueur());
            ste.setString(3, j.getPseaudo_Joueur());
            ste.setInt(4,j.getId_equipe());
            ste.setInt(5,j.getId_Joueur());
            
            ste.executeUpdate();
            System.out.println("joueur modifier");
            ste.close();
      
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }
    public ObservableList<Pair> RechercheAV(String nom){
       ObservableList<Pair> Joueur = FXCollections.observableArrayList();

        String sql="SELECT DISTINCT j.nom_Joueur ,e.nom_Equipe FROM defi de"
                + " INNER JOIN details_defi dd on de.id_Defi = dd.id_defi "
                + "INNER join equipe e on (dd.EquipeA = e.id_Equipe OR dd.EquipeB = e.id_Equipe)"
                + " INNER JOIN joueur j on j.id_equipe = e.id_Equipe "
                + "WHERE e.nom_Equipe LIKE '%"+nom+"%'";
        try {
            
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Joueur j = new Joueur();
                Equipe e =new Equipe();
               
                j.setNom_Joueur(rs.getString("nom_Joueur"));
                e.setNom_Equipe(rs.getString("nom_Equipe"));
                
                Pair p = new Pair(j,e);
                
                Joueur.add(p);
                //System.out.println(Joueur);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return Joueur;
    }
    public Joueur Select(){
        Joueur j = new Joueur();

        String sql="SELECT * FROM `joueur` WHERE id_Joueur = ( SELECT MAX(id_Joueur) FROM `joueur`)" ;

        try{
        ste=mc.prepareStatement(sql);
        
        ResultSet rs=ste.executeQuery();
           while(rs.next()){
                
                j.setNom_Joueur(rs.getString("nom_Joueur"));
                j.setRang_Joueur(rs.getString("rang_Joueur"));
                j.setPseaudo_Joueur(rs.getString("Pseaudo_Joueur"));
               
           }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return j ;
} 
    
}
