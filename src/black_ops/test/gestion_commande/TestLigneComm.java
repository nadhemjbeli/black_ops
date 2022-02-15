/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.test.gestion_commande;

import black_ops.Controller.LigneCommController;

import black_ops.Entity.LigneComm;
import black_ops.config.MaConnexion;

/**
 *
 * @author aZiz
 */
public class TestLigneComm {
      public static void main(String[] args) {
        // TODO code application logic here
        MaConnexion conn = MaConnexion.getInstance();
      

        
        LigneComm LC1 = new LigneComm(2,35,1,1);
          LigneComm LC2 = new LigneComm(1,44,2,2);
        LigneCommController ligneCommande = new LigneCommController();
        
      ligneCommande.ajouterLigneComm(LC2);
        System.out.println(ligneCommande.afficherLigneComm());
        
    }
}
