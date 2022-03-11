/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI;

import black_ops.Controller.InfoStreamController;
import black_ops.Controller.ModeController;
import black_ops.Entity.Info_Stream;
import black_ops.Entity.Mode;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author aZiz
 */
public class FrontUserDescriptionController implements Initializable {

    @FXML
    private Text retour;
    @FXML
    private ImageView ImageDescription;
    @FXML
    private Label txttitre;
    @FXML
    private Label txtdescription;
    private int idinfo;
    @FXML
    private AnchorPane Pane;
    @FXML
    private FontAwesomeIconView moonIcon;
    @FXML
    private FontAwesomeIconView lightIcon;
    
 
    @FXML
    private Button btnlight;
    @FXML
    private Button btnmoon;
    @FXML
    private Text titrepage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setTextField(idinfo);
        
        modeOn();
    
    }    

    void setTextField(int id) {
        
        idinfo= id;
        if (idinfo >0){
        data(idinfo);
        }
    }

    @FXML
    private void Afficherretour(MouseEvent event) {
        
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/black_ops/GUI/FrontUser.fxml"));
            Parent root = loader.load();
        
            
           txtdescription.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void data(int id) {
        
          InfoStreamController infoStream = new InfoStreamController();
   ObservableList<Info_Stream> list = (ObservableList<Info_Stream>) infoStream.afficherInfoStreamByid(id);
   
   for (Info_Stream l:list){
       
       ImageDescription.setImage(l.getImageV2());
      
       txttitre.setText(l.getNom());
   
       txtdescription.setText(l.getDescription());
       
       
       
       
   }
        
     
    }

    @FXML
    private void darkMode(MouseEvent event) {
        
      
        
        
        
    }

    private void moonMode() {
        
     
            
            btnmoon.setVisible(false);
            btnlight.setVisible(true);
            
           //Pane.getStylesheets().remove("style.css");
            Pane.getStylesheets().add(getClass().getResource("style_dark.css").toString());
            
        retour.setFill(Paint.valueOf("white"));
        titrepage.setFill(Paint.valueOf("white"));
 
    }

 
    private void lightMode() {
        
       
             
             
                btnmoon.setVisible(true);
           btnlight.setVisible(false);
            
             
            Pane.getStylesheets().remove(getClass().getResource("style_dark.css").toString());
            Pane.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
                  
        retour.setFill(Paint.valueOf("Black"));
        titrepage.setFill(Paint.valueOf("Black"));
         
    }
    
    
    @FXML
    private void changemode (ActionEvent event){
//        IsLight = !IsLight;
//        
//        if (IsLight){
//            
//            lightMode();
//            
//        }else {
//            
//            
//            moonMode();
//        }

 ModeController mode = new ModeController();
        
         ObservableList<Mode> list = (ObservableList<Mode>) mode.affichermode();
         
         for (Mode l:list){
             
             if (l.getLight_mode() == 1){
                 
                 moonMode();
                 mode.ModifiermodeDark();
                 
                 
             }else {
                 
                 lightMode();
                 mode.ModifiermodeLight();
                 
             }
             
             
             
             
             
         }
        
    }

    private void modeOn() {
   ModeController mode = new ModeController();
        
         ObservableList<Mode> list = (ObservableList<Mode>) mode.affichermode();
         
         for (Mode l:list){
             
             if (l.getLight_mode() == 1){
                 
             lightMode();
            
                 
                 
             }else {
                 
                 moonMode();
            
                 
             }
             
             
             
             
             
         }
    }

    
}
