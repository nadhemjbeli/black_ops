/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI;

import black_ops.Controller.ReplayStreamController;
import black_ops.Entity.Replay_Stream;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
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
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author aZiz
 */
public class AfficherReplayStreamController implements Initializable {

    @FXML
    private TableView<Replay_Stream> table;
    @FXML
    private TableColumn<Replay_Stream, Integer> colid;
    @FXML
    private TableColumn<Replay_Stream, String> colnom;
    @FXML
    private TableColumn<Replay_Stream, String> coldescription;
    @FXML
    private TableColumn<Replay_Stream, Integer> colsouscat;
    @FXML
    private TableColumn<Replay_Stream, String> colaction;
    @FXML
    private Button btnajouter;
    @FXML
    private TextField txtrechercher;
    @FXML
    private TableColumn<Replay_Stream, String> colurl;
    @FXML
    private TableColumn<Replay_Stream, Date> coldate;
    Replay_Stream replay_stream = null;
    @FXML
    private Button btninformationstream;
    @FXML
    private Button btnlivestream;
    @FXML
    private Button btnvues;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        datatable();
    }    
    
    
    
     public void datatable(){
        
        ReplayStreamController replayStream = new ReplayStreamController();
        table.setItems((ObservableList<Replay_Stream>) replayStream.afficherReplay());
        
       colid.setCellValueFactory(new PropertyValueFactory<>("id"));
                colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colurl.setCellValueFactory(new PropertyValueFactory<>("url"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));

        coldescription.setCellValueFactory(new PropertyValueFactory<>("description"));

        colsouscat.setCellValueFactory(new PropertyValueFactory<>("nomsouscat"));
        
         Callback<TableColumn<Replay_Stream, String>, TableCell<Replay_Stream, String>> cellFoctory = (TableColumn<Replay_Stream, String> param) -> {
       
            final TableCell<Replay_Stream, String> cell = new TableCell<Replay_Stream, String>() {
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
                             replay_stream = table.getSelectionModel().getSelectedItem();
                             Alert alert = new Alert(AlertType.CONFIRMATION);
                                alert.setTitle("Supprimer Replay");
                                alert.setHeaderText("Êtes-vous sûr de vouloir supprimer ce Replay Stream");
                               
                                alert.setContentText(" Id - "+replay_stream.getId()+" / : "+replay_stream.getNom());
                           Optional<ButtonType> option = alert.showAndWait();
                           
                   if (option.get() == ButtonType.OK) {
          replayStream.supprimerReplay(replay_stream.getId());
          datatable();
                            
      } if  (option.get() == ButtonType.CANCEL) {
         datatable();
      } 
                         
                            
                           

                          

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            replay_stream = table.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/black_ops/GUI/AjouterReplayStream.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                ex.getMessage();
                            }
                            
                            AjouterReplayStreamController ajouterReplayStream = loader.getController();
                        ajouterReplayStream.setUpdate(true);
                            ajouterReplayStream.setTextField(replay_stream.getId(), replay_stream.getNom(), 
                                    replay_stream.getUrl(),replay_stream.getDescription(), replay_stream.getNomsouscat());
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
         table.setItems((ObservableList<Replay_Stream>) replayStream.afficherReplay());
               


        
    }
    
    

    @FXML
    private void ajouterReplayStream(ActionEvent event) {
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/black_ops/GUI/AjouterReplayStream.fxml"));
            Parent root = loader.load();
  
            
           txtrechercher.getScene().setRoot(root);
            
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
       
                  ReplayStreamController infoStream = new ReplayStreamController();

               table.setItems((ObservableList<Replay_Stream>) infoStream.afficherReplay2(recherche));
        
       colid.setCellValueFactory(new PropertyValueFactory<>("id"));
                colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colurl.setCellValueFactory(new PropertyValueFactory<>("url"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));

        coldescription.setCellValueFactory(new PropertyValueFactory<>("description"));

        colsouscat.setCellValueFactory(new PropertyValueFactory<>("nomsouscat"));
         
      }
        
    }

    @FXML
    private void Informationstream(ActionEvent event) {
        try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/black_ops/GUI/AfficherInfoStream.fxml"));
            Parent root = loader.load();
       
            
           txtrechercher.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @FXML
    private void LiveStream(ActionEvent event) {
        
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/black_ops/GUI/AfficherLiveStream.fxml"));
            Parent root = loader.load();
       
            
           txtrechercher.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void Vues(ActionEvent event) {
        
           try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/black_ops/GUI/AfficherReplayStreamStatistique.fxml"));
            Parent root = loader.load();
       
            
           txtrechercher.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
