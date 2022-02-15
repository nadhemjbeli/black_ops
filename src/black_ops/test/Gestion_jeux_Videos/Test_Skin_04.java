/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.test.Gestion_jeux_Videos;

import black_ops.Controller.SkinController;
import black_ops.Entity.Skin;
import black_ops.config.MaConnexion;



/**
 *
 * @author jmokh
 */
public class Test_Skin_04 {
       public static void main(String[] args) {
    MaConnexion m = MaConnexion.getInstance();
    Skin s= new Skin(1,"yyyy",2);
    SkinController ss= new SkinController();
    //ss.ajouterSkin(s);
    //ss.updateSkin(s);
    ss.deleteSkin(s);
    System.out.println(ss.afficherSkins());
}}
