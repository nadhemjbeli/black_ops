/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI_User.Jeux.gestionjeuxVidéos;

import black_ops.Entity.Champion;
import black_ops.Entity.Jeu;
import black_ops.GUI_User.Jeux.gestionchamps.MyListener;
import black_ops.config.MaConnexion;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author jmokh
 */
public class ItemjeuController  {

    @FXML
    private Label jeuLabel;
    @FXML
    private ImageView imgjeu;
    @FXML
    private Label GenreJeu;
    private Jeu jeu;
    Connection mc;
    PreparedStatement ste;
     private MyListener2 mylistener;
    /**
     * Initializes the controller class.
     */
    

    public void setData(Jeu jeu,MyListener2 mylistener) 
    {this.jeu=jeu;
    this.mylistener=mylistener;
    String nomj=jeu.getNom();
   int idimg=0;
    
//    this.mylistener=mylistener;
        jeuLabel.setText(nomj);
         int id =0;
         int idj=0;
         String Urlimg="";
         String nomsscat="";
       
        try {
                  
            String sql="select * from jeu where Nom=?";
             mc=MaConnexion.getInstance().getCnx();
            
            ste=mc.prepareStatement(sql);
            ste.setString(1,nomj);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
               id=rs.getInt("id_souscat");
               idj=rs.getInt("Id_Jeu");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        try {
                  
            String sql="select nom_SousCat from sous_categorie where id_SousCat=?";
             mc=MaConnexion.getInstance().getCnx();
            ste=mc.prepareStatement(sql);
            ste.setInt(1,id);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
               nomsscat=rs.getString("nom_SousCat");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        GenreJeu.setText(nomsscat);
        try {
                  
            String sql="select * from image where Id_jeu=?";
             mc=MaConnexion.getInstance().getCnx();
            ste=mc.prepareStatement(sql);
            ste.setInt(1,idj);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
               Urlimg=rs.getString("Url_Image");
               idimg=rs.getInt("Id_Image");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        Integer h = idimg;
        String path1=h.toString()+".png";

        String path2 = "src/Images/ImagesJeux/" + path1;

//        System.out.println(path2);
        String Path_name = new File(path2).getAbsolutePath();
//        System.out.println(Path_name);
        ImageView i = new ImageView();
        File f = new File(Path_name);
        Image im = new Image(f.toURI().toString());
        imgjeu.setImage(im);
 
    }

      @FXML
    private void click2(MouseEvent mouseEvent) {
        mylistener.onClickListener(jeu);
    }
     
}


