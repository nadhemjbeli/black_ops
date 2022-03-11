/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.test.Gestion_utilisateur;
import black_ops.config.MaConnexion;
import static java.time.LocalTime.now;
import java.util.Calendar;
import black_ops.Entity.Contact;
import black_ops.Controller.Contact_Controller;
import black_ops.config.MaConnexion;

/**
 *
 * @author Khalil
 */
public class test_conact {
     public static void main(String[] args) {
        MaConnexion m=MaConnexion.getInstance();
        java.util.Date date = Calendar.getInstance().getTime();
        java.sql.Timestamp sqlDate = new java.sql.Timestamp(date.getTime());
        Contact A=new Contact(1,"kamya","khalil.khalil@gmail.com","ok",sqlDate);
        Contact_Controller AS=new Contact_Controller();
        //AS.ajoutercontact(A);
        //AS.Updatecontact(A);
        //System.out.println(AS.affichercontact());
        AS.Deletecontact(A);
     }
}
     
