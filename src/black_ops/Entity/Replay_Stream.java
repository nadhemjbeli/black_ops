/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.Entity;


import java.sql.Date;
//import java.time.LocalDateTime;

/**
 *
 * @author aZiz
 */
public class Replay_Stream {
    private int id,id_souscat;

  
    private String nom,url,description,Nomsouscat;
   // private LocalDateTime date;
        private Date date;

    public Replay_Stream(){
    }
    public Replay_Stream(String nom,String url,String description, int id_souscat ){
        
        this.nom = nom ;
        this.description = description ;
        this.url = url ;
        this.id_souscat = id_souscat;
    }

    @Override
    public String toString() {
        return "Replay_Stream{" + "id = " + id + ", nom = " + nom + ", Path_Video = " + url + " , Date insertion = " + date + ", Description = " + description + ", id_SousCat = " + id_souscat +  '}';
    }

      public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId_souscat() {
        return id_souscat;
    }

    public void setId_souscat(int id_souscat) {
        this.id_souscat = id_souscat;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    /**
     *
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    public String getNomsouscat() {
        return Nomsouscat;
    }

    public void setNomsouscat(String Nomsouscat) {
        this.Nomsouscat = Nomsouscat;
    }

    
    
    
    
    
}
