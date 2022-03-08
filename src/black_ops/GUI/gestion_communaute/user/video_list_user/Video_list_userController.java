/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI.gestion_communaute.user.video_list_user;

import black_ops.Controller.Video_UploadeController;
import black_ops.GUI.gestion_communaute.Video_playController;
import black_ops.GUI.gestion_communaute.classes.VideoClient;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
                
//                vb1.getChildren().add(hb);
                Button btn = new Button("view video");
                btn.setAlignment(Pos.TOP_RIGHT);
                btn.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            try {
                                Video_playController.id = l.getVideo().getId_vdeo();
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/black_ops/GUI/gestion_communaute/video_play.fxml"));
                                Parent root = (Parent) loader.load();
                                
                                
                                Scene scene = new Scene(root);
                                Stage stage = new Stage();

                                stage.setScene(scene);
                                stage.show();

                            } catch (IOException ex) {
                                System.out.println(ex.getMessage());
                            }
                        }
                    });
//                hb1.getChildren().addAll(labelDesc, btn);
//                vb1.getChildren().add(hb1);
            i.setImage(im);
            i.setId("image");
            i.setFitWidth(50);
            i.setFitHeight(50);
            hb1.getChildren().addAll(i,labelempty, labelName);
            vb1.getChildren().addAll(hb1, labelVideoName, btn, labelDesc);
//            vb2.getChildren().addAll(labelempty, labelempty, labelempty);
            vb3.getChildren().addAll(labelDate, labelempty);
            hb.getChildren().addAll(vb1, vb3);
            vb_all.getChildren().add(hb);
            
            
                
                
                
                
                
                vb.getChildren().add(vb_all);
//            String Path_name = new File("src/Images/Imageclient").getAbsolutePath();
//            String image = Path_name+ "\\" + l.getClient().getPseaudo_Cl() + ".PNG";
//            ImageView i = new ImageView();
//            File f =  new File (image);
//            Image im =new Image(f.toURI().toString());
//            VBox vb1 = new VBox();
//            if(!l.getClient().getPseaudo_Cl().equals(client_name)){
//                
//                labelDate.setAlignment(Pos.TOP_RIGHT);
//            
//                i.setImage(im);
//                i.setId("image");
//                i.setFitWidth(50);
//                i.setFitHeight(50);
//                hb.getChildren().addAll(i, labelName);
//                vb1.getChildren().add(hb);
//
//                vb1.getChildren().add(labelContenu);
//
//                vb1.getChildren().add(labelDate);
//                
//                vb1.setId("vb1");
//                
//                vb.getChildren().add(vb1); 
//               
//                
//            }
//            else{
//                labelDate.setAlignment(Pos.TOP_RIGHT);
//            
//                i.setImage(im);
//                i.setId("image");
//                i.setFitWidth(50);
//                i.setFitHeight(50);
//                labelName.setAlignment(Pos.CENTER_LEFT);
//                hb.getChildren().addAll(labelName, i);
//                hb.setAlignment(Pos.TOP_RIGHT);
//                
//                vb1.getChildren().add(hb);
//
//                vb1.getChildren().add(labelContenu);
//
//                vb1.getChildren().add(labelDate);
//                vb1.setAlignment(Pos.TOP_RIGHT);
//                vb1.setId("vb1");
//                vb.getChildren().add(vb1); 
//            }
//            
//            
//            
////            sp.setFitToWidth(true);
////            sp.setFitToHeight(true);
////            sp.setVmax(600);
////            hb.getChildren().add(labelName);
////            hb.getChildren().add(labelId);
//            
    
//            }
//            else{
//                
//                hb.getChildren().add(hb1);
//                
//            }
            
            
            
            
            
            index++;
           
        }
        
        sp.setPrefSize(700, 700);
        sp.setContent(vb);
        
    }
    
    
    
    
    
    
    
}
