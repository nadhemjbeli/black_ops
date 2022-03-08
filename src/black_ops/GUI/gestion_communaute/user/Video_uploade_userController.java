/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI.gestion_communaute.user;

import black_ops.Controller.Message_Controller;
import black_ops.Controller.Video_UploadeController;
import black_ops.Entity.Client;
import black_ops.Entity.VideoUploadee;
import black_ops.config.MaConnexion;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class Video_uploade_userController implements Initializable {

    @FXML
    private JFXTextField txt_nom_video;
    @FXML
    private JFXTextArea txt_desc_video;
    @FXML
    private VBox video_info;
    @FXML
    private VBox video_upload;
    
    public static String client_name = "nadhem";
    public static int id_sous_categorie = 1;
    
    Stage stage;
    Scene scene;
    
    private Connection mc;
    
    PreparedStatement ste;
    
    String path = new File("src/media").getAbsolutePath();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }  

    @FXML
    private void insert_video_desc(ActionEvent event) {
        String dsc = txt_desc_video.getText();
        Message_Controller msgc = new Message_Controller();
        Client active_cl = msgc.afficher_client_dans_chat(client_name);

        String nom = txt_nom_video.getText();
        String url = "";
        VideoUploadee video = new VideoUploadee(1, nom, dsc, url, 1, active_cl.getId_Cl());
        Video_UploadeController vC = new Video_UploadeController();
        vC.ajouterVideo(video);
        System.out.println(video);
        if(!txt_nom_video.getText().isEmpty() && !txt_desc_video.getText().isEmpty()){
            video_info.setVisible(false);
            video_upload.setVisible(true);
        }
    }

    @FXML
    private void upload_video(ActionEvent event) {
        int id_video = 0;
        VideoUploadee video = new VideoUploadee();
        try {
        String sql = "select * from video_uploade"
                + " where id_vdeo = (select max(id_vdeo) from video_uploade);";
        System.out.println(sql);
            mc=MaConnexion.getInstance().getCnx();
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                
                video.setId_vdeo(rs.getInt("id_vdeo"));
                video.setNom_video(rs.getString("nom_video"));
                video.setDate_video(rs.getDate("date_video"));
                video.setDescription_video(rs.getString("description_video"));
                video.setUrl_video(rs.getString("url_video"));
                video.setId_souscat(rs.getInt("id_souscat"));
                video.setId_cl(rs.getInt("id_cl"));
                
            }
        
            
            
        } 
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        upload(video);
    }
    
    
    private void upload(VideoUploadee video) {
        try{
            
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("MP4 Files", "*.mp4")
//                    new ExtensionFilter("MKV Files", "*.mkv")
            );
            File selectedFile = fileChooser.showOpenDialog(stage);
            
            if(selectedFile != null){
                
                Path from = Paths.get(selectedFile.toURI());
                Path to = Paths.get(path+"\\"+video.getId_vdeo()+".mp4");
                CopyOption[] options = new CopyOption[]{
                    StandardCopyOption.REPLACE_EXISTING,
                    StandardCopyOption.COPY_ATTRIBUTES
                };
                Files.copy(from, to, options);
                System.out.println("added");
//                saveSystem(selectedFile, )
            }
            

        
        String nom = txt_nom_video.getText();
        String url = video.getId_vdeo()+".mp4";
        VideoUploadee video_final = new VideoUploadee(
                video.getId_vdeo(), 
                video.getNom_video(), 
                video.getDescription_video(), 
                url, 
                video.getId_souscat(), 
                video.getId_cl()
        );
        Video_UploadeController vC = new Video_UploadeController();
        vC.UpdateVideo(video_final);
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
}
