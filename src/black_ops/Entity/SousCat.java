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
public class SousCat {
    private int id,id_cat;

  
    private String nom;
 
    
    public SousCat(){
    }
    public SousCat(String nom, int id_cat ){
        
        this.nom = nom ;
       
        this.id_cat = id_cat;
    }

    @Override
    public String toString() {
        return "Sous_Categoirie{" + "id = " + id + ", nom = " + nom + ", id_categorie = " + id_cat +  '}';
    }

      public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId_cat() {
        return id_cat;
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

  
    
    
    
    
}
