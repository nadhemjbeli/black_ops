/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.Entity;

/**
 *
 * @author jmokh
 */
public class Jeu {
    private int Id_Jeu;
    private String Nom ;
    private String description ;
    private String Url;
    private int id_souscat;
public Jeu (){}
    public Jeu(int Id_Jeu,String Nom, String description, String Url, int id_souscat) {
        this.Id_Jeu=Id_Jeu;
        this.Nom = Nom;
        this.description = description;
        this.Url = Url;
        this.id_souscat = id_souscat;
    }
     public Jeu(int Id_Jeu){  
      this.Id_Jeu=Id_Jeu;
     }

    public int getId_Jeu() {
        return Id_Jeu;
    }

    public String getNom() {
        return Nom;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return Url;
    }

    public int getId_souscat() {
        return id_souscat;
    }

    public void setId_Jeu(int Id_Jeu) {
        this.Id_Jeu = Id_Jeu;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String Url) {
        this.Url = Url;
    }

    public void setId_souscat(int id_souscat) {
        this.id_souscat = id_souscat;
    }

    @Override
    public String toString() {
        return "Jeu{" + "Id_Jeu=" + Id_Jeu + ", Nom=" + Nom + ", description=" + description + ", Url=" + Url + ", id_souscat=" + id_souscat + '}'+'\n';
    }
    
    
}
