
package black_ops.Controller;

import black_ops.Entity.Details_Defi;
import black_ops.config.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Details_DefiController {
    Connection mc;
    PreparedStatement ste;

    public Details_DefiController() {
                mc = DataBase.getInstance().getCnx();

    }
    
    public void Ajouter_Details_Defi(Details_Defi d_f){
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
    public List<Details_Defi> afficher_Details_defi(){
        List<Details_Defi> Details_Defi = new ArrayList<>();
        String sql="select * from details_defi";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Details_Defi df = new Details_Defi();
              df.setId_Statistique(rs.getInt("id_Statistique"));
              df.setEquipeA(rs.getInt("EquipeA"));
              df.setImgScore(rs.getString("imgScore"));
              df.setEquipeB(rs.getInt("EquipeB"));
              df.setScore_finale(rs.getString("Score_finale"));
              df.setId_defi(rs.getInt("id_defi"));
                

                Details_Defi.add(df);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return Details_Defi;
    }
    
    public List<Details_Defi> Select_Detail(Details_Defi De_Se){
        String sql="SELECT * FROM details_defi WHERE id_Statistique = ?";
        List<Details_Defi> match = new ArrayList<>();
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
            System.out.println(ex.getMessage());
        }
        return match ;
    }
    
    public void DeleteEquipe(Details_Defi ddf ){
        
        String sql ="delete from details_defi where id_Statistique = ?";
        try{
        ste=mc.prepareStatement(sql);
        ste.setInt(1, ddf.getId_Statistique());
        ste.executeUpdate();
        System.out.println("Details_Defi supprimer");
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void UpdateDefi(Details_Defi d_f){
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
            System.out.println(ex.getMessage());
        }
        
    }
}
