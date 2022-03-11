/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI.gestion_categorie;

import black_ops.Controller.CategorieController;
import black_ops.Entity.Categorie;
import black_ops.Entity.Equipe;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author User
 */
public class Gestion_CategorieController implements Initializable {

    @FXML
    private TableView<Categorie> table_categorie;
    @FXML
    private TableColumn<Categorie, Integer> col_id;
    @FXML
    private TextField txt_id_cat;
    @FXML
    private Button btn_add;
    @FXML
    private Button btn_delete;
    @FXML
    private Button btn_edit;
    @FXML
    private Button btn_diselect;
    @FXML
    private Button btn_refresh;
    @FXML
    private TextField txt_search;
    @FXML
    private Button btn_search;
    @FXML
    private TextField txt_nom_cat;
    @FXML
    private TableColumn<?, ?> col_Nom;
    @FXML
    private TextField search;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       showCategorie();
        
    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
        Categorie ct = table_categorie.getSelectionModel().getSelectedItem();
        txt_id_cat.setText(""+ct.getId());
        txt_nom_cat.setText(""+ct.getNom());
        
    }



    @FXML
    private void disselect(ActionEvent event) {
    }

    @FXML
    private void refreshTable(ActionEvent event) {
    }

    @FXML
    private void search_messages_par_contenu(ActionEvent event) {
    }

    @FXML
    private void add_categorie(ActionEvent event) {
        try{
            String nom = txt_nom_cat.getText();
            
//           Timestamp ts = Timestamp.valueOf(text);
        Categorie cat = new Categorie(1, nom);
          CategorieController cate = new CategorieController();
           cate.ajouterCategorie(cat);
           showCategorie(); 
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void delete_categorie(ActionEvent event) {
        try{
            String idmsg=txt_id_cat.getText();
            int id_cat = Integer.parseInt(idmsg);
           CategorieController ct = new CategorieController();
           ct.supprimerCategorie(id_cat);
           showCategorie();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void edit_categorie(ActionEvent event) {
         try{
            String id_cat=txt_id_cat.getText();
            int id_ca = Integer.parseInt(id_cat);
            String nom = txt_nom_cat.getText();
            
           
            
         
           Categorie cat = new Categorie(id_ca,nom);
           CategorieController ct = new CategorieController();
           ct.updateCategorie(cat);
           showCategorie();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

   

    private void showCategorie() {
        CategorieController mcl = new CategorieController();
       
        ObservableList<Categorie> list = mcl.afficherCategorie();
     
        col_id.setCellValueFactory(new PropertyValueFactory<Categorie, Integer>("id"));
        col_Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        table_categorie.setItems(list);
    }

     
        
    }

