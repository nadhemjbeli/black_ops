/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.test.gestion_communaute;

import black_ops.Entity.Messagee;
import black_ops.Controller.MessageControllerr;
import black_ops.config.MaConnexion;

/**
 *
 * @author ASUS
 */
public class TestMessagee {
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MaConnexion m = MaConnexion.getInstance();
        
        Messagee message1 = new Messagee(1,"bonjour, je suis un nouveau membre", 1, 1);
        Messagee message2 = new Messagee(2, "bonsoir, je suis un nouveau membre", 1, 1);
        MessageControllerr ms = new MessageControllerr();
//        ms.ajouterMessage(message1);
//        ms.UpdatePersonne(message2);
//        ms.ajouterMessage(message1);
//        ms.DeleteMessage(2);
        System.out.println(ms.afficherMessages());
        
    }
    
}
