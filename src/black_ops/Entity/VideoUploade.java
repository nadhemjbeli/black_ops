/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.Entity;
import java.sql.Date;
/**
 *
 * @author ASUS
 */
public class VideoUploade {
    private int id_vdeo;
    private String nom_video;
    private Date date_video;
    private String description_video, url_video;
    private int id_souscat, id_cl;

    public VideoUploade() {
    }

    public VideoUploade(int id_vdeo) {
        this.id_vdeo = id_vdeo;
    }
    
    

    public VideoUploade(int id_vdeo, String nom_video, String description_video, String url_video, int id_souscat, int id_cl) {
        this.id_vdeo = id_vdeo;
        this.nom_video = nom_video;
        this.description_video = description_video;
        this.url_video = url_video;
        this.id_souscat = id_souscat;
        this.id_cl = id_cl;
    }

    public VideoUploade(String nom_video, String description_video, String url_video, int id_souscat, int id_cl) {
        this.nom_video = nom_video;
        this.description_video = description_video;
        this.url_video = url_video;
        this.id_souscat = id_souscat;
        this.id_cl = id_cl;
    }

    public int getId_vdeo() {
        return id_vdeo;
    }

    public void setId_vdeo(int id_vdeo) {
        this.id_vdeo = id_vdeo;
    }

    public String getNom_video() {
        return nom_video;
    }

    public void setNom_video(String nom_video) {
        this.nom_video = nom_video;
    }

    public Date getDate_video() {
        return date_video;
    }

    public void setDate_video(Date date_video) {
        this.date_video = date_video;
    }

    public String getDescription_video() {
        return description_video;
    }

    public void setDescription_video(String description_video) {
        this.description_video = description_video;
    }

    public String getUrl_video() {
        return url_video;
    }

    public void setUrl_video(String url_video) {
        this.url_video = url_video;
    }

    public int getId_souscat() {
        return id_souscat;
    }

    public void setId_souscat(int id_souscat) {
        this.id_souscat = id_souscat;
    }

    public int getId_cl() {
        return id_cl;
    }

    public void setId_cl(int id_cl) {
        this.id_cl = id_cl;
    }

    @Override
    public String toString() {
        return "\n"+"VideoUploade{" + "id_vdeo=" + id_vdeo + ", nom_video=" + nom_video + ", date_video=" + date_video + ", description_video=" + description_video + ", url_video=" + url_video + ", id_souscat=" + id_souscat + ", id_cl=" + id_cl + '}';
    }
    
    
    
    
    
    
    
}
