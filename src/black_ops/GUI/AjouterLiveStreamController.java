/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI;

import black_ops.Controller.LiveStreamController;
import black_ops.Entity.Live_Stream;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author aZiz
 */
public class AjouterLiveStreamController implements Initializable {

    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtpathe;
    @FXML
    private Button ajouter;
    @FXML
    private Button btnannuler;
    @FXML
    private ComboBox<String> listdefi;
    @FXML
    private Text retour;
    @FXML
    private ComboBox<String> listvis;
      private boolean update;
        Integer idlivestream;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            Combobox();
            listdefi.setPromptText("Choisissier");
        listvis.getItems().addAll(
                 "Afficher",
                 "En cours",
                 "Masquer"

                );
    
        // TODO
    }    

    @FXML
    private void AjouterLive(ActionEvent event) {
        
           String nom = txtnom.getText();
        String path = txtpathe.getText();
        String visibilte = listvis.getValue();
        String defi = listdefi.getValue();
        System.out.println(defi);
                
         if (nom.isEmpty() || path.isEmpty() || visibilte.isEmpty() || defi==null ){
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            
            alert.setHeaderText(null);
           alert.setContentText(" Veuillez remplir tous les champs ");
            alert.showAndWait();
            
         
            
            
         }else {
             
               LiveStreamController liveStream = new LiveStreamController();
              int x = liveStream.DefiId(defi);
            
            Live_Stream ls = new Live_Stream(nom,path,visibilte,x); 
           
            
          
           
             
             if (update == false ){
             liveStream.ajouterLiveStream(ls);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Ajouter Live Stream");

		 alert.setHeaderText(null);
		alert.setContentText("Live stream a été ajouté avec success");

		alert.showAndWait();
            
            }else{
                 
               liveStream.updateLiveStream(ls, idlivestream);
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Modification Live Stream");

		 alert.setHeaderText(null);
		alert.setContentText("Live stream a été modifié avec success");

		alert.showAndWait();
                
            }
             
             Afficher(event);
         }
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

   
    @FXML
    private void Combobox() {
         
  LiveStreamController dfs = new LiveStreamController();
           listdefi.setItems(dfs.Defi());

       
    }

    private void Afficher(ActionEvent event) {
   try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/black_ops/GUI/AfficherLiveStream.fxml"));
            Parent root = loader.load();
           
           listdefi.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
      }



    @FXML
    private void Afficherretour(MouseEvent event) {
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Annuler Ajout Live Stream");
                                alert.setHeaderText("Êtes-vous sûr de vouloir Annuler l'ajout");
                                     Optional<ButtonType> option = alert.showAndWait();
                                       if (option.get() == ButtonType.OK) {
          try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/black_ops/GUI/AfficherLiveStream.fxml"));
            Parent root = loader.load();
       
            
            listdefi.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

}
    }

    @FXML
    private void Annulerretour(ActionEvent event) {
    }

    void setTextField(int id, String nom, String path, String visiblte, String nom_defi) {
        idlivestream = id;
        txtnom.setText(nom);
        txtpathe.setText(path);
        listvis.setValue(visiblte);
        listdefi.setValue(nom_defi);
        
    }
    }
    

