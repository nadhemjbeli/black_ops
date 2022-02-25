/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.test.gestion_stream;

import black_ops.Controller.ReplayStreamController;

import black_ops.Entity.Replay_Stream;
import black_ops.config.MaConnexion;

/**
 *
 * @author aZiz
 */
public class TestReplayStream {
      public static void main(String[] args) {
        // TODO code application logic here
        MaConnexion conn = MaConnexion.getInstance();
      

        
        Replay_Stream replay1 = new Replay_Stream("Fnatic Vs SMG", "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/qKeui0SrkoQ\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>","FNATIC vs TEAM SMG - ELIMINATION - Dota 2 DPC 2022 SEA REGIONAL FINALS \n" +
"Dota Pro Circuit DPC SEA League Winter Tour by BeyondTheSummit Dota 2 Highlights 2022 Tournament - LB Round 1",1);
          Replay_Stream replay2 = new Replay_Stream("Fnatic Vs SMG", "src= https://www.youtube.com/embed/qKeui0SrkoQ","FNATIC vs TEAM SMG - ELIMINATION - Dota 2 DPC 2022 SEA REGIONAL FINALS",1);
        
        ReplayStreamController replaystream = new ReplayStreamController();
        
       //replaystream.ajouterReplay(replay2);
     //  replaystream.updateReplay(replay2, 4);
       // replaystream.supprimerReplay(3);
        System.out.println(replaystream.afficherReplay());
        
    }
}
