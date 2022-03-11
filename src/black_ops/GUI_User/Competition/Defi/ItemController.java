/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI_User.Competition.Defi;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import black_ops.Entity.Champion;
import black_ops.Entity.Defi;
import java.io.File;
import java.net.URL;
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
public class ItemController  {
   

    @FXML
    private ImageView img;
    
    private Defi defi ;
    
     private MyListener mylistener;
    @FXML
    private Label LabebNom;
    @FXML
    private Label LabelDate;
       
     @FXML
    private void click(MouseEvent mouseEvent) {
        mylistener.onClickListener(defi);
    }
     
    public void setData(Defi defi,MyListener mylistener){
    {this.defi = defi;
    this.mylistener=mylistener;
    LabebNom.setText(defi.getNom_Defi());
    LabelDate.setText(""+defi.getDate_defi());
//    LabelDiff2.setText(champion.getDifficulte_Champ());
     
      
       String path2=defi.getImg_Defi();
      
//        System.out.println(path2);
       
        String Path_name = new File(path2).getAbsolutePath();
//        System.out.println(Path_name);
        ImageView i = new ImageView();
        File f =  new File (Path_name);
        Image im =new Image(f.toURI().toString());
       img.setImage(im);
// Image image = new Image(getClass().getResourceAsStream(champion.getImage_Champ()));
//          img.setImage(image);
    }}}

