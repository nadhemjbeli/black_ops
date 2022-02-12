/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author jmokh
 */
public class Image {
    private int Id_Image;
    private String Url_Image;
    private int Id_jeu;
    public Image(){}

    public Image(int Id_Image, String Url_Image, int Id_jeu) {
        this.Id_Image = Id_Image;
        this.Url_Image = Url_Image;
        this.Id_jeu = Id_jeu;
    }

    public int getId_Image() {
        return Id_Image;
    }

    public String getUrl_Image() {
        return Url_Image;
    }

    public int getId_jeu() {
        return Id_jeu;
    }

    public void setId_Image(int Id_Image) {
        this.Id_Image = Id_Image;
    }

    public void setUrl_Image(String Url_Image) {
        this.Url_Image = Url_Image;
    }

    public void setId_jeu(int Id_jeu) {
        this.Id_jeu = Id_jeu;
    }

    @Override
    public String toString() {
        return "Image{" + "Id_Image=" + Id_Image + ", Url_Image=" + Url_Image + ", Id_jeu=" + Id_jeu + '}'+'\n';
    }
    
    
}
