/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI.gestion_jeux.gestionChampions;

import black_ops.Controller.ChampionController;
import black_ops.Entity.Champion;
import black_ops.GUI.gestion_jeux.gestionSkins.AfficherSkinController;
import black_ops.config.DB_Connection;

import black_ops.config.MaConnexion;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
public class CRUDchampionController implements Initializable {

    @FXML
    private TextField txtId_jeu;
    @FXML
    private TextField txtdescription_champ;
    @FXML
    private TextField txtRole_champ;
    @FXML
    private TextField txtDiff_champ;
    @FXML
    private TextField txtimg_champ;
    @FXML
    private TableView<Champion> TVChamps;
    @FXML
    private TableColumn<Champion, Integer> colId_champ;
    @FXML
    private TableColumn<Champion, String> colNom_champ;
    @FXML
    private TableColumn<Champion, String> colDescription_champ;
    @FXML
    private TableColumn<Champion, String> colRole_champ;
    @FXML
    private TableColumn<Champion, String> colDiff_champ;
    @FXML
    private TableColumn<Champion, String> colImg_champ;
    @FXML
    private TableColumn<Champion, Integer> colId_jeu;
    @FXML
    private TextField txtid_champ;
    @FXML
    private TextArea txtNom_champ;
     
 
    @FXML
    private TextField txt_search;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btn_delete;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btn_diselect;
    @FXML
    private Button btn_import;
    @FXML
    private Button btnAffichage_img;
    @FXML
    private Button btn_refresh;
    @FXML
    private Button btn_search;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       showchamps();
    }    

    @FXML
    private void handleMouseAction(MouseEvent event) {
        Champion c = TVChamps.getSelectionModel().getSelectedItem();
       txtid_champ.setText(""+c.getId_Champ());
       txtNom_champ.setText(""+c.getNom_Champ());
    txtId_jeu.setText(""+c.getId_jeu());
    txtRole_champ.setText(""+c.getRole_Champ());
     txtDiff_champ.setText(""+c.getDifficulte_Champ());
     txtimg_champ.setText(""+c.getImage_Champ());
      txtdescription_champ.setText(""+c.getDescription_Champ());
    }


    
    public void showchamps()
    {
        ChampionController c1 = new ChampionController();
       
            ObservableList<Champion> list = c1.afficherChampions();
     
     colId_champ.setCellValueFactory(new PropertyValueFactory<Champion,Integer>("Id_Champ"));
     colNom_champ.setCellValueFactory(new PropertyValueFactory<Champion,String>("Nom_Champ") );
     colId_jeu.setCellValueFactory(new PropertyValueFactory<Champion,Integer>("Id_jeu") );
     colRole_champ.setCellValueFactory(new PropertyValueFactory<Champion,String>("Role_Champ") );
     colDiff_champ.setCellValueFactory(new PropertyValueFactory<Champion,String>("Difficulte_Champ") );
     colImg_champ.setCellValueFactory(new PropertyValueFactory<Champion,String>("Image_Champ") );
        
      colDescription_champ.setCellValueFactory(new PropertyValueFactory<Champion,String>("description_Champ") );
     TVChamps.setItems(list);
    }

    @FXML
    private void AddChamp(ActionEvent event) {
        String nomChamp = txtNom_champ.getText();
            String descChamp = txtdescription_champ.getText();
            String Role = txtRole_champ.getText();
            String Diff = txtDiff_champ.getText();
           String id_jeu = txtId_jeu.getText();
           int h = Integer.parseInt(id_jeu);
           String txtimgchamp=txtimg_champ.getText();
            Champion j1 = new Champion(4,nomChamp,descChamp,Role,Diff,txtimgchamp,h);
            
            ChampionController jc1 = new ChampionController();
            jc1.ajouterChampion(j1);
            //notification code 
            String path="src\\ImagesChampions\\confirm.png";
            String Path_name = new File(path).getAbsolutePath();
        System.out.println(Path_name);
        ImageView i = new ImageView();
        File f =  new File (Path_name);
        Image im =new Image(f.toURI().toString());
             
      Notifications notificationBuilder;
        notificationBuilder = Notifications.create()
                .title("champion ajouté avec succès")
                .text("Le champion " +nomChamp+" a bien été ajouté")
                .graphic(new ImageView(im))
                .hideAfter(Duration.seconds(4))
                .position(Pos.TOP_RIGHT)
                
                .onAction((ActionEvent event1) -> {
                    System.out.println("Click on notification");
                });
          
         notificationBuilder.darkStyle();
       notificationBuilder.show();
      
            
            //
             showchamps();
    }

    @FXML
    private void UpdateChamp(ActionEvent event) {
         String idChamp=txtid_champ.getText();
         int v = Integer.parseInt(idChamp);
        String nomChamp = txtNom_champ.getText();
            String descChamp = txtdescription_champ.getText();
            String Role = txtRole_champ.getText();
            String Diff = txtDiff_champ.getText();
            String Image_Url = txtimg_champ.getText();
           String id_jeu = txtId_jeu.getText();
           int h = Integer.parseInt(id_jeu);
             Champion j1 = new Champion(v,nomChamp,descChamp,Role,Diff,Image_Url,h);
            ChampionController jc1 = new ChampionController();
            jc1.updateChampion(j1);
            
            //notif 
            String path="src\\ImagesChampions\\updateicone.png";
            String Path_name = new File(path).getAbsolutePath();
        System.out.println(Path_name);
        ImageView i = new ImageView();
        File f =  new File (Path_name);
        Image im =new Image(f.toURI().toString());
             
      Notifications notificationBuilder;
        notificationBuilder = Notifications.create()
                .title("champion modifié avec succès")
                .text("Le champion " +nomChamp+" a bien été modifié")
                .graphic(new ImageView(im))
                .hideAfter(Duration.seconds(4))
                .position(Pos.TOP_RIGHT)
                
                .onAction((ActionEvent event1) -> {
                    System.out.println("Click on notification");
                });
          
         notificationBuilder.darkStyle();
       notificationBuilder.show();
      
            //
             showchamps();
             
             
             
    }
        
    @FXML
    private void DeleteChamp(ActionEvent event) {
         String nomChamp = txtNom_champ.getText();
         String idChamp=txtid_champ.getText();
         int v = Integer.parseInt(idChamp);
             Champion c1 = new Champion(v);
             ChampionController jc1 = new  ChampionController();
            jc1.deleteChampion(c1);
            //notif 
            String path="src\\ImagesChampions\\suppression.png";
            String Path_name = new File(path).getAbsolutePath();
        System.out.println(Path_name);
        ImageView i = new ImageView();
        File f =  new File (Path_name);
        Image im =new Image(f.toURI().toString());
             
      Notifications notificationBuilder;
        notificationBuilder = Notifications.create()
                .title("champion supprimé avec succès")
                .text("Le champion " +nomChamp+" a bien été supprimé")
                .graphic(new ImageView(im))
                .hideAfter(Duration.seconds(4))
                .position(Pos.TOP_RIGHT)
                
                .onAction((ActionEvent event1) -> {
                    System.out.println("Click on notification");
                });
          
         notificationBuilder.darkStyle();
       notificationBuilder.show();
      
            //
              showchamps();
    }

