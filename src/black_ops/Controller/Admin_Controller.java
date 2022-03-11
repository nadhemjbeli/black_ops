/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.Controller;

import black_ops.Entity.Super;
import black_ops.Entity.Contact;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import black_ops.config.MaConnexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Admin_Controller {
    
    Connection mc;
    PreparedStatement ste;

    public Admin_Controller() {
        mc=MaConnexion.getInstance().getCnx();
    }
    
    
  
    public void ajouterAdmin(Super A){
        String sql ="insert into admin(mail_admin,password_admin,grade,NewPass) Values(?,?,?,?)";
        try {
            ste=mc.prepareStatement(sql);
            ste.setString(1, A.getMail_admin());
            ste.setString(2, A.getPassword_admin());
            ste.setInt(3, A.getGrade());
            ste.setInt(4, A.getNewPass());
            ste.executeUpdate();
            System.out.println("Personne Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    public void UpdateAdmin(Super A){
        String sql ="update admin set mail_admin = ? , password_admin = ? ,grade =? where Id_admin = ? ";
        try {
            ste=mc.prepareStatement(sql);
            ste.setString(1, A.getMail_admin());
            ste.setString(2,A.getPassword_admin() );
            ste.setInt(3, A.getGrade());
            ste.setInt(4, A.getId_admin());
            ste.executeUpdate();
            System.out.println("Personne modifier");
            ste.close();
      
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    public ObservableList<Super> afficherPersonne(){
        ObservableList<Super> personnes ;
        personnes=FXCollections.observableArrayList();
        
        String sql="select * from admin";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Super A = new Super();
                A.setId_admin(rs.getInt("Id_admin"));
                A.setMail_admin(rs.getString("Mail_admin"));
                A.setPassword_admin(rs.getString("Password_admin"));
                A.setGrade(rs.getInt("Grade"));
                A.setNewPass(rs.getInt("NewPass"));
                personnes.add(A);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return personnes;
    }    
    public void DeletePersonne(Super A ){
        
        String sql ="delete from admin where Id_admin = ?";
        try{
        ste=mc.prepareStatement(sql);
        ste.setInt(1, A.getId_admin() );
        ste.executeUpdate();
        System.out.println("Personne supprimer");
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void ajoutercontact(Contact A) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
