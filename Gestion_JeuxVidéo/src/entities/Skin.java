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
public class Skin {
    private int Id_skin;
    private String image_skin;
    private int id_champ;
    
    public Skin(){}

    public Skin(int Id_skin, String image_skin, int id_champ) {
        this.Id_skin = Id_skin;
        this.image_skin = image_skin;
        this.id_champ = id_champ;
    }

    public int getId_skin() {
        return Id_skin;
    }

    public String getImage_skin() {
        return image_skin;
    }

    public int getId_champ() {
        return id_champ;
    }

    public void setId_skin(int Id_skin) {
        this.Id_skin = Id_skin;
    }

    public void setImage_skin(String image_skin) {
        this.image_skin = image_skin;
    }

    public void setId_champ(int id_champ) {
        this.id_champ = id_champ;
    }

    @Override
    public String toString() {
        return "Skin{" + "Id_skin=" + Id_skin + ", image_skin=" + image_skin + ", id_champ=" + id_champ + '}'+'\n';
    }
    
}
