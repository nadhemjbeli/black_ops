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
    
}
