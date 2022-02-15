/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.test.gestion_communaute;

import black_ops.Entity.VideoUploadee;
import black_ops.Controller.VideoUploadeControllerr;
import black_ops.config.MaConnexion;

/**
 *
 * @author ASUS
 */
public class TestVideoUploadee {
    
    public static void main(String[] args){
        VideoUploadee video1 = new VideoUploadee(1, "video 1", "jeux video de lol","video1.mp4", 1, 1);
        VideoUploadee video2 = new VideoUploadee(1, "video 2", "jeux video de cs","video2.mp4", 1, 1);
        MaConnexion v = MaConnexion.getInstance();
        
        
        VideoUploadeControllerr vs = new VideoUploadeControllerr();
//        vs.ajouterVideo(video1);
//        vs.ajouterVideo(video2);
//        vs.UpdateVideo(video2);
        vs.DeleteVideo(2);
        System.out.println(vs.afficherVideos());
//        String str="2015-03-31";
//        Date date=Date.valueOf(str);//converting string into sql date.
//        System.out.println(date);
    }
    
}
