/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI.Gestion_Competition.Gestion_Defis;

import black_ops.Controller.DefiController;
import black_ops.Entity.Defi;
import com.jfoenix.controls.JFXComboBox;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import java.net.URL;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;




import java.io.File;
import java.util.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

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
    @FXML
    private Button imp;
    @FXML
    private AnchorPane nav;
    @FXML
    private Button hide;
    @FXML
    private Button show;
    @FXML
    private JFXComboBox  gc;
    @FXML
    private JFXComboBox gcp;
    @FXML
    private Button gu;
    @FXML
    private JFXComboBox gCom;
    @FXML
    private JFXComboBox gcat;
    @FXML
    private JFXComboBox gS;
    @FXML
    private JFXComboBox gJ;
    
    private Stage stage;
 private Scene scene;
 private Parent root;
    @FXML
    private AnchorPane main;
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       Show_defi();
      ComboBoxListInti();
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
                       Recherche_Defi(event);

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
            Recherche_Defi(event);
            DissForm();
    }

    @FXML
    private void Ajouter_defi(ActionEvent event) throws IOException {
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
            
            Recherche_Defi(event);

    }

    @FXML
    private void Recherche_Defi(ActionEvent event ) {
     String srsh = recherche.getText();
     DefiController df = new DefiController();
     ObservableList<Defi> list = df.Select(srsh);
     cl_id.setCellValueFactory(new PropertyValueFactory<Defi,Integer>("Id_Defi"));
     cl_nom.setCellValueFactory(new PropertyValueFactory<Defi,String>("Nom_Defi") );
     cl_desc.setCellValueFactory(new PropertyValueFactory<Defi,String>("Description") );
     cl_prix.setCellValueFactory(new PropertyValueFactory<Defi,Integer>("prix") ); 
     cl_date.setCellValueFactory(new PropertyValueFactory<Defi,Date>("date_defi") );
     cl_jeu.setCellValueFactory(new PropertyValueFactory<Defi,String>("jeu_Defis") );
     cl_nbr.setCellValueFactory(new PropertyValueFactory<Defi,Integer>("nbr_equipe_Defi") );
    
     table.setItems(list);

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
        recherche.setText("");
    }

    @FXML
    private void refreshTable(ActionEvent event) {
            DefiController df = new DefiController();   
             ObservableList<Defi> list = df.afficherDefi(); 
            list.clear();
            Show_defi();
            Recherche_Defi(event);

    }
    public void DissForm(){
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
    private void EnterRecherche(ActionEvent event) {
        recherche.setOnKeyPressed(new EventHandler<KeyEvent>() {
    @Override
    public void handle(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER)  {
         Recherche_Defi(event);
        }
    }
});
    }

    @FXML
    private String importImg(ActionEvent event) {
        String id =inp_Nom.getText();
          Path to = null;
         String  m = null;
         String path = "src/Image/Image defi";
         JFileChooser chooser = new JFileChooser();
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & PNG Images", "jpg","jpeg","PNG");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
           m = chooser.getSelectedFile().getAbsolutePath();
//            System.out.println("You chose to open this file: " +m
//                    );
            
            if(chooser.getSelectedFile() != null){
                
               try {
                   Path from = Paths.get(chooser.getSelectedFile().toURI());
                    to = Paths.get(path+"\\"+id+".png");
                   CopyOption[] options = new CopyOption[]{
                       StandardCopyOption.REPLACE_EXISTING,
                       StandardCopyOption.COPY_ATTRIBUTES
                   };
                   Files.copy(from, to, options);
                   System.out.println("added");
//                saveSystem(selectedFile, )
                       System.out.println(to);

               } catch (IOException ex) {
                   System.out.println();
               }
            }
             inp_img_defi.setText(to.toString());
        
    }
      return to.toString(); 
    }

    @FXML
    private void show_nav_bar(ActionEvent event) {
     nav.setVisible(true); 
     hide.setVisible(true);
     show.setVisible(false);
     
    }

  

    @FXML
    private void hide_nav_bar(ActionEvent event) {
        nav.setVisible(false); 
     hide.setVisible(false);
     show.setVisible(true);
    }

    @FXML
    private void showGC(ActionEvent event) {
        String s = gc.getSelectionModel().getSelectedItem().toString();
        
    }

    @FXML
    private void showComp(ActionEvent event) throws Exception {
        
        String s = gcp.getSelectionModel().getSelectedItem().toString();
//       
        switch(s){
            case "Gestion Defi" :
                 SwitchScene(stage,"Gestion_Competition/Gestion_Defis/GestionDefi",event);
                 break ;
            case "Gestion Matches" :
                    SwitchScene(stage,"Gestion_Competition/Gestion_Details_Defis/GestionDetailsDefis",event);
           break ; 
            case "Gestion Joueurs" :
                  SwitchScene(stage,"Gestion_Competition/Gestion_Joueur/GestionJoueur",event);
           break ;
            case "Gestion Equipe":
                SwitchScene(stage,"Gestion_Competition/Gestion_Equipe/GestionEquipe",event);
        }
   
    }

    @FXML
    private void showU(ActionEvent event) throws IOException, Exception {
        SwitchScene(stage,"Gestion_Competition/Gestion_Defis/GestionDefi",event);
        }  
        
    

    @FXML
    private void showCommande(ActionEvent event) throws Exception {
        String s = gCom.getSelectionModel().getSelectedItem().toString();
        switch(s){
            case "Gestion Defi" :
                 SwitchScene(stage,"Gestion_Competition/Gestion_Defis/GestionDefi",event);
                 break ;
            case "Gestion Matches" :
                    SwitchScene(stage,"Gestion_Competition/Gestion_Details_Defis/GestionDetailsDefis",event);
           break ; 
            case "Gestion Joueurs" :
                  SwitchScene(stage,"Gestion_Competition/Gestion_Joueur/GestionJoueur",event);
           break ;
            case "Gestion Equipe":
                SwitchScene(stage,"Gestion_Competition/Gestion_Equipe/GestionEquipe",event);
        }
        
    }

    @FXML
    private void showCat(ActionEvent event) throws Exception {
        String s = gcat.getSelectionModel().getSelectedItem().toString();
        switch(s){
            case "Gestion Defi" :
                 SwitchScene(stage,"Gestion_Competition/Gestion_Defis/GestionDefi",event);
                 break ;
            case "Gestion Matches" :
                    SwitchScene(stage,"Gestion_Competition/Gestion_Details_Defis/GestionDetailsDefis",event);
           break ; 
            case "Gestion Joueurs" :
                  SwitchScene(stage,"Gestion_Competition/Gestion_Joueur/GestionJoueur",event);
           break ;
            case "Gestion Equipe":
                SwitchScene(stage,"Gestion_Competition/Gestion_Equipe/GestionEquipe",event);
        }
        
    }

    @FXML
    private void showS(ActionEvent event) throws Exception {
        String s = gS.getSelectionModel().getSelectedItem().toString();
        switch(s){
            case "Gestion Defi" :
                 SwitchScene(stage,"Gestion_Competition/Gestion_Defis/GestionDefi",event);
                 break ;
            case "Gestion Matches" :
                    SwitchScene(stage,"Gestion_Competition/Gestion_Details_Defis/GestionDetailsDefis",event);
           break ; 
            case "Gestion Joueurs" :
                  SwitchScene(stage,"Gestion_Competition/Gestion_Joueur/GestionJoueur",event);
           break ;
            case "Gestion Equipe":
                SwitchScene(stage,"Gestion_Competition/Gestion_Equipe/GestionEquipe",event);
        }
    }

    @FXML
    private void showJ(ActionEvent event) throws Exception {
        String s = gJ.getSelectionModel().getSelectedItem().toString();
        switch(s){
            case "Gestion Defi" :
                 SwitchScene(stage,"Gestion_Competition/Gestion_Defis/GestionDefi",event);
                 break ;
            case "Gestion Matches" :
                    SwitchScene(stage,"Gestion_Competition/Gestion_Details_Defis/GestionDetailsDefis",event);
           break ; 
            case "Gestion Joueurs" :
                  SwitchScene(stage,"Gestion_Competition/Gestion_Joueur/GestionJoueur",event);
           break ;
            case "Gestion Equipe":
                SwitchScene(stage,"Gestion_Competition/Gestion_Equipe/GestionEquipe",event);
                break ;
        }
    }
  
    public void SwitchScene(Stage stage ,String nom ,ActionEvent event ) throws Exception {
        
        String path = "/black_ops/GUI/" ;
        
    FXMLLoader loader = new FXMLLoader(getClass().getResource(path+nom+".fxml"));
            Parent root = loader.load();
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
    public void ComboBoxListInti(){
       nav.setVisible(false);  
       ObservableList<String> GestionCommunaute = FXCollections.observableArrayList(" Gestion Message" ,"Gestion Video");
       gc.setItems(GestionCommunaute);
       ObservableList<String> GestionCompetition = FXCollections.observableArrayList("Gestion Defi" ,"Gestion Matches" ,"Gestion Joueurs","Gestion Equipe");
       gcp.setItems(GestionCompetition);
       ObservableList<String> GestionCommande = FXCollections.observableArrayList("Gestion Commande" ,"Gestion  Ligne Commande" );
       gCom.setItems(GestionCommande);
       ObservableList<String> GestionCategorie = FXCollections.observableArrayList("Gestion Categorie" ,"Gestion  SousCategorie" );
       gcat.setItems(GestionCategorie);
        ObservableList<String> GestionStream = FXCollections.observableArrayList("Stream Replay " ,"Stream Info " );
       gS.setItems(GestionStream);
        ObservableList<String> GestionJeu = FXCollections.observableArrayList("Gestion Jeu" ,"Gestion  Image", "Gestion  Skin" ,"Gestion  Champion"  );
       gJ.setItems(GestionJeu);
    }
    
     
 
}
