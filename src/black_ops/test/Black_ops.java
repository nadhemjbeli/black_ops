/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.test;

import black_ops.Controller.DefiController;
import black_ops.Controller.Details_DefiController;
import black_ops.Controller.Equipe_Controller;
import black_ops.Controller.JoueurController;
import black_ops.Entity.Defi;
import black_ops.Entity.Details_Defi;
import black_ops.Entity.Equipe;
import black_ops.Entity.Joueur;
import black_ops.config.DataBase;
import static java.time.LocalTime.now;
import java.util.Calendar;



/**
 *
 * @author medaz
 */
public class Black_ops {

    public static void main(String[] args) {
        //test Connextion Base de Donne
          DataBase db = DataBase.getInstance();
         
        java.util.Date date = Calendar.getInstance().getTime();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime()); 
        
        
        // Gestion De Defis    
        DefiController df = new DefiController();
       // Defi Lol = new Defi(999, "valo", "Welcome", "Url", 100, sqlDate , "valorant", 10, "comment", "500");
        //Defi up_lol= new Defi(2, "valo", "Welcome", "Url", 1000, sqlDate , "valorant", 10, "comment", "500");
        //df.ajouterDefi(Lol);
        //df.UpdateDefi(up_lol);
           Defi  Lol = new Defi(2);
       df.DeleteDefi(Lol);
        //System.out.println(df.afficherDefi());
        
        
  //Gestion Equipe
         Equipe_Controller EquipeC = new Equipe_Controller();
         Equipe  eq1 = new Equipe(5,"Blac_ops","url",sqlDate,5) ;
         Equipe  eq1u = new Equipe(4,"Blac_ops","url",sqlDate,7) ;
         Equipe  e = new Equipe(6) ;
         //EquipeC.ajouterEquipe(eq1); // Ajout
        //System.out.println(EquipeC.afficherEquipe()); // Lister
       //System.out.println(EquipeC.Select_Detail(e)); // Selectioner par Id
        //EquipeC.UpdateEquipe(e); // modifier
        //EquipeC.DeleteEquipe(e);//Supprimer
        
        //gestionJoueur
        JoueurController JoueurC = new JoueurController() ;
        Joueur J1 = new Joueur(4,"Aziz","Gold","Fritchou",6) ;
        Joueur J2 = new Joueur (2,"Mokhtar","Gold","Valnloya",6) ;
        //JoueurC.ajouterJoueur(J1);//add
        //System.out.println(JoueurC.afficherJoueur());
        //JoueurC.UpdateJoueur(J1); // update
        //JoueurC.DeleteJoueur(J1);//Delete
        
        
      //gestion detail_defis  
      Details_DefiController D_DefiC = new Details_DefiController();
      Details_Defi match1 = new Details_Defi(3,1,"A",2,"B",1);
      Details_Defi sql_match1 = new Details_Defi(2);

      Details_Defi match2 = new Details_Defi(2,1,"_",2,"", 2);
      
      //D_DefiC.Ajouter_Details_Defi(match2);//add
        //System.out.println(D_DefiC.Select_Detail(sql_match1));// select
      // D_DefiC.DeleteEquipe(match1);//delete
      //System.out.println(D_DefiC.afficher_Details_defi());//afficher
       //D_DefiC.UpdateDefi(match1);//update

    }
    
}
