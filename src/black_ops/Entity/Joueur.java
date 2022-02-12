
package black_ops.Entity;


public class Joueur {
    private int id_Joueur ;
    private String nom_Joueur ;
    private String rang_Joueur ;
    private String Pseaudo_Joueur;
    private int id_equipe ;

    public Joueur(int id_Joueur, String nom_Joueur, String rang_Joueur, String Pseaudo_Joueur, int id_equipe) {
        this.id_Joueur = id_Joueur;
        this.nom_Joueur = nom_Joueur;
        this.rang_Joueur = rang_Joueur;
        this.Pseaudo_Joueur = Pseaudo_Joueur;
        this.id_equipe = id_equipe;
    }

    public Joueur() {
    }

    public int getId_Joueur() {
        return id_Joueur;
    }

    public void setId_Joueur(int id_Joueur) {
        this.id_Joueur = id_Joueur;
    }

    public String getNom_Joueur() {
        return nom_Joueur;
    }

    public void setNom_Joueur(String nom_Joueur) {
        this.nom_Joueur = nom_Joueur;
    }

    public String getRang_Joueur() {
        return rang_Joueur;
    }

    public void setRang_Joueur(String rang_Joueur) {
        this.rang_Joueur = rang_Joueur;
    }

    public String getPseaudo_Joueur() {
        return Pseaudo_Joueur;
    }

    public void setPseaudo_Joueur(String Pseaudo_Joueur) {
        this.Pseaudo_Joueur = Pseaudo_Joueur;
    }

    public int getId_equipe() {
        return id_equipe;
    }

    public void setId_equipe(int id_equipe) {
        this.id_equipe = id_equipe;
    }

    @Override
    public String toString() {
        return "Joueur{" + "id_Joueur=" + id_Joueur + ", nom_Joueur=" + nom_Joueur + ", rang_Joueur=" + rang_Joueur + ", Pseaudo_Joueur=" + Pseaudo_Joueur + ", id_equipe=" + id_equipe + '}';
    }

    public Joueur(int id_Joueur) {
        this.id_Joueur = id_Joueur;
    }
    
    
}
