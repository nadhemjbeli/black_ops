/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI.gestion_communaute;

import black_ops.Controller.Message_Controller;
import black_ops.Controller.Nav_barController;
import black_ops.Entity.Client;
import black_ops.Entity.Messagee;
import black_ops.Entity.Nav_Bar;
import black_ops.GUI.gestion_communaute.classes.MessageClient;
import black_ops.GUI.gestion_communaute.user.Video_uploade_userController;
import black_ops.config.MaConnexion;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ChatController implements Initializable {

    @FXML
    private Button send_btn;
    private VBox scrolled;
    private ScrollPane sp;
    @FXML
    private AnchorPane anchorid;
    
    @FXML
    private JFXTextArea txt_message;
    @FXML
    private Label lbl_title;
    
    Stage stage;
    Scene scene;
    
    private Connection mc;
    
    PreparedStatement ste;
    private JFXComboBox liste_c;
    private JFXComboBox liste_sc;
    
    public ObservableList<String> cats;
    private JFXButton btn_communaute;
    private JFXButton btn_videos;
    private JFXButton btn_commande;
    
    public String path = "src/black_ops/GUI/";
    public static String client_name = "nadhem";
    public static int id_sous_categorie = 1;
    @FXML
    private ComboBox ComboJeux;
    @FXML
    private ComboBox ComboStream;
    @FXML
    private ComboBox ComboCompétition;
    @FXML
    private FontAwesomeIconView ComboUser;
    @FXML
    private ComboBox ComboCommunaute;
    private Parent root;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        fill_c_combo();
Navbar();
        ComboBoxListInti();
        System.out.println("username: "+client_name);
        System.out.println("sous_cat: "+id_sous_categorie);
        show_msgs();
            lbl_title.setText("Chat");
//            liste_sc.setValue(lbl_title.getText());
//        VBox.setVgrow(sp, Priority.ALWAYS);
    }    

    @FXML
    private void send_msg(ActionEvent event) {
        try{
            Message_Controller msgc = new Message_Controller();
            Client active_cl = msgc.afficher_client_dans_chat(client_name);
            System.out.println(active_cl);
    //        System.out.println("_________________________");
    //        ObservableList<MessageClient> listmsg = msgc.afficherMessagesEtClients();
    ////        System.out.println(listmsg);
    //        listmsg.forEach(System.out::println);
            String contenu = txt_message.getText();
                int id_client = active_cl.getId_Cl();
                int id_sc = id_sous_categorie;
                Messagee msg = new Messagee(1, contenu, id_client, id_sc);
            if(!contenu.isEmpty()){
                msgc.ajouterMessage(msg);
                show_msgs();
                txt_message.setText("");
                
            }
         
        }
        catch(Exception e){
            e.getMessage();
        }
        
    }
    private void clearTable(ActionEvent event) {
        Message_Controller mcl = new Message_Controller(); 
        ObservableList<Messagee> list = mcl.afficherMessagesOrderDate();
        list.clear();
    }
    
    private void show_msgs() {
        final ScrollPane sp = new ScrollPane();
        final VBox vb = new VBox();
        Message_Controller mcl = new Message_Controller();
        String path = "src/Images/Imageclient/";
        sp.setId("sp");
        vb.setId("vb");
        ObservableList<MessageClient> listmc = mcl.afficherSousCatMessagesEtClients();
        
        anchorid.getChildren().addAll(sp);
        VBox.setVgrow(sp, Priority.ALWAYS);
        HBox.setHgrow(sp, Priority.ALWAYS);
        for(MessageClient l:listmc){
            HBox hb = new HBox();
            sp.setHbarPolicy(ScrollBarPolicy.NEVER);
            sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
            Label labelId = new Label(""+l.getMessage().getId_cl());
            Label labelName = new Label(""+l.getClient().getPseaudo_Cl());
            Label labelContenu = new Label(""+l.getMessage().getContenu_message());
            Label labelDate = new Label(""+l.getMessage().getDate_message());
            hb.setId("hb");
            labelId.setId("labelId");
            labelName.setId("labelName");
            labelContenu.setId("labelContenu");
            labelDate.setId("labelDate");
            String Path_name = new File("src/Images/Imageclient").getAbsolutePath();
            String image = Path_name+ "\\" + l.getClient().getPseaudo_Cl() + ".PNG";
            
            ImageView i = new ImageView();
            File f =  new File (image);
            Image im =new Image(f.toURI().toString());
            VBox vb1 = new VBox();
            i.setImage(im);
            i.setId("image");
            i.setFitWidth(50);
            i.setFitHeight(50);
            if(!l.getClient().getPseaudo_Cl().equals(client_name)){
                
                labelDate.setAlignment(Pos.TOP_RIGHT);
            
                
                hb.getChildren().addAll(i, labelName);
                vb1.getChildren().add(hb);

                vb1.getChildren().add(labelContenu);

                vb1.getChildren().add(labelDate);
                
                vb1.setId("vb1");
                
                vb.getChildren().add(vb1); 
               
                
            }
            else{
                labelDate.setAlignment(Pos.TOP_LEFT);
            
                labelName.setAlignment(Pos.CENTER_LEFT);
                hb.getChildren().addAll(labelName, i);
                hb.setAlignment(Pos.TOP_RIGHT);
                
                vb1.getChildren().add(hb);

                vb1.getChildren().add(labelContenu);

                vb1.getChildren().add(labelDate);
                vb1.setAlignment(Pos.TOP_RIGHT);
                vb1.setId("vb1");
                vb.getChildren().add(vb1); 
            }
            
            
            
            
        }
        vb.setPrefSize(610, 580);
        sp.setPrefSize(630, 600);
        sp.setContent(vb);
    }
    
    private void fill_c_combo() {
        mc = MaConnexion.getInstance().getCnx();
        try {

            ObservableList options3 = FXCollections.observableArrayList();

            String sql2 = "select nom_Cat from categorie order by id_Cat";
            ResultSet rs = mc.createStatement().executeQuery(sql2);
            while (rs.next()) {
                options3.add(rs.getString(1));
            }
            liste_c.setItems(options3);
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }
    
    
    private void fill_s_c_combo() {
        mc = MaConnexion.getInstance().getCnx();
        try {

            ObservableList options3 = FXCollections.observableArrayList();

            String sql2 = "select nom_sousCat from sous_categorie order by id_sousCat";
            ResultSet rs = mc.createStatement().executeQuery(sql2);
            while (rs.next()) {
                options3.add(rs.getString(1));
            }
            liste_sc.setItems(options3);
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    
    private int select_id_sc(String scat) {
        int id_scat = 0;
        try {
            if (!scat.isEmpty()){
                String sql = "select id_souscat from sous_categorie"
                        + " where nom_souscat = '"+scat+"';";
                
                
                System.out.println(sql);
                ste=mc.prepareStatement(sql);
                ResultSet rs=ste.executeQuery();
                while(rs.next()){
                    id_scat = rs.getInt(1);

                }
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return id_scat;
    }
    
    private ObservableList<String> select_nom_scat(String sql) throws SQLException{
            ObservableList<String> list=FXCollections.emptyObservableList();
        
            list = FXCollections.observableArrayList();
            mc = MaConnexion.getInstance().getCnx();
            ste = mc.prepareStatement(sql);
            
            //        ste.setString(1, id_s_c);
            ResultSet rs1 = ste.executeQuery();
            while (rs1.next()) {
                String nomsc = rs1.getString(1);
                list.add(nomsc);
            }
            System.out.println(list);
            return list;
    }
    
    
    private String select_un_nom_scat() throws SQLException{
            String sql = "select nom_souscat from sous_categorie where id_souscat = "+id_sous_categorie+";";
            String nomsc = "";
            mc = MaConnexion.getInstance().getCnx();
            ste = mc.prepareStatement(sql);
            
            
            ResultSet rs1 = ste.executeQuery();
            while (rs1.next()) {
                nomsc = rs1.getString(1);
            }
            System.out.println(nomsc);
            return nomsc;
    }

    private void showSousCat(ActionEvent event) {
        String scat = liste_sc.getSelectionModel().getSelectedItem().toString();
        if(!scat.isEmpty()){
            
            id_sous_categorie = select_id_sc(scat);
            btn_communaute.setVisible(true);
            btn_videos.setVisible(true);
            btn_commande.setVisible(true);
        }else{
            btn_communaute.setVisible(false);
            btn_videos.setVisible(false);
            btn_commande.setVisible(false);
        }
        
    }

    private void showCat(ActionEvent event) {
        try{
            String nom_cat = liste_c.getSelectionModel().getSelectedItem().toString();
        String sql = "select sc.nom_souscat from categorie cat "
                + "inner join sous_categorie sc on sc.id_cat = cat.id_cat"
                + " where nom_cat ='"+nom_cat+"';";
        if(!nom_cat.isEmpty()){
            ObservableList<String> list; 
            list = select_nom_scat(sql);
            System.out.println(list);
            liste_sc.setItems(list);
        }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
        
    }

    private void scene_comm(ActionEvent event) {
        String com = ComboCommunaute.getSelectionModel().getSelectedItem().toString();
        
        
        if(com.equals("chat")){
            try {

    //            this.= id_sous_categorie;
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/black_ops/GUI/gestion_communaute/chat.fxml"));
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
        else if(ComboCommunaute.getValue().equals("video")){
            try {

    //            this.= id_sous_categorie;
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/black_ops/GUI/gestion_communaute/user/video_list_user/video_list_user.fxml"));
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
    }

    private void scene_video(ActionEvent event) {
        try {
            
            Video_uploade_userController.id_sous_categorie = id_sous_categorie;
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path+"gestion_communaute/user/video_list_user/video_list_user.fxml"));
            Parent root = loader.load();
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
   
    
    private void to_list_video(){
        
    }
    
    

    private void showU(ActionEvent event) throws IOException, Exception {
        SwitchScene(stage, "Gestion_Competition/Gestion_Defis/GestionDefi", event);
    }

    

   

    private void showS(ActionEvent event) throws Exception {
        String s = ComboStream.getSelectionModel().getSelectedItem().toString();
        switch (s) {
            case "Gestion Defi":
                SwitchScene(stage, "Gestion_Competition/Gestion_Defis/GestionDefi", event);
                break;
            case "Gestion Matches":
                SwitchScene(stage, "Gestion_Competition/Gestion_Details_Defis/GestionDetailsDefis", event);
                break;
            case "Gestion Joueurs":
                SwitchScene(stage, "Gestion_Competition/Gestion_Joueur/GestionJoueur", event);
                break;
            case "Gestion Equipe":
                SwitchScene(stage, "Gestion_Competition/Gestion_Equipe/GestionEquipe", event);
                break;
        }
    }

    

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

    @FXML
    private void showJ(ActionEvent event) {
    }

    
    
    
}
