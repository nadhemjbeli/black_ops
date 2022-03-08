/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.Controller;

import black_ops.Entity.Client;
import black_ops.Entity.VideoUploadee;
import black_ops.GUI.gestion_communaute.classes.VideoClient;
import black_ops.config.MaConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ASUS
 */
public class Video_UploadeController {
        private Connection mc;
        private PreparedStatement ste;

    public Video_UploadeController() {
        mc=MaConnexion.getInstance().getCnx();
    }
    
    public void ajouterVideo(VideoUploadee video){
        String sql ="INSERT INTO `video_uploade`(`nom_video`, `description_video`, `url_video`, `id_cl`, `id_souscat`) VALUES (?, ?, ?, ?, ?)";
        try {
            ste=mc.prepareStatement(sql);
            ste.setString(1, video.getNom_video());
            ste.setString(2, video.getDescription_video());
            ste.setString(3, video.getUrl_video());
            ste.setInt(4, video.getId_cl());
            ste.setInt(5, video.getId_souscat());
            ste.executeUpdate();
            System.out.println("Video Ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public ObservableList<VideoUploadee> afficherVideos(){
        ObservableList<VideoUploadee> video;
            video = FXCollections.observableArrayList();
        String sql="select * from video_uploade";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                VideoUploadee v = new VideoUploadee();
                v.setId_vdeo(rs.getInt("id_vdeo"));
                v.setNom_video(rs.getString("nom_video"));
                v.setDate_video(rs.getDate("date_video"));
                v.setDescription_video(rs.getString("description_video"));
                v.setUrl_video(rs.getString("url_video"));
                v.setId_souscat(rs.getInt("id_souscat"));
                v.setId_cl(rs.getInt("id_cl"));

                video.add(v);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return video;
    }
    
    public void UpdateVideo(VideoUploadee v){
        String sql ="UPDATE video_uploade SET nom_video=?,description_video=?,"
                + "url_video=? WHERE id_vdeo= ? ";
        try {
            ste=mc.prepareStatement(sql);
            ste.setString(1, v.getNom_video());
            ste.setString(2, v.getDescription_video());
            ste.setString(3, v.getUrl_video());
            ste.setInt(4,v.getId_vdeo());
            ste.executeUpdate();
            System.out.println("video modifier");
            ste.close();
      
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public void DeleteVideo(int v){
        String sql1 ="DELETE FROM video_uploade WHERE id_vdeo = ?";
        try{
        ste=mc.prepareStatement(sql1);
        ste.setInt(1, v);
        ste.executeUpdate();
        System.out.println("video supprime");
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public ObservableList<VideoClient> afficherSousCatMessagesEtClients(int id_sc){
        
        String sql="select * from client cl"
                + " inner join Video_uploade vid on cl.id_cl=vid.id_cl"
                + " inner join sous_categorie sc on sc.id_souscat = vid.id_souscat"
                + " where sc.id_souscat = "+id_sc+" "
                + "order by vid.date_video;";
        ObservableList<VideoClient> video_clients;
        video_clients = FXCollections.observableArrayList();
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                VideoClient vdcl = new VideoClient();
                VideoUploadee video = new VideoUploadee();
                video.setId_vdeo(rs.getInt("id_vdeo"));
                video.setNom_video(rs.getString("nom_video"));
                video.setDescription_video(rs.getString("description_video"));
                video.setDate_video(rs.getDate("date_video"));
                video.setId_cl(rs.getInt("vid.id_cl"));
                video.setId_souscat(rs.getInt("id_souscat"));
                vdcl.setVideo(video);
                
                Client client = new Client();
                client.setId_Cl(rs.getInt("cl.id_Cl"));
                client.setPseaudo_Cl(rs.getString("Pseaudo_Cl"));
                client.setDateNaissance_Cl(rs.getDate("DateNaissance_Cl"));
                client.setMail_Cl(rs.getString("mail_Cl"));
                client.setPhoto_Cl(rs.getString("Photo_Cl"));
                vdcl.setClient(client);
                
                video_clients.add(vdcl);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return video_clients;
    }
    
    
    
}
