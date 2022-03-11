/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.Controller;

import black_ops.Entity.Commentaire_Stream;
import black_ops.Entity.Live_Stream;
import black_ops.config.MaConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author aZiz
 */
public class LiveStreamController {
    Connection cnx;
       PreparedStatement ste;
       
       
       
       
       public LiveStreamController(){
           cnx=MaConnexion.getInstance().getCnx();
           
       }
       
         public void ajouterLiveStream(Live_Stream ls){
        String sql ="INSERT INTO live_stream(Nom_LiveStream,Path_LiveStream , Visibilite_LiveStream, id_defi) VALUES (?,?,?,?)";
        try {
            ste=cnx.prepareStatement(sql);
            ste.setString(1, ls.getNom());
            ste.setString(2, ls.getPath());
            ste.setString(3, ls.getVisiblte());
            ste.setInt(4, ls.getId_defi());

            ste.executeUpdate();
            System.out.println("Live Stream Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
        public List<Live_Stream> afficherLiveStream(){
        ObservableList<Live_Stream> infoS = FXCollections.observableArrayList();
        String sql="select * from live_stream where Visibilite_LiveStream != 'Masquer'";
    
               
                       
        try {
            ste=cnx.prepareStatement(sql);
          
            ResultSet rs=ste.executeQuery();
            
            while(rs.next()){
                Live_Stream ls = new Live_Stream();
                ls.setId(rs.getInt(1));
                ls.setNom(rs.getString(2));
   ls.setPath(rs.getString(3));
   ls.setVisiblte(rs.getString(4));
   ls.setId_defi(rs.getInt(5));
  int x = (rs.getInt(5));
      String sql2 ="SELECT nom_Defi from Defi where id_Defi="+x;
        
            ste=cnx.prepareStatement(sql2);
            ResultSet rs2=ste.executeQuery();
            
            while(rs2.next()){
             
              ls.setNom_defi(rs2.getString(1));
                 infoS.add(ls);
            
            }
            
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return infoS;
    } 
        
        public List<Live_Stream> afficherLiveStreamtable(){
        ObservableList<Live_Stream> infoS = FXCollections.observableArrayList();
        String sql="select * from live_stream ";
    
               
                       
        try {
            ste=cnx.prepareStatement(sql);
          
            ResultSet rs=ste.executeQuery();
            
            while(rs.next()){
                Live_Stream ls = new Live_Stream();
                ls.setId(rs.getInt(1));
                ls.setNom(rs.getString(2));
   ls.setPath(rs.getString(3));
   ls.setVisiblte(rs.getString(4));
   ls.setId_defi(rs.getInt(5));
  int x = (rs.getInt(5));
      String sql2 ="SELECT nom_Defi from Defi where id_Defi="+x;
        
            ste=cnx.prepareStatement(sql2);
            ResultSet rs2=ste.executeQuery();
            
            while(rs2.next()){
             
              ls.setNom_defi(rs2.getString(1));
                 infoS.add(ls);
            
            }
            
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return infoS;
    } 
         
         
         
           public List<Live_Stream> afficherVideoStream(int id){
      ObservableList<Live_Stream> infoS = FXCollections.observableArrayList();
        String sql="select * from live_stream where id_LiveStream ="+id;
    
             //  System.out.println(sql);
                       
        try {
              ste=cnx.prepareStatement(sql);
          
            ResultSet rs=ste.executeQuery();
               while(rs.next()){
                Live_Stream ls = new Live_Stream();
                ls.setId(rs.getInt(1));
                ls.setNom(rs.getString(2));
   ls.setPath(rs.getString(3));
   ls.setVisiblte(rs.getString(4));
   ls.setId_defi(rs.getInt(5));
               
                infoS.add(ls);
            
            }
            
            }
         catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return infoS;
    } 
                 public List<Live_Stream> afficherVideoStreamRecherche(String nom){
      ObservableList<Live_Stream> infoS = FXCollections.observableArrayList();
        String sql="select * from live_stream where nom_LiveStream Like ?";
    
             //  System.out.println(sql);
                       
        try {
              ste=cnx.prepareStatement(sql);
              String nom2 = "%"+nom+"%";
                         ste.setString(1, nom2);
          
            ResultSet rs=ste.executeQuery();
               while(rs.next()){
                Live_Stream ls = new Live_Stream();
                ls.setId(rs.getInt(1));
                ls.setNom(rs.getString(2));
   ls.setPath(rs.getString(3));
   ls.setVisiblte(rs.getString(4));
   ls.setId_defi(rs.getInt(5));
   int x = (rs.getInt(5));
      String sql2 ="SELECT nom_Defi from Defi where id_Defi="+x;
        
            ste=cnx.prepareStatement(sql2);
            ResultSet rs2=ste.executeQuery();
            
            while(rs2.next()){
             
              ls.setNom_defi(rs2.getString(1));
                 infoS.add(ls);
            
            }
               
            
            }
            
            }
         catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return infoS;
    } 
         
      public void ajouterCommentaireLiveStream(String Contenu, int id){
        String sql ="INSERT INTO commentaire(contenu_commentaire,id_livestream ) VALUES (?,?)";
        try {
            ste=cnx.prepareStatement(sql);
            ste.setString(1,Contenu);
            ste.setInt(2, id);
     

            ste.executeUpdate();
            System.out.println("Commenatire Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
      
                public void updateLiveStream(Live_Stream ls, Integer id){

        String sql ="UPDATE live_stream SET Nom_LiveStream=?,Path_LiveStream=?,Visibilite_LiveStream=?,id_defi=? where id_LiveStream=?";
        try {
            ste=cnx.prepareStatement(sql);
            ste.setString(1, ls.getNom());
            ste.setString(2, ls.getPath());
            ste.setString(3, ls.getVisiblte());
            ste.setInt(4, ls.getId_defi());
                        ste.setInt(5, id);


            ste.executeUpdate();
            System.out.println("Live Stream is Update....");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
      
    
      public List<Commentaire_Stream> afficherCommentaireLiveStream(int id){
      ObservableList<Commentaire_Stream> commS = FXCollections.observableArrayList();
        String sql="select  id_commentaire,contenu_commentaire,id_livestream from commentaire where id_livestream ="+id+" ORDER BY date_commentaire DESC LIMIT 1";
    
          //System.out.println(sql);
                       
        try {
              ste=cnx.prepareStatement(sql);
          
            ResultSet rs=ste.executeQuery();
               while(rs.next()){
                Commentaire_Stream cs = new Commentaire_Stream();
                cs.setId(rs.getInt(1));
                cs.setContenu(rs.getString(2));
   cs.setId_live(rs.getInt(3));


               
                commS.add(cs);
            
            }
            
            }
         catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return commS;
    } 
        public ObservableList Defi(){
         
  ObservableList data = FXCollections.observableArrayList();
              String sql ="SELECT nom_Defi from Defi";
        try {
            ste=cnx.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            
            while(rs.next()){
             
              data.add(rs.getString(1));
                
            
            }
         

          }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
          return data;
    }
     public Integer DefiId(String nom){
         
  Integer data = null ;
              String sql ="SELECT id_Defi from Defi where nom_Defi= ?";
        try {
          
            ste=cnx.prepareStatement(sql);
              ste.setString(1, nom);
            ResultSet rs=ste.executeQuery();
            
            while(rs.next()){
             
              data = rs.getInt(1);
                
            
            }
         

          }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
          return data;
    }
     
     
     
     
     
       public void supprimerLiveStream(Integer id){

        String sql ="DELETE from live_stream where id_LiveStream = "+id;
     
        String sql2 ="DELETE from commentaire where id_livestream = "+id;
        try {
            ste=cnx.prepareStatement(sql);
       
        
 

           int row = ste.executeUpdate(sql);
           if (row == 1 ){
            System.out.println("Live Stream est supprimé....");
           }else {
                           System.out.println("Live Stream n'existe pas....");

           }
           
           ste=cnx.prepareStatement(sql2);
            int row2 = ste.executeUpdate(sql2);
           if (row2 == 1 ){
            System.out.println("Commentaire est supprimé....");
           }else {
                           System.out.println("Commentaire n'existe pas....");

           }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
}
