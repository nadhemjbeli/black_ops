
package black_ops.Controller;
import black_ops.Entity.Defi;
import black_ops.config.MaConnexion;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
public class DefiController {
    Connection mc;
    PreparedStatement ste;

    public DefiController() {
        mc = MaConnexion.getInstance().getCnx();
    }
    public void ajouterDefi(Defi d){
        String sql ="INSERT INTO defi(nom_Defi, Description_Defi,"
                + " img_Defi, prix_Defi, date_Defi, jeu_Defi,"
                + " nbr_equipe_Defi, Régle_Defi, Recompense_Defi) VALUES"
                + "(?,?,?,?,?,?,?,?,?)";
        try {
            ste=mc.prepareStatement(sql);
            ste.setString(1, d.getNom_Defi());
            ste.setString(2, d.getDescription());
            ste.setString(3, d.getImg_Defi());
            ste.setInt(4, d.getPrix());
            ste.setDate(5,  d.getDate_defi());
            ste.setString(6, d.getJeu_Defis());
            ste.setInt(7, d.getNbr_equipe_Defi());
            ste.setString(8, d.getRégle_Defi());
            ste.setString(9, d.getRecompense_Defi());
            
            ste.executeUpdate();
            System.out.println("Defi Ajoutée");
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
        
    }
      public void UpdateDefi(Defi d){
        String sql ="UPDATE defi SET nom_Defi= ?,"
                + "Description_Defi= ? ,img_Defi= ? ,prix_Defi= ? ,"
                + "date_Defi= ? ,jeu_Defi= ? ,nbr_equipe_Defi= ? ,"
                + "Régle_Defi = ? ,Recompense_Defi= ? WHERE id_Defi = ? ";
        try {
            ste=mc.prepareStatement(sql);
            ste.setInt(10, d.getId_Defi());
            ste.setString(1, d.getNom_Defi());
            ste.setString(2, d.getDescription());
            ste.setString(3, d.getImg_Defi());
            ste.setInt(4, d.getPrix());
            ste.setDate(5,  d.getDate_defi());
            ste.setString(6, d.getJeu_Defis());
            ste.setInt(7, d.getNbr_equipe_Defi());
            ste.setString(8, d.getRégle_Defi());
            ste.setString(9, d.getRecompense_Defi());
            ste.executeUpdate();
            System.out.println("Defi modifier");
            ste.close();
      
        } catch (SQLException ex) {
             Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
        
    }
    public ObservableList<Defi> afficherDefi(){
        ObservableList<Defi> Defi = FXCollections.observableArrayList();
        String sql="select * from Defi";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Defi d = new Defi();
                d.setId_Defi(rs.getInt("Id_Defi"));
                d.setNom_Defi(rs.getString("Nom_Defi"));
                d.setDescription(rs.getString("Description_Defi"));
                d.setImg_Defi(rs.getString("Img_defi"));
                d.setPrix(rs.getInt("prix_Defi"));
                d.setDate_defi(rs.getDate("date_Defi"));
                d.setJeu_Defis((rs.getString("Jeu_Defi")));
                d.setNbr_equipe_Defi(rs.getInt("nbr_equipe_Defi"));
                d.setRégle_Defi(rs.getString("Régle_Defi"));
                d.setRecompense_Defi(rs.getString("Recompense_Defi"));
                Defi.add(d);
            }
        } catch (SQLException ex) {
             Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
        
        return Defi;
    }    
    public void DeleteDefi(Defi p ){
        String sql ="delete from details_defi where id_defi = ( SELECT id_Defi from defi where id_Defi = ? )";
        try{
        ste=mc.prepareStatement(sql);
        ste.setInt(1, p.getId_Defi());
        ste.executeUpdate();
        System.out.println("details_defi supprimer");
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        String sql2 ="DELETE FROM defi WHERE id_Defi = ?";
        try{
        ste=mc.prepareStatement(sql2);
        ste.setInt(1, p.getId_Defi());
        ste.executeUpdate();
        System.out.println("Defi supprimer");
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    public ObservableList<Defi> Select(String nom){
        ObservableList<Defi> Defi = FXCollections.observableArrayList();;
        String sql="select * from defi where Nom_Defi  COLLATE UTF8_GENERAL_CI like '%"+nom+"%'"
                + " or Jeu_Defi COLLATE UTF8_GENERAL_CI like '%"+nom+"%'"
                +"or Description_Defi COLLATE UTF8_GENERAL_CI like '%"+nom+"%'";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Defi d = new Defi();
                d.setId_Defi(rs.getInt("Id_Defi"));
                d.setNom_Defi(rs.getString("Nom_Defi"));
                d.setDescription(rs.getString("Description_Defi"));
                d.setImg_Defi(rs.getString("Img_defi"));
                d.setPrix(rs.getInt("prix_Defi"));
                d.setDate_defi(rs.getDate("date_Defi"));
                d.setJeu_Defis((rs.getString("Jeu_Defi")));
                d.setNbr_equipe_Defi(rs.getInt("nbr_equipe_Defi"));
                d.setRégle_Defi(rs.getString("Régle_Defi"));
                d.setRecompense_Defi(rs.getString("Recompense_Defi"));
                Defi.add(d);
            }
        } catch (SQLException ex) {
             Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();

    }
         return Defi;
    }
    
}
