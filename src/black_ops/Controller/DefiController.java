
package black_ops.Controller;
import black_ops.Entity.Defi;
import black_ops.config.DataBase;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class DefiController {
    Connection mc;
    PreparedStatement ste;

    public DefiController() {
        mc = DataBase.getInstance().getCnx();
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
            System.out.println(ex.getMessage());
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
            System.out.println(ex.getMessage());
        }
        
    }
    public List<Defi> afficherDefi(){
        List<Defi> Defi = new ArrayList<>();
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
            System.out.println(ex.getMessage());
        }
        
        return Defi;
    }    
    public void DeleteDefi(Defi p ){
        
        String sql ="delete from Defi where id_Defi = ?";
        try{
        ste=mc.prepareStatement(sql);
        ste.setInt(1, p.getId_Defi());
        ste.executeUpdate();
        System.out.println("Defi supprimer");
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
