/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI.gestion_jeux;

import black_ops.Controller.SkinController;
import black_ops.Entity.Skin;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
public class CRUDSkinController implements Initializable {

    @FXML
    private TextArea txtid_skin;
    @FXML
    private TextField txt_ImgSkin;
    @FXML
    private TextField txt_id_Champ;
    @FXML
    private TableColumn<Skin, Integer> colId_id_skin;
    @FXML
    private TableColumn<Skin,String> col_img_skin;
    @FXML
    private TableColumn<Skin, Integer> col_Id_champ;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView<Skin> TVskin;
     private Stage stage;
 private Scene scene;
 private Parent root;
    @FXML
    private TextField id_message;
    @FXML
    private TextField date_message;
    @FXML
    private Button DIselect;
    @FXML
    private FontAwesomeIconView btn_Diselect;
    @FXML
    private Button btn_search;
    @FXML
    private Button btn_refresh;
    @FXML
    private Button btnupdate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       showSkins();
    }    

    @FXML
    private void handleMouseAction(MouseEvent event) {
        Skin s = TVskin.getSelectionModel().getSelectedItem();
       txtid_skin.setText(""+s.getId_skin());
    txt_ImgSkin.setText(""+s.getImage_skin());
    txt_id_Champ.setText(""+s.getId_champ());
    }

    @FXML
    private void AddSkin(ActionEvent event) {
         String imgskin = txt_ImgSkin.getText();
           String text = txt_id_Champ.getText();
           int h = Integer.parseInt(text);
             Skin i1 = new Skin(4,imgskin,h);
          SkinController ic1 = new SkinController();
            ic1.ajouterSkin(i1);
             showSkins();
    }

    @FXML
    private void UpdateSkin(ActionEvent event) {
         String idskin=txtid_skin.getText();
         int v = Integer.parseInt(idskin);
         String imgskin = txt_ImgSkin.getText();
           String text = txt_id_Champ.getText();
           int h = Integer.parseInt(text);
             Skin i1 = new Skin(v,imgskin,h);
          SkinController ic1 = new SkinController();
            ic1.updateSkin(i1);
             showSkins();
    }

    @FXML
    private void DeleteSkin(ActionEvent event) {
         String idskin=txtid_skin.getText();
         int v = Integer.parseInt(idskin);
            Skin i1 = new Skin(v);
           SkinController jc1 = new SkinController();
            jc1.deleteSkin(i1);
            showSkins();
    }
    public void showSkins(){
     SkinController ic1 = new SkinController();
       
            ObservableList<Skin> list = ic1.afficherSkins();
     
     colId_id_skin.setCellValueFactory(new PropertyValueFactory<Skin,Integer>("Id_skin") );
     col_img_skin.setCellValueFactory(new PropertyValueFactory<Skin,String>("image_skin") );
     col_Id_champ.setCellValueFactory(new PropertyValueFactory<Skin,Integer>("Id_champ") );
     TVskin.setItems(list);
}

    private void jeuScene(ActionEvent event) throws IOException {
         root = FXMLLoader.load(getClass().getResource("CRUDjeu.fxml"));
          
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
    }

    private void ImageScene(ActionEvent event) throws IOException {
          root = FXMLLoader.load(getClass().getResource("CRUDimage.fxml"));
          
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
    }

    private void ChampionScene(ActionEvent event) throws IOException {
               root = FXMLLoader.load(getClass().getResource("CRUDchampion.fxml"));
          
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
    }

    @FXML
    private void Refresh(ActionEvent event) {
         SkinController jc1 = new SkinController();
       
            ObservableList<Skin> list = jc1.afficherSkins();
            list.clear();
            showSkins();
    }


    @FXML
    private void DIselect(ActionEvent event) {
        txtid_skin.setText("");
    txt_ImgSkin.setText("");
    txt_id_Champ.setText("");
    }

    @FXML
    private void Search(ActionEvent event) {
    }
}