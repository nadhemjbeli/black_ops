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
public class Champion {
    private int Id_Champ ;
    private String Nom_Champ;
    private String description_Champ;
    private String Role_Champ;
    private String Difficulte_Champ;
    private String Image_Champ;
    private int Id_jeu ;
    public Champion(){}

    public Champion(int Id_Champ, String Nom_Champ, String description_Champ, String Role_Champ, String Difficulte_Champ, String Image_Champ, int Id_jeu) {
        this.Id_Champ = Id_Champ;
        this.Nom_Champ = Nom_Champ;
        this.description_Champ = description_Champ;
        this.Role_Champ = Role_Champ;
        this.Difficulte_Champ = Difficulte_Champ;
        this.Image_Champ = Image_Champ;
        this.Id_jeu = Id_jeu;
    }
      
    public Champion(int Id_Champ)
    { this.Id_Champ = Id_Champ;
    }

    public int getId_Champ() {
        return Id_Champ;
    }

    public String getNom_Champ() {
        return Nom_Champ;
    }

    public String getDescription_Champ() {
        return description_Champ;
    }

    public String getRole_Champ() {
        return Role_Champ;
    }

    public String getDifficulte_Champ() {
        return Difficulte_Champ;
    }

    public String getImage_Champ() {
        return Image_Champ;
    }

    public int getId_jeu() {
        return Id_jeu;
    }

    public void setId_Champ(int Id_Champ) {
        this.Id_Champ = Id_Champ;
    }

    public void setNom_Champ(String Nom_Champ) {
        this.Nom_Champ = Nom_Champ;
    }

    public void setDescription_Champ(String description_Champ) {
        this.description_Champ = description_Champ;
    }

    public void setRole_Champ(String Role_Champ) {
        this.Role_Champ = Role_Champ;
    }

    public void setDifficulte_Champ(String Difficulte_Champ) {
        this.Difficulte_Champ = Difficulte_Champ;
    }

    public void setImage_Champ(String Image_Champ) {
        this.Image_Champ = Image_Champ;
    }

    public void setId_jeu(int Id_jeu) {
        this.Id_jeu = Id_jeu;
    }

    @Override
    public String toString() {
        return "Champion{" + "Id_Champ=" + Id_Champ + ", Nom_Champ=" + Nom_Champ + ", description_Champ=" + description_Champ + ", Role_Champ=" + Role_Champ + ", Difficulte_Champ=" + Difficulte_Champ + ", Image_Champ=" + Image_Champ + ", Id_jeu=" + Id_jeu + '}'+'\n';
    }
    
    
    
}
