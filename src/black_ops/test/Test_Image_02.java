/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.test;

import black_ops.Controller.ImageController;
import black_ops.Entity.Image;
import black_ops.config.MaConnexion;



/**
 *
 * @author jmokh
 */
public class Test_Image_02 {
    public static void main(String[] args) {
    MaConnexion m = MaConnexion.getInstance();
    Image i1=new Image(2,"E:/Image/Astra.jpg",3);
    ImageController IS1 = new ImageController();
   // IS1.ajouterImage(i1);
   //IS1.updateImage(i1);
   IS1.deleteImage(i1);
    System.out.println(IS1.afficherImages());
}
}