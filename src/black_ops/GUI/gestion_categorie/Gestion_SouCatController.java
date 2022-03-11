/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI.gestion_categorie;


import black_ops.Controller.SousCatController;
import black_ops.Entity.Categorie;
import black_ops.Entity.SousCat;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
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
 * FXML Controller class
 *
 * @author User
 */
public class Gestion_SouCatController implements Initializable {

    @FXML
    private TableColumn<SousCat,Integer> col_id;
    @FXML
    private TableColumn<SousCat,String> col_id_sous;
     @FXML
    private TableColumn<SousCat,Integer> col_id_cat;
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
    private TableView<SousCat> txt_sous_cat;
    @FXML
    private TextField txt_nom_soucat;
    @FXML
    private TextField txt_id_cat;
   
    @FXML
    private TextField id_sous_cat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      showSousCategorie();
    }    

    @FXML
    private void handleMouseAction(MouseEvent event) {
        Categorie ct = (Categorie) table_categorie.getSelectionModel();
        col_id.setText(""+ct.getId());
        col_id_sous.setText(""+ct.getNom());
        
    }

    @FXML
    private void add_SousCategorie(ActionEvent event) {
         try{
            String nom =txt_nom_soucat.getText();
            String cat = txt_id_cat.getText();
            int id_cat = Integer.parseInt(cat);
            
//           Timestamp ts = Timestamp.valueOf(text);
        SousCat s_cat = new SousCat(1,id_cat ,nom);
          SousCatController cate = new SousCatController();
           cate.ajouterSouscat(s_cat);
           showSousCategorie(); 
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void delete_SousCategorie(ActionEvent event) {
        try{
            String idsouscat=col_id.getText();
            int id_Souscat = Integer.parseInt(idsouscat);
           SousCatController ct = new SousCatController();
           ct.supprimerSouscat(id_Souscat);
           showSousCategorie();
        }catch(NumberFormatException e){
            System.out.println(e.getMessage());
        }
    }

    private void edit_SousCategorie(ActionEvent event) {
         try{
            String id_Souscat=col_id_sous.getText();
            int id_sous_cat = Integer.parseInt(id_Souscat);
            String nom = txt_nom_soucat.getText();
            String cat2 = txt_id_cat.getText();
            int id_cat = Integer.parseInt(cat2);
           SousCat cat = new SousCat(id_sous_cat,id_cat,nom);
           SousCatController ct = new SousCatController();
           ct.updateSouscat(cat);
           showSousCategorie();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void showSousCategorie() {
         SousCatController sc =  new SousCatController();
       
        ObservableList<SousCat> list = sc.afficherSouscat();
     
       col_id.setCellValueFactory(new PropertyValueFactory<SousCat,Integer>("id"));
        col_id_sous.setCellValueFactory(new PropertyValueFactory<SousCat,String>("nom"));
        col_id_cat.setCellValueFactory(new PropertyValueFactory<SousCat,Integer>("id_cat"));
        txt_sous_cat.setItems(list);
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
    private void delete_message(ActionEvent event) {
    }

    @FXML
    private void edit_message(ActionEvent event) {
    }
     
    
}
