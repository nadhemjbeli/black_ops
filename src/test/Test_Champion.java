/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import Entity.Champion;
import Controller.ChampionController;
import config.MaConnexion;

/**
 *
 * @author jmokh
 */
public class Test_Champion {
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