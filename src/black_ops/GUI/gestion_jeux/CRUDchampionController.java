/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI.gestion_jeux;

import black_ops.Controller.ChampionController;
import black_ops.Entity.Champion;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jmokh
 */
public class CRUDchampionController implements Initializable {

    @FXML
    private TextField txtId_jeu;
    @FXML
    private TextField txtdescription_champ;
    @FXML
    private TextField txtRole_champ;
    @FXML
    private TextField txtDiff_champ;
    @FXML
    private TextField txtimg_champ;
    @FXML
    private TableView<Champion> TVChamps;
    @FXML
    private TableColumn<Champion, Integer> colId_champ;
    @FXML
    private TableColumn<Champion, String> colNom_champ;
    @FXML
    private TableColumn<Champion, String> colDescription_champ;
    @FXML
    private TableColumn<Champion, String> colRole_champ;
    @FXML
    private TableColumn<Champion, String> colDiff_champ;
    @FXML
    private TableColumn<Champion, String> colImg_champ;
    @FXML
    private TableColumn<Champion, Integer> colId_jeu;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TextField txtid_champ;
    @FXML
    private TextField txtNom_champ;
     private Stage stage;
 private Scene scene;
 private Parent root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       showchamps();
    }    

    @FXML
    private void handleMouseAction(MouseEvent event) {
        Champion c = TVChamps.getSelectionModel().getSelectedItem();
       txtid_champ.setText(""+c.getId_Champ());
       txtNom_champ.setText(""+c.getNom_Champ());
     txtdescription_champ.setText(""+c.getDescription_Champ());
    txtRole_champ.setText(""+c.getRole_Champ());
     txtDiff_champ.setText(""+c.getDifficulte_Champ());
     txtimg_champ.setText(""+c.getImage_Champ());
     txtId_jeu.setText(""+c.getId_jeu());
    }


    
    public void showchamps()
    {
        ChampionController c1 = new ChampionController();
       
            ObservableList<Champion> list = c1.afficherChampions();
     
     colId_champ.setCellValueFactory(new PropertyValueFactory<Champion,Integer>("Id_Champ"));
     colNom_champ.setCellValueFactory(new PropertyValueFactory<Champion,String>("Nom_Champ") );
     colDescription_champ.setCellValueFactory(new PropertyValueFactory<Champion,String>("description_Champ") );
     colRole_champ.setCellValueFactory(new PropertyValueFactory<Champion,String>("Role_Champ") );
     colDiff_champ.setCellValueFactory(new PropertyValueFactory<Champion,String>("Difficulte_Champ") );
     colImg_champ.setCellValueFactory(new PropertyValueFactory<Champion,String>("Image_Champ") );
     colId_jeu.setCellValueFactory(new PropertyValueFactory<Champion,Integer>("Id_jeu") );    
     TVChamps.setItems(list);
    }

    @FXML
    private void AddChamp(ActionEvent event) {
        String nomChamp = txtNom_champ.getText();
            String descChamp = txtdescription_champ.getText();
            String Role = txtRole_champ.getText();
            String Diff = txtDiff_champ.getText();
            String Image_Url = txtDiff_champ.getText();
           String id_jeu = txtId_jeu.getText();
           int h = Integer.parseInt(id_jeu);
             Champion j1 = new Champion(4,nomChamp,descChamp,Role,Diff,Image_Url,h);
            ChampionController jc1 = new ChampionController();
            jc1.ajouterChampion(j1);
             showchamps();
    }

    @FXML
    private void UpdateChamp(ActionEvent event) {
         String idChamp=txtid_champ.getText();
         int v = Integer.parseInt(idChamp);
        String nomChamp = txtNom_champ.getText();
            String descChamp = txtdescription_champ.getText();
            String Role = txtRole_champ.getText();
            String Diff = txtDiff_champ.getText();
            String Image_Url = txtDiff_champ.getText();
           String id_jeu = txtId_jeu.getText();
           int h = Integer.parseInt(id_jeu);
             Champion j1 = new Champion(v,nomChamp,descChamp,Role,Diff,Image_Url,h);
            ChampionController jc1 = new ChampionController();
            jc1.updateChampion(j1);
             showchamps();
    }
        
    @FXML
    private void DeleteChamp(ActionEvent event) {
         String idChamp=txtid_champ.getText();
         int v = Integer.parseInt(idChamp);
             Champion c1 = new Champion(v);
             ChampionController jc1 = new  ChampionController();
            jc1.deleteChampion(c1);
              showchamps();
    }

    @FXML
    private void JeuScene(ActionEvent event) throws IOException {
   root = FXMLLoader.load(getClass().getResource("CRUDjeu.fxml"));
          
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
    }

    @FXML
    private void ImageScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CRUDimage.fxml"));
          
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
    }

    @FXML
    private void SkinScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CRUDSkin.fxml"));
          
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
    }

    @FXML
    private void Refresh(ActionEvent event) {
         ChampionController jc1 = new ChampionController();
       
            ObservableList<Champion> list = jc1.afficherChampions();
            list.clear();
            showchamps();
    }
}
