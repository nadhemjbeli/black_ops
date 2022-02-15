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
import black_ops.Entity.Info_Stream;

/**
 *
 * @author aZiz
 */
public class InfoStreamController {
    Connection cnx;
       PreparedStatement ste;
       
       
       
       
       public InfoStreamController(){
           cnx=MaConnexion.getInstance().getCnx();
           
       }
       
         public void ajouterInfoStream(Info_Stream is){
        String sql ="INSERT INTO stream_info(nom_Stream, image_Stream, description_Stream, id_souscat) VALUES (?,?,?,?)";
        try {
            ste=cnx.prepareStatement(sql);
            ste.setString(1, is.getNom());
            ste.setString(2, is.getImage());
            ste.setString(3, is.getDescription());
            ste.setInt(4, is.getId_souscat());

            ste.executeUpdate();
            System.out.println("Information Stream Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
         
         // public void updateInfoStream(Info_Stream is){
                   public void updateInfoStream(Info_Stream is, Integer id){

        String sql ="UPDATE stream_info SET nom_Stream=?,image_Stream=?,description_Stream=?,id_souscat=? where id_Stream=?";
        try {
            ste=cnx.prepareStatement(sql);
            ste.setString(1, is.getNom());
            ste.setString(2, is.getImage());
            ste.setString(3, is.getDescription());
            ste.setInt(4, is.getId_souscat());
                        ste.setInt(5, id);


            ste.executeUpdate();
            System.out.println("Information Stream is Update....");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
          
          //public void supprimerInfoStream(Info_Stream is){
                    public void supprimerInfoStream(Integer id){

        String sql ="DELETE from stream_info where id_Stream=?";
        try {
            ste=cnx.prepareStatement(sql);
            ste.setInt(1, id);
        


           int row = ste.executeUpdate();
           if (row == 1 ){
            System.out.println("Information Stream est supprimé....");
           }else {
                           System.out.println("Information Stream n'existe pas....");

           }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
          
          
    public List<Info_Stream> afficherInfoStream(){
        List<Info_Stream> infoS = new ArrayList<>();
        String sql="select * from stream_info";
        try {
            ste=cnx.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Info_Stream is = new Info_Stream();
                is.setId(rs.getInt(1));
                is.setNom(rs.getString(2));
                is.setImage(rs.getString(3));
                is.setDescription(rs.getString(4));
                is.setId_souscat(rs.getInt(5));
                
                infoS.add(is);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return infoS;
    }
       
}