//    private void JeuScene(ActionEvent event) throws IOException {
//   root = FXMLLoader.load(getClass().getResource("CRUDjeu.fxml"));
//          
//  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//  scene = new Scene(root);
//  stage.setScene(scene);
//  stage.show();
//    }
//
//    private void ImageScene(ActionEvent event) throws IOException {
//        root = FXMLLoader.load(getClass().getResource("CRUDimage.fxml"));
//          
//  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//  scene = new Scene(root);
//  stage.setScene(scene);
//  stage.show();
//    }
//
//    private void SkinScene(ActionEvent event) throws IOException {
//        root = FXMLLoader.load(getClass().getResource("CRUDSkin.fxml"));
//          
//  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//  scene = new Scene(root);
//  stage.setScene(scene);
//  stage.show();
//    }

    @FXML
    private void Refresh(ActionEvent event) {
         ChampionController jc1 = new ChampionController();
            ObservableList<Champion> list = jc1.afficherChampions();
            list.clear();
            showchamps();
    }

    @FXML
    private void Diselect(ActionEvent event) {
         txtid_champ.setText("");
       txtNom_champ.setText("");
     txtdescription_champ.setText("");
    txtRole_champ.setText("");
     txtDiff_champ.setText("");
     txtimg_champ.setText("");
     txtId_jeu.setText("");
        
    }

    @FXML
    private void Search(ActionEvent event) {
         String nomChamp = txt_search.getText();
          ChampionController jc1 = new ChampionController();
       ObservableList<Champion> list2 = jc1.RechercherChampion(nomChamp);
          colId_champ.setCellValueFactory(new PropertyValueFactory<Champion,Integer>("Id_Champ"));
     colNom_champ.setCellValueFactory(new PropertyValueFactory<Champion,String>("Nom_Champ") );
     colDescription_champ.setCellValueFactory(new PropertyValueFactory<Champion,String>("description_Champ") );
     colRole_champ.setCellValueFactory(new PropertyValueFactory<Champion,String>("Role_Champ") );
     colDiff_champ.setCellValueFactory(new PropertyValueFactory<Champion,String>("Difficulte_Champ") );
     colImg_champ.setCellValueFactory(new PropertyValueFactory<Champion,String>("Image_Champ") );
     colId_jeu.setCellValueFactory(new PropertyValueFactory<Champion,Integer>("Id_jeu") );    
     TVChamps.setItems(list2);
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
    private String Importer_Image(ActionEvent event){
        String nomchampion=txtNom_champ.getText();
        Path to2 = null;
        Path to1=null;
         String  m = null;
         String path = "\\ImagesChampions";
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
                    to1=Paths.get(path+"\\"+nomchampion+".png");
                    to2 = Paths.get("src\\"+path+"\\"+nomchampion+".png");
                       
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
             txtimg_champ.setText(to1.toString());
        
    }
      return to1.toString(); 
}

    @FXML
    private void AfficherImage(ActionEvent event) {
       String path1= txtimg_champ.getText();
       String path2="src"+'\\'+path1;
        System.out.println(path2);
       String nom=txtNom_champ.getText();
        String Path_name = new File(path2).getAbsolutePath();
        System.out.println(Path_name);
        ImageView i = new ImageView();
        File f =  new File (Path_name);
        Image im =new Image(f.toURI().toString());
       
        
         
      
        try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("ImageChampion.fxml"));
            Parent root=(Parent)loader.load();
            ImageChampionController ic = loader.getController();
            ic.function(im,nom);
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

    @FXML
    private void exportLogs(ActionEvent event) {
        
            
             JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Only CSV files", "csv");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        String m = null;
        if(returnVal == JFileChooser.APPROVE_OPTION) 
            m= chooser.getSelectedFile().getPath();
       
      
         
        String csvFilePath = m;
          DB_Connection obj_DB_Connection=new DB_Connection();
         Connection connection=obj_DB_Connection.getConnection();
        try  {
            String sql = "SELECT * FROM `general_log` ORDER BY `general_log`.`event_time` DESC";
             
            Statement statement = connection.createStatement();
             
            ResultSet result = statement.executeQuery(sql);
             
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(csvFilePath));
             
            // write header line containing column names       
            fileWriter.write("event_time,user_host,thread_id,server_id,command_type,argument");
             
            while (result.next()) {
                String event_time = result.getString("event_time");
                String user_host = result.getString("user_host");
                String thread_id = result.getString("thread_id");
                String server_id = result.getString("server_id");
                String command_type = result.getString("command_type");
                String argument = result.getString("argument");
                 
                if (argument == null) {
                    argument = "";   
                } else {
                    argument = "\"" + argument + "\""; 
                }
                 
                String line = String.format("\"%s\",%s,%s,%s,%s,%s",
                       event_time,user_host,thread_id,server_id,command_type,argument);
                 
                fileWriter.newLine();
                fileWriter.write(line);            
            }
             
            statement.close();
            fileWriter.close();
             
        } catch (SQLException e) {
            System.out.println("Datababse error:");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("File IO error:");
            e.printStackTrace();
        }
         //notif 
            String path="src\\ImagesChampions\\logs.png";
            String Path_name = new File(path).getAbsolutePath();
        System.out.println(Path_name);
        ImageView i = new ImageView();
        File f =  new File (Path_name);
        Image im =new Image(f.toURI().toString());
             
      Notifications notificationBuilder;
        notificationBuilder = Notifications.create()
                .title("Journalisation terminée avec succès")
                .text("Votre fichier est prêt!")
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
    }

