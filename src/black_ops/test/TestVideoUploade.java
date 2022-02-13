/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.test;

import black_ops.Entity.VideoUploade;
import black_ops.Controller.MessageController;
import black_ops.Controller.VideoUploadeController;
import black_ops.config.MaConnexion;

/**
 *
 * @author ASUS
 */
public class TestVideoUploade {
    
    public static void main(String[] args){
        VideoUploade video1 = new VideoUploade("video 1", "jeux video de lol","video1.mp4", 1, 1);
        VideoUploade video2 = new VideoUploade(2, "video 1", "jeux video de lol","video1.mp4", 1, 1);
        MaConnexion m = MaConnexion.getInstance();
        
        
        VideoUploadeController ms = new VideoUploadeController();
        ms.ajouterVideo(video1);
    }
    
}
