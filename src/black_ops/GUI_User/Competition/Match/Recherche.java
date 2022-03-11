/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI_User.Competition.Match;

import black_ops.Entity.Defi;
import black_ops.Entity.DetailsDefi;
import black_ops.Entity.Equipe;

/**
 *
 * @author medaz
 */
public class Recherche {
     public Equipe equipeA ;
     public Equipe equipeB ; 
        public Defi   defi ;
        public DetailsDefi ddefi;

    public Recherche(Equipe equipeA, Equipe equipeB, Defi defi, DetailsDefi ddefi) {
        this.equipeA = equipeA;
        this.equipeB = equipeB;
        this.defi = defi;
        this.ddefi = ddefi;
    }

    public Recherche() {
    }
        
        

    public Equipe getEquipeA() {
        return equipeA;
    }

    public void setEquipeA(Equipe equipeA) {
        this.equipeA = equipeA;
    }

    public Equipe getEquipeB() {
        return equipeB;
    }

    public void setEquipeB(Equipe equipeB) {
        this.equipeB = equipeB;
    }

    public Defi getDefi() {
        return defi;
    }

    public void setDefi(Defi defi) {
        this.defi = defi;
    }

    public DetailsDefi getDdefi() {
        return ddefi;
    }

    public void setDdefi(DetailsDefi ddefi) {
        this.ddefi = ddefi;
    }

    @Override
    public String toString() {
        return "Recherche{" + "equipeA=" + equipeA + ", equipeB=" + equipeB + ", defi=" + defi + ", ddefi=" + ddefi + '}';
    }
        
    
}
