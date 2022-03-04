/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI.gestion_jeux.gestionjeu;

import black_ops.Controller.JeuController;
import black_ops.Entity.Jeu;
import black_ops.config.MaConnexion;
import com.jfoenix.controls.JFXComboBox;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author jmokh
 */
public class CRUDJeuController implements Initializable {

    @FXML
    private TableView<Jeu> TVGames;
    @FXML
    private TableColumn<Jeu,Integer> colId;
    @FXML
    private TableColumn<Jeu, String> colNom;
    @FXML
    private TableColumn<Jeu, String> colDescription;
    @FXML
    private TableColumn<Jeu, String> colUrl;
    @FXML
    private TableColumn<Jeu, Integer> colIdSoucat;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TextField txtid;
    @FXML
    private TextArea txtnom;
    @FXML
    private TextField txtdescription;
    @FXML
    private TextField txturl;
    private TextField txtIdScat;
     private Stage stage;
 private Scene scene;
 private Parent root;
    @FXML
    private Button btn_diselect;
    @FXML
    private Button btn_refresh;
    @FXML
    private TextField txt_search;
    @FXML
    private Button btn_search;
    @FXML
    public ComboBox ListeR;
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
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       showgames();
       fillcomboBox();
       ComboBoxListInti();
    } 
     
    
    @FXML
    private void AddGame(ActionEvent event) {
         int k=0;
        String id_jeu=ListeR.getSelectionModel().getSelectedItem().toString();
          try {
             
               String sql2="select id_SousCat from sous_categorie where nom_SousCat=?";
               
             mc=MaConnexion.getInstance().getCnx();
             ste=mc.prepareStatement(sql2);
             
              
              ste.setString(1,id_jeu);
              ResultSet rs=ste.executeQuery();
               while(rs.next()){
             k=rs.getInt("id_SousCat");
            
            
        } }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         
            
            String nom = txtnom.getText();
            String desc = txtdescription.getText();
            String url = txturl.getText();
         
             Jeu j1 = new Jeu(4,nom,desc,url,k);
            JeuController jc1 = new JeuController();
            jc1.ajouterJeu(j1);
            //notification code 
            String path="src\\ImagesChampions\\confirm.png";
            String Path_name = new File(path).getAbsolutePath();
        System.out.println(Path_name);
        ImageView i = new ImageView();
        File f =  new File (Path_name);
        Image im =new Image(f.toURI().toString());
             
      Notifications notificationBuilder;
        notificationBuilder = Notifications.create()
                .title("jeu ajouté avec succès")
                .text("Le jeu " +nom+" a bien été ajouté")
                .graphic(new ImageView(im))
                .hideAfter(Duration.seconds(4))
                .position(Pos.TOP_RIGHT)
                
                .onAction((ActionEvent event1) -> {
                    System.out.println("Click on notification");
                });
          
         notificationBuilder.darkStyle();
       notificationBuilder.show();
      
            
            //
             showgames();
    }
    @FXML
    private void UpdateGame(ActionEvent event) {
            int k=0;
        String id_jeu=ListeR.getSelectionModel().getSelectedItem().toString();
          try {
             
               String sql2="select id_SousCat from sous_categorie where nom_SousCat=?";
               
             mc=MaConnexion.getInstance().getCnx();
             ste=mc.prepareStatement(sql2);
             
              
              ste.setString(1,id_jeu);
              ResultSet rs=ste.executeQuery();
               while(rs.next()){
             k=rs.getInt("id_SousCat");
            
            
        } }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        String idJeu=txtid.getText();
         int v = Integer.parseInt(idJeu);
        String nom = txtnom.getText();
            String desc = txtdescription.getText();
            String url = txturl.getText();
          
             Jeu j1 = new Jeu(v,nom,desc,url,k);
            JeuController jc1 = new JeuController();
            jc1.updateJeu(j1);
            //notif 
            String path="src\\ImagesChampions\\updateicone.png";
            String Path_name = new File(path).getAbsolutePath();
        System.out.println(Path_name);
        ImageView i = new ImageView();
        File f =  new File (Path_name);
        Image im =new Image(f.toURI().toString());
             
      Notifications notificationBuilder;
        notificationBuilder = Notifications.create()
                .title("jeu modifié avec succès")
                .text("Le jeu " +nom+" a bien été modifié")
                .graphic(new ImageView(im))
                .hideAfter(Duration.seconds(4))
                .position(Pos.TOP_RIGHT)
                
                .onAction((ActionEvent event1) -> {
                    System.out.println("Click on notification");
                });
          
         notificationBuilder.darkStyle();
       notificationBuilder.show();
      
            //
             showgames();
    }

    @FXML
    private void DeleteGame(ActionEvent event) {
         String nom = txtnom.getText();
        String idJeu=txtid.getText();
         int v = Integer.parseInt(idJeu);
             Jeu j1 = new Jeu(v);
            JeuController jc1 = new JeuController();
            jc1.deletejeu(j1);
              //notif 
            String path="src\\ImagesChampions\\suppression.png";
            String Path_name = new File(path).getAbsolutePath();
        System.out.println(Path_name);
        ImageView i = new ImageView();
        File f =  new File (Path_name);
        Image im =new Image(f.toURI().toString());
             
      Notifications notificationBuilder;
        notificationBuilder = Notifications.create()
                .title("jeu supprimé avec succès")
                .text("Le jeu " +nom+" a bien été supprimé")
                .graphic(new ImageView(im))
                .hideAfter(Duration.seconds(4))
                .position(Pos.TOP_RIGHT)
                
                .onAction((ActionEvent event1) -> {
                    System.out.println("Click on notification");
                });
          
         notificationBuilder.darkStyle();
       notificationBuilder.show();
      
            //
             showgames();
        
    }
    public void showgames()
    {
        JeuController jc1 = new JeuController();
       
            ObservableList<Jeu> list = jc1.AfficherJeux();
     
     colId.setCellValueFactory(new PropertyValueFactory<Jeu,Integer>("Id_Jeu"));
     colNom.setCellValueFactory(new PropertyValueFactory<Jeu,String>("Nom") );
     colIdSoucat.setCellValueFactory(new PropertyValueFactory<Jeu,Integer>("id_souscat") );
     colUrl.setCellValueFactory(new PropertyValueFactory<Jeu,String>("Url") );
    colDescription.setCellValueFactory(new PropertyValueFactory<Jeu,String>("description") );
     TVGames.setItems(list);
    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
    Jeu j = TVGames.getSelectionModel().getSelectedItem();
       txtid.setText(""+j.getId_Jeu());
     txtnom.setText(""+j.getNom());
       try {
             
               String sql2="select nom_SousCat from sous_categorie where id_SousCat=?";
               
             mc=MaConnexion.getInstance().getCnx();
             ste=mc.prepareStatement(sql2);
             
              
              ste.setInt(1,j.getId_souscat());
              ResultSet rs=ste.executeQuery();
               while(rs.next()){
        String     k=rs.getString("nom_SousCat");
        ListeR.setValue(k);
            
            
        } }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     txturl.setText(""+j.getUrl());
    txtdescription.setText(""+j.getDescription());
         
    }

    private void ImageScene(ActionEvent event) throws IOException {
        
  root = FXMLLoader.load(getClass().getResource("CRUDimage.fxml"));
          
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
    }

    private void ChampSccene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CRUDchampion.fxml"));
          
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
    }

    private void SkinScene(ActionEvent event) throws IOException {
   root = FXMLLoader.load(getClass().getResource("CRUDSkin.fxml"));     
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
        
    }

    @FXML
    private void Refresh(ActionEvent event) {
         JeuController jc1 = new JeuController();
       
            ObservableList<Jeu> list = jc1.AfficherJeux();
            list.clear();
            showgames();
        
    }

    @FXML
    private void disselect(ActionEvent event) {
       
       txtid.setText("");
     txtnom.setText("");
    txtdescription.setText("");
     txturl.setText("");
     ListeR.setValue("");
    }

    @FXML
    private void SearchNom(ActionEvent event) 
    { JeuController jc1 = new JeuController();
       String nom = txt_search.getText();
            ObservableList<Jeu> list = jc1.RechercherJeux(nom);
     
     colId.setCellValueFactory(new PropertyValueFactory<Jeu,Integer>("Id_Jeu"));
     colNom.setCellValueFactory(new PropertyValueFactory<Jeu,String>("Nom") );
     colDescription.setCellValueFactory(new PropertyValueFactory<Jeu,String>("description") );
     colUrl.setCellValueFactory(new PropertyValueFactory<Jeu,String>("Url") );
     colIdSoucat.setCellValueFactory(new PropertyValueFactory<Jeu,Integer>("id_souscat") );
     TVGames.setItems(list);
      //notif 
            String path="src\\ImagesChampions\\Recherche.png";
            String Path_name = new File(path).getAbsolutePath();
        System.out.println(Path_name);
        ImageView i = new ImageView();
        File f =  new File (Path_name);
        Image im =new Image(f.toURI().toString());
             
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
      
            //
        
    
}

    @FXML
    private void fillcomboBox() {
         mc=MaConnexion.getInstance().getCnx();
    try  {
            
             ObservableList options2 =FXCollections.observableArrayList();
             
             
             String sql2="select nom_SousCat from sous_categorie";
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




