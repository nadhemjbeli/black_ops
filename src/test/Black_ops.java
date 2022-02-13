/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import Controller.ChampionService;
import Controller.ImageService;
import Controller.JeuService;
import Controller.SkinService;
import Entity.Champion;
import Entity.Image;
import Entity.Jeu;
import Entity.Skin;
import config.MaConnexion;

/**
 *
 * @author jmokh
 */
public class Black_ops {
    
    //Gestion_jeu
    public static void main(String[] args) {
         MaConnexion m = MaConnexion.getInstance();
         Jeu j1 = new Jeu(999,"Valorant","est un jeu de tir à la première personne gratuit développé et publié par Riot Games, pour Microsoft Windows. D’abord teasé sous le nom de code Project A en octobre 2019, le jeu a commencé une période de bêta fermée avec un accès limité le 7 avril 2020, suivie d’une sortie officielle le 2 juin 2020. Le développement du jeu a commencé en 2014. Valorant s’inspire de la série de jeux de tir tactiques Counter-Strike, empruntant plusieurs mécanismes tels que le menu d’achat, les motifs de pulvérisation et l’imprécision lors des déplacements.","https://playvalorant.com/ar-ae/",4);
         //Jeu j2 = new Jeu(999,"cs go ","om aziz")
         JeuService js1 = new JeuService();
        //js1.ajouterJeu(j1);
         //js1.updateJeu(j1);
        js1.deletejeu(j1);
         System.out.println(js1.afficherjeux());
    
    
    //Gestion_image
    
    Image i1=new Image(2,"E:/Image/Astra.jpg",3);
    ImageService IS1 = new ImageService();
   // IS1.ajouterImage(i1);
   //IS1.updateImage(i1);
   IS1.deleteImage(i1);
    System.out.println(IS1.afficherImages());
    
    //Gestion_Champion
    Champion c1 = new Champion(1,"killjoy","champion valorant23","iniator","8","D",3);
    ChampionService cs = new ChampionService();
    //cs.ajouterChampion(c1);
   // cs.updateChampion(c1);
    // cs.deleteChampion(c1);
     System.out.println(cs.afficherChampions());
     
     //Gestion_Skin
     Skin s= new Skin(1,"yyyy",2);
    SkinService ss= new SkinService();
    //ss.ajouterSkin(s);
    //ss.updateSkin(s);
    ss.deleteSkin(s);
    System.out.println(ss.afficherSkins());
    
}}
