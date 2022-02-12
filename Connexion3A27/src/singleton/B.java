/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package singleton;

/**
 *
 * @author Fayechi
 */
public class B {
    private int x;
    public static B b;

    private B() {
        System.out.println("je suis le constructer de B");
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
    
    public static B getInstance(){
        if(b==null)
            b= new B();
        return b;
    }
}
