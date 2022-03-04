/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI.gestion_jeux.gestionImages;

import black_ops.Controller.ImageController;
import black_ops.Entity.Image;
import black_ops.GUI.gestion_jeux.gestionChampions.ImageChampionController;
import black_ops.config.MaConnexion;
import com.jfoenix.controls.JFXComboBox;
import java.io.File;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author jmokh
 */
public class CRUDImageController implements Initializable {
    

    @FXML
    private TextArea txtid_image;
    @FXML
    private TextField txt_Url_image;
    private TextField txt_id_jeu;
    @FXML
    private TableView<Image>TVpics;
    @FXML
    private TableColumn<Image, Integer> colId_id_img;
    @FXML
    private TableColumn<Image, String> col_Url_img;
    @FXML
    private TableColumn<Image, Integer> col_Id_jeu;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
  
    @FXML
    private Button btn_add;
    @FXML
    private Button btn_search;
    @FXML
    private Button DIselect;
    @FXML
    private Button btn_refresh;
    @FXML
    private TextField searimage;
    @FXML
    private Button btnAffjeu;
    @FXML
    private Button btnaffi;
   
    @FXML
    public ComboBox ListeR; 
      public Connection mc;
    public PreparedStatement ste;
    @FXML
    private TextField id_message;
    @FXML
    private TextField date_message;
    @FXML
    private AnchorPane nav;
    @FXML
    private JFXComboBox gc;
    @FXML
    private JFXComboBox gcp;
    @FXML
    private Button gu;
    @FXML
    private JFXComboBox gCom;
    @FXML
    private JFXComboBox gcat;
    @FXML
    private JFXComboBox gS;
    @FXML
    private JFXComboBox gJ;
    @FXML
    private Button hide;
    @FXML
    private Button show;
  
