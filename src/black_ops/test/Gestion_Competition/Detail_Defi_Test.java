/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.test.Gestion_Competition;

import black_ops.Controller.Details_DefiController;
import black_ops.Entity.Details_Defi;
import black_ops.config.MaConnexion;
import java.util.Calendar;


/**
 *
 * @author medaz
 */
public class Detail_Defi_Test {


    public static void main(String[] args) {
         MaConnexion db = MaConnexion.getInstance();
         
        java.util.Date date = Calendar.getInstance().getTime();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime()); 
        
      Details_DefiController D_DefiC = new Details_DefiController();
      Details_Defi match1 = new Details_Defi(3,1,"A",2,"B",1);
      Details_Defi sql_match1 = new Details_Defi(2);

      Details_Defi match2 = new Details_Defi(2,1,"_",2,"", 2);
//      D_DefiC.Create_Details_Defi(match2);
//      D_DefiC.Update_Details_Defi(match2);
   //     System.out.println(D_DefiC.Select_Detail(sql_match1).toString());
//      D_DefiC.Delete_Details_Defi(match2);
//      System.out.println(D_DefiC.View_Details_defi());
    }
    
}
