/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.GUI.gestion_commande.UI;

import black_ops.Controller.CommandeController;
import black_ops.Controller.LigneCommController;
import black_ops.Entity.Commande;
import black_ops.Entity.LigneComm;
import black_ops.config.MaConnexion;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class Annuller_CommandeController implements Initializable {

    @FXML
    private Button btn_delete_commande;
    @FXML
    private TextField idc_supp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void Annuller_Ma_Commande(ActionEvent event) {
          try {
         String Id_Commande;
        Id_Commande = idc_supp.getText();
         int idc = Integer.parseInt(Id_Commande);
            
            
            Connection cnx = MaConnexion.getInstance().getCnx();
            String sql="select * from lignecommande";
            PreparedStatement ste=cnx.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            int idc1 = 0,idlc1=0;
            while(rs.next()){
                idc1 = rs.getInt("id_commande");
                idlc1=rs.getInt("id_LigneCommande");
                if( idc1 == idc){
                    LigneComm lc1 = new LigneComm(idlc1);
                   LigneCommController lcc = new LigneCommController();
                   lcc.supprimerLigneComm(idlc1);   
                }
                 LigneComm lc1 = new LigneComm();   
            }
            Commande c = new Commande(idc);
            CommandeController cc = new CommandeController();
            cc.supprimerCommande(idc);
           
           }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Commande Introuvale");
        }
    }
    
}
