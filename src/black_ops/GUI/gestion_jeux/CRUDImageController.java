/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI.gestion_jeux;

import black_ops.Controller.ImageController;
import black_ops.Entity.Image;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jmokh
 */
public class CRUDImageController implements Initializable {
    

    @FXML
    private TextArea txtid_image;
    @FXML
    private TextField txt_Url_image;
    @FXML
    private TextField txt_id_jeu;
    @FXML
    private TableView<Image>TVpics;
    @FXML
    private TableColumn<Image, Integer> colId_id_img;
    @FXML
    private TableColumn<Image, String> col_Url_img;
    @FXML
    private TableColumn<Image, Integer> col_Id_jeu;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    private Stage stage;
 private Scene scene;
 private Parent root;
    @FXML
    private TextField id_message;
    @FXML
    private TextField date_message;
    @FXML
    private Button btn_add;
    @FXML
    private Button btn_search;
    @FXML
    private Button DIselect;
    @FXML
    private Button btn_refresh;
    
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showpics();
    }    

    @FXML
    private void handleMouseAction(MouseEvent event) {
        Image i = TVpics.getSelectionModel().getSelectedItem();
       txtid_image.setText(""+i.getId_Image());
    txt_Url_image.setText(""+i.getUrl_Image());
    txt_id_jeu.setText(""+i.getId_jeu());

         
    }

    @FXML
    private void AddPic(ActionEvent event) {
            String url = txt_Url_image.getText();
           String text = txt_id_jeu.getText();
           int h = Integer.parseInt(text);
             Image i1 = new Image(4,url,h);
           ImageController ic1 = new ImageController();
            ic1.ajouterImage(i1);
             showpics();
    }

    @FXML
    private void UpdatePic(ActionEvent event) {
        String idimg=txtid_image.getText();
         int v = Integer.parseInt(idimg);
        String url_img = txt_Url_image.getText();
           String text = txt_id_jeu.getText();
           int h = Integer.parseInt(text);
            Image i1 = new Image(v,url_img,h);
            ImageController jc1 = new ImageController();
            jc1.updateImage(i1);
             showpics();
    }

    @FXML
    private void DeletePic(ActionEvent event) {
         String idJeu=txtid_image.getText();
         int v = Integer.parseInt(idJeu);
             Image i1 = new Image(v);
            ImageController jc1 = new ImageController();
            jc1.deleteImage(i1);
             showpics();
    }
    public void showpics()
    {
        ImageController ic1 = new ImageController();
        ObservableList<Image> list = ic1.afficherImages();
        System.out.println(list);
     colId_id_img.setCellValueFactory(new PropertyValueFactory<Image,Integer>("Id_Image"));
     col_Url_img.setCellValueFactory(new PropertyValueFactory<Image,String>("Url_Image") );
     col_Id_jeu.setCellValueFactory(new PropertyValueFactory<Image,Integer>("Id_jeu") );
     TVpics.setItems(list);
     
     
    }


    private void SkinScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CRUDSkin.fxml"));
          
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
    }

    private void JeuScene(ActionEvent event) throws IOException {
         root = FXMLLoader.load(getClass().getResource("CRUDjeu.fxml"));
          
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
    }

    private void ChampScene(ActionEvent event) throws IOException {
             root = FXMLLoader.load(getClass().getResource("CRUDchampion.fxml"));
          
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
    }

    @FXML
    private void Refresh(ActionEvent event) {
         ImageController jc1 = new ImageController();
       
            ObservableList<Image> list = jc1.afficherImages();
            list.clear();
            showpics();
    }


    @FXML
    private void Search(ActionEvent event) {
    }

    @FXML
    private void DIselect(ActionEvent event) {
         txtid_image.setText("");
     txt_Url_image.setText("");
    txt_id_jeu.setText("");    
    }


}
