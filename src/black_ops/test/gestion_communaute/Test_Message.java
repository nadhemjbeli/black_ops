/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.test.gestion_communaute;

import black_ops.Entity.Messagee;

import black_ops.Controller.Message_Controller;
import black_ops.config.MaConnexion;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class Test_Message {
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MaConnexion m = MaConnexion.getInstance();
        
        Messagee message1 = new Messagee(1,"bonjour, je suis un nouveau membre", 1, 1);
        Messagee message2 = new Messagee(2, "bonsoir, je suis un nouveau membre", 1, 1);
        Message_Controller ms = new Message_Controller();
        System.out.println(ms.afficherMessages());
        List<Messagee> messages = ms.afficherMessages();
        for(int i= 0;i<messages.size();i++){
            System.out.println(messages.get(i).getContenu_message());
        }
        
    }
    
}

