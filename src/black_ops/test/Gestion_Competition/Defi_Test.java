/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.test.Gestion_Competition;

import black_ops.Controller.DefiController;
import black_ops.Entity.Defi;
import black_ops.config.MaConnexion;
import java.util.Calendar;

/**
 *
 * @author medaz
 */
public class Defi_Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
           MaConnexion db = MaConnexion.getInstance();
         
        java.util.Date date = Calendar.getInstance().getTime();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime()); 
        DefiController df = new DefiController();
        Defi Lol = new Defi(999, "valo", "Welcome", "Url", 100, sqlDate , "valorant", 10, "comment", "500");
        //df.ajouterDefi(Lol);
        //df.UpdateDefi(up_lol);
        //Defi  Lol = new Defi(2);
        //df.DeleteDefi(Lol);
        System.out.println(df.afficherDefi());
    }
    
}
