/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.tests;

import edu.esprit.entities.Personne;
import edu.esprit.services.PersonneService;
import edu.esprit.tools.MaConnexion;

/**
 *
 * @author Fayechi
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MaConnexion m = MaConnexion.getInstance();
        //Personne p =new Personne(9999, "Mostfa", "samir");
      
        Personne p1 = new Personne(1,"Mokhtar","Jaffar");
        Personne p2 = new Personne(2,"Mohamed","Ferchichi");
        PersonneService ps = new PersonneService();
        ps.ajouterPersonne(p2);
        //ps.UpdatePersonne(p1);
        ps.DeletePersonne(p1);
        System.out.println(ps.afficherPersonne());
    }
    
}
