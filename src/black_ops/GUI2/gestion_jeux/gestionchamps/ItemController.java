/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI2.gestion_jeux.gestionchamps;

import black_ops.Entity.Champion;
import black_ops.Entity.Fruit;
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
    private Label ChampLabel2;

    @FXML
    private ImageView img;
    @FXML
    private Label LabelRôle2;

    @FXML
    private Label LabelDiff2;
    private Champion champion;
    private Fruit fruit;
     private MyListener mylistener;
       @FXML
    private void click(MouseEvent mouseEvent) {
        mylistener.onClickListener(champion);
    }
     
    public void setData(Champion champion,MyListener mylistener){
    {this.champion=champion;
    this.mylistener=mylistener;
    ChampLabel2.setText(champion.getNom_Champ());
    LabelRôle2.setText(champion.getRole_Champ());
//    LabelDiff2.setText(champion.getDifficulte_Champ());
      String path1= champion.getImage_Champ();
      
       String path2="src/Images/ImagesChampions/"+path1;
      
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
            
    
            
