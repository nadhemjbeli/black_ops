
package black_ops.Controller;

import black_ops.Entity.Joueur;
import black_ops.config.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class JoueurController {
    
    Connection mc;
    PreparedStatement ste;

    public JoueurController() {
        mc = DataBase.getInstance().getCnx();
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
    public List<Joueur> afficherJoueur(){
        List<Joueur> Joueur = new ArrayList<>();
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
    
}
