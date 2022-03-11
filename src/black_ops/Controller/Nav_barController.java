/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.Controller;

import black_ops.Entity.Nav_Bar;
import black_ops.config.MaConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author aZiz
 */
public class Nav_barController {
        Connection cnx;
       PreparedStatement ste;
       
       
       
       
       public Nav_barController(){
           cnx=MaConnexion.getInstance().getCnx();
           
       }
       
       
       public ObservableList<Nav_Bar> afficherNavBar(){
        ObservableList<Nav_Bar> NavBar = FXCollections.observableArrayList();;
        String sql="select * from sous_categorie where id_cat = (Select id_cat from categorie where nom_cat = 'Stream')";
           //System.out.println(sql);
        try {
            ste=cnx.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Nav_Bar nb = new Nav_Bar();
         nb.setId(rs.getInt(1));
          nb.setNom(rs.getString(2));
           nb.setId_cat(rs.getInt(3));
                NavBar.add(nb);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return NavBar;
    } 
       
       
}
