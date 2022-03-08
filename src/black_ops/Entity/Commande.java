/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.Entity;

import java.sql.Date;

/**
 *
 * @author aZiz
 */
public class Commande {

    private int id,id_cl;
    private String etat;
    private Date date;

    public Commande(int id, int id_cl, String etat, Date date) {
        this.id = id;
        this.id_cl = id_cl;
        this.etat = etat;
        this.date = date;
    }

    public Commande(int id) {
        this.id = id;
    }
    

    public Commande(String etat,int id_cl) {
     
        this.etat = etat;
        this.id_cl = id_cl;
      

    }

    public Commande(int id_cl, String etat) {
        this.id_cl = id_cl;
        this.etat = etat;
    }
    

    public Commande() {
    }

    @Override
    public String toString() {
        return "Commande{" + " Id = "+ id + ", Date = " + date + ", Etat_Commande = " + etat + ", Id_Client = " + id_cl +'}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_cl() {
        return id_cl;
    }

    public void setId_cl(int id_cl) {
        this.id_cl = id_cl;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

   

   
    
   

}
