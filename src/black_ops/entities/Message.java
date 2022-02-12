/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.entities;

import java.sql.Timestamp;

/**
 *
 * @author ASUS
 */
public class Message {
    private int id_message;
    private Timestamp date_message;
    private String contenu_message;
    private int id_cl, id_sous_cat;

    public Message() {
    }

    public Message(int id_message, String contenu_message, int id_cl, int id_sous_cat) {
        this.id_message = id_message;
        this.contenu_message = contenu_message;
        this.id_cl = id_cl;
        this.id_sous_cat = id_sous_cat;
    }

    public Message(String contenu_message, int id_cl, int id_sous_cat) {
        this.contenu_message = contenu_message;
        this.id_cl = id_cl;
        this.id_sous_cat = id_sous_cat;
    }

    public int getId_message() {
        return id_message;
    }

    public void setId_message(int id_message) {
        this.id_message = id_message;
    }

    public Timestamp getDate_message() {
        return date_message;
    }

    public void setDate_message(Timestamp date_message) {
        this.date_message = date_message;
    }

    public String getContenu_message() {
        return contenu_message;
    }

    public void setContenu_message(String contenu_message) {
        this.contenu_message = contenu_message;
    }

    public int getId_cl() {
        return id_cl;
    }

    public void setId_cl(int id_cl) {
        this.id_cl = id_cl;
    }

    public int getId_sous_cat() {
        return id_sous_cat;
    }

    public void setId_sous_cat(int id_sous_cat) {
        this.id_sous_cat = id_sous_cat;
    }

    @Override
    public String toString() {
        return '\n' + "message{" + "id_message=" + id_message + ", date_message=" + date_message + ", contenu_message=" + contenu_message + ", id_cl=" + id_cl + ", id_sous_cat=" + id_sous_cat + '}';
    }
    
    
    
    
    
}
