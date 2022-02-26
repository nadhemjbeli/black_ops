/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.test.Gestion_utilisateur;

import black_ops.Entity.Client;
import black_ops.Entity.Contact;
import black_ops.Controller.Client_Controller;
import black_ops.Controller.Contact_Controller;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import black_ops.config.MaConnexion;

/**
 *
 * @author Khalil
 */
public class test_client {
     public static void main(String[] args) {
        String str="2025-06-30";
        Date date=Date.valueOf(str);//converting string into sql date.
        
        Client A=new Client(4,"khalil","khemiri","ok","okk",date,"barra",5);
        Client_Controller AS=new Client_Controller();
        //AS.ajouterclient(A);
        //AS.Updateclient(A);
        //System.out.println(AS.afficherclient());
        //AS.Deletecontact(A);
}
}