   private Stage stage;
 private Scene scene;
 private Parent root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showpics();
        showActualId();
        fillcomboBox() ;
        ComboBoxListInti();
    }    

    @FXML
    private void handleMouseAction(MouseEvent event) {
        Image i = TVpics.getSelectionModel().getSelectedItem();
       txtid_image.setText(""+i.getId_Image());
    txt_Url_image.setText(""+i.getUrl_Image());
    try {
             
               String sql2="select Nom from Jeu where Id_Jeu=?";
               
             mc=MaConnexion.getInstance().getCnx();
             ste=mc.prepareStatement(sql2);
             
              
              ste.setInt(1,i.getId_jeu());
              ResultSet rs=ste.executeQuery();
               while(rs.next()){
        String     k=rs.getString("Nom");
        ListeR.setValue(k);
            
            
        } }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

         
    }

    @FXML
    private void AddPic(ActionEvent event) {
         int k=0;
        String id_jeu=ListeR.getSelectionModel().getSelectedItem().toString();
          
         try {
             
               String sql2="select Id_Jeu from Jeu where Nom=?";
               
             mc=MaConnexion.getInstance().getCnx();
             ste=mc.prepareStatement(sql2);
             
              
              ste.setString(1,id_jeu);
              ResultSet rs=ste.executeQuery();
               while(rs.next()){
             k=rs.getInt("Id_Jeu");
            
            
        } }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         
            String url = txt_Url_image.getText();
          
             Image i1 = new Image(3,url,k);
           ImageController ic1 = new ImageController();
            ic1.ajouterImage(i1);
            //notification code 
            String path="src\\ImagesChampions\\confirm.png";
            String Path_name = new File(path).getAbsolutePath();
        System.out.println(Path_name);
        ImageView i = new ImageView();
        File f =  new File (Path_name);
        javafx.scene.image.Image im =new javafx.scene.image.Image(f.toURI().toString());
             
      Notifications notificationBuilder;
        notificationBuilder = Notifications.create()
                .title("image ajouté avec succès")
                .text("L'image a bien été ajoutée")
                .graphic(new ImageView(im))
                .hideAfter(Duration.seconds(4))
                .position(Pos.TOP_RIGHT)
                
                .onAction((ActionEvent event1) -> {
                    System.out.println("Click on notification");
                });
          
         notificationBuilder.darkStyle();
       notificationBuilder.show();
      
            
            //
             showpics();
             showActualId();
    }

    @FXML
    private void UpdatePic(ActionEvent event) {
         int k=0;
        String id_jeu=ListeR.getSelectionModel().getSelectedItem().toString();
           try {
             
               String sql2="select Id_Jeu from Jeu where Nom=?";
               
             mc=MaConnexion.getInstance().getCnx();
             ste=mc.prepareStatement(sql2);
             
              
              ste.setString(1,id_jeu);
              ResultSet rs=ste.executeQuery();
               while(rs.next()){
             k=rs.getInt("Id_Jeu");
            
            
        } }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
           
        String idimg=txtid_image.getText();
         int v = Integer.parseInt(idimg);
        String url_img = txt_Url_image.getText();
          
            Image i1 = new Image(v,url_img,k);
            ImageController jc1 = new ImageController();
            jc1.updateImage(i1);
              //notif 
            String path="src\\ImagesChampions\\updateicone.png";
            String Path_name = new File(path).getAbsolutePath();
        System.out.println(Path_name);
        ImageView i = new ImageView();
        File f =  new File (Path_name);
        javafx.scene.image.Image im =new javafx.scene.image.Image(f.toURI().toString());
             
      Notifications notificationBuilder;
        notificationBuilder = Notifications.create()
                .title("Image modifié avec succès")
                .text("L'image a bien été modifiée")
                .graphic(new ImageView(im))
                .hideAfter(Duration.seconds(4))
                .position(Pos.TOP_RIGHT)
                
                .onAction((ActionEvent event1) -> {
                    System.out.println("Click on notification");
                });
          
         notificationBuilder.darkStyle();
       notificationBuilder.show();
      
            //
             showpics();
             showActualId();
    }

    @FXML
    private void DeletePic(ActionEvent event) {
         String idJeu=txtid_image.getText();
         int v = Integer.parseInt(idJeu);
             Image i1 = new Image(v);
            ImageController jc1 = new ImageController();
            jc1.deleteImage(i1);
                //notif 
            String path="src\\ImagesChampions\\suppression.png";
            String Path_name = new File(path).getAbsolutePath();
        System.out.println(Path_name);
        ImageView i = new ImageView();
        File f =  new File (Path_name);
        javafx.scene.image.Image im =new javafx.scene.image.Image(f.toURI().toString());
             
      Notifications notificationBuilder;
        notificationBuilder = Notifications.create()
                .title("image supprimé avec succès")
                .text("L'image a bien été supprimée")
                .graphic(new ImageView(im))
                .hideAfter(Duration.seconds(4))
                .position(Pos.TOP_RIGHT)
                
                .onAction((ActionEvent event1) -> {
                    System.out.println("Click on notification");
                });
          
         notificationBuilder.darkStyle();
       notificationBuilder.show();
      
            //
             showpics();
             showActualId();
    }
    public void showpics()
    {
        ImageController ic1 = new ImageController();
        ObservableList<Image> list = ic1.afficherImages();
        System.out.println(list);
     colId_id_img.setCellValueFactory(new PropertyValueFactory<Image,Integer>("Id_Image"));
     col_Url_img.setCellValueFactory(new PropertyValueFactory<Image,String>("Url_Image") );
     col_Id_jeu.setCellValueFactory(new PropertyValueFactory<Image,Integer>("Id_jeu") );
     TVpics.setItems(list);
     
     
     
    }


    private void SkinScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CRUDSkin.fxml"));
          
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
    }

    private void JeuScene(ActionEvent event) throws IOException {
         root = FXMLLoader.load(getClass().getResource("CRUDjeu.fxml"));
          
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
    }

    private void ChampScene(ActionEvent event) throws IOException {
             root = FXMLLoader.load(getClass().getResource("CRUDchampion.fxml"));
          
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
    }

    @FXML
    private void Refresh(ActionEvent event) {
         ImageController jc1 = new ImageController();
       
            ObservableList<Image> list = jc1.afficherImages();
            list.clear();
            showpics();
    }


    @FXML
    private void Search(ActionEvent event) {
        String url=searimage.getText();
         ImageController ic1 = new ImageController();
        ObservableList<Image> list = ic1.RechercherImage(url);
        System.out.println(list);
     colId_id_img.setCellValueFactory(new PropertyValueFactory<Image,Integer>("Id_Image"));
     col_Url_img.setCellValueFactory(new PropertyValueFactory<Image,String>("Url_Image") );
     col_Id_jeu.setCellValueFactory(new PropertyValueFactory<Image,Integer>("Id_jeu") );
     TVpics.setItems(list);
      //notif 
            String path="src\\ImagesChampions\\Recherche.png";
            String Path_name = new File(path).getAbsolutePath();
        System.out.println(Path_name);
        ImageView i = new ImageView();
        File f =  new File (Path_name);
        javafx.scene.image.Image im =new javafx.scene.image.Image(f.toURI().toString());
             
      Notifications notificationBuilder;
        notificationBuilder = Notifications.create()
                .title("opération terminé")
                .text("Recherche terminé avec succès")
                .graphic(new ImageView(im))
                .hideAfter(Duration.seconds(4))
                .position(Pos.TOP_RIGHT)
                
                .onAction((ActionEvent event1) -> {
                    System.out.println("Click on notification");
                });
          
         notificationBuilder.darkStyle();
       notificationBuilder.show();
       showActualId();
      
            //
    }

    @FXML
    private void DIselect(ActionEvent event) {
         txtid_image.setText("");
     txt_Url_image.setText("");
    ListeR.setValue("");
    showActualId();
    }

    @FXML
    private String Importer_img_Jeu(ActionEvent event) {
        String idimg=txtid_image.getText();
        Path to = null;
        Path to2=null;
         String  m = null;
         String path = "\\ImagesJeux";
         JFileChooser chooser = new JFileChooser();
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & PNG Images", "jpg","jpeg","PNG");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
           m = chooser.getSelectedFile().getAbsolutePath();
//            System.out.println("You chose to open this file: " +m
//                    );
            
            if(chooser.getSelectedFile() != null){
                
               try {
                   Path from = Paths.get(chooser.getSelectedFile().toURI());
                    to = Paths.get(path+"\\"+idimg+".png");
                      to2 = Paths.get("src\\"+path+"\\"+idimg+".png"); 
                   CopyOption[] options = new CopyOption[]{
                       StandardCopyOption.REPLACE_EXISTING,
                       StandardCopyOption.COPY_ATTRIBUTES
                   };
                   Files.copy(from, to2, options);
                   System.out.println("added");
//                saveSystem(selectedFile, )
               } catch (IOException ex) {
                   System.out.println();
               }
            }
             txt_Url_image.setText(to.toString());
        
    }
      return to.toString(); 
        
    }

    @FXML
    private void AfficherImage(ActionEvent event) {
         String path1= txt_Url_image.getText();
         String path_2="src\\"+path1;
         
       String idjeu=txt_id_jeu.getText();
        String Path_name = new File(path_2).getAbsolutePath();
        System.out.println(Path_name);
        ImageView i = new ImageView();
        File f =  new File (Path_name);
        javafx.scene.image.Image im =new javafx.scene.image.Image(f.toURI().toString());
       
        
         
      
        try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("AfficherImage.fxml"));
            Parent root=(Parent)loader.load();
            AfficherImageController ic = loader.getController();
            ic.function(im,idjeu);
