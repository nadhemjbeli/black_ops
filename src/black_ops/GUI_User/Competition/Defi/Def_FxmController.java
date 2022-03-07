/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI_User.Competition.Defi;

import black_ops.Controller.DefiController;
import black_ops.Entity.Champion;
import black_ops.Entity.Defi;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;


import black_ops.config.MaConnexion;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author jmokh
 */
public class Def_FxmController implements Initializable {
@FXML
    private VBox chosenChampCard;
    private Label ChampNom;

    private ImageView ChampImg;

    @FXML
    private GridPane grid;
    private Label RoleLabel;
   private ObservableList<Defi> def = FXCollections.observableArrayList();
    private MyListener mylistener;
     private Image image;
    @FXML
    private Label LabelNom1;
    @FXML
    private Label nomD;
    @FXML
    private ImageView imgD;
    @FXML
    private Label Date;
    @FXML
    private Button showm;
    
public ObservableList<Defi> getData()
{ 
           DefiController Df = new DefiController();
       
            ObservableList<Defi> df =Df.afficherDefi();
            
      return df;
     
}
 private void setChosenChamp(Defi Df) {
        nomD.setText(Df.getNom_Defi());
        Date.setText(""+Df.getDate_defi());
        String path1= Df.getNom_Defi();
      
       String path2=Df.getImg_Defi();
      
//        System.out.println(path2);
       
        String Path_name = new File(path2).getAbsolutePath();
//        System.out.println(Path_name);
        ImageView i = new ImageView();
        File f =  new File (Path_name);
        Image im =new Image(f.toURI().toString());
        imgD.setImage(im);
       
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         def.addAll(getData());
          if (def.size() > 0) {
            setChosenChamp(def.get(0));
           mylistener = new MyListener() {
                @Override
                public void onClickListener(Defi champion) {
                    setChosenChamp(champion);
                }
            };}
         int column = 0;
           int row = 1;
           
         try{
          for (int i = 0; i < def.size(); i++) {
              FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../Defi/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
            ItemController it = fxmlLoader.getController();
                 it.setData(def.get(i), mylistener);
          
                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
               
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void Information(ActionEvent event) {
         try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("AfficherInfo.fxml"));
            Parent root=(Parent)loader.load();
            AfficherInfoController ic = loader.getController();
            ic.SetNom(nomD.getText());
            Scene scene = new Scene(root);
            Stage stage=new Stage();
            
        stage.setScene(scene);
        stage.show();
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    
}
