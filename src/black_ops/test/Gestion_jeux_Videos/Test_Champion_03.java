/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.test.Gestion_jeux_Videos;

import black_ops.Controller.ChampionController;
import black_ops.Entity.Champion;
import black_ops.config.MaConnexion;



/**
 *
 * @author jmokh
 */
public class Test_Champion_03 {
     public static void main(String[] args) {
    MaConnexion m = MaConnexion.getInstance();
    Champion c1 = new Champion(1,"killjoy","champion valorant23","iniator","8","D",3);
    ChampionController cs = new ChampionController();
    //cs.ajouterChampion(c1);
   // cs.updateChampion(c1);
    // cs.deleteChampion(c1);
     System.out.println(cs.afficherChampions());
}
}