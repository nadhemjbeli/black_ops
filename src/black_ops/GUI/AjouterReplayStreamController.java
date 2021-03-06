/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI;

import black_ops.Controller.ReplayStreamController;
import black_ops.Entity.Replay_Stream;
import black_ops.config.MaConnexion;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author aZiz
 */
public class AjouterReplayStreamController implements Initializable {

    @FXML
    private TextField txtnom;
    @FXML
    private Button ajouter;
    @FXML
    private Button btnannuler;
    @FXML
    private ComboBox listsouscat;
    @FXML
    private TextArea txtdescription;
    @FXML
    private Text retour;
     private int id;
      private boolean update;
      int idreplaystream;
        Connection cnx;
       PreparedStatement ste;
    @FXML
    private TextField txturl;
    @FXML
    private FontAwesomeIconView browser;
     private FileChooser fileChooser ;

 private File file;
       
        public AjouterReplayStreamController(){
           cnx=MaConnexion.getInstance().getCnx();
           
       }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listsouscat.setPromptText("Choisissez ");
        // TODO
        Combobox();
        
          fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
         new FileChooser.ExtensionFilter("VideoFile", "*.mp4")
        
        );
    }    

    @FXML
    private void AjouterInfo(ActionEvent event) throws IOException {
        
        String nom = txtnom.getText();
        String image = txturl.getText();
        String description = txtdescription.getText();
 
        
        String valeur = (String) listsouscat.getValue();
        if (nom.isEmpty() || image.isEmpty() || description.isEmpty() || valeur == null){
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(" Veuillez remplir tous les champs ");
            alert.showAndWait();
            
         
            
            
        }else {
             String Sql = "SELECT id_SousCat from sous_categorie where nom_SousCat = ?";
              try {
            ste=cnx.prepareStatement(Sql);
            ste.setString(1, valeur);
                 ResultSet rs=ste.executeQuery();
             while(rs.next()){
               
                setId(rs.getInt(1));
                
             }
            Replay_Stream replay = new Replay_Stream(nom,image,description,id); 
            ReplayStreamController replayStream = new ReplayStreamController();
            if (update == false ){
                uplode();
            replayStream.ajouterReplay(replay);
            Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Ajouter Replay Stream");

		 alert.setHeaderText(null);
		alert.setContentText("Replay stream a ??t?? ajout?? avec success");

		alert.showAndWait();
            }else{
                uplode();
                 replayStream.updateReplay(replay, idreplaystream);
                  Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Modification Replay Stream");

		 alert.setHeaderText(null);
		alert.setContentText("Replay stream a ??t?? modifi?? avec success");

		alert.showAndWait();
            }
                  Afficher(event);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
              
        }
            
    }

    private void Afficher(ActionEvent event) {
        
        
        
          try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/black_ops/GUI/AfficherReplayStream.fxml"));
            Parent root = loader.load();
   
            txturl.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
                                     
        
        
    }

    @FXML
    private void Combobox() {
             String sql ="SELECT * from sous_categorie where id_cat = (select id_cat from categorie where nom_cat='Stream') ";
        try {
            ste=cnx.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            ObservableList data = FXCollections.observableArrayList();
            while(rs.next()){
                data.add(rs.getString(2));
            }
            listsouscat.setItems(data);

          }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    }
    
      void setTextField(int id, String name, String url, String description, String nomsouscat) {

        idreplaystream = id;
        txtnom.setText(name);
        txturl.setText(url);
        txtdescription.setText(description);
       listsouscat.setValue(nomsouscat);

    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    @FXML
    private void Afficherretour(MouseEvent event) {
  Alert alert = new Alert(AlertType.CONFIRMATION);
                                alert.setTitle("Annuler Ajout Replay");
                                alert.setHeaderText("??tes-vous s??r de vouloir Annuler l'ajout");
                                     Optional<ButtonType> option = alert.showAndWait();
                                       if (option.get() == ButtonType.OK) {
        
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/black_ops/GUI/AfficherReplayStream.fxml"));
            Parent root = loader.load();
      
            
            txturl.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
}
        }
    }

    @FXML
    private void UrlFile(MouseEvent event) {
        Window ownerWindow = null;
            
            file = fileChooser.showOpenDialog(ownerWindow);
            if (file != null){
                
                txturl.setText(file.getAbsolutePath());
                
                     txturl.setText(file.getName());
                
                
              
                
            }
        
    }
    private void uplode() throws IOException{
        
             CopyOption[] options = new CopyOption[]{
                    StandardCopyOption.REPLACE_EXISTING,
                    StandardCopyOption.COPY_ATTRIBUTES
                };
                   String path = new File("src/black_ops/MediaStream").getAbsolutePath();
                   Path from = Paths.get(file.getAbsolutePath());
                   Path to = Paths.get(path+"/"+txturl.getText());
                Files.copy(from,to,options);
        
    }
    
}
