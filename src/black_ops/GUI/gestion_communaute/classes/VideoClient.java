/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI.gestion_communaute.classes;

import black_ops.Entity.Client;
import black_ops.Entity.VideoUploadee;

/**
 *
 * @author ASUS
 */
public class VideoClient {
    VideoUploadee video;
    Client client;

    public VideoClient() {
    }

    public VideoClient(VideoUploadee video, Client client) {
        this.video = video;
        this.client = client;
    }

    public VideoUploadee getVideo() {
        return video;
    }

    public void setVideo(VideoUploadee video) {
        this.video = video;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    
    
    
}
