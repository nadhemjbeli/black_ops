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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
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
        QR.setDisable(false);
        
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
            
           QRimg.setImage(null);
        
            
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
            String Path_name = new File("src/QrCode/").getAbsolutePath();
            try{
            FileOutputStream fout = new FileOutputStream(new File(Path_name+"/"+ (f_name +".PNG")) );
            fout.write(out.toByteArray());
            fout.flush();
            System.out.println(Path_name);

            
        }catch(Exception e){
            System.out.println(e);
            
        }    
            
             affichImage();
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
        String Path_name = new File("src/QrCode/").getAbsolutePath();
        File  dir = new File(Path_name);
        File[] liste = dir.listFiles();
        String id = inp_Id_stat.getText()+".PNG";
        for(File item : liste){
        if(item.isFile())
        { 
            if (item.getName().equals(id)) {
                System.out.println(true);
                QR.setDisable(true);
                affichImage();
                
                break;
            }else{
                QRimg.setImage(null);
                QR.setDisable(false);
            }

           
        }
       
         }
    }
    @FXML
    private void disselect(ActionEvent event) {
        inp_Id_stat.setText("");
        inp_A.setText("");
        inp_url.setText("");
        inp_B.setText("");
        inp_score_finale.setText("");
        inp_defi.setText("");
        QRimg.setImage(null);
       
    }

    @FXML
    private void refreshTable(ActionEvent event) {
        DetailsDefiController D_DefiC = new DetailsDefiController();
             ObservableList<DetailsDefi> list = D_DefiC.View_Details_defi(); 
            list.clear();
           Show_Match();
        
    }
    public void affichImage(){
        String Id_stat = inp_Id_stat.getText();
        String f_name = Id_stat;
        String Path_name = new File("src/QrCode/").getAbsolutePath();
        String image = Path_name+ "\\" + Id_stat + ".PNG";
        System.out.println(image);
        ImageView i = new ImageView();
        File f =  new File (image);
        Image im =new Image(f.toURI().toString());
        QRimg.setImage(im);
    }
}
