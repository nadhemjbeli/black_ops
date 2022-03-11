/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.Controller;

import black_ops.Entity.Mode;
import black_ops.config.MaConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author aZiz
 */
public class ModeController {
    
    Connection cnx;
       PreparedStatement ste;
       
       
       
       
       public ModeController(){
           cnx=MaConnexion.getInstance().getCnx();
           
       }
       
       public List<Mode> affichermode(){
              ObservableList<Mode> mode = FXCollections.observableArrayList();
           
           String sql ="SELECT dark_mode , light_mode from mode where id_mode = 1 ";
           
           try{
               ste=cnx.prepareStatement(sql);
          
            ResultSet rs=ste.executeQuery();
            
            while(rs.next()){
                
                Mode md = new Mode();
                
                md.setDark_mode(rs.getInt(1));
                md.setLight_mode(rs.getInt(2));
                mode.add(md);
            }
               
           }catch (SQLException ex){
               
                   System.out.println(ex.getMessage());
           }
           
           return mode;
           
       }
       
       
    public void ModifiermodeDark(){
      String  sql = "UPDATE MODE Set dark_mode=1 , light_mode=0 where id_mode = 1" ;
        try {
            ste=cnx.prepareStatement(sql);
  

        
            ste.executeUpdate();
            System.out.println("mode dark activée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      
        
        
        
    }
    
       public void ModifiermodeLight(){
      String  sql = "UPDATE MODE Set dark_mode=0 , light_mode=1 where id_mode = 1" ;
        try {
            ste=cnx.prepareStatement(sql);
  

        
            ste.executeUpdate();
            System.out.println("mode dark activée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      
        
        
        
    }
    
    
    
}
