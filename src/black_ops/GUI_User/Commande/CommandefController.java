/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI_User.Commande;

import black_ops.Controller.CommandeController;
import black_ops.Controller.LigneCommController;
import black_ops.Entity.Commande;

import black_ops.Entity.Defi;
import black_ops.Entity.Equipe;
import black_ops.Entity.LigneComm;
import black_ops.config.MaConnexion;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author medaz
 */
public class CommandefController implements Initializable {

    @FXML
    private ImageView imgdef;
    @FXML
    private Label prix;
    @FXML
    private Label nomdef;
    @FXML
    private TextField id;
    Connection mc;
    PreparedStatement ste2,ste;
    public static int idcli = 0;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
    }    
    public void SetNom(String txt,String pr,String idd)
    { 
        nomdef.setText(txt);
        prix.setText(pr);
        id.setText(idd);
    }
     public int Select(){
        int a = 0 ;
          mc = MaConnexion.getInstance().getCnx();
        String sql="SELECT id_Commande FROM commande order by id_Commande desc  " ;

        try{
        ste2 = mc.prepareStatement(sql);
         
        ResultSet rs=ste2.executeQuery();
           while(rs.next()){             
              a = rs.getInt(1);
break ;                
           }
        }catch (SQLException ex) {
             Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
        return a ;
} 
    @FXML
    private void validerCom(ActionEvent event) {
       CommandeController cm = new CommandeController();
       Commande c  = new Commande("Non Traite",idcli);
       cm.ajouterCommande(c);
       int c1 = Select();
       
       int pp = Integer.parseInt(prix.getText());
       int idd= Integer.parseInt(id.getText());
      LigneCommController lcm = new LigneCommController();
      LigneComm lc = new LigneComm(1,pp,idd,c1);
      lcm.ajouterLigneComm(lc);
      int options = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(null, lc.toString1() ,"SERIOUS QUESTION", options, 3);
        if (result == JOptionPane.YES_OPTION) {
           System.exit(0);
        }  
     
      System.out.println("");
    }
}
