/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI.gestion_jeux.gestionSkins;

import black_ops.Controller.Admin_Controller;
import black_ops.Controller.Client_Controller;
import black_ops.Controller.SkinController;
import black_ops.Entity.Client;
import black_ops.Entity.Skin;
import black_ops.Entity.Super;
import black_ops.GUI.gestion_jeux.gestionChampions.ImageChampionController;
import black_ops.config.MaConnexion;
import com.jfoenix.controls.JFXComboBox;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
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
public class CRUDSkinController implements Initializable {

    @FXML
    private TextArea txtid_skin;
    @FXML
    private TextField txt_ImgSkin;
    private TextField txt_id_Champ;
    @FXML
    private TableColumn<Skin, Integer> colId_id_skin;
    @FXML
    private TableColumn<Skin,String> col_img_skin;
    @FXML
    private TableColumn<Skin, Integer> col_Id_champ;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView<Skin> TVskin;
     private Stage stage;
 private Scene scene;
 private Parent root;
   
    @FXML
    private Button DIselect;
    @FXML
    private FontAwesomeIconView btn_Diselect;
    @FXML
    private Button btn_search;
    @FXML
    private Button btn_refresh;
    @FXML
    private Button btnupdate;
    @FXML
    private TextField srchskin;
    @FXML
    private Button btnImport;
    @FXML
    private TextField id_message;
    @FXML
    private TextField date_message;
    @FXML
    private Button btnAffSkin;
    @FXML
    public ComboBox ListeR ;
      public Connection mc;
    public PreparedStatement ste;
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
    @FXML
    private Button mailsend;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       showSkins();
       showActualId();
       fillcomboBox();
       ComboBoxListInti();
    }    

    @FXML
    private void handleMouseAction(MouseEvent event) {
        Skin s = TVskin.getSelectionModel().getSelectedItem();
       txtid_skin.setText(""+s.getId_skin());
    txt_ImgSkin.setText(""+s.getImage_skin());
     try {
             
               String sql2="select Nom_Champ from champion where Id_Champ=?";
               
             mc=MaConnexion.getInstance().getCnx();
             ste=mc.prepareStatement(sql2);
             
              
              ste.setInt(1,s.getId_champ());
              ResultSet rs=ste.executeQuery();
               while(rs.next()){
        String     k=rs.getString("Nom_Champ");
        ListeR.setValue(k);
        
            } }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void AddSkin(ActionEvent event) {
        String imgskin = txt_ImgSkin.getText();
          int k=0;
        String id_jeu=ListeR.getSelectionModel().getSelectedItem().toString();
             
         try {
             
               String sql2="select Id_Champ from champion where Nom_Champ=?";
               
             mc=MaConnexion.getInstance().getCnx();
             ste=mc.prepareStatement(sql2);
             
              
              ste.setString(1,id_jeu);
              ResultSet rs=ste.executeQuery();
               while(rs.next()){
             k=rs.getInt("Id_Champ");
            
            
        } }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         
          
             Skin i1 = new Skin(4,imgskin,k);
          SkinController ic1 = new SkinController();
           if (txt_ImgSkin.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("Vous devez importer le skin du champion");
            alert.showAndWait();
            return;}
            ic1.ajouterSkin(i1);
            //notification code 
            String path="src\\Images\\ImagesChampions\\confirm.png";
            String Path_name = new File(path).getAbsolutePath();
        System.out.println(Path_name);
        ImageView i = new ImageView();
        File f =  new File (Path_name);
        Image im =new Image(f.toURI().toString());
             
      Notifications notificationBuilder;
        notificationBuilder = Notifications.create()
                .title("skin ajout?? avec succ??s")
                .text("Le skin a bien ??t?? ajout??")
                .graphic(new ImageView(im))
                .hideAfter(Duration.seconds(4))
                .position(Pos.TOP_RIGHT)
                
                .onAction((ActionEvent event1) -> {
                    System.out.println("Click on notification");
                });
          
         notificationBuilder.darkStyle();
       notificationBuilder.show();
      
            
            //
             showSkins();
             showActualId();
    }

    @FXML
    private void UpdateSkin(ActionEvent event) {
         int k=0;
        String id_jeu=ListeR.getSelectionModel().getSelectedItem().toString();
           try {
             
               String sql2="select Id_Champ from champion where Nom_Champ=?";
               
             mc=MaConnexion.getInstance().getCnx();
             ste=mc.prepareStatement(sql2);
             
              
              ste.setString(1,id_jeu);
              ResultSet rs=ste.executeQuery();
               while(rs.next()){
             k=rs.getInt("Id_Champ");
            
            
        } }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         String idskin=txtid_skin.getText();
         int v = Integer.parseInt(idskin);
         String imgskin = txt_ImgSkin.getText();
         
             Skin i1 = new Skin(v,imgskin,k);
          SkinController ic1 = new SkinController();
          Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
            alert2.setHeaderText("Warning");
            alert2.setContentText("Confirmation..!");
          if (txt_ImgSkin.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("Vous devez importer le skin du champion");
            alert.showAndWait();
            return;}
           Optional<ButtonType>result =  alert2.showAndWait();
              if(result.get() == ButtonType.OK){
            
            ic1.updateSkin(i1);
             //notif 
            String path="src\\Images\\ImagesChampions\\updateicone.png";
            String Path_name = new File(path).getAbsolutePath();
        System.out.println(Path_name);
        ImageView i = new ImageView();
        File f =  new File (Path_name);
        Image im =new Image(f.toURI().toString());
             
      Notifications notificationBuilder;
        notificationBuilder = Notifications.create()
                .title("skin modifi?? avec succ??s")
                .text("Le skin a bien ??t?? modifi??")
                .graphic(new ImageView(im))
                .hideAfter(Duration.seconds(4))
                .position(Pos.TOP_RIGHT)
                
                .onAction((ActionEvent event1) -> {
                    System.out.println("Click on notification");
                });
          
         notificationBuilder.darkStyle();
       notificationBuilder.show();
      
            //
             showSkins();
             showActualId();}
              else{
              txt_ImgSkin.setText(null);

        }
    }

    @FXML
    private void DeleteSkin(ActionEvent event) {
         String idskin=txtid_skin.getText();
         int v = Integer.parseInt(idskin);
            Skin i1 = new Skin(v);
           SkinController jc1 = new SkinController();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Warning");
            alert.setContentText("Confirmation..!");



        Optional<ButtonType>result =  alert.showAndWait();
        if(result.get() == ButtonType.OK){
            jc1.deleteSkin(i1);
             //notif 
            String path="src\\Images\\ImagesChampions\\suppression.png";
            String Path_name = new File(path).getAbsolutePath();
        System.out.println(Path_name);
        ImageView i = new ImageView();
        File f =  new File (Path_name);
        Image im =new Image(f.toURI().toString());
             
      Notifications notificationBuilder;
        notificationBuilder = Notifications.create()
                .title("skin supprim?? avec succ??s")
                .text("Le skin a bien ??t?? supprim??")
                .graphic(new ImageView(im))
                .hideAfter(Duration.seconds(4))
                .position(Pos.TOP_RIGHT)
                
                .onAction((ActionEvent event1) -> {
                    System.out.println("Click on notification");
                });
          
         notificationBuilder.darkStyle();
       notificationBuilder.show();
      
            //
            showSkins();
            showActualId();}
         else{
              txt_ImgSkin.setText(null);

        }
    }
    public void showSkins(){
     SkinController ic1 = new SkinController();
       
            ObservableList<Skin> list = ic1.afficherSkins();
     
     colId_id_skin.setCellValueFactory(new PropertyValueFactory<Skin,Integer>("Id_skin") );
     col_img_skin.setCellValueFactory(new PropertyValueFactory<Skin,String>("image_skin") );
     col_Id_champ.setCellValueFactory(new PropertyValueFactory<Skin,Integer>("Id_champ") );
     TVskin.setItems(list);
}

    private void jeuScene(ActionEvent event) throws IOException {
         root = FXMLLoader.load(getClass().getResource("CRUDjeu.fxml"));
          
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
    }

    private void ImageScene(ActionEvent event) throws IOException {
          root = FXMLLoader.load(getClass().getResource("CRUDimage.fxml"));
          
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
    }

    private void ChampionScene(ActionEvent event) throws IOException {
               root = FXMLLoader.load(getClass().getResource("CRUDchampion.fxml"));
          
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
    }

    @FXML
    private void Refresh(ActionEvent event) {
         SkinController jc1 = new SkinController();
       
            ObservableList<Skin> list = jc1.afficherSkins();
            list.clear();
            showSkins();
            showActualId();
    }


    @FXML
    private void DIselect(ActionEvent event) {
        txtid_skin.setText("");
    txt_ImgSkin.setText("");
   ListeR.setValue("");
    showActualId();}
    

    @FXML
    private void Search(ActionEvent event) {
        String skin =srchskin.getText();
         SkinController ic1 = new SkinController();
       
            ObservableList<Skin> list = ic1.RechercherSkin(skin);
     
     colId_id_skin.setCellValueFactory(new PropertyValueFactory<Skin,Integer>("Id_skin") );
     col_img_skin.setCellValueFactory(new PropertyValueFactory<Skin,String>("image_skin") );
     col_Id_champ.setCellValueFactory(new PropertyValueFactory<Skin,Integer>("Id_champ") );
     TVskin.setItems(list);
      //notif 
            String path="src\\Images\\ImagesChampions\\Recherche.png";
            String Path_name = new File(path).getAbsolutePath();
        System.out.println(Path_name);
        ImageView i = new ImageView();
        File f =  new File (Path_name);
        Image im =new Image(f.toURI().toString());
             
      Notifications notificationBuilder;
        notificationBuilder = Notifications.create()
                .title("op??ration termin??")
                .text("Recherche termin?? avec succ??s")
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
    private String ImportSkin(ActionEvent event) {
        String idskin=txtid_skin.getText();
        Path to1=null;
        Path to2 = null;
         String  m = null;
         String path = "\\Images\\SkinImages";
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
                    to1 = Paths.get(idskin+".png");
                    to2=Paths.get("src\\"+path+"\\"+idskin+".png");
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
             txt_ImgSkin.setText(to1.toString());
        
    }
      return to1.toString(); 
    }

    @FXML
    private void AfficherSkin(ActionEvent event) {
       String path1= txt_ImgSkin.getText();
       String nom=ListeR.getSelectionModel().getSelectedItem().toString();
        String path2="src"+"\\Images\\SkinImages\\"+path1;
        System.out.println(path2);
        String Path_name = new File(path2).getAbsolutePath();
        System.out.println(Path_name);
        ImageView i = new ImageView();
        File f =  new File (Path_name);
        Image im =new Image(f.toURI().toString());
       
        
         
      
        try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("AfficherSkin.fxml"));
            Parent root=(Parent)loader.load();
            AfficherSkinController ic = loader.getController();
            ic.function(im,nom);
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
    String sql ="SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_SCHEMA = \"black_ops\" AND TABLE_NAME = \"skin\";";
      
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
               int h= rs.getInt("AUTO_INCREMENT");
              String u = Integer.toString(h, 1);
               txtid_skin.setText(u);
            } } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }

    @FXML
    private void fillcomboBox() {
          mc=MaConnexion.getInstance().getCnx();
    try  {
            
             ObservableList options2 =FXCollections.observableArrayList();
             
             
             String sql2="select Nom_Champ from champion";
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

    @FXML
    private void SendMail(ActionEvent event) {
         Client_Controller ac = new Client_Controller();
        List<Client> personnes = ac.afficherclient();
        for (Client l:personnes){
        SendEmail send = new SendEmail(l.getMail_Cl(), "New content is available", "Hey Gamer , New content is available on our application , check it out "); 
   
    }
}
}  
    
    
