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
public class LigneComm {
    private int id,id_defi,id_commande,prix,quantite;

  
  
 
    
    public LigneComm(){
    }
    public LigneComm(int quantite,int prix, int id_defi, int id_commande ){
        
        this.quantite = quantite ;
        this.prix = prix ;
         this.id_defi = id_defi ;
          this.id_commande = id_commande ;
     
    }

    @Override
    public String toString() {
        return "Ligne de Commande{" + "id = " + id + ", Quantite = " + quantite + ", Prix = " + prix + ", Id Defi = " + id_defi + ", Id Commande = " + id_commande +  '}';
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

    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    

  
    
    
    
    
}
