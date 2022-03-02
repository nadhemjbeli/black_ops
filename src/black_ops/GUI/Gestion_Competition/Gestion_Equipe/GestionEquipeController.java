/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI.Gestion_Competition.Gestion_Equipe;

import black_ops.Controller.EquipeController;
import black_ops.Entity.Equipe;
import java.io.IOException;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.util.Calendar;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * FXML Controller class
 *
 * @author medaz
 */
public class GestionEquipeController implements Initializable {
    @FXML
    private Button btn_update;
    @FXML
    private Button btn_sup;
    @FXML
    private Button btn_insert;
    @FXML
    private TextField inp_Equipe;
    @FXML
    private TextField inp_Nom;
    @FXML
    private TextField inp_url;
    @FXML
    private TextField inp_date;
    @FXML
    private TextField inp_nbr;
    @FXML
    private TableView<Equipe> table;
    @FXML
    private TableColumn<Equipe, Integer> cl_id;
    @FXML
    private TableColumn<Equipe, String> cl_nom;
    @FXML
    private TableColumn<Equipe, String> cl_url;
    @FXML
    private TableColumn<Equipe, Date> cl_date;
    @FXML
    private TableColumn<Equipe, Integer> cl_nbr;
    @FXML
    private TextField recherche;
    @FXML
    private Button btn_rech;
    @FXML
    private Button btn_diselect;
    @FXML
    private Button btn_refresh;
    @FXML
    private Button imp;
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Show_Equipe();
    }    
    @FXML
    private void Modifier_Equipe(ActionEvent event) {
            String id_eq = inp_Equipe.getText();
            String Nom = inp_Nom.getText();
            String url = inp_url.getText();
            String nbr_joueur = inp_nbr.getText() ;
            String d = inp_date.getText();
            int id = Integer.parseInt(id_eq);
            Date date = Date.valueOf(d);
              int nbr = Integer.parseInt(nbr_joueur);
               Equipe eq = new Equipe(id,Nom,url,date,nbr);
               EquipeController Ec = new EquipeController();
               Ec.UpdateEquipe(eq);
               Show_Equipe();
    }

    @FXML
    private void Supprimer_Equipe(ActionEvent event) {
        String Id_eq = inp_Equipe.getText();
         int id = Integer.parseInt(Id_eq);
         Equipe eq = new Equipe(id);
         EquipeController Ec = new EquipeController();  
            Ec.DeleteEquipe(eq);
            Show_Equipe();
    }

    @FXML
    private void Ajouter_Equipe(ActionEvent event) {
             java.util.Date date = Calendar.getInstance().getTime();
             java.sql.Date dateJ = new java.sql.Date(date.getTime());     
            String Nom = inp_Nom.getText();
            String url = inp_url.getText();
            String nbr_joueur = inp_nbr.getText() ;
            
              int nbr = Integer.parseInt(nbr_joueur);
               Equipe eq = new Equipe(3,Nom,url,dateJ,nbr);
               EquipeController Ec = new EquipeController();
               Ec.ajouterEquipe(eq);
               Show_Equipe();
    }

  
    @FXML
    private void Recherche_Defi(ActionEvent event) {
        
        
    }
    private void Show_Equipe(){
    EquipeController Ec = new EquipeController();  

    ObservableList<Equipe> list = Ec.afficherEquipe() ; 
     cl_url.setPrefWidth(80);
     cl_id.setCellValueFactory(new PropertyValueFactory<Equipe,Integer>("id_Equipe"));
     cl_nom.setCellValueFactory(new PropertyValueFactory<Equipe,String>("nom_Equipe") );
     cl_url.setCellValueFactory(new PropertyValueFactory<Equipe,String>("logo_Equipe") );
     cl_date.setCellValueFactory(new PropertyValueFactory<Equipe,Date>("date") );
     cl_nbr.setCellValueFactory(new PropertyValueFactory<Equipe,Integer>("nbr_joueur_Equipe") );
    
     table.setItems(list);
    }
    
    @FXML
    private void handleMouseAction(MouseEvent event) {
    Equipe equipe = table.getSelectionModel().getSelectedItem();
        inp_Equipe.setText(""+equipe.getId_Equipe());
        inp_Nom.setText(""+equipe.getNom_Equipe());
        inp_url.setText(""+equipe.getLogo_Equipe());
        inp_date.setText(""+equipe.getDate());
        inp_nbr.setText(""+equipe.getNbr_joueur_Equipe());
        
    }

    @FXML
    private void disselect(ActionEvent event) {
        inp_Equipe.setText("");
        inp_Nom.setText("");
        inp_url.setText("");
        inp_date.setText("");
        inp_nbr.setText("");
    }

    @FXML
    private void refreshTable(ActionEvent event) {
    }

    @FXML
    private String importImg(ActionEvent event) {
         String id =inp_Nom.getText();
          Path to = null;
         String  m = null;
         String path = "src/Image/Logo_Equipe";
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
             inp_url.setText(to.toString());
        
    }
      return to.toString(); 
    }
    
    
}
