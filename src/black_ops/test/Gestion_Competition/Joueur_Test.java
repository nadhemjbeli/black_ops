/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.test.Gestion_Competition;

import black_ops.Controller.Joueur_Controller;
import black_ops.Entity.Joueur;
import black_ops.config.MaConnexion;

/**
 *
 * @author medaz
 */
public class Joueur_Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                   MaConnexion db = MaConnexion.getInstance();

         Joueur_Controller JoueurC = new Joueur_Controller() ;
        Joueur J1 = new Joueur(4,"Aziz","Gold","Fritchou",6) ;
        Joueur J2 = new Joueur (2,"Mokhtar","Gold","Valnloya",6) ;
        //JoueurC.ajouterJoueur(J1);//add
        System.out.println(JoueurC.afficherJoueur());
        //JoueurC.UpdateJoueur(J1); // update
        //JoueurC.DeleteJoueur(J1);//Delete
    }
    
}
