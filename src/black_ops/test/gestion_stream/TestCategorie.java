/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.test.gestion_stream;

import black_ops.Controller.CategorieController;
import black_ops.Entity.Categorie;
import black_ops.config.MaConnexion;

/**
 *
 * @author aZiz
 */
public class TestCategorie {
    
      public static void main(String[] args) {
        // TODO code application logic here
        MaConnexion conn = MaConnexion.getInstance();
        Categorie cat1 = new Categorie("Competition");
        CategorieController categorie = new CategorieController();
    //  categorie.ajouterCategorie(cat1);
     // categorie.updateCategorie(cat1, 3);
//categorie.supprimerCategorie(3);
        System.out.println(categorie.afficherCategorie()); 
        
      
        
    }
}
