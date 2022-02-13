/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.Controller;

import black_ops.Entity.VideoUploade;
import black_ops.config.MaConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class VideoUploadeController {
        private Connection mc;
        private PreparedStatement ste;

    public VideoUploadeController() {
        mc=MaConnexion.getInstance().getCnx();
    }
    
    public void ajouterVideo(VideoUploade video){
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
    
    public List<VideoUploade> afficherVideos(){
        List<VideoUploade> video = new ArrayList<>();
        String sql="select * from video_uploade";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                VideoUploade v = new VideoUploade();
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
    
    public void UpdateVideo(VideoUploade v){
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
    
    public void DeleteVideo(VideoUploade video ){
        String sql1 ="DELETE FROM video_uploade WHERE id_vdeo = ?";
        try{
        ste=mc.prepareStatement(sql1);
        ste.setInt(1, video.getId_vdeo());
        ste.executeUpdate();
        System.out.println("video supprime");
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
