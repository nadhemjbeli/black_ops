/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI.gestion_communaute.user.video_list_user;

import black_ops.Controller.Nav_barController;
import black_ops.Controller.Video_UploadeController;
import black_ops.Entity.Nav_Bar;
import black_ops.Entity.VideoUploadee;
import black_ops.GUI.gestion_communaute.Video_playController;
import black_ops.GUI.gestion_communaute.classes.VideoClient;
import black_ops.GUI.gestion_communaute.user.video_user_play.Video_user_playController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class Video_list_userController implements Initializable {

    @FXML
    private AnchorPane anchorid;
    public static String client_name = "nadhem";
    public static int id_sous_categorie = 1;
    @FXML
    private ComboBox ComboJeux;
    @FXML
    private ComboBox ComboStream;
    @FXML
    private ComboBox ComboCompétition;
    @FXML
    private ComboBox ComboCommunaute;
    @FXML
    private FontAwesomeIconView ComboUser;

    private Stage stage;
 private Scene scene;
 private Parent root;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Navbar();
        ComboBoxListInti();
        show_vids();
    }    
    
    
    private void show_vids() {
        final ScrollPane sp = new ScrollPane();
        final VBox vb = new VBox();
//        vb.setAlignment(Pos.BOTTOM_LEFT);
        Video_UploadeController vcl = new Video_UploadeController();
        String path = "src/Images/Imageclient/";
//        ScrollPane scroll = new ScrollPane();
//        scroll.setContent(checkboxContainer); 
        sp.setId("sp");
        vb.setId("vb");
        ObservableList<VideoClient> listvc = vcl.afficherSousCatMessagesEtClients(id_sous_categorie);
//        System.out.println(listmc);
        
        anchorid.getChildren().addAll(sp);
        VBox.setVgrow(sp, Priority.ALWAYS);
        HBox.setHgrow(sp, Priority.ALWAYS);
        int index = 0;
        
            List<HBox> lvb = new ArrayList();
        for(VideoClient l:listvc){
            VBox vb1 = new VBox();
            VBox vb3 = new VBox();
            VBox vb_all = new VBox();
            vb1.setId("vb1");
            vb_all.setId("vb_all");
            HBox hb = new HBox();
//            lvb.add(hb);
            String Path_name = new File("src/Images/Imageclient").getAbsolutePath();
            String image = Path_name+ "\\" + l.getClient().getPseaudo_Cl() + ".PNG";
            ImageView i = new ImageView();
            File f =  new File (image);
            Image im =new Image(f.toURI().toString());
            sp.setHbarPolicy(ScrollBarPolicy.NEVER);
            sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
            
            Label lbl_no_vid = new Label("");  
            HBox hb2 = new HBox();
            
            if(l.getClient().getPseaudo_Cl().equals(client_name)){
                Label labelName = new Label(""+l.getClient().getPseaudo_Cl());
                Label labeluploaded = new Label(" uploaded a video ");
                Label labelempty = new Label("");
                labeluploaded.setId("labeluploaded");
                Label labelVideoName = new Label(l.getVideo().getNom_video());
                labelVideoName.setId("video_name");
                Label labelDesc = new Label(""+l.getVideo().getDescription_video());
                Label labelDate = new Label(""+l.getVideo().getDate_video());
                Label labelyou = new Label(" (you)");
                labelyou.setId("labelyou");
                labelDate.setAlignment(Pos.CENTER_RIGHT);



                hb.setId("hb");
                labelName.setId("labelName");
                labelDesc.setId("labelContenu");
                labelDate.setId("labelDate");
                HBox hb1 = new HBox();
                HBox hb3 = new HBox();
                hb1.setId("hb1");
                Button btn = new Button("view video");
                btn.setAlignment(Pos.TOP_RIGHT);
                btn.setCursor(Cursor.HAND);
                btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    try {
                        Scene scene;

                        Video_user_playController.id = l.getVideo().getId_vdeo();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/black_ops/GUI/gestion_communaute/user/video_user_play/video_user_play.fxml"));
                        Parent root = (Parent) loader.load();


                         
                        scene = new Scene(root);
                        Stage stage = new Stage();


                        stage.setScene(scene);
//                        stage.initStyle(StageStyle.UNDECORATED);
                        
                        stage.setOnCloseRequest(evt -> {
                            // prevent window from closing
                            evt.consume();

                            // execute own shutdown procedure
                            shutdown(stage);
                        });
                        stage.show();

                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }});
    //                hb1.getChildren().addAll(labelDesc, btn);
    //                vb1.getChildren().add(hb1);
                i.setImage(im);
                i.setId("image");
                i.setFitWidth(50);
                i.setFitHeight(50);
                hb1.getChildren().addAll(i,labelempty, labelName, labelyou);
                File folder = new File("src/Media/");
                File[] listOfFiles = folder.listFiles();
                Button btn_delete = new Button("delete");
                    btn_delete.setAlignment(Pos.TOP_RIGHT);
                    btn_delete.setCursor(Cursor.HAND);
                    btn_delete.setId("delete");
                    btn_delete.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            int id=l.getVideo().getId_vdeo();
                            Video_UploadeController vc = new Video_UploadeController();
                            Alert alert = new Alert(Alert.AlertType.NONE, "Efassez le video? Video?", ButtonType.YES, ButtonType.NO);
                            if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                                vc.DeleteVideo(id);
                                show_vids();
                            }
                        }
                        
                    });
                
                for (File file : listOfFiles) {
                    System.out.println(file.getName());
                    if (file.isFile() && file.getName().equals(l.getVideo().getId_vdeo()+".mp4")) {
                        btn.setAlignment(Pos.TOP_RIGHT);
                        btn.setCursor(Cursor.HAND);
                        btn.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            try {
                                Scene scene;

                                Video_user_playController.id = l.getVideo().getId_vdeo();
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/black_ops/GUI/gestion_communaute/user/video_user_play/video_user_play.fxml"));
                                Parent root = (Parent) loader.load();



                                scene = new Scene(root);
                                Stage stage = new Stage();
                                stage.setOnCloseRequest(evt -> {
                                    // prevent window from closing
                                    evt.consume();
                                    Video_user_playController.mp.stop();
                                    // execute own shutdown procedure
                                    shutdown(stage);

                                });

                                stage.setScene(scene);
        //                                    stage.initStyle(StageStyle.UNDECORATED);
                                stage.show();

                            } catch (IOException ex) {
                                System.out.println(ex.getMessage());
                            }
                        }});
                        lbl_no_vid.setText("");
                        hb3.getChildren().addAll(btn, btn_delete);
                        vb1.getChildren().addAll(hb1, labelVideoName, hb3, labelDesc);
                        break;
                        
                    }
                    else{
                        lbl_no_vid.setText("Pas de video");
                    }
                        
                        
                    
                }
                if(lbl_no_vid.getText().equals("Pas de video")){
                    Button btn_upload = new Button("upload");
                    btn_upload.setAlignment(Pos.TOP_RIGHT);
                    btn_upload.setCursor(Cursor.HAND);
                    btn_upload.setId("upload");
                    btn_upload.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
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
                                    Path to = Paths.get(folder+"\\"+l.getVideo().getId_vdeo()+".mp4");
                                    CopyOption[] options = new CopyOption[]{
                                        StandardCopyOption.REPLACE_EXISTING,
                                        StandardCopyOption.COPY_ATTRIBUTES
                                    };
                                    Files.copy(from, to, options);
                                    System.out.println("added");
                    //                saveSystem(selectedFile, )
                                }

                                String url = l.getVideo().getId_vdeo()+".mp4";
                                VideoUploadee video = l.getVideo();
                                System.out.println(l.getVideo());
                                System.out.println(url);
                                video.setUrl_video(url);
                                Video_UploadeController vC = new Video_UploadeController();
                                vC.UpdateVideo(video);
                                show_vids();
                            }catch(Exception e){
                                System.out.println(e.getMessage());
                            }
                        }
                        
                    });
                    
                    
                    
                    lbl_no_vid.setId("no_vid");
                    hb2.getChildren().addAll(lbl_no_vid, btn_upload, btn_delete);
                    vb1.getChildren().addAll(hb1, labelVideoName, hb2, labelDesc);
                }
    //            vb2.getChildren().addAll(labelempty, labelempty, labelempty);
                vb3.getChildren().addAll(labelDate, labelempty);
                hb.getChildren().addAll(vb1, vb3);
                vb_all.getChildren().add(hb);
            }
            else{
                Label labelName = new Label(""+l.getClient().getPseaudo_Cl());
                Label labeluploaded = new Label(" uploaded a video ");
                Label labelempty = new Label("");

                labeluploaded.setId("labeluploaded");
                Label labelVideoName = new Label(l.getVideo().getNom_video());
                labelVideoName.setId("video_name");
                Label labelDesc = new Label(""+l.getVideo().getDescription_video());
                Label labelDate = new Label(""+l.getVideo().getDate_video());
                
                labelDate.setAlignment(Pos.CENTER_RIGHT);



                hb.setId("hb");
                labelName.setId("labelName");
                labelDesc.setId("labelContenu");
                labelDate.setId("labelDate");
                HBox hb1 = new HBox();
                hb1.setId("hb1");
                i.setImage(im);
                i.setId("image");
                i.setFitWidth(50);
                i.setFitHeight(50);
                hb1.getChildren().addAll(i,labelempty, labelName);
                File folder = new File("src/Media/");
                File[] listOfFiles = folder.listFiles();
                Button btn = new Button("view video");
                for (File file : listOfFiles) {
                    System.out.println(file.getName());
                    if (file.isFile() && file.getName().equals(l.getVideo().getId_vdeo()+".mp4")) {
                        btn.setAlignment(Pos.TOP_RIGHT);
                        btn.setCursor(Cursor.HAND);
                        btn.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            try {
                                Scene scene;

                                Video_user_playController.id = l.getVideo().getId_vdeo();
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/black_ops/GUI/gestion_communaute/user/video_user_play/video_user_play.fxml"));
                                Parent root = (Parent) loader.load();



                                scene = new Scene(root);
                                Stage stage = new Stage();
                                stage.setOnCloseRequest(evt -> {
                                    // prevent window from closing
                                    evt.consume();
                                    Video_user_playController.mp.stop();
                                    // execute own shutdown procedure
                                    shutdown(stage);

                                });

                                stage.setScene(scene);
        //                                    stage.initStyle(StageStyle.UNDECORATED);
                                stage.show();

                            } catch (IOException ex) {
                                System.out.println(ex.getMessage());
                            }
                        }});
                        lbl_no_vid.setText("");
                        vb1.getChildren().addAll(hb1, labelVideoName, btn, labelDesc);
                        break;
                        
                    }
                    else{
                        lbl_no_vid.setText("Pas de video");
                    }
                        
                        
                    
                }
                if(lbl_no_vid.getText().equals("Pas de video")){
                    
                    
                    
                    lbl_no_vid.setId("no_vid");
                    hb2.getChildren().addAll(lbl_no_vid);
                    vb1.getChildren().addAll(hb1, labelVideoName, hb2, labelDesc);
                }
                
                
    //                hb1.getChildren().addAll(labelDesc, btn);
    //                vb1.getChildren().add(hb1);
                
                
    //            vb2.getChildren().addAll(labelempty, labelempty, labelempty);
                vb3.getChildren().addAll(labelDate, labelempty);
                hb.getChildren().addAll(vb1, vb3);
                vb_all.getChildren().add(hb);
            }
                
                
                vb.getChildren().add(vb_all);
            
            
            
            
            index++;
           
        }
        
        sp.setPrefSize(700, 700);
        sp.setContent(vb);
        
    }
    
    private void shutdown(Stage mainWindow) {
    // you could also use your logout window / whatever here instead
    Alert alert = new Alert(Alert.AlertType.NONE, "Really close the Video?", ButtonType.YES, ButtonType.NO);
    if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
        // you may need to close other windows or replace this with Platform.exit();
        Video_user_playController.mp.stop();
        mainWindow.close();
    }
}
    
    
    

    @FXML
    private void scene_add_video(ActionEvent event) {
        try {
                
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/black_ops/GUI/gestion_communaute/user/video_uploade_user.fxml"));
            Parent root = loader.load();
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
//            lbl_title.setText(""+liste_sc.getSelectionModel().getSelectedItem().toString());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
//    @FXML
//    private void Showj(ActionEvent event) throws IOException {
//        root = FXMLLoader.load(getClass().getResource("/black_ops/GUI_User/Jeux/gestionjeuxVidéos/jeux.fxml"));
//
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
    
    @FXML
    private void showComp(ActionEvent event) throws Exception {

        String s = ComboCompétition.getSelectionModel().getSelectedItem().toString();
        
        switch (s) {
            case "Defi":
                SwitchScene(stage, "Competition/Defi/Defi", event);
                break;
            case "Match":
                SwitchScene(stage, "Competition/Match/ListMatch", event);
                break;
            case "Joueur":
                SwitchScene(stage, "Competition/Joueur/InscriptionJoueur", event);
                break;
            case "Equipe":
                SwitchScene(stage, "Competition/Equipe/InscriptionEquipe", event);
                break;
        }

    }

    @FXML
    private void Showj(ActionEvent event) throws IOException {
        try {
            String s = ComboJeux.getSelectionModel().getSelectedItem().toString();
            switch (s){
                case"jeux":
                    SwitchScene(stage, "Jeux/gestionjeuxVidéos/jeux", event);
                    break;
            }
//            root = FXMLLoader.load(getClass().getResource("black_ops/GUI_User/Jeux/gestionjeuxVidéos/jeux.fxml"));
//            
//            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//            scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @FXML
    private void showComu(ActionEvent event) throws Exception {
        String s = ComboCommunaute.getSelectionModel().getSelectedItem().toString();
        switch (s) {
            case "chat":
                SwitchScene_comu(stage, "gestion_communaute/chat", event);
                break;
            case "video":
                SwitchScene_comu(stage, "gestion_communaute/user/video_list_user/video_list_user", event);
                break;
        }
    }

    public void SwitchScene(Stage stage, String nom, ActionEvent event) throws Exception {

        String path = "/black_ops/GUI_User/";

        FXMLLoader loader = new FXMLLoader(getClass().getResource(path + nom + ".fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void ComboBoxListInti() {
        
        ObservableList<String> GestionCommunaute = FXCollections.observableArrayList("chat", "video");
        ComboCommunaute.setItems(GestionCommunaute);
        ObservableList<String> GestionCompetition = FXCollections.observableArrayList("Defi", "Match", "Joueur", "Equipe");
        ComboCompétition.setItems(GestionCompetition);  
        ObservableList<String> GestionJeux = FXCollections.observableArrayList("jeux");
        ComboJeux.setItems(GestionJeux);
       
    }
    
    public void SwitchScene_comu(Stage stage, String nom, ActionEvent event) throws Exception {

        String path = "/black_ops/GUI/";

        FXMLLoader loader = new FXMLLoader(getClass().getResource(path + nom + ".fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void Navbar(){
     
    
       
     Nav_barController navbar = new Nav_barController();
  
    ObservableList<Nav_Bar> list = navbar.afficherNavBar();
    
      ObservableList data = FXCollections.observableArrayList();
    for (Nav_Bar l:list){
        
       data.add(l.getNom());
        
    }
    ComboStream.setItems(data);
        
        
  
     
     
     
 }   
    @FXML
    private void NvabarTarget(ActionEvent event) {
        
      
           if (ComboStream.getValue().equals("Live"))
           {
                   try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/black_ops/GUI/DefiLive.fxml"));
            Parent root = loader.load();
       
            
            ComboStream.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
               
               
           }
        if (ComboStream.getValue().equals("Replay"))
           {
               
                           try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/black_ops/GUI/FrontUserVideo.fxml"));
            Parent root = loader.load();
       
            
            ComboStream.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
               
           }
        if (ComboStream.getValue().equals( "Information"))
           {
               
                try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/black_ops/GUI/FrontUser.fxml"));
            Parent root = loader.load();
       
            
            ComboStream.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
               
           }
        
    }

    @FXML
    private void Accueil(MouseEvent event) {
            try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/black_ops/GUI/AccueilPage.fxml"));
            Parent root = loader.load();
       
            
            ComboStream.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    

    
    
    
    
    
}
