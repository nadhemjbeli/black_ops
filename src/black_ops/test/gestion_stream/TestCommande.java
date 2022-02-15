/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.test.gestion_stream;

import black_ops.Controller.CommandeController;
import black_ops.Entity.Commande;
import black_ops.config.MaConnexion;

/**
 *
 * @author aZiz
 */
public class TestCommande {
    
      public static void main(String[] args) {
        // TODO code application logic here
        MaConnexion conn = MaConnexion.getInstance();
        Commande comm1 = new Commande("en cours de traitement",1);
        CommandeController commande = new CommandeController();
   // commande.ajouterCommande(comm1);
      //commande.supprimerCommande(1);
        System.out.println(commande.afficherCommande()); 
        
      
        
    }
}
