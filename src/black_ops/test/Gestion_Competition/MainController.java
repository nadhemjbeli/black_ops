/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.test.Gestion_Competition;

import black_ops.Controller.Details_DefiController;
import black_ops.Entity.Details_Defi;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

/**
 * FXML Controller class
 *
 * @author medaz
 */
public class MainController implements Initializable {

    @FXML
    private Button jButton1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void BtnQr(ActionEvent event) {
        Details_DefiController D_DefiC = new Details_DefiController();
        Details_Defi sql_match1 = new Details_Defi(2);
        String Match = D_DefiC.Select_Detail(sql_match1).toString();
        ByteArrayOutputStream out = QRCode.from(Match)
                    .to(ImageType.PNG).stream() ;
            String f_name = "Match";
            String Path_name = "C:\\Users\\medaz\\Documents\\NetBeansProjects\\Black_ops\\src\\QrCode\\";
            try{
            FileOutputStream fout = new FileOutputStream(new File(Path_name+ (f_name +".PNG")) );
            fout.write(out.toByteArray());
            fout.flush();
        }catch(Exception e){
            System.out.println(e);
            
        }
        
    }
    
}
