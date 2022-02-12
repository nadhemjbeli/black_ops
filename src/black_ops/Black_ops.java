/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops;

import black_ops.entities.Message;
import black_ops.services.MessageService;
import black_ops.tools.MaConnexion;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author ASUS
 */
public class Black_ops {
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MaConnexion m = MaConnexion.getInstance();
        
        Message message1 = new Message("bonjour, je suis un nouveau membre", 1, 1);
        Message message2 = new Message(2, "bonsoir, je suis un nouveau membre", 1, 1);
        MessageService ms = new MessageService();
//        ms.ajouterMessage(message1);
//        ms.UpdatePersonne(message2);
//        ms.ajouterMessage(message1);
//        ms.DeleteMessage(message2);
        System.out.println(ms.afficherMessages());
        
    }
    
}
