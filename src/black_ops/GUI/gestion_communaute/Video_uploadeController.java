/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI.gestion_communaute;

import black_ops.Controller.Video_UploadeController;
import black_ops.Entity.VideoUploadee;
import black_ops.config.MaConnexion;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class Video_uploadeController implements Initializable {

    @FXML
    private TableView<VideoUploadee> table_video;
    @FXML
    private TableColumn<VideoUploadee, Integer> col_id;
    @FXML
    private TableColumn<VideoUploadee, String> col_nom_video;
    @FXML
    private TableColumn<VideoUploadee, Date> col_date;
    @FXML
    private TableColumn<VideoUploadee, String> col_url;
    @FXML
    private TableColumn<VideoUploadee, Integer> col_id_client;
    @FXML
    private TableColumn<VideoUploadee, Integer> col_id_sous;
    @FXML
    private TableColumn<VideoUploadee, String> col_description;
    @FXML
    private TextField txt_id;
    @FXML
    private TextField txt_nom_video;
    @FXML
    private TextArea txt_description;
    @FXML
    private TextField txt_url_video;
    private TextField txt_id_cl;
    private TextField txt_id_sous_cat;
    @FXML
    private TextField date_video;
    @FXML
    private Button btn_add;
    @FXML
    private Button btn_delete;
    @FXML
    private Button btn_edit;
    @FXML
    private Button btn_diselect;
    @FXML
    private Button btn_refresh;
    @FXML
    private Button btn_search;
    @FXML
    private Button btn_upload;
    
    Stage stage;
    
    private Connection mc;

    ObservableList<VideoUploadee> data_list;
    
    @FXML
    private JFXComboBox liste_cl;
    @FXML
    private JFXComboBox liste_sc;
    
    
    PreparedStatement ste;

   

    /**
     * Initializes the controller class.
     */
    
    String path = new File("src/media").getAbsolutePath();
    @FXML
    private JFXTextField txt_search;
    @FXML
    private Button btn_view_video;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fill_client_combo();
        fill_s_c_combo();
        btn_delete.setVisible(false);
        btn_edit.setVisible(false);
        btn_upload.setVisible(false);
        btn_view_video.setVisible(false);
        search();
        
    }    

    @FXML
    private void handleMouseAction(MouseEvent event) {
        VideoUploadee vd = table_video.getSelectionModel().getSelectedItem();
        String user = "";
        String sous_c = "";
        txt_id.setText(""+vd.getId_vdeo());
        txt_nom_video.setText(""+vd.getNom_video());
        txt_url_video.setText(""+vd.getUrl_video());
        txt_description.setText(""+vd.getDescription_video());
        try {

            ObservableList options2 = FXCollections.observableArrayList();

            String sql22 = "select Pseaudo_Cl from client where id_cl = ?";
            ste=mc.prepareStatement(sql22);
             ste.setInt(1,vd.getId_cl());
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                user = rs.getString("Pseaudo_Cl");
            }
            liste_cl.setValue(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        
        try {

            ObservableList options2 = FXCollections.observableArrayList();

            String sql3 = "select nom_SousCat from sous_categorie where id_SousCat =?";
            ste=mc.prepareStatement(sql3);
             ste.setInt(1,vd.getId_souscat());
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                sous_c = rs.getString("nom_SousCat");
            }
            liste_sc.setValue(sous_c);
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        date_video.setText(""+vd.getDate_video());
        btn_delete.setVisible(true);
        btn_edit.setVisible(true);
        if(txt_url_video.getText().length()>0){
            
        }
        File folder = new File("src/Media/");
        File[] listOfFiles = folder.listFiles();

            for (File file : listOfFiles) {
                if (file.isFile()) {
//                    System.out.println(file.getName());
//                    System.out.println(txt_url_video);
                    if (file.getName().equals(txt_url_video.getText())){
                        btn_delete.setVisible(true);
                        btn_edit.setVisible(true);
                        btn_view_video.setVisible(true);
                        btn_upload.setVisible(false);
                        break;
                    }
                    else{
                        
                        btn_view_video.setVisible(false);
                        btn_upload.setVisible(true);
                    }
                    
                    
                }
            }
    }

    @FXML
    private void add_video(ActionEvent event) {
        try {
            String id_cl = liste_cl.getSelectionModel().getSelectedItem().toString();
            String id_s_c = liste_sc.getSelectionModel().getSelectedItem().toString();
            
            
            String sql2 = "select id_cl from client where Pseaudo_Cl =?";
            int idc = select_id_cl(sql2, id_cl);
            
            
            String sql3 = "select id_SousCat from sous_categorie where nom_SousCat =?";
            int id_sc = select_id_sc(sql3, id_s_c);
            String dsc = txt_description.getText();
            
            String nom = txt_nom_video.getText();
            String url = "";
            VideoUploadee video = new VideoUploadee(1, nom, dsc, url, id_sc, idc);
            Video_UploadeController vC = new Video_UploadeController();
            vC.ajouterVideo(video);
            search_video(event);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            
        
        
        
    }

    @FXML
    private void delete_video(ActionEvent event) {
        int id=Integer.parseInt(txt_id.getText());
        Video_UploadeController vc = new Video_UploadeController();
        vc.DeleteVideo(id);
        refreshTable(event);
        search_video(event);
        
    }

    @FXML
    private void edit_video(ActionEvent event) {
        try {
            String id_cl = liste_cl.getSelectionModel().getSelectedItem().toString();
            String id_s_c = liste_sc.getSelectionModel().getSelectedItem().toString();
            
            String sql2 = "select id_cl from client where Pseaudo_Cl =?";
            int idc = select_id_cl(sql2, id_cl);
            
            
            String sql3 = "select id_SousCat from sous_categorie where nom_SousCat =?";
            int id_sc = select_id_sc(sql3, id_s_c);
            
            int id = Integer.parseInt(txt_id.getText());
            String dsc = txt_description.getText();
            
            String nom = txt_nom_video.getText();
            String url = txt_url_video.getText();
            VideoUploadee video = new VideoUploadee(id, nom, dsc, url, id_sc, idc);
            Video_UploadeController vC = new Video_UploadeController();
            vC.UpdateVideo(video);
            search_video(event);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void disselect(ActionEvent event) {
        txt_id.setText("");
        txt_nom_video.setText("");
        txt_url_video.setText("");
        txt_description.setText("");
        liste_cl.setValue("");
        liste_sc.setValue("");
        date_video.setText("");
        btn_delete.setVisible(false);
        btn_edit.setVisible(false);
    }

    @FXML
    private void refreshTable(ActionEvent event) {
        Video_UploadeController vC = new Video_UploadeController();
        ObservableList<VideoUploadee> list = vC.afficherVideos();
        list.clear();
        showVideo();
        txt_search.setText("");
        disselect(event);
    }

    @FXML
    private void search_video(ActionEvent event) {
        FilteredList<VideoUploadee> filtered_data;
        Video_UploadeController vC = new Video_UploadeController();
        ObservableList<VideoUploadee> list = vC.afficherVideos();
        
        col_id.setCellValueFactory(new PropertyValueFactory<VideoUploadee, Integer>("id_vdeo"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date_video"));
        col_description.setCellValueFactory(new PropertyValueFactory<>("description_video"));
        col_nom_video.setCellValueFactory(new PropertyValueFactory<>("nom_video"));
        col_url.setCellValueFactory(new PropertyValueFactory<>("url_video"));
        col_id_client.setCellValueFactory(new PropertyValueFactory<>("id_cl"));
        col_id_sous.setCellValueFactory(new PropertyValueFactory<>("id_souscat"));
        table_video.setItems(list);
        filtered_data = new FilteredList<>(list, b -> true);
        txt_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filtered_data.setPredicate(video -> {
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if(video.getNom_video().toLowerCase().indexOf(lowerCaseFilter)!= -1){
                    return true;
                }
                else if(video.getDescription_video().toLowerCase().indexOf(lowerCaseFilter)!= -1){
                    return true;
                }
                else return false;
            });
            
        });
        SortedList<VideoUploadee> sortedData = new SortedList<>(filtered_data);
        sortedData.comparatorProperty().bind(table_video.comparatorProperty());
        table_video.setItems(sortedData);
    }
    
    void search(){
        FilteredList<VideoUploadee> filtered_data;
        Video_UploadeController vC = new Video_UploadeController();
        ObservableList<VideoUploadee> list = vC.afficherVideos();
        
        col_id.setCellValueFactory(new PropertyValueFactory<VideoUploadee, Integer>("id_vdeo"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date_video"));
        col_description.setCellValueFactory(new PropertyValueFactory<>("description_video"));
        col_nom_video.setCellValueFactory(new PropertyValueFactory<>("nom_video"));
        col_url.setCellValueFactory(new PropertyValueFactory<>("url_video"));
        col_id_client.setCellValueFactory(new PropertyValueFactory<>("id_cl"));
        col_id_sous.setCellValueFactory(new PropertyValueFactory<>("id_souscat"));
        table_video.setItems(list);
        filtered_data = new FilteredList<>(list, b -> true);
        txt_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filtered_data.setPredicate(video -> {
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if(video.getNom_video().toLowerCase().indexOf(lowerCaseFilter)!= -1){
                    return true;
                }
                else if(video.getDescription_video().toLowerCase().indexOf(lowerCaseFilter)!= -1){
                    return true;
                }
                else return false;
            });
            
        });
        SortedList<VideoUploadee> sortedData = new SortedList<>(filtered_data);
        sortedData.comparatorProperty().bind(table_video.comparatorProperty());
        table_video.setItems(sortedData);
    }

    @FXML
    private void search_messages_par_contenu(ActionEvent event) {
    }
    
    public void showVideo(){
        Video_UploadeController vC = new Video_UploadeController();
        ObservableList<VideoUploadee> list = vC.afficherVideos();
        
        col_id.setCellValueFactory(new PropertyValueFactory<VideoUploadee, Integer>("id_vdeo"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date_video"));
        col_description.setCellValueFactory(new PropertyValueFactory<>("description_video"));
        col_nom_video.setCellValueFactory(new PropertyValueFactory<>("nom_video"));
        col_url.setCellValueFactory(new PropertyValueFactory<>("url_video"));
        col_id_client.setCellValueFactory(new PropertyValueFactory<>("id_cl"));
        col_id_sous.setCellValueFactory(new PropertyValueFactory<>("id_souscat"));
        table_video.setItems(list);
    }

    @FXML
    private void upload(ActionEvent event) {
        try{
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            fileChooser.getExtensionFilters().addAll(
                    new ExtensionFilter("MP4 Files", "*.mp4")
//                    new ExtensionFilter("MKV Files", "*.mkv")
            );
            File selectedFile = fileChooser.showOpenDialog(stage);
            
            if(selectedFile != null){
                
                Path from = Paths.get(selectedFile.toURI());
                Path to = Paths.get(path+"\\"+txt_id.getText()+".mp4");
                CopyOption[] options = new CopyOption[]{
                    StandardCopyOption.REPLACE_EXISTING,
                    StandardCopyOption.COPY_ATTRIBUTES
                };
                Files.copy(from, to, options);
                System.out.println("added");
//                saveSystem(selectedFile, )
            }
            String dsc = txt_description.getText();
        String id_cl = txt_id_cl.getText();
        String sous_cat = txt_id_sous_cat.getText();

        int s_c = Integer.parseInt(sous_cat);
        int c_l = Integer.parseInt(id_cl);
        String nom = txt_nom_video.getText();
        String url = txt_id.getText()+".mp4";
        VideoUploadee video = new VideoUploadee(Integer.parseInt(txt_id.getText()), nom, dsc, url, s_c, c_l);
        Video_UploadeController vC = new Video_UploadeController();
        vC.UpdateVideo(video);
            search_video(event);
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    

    @FXML
    private void viewVideo(ActionEvent event) {
        try {
            
            Video_playController.id = Integer.parseInt(txt_id.getText());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("video_play.fxml"));
            Parent root = loader.load();
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
    private int select_id_cl(String sql, String id_cl) throws SQLException{
        int idc = 0;
        mc = MaConnexion.getInstance().getCnx();
            ste = mc.prepareStatement(sql);

            ste.setString(1, id_cl);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                idc = rs.getInt("id_cl");
            }
            return idc;
    }
    
    private int select_id_sc(String sql, String id_s_c) throws SQLException{
        int idsc = 0;
        mc = MaConnexion.getInstance().getCnx();
        ste = mc.prepareStatement(sql);

        ste.setString(1, id_s_c);
        ResultSet rs1 = ste.executeQuery();
        while (rs1.next()) {
            idsc = rs1.getInt("id_SousCat");
        }
        return idsc;
    }
    
    private void fill_client_combo() {
        mc = MaConnexion.getInstance().getCnx();
        try {

            ObservableList options2 = FXCollections.observableArrayList();

            String sql2 = "select Pseaudo_Cl from client order by id_cl";
            ResultSet rs = mc.createStatement().executeQuery(sql2);
            while (rs.next()) {
                options2.add(rs.getString(1));
            }
            liste_cl.setItems(options2);
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    private void fill_s_c_combo() {
        mc = MaConnexion.getInstance().getCnx();
        try {

            ObservableList options3 = FXCollections.observableArrayList();

            String sql2 = "select nom_sousCat from sous_categorie sc"
                    + "inner join categorie c on sc where order by id_sousCat";
            ResultSet rs = mc.createStatement().executeQuery(sql2);
            while (rs.next()) {
                options3.add(rs.getString(1));
            }
            liste_sc.setItems(options3);
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }
    
}