//            AfficherSkinController ic2 = loader.getController();
//            ic2.function2(nom);
            Scene scene = new Scene(root);
            Stage stage=new Stage();
            
        stage.setScene(scene);
        stage.show();
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        
     
    }
    public void showActualId()
      {       Connection mc;
    PreparedStatement ste;
      mc=MaConnexion.getInstance().getCnx();
    String sql ="SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_SCHEMA = \"black_ops\" AND TABLE_NAME = \"image\";";
      
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
               int h= rs.getInt("AUTO_INCREMENT");
              String u = Integer.toString(h, 1);
               txtid_image.setText(u);
            } } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }

    @FXML
    private void fillcomboBox() {
          try  {
        mc=MaConnexion.getInstance().getCnx();
        ObservableList options2 =FXCollections.observableArrayList();
             String sql2="select Nom from Jeu";
                 ResultSet rs=mc.createStatement().executeQuery(sql2);               
                 while(rs.next()){
                     options2.add(rs.getString(1)); 
  
                 }
            ListeR.setItems(options2);
    }catch (Exception e)
    {
        System.out.println(e.getMessage());
    
    }
    
    }

    @FXML
    private void show_nav_bar(ActionEvent event) {
     nav.setVisible(true); 
     hide.setVisible(true);
     show.setVisible(false);
     
    }

  

    @FXML
    private void hide_nav_bar(ActionEvent event) {
        nav.setVisible(false); 
     hide.setVisible(false);
     show.setVisible(true);
    }

    @FXML
    private void showGC(ActionEvent event) {
        String s = gc.getSelectionModel().getSelectedItem().toString();
        
    }

    @FXML
    private void showComp(ActionEvent event) throws Exception {
        
        String s = gcp.getSelectionModel().getSelectedItem().toString();
//       
        switch(s){
            case "Gestion Defi" :
                 SwitchScene(stage,"Gestion_Competition/Gestion_Defis/GestionDefi",event);
                 break ;
            case "Gestion Matches" :
                    SwitchScene(stage,"Gestion_Competition/Gestion_Details_Defis/GestionDetailsDefis",event);
           break ; 
            case "Gestion Joueurs" :
                  SwitchScene(stage,"Gestion_Competition/Gestion_Joueur/GestionJoueur",event);
           break ;
            case "Gestion Equipe":
                SwitchScene(stage,"Gestion_Competition/Gestion_Equipe/GestionEquipe",event);
        }
   
    }

    @FXML
    private void showU(ActionEvent event) throws IOException, Exception {
        SwitchScene(stage,"Gestion_Competition/Gestion_Defis/GestionDefi",event);
        }  
        
    

    @FXML
    private void showCommande(ActionEvent event) throws Exception {
        String s = gCom.getSelectionModel().getSelectedItem().toString();
        switch(s){
            case "Gestion Defi" :
                 SwitchScene(stage,"Gestion_Competition/Gestion_Defis/GestionDefi",event);
                 break ;
            case "Gestion Matches" :
                    SwitchScene(stage,"Gestion_Competition/Gestion_Details_Defis/GestionDetailsDefis",event);
           break ; 
            case "Gestion Joueurs" :
                  SwitchScene(stage,"Gestion_Competition/Gestion_Joueur/GestionJoueur",event);
           break ;
            case "Gestion Equipe":
                SwitchScene(stage,"Gestion_Competition/Gestion_Equipe/GestionEquipe",event);
        }
        
    }

    @FXML
    private void showCat(ActionEvent event) throws Exception {
        String s = gcat.getSelectionModel().getSelectedItem().toString();
        switch(s){
            case "Gestion Defi" :
                 SwitchScene(stage,"Gestion_Competition/Gestion_Defis/GestionDefi",event);
                 break ;
            case "Gestion Matches" :
                    SwitchScene(stage,"Gestion_Competition/Gestion_Details_Defis/GestionDetailsDefis",event);
           break ; 
            case "Gestion Joueurs" :
                  SwitchScene(stage,"Gestion_Competition/Gestion_Joueur/GestionJoueur",event);
           break ;
            case "Gestion Equipe":
                SwitchScene(stage,"Gestion_Competition/Gestion_Equipe/GestionEquipe",event);
        }
        
    }

    @FXML
    private void showS(ActionEvent event) throws Exception {
        String s = gS.getSelectionModel().getSelectedItem().toString();
        switch(s){
                case "Gestion jeu" :
                 SwitchScene(stage,"Gestion_jeux/gestionjeu/CRUDjeu",event);
                 break ;
            case "Gestion Matches" :
                    SwitchScene(stage,"Gestion_Competition/Gestion_Details_Defis/GestionDetailsDefis",event);
           break ; 
            case "Gestion Joueurs" :
                  SwitchScene(stage,"Gestion_Competition/Gestion_Joueur/GestionJoueur",event);
           break ;
            case "Gestion Equipe":
                SwitchScene(stage,"Gestion_Competition/Gestion_Equipe/GestionEquipe",event);
        }
    }

    @FXML
    private void showJ(ActionEvent event) throws Exception {
        String s = gJ.getSelectionModel().getSelectedItem().toString();
        switch(s){
            case "Gestion Jeu" :
                 SwitchScene(stage,"Gestion_jeux/gestionjeu/CRUDjeu",event);
                 break ;
            case "Gestion Image" :
                    SwitchScene(stage,"Gestion_jeux/gestionImages/CRUDimage",event);
           break ; 
            case "Gestion Skin" :
                  SwitchScene(stage,"Gestion_jeux/gestionSkins/CRUDSkin",event);
           break ;
            case "Gestion Champion":
                SwitchScene(stage,"Gestion_jeux/gestionChampions/CRUDchampion",event);
                break ;
        }
    }
  
    public void SwitchScene(Stage stage ,String nom ,ActionEvent event ) throws Exception {
        
        String path = "/black_ops/GUI/" ;
        
    FXMLLoader loader = new FXMLLoader(getClass().getResource(path+nom+".fxml"));
            Parent root = loader.load();
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
    public void ComboBoxListInti(){
       nav.setVisible(false);  
       ObservableList<String> GestionCommunaute = FXCollections.observableArrayList(" Gestion Message" ,"Gestion Video");
       gc.setItems(GestionCommunaute);
       ObservableList<String> GestionCompetition = FXCollections.observableArrayList("Gestion Defi" ,"Gestion Matches" ,"Gestion Joueurs","Gestion Equipe");
       gcp.setItems(GestionCompetition);
       ObservableList<String> GestionCommande = FXCollections.observableArrayList("Gestion Commande" ,"Gestion  Ligne Commande" );
       gCom.setItems(GestionCommande);
       ObservableList<String> GestionCategorie = FXCollections.observableArrayList("Gestion Categorie" ,"Gestion  SousCategorie" );
       gcat.setItems(GestionCategorie);
        ObservableList<String> GestionStream = FXCollections.observableArrayList("Stream Replay " ,"Stream Info " );
       gS.setItems(GestionStream);
        ObservableList<String> GestionJeu = FXCollections.observableArrayList("Gestion Jeu" ,"Gestion Image", "Gestion Skin" ,"Gestion Champion"  );
       gJ.setItems(GestionJeu);
    }
}


