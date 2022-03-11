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
public class Live_Stream {
    
    private int id,id_defi;
    private String nom,path,visiblte,nom_defi;
    

    public Live_Stream() {
    }

    public Live_Stream( String nom, String path, String visiblte,int id_defi) {
        this.id_defi = id_defi;
        this.nom = nom;
        this.path = path;
        this.visiblte = visiblte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_defi() {
        return id_defi;
    }

    public void setId_defi(int id_defi) {
        this.id_defi = id_defi;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getVisiblte() {
        return visiblte;
    }

    public void setVisiblte(String visiblte) {
        this.visiblte = visiblte;
    }

    public String getNom_defi() {
        return nom_defi;
    }

    public void setNom_defi(String nom_defi) {
        this.nom_defi = nom_defi;
    }
    
    
}
