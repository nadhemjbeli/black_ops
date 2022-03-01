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
    Champion c1 = new Champion(39,"kj","champion","iniator","4789","D",18);
    ChampionController cs = new ChampionController();
    //cs.ajouterChampion(c1);
    cs.updateChampion(c1);
     //cs.deleteChampion(c1);
     //cs.RechercherChampion("kj");
     //System.out.println(cs.afficherChampions());
}
}