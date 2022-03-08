/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI.Gestion_Competition.Gestion_Joueur;

import black_ops.Entity.Equipe;
import black_ops.Entity.Joueur;

/**
 *
 * @author medaz
 */
public class Pair {
    Joueur j ;
    Equipe e ;
    String jj,ee ;
    
    public Pair(Joueur j, Equipe e) {
       this.jj =j.getNom_Joueur();
       this.ee =e.getNom_Equipe();
    }

    Pair() {
    }

    @Override
    public String toString() {
        return "Pair{" + "jj=" + jj + ", ee=" + ee + '}';
    }

    public Joueur getJ() {
        return j;
    }

    public void setJ(Joueur j) {
        this.j = j;
    }

    public Equipe getE() {
        return e;
    }

    public void setE(Equipe e) {
        this.e = e;
    }

    public String getJj() {
        return jj;
    }

    public void setJj(String jj) {
        this.jj = jj;
    }

    public String getEe() {
        return ee;
    }

    public void setEe(String ee) {
        this.ee = ee;
    }
    
    
}
