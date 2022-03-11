/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.Entity;

/**
 *
 * @author aZiz
 */
public class Commentaire_Stream {
    private int id, id_live;
    private String contenu;

    public Commentaire_Stream() {
    }

    public Commentaire_Stream( String contenu,int id_live) {
        this.id_live = id_live;
        this.contenu = contenu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_live() {
        return id_live;
    }

    public void setId_live(int id_live) {
        this.id_live = id_live;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
    
    
    
    
    
}
