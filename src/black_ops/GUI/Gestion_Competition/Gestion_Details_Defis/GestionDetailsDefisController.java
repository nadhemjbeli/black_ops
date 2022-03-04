/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI.Gestion_Competition.Gestion_Details_Defis;

import black_ops.Controller.DetailsDefiController;
import black_ops.Entity.DetailsDefi;
import black_ops.config.MaConnexion;
import com.jfoenix.controls.JFXComboBox;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
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
    private TextField inp_A;
    @FXML
    private TextField inp_url;
    private TextField inp_B;
    @FXML
    private TextField inp_score_finale;
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
    private Button show;
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
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private ComboBox ListEquipeA;
    @FXML
    private ComboBox ListEquipeB;
    @FXML
    private ComboBox ListDefi;
    Connection mc;
    PreparedStatement ste;
    PreparedStatement ste1;
    PreparedStatement ste2;
    @FXML
    private Button imp;

    /**
     * Initializes the controller class.
     */
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        Show_Match();
        QR.setDisable(false);
        ComboBoxListInti();
        fillcomboBox();
    }
    @FXML
    private void Modifier_Match(ActionEvent event) {
        int k = 0;
        String id_eqa = ListEquipeA.getSelectionModel().getSelectedItem().toString();
        int k1 = 0;
        String id_eqb = ListEquipeB.getSelectionModel().getSelectedItem().toString();
        int k2 = 0;
        String id_d = ListDefi.getSelectionModel().getSelectedItem().toString();
        String Id_stat = inp_Id_stat.getText();
        int id = Integer.parseInt(Id_stat);
        String url = inp_url.getText();
        String score = inp_score_finale.getText();
        
        try {

            String sql2 = "select id_equipe from equipe where nom_Equipe=?";
            String sql3 = "select id_equipe from equipe where nom_Equipe=?";
            String sql4 = "select id_Defi from defi where nom_Defi=?";
            mc = MaConnexion.getInstance().getCnx();
            ste = mc.prepareStatement(sql2);
            ste1 = mc.prepareStatement(sql3);
            ste2 = mc.prepareStatement(sql4);
            ste.setString(1, id_eqa);
            ste1.setString(1, id_eqb);
            ste2.setString(1, id_d);
            ResultSet rs = ste.executeQuery();
            ResultSet rs1 = ste1.executeQuery();
            ResultSet rs2 = ste2.executeQuery();
            while (rs.next()) {
                k = rs.getInt("id_equipe");
                
            }
            while (rs1.next()) {
                
                k1 = rs1.getInt("id_equipe");
               
            }
            while (rs2.next()) {
               
                k2 = rs2.getInt("id_Defi");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

       
       
        DetailsDefi dfi = new DetailsDefi(id, k, url, k1, score, k2);
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
         int k = 0;
        String id_eqa = ListEquipeA.getSelectionModel().getSelectedItem().toString();
        int k1 = 0;
        String id_eqb = ListEquipeB.getSelectionModel().getSelectedItem().toString();
        int k2 = 0;
        String id_d = ListDefi.getSelectionModel().getSelectedItem().toString();
      
        String url = inp_url.getText();
        String score = inp_score_finale.getText();
        
       
        try {

            String sql2 = "select id_equipe from equipe where nom_Equipe=?";
            String sql3 = "select id_equipe from equipe where nom_Equipe=?";
            String sql4 = "select id_Defi from defi where nom_Defi=?";
            mc = MaConnexion.getInstance().getCnx();
            ste = mc.prepareStatement(sql2);
            ste1 = mc.prepareStatement(sql3);
            ste2 = mc.prepareStatement(sql4);
            ste.setString(1, id_eqa);
            ste1.setString(1, id_eqb);
            ste2.setString(1, id_d);
            ResultSet rs = ste.executeQuery();
            ResultSet rs1 = ste1.executeQuery();
            ResultSet rs2 = ste2.executeQuery();
            while (rs.next()) {
                k = rs.getInt("id_equipe");
               
            }
            while (rs1.next()) {
              
                k1 = rs1.getInt("id_equipe");
               
            }
            while (rs2.next()) {
                
                
                k2 = rs2.getInt("id_defi");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        DetailsDefi dfi = new DetailsDefi(33, k, url, k1, score, k2);
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
                .to(ImageType.PNG).stream();
        String f_name = Id_stat;
        String Path_name = new File("src/QrCode/").getAbsolutePath();
        try {
            FileOutputStream fout = new FileOutputStream(new File(Path_name + "/" + (f_name + ".PNG")));
            fout.write(out.toByteArray());
            fout.flush();
            System.out.println(Path_name);

        } catch (Exception e) {
            System.out.println(e);

        }

        affichImage();
    }

    @FXML
    private void Recherche_Defi(ActionEvent event) {
    }

    private void Show_Match() {

        DetailsDefiController dfc = new DetailsDefiController();
        ObservableList<DetailsDefi> list = dfc.View_Details_defi();
        cl_id.setCellValueFactory(new PropertyValueFactory<DetailsDefi, Integer>("id_Statistique"));
        cl_A.setCellValueFactory(new PropertyValueFactory<DetailsDefi, Integer>("EquipeA"));
        cl_url.setCellValueFactory(new PropertyValueFactory<DetailsDefi, String>("imgScore"));
        cl_B.setCellValueFactory(new PropertyValueFactory<DetailsDefi, Integer>("EquipeB"));
        cl_Score.setCellValueFactory(new PropertyValueFactory<DetailsDefi, String>("Score_finale"));
        cl_defi.setCellValueFactory(new PropertyValueFactory<DetailsDefi, Integer>("id_defi"));

        table.setItems(list);
    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
        DetailsDefi match = table.getSelectionModel().getSelectedItem();
        inp_Id_stat.setText("" + match.getId_Statistique());

        inp_url.setText("" + match.getImgScore());

        inp_score_finale.setText("" + match.getScore_finale());

        String Path_name = new File("src/QrCode/").getAbsolutePath();
        File dir = new File(Path_name);
        File[] liste = dir.listFiles();
        String id = inp_Id_stat.getText() + ".PNG";
        for (File item : liste) {
            if (item.isFile()) {
                if (item.getName().equals(id)) {
                    System.out.println(true);
                    QR.setDisable(true);
                    affichImage();

                    break;
                } else {
                    QRimg.setImage(null);
                    QR.setDisable(false);
                }

            }

        }
        try {
            String sql2 = "select nom_Equipe from equipe where id_equipe=?";
            String sql3 = "select nom_Equipe from equipe where id_equipe=?";
            String sql4 = "select nom_Defi from defi where id_Defi=?";
            mc = MaConnexion.getInstance().getCnx();
            ste = mc.prepareStatement(sql2);
            ste1 = mc.prepareStatement(sql3);
            ste2 = mc.prepareStatement(sql4);
            ste.setInt(1, match.getEquipeA());
            ste1.setInt(1, match.getEquipeB());
            ste2.setInt(1, match.getId_defi());
            ResultSet rs = ste.executeQuery();
            ResultSet rs1 = ste1.executeQuery();
            ResultSet rs2 = ste2.executeQuery();
            while (rs.next()) {
                String k = rs.getString("nom_Equipe");  
                ListEquipeA.setValue(k);   
               
            }
            while (rs1.next()) {     
                String k1 = rs1.getString("nom_Equipe");
                ListEquipeB.setValue(k1);  
            }
            while (rs2.next()) { 
                String k2 = rs2.getString("nom_Defi");
                ListDefi.setValue(k2);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void disselect(ActionEvent event) {
        inp_Id_stat.setText("");

        inp_url.setText("");

        inp_score_finale.setText("");

        QRimg.setImage(null);
        ListEquipeA.setValue("");
        ListEquipeB.setValue("");
        ListDefi.setValue("");
    }

    private void refreshTable(ActionEvent event) {
        DetailsDefiController D_DefiC = new DetailsDefiController();
        ObservableList<DetailsDefi> list = D_DefiC.View_Details_defi();
        list.clear();
        Show_Match();

    }

    public void affichImage() {
        String Id_stat = inp_Id_stat.getText();
        String f_name = Id_stat;
        String Path_name = new File("src/QrCode/").getAbsolutePath();
        String image = Path_name + "\\" + Id_stat + ".PNG";
        //System.out.println(image);
        ImageView i = new ImageView();
        File f = new File(image);
        Image im = new Image(f.toURI().toString());
        QRimg.setImage(im);
    }

    @FXML
    private void show_nav_bar(ActionEvent event) {
        nav.setVisible(true);
        hide.setVisible(true);
        show.setVisible(false);

    }

//nav
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
    private void showU(ActionEvent event) throws IOException, Exception {
        SwitchScene(stage, "Gestion_Competition/Gestion_Defis/GestionDefi", event);
    }

    @FXML
    private void showCommande(ActionEvent event) throws Exception {
        String s = gCom.getSelectionModel().getSelectedItem().toString();
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
    private void showCat(ActionEvent event) throws Exception {
        String s = gcat.getSelectionModel().getSelectedItem().toString();
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
    private void showS(ActionEvent event) throws Exception {
        String s = gS.getSelectionModel().getSelectedItem().toString();
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
    private void showJ(ActionEvent event) throws Exception {
        String s = gJ.getSelectionModel().getSelectedItem().toString();
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

    public void SwitchScene(Stage stage, String nom, ActionEvent event) throws Exception {

        String path = "/black_ops/GUI/";

        FXMLLoader loader = new FXMLLoader(getClass().getResource(path + nom + ".fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

   

    public void ComboBoxListInti() {
        nav.setVisible(false);
        ObservableList<String> GestionCommunaute = FXCollections.observableArrayList(" Gestion Message", "Gestion Video");
        gc.setItems(GestionCommunaute);
        ObservableList<String> GestionCompetition = FXCollections.observableArrayList("Gestion Defi", "Gestion Matches", "Gestion Joueurs", "Gestion Equipe");
        gcp.setItems(GestionCompetition);
        ObservableList<String> GestionCommande = FXCollections.observableArrayList("Gestion Commande", "Gestion  Ligne Commande");
        gCom.setItems(GestionCommande);
        ObservableList<String> GestionCategorie = FXCollections.observableArrayList("Gestion Categorie", "Gestion  SousCategorie");
        gcat.setItems(GestionCategorie);
        ObservableList<String> GestionStream = FXCollections.observableArrayList("Stream Replay ", "Stream Info ");
        gS.setItems(GestionStream);
        ObservableList<String> GestionJeu = FXCollections.observableArrayList("Gestion Jeu", "Gestion  Image", "Gestion  Skin", "Gestion  Champion");
        gJ.setItems(GestionJeu);
    }

    private void fillcomboBox() {

        mc = MaConnexion.getInstance().getCnx();
        try {

            ObservableList options = FXCollections.observableArrayList();
            ObservableList options1 = FXCollections.observableArrayList();
            ObservableList options2 = FXCollections.observableArrayList();
            String sql2 = "select nom_Equipe from Equipe";
            String sql3 = "select nom_Equipe from Equipe";
            String sql4 = "select nom_Defi from defi";
            
            ResultSet rs = mc.createStatement().executeQuery(sql2);
            ResultSet rs1 = mc.createStatement().executeQuery(sql3);
            ResultSet rs2 = mc.createStatement().executeQuery(sql4);
            while (rs.next()) {
                options.add(rs.getString(1));
            }
            while (rs1.next()) {
                options1.add(rs1.getString(1));
            }
            while (rs2.next()) {
                options2.add(rs2.getString(1));
            }
            ListEquipeA.setItems(options);
            ListEquipeB.setItems(options1);
            ListDefi.setItems(options2);
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }

    @FXML
    private String importImg(ActionEvent event) {
         int k = 0;
        String id_eqa = ListEquipeA.getSelectionModel().getSelectedItem().toString();
        int k1 = 0;
        String id_eqb = ListEquipeB.getSelectionModel().getSelectedItem().toString();
        int k2 = 0;
        String id_d = ListDefi.getSelectionModel().getSelectedItem().toString();
      
        
        String nom = id_eqa +" vs "+ id_eqb;
        Path to = null;
        String m = null;
        String path = "src/Image/Matches";
        JFileChooser chooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & PNG Images", "jpg", "jpeg", "PNG");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            m = chooser.getSelectedFile().getAbsolutePath();
//            System.out.println("You chose to open this file: " +m
//                    );

            if (chooser.getSelectedFile() != null) {

                try {
                    Path from = Paths.get(chooser.getSelectedFile().toURI());
                    to = Paths.get(path + "\\" + nom + ".png");
                    CopyOption[] options = new CopyOption[]{
                        StandardCopyOption.REPLACE_EXISTING,
                        StandardCopyOption.COPY_ATTRIBUTES
                    };
                    Files.copy(from, to, options);
                    System.out.println("added");
//                saveSystem(selectedFile, )
                    System.out.println(to);

                } catch (IOException ex) {
                    System.out.println();
                }
            }
            inp_url.setText(to.toString());

        }
        return to.toString();
    }
}
