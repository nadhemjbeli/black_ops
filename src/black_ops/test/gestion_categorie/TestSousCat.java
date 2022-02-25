/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.test.gestion_stream;

import black_ops.Controller.SousCatController;

import black_ops.Entity.SousCat;
import black_ops.config.MaConnexion;

/**
 *
 * @author aZiz
 */
public class TestSousCat {
      public static void main(String[] args) {
        // TODO code application logic here
        MaConnexion conn = MaConnexion.getInstance();
      

        
        SousCat sousC1 = new SousCat("Action",1);
          SousCat sousC2 = new SousCat("Aventure",1);
        SousCatController sousCategorie = new SousCatController();
        
      // sousCategorie.ajouterSouscat(sousC2);
       //sousCategorie.updateSouscat(sousC1, 4);
       //sousCategorie.supprimerSouscat(4);
        System.out.println(sousCategorie.afficherSouscat());
        
    }
}
