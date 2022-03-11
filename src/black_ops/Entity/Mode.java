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
public class Mode {
    private int id, dark_mode ,  light_mode;

    public Mode() {
    }

    public Mode( int dark_mode, int light_mode) {
      
        this.dark_mode = dark_mode;
        this.light_mode = light_mode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDark_mode() {
        return dark_mode;
    }

    public void setDark_mode(int dark_mode) {
        this.dark_mode = dark_mode;
    }

    public int getLight_mode() {
        return light_mode;
    }

    public void setLight_mode(int light_mode) {
        this.light_mode = light_mode;
    }
    
    
    
    
    
    
    
    
}
