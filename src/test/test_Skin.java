/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import Entity.Skin;
import Controller.SkinService;
import config.MaConnexion;

/**
 *
 * @author jmokh
 */
public class test_Skin {
       public static void main(String[] args) {
    MaConnexion m = MaConnexion.getInstance();
    Skin s= new Skin(1,"yyyy",2);
    SkinService ss= new SkinService();
    //ss.ajouterSkin(s);
    //ss.updateSkin(s);
    ss.deleteSkin(s);
    System.out.println(ss.afficherSkins());
}}
