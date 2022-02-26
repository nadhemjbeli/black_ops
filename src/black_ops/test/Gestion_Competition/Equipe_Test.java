/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.test.Gestion_Competition;

import black_ops.Controller.Equipe_Controller;
import black_ops.Entity.Equipe;
import black_ops.config.MaConnexion;
import java.util.Calendar;

/**
 *
 * @author medaz
 */
public class Equipe_Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         MaConnexion db = MaConnexion.getInstance();
         
        java.util.Date date = Calendar.getInstance().getTime();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime()); 
       Equipe_Controller EquipeC = new Equipe_Controller();
         Equipe  eq1 = new Equipe(5,"Blac_ops","url",sqlDate,5) ;
         Equipe  eq1u = new Equipe(4,"Blac_ops","url",sqlDate,7) ;
         Equipe  e = new Equipe(6) ;
         EquipeC.ajouterEquipe(eq1); // Ajout
        System.out.println(EquipeC.afficherEquipe()); // Lister
       //System.out.println(EquipeC.Select_Detail(e)); // Selectioner par Id
        //EquipeC.UpdateEquipe(e); // modifier
        //EquipeC.DeleteEquipe(e);//Supprimer
    }
    
}
