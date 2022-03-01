/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI;

import black_ops.Controller.InfoStreamController;
import black_ops.Controller.ReplayStreamController;
import black_ops.Entity.Info_Stream;
import black_ops.Entity.Replay_Stream;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author aZiz
 */
public class AfficherInfoStreamController implements Initializable {

    @FXML
    private TableView<Info_Stream> table;
    @FXML
    private TableColumn<Info_Stream, Integer> colid;
    @FXML
    private TableColumn<Info_Stream, String> colnom;
    @FXML
    private TableColumn<Info_Stream, String> colimage;
    @FXML
    private TableColumn<Info_Stream, String> coldescription;
    @FXML
    private TableColumn<Info_Stream, Integer> colsouscat;
    @FXML
    private TableColumn<Info_Stream, String> colaction;
    @FXML
    private Button btnajouter;
    @FXML
    private TextField txtrechercher;
 Info_Stream info_stream = null ;
    @FXML
    private Button btnreplaystream;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        datatable();
        // TODO
    }    
    
    
    public void datatable(){
        
        InfoStreamController infoStream = new InfoStreamController();
        table.setItems((ObservableList<Info_Stream>) infoStream.afficherInfoStream());
        
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
                colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colimage.setCellValueFactory(new PropertyValueFactory<>("image"));

        coldescription.setCellValueFactory(new PropertyValueFactory<>("description"));

        colsouscat.setCellValueFactory(new PropertyValueFactory<>("nomsouscat"));
        
         Callback<TableColumn<Info_Stream, String>, TableCell<Info_Stream, String>> cellFoctory = (TableColumn<Info_Stream, String> param) -> {
   
            final TableCell<Info_Stream, String> cell = new TableCell<Info_Stream, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
              
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {
                     FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH_ALT);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#09a195;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#09a195;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                             info_stream = table.getSelectionModel().getSelectedItem();
                             Alert alert = new Alert(AlertType.CONFIRMATION);
                                alert.setTitle("Supprimer Information");
                                alert.setHeaderText("Êtes-vous sûr de vouloir supprimer ces informations Stream");
                               
                                alert.setContentText(" Id - "+info_stream.getId()+" / : "+info_stream.getNom());
                           Optional<ButtonType> option = alert.showAndWait();
                           
                   if (option.get() == ButtonType.OK) {
          infoStream.supprimerInfoStream(info_stream.getId());
          datatable();
                            
      } if  (option.get() == ButtonType.CANCEL) {
         datatable();
      } 
                         
                            
                           

                          

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            info_stream = table.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("AjouterInfoStream.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                ex.getMessage();
                            }
                            
                            AjouterInfoStreamController ajouterInfoStream = loader.getController();
                        ajouterInfoStream.setUpdate(true);
                            ajouterInfoStream.setTextField(info_stream.getId(), info_stream.getNom(), 
                                    info_stream.getImage(),info_stream.getDescription(), info_stream.getNomsouscat());
                            Parent parent = loader.getRoot();
                           // Stage stage = new Stage();
                           // stage.setScene(new Scene(parent));
                           // stage.initStyle(StageStyle.UTILITY);
                            //stage.show();
                            txtrechercher.getScene().setRoot(parent);

                           

                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
         colaction.setCellFactory(cellFoctory);
         table.setItems((ObservableList<Info_Stream>) infoStream.afficherInfoStream());
               


        
    }

    @FXML
    private void ajouterInfoStream(ActionEvent event) {
        
        try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterInfoStream.fxml"));
            Parent root = loader.load();
        
            
           btnajouter.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void chercher(KeyEvent event) {
        
      String recherche = txtrechercher.getText();
      
      if (recherche.isEmpty())
      {
          datatable();
          
          
          
      }else {
       
                  InfoStreamController infoStream = new InfoStreamController();

               table.setItems((ObservableList<Info_Stream>) infoStream.afficherInfoStream2(recherche));
        
       colid.setCellValueFactory(new PropertyValueFactory<>("id"));
                colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colimage.setCellValueFactory(new PropertyValueFactory<>("image"));

        coldescription.setCellValueFactory(new PropertyValueFactory<>("description"));

        colsouscat.setCellValueFactory(new PropertyValueFactory<>("nomsouscat"));
         
      }
        
        
    }

    @FXML
    private void replaystream(ActionEvent event) {
           try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherReplayStream.fxml"));
            Parent root = loader.load();
       
            
           txtrechercher.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
