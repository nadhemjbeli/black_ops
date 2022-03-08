
package black_ops.Controller;

import black_ops.Entity.Defi;
import black_ops.Entity.DetailsDefi;
import black_ops.Entity.Equipe;
import black_ops.GUI_User.Competition.Match.Recherche;
import black_ops.config.MaConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;


public class DetailsDefiController {
    Connection mc;
    PreparedStatement ste;

    public DetailsDefiController() {
                mc = MaConnexion.getInstance().getCnx();

    }
    
    public void Create_Details_Defi(DetailsDefi d_f){
    String sql = "INSERT INTO details_defi( EquipeA, imgScore, EquipeB,Score_finale, id_defi) VALUES (?,?,?,?,?)";
    try {
            ste=mc.prepareStatement(sql);
            ste.setInt(1, d_f.getEquipeA());
            ste.setString(2, d_f.getImgScore());
            ste.setInt(3, d_f.getEquipeB());
            ste.setString(4,d_f.getScore_finale());
            ste.setInt(5, d_f.getId_defi());
            ste.executeUpdate();
            System.out.println("details_defi  Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }
    public ObservableList<DetailsDefi>  View_Details_defi(){
        ObservableList<DetailsDefi> match = FXCollections.observableArrayList();
        String sql="select * from details_defi";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                DetailsDefi df = new DetailsDefi();
              df.setId_Statistique(rs.getInt("id_Statistique"));
              df.setEquipeA(rs.getInt("EquipeA"));
              df.setImgScore(rs.getString("imgScore"));
              df.setEquipeB(rs.getInt("EquipeB"));
              df.setScore_finale(rs.getString("Score_finale"));
              df.setId_defi(rs.getInt("id_defi"));
              match.add(df);
            }
        } catch (SQLException ex) {
           Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
        
        return match;
    }
    
    public  ObservableList<DetailsDefi> Select_Detail(DetailsDefi De_Se){
        ObservableList<DetailsDefi> match = FXCollections.observableArrayList();
        String sql="SELECT * FROM details_defi WHERE id_Statistique = ?";
        
        try{
        ste=mc.prepareStatement(sql);
        ste.setInt(1, De_Se.getId_Statistique());
        ResultSet rs=ste.executeQuery();
        while(rs.next()){
              De_Se.setId_Statistique(rs.getInt("id_Statistique"));
              De_Se.setEquipeA(rs.getInt("EquipeA"));
              De_Se.setImgScore(rs.getString("imgScore"));
              De_Se.setEquipeB(rs.getInt("EquipeB"));
              De_Se.setScore_finale(rs.getString("Score_finale"));
              De_Se.setId_defi(rs.getInt("id_defi"));
                

              match.add(De_Se);
        }
        }catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
        return match ;
    }
    
    public void Delete_Details_Defi(DetailsDefi ddf ){
        
        String sql ="delete from details_defi where id_Statistique = ?";
        try{
        ste=mc.prepareStatement(sql);
        ste.setInt(1, ddf.getId_Statistique());
        ste.executeUpdate();
        System.out.println("Details_Defi supprimer");
        }catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }
    public void Update_Details_Defi(DetailsDefi d_f){
        String sql ="UPDATE details_defi SET EquipeA=?,imgScore=?,"
                + "EquipeB=?,Score_finale=?,id_defi=? WHERE id_Statistique= ? ";
        try {
            ste=mc.prepareStatement(sql);
            ste.setInt(1, d_f.getEquipeA());
            ste.setString(2, d_f.getImgScore());
            ste.setInt(3, d_f.getEquipeB());
            ste.setString(4,d_f.getScore_finale());
            ste.setInt(5, d_f.getId_defi());
            ste.setInt(6,d_f.getId_Statistique());
            ste.executeUpdate();
            System.out.println("detail_defi modifier");
            ste.close();
      
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
        
    }
    public ObservableList<Recherche> recherche(String nom) {

        ObservableList<Recherche> dd = FXCollections.observableArrayList();

        try {
            String sql3 = "SELECT DISTINCT * FROM defi de INNER JOIN details_defi dd on de.id_Defi = dd.id_defi "
                    + "INNER join equipe ea on (dd.EquipeA = ea.id_Equipe) "
                    + "INNER join equipe eb on (dd.EquipeB = eb.id_Equipe) "
                    + "WHERE (ea.nom_Equipe LIKE '%%'AND eb.nom_equipe LIKE '%"+nom+"%') "
                    + "or (ea.nom_Equipe LIKE '%"+nom+"%'AND eb.nom_equipe LIKE '%%')";
            ste = mc.prepareStatement(sql3);
            
            ResultSet rs = ste.executeQuery();
           
            while (rs.next()) {  
                Equipe A = new Equipe();
                Equipe B = new Equipe();
                Defi D = new Defi();
                DetailsDefi detaildefi = new DetailsDefi();
                A.setNom_Equipe(rs.getString("ea.nom_Equipe"));
                A.setLogo_Equipe(rs.getString("ea.logo_Equipe"));
                B.setLogo_Equipe(rs.getString("eb.logo_Equipe"));
                B.setNom_Equipe(rs.getString("eb.nom_Equipe"));
                D.setNom_Defi(rs.getString("de.nom_Defi"));
                detaildefi.setImgScore(rs.getString("dd.imgScore"));
                detaildefi.setScore_finale(rs.getString("dd.Score_finale"));
                Recherche r = new Recherche();
                r.setEquipeA(A);
                r.setEquipeB(B);
                r.setDefi(D);
                r.setDdefi(detaildefi);

                dd.add(r);
            }
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }

        return dd;
    }


}
