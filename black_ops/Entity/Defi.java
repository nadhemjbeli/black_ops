/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.Entity;

import java.sql.Date;



public class Defi {
    
    private int id_Defi ;
    private String nom_Defi ;
    private String Description ;
    private String img_Defi ;
    private int prix ;
    private Date date_defi ;
    private String jeu_Defis ;
    private int nbr_equipe_Defi ;
    private String Régle_Defi ;
    private String Recompense_Defi;

    public int getId_Defi() {
        return id_Defi;
    }

    public void setId_Defi(int id_Defi) {
        this.id_Defi = id_Defi;
    }

    public String getNom_Defi() {
        return nom_Defi;
    }

    public void setNom_Defi(String nom_Defi) {
        this.nom_Defi = nom_Defi;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getImg_Defi() {
        return img_Defi;
    }

    public void setImg_Defi(String img_Defi) {
        this.img_Defi = img_Defi;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public Date getDate_defi() {
        return date_defi;
    }

    public void setDate_defi(Date date_defi) {
        this.date_defi = date_defi;
    }

    public String getJeu_Defis() {
        return jeu_Defis;
    }

    public void setJeu_Defis(String jeu_Defis) {
        this.jeu_Defis = jeu_Defis;
    }

    public int getNbr_equipe_Defi() {
        return nbr_equipe_Defi;
    }

    public void setNbr_equipe_Defi(int nbr_equipe_Defi) {
        this.nbr_equipe_Defi = nbr_equipe_Defi;
    }

    public String getRégle_Defi() {
        return Régle_Defi;
    }

    public void setRégle_Defi(String Régle_Defi) {
        this.Régle_Defi = Régle_Defi;
    }

    public String getRecompense_Defi() {
        return Recompense_Defi;
    }

    public void setRecompense_Defi(String Recompense_Defi) {
        this.Recompense_Defi = Recompense_Defi;
    }

    @Override
    public String toString() {
        return "Defi{" + "id_Defi=" + id_Defi + ", nom_Defi=" + nom_Defi + ", Description=" + Description + ", img_Defi=" + img_Defi + ", prix=" + prix + ", date_defi=" + date_defi + ", jeu_Defis=" + jeu_Defis + ", nbr_equipe_Defi=" + nbr_equipe_Defi + ", R\u00e9gle_Defi=" + Régle_Defi + ", Recompense_Defi=" + Recompense_Defi + '}';
    }

    public Defi() {
    }

    public Defi(int id_Defi, String nom_Defi, String Description, String img_Defi, int prix, Date date_defi, String jeu_Defis, int nbr_equipe_Defi, String Régle_Defi, String Recompense_Defi) {
        this.id_Defi = id_Defi;
        this.nom_Defi = nom_Defi;
        this.Description = Description;
        this.img_Defi = img_Defi;
        this.prix = prix;
        this.date_defi = date_defi;
        this.jeu_Defis = jeu_Defis;
        this.nbr_equipe_Defi = nbr_equipe_Defi;
        this.Régle_Defi = Régle_Defi;
        this.Recompense_Defi = Recompense_Defi;
    }

    public Defi(int id_Defi) {
        this.id_Defi = id_Defi;
    }
    
    
    
}
