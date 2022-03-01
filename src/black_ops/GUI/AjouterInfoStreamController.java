/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI;

import black_ops.Controller.InfoStreamController;
import black_ops.Entity.Info_Stream;
import black_ops.config.MaConnexion;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author aZiz
 */
public class AjouterInfoStreamController implements Initializable {

    @FXML
    private Button ajouter;
    @FXML
    private TextField txtnom;
    @FXML
    private TextArea txtdescription;
    @FXML
    private ComboBox listsouscat;
    @FXML
    private TextField txtimage;
      private int id;
      private boolean update;
      int idinfostream;

 

   
    Connection cnx;
       PreparedStatement ste;
    @FXML
    private Text retour;
    @FXML
    private Button btnannuler;
    /**
     * Initializes the controller class.
     */
       
         public AjouterInfoStreamController(){
           cnx=MaConnexion.getInstance().getCnx();
           
       }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        listsouscat.setPromptText("Choisissez ");
        Combobox();
        // TODO
    }    
    
    /*public void Combo(){
        
        
        
    }*/
    
    @FXML
    private void AjouterInfo (ActionEvent event){
        
        String nom = txtnom.getText();
        String image = txtimage.getText();
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
            Info_Stream info = new Info_Stream(44,nom,image,description,id); 
            InfoStreamController infoStream = new InfoStreamController();
            if (update == false ){
            infoStream.ajouterInfoStream(info);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Ajouter Information Stream");

		 alert.setHeaderText(null);
		alert.setContentText("L'information stream a été ajouté avec success");

		alert.showAndWait();
            
            }else{
                infoStream.updateInfoStream(info, idinfostream);
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Modification Information Stream");

		 alert.setHeaderText(null);
		alert.setContentText("L'information stream a été modifié avec success");

		alert.showAndWait();
                
            }
                  Afficher(event);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
              
        }
             
       
       
        
        
    }

    



    

    @FXML
   // private void Combobox(MouseEvent event) {
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

  public void setId(int id) {
        this.id = id;
    }

    @FXML
    private void Afficher(ActionEvent event) {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Annuler Ajout Information");
                                alert.setHeaderText("Êtes-vous sûr de vouloir Annuler l'ajout");
                                     Optional<ButtonType> option = alert.showAndWait();
                                       if (option.get() == ButtonType.OK) {
        
          try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherInfoStream.fxml"));
            Parent root = loader.load();
           
            txtnom.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
                                       }
        
    }

     void setTextField(int id, String name, String image, String description, String nomsouscat) {

        idinfostream = id;
        txtnom.setText(name);
        txtimage.setText(image);
        txtdescription.setText(description);
       listsouscat.setValue(nomsouscat);

    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

   

    @FXML
    private void Afficherretour(MouseEvent event) {
          try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherInfoStream.fxml"));
            Parent root = loader.load();
       
            
            txtnom.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
 
    
}
