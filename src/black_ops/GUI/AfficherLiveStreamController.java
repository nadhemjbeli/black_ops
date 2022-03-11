/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI;

import black_ops.Controller.LiveStreamController;
import black_ops.Entity.Live_Stream;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
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
public class AfficherLiveStreamController implements Initializable {

    @FXML
    private TableView<Live_Stream> table;
    @FXML
    private TableColumn<Live_Stream, String> colid;
    @FXML
    private TableColumn<Live_Stream, String> colnom;
    @FXML
    private TableColumn<Live_Stream, String> colyoutube;
    @FXML
    private TableColumn<Live_Stream,String> colvisi;
    @FXML
    private TableColumn<Live_Stream, Integer> coldefi;
    @FXML
    private TableColumn<Live_Stream, String> colaction;
    @FXML
    private Button btnajouter;
    @FXML
    private TextField txtrechercher;
    @FXML
    private Button btnreplaystream;
    @FXML
    private Button btninformationstream;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         datatable();
    }    
 public void datatable(){
      LiveStreamController liveStream = new LiveStreamController();

               table.setItems((ObservableList<Live_Stream>) liveStream.afficherLiveStreamtable());
        
       colid.setCellValueFactory(new PropertyValueFactory<>("id"));
                colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colyoutube.setStyle("-fx-alignment: CENTER-LEFT;");
        colyoutube.setCellValueFactory(new PropertyValueFactory<>("path"));

        colvisi.setCellValueFactory(new PropertyValueFactory<>("visiblte"));

        coldefi.setCellValueFactory(new PropertyValueFactory<>("nom_defi"));
      Callback<TableColumn<Live_Stream, String>, TableCell<Live_Stream, String>> cellFoctory = (TableColumn<Live_Stream, String> param) -> {
   
            final TableCell<Live_Stream, String> cell = new TableCell<Live_Stream, String>() {
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
                         Live_Stream live_stream = table.getSelectionModel().getSelectedItem();
                             Alert alert = new Alert(AlertType.CONFIRMATION);
                                alert.setTitle("Supprimer Live");
                                alert.setHeaderText("Êtes-vous sûr de vouloir supprimer ce Live Stream");
                               
                                alert.setContentText(" Id - "+live_stream.getId()+" / : "+live_stream.getNom());
                           Optional<ButtonType> option = alert.showAndWait();
                           
                   if (option.get() == ButtonType.OK) {
          liveStream.supprimerLiveStream(live_stream.getId());
          datatable();
                            
      } if  (option.get() == ButtonType.CANCEL) {
         datatable();
      } 
                         
                            
                           

                          

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                         Live_Stream live_stream = table.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/black_ops/GUI/AjouterLiveStream.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                ex.getMessage();
                            }
                            
                            AjouterLiveStreamController ajouterInfoStream = loader.getController();
                        ajouterInfoStream.setUpdate(true);
                            ajouterInfoStream.setTextField(live_stream.getId(), live_stream.getNom(), 
                                    live_stream.getPath(),live_stream.getVisiblte(), live_stream.getNom_defi());
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
         table.setItems((ObservableList<Live_Stream>) liveStream.afficherLiveStreamtable());
     
     
     
     
 }
 
    @FXML
    private void ajouterLiveStream(ActionEvent event) {
        
        try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/black_ops/GUI/AjouterLiveStream.fxml"));
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
       
                  LiveStreamController liveStream = new LiveStreamController();

               table.setItems((ObservableList<Live_Stream>) liveStream.afficherVideoStreamRecherche(recherche));
        
       colid.setCellValueFactory(new PropertyValueFactory<>("id"));
                colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colyoutube.setStyle("-fx-alignment: CENTER-LEFT;");
        colyoutube.setCellValueFactory(new PropertyValueFactory<>("path"));

        colvisi.setCellValueFactory(new PropertyValueFactory<>("visiblte"));

        coldefi.setCellValueFactory(new PropertyValueFactory<>("nom_defi"));
         
      }
        
    }

    @FXML
    private void replaystream(ActionEvent event) {
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/black_ops/GUI/AfficherReplayStream.fxml"));
            Parent root = loader.load();
       
            
           txtrechercher.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
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
    
}
