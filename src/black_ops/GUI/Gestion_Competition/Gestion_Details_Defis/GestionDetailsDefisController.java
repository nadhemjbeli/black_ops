/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI.Gestion_Competition.Gestion_Details_Defis;

import black_ops.Controller.DetailsDefiController;
import black_ops.Entity.DetailsDefi;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
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
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

/**
 * FXML Controller class
 *
 * @author medaz
 */
public class GestionDetailsDefisController implements Initializable {

      @FXML
    private Button btn_update;
    @FXML
    private Button btn_sup;
    @FXML
    private Button btn_insert;
    @FXML
    private TextField inp_Id_stat;
    @FXML
    private TextField inp_A;
    @FXML
    private TextField inp_url;
    @FXML
    private TextField inp_B;
    @FXML
    private TextField inp_score_finale;
    @FXML
    private TextField inp_defi;
    @FXML
    private Button QR;
    @FXML
    private TableView<DetailsDefi> table;
    @FXML
    private TableColumn<DetailsDefi, Integer> cl_id;
    @FXML
    private TextField recherche;
    @FXML
    private Button btn_rech;
    @FXML
    private TableColumn<DetailsDefi, Integer> cl_A;
    @FXML
    private TableColumn<DetailsDefi, String> cl_url;
    @FXML
    private TableColumn<DetailsDefi, Integer> cl_B;
    @FXML
    private TableColumn<DetailsDefi, String> cl_Score;
    @FXML
    private TableColumn<DetailsDefi, Integer> cl_defi;
    @FXML
    private ImageView QRimg;
    @FXML
    private Button btn_diselect;
    @FXML
    private Button btn_refresh;
    
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Show_Match();
    }    

    @FXML
    private void Modifier_Match(ActionEvent event) {
        
            String Id_stat = inp_Id_stat.getText();
            int id = Integer.parseInt(Id_stat);
            String url = inp_url.getText();
            String EquipeB = inp_B.getText();
            String score = inp_score_finale.getText() ;
            String defi = inp_defi.getText();
            String EquipeA = inp_A.getText();

            int A = Integer.parseInt(EquipeA);
            int B = Integer.parseInt(EquipeB);
            int df = Integer.parseInt(defi);
            DetailsDefi dfi = new DetailsDefi(id,A,url,B,score,df);
            DetailsDefiController dfc = new DetailsDefiController();
            dfc.Update_Details_Defi(dfi);
            Show_Match();
        
    }

    @FXML
    private void Supprimer_Match(ActionEvent event) {
        
        String Id_stat = inp_Id_stat.getText();
         int id = Integer.parseInt(Id_stat);
         DetailsDefi dfi = new DetailsDefi(id);
         DetailsDefiController dfc = new DetailsDefiController();  
            dfc.Delete_Details_Defi(dfi);
            Show_Match();
    }

    @FXML
    private void Ajouter_Match(ActionEvent event) {
            String EquipeA = inp_A.getText();
            
            String url = inp_url.getText();
            String EquipeB = inp_B.getText();
            String score = inp_score_finale.getText() ;
            String defi = inp_defi.getText();
            
            int A = Integer.parseInt(EquipeA);
            int B = Integer.parseInt(EquipeB);
            int df = Integer.parseInt(defi);
            DetailsDefi dfi = new DetailsDefi(3,A,url,B,score,df);
            DetailsDefiController dfc = new DetailsDefiController();
            dfc.Create_Details_Defi(dfi);
            Show_Match();
        
    }

    @FXML
    private void CodeQr(ActionEvent event) {
        DetailsDefiController D_DefiC = new DetailsDefiController();
         String Id_stat = inp_Id_stat.getText();
         int id = Integer.parseInt(Id_stat);
        DetailsDefi sql_match1 = new DetailsDefi(id);
        String Match = D_DefiC.Select_Detail(sql_match1).toString();
        ByteArrayOutputStream out = QRCode.from(Match)
                    .to(ImageType.PNG).stream() ;
            String f_name = Id_stat;
            String Path_name = "C:\\Users\\medaz\\Documents\\NetBeansProjects\\crud_competition\\src\\QrCode\\";
            try{
            FileOutputStream fout = new FileOutputStream(new File(Path_name+ (f_name +".PNG")) );
            fout.write(out.toByteArray());
            fout.flush();
                
            
        }catch(Exception e){
            System.out.println(e);
            
        }
             Image img = new Image("C:\\Users\\medaz\\Documents\\NetBeansProjects\\Black_ops\\src\\QrCode\\hello.PNG");
             QRimg.setImage(img);
             Show_Match();
        
    }

    
    @FXML
    private void Recherche_Defi(ActionEvent event) {
    }
    private void Show_Match(){
     DetailsDefiController dfc = new DetailsDefiController();  
     ObservableList<DetailsDefi> list = dfc.View_Details_defi(); 
     cl_id.setCellValueFactory(new PropertyValueFactory<DetailsDefi,Integer>("id_Statistique"));
     cl_A.setCellValueFactory(new PropertyValueFactory<DetailsDefi,Integer>("EquipeA") );
     cl_url.setCellValueFactory(new PropertyValueFactory<DetailsDefi,String>("imgScore") );
     cl_B.setCellValueFactory(new PropertyValueFactory<DetailsDefi,Integer>("EquipeB") );
     cl_Score.setCellValueFactory(new PropertyValueFactory<DetailsDefi,String>("Score_finale") );
     cl_defi.setCellValueFactory(new PropertyValueFactory<DetailsDefi,Integer>("id_defi") );
     
    
     table.setItems(list);
    }
    @FXML
    private void handleMouseAction(MouseEvent event) {
    DetailsDefi match = table.getSelectionModel().getSelectedItem();
        inp_Id_stat.setText(""+match.getId_Statistique());
        inp_A.setText(""+match.getEquipeA());
        inp_url.setText(""+match.getImgScore());
        inp_B.setText(""+match.getEquipeB());
        inp_score_finale.setText(""+match.getScore_finale());
        inp_defi.setText(""+match.getId_defi());
    }
    @FXML
    private void disselect(ActionEvent event) {
        inp_Id_stat.setText("");
        inp_A.setText("");
        inp_url.setText("");
        inp_B.setText("");
        inp_score_finale.setText("");
        inp_defi.setText("");
    }

    @FXML
    private void refreshTable(ActionEvent event) {
        
    }
   
}
