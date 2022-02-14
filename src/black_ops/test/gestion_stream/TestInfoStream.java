/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.test.gestion_stream;

import black_ops.Controller.InfoStreamController;
import black_ops.Entity.Info_Stream;
import black_ops.config.MaConnexion;

/**
 *
 * @author aZiz
 */
public class TestInfoStream {
    
      public static void main(String[] args) {
        // TODO code application logic here
        MaConnexion conn = MaConnexion.getInstance();
        Info_Stream info1 = new Info_Stream(0,"twitch studio beta","E:\\Black Ops\\twitch.png","Lancer le live n'a jamais été aussi facile.",1);
        InfoStreamController infostream = new InfoStreamController();
      // infostream.updateInfoStream(info1,1);
        //infostream.ajouterInfoStream(info1);
       // infostream.supprimerInfoStream(info1);
        //infostream.supprimerInfoStream(3);

        System.out.println(infostream.afficherInfoStream()); 
        
      
        
    }
}
