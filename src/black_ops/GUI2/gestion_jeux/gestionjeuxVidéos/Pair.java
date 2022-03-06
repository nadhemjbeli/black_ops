/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI2.gestion_jeux.gestionjeuxVidéos;

import black_ops.Entity.Image;
import black_ops.Entity.Jeu;

/**
 *
 * @author jmokh
 */
public class Pair {
     private Jeu Id_Jeu;
    private Jeu Nom ;
    private Jeu description ;
    private Jeu Url;
    private Jeu id_souscat;
    private Image Id_Image;
    private Image Url_Image;

    public Pair(Jeu Id_Jeu, Jeu Nom, Jeu description, Jeu Url, Jeu id_souscat, Image Id_Image, Image Url_Image) {
        this.Id_Jeu = Id_Jeu;
        this.Nom = Nom;
        this.description = description;
        this.Url = Url;
        this.id_souscat = id_souscat;
        this.Id_Image = Id_Image;
        this.Url_Image = Url_Image;
    }

    public Pair() {
    }

    public Jeu getId_Jeu() {
        return Id_Jeu;
    }

    public void setId_Jeu(Jeu Id_Jeu) {
        this.Id_Jeu = Id_Jeu;
    }

    public Jeu getNom() {
        return Nom;
    }

    public void setNom(Jeu Nom) {
        this.Nom = Nom;
    }

    public Jeu getDescription() {
        return description;
    }

    public void setDescription(Jeu description) {
        this.description = description;
    }

    public Jeu getUrl() {
        return Url;
    }

    public void setUrl(Jeu Url) {
        this.Url = Url;
    }

    public Jeu getId_souscat() {
        return id_souscat;
    }

    public void setId_souscat(Jeu id_souscat) {
        this.id_souscat = id_souscat;
    }

    public Image getId_Image() {
        return Id_Image;
    }

    public void setId_Image(Image Id_Image) {
        this.Id_Image = Id_Image;
    }

    public Image getUrl_Image() {
        return Url_Image;
    }

    public void setUrl_Image(Image Url_Image) {
        this.Url_Image = Url_Image;
    }

    @Override
    public String toString() {
        return "Pair{" + "Id_Jeu=" + Id_Jeu + ", Nom=" + Nom + ", description=" + description + ", Url=" + Url + ", id_souscat=" + id_souscat + ", Id_Image=" + Id_Image + ", Url_Image=" + Url_Image + '}';
    }

   
   
    
    
}
