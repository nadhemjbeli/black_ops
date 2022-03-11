/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI;

import black_ops.Controller.ReplayStreamController;
import black_ops.Entity.Replay_Stream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author aZiz
 */
public class AfficherReplayStreamStatistiqueController implements Initializable {

    @FXML
    private Text retour;
    @FXML
    private BarChart<String, Integer> barchart;
    @FXML
    private NumberAxis catY;
    @FXML
    private CategoryAxis catX;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        XYChart.Series<String,Integer> series = new XYChart.Series<>();
        series.setName("Vues Par Vidéo");
        
           ReplayStreamController replayStream = new ReplayStreamController();
        ObservableList<Replay_Stream> list = (ObservableList<Replay_Stream>) replayStream.afficherReplay();
        for (Replay_Stream l:list){
            
            
            
            series.getData().add(new XYChart.Data<>(l.getNom(),l.getVues()));
          
        }
        
        barchart.getData().add(series);
        // TODO
    }    

    @FXML
    private void Afficherretour(MouseEvent event) {
        
           try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/black_ops/GUI/AfficherReplayStream.fxml"));
            Parent root = loader.load();
       
            
           retour.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
}
