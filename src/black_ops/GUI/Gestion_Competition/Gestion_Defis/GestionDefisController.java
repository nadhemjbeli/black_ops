/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI.Gestion_Competition.Gestion_Defis;

import black_ops.Controller.DefiController;
import black_ops.Entity.Defi;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import java.net.URL;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author medaz
 */
public class GestionDefisController implements Initializable {

    @FXML
    private Button btn_update;
    @FXML
    private Button btn_sup;
    @FXML
    private Button btn_insert;
    @FXML
    private TextField inp_Id_defi;
    @FXML
    private TextArea inp_desc;
    @FXML
    private TextField inp_img_defi;
    @FXML
    private TextField inp_prix;
    @FXML
    private TextField inp_nbr_equipe;
    @FXML
    private TextField inp_regle_defis;
    @FXML
    private TextField inp_Rec_defis;
    @FXML
    private TableView<Defi> table;
    @FXML
    private TableColumn<Defi, Integer> cl_id;
    @FXML
    private TableColumn<Defi, String> cl_nom;
    @FXML
    private TableColumn<Defi, Integer> cl_prix;
    @FXML
    private TableColumn<Defi, Date> cl_date;
    @FXML
    private TableColumn<Defi, String> cl_jeu;
    @FXML
    private TableColumn<Defi, Integer> cl_nbr;
    @FXML
    private TableColumn<Defi, String> cl_desc;
    @FXML
    private TextField recherche;
    @FXML
    private Button btn_rech;
    @FXML
    private TextField inp_Jeu;
     @FXML
    private TextField inp_Nom;
    @FXML
    private TextField inp_date;
    
    @FXML
    private Button btn_diselect;
    @FXML
    private Button btn_refresh;
     
  
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       Show_defi();
    }    

    @FXML
    private void Modifier_Defi(ActionEvent event) throws ParseException {
     
     String Id_Defi = inp_Id_defi.getText();
            int id = Integer.parseInt(Id_Defi);
            String nom = inp_Nom.getText();
            String desc = inp_desc.getText();
            String url = inp_img_defi.getText();
            String prix = inp_prix.getText();
            String nbr_Equipe = inp_nbr_equipe.getText() ;
            String regle = inp_regle_defis.getText();
            String rec = inp_Rec_defis.getText();
            String Jeu =  inp_Jeu.getText();
            String d = inp_date.getText();
            Date date = Date.valueOf(d);//
            int nbe = Integer.parseInt(nbr_Equipe);
            int pr = Integer.parseInt(prix);
            Defi dfi = new Defi(id,nom, desc, url, pr,date,Jeu, nbe,regle , rec);
            DefiController df = new DefiController();
            df.UpdateDefi(dfi);
            System.out.println("hello");
            Show_defi();
 
    }

    @FXML
    private void Supprimer_defi(ActionEvent event) {
         String Id_Defi = inp_Id_defi.getText();
         int id = Integer.parseInt(Id_Defi);
            Defi dfi = new Defi(id);
            DefiController df = new DefiController();
            df.DeleteDefi(dfi);
            Show_defi();
             
    }

    @FXML
    private void Ajouter_defi(ActionEvent event) {
        java.util.Date date = Calendar.getInstance().getTime();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime()); 
            String nom = inp_Nom.getText();
            String desc = inp_desc.getText();
            String url = inp_img_defi.getText();
            String prix = inp_prix.getText();
            String nbr_Equipe = inp_nbr_equipe.getText() ;
            String regle = inp_regle_defis.getText();
            String rec = inp_Rec_defis.getText();
            String Jeu =  inp_Jeu.getText();
            int nbe = Integer.parseInt(nbr_Equipe);
            int pr = Integer.parseInt(prix);
            Defi dfi = new Defi(999,nom, desc, url, pr, sqlDate , Jeu, nbe,regle , rec);
            DefiController df = new DefiController();
            df.ajouterDefi(dfi);
            Show_defi();
    }

    @FXML
    private void Recherche_Defi(ActionEvent event) {
        
        
    }
   
    public void Show_defi(){
     DefiController df = new DefiController();
     ObservableList<Defi> list = df.afficherDefi(); 
     cl_id.setCellValueFactory(new PropertyValueFactory<Defi,Integer>("Id_Defi"));
     cl_nom.setCellValueFactory(new PropertyValueFactory<Defi,String>("Nom_Defi") );
     cl_desc.setCellValueFactory(new PropertyValueFactory<Defi,String>("Description") );
     cl_prix.setCellValueFactory(new PropertyValueFactory<Defi,Integer>("prix") ); 
     cl_date.setCellValueFactory(new PropertyValueFactory<Defi,Date>("date_defi") );
     cl_jeu.setCellValueFactory(new PropertyValueFactory<Defi,String>("jeu_Defis") );
     cl_nbr.setCellValueFactory(new PropertyValueFactory<Defi,Integer>("nbr_equipe_Defi") );
    
     table.setItems(list);
    }
    @FXML
    private void handleMouseAction(MouseEvent event) {
    Defi defi = table.getSelectionModel().getSelectedItem();
        inp_Id_defi.setText(""+defi.getId_Defi());
        inp_Nom.setText(""+defi.getNom_Defi());
        inp_prix.setText(""+defi.getPrix());
        inp_date.setText(""+defi.getDate_defi());
        inp_Jeu.setText(""+defi.getJeu_Defis());
        inp_nbr_equipe.setText(""+defi.getNbr_equipe_Defi());
        inp_regle_defis.setText(""+defi.getRégle_Defi());
        inp_Rec_defis.setText(""+defi.getRecompense_Defi());
        inp_desc.setText(""+defi.getDescription());
        inp_img_defi.setText(""+defi.getImg_Defi());
    }

    @FXML
    private void disselect(ActionEvent event) {
        inp_Id_defi.setText("");
        inp_Nom.setText("");
        inp_prix.setText("");
        inp_date.setText("");
        inp_Jeu.setText("");
        inp_nbr_equipe.setText("");
        inp_regle_defis.setText("");
        inp_Rec_defis.setText("");
        inp_desc.setText("");
        inp_img_defi.setText("");
    }

    @FXML
    private void refreshTable(ActionEvent event) {
    }
}
