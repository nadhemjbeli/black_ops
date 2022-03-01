/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.Entity;

/**
 *
 * @author aZiz
 */
public class Info_Stream {

    private int id, id_souscat;
    private String nom,image,nomsouscat, description;

    public Info_Stream(int id,String nom, String image, String description, int id_souscat) {
        this.id = id;
        this.nom = nom;
        this.image = image;
        this.description = description;
        this.id_souscat = id_souscat;

    }

    public Info_Stream() {
    }

    @Override
    public String toString() {
        return "Info_Stream{" + " Id = "+ id + ", nom = " + nom + ", Path_image = " + image + ", description = " + description + ", id_SousCat = " + nomsouscat + '}';
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
    
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNomsouscat() {
        return nomsouscat;
    }

    public void setNomsouscat(String nomsouscat) {
        this.nomsouscat = nomsouscat;
    }

}
